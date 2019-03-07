package com.lifeshs.po.user;

import java.util.Date;

import lombok.Data;

/**
 *  用户卡包领取记录
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月7日 下午5:16:36
 */
@Data
public class UserElectronicCouponsPackageRecordPO {

    private Integer id;
    /** 用户id */
    private Integer userId;
    /** 卡包id */
    private Integer packageId;
    /** 创建日期 */
    private Date createDate;
}
