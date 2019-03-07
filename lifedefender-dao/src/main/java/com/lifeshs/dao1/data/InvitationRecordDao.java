package com.lifeshs.dao1.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.invitation.InvitationRecordPO;
import com.lifeshs.pojo.member.InvicationRecordDTO;

/**
 * 邀请码记录
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月30日 上午9:33:54
 */
@Repository(value = "invicationRecordDao")
public interface InvitationRecordDao {

    /**
     * 添加一条邀请码记录
     * 
     * @author yuhang.weng
     * @DateTime 2017年4月28日 上午11:31:16
     *
     * @param recordDTO
     */
    int addUserInvitationRecord(InvitationRecordPO recordDTO);

    /**
     * 获取用户邀请码记录列表
     * 
     * @author yuhang.weng
     * @DateTime 2017年4月28日 上午11:16:57
     *
     * @param userId 用户id
     * @return
     */
    List<InvicationRecordDTO> findInvitationRecordByUserId(@Param("userId") int userId);
}
