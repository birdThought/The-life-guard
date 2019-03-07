package com.lifeshs.thirdservice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.common.Constant;
import com.lifeshs.common.constants.common.jianKe.JianKe;
import com.lifeshs.common.constants.common.jianKe.OrderStatusEnum;
import com.lifeshs.common.constants.common.order.PayReturnOrderTypeEnum;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.component.weChat.UnifiedOrderUtils;
import com.lifeshs.component.weChat.WXPayUtil;
import com.lifeshs.dao1.drugs.DrugsDao;
import com.lifeshs.dao1.member.IMemberAddressDao;
import com.lifeshs.po.UserAddressPO;
import com.lifeshs.po.drugs.DrugsPO;
import com.lifeshs.po.drugs.DrugsTypePO;
import com.lifeshs.po.drugs.LogisticsPO;
import com.lifeshs.po.drugs.OrderLogisticsPO;
import com.lifeshs.po.drugs.OrderPO;
import com.lifeshs.po.drugs.OrderProductPO;
import com.lifeshs.po.drugs.OrderProductPushPO;
import com.lifeshs.po.drugs.OrderPushPO;
import com.lifeshs.po.drugs.PrescriptionType;
import com.lifeshs.po.drugs.ProductAttribute;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.thirdservice.DrugsService;
import com.lifeshs.utils.DateTimeUtil;
import com.lifeshs.utils.JSONHelper;
import com.lifeshs.utils.NumberUtils;

import net.sf.json.JSONArray;

@Service("drugsService")
public class DrugsServiceImpl extends AppNormalService implements DrugsService {
    private final Logger logger = LoggerFactory.getLogger(DrugsServiceImpl.class);

    @Autowired
    DrugsDao drugsDao;
    
    @Autowired
    IMemberAddressDao memberAddressDao;
    
    /**
     * 
     *  查找药品类型  
     *  @author liaoguo
     *	@DateTime 2018年6月25日 上午11:28:16
     *  @serverCode 服务代码
     *  @return    
     *  @see com.lifeshs.thirdservice.DrugsService#findDrusType()
     */
    public List<DrugsTypePO> findDrusType(){
        return drugsDao.findDrusType();
    }

    /**
     * 
     * 根据名称查找药品
     * 
     * @author liaoguo
     * @DateTime 2018年6月11日 下午4:40:51
     * @serverCode 服务代码
     * @param name
     * @param startRow
     * @param pageSize
     * @return
     * @see com.lifeshs.thirdservice.DrugsService#findDrudsByName(java.lang.String,
     *      int, int)
     */
    @Override
    public List<DrugsPO> findDrusListByName(String productName,String firstClassName, String secondClassName, int startRow, int pageSize) {
        return drugsDao.findDrusListByName(productName, firstClassName, secondClassName, startRow, pageSize);
    }

    /**
     * 
     * 添加订单
     * 
     * @author liaoguo
     * @DateTime 2018年6月13日 下午4:38:33
     * @serverCode 服务代码
     * @param orderPO
     * @return
     * @see com.lifeshs.thirdservice.DrugsService#addOrder(com.lifeshs.po.drugs.OrderPushPO)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addLocalOrder(int userId, OrderPO orderPO) {
        // 生成订单号
        String orderNo = AlipayService.createOrderNumber();

        orderPO.setStatus(1);
        orderPO.setOrderNo(orderNo);
        orderPO.setUserId(userId);
        int result = drugsDao.addLocalOrder(orderPO);

        if (result == 1) {
            OrderProductPO product = null;
            List<OrderProductPO> orderProduct = orderPO.getOrderProductList();
            for (int i = 0; i < orderProduct.size(); i++) {
//                DrugsPO drugs = drugsDao.getDrugsByCode(orderProduct.get(i).getProductCode());
                product = new OrderProductPO();
                product = orderProduct.get(i);
                product.setOrderNo(orderNo);
                String productName = StringEscapeUtils.unescapeHtml4(orderProduct.get(i).getProductName());
                product.setProductName(productName);
//                product.setProductName(drugs.getProductName());
            }
            result = drugsDao.addOrderProduct(orderProduct);
            logger.info("添加订单:" + result);
        }

        return result;
    }

    /**
     * 
     * 订单查询
     * 
     * @author liaoguo
     * @DateTime 2018年6月19日 上午11:13:23
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<OrderLogisticsPO> findOrderList(String method, int pageIndex, int pageSize) {

        // 获取本地订单信息
        List<OrderPO> orderList = drugsDao.getOrderList((pageIndex - 1) * pageSize, pageSize);
        List<OrderLogisticsPO> list = findOrderInfo(orderList, method);

        return list;
    }

    /**
     * 
     * 单个订单查询
     * 
     * @author liaoguo
     * @DateTime 2018年6月7日 下午2:09:16
     * @serverCode 服务代码
     * @param orderNo
     * @param method
     * @return
     * @see com.lifeshs.thirdservice.DrugsService#findOrder(java.lang.String,java.lang.String)
     */
    @Override
    public List<OrderLogisticsPO> findOrder(List<OrderPO> orderList, String method) {
//        // 获取本地订单信息
//        List<OrderPO> orderList = getLocalOrderByParam(orderNo);
        //调用健客接口、并更新本地信息
        List<OrderLogisticsPO> list = findOrderInfo(orderList, method);
        
        return list;
    }

