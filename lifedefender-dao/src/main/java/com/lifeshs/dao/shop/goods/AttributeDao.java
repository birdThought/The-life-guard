package com.lifeshs.dao.shop.goods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.shop.AttributeDTO;
import com.lifeshs.shop.AttributeValueDTO;
import com.lifeshs.shop.RelationDTO;

@Repository("shop_attribute_dao")
public interface AttributeDao {
	
	List<AttributeDTO> getAttributeByCategoryAndShop(@Param(value = "status") Integer status
				,@Param(value = "categoryId") Integer categoryId // 类目
				,@Param(value = "shopId") Integer shopId	// 店铺id
			);
	
	List<AttributeDTO> getAttrsByCondition(@Param(value = "status") Integer status
			,@Param(value = "categoryId") Integer categoryId // 类目
			,@Param(value = "shopId") Integer shopId	// 店铺id
			,@Param("goodsId") Integer goodsId);
	
	int insertAttr(AttributeDTO attributeDTO);
	
	int insertAttrValue(AttributeValueDTO valueDTO);
	
	/**
	 * 
	 * @param attributes
	 * @return
	 */
	int batchInsertAttrValues(@Param("attributeValues") List<AttributeValueDTO> attributeValues);
	
	/**
	 * 批量插入属性与值的关系
	 * @param relations
	 * @param gid
	 * @return
	 */
	int batchInsertRelations(@Param("relations") List<RelationDTO> relations, @Param("gid") Integer gid);

	int removeRelationsByGoodsId(@Param("goodsId") Integer goodsId);
	
	int removeAttrsByIds(@Param("ids") String ids);
	
	/**
	 * 查询商品属性
	 * @param gid
	 * @return
	 */
	List<Map<String, Object>> selectAttrRelationByGoodsId(@Param("gid") Integer gid);
	
}
