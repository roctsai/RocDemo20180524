package com.roc.demo.j2se.itcastThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 子线程10次，主线程100次。。。如此循环50次
 */
public class TraditionalThreadCommunication {

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
//                    synchronized (TraditionalThreadCommunication.class) {
                        business.sub(i);
//                    }
                }
            }
        }).start();
        for (int i = 1; i <= 50; i++) {
//            synchronized (TraditionalThreadCommunication.class) {
                business.main(i);
//            }
        }
//        new Thread().start();
    }


}

/**
 * 资源类
 * 设计原则：自己的同步业务管理自己管，不要让外面的线程管
 * 锁是上在代表要操作的资源的类的内部方法中，而不是线程代码中
 */
class Business {
    private boolean beShouldSub = true;

    public synchronized void sub(int i) {
//        if (!beShouldSub) {
        while (!beShouldSub) {    //也可以用while，更好地防止没有经过通知便唤醒
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("======= sub thread sequence of (j=)" + j + ", loop of (i=)" + i);
        }
        beShouldSub = false;
        this.notify();
    }

    public synchronized void main(int i) {
//        if (beShouldSub) {
        while (beShouldSub) {    //也可以用while，更好地防止没有经过通知便唤醒
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 100; j++) {
            System.out.println("======= main thread sequence of (j=)" + j + ", loop of (i=)" + i);
        }
        beShouldSub = true;
        this.notify();
    }

}




