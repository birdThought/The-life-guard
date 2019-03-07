package com.lifeshs.service1.smsRemind;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.pojo.member.UserSMSRemainDTO;

/**
 *  短信剩余service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月30日 下午4:55:24
 */
public interface SmsRemindService {

    /**
     *  为用户添加短信
     *  @author yuhang.weng 
     *  @DateTime 2017年8月31日 上午9:18:38
     *
     *  @param userId 用户id
     *  @param number 短信数量
     *  @throws OperationException
     */
    void addMemberSmsRemind(int userId, long number) throws OperationException;
    
    /**
     *  为机构添加短信
     *  @author yuhang.weng 
     *  @DateTime 2017年8月31日 上午9:19:00
     *
     *  @param orgId 机构id
     *  @param number 短信数量
     *  @throws OperationException
     */
    void addOrgSmsRemind(int orgId, long number) throws OperationException;
    
    /**
     *  减少用户的短信剩余数量
     *  @author yuhang.weng 
     *  @DateTime 2017年8月31日 上午9:28:55
     *
     *  @param userId
     *  @param number
     */
    void reduceMemberSmsRemind(int userId, long number) throws OperationException;
    
    /**
     *  减少机构的短信剩余数量
     *  @author yuhang.weng 
     *  @DateTime 2017年8月31日 上午9:29:08
     *
     *  @param orgId
     *  @param number
     */
    void reduceOrgSmsRemind(int orgId, long number) throws OperationException;
    
    /**
     *  获取用户的短信剩余
     *  @author yuhang.weng 
     *  @DateTime 2017年8月31日 上午9:23:19
     *
     *  @param userId
     *  @return
     */
    UserSMSRemainDTO getMemberSmsRemind(int userId);
    
    /**
     *  获取机构的短信剩余
     *  @author yuhang.weng 
     *  @DateTime 2017年8月31日 上午9:23:36
     *
     *  @param orgId
     *  @return
     */
    UserSMSRemainDTO getOrgSmsRemind(int orgId);
}
