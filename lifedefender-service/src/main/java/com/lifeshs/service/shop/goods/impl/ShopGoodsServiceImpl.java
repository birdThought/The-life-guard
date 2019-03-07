package com.lifeshs.service.shop.goods.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.lifeshs.common.constants.common.shop.ShopConstants;
import com.lifeshs.common.constants.common.shop.ShopConstants.GoodsSpecType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao.shop.CategoryDao;
import com.lifeshs.dao.shop.CommodityDao;
import com.lifeshs.dao.shop.goods.AttributeDao;
import com.lifeshs.dao.shop.goods.PictureDao;
import com.lifeshs.dao.shop.goods.ShopGoodsDao;
import com.lifeshs.dao.shop.goods.ShopLabelDao;
import com.lifeshs.dao.shop.goods.ShopSkuDao;
import com.lifeshs.dao.shop.goods.SpecDao;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.shop.goods.ShopGoodsService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.shop.AttributeDTO;
import com.lifeshs.shop.AttributeValueDTO;
import com.lifeshs.shop.GoodsDTO;
import com.lifeshs.shop.GoodsSpecDTO;
import com.lifeshs.shop.PageRecommendGoodsDTO;
import com.lifeshs.shop.PictureDTO;
import com.lifeshs.shop.RelationDTO;
import com.lifeshs.shop.SkuDTO;
import com.lifeshs.shop.SpecDTO;
import com.lifeshs.shop.SpecValueDTO;
import com.lifeshs.vo.shop.GoodsEditBean;

@Transactional
@Service("shop_goods_service")
public class ShopGoodsServiceImpl implements ShopGoodsService {
	
	@Autowired @Qualifier("shop_goods_dao")
	ShopGoodsDao shopGoodsDao;
	
	@Autowired @Qualifier("shop_label_dao")
	ShopLabelDao shopLabelDao;
	
	@Autowired @Qualifier("shop_attribute_dao")
	AttributeDao attributeDao;
	
	@Autowired @Qualifier("shop_sku_dao")
	ShopSkuDao skuDao;
	
	@Autowired @Qualifier("shop_spec_dao")
	SpecDao specDao;
	
	@Autowired @Qualifier("shop_category_dao")
	CategoryDao categoryDao;

	@Autowired @Qualifier("shop_picture_dao")
	PictureDao pictureDao;
	
	@Autowired
	private CommodityDao commodityDao;
	
	@Override
	public PaginationDTO<Map<String, Object>> getGoodsListPaging(Integer shopId, String goodsName, String shopName, String userName, Integer status,
			Integer page, Integer pageSize) {
		Paging<Map<String, Object>> paging = new Paging<>(page, pageSize);
		paging.setQueryProc(new IPagingQueryProc<Map<String,Object>>() {
			
			@Override
			public int queryTotal() {
				return shopGoodsDao.pagingGoodsTotal(shopId, goodsName, shopName, userName, status);
			}
			
			@Override
			public List<Map<String, Object>> queryData(int startRow, int pageSize) {
				
				return shopGoodsDao.goodsListByCondition(shopId, goodsName, shopName, userName, status, startRow, pageSize);
			}
		});
		PaginationDTO<Map<String, Object>> pDTO = paging.getPagination();
		pDTO.setPageSize(pageSize);
		return pDTO;
	}
	
	/**
	 * 添加商品页面初始化数据
	 * @param shopId 店铺id
	 * @param categoryId 类目id
	 * @param optional	是否是规格或属性
	 */
	@Override
	public Map<String, Object> toAddInit() {
		Map<String, Object> result = new HashMap<>();
		
		// 分组
//		List<LabelDTO> labels = shopLabelDao.findAllLabel();
//		result.put("labels", labels);
		List<PageRecommendGoodsDTO> pageGoods = commodityDao.getRecommendCategory("DESC", null);
		result.put("labels", pageGoods);
		// 固定规格
		List<SpecDTO> fixSpecs = specDao.selectByGoodsId(-1);
		result.put("fixSpecs", fixSpecs);
		
		// 类目 allCategory
		result.put("allCategory", categoryDao.getAllCategory());
		
		return result;
	}
	
