package com.lifeshs.service.terminal.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.pojo.mina.WatchCommand;
import com.lifeshs.service.terminal.IGPRSWatchService;
import com.lifeshs.service.terminal.IGPRSWatchTerminal;

@Component
public class GPRSWatchServiceImpl implements IGPRSWatchService {

	@Autowired
	private IGPRSWatchTerminal watch;
	
	@Override
	public String lk(WatchCommand data) throws Exception {
		return watch.lk(data);
	}

	@Override
	public void ud(WatchCommand data) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void ud2(WatchCommand data) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public String al(WatchCommand data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String wad(WatchCommand data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String wg(WatchCommand data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* 服务器发送指令到终端手表 */
	
	@Override
	public String sendUploadCommand(String imei, int time) throws Exception {
		return watch.sendUploadCommand(imei, time);
	}

	@Override
	public String sendControlPasswordCommand(String imei, String password) throws Exception {
		return watch.sendControlPasswordCommand(imei, password);
	}

	@Override
	public String sendSMSCommand(String imei, String mobile, String content) throws Exception {
		return watch.sendSMSCommand(imei, mobile, content);
	}

	@Override
	public String sendMonitorCommand(String imei) throws Exception {
		return watch.sendMonitorCommand(imei);
	}

	@Override
	public String sendSOSCommand(String imei, int sosCount, String sosNumber) throws Exception {
		return watch.sendSOSCommand(imei, sosCount, sosNumber);
	}

	@Override
	public String sendIpAndPortCommand(String imei, String ipOrURL, int port) throws Exception {
		return watch.sendIpAndPortCommand(imei, ipOrURL, port);
	}

	@Override
	public String sendPowerOffCommand(String imei) throws Exception {
		return watch.sendPowerOffCommand(imei);
	}

	@Override
	public String sendCountStepCommand(String imei, String interval1, String interval2, String interval3)
			throws Exception {
		return watch.sendCountStepCommand(imei, interval1, interval2, interval3);
	}

	@Override
	public String sendContactCommand(String imei, int contactCount, List<Map<String, String>> list) throws Exception {
		return watch.sendContactCommand(imei, contactCount, list);
	}

}
