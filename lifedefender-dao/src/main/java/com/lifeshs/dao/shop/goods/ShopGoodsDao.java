package com.lifeshs.dao.shop.goods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.shop.GoodsDTO;

@Repository("shop_goods_dao")
public interface ShopGoodsDao {
	
	Map<String, Object> getOneById(@Param("id") Integer id);
	
	/**
	 * 插入商品
	 * @param goodsDTO
	 * @return
	 */
	int insertGoods(GoodsDTO goodsDTO);

	/**
	 * 商品列表
	 * @param goodsName
	 * @param shopName
	 * @param status
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> goodsListByCondition(@Param("shopId") Integer shopId,
			@Param(value = "goodsName") String goodsName,
            @Param(value = "shopName") String shopName,
            @Param(value = "userName") String userName,
            @Param(value = "status") Integer status,
            @Param("startRow") int startRow, 
            @Param("pageSize") int pageSize);
	
	
	int pagingGoodsTotal(@Param("shopId") Integer shopId, @Param(value = "goodsName") String goodsName,
            @Param(value = "shopName") String shopName,
            @Param(value = "userName") String userName,
            @Param(value = "status") Integer status);
	
	GoodsDTO getGoodsById(@Param("gid") Integer gid);
	
	int updateGoods(GoodsDTO goodsDTO);
	
	int changeGoodsStatus(@Param("id") Integer id, @Param("action") Integer action);
}