    /**
     * 
     * 接口订单查询
     * 
     * @author liaoguo
     * @DateTime 2018年6月7日 下午2:06:34
     *
     * @param method
     * @param orderNo
     * @return
     */
    private List<OrderPO> findOrderInterface(String method, String externalOrderNo) {

        // 对未完成的订单进行同步
        Map<String, String> headers = new HashMap<String, String>();
        SortedMap<String, Object> params = new TreeMap<String, Object>();

        String key = JianKe.LOGIN_PWD;
        String cid = JianKe.LOGIN_NAME;
        String signMethod = Constant.MD5;
        String randomStr = WXPayUtil.generateNonceStr().substring(0, 8);
        String url = JianKe.INTERFACE_URL;
        headers.put("Content-Type", "application/json");

        // 参数设置
        params.put("method", method);
        params.put("cid", cid);
        params.put("signMethod", signMethod);
        params.put("randomStr", randomStr);
        params.put("orderNo", externalOrderNo);

        // 签名设置
        String sign = WeChatHttpUtils.createSign(params, key);
        params.put("sign", sign);

        // 将Map转换成json
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(params);
        String josn = jsonObject.toString();
        System.out.println("params:" + params);
        System.out.println("josn:" + josn);

        // 接口调用
        String result = WeChatHttpUtils.httpsRequest(url, "POST", josn, headers);

        // json转实体
        List<OrderPO> resultList = (List<OrderPO>) JSONArray.toList(JSONArray.fromObject(result), OrderPO.class);

        return resultList;
    }

    /**
     * 
     *  服务注解
     *  @author liaoguo
     *  @DateTime 2018年6月25日 下午2:09:10
     *
     *  @param orderList
     *  @param method
     *  @return
     */
    @SuppressWarnings("unchecked")
    private List<OrderLogisticsPO> findOrderInfo (List<OrderPO> orderList, String method){
        String externalOrderNo = "";
        String result = "";
        OrderLogisticsPO orderLogisticsInfo = null;
        List<OrderPO> list = null;
        List<OrderLogisticsPO> orderLogistics = new ArrayList<OrderLogisticsPO>();
        
        // 筛选出已支付订单、健客订单号
        for (OrderPO order : orderList) {
            // 收集已支付状态的订单号
            if (StringUtils.isNotBlank(order.getExternalOrderNo()) && 2 == order.getStatus()) {
                externalOrderNo += order.getExternalOrderNo() + ",";
                orderList.remove(order);
            }
        }
        
        if(StringUtils.isNotBlank(externalOrderNo)){
            // 进行健客接口调用
            externalOrderNo = externalOrderNo.substring(0, externalOrderNo.length() - 1);
            list = findOrderInterface(method, externalOrderNo);
            
            // 修改本地库
            updateOrder(list);
            
            //合并物流信息
            orderList.addAll(list);
            BeanUtils.copyProperties(orderLogistics, orderList);
            
            //物流信息
            for(int i = 0; i < orderLogistics.size(); i++){
                orderLogisticsInfo = new OrderLogisticsPO();
                orderLogisticsInfo = orderLogistics.get(i);
                
                //获取物流信息
                result = logisticsInfo(orderLogisticsInfo.getOrderNo());
                
                //解析物流信息
                net.sf.json.JSONObject logisJson = net.sf.json.JSONObject.fromObject(result);
                String shippingCode = logisJson.getString("shippingCode").toString();
                String shippingName = logisJson.getString("shippingName");
                String shippingNo = logisJson.getString("shippingNo");
                List<LogisticsPO> logisList = logisJson.getJSONArray("mapList");
                
                //设置物流信息
                orderLogisticsInfo.setShippingCode(shippingCode);
                orderLogisticsInfo.setShippingName(shippingName);
                orderLogisticsInfo.setShippingNo(shippingNo);
                orderLogisticsInfo.setLogisticsList(logisList);
            }
        }
        
        return orderLogistics;
    }
    
