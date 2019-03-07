package com.lifeshs.dao.org.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.org.TOrg;
import com.lifeshs.po.org.user.OrgUserPO;

/**
 * 
 *  管理机构
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2019年1月19日 下午1:47:33
 */
@Repository
public interface ManageOrgDao {

    public int getCountOfOrg(@Param("orgId") Integer orgId, @Param("orgName") String orgName);
    
    
    public List<TOrg> listManageOrg(@Param("orgId") Integer orgId, @Param("orgName") String orgName, 
            @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);
    
    public List<TOrg> findOrgListByOrgId(@Param("orgId") Integer orgId);
    
    public List<OrgUserPO> findOrgUserByOrgId(@Param("orgId") Integer orgId);
    
    
    int updateOrgByMobile(@Param("orgId") Integer orgId,@Param("mobile") String mobile);


	public TOrg getOrgById(@Param("orgId")int orgId);
    
    
//    
//	/**
//	 * 计算机构数量（逐层深入查询）
//	 * 
//	 * @author yuhang.weng
//	 * @DateTime 2016年9月6日 下午2:25:11
//	 *
//	 * @param id
//	 *            机构ID
//	 * @param type
//	 *            机构类型 0表示管理机构， 1表示服务机构
//	 * @return
//	 */
//	public Integer countChildOrg(@Param("id") Integer org_id);
//
//	/**
//	 * 统计门店数量与会员数量
//	 * 
//	 * @author yuhang.weng
//	 * @DateTime 2016年9月6日 下午4:56:38
//	 *
//	 * @param org_id
//	 * @return
//	 */
//	public OrgServiceAndMemberDO countOrgChildServiceAndMember(@Param("id") Integer org_id);
//
//	/**
//	 * 根据用户所在的机构id寻找该机构的类型
//	 * 
//	 * @author zhansi.Xu
//	 * @DateTime 2016年9月6日
//	 * @serverComment
//	 */
//	public int findOrgType(int orgId);
//
//	/**
//	 * 根据查询条件筛选出员工列表
//	 * 
//	 * @author zhansi.Xu
//	 * @DateTime 2016年9月7日
//	 * @serverComment
//	 */
//	public List<Map<String, Object>> getEmployList(Map<String, Object> params);
//
//	/**
//	 * 根据父机构的ID获取子机构的id与名字，机构类型
//	 * 
//	 * @author yuhang.weng
//	 * @DateTime 2016年9月7日 下午4:46:31
//	 *
//	 * @param parentId
//	 *            父类机构ID
//	 * @return
//	 */
//	public List<OrgTreeVO> selectChildOrgTreeByParentId(@Param("parentId") Integer parentId);
//
//	/**
//	 * 查看机构详细信息
//	 * 
//	 * @author yuhang.weng
//	 * @DateTime 2016年9月8日 下午8:09:43
//	 *
//	 * @param parentId
//	 * @return
//	 */
//	public OrgDetailVO selectOrgDetailById(@Param("parentId") Integer parentId);
//
//	// public Integer selectAmountOfChildOrgByParentAndType(@Param("parentId")
//	// Integer parentId,
//	// @Param("type") Integer type);
//	//
//	// public List<TOrg>
//	// selectChildOrgByParentAndTypeWithPage(@Param("parentId") Integer
//	// parentId,
//	// @Param("type") Integer type,
//	// @Param("startIndex") Integer startIndex,
//	// @Param("pageSize") Integer pageSize);
//
//	/**
//	 * 查找child机构是否从属于parent机构
//	 * 
//	 * @author yuhang.weng
//	 * @DateTime 2016年9月10日 上午10:05:02
//	 *
//	 * @param parentId
//	 * @param childId
//	 * @return
//	 */
//	public Integer isThisOrgBelongToAnotherOne(@Param("parentId") Integer parentId, @Param("childId") Integer childId);
//
//	/**
//	 * 获取机构信息
//	 * 
//	 * @param orgId
//	 * @return
//	 */
//	public TOrg getOrgDetail(int orgId);
//
//	/**
//	 *  获取用户userCode
//	 *  @author yuhang.weng 
//	 *	@DateTime 2017年3月2日 下午6:32:36
//	 *
//	 *  @param orgUserIds
//	 *  @return
//	 */
//	List<String> listUserCode(@Param("orgUserIds") List<Integer> orgUserIds);
//
//	/**
//	 * @Description: 更新机构信息
//	 * @author: wenxian.cai
//	 * @create: 2017/4/26 15:10
//	 */
//	int updateOrg(OrgRegisterDTO org);
//	
//	/**
//	 *  机构更改或添加数据
//	 *  
//	 *  @param data
//	 *  @return
//	 */
//	public Integer updataOrgById(@Param("orgId")Integer orgId, @Param("netPath")String netPath, @Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("statusMap")Integer statusMap);


}
