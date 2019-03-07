package com.lifeshs.service1.data.invitation;

import java.util.List;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.invitation.InvitationPO;

/**
 *  邀请码相关service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月30日 下午4:23:12
 */
public interface InvitationService {

    /**
     *  添加邀请码
     *  @author yuhang.weng 
     *  @DateTime 2017年8月30日 下午4:46:18
     *
     *  @param invitation
     *  @return
     */
    boolean addInvitation(InvitationPO invitation);
    
    /**
     *  添加邀请码
     *  @author yuhang.weng 
     *  @DateTime 2017年8月30日 下午4:46:26
     *
     *  @param invitationList
     *  @return
     */
    boolean addInvitation(List<InvitationPO> invitationList);
    
    /**
     *  修改邀请码为已使用状态
     *  @author yuhang.weng 
     *  @DateTime 2017年8月30日 下午4:42:25
     *
     *  @param userId 用户id
     *  @param code 邀请码
     *  @return
     *  @throws OperationException
     */
    void updateInvitaionToUse(int userId, String code) throws OperationException;
}
