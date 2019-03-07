package com.lifeshs.customer.controller.account;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.customer.security.realm.UserRealm;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.vo.customer.CustomerSharingDataVO;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * 客服人员信息控制器
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
		CustomerSharingDataVO user = getUserSharingData();
		ajaxJson.setObj(user);
		return ajaxJson;
	}

}