    /**
     * 
     * 修改本地库
     * 
     * @author liaoguo
     * @DateTime 2018年6月19日 下午1:56:39
     *
     * @param resultList
     */
    private void updateOrder(List<OrderPO> resultList) {
        OrderPO order = null;
        OrderProductPO product = null;
        // 同步数据更新本地库
        for (int i = 0; i < resultList.size(); i++) {
            order = new OrderPO();
            order = resultList.get(i);
            order.setExternalOrderNo(order.getOrderNo());
            drugsDao.updateOrder(order);
            for (int j = 0; j < order.getOrderProductList().size(); i++) {
                product = new OrderProductPO();
                product = order.getOrderProductList().get(i);
                String currentOrderNo = drugsDao.getLocalOrderByParam(order.getExternalOrderNo()).get(0).getOrderNo();
                drugsDao.updateProduct(product, currentOrderNo);
            }
        }
    }

    /**
     * 
     * 查询订单
     * 
     * @author liaoguo
     * @DateTime 2018年6月14日 上午10:30:47
     *
     * @param orderNo
     * @return
     */
    public List<OrderPO> getLocalOrderByParam(String orderNo) {
        return drugsDao.getLocalOrderByParam(orderNo);
    }

    /**
     * 
     * 物流信息
     * 
     * @author liaoguo
     * @DateTime 2018年6月7日 下午2:10:28
     * @serverCode 服务代码
     * @return
     * @see com.lifeshs.thirdservice.DrugsService#logisticsInfo()
     */
    @Override
    public String logisticsInfo(String orderNo) {
        Map<String, String> headers = new HashMap<String, String>();
        SortedMap<String, Object> params = new TreeMap<String, Object>();
        headers.put("Content-Type", "application/json");

        String key = JianKe.LOGIN_PWD;
        String method = Constant.JIANKE_LOGISTICS_GET;
        String cid = JianKe.LOGIN_NAME;
        String signMethod = Constant.MD5;
        String randomStr = WXPayUtil.generateNonceStr().substring(0, 8);
        String url = JianKe.INTERFACE_URL;

        // 参数设置
        params.put("method", method);
        params.put("cid", cid);
        params.put("signMethod", signMethod);
        params.put("randomStr", randomStr);
        params.put("orderNo", orderNo);

        // 签名设置
        String sign = WeChatHttpUtils.createSign(params, key);
        params.put("sign", sign);

        // 将Map转换成json
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(params);
        String josn = jsonObject.toString();
        logger.info("params:" + params);
        logger.info("josn:" + josn);

        // 接口调用
        String result = WeChatHttpUtils.httpsRequest(url, "POST", josn, headers);
        logger.info("单个订单查询:" + result);

        return result;
    }

    

