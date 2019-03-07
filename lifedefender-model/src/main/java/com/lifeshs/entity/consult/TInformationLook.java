package com.lifeshs.entity.consult;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by XuZhanSi on 2017/1/18 0018.
 */
@Table(name="t_information_look")
public class TInformationLook implements Serializable{
    private Integer id;
    private Integer informationId;
    private String ip;
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    @Column(name = "information_id")
    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    @Override
    public String toString() {
        return "TInformationLook{" +
                "id=" + id +
                ", informationId=" + informationId +
                ", ip='" + ip + '\'' +
                '}';
    }

    public TInformationLook() {
    }

    public TInformationLook(Integer id, Integer informationId, String ip) {
        this.id = id;
        this.informationId = informationId;
        this.ip = ip;
    }
}
