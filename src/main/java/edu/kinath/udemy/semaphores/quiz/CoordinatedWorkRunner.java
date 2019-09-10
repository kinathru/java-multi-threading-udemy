package edu.kinath.udemy.semaphores.quiz;

public class CoordinatedWorkRunner implements Runnable
{
    private Barrier barrier;

    public CoordinatedWorkRunner( Barrier barrier )
    {
        this.barrier = barrier;
    }

    @Override
    public void run()
    {
        try
        {
            task();
        }
        catch( InterruptedException e )
        {
        }
    }

    private void task() throws InterruptedException
    {
        // Performing Part 1
        System.out.println( Thread.currentThread().getName() + " part 1 of the work is finished" );

        barrier.barrier();

        // Performing Part2
        System.out.println( Thread.currentThread().getName() + " part 2 of the work is finished" );
    }
}