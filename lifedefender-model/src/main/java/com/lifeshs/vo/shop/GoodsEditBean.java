package com.lifeshs.vo.shop;

import java.util.List;

import com.lifeshs.shop.AttributeDTO;
import com.lifeshs.shop.GoodsDTO;
import com.lifeshs.shop.PageRecommendGoodsDTO;
import com.lifeshs.shop.RelationDTO;
import com.lifeshs.shop.SkuDTO;
import com.lifeshs.shop.SpecDTO;

public class GoodsEditBean {
	private GoodsDTO goods;
	
	private List<AttributeDTO> attributes;
	
	private List<RelationDTO> relations;
	
	private List<SpecDTO> specs;
	
	private List<SkuDTO> skus;
	
	private List<PageRecommendGoodsDTO> labels; //t_shop_page_goods
	
	private String willRemoveAttrs;

	public GoodsDTO getGoods() {
		return goods;
	}

	public void setGoods(GoodsDTO goods) {
		this.goods = goods;
	}

	public List<AttributeDTO> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeDTO> attributes) {
		this.attributes = attributes;
	}

	public List<RelationDTO> getRelations() {
		return relations;
	}

	public void setRelations(List<RelationDTO> relations) {
		this.relations = relations;
	}

	public List<SpecDTO> getSpecs() {
		return specs;
	}

	public void setSpecs(List<SpecDTO> specs) {
		this.specs = specs;
	}

	public List<SkuDTO> getSkus() {
		return skus;
	}

	public void setSkus(List<SkuDTO> skus) {
		this.skus = skus;
	}

	public List<PageRecommendGoodsDTO> getLabels() {
		return labels;
	}

	public void setLabels(List<PageRecommendGoodsDTO> labels) {
		this.labels = labels;
	}

	public String getWillRemoveAttrs() {
		return willRemoveAttrs;
	}

	public void setWillRemoveAttrs(String willRemoveAttrs) {
		this.willRemoveAttrs = willRemoveAttrs;
	}
	
}
