package com.lifeshs.pojo.member.healthIndex;

import lombok.Data;

/**
 *  健康指数分数详情
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月7日 下午3:40:16
 */
public @Data class DetailDTO {

    /** 个人基本数据 */
    private int basic;
    /** 基本生命体征 */
    private int vitalSign;
    /** 日常测量 */
    private int measure;
    /** 体制辩证 */
    private int physique;
    /** 健康状态 */
    private int healthStatus;
}