	@Override
	public GoodsEditBean getByGoodsId(Integer gid) {
		GoodsEditBean goodsEdit = new GoodsEditBean();
		GoodsDTO goods = shopGoodsDao.getGoodsById(gid);
		goodsEdit.setGoods(goods);
		List<AttributeDTO> attributes = attributeDao.getAttrsByCondition(0, goods.getCategoryId(), goods.getShopId(), gid);
		goodsEdit.setAttributes(attributes);
		GoodsSpecDTO goodsSpecDTO = specDao.selectSpecFormatByGid(gid);
		if(goodsSpecDTO == null || StringUtils.isBlank(goodsSpecDTO.getSpecsFormat())) {
			// 固定规格
			List<SpecDTO> fixSpecs = specDao.selectByGoodsId(-1);
			goodsEdit.setSpecs(fixSpecs);
		} else {
			goodsEdit.setSpecs(JSON.parseArray(goodsSpecDTO.getSpecsFormat(), SpecDTO.class));
			List<SkuDTO> skus = skuDao.selectByGid(gid);
			goodsEdit.setSkus(skus);
		}
		// 分组
//		List<LabelDTO> labels = shopLabelDao.findAllLabel();
//		goodsEdit.setLabels(labels);
		List<PageRecommendGoodsDTO> pageGoods = commodityDao.getRecommendCategory("DESC", null);
		goodsEdit.setLabels(pageGoods);
		return goodsEdit;
	}
	
	@Override
	public Map<String, Object> changeAttrsByCategoryId(Integer shopId, Integer categoryId){
		Map<String, Object> result = new HashMap<>();
		List<AttributeDTO> attrs = attributeDao.getAttributeByCategoryAndShop(0, categoryId, shopId);
		// 商品属性
		result.put("attrs", attrs);
		return result;
	}
	
	@Override
	public int createAttrValue(AttributeValueDTO attrValue) {
		return attributeDao.insertAttrValue(attrValue);
	}
	
	@Override
	public void saveGoods(GoodsDTO goods,List<AttributeDTO> attributes, List<RelationDTO> relations
			, List<SpecDTO> specs, List<SkuDTO> skus, String willRemoveAttrs) throws OperationException {
		// step1:添加一个商品(基本属性信息),获取主键id
		if(GoodsSpecType.多规格.codeEq(goods.getSpecType()) && skus != null && !skus.isEmpty()) { // 如果是多规格,设置默认价格&库存
			SkuDTO defaultSku = skus.get(0);
			goods.setMarketPrice(defaultSku.getMarketPrice());
			goods.setFavorablePrice(defaultSku.getFavorablePrice());
			goods.setInventory(defaultSku.getInventory());
			goods.setBraCode(defaultSku.getBraCode());
		}
		int n = this.saveGoodsBasicAttrs(goods);
		if(n < 1) {
			throw new OperationException("添加商品失败!", ErrorCodeEnum.FAILED);
		}
		// step1.1:商品图片
		this.batchInsertPictures(goods);
		
		// step2:商品动态属性
		if(StringUtils.isNotBlank(willRemoveAttrs)) {
			this.removeAttrs(willRemoveAttrs);
		}
		this.saveDynamicAttrs(attributes, relations, goods);
		
		// step3:规格
		this.saveAboutSpec(specs, goods.getId());
		// step4:skus
		if(GoodsSpecType.统一规格.codeEq(goods.getSpecType())) {
			skus = new ArrayList<>();
			SkuDTO sku = new SkuDTO();
			sku.setMarketPrice(goods.getMarketPrice());
			sku.setFavorablePrice(goods.getFavorablePrice());
			sku.setInventory(goods.getInventory());
			sku.setBraCode(goods.getBraCode());
			skus.add(sku);
		}
		this.batchInsertSkus(skus, goods.getId());
	}
	
	/**
	 * step4:skus
	 */
	private int batchInsertSkus(List<SkuDTO> skus, Integer gid) {
		int n = 0;
		if(skus != null && !skus.isEmpty()) {
			n = skuDao.batchInsertSkus(skus, gid);
		}
		return n;
	}
	
	/**
	 * step3:规格
	 */
	private void saveAboutSpec(List<SpecDTO> specs, Integer gid) {
		if(specs != null && !specs.isEmpty()) {
			// 规格表:t_shop_spec&规格值表:t_shop_spec_value
			this.insertSpec(specs, gid);
			// t_shop_goods_spec
			this.createSpecsFormat(specs, gid);
		}
	}
	
	private void insertSpec(List<SpecDTO> specs, Integer gid) {
		for (SpecDTO spec : specs) {
			if(spec.getGoodsId() == null || spec.getGoodsId() != -1) { // 是否固定的(公用的)规格
				spec.setGoodsId(gid);
				specDao.saveSpec(spec);
				Integer specId = spec.getId();
				List<SpecValueDTO> specValues = spec.getSpecValues();
				for (SpecValueDTO specValue : specValues) {
					specValue.setSpecId(specId);
					specDao.saveSpecValue(specValue);
				}
			}
		}
	}
	
