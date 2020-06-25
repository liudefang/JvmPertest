package com.pertest;

public class ThreadPerTest {
    public static class ThreadPerTask implements Runnable{
        public void run() {
            while (true){
                double value = Math.random() * Math.random();
            }
        }
    }
    public static class LazyTask implements Runnable{
        public void run(){
            try {
                while (true){
                    Thread.sleep(1000);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
