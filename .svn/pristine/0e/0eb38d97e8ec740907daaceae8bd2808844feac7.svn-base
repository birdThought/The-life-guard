package com.lifeshs.service1.order.vip.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.BusinessConstant;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.app.vip.VipOrder;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.order.PayReturnOrderTypeEnum;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.constants.common.order.SetMealTypeEnum;
import com.lifeshs.common.constants.common.order.VipOrderTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.component.weChat.UnifiedOrderUtils;
import com.lifeshs.component.weChat.WXPayUtil;
import com.lifeshs.dao1.order.vip.VipUserOrderDao;
import com.lifeshs.po.order.VipUserOrderPO;
import com.lifeshs.po.vip.VipUserPO;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service1.order.impl.IncomeServiceImpl;
import com.lifeshs.service1.order.vip.VipOrderFlowService;
import com.lifeshs.service1.order.vip.VipUserOrderService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.vip.IVipComboService;
import com.lifeshs.service1.vip.IVipUserService;
import com.lifeshs.thirdservice.impl.WeChatHttpUtils;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.vo.agent.AgetnIncomeVo;
import com.lifeshs.vo.order.vip.BusinessOrderBillVO;
import com.lifeshs.vo.order.vip.VipUserOrderVO;
import com.lifeshs.vo.vip.VipComboVO;

@Service(value = "vipUserOrderServiceImpl")
public class VipUserOrderServiceImpl implements VipUserOrderService {
    private final Logger logger = LoggerFactory.getLogger(VipUserOrderServiceImpl.class);
    
    @Resource(name = "vipUserOrderDao")
    private VipUserOrderDao orderDao;

    @Resource(name = "vipOrderFlowServiceImpl")
    private VipOrderFlowService orderFlowService;
    
    @Resource(name = "vipUserService")
    private IVipUserService vipUserService;
    
    @Resource(name = "vipComboService")
    private IVipComboService comboService;
    
