package com.lifeshs.service.org;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lifeshs.common.exception.org.AuthorityException;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.po.OrgPO;
import com.lifeshs.pojo.org.OrgRegisterDTO;
import com.lifeshs.pojo.org.bank.BankInfoDTO;
import com.lifeshs.pojo.org.management.ManagementVO;
import com.lifeshs.pojo.org.management.OrgDetailVO;
import com.lifeshs.pojo.org.management.OrgServiceAndMemberDO;
import com.lifeshs.pojo.org.profile.OrgProfileDTO;

/**
 *  管理机构服务接口（上层机构，如总部、分部、区域代理等）
 *  @author duosheng.mo  
 *  @DateTime 2016年5月23日 下午4:03:06
 */
public interface IManagerOrgService {
	/**
	 *  添加一个注册机构（顶层机构） 并且返回主键id
	 *  @author dengfeng
	 *	@DateTime 2016-6-1 下午04:03:23
	 *
	 *  @param orgName 机构名称
	 *  @param userName 管理员帐号
	 *  @param password 管理员密码
	 *  @param type 机构类型
	 *  @param orgType 服务性质
	 *  @return
	 */
	int registOrg(String orgName, String userName, String password, Integer type, String orgType);

	int registOrg(OrgRegisterDTO org);

	/**
	 * @Description: 注册机构登录用户
	 * @author: wenxian.cai
	 * @create: 2017/4/26 19:32
	 */
	int registOrgUser(String userName, String password, Integer orgId, int type, String mobile);
	
	int registOrg(TOrg org, TOrgUser user);

	/**
	 *  添加一个下级管理机构或者服务机构
	 *  @author yuhang.weng
	 *	@DateTime 2016年9月6日 下午5:26:34
	 *
	 *  @param orgVO
	 *  @param parent 父机构ID
	 */
	Integer registChildOrg(ManagementVO orgVO, Integer parent);

	/**
	 * 验证机构是否已存在
	 *  @author dengfeng
	 *	@DateTime 2016-6-2 上午11:00:53
	 *
	 *  @param orgName
	 *  @return
	 */
	boolean orgIsExist(String orgName);
	
	/**
	 *  获取子机构数量
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月6日 下午2:46:04
	 *
	 *  @return
	 */
	Integer getTheAmountOfChildManagement(Integer orgId);
	
	/**
	 *  获取子门店数量
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月6日 下午2:47:03
	 *
	 *  @param orgId
	 *  @return
	 */
	OrgServiceAndMemberDO getTheAmountOfChildService(Integer orgId);
	
	/**
	 *  获取机构信息
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月6日 下午5:50:03
	 *
	 *  @param orgId
	 *  @return
	 */
	TOrg getOrgMessage(Integer orgId);
	
	/**
	 *  获取下一级机构信息（管理+服务机构）
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月8日 上午9:38:12
	 *
	 *  @param parentId
	 *  @return
	 */
	List<Map<String, Object>> getChildOrgMessage(Integer parentId);
	
	/**
	 *  获取下一级机构信息（服务机构）
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月9日 上午9:44:53
	 *
	 *  @param parentId
	 *  @return
	 */
	List<Map<String, Object>> getChildOrgServiceMessage(Integer parentId);
	
	/**
	 *  获取机构树信息（机构ID、机构名、下级机构数量、下级机构会员数量、机构类型）
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月7日 下午7:28:49
	 *
	 *  @param parentId
	 *  @return
	 */
	Object getOrganizeTree(Integer parentId);
	
	/**
	 *  获取机构详细信息
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月8日 下午4:52:36
	 *
	 *  @param orgId
	 *  @return
	 */
	OrgDetailVO getOrgDetailById(Integer orgId);
	
	/**
	 *  判断两个org是否有从属关系
	 *  如果不属于该机构，抛出AuthorityException异常
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月10日 上午10:12:33
	 *
	 *  @param fatherOrgId
	 *  @param childOrgId
	 *  @throws AuthorityException
	 */
	void isThisOrgBelongToAnotherOne(Integer fatherOrgId, Integer childOrgId) throws AuthorityException;
	
	/**
	 * 更新机构的信息
	 * @author zhansi.Xu
	 * @DateTime 2016年9月28日
	 * @serverComment
	 */
	void updateOrgDetails(Map<String,Object> param,Integer orgId);
	
	/**
	 * 获取机构信息
	 * @param orgId
	 * @return
	 */
	TOrg getOrgDetail(int orgId);

	/**
	 * @Description: 更新机构信息
	 * @author: wenxian.cai
	 * @create: 2017/4/26 15:11
	 */
	boolean updateOrg(OrgRegisterDTO org);

	/*boolean updateOrgUser();*/

	/**
	 *  获取机构信息
	 *  @author yuhang.weng 
	 *	@DateTime 2017年6月12日 下午3:40:28
	 *
	 *  @param id
	 *  @return
	 */
	OrgPO getOrg(int id);

    /**
     * @Description: 获取门店信息
     * @Author: wenxian.cai
     * @Date: 2017/6/9 9:41
     */
    OrgProfileDTO getOrgInfo(Integer orgId);

	/**
	 * @Description: 更新门店信息
	 * @Author: wenxian.cai
	 * @Date: 2017/6/9 14:52
	 */
	boolean updateOrgInfo(OrgProfileDTO org);
	
	/**
	 *  更新机构银行相关信息
	 *  @author yuhang.weng 
	 *	@DateTime 2017年6月12日 下午3:41:02
	 *
	 *  @param data
	 *  @return
	 */
	boolean updateOrgBankInfo(BankInfoDTO data);
	
	/**
	 *  机构更改或添加数据
	 *  @author yuhang.weng 
	 *	@DateTime 2017年6月12日 下午3:41:02
	 *
	 *  @param data
	 *  @return
	 */
	Integer updataOrgById(Integer orgId, String netPath, Date startTime, Date endTime, Integer statusMap);
	
	
	/**
	 * 添加数据到DataSize
	 * @param orgId
	 * @param netPath
	 * @param startTime
	 * @param endTime
	 * @param statusMap
	 * @return
	 */
	Integer addDataSize(Integer siteId ,Integer orgId, String netPath, String startTime, String endTime, String statusMap);
}
