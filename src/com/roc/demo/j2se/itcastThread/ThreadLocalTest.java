package com.roc.demo.j2se.itcastThread;

import java.util.Random;

/**
 * 有一种重要的线程思想：多个模块共享同一个对象，在线程内共享，在线程外独立
 * 用ThreadLocal代替Map<Thread, Integer>
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();
    private static ThreadLocal<MyThreadScopeData> myThreadScopeData =
            new ThreadLocal<MyThreadScopeData>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println("=======" +
                            Thread.currentThread().getName() +
                            " has put data :" + data);
                    x.set(data);
//                    MyThreadScopeData myData = new MyThreadScopeData();
//                    myData.setName("name" + data);
//                    myData.setAge(data);
//                    myThreadScopeData.set(myData);
                    MyThreadScopeData.getThreadInstance()
                            .setName("name" + data);
                    MyThreadScopeData.getThreadInstance()
                            .setAge(data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    /**
     * A模块
     */
    static class A {

        public void get() {
            int data = x.get();
            System.out.println("======= A from " +
                    Thread.currentThread().getName() +
                    " get data :" + data);
//            MyThreadScopeData myData = myThreadScopeData.get();
//            System.out.println("======= A from " +
//                    Thread.currentThread().getName() +
//                    " get myData :" + myData.getName() + "," +
//            myData.getAge());
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("======= A from " +
                    Thread.currentThread().getName() +
                    " get myData :" + myData.getName() + "," +
            myData.getAge());
        }

    }

    /**
     * B模块
     */
    static class B {
        public void get() {
            int data = x.get();
            System.out.println("======= B from " +
                    Thread.currentThread().getName() +
                    " get data :" + data);
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("======= B from " +
                    Thread.currentThread().getName() +
                    " get myData :" + myData.getName() + "," +
                    myData.getAge());
        }
    }

}

class MyThreadScopeData {
    //单例模式
    private MyThreadScopeData() {}
//    public static synchronized MyThreadScopeData getThreadInstance() {
    public static MyThreadScopeData getThreadInstance() {
        MyThreadScopeData instance = map.get();
        //懒汉
//        return instance;
        //饥汉
        if (instance == null) { //线程1new了之后还没来得及赋值，
            // 线程2进来发现instance还是null，于是会new多个对象，
            // 最终instance被最后的对象覆盖，所以要加上synchronized
            instance = new MyThreadScopeData();
            map.set(instance);
        }
        return instance;
    }
    //懒汉
//    private static MyThreadScopeData instance =
//            new MyThreadScopeData();
    //饥汉（等到需要才new）
//    private static MyThreadScopeData instance = null;

    private static ThreadLocal<MyThreadScopeData> map =
            new ThreadLocal<MyThreadScopeData>();

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
