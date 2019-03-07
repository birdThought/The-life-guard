package com.lifeshs.vo.vip;

import java.util.Date;

import lombok.Data;

/**
 *  套餐关系表
 *  @author 
 *  @version 1.0
 *  @DateTime 2017年10月18日 下午2:31:27
 */
public @Data class VipComboItemRelationVO {

   private Integer id;
   private Integer vipComboId;
   private Integer vipComboItemId;
   private Date createDate;
   private Integer number;
}
