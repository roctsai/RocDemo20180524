package com.roc.demo.j2se.itcastThread;

/**
 * 多个线程之间共享数据的方式探讨
 */
public class MultiThreadShareData {

    private static ShareData1 data1 = new ShareData1();

    public static void main(String[] args) {
        //方式1
        ShareData1 data2 = new ShareData1();
        new Thread(new MyRunnable1(data2)).start();
        new Thread(new MyRunnable2(data2)).start();

        //方式2
        final ShareData1 data1 = new ShareData1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                data1.decrement();
            }
        }){}.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                data1.increment();
            }
        }){}.start();
    }

}

class MyRunnable1 implements Runnable {
    private ShareData1 data1;
    public MyRunnable1(ShareData1 data1) {
        this.data1 = data1;
    }
    @Override
    public void run() {
        data1.decrement();
    }
}

class MyRunnable2 implements Runnable {
    private ShareData1 data1;
    public MyRunnable2(ShareData1 data1) {
        this.data1 = data1;
    }
    @Override
    public void run() {
        data1.increment();
    }
}

//class ShareData1 implements Runnable {
class ShareData1 {

    private int count = 100;    //模拟卖票

//    @Override
//    public void run() {
//        while (true) {
//            count--;
//        }
//    }

    private int j = 0;

    public synchronized void increment() {
        j++;
    }

    public synchronized void decrement() {
        j--;
    }

}