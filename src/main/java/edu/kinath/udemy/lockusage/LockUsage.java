package edu.kinath.udemy.lockusage;

public class LockUsage
{
    public static void main( String[] args ) throws InterruptedException
    {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread( inventoryCounter );
        DecrementingThread decrementingThread = new DecrementingThread( inventoryCounter );

        incrementingThread.start();
        decrementingThread.start();
        decrementingThread.join();
        incrementingThread.join();

        System.out.println( "Remaining Inventory : " + inventoryCounter.getCount() );
    }
}

class IncrementingThread extends Thread
{
    InventoryCounter inventoryCounter;

    public IncrementingThread( InventoryCounter inventoryCounter )
    {
        this.inventoryCounter = inventoryCounter;
    }

    @Override
    public void run()
    {
        for( int i = 0; i < 10000; i++ )
        {
            inventoryCounter.increment();
        }
    }
}

class DecrementingThread extends Thread
{
    InventoryCounter inventoryCounter;

    public DecrementingThread( InventoryCounter inventoryCounter )
    {
        this.inventoryCounter = inventoryCounter;
    }

    @Override
    public void run()
    {
        for( int i = 0; i < 10000; i++ )
        {
            inventoryCounter.decrement();
        }
    }
}


class InventoryCounter
{
    int count = 0;
    Object lock = new Object();

    public void increment()
    {
        synchronized( this.lock )
        {
            count++;
        }
    }

    public synchronized void decrement()
    {
        synchronized( this.lock )
        {
            count--;
        }
    }

    public synchronized int getCount()
    {
        synchronized( this.lock )
        {
            return count;
        }
    }
}
