package com.lifeshs.service.terminal;

import java.util.List;

import com.lifeshs.pojo.member.commond.HL031Contact;
import com.lifeshs.pojo.mina.HLCommand;

/**
 *  版权归
 *  TODO HL03服务接口类
 *  @author yuhang.weng  
 *  @DateTime 2016年5月30日 下午12:56:34
 */
public interface IHL03Service {
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月20日 上午10:28:36
	 *  @serverComment 登录 login
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t1(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午4:10:50
	 *  @serverComment 心跳报文 heartPackage
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t2(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午4:13:17
	 *  @serverComment 实时测量指令(服务器发送到终端) sendMeasure
	 *
	 *	@param imei 串号
	 *  @param typeNum 1：心率  2：体温  3：血压  4：心电  0：所有
	 *  @return
	 *  @throws Exception
	 */
	public String s3(String imei, int typeNum) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午4:14:23
	 *  @serverComment 测试量数据上传 uploadMeasureData
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t4(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午4:15:02
	 *  @serverComment 实时定位指令(服务器发送到终端) sendLocation
	 *
	 *  @param imei 串号
	 *  @return
	 *  @throws Exception
	 */
	public String s5(String imei) throws Exception;

	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月1日 下午12:56:20
	 *  @serverComment 定位数据上传 location
	 *
	 *  @param datas
	 *  @return
	 *  @throws Exception
	 */
	public String t6(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午4:17:37
	 *  @serverComment 实时监听指令(服务器发送到终端) sendMonitor
	 *
	 *  @param imei 串号
	 *  @param second 监听时长
	 *  @return
	 *  @throws Exception
	 */
	public String s7(String imei, int second) throws Exception;

	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月1日 下午12:54:56
	 *  @serverComment 计步睡眠数据上传 sleepofday
	 *
	 *  @param datas
	 *  @return
	 *  @throws Exception
	 */
	public String t8(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午4:24:57
	 *  @serverComment 数据定时上传 dataUpload
	 *
	 *  @param datas
	 *  @return
	 *  @throws Exception
	 */
	public String t9(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午4:33:44
	 *  @serverComment 定时间隔设置(服务器发送到终端) sendTimeInterval
	 *
	 *  @param imei 串号
	 *  @param heartRate 心率频率
	 *  @param step 计步频率
	 *  @param upload 上传频率
	 *  @return
	 *  @throws Exception
	 */
	public String s10(String imei, int heartRate, int step, int upload) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午5:01:40
	 *  @serverComment 获取天气指令 weather
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t11(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午5:02:10
	 *  @serverComment 给终端发送消息(服务器发送到终端) sendMessage
	 *
	 *  @param imei 串号
	 *  @param typeNum 发送消息类型 1：文字，2：语音，3：图片(2、3暂时不支持)
	 *  @param message 文字信息(需要UTF-8转码)
	 *  @param terminalCode
	 *  @param voiceSecond
	 *  @return
	 *  @throws Exception
	 */
	public String s12(String imei, int typeNum, String message, String terminalCode, int voiceSecond) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月1日 下午12:47:24
	 *  @serverComment 关机指令(服务器发送到终端) sendPowerOff
	 *
	 *  @param imei 串号
	 *  @return
	 *  @throws Exception
	 */
	public String s13(String imei) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午5:07:14
	 *  @serverComment 关机报告 powerOff
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t14(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午5:08:18
	 *  @serverComment 设置健康参数正常阈值(服务器发送到终端) sendHealthStandard
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
	public String s15(String imei, int hMin, int hMax, int tMin, int tMax, int dMin, int dMax, int sMin, int sMax, int eMin, int eMax) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午5:10:49
	 *  @serverComment 设置服务器地址(服务器发送到终端) sendServerAddress
	 *
	 * 	@param imei 串号
	 *  @param ipAddress 服务器ip地址
	 *  @param port 端口号
	 *  @return
	 *  @throws Exception
	 */
	public String s16(String imei, int ipAddress, int port) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午5:11:51
	 *  @serverComment 设置提醒(服务器发送到终端) sendNotice
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
	public String s17(String imei, int type, int status, String recordId, String noticeTime, String weeks, String content) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午5:12:46
	 *  @serverComment 终端发送短信(服务器发送到终端) sendSMS
	 *
	 *  @param imei 串号
	 *  @param content 消息内容
	 *  @param mobile 号码
	 *  @return
	 *  @throws Exception
	 */
	public String s18(String imei, String content, String mobile) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 上午11:18:36
	 *
	 *  @param imei 串号
	 *  @param contacts HL031电话本对象List集合
	 *  @return
	 *  @throws Exception
	 */
	public String s19(String imei, List<HL031Contact> contacts) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午5:16:14
	 *  @serverComment 设置短信网关号码(服务器发送到终端) sendDNSNumber
	 *
	 *  @param imei 串号
	 *  @param gateway 网关
	 *  @return
	 *  @throws Exception
	 */
	public String s20(String imei, String gateway) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月30日 下午5:17:27
	 *  @serverComment 设置黑名单(服务器发送到终端) sendBlackList
	 *
	 *  @param imei 串号
	 *  @param numbers 号码list
	 *  @return
	 *  @throws Exception
	 */
	public String s21(String imei, int commandType, List<String> numbers) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 上午10:30:25
	 *  @serverComment 设置身高体重年龄步距
	 *
	 *	@param imei 串号
	 *  @param height 身高
	 *  @param weight 体重
	 *  @param age 年龄
	 *  @param stepLength 步距
	 *  @return
	 *  @throws Exception
	 */
	public String s22(String imei, int height, int weight, int age, int stepLength) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月4日 上午10:31:57
	 *  @serverComment 请求身高体重年龄步距
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t23(HLCommand data) throws Exception;
}
