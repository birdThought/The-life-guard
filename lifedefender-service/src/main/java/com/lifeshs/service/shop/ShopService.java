package com.lifeshs.service.shop;

import java.util.List;
import java.util.Map;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.shop.Cart;
import com.lifeshs.po.shop.CatalogOne;
import com.lifeshs.po.shop.GoodsSkuPO;
import com.lifeshs.po.shop.OrderDetailsPo;
import com.lifeshs.po.shop.OrderPO;
import com.lifeshs.po.shop.SearchPo;
import com.lifeshs.po.shop.ShowPreviewPO;
import com.lifeshs.po.shop.SkuItem;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.pojo.order.PaymentOrderDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.shop.Address;
import com.lifeshs.shop.GoodsDTO;
import com.lifeshs.shop.OrderDTO;
import com.lifeshs.shop.OrderDecomposeDTO;
import com.lifeshs.shop.OrderEvaluateDTO;
import com.lifeshs.shop.ShopAuthitionDTO;

public interface ShopService {
	

	/**
	 * 查询全部分级
	 * @return
	 */
	List<CatalogOne> findCatalogAll();
	
	/**
	 * 指定一级目录查询目录
	 * @param id
	 * @return
	 */	
	List<CatalogOne> findCatalogById(Integer id);
	
	/**
	 * 搜索模糊查询商店及商品
	 * @param keyword
	 * @return
	 */
	SearchPo shopSearch(String keyword);
	
	/**
	 * 根据id获取首页商品展示及标签
	 * @param oneColumn
	 * @param twoColumn
	 * @return
	 */
	List<ShowPreviewPO> showPreview(Integer oneColumn, Integer twoColumn);
	
	/**
	 * 根据id 查找相对应的sku
	 * @param skuId
	 * @return
	 */
	GoodsSkuPO findSku(String goodsId);
	
	/**
	 * 根据cid 查找商品及排序过滤
	 * @param cId
	 * @param sort
	 * @param filter
	 * @return
	 */
	ShowPreviewPO showPreview(Integer cId, Integer sort, String filter);
	
	/**
	 * 根据skuID 查找对应的sku商品信息
	 * @param itemId
	 * @return
	 */
	SkuItem selectByPrimaryKey(Integer itemId);
	
	/**
	 * 根据id 查找attr 
	 * @param itemId
	 * @return
	 */
	String selectAttrLengthById(Integer itemId);
	
	/**
	 * 根据用户id查找地址
	 * @param userId
	 * @return
	 */
	List<Address> getAddressById(Integer userId);
	
	/**
	 * 添加地址
	 * @param address
	 */
	Integer addAddress(Address address);
	
	/**
	 * 结算页面获取结算清单
	 * @param userId
	 * @param list
	 * @return
	 */
	List<Cart> LoadingList(Integer userId, List<Integer> list);
	
	/**
	 * 根据id查询地址
	 * @param addressId
	 * @return
	 */
	Address queryAddressById(Integer addressId);
	
	/**
	 * 添加订单
	 * @param order
	 * @return
	 */
	Integer addOrder(OrderDTO order);
	
	/**
	 * 根据skuId去redis那对应商品
	 * @param skuId
	 */
	OrderDecomposeDTO getGoodsInfo(Integer skuId,Integer userId);
	
	/**
	 * 添加拆单数据
	 * @param orderDec
	 * @return
	 */
	Integer addOrderDecompose(OrderDecomposeDTO orderDec);
	
	/**
	 * 根据sku集合删除redis中的对应数据
	 * @param skuIdList
	 * @return
	 */
	Boolean delCartBySkuId(List<Integer> skuIdList,Integer userId);
	
	/**
	 * 根据prderNo 获取订单详情
	 * @param orderNo
	 * @return
	 */
	List<OrderDetailsPo> getDetailsByOrderNo(String orderNo);
	
	/**
	 * 根据订单号查订单
	 * @param orderNo
	 * @return
	 */
	List<OrderDTO> getLocalOrderByParam(String orderNo);
	
