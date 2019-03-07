package com.lifeshs.common.constants.common;

public class Constant {

    public static final String DEFAULT_USER_PWD = "123456";

    public static final String MD5 = "md5";
    
    //<!-- 套餐子项详细ID(链接文章的ID) -->
    public static final String COMBO_MANAGER_DETAIL = "http://lifekeepers.cn/app/appweb?comboDetail&id=";
    
    //<!-- 药品管理 -->
    public static final String JIANKE_ORDER_GET = "jianke.order.get";                           //单个订单查询
    public static final String JIANKE_ORDER_GETBATCHLIST = "jianke.order.getBatchList";         //订单批量查询
    public static final String JIANKE_ORDER_EXTERNALCREATE = "jianke.order.externalcreate";     //订单推送
    public static final String JIANKE_LOGISTICS_GET = "jianke.logistics.get";                   //物流信息
    public static final String JIANKE_PRODUCT_SYNC = "jianke.product.sync";                     //同步药品
    public static final String SYNC_DATA_NUMBER = "50";                                         //每次同步药品数量
    
    public static final Integer DRUGS_TRANSPORTCOSTS = 100;                                     //运费
    
}
