package com.lifeshs.pojo.message;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  消息DTO
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月12日 下午4:25:57
 */
public @Data class MessageDTO {

    private Integer id;
    /** 用户ID */
    @JSONField(serialize = false)
    private Integer userId;
    /** 用户类型 */
    @JSONField(serialize = false)
    private Integer userType;
    /** 标题 */
    private String title;
    /** 内容 */
    private String content;
    /** 是否已读 */
    private Boolean read;
    /** 消息类型 */
    @JSONField(serialize = false)
    private Integer msgType;
    /** 创建日期 */
    private Date createDate;
    /** 修改日期 */
    private Date modifyDate;
    /** 发送者ID */
    private Integer sendId;
    /** 打开类型：1查看消息,2打开指定APP页面,3打开指定URL */
    private Integer openType;
    /** 打开目标,activity地址 */
    private String openTarget;
    /** 附加消息,格式：key1:value1,key2:value2 */
    private String openAttach;
    /** 打开目标_IOS，ios页面名称 */
    private String openTargetIOS;
    /** 附加消息,格式：key1:value1,key2:value2 */
    private String openAttachIOS;

    @Override
    public MessageDTO clone(){
        //实现深拷贝
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.id = this.id;
        messageDTO.userId = this.userId;
        messageDTO.userType = this.userType;
        messageDTO.title = this.title;
        messageDTO.content = this.content;
        messageDTO.read = this.read;
        messageDTO.msgType = this.msgType;
        messageDTO.createDate = this.createDate;
        messageDTO.modifyDate = this.modifyDate;
        messageDTO.sendId = this.sendId;
        messageDTO.openType = this.openType;
        messageDTO.openTarget = this.openTarget;
        messageDTO.openAttach = this.openAttach;
        messageDTO.openTargetIOS = this.openTargetIOS;
        messageDTO.openAttachIOS = this.openAttachIOS;
        return messageDTO;
    }
}
