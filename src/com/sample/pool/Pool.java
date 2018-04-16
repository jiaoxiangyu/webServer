package com.sample.pool;

public interface Pool<T> {

    //获得对象
    Factory<T> getObject(String className);

    //把对象放回对象池
    void release(Factory<T> factory);

    //销毁对象池
    void shutdown();

}
