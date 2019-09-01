package edu.kinath.udemy.atomic.operation;

import java.util.Random;

public class App
{
    public static void main( String[] args )
    {
        Metrics metrics = new Metrics();
        BusinessLogic b1 = new BusinessLogic( metrics );
        BusinessLogic b2 = new BusinessLogic( metrics );
        MetricsPrinter p1 = new MetricsPrinter( metrics );

        b1.start();
        b2.start();
        p1.start();
    }
}

class MetricsPrinter extends Thread
{
    private Metrics metrics;

    public MetricsPrinter( Metrics metrics )
    {
        this.metrics = metrics;
    }

    @Override
    public void run()
    {
        while( true )
        {
            try
            {
                Thread.sleep( 100 );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }

            double currentAverage = metrics.getAverage();
            System.out.println( "Current Average is : " + currentAverage );
        }
    }
}

class BusinessLogic extends Thread
{
    private Metrics metrics;
    private Random random = new Random();

    public BusinessLogic( Metrics metrics )
    {
        this.metrics = metrics;
    }

    @Override
    public void run()
    {
        while( true )
        {
            long start = System.currentTimeMillis();

            try
            {
                Thread.sleep( random.nextInt( 10 ) );
            }
            catch( InterruptedException e )
            {
                System.out.println( e.getMessage() );
            }
            long end = System.currentTimeMillis();
            metrics.addSample( end - start );
        }
    }
}

class Metrics
{
    private long count = 0;
    /**
     * Use volatile here because, reading/writing double is not atomic. with volatile it's guaranteed
     * that reading and writing to double is atomic.
     */
    private volatile double average = 0.0;

    public synchronized void addSample( long sample )
    {
        double currentSum = average * count;
        count++;
        average = ( currentSum + sample ) / count;
    }

    public double getAverage()
    {
        return average;
    }
}
