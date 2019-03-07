package com.lifeshs.service1.business;

import com.lifeshs.po.business.BusinessCodePO;
import com.lifeshs.po.business.BusinessUserPO;
import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.pojo.org.employee.OrgEmploy;
import com.lifeshs.service1.page.Paging;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-03-08
 * 17:23
 * @desc
 */
public interface BusinessCodeService {

    /**
     * 获取所有套餐
     * @return
     */
    List<VipComboPO> fingByName();

    /**
     *  添加套餐
     * @param id
     * @param ageId
     * @return
     */
    int addPackage(Integer id, Integer ageId);

    /**
     * 获取推荐套餐列表
     * @param id
     * @param page
     * @param pageSize
     * @return
     */
    Paging<BusinessCodePO> obtainData(Integer id,Integer page,Integer pageSize);

    /**
     * 删除推荐
     * @param id
     * @return
     */
    int del(Integer id);

    /**
     *  获取下级用户
     * @param id
     * @param page
     * @param pagesize
     * @return
     */
    Paging<BusinessUserPO> findBySell(Integer id, Integer page, int pagesize);

    /**
     *  修改
     * @param id
     * @param userName
     * @param name
     * @return
     */
    int ModifySell(Integer id, String userName, String name);

    /**
     * 获取机构下的所有用户
     * @param storeId
     * @return
     */
    List<OrgEmploy> getSercerId(Integer storeId);
}
