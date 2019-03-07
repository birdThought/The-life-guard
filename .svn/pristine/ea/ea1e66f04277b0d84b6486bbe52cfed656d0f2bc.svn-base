package com.lifeshs.controller.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.alibaba.druid.filter.Filter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.common.jianKe.OrderStatusEnum;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.po.shop.Cart;
import com.lifeshs.po.shop.CatalogOne;
import com.lifeshs.po.shop.GoodsSkuPO;
import com.lifeshs.po.shop.OrderDetailsPo;
import com.lifeshs.po.shop.OrderPO;
import com.lifeshs.po.shop.SearchPo;
import com.lifeshs.po.shop.ShowPreviewPO;
import com.lifeshs.po.shop.SkuItem;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.service.shop.CommodityService;
import com.lifeshs.service.shop.ShopService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service.terminal.app.serve.IAppServeService;
import com.lifeshs.shop.Address;
import com.lifeshs.shop.GoodsDTO;
import com.lifeshs.shop.LabelDTO;
import com.lifeshs.shop.OrderDTO;
import com.lifeshs.shop.OrderDecomposeDTO;
import com.lifeshs.shop.OrderEvaluateDTO;
import com.lifeshs.thirdservice.IWeChatService;
import com.lifeshs.utils.HttpUtils;
import com.mysql.fabric.xmlrpc.base.Array;

@RestController
@RequestMapping("shop")
public class ShopController {
	
	private static final String String = null;

	@Resource(name = "shop_service")
	private ShopService shopService;
	
	@Autowired
	private HttpSession session;
	
	@Resource(name = "IWeChatService")
    private IWeChatService weChatService;
	
	@Resource(name = "appServeService")
    private IAppServeService serveService;
	
	@Autowired
    private CommodityService commodityService;
	
	private final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	
	@RequestMapping(value="/page",method = RequestMethod.GET) 
	public ModelAndView goShop(@RequestParam (value="userId",required=false) Integer userId) {		
		session.setAttribute("userId", userId);			
		System.out.println(session.getAttribute("userId"));
		ModelAndView modelAndView = new ModelAndView("shop/index");
		return modelAndView;						
	}
	
	@RequestMapping(value="/index",method = RequestMethod.GET) 
	public ModelAndView index() {								
		ModelAndView modelAndView = new ModelAndView("shop/index");
		return modelAndView;						
	}
	
	@RequestMapping(value="/CatalogPage",method = RequestMethod.GET) 
	public ModelAndView CatalogPage() {
		ModelAndView modelAndView = new ModelAndView("shop/index_content");
		return modelAndView;						
	}
	
	@RequestMapping(value="/moreGoodsPage",method = RequestMethod.GET) 
	public ModelAndView moreGoodsPage(Integer id,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("shop/index_subcontent");
		request.getServletContext().setAttribute("moreGoodsId", id);
		return modelAndView;						
	}
	
	
	@RequestMapping("findClassfiyTags") 
	public AjaxJson findClassfiyTags(){		
		AjaxJson ajaxJson = new AjaxJson();    
        List<LabelDTO> list=commodityService.findClassfiyTags();	        
        ajaxJson.setObj(list);
        return ajaxJson;
	 }
	
