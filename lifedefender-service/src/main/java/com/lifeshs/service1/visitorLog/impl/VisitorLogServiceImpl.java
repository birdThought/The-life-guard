package com.lifeshs.service1.visitorLog.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao1.visitLogs.VisitorLogDao;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.visitorLog.VisitorLogService;
import com.lifeshs.vo.visit.VisitLogVo;

@Service("visitorLogService")
public class VisitorLogServiceImpl implements VisitorLogService {

	@Autowired
	VisitorLogDao visitorLogDao;

	@Override
	public Paging<VisitLogVo> findUserList(Integer orgId, Integer userType, String terminalType, int curPage, int pageSize) {
		Paging p = new Paging(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc() {

			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return visitorLogDao.countUserLog(orgId, userType, terminalType);
			}

			@Override
			public List queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return visitorLogDao.findUserLog(orgId, userType, terminalType, startRow, pageSize);
			}
		});
		return p;
	}

}
