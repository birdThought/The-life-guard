package com.lifeshs.dto.manager.register.store;

import com.lifeshs.dto.manager.register.WorkAddressDTO;

import lombok.Data;

/**
 *  注册门店 - 公司信息认证
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月11日 上午10:51:35
 */
public @Data class OrgDTO {

    /** 图标 */
    private String logo;
    /** 门店名字 */
    private String orgName;
    /** 营业执照注册号 */
    private String businessLicenseNumber;
    /** 营业执照图片 */
    private String businessLicensePic;
    /** 公司性质 */
    private String orgType;
    /** 地区 */
    private WorkAddressDTO workAddress;
    /** 从事领域 */
    private String workField;
    /** 公司电话 */
    private String tel;
}
