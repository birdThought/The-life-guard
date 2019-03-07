package com.lifeshs.service.shop.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lifeshs.common.constants.common.order.PayReturnOrderTypeEnum;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.constants.common.shop.ShopConstants.ShopAuthit;
import com.lifeshs.common.constants.common.shop.ShopConstants.ShopState;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao.shop.ShopDao;
import com.lifeshs.po.shop.Cart;
import com.lifeshs.po.shop.CatalogOne;
import com.lifeshs.po.shop.GoodsSkuPO;
import com.lifeshs.po.shop.OrderDetailsPo;
import com.lifeshs.po.shop.OrderPO;
import com.lifeshs.po.shop.SearchPo;
import com.lifeshs.po.shop.SearchSkuDisplayPO;
import com.lifeshs.po.shop.ShowPreviewPO;
import com.lifeshs.po.shop.SkuItem;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.pojo.order.PaymentOrderDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service.shop.ShopService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.shop.Address;
import com.lifeshs.shop.GoodsDTO;
import com.lifeshs.shop.OrderDTO;
import com.lifeshs.shop.OrderDecomposeDTO;
import com.lifeshs.shop.OrderEvaluateDTO;
import com.lifeshs.shop.OrderRecordDTO;
import com.lifeshs.shop.ShopAuthitionDTO;
import com.lifeshs.shop.ShopDTO;
import com.lifeshs.shop.SkuDTO;
import com.lifeshs.utils.NumberUtils;

import net.sf.ehcache.util.SetAsList;
@Service(value="shop_service")
@Transactional
public class ShopServiceImpl implements ShopService {
	
	private static final String REDIS_CART_LIST = "CART_LIST";
	
	private static final Logger logger = Logger.getLogger(ShopServiceImpl.class);
	
	@Autowired @Qualifier("shop_dao")
	private ShopDao shopDao;
	
	 @Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public List<CatalogOne> findCatalogAll() {		
		List<CatalogOne> CatalogAll = shopDao.findCatalogAll(null);		
		return CatalogAll;
	}


	@Override
	public List<CatalogOne> findCatalogById(Integer id) {
		return shopDao.findCatalogAll(id);		 
	}


	@Override
	public SearchPo shopSearch(String keyword) {
		List<ShopDTO>shops =  shopDao.SearchShop(keyword);
		List<SearchSkuDisplayPO> skus = shopDao.SearchSku(keyword);
		SearchPo searchPo = new SearchPo();
		searchPo.setShopList(shops);
		searchPo.setSkuList(skus);
		return searchPo;
	}


	@Override
	public List<ShowPreviewPO> showPreview(Integer oneColumn, Integer twoColumn) {
		List<ShowPreviewPO> list = new ArrayList<ShowPreviewPO>();	
		List<Integer> ids = shopDao.selectPreviewId();
		list.add(shopDao.showPreview(ids.get(0),null,null));
		list.add(shopDao.showPreview(ids.get(1),null,null));			
		return list;
	}


	@Override
	public GoodsSkuPO findSku(String goodsId) {		
		List<SkuDTO> findSku = shopDao.findSku(goodsId);
		GoodsSkuPO goodsSkuPO = shopDao.findGoodsInfo(goodsId);
		goodsSkuPO.setEvaluate(shopDao.findGoodsEvaluate(goodsId));		
		List<SkuDTO> selectSku = shopDao.selectSku(goodsId);
		goodsSkuPO.setSpecsFormat(shopDao.selectSpecsFormat(goodsId));
		goodsSkuPO.setSku(selectSku);
		return goodsSkuPO;		
	}


	@Override
	public ShowPreviewPO showPreview(Integer cId, Integer sort, String filter) {
		String sort1 = "";
		if(sort != null && sort == 1){
			sort1 = "ASC";
		}else if(sort != null && sort == 0){
			sort1 = "DESC"; //降
		}else{
			sort1 = null;
		}
		if(cId == null){
			return null;
		}
		
		ShowPreviewPO showPreview = shopDao.showPreview(cId,sort1,filter);
		return showPreview;
	}


	@Override
	public SkuItem selectByPrimaryKey(Integer itemId) {		
		return shopDao.selectByPrimaryKey(itemId);
	}


	@Override
	public String selectAttrLengthById(Integer itemId) {		
		return shopDao.selectAttrLengthById(itemId);
	}


	@Override
	public List<Address> getAddressById(Integer userId) {
		return shopDao.getAddressById(userId);		 
	}


	@Override
	public Integer addAddress(Address address) {		
		return shopDao.addAddress(address);
	}


