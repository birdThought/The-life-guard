package com.lifeshs.pojo.healthStandard;

import java.util.HashMap;
import java.util.Map;

/**
 * 健康标准值（低/偏低/正常/偏高/高）
 * @author dengfeng
 * 
 */
public class HealthStandardValueEx<T> {
	
	T less ;  // <less:低，或肺活量1分，或血糖餐前min
	T min ;   // <min:偏低，或肺活量2分，或血糖餐后min
	T max ;   // >max:偏高，或肺活量4分，或血糖餐后max
	T more ;  // >more:高，或肺活量5分，或血糖餐前max

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
	public T getLess() {
		return less;
	}
	public void setLess(T less) {
		this.less = less;
	}
	public T getMore() {
		return more;
	}
	public void setMore(T more) {
		this.more = more;
	}
	
	public Map<String, String> toMap(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("min", min.toString());
		map.put("max", max.toString());
		map.put("less", less.toString());
		map.put("more", more.toString());
		return map;
	}
}
