package edu.kinath.udemy.atomic.quiz14;

import java.util.concurrent.atomic.AtomicLong;

public class App
{
}

class Metric
{
    private AtomicLong count = new AtomicLong( 0 );
    private AtomicLong sum = new AtomicLong( 0 );

    public void addSample( long sample )
    {
        sum.addAndGet( sample );
        count.incrementAndGet();
    }

    public double getAverage()
    {
        double average = (double) sum.get() / count.get();
        reset();
        return average;
    }

    private void reset()
    {
        count.set( 0 );
        sum.set( 0 );
    }
}
