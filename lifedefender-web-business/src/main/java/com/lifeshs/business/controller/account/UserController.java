package com.lifeshs.business.controller.account;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.vo.business.BusinessSharingDataVO;

/**
 * 渠道商信息控制器
 * author: wenxian.cai
 * date: 2017/10/23 11:45
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	CustomerUserService customerUserService;

	@RequestMapping(value = "/info",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getUserInfo() {
		AjaxJson ajaxJson = new AjaxJson();
		BusinessSharingDataVO user = getUserSharingData();
		ajaxJson.setObj(user);
		if (user == null) {
		    ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

}
