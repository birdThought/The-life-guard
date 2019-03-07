package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 梦时代手表电话本数据 实体
 *  @author yuhang.weng  
 *  @DateTime 2016年7月7日 下午5:09:29
 */
public class LCHBContact {

	/** 电话本排位 1表示排列在第一位 */
	private int rankNumber;
	/** 联系人名称 */
	private String name;
	/** 联系人号码 */
	private String number;
	
	public int getRankNumber() {
		return rankNumber;
	}
	public void setRankNumber(int rankNumber) {
		this.rankNumber = rankNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	/** 
	 * 	<p>格式为number,name,rankNumber
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月11日 上午10:02:59
	 *  @serverCode 服务代码
	 *  @return    
	 *  @see Object#toString()
	 */
	public String toString(){
		return number + "," + name + "," + rankNumber;
	}
}
