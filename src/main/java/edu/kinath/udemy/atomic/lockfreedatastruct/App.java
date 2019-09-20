package edu.kinath.udemy.atomic.lockfreedatastruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class App
{
    public static void main( String[] args ) throws InterruptedException
    {
        Random random = new Random(  );
        LockFreeStack<Integer> stack = new LockFreeStack<>();
        List<Thread> threadList = new ArrayList<>();

        int pushingThreads = 2;
        int poppingThreads = 2;

        for( int i = 0 ; i < pushingThreads ; i++ )
        {
            Thread thread = new Thread( () -> {
                while( true )
                {
                    stack.push( random.nextInt(  ) );
                }
            } );
            thread.setDaemon( true );
            threadList.add( thread );
        }

        for( int i = 0 ; i < poppingThreads ; i++ )
        {
            Thread thread = new Thread( () -> {
                while( true )
                {
                    stack.pop();
                }
            } );
            thread.setDaemon( true );
            threadList.add( thread );
        }

        for( Thread thread : threadList )
        {
            thread.start();
        }

        Thread.sleep( 10000 );

        System.out.println(String.format( "%,d operations were performed in 10 seconds", stack.getCounter() ));

    }
}

class LockFreeStack<T>
{
    private AtomicReference<StackNode<T>> head = new AtomicReference<>();
    AtomicInteger counter = new AtomicInteger( 0 );

    public void push( T value )
    {
        StackNode<T> newHeadNode = new StackNode<>( value );

        while( true )
        {
            StackNode<T> currentHeadNode = head.get();
            newHeadNode.next = currentHeadNode;
            if( head.compareAndSet( currentHeadNode, newHeadNode ) )
            {
                break;
            }
            else
            {
                LockSupport.parkNanos( 1 );
            }
        }
        counter.incrementAndGet();
    }

    public T pop()
    {
        StackNode<T> currentHeadNode = head.get();
        StackNode<T> newHeadNode;

        while( currentHeadNode != null )
        {
            newHeadNode = currentHeadNode.next;
            if( head.compareAndSet( currentHeadNode, newHeadNode ) )
            {
                break;
            }
            else
            {
                LockSupport.parkNanos( 1 );
                currentHeadNode = head.get();
            }
        }
        counter.incrementAndGet();
        return currentHeadNode != null ? currentHeadNode.value : null;
    }

    public int getCounter()
    {
        return counter.get();
    }
}

class StandardStack<T>
{
    private StackNode<T> head;
    private volatile int counter = 0;

    public synchronized void push( T value )
    {
        StackNode<T> newHead = new StackNode<>( value );
        newHead.next = head;
        head = newHead;
        counter++;
    }

    public synchronized T pop()
    {
        if( head == null )
        {
            counter++;
            return null;
        }

        T value = head.value;
        head = head.next;
        counter++;
        return value;
    }

    public synchronized int getCounter()
    {
        return counter;
    }
}

class StackNode<T>
{
    public T value;
    public StackNode<T> next;

    public StackNode( T value )
    {
        this.value = value;
        this.next = next;
    }
}
