package com.lifeshs.pojo.data;

import java.util.Date;

/**
 *  合作机构
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年4月5日 下午4:45:29
 */
public class AgencyDTO {

    private Integer id;

    private String name;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "AgencyDTO [id=" + id + ", name=" + name + ", createDate=" + createDate + "]";
    }

}
