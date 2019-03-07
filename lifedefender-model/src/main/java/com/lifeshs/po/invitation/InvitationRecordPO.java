package com.lifeshs.po.invitation;

import java.util.Date;

import lombok.Data;

/**
 *  用户邀请码使用记录
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月30日 下午1:55:41
 */
public @Data class InvitationRecordPO {

    private Integer id;
    /** 用户ID */
    private int userId;
    /** 邀请码ID */
    private int invitationCodeId;
    /** 创建日期 */
    private Date createDate;
}
