package com.lifeshs.customer.controller.shop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.common.shop.ShopConstants;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.service.shop.ShopService;
import com.lifeshs.shop.ShopAuthitionDTO;

/**
 * 
 * @author liu
 * @时间 2018年12月14日 上午10:35:31
 * @说明 商铺管理
 */
@Controller("shop-controller")
@RequestMapping("/commodity/shop")
public class ShopController{
	private final static int PAGE_SIZE = 10;

	@Autowired @Qualifier("shop_service")
	ShopService shopService;
	

	@RequestMapping("/page")
    public ModelAndView pagination(){
        ModelAndView mvc = new ModelAndView("platform/shop/shopList");
        return mvc;
    }
	
	@RequestMapping(value = "/list/{page}", method = {RequestMethod.GET})
	@ResponseBody
	public AjaxJson shopList(@PathVariable("page") Integer page
				, @RequestParam(name = "pageSize", required = false, defaultValue = PAGE_SIZE + "") Integer pageSize
				,@RequestParam(value = "shopName", required = false) String shopName
				,@RequestParam(value = "userName", required = false) String userName
				,@RequestParam(value = "orgName", required = false) String orgName
				,@RequestParam(value = "state", required = false) Integer state
				,@RequestParam(value = "size", required = false) Integer size) {
		AjaxJson json = new AjaxJson();
		Map<String, Object> search = new HashMap<>();
		search.put("shopName", shopName);
		search.put("userName", userName);
		search.put("orgName", orgName);
		search.put("state", state);
		json.setObj(shopService.getShopList(search, page, pageSize));
		return json;
	}
	
	/**
	 * 审核
	 * @return
	 */
	@RequestMapping(value = "/authit", method = {RequestMethod.POST})
	@ResponseBody
	public AjaxJson authit(@RequestBody ShopAuthitionDTO authitionDTO) {
		AjaxJson json = new AjaxJson();
		Integer state = shopService.authitShop(authitionDTO);
		json.setObj(state);
		return json;
	}
	
	/**
	 * 冻结&解冻(frozen&unfrozen)
	 * @param action
	 * @return
	 */
	@RequestMapping(value = "/changeState/{action}", method = {RequestMethod.POST})
	@ResponseBody
	public AjaxJson changeState(@PathVariable(value = "action") String action, @RequestParam(value = "id") Integer id) {
		AjaxJson json = new AjaxJson();
		ShopConstants.ShopState state = "frozen".equals(action)?ShopConstants.ShopState.冻结
					:"unfrozen".equals(action)?ShopConstants.ShopState.解冻:null;
		int n = shopService.changeState(state == null?null:state.getCode(), id);
		if(n == 0) {
			json.setSuccess(false);
			json.setMsg("已经处于该状态,或此状态下不能执行此操作!");
		} else {json.setObj(state.getCode());}
		return json;
	}
}
