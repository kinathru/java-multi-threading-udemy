package edu.kinath.udemy.joiningthreads;

import java.math.BigInteger;

public class FactorialThread extends Thread
{
    private long inputValue;
    private boolean finished;
    private BigInteger factorial;

    @Override
    public void run()
    {
        factorial = calculateFactorial( this.inputValue );
        finished = true;
    }

    public BigInteger calculateFactorial( long input )
    {
        BigInteger factorial = BigInteger.valueOf( input );
        for( long i = input; i > 1; i-- )
        {
            factorial = factorial.multiply( BigInteger.valueOf( i - 1 ) );
            if( this.isInterrupted() )
            {
                return BigInteger.ZERO;
            }
        }
        return factorial;
    }

    public long getInputValue()
    {
        return inputValue;
    }

    public void setInputValue( long inputValue )
    {
        this.inputValue = inputValue;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public BigInteger getFactorial()
    {
        return factorial;
    }
}
