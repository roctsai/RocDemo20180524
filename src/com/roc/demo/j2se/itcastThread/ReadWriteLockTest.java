package com.roc.demo.j2se.itcastThread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        final Queue3 q3 = new Queue3();
        for (int i = 0; i < 3; i++) {   //3个读线程，3个写线程
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        q3.get();
                    }
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        q3.put(new Random().nextInt(10000));
                    }
                }
            }.start();
        }
    }

}

class Queue3 {
    private Object data = null; //共享数据，只能有一个线程写该数据，
    // 但可以有多个线程读该数据
    private ReadWriteLock rwl =
            new ReentrantReadWriteLock();

    public void get() {
        rwl.readLock().lock();
        try {
            System.out.println("=======currentThread = " +
                    Thread.currentThread().getName() + " be ready to " +
                    "read data!");
//            Thread.sleep((long) (Math.random()*1000));
            Thread.sleep(4000);
            System.out.println("=======currentThread = " +
                    Thread.currentThread().getName() + " have " +
                    "read data = " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
    }

    public void put(Object data) {
        rwl.writeLock().lock();
        try {
            System.out.println("=======currentThread = " +
                    Thread.currentThread().getName() + " be ready to " +
                    "write data!");
//            Thread.sleep((long) (Math.random()*1000));
            Thread.sleep(4000);
            this.data = data;
            System.out.println("=======currentThread = " +
                    Thread.currentThread().getName() + " have " +
                    "write data = " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }
}
