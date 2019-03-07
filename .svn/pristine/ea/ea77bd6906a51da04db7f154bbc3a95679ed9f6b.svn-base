package com.lifeshs.dao.shop;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.shop.CategoryDTO;

@Repository("shop_category_dao")
public interface CategoryDao {

	List<CategoryDTO> selectCategoryByPid(@Param("pid") Integer pid);

	List<CategoryDTO> getAllCategory();
	
	CategoryDTO getById(@Param("id") Integer id);
	
	int createCategory(CategoryDTO category);
	
	int removeById(Integer id);
	
	int updateCategory(CategoryDTO category);
	
	int removeByIdPath(String idPath);
}
