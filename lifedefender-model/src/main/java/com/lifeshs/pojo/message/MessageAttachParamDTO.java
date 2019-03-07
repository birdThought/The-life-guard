package com.lifeshs.pojo.message;

import lombok.Data;

/**
 *  消息追加参数
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年11月3日 下午4:32:16
 */
@Data
public class MessageAttachParamDTO {

    /** 服务code */
    private String code;
    /** id */
    private String id;
    /** 用户id */
    private String userId;
    /** 日期 */
    private String date;
    /** 用户头像 */
    private String photo;
    /** 用户名（管理app） */
    private String name;
    /** 环信账号 */
    private String huanxinUserName;
}
