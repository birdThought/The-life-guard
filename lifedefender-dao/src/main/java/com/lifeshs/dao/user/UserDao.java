package com.lifeshs.dao.user;

import com.lifeshs.po.user.UserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @create 2018-02-01
 * 11:22
 * @desc
 */
@Repository("userDao")
public interface UserDao {

    UserPO getFullUserById(@Param("userId") Integer userId);
    
    UserPO getUserInfo(@Param("userId") Integer userId);
}