	/**
	 * step2:商品属性: 1:如果有新添加的属性,需要在`t_shop_attribute`和`t_shop_attribute_value`表添加;2:选中的都插入`t_shop_attribute_relation`表
	 * @param newAttrs
	 * @param relations
	 * @param gid
	 * @author liu
	 * @时间 2019年1月2日 上午9:36:51
	 * @remark
	 */
	private void saveDynamicAttrs(List<AttributeDTO> newAttrs, List<RelationDTO> relations
				, GoodsDTO goods) {
		if(relations == null) {
			relations = new ArrayList<>();
		}
		// step2.1:如果有新添加的属性,需要往`t_shop_attribute`和`t_shop_attribute_value`表添加
		// -插入属性-
		Date date = new Date();
		List<AttributeValueDTO> unselects = new ArrayList<>();
		for(AttributeDTO attributeDTO: newAttrs) {
			// 添加属性
			attributeDTO.setCid(goods.getCategoryId());
			attributeDTO.setShopId(goods.getShopId());
			attributeDTO.setCreateTime(date);
			attributeDao.insertAttr(attributeDTO);
			Integer attrId = attributeDTO.getId();
			List<AttributeValueDTO> attributeValues = attributeDTO.getAttributeValues();
			for (AttributeValueDTO attributeValueDTO : attributeValues) {
				attributeValueDTO.setAttributeId(attrId); // 设置属性id,与属性值关联
				if(attributeValueDTO.getSelected() == 1) { // 选中的属性值先插入数据库表,为了获取主键id
					attributeDao.insertAttrValue(attributeValueDTO);
					Integer valueId = attributeValueDTO.getId();
					if(valueId != null) {
						RelationDTO relation = new RelationDTO();
						relation.setAttributeId(attrId);
						relation.setAttributeVId(valueId);
						relations.add(relation);
					}
				} else {
					unselects.add(attributeValueDTO);
				}
			}
		}
		// -批量插入属性值(不选中的)-
		if(!unselects.isEmpty()) {
			attributeDao.batchInsertAttrValues(unselects);
		}
		// step2.2  选中的都插入`t_shop_attribute_relation`表
		if(!relations.isEmpty()) {
			attributeDao.batchInsertRelations(relations, goods.getId());
		}
	}
	
	private int removeAttrs(String willRemoveAttrs) {
		return attributeDao.removeAttrsByIds(willRemoveAttrs);
	}
	
	/**
	 * step1:添加一个商品(基本属性信息),获取主键id
	 * @param goodsDTO
	 * @return
	 */
	private int saveGoodsBasicAttrs(GoodsDTO goodsDTO) {
		goodsDTO.setCreateTime(new Date());
		int n = shopGoodsDao.insertGoods(goodsDTO);
		return n;
	}
	
	/**
	 * 批量插入商品图片
	 * @param goodsDTO
	 * @return
	 * @author liu
	 * @时间 2019年1月17日 上午11:31:23
	 * @remark
	 */
	private int batchInsertPictures(GoodsDTO goodsDTO) {
		// 商品图片
		Integer gid = goodsDTO.getId();
		Date date = new Date();
		List<PictureDTO> pictures = new ArrayList<>();
		// 主图
		String mainPic = goodsDTO.getMainPic();
		if(StringUtils.isNotBlank(mainPic)) {
			PictureDTO main = new PictureDTO();
			main.setGoodsId(gid);
			main.setMasterGraph(0);
			main.setPictureUrl(mainPic);
			main.setCreateTime(date);
			pictures.add(main);
		}
		// 其它图片
		String otherPics = goodsDTO.getOtherPics();
		if(StringUtils.isNotBlank(otherPics)) {
			String[] arr = otherPics.split("\\|");
			for (String pic : arr) {
				PictureDTO other = new PictureDTO();
				other.setGoodsId(gid);
				other.setMasterGraph(1);
				other.setPictureUrl(pic);
				other.setCreateTime(date);
				pictures.add(other);
			}
		}
		int n = 0;
		if(!pictures.isEmpty()) {
			n= pictureDao.batchInsertPictures(pictures);
		}
		return n;
	}
	
