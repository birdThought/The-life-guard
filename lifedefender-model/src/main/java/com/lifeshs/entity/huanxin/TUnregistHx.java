package com.lifeshs.entity.huanxin;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by XuZhanSi on 2016/12/5 0005.
 */
@Table(name = "t_unregist_hx")
public class TUnregistHx {
    private Integer id;
    private String username;
    private String password;
    private Date createDate;
    private Integer errorCode;
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "createDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public TUnregistHx(){}
    @Column(name = "errorCode")
    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public TUnregistHx(Integer id, String username, String password, Date createDate, Integer errorCode) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "TUnregistHx{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", errorCode=" + errorCode +
                '}';
    }
}
