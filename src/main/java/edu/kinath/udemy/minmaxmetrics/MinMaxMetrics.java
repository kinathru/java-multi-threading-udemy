package edu.kinath.udemy.minmaxmetrics;

public class MinMaxMetrics
{
    // Add all necessary member variables
    private volatile long minValue;
    private volatile long maxValue;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics()
    {
        // Add code here
        minValue = Long.MIN_VALUE;
        maxValue = Long.MAX_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public synchronized void addSample( long newSample )
    {
        minValue = newSample < minValue ? newSample : minValue;
        maxValue = newSample > maxValue ? newSample : maxValue;
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin()
    {
        // Add code here
        return minValue;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax()
    {
        // Add code here
        return maxValue;
    }
}
