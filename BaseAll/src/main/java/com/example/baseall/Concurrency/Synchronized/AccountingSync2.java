package com.example.baseall.Concurrency.Synchronized;

public class AccountingSync2 implements Runnable {
    static AccountingSync2 instance = new AccountingSync2(); // 饿汉单例模式
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

    @Override
    public void run() {
        //省略其他耗时操作....
        //使⽤同步代码块对变ᰁi进⾏同步操作,锁对象为instance
//        synchronized (instance) {
//            for (int j = 0; j < 1000000; j++) {
//                i++;
//            }
//        }

//        //this,当前实例对象锁
//        synchronized (this) {
//            for (int j = 0; j < 1000000; j++) {
//                i++;
//            }
//        }

        //Class对象锁
        synchronized (AccountingSync2.class) {
            for (int j = 0; j < 1000000; j++) {
                i++;
            }
        }


    }
}