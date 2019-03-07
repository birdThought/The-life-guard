package com.lifeshs.service.org.impl;

import org.springframework.stereotype.Component;

import com.lifeshs.entity.code.TSysCode;
import com.lifeshs.entity.org.TOrg;

/**
 * 
 *  管理机构业务类（上层机构，如总部、分部、区域代理等）
 *  @author dengfeng  
 *  @DateTime 2016-5-27 下午05:29:26
 */
@Component
public class ManagerOrg extends Organization {

	/**
	 *  添加一个机构
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月5日 下午2:48:37
	 *
	 *  @param org
	 *  @return
	 */
	public Boolean addOrg(TOrg org) {
		if(commonTrans.save(org) > 0) {
			return true;
		}
		return false;
	}
	
	/**
     *  修改一个机构
     *  @author yuhang.weng 
     *  @DateTime 2016年9月5日 下午2:48:37
     *
     *  @param org
     *  @return
     */
    public Boolean updateOrg(TOrg org) {
        if(commonTrans.updateEntitie(org) > 0) {
            return true;
        }
        return false;
    }
	
	/**
	 *  得到最新的企业编号
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月5日 下午2:54:47
	 *
	 *  @return
	 */
	public String getOrgCode() {
		TSysCode sysCode = commonTrans.get(TSysCode.class, 1);
		String resCode = sysCode.getOrgCode();
		int code = Integer.valueOf(resCode)+1;
		sysCode.setOrgCode(String.valueOf(code));

		commonTrans.saveOrUpdate(sysCode);
		return resCode;
	}
}
