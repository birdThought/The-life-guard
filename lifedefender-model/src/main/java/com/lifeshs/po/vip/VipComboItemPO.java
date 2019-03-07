package com.lifeshs.po.vip;

import java.util.Date;

import lombok.Data;

/**
 *  vip套餐详细项目
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月18日 下午2:30:36
 */
@Data
public class VipComboItemPO {

    private Integer id;
    /** 名字 */
    private String name;
    /** 创建日期 */
    private Date createDate;
    /** 修改日期 */
    private Date modifyDate;
    /** 备注 */
    private String remark;
    /** 图标路径 */
    private String icon;
    /** 详细描述 */
    private String itemDetail;
    /** default number */
    private Integer number;
    /** 类别 1.预约地址栏 2.服务师列表 */
    private int type;
}
