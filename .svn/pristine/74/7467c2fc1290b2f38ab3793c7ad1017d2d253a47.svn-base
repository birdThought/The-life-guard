package com.lifeshs.service1.systemManage.department;

import java.util.List;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.systemManage.DepartmentVo;

public interface DepartmentService {
	
	/**
	 * 获取科室列表
	 * @param parentId
	 * @return
	 */
	Paging<DepartmentVo> findDepartment(Integer parentId,int curPage,int pageSize);
	
	/**
	 * 根据id获取子科室
	 * @param id
	 * @return
	 */
	List<DepartmentVo> getChildDepartmentById(Integer id);
	
	/**
	 * 添加科室
	 * @param departmentVo
	 */
	void addDepartment(DepartmentVo departmentVo) throws OperationException;
	
	/**
	 * 更改科室
	 * @param departmentVo
	 */
	void updateDepartment(DepartmentVo departmentVo) throws OperationException;
	
	/**
	 * 删除科室
	 * @param id
	 */
	void deleteDepartment(Integer id) throws OperationException;
}
