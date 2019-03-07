package com.lifeshs.entity.consult;/**
 * Created by Administrator on 2017/5/2.
 */

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * 资讯收藏
 *
 * @author wenxian.cai
 * @create 2017-05-02 15:45
 **/

@Table(name="t_information_collect")
public class TInformationCollect {

    /**表主键*/
    private int id;

    /**用户ID*/
    private int userId;

    /**资讯ID*/
    private int informationId;

    /**创建日期*/
    private Date createDate;

    /**修改日期*/
    private Date modifyDate;

    @Override
    public String toString() {
        return "TInformationCollect{" +
                "id=" + id +
                ", userId=" + userId +
                ", informationId=" + informationId +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }

    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "informationId")
    public int getInformationId() {
        return informationId;
    }

    public void setInformationId(int informationId) {
        this.informationId = informationId;
    }

    @Column(name = "createDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "modifyDate")
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
