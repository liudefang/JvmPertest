<%@ page language="java" import="com.pertest.JvmThead1" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

  <head>

    <title>JVM配置优化</title>

  </head>

  <body><%
        int i;

          for(i=0; i < 100; i++){
                  new Thread(new JvmThead1.SynAddRunable(1,5)).start();

                  }

    %>

        调用结果:<%="线程死锁等待"%><br/>

  </body>

</html>