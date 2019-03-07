package com.lifeshs.entity.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_data_healthDescribe
 */
@Table(name = "t_data_health_describe", schema = "")
@SuppressWarnings("serial")
public class TDataHealthDescribe implements Serializable {
    /** 健康文字描述 */
    private Integer id;

    /** 参数 */
    private String param;

    /** 性别：女_0，男_1，不限_2 */
    private Boolean sex;

    /** 低 */
    private String less;

    /** 偏低 */
    private String min;

    /** 正常 */
    private String normal;

    /** 偏高 */
    private String max;

    /** 高 */
    private String more;

    public TDataHealthDescribe() {
        super();
    }

    public TDataHealthDescribe(Integer id, String param, Boolean sex, String less, String min, String normal,
            String max, String more) {
        super();
        this.id = id;
        this.param = param;
        this.sex = sex;
        this.less = less;
        this.min = min;
        this.normal = normal;
        this.max = max;
        this.more = more;
    }

    @Column(name = "id", nullable = true, length = 10)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "param", nullable = false, length = 16)
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Column(name = "sex", nullable = false, length = 1)
    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Column(name = "less", nullable = false, length = 500)
    public String getLess() {
        return less;
    }

    public void setLess(String less) {
        this.less = less;
    }

    @Column(name = "min", nullable = false, length = 500)
    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    @Column(name = "normal", nullable = false, length = 500)
    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    @Column(name = "max", nullable = false, length = 500)
    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    @Column(name = "more", nullable = false, length = 500)
    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

}