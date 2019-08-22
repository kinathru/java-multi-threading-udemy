package edu.kinath.udemy.inettruptions;

import java.math.BigInteger;

public class CalculatingPowerDaemon
{
    public static void main( String[] args )
    {
        Thread t1 = new Thread( new LongComputationTask( new BigInteger( "200" ), new BigInteger( "200000000" ) ) );
        t1.setDaemon( true );
        t1.start();
        t1.interrupt();
    }
}
