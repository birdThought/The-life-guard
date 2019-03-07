package com.lifeshs.dao.shop.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.shop.SkuDTO;

@Repository("shop_sku_dao")
public interface ShopSkuDao {
	
	/**
	 * 批量插入skus
	 * @param skus
	 * @param gid
	 * @return
	 */
	int batchInsertSkus(@Param("skus") List<SkuDTO> skus, @Param("gid") Integer gid);
	
	/**
	 * 通过商品id删除skus
	 * @param goodsId
	 * @return
	 */
	int removeSkusByGoodsId(@Param("goodsId") Integer goodsId);
	
	List<SkuDTO> selectByGid(@Param("gid") Integer gid);
}
