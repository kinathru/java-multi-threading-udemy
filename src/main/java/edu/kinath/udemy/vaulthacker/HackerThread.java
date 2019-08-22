package edu.kinath.udemy.vaulthacker;

public abstract class HackerThread extends Thread
{
    protected Vault vault;

    public HackerThread( Vault vault )
    {
        this.vault = vault;
        this.setName( this.getClass().getSimpleName() );
        this.setPriority( MAX_PRIORITY );
    }

    @Override public synchronized void start()
    {
        System.out.println( "Current thread starting : " + this.getName() );
        super.start();
    }
}

class AscendingHackerThread extends HackerThread
{

    public AscendingHackerThread( Vault vault )
    {
        super( vault );
    }

    @Override public void run()
    {
        for( int i = 0 ; i < App.MAX_PASSWORD ; i++ )
        {
            if( vault.isCorrectPassword( i ) )
            {
                System.out.println(this.getName() + " guessed password " + i);
                System.exit( 0 );
            }
        }
    }
}

class DescendingHackerThread extends HackerThread
{

    public DescendingHackerThread( Vault vault )
    {
        super( vault );
    }

    @Override public void run()
    {
        for( int i = App.MAX_PASSWORD ; i > 0 ; i-- )
        {
            if( vault.isCorrectPassword( i ) )
            {
                System.out.println(this.getName() + " guessed password " + i);
                System.exit( 0 );
            }
        }
    }
}
