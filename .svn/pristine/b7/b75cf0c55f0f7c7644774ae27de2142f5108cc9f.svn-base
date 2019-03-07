package com.lifeshs.customer.controller.shop;

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

import com.lifeshs.common.constants.common.AgentType;
import com.lifeshs.common.constants.common.shop.ShopConstants.GoodsStatus;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.shop.goods.ShopGoodsService;
import com.lifeshs.shop.AttributeValueDTO;
import com.lifeshs.vo.customer.CustomerSharingDataVO;
import com.lifeshs.vo.shop.GoodsEditBean;

/**
 * @author liu
 * @时间 2018年12月14日 上午10:13:46
 * @说明
 */
@Controller("shop_goods_controller")
@RequestMapping(value = "commodity/goods")
public class ShopGoodsController extends BaseController{
	
	private final static int PAGE_SIZE = 10;

	@Autowired @Qualifier("shop_goods_service")
	ShopGoodsService shopGoodsService;
	
	@RequestMapping("/page")
    public ModelAndView getCharacter(){
        ModelAndView mvc = new ModelAndView("platform/shop/goodsListOf");
        return mvc;
    }
	
	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getGoodsList(@PathVariable(name = "page", required = false) Integer page
				, @RequestParam(name = "pageSize", required = false, defaultValue = PAGE_SIZE + "") Integer pageSize
				, @RequestParam(name = "status", required = false) Integer status
				, @RequestParam(name = "goodsName", required = false) String goodsName
				, @RequestParam(name = "shopName", required = false) String shopName
				, @RequestParam(name = "userName", required = false) String userName) {
		AjaxJson json = new AjaxJson();
		Integer shopId = null;
		CustomerSharingDataVO client =  super.getUserSharingData();
		if(client.getAgentId() == AgentType.商铺.getCode()) {
			shopId = client.getAgentNum();
		}
		PaginationDTO<Map<String, Object>> pDTO = shopGoodsService
				.getGoodsListPaging(shopId, goodsName, shopName, userName, status, page, pageSize);
		json.setObj(pDTO);
		return json;
	}
	
	@RequestMapping(value = "/toAdd", method = {RequestMethod.GET})
    public ModelAndView toAddGoods(@RequestParam("type") String type
    		,@RequestParam(value = "goodsId", required=false) Integer goodsId){
		ModelAndView mvc = new ModelAndView("platform/shop/goodsEdit");
		mvc.addObject("type", type);
		mvc.addObject("goodsId", goodsId);
        return mvc;
    }
	
	@RequestMapping(value = "/addInit", method = {RequestMethod.GET})
	@ResponseBody
	public AjaxJson addInit() throws OperationException {
		AjaxJson json = new AjaxJson();
		json.setObj(shopGoodsService.toAddInit());
		return json;
	}
	
	@RequestMapping(value = "/getGoodsById", method = {RequestMethod.GET})
	@ResponseBody
	public AjaxJson getGoodsById(@RequestParam("goodsId") Integer goodsId) {
		AjaxJson json = new AjaxJson();
		json.setObj(shopGoodsService.getByGoodsId(goodsId));
		return json;
	}
	
	@RequestMapping(value = "/changeAttrs", method = {RequestMethod.GET})
	@ResponseBody
	public AjaxJson changeAttrsCategoryId(@RequestParam(name = "shopId", required=false) Integer shopId
			, @RequestParam(name = "categoryId", required=false) Integer categoryId) throws OperationException {
		AjaxJson json = new AjaxJson();
		CustomerSharingDataVO client = super.getUserSharingData();
//		if(client.getAgentId() != AgentType.商铺.getCode()) {
//			throw new OperationException("非商铺用户不能添加商品!", ErrorCodeEnum.DENY);
//		} else {
//			shopId = (shopId == null?client.getAgentNum():shopId);
//		}
		shopId = (shopId == null?client.getAgentNum():shopId);
		json.setObj(shopGoodsService.changeAttrsByCategoryId(shopId, categoryId));
		return json;
	}
	
	@RequestMapping(value = "/addAttrValue", method = {RequestMethod.POST})
	@ResponseBody
	public AjaxJson addAttrValue(@RequestBody AttributeValueDTO attrValue) {
		AjaxJson json = new AjaxJson();
		shopGoodsService.createAttrValue(attrValue);
		json.setObj(attrValue);
		return json;
	}
	
	/**
	 * 
	 * @param goodsEdit
	 * @return
	 * @author liu
	 * @throws OperationException 
	 * @时间 2018年12月14日 上午10:21:53
	 * @remark 保存添加商品
	 */
	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson doAddGoods(@RequestBody GoodsEditBean goodsEdit) throws OperationException {
		AjaxJson json = new AjaxJson();
		Integer shopId = null;
		CustomerSharingDataVO client = super.getUserSharingData();
		
		if(client.getAgentId() != AgentType.商铺.getCode()) {
			throw new OperationException("非商铺用户不能添加商品!", ErrorCodeEnum.DENY);
		} else {
			shopId = (shopId == null?client.getAgentNum():shopId);
		}
		goodsEdit.getGoods().setShopId(shopId);
		shopGoodsService.saveGoods(goodsEdit.getGoods(), goodsEdit.getAttributes(), goodsEdit.getRelations()
					, goodsEdit.getSpecs(), goodsEdit.getSkus(), goodsEdit.getWillRemoveAttrs());
		return json;
	}
	
	@RequestMapping(value = "/doUpdate", method = {RequestMethod.POST})
	@ResponseBody
	public AjaxJson editGoods(@RequestBody GoodsEditBean goodsEdit) throws OperationException {
		AjaxJson json = new AjaxJson();
		Integer shopId = null;
		CustomerSharingDataVO client = super.getUserSharingData();
		if(client.getAgentId() != AgentType.商铺.getCode()) {
			throw new OperationException("非商铺用户不能添加商品!", ErrorCodeEnum.DENY);
		} else {
			shopId = (shopId == null?client.getAgentNum():shopId);
		}
		goodsEdit.getGoods().setShopId(shopId);
		shopGoodsService.updateGoods(goodsEdit.getGoods(), goodsEdit.getAttributes(), goodsEdit.getRelations()
					, goodsEdit.getSpecs(), goodsEdit.getSkus(), goodsEdit.getWillRemoveAttrs());
		return json;
	}
	
	/**
	 * 
	 * @param id
	 * @param action
	 * @return
	 * @author liu
	 * @时间 2018年12月14日 上午10:22:24
	 * @remark 上下架:down=下架,up=上架
	 */
	@RequestMapping(value = "/changeStatus/{action}", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson changeStatus(@RequestParam(value = "id") Integer id,@PathVariable("action") String action) {
		AjaxJson json = new AjaxJson();
		GoodsStatus status = "down".equals(action)?GoodsStatus.已下架:"up".equals(action)?GoodsStatus.已上架:null;
		int n = shopGoodsService.changeStatus(id, status);
		if(n == 0) {
			json.setSuccess(false);
		} else {
			json.setObj(status.getCode());
		}
		return json;
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getGoodsDetails(@RequestParam(value = "id") Integer id) {
		AjaxJson json = new AjaxJson();
		json.setObj(shopGoodsService.getGoodsDetails(id));;
		return json;
	}
}
