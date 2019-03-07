package com.lifeshs.entity.sms;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_sms
 */
@Table(name = "t_sms", schema = "")
@SuppressWarnings("serial")
public class TSms implements Serializable {

    private Integer id;

    /** 发送者类型：1_用户，2_机构，0_平台 */
    private Integer sendType;

    /** 发送者ID */
    private Integer sendId;

    private String content;

    private String receiveMobile;

    /** 状态：0_已发送，1_未发送 */
    private Integer status;

    /** IP */
    private String ip;

    /** 发送时间 */
    private java.util.Date createDate;

    public TSms() {
        super();
    }

    public TSms(Integer id, Integer sendType, Integer sendId, String content, String receiveMobile, Integer status,
            String ip, java.util.Date createDate) {
        super();
        this.id = id;
        this.sendType = sendType;
        this.sendId = sendId;
        this.content = content;
        this.receiveMobile = receiveMobile;
        this.status = status;
        this.ip = ip;
        this.createDate = createDate;
    }

    @Column(name = "id", nullable = true, length = 20)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "sendType", nullable = false, length = 4)
    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    @Column(name = "sendId", nullable = false, length = 11)
    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    @Column(name = "content", nullable = false, length = 140)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "receiveMobile", nullable = false, length = 11)
    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    @Column(name = "status", nullable = false, length = 4)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "ip", nullable = false, length = 15)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "createDate", nullable = false, length = 10)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

}