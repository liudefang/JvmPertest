package com.pertest;

/**
 * Time    : 2020/4/24 3:42 下午
 * Author  : mike.liu
 * File    : ForTest.java
 */
public class ForTest {

    public static void main(String[] args) {
        for(int i=0;i<10000;i++){

            PerTest b = new PerTest();

            PerMain.list.add(b);

            System.out.println("SIZE:"+PerMain.list.size());
            System.out.println("counter:"+PerMain.counter++);


        }

    }
}
