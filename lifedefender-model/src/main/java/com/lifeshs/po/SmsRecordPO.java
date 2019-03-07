package com.lifeshs.po;

import java.util.Date;

import lombok.Data;

/**
 *  短信记录
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月28日 上午11:24:41
 */
@Data
public class SmsRecordPO {

    private Integer id;
    /** 发送者类型：1_用户，2_机构，0_平台  */
    private Integer sendType;
    /** 发送者id */
    private Integer sendId;
    /** 短信内容 */
    private String content;
    /** 接收短信的手机号码 */
    private String receiveMobile;
    /** 状态：0_已发送，1_未发送 */
    private Integer status;
    /** ip地址 */
    private String ip;
    /** 创建日期 */
    private Date createDate;
}