	@RequestMapping("findCatalogAll")
	public ModelAndView findCatalogAll(){
		ModelAndView mav = new ModelAndView("shop/index_content");
		List<CatalogOne> CatalogList = shopService.findCatalogAll();		
		mav.addObject("CatalogList",JSON.toJSON(CatalogList));	
		return mav;
	}
	
	
	@RequestMapping("findCatalogById")
	public ModelAndView findCatalogById(Integer id){	
		ModelAndView mav = new ModelAndView("shop/index_content"); 
		List<CatalogOne> findCatalogAll = shopService.findCatalogById(id);		
		mav.addObject("CatalogList", JSON.toJSON(findCatalogAll));
		return mav;		
	}
	
	
	@GetMapping("shopSearch")
	public ModelAndView shopSearch(@RequestParam("keyWord") String keyWord){	
		ModelAndView mav = new ModelAndView("shop/search_list");
		SearchPo searchPo =  shopService.shopSearch(keyWord);	
		mav.addObject("searchPo", JSON.toJSON(searchPo));		
		return mav;	
	}
	
	
	@PostMapping("showPreview")
	public AjaxJson showPreview(@RequestParam Integer oneColumn,@RequestParam Integer twoColumn){		
		AjaxJson ajaxJson = new AjaxJson();
		List<ShowPreviewPO> list = shopService.showPreview(oneColumn,twoColumn);					
		ajaxJson.setObj(list);
		return ajaxJson;
	}
	
	
	@RequestMapping(value="/detailsPage",method = RequestMethod.GET) 
	public ModelAndView detailsPage(String goodsId,Integer skuId, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("shop/product_index_option");
		GoodsSkuPO goodsSkuPO =  shopService.findSku(goodsId);			
		modelAndView.addObject("goodsSkuPO", JSON.toJSON(goodsSkuPO));
		modelAndView.addObject("skuId", skuId);
		return modelAndView;						
	}
	
	
	@RequestMapping("ChoiceSkuPage")
	public AjaxJson ChoiceSkuPage(HttpServletRequest request){
		AjaxJson ajaxJson = new AjaxJson();
		String goodsId = (String) request.getServletContext().getAttribute("goodsId");
		GoodsSkuPO goodsSkuPO =  shopService.findSku(goodsId);			
		ajaxJson.setObj(goodsSkuPO);
		return ajaxJson;	
	}
	
	
	@PostMapping("moreGoods")
	public AjaxJson moreGoods(@RequestParam String filter,@RequestParam Integer sort ,HttpServletRequest request){
		AjaxJson ajaxJson = new AjaxJson();
		if(filter != null && filter.length() == 0 ){
			filter = null;
		}
		Integer id = (Integer) request.getServletContext().getAttribute("moreGoodsId");
		ShowPreviewPO list = shopService.showPreview(id,sort,filter);			
		ajaxJson.setObj(list);
		return ajaxJson;		
	}
	
