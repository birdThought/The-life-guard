package com.lifeshs.service1.member.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.user.UserServeRecordDao;
import com.lifeshs.po.user.UserServeRecordPO;
import com.lifeshs.service1.member.ServeRecordService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;

/**
 * 用户服务记录
 * author: wenxian.cai
 * date: 2017/11/3 10:35
 */
@Service("serveRecordService")
public class ServeRecordServiceImpl implements ServeRecordService{
	@Autowired()
	UserServeRecordDao userServeRecordDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
	public void addUserServeRecord(UserServeRecordPO po) throws OperationException{
	    String serveContent = po.getServeContent();
	    if (StringUtils.isBlank(serveContent)) {
	        throw new OperationException("请填写服务内容", ErrorCodeEnum.MISSING);
	    }
	    if (serveContent.length() > 100) {
	        throw new OperationException("服务内容文字长度不能超过100个字符", ErrorCodeEnum.FAILED);
	    }
		int result = userServeRecordDao.addUserServeRecord(po);
		if (result == 0) {
			throw new OperationException("用户服务记录：添加出错", ErrorCodeEnum.FAILED);
		}
	}

	@Override
	public Paging<UserServeRecordPO> listUserServeRecord(Integer userId, Integer customerId, int pageIndex, int pageSize) {
		Paging paging = new Paging(pageIndex, pageSize);
		paging.setQueryProc(new IPagingQueryProc() {
			@Override
			public int queryTotal() {
				return userServeRecordDao.countUserServeRecord(userId, customerId);
			}

			@Override
			public List queryData(int startRow, int pageSize) {
				return userServeRecordDao.findUserServeRecordList(userId, customerId, paging.getStartRow(), pageSize);
			}
		});
		return paging;
	}
}
