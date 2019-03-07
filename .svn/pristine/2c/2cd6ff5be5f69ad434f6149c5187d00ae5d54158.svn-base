package com.lifeshs.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  有关List的工具类
 *  @author yuhang.weng  
 *  @DateTime 2016年10月21日 下午5:24:34
 */
public class ListUtil {

    /**
     *  移除ArrayList中重复的元素
     *  @author yuhang.weng 
     *  @DateTime 2016年10月21日 下午5:35:14
     *
     *  @param source LIST对象
     *  @param clazz LIST中的集合类型
     *  @return
     */
    public static <T> List<T> removeRepeatElement(List<T> source, Class<T> clazz) {
        List<T> tmp = new ArrayList<>();
        for(T s: source){
            if(Collections.frequency(tmp, s) < 1) tmp.add(s);
        }
        return tmp;
    }
}
