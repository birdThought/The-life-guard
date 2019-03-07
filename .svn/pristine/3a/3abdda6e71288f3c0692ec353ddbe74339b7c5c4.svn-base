package com.lifeshs.po.push;

import lombok.Data;

import java.util.Date;

/**
 * 推送信息
 * author: wenxian.cai
 * date: 2017/8/24 10:13
 */
public @Data class PushMessagePO {

    /** 主键 */
    private Integer id;

    /** 消息接受者Id集合（以符号''；''隔开） */
    private String receiveId;

    /** 消息接受者姓名集合 */
    private String[] userName;

    /** 用户类型（1_普通用户, 2_机构用户） */
    private int userType;

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 是否已删除（0未删除，1已删除） */
    private int deleted;

    /** 消息类型（1_系统推送，2_门店推送，3_服务师推送,4_客服推送 ） */
    private int msgType;

    /** 发送类型（1_app推送，2_短信推送） */
    private int sendType;

    /** 发送者Id */
    private int sendId;

    /** 打开类型：1查看消息,2打开指定APP页面,3打开指定URL */
    private int openType;

    /** 打开目标,由openType决定是activity地址或url地址 */
    private String openTarget;

    /** 附加消息,格式：key1:value1,key2:value2 */
    private String openAttach;

    /** 创建时间 */
    private Date createDate;

}
