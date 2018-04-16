package com.sample.pool;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class ObjectPool<T> extends AbstractPool<T> {



    /**
     * 空闲对象池（已交回的对象），初始化时都是空闲的对象
     */

    private ConcurrentMap<String ,Vector<Factory<T>>> objects ;
    private Vector<Factory<T>> vector;
    private Factory<T> factory;
    private T t;

    private volatile int size = 0;// 对象池大小

    private volatile int objectMax = 0;// 某个对象最大个数

    public ObjectPool() {

    }

    public ObjectPool(Factory<T> factory,int objectMax) {
        this.objectMax = objectMax;
        objects=new ConcurrentHashMap<String, Vector<Factory<T>>>();

    }

    public ObjectPool(int size, int objectMax) {
        this.size = size;
        this.objectMax = objectMax;
    }



    @Override
    public synchronized Factory<T> getObject(String className) {
        vector=objects.get(className);
        if (vector==null){
           factory= createNewObject(className);
           if (factory!=null){
               factory.setBusy(true);
               return factory;
           }
        }else {
            for (Factory<T> factory:vector){
                if (factory.isBusy()==false){
                    factory.setBusy(true);
                    return factory;
                }
            }
            if (vector.size()==objectMax){
                wait(2000);
                getObject(className);
            }else if (vector.size()<objectMax){
                factory= createNewObject(className);
                if (factory!=null){
                    factory.setBusy(true);
                    return factory;
                }
            }
        }
        return null;
    }

    @Override
    protected synchronized void handleInvalidReturn(Factory<T> factory) {
        factory.destroy();
        factory=null;
    }

    @Override
    protected synchronized void returnToPool(Factory<T> factory) {
        factory.setBusy(false);
    }

    @Override
    protected synchronized boolean isValid(Factory<T> factory) {
        if (factory.getObject()!=null){
            return true;
        }
        return false;
    }

    @Override
    public synchronized void  shutdown() {
        for (Vector<Factory<T>> tVector:objects.values()){
            for (Factory<T> tFactory:tVector){
                factory.destroy();
                factory=null;
            }
            tVector.clear();
        }
        objects.clear();
    }

    private synchronized Factory<T> createNewObject(String className){
        try {
            Class clazz=Class.forName(className);
            t=(T)clazz.newInstance();
            factory=new BeanFactory<T>(t);
            System.out.println(factory);
            vector=new Vector<Factory<T>>();
            vector.add( factory);
            objects.put(className,vector);
            return factory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private synchronized void wait(int mSeconds) {
        try {
            Thread.sleep(mSeconds);
        }
        catch (InterruptedException e) {
        }
    }


}
