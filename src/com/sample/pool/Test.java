package com.sample.pool;

import com.sample.servlet.Servlet;

public class Test {
    public static void main(String[] args) {
       // ObjectPool<Servlet> objectPool=new ObjectPool<>(new BeanFactory<Servlet>() ,1);

        Factory<Servlet> factory=ServletPool.getObjectPool().getObject("com.sample.servlet.HelloWorldServlet");
        System.out.println(factory.getObject().hashCode());
    }


}
