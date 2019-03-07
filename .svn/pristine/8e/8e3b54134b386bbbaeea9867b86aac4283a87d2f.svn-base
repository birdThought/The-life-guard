package com.lifeshs.customer.controller.shop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.service.shop.goods.ShopLabelService;

@Controller
@RequestMapping("/commodity/label")
public class ShopLableController {
	
	@Autowired
	ShopLabelService shopLabelService;

	@RequestMapping(value = "/list",method= {RequestMethod.GET})
	@ResponseBody
	public AjaxJson getLabelList(){
		AjaxJson json = new AjaxJson();
		json.setObj(shopLabelService.getLabelList());
		return json;
	}
}
