package com.lifeshs.pojo.C3;

/**
 *  版权归
 *  TODO LBS 实体类
 *  @author duosheng.mo  
 *  @DateTime 2016年6月1日 上午11:14:28
 */
public class Lbs {
	
	/**  家代码  */    
	private int mcc;
	/**  网络号码（中国移动为0，中国联通为1，中国电信为2）  */    
	private int mnc;
	/**  位置区域码  */    
	private int lac;
	/**  ，基站编号  */    
	private int cell;
	/**  基站信号强度  */    
	private int signal;
	
	public int getMcc() {
		return mcc;
	}
	public void setMcc(int mcc) {
		this.mcc = mcc;
	}
	public int getMnc() {
		return mnc;
	}
	public void setMnc(int mnc) {
		this.mnc = mnc;
	}
	public int getLac() {
		return lac;
	}
	public void setLac(int lac) {
		this.lac = lac;
	}
	public int getCell() {
		return cell;
	}
	public void setCell(int cell) {
		this.cell = cell;
	}
	public int getSignal() {
		return signal;
	}
	public void setSignal(int signal) {
		this.signal = signal;
	}
}
