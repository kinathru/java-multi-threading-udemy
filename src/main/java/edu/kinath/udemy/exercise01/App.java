package edu.kinath.udemy.exercise01;

import java.math.BigInteger;

public class App
{
    public static void main( String[] args )
    {
        runSequentially();
        runParallelly();
    }

    public static void runSequentially()
    {
        long start = System.currentTimeMillis();
        BigInteger r1 = calculate( 2000, 1000000 );
        BigInteger r2 = calculate( 3000, 1000000 );
        BigInteger result = r1.add( r2 );
        System.out.println( result );
        long end = System.currentTimeMillis();
        System.out.println( "Sequential Time : " + ( end - start ) / 1000 );
    }

    public static void runParallelly()
    {
        long start = System.currentTimeMillis();
        ComplexCalculation complexCalculation = new ComplexCalculation();
        BigInteger result = complexCalculation.calculateResult( BigInteger.valueOf( 200 ), BigInteger.valueOf( 100000 ), BigInteger.valueOf( 3333 ), BigInteger.valueOf( 10000 ) );
        System.out.println( result );
        long end = System.currentTimeMillis();
        System.out.println( "Parallel Time : " + ( end - start ) / 1000 );
    }

    public static BigInteger calculate( int pBase, int pPower )
    {
        BigInteger result = BigInteger.ONE;
        BigInteger base = BigInteger.valueOf( pBase );
        BigInteger power = BigInteger.valueOf( pPower );

        for( BigInteger i = BigInteger.ZERO; i.compareTo( power ) != 0; i = i.add( BigInteger.ONE ) )
        {
            result = result.multiply( base );
        }
        return result;
    }
}
