package com.xpanxion.xrest.core;

import java.util.List;

public interface XRestResource<T> {

    public T get(String id);

    public List<T> list();

    public String post(T content);

    public void put(T content, String id);

    public void delete(String id);
}
