package com.lifeshs.pojo.data;

import java.util.Date;

/**
 * 用户邀请码
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年4月5日 下午4:43:34
 */
public class InvitationCodeDTO {

    private Integer id;

    private Integer agencyId;

    private String invitationCode;

    private Date createDate;

    private Date entryDate;

    private AgencyDTO agency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public AgencyDTO getAgency() {
        return agency;
    }

    public void setAgency(AgencyDTO agency) {
        this.agency = agency;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public String toString() {
        return "InvitationCodeDTO [id=" + id + ", agencyId=" + agencyId + ", invitationCode=" + invitationCode
                + ", createDate=" + createDate + ", entryDate=" + entryDate + ", agency=" + agency + "]";
    }

}
