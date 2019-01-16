package com.roc.demo.j2se.itcastThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        new LockTest().init();
    }

    private void init() {
        Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("zhangxiaoxiang");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    outputer.output("lihuoming");
//                    new Outputer().output("lihuoming_new");
//                    outputer.output2("lihuoming_2");
                    outputer.output3("lihuoming_3");
                }
            }
        }).start();
    }

    /**
     * 外部类的静态方法
     */
    private static void staticMethod() {
        System.out.println("=======staticMethod");
    }

//    class Outputer {
    static class Outputer { //加上static相当于外部类，所以里面可以写静态方法
        String xxx = "";
        Lock lock = new ReentrantLock();
        public void output(String name) {
            int len = name.length();
//            synchronized (name) {   //name可能不是同一个对象
//            synchronized (xxx) {   //xxx是同一个对象(同一个门栓)
//            synchronized (this) {   //this为调用我的那个家伙
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }

        public synchronized void output2(String name) { //synchronized用一次即可，如果里面也有，会造成死锁， 此处的synchronized锁的也是this对象(同一个门栓)
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

        public static synchronized void output3(String name) { //synchronized用一次即可，如果里面也有，会造成死锁， 此处的synchronized锁的也是this对象(同一个门栓)
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }

}
