package com.lifeshs.dao1.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.data.PhysicalPO;

@Repository(value = "dataPhysicalDao")
public interface PhysicalItemDao {

    /**
     *  统计体检项目数量
     *  @author yuhang.weng 
     *  @DateTime 2017年10月12日 上午9:57:05
     *
     *  @return
     */
    int countPhysicalItem();
    
    /**
     *  获取体检项目
     *  @author yuhang.weng 
     *  @DateTime 2017年10月13日 上午11:32:02
     *
     *  @param id 体检项目id
     *  @return
     */
    PhysicalPO getPhysicalItem(@Param("id") int id);
    
    /**
     *  获取体检项目列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月12日 上午9:56:55
     *
     *  @param startRow
     *  @param pageSize
     *  @return
     */
    List<PhysicalPO> findPhysicalItemWithConditionList(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
}
