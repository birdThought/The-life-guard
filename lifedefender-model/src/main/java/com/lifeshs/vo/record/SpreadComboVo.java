package com.lifeshs.vo.record;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @create 2018-04-16
 * 17:47   套餐推广记录vo
 * @desc
 */
public @Data class SpreadComboVo {
    private Integer id;
    private String realName;
    private String cuName;
    private String buName;
    private Integer price;
    private Integer status;
    private Date createDate;

}
