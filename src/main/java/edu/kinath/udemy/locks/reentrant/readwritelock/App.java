package edu.kinath.udemy.locks.reentrant.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

public class App
{
    public static final int HIGHEST_PRICE = 1000;

    public static void main( String[] args )
    {
        InventoryDatabase inventoryDatabase = new InventoryDatabase();
        Random random = new Random();

        for( int i = 0; i < 10000; i++ )
        {
            inventoryDatabase.addItem( random.nextInt( HIGHEST_PRICE ) );
        }

        Thread writer = new Thread( () -> {
            while( true )
            {
                inventoryDatabase.addItem( random.nextInt( HIGHEST_PRICE ) );
                inventoryDatabase.removeItem( random.nextInt( HIGHEST_PRICE ) );

                try
                {
                    Thread.sleep( 10 );
                }
                catch( InterruptedException e )
                {
                    e.printStackTrace();
                }
            }
        } );

        writer.setDaemon( true );
        writer.start();

        int numberOfReaderThreads = 7;
        List<Thread> threadList = new ArrayList<>();

        for( int i = 0; i < numberOfReaderThreads; i++ )
        {
            Thread reader = new Thread( () -> {
                for( int j = 0; j < 10000; j++ )
                {
                    int upperBoundPrice = random.nextInt( HIGHEST_PRICE );
                    int lowerBoundPrice = upperBoundPrice > 0 ? random.nextInt( upperBoundPrice ) : 0;
                    inventoryDatabase.getNumberOfItemsInPriceRange( lowerBoundPrice, upperBoundPrice );
                }
            } );

            reader.setDaemon( true );
            threadList.add( reader );
        }

        long start = System.currentTimeMillis();

        threadList.forEach( Thread::start );
        threadList.forEach( r -> {
            try
            {
                r.join();
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }
        } );

        long end = System.currentTimeMillis();

        System.out.println("Reading took : " + (end - start) + " ms");

    }
}

class InventoryDatabase
{
    private TreeMap<Integer, Integer> priceCountMap = new TreeMap<>();
    ReentrantLock lock = new ReentrantLock();

    public int getNumberOfItemsInPriceRange( int lowerBound, int upperBound )
    {
        lock.lock();

        try
        {
            Integer fromKey = priceCountMap.ceilingKey( lowerBound );
            Integer toKey = priceCountMap.floorKey( upperBound );

            if( fromKey == null || toKey == null )
            {
                return 0;
            }

            NavigableMap<Integer, Integer> rangeOfPrices = priceCountMap.subMap( fromKey, true, toKey, true );

            int sum = 0;
            for( int numOfItemsForPrice : rangeOfPrices.values() )
            {
                sum += numOfItemsForPrice;
            }

            return sum;
        }
        finally
        {
            lock.unlock();
        }
    }

    public void addItem( int price )
    {
        lock.lock();
        try
        {
            Integer numberOfItemsForPrice = priceCountMap.get( price );

            if( numberOfItemsForPrice == null )
            {
                priceCountMap.put( price, 1 );
            }
            else
            {
                priceCountMap.put( price, numberOfItemsForPrice + 1 );
            }
        }
        finally
        {
            lock.unlock();
        }
    }

    public void removeItem( int price )
    {
        lock.lock();

        try
        {
            Integer numberOfItemsForPrice = priceCountMap.get( price );
            if( numberOfItemsForPrice == null || numberOfItemsForPrice == 1 )
            {
                priceCountMap.remove( price );
            }
            else
            {
                priceCountMap.put( price, numberOfItemsForPrice - 1 );
            }
        }
        finally
        {
            lock.unlock();
        }
    }
}
