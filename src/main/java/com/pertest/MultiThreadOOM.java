package com.pertest;

public class MultiThreadOOM {
    public static class SleepThread implements Runnable{
        public void run() {
            try {
                Thread.sleep(10000000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
