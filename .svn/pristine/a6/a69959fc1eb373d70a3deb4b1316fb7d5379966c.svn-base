package com.lifeshs.common.constants.common;

/**
 * 
 *  终端数据错误类型
 *  @author dengfeng  
 *  @DateTime 2016-5-20 下午04:16:28
 */
public enum TerminalErrorType {

	/**
	 * 正常响应
	 */
	Normal(0, "正常响应"),
	/**
	 * 密码验证错误
	 */
	IncorrectPassword (1, "密码验证错误"),
	/**
	 * 数据格式异常
	 */
	DataFormatError (2, "数据格式异常"),
	/**
	 * imei还未与用户绑定
	 */
	NotBound (3, "imei还未与用户绑定"),
	/**
	 * 设备类型不匹配
	 */
	TypeMismatch (4, "设备类型不匹配"),
	
	/**  IMEI号不存在  */    
	NotImei (5, "IMEI号不存在"),
	
	/**  参数为空  */    
	ParamNull (6, "参数为空"),
	
	/** 关机成功短信发送失败  */    
	PoweroffSmsError (7, "关机成功短信发送失败"),
	
	/**  LBS获取经纬度失败  */    
	LbsFailure (8, "LBS获取经纬度失败"),
	
	/**  位置上传信息发送短信通知失败  */    
	LocalSendError (9, "位置上传信息发送短信通知失败"),
	
	/** TOKEN失效 */
	TokenOverTime (10, "Token失效"),
	
	/** TOKEN不正确 */
	IncorrectToken (11, "Token不正确");

    private int _value;
    private String _name;

    private TerminalErrorType(int value, String name)
    {
       _value = value;
        _name = name;
    }

    public int value()
    {
        return _value;
    }

    public String getName()
    {
        return _name;
     }
}
