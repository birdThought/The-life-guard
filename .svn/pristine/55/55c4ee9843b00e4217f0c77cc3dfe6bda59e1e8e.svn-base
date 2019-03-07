package com.lifeshs.service1.visitor.impl;


import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.visitor.IVisitorDao;
import com.lifeshs.po.visitor.FeedBackPO;
import com.lifeshs.service1.visitor.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("visitorService")
public class VisitorServiceImpl implements IVisitorService{
	@Autowired
	IVisitorDao visitorDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
	public void addFeedBack(FeedBackPO po) throws OperationException{
		try {
			visitorDao.addFeedBack(po);
		} catch (Exception e) {
			throw new OperationException("添加游客反馈信息失败", ErrorCodeEnum.FAILED);
		}

	}
}
