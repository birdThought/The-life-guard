package com.lifeshs.shop;


import lombok.Data;

/*
 * 类目表(t_shop_category)
 * Founder: jiang chang bin   2018/11/7 10:16
 * 
 */
public @Data class CategoryDTO {

	//编号
	private Integer id;
	
	//类别名称
	private String cName;
	
	//父级ID
	private Integer pid;
	
	//是否父级 0:是  1:不是
	private Integer parentnode;
	
	//优先级
	private String sort;
		
	// 标签
	private Integer labelId;
	
	private String idPath;
	
	private String labelName;
	
	private Integer deleted = 1;
	
}
