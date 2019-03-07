package com.lifeshs.customer.controller.business;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.business.BusinessUserPO;
import com.lifeshs.service1.business.UserService;
import com.lifeshs.service1.page.Paging;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 渠道商及用户控制器
 * author: wenxian.cai
 * date: 2017/10/20 11:12
 */

@Controller
@RequestMapping("/business")
public class BusinessUserController extends BaseController{

	final static Logger logger = Logger.getLogger(BusinessUserController.class);
	final static int BUEINESS_USER_PAGE_SIZE = 100;
	static final int BUSINESS_PAGE_SIZE=10;

	@Autowired
	private UserService userService;
	
	/**
	 * 渠道商管理页面
	 * autghor:shiqiang.zeng
	 * date: 2018/1/4
	 */
	@RequestMapping("/page")
	public ModelAndView BusinessUserControllerPage() {
		return new ModelAndView("platform/business/business");
	}
	
	/**
	 * 获取渠道商列表
	 * @param page
	 */
	@RequestMapping("/list/{page}")
	@ResponseBody
	public AjaxJson listBusiness(@PathVariable("page")int page) {
		AjaxJson ajaxJson=new AjaxJson();
		Paging paging=userService.listUser(page, BUSINESS_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}

	/**
	 * 获取渠道商用户列表
	 * author: wenxian.cai
	 * date: 2017/10/20 11:16
	 */
	@RequestMapping("/user/list")
	@ResponseBody
	public AjaxJson listBusinessUser() {
		AjaxJson ajaxJson = new AjaxJson();
		Paging paging = userService.listUser(1, BUEINESS_USER_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}

	/**
	 * 更改渠道商用户
	 */
	@RequestMapping(value="/user/edit",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson updateBusiness(@RequestParam("userName")String userName,@RequestParam("name")String name,@RequestParam("address")String address,
								   @RequestParam("phone")String phone,@RequestParam("email")String email, @RequestParam("id")Integer id) {
		AjaxJson ajaxJson=new AjaxJson();
		try {
			userService.updateBusiness(userName,name,address,phone,email,id);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJson.setMsg(o.getMessage());
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

}
