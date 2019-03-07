package com.lifeshs.dao1.business;

import com.lifeshs.po.business.BusinessCodePO;
import com.lifeshs.po.vip.VipComboPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-03-08
 * 15:08
 * @desc
 */
@Repository("businessCodeDao")
public interface IBusinessCodeDao {

    /**
     * 添加套餐推荐
     * @param bcp
     * @return
     */
    int  saveCodePackage(BusinessCodePO bcp);

    /**
     *  获取肿瘤套餐
     * @return
     */
    List<VipComboPO> findByIdName();

    /**
     * 获取套餐名
     * @param ageId
     * @return
     */
    String findByName(@Param("ageId") Integer ageId);

    // TODO 2018年3月20日 10:46:49 废弃
    String  findByPackageName(@Param("id") Integer id);

    /**
     *  分页参数
     * @param id
     * @return
     */
    int findCountData(@Param("id") Integer id,@Param("type") Integer type, @Param("superior") Integer superior);

    /**
     *  分页
     * @param id
     * @param curPage
     * @param pageSize
     * @return
     */
    List<BusinessCodePO> findListPackage(@Param("id") Integer id,@Param("type") Integer type, @Param("superior")Integer superior,@Param("curPage") int curPage,@Param("pageSize") int pageSize);

    /**
     *  删除推荐
     * @param id
     * @return
     */
    int deletePackage(@Param("id") Integer id);

    /**
     *  修改渠道商 指定字段
     * @param id
     * @param userName
     * @param name
     * @return
     */
    int updateSellData(@Param("id") Integer id,@Param("userName") String userName,@Param("name") String name);
}
