package com.lifeshs.customer.controller.combo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.customer.UpdateOrderPo;
import com.lifeshs.service1.customer.CustomerOrder1Service;
import com.lifeshs.service1.page.Paging;


/**
 * 客服工单
 * @author shiqiang.zeng
 *
 */

@Controller
@RequestMapping("/combo/member/worklist")
public class WorkListController extends BaseController {
	
	static final Logger logger = Logger.getLogger(WorkListController.class);
	static final int CUSTOMER_ORDER_PAGE_SIZE = 10;
	@Autowired
	CustomerOrder1Service customerOrder1Service;
	
	/**
	 * 客服工单页面
	 */
	@RequestMapping("/page")
	private ModelAndView CustomerControllerPage() {
		return new ModelAndView("platform/customerorder/customer-order");
	}
	
	/**
	 * 获取客服工单列表
	 * @param page
	 */
	@RequestMapping("/data/list/all/{page}")
	@ResponseBody
	public AjaxJson listOrder(@PathVariable("page") int page) {
	AjaxJson ajaxJson=new AjaxJson();
    Paging paging=customerOrder1Service.findOrderList(page, CUSTOMER_ORDER_PAGE_SIZE);
    ajaxJson.setObj(paging.getPagination());
	return ajaxJson;
	}
	
	/**
	 * 获取未提交的客服工单列表
	 * @param page
	 */
	@RequestMapping("/data/list/untreated/{page}")
	@ResponseBody
	public AjaxJson listNotOrder(@PathVariable("page") int page) {
		AjaxJson ajaxJson=new AjaxJson();
		Paging paging=customerOrder1Service.findNotOrderList(0, page, CUSTOMER_ORDER_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}
	
	/**
	 * 获取提交成功的客服工单列表
	 * @Param page
	 */
	@RequestMapping("/data/list/success/{page}")
	@ResponseBody
	public AjaxJson listSuccessOrder(@PathVariable("page") int page) {
		AjaxJson ajaxJson=new AjaxJson();
		Paging paging =customerOrder1Service.findSuccessList(1, page, CUSTOMER_ORDER_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}
	 
	/**
	 * 获取提交失败的客服工单列表
	 * @Param page
	 */
	@RequestMapping("/data/list/fail/{page}")
	@ResponseBody
	public AjaxJson listFailOrder(@PathVariable("page") int page) {
		AjaxJson ajaxJson=new AjaxJson();
		Paging paging =customerOrder1Service.findFailList(2, page, CUSTOMER_ORDER_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}
	
	/**
	 * 客服填写工单
	 * @param id
	 */
	@RequestMapping(value="finish",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson finshOrder(@RequestParam(value="id") int id, UpdateOrderPo updateOrderPo) {
		AjaxJson ajaxJson=new AjaxJson();
		try {
			customerOrder1Service.updateOrder(id, updateOrderPo);	
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJson.setMsg("操作失败");
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
		
	}
	
	@RequestMapping("/finish/page")
	private ModelAndView completePage() {
		return new ModelAndView("platform/customerorder/complete-customer");
	}
}
