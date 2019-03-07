package com.lifeshs.po.transfer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 * @create 2018-01-18
 * 10:34   家政天下订单
 * @desc
 */
@Data
public class TransferCleaning {

    private Integer id;
    private String appid;
    private String accesstoken;
    /** 订单号*/
    private String orderno;
    /** 总金额*/
    private BigDecimal totalprice;
    /** 外部所得*/
    private BigDecimal platformprice;
    /** 门店所得*/
    private BigDecimal companyprice;
    /** 员工工资*/
    private BigDecimal nurseprice;
    /** 客户姓名*/
    private String customername;
    /** 客户手机*/
    private String customermobile;
    /** 省*/
    private String workprovince;
    /** 市*/
    private String workcity;
    /** 区*/
    private String workdistrict;
    /** 详细地址*/
    private String workaddress;
    /** 家庭面积*/
    private String familyarea;
    /** 家庭/人口 */
    private Integer population;
    private Integer gender;
    /** 语言*/
    private Integer language;
    /** 工作类型*/
    private String jobname;
    /** 其他信息*/
    private String details;
    /** 有效期*/
    private String youxiqi;
    /** 服务时间*/
    private String yonggongshijian;
    /** 工作时长*/
    private Integer yonggongshichang;
    /** 创建日期*/
    private Date createdate;
    /** 修改日期*/
    private Date modifytime;
    /** 原因*/
    private String Info;

}
