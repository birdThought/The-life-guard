package com.lifeshs.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUserBlackWhiteList;
import com.lifeshs.entity.member.TUserNotice;
import com.lifeshs.entity.member.TUserOperationDetail;

@Component("baseTerminal")
public class BaseTerminal extends CommonServiceImpl {
	
	/**
	 * 得到用户的绑定设备列表
	 * 
	 * @param userName 用户名
	 * @return 
	 */
	public List<TUserTerminal> getDeviceList(int userId){
		List<TUserTerminal> devices = commonTrans.findByProperty(TUserTerminal.class, "userId", userId);
		return devices;
	}
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月18日 上午10:04:00
	 *  @serverComment 获取黑名单
	 *
	 *  @param userDeviceId 设备ID
	 *  @return
	 */
	public List<TUserBlackWhiteList> getDeviceBlackList(int userDeviceId){
		List<TUserBlackWhiteList> resList = new ArrayList<>();
		List<TUserBlackWhiteList> tempList = commonTrans.findByProperty(TUserBlackWhiteList.class, "userDeviceId", userDeviceId);
		if(tempList != null){
			for (TUserBlackWhiteList list : tempList) {
				// 把黑名单加入到lists中
				if(list.type == 2){
					resList.add(list);
				}
			}
			return resList;
		}
		return null;
	}
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月20日 下午2:08:49
	 *  @serverComment 获取设备提醒列表
	 *
	 *  @param userId
	 *  @return
	 */
	public List<TUserNotice> getTUserNotices(int userId){
		List<TUserNotice> notices = new ArrayList<>();
		notices = commonTrans.findByProperty(TUserNotice.class, "userId", userId);
		return notices;
	}
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月21日 上午9:53:00
	 *  @serverComment 获取设备模式详细设置列表
	 *
	 *  @param userDeviceId
	 *  @return
	 *  @throws Exception
	 */
	public TUserOperationDetail getTerminalOperationDetails(int userDeviceId) throws Exception{
		List<TUserOperationDetail> details = commonTrans.findByProperty(TUserOperationDetail.class, "userDeviceId", userDeviceId);
		if(details.size() == 0)
			return null;
		return details.get(0);
	}
}
