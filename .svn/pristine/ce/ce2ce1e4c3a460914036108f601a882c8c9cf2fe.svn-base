package com.lifeshs.dao1.smsRemind;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.pojo.member.UserSMSRemainDTO;

/**
 *  短信剩余dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月30日 上午9:45:46
 */
@Repository(value = "smsRemindDao")
public interface SmsRemindDao {

    /**
     *  添加用户剩余短信数量记录
     *  @author yuhang.weng 
     *  @DateTime 2017年4月27日 上午10:15:51
     *
     *  @param smsRemainDTO
     */
    int addSMSRemain(UserSMSRemainDTO smsRemainDTO);
    
    /**
     *  修改用户剩余短信数量记录
     *  需要提供用户id以及用户类型userType
     *  @author yuhang.weng 
     *  @DateTime 2017年4月27日 上午10:16:03
     *
     *  @param smsRemainDTO
     */
    int updateSMSRemain(UserSMSRemainDTO smsRemainDTO);
    
    /**
     *  获取用户剩余短信数量
     *  @author yuhang.weng 
     *  @DateTime 2017年4月27日 上午9:56:49
     *
     *  @param userId 用户
     *  @param userType 用户类型：1_会员，2_机构
     *  @return
     */
    UserSMSRemainDTO findSMSRemainByUserIdAndUserType(@Param("userId") int userId, @Param("userType") int userType);
}
