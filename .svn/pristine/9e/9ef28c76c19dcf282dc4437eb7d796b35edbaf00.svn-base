package com.lifeshs.customer.controller.shop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.service.shop.CommodityService;
import com.lifeshs.shop.AdvertDTO;
import com.lifeshs.task.RecommendGoods;
import com.lifeshs.vo.shop.ResetRecommendGoodsVO;

@Controller
@RequestMapping("/commodity/recommend")
public class ShopRecommendController {
	
	@Resource
	CommodityService commodityService;
	
	private final static int PAGE_SIZE = 10;
	
	/**
	 * 广告配置
	 * @return
	 * @author liu
	 * @时间 2018年12月19日 上午10:47:21
	 * @remark
	 */
	@RequestMapping(value = "/category", method = {RequestMethod.GET})
	public ModelAndView category() {
		ModelAndView mvc = new ModelAndView("platform/shop/recommend/cate");
        return mvc;
	}
	
	/**
	 * 推荐类目配置
	 * @return
	 * @author liu
	 * @时间 2018年12月19日 上午10:47:29
	 * @remark
	 */
	@RequestMapping(value = "/advert", method = {RequestMethod.GET})
	public ModelAndView advert() {
		ModelAndView mvc = new ModelAndView("platform/shop/recommend/advert");
        return mvc;
	}
	
	@RequestMapping(value = "/getRecommendCategory", method = {RequestMethod.GET})
	@ResponseBody
	public AjaxJson getRecommendCategory() {
		AjaxJson json = new AjaxJson();
		json.setObj(commodityService.getRecommendCategory("DESC", null));
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("recommendSize", RecommendGoods.limitSize);
		json.setAttributes(attrs);
		return json;
	}
	
	@RequestMapping(value = "/resetRecommendCategory", method = {RequestMethod.POST})
	@ResponseBody
	public AjaxJson reset(@RequestBody ResetRecommendGoodsVO recommendGoogds) {
		AjaxJson json = new AjaxJson();
		commodityService.resetRecommendCategory(recommendGoogds.getPageGoods());
//		json.setObj(recommendGoogds.getPageGoods());
		return json;
	}
	
	@RequestMapping(value = "/editEndTime", method = {RequestMethod.POST})
	@ResponseBody
	public AjaxJson editTime(@RequestParam("id") Integer id, @RequestParam("newTime") String newTime) throws ParseException {
		AjaxJson json = new AjaxJson();
		commodityService.moveSortOrSetTime(id, null, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newTime));
		return json;
	}
	
	@RequestMapping(value = "/getAdverts/{page}", method = {RequestMethod.GET})
	@ResponseBody
	public AjaxJson getAdverts(@PathVariable(name = "page", required = false) Integer page
			,@RequestParam(name = "pageSize", required = false, defaultValue = PAGE_SIZE + "") Integer pageSize
			,@RequestParam(value = "goodsName", required = false) String goodsName
			,@RequestParam(value = "shopName", required = false) String shopName
			,@RequestParam(value = "userName", required = false) String userName
			,@RequestParam(value = "date", required = false) String date
			,@RequestParam(value = "status", required = false) Integer status
			,@RequestParam(value="advertType", required = false) Integer advertType) {
		AjaxJson json = new AjaxJson();
		json.setObj(commodityService.getAdvertPagination(page, pageSize, date, userName, shopName, goodsName, status, advertType));
		return json;
	}
	
	/**
	 * 逻辑删除广告
	 * @return
	 * @author liu
	 * @时间 2018年12月21日 上午10:49:06
	 * @remark
	 */
	@RequestMapping(value = "/removeAdvert", method = {RequestMethod.POST})
	@ResponseBody
	public AjaxJson delAdvertById(@RequestParam("id") Integer id) {
		AjaxJson json = new AjaxJson();
		int n = commodityService.deleteAdvertById(id);
		json.setObj(n);
		return json;
	}
	
	/**
	 * 添加广告
	 * @param advert
	 * @return
	 * @author liu
	 * @throws OperationException 
	 * @时间 2018年12月24日 上午10:32:22
	 * @remark
	 */
	@RequestMapping(value = "/addAdvert", method = {RequestMethod.POST})
	@ResponseBody
	public AjaxJson addAdvert(@RequestBody AdvertDTO advert) throws OperationException {
		AjaxJson json = new AjaxJson();
		commodityService.createAdvert(advert);
		json.setObj(advert);
		return json;
	}
	
			
}
