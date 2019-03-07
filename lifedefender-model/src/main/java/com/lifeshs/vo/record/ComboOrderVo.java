package com.lifeshs.vo.record;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @create 2018-04-18
 * 10:13   结算详细
 * @desc
 */
public @Data class ComboOrderVo {
    private String realName;
    private String name;
    private String buName;
    private Integer price;
    private Integer status;
    private Date createDate;


}
