package com.lifeshs.pojo.serve.lesson;

import lombok.Data;

/**
 *  推荐课堂
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月20日 上午9:57:14
 */
public @Data class RecommendedLessonDTO {

    /** 课堂 */
    private LessonProjectDTO lessonProject;
    /** 购买者数量 */
    private int buyerCount;
}
