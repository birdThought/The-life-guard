package com.lifeshs.app.api.member.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.store.ReturnDataAgent;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.pojo.app.manager.MAppJSON;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.shop.CommodityService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.shop.GoodsDTO;
import com.lifeshs.shop.LabelDTO;
import com.lifeshs.shop.PictureDTO;
import com.lifeshs.shop.SkuDTO;

/*
 *  app  商城 首页综合信息
 *  Founder: jiang chang bin   2018/11/7 9:20
 *  com.lifeshs.app.api.stores.CommodityController
 */
@RestController
@RequestMapping("/scapp/stores")
public class CommodityController {
    
	@Autowired
    private CommodityService commodityService;
	
	/*
	 * 首页 获取分类标签(findClassfiyTags)
	 */
    @RequestMapping(value = "findClassfiyTags", method = RequestMethod.POST) 
	public @ResponseBody JSONObject findClassfiyTags(@RequestParam String json) throws OperationException {
		
//        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
//        JSONObject data = appJSON.getData();
        
        List<LabelDTO> list=commodityService.findClassfiyTags();
        
        return ReturnDataAgent.success(list);
    }
	
	/*
	 * 首页  获取分类商品(findClassfiyCommodity)
	 */
    @RequestMapping(value = "findClassfiyCommodity", method = RequestMethod.POST) 
    public @ResponseBody JSONObject findClassfiyCommodity(@RequestParam String json) {
		
		//展示女性保健品
		List<GoodsDTO> woman=commodityService.findClassfiyCommodityWoman();
		
		//展示女男性保健品
		List<GoodsDTO> man=commodityService.findClassfiyCommodityMan();
		
		JSONObject root = new JSONObject();
		//woman 表示女性专区
        root.put("woman", woman);
        //man  表示男性专区
        root.put("man", man);
		return root;
	}
	
    
	/**
     * 
     *  查找2.182.商品列表(商品分类) (findCommodityList)
     *  Founder: jiang chang bin   2018/11/9 10:50
     *  @DateTime 2018年6月25日 下午12:02:47
     *
     *  @return
     */
	@RequestMapping(value = "findCommodityList", method = RequestMethod.POST)
    public @ResponseBody JSONObject findDrusTypeList(SubmitDTO sumbitDTO){
		
		
		return null;
	}
	
}
