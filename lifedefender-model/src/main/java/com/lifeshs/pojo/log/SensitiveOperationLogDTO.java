package com.lifeshs.pojo.log;

import com.lifeshs.common.constants.common.SensitiveOperationType;
import com.lifeshs.common.constants.common.SensitiveUserType;

/**
 * 敏感操作记录
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年4月7日 上午9:43:57
 */
public class SensitiveOperationLogDTO {

    /** 用户id */
    private Integer userId;

    /** 用户类型 */
    private SensitiveUserType userType;

    /** 操作类型 */
    private SensitiveOperationType operationType;

    /** 原数据 */
    private String generateData;

    /** 新数据 */
    private String newData;

    /** ip */
    private String ip;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public SensitiveUserType getUserType() {
        return userType;
    }

    public void setUserType(SensitiveUserType userType) {
        this.userType = userType;
    }

    public SensitiveOperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(SensitiveOperationType operationType) {
        this.operationType = operationType;
    }

    public String getGenerateData() {
        return generateData;
    }

    public void setGenerateData(String generateData) {
        this.generateData = generateData;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "SensitiveOperationLogDTO [userId=" + userId + ", userType=" + userType + ", operationType="
                + operationType + ", generateData=" + generateData + ", newData=" + newData + ", ip=" + ip + "]";
    }

}