	@Override
	public List<Cart> LoadingList(Integer userId, List<Integer> list) {		
		String object  = (String)redisTemplate.boundHashOps(REDIS_CART_LIST).get(userId+"");		
		List<Cart> cartList = JSONArray.parseArray(object, Cart.class);
		List<Cart> SelectedCartList = new ArrayList<>();
		for (Cart cart : cartList) {
			List<SkuItem> orderItemList = cart.getOrderItemList();
			for (SkuItem skuItem : orderItemList) {
				for (Integer skuId : list) {
					if(skuItem.getId() == skuId){
						//判断新的购物车里有没这店						
						if(!Judge(SelectedCartList,cart)){
							Cart c = new Cart();
							c.setSellerId(cart.getSellerId());
							c.setSeller(cart.getSeller());
							c.newOrderItemList();
							c.getOrderItemList().add(skuItem);							
							SelectedCartList.add(c);							
						}else{
							for (Cart cart2 : SelectedCartList) {
								if(cart2.getSellerId() == cart.getSellerId()){
									cart2.getOrderItemList().add(skuItem);
								}
							}	
						}
						
																										
					}
				}
			}
		}				
		return SelectedCartList;
	}


	private boolean Judge(List<Cart> selectedCartList, Cart cart) {
		if(selectedCartList == null && selectedCartList.size()==0){
			return false;
		}
		for (Cart cart1 : selectedCartList) {
			if(cart1.getSellerId() == cart.getSellerId()){
				return true;
			}
		}
		return false;
	}


	@Override
	public Address queryAddressById(Integer addressId) {		
		return shopDao.queryAddressById(addressId);
	}


	@Override
	public Integer addOrder(OrderDTO order) {		
		return shopDao.addOrder(order);
	}

	
	@Override
	public OrderDecomposeDTO getGoodsInfo(Integer skuId,Integer userId) {
		String object  = (String) redisTemplate.boundHashOps(REDIS_CART_LIST).get(userId+"");
		List<Cart> cartList = JSONArray.parseArray(object, Cart.class);
		OrderDecomposeDTO orderDec = new OrderDecomposeDTO();
		for (Cart cart : cartList) {
			List<SkuItem> orderItemList = cart.getOrderItemList();
			for (SkuItem skuItem : orderItemList) {
				if(skuId == skuItem.getId()){
					orderDec.setShopId(skuItem.getShopId());
					orderDec.setShopName(skuItem.getShopName());
					orderDec.setGoodsId(skuItem.getGoodsId());
					orderDec.setGoodsName(skuItem.getGoodsName());
					orderDec.setStatus(1);
					/*orderDec.setSuggestedPrice(skuItem.getMarketPrice());*/
					orderDec.setPrice(skuItem.getFavorablePrice());
					orderDec.setNum(skuItem.getNum());
					/*orderDec.setCreateTime(new Date());*/
					orderDec.setAmount(skuItem.getFavorablePrice()*skuItem.getNum());				
					orderDec.setAttributeValue(skuItem.getGroup_spec());
							
				}
			}
		}				
		return orderDec;				
	}


	@Override
	public Integer addOrderDecompose(OrderDecomposeDTO orderDec) {		
		return shopDao.addOrderDecompose(orderDec);
	}


	@Override
	public Boolean delCartBySkuId(List<Integer> skuIdList,Integer userId) {
		String object  = (String) redisTemplate.boundHashOps(REDIS_CART_LIST).get(userId+"");
		List<Cart> cartList = JSONArray.parseArray(object, Cart.class);
        for (int i = 0; i < cartList.size(); i++) {
        	List<SkuItem> orderItemList = cartList.get(i).getOrderItemList();
        	for (int j = 0; j < orderItemList.size(); j++) {
        		if(skuIdList.contains(orderItemList.get(j).getId())){
    				orderItemList.remove(j);
    				j--;
    			}
			}        	
        	if(cartList.get(i).getOrderItemList().size() == 0){
        		cartList.remove(i);
        		i--;
        	}       	        	
		}        
      //将最新的购物车列表数据写回redis
        String json = JSON.toJSONString(cartList);
        redisTemplate.boundHashOps(REDIS_CART_LIST).put(userId+"", json);       
        return true;		
	}


	@Override
	public List<OrderDetailsPo> getDetailsByOrderNo(String orderNo) {		
		return shopDao.getDetailsByOrderNo(orderNo);
	}


	@Override
	public List<OrderDTO> getLocalOrderByParam(String orderNo) {
		return shopDao.getLocalOrderByParam(orderNo);
	}


	@Override
	public List<OrderDecomposeDTO> getOrderDecList(String orderNo) {		
		return shopDao.getOrderDecList(orderNo);
	}


