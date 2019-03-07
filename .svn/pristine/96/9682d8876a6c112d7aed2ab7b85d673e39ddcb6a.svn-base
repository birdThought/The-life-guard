package com.lifeshs.dao.shop;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.shop.AdvertDTO;
import com.lifeshs.shop.GoodsDTO;
import com.lifeshs.shop.LabelDTO;
import com.lifeshs.shop.PageRecommendGoodsDTO;
/*
 * 商城的首页综合信息
 * Founder: jiang chang bin   2018/11/7 10:20
 */

@Repository(value = "commodityDao")
public interface CommodityDao {

	/*
	 *首页 获取分类标签(findClassfiyTags)
	 * Founder: jiang chang bin   2018/11/7 14:31
	 */
	List<LabelDTO> findClassfiyTags();
	
	/*
	 * 首页  获取分类商品(findClassfiyCommodity)
	 */
	//获取女性
	List<GoodsDTO> findClassfiyCommodityWoman();
	//获取男性
	List<GoodsDTO> findClassfiyCommodityMan();
	
	
	List<PageRecommendGoodsDTO> getRecommendCategory(@Param("rule") String rule, @Param("size") Integer size);

	int moveSortOrSetTime(@Param("id") Integer id, @Param("sort") Integer sort,@Param("endTime") Date endTime);
	
	int updateSortByCase(@Param("list") List<PageRecommendGoodsDTO> list);
	
	/**
	 * 商城首页置顶广告
	 * @param date
	 * @param userName
	 * @param shopName
	 * @param goodsName
	 * @return
	 * @author liu
	 * @时间 2018年12月20日 下午8:22:34
	 * @remark
	 */
	List<Map<String, Object>> selectAdvertPager(@Param("date") String date
			,@Param("userName") String userName
			,@Param("shopName") String shopName
			,@Param("goodsName") String goodsName
			,@Param("status") Integer status
			,@Param("advertType") Integer advertType
			,@Param("pageSize") Integer pageSize
			,@Param("startRow") Integer startRow);
	
	/**
	 * 商城首页置顶广告分页total
	 */
	int selectAdvertPagerTotal(@Param("date") String date
			,@Param("userName") String userName
			,@Param("shopName") String shopName
			,@Param("goodsName") String goodsName
			,@Param("status") Integer status
			,@Param("advertType") Integer advertType);
	
	/**
	 * 逻辑删除或使用
	 * @param id
	 * @param status
	 * @return
	 * @author liu
	 * @时间 2018年12月21日 上午10:48:02
	 * @remark
	 */
	int updateAdvertStatusById(@Param("id") Integer id, @Param("status") Integer status);
	
	/**
	 * 查询时间内的广告次数
	 * @param showTime
	 * @return
	 * @author liu
	 * @时间 2018年12月24日 下午2:46:09
	 * @remark
	 */
	List<AdvertDTO> checkNumForAdvertShowTime(@Param("showTime") String showTime);
	
	/**
	 * 添加商城首页置顶广告
	 * @param advert
	 * @return
	 * @author liu
	 * @时间 2018年12月24日 上午10:42:31
	 * @remark
	 */
	int addAdvert(AdvertDTO advert);
}
