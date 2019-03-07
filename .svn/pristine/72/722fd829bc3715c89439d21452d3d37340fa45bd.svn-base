package com.lifeshs.business.controller.accountsecurity;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.service1.business.UserService;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 账户安全
 * author: wenxian.cai
 * date: 2017/10/23 17:27
 */

@Controller
@RequestMapping("/account-security")
public class AccountSecurityController extends BaseController{

	Logger logger = Logger.getLogger(AccountSecurityController.class);

	@Autowired
	UserService userService;

	/**
	 * 账户安全界面
	 * author: wenxian.cai
	 * date: 2017/10/23 18:01
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView page () {
		return new ModelAndView("platform/accountsecurity/account-security");
	}

	/**
	 * 修改密码
	 * author: wenxian.cai
	 * date: 2017/10/23 18:01
	 */
	@RequestMapping(value = "/password", method = RequestMethod.PATCH)
	@ResponseBody
	public AjaxJson modifyPassword(@RequestBody Map<String, String> map) {
		AjaxJson ajaxJson = new AjaxJson();
		LoginUser user = getLoginUser();
		String oldPassword = map.get("oldPassword");
		String newPassword = map.get("newPassword");
		if (StringUtil.isBlank(oldPassword) || StringUtil.isBlank(newPassword)) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("请求参数不足");
			return ajaxJson;
		}
		if (newPassword.length() < 6) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("密码至少6位");
			return ajaxJson;
		}
		try {
			userService.updatePassword(user.getId(), oldPassword, newPassword);
			return ajaxJson;
		} catch (OperationException o) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(o.getMessage());
			logger.error("修改密码出错");
		}
		return ajaxJson;
	}
}
