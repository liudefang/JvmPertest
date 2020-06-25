package com.pertest;

public class JvmThead1 {
    /**
     * 线程死锁等待演示
     */
    public static class SynAddRunable implements Runnable{
        int a,b;
        public SynAddRunable(int a, int b){
            this.a = a;
            this.b = b;

        }
        @Override
        public void run(){
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a+b);
                }
            }
        }
    }
}
