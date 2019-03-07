package com.lifeshs.pojo.serve.lesson;

import java.util.List;

import com.lifeshs.pojo.serve.PriceConditionDTO;

import lombok.Data;

/**
 *  课堂条件
 *  @author yuhang.weng
 *  @version 2.4
 *  @DateTime 2017年6月23日 下午3:58:54
 */
public @Data class LessonConditionDTO {

    /** 类型 */
    private List<String> type;
    /** 价格 */
    private List<PriceConditionDTO> price;
}
