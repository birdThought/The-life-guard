package com.lifeshs.service.terminal;

import java.util.List;

import com.lifeshs.pojo.member.commond.HL031Contact;
import com.lifeshs.pojo.mina.HLCommand;

/**
 * 
 *  面向上层的终端设备业务接口(HL03)
 *  @author dengfeng  
 *  @DateTime 2016-5-20 上午10:59:55
 */
public interface IHL03Terminal {
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月20日 上午9:23:48
	 *  @serverComment 终端登录
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String login(HLCommand data) throws Exception;
	
	/**
	 * 心跳
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 上午10:32:41
	 *
	 *  @return 终端接收JSON串
	 */
	public String heartpackge(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年5月25日 下午2:45:37
	 *  @serverComment 关机报告
	 *
	 *  @param datas
	 *  @return
	 *  @throws Exception
	 */
	public String poweroff(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年5月25日 下午3:13:45
	 *  @serverComment 计步睡眠上传(日)
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public String sleepofday(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年5月30日 下午2:17:12
	 *  @serverComment 位置信息
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public String receiveLocation(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年5月31日 上午11:29:53
	 *  @serverComment 测量数据上传
	 *
	 *  @param datas
	 *  @return
	 *  @throws Exception
	 */
	public String measure(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月1日 下午3:33:25
	 *  @serverComment 发送实时测量指令
	 *
	 *	@param imei 串号
	 *  @param typeNum 1：心率  2：体温  3：血压  4：心电  0：所有
	 *  @return
	 *  @throws Exception
	 */
	public String sendMeasureCommand(String imei, int typeNum) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月1日 下午3:53:34
	 *  @serverComment 实时定位指令
	 *
	 *  @param imei 串号
	 *  @return
	 *  @throws Exception
	 */
	public String sendLocationCommand(String imei) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月1日 下午3:55:28
	 *  @serverComment 实时监听指令
	 *
	 *  @param imei 串号
	 *  @param second 监听时长
	 *  @return
	 *  @throws Exception
	 */
	public String sendMoniterCommand(String imei, int second) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月1日 下午4:01:28
	 *  @serverComment 定时间间隔设置
	 *
	 *  @param imei 串号
	 *  @param heartRate 心率频率
	 *  @param step 计步频率
	 *  @param upload 上传频率
	 *  @return
	 *  @throws Exception
	 */
	public String sendTimerCommand(String imei, int heartRate, int step, int upload) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月1日 下午4:11:17
	 *  @serverComment 服务注解
	 *
	 *  @param imei 串号
	 *  @param typeNum 发送消息类型 1：文字，2：语音，3：图片(2、3暂时不支持)
	 *  @param message 文字信息(需要UTF-8转码)
	 *  @param terminalCode
	 *  @param voiceSecond
	 *  @return
	 *  @throws Exception
	 */
	public String sendMessageCommand(String imei, int typeNum, String message, String terminalCode, int voiceSecond) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月1日 下午5:36:52
	 *  @serverComment 关机指令
	 *
	 *  @param imei
	 *  @return
	 *  @throws Exception
	 */
	public String sendPowerOffCommand(String imei) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 上午10:43:50
	 *  @serverComment 设置健康参数正常阈值
	 *
	 *  @param imei 串号
	 *  @param hMin 心率最小值
	 *  @param hMax 心率最大值
	 *  @param tMin 体温最小值
	 *  @param tMax 体温最大值
	 *  @param dMin 舒张压最小值
	 *  @param dMax 舒张压最大值
	 *  @param sMin 收缩压最小值
	 *  @param sMax 收缩压最大值
	 *  @param eMin 心电最小值
	 *  @param eMax 心电最大值
	 *  @return
	 *  @throws Exception
	 */
	public String sendHealthStandardCommand(String imei, int hMin, int hMax, int tMin, int tMax, int dMin, int dMax, int sMin, int sMax, int eMin, int eMax) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 上午10:52:44
	 *  @serverComment 设置服务器地址
	 *
	 *  @param imei 串号
	 *  @param ipAddress 服务器ip地址
	 *  @param port 端口号
	 *  @return
	 *  @throws Exception
	 */
	public String sendServerAddressCommand(String imei, int ipAddress, int port) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 上午11:02:31
	 *  @serverComment 设置提醒
	 *
	 *  @param imei 串号
	 *  @param type 类型字段,包括1：添加，2：修改，3：删除
	 *  @param status 表示是否开关1表示打开提醒，0表示关闭提醒
	 *  @param recordId 记录ID
	 *  @param noticeTime 提醒时间，2100表示晚上9点提醒
	 *  @param weeks 对应一周七天（周一到周日），0：不提醒，1提醒
	 *  @param content 提示内容，UTF8
	 *  @return
	 *  @throws Exception
	 */
	public String sendNoticeCommand(String imei, int type, int status, String recordId, String noticeTime, String weeks, String content) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 上午11:10:44
	 *  @serverComment 服务注解
	 *
	 *  @param imei 串号
	 *  @param content 消息内容
	 *  @param mobile 号码
	 *  @return
	 *  @throws Exception
	 */
	public String sendSMSCommand(String imei, String content, String mobile) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 上午11:18:36
	 *
	 *  @param imei 串号
	 *  @param contacts HL031电话本对象List集合
	 *  @return
	 *  @throws Exception
	 */
	public String sendContactCommand(String imei, List<HL031Contact> contacts) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 下午1:37:13
	 *  @serverComment 设置短信网关号码
	 *
	 *  @param imei 串号
	 *  @param gateway 网关
	 *  @return
	 *  @throws Exception
	 */
	public String sendSMSGatewayCommand(String imei, String gateway) throws Exception;
	
	/**
	 * 	<p>如果commandType为0或1，不需要设置numbers参数的值，null就可以
	 * 	<p>如果commandType为2，需要传一个List&lt;String&gt;类型的电话号码数组
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 下午1:41:18
	 *  @serverComment 设置黑名单
	 *
	 *  @param imei 串号
	 *  @param commandType 0_表示不限制接听、1_表示只能接收电话本的号码、2_表示设置黑名单
	 *  @param numbers 号码list
	 *  @return
	 *  @throws Exception
	 */
	public String sendBlackListCommand(String imei, int commandType, List<String> numbers) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 下午1:46:01
	 *  @serverComment 设置身高体重年龄步距
	 *
	 *  @param imei 串号
	 *  @param height 身高cm
	 *  @param weight 体重kg
	 *  @param age 年龄
	 *  @param stepLength 步长cm
	 *  @return
	 *  @throws Exception
	 */
	public String sendHeightWeightAgeStepLengthCommand(String imei, int height, int weight, int age, int stepLength) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 下午1:51:04
	 *  @serverComment 请求身高体重年龄步距
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String heightWeightAgeStepLength(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 下午8:01:07
	 *  @serverComment 获取天气指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String weather(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月5日 下午4:33:24
	 *  @serverComment 数据（计步/LBS/脉搏）定时上传
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String timer(HLCommand data) throws Exception;
}
