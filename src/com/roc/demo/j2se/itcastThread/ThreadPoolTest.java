package com.roc.demo.j2se.itcastThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池演示
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        //可改变线程池的数量nThreads，再分别看下效果
//        ExecutorService threadPool =
//                Executors.newFixedThreadPool(5);    //固定线程池
        ExecutorService threadPool =
                Executors.newCachedThreadPool();    //缓存线程池
//        ExecutorService threadPool =
//                Executors.newSingleThreadExecutor();    //单一线程池，
        // 可解决线程死掉后重新启动的问题
        for (int i = 1; i <= 10; i++) {
            final int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 10; j++) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("=======" +
                                Thread.currentThread().getName() +
                                " is looping of " + j +
                                " for task of " + finalI);
                    }
                }
            });
        }
        System.out.println("=======all of 10 tasks have committed! ");
        threadPool.shutdown();  //可（开/关）注释看下效果
//        threadPool.shutdownNow();

        Executors.newScheduledThreadPool(3).
                scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("=======bombing!");
                    }
                },
                        10,
                        3,
                        TimeUnit.SECONDS);
    }

}
