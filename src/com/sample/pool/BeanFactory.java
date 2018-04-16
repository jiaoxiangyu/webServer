package com.sample.pool;

public class BeanFactory<T> implements Factory<T>{

    private T t;
    private boolean busy=false;//判断该对象是否被占用，默认未被占用

    public BeanFactory() {
    }

    public BeanFactory(T t) {
        this.t = t;
    }

    @Override
    public T getObject() {
        return t;
    }

    @Override
    public boolean isBusy() {
        return busy;
    }

    @Override
    public void setBusy(boolean busy) {
        this.busy=busy;
    }

    @Override
    public void destroy() {
        t=null;
    }
}
