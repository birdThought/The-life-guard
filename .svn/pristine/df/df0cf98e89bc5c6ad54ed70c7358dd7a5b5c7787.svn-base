package com.lifeshs.service1.systemManage.department.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.systemManage.DepartmentManageDao;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.department.DepartmentService;
import com.lifeshs.vo.systemManage.DepartmentVo;

@Service(value = "departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Resource(name = "departmentManageDao")
	DepartmentManageDao departmentManageDao;

	@Override
	public List<DepartmentVo> getChildDepartmentById(Integer id) {
		// TODO Auto-generated method stub
		return departmentManageDao.getChildDepartmentById(id);
	}

	@Override
	public Paging<DepartmentVo> findDepartment(Integer parentId, int curPage, int pageSize) {

		Paging<DepartmentVo> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<DepartmentVo>() {

			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return departmentManageDao.countDepartment(parentId);
			}

			@Override
			public List<DepartmentVo> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return departmentManageDao.findDepartment(parentId, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public void addDepartment(DepartmentVo departmentVo) throws OperationException {

		try {
			departmentManageDao.addDepartment(departmentVo);
		} catch (Exception e) {
			throw new OperationException("科室添加失败", ErrorCodeEnum.FAILED);
		}
	}

	@Override
	public void updateDepartment(DepartmentVo departmentVo) throws OperationException {

		try {
			departmentManageDao.updateDepartment(departmentVo);
		} catch (Exception e) {
			throw new OperationException("科室更新失败", ErrorCodeEnum.FAILED);
		}

	}

	@Override
	public void deleteDepartment(Integer id) throws OperationException {

		try {
			departmentManageDao.deleteDepartment(id);
		} catch (Exception e) {
			throw new OperationException("科室删除失败", ErrorCodeEnum.FAILED);
		}

	}

}
