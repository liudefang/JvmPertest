package com.pertest;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class JVMHttpRequest {
    @RequestMapping(value = "/test1")
    public String test1(HttpServletRequest request) {
        List<Byte[]> temp = new ArrayList<Byte[]>();

        Byte[] b = new Byte[1024 * 1024];
        temp.add(b);

        return "success";

    }



    }

