package com.lifeshs.common.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XuZhanSi on 2016/12/10 0010.
 */
public class DataResult implements Serializable{
    private Map<String,Object> attr;//基本数据
    private Object obj;

    public Map<String, Object> getAttr() {
        return attr;
    }

    public void put(String key,Object value){
        this.attr.put(key,value);
    }

    public Object get(String key){
        if (this.attr==null)
            return null;
        return this.attr.get(key);
    }

    public Object getObj() {
        return obj;
    }

    public void setAttr(Map<String, Object> attr) {
        this.attr = attr;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public DataResult() {
        this.setAttr(new HashMap<String,Object>());
    }

    @Override
    public String toString() {
        return "DataResult{" +
                "attr=" + attr +
                ", obj=" + obj +
                '}';
    }
}
