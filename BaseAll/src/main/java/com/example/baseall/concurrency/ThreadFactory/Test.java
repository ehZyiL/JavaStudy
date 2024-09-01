package com.example.baseall.concurrency.ThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ehyzil
 * @Description
 * @create 2023-12-2023/12/26-19:06
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
                5,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new MyThreadFactory("测试自建线程工厂"));


        for (int i = 0; i < 100; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

    }
}
