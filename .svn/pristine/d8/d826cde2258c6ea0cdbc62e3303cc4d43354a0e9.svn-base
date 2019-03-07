package com.lifeshs.pojo.member;

import lombok.Data;

/**
 * 健康指数
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月17日 下午2:33:46
 */
public @Data class HealthPointDTO {

    /** 分数详情 */
    private HealthPointDetailDTO detail;
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
