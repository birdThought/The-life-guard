package com.lifeshs.pojo.healthStandard;

import java.util.HashMap;
import java.util.Map;

/**
 * 健康标准值（正常范围）
 * @author dengfeng
 *
 */
public class HealthStandardValue<T> {
	
	T min ;
	T max ;
	
	public T getMin() {
		return min;
	}
	public void setMin(T min) {
		this.min = min;
	}
	public T getMax() {
		return max;
	}
	public void setMax(T max) {
		this.max = max;
	}
	/**
	 * 返回MAP格式
	 * @return
	 */
	public Map<String, String> toMap(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("min", min.toString());
		map.put("max", max.toString());
		return map;
	}
	
	@Override
	public String toString(){
		return "(" + min + "-"+ max + ")";
	}
	
	/**
	 * 判断给入的值是否异常
	 * @param data
	 * @return
	 */
	public boolean isWanring(T data){
		if(min.getClass() == String.class){
			float fmin = Float.valueOf((String)min);
			float fmax = Float.valueOf((String)max);
			float fdata = Float.valueOf((String)data);
			if(fdata < fmin || fdata > fmax)
				return true;
		}else{
			int imin = (Integer)min;
			int imax = (Integer)max;
			int idata = (Integer)data;
			if(idata < imin || idata > imax)
				return true;
		}
		return false;
	}
}
