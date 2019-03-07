package com.lifeshs.customer.controller.shop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.common.AgentType;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.service.shop.ShopOrderService;
import com.lifeshs.vo.customer.CustomerSharingDataVO;

/**
 * 
 * @author liu
 * 订单管理
 */
@RequestMapping("/commodity/order")
@Controller
public class ShopOrderController extends BaseController {
	
	@Autowired @Qualifier("shop_order_service")
	ShopOrderService orderService;

	private final static int PAGE_SIZE = 10;
	
	@RequestMapping("/page")
    public ModelAndView toList(){
        ModelAndView mvc = new ModelAndView("platform/shop/orderList");
        return mvc;
    }
	
	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getOrderList(@PathVariable(name = "page", required = false) Integer page
			, @RequestParam(name = "pageSize", required = false, defaultValue = PAGE_SIZE + "") Integer pageSize
			, @RequestParam(name = "shopName", required = false) String shopName
			, @RequestParam(name = "userName", required = false) String userName // 下单人
			, @RequestParam(name = "mobile", required = false) String mobile // 下单人
			, @RequestParam(name = "orderNo", required = false) String orderNo
			, @RequestParam(name = "status", required = false) Integer status) {
		AjaxJson json = new AjaxJson();
		Integer shopId = null;
		CustomerSharingDataVO client =  super.getUserSharingData();
		if(client.getAgentId() == AgentType.商铺.getCode()) {
			shopId = client.getAgentNum();
		}
		Map<String, Object> search = new HashMap<>();
		search.put("shopId", shopId);
		search.put("shopName", shopName);
		search.put("userName", userName);
		search.put("mobile", mobile);
		search.put("orderNo", orderNo);
		search.put("status", status);
		json.setObj(orderService.getOrdersPagination(search, page, pageSize));
		return json;
	}
}
