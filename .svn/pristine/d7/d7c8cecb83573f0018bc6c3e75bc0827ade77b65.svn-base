package com.lifeshs.service.shop;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.shop.AdvertDTO;
import com.lifeshs.shop.GoodsDTO;
import com.lifeshs.shop.LabelDTO;
import com.lifeshs.shop.PageRecommendGoodsDTO;

/*
 * 商城的首页综合信息
 * Founder: jiang chang bin   2018/11/7 10:20
 */

public interface CommodityService {

	/*
	 * app  首页 获取分类标签(findClassfiyTags)
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
	
	List<PageRecommendGoodsDTO> getRecommendCategory(String orderRule, Integer size);
	
	int moveSortOrSetTime(Integer id, Integer sort, Date endTime);
	
	int resetRecommendCategory(List<PageRecommendGoodsDTO> list);
	
	PaginationDTO<Map<String, Object>> getAdvertPagination(Integer page, Integer pageSize
			,final String date
			,final String userName
			,final String shopName
			,final String goodsName
			,Integer status
			,Integer advertType);
	
	/**
	 * 逻辑删除删除首页置顶广告
	 * @param id
	 * @return
	 * @author liu
	 * @时间 2018年12月21日 上午10:53:40
	 * @remark
	 */
	int deleteAdvertById(Integer id);
	
	/**
	 * 插入广告
	 * @param advert
	 * @return
	 * @author liu
	 * @时间 2018年12月24日 上午10:31:29
	 * @remark
	 */
	int createAdvert(AdvertDTO advert) throws OperationException;
}
