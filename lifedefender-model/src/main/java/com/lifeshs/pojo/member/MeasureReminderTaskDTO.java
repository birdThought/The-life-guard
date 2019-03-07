package com.lifeshs.pojo.member;

import lombok.Data;

/**
 *  测量任务
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月23日 上午10:38:09
 */
public @Data class MeasureReminderTaskDTO {

    /** 提醒ID */
    private Integer reminderDetailId;
    /** 设备token */
    private String deviceToken;
    /** 操作系统 1_安卓，2_苹果 */
    private Integer OS;
    /** 系统版本 */
    private String systemVersion;
    /** 测量设备 */
    private String devices;
    /** 用户ID */
    private Integer receiverId;
}
