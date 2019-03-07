package com.lifeshs.pojo.C3;

import java.util.List;

import com.lifeshs.common.model.map.Gps;

/**
 *  版权归
 *  TODO C3 上传定位信息实体类
 *  @author duosheng.mo  
 *  @DateTime 2016年6月1日 上午11:21:24
 */
public class C3Location {

	private int userId;
	/**  类型  */    
	private String type;
	/**  指令  */    
	private String command;
	/**  设备串号  */    
	private String imei;
	/**  密码  */    
	private String password;
	/**  时间  */    
	private String datetime;
	/**  定位类型  locaType 等于 GPS 是GPS 数据上传 、等于  LBS 是 LBS数据上传 */    
	private String locaType;
	/**  短信号码  */    
	private String smsNum;
	/**    等于 DW 是实时定位、等于 SOS 是SOS定位信息，等于DS是定时上传定位的内容*/    
	private String realLoca;
	/**  GPS  */    
	private Gps gps;
	/**  LBS  */    
	private List<Lbs> listLBS;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getLocaType() {
		return locaType;
	}
	public void setLocaType(String locaType) {
		this.locaType = locaType;
	}
	public String getSmsNum() {
		return smsNum;
	}
	public void setSmsNum(String smsNum) {
		this.smsNum = smsNum;
	}
	public String getRealLoca() {
		return realLoca;
	}
	public void setRealLoca(String realLoca) {
		this.realLoca = realLoca;
	}
	public Gps getGps() {
		return gps;
	}
	public void setGps(Gps gps) {
		this.gps = gps;
	}
	public List<Lbs> getListLBS() {
		return listLBS;
	}
	public void setListLBS(List<Lbs> listLBS) {
		this.listLBS = listLBS;
	}
}
