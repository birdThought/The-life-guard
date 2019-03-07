package com.lifeshs.service1.member;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.user.UserServeRecordPO;
import com.lifeshs.service1.page.Paging;

/**
 * 用户服务记录
 * author: wenxian.cai
 * date: 2017/11/3 10:31
 */
public interface ServeRecordService {

	/**
	 * 添加用户服务记录
	 * @param po
	 */
	void addUserServeRecord(UserServeRecordPO po) throws OperationException;

	/**
	 * 获取用户服务记录列表
	 * @param userId
	 * @param customerId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	Paging<UserServeRecordPO> listUserServeRecord(Integer userId, Integer customerId, int pageIndex, int pageSize);
}
