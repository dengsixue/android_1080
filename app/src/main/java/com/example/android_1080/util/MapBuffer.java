package com.example.android_1080.util;

import java.util.HashMap;

/**
 * created Dengsixue
 * Date 2021/1/23 19:44
 * Descaption
 */
public class MapBuffer<K,V> {
    private HashMap<K,V> map;
    public MapBuffer<K,V> builder(){
        map=new HashMap<>();
        return this;
    }
    public MapBuffer<K,V> put(K key, V value){
        map.put(key,value);
        return this;
    }
    public MapBuffer<K,V> put(boolean isPut, K key, V value){
        if (isPut){
            map.put(key,value);
        }
        return this;
    }
    public HashMap<K,V> build(){
        return map;
    }
}
