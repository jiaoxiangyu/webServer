package com.sample.pool;

abstract class AbstractPool<T> implements Pool <T> {
    @Override
    public final void release(Factory<T> factory)
    {
        if(isValid(factory))
        {
            returnToPool(factory);
        }
        else
        {
            handleInvalidReturn(factory);
        }
    }

    //处理无效的返回对象
    protected abstract void handleInvalidReturn(Factory<T> factory);

    protected abstract void returnToPool(Factory<T> factory);

    //判断是否有效，防止无效对象放回对象池中
    protected abstract boolean isValid(Factory<T> factorys);
}
