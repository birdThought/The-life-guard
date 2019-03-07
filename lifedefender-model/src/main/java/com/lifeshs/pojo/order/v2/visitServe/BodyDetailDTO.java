package com.lifeshs.pojo.order.v2.visitServe;

import lombok.Data;

public @Data class BodyDetailDTO {
    /** 单项名字 */
    private String name;
    /** 门市(?)价格 */
    private String price;
    /** 规格 */
    private String unit;
}
