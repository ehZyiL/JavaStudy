package com.example.baseall.concurrency.Synchronized;

public class AccountingSyncMethod {
    //共享资源(临界资源)
    static int i = 0;

    // synchronized 同步⽅法
    public static synchronized void increase() {
        i++;
    }

    public static void main(String args[]) throws InterruptedException {
        AccountingSyncMethod instance = new AccountingSyncMethod();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 1000000; j++) {
                    increase();
                }
            }
        };
        runnable.run();
        runnable.run();

        System.out.println("static, i output:" + i);
    }


}