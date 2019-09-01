package edu.kinath.udemy.locks.reentrant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantApp
{

}

class PriceUpdater extends Thread
{
    private PricesContainer pricesContainer;
    private Random random = new Random();

    public PriceUpdater(PricesContainer pricesContainer)
    {
        this.pricesContainer = pricesContainer;
    }

    @Override
    public void run()
    {
        while( true )
        {
            pricesContainer.getLockObject().lock();

            try
            {
                try
                {
                    Thread.sleep( 1000 );
                }
                catch( InterruptedException e )
                {
                    e.printStackTrace();
                }

                pricesContainer.setBitcoinPrice( random.nextInt( 2000 ) );
                pricesContainer.setEtherPrice( random.nextInt( 2000 ) );
                pricesContainer.setLiteCoinPrice( random.nextInt( 500 ) );
                pricesContainer.setBitcoinCashPrice( random.nextInt( 5000 ) );
                pricesContainer.setRipplePrice( random.nextDouble() );
            }
            finally
            {
                pricesContainer.getLockObject().unlock();
            }

            try
            {
                Thread.sleep( 2000 );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }
        }
    }
}

class PricesContainer
{
    private Lock lockObject = new ReentrantLock();

    private double bitcoinPrice;
    private double etherPrice;
    private double liteCoinPrice;
    private double bitcoinCashPrice;
    private double ripplePrice;

    public Lock getLockObject()
    {
        return lockObject;
    }

    public void setLockObject( Lock lockObject )
    {
        this.lockObject = lockObject;
    }

    public double getBitcoinPrice()
    {
        return bitcoinPrice;
    }

    public void setBitcoinPrice( double bitcoinPrice )
    {
        this.bitcoinPrice = bitcoinPrice;
    }

    public double getEtherPrice()
    {
        return etherPrice;
    }

    public void setEtherPrice( double etherPrice )
    {
        this.etherPrice = etherPrice;
    }

    public double getLiteCoinPrice()
    {
        return liteCoinPrice;
    }

    public void setLiteCoinPrice( double liteCoinPrice )
    {
        this.liteCoinPrice = liteCoinPrice;
    }

    public double getBitcoinCashPrice()
    {
        return bitcoinCashPrice;
    }

    public void setBitcoinCashPrice( double bitcoinCashPrice )
    {
        this.bitcoinCashPrice = bitcoinCashPrice;
    }

    public double getRipplePrice()
    {
        return ripplePrice;
    }

    public void setRipplePrice( double ripplePrice )
    {
        this.ripplePrice = ripplePrice;
    }
}