package com.lifeshs.service1.member;

import java.util.List;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.user.UserHobbyPO;

/**
 *  用户兴趣爱好服务
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月4日 上午11:58:50
 */
public interface HobbyService {

    /**
     *  获取用户的兴趣爱好列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 上午11:55:19
     *
     *  @param userId
     *  @return
     */
    List<UserHobbyPO> listUserHobby(int userId);
    
    /**
     *  为用户添加兴趣爱好
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 上午11:56:29
     *
     *  @param userId
     *  @param hobbyIdList 兴趣爱好id列表
     *  @return
     */
    void addUserHobby(int userId, List<Integer> hobbyIdList) throws OperationException;
    
    /**
     *  移除用户的所有兴趣爱好
     *  @author yuhang.weng 
     *  @DateTime 2017年8月15日 下午2:19:06
     *
     *  @param userId
     */
    void removeUserHobby(int userId);
    
    /**
     *  移除用户的一项兴趣爱好
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 下午2:16:36
     *
     *  @param userId
     *  @param userHobbyId 用户兴趣爱好id
     *  @return
     *  @throws OperationException
     */
    void removeUserHobby(int userId, int userHobbyId) throws OperationException;
    
    /**
     *  移除用户的多项兴趣爱好
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 下午2:17:50
     *
     *  @param userId
     *  @param userHobbyIdList 用户兴趣爱好id列表
     *  @return
     *  @throws OperationException
     */
    void removeUserHobby(int userId, List<Integer> userHobbyIdList) throws OperationException;
    
    /**
     *  更新用户的兴趣爱好记录
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 下午2:19:47
     *
     *  @param userHobby
     *  @return
     *  @throws OperationException
     */
    void updateUserHobby(UserHobbyPO userHobby) throws OperationException;
}
