package com.lifeshs.dao1.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.vo.data.hobby.HotHobbyVO;

/**
 *  热门的兴趣爱好
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月4日 下午3:53:55
 */
@Repository(value = "hotHobbyDao")
public interface HotHobbyDao {

    /**
     *  获取热门的兴趣爱好列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 下午3:37:10
     *
     *  @param limit
     *  @return
     */
    List<HotHobbyVO> findHotHobbyList(@Param("limit") int limit);
}
