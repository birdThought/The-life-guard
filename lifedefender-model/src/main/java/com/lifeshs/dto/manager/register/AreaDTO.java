package com.lifeshs.dto.manager.register;

import lombok.Data;

/**
 *  管理app - 注册 - 地区
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月9日 下午5:49:53
 */
public @Data class AreaDTO {
    /** 省 */
    private String province;
    /** 市 */
    private String city;
    /** 县 */
    private String county;
}
