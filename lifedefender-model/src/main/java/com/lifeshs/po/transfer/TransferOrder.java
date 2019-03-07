package com.lifeshs.po.transfer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *  转让家政天下订单
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2019年1月25日 上午11:51:12
 */
@Data
public class TransferOrder {

    private Integer id;
    private String appid;
    private String accesstoken;
    private String orderno;//外部系统订单号
    private BigDecimal totalprice;//订单总金额
    private BigDecimal sysprice;//平台所得
    private BigDecimal platformprice;//家政天下所得金额
    private BigDecimal companyprice;//门店所得金额
    private BigDecimal nurseprice;//家政员月工资
    private String fullName;//客户姓名
    private String tel;//客户手机号
    private String area_P;//工作地点（省）
    private String area_C;//工作地点（市）
    private String area_D;//工作地点（区）
    private String address;//工作地点
    private Integer jobTypeID;//工作类型
    private String smalljob;//岗位服务内容
    private Date workDate;//具体用工时间起
    private Integer workDate2;//时长
    private String readMe;//详细订单情况
    private String isHome;//是否需要陪护人员住家
    private String restWay;//休息方式
    private String restDay;//休息几天
    private String goWorkTimeStart;//上班时间点/上门时间
    private String goWorkTimeEnd;//下班时间点
    private String ziLiSex;//被照顾者性别
    private String ziLi;//自理能力
    private String babyAge;//宝宝年龄
    private String yuChanQi;//月嫂使用时长

}
