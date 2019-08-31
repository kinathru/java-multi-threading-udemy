package edu.kinath.udemy.exercise01;

import java.math.BigInteger;

public class ComplexCalculation
{
    public BigInteger calculateResult( BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2 )
    {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        PowerCalculatingThread p1 = new PowerCalculatingThread( base1, power1 );
        PowerCalculatingThread p2 = new PowerCalculatingThread( base2, power2 );

        p1.start();
        p2.start();

        try
        {
            p1.join();
            p2.join();
        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }

        System.out.println( p1.getResult() );
        System.out.println( p2.getResult() );

        result = p1.getResult().add( p2.getResult() );

        return result;
    }

    private static class PowerCalculatingThread extends Thread
    {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread( BigInteger base, BigInteger power )
        {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run()
        {
            for( BigInteger i = BigInteger.ZERO; i.compareTo( power ) != 0; i = i.add( BigInteger.ONE ) )
            {
                result = result.multiply( base );
                if( Thread.currentThread().isInterrupted() )
                {
                    return;
                }
            }
        }

        public BigInteger getResult()
        {
            return result;
        }
    }
}
