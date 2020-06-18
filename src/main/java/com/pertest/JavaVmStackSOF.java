package com.pertest;

public class JavaVmStackSOF {
    public int stackLength=1;
    public void stackLeak(){
        stackLength ++;
        stackLeak();
    }
}
