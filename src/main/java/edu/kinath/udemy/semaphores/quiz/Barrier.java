package edu.kinath.udemy.semaphores.quiz;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier
{
    private final int numberOfWorkers;
    private Semaphore semaphore = new Semaphore( 0 ); // no of permits
    private int counter = 0; //counter
    private Lock lock = new ReentrantLock();

    public Barrier( int numberOfWorkers )
    {
        this.numberOfWorkers = numberOfWorkers;
    }

    public void barrier()
    {
        lock.lock();
        boolean isLastWorker = false;
        try
        {
            counter++;

            if( counter == numberOfWorkers )
            {
                isLastWorker = true;
            }
        }
        finally
        {
            lock.unlock();
        }

        if( isLastWorker )
        {
            semaphore.release( numberOfWorkers - 1 ); // no of permits
        }
        else
        {
            try
            {
                semaphore.acquire();
            }
            catch( InterruptedException e )
            {
            }
        }
    }

    public Semaphore getSemaphore()
    {
        return semaphore;
    }
}
