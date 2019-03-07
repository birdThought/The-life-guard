package com.lifeshs.entity.consult;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by XuZhanSi on 2016/12/15 0015.
 * 咨询栏目
 */
@Entity
@Table(name = "t_information_column")
public class TInfomationColumn implements Serializable{
    private Integer id;//主键id
    private String name;//栏目名称
    private String code;//栏目代码
    private Integer parentId;//父栏目id，0为顶层栏目
    private Date createDate;//创建时间

    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "parentId")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(name = "createDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public TInfomationColumn() {
    }

    public TInfomationColumn(Integer id, String name, String code, Integer parentId, Date createDate) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.parentId = parentId;
        this.createDate = createDate;
    }
}
