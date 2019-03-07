package com.lifeshs.dao.shop.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.shop.GoodsSpecDTO;
import com.lifeshs.shop.SpecDTO;
import com.lifeshs.shop.SpecValueDTO;

@Repository("shop_spec_dao")
public interface SpecDao {
	
	/**
	 * 通过规格id查询单个规格及其规格值
	 * @param id
	 * @return
	 * @author liu
	 * @时间 2019年1月8日 下午5:34:47
	 * @remark
	 */
	SpecDTO getOneById(@Param("id") Integer id);
	
	/**
	 * 通过商品id获取规格
	 * @param goodsId 
	 * @return
	 * @author liu
	 * @时间 2019年1月15日 上午10:12:31
	 * @remark 
	 */
	List<SpecDTO> selectByGoodsId(@Param("goodsId") Integer goodsId);
	
	int saveSpecValue(SpecValueDTO specValueDTO);
	
	/**
	 * 通过商品id查找规格format
	 * @param gid
	 * @return
	 * @author liu
	 * @时间 2019年1月15日 下午7:05:14
	 * @remark
	 */
	GoodsSpecDTO selectSpecFormatByGid(@Param("gid") Integer gid);
	
	/**
	 * 保存规格:t_shop_spec
	 * @param spec
	 * @return
	 * @author liu
	 * @时间 2018年12月28日 上午10:22:33
	 * @remark
	 */
	int saveSpec(SpecDTO spec);
	
	int batchInsertSpecValue(@Param("specValues") List<SpecValueDTO> specValues);
	
	/**
	 * 插入规格
	 * @param specs
	 * @param gid
	 * @return
	 */
	int insertGoodsSpecs(GoodsSpecDTO specs);
	
	/**
	 * 逻辑删除t_shop_goods_spec
	 * @param gid 商品id
	 * @return
	 * @author liu
	 * @时间 2019年1月17日 上午11:28:53
	 * @remark
	 */
	int busRemoveGoodsSpecByGid(@Param("gid") Integer gid);
	
	/**
	 * 通过商品删除规格以及关联的规格值
	 * @return
	 * @author liu
	 * @时间 2019年1月17日 上午11:21:03
	 * @remark
	 */
	int removeOldSpecsAndValuesByGid(@Param("gid") Integer gid);
	
	/**
	 * 修改规格
	 * @param specs
	 * @return
	 */
	int updateSpecs(GoodsSpecDTO specs);
}
