package com.roc.demo.j2se.itcastThread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {

    private static int count = 0;

    public static void main(String[] args) {
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("ring my bells!");
//            }
//        }, 7000, 2000);

//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("ring my bells!");
//                new Timer().schedule(
////                        new TimerTask() {
////                    @Override
////                    public void run() {
////                        System.out.println("ring the ring!");
////                    }
////                },
//                        this,   //this代替上面注释的new TimerTask()，但是不可行，因为this已调度完成。因此可以将匿名内部类new TimerTask()提到外面去,给个类名
//                        2000);
//            }
//        }, 2000);

        class MyTimerTask extends TimerTask {
            @Override
            public void run() {
                count = (count + 1) % 2;
                System.out.println("ring my bells! count = " + count);
                new Timer().schedule(
                        new MyTimerTask(),
                        2000 + 2000 * count);
            }
        };
        new Timer().schedule(new MyTimerTask(), 2000);

        while (true) {
            System.out.println("当前时间 = " + new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
