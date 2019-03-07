package com.lifeshs.shop;

import java.util.Date;

import lombok.Data;

/*
 * 商品图片表(t_shop_picture)
 * Founder: jiang chang bin   2018/11/7 10:49
 */
public @Data class PictureDTO {

	        //编号
			private Integer id;

			//商品ID
			private Integer goodsId;
					
			//图片地址
			private String pictureUrl;
			
			//是否主图
			private Integer masterGraph;
			
			//操作人ID
			private Integer operatorId;
			
			//创建时间
			private Date createTime;
	
}
