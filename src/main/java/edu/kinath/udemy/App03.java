package edu.kinath.udemy;

public class App03
{
    public static void main( String[] args )
    {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

class MyThread extends Thread
{
    @Override public void run()
    {
        System.out.println("Hello from : " + this.getName());
    }
}
