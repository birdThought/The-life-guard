package com.lifeshs.dao1.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.user.UserHobbyPO;

/**
 *  用户兴趣爱好
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月4日 下午3:17:34
 */
@Repository(value = "userHobbyDao")
public interface UserHobbyDao {

    /**
     *  获取用户的兴趣爱好列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 下午2:10:26
     *
     *  @param userId
     *  @return
     */
    List<UserHobbyPO> findUserHobbyByUserId(@Param("userId") int userId);
    
    /**
     *  为用户添加兴趣爱好
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 下午1:47:01
     *
     *  @param userId
     *  @param hobbyIdList 兴趣爱好id集合
     *  @return
     */
    int addUserHobby(@Param("userId") int userId, @Param("hobbyIdList") List<Integer> hobbyIdList);
    
    /**
     *  删除用户所有的兴趣爱好
     *  @author yuhang.weng 
     *  @DateTime 2017年8月15日 下午2:20:56
     *
     *  @param userId
     *  @return
     */
    int delUserHobby(@Param("userId") int userId);
    
    /**
     *  删除用户的多项兴趣爱好
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 下午1:49:26
     *
     *  @param userId
     *  @param userHobbyIdList 用户的兴趣爱好id集合
     *  @return
     */
    int delUserHobbyList(@Param("userId") int userId, @Param("userHobbyIdList") List<Integer> userHobbyIdList);
    
    /**
     *  更新用户的一项兴趣爱好
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 下午1:55:30
     *
     *  @param hobby
     *  @return
     */
    int updateUserHobby(UserHobbyPO hobby);
}
