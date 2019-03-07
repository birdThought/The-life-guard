package com.lifeshs.pojo.paper;

import lombok.Data;

/*
 * 中风风险测试结果分析
 * date: 2017/8/7 17:25
 */
public @Data class PaperStrokeStandardDTO {

    /** 主键 */
    private int id;

    /** 基础描述 */
    private String basicDescription;

    /** 建议 */
    private String suggestion;
}
