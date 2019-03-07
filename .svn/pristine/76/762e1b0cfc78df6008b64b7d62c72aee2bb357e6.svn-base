package com.lifeshs.dao.shop;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.lifeshs.po.shop.CatalogOne;
import com.lifeshs.po.shop.GoodsSkuPO;
import com.lifeshs.po.shop.OrderDetailsPo;
import com.lifeshs.po.shop.OrderPO;
import com.lifeshs.po.shop.RelationPO;
import com.lifeshs.po.shop.SearchSkuDisplayPO;
import com.lifeshs.po.shop.ShowPreviewPO;
import com.lifeshs.po.shop.SkuItem;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.shop.Address;
import com.lifeshs.shop.GoodsDTO;
import com.lifeshs.shop.OrderDTO;
import com.lifeshs.shop.OrderDecomposeDTO;
import com.lifeshs.shop.OrderEvaluateDTO;
import com.lifeshs.shop.OrderRecordDTO;
import com.lifeshs.shop.ShopAuthitionDTO;
import com.lifeshs.shop.ShopDTO;
import com.lifeshs.shop.SkuDTO;
import com.sun.jdi.IntegerType;

@Repository("shop_dao")
public interface ShopDao {
	/**
	 * 查询1-4级目录
	 * @return
	 */
	List<CatalogOne> findCatalogAll(@Param("id")Integer id);
	
				
	/**
	 * 获取一级目录
	 * @return
	 */
	List<CatalogOne> findCatalogOne();
	
	/**
	 * 模糊查询店家
	 * @param keyword
	 * @return
	 */
	List<ShopDTO> SearchShop(@Param("keyword")String keyword);
	/**
	 * 模糊查询商品
	 * @param keyword
	 * @return
	 */
	List<SearchSkuDisplayPO> SearchSku(@Param("keyword") String keyword);
	
	/**
	 * 根据id 获取首页商品展示
	 * @param oneColumn
	 * @param filter 
	 * @param sort 
	 * @return
	 */
	ShowPreviewPO showPreview(@Param("id")Integer oneColumn,@Param("sort") String sort,@Param("filter") String filter);
	
	/**
	 * 根据id 查找相对应的sku
	 * @param skuId
	 * @return
	 */
	List<SkuDTO> findSku(@Param("id") String goodsId);
	
	/**
	 * 根据id查找对应的商品评价
	 * @param goodsId
	 * @return
	 */
	List<OrderEvaluateDTO> findGoodsEvaluate(@Param("goodsId")String goodsId);
	
	/**
	 * 根据id查找所有属性及属性值
	 * @param goodsId
	 * @return
	 */
	List<RelationPO> findSkuAttr(@Param("goodsId")String goodsId);
	
	/**
	 * 查询sku 并查询属性
	 * @param length
	 * @param goodsId
	 * @return
	 */
	SkuDTO selectSku(@Param("length")int length,@Param("id") Integer goodsId);
	
	/**
	 * 查询信息
	 * @param goodsId
	 * @return
	 */
	GoodsSkuPO findGoodsInfo(String goodsId);
	
	
	/**
	 * 查询skuid  对应信息
	 * @param itemId
	 * @return
	 */
	SkuItem selectByPrimaryKey(@Param("id")Integer itemId);
	
	
	/**
	 * 根据id 查找attr 
	 * @param itemId
	 * @return
	 */
	String selectAttrLengthById(@Param("itemId")Integer itemId);
	
	/**
	 * 根据用户id查找地址
	 * @param userId
	 * @return
	 */
	List<Address> getAddressById(@Param("userId")Integer userId);
	
	
	/**
	 * 添加地址
	 * @param address
	 */
	Integer addAddress(Address address);
	
	/**
	 * 根据id查询地址
	 * @param addressId
	 * @return
	 */
	Address queryAddressById(@Param("id")Integer addressId);
	
	/**
	 * 添加订单
	 * @param order
	 * @return
	 */
	Integer addOrder(OrderDTO order);
	
	/**
	 * 添加拆单数据
	 * @param orderDec
	 * @return
	 */
	Integer addOrderDecompose(OrderDecomposeDTO orderDec);
	
	/**
	 * 根据prderNo 获取订单详情
	 * @param orderNo
	 * @return
	 */
	List<OrderDetailsPo> getDetailsByOrderNo(@Param("orderNo")String orderNo);

	/**
	 * 根据订单号查订单
	 * @param orderNo
	 * @return
	 */
	List<OrderDTO> getLocalOrderByParam(@Param("orderNo")String orderNo);

	/**
	 * 根据订单号查订单拆单
	 * @param orderNo
	 * @return
	 */
	List<OrderDecomposeDTO> getOrderDecList(@Param("orderNo")String orderNo);

