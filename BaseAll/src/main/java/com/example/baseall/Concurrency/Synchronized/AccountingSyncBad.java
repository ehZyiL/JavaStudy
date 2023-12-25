package com.example.baseall.Concurrency.Synchronized;

public class AccountingSyncBad implements Runnable {
    //共享资源(临界资源)
    static int i = 0;

    // synchronized 同步⽅法
    public  synchronized void increase() {
        i++;
    }
    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // new 两个AccountingSync新实例
        Thread t1 = new Thread(new AccountingSyncBad());
        Thread t2 = new Thread(new AccountingSyncBad());
        t1.start();
        t2.start();
        t1.join();//「Thread.join()方法表示调用此方法的线程被阻塞，仅当该方法完成以后，才能继续运行」。
        t2.join();
        System.out.println("static, i output:" + i);
    }
}