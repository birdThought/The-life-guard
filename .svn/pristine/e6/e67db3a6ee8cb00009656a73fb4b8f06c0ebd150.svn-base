package com.lifeshs.service.org.impl.user;

import org.springframework.stereotype.Component;

import com.lifeshs.entity.code.TSysCode;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.service.common.impl.BaseUserImpl;

/**
 * 版权归 TODO 企业用户（会员）操作类
 * 
 * @author yuhang.weng
 * @DateTime 2016年5月16日 下午5:05:15
 */
@Component("orgUser")
public class OrgUser extends BaseUserImpl {

	/**
	 * 得到最新的企业用户编号
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年9月5日 下午2:54:50
	 *
	 * @return
	 */
	public String getUserCode() {
		TSysCode sysCode = commonTrans.get(TSysCode.class, 1);
		String resCode = sysCode.getOrgUserCode();
		int code = Integer.valueOf(resCode) + 1;
		sysCode.setOrgUserCode(String.valueOf(code));

		commonTrans.saveOrUpdate(sysCode);
		return resCode;
	}

	/**
	 * 添加机构用户
	 * 
	 * @author dengfeng
	 * @DateTime 2016-6-1 下午02:23:24
	 *
	 * @param orgUser
	 *            机构用户
	 */
	public Integer addUser2(TOrgUser orgUser) {
		return commonTrans.save(orgUser);
	}
	
	public Boolean addUser(TOrgUser orgUser) {
		return commonTrans.save(orgUser) > 0;
	}

	/**
	 * @author yuhang.weng
	 * @DateTime 2016年6月7日 下午4:12:27
	 * @serverComment 更新用户信息
	 *
	 * @param orgUser
	 *            TOrgUser对象
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrgUser(TOrgUser orgUser) {
		return (commonTrans.updateEntitie(orgUser) == 0 ? false : true);
	}
}
