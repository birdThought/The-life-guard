package com.lifeshs.pojo.serve;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  距离条件
 *  @author yuhang.weng
 *  @version 2.4
 *  @DateTime 2017年6月23日 下午4:38:31
 */
public @Data class DistanceConditionDTO {

    /** 距离区间 */
    @JSONField(name = "distanceArea")
    private String area;
    /** 显示名称 */
    private String name;
}
