package com.example.baseall.Concurrency.Synchronized;

public class AccountingSyncClass implements Runnable {
    static int i = 0;

    /**
     * 同步静态⽅法,锁是当前class对象，也就是* AccountingSyncClass类对应的class对象
     */
    public static synchronized void increase() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1 = new Thread(new AccountingSyncClass());
        //new新实例
        Thread t2 = new Thread(new AccountingSyncClass());
        //启动线程
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

    // ⾮静态,访问时锁不⼀样不会发⽣互斥
    public synchronized void increase4Obj() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }
}