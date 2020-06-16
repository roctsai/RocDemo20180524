package com.roc.demo.j2se.itcastThread;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 子线程10次，主线程100次。。。如此循环50次
 */
public class BlockingQueueCommunication {

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

    /**
     * 资源类
     * 设计原则：自己的同步业务管理自己管，不要让外面的线程管
     * 锁是上在代表要操作的资源的类的内部方法中，而不是线程代码中
     */
    static class Business {
        BlockingQueue<Integer> queue1 =
                new ArrayBlockingQueue<Integer>(1);
        BlockingQueue<Integer> queue2 =
                new ArrayBlockingQueue<Integer>(1);
        //匿名的构造方法，运行时机：在任何构造方法之前
        {
//            Collections.synchronizedMap(null);
            try {
                System.out.println("=======before queue2.put(1)");
                queue2.put(1);
                System.out.println("=======after queue2.put(1)");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        public synchronized void sub(int i) {
        public void sub(int i) {    //改为阻塞队列后，要去掉synchronized
            System.out.println("=======enter into sub()");
            try {
                queue1.put(1);
                for (int j = 1; j <= 10; j++) {
                    System.out.println("======= sub thread sequence of (j=)" + j + ", loop of (i=)" + i);
                }
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

//        public synchronized void main(int i) {
        public void main(int i) {    //改为阻塞队列后，要去掉synchronized
                System.out.println("=======enter into main()");
            try {
                queue2.put(1);
                for (int j = 1; j <= 100; j++) {
                    System.out.println("======= main thread sequence of (j=)" + j + ", loop of (i=)" + i);
                }
                queue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}






