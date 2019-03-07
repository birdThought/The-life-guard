package com.lifeshs.customer.controller.dd;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.department.DepartmentService;
import com.lifeshs.vo.systemManage.DepartmentVo;

@RestController
@RequestMapping(value = "/data/department")
public class DepartmentController {

	static final Logger logger = Logger.getLogger(DepartmentController.class);

	static final int PAGESIZE = 50;

	@Autowired
	DepartmentService departmentService;

	/**
	 * 科室管理页面
	 * 
	 * @author shiqiang.zeng
	 * @date 2018.1.14 14:00
	 * @return
	 */
	@RequestMapping(value = "/page")

	private ModelAndView DepartmentManagePage() {
		return new ModelAndView("platform/systemmanage/department-manage");
	}

	@RequestMapping(value = "/list-department/{page}", method = RequestMethod.GET)
	public AjaxJsonV2 listDepartment(@PathVariable("page") int page) {
		Paging paging = departmentService.findDepartment(0, page, PAGESIZE);
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		ajaxJsonV2.setObj(paging.getPagination());
		return ajaxJsonV2;

	}

	/**
	 * 获取子科室
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/child", method = RequestMethod.GET)
	public AjaxJsonV2 getChildDepartment(@RequestParam("id") int id) {
		List<DepartmentVo> childDepartment = departmentService.getChildDepartmentById(id);
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		ajaxJsonV2.setObj(childDepartment);
		return ajaxJsonV2;
	}
	
	/**
	 * 添加科室
	 * @param departmentVo
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public AjaxJsonV2 addDepartment(DepartmentVo departmentVo) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		try {
			departmentService.addDepartment(departmentVo);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
		
	}
	
	/**
	 * 更改科室
	 * @param departmentVo
	 * @return
	 */
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public AjaxJsonV2 updateDepartment(DepartmentVo departmentVo) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		try {
			departmentService.updateDepartment(departmentVo);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}
	
	/**
	 * 删除科室
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method =RequestMethod.POST)
	public AjaxJsonV2 deleteDepartment(@RequestParam("id") Integer id) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		try {
			departmentService.deleteDepartment(id);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
		
	}
	

}
