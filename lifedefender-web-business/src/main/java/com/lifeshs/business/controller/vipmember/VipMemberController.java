package com.lifeshs.business.controller.vipmember;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.vip.IVipUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * vip会员管理控制器
 * author: wenxian.cai
 * date: 2017/10/11 15:08
 */

@Controller
@RequestMapping("/vip-member")
public class VipMemberController extends BaseController{

	static final Logger logger = Logger.getLogger(VipMemberController.class);
	static final int VIP_MEMBER_PAGE_SIZE = 10;
	@Autowired
	IVipUserService vipUserService;

	/**
	 * vip会员管理界面
	 * author: wenxian.cai
	 * date: 2017/10/12 14:30
	 */
	@RequestMapping("/page")
	public ModelAndView vipMember() {
		return new ModelAndView("platform/vipmember/vip-member");
	}

	/**
	 * 渠道商获取vip会员
	 * author: wenxian.cai
	 * date: 2017/10/12 14:41
	 */
	@RequestMapping(value = "/vip-members/{page}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listVipMember(@PathVariable("page") int page, @RequestParam("isEndTime") Boolean isEndTime, @RequestParam("gender") Boolean gender,
								  @RequestParam("startAge") Integer startAge, @RequestParam("endAge") Integer endAge,
								  @RequestParam("vipComboId") Integer vipComboId, @RequestParam("status") Integer status,
								  @RequestParam("keyword") String keyword) {
		AjaxJson ajaxJson = new AjaxJson();
		LoginUser business = getLoginUser();
		Paging paging = vipUserService.listVipUserByBusiness(business.getId(), isEndTime, gender, startAge, endAge, vipComboId, status, keyword, page, VIP_MEMBER_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}


}
