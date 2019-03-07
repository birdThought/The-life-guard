package com.lifeshs.service.terminal.impl;

import java.util.List;
import java.util.Map;

import com.lifeshs.common.constants.common.TerminalType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.pojo.mina.WatchCommand;
import com.lifeshs.service.terminal.IGPRSWatchTerminal;

@Component
public class GPRSWatchTerminalAdapter implements IGPRSWatchTerminal {

	private static TerminalType terminalType = TerminalType.Watch;
	
	/**
	 * 适配源，标准功能的对象
	 */
	@Autowired
	private TerminalAdaptee terminal;
	
	@Override
	public String lk(WatchCommand data) throws Exception {
		String imei = data.getImei();
		String metas = data.getParams();
		
		String message = "LK";	// 返回命令
		
		if(!StringUtils.equals(metas, "LK")){	// 如果数据中包含其他数据，再进行处理
			String[] m = metas.split(",");
			String step = m[1];	// 步数
			String roll = m[2];	// 翻滚
			String power = m[3];// 电量
			
			TUserTerminal terminal = getWatch(imei);
		}

		return returnNormalWatchData(data, message);
	}

	@Override
	public void ud(WatchCommand data) throws Exception {
		String imei = data.getImei();
		String metas = data.getParams();
		
		TUserTerminal terminal = getWatch(imei);
		
		// TODO 数据获取与定位
		
	}

	@Override
	public void ud2(WatchCommand data) throws Exception {
		String imei = data.getImei();
		String metas = data.getParams();
		
		TUserTerminal terminal = getWatch(imei);
		
		// TODO 数据获取与定位
		
	}

	@Override
	public String al(WatchCommand data) throws Exception {
		String imei = data.getImei();
		String metas = data.getParams();
		
		String message = "AL";	// 返回命令
		
		TUserTerminal terminal = getWatch(imei);
		
		// TODO 数据获取与定位
		
		
		return returnNormalWatchData(data, message);
	}

	@Override
	public String wad(WatchCommand data) throws Exception {
		
		String imei = data.getImei();
		String metas = data.getParams();
		
		TUserTerminal terminal = getWatch(imei);
		
		// TODO 数据获取与定位
		String[] m = metas.split(",");
		String language = m[1];
		
		String locationType = "GPS";	// 定位类型分为GPS定位与BASE定位(BASE表示基站)
		String address = "";			// 地址数据(中文需要转码为GB2312)
		
		String message = "RAD" + locationType + "," + address + "]";
		
		return returnNormalWatchData(data, message);
	}

	@Override
	public String wg(WatchCommand data) throws Exception {
		
		String imei = data.getImei();
		String metas = data.getParams();
		
		TUserTerminal terminal = getWatch(imei);
		
		// TODO 数据获取与定位
		String[] m = metas.split(",");
		String temp = m[2];	// 此处表示位置数据
		
		String locationType = "BASE";	// 基站定位(可选GPS、BASE)
		String locationStr = "22.571707,N,113.8613968,E";
		
		String message = "RG," + locationType + "," + locationStr + "]";
		
		return returnNormalWatchData(data, message);
	}
	
	/**
	 * 	<p>GPRS的请求返回结果
	 * 	<p>通过获取data数据中的固定部分，再对数据进行拼装即可获得返回结果字符串
	 * 	<p>如:[CS*YYYYYYYYY*0002*LK]其返回结果为[CS*YYYYYYYYY*LEN*message]
	 * 	<p>首先将data进行字符串按"*"分割，保留前2个字符串数据，即下标为0、1
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 上午11:19:18
	 *
	 *	@param data 
	 *	@param message 返回信息
	 *  @return
	 */
	private String returnNormalWatchData(WatchCommand data, String message){
		StringBuffer sb = new StringBuffer("[");
		sb.append(data.getCs() + "*" + data.getImei() + "*" + coverIntTo16String(message.length()) + "*" + message + "]");
		return sb.toString();
	}
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 上午11:35:03
	 *  @serverComment 将length转换为长度为4的16位进制字符串
	 *
	 *  @param length
	 *  @return
	 */
	private String coverIntTo16String(int length){
		if(length < 0){
			return "0000";
		}
		if(length > 65535){
			return "FFFF";
		}
		String result = Integer.toHexString(length).toUpperCase();
		while (result.length() < 4) {
			result = 0 + result;
		}
		return result;
	}
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 上午11:54:30
	 *  @serverComment 获取imei对应的watch对象
	 *
	 *  @param imei
	 *  @return
	 */
	private TUserTerminal getWatch(String imei){
		return terminal.getDeviceService().selectDeviceIsBinding(imei, terminalType.getName());
	}
	
