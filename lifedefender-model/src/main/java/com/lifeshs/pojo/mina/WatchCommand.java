package com.lifeshs.pojo.mina;

/**
 *  版权归
 *  TODO 老人表 上传数据的实体
 *  @author yuhang.weng  
 *  @DateTime 2016年7月6日 上午11:53:00
 */
public class WatchCommand {

	// [SG*123456789012345*FFFF*LK]
	// [厂商*设备 ID*内容长度*数据携带的参数]
	
	/** 厂商，固定为两个字节 */
	private String cs;
	/** 设备 ID */
	private String imei;
	/** 内容长度，固定为四个字节的ASCII码，16进制数 */
	private String length;
	/** 数据携带的参数 */
	private String params;
	
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = cs;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
}
