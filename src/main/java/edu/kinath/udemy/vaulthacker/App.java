package edu.kinath.udemy.vaulthacker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App
{
    public static final int MAX_PASSWORD = 999;

    public static void main( String[] args )
    {
        Random random = new Random(  );
        Vault vault = new Vault( random.nextInt(MAX_PASSWORD) );

        List<Thread> threadList = new ArrayList<Thread>( );
        threadList.add( new AscendingHackerThread( vault ) );
        threadList.add( new DescendingHackerThread( vault ) );
        threadList.add( new PoliceThread() );

        for( Thread thread : threadList )
        {
            thread.start();
        }
    }
}
