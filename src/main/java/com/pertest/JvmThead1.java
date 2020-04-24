package com.pertest;

public class JvmThead1 {
    /**
     * 线程死锁等待演示
     */
    public static class SynAddRunalbe implements Runnable{
        int a,b;
        public SynAddRunalbe(int a, int b){
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
