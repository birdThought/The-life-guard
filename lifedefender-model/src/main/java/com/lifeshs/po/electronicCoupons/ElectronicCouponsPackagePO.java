package com.lifeshs.po.electronicCoupons;

import java.util.Date;

import lombok.Data;

/**
 *  电子券卡包
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月7日 下午5:01:29
 */
@Data
public class ElectronicCouponsPackagePO {

    private Integer id;
    /** 名称 */
    private String name;
    /** 相对金额 */
    private Integer price;
    /** 标识码卡号 */
    private String code;
    /** 使用说明 */
    private String instructions;
    /** 渠道商id */
    private Integer businessId;
    /** 是否已删除 */
    private Boolean deleted;
    /** 创建日期 */
    private Date createDate;
    /** 修改日期 */
    private Date modifyDate;
}
