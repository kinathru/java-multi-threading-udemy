package edu.kinath.udemy.data.race;

public class App
{
    public static void main( String[] args )
    {
        SharedClass sharedClass = new SharedClass();

        Thread t1 = new Thread( () -> {
            for( int i = 0 ; i < Integer.MAX_VALUE ; i++ )
            {
                sharedClass.increment();
            }
        } );

        Thread t2 = new Thread( () -> {
            for( int i = 0 ; i < Integer.MAX_VALUE ; i++ )
            {
                sharedClass.checkForDataRace();
            }
        } );

        t1.start();
        t2.start();
    }
}

class SharedClass
{
    private volatile int x = 0;
    private volatile int y = 0;

    public void increment()
    {
        x++;
        y++;
    }

    public void checkForDataRace()
    {
        if( y > x )
        {
            System.out.println( "y > x -> Data Race Detected");
        }
    }
}
