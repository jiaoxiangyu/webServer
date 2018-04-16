package com.sample.pool;

public interface Factory<T> {

    public T getObject();

    public boolean isBusy();

    public void setBusy(boolean busy);

    public void destroy();
}
