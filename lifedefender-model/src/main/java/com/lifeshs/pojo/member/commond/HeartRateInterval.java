package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 定时脉搏数据实体类
 *  @author yuhang.weng  
 *  @DateTime 2016年7月7日 下午4:45:22
 */
public class HeartRateInterval {

	/** 开始时间 取值范围为00到24 */
	private String start;
	/** 结束时间 取值范围为00到24 */
	private String end;
	/** 间隔 */
	private int interval;
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	
	/** 
	 * 	<p>格式为start-end|interval
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月11日 上午10:01:15
	 *  @serverCode 服务代码
	 *  @return    
	 *  @see Object#toString()
	 */
	public String toString(){
		return start + "-" + end + "|" + interval;
	}
}
