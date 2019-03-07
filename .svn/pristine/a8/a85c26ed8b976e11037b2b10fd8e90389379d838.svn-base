package com.lifeshs.service.shop.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.dao.shop.CategoryDao;
import com.lifeshs.dao.shop.goods.ShopLabelDao;
import com.lifeshs.service.shop.ShopClassifyService;
import com.lifeshs.shop.CategoryDTO;

@Transactional
@Service("shop_classify_service")
public class ShopClassifyServiceImpl implements ShopClassifyService{
	@Autowired @Qualifier("shop_category_dao")
	CategoryDao categoryDao;
	
	@Autowired @Qualifier("shop_label_dao")
	ShopLabelDao labelDao;
	
	@Override
	public List<CategoryDTO> getCategoryByPid(Integer pid) {
		return categoryDao.selectCategoryByPid(pid);
	}
	
	@Override
	public List<CategoryDTO> getAllCategory(){
		return categoryDao.getAllCategory();
	}

	@Override
	public int saveCategory(CategoryDTO category) {
		String idPath = category.getIdPath();
		category.setIdPath(null);
		int n = categoryDao.createCategory(category);
		if(n > 0) {
			CategoryDTO cate = new CategoryDTO();
			cate.setDeleted(null);
			cate.setId(category.getId());
			cate.setIdPath(idPath + category.getId() + ",");
			categoryDao.updateCategory(cate);
			category.setIdPath(cate.getIdPath());
		}
		return n;
	}

	@Override
	public int removeCategoryByIdPath_R(String idPath) {
		return categoryDao.removeByIdPath(idPath);
	}

	@Override
	public int updateCategory(CategoryDTO category) {
		return categoryDao.updateCategory(category);
	}
}
