package com.example.baseall.Concurrency.Volatile;

class ReorderExample {
    int a = 0;
    //    boolean flag = false;
    volatile boolean flag = false;

    public static void main(String[] args) {
        ReorderExample example = new ReorderExample();
        example.writer();
        example.reader();
//        new Thread(example::reader).start();
//        new Thread(example::writer).start();
    }

    public void writer() {
        a = 1; //1
        flag = true; //2
    }

    public void reader() {
        if (flag) { //3
            int i = a * a; //4
            System.out.println(i);
        }
    }
}