    /**
     * 
     * 订单推送
     * 
     * @author liaoguo
     * @DateTime 2018年6月7日 下午2:10:03
     * @serverCode 服务代码
     * @return
     * @see com.lifeshs.thirdservice.DrugsService#orderPush()
     */
    public String orderPush(OrderPushPO orderPO) {
        Map<String, String> headers = new HashMap<String, String>();
        SortedMap<String, Object> params = new TreeMap<String, Object>();
        headers.put("Content-Type", "application/json");

        String key = JianKe.LOGIN_PWD;
        String url = JianKe.INTERFACE_URL;
        String method = Constant.JIANKE_ORDER_EXTERNALCREATE;
        String cid = JianKe.LOGIN_NAME;
        String signMethod = Constant.MD5;
        String randomStr = WXPayUtil.generateNonceStr().substring(0, 8);
        String externalOrderNo = orderPO.getExternalOrderNo(); //外部订单编号

        String invoice = "";
        if(orderPO.getInvoice() != null){
            invoice = orderPO.getInvoice();
        }
        String orderNotes = null;
        if(StringUtils.isNotBlank(orderPO.getOrderNotes())){
            orderNotes = orderPO.getOrderNotes(); // 非必填
        }
        String money = orderPO.getMoney() + "";
        String orderTime = orderPO.getOrderTime();
        String deliveryZipCode = orderPO.getDeliveryZipCode();//邮编
        String accountId = orderPO.getAccountId()+"";//账号id
        String consignee = orderPO.getConsignee();//收件人
        String telephone = orderPO.getTelephone();//联系电话
        String mobilephone = null;
        if(StringUtils.isNotBlank(orderPO.getMobilephone())){
            mobilephone = orderPO.getMobilephone(); // 非必填
        }
        String province = null;
        if(StringUtils.isNotBlank(orderPO.getProvince())){
            province = orderPO.getProvince(); // 非必填
        }
        String city = null;
        if(StringUtils.isNotBlank(orderPO.getCity())){
            city = orderPO.getCity(); // 非必填
        }
        String district = null;
        if(StringUtils.isNotBlank(orderPO.getDistrict())){
            district = orderPO.getDistrict(); // 非必填
        }
        String town = null;
        if(StringUtils.isNotBlank(orderPO.getTown())){
            town = orderPO.getTown(); // 非必填
        }
        String address = null;
        if(StringUtils.isNotBlank(orderPO.getAddress())){
            address = orderPO.getAddress(); // 非必填
        }
        String paymentType = null;
        if(StringUtils.isNotBlank(orderPO.getPaymentType())){
            paymentType = orderPO.getPaymentType(); // 非必填
        }
        String transportCosts = null;
        if(StringUtils.isNotBlank(orderPO.getTransportCosts()+"")){
            transportCosts = orderPO.getTransportCosts() + ""; // 非必填
        }
        String status = orderPO.getStatus() + "";
        List<OrderProductPushPO> orderProductList = orderPO.getOrderProductList();

        // 参数设置
        params.put("method", method);
        params.put("cid", cid);
        params.put("signMethod", signMethod);
        params.put("randomStr", randomStr);
        params.put("externalOrderNo", externalOrderNo);
        params.put("deliveryZipCode", deliveryZipCode);
        params.put("invoice", invoice);
        params.put("orderNotes", orderNotes); // 非必填
        params.put("money", money);
        params.put("orderTime", orderTime);
        params.put("accountId", accountId);
        params.put("consignee", consignee);
        params.put("telephone", telephone);
        params.put("mobilephone", mobilephone);
        params.put("province", province);
        params.put("city", city);
        params.put("district", district);
        params.put("town", town);
        params.put("address", address);
        params.put("mobilephone", mobilephone); // 非必填
        params.put("province", province); // 非必填
        params.put("city", city); // 非必填
        params.put("district", district); // 非必填
        params.put("town", town); // 非必填
        params.put("address", address); // 非必填
        params.put("paymentType", paymentType); // 非必填
        params.put("transportCosts", transportCosts); // 非必填
        params.put("status", status);
        params.put("paymentType", paymentType);
        params.put("transportCosts", transportCosts);

        String childParams = JSONHelper.array2json(orderProductList);
        params.put("orderProductList", childParams);
        logger.info("###params:"+ params);
        System.out.println("###params:"+ params);
        
        // 签名设置
        String sign = WeChatHttpUtils.createJKSign(params, key);
        params.put("sign", sign);
        logger.info("###sign:"+ sign);
        System.out.println("###sign:"+ sign);

        // 将Map转换成json
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(params);
        String josn = jsonObject.toString();
        logger.info("params1:"+ params);
        logger.info("josn1:"+ josn);
        System.out.println("params2:"+ params);
        System.out.println("josn2:"+ josn);

        // 接口调用
        String result = WeChatHttpUtils.httpsRequest(url, "POST", josn, headers);
        logger.info("单个订单查询:" + result);
        logger.error("单个订单查询:" + result);
        System.out.println("###result:"+ result);
        
        // 获取返回订单编号
        net.sf.json.JSONObject orderNoJson = net.sf.json.JSONObject.fromObject(result);
        logger.info("orderNoJson:"+ orderNoJson);
        logger.error("orderNoJson:"+ orderNoJson);
        System.out.println("###orderNoJson:"+ orderNoJson);
        
        String exterOrderNo = orderNoJson.get("orderNo").toString();
        logger.info("健客编号exterOrderNo:" + exterOrderNo);
        logger.error("健客编号exterOrderNo:" + exterOrderNo);
        
        return exterOrderNo;
    }

