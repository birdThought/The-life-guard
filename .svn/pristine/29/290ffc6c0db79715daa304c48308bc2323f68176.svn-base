package com.lifeshs.service.terminal;

import java.util.List;

import com.lifeshs.pojo.member.commond.HeartRateInterval;
import com.lifeshs.pojo.member.commond.LCHBContact;
import com.lifeshs.pojo.mina.HLCommand;

/**
 *  版权归
 *  TODO 梦时代
 *  @author yuhang.weng  
 *  @DateTime 2016年7月7日 上午11:33:07
 */
public interface ILCHBTerminal {

	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午1:35:37
	 *  @serverComment 终端请求登录
	 *
	 *  @param data
	 *  @return
	 */
	public String login(HLCommand data) throws Exception ;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午1:41:32
	 *  @serverComment 终端上报心跳
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String heartPackage(HLCommand data) throws Exception ;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午1:42:42
	 *  @serverComment 终端上传脉搏数据
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String heartRate(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午1:59:14
	 *  @serverComment 终端上传定位数据指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String location(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午2:24:17
	 *  @serverComment 终端上传综合数据指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String measure(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午2:52:03
	 *  @serverComment 终端上传血压数据指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String bloodPressure(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午3:12:07
	 *  @serverComment 终端获取天气指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String weather(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午3:18:50
	 *  @serverComment 终端上传关机指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String powerOff(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午4:54:28
	 *  @serverComment 终端上传血糖数据指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String glucometer(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午4:56:19
	 *  @serverComment 终端发送消息指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String message(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午11:51:04
	 *  @serverComment 终端获取积分，关注，点赞数据指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String memberPointFollowerHotData(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午11:51:02
	 *  @serverComment 服务器设置终端时钟界面指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String mainClockStylish(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午3:28:17
	 *  @serverComment 服务器转发实时脉搏指令
	 *
	 *  @param imei
	 *  @return
	 *  @throws Exception
	 */
	public String sendHeartRateCommand(String imei) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午3:32:01
	 *  @serverComment 服务器转发实时定位指令
	 *
	 *  @param imei
	 *  @return
	 *  @throws Exception
	 */
	public String sendLocationCommand(String imei) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午4:17:50
	 *  @serverComment 服务器设置SOS号码指令
	 *
	 *  @param imei
	 *  @param number
	 *  @return
	 *  @throws Exception
	 */
	public String sendSOSCommand(String imei, String[] number) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午4:27:07
	 *  @serverComment 服务器设置步履年龄体重指令
	 *
	 *  @param imei
	 *  @param height 身高
	 *  @param weight 体重
	 *  @param age 年龄
	 *  @param stepLength 步长
	 *  @return
	 *  @throws Exception
	 */
	public String sendHeightWeightAgeStepLength(String imei, int height, int weight, int age, int stepLength) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午4:29:32
	 *  @serverComment 服务端下发消息指令
	 *
	 *  @param imei
	 *  @param contentType 消息类型 1：文字，2：语音，3：图片
	 *  @param contentLength 消息的长度
	 *  @param content 消息内容(如果类型1，代表文字信息，如果是2或者3代表语音或图片经过base64编码后的字符串)
	 *  @param appAccountNumber app账号
	 *  @param second 如果为语音，代表消息时长，单位为秒
	 *  @return
	 *  @throws Exception
	 */
	public String sendMessageCommand(String imei, int contentType, int contentLength, String content, String appAccountNumber, int second) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午4:47:45
	 *  @serverComment 服务端下发分段定时脉搏指令
	 *
	 *  @param imei
	 *  @param intervals 定时脉搏数据实体类的list集合
	 *  @return
	 *  @throws Exception
	 */
	public String sendHeartRateIntervalCommand(String imei, List<HeartRateInterval> intervals) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午4:50:00
	 *  @serverComment 服务端设置脉搏阈值指令
	 *
	 *  @param imei
	 *  @param min 最小值
	 *  @param max 最大值
	 *  @return
	 *  @throws Exception
	 */
	public String sendHeartRateHealthStandardCommand(String imei, int min, int max) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午9:09:54
	 *  @serverComment 服务端设置地址指令
	 *
	 *  @param imei
	 *  @param ip ip地址
	 *  @param port 端口
	 *  @return
	 *  @throws Exception
	 */
	public String sendIpAddressAndPort(String imei, String ip, int port) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午5:01:29
	 *  @serverComment 服务端手表提醒指令
	 *
	 *  @param imei
	 *  @param noticeType 1，类型字段表示提醒的类型，现有的类型包括1：天气提醒，2：运动提醒，3：心率提醒，4：睡眠提醒
	 *  @param status 1，表示是否开关1表示打开提醒，0表示关闭提醒
	 *  @param noticeTime 提醒时间0900表示九点提醒，2100表示晚上9点提醒，如果关闭提醒，则为空""(提示:空就传一个null到noticeTime中就可以了)
	 *  @return
	 *  @throws Exception
	 */
	public String sendWatchNoticeCommand(String imei, int noticeType, int status, String[] noticeTime) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 下午7:45:50
	 *  @serverComment 服务端设置电话号码指令
	 *
	 *  @param imei
	 *  @param contacts 电话本数据 实体 list集合
	 *  @return
	 *  @throws Exception
	 */
	public String sendContactCommand(String imei, List<LCHBContact> contacts) throws Exception;
}