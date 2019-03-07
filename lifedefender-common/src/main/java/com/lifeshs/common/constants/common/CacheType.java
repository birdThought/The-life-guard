package com.lifeshs.common.constants.common;

/**
 *  缓存类型
 *  @author yuhang.weng  
 *  @DateTime 2016年6月6日 上午10:47:45
 */
public enum CacheType {
	
	/**  用户注册验证码缓存  */
	USER_REGISTERY_CACHE(1, "registeryValidCache"),
	/**  用户找回密码验证码缓存  */
	USER_RESET_CACHE(2, "resetPasswordCache"),
	/**  设备令牌缓存  */
	USER_TOKEN_CACHE(3, "tokenCache"),
	/**  用户修改手机号缓存  */    
	USER_MOBILE_MODIFY(4, "modifyMobileCache"),
	/**  用户修改邮箱缓存  */
	USER_EMAIL_MODIFY(5, "modifyEmailCache"),
	/** 用户公用数据缓存 */
	USER_SHARE_DATA(6, "userShareData"),
	/** 发送健康预警短信缓存 */
	SEND_HEALTHWARNING_SMS(7, "sendHealthWarningSMSCache"),
	/** 定位缓存 */
	LOCATION(8, "locationCache"),
	/** 发送电子围栏短信缓存 */
	SEND_FENCEWARNING_SMS(9, "sendFenceWarningSMSCache"),
	/** APP特有的，验证码 */
	APP_MOBILE_EMAIL_MODIFY(10, "modifyMobileEmailCache"),
	/** 发送短信休息一天 */
	SMS_ONE_DAY_BREAK(11, "SMSBreakOneDayCache"),
	/** 发送短信计数 */
	SMS_TIMES_BREAK(12, "SMSBreakTimesCache"),
	/** 资讯浏览量缓存 */
	NEWS_LOOK_CACHE(13,"newsLookCache"),
	/** 热门资讯缓存 */
	HOT_NEWS_CACHE(14,"hotNewsCache"),
	/** 认证缓存*/
	OAUTH_CACHE(15,"oauthCache"),
	/** PC登录缓存*/
	/*PC_LOGIN_CACHE(16,"pcLoginCache"),*/
	/** 注册验证token缓存 */
	REGISTER_TOKEN_CACHE(17, "registerTokenCache"),
	/** im消息存储 */
	IM_MESSAGE_CACHE(18, "imMessageCache");

	private int _value;
    private String _name;

    private CacheType(int value, String name)
    {
       _value = value;
        _name = name;
    }

	public int getValue() {
		return _value;
	}

	public void setValue(int _value) {
		this._value = _value;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}
}