	@GetMapping("getAddress")
	public AjaxJson getAddress(){	
		AjaxJson ajaxJson = new AjaxJson();	
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null){
			return ajaxJson;
		}			
        List<Address> address = shopService.getAddressById(userId);	
        if(address.size()==1 && address.get(0).getSelected()!=1){
        	shopService.updateAddressDefault(address.get(0).getId(),1);
        	address = shopService.getAddressById(userId);
        }
        ajaxJson.setObj(address);
		return ajaxJson;
		
	}
	
	@RequestMapping("showAddress")
	public ModelAndView showAddress(){
		ModelAndView mav = new ModelAndView("shop/address");		
		Integer userId = (Integer) session.getAttribute("userId");				
        List<Address> address = shopService.getAddressById(userId);
        mav.addObject("address", JSON.toJSON(address));
		return mav;
	}
	
	
	@RequestMapping("addressPage")
	public ModelAndView addressPage(Boolean judge, Integer id){
		ModelAndView mav = new ModelAndView("shop/address_edit");
		if(judge){
			Address address = shopService.queryAddressById(id);
			mav.addObject("address", JSON.toJSON(address));
		}
		mav.addObject("judge", judge);
		return mav;		
	}
	
	@PostMapping("addAddress")
	public AjaxJson addAddress(@RequestBody Address address){		
		AjaxJson ajaxJson = new AjaxJson();	
		Integer userId = (Integer) session.getAttribute("userId");
        address.setCreateDate(new Date());
        address.setUserId(userId);
        address.setDisplay(1);
        address.setSelected(0);
        shopService.addAddress(address);                
		return ajaxJson;		
	}
	
	@RequestMapping("purchasePage")
	public ModelAndView purchasePage(Integer num, Integer itemId){
		Integer userId = (Integer) session.getAttribute("userId");
		ModelAndView mav = null;
		if(userId == null){
			mav =  new ModelAndView("shop/login");
		}else{
			mav = new ModelAndView("shop/order_confirm");
			mav.addObject("num", num);
			mav.addObject("itemId", itemId)	;
		}			
		return mav;	
	}
	
	
	@PostMapping("goodsdetails")
	public AjaxJson goodsdetails(@RequestParam("itemId")Integer itemId ){
		AjaxJson ajaxJson = new AjaxJson();
		/*java.lang.String st = shopService.selectAttrLengthById(itemId);
		java.lang.String[] split = st.split(",");*/
		SkuItem sku = shopService.selectByPrimaryKey(itemId);
		ajaxJson.setObj(sku);
		return ajaxJson;		
	}
	
	
	@RequestMapping("LoadingList")
	public ModelAndView LoadingList(Integer[] LoadingList){
		ModelAndView mav = null;
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null){
			mav =  new ModelAndView("shop/login");
		}else{
			Integer[]list = (Integer[]) LoadingList;
			mav = new ModelAndView("shop/order_confirm1");
			List<Integer> asList = Arrays.asList(list);					
	        List<Cart> loadingList = shopService.LoadingList(userId,asList); 
	        Boolean JudgementStock = false; 
	        for (Cart cart : loadingList) {
				List<SkuItem> orderItemList = cart.getOrderItemList();
				for (SkuItem skuItem : orderItemList) {
					 SkuItem item = shopService.selectByPrimaryKey(skuItem.getId()); 
					 if(skuItem.getNum()>item.getInventory()){
						 JudgementStock = true;
					 }
				}
			}	        	       
	        mav.addObject("JudgementStock", JudgementStock);
	        mav.addObject("loadingList", JSON.toJSON(loadingList));
		}		
		return mav;
		
	}
	
	
	@PostMapping("GenerateOrder")
	@Transactional
	public AjaxJson GenerateOrder(@RequestBody Map<String, Object>map){				
		AjaxJson ajaxJson = new AjaxJson();
        OrderDTO order = new OrderDTO();
		String orderNo = System.currentTimeMillis()+"";
        order.setOrderNo(orderNo);
        Integer userId = (Integer) session.getAttribute("userId");
        order.setUserId(userId);     
        order.setMoney(Double.parseDouble(map.get("money")+""));
        /*Address address = shopService.queryAddressById (addressId); */
        order.setAddressId((Integer) map.get("addressId")+"");
        order.setCreateTime(new Date());
        /*order.setPaymentType((Integer) map.get("paymentType"));*/
        order.setStatus(1);
        shopService.addOrder(order);   //添加订单        
        //--------------------------------------------------------       
        List<Map<String, Object>> LoadingList = (List<Map<String, Object>>) map.get("LoadingList");
        List<String>skuIdList =  (List<java.lang.String>) map.get("skuId");
        /*List<String>shopList =  (List<java.lang.String>) map.get("shop");*/
        List<Integer>skuIdList1 = new ArrayList<>();
       
        for (int i = 0;i < LoadingList.size();i++) {
			for(String skuId : skuIdList){
				java.lang.String[] split = skuId.split("-");
				if(Integer.parseInt(split[0]) == i){					
					OrderDecomposeDTO orderDec =  shopService.getGoodsInfo(Integer.parseInt(split[1]),order.getUserId());
					orderDec.setOrderNo(orderNo);
					orderDec.setOrderNotes(LoadingList.get(i).get("orderNotes").toString());	
					/*orderDec.setTransportCosts(Long.parseLong((String)map.get("transportCosts")));*/
					orderDec.setInvoice(LoadingList.get(i).get("invoice")+"");										
					shopService.addOrderDecompose(orderDec);   //添加拆单表数据	
					shopService.deductStock(Integer.parseInt(split[1]),orderDec.getNum()); //扣减库存
					shopService.addSkuSales(Integer.parseInt(split[1]),orderDec.getNum()); //增加sku销量
				}				
			}								
		}    
        for (String skuId : skuIdList) {
        	java.lang.String[] split = skuId.split("-");
        	skuIdList1.add(Integer.parseInt(split[1]));
		}
        shopService.delCartBySkuId(skuIdList1,order.getUserId());    //删除redis中对应的购物车数据                   
        ajaxJson.setObj(order);
        return ajaxJson;		
	}
		
	
	
	@PostMapping("GenerateOrder2")
	@Transactional
	public AjaxJson GenerateOrder2(@RequestBody Map<String, Object>map){	
		AjaxJson ajaxJson = new AjaxJson();
		Integer  skuId =  (Integer) map.get("skuId");	
	    SkuItem item = shopService.selectByPrimaryKey(skuId); 
	    if(item.getInventory() < (Integer) map.get("num")){
	    	ajaxJson.setMsg("购买的商品数量已超过商品库存");
	    	ajaxJson.setSuccess(false);
	    	return ajaxJson;
	    }
	    
		
        OrderDTO order = new OrderDTO();
		String orderNo = System.currentTimeMillis()+"";
        order.setOrderNo(orderNo);
        /*order.setUserId((Integer) map.get("userId"));*/
        Integer userId = (Integer) session.getAttribute("userId");
        order.setUserId(userId);     
        order.setMoney(Double.parseDouble(map.get("money")+""));
        /*Address address = shopService.queryAddressById (addressId); */
        order.setAddressId((Integer) map.get("addressId")+"");
        order.setCreateTime(new Date());
        /*order.setPaymentType((Integer) map.get("paymentType"));*/
        order.setStatus(1);
        shopService.addOrder(order);   //添加订单        
        //--------------------------------------------------------        
        List<Map<String, Object>> LoadingList = (List<Map<String, Object>>) map.get("LoadingList");
       
		OrderDecomposeDTO orderDec =  new OrderDecomposeDTO();
		orderDec.setNum((Integer) map.get("num"));
		orderDec.setOrderNo(orderNo);
		orderDec.setOrderNotes(LoadingList.get(0).get("orderNotes").toString());
		orderDec.setShopId(item.getShopId());
		orderDec.setAmount(Double.parseDouble(map.get("money")+""));
		orderDec.setOrderNotes( LoadingList.get(0).get("orderNotes")+"");
		orderDec.setGoodsName(item.getGoodsName());
		orderDec.setInvoice(LoadingList.get(0).get("invoice")+"");
		orderDec.setPrice(item.getFavorablePrice());
		orderDec.setStatus(1);
		orderDec.setShopName(item.getShopName());
		orderDec.setGoodsId(item.getGoodsId());
		orderDec.setPrice(Double.parseDouble(map.get("money")+"")/(Integer) map.get("num"));
		/*orderDec.setTransportCosts(Long.parseLong((String)map.get("transportCosts")));*/
		orderDec.setAttributeValue(item.getGroup_spec());				
		shopService.addOrderDecompose(orderDec);   //添加拆单表数据	
		shopService.deductStock(skuId,(Integer) map.get("num")); //扣减库存
		shopService.addSkuSales(skuId,(Integer)map.get("num")); //增加sku销量
        ajaxJson.setObj(order);
        return ajaxJson;		
	}		
	
	
	@GetMapping("OrderDetails")
	public ModelAndView OrderDetails(String orderNo){		
		ModelAndView ajaxJson = new ModelAndView("/shop/order_describe");
        List<OrderDetailsPo> order = shopService.getDetailsByOrderNo(orderNo);        
        ajaxJson.addObject("order",JSON.toJSON(order));
		return ajaxJson;		
	}
				
		
	@RequestMapping("getOrderAll")
	public ModelAndView getOrderAll(Integer status){		
		Integer userId = (Integer) session.getAttribute("userId");
		ModelAndView mav = null;
		if(userId == null){
			mav =  new ModelAndView("shop/login");
		}else{
			mav = new ModelAndView("shop/order_list");
	        List<OrderPO> orderList = shopService.getOrderAll(status,userId);             
			mav.addObject("order", JSON.toJSON(orderList));
		}		
		return mav;
	}	
	
	
	@RequestMapping("delOrder")
	public AjaxJson delOrder(String orderNo){		
		Integer userId = (Integer) session.getAttribute("userId");
		AjaxJson ajaxJson = new AjaxJson();
		List<OrderDecomposeDTO> orderDecList = shopService.getOrderDecList(orderNo);
		//恢复库存和销量
		for (OrderDecomposeDTO orderDec : orderDecList) {
			shopService.ResumeInventoryAndSales(orderDec.getGoodsId(),orderDec.getAttributeValue(),orderDec.getNum());
		}		
        shopService.delOrder(orderNo);         
        List<OrderPO> orderList = shopService.getOrderAll(null,userId);  
        ajaxJson.setObj(JSON.toJSON(orderList));
		return ajaxJson;		
	}
				
	
	 /**
	  * 获取签名
	  * @param json
	  * @param request
	  * @return
	  * @throws BaseException
	  */
	 @RequestMapping(value = "getOrderInfoNew", method = RequestMethod.POST)
	    public JSONObject getOrderInfoNew(@RequestBody Map<String, Object> map, HttpServletRequest request) throws BaseException {
	        JSONObject jsonObject = null;

	        /*logger.info("支付接口json:"+json);
	        // 区分支付方式 1：支付宝 2：微信
	        AppJSON appJSON = AppNormalService.parseAppJSON(json);
	        JSONObject msg_0 = appJSON.getData().getFirstJSONObject();
	        logger.info("支付接口msg_0:"+msg_0);*/
	        int payType = (int) map.get((Order.PAY_TYPE));
	        int orderType = (int) map.get((Order.ORDER_TYPE));
	        String orderNo = (String) map.get((Order.NUMBER));

	        //支付宝支付
	        if (PayTypeEnum.ALIPAY.getValue() == payType) {
	        		            	            
                jsonObject = serveService.getOrderInfoNew(map);	           

	        } else if (PayTypeEnum.WECHAT.getValue() == payType) {
	            //微信支付
	            String body = null;  //商品描述
	            String outTradeNo = null; //商品订单号
	            String totalFee = null;	//标价金额，为分
	            String tradeType = null; //交易类型
	            String spbillCreateIp = null; //ip
	            String attach = null; //附件
	            //int transportCosts = 0; //运费
	            
                // 获取用户订单信息
                List<OrderDTO> orderList = shopService.getLocalOrderByParam(orderNo);
                if (orderList.size() < 1) {
                    logger.error(String.format("不存在此订单orderNo:%s", orderNo));
                    return null;
                }
                
                OrderDTO order = orderList.get(0);
                if (OrderStatusEnum.UNPAID.getValue() != order.getStatus()){
                    throw new OperationException("该订单状态为不可支付", ErrorCodeEnum.FAILED);
                }
                
                int total = 0;
                int money = (int) map.get(Order.MONEY);
                List<OrderDecomposeDTO> productList = shopService.getOrderDecList(orderNo);
                for(OrderDecomposeDTO p : productList){
                    total += p.getPrice() * p.getNum();  //单价乘数量
                }
                if(money != total){
                    throw new OperationException("金额不匹配 单位 分 (" + money + "=>" + total,ErrorCodeEnum.FAILED);    
                }
                
                body = "生命守护-商城商品购买";
                outTradeNo = orderNo;
                //totalFee = order.getMoney()+"";
                totalFee = total+"";
                tradeType = Order.TRADE_TYPE_APP;
                spbillCreateIp = HttpUtils.getIpAddress(request);                      
                int addressId = Integer.parseInt(order.getAddressId());                              
                StringBuilder sb = new StringBuilder();
                sb.append(Order.ADDRESSID+":"+addressId); //邮寄地址
//	                sb.append(","+Order.ACCOUNTID+":"+ accountId);//账户ID
                sb.append(","+Order.PAYMENTTYPE+":"+payType);//支付类型
                sb.append(","+Order.ORDER_TYPE+":"+ orderType);//订单类型
                attach = sb.toString();
                logger.info("传送到微信的参数attach："+attach);
                       
            jsonObject = weChatService.unifiedOrderNew(body, outTradeNo, totalFee, tradeType, spbillCreateIp, attach);	        
	        }	        
	        return jsonObject;
	    }
	 
	 
	 @RequestMapping("getAdvertisement")
	 public AjaxJson getAdvertisement(){	
		 AjaxJson ajaxJson = new AjaxJson();
        List<Map<String, Object>> advertisementList = shopService.getAdvertisement();        
        ajaxJson.setObj(advertisementList);
		return ajaxJson;			
	 }
	 
	 @RequestMapping("delAddress")
	 public AjaxJson delAddress(Integer id){	
		AjaxJson ajaxJson = new AjaxJson();	
        shopService.delAddress(id); 
        Integer userId = (Integer) session.getAttribute("userId");				
        List<Address> address = shopService.getAddressById(userId);	
        ajaxJson.setObj(address);
		return ajaxJson;		
	 }
	 
	 @RequestMapping("updateAddressDefault")
	 public AjaxJson updateAddressDefault(Integer id){	
		 Integer userId = (Integer) session.getAttribute("userId");	
	 	AjaxJson ajaxJson = new AjaxJson();
        List<Address> addressList = shopService.getAddressById(userId);
        Integer def = 1;
        Integer notDef = 0;
        if(addressList != null){
        	for (Address address : addressList) {
    			if(address.getId() == id){
    				shopService.updateAddressDefault(address.getId(),def); //默认
    			}else{
    				shopService.updateAddressDefault(address.getId(),notDef);
    			}
    		}
        }  
        List<Address> address = shopService.getAddressById(userId);	
        ajaxJson.setObj(address);        
		return ajaxJson;	
	 }
	 
	 
	@RequestMapping("addOrderEvaluate")
	public AjaxJson addOrderEvaluate(@RequestParam OrderEvaluateDTO orderEva){	
		AjaxJson ajaxJson = new AjaxJson();		
        String picturesList = orderEva.getPictures(); 
        ImageDTO image = null;	      
        String productImgPath = null;
        String[] split = picturesList.split(",");
        for (String pictures : split) {            	
        	String st = pictures.replace("%2B",  "+");
            if (StringUtils.isNotBlank(st)) {
                image = MAppNormalService.uploadPhoto(st, null, "shop", false);
                if (image.getUploadSuccess()) {
                    productImgPath += image.getNetPath()+",";
                }
            }
		}
        if(productImgPath.endsWith(",")){
        	 productImgPath = productImgPath.substring(0, productImgPath.length()-1);
        }
       orderEva.setPictures(productImgPath);
       shopService.addOrderEvaluate(orderEva);        
	   return ajaxJson;	
	}
	
	
	 @RequestMapping("selectOrderNoEvaluate")
	 public AjaxJson selectOrderNoEvaluate(Integer userId){	
		AjaxJson ajaxJson = new AjaxJson();
		List<OrderPO> OrderNoEvaluateList = shopService.selectOrderNoEvaluate(userId);		
		ajaxJson.setObj(OrderNoEvaluateList);
		return ajaxJson;		
	 }
	 
	 
	 @RequestMapping("personal")
	 public ModelAndView personal(){	
		 ModelAndView mav = null;
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId==null){
			mav =  new ModelAndView("shop/login");
		}else{
			mav =  new ModelAndView("shop/mine");					       
	        UserPO userInfo = shopService.getUserInfo(userId);        
	        mav.addObject("userInfo", userInfo);
		}		
		return mav;		
	 };
	 
	 @RequestMapping("userOut")
	 public ModelAndView userOut(){	
		ModelAndView ajaxJson = new ModelAndView("shop/index");
		session.removeAttribute("userId");			                              
		return ajaxJson;		
	 }
	 
	 
	 @RequestMapping("payment")
	 public ModelAndView payment(String orderNo){	
		ModelAndView ajaxJson = new ModelAndView("shop/payment");
		List<OrderDetailsPo> order = shopService.getDetailsByOrderNo(orderNo);
		ajaxJson.addObject("order", JSON.toJSON(order));	                              
		return ajaxJson;		
	 }
	 
	 
	 @RequestMapping("getGoods")
	 public ModelAndView getGoods(Integer cid){	
		ModelAndView ajaxJson = new ModelAndView("shop/index_subcontent1");
		List<GoodsDTO> goodsList = shopService.getGoodsByCid(cid,null,null);
		ajaxJson.addObject("goods", JSON.toJSON(goodsList));
		return ajaxJson;		
	 }
	 
	@PostMapping("showGoods")
	public AjaxJson showGoods(@RequestParam String filter,@RequestParam Integer sort ,HttpServletRequest request){
		AjaxJson ajaxJson = new AjaxJson();
		if(filter != null && filter.length() == 0 ){
			filter = null;
		}
		Integer id = (Integer) request.getServletContext().getAttribute("moreGoodsId");
		List<GoodsDTO> goodsList = shopService.getGoodsByCid(id,sort,filter);			
		ajaxJson.setObj(goodsList);
		return ajaxJson;		
	}
	
	
	@GetMapping("imageChange")
	public AjaxJson imageChange(String img){
		AjaxJson ajaxJson = new AjaxJson();
		Integer userId = (Integer) session.getAttribute("userId");
		shopService.imageChange(userId,img);						
		return ajaxJson;		
	}
	
	@GetMapping("ReceiptConfirm")
	public AjaxJson ReceiptConfirm(String shippingNo,String orderNo){
		AjaxJson ajaxJson = new AjaxJson();		
		shopService.ReceiptConfirm(shippingNo,orderNo);	 //更改状态	
		Integer userId = (Integer) session.getAttribute("userId");
		List<OrderPO> orderList = shopService.getOrderAll(null,userId);  
		ajaxJson.setObj(orderList);
		return ajaxJson;		
	}
	
}
