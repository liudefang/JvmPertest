<%@ page language="java" import="com.pertest.JVMHttpRequest" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

  <head>

    <title>JVM配置优化</title>

  </head>

  <body><%

            JVMHttpRequest pertest = new JVMHttpRequest();

            pertest.test1(request);

    %>

    调用结果:<%=pertest.test1(request)%><br/>



  </body>

</html>