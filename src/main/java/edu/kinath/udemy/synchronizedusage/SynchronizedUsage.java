package edu.kinath.udemy.synchronizedusage;

public class SynchronizedUsage
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

    public synchronized void increment()
    {
        count++;
    }

    public synchronized void decrement()
    {
        count--;
    }

    public synchronized int getCount()
    {
        return count;
    }
}
