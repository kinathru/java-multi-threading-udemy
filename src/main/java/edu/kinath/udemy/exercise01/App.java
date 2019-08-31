package edu.kinath.udemy.exercise01;

import java.math.BigInteger;

public class App
{
    public static void main( String[] args )
    {
        ComplexCalculation complexCalculation = new ComplexCalculation();
        BigInteger result = complexCalculation.calculateResult( BigInteger.valueOf( 200 ), BigInteger.valueOf( 100000 ), BigInteger.valueOf( 3333 ), BigInteger.valueOf( 10000 ) );
        System.out.println( result );
    }
}
