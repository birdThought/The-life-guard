package com.lifeshs.service.shop.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.shop.ShopConstants.AdvertStatus;
import com.lifeshs.common.constants.common.shop.ShopConstants.AdvertType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao.shop.CommodityDao;
import com.lifeshs.dao.shop.ShopDao;
import com.lifeshs.dao.shop.goods.ShopGoodsDao;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.shop.CommodityService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.shop.AdvertDTO;
import com.lifeshs.shop.GoodsDTO;
import com.lifeshs.shop.LabelDTO;
import com.lifeshs.shop.PageRecommendGoodsDTO;
/*
 * 
 */
@Service @Transactional
public class CommodityServiceImpl implements CommodityService {
	
	public static int maxAdvertNum = 5;//限制广告数量

	@Autowired
	private CommodityDao commodityDao;
	
	@Autowired @Qualifier("shop_dao")
	private ShopDao shopDao;
	
	@Autowired @Qualifier("shop_goods_dao")
	ShopGoodsDao goodsDao;
	
	@Override
	public List<LabelDTO> findClassfiyTags() {
		//
		return commodityDao.findClassfiyTags();
	}

	@Override
	public List<GoodsDTO> findClassfiyCommodityWoman() {
		// TODO Auto-generated method stub
		return commodityDao.findClassfiyCommodityWoman();
	}

	@Override
	public List<GoodsDTO> findClassfiyCommodityMan() {
		// TODO Auto-generated method stub
		return commodityDao.findClassfiyCommodityMan();
	}

	@Override
	public List<PageRecommendGoodsDTO> getRecommendCategory(String orderRule, Integer size) {
		return commodityDao.getRecommendCategory(orderRule, size);
	}
	
	@Override
	public int moveSortOrSetTime(Integer id, Integer sort, Date endTime) {
		return commodityDao.moveSortOrSetTime(id, sort, endTime);
	}
	
	@Override
	public int resetRecommendCategory(List<PageRecommendGoodsDTO> list) {
		return commodityDao.updateSortByCase(list);
	}
	
	@Override
	public PaginationDTO<Map<String, Object>> getAdvertPagination(Integer page, Integer pageSize
			,final String date
			,final String userName
			,final String shopName
			,final String goodsName
			,final Integer status
			,final Integer advertType){
		Paging<Map<String, Object>> paging = new Paging<>(page, pageSize);
		paging.setQueryProc(new IPagingQueryProc<Map<String,Object>>() {
			
			@Override
			public int queryTotal() {
				return commodityDao.selectAdvertPagerTotal(date, userName, shopName, goodsName, status, advertType);
			}
			
			@Override
			public List<Map<String, Object>> queryData(int startRow, int pageSize) {
				return commodityDao.selectAdvertPager(date, userName, shopName, goodsName, status,advertType, pageSize, startRow);
			}
		});
		PaginationDTO<Map<String, Object>> pDto =  paging.getPagination();
		pDto.setPageSize(pageSize);
		return pDto;
	}
	
	@Override
	public int deleteAdvertById(Integer id) {
		return commodityDao.updateAdvertStatusById(id, AdvertStatus.删除.getCode());
	}
	
	@Override
	public int createAdvert(AdvertDTO advert) throws OperationException {
		// 检查广告数量是否超过设定数量
		List<AdvertDTO> adverts = commodityDao.checkNumForAdvertShowTime(advert.getShowTime());
		if(adverts != null && adverts.size() >= maxAdvertNum) {
			throw new OperationException("[" + advert.getShowTime() + "] 广告数超过" + maxAdvertNum + "个", ErrorCodeEnum.NOT_COMPLETE);
		}
		for (AdvertDTO advertDTO : adverts) {
			if(advertDTO.getGoodsId() == advert.getGoodsId()) {
				throw new OperationException("这天已有此商品的广告", ErrorCodeEnum.NOT_COMPLETE);
			}
		}
		Integer type = advert.getAdvertType();
		if(AdvertType.商铺广告.codeEq(type)) {
			Integer shopId = advert.getShopId();
			Map<String, Object> shop = shopDao.getOneById(shopId);
			advert.setUserId((Integer)shop.get("user_id"));
		} else if(AdvertType.商品广告.codeEq(type)) {
			Map<String, Object> goods = goodsDao.getOneById(advert.getGoodsId());
			advert.setUserId((Integer)goods.get("userId"));
			advert.setShopId((Integer)goods.get("shopId"));
		} else {
			throw new OperationException("广告类型不正确", ErrorCodeEnum.SERVE);
		}
		advert.setStatus(AdvertStatus.使用.getCode());
		advert.setSort(1);
		advert.setCreateTime(new Date());
		int n = commodityDao.addAdvert(advert);
		return n;
	}
}
