package com.lifeshs.business.controller.vipmember;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.service1.order.vip.VipUserOrderService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.vip.IVipUserService;
import com.lifeshs.vo.order.vip.BusinessOrderBillVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * 结算管理
 * author: wenxian.cai
 * date: 2017/10/11 15:12
 */
@Controller
@RequestMapping("/vip-member/financial")
public class FinancialController extends BaseController{

	final static Logger logger = Logger.getLogger(FinancialController.class);
	final static int ORDER_PAGE_SIZE = 10;

	@Autowired
	VipUserOrderService vipUserOrderService;

	/**
	 * 会员结算界面
	 * author: wenxian.cai
	 * date: 2017/10/31 10:05
	 */
	@RequestMapping("/page")
	private ModelAndView page() {
		return new ModelAndView("platform/vipmember/financial");
	}

	/**
	 * 获取订单列表
	 * author: wenxian.cai
	 * date: 2017/11/1 14:31
	 */
	@RequestMapping(value = "/orders/{page}", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJson listOrders (@PathVariable("page") int page, @RequestParam("month") String month) {
		AjaxJson ajaxJson = new AjaxJson();
		page = page > 0 ? page : 1;
		int businessId = getLoginUser().getId();
		LocalDate localDate = LocalDate.parse(month, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate start =  localDate.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate end =  localDate.with(TemporalAdjusters.lastDayOfMonth());
		Paging paging = vipUserOrderService.listOrder(businessId, start.toString(), end.toString(), page, ORDER_PAGE_SIZE);

		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}

	/**
	 * 获取渠道商总账单
	 * author: wenxian.cai
	 * date: 2017/11/1 16:46
	 */
	@RequestMapping(value = "/bill", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJson getBill (@RequestParam("month") String month) {
		AjaxJson ajaxJson = new AjaxJson();
		int businessId = getLoginUser().getId();
		LocalDate localDate = LocalDate.parse(month, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate start =  localDate.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate end =  localDate.with(TemporalAdjusters.lastDayOfMonth());
		BusinessOrderBillVO vo = vipUserOrderService.getBusinessOrderBill(businessId, start.toString(), end.toString());

		ajaxJson.setObj(vo);
		return ajaxJson;
	}
}
