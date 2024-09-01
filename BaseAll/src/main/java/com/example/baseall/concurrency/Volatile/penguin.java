package com.example.baseall.concurrency.Volatile;

/**
 * volatile 实现单例模式的双重锁
 */
public class penguin {
    private static volatile penguin m_penguin = null;

    public static penguin getInstance() { //1
        if (null == m_penguin) { //2
            synchronized (penguin.class) { //3
                if (null == m_penguin) { //4
                    m_penguin = new penguin(); //5
                }
            }
        }
        return m_penguin; //6
    }

    // 避免通过new初始化对象
    private void penguin() {
    }

    public void beating() {
        System.out.println("打⾖⾖");
    }

    public static void main(String[] args) {
        for (int i = 0; i <100 ; i++) {
            getInstance().beating();
        }
    }
}