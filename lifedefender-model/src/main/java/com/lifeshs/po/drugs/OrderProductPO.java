package com.lifeshs.po.drugs;

import lombok.Data;

@Data
public class OrderProductPO {
    
    private int id; //id
    private String orderNo; //订单编号
    private String productCode;//产品编号
    private String productName;//产品名称
    private int actualPrice;//单品单价
    private int amount;//产品数量
    private String packing;//药品规格
    private String dosage; //用法用量
    private String thumbnailUrl; //图片
    
}
