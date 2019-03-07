package com.lifeshs.service.shop;

import java.util.List;

import com.lifeshs.shop.CategoryDTO;

public interface ShopClassifyService {
	
	List<CategoryDTO> getCategoryByPid(Integer pid);
	
	List<CategoryDTO> getAllCategory();
	
	int saveCategory(CategoryDTO category);
	
	/**
	 * (逻辑)删除类目及其无限下级
	 * @param idPath
	 * @return
	 * @author liu
	 * @时间 2018年12月28日 下午2:26:15
	 * @remark
	 */
	int removeCategoryByIdPath_R(String idPath);
	
	int updateCategory(CategoryDTO category);
	
}
