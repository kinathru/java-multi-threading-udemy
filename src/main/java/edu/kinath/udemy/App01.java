package edu.kinath.udemy;

public class App01
{
    public static void main( String[] args ) throws InterruptedException
    {
        Thread t1 = new Thread( new Runnable()
        {
            public void run()
            {
                System.out.println("Running the thread : " + Thread.currentThread().getName());
                System.out.println("Running the thread : " + Thread.currentThread().getPriority());
            }
        } );

        t1.setName( "Working Thread" );
        t1.setPriority( Thread.MAX_PRIORITY );

        System.out.println("Before starting the thread... ");
        t1.start();
        System.out.println("After starting the thread... ");

        Thread.sleep( 1000 );
    }
}
