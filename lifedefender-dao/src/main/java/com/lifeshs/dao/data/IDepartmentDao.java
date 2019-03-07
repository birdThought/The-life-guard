package com.lifeshs.dao.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.data.TDataDepartment;

/**
 *  <p> 部门DAO
 *  @author yuhang.weng  
 *  @DateTime 2016年8月13日 下午3:37:31
 */
@Repository("departmentDao")
public interface IDepartmentDao {

	/**
	 *  <p> 通过父类ID获取子类的部门数据(复数)
	 *  @author yuhang.weng 
	 *	@DateTime 2016年8月13日 下午3:42:01
	 *
	 *	@param parentDepartmentId
	 *  @return
	 */
	public List<TDataDepartment> selectChildDepartmentsDataByParentId(@Param("parentDepartmentId") Integer parentDepartmentId);
	
	/**
	 *  通过子类ID获取父类的部门数据（所有的父类部门集合）
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月3日 下午1:57:17
	 *
	 *  @param childDepartmentId
	 *  @return
	 */
	public List<TDataDepartment> selectParentsDepartmentByChildId(@Param("childDepartmentId") Integer childDepartmentId);
	
	/**
	 *  查找顶级父类部门对象
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月3日 上午10:59:39
	 *
	 *  @param childDepartmentId
	 *  @return
	 */
	public TDataDepartment selectTopDepartmentByChildId(@Param("childDepartmentId") Integer childDepartmentId);
	
	/**
	 *  获取所有的部门信息
	 *  @author yuhang.weng 
	 *	@DateTime 2016年12月12日 上午10:42:07
	 *
	 *  @return
	 */
	public List<TDataDepartment> selectAllDepartments();
}
