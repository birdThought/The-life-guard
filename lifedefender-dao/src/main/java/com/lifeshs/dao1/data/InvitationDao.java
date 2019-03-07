package com.lifeshs.dao1.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.invitation.InvitationPO;

/**
 *  邀请码dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月30日 上午10:51:22
 */
@Repository(value = "invitationDao")
public interface InvitationDao {

    /**
     *  添加邀请码
     *  @author yuhang.weng 
     *  @DateTime 2017年8月30日 下午3:20:40
     *
     *  @param invitationCode
     *  @return
     */
    int addInvitation(InvitationPO invitationCode);
    
    /**
     *  添加邀请码(批量)
     *  @author yuhang.weng 
     *  @DateTime 2017年8月30日 下午3:20:48
     *
     *  @param invitationCodes
     *  @return
     */
    int addInvitationList(@Param("invitationPOList") List<InvitationPO> invitationPOList);
    
    /**
     *  更新邀请码状态为已使用
     *  @author yuhang.weng 
     *  @DateTime 2017年8月30日 下午2:29:11
     *
     *  @param code 邀请码
     *  @return
     */
    int updateInvitationCodeToUsedByCode(@Param("code") String code);
    
    /**
     *  获取一个邀请码
     *  @author yuhang.weng 
     *  @DateTime 2017年8月30日 下午1:46:24
     *
     *  @param id
     *  @return
     */
    InvitationPO getInvitationCode(@Param("id") int id);
    
    /**
     *  通过code去查找邀请码对象
     *  @author yuhang.weng 
     *  @DateTime 2017年8月30日 下午2:29:33
     *
     *  @param code
     *  @return
     */
    InvitationPO getInvitationCodeByCode(@Param("code") String code);
}
