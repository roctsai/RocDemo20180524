package com.roc.demo.j2se.itcastThread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {

    private Map<String, Object> cache = new HashMap<String, Object>();

    public static void main(String[] args) {

    }

    //方式二
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

//    public synchronized Object getData(String key) {    //方式一
    public Object getData(String key) {
        rwl.readLock().lock();  //正在读的时候能进入再读，不能再写
        Object value = null;
        try {
            value = cache.get(key);
            if (value == null) {    //如果读不到数据，则打开读锁，
                // 并上写锁（防止进入再读和再写）
                rwl.readLock().unlock();
                rwl.writeLock().lock(); //此处可能等了很多个写线程和读线程
                try {
                    if (value == null) {    //虽然上面有判断，但此
                        // 处并不多余，因为前一个写线程一打开写锁，可能紧接
                        // 着又是一个写线程进入
                        value = "aaaa"; //实际是，比如查询数据库
                    }
                } finally {
                    rwl.writeLock().unlock();   //直到写完后再打开
                    // 写锁。之后恢复可读和可写
                }
                rwl.readLock().lock();  //上读锁（只能再读，不能再写）
            }
        } finally {
            rwl.readLock().unlock();    //打开读锁，恢复可读和可写
        }
        return value;
    }

}
