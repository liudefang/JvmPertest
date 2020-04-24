package com.pertest;

public class JvmThread {
    /**
     * 线程死循环演示
     */

    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true);

            }
        }, "testBusyThread");
        thread.start();
    }
    /**
     * 线程锁等待演示
     */
    public  static void createLockThrad(final Object lock){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        }, "testLockThread");
        thread.start();
    }
}
