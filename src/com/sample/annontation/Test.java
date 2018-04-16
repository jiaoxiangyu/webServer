package com.sample.annontation;

import com.sample.servlet.HelloWorldServlet;

import java.lang.annotation.Annotation;

public class Test {
    public static void main(String[] args) {
        Class clazz=HelloWorldServlet.class;
        WebServlet webServlet=(WebServlet) clazz.getAnnotation(WebServlet.class);
        System.out.println(webServlet.name());
        System.out.println(webServlet.urlPatterns());

    }
}