	/**
	 * 根据订单号查订单拆单
	 * @param orderNo
	 * @return
	 */
	List<OrderDecomposeDTO> getOrderDecList(String orderNo);
	
	/**
	 * 微信回调
	 * @param out_trade_no
	 * @param transaction_id
	 * @param payerAccont
	 * @param sellerAccount
	 * @param payMoney
	 * @param wechat
	 * @param string
	 * @param map
	 */
	void finishOrder(String out_trade_no, String transaction_id, String payerAccont, String sellerAccount,
			double payMoney, PayTypeEnum wechat, String string, Map<String, String> map) throws OperationException ;
	/**
	 * 返回ali支付签名
	 * @param paymentOrder
	 * @param aPP_RECHARGE_NOTIFY_URL
	 * @param extraData
	 * @return
	 */
	String getOrderAlipaySignInfo(PaymentOrderDTO paymentOrder, String aPP_RECHARGE_NOTIFY_URL,
			Map<String, String> extraData);
	
	
	/**
	 * 获取门户广告位图片及索引id
	 * @return
	 */
	List<Map<String, Object>> getAdvertisement();
	
	/**
	 * 用户根据id删除收货地址
	 * @param id
	 */
	void delAddress(Integer id);
	
	/**
	 * 设置地址默认
	 * @param id
	 * @param def
	 */
	void updateAddressDefault(Integer id, Integer def);
	
	/**
	 * 获取用户全部订单
	 * @param userId
	 * @return
	 */
	List<OrderPO> getOrderAll(Integer status,Integer userId);
	
	/**
	 * 根据订单编码删除订单及拆单表
	 * @param orderNo
	 * @return
	 */
	void delOrder(String orderNo);
	
	/**
	 * 添加商品评价
	 * @param orderEva
	 */
	Integer addOrderEvaluate(OrderEvaluateDTO orderEva);
	
	/**
	 * 获取交易完成但是未评价的订单
	 * @param userId
	 */
	List<OrderPO> selectOrderNoEvaluate(Integer userId);
	
	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	UserPO getUserInfo(Integer userId);
	
	/**
	 * 支付回调更改信息
	 * @param out_trade_no
	 * @param transaction_id
	 * @param wechat
	 */
	void finishOrder(String out_trade_no, String transaction_id, String payerAccont, String sellerAccount,String payMoney,PayTypeEnum wechat);
	
	/**
	 * 根据cid 获取商品
	 * @param cid
	 * @return
	 */
	List<GoodsDTO> getGoodsByCid(Integer cid,Integer sort,String filter);

	
	PaginationDTO<Map<String, Object>> getShopList(Map<String, Object> search, Integer page, Integer pageSize);
	
	/**
	 * 冻结&解冻
	 * @param action
	 * @param id
	 * @return
	 */
	int changeState(Integer action, Integer id);
	
	/**
	 * 商铺审核
	 * @param authitionDTO
	 * @return state
	 */
	Integer authitShop(ShopAuthitionDTO authitionDTO);
	
	/**
	 * 更换用户头像
	 * @param userId
	 * @param img
	 */
	void imageChange(Integer userId, java.lang.String img);
	
	/**
	 * 未支付订单过期
	 */
	void OrderOut();
	
	/**
	 * 根据skuid扣减库存
	 * @param skuId
	 * @param integer
	 */
	void deductStock(Integer skuId, Integer integer);
	
	/**
	 * 恢复库存
	 * @param goodsId
	 * @param attributeValue
	 * @param num
	 */
	void ResumeInventoryAndSales(java.lang.String goodsId, java.lang.String attributeValue, Integer num);
	
	/**
	 * 订单到时自动确认
	 */
	void AutoCompletionOrder();
	
	/**
	 * 确认收货，根据物流编号更改拆单表状态
	 * @param shippingNo
	 */
	void ReceiptConfirm(String shippingNo,String orderNo);
	
	/**
	 * 根据skuId增加销量
	 * @param parseInt
	 * @param num
	 */
	void addSkuSales(Integer parseInt, Integer num);

	
	
}
