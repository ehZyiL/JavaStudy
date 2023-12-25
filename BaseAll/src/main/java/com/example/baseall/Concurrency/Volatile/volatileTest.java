package com.example.baseall.Concurrency.Volatile;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ehyzil
 * @Description volatile不适用非原子性操作
 * @create 2023-12-2023/12/25-11:31
 */
public class volatileTest {
    public volatile int inc = 0;
    public static void main(String[] args) {
        final volatileTest test = new volatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
        while(Thread.activeCount()>2) //保证前⾯的线程都执⾏完
            Thread.yield();
        System.out.println("inc output:" + test.inc);
    }

    public void increase() {
        inc++; //不是⼀个原⼦性操作
    }



}
