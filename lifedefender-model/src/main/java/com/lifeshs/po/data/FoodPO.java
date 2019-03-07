package com.lifeshs.po.data;

import lombok.Data;

/**
 *  食物
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月13日 上午11:38:36
 */
@Data
public class FoodPO {

    private Integer id;
    /** 名称 */
    private String name;
    /** 食物类型 */
    private Integer kind;
    /** 大卡（单位为50g） */
    private Float kcal;
    /** 图片 */
    private String image;
}
