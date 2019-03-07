package com.lifeshs.pojo.mina;

/**
 *  版权归
 *  TODO HL 上传数据的实体
 *  @author duosheng.mo  
 *  @DateTime 2016年7月4日 下午3:06:07
 */
public class HLCommand {
//	{终端类型:版本号:加密方式:识别码:imei:命令:参数}
	
	/** 终端类型:0代表平台， 2代表HL-031，9代表app */
	private String TerminalType;
	
	/** 版本号 */
	private String versionNum;
	
	/** 加密方式 */
	private String encryptionMode;
	
	/** 识别码 */
	private String iDCode;
	
	/** imei */
	private String imei;
	
	/** 命令 */
	private String commond;
	
	/** 参数 */
	private String param;

	public String getTerminalType() {
		return TerminalType;
	}

	public void setTerminalType(String terminalType) {
		TerminalType = terminalType;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getEncryptionMode() {
		return encryptionMode;
	}

	public void setEncryptionMode(String encryptionMode) {
		this.encryptionMode = encryptionMode;
	}

	public String getiDCode() {
		return iDCode;
	}

	public void setiDCode(String iDCode) {
		this.iDCode = iDCode;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getCommond() {
		return commond;
	}

	public void setCommond(String commond) {
		this.commond = commond;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	
	
}
