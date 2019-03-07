package com.lifeshs.thirdservice;

import java.util.List;
import java.util.Map;

import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.UserAddressPO;
import com.lifeshs.po.drugs.DrugsPO;
import com.lifeshs.po.drugs.DrugsTypePO;
import com.lifeshs.po.drugs.OrderLogisticsPO;
import com.lifeshs.po.drugs.OrderPO;
import com.lifeshs.po.drugs.OrderProductPO;
import com.lifeshs.po.drugs.PrescriptionType;
import com.lifeshs.po.drugs.ProductAttribute;
import com.lifeshs.service1.page.Paging;

public interface DrugsService {
    
    //<!-- app调用 -->
    //查找药品类型
    public List<DrugsTypePO> findDrusType();
    
    //根据名称查找药品
    public List<DrugsPO> findDrusListByName(String name, String firstClassName, String secondClassName, int startRow, int pageSize);

    //医生添加订单
    public int addLocalOrder(int userId, OrderPO orderPO);
    
    //订单查询
    public List<OrderLogisticsPO> findOrder(List<OrderPO> orderList, String method);
    
    //用户批量查询订单
    public List<OrderLogisticsPO> findOrderList(String method, int pageIndex, int pageSize);
    
    public DrugsPO getDrugsByCode(String productCode);
    
    /**
     * 得到用户的上门地址
     * @param userId
     * @return
     */
    UserAddressPO findUserAddress(int userId);
    
    UserAddressPO findUserAddressById(int id);
    
    
    //修改订单
    int updateOrder(OrderPO order);
    
    
//    //支付订单
//    public String paymentOrder(int userId, OrderPO orderPO);
    
    
    
    
    
    
    
    
    
    
//    //订单批量查询
//    public List<OrderPO> findOrderList(String orderNo, String method);
    
    //查找订单
    public List<OrderPO> getLocalOrderByParam(String orderNo);
    
    //物流信息
    public String logisticsInfo(String orderNo);
    
//  //订单推送
//  public String orderPush(OrderPO orderPO);
    
    public List<OrderProductPO> getOrderProductList(String orderNo);
    
    /**
     *   完成回调
     * @param orderNumber 订单好
     * @param tradeNumber  交易号
     * @param payerAccount  买家
     * @param sellerAccount 卖家
     * @param payMoney  支付金额
     * @param alipay       支付类型
     * @param deviceType  支付设备类型
     * @param map 订单参数
     * @throws OperationException
     */
    void finishOrder(String orderNumber, String tradeNumber, String payerAccount, String sellerAccount,
                            double payMoney, PayTypeEnum alipay, String deviceType, Map<String,String> map) throws OperationException;
    
    
    
    //<!-- 网站调用 -->
    //查询药品
    public Paging<DrugsPO> listLocalDrugs(String productName,String productAttribute,String prescriptionType,int curPage,int pageSize);
    
    //数据同步插入
    public String saveDrugsList(List<DrugsPO> drugsPO,String syncTime);
    
    //保存同步记录
    public int saveSyncRecord(int userId, String syncTime,String nextTime, String content);
    
    //获取最后一次同步时间
    public String getSyncLastDate();
    
    List<PrescriptionType> getPrescriptionType();
    List<ProductAttribute> getProductAttribute();
    
    
    
//    //支付宝支付完成后调用
//    public void finishOrderPrivate(String orderNumber, String tradeNumber, String payerAccount, String sellerAccount,
//            Double payMoney, PayTypeEnum alipay, String deviceType) throws OperationException;
    
}
