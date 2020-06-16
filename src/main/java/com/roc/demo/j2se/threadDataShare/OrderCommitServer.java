package com.roc.demo.j2se.threadDataShare;

import java.util.Random;

/**
 * 服务类，接受客户端提交过来的订单
 */
public class OrderCommitServer {

    private static float money;

    public static void main(String[] args) {
        //实际业务场景中，要承受上万个并发线程请求，每个线程去维护一个订单
        for (int i = 0; i < 4; i++) {   //启动4个线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //具体的业务，每一个线程过来要经过服务器中多个模块做处理，过程是并发的，线程在此挂起
//                    money = new Random().nextFloat();
//                    System.out.println("当前处理客户端的线程是：" + Thread.currentThread().getName() +
//                            "，后台显示订单金额为：" + money);
//                    new AModule().getOrderMoney();
//                    new BModule().getOrderMoney();

                    //保证锁是同一个对象，看到的对象是不一样的
//                    synchronized (this) {
                    synchronized (OrderCommitServer.class) {    //重锁的方式，效率非常低
                        money = new Random().nextFloat();
                        System.out.println("当前处理客户端的线程是：" + Thread.currentThread().getName() +
                                "，后台显示订单金额为：" + money);
                        new AModule().getOrderMoney();
                        new BModule().getOrderMoney();
                    }
                }
            }).start();
        }
    }

    //仓库查询模块，A
    static class AModule {
        public void getOrderMoney() {
            System.out.println(Thread.currentThread().getName() + "进入A模块处理完毕之后，当前金额为：" + money);
        }
    }

    //账号余额模块，这里直接调用第三方接口，B
    static class BModule {
        public void getOrderMoney() {
            System.out.println(Thread.currentThread().getName() + "进入B模块处理完毕之后，当前金额为：" + money);
        }
    }

    //通知商户发货模块

    //。。。
}
