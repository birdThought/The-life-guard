package com.lifeshs.entity.device;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_sport_location
 */
@Table(name = "t_sport_location", schema = "")
@SuppressWarnings("serial")
public class TSportLocation implements Serializable {

    /**
     * 定位记录
     */
    public Integer id;

    /**
     * 用户设备ID
     */
    public Integer userId;

    /**
     * 产品型号
     */
    public String productModel;

    /**
     * 经度(建议不超过.后六位)
     */
    public Double longitude;

    /**
     * 纬度(建议不超过.后六位)
     */
    public Double latitude;

    /**
     * 中文地址
     */
    public String address;

    /**
     * 定位设备：GPS_1,WIFI_2,SIM_3
     */
    public Integer locationMode;

    /**
     * 测量时间
     */
    public Date measureDate;

    /**
     * 等于 DW 是实时定位、等于 SOS 是SOS定位信息，等于DS是定时上传定位的内容，等于SD是手动上传数据
     */
    public String realLoca;

    /**
     * 创建时间
     */
    public Date createDate;


    public TSportLocation() {
        super();
    }

    public TSportLocation(Integer id, Integer userId, String productModel, Double longitude, Double latitude, String address, Integer locationMode, Date measureDate, String realLoca, Date createDate) {
        super();
        this.id = id;
        this.userId = userId;
        this.productModel = productModel;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.locationMode = locationMode;
        this.measureDate = measureDate;
        this.realLoca = realLoca;
        this.createDate = createDate;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "userId", nullable = true, length = 11)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "productModel", nullable = false, length = 16)
    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    @Column(name = "longitude", nullable = false, length = 12)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "latitude", nullable = false, length = 12)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "address", nullable = false, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "locationMode", nullable = false, length = 6)
    public Integer getLocationMode() {
        return locationMode;
    }

    public void setLocationMode(Integer locationMode) {
        this.locationMode = locationMode;
    }

    @Column(name = "measureDate", nullable = false, length = 19)
    public Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }

    @Column(name = "realLoca", nullable = false, length = 10)
    public String getRealLoca() {
        return realLoca;
    }

    public void setRealLoca(String realLoca) {
        this.realLoca = realLoca;
    }

    @Column(name = "createDate", nullable = false, length = 19)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}
