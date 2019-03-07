package com.lifeshs.entity.org;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_org_serve
 */
@Table(name = "t_org_serve", schema = "")
@SuppressWarnings("serial")
public class TOrgServe implements Serializable {

    private Integer id;

    /** 机构服务ID */
    private Integer orgId;

    /** 服务ID */
    private Integer serveId;

    /** 0_免费，1_收费 */
    private Boolean hasFree;

    /** 免费时长（月），0无限制 */
    private Integer freeDate;

    /** 有按次收费：1_是，0_否 */
    private Boolean hasTime;

    /** 按次的价格 */
    private Integer timePrice;

    /** 有按月收费：1_是，0_否 */
    private Boolean hasMonth;

    /** 按月的价格 */
    private Integer monthPrice;

    /** 有按年收费：1_是，0_否 */
    private Boolean hasYear;

    /** 按年的价格 */
    private Integer yearPrice;

    /** 服务分类 */
    private String classify;

    /** 服务介绍 */
    private String about;

    /** 创建人 */
    private String creater;

    /** 创建时间 */
    private java.util.Date createDate;

    public TOrgServe() {
        super();
    }

    public TOrgServe(Integer id, Integer orgId, Integer serveId, Boolean hasFree, Integer freeDate, Boolean hasTime,
            Integer timePrice, Boolean hasMonth, Integer monthPrice, Boolean hasYear, Integer yearPrice,
            String classify, String about, String creater, java.util.Date createDate) {
        super();
        this.id = id;
        this.orgId = orgId;
        this.serveId = serveId;
        this.hasFree = hasFree;
        this.freeDate = freeDate;
        this.hasTime = hasTime;
        this.timePrice = timePrice;
        this.hasMonth = hasMonth;
        this.monthPrice = monthPrice;
        this.hasYear = hasYear;
        this.yearPrice = yearPrice;
        this.classify = classify;
        this.about = about;
        this.creater = creater;
        this.createDate = createDate;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "orgId", nullable = true, length = 11)
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Column(name = "serveId", nullable = true, length = 11)
    public Integer getServeId() {
        return serveId;
    }

    public void setServeId(Integer serveId) {
        this.serveId = serveId;
    }

    @Column(name = "hasFree", nullable = false, length = 1)
    public Boolean getHasFree() {
        return hasFree;
    }

    public void setHasFree(Boolean hasFree) {
        this.hasFree = hasFree;
    }

    @Column(name = "freeDate", nullable = false, length = 11)
    public Integer getFreeDate() {
        return freeDate;
    }

    public void setFreeDate(Integer freeDate) {
        this.freeDate = freeDate;
    }

    @Column(name = "hasTime", nullable = false, length = 1)
    public Boolean getHasTime() {
        return hasTime;
    }

    public void setHasTime(Boolean hasTime) {
        this.hasTime = hasTime;
    }

    @Column(name = "timePrice", nullable = false, length = 11)
    public Integer getTimePrice() {
        return timePrice;
    }

    public void setTimePrice(Integer timePrice) {
        this.timePrice = timePrice;
    }

    @Column(name = "hasMonth", nullable = false, length = 1)
    public Boolean getHasMonth() {
        return hasMonth;
    }

    public void setHasMonth(Boolean hasMonth) {
        this.hasMonth = hasMonth;
    }

    @Column(name = "monthPrice", nullable = false, length = 11)
    public Integer getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(Integer monthPrice) {
        this.monthPrice = monthPrice;
    }

    @Column(name = "hasYear", nullable = false, length = 1)
    public Boolean getHasYear() {
        return hasYear;
    }

    public void setHasYear(Boolean hasYear) {
        this.hasYear = hasYear;
    }

    @Column(name = "yearPrice", nullable = false, length = 11)
    public Integer getYearPrice() {
        return yearPrice;
    }

    public void setYearPrice(Integer yearPrice) {
        this.yearPrice = yearPrice;
    }

    @Column(name = "classify", nullable = false, length = 20)
    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @Column(name = "about", nullable = false, length = 500)
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Column(name = "creater", nullable = false, length = 20)
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Column(name = "createDate", nullable = false, length = 19)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "TOrgServe [id=" + id + ", orgId=" + orgId + ", serveId=" + serveId + ", hasFree=" + hasFree
                + ", hasTime=" + hasTime + ", timePrice=" + timePrice + ", hasMonth=" + hasMonth + ", monthPrice="
                + monthPrice + ", hasYear=" + hasYear + ", yearPrice=" + yearPrice + ", classify=" + classify
                + ", about=" + about + ", creater=" + creater + ", createDate=" + createDate + ", freeDate=" + freeDate
                + "]";
    }
}