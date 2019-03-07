package com.lifeshs.customer.controller.common;

import com.lifeshs.service.data.IDataAreaService;
import org.springframework.beans.factory.annotation.Autowired;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.security.sessionmgr.ClientManager;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.vo.customer.CustomerSharingDataVO;

/**
 * 基础控制器
 * author: wenxian.cai
 * date: 2017/9/26 10:08
 */
public class BaseController {

	@Autowired
	CustomerUserService userService;
	@Autowired
	protected IDataAreaService dataAreaService;

	/**
	 * 获取当前用户
	 */
	protected LoginUser getLoginUser() {
		return ClientManager.getSessionUser();
	}

	/**
	 * 获取用户缓存信息
	 * @return
	 */
	protected CustomerSharingDataVO getUserSharingData() {
		LoginUser user = getLoginUser();
		int userId = user.getId();
		return userService.getUserSharingData(userId);
	}

	/**
	 * 更新用户缓存信息
	 */
	protected void updateUserSharingData() {
		LoginUser user = getLoginUser();
		int userId = user.getId();
		try {
			userService.updateUserSharingData(userId);
		} catch (OperationException o) {

		}

	}

}
