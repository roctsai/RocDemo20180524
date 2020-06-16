package com.roc.demo.j2se.threadDataShare;

public class BusinessDemo {

    //内部一个控制属开关
    private boolean isShowSonThread = true;

    //子线程先进来搞事
    public synchronized void sonBusiness(int i) {
        while (!isShowSonThread) {
            try {
                //子线程就在外面等着了，死了
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //代表厕所没人
        for (int j = 1; j <= 30; j++) {
            System.out.println("=======子线程运行第：" + i + "轮，第：" + j + "次");
        }
        //厕所门打开
        isShowSonThread = false;
        //通知主线程这个哥们
        this.notify();
//        System.out.println("=======BusinessDemo->sonBusiness i = " + i);
    }


    public synchronized void mainBusiness(int i) {
        while (isShowSonThread) {
            try {
                //子线程就在外面等着了，死了
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("=======BusinessDemo->mainBusiness i = " + i);
        //代表厕所没人，主线程就去做如下业务
        for (int j = 1; j <= 40; j++) {
            System.out.println("主线程运行第：" + i + "轮，第：" + j + "次");
        }
        //厕所门打开
        isShowSonThread = true;
        //通知主线程这个哥们
        this.notify();
    }

}