	@Override
	public void finishOrder(String orderNo, String transaction_id, String payerAccont, String sellerAccount,
			double payMoney, PayTypeEnum payType, String app, Map<String, String> map) throws OperationException {
		
		if(orderNo!= null){			
			List<OrderDTO> orderList = shopDao.getLocalOrderByParam(orderNo); //订单
			List<OrderDecomposeDTO> orderDecList = shopDao.getOrderDecList(orderNo); //订单拆单
			Double num = null; //拆单表金额之和
			Double money = orderList.get(0).getMoney(); //本地商品总额
			for (OrderDecomposeDTO orderDec : orderDecList) {
				num += orderDec.getNum()*orderDec.getAmount();
			}
			
			if(payMoney != num){
				throw new OperationException("更新失败", ErrorCodeEnum.FAILED); 
			}			
			
			shopDao.updataOrderStatusByOrderNo(orderNo,payType.getValue(),2); //更改订单表的状态及支付类型
			shopDao.updataOrderDecStatusByOrderNo(orderNo,2); //更改拆单表状态
						
			for (OrderDecomposeDTO orderDec : orderDecList) {
				OrderRecordDTO orderRec = new OrderRecordDTO();
				orderRec.setMoneyType(transaction_id);
				orderRec.setOrderNo(orderNo);
				orderRec.setMoney(orderDec.getAmount());
				orderRec.setPayCost(orderDec.getNum()*orderDec.getAmount());
				orderRec.setPayAccount(payerAccont);
				orderRec.setSellerAccount(sellerAccount);
				shopDao.addOrderRecord(orderRec);  //根据拆单表生成订单流水
			}									
		}						
	}
	
	
	
	@Override
	public void finishOrder(String orderNo, String transaction_id, String payerAccont, String sellerAccount,String payMoney ,PayTypeEnum wechat) {
		if(orderNo!= null){	
			
		List<OrderDecomposeDTO> orderDecList = shopDao.getOrderDecList(orderNo); //订单拆单
		shopDao.updataOrderStatusByOrderNo(orderNo,wechat.getValue(),2); //更改订单表的状态及支付类型
		shopDao.updataOrderDecStatusByOrderNo(orderNo,2); //更改拆单表状态
		
		for (OrderDecomposeDTO orderDec : orderDecList) {
			OrderRecordDTO orderRec = new OrderRecordDTO();
			orderRec.setMoneyType(transaction_id);
			orderRec.setOrderNo(orderNo);
			orderRec.setMoney(orderDec.getAmount());
			orderRec.setPayCost(orderDec.getNum()*orderDec.getAmount());
			orderRec.setPayAccount(payerAccont);
			orderRec.setSellerAccount(sellerAccount);
			Date date = new Date();
			orderRec.setCreateTIme(date);
			shopDao.addOrderRecord(orderRec);  //根据拆单表生成订单流水
		}	
		
		}		
	}


	@Override
	public String getOrderAlipaySignInfo(PaymentOrderDTO order, String notifyUrl,
			Map<String, String> extraData) {
				
		 	String orderNumber = order.getOrderNumber();
	        int orderType = order.getOrderType();
	        Double total = NumberUtils.changeF2Y(String.valueOf(order.getCharge()));
	        String subject = "生命守护";
	        String body = "购买商品" + total + "元";;                
            PayReturnOrderTypeEnum payReturnOrderType = PayReturnOrderTypeEnum.SHOP;
	           
	        extraData.put("orderType", String.valueOf(payReturnOrderType.getValue()));
	        logger.info(String.format("支付宝支付参数total:%s,subject:%s,body:%s,orderNumber:%s,extraData:%s,notifyUrl:%s", total,subject,body,orderNumber,extraData,notifyUrl));
	        String orderInfo = AlipayService.getOrderInfo(total, subject, body, orderNumber, extraData, notifyUrl);
	        return orderInfo;
		
	}


	@Override
	public List<Map<String, Object>> getAdvertisement() {		
		return shopDao.getAdvertisement();
	}


	@Override
	public void delAddress(Integer id) {
		shopDao.delAddress(id);		
	}


	@Override
	public void updateAddressDefault(Integer id, Integer isdef) {
		shopDao.updateAddressDefault(id,isdef);		
	}


	@Override
	public List<OrderPO> getOrderAll(Integer status ,Integer userId) {		
		return shopDao.getOrderAll(status ,userId);
	}


	@Override
	public void delOrder(String orderNo) {		
		shopDao.delOrder(orderNo);
		shopDao.delOrderDec(orderNo);
	}


	@Override
	public Integer addOrderEvaluate(OrderEvaluateDTO orderEva) {
		return shopDao.addOrderEvaluate(orderEva);		
	}


	@Override
	public List<OrderPO> selectOrderNoEvaluate(Integer userId) {
		return shopDao.selectOrderNoEvaluate(userId);
		
	}


