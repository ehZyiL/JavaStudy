package com.example.baseall.concurrency.Volatile;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class volatileTest2 {
    public int inc = 0;
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final volatileTest2 test = new volatileTest2();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                }

                ;
            }.start();
        }
        while (Thread.activeCount() > 1) //保证前⾯的线程都执⾏完
            Thread.yield();
        System.out.println("add lock, inc output:" + test.inc);
    }

    public void increase() {
        lock.lock();
        inc++;
        lock.unlock();
    }
}