	/**
	 * 根据orderNo更改
	 * @param orderNo
	 * @param paymentType
	 * @param status
	 * @return
	 */
	Integer updataOrderStatusByOrderNo(@Param("orderNo")String orderNo, @Param("paymentType")int paymentType, @Param("status")int status);

	/**
	 * 根据orderNo更改拆单表状态
	 * @param orderNo
	 * @param paymentType
	 * @param status
	 * @return
	 */
	Integer updataOrderDecStatusByOrderNo(@Param("orderNo")String orderNo, @Param("status")int status);

	/**
	 * 
	 * 新增订单流水表
	 * @param orderRec
	 * @return
	 */
	Integer addOrderRecord(OrderRecordDTO orderRec);

	/**
	 * 获取门户广告位图片及索引id
	 * @return
	 */
	List<Map<String, Object>> getAdvertisement();

	/**
	 * 用户根据id删除收货地址
	 * @param id
	 */
	void delAddress(@Param("id")Integer id);

	/**
	 * 设置地址默认
	 * @param isdef
	 */
	void updateAddressDefault(@Param("id")Integer id,@Param("selected")Integer isdef);

	/**
	 * 获取用户全部订单
	 * @param userId
	 * @return
	 */
	List<OrderPO> getOrderAll(@Param("status")Integer status ,@Param("userId")Integer userId);

	/**
	 * 根据订单编号删除订单
	 * @param orderNo
	 */
	void delOrder(@Param("orderNo")String orderNo);

	/**
	 * 根据订单变化删除订单拆单表数据
	 * @param orderNo
	 */
	void delOrderDec(@Param("orderNo")String orderNo);

	/**
	 * 添加评论
	 * @param orderEva
	 * @return
	 */
	Integer addOrderEvaluate(OrderEvaluateDTO orderEva);

	/**
	 * 获取交易完成但是未评价的订单
	 * @param userId
	 */
	List<OrderPO> selectOrderNoEvaluate(@Param("userId")Integer userId);

	/**
	 * 根据id 获取规格json字符串
	 * @param goodsId
	 * @return
	 */
	List<SkuDTO> selectSku(@Param("goodsId")String goodsId);

	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	UserPO getUserInfo(@Param("userId")Integer userId);

	/**
	 * 根据商品id获取json规格串
	 * @param goodsId
	 * @return
	 */
	String selectSpecsFormat(@Param("goodsId")String goodsId);

	/**
	 * 根据cid 拿到商品
	 * @param cid
	 * @return
	 */
	List<GoodsDTO> getOrderByCid(@Param("cid")Integer cid,@Param("sort")Integer sort,@Param("filter")String filter);

	

List<Map<String, Object>> selectShopBySearch(Map<String, Object> map);
	
	int pagingShopTotal(Map<String, Object> map);
	
	/**
	 * 审核&冻结&解冻 
	 * @param action
	 * @param id
	 * @return
	 */
	int chanageState(@Param("action") Integer action, @Param("id") Integer id);
	
	/**
	 * 插入商铺审核
	 * @param authitionDTO
	 * @return
	 */
	int addAuthit(ShopAuthitionDTO authitionDTO);
	
	Map<String, Object> getOneById(Integer id);

	/**
	 * 查找商品展示id
	 * @return
	 */
	List<Integer> selectPreviewId();

	/**
	 * 跟换用户头像
	 * @param userId
	 * @param img
	 */
	void imageChange(@Param("id")Integer userId, @Param("img")String img);

	
	int saveShop(ShopDTO shop);

	/**
	 * 查询未支付订单超过24小时
	 */
	List<com.lifeshs.shop.OrderDecomposeDTO> OrderOut();

	/**
	 * 恢复库存
	 * @param goodsId
	 * @param attributeValue
	 * @param num
	 */
	void ResumeInventoryAndSales(@Param("goodsId")String goodsId, @Param("attributeValue")String attributeValue, @Param("num")Integer num);

	/**
	 * 扣除库存
	 * @param skuId
	 * @param num
	 */
	void deductStock(@Param("skuId")Integer skuId, @Param("num")Integer num);

	/**
	 * 获取需要自动确定的订单
	 */
	List<OrderDecomposeDTO> selectAutoCompletionOrder();

	/**
	 * 根据id修改拆单为完成状态
	 * @param id
	 */
	void updateOrderDecStatusById(@Param("id")Integer id);

	/**
	 * 根据订单编号更改订单状态
	 * @param st
	 */
	void updateOrderStatusByOrderNo(@Param("orderNo")String st,@Param("status")Integer status);

	/**
	 * 确认收货，根据物流编号更改拆单表状态
	 * @param shippingNo
	 */
	void ReceiptConfirm(@Param("shippingNo")String shippingNo);

	/**
	 *  根据skuId增加销量
	 * @param id
	 * @param num
	 */
	void addSkuSales(@Param("skuId")Integer id, @Param("num")Integer num);
	

	


	
}
