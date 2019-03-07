package com.lifeshs.business.controller.vipmember;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.po.business.BusinessCardPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.service1.business.BusinessCardService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.vip.IVipComboService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 邀请码管理
 * author: wenxian.cai
 * date: 2017/10/11 15:10
 */
@Controller
@RequestMapping("/vip-card")
public class VipCardController extends BaseController{

	static final Logger logger = Logger.getLogger(VipCardController.class);
	static final int VIP_CARD_PAGE_SIZE = 10;
	@Autowired
	BusinessCardService businessCardService;
	@Autowired
	IVipComboService vipComboService;

	/**
	 * vip邀请码管理
	 * author: wenxian.cai
	 * date: 2017/10/11 15:23
	 */
	@RequestMapping("/page")
	private ModelAndView vipCardPage() {
		return new ModelAndView("platform/vipmember/vip-card");
	}

	/**
	 * 获取渠道商的vip邀请码列表
	 * author: wenxian.cai
	 * date: 2017/10/11 15:23
	 */
	@RequestMapping(value = "/cards/{page}",method = RequestMethod.GET)
	@ResponseBody
	private AjaxJson listCard (@PathVariable("page") int page,
							   @RequestParam("cardStatus") int status,
							   @RequestParam("keyword") String keyword) {
		AjaxJson ajaxJson = new AjaxJson();
		LoginUser business = getLoginUser();
		Paging paging = businessCardService.listBusinessCard(business.getId(), keyword, status, page, VIP_CARD_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}

	/**
	 * 渠道商申请vip邀请码
	 * @param vipComboId
	 * @return
	 */
	@RequestMapping(value = "/card", method = RequestMethod.POST)
	@ResponseBody
	private AjaxJson applyCard (@RequestParam("vipComboId") int vipComboId) {
		AjaxJson ajaxJson = new AjaxJson();
		LoginUser business = getLoginUser();
		BusinessCardPO po = new BusinessCardPO();
		po.setBusinessId(business.getId());
		po.setVipComboId(vipComboId);
		try {
			businessCardService.addBusinessCard(po);
		} catch (OperationException o) {
			ajaxJson.setMsg(o.getMessage());
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

	/**
	 * 获取vip套餐列表
	 * author: wenxian.cai
	 * date: 2017/10/11 16:42
	 */
	@RequestMapping(value = "/vip-combo", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJson listVipCombo () {
		AjaxJson ajaxJson = new AjaxJson();
		List list = vipComboService.findVipComboList(null);
		ajaxJson.setObj(list);
		return ajaxJson;
	}
}
