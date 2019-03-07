package com.lifeshs.dto.manager.register.store;

import lombok.Data;

/**
 *  注册门店 - 法人信息认证
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月11日 上午10:56:14
 */
public @Data class LegalPersonDTO {

    /** 法人名称 */
    private String name;
    /** 身份证号码 */
    private String idCardNumber;
    /** 身份证正面照 */
    private String idCardPicFront;
    /** 身份证反面照 */
    private String idCardPicBack;
}
