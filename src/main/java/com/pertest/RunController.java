package com.pertest;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Unsafe;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by diyangxia on 2017/3/6.
 */
@org.springframework.stereotype.Controller
public class RunController {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    // jvm优化
    @RequestMapping(value = "/test1")
    @ResponseBody
    public String test1(HttpServletRequest request) {
        List<Byte[]> temp = new ArrayList<Byte[]>();

        Byte[] b = new Byte[1024 * 1024];
        temp.add(b);

        return "jvm";

    }
    @RequestMapping(value = "pertest0", method = RequestMethod.GET)
    @ResponseBody
    public String pertest0(HttpServletRequest request){
        ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();
        localVariable.set(new Byte[4096*1024]); //为线程添加变量
        return "Thread success";
    }


    static class OOMObject{

    }
    // 内存溢出

    @RequestMapping(value = "test2", method = RequestMethod.GET)
    @ResponseBody
    public String test2() {
        List<OOMObject> list=new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());

        }
    }

    // 线程死锁等待
    @RequestMapping(value = "SynAddRunalbe", method = RequestMethod.GET)
    @ResponseBody
    public void SynAddRunalbe() {
        int i;

        for(i=0; i < 100; i++){
            new Thread(new JvmThead1.SynAddRunalbe(1,3)).start();

        }
    }

    // 线程等待
    @RequestMapping(value = "TheadWait", method = RequestMethod.GET)
    @ResponseBody
    public void TheadWait() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        JvmThread.createBusyThread();
        br.readLine();
        Object obj = new Object();
        JvmThread.createLockThrad(obj);
    }

    // 内存溢出
    @RequestMapping(value = "pertest1", method = RequestMethod.GET)
    @ResponseBody
    public String pertest1() {
        for(int i=0;i<10000;i++){

            PerTest b = new PerTest();

            PerMain.list.add(b);

        }
        System.out.println("SIZE:"+PerMain.list.size());
        return("counter:"+PerMain.counter++);
    }

    //堆溢出
    @RequestMapping(value = "pertest2", method = RequestMethod.GET)
    @ResponseBody
    public String SimpleHeapOOM() {
        ArrayList<byte[]> list = new ArrayList<byte[]>();
        for (int i = 0; i < 10240; i++) {
            list.add(new byte[1024 * 1024]);
            System.out.println("循环次数:"+i);
        }
        return "success";
    }

    //过多线程导致内存溢出
    @RequestMapping(value = "pertest3", method = RequestMethod.GET)
    @ResponseBody
    public String MultiThread(){
        for(int i=0;i<1500;i++){
            new Thread(new MultiThreadOOM.SleepThread(),"Thread"+i).start();
            System.out.println("Thread"+i+" created");
        }
        return "Thread";
    }

    //虚拟机栈和本地方法栈溢出
    @RequestMapping(value = "pertest4", method = RequestMethod.GET)
    @ResponseBody
    public String VmStack(){
        JavaVmStackSOF oom=new JavaVmStackSOF();
        try {

            oom.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length:"+oom.stackLength);
            throw e;
        }
        return "JavaVmStackSOF";
    }

    //运行时常量池溢出(还是堆溢出）
    @RequestMapping(value = "pertest5", method = RequestMethod.GET)
    @ResponseBody
    public String RuntimeConstantPoolOOM(){
        //使用List保持着常量池引用，避免FULL Gc回收常量池行为
        List<String> list = new ArrayList<String>();
        //10MB的PermSize在integer范围内足够产生OOM了
        int i = 0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }


    //方法区出现内存溢出的异常
    @RequestMapping(value = "pertest6", method = RequestMethod.GET)
    @ResponseBody
    public String JavaMethodAreaOOM(){
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(obj,args);
                }
            });
            enhancer.create();
        }
    }

    //本机直接内存溢出(不出现)
    //使用unsafe分配本机内存
    @RequestMapping(value = "pertest7", method = RequestMethod.GET)
    @ResponseBody
    public String DirectMemoryOOM() throws IllegalAccessException {
        final int _1MB = 1024*1024;
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }



}
