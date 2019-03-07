package com.lifeshs.pojo.serve.consult;

import java.util.List;

import com.lifeshs.pojo.serve.PriceConditionDTO;
import com.lifeshs.pojo.serve.SortConditionDTO;

import lombok.Data;

public @Data class ConsultConditionDTO {

    /** 类型 */
    private List<String> type;
    /** 价格 */
    private List<PriceConditionDTO> price;
    /** 排序 */
    private List<SortConditionDTO> sort;
}
