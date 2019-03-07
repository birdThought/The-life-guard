package com.lifeshs.service.org.manage;

import java.util.List;

import com.lifeshs.entity.org.TOrg;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.service1.page.Paging;

/**
 * @Description: 服务项目管理
 * @Author: wenxian.cai
 * @Date: 2017/5/17 11:02
 */

public interface IManageOrgService {

    
    /**
     * 
     *  查找管理机构下的管理机构或服务机构
     *  @author liaoguo
     *  @DateTime 2019年1月19日 上午11:54:00
     *
     *  @param orgId
     *  @param orgName
     *  @param pageIndex
     *  @param pageSize
     *  @return
     */
    Paging<TOrg> listManageOrg(Integer orgId, String orgName, Integer pageIndex, Integer pageSize);
    
    /**
     * 
     *  点击+号查找机构
     *  @author NaN
     *  @DateTime 2019年1月19日 下午3:12:48
     *
     *  @param orgId
     *  @return
     */
    List<TOrg> findOrgListByOrgId(Integer orgId);
    
    /**
     * 
     *  根据机构id查找员工
     *  @author NaN
     *  @DateTime 2019年1月19日 下午3:23:09
     *
     *  @param orgId
     *  @return
     */
    List<OrgUserPO> findOrgUserByOrgId(Integer orgId);
    
    
    /**
     * 
     *  修改机构上级编号
     *  @author liaoguo
     *  @DateTime 2019年1月19日 下午4:54:58
     *
     *  @param orgId
     *  @param mobile
     *  @return
     */
    int updateOrgByMobile(Integer orgId, String mobile);
    
    /**
     * 跟据id获取门店信息
     * @param orgId
     * @return
     */
	TOrg getOrgById(int orgId);
    
}
