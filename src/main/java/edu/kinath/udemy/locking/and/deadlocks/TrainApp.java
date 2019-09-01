package edu.kinath.udemy.locking.and.deadlocks;

import java.util.Random;

/**
 * Changing the order of lock acquisition can solve the deadlock. That means, every time the locks should be acquired in the same order.
 */
public class TrainApp
{
    public static void main( String[] args )
    {
        Intersection intersection = new Intersection();
        Thread trainA = new Thread( new TrainA( intersection ) );
        Thread trainB = new Thread( new TrainB( intersection ) );
        trainA.start();
        trainB.start();
    }
}

class TrainA implements Runnable
{
    private Intersection intersection;
    private Random random = new Random();

    public TrainA( Intersection intersection )
    {
        this.intersection = intersection;
    }

    @Override
    public void run()
    {
        while( true )
        {
            try
            {
                Thread.sleep( random.nextInt( 5 ) );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }
            intersection.takeRoadA();
        }
    }
}

class TrainB implements Runnable
{
    private Intersection intersection;
    private Random random = new Random();

    public TrainB( Intersection intersection )
    {
        this.intersection = intersection;
    }

    @Override
    public void run()
    {
        while( true )
        {
            try
            {
                Thread.sleep( random.nextInt( 5 ) );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }
            intersection.takeRoadB();
        }
    }
}

class Intersection
{
    private Object roadA = new Object();
    private Object roadB = new Object();

    public void takeRoadA()
    {
        synchronized( roadA )
        {
            System.out.println( "Road A is locked by " + Thread.currentThread().getName() );

            synchronized( roadB )
            {
                System.out.println( "Train is passing through road A" );
                try
                {
                    Thread.sleep( 1 );
                }
                catch( InterruptedException e )
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void takeRoadB()
    {
        synchronized( roadA )
        {
            System.out.println( "Road B is locked by " + Thread.currentThread().getName() );

            synchronized( roadB )
            {
                System.out.println( "Train is passing through road B" );
                try
                {
                    Thread.sleep( 1 );
                }
                catch( InterruptedException e )
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
