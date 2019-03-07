package com.lifeshs.customer.controller.combo;

import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.po.business.BusinessCardPO;
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
@RequestMapping("/combo/invite-code")
public class VipCardController extends BaseController{

	static final Logger logger = Logger.getLogger(VipCardController.class);
	static final int VIP_CARD_PAGE_SIZE = 10;
	static final int VIP_CARD_COMBO_PAGE_SIZE = 10;
	@Autowired
	BusinessCardService businessCardService;

	/**
	 * vip邀请码管理
	 * author: wenxian.cai
	 * date: 2017/10/11 15:23
	 */
	@RequestMapping("/page")
	private ModelAndView vipCardPage() {
		return new ModelAndView("platform/vipcard/vip-card");
	}

	/**
	 * 获取vip邀请码列表
	 * author: wenxian.cai
	 * date: 2017/10/11 15:23
	 */
	@RequestMapping("/list/{page}")
	@ResponseBody
	public AjaxJson listCard (@PathVariable("page") int page,
							   @RequestParam("cardStatus") int status,
							   @RequestParam("keyword") String keyword) {
		AjaxJson ajaxJson = new AjaxJson();
		Paging paging = businessCardService.listBusinessCard(null, keyword, status, page, VIP_CARD_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}

	/**
	 * 申请vip邀请码
	 * @param vipComboId
	 * @return
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson applyCard (@RequestParam("vipComboId") int vipComboId, @RequestParam("businessId") int businessId,
								@RequestParam("amount") int amount) {
		AjaxJson ajaxJson = new AjaxJson();
		BusinessCardPO po = new BusinessCardPO();
		po.setBusinessId(businessId);
		po.setVipComboId(vipComboId);
		try {
			businessCardService.addBusinessCardBatch(po, amount);
		} catch (OperationException o) {
			ajaxJson.setMsg(o.getMessage());
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

}
