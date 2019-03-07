package com.lifeshs.service.terminal.impl;

import java.util.ArrayList;
import java.util.List;

import com.lifeshs.common.constants.common.TerminalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.entity.device.TSportLocation;
import com.lifeshs.common.model.map.Gps;
import com.lifeshs.service.device.IDeviceService;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.terminal.IHLWebService;

@Component
public class HLWebServiceImpl extends CommonServiceImpl implements IHLWebService {

	private final TerminalType terminalType = TerminalType.HL03;
	
	@Autowired
	IDeviceService deviceService;
	
	@Override
	public Gps getGps(int userId) throws Exception {
		// 获取TSportLocation对象
		List<TSportLocation> locations = latestLocation(userId, 1);
		/**
		 * 如果limit条件错误，locations的值就会变成null
		 * 如果查询不到定位信息，locations的size就会等于0
		 * 这时候都需要返回null
		*/
		if(locations == null || locations.size() == 0)
			return null;
		return new Gps(locations.get(0).getLatitude(), locations.get(0).getLongitude());
	}
	
	@Override
	public List<Gps> getGpsList(int userId, int limit) throws Exception {
		// 判断limit的数量，上限为7，下限为1
		limit = limit > 7 ? 7 : limit;
		List<TSportLocation> locations = latestLocation(userId, limit);
		/**
		 * 如果limit条件错误，locations的值就会变成null
		 * 如果查询不到定位信息，locations的size就会等于0
		 * 这时候都需要返回null
		*/
		if(locations == null || locations.size() == 0)
			return null;
		/**
		 * 封装结果
		 * 只获取第一条数据与最后一条数据
		 */
		List<Gps> resList = new ArrayList<>();
		for (int i = 0; i < locations.size(); i++){
			if (i == 0 || i == (locations.size() - 1)){
				TSportLocation location = locations.get(i);
				resList.add(new Gps(location.getLatitude(), location.getLongitude()));
			}
		}
		return resList;
	}

	@Override
	public List<Gps> getOrbit(int userId, String start, String end) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月12日 上午11:31:32
	 *  @serverComment 获取HL03设备的最新定位信息
	 *
	 *  @param userId
	 *  @param limit 限制查询数量
	 *  @return
	 */
	private List<TSportLocation> latestLocation(int userId, int limit){
		// 不对小于1的数字进行处理
		if(limit <= 0)
			return null;
		return deviceService.findLatestGpsMessage(userId, terminalType.getName(), limit);
	}
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月18日 上午10:15:17
	 *  @serverComment 获取设备对象
	 *
	 *  @param userId
	 *  @return
	 */
//	private TUserTerminal getTerminal(int userId){
//		// 获取设备列表，并对设备列表信息进行筛选
//		List<TUserTerminal> terminals = commonTrans.findByProperty(TUserTerminal.class, "userId", userId);
//		if(terminals == null)
//			return null;	// 设备列表为空，找不到设备ID
//		for (TUserTerminal terminal : terminals) {
//			if(StringUtils.equals(terminal.getTerminalType(), "HL03")){	// 查找相应的设备
//				return terminal;	// 返回设备
//			}
//		}
//		return null;
//	}
}
