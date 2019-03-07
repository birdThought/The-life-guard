package com.lifeshs.po;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectComboPO {
    private Integer id;

    private String projectCode;

    private BigDecimal price;

    private String introduce;

    private String name;

    private Date createDate;

    private BigDecimal marketPrice;

    public ProjectComboPO(Integer id, String projectCode, BigDecimal price, String introduce, String name, Date createDate, BigDecimal marketPrice) {
        this.id = id;
        this.projectCode = projectCode;
        this.price = price;
        this.introduce = introduce;
        this.name = name;
        this.createDate = createDate;
        this.marketPrice = marketPrice;
    }

    public ProjectComboPO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Override
    public String toString() {
        return "ProjectComboPO{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", price=" + price +
                ", introduce='" + introduce + '\'' +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", marketPrice=" + marketPrice +
                '}';
    }
}