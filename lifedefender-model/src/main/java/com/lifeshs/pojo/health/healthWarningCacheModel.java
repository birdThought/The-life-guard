package com.lifeshs.pojo.health;

/**
 *  版权归
 *  TODO 短信预警缓存存储格式
 *  @author wenxian.cai 
 *  @datetime 2016年12月15日下午4:50:22
 */
public class healthWarningCacheModel {
	
	/*健康参数*/
	String healthType;
	/*短信发送次数*/
	int times;
	/*时间戳*/
	long timestamp;
	
	public String getHealthType() {
		return healthType;
	}
	public void setHealthType(String healthType) {
		this.healthType = healthType;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int l) {
		this.times = l;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long l) {
		this.timestamp = l;
	}
	
	
}
