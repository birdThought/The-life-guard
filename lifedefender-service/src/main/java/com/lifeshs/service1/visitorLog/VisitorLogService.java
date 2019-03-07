package com.lifeshs.service1.visitorLog;

import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.visit.VisitLogVo;

/**
 * 访问量数据方法
 * @author shiqiang.zneg
 * @Date 2018.1.17 14:00
 */
public interface VisitorLogService {
	
	/**
	 * 获取用户登录记录
	 * @param orgId
	 * @param userType
	 * @param terminalType
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<VisitLogVo> findUserList(Integer orgId,Integer userType,String terminalType,int curPage,int pageSize);

}
