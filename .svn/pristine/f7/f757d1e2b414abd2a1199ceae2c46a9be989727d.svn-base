package com.lifeshs.customer.controller.dd;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.entity.record.TDataFoodKind;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.foodKind.FoodKindService;

@RestController
@RequestMapping(value = "/data/foodKind")
public class FoodKindController {
	static final Logger logger = Logger.getLogger(FoodKindController.class);

	static final int PAGESIZE = 10;

	@Autowired
	FoodKindService foodKindService;
	
	/**
	 * 食物种类页面
	 * @return
	 */
	@RequestMapping(value = "/page")

	private ModelAndView FoodKindPage() {
		return new ModelAndView("platform/systemmanage/foodKind");
	}

	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public AjaxJsonV2 listFoodKind(@PathVariable("page") int page) {
		Paging paging = foodKindService.findFoodKind(page, PAGESIZE);
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		ajaxJsonV2.setObj(paging.getPagination());
		return ajaxJsonV2;

	}
	
	/**
	 * 添加食物种类
	 * @param tDataFoodKind
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public AjaxJsonV2 addFoodKind(TDataFoodKind tDataFoodKind) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		try {
			foodKindService.addFoodKind(tDataFoodKind);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
		
	}
	
	/**
	 * 更改食物种类
	 * @param tDataFoodKind
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public AjaxJsonV2 updateFoodKind(TDataFoodKind tDataFoodKind) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		try {
			foodKindService.updateFoodKind(tDataFoodKind);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}
	
	/**
	 * 删除食物种类
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public AjaxJsonV2 deleteFoodKind(@RequestParam("id") Integer id) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		try {
			foodKindService.deleteFoodKind(id);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}
}
