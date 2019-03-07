package com.lifeshs.vo.systemManage;

import java.util.List;

import com.lifeshs.entity.data.TDataDepartment;

import lombok.Data;

/**
 * 科室管理Vo
 * @author shiqing.zeng
 * @date 2018.1.24 10:58
 */
@Data
public class DepartmentVo extends TDataDepartment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TDataDepartment> itemList;

}
