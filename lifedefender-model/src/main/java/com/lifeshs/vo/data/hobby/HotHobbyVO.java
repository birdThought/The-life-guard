package com.lifeshs.vo.data.hobby;

import lombok.Data;

/**
 *  热门的兴趣爱好
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月4日 下午3:36:01
 */
public @Data class HotHobbyVO {

    /** 数量 */
    private int count;
    /** 兴趣爱好的id */
    private int hobbyId;
    /** 兴趣爱好的名字 */
    private String hobbyName;
}
