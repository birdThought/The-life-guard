package com.lifeshs.customer.controller.shop;

import java.util.List;

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

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.service.shop.ShopClassifyService;
import com.lifeshs.shop.CategoryDTO;

/**
 *   商品分类
 * @author jiangchangbin
 * @create 2018-11-25
 * 15:23
 * @desc
 */
@Controller("goodsClassifyController")
@RequestMapping("/commodity/classify")
public class GoodsClassifyController {
	
	@Autowired @Qualifier("shop_classify_service")
	ShopClassifyService shopClassifyService;
	
	/*
	 * 商品分类   跳转路径到jsp
	 */
	@RequestMapping("/page")
    public ModelAndView GoodsClassifyList(){
        return new ModelAndView("platform/shop/categoryTree");
    }
	
	@RequestMapping(value = "/allCategory", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getAllCategory() {
		AjaxJson json = new AjaxJson();
		json.setObj(shopClassifyService.getAllCategory());
		return json;
	}
	
	@RequestMapping(value = "/list/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getCategoryByPid(@PathVariable("pid") Integer pid) {
		AjaxJson json = new AjaxJson();
		List<CategoryDTO> categorys = shopClassifyService.getCategoryByPid(pid);
		json.setObj(categorys);
		return json;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson createCategory(@RequestBody CategoryDTO category) {
		AjaxJson json = new AjaxJson();
		shopClassifyService.saveCategory(category);
		json.setObj(category);
		return json;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson deleteCategory(@RequestParam("idPath") String idPath) {
		AjaxJson json = new AjaxJson();
		shopClassifyService.removeCategoryByIdPath_R(idPath);
		return json;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson updateCategory(@RequestBody CategoryDTO category) {
		AjaxJson json = new AjaxJson();
		shopClassifyService.updateCategory(category);
		return json;
	}
}
