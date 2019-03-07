package com.lifeshs.po.shop;

import java.util.List;

import com.lifeshs.shop.PictureDTO;
import com.lifeshs.shop.SkuDTO;

import lombok.Data;

public @Data class SkuItem extends SkuDTO {
	
	private Integer shopId;
	private String shopName;
	private String goodsName;  //商品名称
	private List<PictureDTO> pictureUrls;   //商品图片
	private String details;    //商品详情
	private String Instructions;  //说明书	
	private Integer num; //购买数量
	
	
}
