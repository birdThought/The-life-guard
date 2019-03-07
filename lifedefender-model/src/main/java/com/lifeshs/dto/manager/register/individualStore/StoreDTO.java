package com.lifeshs.dto.manager.register.individualStore;

import lombok.Data;

/**
 * 个体门店注册 - 店铺简介
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月9日 下午5:43:44
 */
public @Data class StoreDTO {

    /** 店铺名称 */
    private String orgName;
    /** 店铺分类 */
    private String orgType;
    /** 从事领域 */
    private String workField;
    /** 店铺简介 */
    private String about;
}
