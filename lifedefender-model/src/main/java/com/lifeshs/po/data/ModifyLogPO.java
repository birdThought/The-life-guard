package com.lifeshs.po.data;

import java.util.Date;

import lombok.Data;

/**
 *  字典更新记录
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月22日 下午4:32:39
 */
public @Data class ModifyLogPO {

    private Integer id;
    /** 表名 */
    private String tableName;
    /** 最新更新日期 */
    private Date modifyDate;
}
