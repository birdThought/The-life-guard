package com.lifeshs.dto.manager.register;

import lombok.Data;

/**
 * 管理app - 注册 - 工作地址
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月9日 下午5:50:10
 */
public @Data class WorkAddressDTO {
    /** 地区 */
    private AreaDTO area;
    /** 详细地址 */
    private String address;
    /** 经度 */
    private Double longitude;
    /** 纬度 */
    private Double latitude;
}
