package com.sample.test;

import com.sample.servlet.Servlet;

public class Test {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.sample.servlet.HelloWorldServlet");
        //ServletImpl  servlet= (ServletImpl) className.newInstance();
        Servlet servlet = (Servlet) clazz.newInstance();
        System.out.println(servlet.hashCode());

        Servlet servlet2 = (Servlet) clazz.newInstance();
        System.out.println(servlet2.hashCode());

        System.out.println(servlet.equals(servlet2));
    }
}
