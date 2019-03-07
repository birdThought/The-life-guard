package com.lifeshs.pojo.serve.visit;

import java.util.List;

import com.lifeshs.pojo.serve.DistanceConditionDTO;
import com.lifeshs.pojo.serve.PriceConditionDTO;
import com.lifeshs.pojo.serve.SortConditionDTO;

import lombok.Data;

/**
 *  线下服务筛选条件
 *  @author yuhang.weng
 *  @version 2.4
 *  @DateTime 2017年6月23日 下午4:39:12
 */
public @Data class VisitConditionDTO {

    /** 类型 */
    private List<String> type;
    /** 价格 */
    private List<PriceConditionDTO> price;
    /** 距离 */
    private List<DistanceConditionDTO> distance;
    /** 排序 */
    private List<SortConditionDTO> sort;
}
