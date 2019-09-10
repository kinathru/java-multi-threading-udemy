package edu.kinath.udemy.semaphores.quiz;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws InterruptedException
    {
        int numberOfThreads = 101; //or any number you'd like

        List<Thread> threads = new ArrayList<>();

        Barrier barrier = new Barrier( numberOfThreads );
        for( int i = 0; i < numberOfThreads; i++ )
        {
            threads.add( new Thread( new CoordinatedWorkRunner( barrier ) ) );
        }

        System.out.println( "Permits : " + barrier.getSemaphore().availablePermits() );

        for( Thread thread : threads )
        {
            thread.start();
        }

        for( Thread thread : threads )
        {
            thread.join();
        }

        System.out.println( "Permits : " + barrier.getSemaphore().availablePermits() );
    }
}
