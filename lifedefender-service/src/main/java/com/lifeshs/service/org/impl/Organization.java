package com.lifeshs.service.org.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.lifeshs.entity.org.TOrg;
import com.lifeshs.service.common.transform.ICommonTrans;

/**
 * 
 *  机构的基类
 *  @author dengfeng  
 *  @DateTime 2016-5-27 下午05:21:57
 */
public abstract class Organization {

	@Autowired
	ICommonTrans commonTrans;
	
	/**
	 * 验证机构是否已存在
	 *  @author dengfeng
	 *	@DateTime 2016-6-2 上午11:00:53
	 *
	 *  @param orgName
	 *  @return
	 * @throws Exception 
	 */
	public boolean orgIsExist(String orgName) {
		List<Map<String, Object>> orgs = commonTrans.findByPropertyByMap(TOrg.class, "orgName", orgName);
 	    if(orgs != null && orgs.size() > 0)
 	    	return true;
		return false;
	}
}
