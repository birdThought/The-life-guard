package com.lifeshs.entity.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_data_healthStandardValue
 */
@Table(name = "t_data_health_standard_value", schema = "")
@SuppressWarnings("serial")
public class TDataHealthStandardValue implements Serializable {

    /** 健康标准值 */
    public Integer id;

    /** 健康参数 */
    public String healthType;

    /** 性别 */
    public Boolean sex;

    /** 年龄段开始 */
    public Integer ageStart;

    /** 年龄段结束 */
    public Integer ageEnd;

    /** 最小值 */
    public Double min;

    /** 最大值 */
    public Double max;

    /** 创建时间 */
    public java.util.Date createDate;

    public TDataHealthStandardValue() {
        super();
    }

    public TDataHealthStandardValue(Integer id, String healthType, Boolean sex, Integer ageStart, Integer ageEnd,
            Double min, Double max, java.util.Date createDate) {
        super();
        this.id = id;
        this.healthType = healthType;
        this.sex = sex;
        this.ageStart = ageStart;
        this.ageEnd = ageEnd;
        this.min = min;
        this.max = max;
        this.createDate = createDate;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "healthType", nullable = true, length = 10)
    public String getHealthType() {
        return healthType;
    }

    public void setHealthType(String healthType) {
        this.healthType = healthType;
    }

    @Column(name = "sex", nullable = true, length = 1)
    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Column(name = "ageStart", nullable = false, length = 4)
    public Integer getAgeStart() {
        return ageStart;
    }

    public void setAgeStart(Integer ageStart) {
        this.ageStart = ageStart;
    }

    @Column(name = "ageEnd", nullable = false, length = 4)
    public Integer getAgeEnd() {
        return ageEnd;
    }

    public void setAgeEnd(Integer ageEnd) {
        this.ageEnd = ageEnd;
    }

    @Column(name = "min", nullable = false, length = 12)
    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    @Column(name = "max", nullable = false, length = 12)
    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @Column(name = "createDate", nullable = false, length = 19)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

}
