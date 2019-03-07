package com.lifeshs.po.electronicCoupons;

import java.util.Date;

import lombok.Data;

/**
 *  电子券模板
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月7日 下午5:05:06
 */
@Data
public class ElectronicCouponsTempletPO {

    private Integer id;
    /** 名称 */
    private String name;
    /** 相对金额 */
    private Integer price;
    /** 门店id */
    private Integer orgId;
    /** 服务code */
    private String serveCode;
    /** 服务项目id */
    private Integer serveItemId;
    /** 具体服务项目名称 */
    private String serveItemName;
    /** 机构项目code */
    private String projectCode;
    /** 机构项目名称 */
    private String projectName;
    /** 过期模式,0_不会失效,1_限制时间,2_领券后计时 */
    private Integer overdueModel;
    /** 领券后有效天数 */
    private Integer validDay;
    /** 限制结束日期 */
    private Date endDate;
    /** 是否已删除 */
    private Boolean deleted;
    /** 创建日期 */
    private Date createDate;
    /** 修改日期 */
    private Date modifyDate;
}
