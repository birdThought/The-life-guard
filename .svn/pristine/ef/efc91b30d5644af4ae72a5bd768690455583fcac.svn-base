package com.lifeshs.service.terminal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.pojo.mina.HLCommand;
import com.lifeshs.service.terminal.ILCHBService;
import com.lifeshs.service.terminal.ILCHBTerminal;

/**
 *  版权归
 *  TODO 梦时代业务实现类
 *  @author yuhang.weng  
 *  @DateTime 2016年7月7日 上午11:32:50
 */
@Component
public class LCHBServiceImpl implements ILCHBService {

	@Autowired
	private ILCHBTerminal lCHBTerminal;
	
	@Override
	public String t1(HLCommand data) throws Exception {
		return lCHBTerminal.login(data);
	}

	@Override
	public String t2(HLCommand data) throws Exception {
		return lCHBTerminal.heartPackage(data);
	}

	@Override
	public String t28(HLCommand data) throws Exception {
		return lCHBTerminal.heartRate(data);
	}

	@Override
	public String t29(HLCommand data) throws Exception {
		return lCHBTerminal.location(data);
	}

	@Override
	public String t50(HLCommand data) throws Exception {
		return lCHBTerminal.measure(data);
	}

	@Override
	public String t45(HLCommand data) throws Exception {
		return lCHBTerminal.bloodPressure(data);
	}

	@Override
	public String t47(HLCommand data) throws Exception {
		return lCHBTerminal.weather(data);
	}

	@Override
	public String t51(HLCommand data) throws Exception {
		return lCHBTerminal.powerOff(data);
	}

	@Override
	public String t59(HLCommand data) throws Exception {
		return lCHBTerminal.glucometer(data);
	}

	@Override
	public String t63(HLCommand data) throws Exception {
		return lCHBTerminal.message(data);
	}

	@Override
	public String t65(HLCommand data) throws Exception {
		return lCHBTerminal.memberPointFollowerHotData(data);
	}

	@Override
	public String t66(HLCommand data) throws Exception {
		return lCHBTerminal.mainClockStylish(data);
	}
}
