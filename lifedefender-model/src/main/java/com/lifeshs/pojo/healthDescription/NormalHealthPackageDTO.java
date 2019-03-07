package com.lifeshs.pojo.healthDescription;

/**
 * 按照健康包分类的健康描述DTO
 * 
 * @author yuhang.weng
 * @DateTime 2017年2月20日 上午11:32:11
 */
public class NormalHealthPackageDTO {

    private Integer id;
    /** 健康参数二进制值 */
    private Long healthPackageParamBinaryValue;
    /** 描述文字 */
    private String description;
    /** 状态(低_1,偏低_2,正常_3,偏高_4,高_5) */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getHealthPackageParamBinaryValue() {
        return healthPackageParamBinaryValue;
    }

    public void setHealthPackageParamBinaryValue(Long healthPackageParamBinaryValue) {
        this.healthPackageParamBinaryValue = healthPackageParamBinaryValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NormalHealthPackageDTO [id=" + id + ", healthPackageParamBinaryValue=" + healthPackageParamBinaryValue
                + ", description=" + description + ", status=" + status + "]";
    }

}
