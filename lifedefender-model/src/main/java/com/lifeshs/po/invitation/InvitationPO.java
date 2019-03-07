package com.lifeshs.po.invitation;

import java.util.Date;

import lombok.Data;

/**
 *  邀请码
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月30日 上午11:19:47
 */
public @Data class InvitationPO {

    private Integer id;
    /** 合作单位id */
    private Integer agencyId;
    /** 邀请码 */
    private String invitationCode;
    /** 创建日期 */
    private Date createDate;
    /** 输入日期 */
    private Date entryDate;
}
