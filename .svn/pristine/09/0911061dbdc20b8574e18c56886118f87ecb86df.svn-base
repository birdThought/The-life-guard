package com.lifeshs.dao1.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.user.PhysicalItemPO;

/**
 *  用户体检项目dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月12日 下午3:28:38
 */
@Repository(value = "userPhysicalItemDao")
public interface UserPhysicalItemDao {

    /**
     *  添加用户体检项目
     *  @author yuhang.weng 
     *  @DateTime 2017年10月12日 上午11:55:15
     *
     *  @param item
     *  @return
     */
    int addPhysicalItem(PhysicalItemPO item);
    
    /**
     *  更新用户体检项目内容
     *  @author yuhang.weng 
     *  @DateTime 2017年10月12日 上午11:54:59
     *
     *  @param item
     *  @return
     */
    int updatePhysicalItem(PhysicalItemPO item);
    
    /**
     *  获取用户体检项目
     *  @author yuhang.weng 
     *  @DateTime 2017年10月12日 上午11:54:44
     *
     *  @param id
     *  @return
     */
    PhysicalItemPO getPhysicalItem(@Param("id") int id);
    
    /**
     *  获取用户体检项目
     *  @author yuhang.weng 
     *  @DateTime 2017年10月12日 下午5:29:23
     *
     *  @param userId 用户id
     *  @param physicalItemId 体检项目id
     *  @return
     */
    PhysicalItemPO findPhysicalItemByUserIdAndPhysicalItemId(@Param("userId") int userId, @Param("physicalItemId") int physicalItemId);
    
    /**
     *  统计用户体检项目
     *  @author yuhang.weng 
     *  @DateTime 2017年10月12日 上午11:53:35
     *
     *  @param userId 用户id
     *  @param physicalItemId 体检项目id
     *  @param status 记录状态
     *  @param reply 是否已回复
     *  @return
     */
    int countPhysicalItemWithCondition(@Param("userId") Integer userId, @Param("status") Integer status,
            @Param("physicalItemId") Integer physicalItemId, @Param("reply") Boolean reply);
    
    /**
     *  获取用户体检项目列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月12日 上午11:53:37
     *
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @param userId 用户id
     *  @param physicalItemId 体检项目id
     *  @param status 记录状态
     *  @param reply 是否已回复
     *  @return
     */
    List<PhysicalItemPO> findPhysicalItemWithConditionList(@Param("startRow") int startRow, @Param("pageSize") int pageSize,
            @Param("userId") Integer userId, @Param("physicalItemId") Integer physicalItemId,
            @Param("status") Integer status, @Param("reply") Boolean reply);
}
