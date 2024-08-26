package org.nanotek.meta.util;

public class Holder<T>{

    private T t;

    public Holder(T t){
        this.t = t;
    }

    public void put(T t){
        this.t = t;
    }

    public T get(){
        return t;
    }
    
}