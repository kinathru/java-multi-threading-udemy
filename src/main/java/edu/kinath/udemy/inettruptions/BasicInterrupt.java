package edu.kinath.udemy.inettruptions;

public class BasicInterrupt
{
    public static void main( String[] args )
    {
        Thread t1 = new Thread( new Runnable()
        {
            public void run()
            {
                try
                {
                    Thread.sleep( 5000 );
                }
                catch( InterruptedException e )
                {
                    System.out.println("Exiting Thread");
                }
            }
        } );

        t1.start();
        t1.interrupt();
    }
}
