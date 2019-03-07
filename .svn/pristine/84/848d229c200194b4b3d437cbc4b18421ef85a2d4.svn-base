package com.lifeshs.service.terminal.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.pojo.member.commond.HL031Contact;
import com.lifeshs.pojo.mina.HLCommand;
import com.lifeshs.service.terminal.IHL03Service;
import com.lifeshs.service.terminal.IHL03Terminal;

/**
 *  版权归
 *  TODO HL03服务实现类
 *  @author yuhang.weng  
 *  @DateTime 2016年5月30日 上午9:50:15
 */
@Service("hL03ServiceImpl")
public class HL03ServiceImpl implements IHL03Service {

	@Autowired
	private IHL03Terminal hL03Adapter;

	@Override
	public String t1(HLCommand data) throws Exception {
		return hL03Adapter.login(data);
	}

	@Override
	public String t2(HLCommand data) throws Exception {
		return hL03Adapter.heartpackge(data);
	}

	@Override
	public String s3(String imei, int typeNum) throws Exception {
		return hL03Adapter.sendMeasureCommand(imei, typeNum);
	}

	@Override
	public String t4(HLCommand data) throws Exception {
		// TODO 方法未完善
		return hL03Adapter.measure(data);
	}

	@Override
	public String s5(String imei) throws Exception {
		return hL03Adapter.sendLocationCommand(imei);
	}

	@Override
	public String t6(HLCommand data) throws Exception {
		return hL03Adapter.receiveLocation(data);
	}

	@Override
	public String s7(String imei, int second) throws Exception {
		return hL03Adapter.sendMoniterCommand(imei, second);
	}

	@Override
	public String t8(HLCommand data) throws Exception {
		return hL03Adapter.sleepofday(data);
	}

	@Override
	public String t9(HLCommand data) throws Exception {
		return hL03Adapter.timer(data);
	}

	@Override
	public String s10(String imei, int heartRate, int step, int upload) throws Exception {
		return hL03Adapter.sendTimerCommand(imei, heartRate, step, upload);
	}

	@Override
	public String t11(HLCommand data) throws Exception {
		return hL03Adapter.weather(data);
	}

	@Override
	public String s12(String imei, int typeNum, String message, String terminalCode, int voiceSecond) throws Exception {
		return hL03Adapter.sendMessageCommand(imei, typeNum, message, terminalCode, voiceSecond);
	}

	@Override
	public String s13(String imei) throws Exception {
		return hL03Adapter.sendPowerOffCommand(imei);
	}

	@Override
	public String t14(HLCommand data) throws Exception {
		return hL03Adapter.poweroff(data);
	}

	@Override
	public String s15(String imei, int hMin, int hMax, int tMin, int tMax, int dMin, int dMax, int sMin, int sMax, int eMin, int eMax) throws Exception {
		return hL03Adapter.sendHealthStandardCommand(imei, hMin, hMax, tMin, tMax, dMin, dMax, sMin, sMax, eMin, eMax);
	}

	@Override
	public String s16(String imei, int ipAddress, int port) throws Exception {
		return hL03Adapter.sendServerAddressCommand(imei, ipAddress, port);
	}

	@Override
	public String s17(String imei, int type, int status, String recordId, String noticeTime, String weeks, String content) throws Exception {
		return hL03Adapter.sendNoticeCommand(imei, type, status, recordId, noticeTime, weeks, content);
	}

	@Override
	public String s18(String imei, String content, String mobile) throws Exception {
		return hL03Adapter.sendSMSCommand(imei, content, mobile);
	}

	@Override
	public String s19(String imei, List<HL031Contact> contacts) throws Exception {
		return hL03Adapter.sendContactCommand(imei, contacts);
	}

	@Override
	public String s20(String imei, String gateway) throws Exception {
		return hL03Adapter.sendSMSGatewayCommand(imei, gateway);
	}

	@Override
	public String s21(String imei, int commandType, List<String> numbers) throws Exception {
		return hL03Adapter.sendBlackListCommand(imei, commandType, numbers);
	}

	@Override
	public String s22(String imei, int height, int weight, int age, int stepLength) throws Exception {
		return hL03Adapter.sendHeightWeightAgeStepLengthCommand(imei, height, weight, age, stepLength);
	}

	@Override
	public String t23(HLCommand data) throws Exception {
		return hL03Adapter.heightWeightAgeStepLength(data);
	}
}
