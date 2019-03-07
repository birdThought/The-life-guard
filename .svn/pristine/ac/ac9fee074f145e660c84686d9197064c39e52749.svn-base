
package com.lifeshs.customer.controller.visitorLogs;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.visitorLog.VisitorLogService;


/**
 * 访问量数据 子菜单 
 * 用户登录
 * 
 * @author shiqinag.zeng
 * @Date 2018.1.17 15:50
 */

@Controller
@RequestMapping("/pv/visit")
public class VisitUserLogController extends BaseController {

	Logger logger = Logger.getLogger(VisitUserLogController.class);
	static final int VISIT_USER_LOG_PAGE_SIZE = 10;
	
	@Autowired
	VisitorLogService visitorLogService;

	

	
   /**
	 * 用户登录记录页面 
	 * author : shiqiang.zeng 
	 * date : 2018.1.17 15:57
	 */

	@RequestMapping("/page")
	private ModelAndView visitUserLogPage() {
		return new ModelAndView("platform/visitorLog/visit-userLog");

	}
	
	@RequestMapping("/list/{page}")
	@ResponseBody
	public AjaxJson listUserLog(@PathVariable("page") int page,
								 @RequestParam(value = "userType", required = false) Integer userType,
								 @RequestParam(value = "terminalType", required = false) String terminalType) {
		AjaxJson ajaxJson = new AjaxJson();
		Paging paging =visitorLogService.findUserList(null, userType, terminalType, page, VISIT_USER_LOG_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
		
	}
}

