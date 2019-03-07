package com.lifeshs.utils;

public enum SMSCommand {

	/** 定位指令。格式：lo#once*/
	LOCATION("{lo#?}"),
	
	/**上传心跳包指令*/
	HEARTPACKGE("{hp#?}"),
	
	/**电子围栏，轨迹。格式：lo#event #op类型#开始时间#结束时间#时间段#星期#上传次数#上传间隔**/
	DZWL("lo#event#?#?#?#?#?#?#?#?"),
	
	OUTFENCE("手表终端出了围栏"),
	
	ONFENCE("手表终端已进入围栏"),
	
	SENPOWEROFF("手表已经关机！"),
	
	LOCATIONSUCCEED("手表定位成功！"),
	
	SOS("注意：号码：?，发出SOS求救信息，发出SOS大概所在位置：?"),
	
	SOSGPS("注意：号码：?，发出SOS求救信息，发出SOS所在精准位置：?"),
	
	/** 监听指令 格式：mo#密码#监听号码*/
	MONITOR("{mo#?#?}"),
	
	/**修改密码。格式：格式: mm#once#事件名#旧密码#新密码*/
	MODNUM("mm#1#?#?#?"),
	
	/**发送SMS消息.格式：sm#once#短消息内容#发送的手机号*/
	SENDSMS("sm#once#?#?"),
	
	/**播放语音指令。格式：pv#once#语音内容*/
	PLAYVOICE("pv#once#?"),
	
	/**上传呼叫记录。格式：ca#event# op类型#开始时间#结束时间#时间段#星期*/
	CALLOG("ca#event#?#?#?#?"),
	
	/**上传LOG(日志文件)。格式：lg#event #op类型#开始时间#结束时间#时间段#星期#上传次数#上传间隔*/
	LOG("lg#event#?#?#?#?#?#?#?#?"),
	
	/**关机指令。格式：off#once*/
	POWEROFF("off#once"),
	
	/**音频控制。格式：sv#event #op类型#MIC音量#SPK音量#RING音量#开始时间#结束时间#时间段#星期*/
	SETVOLUM("sv#event#?#?#?#?#?#?#?#?"),
	
	/**清除SIM卡中SMS内容。格式：cl#once*/
	CLEARSMS("cl#once"),
	
	/**文件、图片、视屏等资源下载更新。格式：up#once#URL*/
	UPDATEFW("up#once#?"),
	
	/**通讯录、亲情号码、白名单、黑名单设置。格式：qq#once#
	 * 姓名1:电话号码:亲情号码:黑白名单#
	 * 姓名2:电话号码:亲情号码:黑白名单#
	 * 姓名3:电话号码:亲情号码:黑白名单#
	 * 姓名4:电话号码:亲情号码:黑白名单*/
	CONTACT(""),
	
	/**传感测试格式：ts#once#事件名#测试次数#测试间隔*/
	DATATEST("ts#once#?#?#?"),
	
	/**运行模式设定。格式：ru#event#运行模式#op类型#开始时间#结束时间#时间段#星期*/
	RUNMODE("ru#event#0 #add#08:00:00#09:00:00#inter#127"),
	
	/**群组监控**/
	GROUPMONITOR("lo#event#?#?"),
	
	/**实时定位**/
	SSDW("IMEI:?，大概位置为：?"),
	
	/**实时定位 GPS**/
	SSDWGPS("IMEI:?，精准位置为：?"),
	
	/**尊敬的用户，您注册验证码为{xxxxxxxxxx}请在{xx}分钟内使用**/
	CODE("尊敬的用户，您注册验证码为?，请在?分钟内使用"),
	
	/**温馨提示{xx}用户{xxxxxxxxxxxxxxxxxxxxxxxxxxxx}出现异常{xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx}请多加关注{xx}祝您阖家幸福健康{xxx}生命守护{xx}回复TD拒收{xx}**/
	JKTX("温馨提示：用户（?）?出现异常，?，请多加关注！祝您阖家幸福健康！（生命守护）回复TD拒收。"),
	
	/** 通用验证码 */
	/** 尊敬的{xxxxxxxxxx}您本次验证码为{xxxxxxxxxx}请在{xxx}分钟内使用{xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx} */
	TYYZM("尊敬的?您本次验证码为?请在?分钟内使用"),

	ORG_PUSH("?"),

	STORE_REGISTER("尊敬的用户，您的门店注册申请?");
	
	private String command;
	private SMSCommand(String command) {
		this.command = command;
	}
	public String GetCommand() {
		return command;
	}
}
