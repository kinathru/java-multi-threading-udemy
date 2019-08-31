package edu.kinath.udemy.joiningthreads;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws InterruptedException
    {
        long[] inputValues = {10L, 2L, 5L, 7L, 100, 250000L};
        List<FactorialThread> threadList = new ArrayList<FactorialThread>();

        for( int i = 0; i < inputValues.length; i++ )
        {
            FactorialThread factorialThread = new FactorialThread();
            factorialThread.setInputValue( inputValues[i] );
            factorialThread.setName( "Factorial-" + inputValues[i] );
            threadList.add( factorialThread );
        }

        for( int i = 0; i < threadList.size(); i++ )
        {
            threadList.get( i ).start();
        }

        for( int i = 0; i < threadList.size(); i++ )
        {
            threadList.get( i ).join( 2000 );
        }

        for( int i = 0; i < threadList.size(); i++ )
        {
            if( threadList.get( i ).isFinished() )
            {
                System.out.println( "Factorial calculated for " + inputValues[i] + " is " + threadList.get( i ).getFactorial() );
            }
            else
            {
                System.out.println( "Factorial is not yet calculated for " + inputValues[i] );
            }
        }

        for( FactorialThread thread : threadList )
        {
            if( !thread.isFinished() )
            {
                thread.interrupt();
            }
        }
    }
}
