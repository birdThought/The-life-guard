package com.lifeshs.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_measure_ecg
 */
@Table(name = "t_measure_ecg", schema = "")
@SuppressWarnings("serial")
public class TMeasureEcg implements Serializable {
    /** 心电 */
    private Integer id;

    /** 用户设备ID */
    private Integer userId;

    /** 状态：正常_0,异常_项目和 */
    private Integer status;

    /** 心电 */
    private Integer heartRate;

    /** 测量时间 */
    private java.util.Date measureDate;

    /** 终端类型 */
    private String deviceType;

    /** 创建时间 */
    private java.util.Date createDate;

    /** 心电图片路径 */
    private String image;

    /** 标记类型,0_自动,1_手动 */
    private Boolean signType;

    /** 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    private Integer dataType;

    /** 标记内容 */
    private String tags;

    /** 心律ID */
    private Integer rhythmId;

    public TMeasureEcg() {
        super();
    }

    public TMeasureEcg(Integer id, Integer userId, Integer status, Integer heartRate, java.util.Date measureDate,
            String deviceType, java.util.Date createDate, String image, Boolean signType, Integer dataType, String tags,
            Integer rhythmId) {
        super();
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.heartRate = heartRate;
        this.measureDate = measureDate;
        this.deviceType = deviceType;
        this.createDate = createDate;
        this.image = image;
        this.signType = signType;
        this.dataType = dataType;
        this.tags = tags;
        this.rhythmId = rhythmId;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "userId", nullable = true, length = 11)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "status", nullable = false, length = 6)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "heartRate", nullable = false, length = 6)
    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    @Column(name = "measureDate", nullable = true, length = 19)
    public java.util.Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(java.util.Date measureDate) {
        this.measureDate = measureDate;
    }

    @Column(name = "deviceType", nullable = false, length = 20)
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Column(name = "createDate", nullable = false, length = 19)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "image", nullable = false, length = 200)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name = "signType", nullable = false, length = 1)
    public Boolean getSignType() {
        return signType;
    }

    public void setSignType(Boolean signType) {
        this.signType = signType;
    }

    @Column(name = "dataType", nullable = false, length = 2)
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    @Column(name = "tags", nullable = false, length = 100)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Column(name = "rhythmId", nullable = false, length = 11)
    public Integer getRhythmId() {
        return rhythmId;
    }

    public void setRhythmId(Integer rhythmId) {
        this.rhythmId = rhythmId;
    }

}