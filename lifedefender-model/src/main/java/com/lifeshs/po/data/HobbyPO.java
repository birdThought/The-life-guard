package com.lifeshs.po.data;

import java.util.Date;

import lombok.Data;

/**
 *  兴趣爱好
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月4日 上午9:42:27
 */
public @Data class HobbyPO {

    private int id;
    /** 名字 */
    private String name;
    /** 图片 */
    private String image;
    /** 创建日期 */
    private Date createDate;
    /** 修改日期 */
    private Date modifyDate;
    /** 是否已删除 */
    private Boolean deleted;
}
