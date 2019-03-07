package com.lifeshs.dao1.data;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.data.HobbyPO;

/**
 *  兴趣爱好
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月4日 上午9:52:36
 */
@Repository(value = "hobbyDao")
public interface HobbyDao {

    /**
     *  获取一个兴趣爱好
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 上午10:02:04
     *
     *  @param id
     *  @return
     */
    HobbyPO getHobby(@Param("id") int id);
    
    /**
     *  获取兴趣爱好列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 上午9:46:08
     *
     *  @return
     */
    List<HobbyPO> findHobbyList();
    
    /**
     *  通过id列表查询兴趣爱好列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 上午9:49:31
     *
     *  @param idList
     *  @return
     */
    List<HobbyPO> findHobbyByIdList(@Param("idList") List<Integer> idList);
    
    /**
     *  获取时间节点之后变更过的兴趣爱好列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月22日 下午5:42:45
     *
     *  @param datePoint 时间节点
     *  @return
     */
    List<HobbyPO> findModifyHobbyList(@Param("datePoint") Date datePoint);
}
