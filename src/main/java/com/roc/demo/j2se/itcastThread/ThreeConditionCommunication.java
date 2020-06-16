package com.roc.demo.j2se.itcastThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 老大搞100次，老二搞10次，老三搞20次。。。如此循环50次
 */
public class ThreeConditionCommunication {

    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //原子整数
//                AtomicInteger ii = new AtomicInteger(0);
//                for (; ii.get() < 50; ii.incrementAndGet()) {
//                }
                for (int i = 1; i <= 50; i++) {
//                    synchronized (ConditionCommunication.class) {
                        business.sub2(i);
//                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    business.sub3(i);
                }
            }
        }).start();
        for (int i = 1; i <= 50; i++) {
//            synchronized (ConditionCommunication.class) {
                business.main(i);
//            }
        }
    }

    /**
     * 资源类
     * 设计原则：自己的同步业务管理自己管，不要让外面的线程管
     * 锁是上在代表要操作的资源的类的内部方法中，而不是线程代码中
     */
    static class Business {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        private int shouldSub = 1;

//        public synchronized void sub(int i) {
        public void sub2(int i) {    //用lock代替
            lock.lock();
            try {
                while (shouldSub != 2) {    //也可以用while，更好地防止没有
                    // 经过通知便唤醒
                    try {
                        condition2.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("======= sub2 thread sequence of " +
                            "(j=)" + j + ", loop of (i=)" + i);
                }
                //老二搞完，老三搞
                shouldSub = 3;
//                this.notify();
                condition3.signal(); //notify方法是Object的

            } finally {
                lock.unlock();
            }
        }

        public void sub3(int i) {    //用lock代替
            lock.lock();
            try {
                while (shouldSub != 3) {    //也可以用while，更好地防止没有
                    // 经过通知便唤醒
                    try {
//                        this.wait();
                        condition3.await();  //wait方法是Object的
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 20; j++) {
                    System.out.println("======= sub3 thread sequence of " +
                            "(j=)" + j + ", loop of (i=)" + i);
                }
                shouldSub = 1;
//                this.notify();
                condition1.signal(); //notify方法是Object的

            } finally {
                lock.unlock();
            }
        }

//        public synchronized void main(int i) {
        public void main(int i) {
            lock.lock();
            try {
                while (shouldSub != 1) {    //也可以用while，更好地防
                    // 止没有经过通知便唤醒
                    try {
//                        this.wait();
                        condition1.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 100; j++) {
                    System.out.println("======= main thread " +
                            "sequence of (j=)" + j + ", loop of (i=)" + i);
                }
                shouldSub = 2;
//                this.notify();
                condition2.signal();
            } finally {
                lock.unlock();
            }
        }

    }

}




