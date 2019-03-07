package com.lifeshs.entity.org;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_serve
 */
@Table(name = "t_serve", schema = "")
@SuppressWarnings("serial")
public class TServe implements Serializable {
    /** 平台服务 */
    private Integer id;

    /** 服务名称 */
    private String name;

    /** 代码 */
    private String code;

    /** 服务介绍 */
    private String about;

    /** 平台分成(百分比数字) */
    private Integer profitShare;

    /** 图片路径 */
    private String image;

    /** 服务所属的分类：1_健康养生，2_慢病康复，3_减肥塑体，4居家养老，5_癌症康复 */
    private Integer serveType;

    /** 对服务再分类，以,分隔 */
    private String classify;
    
    private String hasOrder;//已经定制过的服务id，以,分隔开

    /** 添加时间 */
    private java.util.Date createDate;

    //服务的收费方式，以逗号分隔开  0_免费,1_按次收费,_2_按月收费,3_按年收费
    private String chargeMode;

    public TServe() {
        super();
    }

    public TServe(Integer id, String name, String code, String about, Integer profitShare, String image,
            Integer serveType, String classify, java.util.Date createDate) {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
        this.about = about;
        this.profitShare = profitShare;
        this.image = image;
        this.serveType = serveType;
        this.classify = classify;
        this.createDate = createDate;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code", nullable = false, length = 3)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "about", nullable = false, length = 200)
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Column(name = "profitShare", nullable = false, length = 11)
    public Integer getProfitShare() {
        return profitShare;
    }

    public void setProfitShare(Integer profitShare) {
        this.profitShare = profitShare;
    }

    @Column(name = "image", nullable = false, length = 200)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name = "serveType", nullable = false, length = 10)
    public Integer getServeType() {
        return serveType;
    }

    public void setServeType(Integer serveType) {
        this.serveType = serveType;
    }

    @Column(name = "classify", nullable = false, length = 50)
    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @Column(name = "createDate", nullable = false, length = 19)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }
    
    public String getHasOrder() {
        return hasOrder;
    }

    public void setHasOrder(String hasOrder) {
        this.hasOrder = hasOrder;
    }
    @Column(name ="chargeMode")
    public String getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(String chargeMode) {
        this.chargeMode = chargeMode;
    }
}