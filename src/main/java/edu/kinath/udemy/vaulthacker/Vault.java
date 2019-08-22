package edu.kinath.udemy.vaulthacker;

public class Vault
{
    private int password;

    public Vault( int password )
    {
        this.password = password;
    }

    public boolean isCorrectPassword(int guess)
    {
        try
        {
            Thread.sleep( 40 );
        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }
        return this.password == guess;
    }
}
