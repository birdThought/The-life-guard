package com.lifeshs.dto.manager.register.individualStore;

import com.lifeshs.dto.manager.register.WorkAddressDTO;

import lombok.Data;

/**
 * 个体门店注册 - 个人资格认证
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月9日 下午5:33:14
 */
public @Data class PersonalQulificationDTO {
    /** 工作地址 */
    private WorkAddressDTO workAddress;
    /** 职称 */
    private String professionalName;
    /** 职业资格照 */
    private String professionPic;
    /** 擅长领域 */
    private String expertField;
    /** 个人简介 */
    private String about;
}
