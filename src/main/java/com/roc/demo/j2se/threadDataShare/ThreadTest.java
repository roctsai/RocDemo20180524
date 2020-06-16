package com.roc.demo.j2se.threadDataShare;

/**
 * 场景，ibm经典现场面试，要求：子线程循环跑30次，暂停，转到主线程跑40次，接着子线程循环跑30次，暂停，然后转到主线程跑40次，如此往复
 * 一共这样交替50次
 */
public class ThreadTest {

    public static void main(String[] args) {
        final BusinessDemo businessDemo = new BusinessDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    businessDemo.sonBusiness(i);
                }
            }
        }).start();

        //50轮回，主线程部分
        for (int i = 1; i <= 50; i++) {
            //主线程进来一次 40次
            businessDemo.mainBusiness(i);
        }
    }

}
