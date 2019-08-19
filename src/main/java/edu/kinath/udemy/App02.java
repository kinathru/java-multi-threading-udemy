package edu.kinath.udemy;

public class App02
{
    public static void main( String[] args )
    {
        final Thread t1 = new Thread( new Runnable()
        {
            public void run()
            {
                throw new RuntimeException( "Intentional Exception" );
            }
        } );

        t1.setName( "Worker Thread" );

        t1.setUncaughtExceptionHandler( new Thread.UncaughtExceptionHandler()
        {
            public void uncaughtException( Thread t, Throwable e )
            {
                System.out.println("Critical Error happened in the thread : " + t1.getName() + " - " + e.getMessage());
            }
        } );

        t1.start();
    }
}
