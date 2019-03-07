package com.lifeshs.service.member.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lifeshs.entity.device.TTerminalCommond;
import com.lifeshs.pojo.member.commond.CommondDto;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.member.IMemberCommondService;
import com.lifeshs.utils.ReflectUtils;

/**
 * 
 *  版权归
 *  用户对终端的操作指令业务类
 *  @author dengfeng  
 *  @DateTime 2016-5-20 上午10:54:59
 */

@Component
public class MemberCommondService extends CommonServiceImpl implements IMemberCommondService {

	@Override
	public int save(CommondDto commondDto) throws Exception {
		TTerminalCommond commondEntity = commondDto.getEntity();
		return commonTrans.save(commondEntity);
	}

	@Override
	public List<TTerminalCommond> getCommonds(String imei) {
		// 根据imei查找到设备指令 t_terminal_commond 获取未发送的指令
		List<TTerminalCommond> commonds = new ArrayList<TTerminalCommond>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imei", imei);
		params.put("status", false);
		
		List<Map<String, Object>> resList = commonTrans.findByMap(TTerminalCommond.class, params);
		// 将查询结果转换为返回类型
		for (Map<String, Object> map : resList) {
			TTerminalCommond commond = ReflectUtils.getBean(map, TTerminalCommond.class);
			commonds.add(commond);
		}
		
		return commonds;
	}

}
