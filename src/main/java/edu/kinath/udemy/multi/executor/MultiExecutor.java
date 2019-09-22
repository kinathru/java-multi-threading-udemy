package edu.kinath.udemy.multi.executor;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    // Add any necessary member variables here
    List<Runnable> runnableList = new ArrayList<>();

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        // Complete your code here
        runnableList = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        // complete your code here
        for( Runnable runnable : runnableList )
        {
            Thread t1 = new Thread( runnable );
            t1.start();
        }

    }
}