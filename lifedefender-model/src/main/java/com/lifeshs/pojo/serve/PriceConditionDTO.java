package com.lifeshs.pojo.serve;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  价格条件
 *  @author yuhang.weng
 *  @version 2.4
 *  @DateTime 2017年6月23日 下午3:58:02
 */
public @Data class PriceConditionDTO {

    /** 显示名字 */
    private String name;
    /** 区间 */
    @JSONField(name = "priceArea")
    private String area;
}
