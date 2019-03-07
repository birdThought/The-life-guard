package com.lifeshs.dao1.org;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.org.AgencyPO;

/**
 *  管理机构dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月21日 下午2:06:21
 */
@Repository(value = "orgAgencyDao")
public interface AgencyDao {
    
    /**
     *  获取一个管理机构
     *  @author yuhang.weng 
     *  @DateTime 2017年9月21日 下午2:03:38
     *
     *  @param id 管理机构id
     *  @return
     */
    AgencyPO getAgency(@Param("id") int id);
    
    /**
     *  获取子管理机构列表
     *  @author yuhang.weng 
     *  @DateTime 2017年9月21日 下午2:03:11
     *
     *  @param parentId 父管理机构id
     *  @return
     */
    List<AgencyPO> findAgencyByParentIdList(@Param("parentId") int parentId);
    
    /**
     *  添加管理机构
     *  @author yuhang.weng 
     *  @DateTime 2017年9月21日 下午2:02:59
     *
     *  @param agency 一个管理机构
     *  @return
     */
    int addAgency(AgencyPO agency);
}
