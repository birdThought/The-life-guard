package com.lifeshs.pojo.member.healthIndex;

import lombok.Data;

/**
 *  健康指数
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月7日 下午3:40:39
 */
public @Data class HealthIndexDTO {

    /** 分数详情 */
    private DetailDTO detail;
    /** 分数 */
    private int point;
    /** 评估日期 */
    private String evaluationDate;

    public void setPoint(int point) {
        if (point >= 100) {
            point = 100;
        }
        this.point = point <= 0 ? 0 : point;
    }
}
