package com.lifeshs.pojo.order.v2.visitServe;

import java.util.List;

import lombok.Data;

public @Data class BodyDTO {

    /** 套餐id */
    private Integer id;
    /** 套餐名字 */
    private String name;
    /** 套餐详情 */
    private List<BodyDetailDTO> detail;
}
