package com.roc.demo.j2se.threadInternet.semaphore;

/**
 * 开车测试
 */
public class DriveCarTest {

    public static void main(String[] args) {
        Driver driver = new Driver();
//        for (int i = 0; i < 5; i++) {
        for (int i = 0; i < 31; i++) {
            (new Car(driver)).start();
        }
    }

    /**
     * availablePermits()查看现在可用的信号量
     * @param args
     */
//    public static void main(String[] args) {
//        try{
//            Semaphore semaphore = new Semaphore(10);
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//            semaphore.acquire();
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//            semaphore.acquire(2);
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//            semaphore.acquire(3);
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//            semaphore.acquire(4);
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//            semaphore.release();
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//            semaphore.release(2);
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//            semaphore.release(3);
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//            semaphore.release(4);
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//    }


    /**
     * drainPermits()，这个方法返回即可所有的许可数目，并将许可置0
     * @param args
     */
//    public static void main(String[] args) {
//        try{
//            Semaphore semaphore = new Semaphore(10);
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//            semaphore.acquire();
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//            System.out.println("Semaphore drain permits" + semaphore.drainPermits());
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//            System.out.println("Semaphore drain permits" + semaphore.drainPermits());
//            System.out.println("Semaphore available permits: " + semaphore.availablePermits());
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//    }

}
