package com.roc.demo.j2se.thread;

import com.thinkgem.jeesite.common.utils.DateUtils;

import java.util.Date;

public class ThreadSleepTest {

    public static void main(String[] args) {
        System.out.println("=======Test begin, curDateTime = " + DateUtils.formatDateTime(new Date()));
//        System.out.println("=======Test begin, curDateTime = " + System.currentTimeMillis());
        try {
            Thread.sleep(2900);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);//退出程序
        }
        System.out.println("=======Test end, curDateTime = " + DateUtils.formatDateTime(new Date()));
//        System.out.println("=======Test end, curDateTime = " + System.currentTimeMillis());
    }

}
