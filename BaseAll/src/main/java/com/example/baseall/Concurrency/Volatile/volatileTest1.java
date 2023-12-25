package com.example.baseall.Concurrency.Volatile;

public class volatileTest1 {
    public int inc = 0;

    public static void main(String[] args) {

        final volatileTest1 test = new volatileTest1();

        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                }

                ;
            }.start();
        }
        while (Thread.activeCount() > 2) {
            System.out.println(Thread.activeCount()); Thread.yield();}//保证前⾯的线程都执⾏完

        System.out.println("add synchronized, inc output:" + test.inc);
    }

    public synchronized void increase() {
        inc++;
    }
}