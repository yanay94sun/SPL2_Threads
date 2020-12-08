package bgu.spl.mics.application;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCounter {

    private AtomicInteger count;

    private ThreadCounter(){
        count = new AtomicInteger(0);
    }


    public AtomicInteger getCount(){
        return count;
    }

    public void increase(){
        count.incrementAndGet();
    }

    public static ThreadCounter getInstance(){
        return ThreadCounterHolder.threadCounter;
    }

    private static class ThreadCounterHolder{
        private static ThreadCounter threadCounter = new ThreadCounter();
    }



}
