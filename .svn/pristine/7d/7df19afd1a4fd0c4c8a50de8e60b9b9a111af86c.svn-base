package com.lifeshs.dao1.systemManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.vo.systemManage.DepartmentVo;

@Repository("departmentManageDao")
public interface DepartmentManageDao {
	
	/**
	 * 根据父id获取科室信息(包括子科室)
	 * @param parentId 父栏目id
	 * @return
	 */
	List<DepartmentVo> findDepartment(@Param("parentId") Integer parentId,@Param("startRow")int startRow,@Param("pageSize")int pageSize);
	
	/**
	 * 统计科室数量
	 * @return
	 */
	int countDepartment(@Param("parentId") Integer parentId);
	/**
	 * 根据id获取子科室
	 * @param id
	 * @return
	 */
	List<DepartmentVo> getChildDepartmentById(@Param("id") Integer id);
	
	/**
	 * 增加科室
	 * @param departmentVo
	 */
	void addDepartment(DepartmentVo departmentVo);
	
	/**
	 * 更改科室
	 * @param departmentVo
	 * @param id
	 */
	void updateDepartment(DepartmentVo departmentVo);
	
	/**
	 * 删除科室
	 * @param id
	 */
	void deleteDepartment(@Param("id") Integer id);

}
