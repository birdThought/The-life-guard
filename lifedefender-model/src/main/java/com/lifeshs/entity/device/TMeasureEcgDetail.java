package com.lifeshs.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_measure_ecg_detail
 */
@Table(name = "t_measure_ecg_detail", schema = "")
@SuppressWarnings("serial")
public class TMeasureEcgDetail implements Serializable{
    /**id*/
    private Integer id;
    
    /**心电测量ID*/
    private Integer ecgMeasureId;
    
    /**状态：正常_0,异常_项目和*/
    private Long status;
    
    /**心电*/
    private Integer heartRate;
    
    /**测量时间*/
    private java.util.Date measureDate;
    
    /**心电图片路径*/
    private String image;
    
    /**标记类型,0_自动,1_手动*/
    private Boolean signType;
    
    /**标记内容*/
    private String tags;
    
    /**心律ID*/
    private Integer rhythmId;
    
    /**创建时间*/
    private java.util.Date createDate;
    
    public TMeasureEcgDetail() {
        super();
    }
    
    public TMeasureEcgDetail(Integer id, Integer ecgMeasureId, Long status, Integer heartRate, java.util.Date measureDate, String image, Boolean signType, String tags, Integer rhythmId, java.util.Date createDate) {
        super();
        this.id = id; 
        this.ecgMeasureId = ecgMeasureId; 
        this.status = status; 
        this.heartRate = heartRate; 
        this.measureDate = measureDate; 
        this.image = image; 
        this.signType = signType; 
        this.tags = tags; 
        this.rhythmId = rhythmId; 
        this.createDate = createDate; 
    }
    
    @Column(name ="id",nullable=true,length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name ="ecgMeasureId",nullable=true,length=11)
    public Integer getEcgMeasureId() {
        return ecgMeasureId;
    }

    public void setEcgMeasureId(Integer ecgMeasureId) {
        this.ecgMeasureId = ecgMeasureId;
    }
    @Column(name ="status",nullable=false,length=19)
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
    @Column(name ="heartRate",nullable=false,length=6)
    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }
    @Column(name ="measureDate",nullable=true,length=19)
    public java.util.Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(java.util.Date measureDate) {
        this.measureDate = measureDate;
    }
    @Column(name ="image",nullable=false,length=200)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @Column(name ="signType",nullable=false,length=1)
    public Boolean getSignType() {
        return signType;
    }

    public void setSignType(Boolean signType) {
        this.signType = signType;
    }
    @Column(name ="tags",nullable=false,length=100)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    @Column(name ="rhythmId",nullable=false,length=11)
    public Integer getRhythmId() {
        return rhythmId;
    }

    public void setRhythmId(Integer rhythmId) {
        this.rhythmId = rhythmId;
    }
    @Column(name ="createDate",nullable=false,length=19)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }
    
}
