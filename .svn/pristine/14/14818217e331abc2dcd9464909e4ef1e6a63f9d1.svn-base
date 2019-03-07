package com.lifeshs.dao1.member;

import com.lifeshs.po.UserAddressPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户的上门地址（应用于上门服务）管理，不是用户信息中的地址
 * Created by dengfeng on 2017/7/3 0003.
 */
@Repository(value = "memberAddressDao")
public interface IMemberAddressDao {
    /**
     * 得到用户的上门地址
     * @param userId
     * @return
     */
    UserAddressPO findUserAddress(@Param(value = "userId") int userId);
    
    /**
     * 得到用户的上门地址
     * @param userId
     * @return
     */
    UserAddressPO findUserAddressById(@Param(value = "id") int id);
}
