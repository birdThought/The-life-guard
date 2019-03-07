package com.lifeshs.pojo.member;

import lombok.Data;

/**
 * @Author Yue.Li
 * @Date 2017/5/11.
 */
@Data
public class UserMeasurementReminderDTO {
    private Integer reminderId;
    private Integer userId;
    /**
     * 短信开关
     */
    private  Integer smsSwitch;
    /**
     * 推送开关
     */
    private  Integer pushSwitch;
}
