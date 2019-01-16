package com.roc.demo.j2se.itcastThread;

public class TraditionalThread {

    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前线程名currentThread：" + Thread.currentThread().getName());
                    System.out.println("当前线程名this：" + this.getName());
                }
            }
        };
        thread.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前线程名currentThread：" + Thread.currentThread().getName());
//                    System.out.println("当前线程名this：" + this.getName());
                }
            }
        });
        thread2.start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("当前线程名（Runnable）：" + Thread.currentThread().getName());
                        }
                    }
                }
        ){
            //子类已覆盖run方法，不会执行父类的run方法（也就不执行Runnable接口的run方法）
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前线程名（Thread子类）：" + Thread.currentThread().getName());
                }
            }
        }.start();
    }

}