	/* 终端发送指令到手表 */

	@Override
	public String sendUploadCommand(String imei, int time) throws Exception {
		String command = "UPLOAD," + time;
		return assembNormalReturnStr("SG", imei, command, coverIntTo16String(command.length()));
	}

	@Override
	public String sendControlPasswordCommand(String imei, String password) throws Exception {
		String command = "PW," + password;
		return assembNormalReturnStr("SG", imei, command, coverIntTo16String(command.length()));
	}

	@Override
	public String sendSMSCommand(String imei, String mobile, String content) throws Exception {
		String command = "SMS," + mobile + "," + content;
		return assembNormalReturnStr("SG", imei, command, coverIntTo16String(command.length()));
	}

	@Override
	public String sendMonitorCommand(String imei) throws Exception {
		String command = "MONITOR";
		return assembNormalReturnStr("SG", imei, command, coverIntTo16String(command.length()));
	}

	@Override
	public String sendSOSCommand(String imei, int sosCount, String sosNumber) throws Exception {
		if(sosCount < 0 || sosCount >3)
			return null;
		String command = "SOS" + sosCount + "," + sosNumber;
		return assembNormalReturnStr("SG", imei, command, coverIntTo16String(command.length()));
	}

	@Override
	public String sendIpAndPortCommand(String imei, String ipOrURL, int port) throws Exception {
		String command = "IP," + ipOrURL + "," + port;
		return assembNormalReturnStr("SG", imei, command, coverIntTo16String(command.length()));
	}

	@Override
	public String sendPowerOffCommand(String imei) throws Exception {
		String command = "POWEROFF";
		return assembNormalReturnStr("SG", imei, command, coverIntTo16String(command.length()));
	}

	@Override
	public String sendCountStepCommand(String imei, String interval1, String interval2, String interval3)
			throws Exception {
		String command = "WALKTIME," + interval1 + "," + interval2 + "," + interval3;
		return assembNormalReturnStr("SG", imei, command, coverIntTo16String(command.length()));
	}

	@Override
	public String sendContactCommand(String imei, int contactCount, List<Map<String, String>> list) throws Exception {
		
		StringBuffer command = new StringBuffer();
		int length = 0;
		
		if(contactCount == 1){
			command.append("PHB");
			length += command.length();	// 计数
		}else{
			command.append("PHB" + contactCount);
			length += command.length();	// 计数
		}
		
		// 转码前5个list中的name值
		int size = list.size() > 5 ? 5 : list.size();
		for(int i=0; i <size; i++){
			Map<String, String> tMap = list.get(i);
			command.append("," + new String(tMap.get("name").getBytes("unicode")) + "," + tMap.get("number"));	// TODO 转码UNICODE
			String s = "," + tMap.get("name") + "," + tMap.get("number");	// 未转码的字符串
			length += s.length();	// 计数
		}
		
		return assembNormalReturnStr("SG", imei, command.toString(), coverIntTo16String(length));
	}
	
	/**
	 * 	<p>组织一般的返回串，用于服务器发出的命令
	 * 	<p>组串的内容包含[厂商编号*IMEI*LEN*命令串;]
	 * 	<p>如：[SG*123456789012345*0011*SLAVE,00000000000]
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月1日 下午3:36:19
	 *
	 *	@param CS 厂商编号
	 *	@param imei 串号
	 *	@param command 命令串 [详细查看 GPRS协议V1.7 （20151225）.pdf]
	 *	@param length 字符长度(ps:单独列出来是因为转码会破坏数据结构，使数据长度的准确性不能保证)
	 *  @return
	 */
	private String assembNormalReturnStr(String CS, String imei, String command, String length){
		StringBuffer result = new StringBuffer();
		result.append("[" + CS + "*" + imei + "*" + length + "*" + command + "]");
		return result.toString();
	}
}
