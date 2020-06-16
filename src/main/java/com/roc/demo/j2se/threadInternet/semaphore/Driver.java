package com.roc.demo.j2se.threadInternet.semaphore;

import java.util.concurrent.Semaphore;

public class Driver {

    // 将信号量设为3
//    private Semaphore semaphore = new Semaphore(3);
    private Semaphore semaphore = new Semaphore(5); //分配了5个坑

    public void driveCar() {
        try {
//            semaphore.acquire();
            semaphore.acquire(2);   //在上述代码中总的信号量除以每次获取的许可数即5/2=2，就是说
            // 可以允许2个线程一起运行，（或者理解为一个线程占2个茅坑）。
            System.out.println(Thread.currentThread().getName() + " start at " + System.currentTimeMillis());
//            Thread.sleep(1000);
            Thread.sleep(7000);
            System.out.println(Thread.currentThread().getName() + " stop~ at " + System.currentTimeMillis());
            semaphore.release();  //释放1个茅坑
//            semaphore.release(5);   //释放5个茅坑
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