	@Override
	public UserPO getUserInfo(Integer userId) {
		return shopDao.getUserInfo(userId);		
	}


	@Override
	public List<GoodsDTO> getGoodsByCid(Integer cid,Integer sort,String filter) {
		return shopDao.getOrderByCid(cid,sort,filter);		 
	}


	@Override
	public PaginationDTO<Map<String, Object>> getShopList(final Map<String, Object> search, Integer page, Integer pageSize) {
		Paging<Map<String, Object>> paging = new Paging<>(page, pageSize);
		paging.setQueryProc(new IPagingQueryProc<Map<String,Object>>() {			
			@Override
			public int queryTotal() {
				return ShopServiceImpl.this.shopDao.pagingShopTotal(search);
			}
			
			@Override
			public List<Map<String, Object>> queryData(int startRow, int pageSize) {
				search.put("startRow", startRow);
				search.put("pageSize", pageSize);
				return shopDao.selectShopBySearch(search);
			}
		});		
		PaginationDTO<Map<String, Object>> pDto = paging.getPagination();
		pDto.setPageSize(pageSize);
		return pDto;
	}

	/**
	 * 
	 * @return n:执行影响行数
	 */
	@Override
	public int changeState(Integer action, Integer id) {
		if(action == null) {
			return 0;
		}
		return shopDao.chanageState(action, id);
	}
	
	/**
	 * 审核商铺
	 */
	@Override
	public Integer authitShop(ShopAuthitionDTO authitionDTO) {
		Integer pass = authitionDTO.getPass();
		Integer action = null;
		if(pass == null || pass == ShopAuthit.不通过.getCode()) {
			action = ShopState.审核不通过.getCode();
		} else {
			action = ShopState.审核通过.getCode();
		}
		this.changeState(action, authitionDTO.getShopId());
		authitionDTO.setAuthitTime(new Date());
		shopDao.addAuthit(authitionDTO);
		return action;
	}


	@Override
	public void imageChange(Integer userId, String img) {
		shopDao.imageChange(userId,img);		
	}


	@Override
	public void OrderOut() {
		 List<com.lifeshs.shop.OrderDecomposeDTO> orderOutList = shopDao.OrderOut();
		 Set<String>ids = new HashSet<String>();
		 for (OrderDecomposeDTO orderDec : orderOutList) {
			 shopDao.ResumeInventoryAndSales(orderDec.getGoodsId(),orderDec.getAttributeValue(),orderDec.getNum());
			 ids.add(orderDec.getOrderNo());
		 }
		 for (String str : ids) {
			 shopDao.delOrder(str);
			 shopDao.delOrderDec(str);			 
		}		 		 
	}


	@Override
	public void deductStock(Integer skuId, Integer num) {
		shopDao.deductStock(skuId,num);		
	}


	@Override
	public void ResumeInventoryAndSales(String goodsId, String attributeValue, Integer num) {
		shopDao.ResumeInventoryAndSales(goodsId, attributeValue, num);		
	}


	@Override
	public void AutoCompletionOrder() {
		
		List<OrderDecomposeDTO>orderDecList = shopDao.selectAutoCompletionOrder();
		HashSet<String>orderNoSet = new HashSet<String>();
		for (OrderDecomposeDTO orderDecomposeDTO : orderDecList) {
			Integer id = orderDecomposeDTO.getId();
			shopDao.updateOrderDecStatusById(id); //更改拆单状态
			orderNoSet.add(orderDecomposeDTO.getOrderNo()); 
		}
		Boolean temp = true;
		Integer status = 3; //完成状态
		for (String st : orderNoSet) {
			List<OrderDecomposeDTO> orderDecList2 = shopDao.getOrderDecList(st);
			for (OrderDecomposeDTO orderDec : orderDecList2) {
				if(orderDec.getStatus()!=3){
					temp = false; 
				}				
			}
			if(temp){
				shopDao.updateOrderStatusByOrderNo(st,status);//订单所对应的所有拆单都为完成即将订单改为完成
			}			
		}
		
	}


	@Override
	public void ReceiptConfirm(String shippingNo,String orderNo) {
		shopDao.ReceiptConfirm(shippingNo);
		
		Boolean temp = true;
		Integer status = 3; //完成状态
		List<OrderDecomposeDTO> orderDecList = shopDao.getOrderDecList(orderNo);
		for (OrderDecomposeDTO orderDec : orderDecList) {
			if(orderDec.getStatus()!=3){
				temp = false; 
			}				
		}
		if(temp){
			shopDao.updateOrderStatusByOrderNo(orderNo,status);//订单所对应的所有拆单都为完成即将订单改为完成
		}	
		
	}


	@Override
	public void addSkuSales(Integer id, Integer num) {
		shopDao.addSkuSales(id,num);
		
	}
	
	
}