    @Resource(name = "incomeService")
    private IncomeServiceImpl incomeService;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
        public VipUserOrderPO addOrder(int userId, int vipComboId, OrderStatus orderStatus, String subject,
                Integer businessCardId, VipOrderTypeEnum orderType, Integer businessIncome,
                Integer businessId) throws OperationException {
            VipComboVO comboPO = comboService.getVipCombo(vipComboId);
            if (comboPO == null) {
                throw new OperationException("套餐不存在", ErrorCodeEnum.NOT_FOUND);
            }
            int price = 0;
            // 如果不是通过激活码的方式添加订单，需要修改价格为套餐价
            if (orderType != VipOrderTypeEnum.CODE) {
                price = comboPO.getPrice();
            }

            // 获取订单编号
            String orderNumber = AlipayService.createOrderNumber();
        
        VipUserOrderPO order = new VipUserOrderPO();
        order.setOrderNumber(orderNumber);
        order.setBusinessCardId(businessCardId);
        order.setSubject("会员激活");
        order.setBody("充值会员");
        order.setStatus(orderStatus.getStatus());
        order.setType(orderType.getValue());   // 2_邀请码
        order.setUserId(userId);
        order.setVipComboId(vipComboId);
        order.setPrice(price);
        order.setBusinessId(businessId);
        order.setBusinessIncome(businessIncome);
        
        // 如果订单编号重复，最多重新生成5次订单编号
        int result = 0;
        for (int i = 0; i < 5; i++) {
            try{
                result = orderDao.addOrder(order);
                break;
            } catch (DuplicateKeyException e) {
                // 重新生成订单编号
                orderNumber = AlipayService.createOrderNumber();
                order.setOrderNumber(orderNumber);
            }
        }
        
        if (result == 0) {
            throw new OperationException("添加订单失败", ErrorCodeEnum.FAILED);
        }
        
        return order;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void finishOrder(String orderNumber, String tradeNo, String payAccount, String sellerAccount,
            double totalCount, PayTypeEnum payType, String deviceType) throws OperationException {
        int totalC = 0;
        
        //支付宝支付才进行金额单位转换
        if(PayTypeEnum.ALIPAY.getValue() == payType.getValue()){
            totalC = NumberUtils.changeY2F(String.valueOf(totalCount));
        }else{
            Double d1=new Double(totalCount); 
            totalC = d1.intValue();
        }
        
        // 查询该订单编号对应的订单信息
        VipUserOrderPO order = orderDao.findOrderByOrderNumber(orderNumber);
        if (order == null) {
            throw new OperationException("该订单不存在", ErrorCodeEnum.NOT_FOUND);
        }
        if (!OrderStatus.PAYABLE.getStatus().equals(order.getStatus())) {
            throw new OperationException("该订单状态为不可支付", ErrorCodeEnum.FAILED);
        }
        if (order.getPrice().intValue() != totalC) {
            throw new OperationException("金额不匹配(" + order.getPrice() + " => " + totalC + ")", ErrorCodeEnum.FAILED);
        }
        
        Integer businessId = null;
        Integer businessIncome = null;
        AgetnIncomeVo vo = null;
        
        // 激活或续费vip
        int userId = order.getUserId();
        int vipComboId = order.getVipComboId();
        // 获取用户的vip 判断用户是激活还是续费
        VipUserPO vipUserPO = vipUserService.getUserVip1(userId);
//        if (vipUserPO == null) {
//            // 激活vip
//            vipUserService.activateVip(userId, vipComboId);
//            // 如果是激活vip，只能是通过用户在app上面充值，不用考虑渠道商收入分成问题
//        } else {
//            // 续费vip
//            vipUserService.renewalVip(userId, vipComboId);
//            // 获取最新的vip订单
//            List<VipUserOrderPO> orderList = orderDao.findOrderListWithCondition(0, 1, userId, vipComboId, OrderStatus.COMPLETED.getStatus(), null, null, null);
//            VipUserOrderPO o = orderList.get(0);
//            // 获取渠道商id
//            // 这个id如果是null，表示用户不是通过渠道商的介绍添加的订单，就不需要计算渠道商分成
//            businessId = o.getBusinessId();
//            if (businessId != null) {
//                // 计算渠道商收益
//                businessIncome = Double.valueOf(order.getPrice() * BusinessConstant.PROFIT_SHARE).intValue();
//            }
//        }
        
        //修改订单状态为已完成
        VipUserOrderPO orderPO = new VipUserOrderPO();
        orderPO.setId(order.getId());
        orderPO.setStatus(OrderStatus.COMPLETED.getStatus());
        orderPO.setBusinessId(businessId);
        orderPO.setBusinessIncome(businessIncome);
        
        //如果不是渠道商推荐，那进行代理商模式分成
        if (order.getType()!=2 && businessId == null) {
            
            //获取分成信息
            vo = incomeService.orderIncome(orderNumber, totalC, "VIP");
            if(vo!=null){
                int sysIncome = vo.getSysIncome();
                int orgIncome = vo.getIntroduceOrgIncome(); //引入机构
                int serviceOrgIncome = vo.getServiceOrgIncome(); //服务机构
                int agentIncome = vo.getAgentIncome(); //代理商分成
                int salesmanIncome = vo.getSalesmanIncome(); //业务员分成
                
                //区分引入机构
                if(StringUtils.isNotBlank(vo.getSysPlatformId()) && vo.getSysPlatformId().equals(vo.getIntroduceOrgId())){
                    //平台
                    sysIncome += vo.getIntroduceOrgIncome();
                    orgIncome = 0;
                }else if(StringUtils.isNotBlank(vo.getAgentId()) && vo.getAgentId().equals(vo.getIntroduceOrgId())){
                    //代理商
                    agentIncome += vo.getIntroduceOrgIncome();
                    orgIncome = 0;
                }else if(StringUtils.isNotBlank(vo.getSalesmanId()) && vo.getSalesmanId().equals(vo.getIntroduceOrgId())){
                    //业务员
                    salesmanIncome += vo.getIntroduceOrgIncome();
                    orgIncome = 0;
                }else if (vo.getServiceOrgId().equals(vo.getIntroduceOrgId())){
                    //服务机构
                    serviceOrgIncome += vo.getIntroduceOrgIncome();
                    orgIncome = 0;
                }
                
                //服务机构
                if(StringUtils.isNotBlank(vo.getSysPlatformId()) && vo.getSysPlatformId().equals(vo.getServiceOrgId())){
                    //平台
                    sysIncome += serviceOrgIncome;
                    serviceOrgIncome = 0;
                }else if(StringUtils.isNotBlank(vo.getAgentId()) && vo.getAgentId().equals(vo.getServiceOrgId())){
                    //代理商
                    agentIncome += serviceOrgIncome;
                    serviceOrgIncome = 0;
                }
                
                orderPO.setSysUserNo(vo.getSysPlatformId());
                orderPO.setSysIncome(sysIncome);
                orderPO.setAgentUserNo(vo.getAgentId());
                orderPO.setAgentIncome(agentIncome);
                orderPO.setSalesmanUserNo(vo.getSalesmanId());
                orderPO.setSalesmanIncome(salesmanIncome);
                orderPO.setIntroduceOrgUserNo(vo.getIntroduceOrgId());
                orderPO.setIntroduceOrgIncome(orgIncome);
                orderPO.setServiceOrgUserNo(vo.getServiceOrgId());
                orderPO.setServiceOrgIncome(serviceOrgIncome);
            }
        }
        
        int result = orderDao.updateOrder(orderPO);
        if (result == 0) {
            throw new OperationException("修改订单状态失败", ErrorCodeEnum.FAILED);
        }
        
        // 添加流水
        orderFlowService.addOrderFlow(orderNumber, payType, totalC, totalC, payAccount,
                sellerAccount, tradeNo, businessIncome, vo);
        
        // add the vip item record
        vipUserService.addVipItemRecord(userId, vipComboId);
    }

    @Override
    public String getAliPaySign(int orderId) throws OperationException {
        VipUserOrderPO order = orderDao.getOrder(orderId);
        if (order == null) {
            throw new OperationException("订单不存在", ErrorCodeEnum.NOT_FOUND);
        }
        if (VipOrderTypeEnum.PAY.getValue() != order.getType().intValue() && VipOrderTypeEnum.QRPAY.getValue() != order.getType().intValue()) {
            throw new OperationException("订单类型不可支付", ErrorCodeEnum.FAILED);
        }
        if (!OrderStatus.PAYABLE.getStatus().equals(order.getStatus())) {
            throw new OperationException("订单状态不可支付", ErrorCodeEnum.FAILED);
        }
        
        int price = order.getPrice();
        double total = NumberUtils.changeF2Y(String.valueOf(price));
        String subject = order.getSubject();
        String body = order.getBody();
        String orderNumber = order.getOrderNumber();
        String notifyUrl = Normal.APP_RECHARGE_NOTIFY_URL;
        Map<String, String> extraData = new HashMap<>();
        extraData.put("orderType", String.valueOf(PayReturnOrderTypeEnum.VIP.getValue()));
        
        String sign = AlipayService.getOrderInfo(total, subject, body, orderNumber, extraData, notifyUrl);
        return sign;
    }
    
    @Override
    public Map<String, Object> getWeChatPaySign(int orderId,String spbill_create_ip,int orderType) throws OperationException {
        VipUserOrderPO order = orderDao.getOrder(orderId);
        if (order == null) {
            throw new OperationException("订单不存在", ErrorCodeEnum.NOT_FOUND);
        }
        if (VipOrderTypeEnum.PAY.getValue() != order.getType().intValue() && VipOrderTypeEnum.QRPAY.getValue() != order.getType().intValue()) {
            throw new OperationException("订单类型不可支付", ErrorCodeEnum.FAILED);
        }
        if (!OrderStatus.PAYABLE.getStatus().equals(order.getStatus())) {
            throw new OperationException("订单状态不可支付", ErrorCodeEnum.FAILED);
        }
        
        SortedMap<String, Object> params = new TreeMap<String, Object>();
        String appid = UnifiedOrderUtils.getAppid();
        String mch_id = UnifiedOrderUtils.getMch_id();
        String apiKey = UnifiedOrderUtils.getApi_key();
        String notify_url = Normal.APP_WEIXI_NOTIFY_URL;
        String unifiedorder = UnifiedOrderUtils.getUnifiedorder();
        String nonce_str = WXPayUtil.generateNonceStr();
        String body = order.getBody();
        String out_trade_no = order.getOrderNumber();
        String total_fee = order.getPrice()+"";
        String trade_type = Order.TRADE_TYPE_APP;
        
        params.put("appid", appid); //应用ID
        params.put("mch_id", mch_id); //商户号
        params.put("device_info", "WEB"); //设备号
        params.put("nonce_str", nonce_str); //随机字符串
        params.put("body", body); //商品描述
        params.put("out_trade_no", out_trade_no);//商户订单号
        params.put("total_fee", total_fee); //交易金额
        params.put("trade_type", trade_type);//交易类型
        params.put("notify_url", notify_url); //通知地址
        params.put("spbill_create_ip", spbill_create_ip); //终端IP
        
        
      //自定义参数
        StringBuilder sb = new  StringBuilder();
        sb.append("orderType:"+orderType);
        params.put("attach", sb.toString());
        logger.info(String.format("统一下单(套餐)自定义参数orderType:%s,setMealType:%s", orderType,SetMealTypeEnum.VIP.getValue()));

        
        //签名(该签名本应使用微信商户平台的API证书中的密匙key,但此处使用的是微信公众号的密匙APP_SECRET)
         String sign = WeChatHttpUtils.createSign(params,apiKey);
         params.put("sign", sign);
         System.out.println("**sign:"+sign);
         
         String requestXML = WeChatHttpUtils.getRequestXml(params);
         System.out.println("requestXML:"+requestXML);

         String result = WeChatHttpUtils.httpsRequest(unifiedorder, "POST", requestXML);

         org.json.JSONObject resultJson = XML.toJSONObject(result);
         org.json.JSONObject jsonObj = (org.json.JSONObject)resultJson.get("xml");
         String result_code  = jsonObj.getString("result_code");
         SortedMap<String,Object> submitMap = new TreeMap<String,Object>();
         if("SUCCESS".equals(result_code)) {
             //appId，partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay
             submitMap.put("appid", appid);
             submitMap.put("partnerid", mch_id);
             submitMap.put("prepayid", jsonObj.getString("prepay_id"));
             submitMap.put("noncestr", jsonObj.getString("nonce_str"));
             Long time = (System.currentTimeMillis() / 1000);
             submitMap.put("timestamp", time.toString());
             submitMap.put("package", "Sign=WXPay");
             //第二次生成签名
             sign = WeChatHttpUtils.createSign(submitMap, apiKey);
             submitMap.put("sign", sign);
         }
         Map<String, Object> orderInfoMap = new HashMap<>();
         net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(submitMap);
         orderInfoMap.put(VipOrder.SIGN, jsonObject.toString());
         
         return orderInfoMap;
    }

    @Override
    public Paging<VipUserOrderVO> listOrder(int businessId, String startDate, String endDate, int pageIndex, int pageSize) {
        Paging paging = new Paging(pageIndex, pageSize);
        paging.setQueryProc(new IPagingQueryProc() {
            @Override
            public int queryTotal() {
                return orderDao.countOrderByBusinessId(businessId, startDate, endDate);
            }

            @Override
            public List queryData(int startRow, int pageSize) {
                return orderDao.findOrderListByBusinessId(businessId, startDate, endDate, paging.getStartRow(), pageSize);
            }
        });
        return paging;
    }

    @Override
    public BusinessOrderBillVO getBusinessOrderBill(int businessId, String startDate, String endDate) {
        Integer income = orderDao.countBusinessIncome(businessId, startDate, endDate);
        Integer pay = orderDao.countBusinessPayCard(businessId, startDate, endDate);
        income = income == null ? 0 : income;
        pay = pay == null ? 0 : pay;
        BusinessOrderBillVO businessOrderBillVO = new BusinessOrderBillVO();
        businessOrderBillVO.setIncome(income);
        businessOrderBillVO.setPayForCard(pay);
        businessOrderBillVO.setLearning(income - pay);
        return businessOrderBillVO;
    }
    
    @Override
    public List<Map<String, Object>> findVipOrderOutOfEndDateList(Date endTime, Date startTime){
    	return orderDao.findVipOrderOutOfEndDateList(endTime, startTime);
    }
    
    @Override
    public int finishOrder(Integer id) {
    	return orderDao.finishOrder(id);
    }
}