    @Override
    public Paging<DrugsPO> listLocalDrugs(String productName, String productAttribute, String prescriptionType,
            int curPage, int pageSize) {

        Paging<DrugsPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<DrugsPO>() {

            @Override
            public int queryTotal() {
                // TODO Auto-generated method stub
                return drugsDao.countLocalDrugs(productName, productAttribute, prescriptionType);
            }

            @Override
            public List<DrugsPO> queryData(int startRow, int pageSize) {
                // TODO Auto-generated method stub
                return drugsDao.listLocalDrugs(productName, productAttribute, prescriptionType, startRow, pageSize);
            }
        });
        return p;
    }

    @Override
    public String saveDrugsList(List<DrugsPO> datas, String syncTime) {
        // TODO Auto-generated method stub
        String ids = "";
        DrugsPO d = null;

        try {
            for (int i = 0; i < datas.size(); i++) {
                d = new DrugsPO();
                d = datas.get(i);
                d.setSyncTime(syncTime);
                int id = drugsDao.saveDrugsList(d);
                ids += id;
            }
            System.out.println("ids=============" + ids);
            if (StringUtils.isNotBlank(ids)) {
                ids = ids.substring(0, ids.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

        return ids;
    }
    
    /**
     * 
     *  获取产品信息 
     *  @author liaoguo
     *	@DateTime 2018年6月26日 下午2:28:41
     *  @serverCode 服务代码
     *  @param orderNo
     *  @return    
     *  @see com.lifeshs.thirdservice.DrugsService#getOrderProductList(java.lang.String)
     */
    public List<OrderProductPO> getOrderProductList(String orderNo){
        return drugsDao.getOrderProductList(orderNo);
    }

    @Override
    public int saveSyncRecord(int userId, String syncTime, String nextTime, String content) {
        // TODO Auto-generated method stub
        return drugsDao.saveSyncRecord(userId, syncTime, nextTime, content);
    }

    @Override
    public String getSyncLastDate() {
        return drugsDao.getSyncLastDate();
    }

    @Override
    public List<PrescriptionType> getPrescriptionType() {
        // TODO Auto-generated method stub
        return drugsDao.getPrescriptionType();
    }

    @Override
    public List<ProductAttribute> getProductAttribute() {
        // TODO Auto-generated method stub
        return drugsDao.getProductAttribute();
    }

    @Override
    public UserAddressPO findUserAddress(int userId) {
        // TODO Auto-generated method stub
        return memberAddressDao.findUserAddress(userId);
    }
    
    @Override
    public UserAddressPO findUserAddressById(int id) {
        // TODO Auto-generated method stub
        return memberAddressDao.findUserAddressById(id);
    }
    
 // 微信支付成功处理方法
    @Override
    public void finishOrder(String orderNo, String tradeNumber, String payerAccount, String sellerAccount,
            double payMoney, PayTypeEnum alipay, String deviceType, Map<String,String> map) throws OperationException {
        
        int id = Integer.parseInt(map.get(Order.ADDRESSID));
        //获取用户收货地址
        UserAddressPO userAddress = memberAddressDao.findUserAddressById(id);
        //获取订单
        List<OrderPO> orderPO = drugsDao.getLocalOrderByParam(orderNo);
        OrderPO po = orderPO.get(0);
        
        //金额double转int
        Double d1=new Double(payMoney); 
        int totalC = d1.intValue();
        
        
        //推送内容设置
        OrderPushPO push = new OrderPushPO();
        String orderTime = DateTimeUtil.getShowFormat(null);
        push.setExternalOrderNo(orderNo); //健客订单编号
        push.setDeliveryZipCode("000000");//邮编
        push.setInvoice(po.getInvoice());//发票信息invoice的话根据用户有无填写，没有填写的话 可以传 “”
        push.setOrderNotes(po.getOrderNotes());//订单备注
        push.setMoney(totalC);//总金额
        push.setOrderTime(orderTime);
        push.setAccountId(userAddress.getUserId());//账户ID
        push.setConsignee(userAddress.getReceiverName());//收件人
        push.setTelephone(userAddress.getContactNumber());//收件人电话
        push.setMobilephone(userAddress.getContactNumber());
        push.setProvince(null);
        push.setCity(null);
        push.setDistrict(null);
        push.setTown(userAddress.getStreet());//街道
        push.setAddress(userAddress.getAddress());//收货详细地址
        push.setPaymentType(map.get(Order.PAYMENTTYPE));//支付类型  1支付宝，2微信，3网银在线，4货到付款
        push.setStatus(OrderStatusEnum.ALREADYPAID.getValue()); //1未支付，2已支付
        push.setTransportCosts(0);//运费
//        push.setOrderProductList(po.getOrderProductList());
        OrderProductPushPO pushPo = null;
        List<OrderProductPushPO> pushList = new ArrayList<OrderProductPushPO>();
        for(OrderProductPO op : po.getOrderProductList()){
            pushPo = new OrderProductPushPO();
            pushPo.setActualPrice(op.getActualPrice());
            pushPo.setAmount(op.getAmount());
            pushPo.setPacking(op.getPacking());
            pushPo.setProductCode(op.getProductCode());
            pushPo.setProductName(op.getProductName());
            pushList.add(pushPo);
        }
        push.setOrderProductList(pushList);
        
        //订单推送，返回健客订单号
        String externalOrderNo = orderPush(push);
        
        //获取本地订单
        List<OrderPO> orderList = getLocalOrderByParam(orderNo);
        OrderPO order = orderList.get(0);
        //int money = NumberUtils.changeY2F(Double.valueOf(payMoney).toString());
        // 根据健客返回的订单编号更新到本地
        order.setExternalOrderNo(externalOrderNo);
        order.setInvoice(push.getInvoice());
        order.setOrderNotes(push.getOrderNotes());
        order.setMoney(push.getMoney());
        order.setOrderTime(orderTime);
        order.setUserId(push.getAccountId());
        order.setConsignee(push.getConsignee());
//        order.setAddressId(id);
        order.setMobilephone(push.getMobilephone()); //收件人手机号码
        order.setTown(push.getTown());
        order.setAddress(push.getAddress());
        order.setPaymentType(Integer.parseInt(push.getPaymentType())); //支付类型1支付宝，2微信，3网银在线，4货到付款
        order.setTransportCosts(0); //运费单位：分
        order.setStatus(push.getStatus()); //订单状态1未支付，2已支付，3已完成
        order.setPayCost(totalC); //付款帐户支付金额
        order.setSellerAccount(sellerAccount); //收款帐户
        order.setPayAccount(payerAccount);//付款帐户
        

        //更新本地订单信息
        int result = drugsDao.updateOrder(order);
        if (result == 0){
            throw new OperationException("更新失败", ErrorCodeEnum.FAILED);
        }
    }
    
//    //支付宝回调处理方法
//    @Override
//    public void finishOrderPrivate(String orderNumber, String tradeNumber, String payerAccount, String sellerAccount,
//                                   Double payMoney, PayTypeEnum alipay, String deviceType) throws OperationException {
//        //获取本地订单
//        List<OrderPO> orderList = getLocalOrderByParam(orderNumber);
//        OrderPO order = orderList.get(0);
//        int f = NumberUtils.changeY2F(Double.valueOf(payMoney).toString());
//        order.setStatus(2);
//        order.setPaymentType(alipay.getValue());
//        order.setPayAccount(payerAccount);
//        order.setSellerAccount(sellerAccount);
//        order.setPayCost(f);
//        int result = drugsDao.updateOrder(order);//保存完成
//        if (result == 0){
//            throw new OperationException("更新失败", ErrorCodeEnum.FAILED);
//        }
//    }
    
    
    
    
    
    @Override
    public DrugsPO getDrugsByCode(String productCode){
        return drugsDao.getDrugsByCode(productCode);
    }

    @Override
    public int updateOrder(OrderPO order) {
        // TODO Auto-generated method stub
        return drugsDao.updateOrder(order);
    }
}
