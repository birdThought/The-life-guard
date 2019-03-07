package com.lifeshs.service.shop.goods;

import java.util.List;
import java.util.Map;

import com.lifeshs.common.constants.common.shop.ShopConstants;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.shop.AttributeDTO;
import com.lifeshs.shop.AttributeValueDTO;
import com.lifeshs.shop.GoodsDTO;
import com.lifeshs.shop.RelationDTO;
import com.lifeshs.shop.SkuDTO;
import com.lifeshs.shop.SpecDTO;
import com.lifeshs.vo.shop.GoodsEditBean;

public interface ShopGoodsService {
	
	PaginationDTO<Map<String, Object>> getGoodsListPaging(Integer shopId,String goodsName,String shopName, String userName,Integer status,Integer page,Integer pageSize);
	
	Map<String, Object> toAddInit();
	
	GoodsEditBean getByGoodsId(Integer gid);
	
	Map<String, Object> changeAttrsByCategoryId(Integer shopId, Integer categoryId);
	
	int createAttrValue(AttributeValueDTO attrValue);
	
	/**
	 * 
	 * @param goods: 商品基本属性
	 * @param attributes 商品动态多属性
	 * @param relations 商品-属性关系
	 * @param specs 规格
	 * @param skus 库存&价格
	 * @throws OperationException
	 * @author liu
	 * @时间 2018年12月28日 下午1:47:10
	 * @remark 添加商品
	 */
	void saveGoods(GoodsDTO goods,List<AttributeDTO> attributes, List<RelationDTO> relations
			, List<SpecDTO> specs, List<SkuDTO> skus, String willRemoveAttrs) throws OperationException;
	
	/**
	 * 
	 * @param goodsDTO: 商品基本属性
	 * @param attributeDTOs 商品动态多属性
	 * @param relations 商品-属性关系
	 * @param specs 规格
	 * @param skus 库存&价格
	 * @throws OperationException
	 * @author liu
	 * @时间 2018年12月28日 下午1:47:10
	 * @remark 修改商品
	 */
	void updateGoods(GoodsDTO goodsDTO,List<AttributeDTO> newAttrs, List<RelationDTO> relations
			, List<SpecDTO> specs, List<SkuDTO> skus, String willRemoveAttrs) throws OperationException;
	
	/**
	 * 
	 * @param id
	 * @param action 执行动作
	 * @return
	 * @author liu
	 * @时间 2018年12月14日 上午10:23:37
	 * @remark 商品状态改变
	 */
	int changeStatus(Integer id, ShopConstants.GoodsStatus action);
	
	/**
	 * 商品动态属性
	 * @param gid
	 * @return
	 */
	Map<String, Object> getGoodsDetails(Integer gid);
}
