package com.roc.demo.j2se.itcastThread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 有一种重要的线程思想：多个模块共享同一个对象，在线程内共享，在线程外独立
 */
public class ThreadScopeShareData {

    private static int data = 0;
    private static Map<Thread, Integer> threadData =
            new HashMap<Thread, Integer>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println("=======" +
                            Thread.currentThread().getName() +
                            " has put data :" + data);
                    threadData.put(Thread.currentThread(), data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    /**
     * A模块
     */
    static class A {

        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("======= A from " +
                    Thread.currentThread().getName() +
                    " get data :" + data);
        }

    }

    /**
     * B模块
     */
    static class B {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("======= B from " +
                    Thread.currentThread().getName() +
                    " get data :" + data);
        }
    }

}
