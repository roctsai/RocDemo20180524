package com.roc.demo.j2se.threadDataShare;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 服务类，接受客户端提交过来的订单
 */
public class OrderCommitServer2 {

    //所有有共享状态的资源封装进ThreadLocal机制，每个线程都有各自的ThreadLocal。好比银行卡和人
    private static ThreadLocal<Float> orderMoneys = new ThreadLocal<Float>();

//    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("110", "110110");
//        map.put("210", "210110");
//        map.put("310", "310110");
//        Set<Map.Entry<String, Object>> entries = map.entrySet();
//        for (Map.Entry entry : entries) {
//            System.out.println("=======" + entry.getKey() + " = " + entry.getValue());
//        }
//    }

    public static void main2(String[] args) {
        //实际业务场景中，要承受上万个并发线程请求，每个线程去维护一个订单
        for (int i = 0; i < 4; i++) {   //启动4个线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //具体的业务，每一个线程过来要经过服务器中多个模块做处理，过程是并发的，线程在此挂起
                    float orderMoney = new Random().nextFloat();
                    System.out.println("当前处理客户端的线程是：" + Thread.currentThread().getName() +
                            "，后台显示订单金额为：" + orderMoney);
                    //每一个线程在此“开户”存了属于它自己的数据orderMoney
                    orderMoneys.set(orderMoney);
                    new AModule().getOrderMoney();
                    new BModule().getOrderMoney();
                }
            }).start();
        }
    }

    //仓库查询模块，A
    static class AModule {
        public void getOrderMoney() {
            System.out.println(Thread.currentThread().getName() + "进入A模块处理完毕之后，当前金额为：" + orderMoneys.get());
        }
    }

    //账号余额模块，这里直接调用第三方接口，B
    static class BModule {
        public void getOrderMoney() {
            System.out.println(Thread.currentThread().getName() + "进入B模块处理完毕之后，当前金额为：" + orderMoneys.get());
        }
    }

    //通知商户发货模块

    //。。。
}
