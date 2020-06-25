package com.pertest;

public class JVMCase {
    // 常量
    public final static String MAN_SEX_TYPE = "man";

    // 静态变量
    public static String WOMAN_SEX_TYPE = "woman";

    public static void main(String[] args){
        Student stu = new Student();
        stu.setName("Jacket");
        stu.setSexType(MAN_SEX_TYPE);
        stu.setAge(30);

        JVMCase jvmCase = new JVMCase();

        //调用静态方法
        println(stu);
        //调用非静态方法
        jvmCase.sayHello(stu);
    }

    //常规静态方法
    public static void println(Student stu){
        System.out.println("name:" + stu.getName() + "; sex" + stu.getSexType() + "; age:" + stu.getAge());

    }
    //非静态方法
    public void sayHello(Student stu){
        System.out.println(stu.getName() + "say: hello");
    }




}

class Student{
    String name;
    String sexType;
    int age;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getSexType(){
        return sexType;
    }
    public void setSexType(String sexType){
        this.sexType = sexType;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
}
