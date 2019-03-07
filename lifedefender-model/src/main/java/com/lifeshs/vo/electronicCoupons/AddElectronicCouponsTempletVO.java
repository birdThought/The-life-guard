package com.lifeshs.vo.electronicCoupons;

import java.util.Date;

import com.lifeshs.common.constants.common.electronicCoupons.OverdueModelEnum;
import com.lifeshs.po.electronicCoupons.ElectronicCouponsTempletPO;

import lombok.Data;

/**
 *  添加电子券模板VO
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月8日 下午2:49:08
 */
@Data
public class AddElectronicCouponsTempletVO {

    private String name;
    /** 门店id */
    private int orgId;
    /** 服务类型 */
    private String serveCode;
    /** 具体项目服务id */
    private Integer serveItemId;
    /** 具体项目服务名称 */
    private String serveItemName;
    /** 项目服务code */
    private String projectCode;
    /** 项目服务名称 */
    private String projectName;
    /** 相对金额 */
    private double price;
    /** 过期模式 */
    private OverdueModelEnum overDue;
    /** 有效天数 */
    private Integer validDay;
    /** 结束日期 */
    private Date endDate;
}
