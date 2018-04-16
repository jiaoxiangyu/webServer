package com.sample.pool;

import com.sample.servlet.Servlet;

public class ServletPool {
    private static Pool objectPool=new ObjectPool<Servlet>(new BeanFactory<Servlet>() ,1);

    public static Pool<Servlet> getObjectPool(){
        return objectPool;
    }
}