	/**
	 * 修改商品
	 * @param goodsDTO
	 * @param newAttrs
	 * @param relations
	 * @param specs
	 * @param skus
	 * @throws OperationException
	 */
	@Override
	public void updateGoods(GoodsDTO goods,List<AttributeDTO> newAttrs, List<RelationDTO> relations
			, List<SpecDTO> specs, List<SkuDTO> skus, String willRemoveAttrs) throws OperationException {
		if(GoodsSpecType.多规格.codeEq(goods.getSpecType()) && skus != null && !skus.isEmpty()) { // 如果是多规格,设置默认价格&库存
			SkuDTO defaultSku = skus.get(0);
			goods.setMarketPrice(defaultSku.getMarketPrice());
			goods.setFavorablePrice(defaultSku.getFavorablePrice());
			goods.setInventory(defaultSku.getInventory());
			goods.setBraCode(defaultSku.getBraCode());
		}
		goods.setStateTime(new Date());
		int n = shopGoodsDao.updateGoods(goods);
		if(n == 0) {
			throw new OperationException("更新出错", ErrorCodeEnum.FAILED);
		}
		// 更新图片
		this.updatePictures(goods);

		// 更新商品的动态属性
		if(StringUtils.isNotBlank(willRemoveAttrs)) {
			this.removeAttrs(willRemoveAttrs);
		}
		this.updateDynamicAttrs(newAttrs, relations, goods);
		
		//更新商品规格
//		if(GoodsSpecType.多规格.codeEq(goods.getSpecType())) {
			this.updateAboutSpec(specs, goods.getId());
//		}
		//更新skus
		if(GoodsSpecType.统一规格.codeEq(goods.getSpecType())) {
			skus = new ArrayList<>();
			SkuDTO sku = new SkuDTO();
			sku.setMarketPrice(goods.getMarketPrice());
			sku.setFavorablePrice(goods.getFavorablePrice());
			sku.setInventory(goods.getInventory());
			sku.setBraCode(goods.getBraCode());
			skus.add(sku);
		}
		this.updateSkus(skus, goods.getId());
	}
	
	/**
	 * 通过删除-插入更新商品图片
	 * @param goods
	 * @return
	 * @author liu
	 * @时间 2019年1月17日 上午11:34:18
	 * @remark
	 */
	private int updatePictures(GoodsDTO goods) {
		// 删除旧的
		pictureDao.removeByGid(goods.getId());
		//插入新的
		return this.batchInsertPictures(goods);
	}
	
	/**
	 * 更新属性
	 * @param newAttrs
	 * @param relations
	 * @param gid
	 * @author liu
	 * @时间 2018年12月28日 上午11:10:22
	 * @remark
	 */
	private void updateDynamicAttrs(List<AttributeDTO> newAttrs, List<RelationDTO> relations
			, GoodsDTO goods) {
		// 删除原来此商品, 关系表:t_shop_attribute_relation下的数据
		attributeDao.removeRelationsByGoodsId(goods.getId());
		// 插入新的
		this.saveDynamicAttrs(newAttrs, relations, goods);
	}
	
	private int createSpecsFormat(List<SpecDTO> specs, Integer gid) {
		int n = 0;
		if(specs != null && !specs.isEmpty()) {
			GoodsSpecDTO goodsSpec = new GoodsSpecDTO();
			goodsSpec.setGoodsId(gid);
			goodsSpec.setSpecsFormat(JSON.toJSONString(specs));
			goodsSpec.setDeleted(0);
			n  = specDao.insertGoodsSpecs(goodsSpec);
		}
		return n;
	}
	
	/**
	 * 更新规格相关
	 * @param specs
	 * @author liu
	 * @时间 2018年12月28日 上午10:51:50
	 * @remark
	 */
	private void updateAboutSpec(List<SpecDTO> specs, Integer gid) {
		// remove　old spec
		this.removeOldSpecs(gid);
		
		// create new goods_spec_format
		this.createSpecsFormat(specs, gid);
		
		// create new spec
		this.insertSpec(specs, gid);
	}
	
	/**
	 * 删除旧的规格
	 * @param gid
	 * @return
	 * @author liu
	 * @时间 2019年1月17日 上午11:29:35
	 * @remark
	 */
	private int removeOldSpecs(Integer gid) {
		specDao.busRemoveGoodsSpecByGid(gid);
		
		return specDao.removeOldSpecsAndValuesByGid(gid);
	}
	
	/**
	 * 更新skus
	 * @param skus
	 * @param gid
	 * @author liu
	 * @时间 2018年12月28日 上午11:09:04
	 * @remark
	 */
	private int updateSkus(List<SkuDTO> skus, Integer gid) {
		// remove old skus by gid
		skuDao.removeSkusByGoodsId(gid);
		// create new skus
		return this.batchInsertSkus(skus, gid);
	}
	
	public int changeStatus(Integer id, ShopConstants.GoodsStatus action) {
		if(null == action) {
			return 0;
		}
		return shopGoodsDao.changeGoodsStatus(id, action.getCode());
	}
	
	public Map<String, Object> getGoodsDetails(Integer gid) {
		Map<String, Object> goods = shopGoodsDao.getOneById(gid);
		List<Map<String,Object>> attributes = attributeDao.selectAttrRelationByGoodsId(gid);
		goods.put("attributes", attributes);
		return goods;
	}
}
