package com.lifeshs.app.api.member;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.common.jianKe.OrderStatusEnum;
import com.lifeshs.common.constants.common.order.OrderInfoTypeEnum;
import com.lifeshs.common.constants.common.order.PayReturnOrderTypeEnum;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.weChat.UnifiedOrderInfo;
import com.lifeshs.po.CustomOrderPo;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.UserAddressPO;
import com.lifeshs.po.drugs.OrderProductPO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.serve.IAppServeService;
import com.lifeshs.service1.order.CustomOrderService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.thirdservice.DrugsService;
import com.lifeshs.thirdservice.IWeChatService;
import com.lifeshs.utils.HttpUtils;

/**
 * 应用app服务
 * 
 * @author yuhang.weng
 * @DateTime 2017年2月21日 下午4:45:14
 */
@RestController(value = "appServeController")
@RequestMapping(value = { "/app", "/app/serve" })
public class ServeController {

    private final Logger logger = LoggerFactory.getLogger(ServeController.class);

    @Resource(name = "appServeService")
    private IAppServeService serveService;

    @Resource(name = "IWeChatService")
    private IWeChatService weChatService;

    @Resource(name = "v2OrderService")
    private OrderService orderService1;
    
    @Resource(name = "drugsService")
    private DrugsService drugsService;

    @Resource(name = "customOrderService")
    CustomOrderService customOrderService;
    
    /**
     * 获取服务列表
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:23
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getServeList", method = RequestMethod.POST)
    public JSONObject getServeList(@RequestParam String json) {
        return serveService.getServeList(json);
    }

    /**
     * 获取推荐机构列表
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:26
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getRecommendOrgList", method = RequestMethod.POST)
    public JSONObject getRecommendOrgList(@RequestParam String json) {
        return serveService.getRecommendOrgList(json);
    }

    /**
     * 获取服务机构列表
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:29
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getOrgList", method = RequestMethod.POST)
    public JSONObject getOrgList(@RequestParam String json) {
        return serveService.getOrgList(json);
    }

    /**
     * 获取服务机构
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:31
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getOrg", method = RequestMethod.POST)
    public JSONObject getOrg(@RequestParam String json) {
        return serveService.getOrg(json);
    }

    /**
     * 获取机构服务
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:34
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getOrgServe", method = RequestMethod.POST)
    public JSONObject getOrgServe(@RequestParam String json) {
        return serveService.getOrgServe(json);
    }

    /**
     * 获取课堂群组列表
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月7日 下午7:21:43
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getLessonGroup", method = RequestMethod.POST)
    public JSONObject getLessonGroup(@RequestParam String json) {
        return serveService.getLessonGroup(json);
    }

    /**
     * 获取课堂服务详情
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月7日 下午7:23:08
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getOrgLessonServe", method = RequestMethod.POST)
    public JSONObject getOrgLessonServe(@RequestParam String json) {
        return serveService.getOrgLessonServe(json);
    }

    /**
     * 获取服务师信息
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:37
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getServeUser", method = RequestMethod.POST)
    public JSONObject getServeUser(@RequestParam String json) {
        return serveService.getServeUser(json);
    }

    /**
     * 获取用户订单
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:49
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "addOrder", method = RequestMethod.POST)
    public JSONObject addOrder(@RequestParam String json) {
        return serveService.addOrder(json);
    }

    /**
     * 获取订单签名
     *
     * @author yuhang.weng
     * @DateTime 2016年10月31日 下午2:21:43
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getOrderInfo", method = RequestMethod.POST)
    public JSONObject getOrderInfo(@RequestParam String json, HttpServletRequest request) throws BaseException {
        JSONObject jsonObject = null;

        // 区分支付方式 1：支付宝 2：微信
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject msg_0 = appJSON.getData().getFirstJSONObject();
        int payType = msg_0.getInteger(Order.PAY_TYPE);

        if (PayTypeEnum.ALIPAY.getValue() == payType) {
            jsonObject = serveService.getOrderInfo(json);
        } else if (PayTypeEnum.WECHAT.getValue() == payType) {
            // 获取订单号
            String orderNumber = msg_0.getString(Order.NUMBER);
            int orderType = Integer.parseInt(msg_0.getString(Order.ORDER_TYPE));
            OrderInfoTypeEnum orderTypeEnum = OrderInfoTypeEnum.getEnum(orderType);
            UnifiedOrderInfo info = new UnifiedOrderInfo();
            info.setOut_trade_no(orderNumber);// 商户订单号
            info.setTrade_type(Order.TRADE_TYPE_APP);// 交易类型
            info.setSpbill_create_ip(HttpUtils.getIpAddress(request));
            switch (orderTypeEnum) {
                case SERVE:
                    // 获取用户订单信息
                    OrderPO order = orderService1.getOrderByOrderNumber(orderNumber);
                    if (order == null) {
                        return AppNormalService.error("查询不到此订单");
                    }
                    info.setBody(order.getSubject()); // 商品描述
                    info.setTotal_fee(order.getCharge()+ "");// 总金额
                    break;
                case PRIVATE:
                    CustomOrderPo customOrderPo = customOrderService.getOrderDetails(orderNumber);
                    if(customOrderPo == null)
                        return AppNormalService.error("查询不到此订单");
                    info.setBody(customOrderPo.getProductName()); // 商品描述
                    info.setTotal_fee(customOrderPo.getPayCost()+ "");// 总金额
                    break;
            }
            // 自定义参数
            StringBuilder sb = new StringBuilder();
            sb.append("orderType:" + orderType + ",");
            String couponsId = msg_0.getString("couponsId");
            if (couponsId == null)
                sb.append("couponsId:" + couponsId);
            info.setAttach(sb.toString());

            jsonObject = weChatService.unifiedOrder(info);
        }
        return jsonObject;
    }

    /**
     * 
     * 获取订单签名(重写方法,可扩展)
     * 
     * @author liaoguo
     * @DateTime 2018年6月14日 下午5:43:42
     *
     * @param json
     * @param request
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "getOrderInfoNew", method = RequestMethod.POST)
    public JSONObject getOrderInfoNew(@RequestParam String json, HttpServletRequest request) throws BaseException {
        JSONObject jsonObject = null;

        logger.info("支付接口json:"+json);
        // 区分支付方式 1：支付宝 2：微信
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject msg_0 = appJSON.getData().getFirstJSONObject();
        logger.info("支付接口msg_0:"+msg_0);
        int payType = msg_0.getInteger(Order.PAY_TYPE);
        int orderType = msg_0.getInteger(Order.ORDER_TYPE);
        String orderNo = msg_0.getString(Order.NUMBER);

        //支付宝支付
        if (PayTypeEnum.ALIPAY.getValue() == payType) {
            // 药品订单
            if (OrderInfoTypeEnum.GRUDS.getValue() == orderType) {
                jsonObject = serveService.getOrderInfoNew(json);
            }

        } else if (PayTypeEnum.WECHAT.getValue() == payType) {
            //微信支付
            String body = null;
            String outTradeNo = null;
            String totalFee = null;
            String tradeType = null;
            String spbillCreateIp = null; //ip
            String attach = null; //附件
            //int transportCosts = 0; //运费

            // 药品订单
            if (OrderInfoTypeEnum.GRUDS.getValue() == orderType) {
                // 获取用户订单信息
                List<com.lifeshs.po.drugs.OrderPO> orderList = drugsService.getLocalOrderByParam(orderNo);
                if (orderList.size() < 1) {
                    logger.error(String.format("不存在此订单orderNo:%s", orderNo));
                    return null;
                }
                
                com.lifeshs.po.drugs.OrderPO order = orderList.get(0);
                if (OrderStatusEnum.UNPAID.getValue() != order.getStatus()){
                    throw new OperationException("该订单状态为不可支付", ErrorCodeEnum.FAILED);
                }
                
                int total = 0;
                int money = Integer.parseInt(msg_0.getString(Order.MONEY));
                List<OrderProductPO> productList = drugsService.getOrderProductList(orderNo);
                for(OrderProductPO p : productList){
                    total += p.getActualPrice() * p.getAmount();
                }
                if(money != total){
                    throw new OperationException("金额不匹配 单位 分 (" + money + "=>" + total,ErrorCodeEnum.FAILED);    
                }
                
//                //计算运费
//                if(order.getMoney() < Constant.DRUGS_TRANSPORTCOSTS){
//                    transportCosts = 10;
//                }
                
                body = "生命守护-药品购买";
                outTradeNo = orderNo;
                //totalFee = order.getMoney()+"";
                totalFee = total+"";
                tradeType = Order.TRADE_TYPE_APP;
                spbillCreateIp = HttpUtils.getIpAddress(request);
                String invoice = null;
                String orderNotes = null;
                if(StringUtils.isNotBlank(msg_0.getString(Order.INVOICE))){
                    invoice = msg_0.getString(Order.INVOICE);
                }
                if(StringUtils.isNotBlank(msg_0.getString(Order.ORDERNOTES))){
                    orderNotes = msg_0.getString(Order.ORDERNOTES);
                }
//                String accountId = msg_0.getString(Order.ACCOUNTID);
                int addressId = Integer.parseInt(msg_0.getString(Order.ADDRESSID));
                //JSONArray msg = appJSON.getData().getMsg();
                
                //获取用户收货地址
                UserAddressPO userAddress = drugsService.findUserAddressById(addressId);
                if(null == userAddress){
                    throw new OperationException("请选择收货地址!",ErrorCodeEnum.FAILED);
                }
                
                //保存发票和订单备注
//                com.lifeshs.po.drugs.OrderPO po = new com.lifeshs.po.drugs.OrderPO();
                order.setOrderNo(orderNo); 
                order.setInvoice(invoice);  //发票
                order.setOrderNotes(orderNotes); //订单备注
                drugsService.updateOrder(order);
                
                StringBuilder sb = new StringBuilder();
                sb.append(Order.ADDRESSID+":"+addressId); //邮寄地址
//                sb.append(","+Order.ACCOUNTID+":"+ accountId);//账户ID
                sb.append(","+Order.PAYMENTTYPE+":"+payType);//支付类型
                sb.append(","+Order.ORDER_TYPE+":"+ orderType);//订单类型
                attach = sb.toString();
                logger.info("传送到微信的参数attach："+attach);
            }
            
            jsonObject = weChatService.unifiedOrderNew(body, outTradeNo, totalFee, tradeType, spbillCreateIp, attach);

        }
        return jsonObject;
    }

    /**
     * 获取服务标签
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:54
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getOrderList", method = RequestMethod.POST)
    public JSONObject getOrderList(@RequestParam String json) {
        return serveService.getOrderList(json);
    }

    /**
     * 查找服务机构列表
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:57
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getOrder", method = RequestMethod.POST)
    public JSONObject getOrder(@RequestParam String json) {
        return serveService.getOrder(json);
    }

    /**
     * 获取分类标签
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:40
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getClassifyTags", method = RequestMethod.POST)
    public JSONObject getClassifyTags(@RequestParam String json) {
        return serveService.getClassifyTags(json);
    }

    /**
     * 获取服务标签
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:43
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getServiceTags", method = RequestMethod.POST)
    public JSONObject getServiceTags(@RequestParam String json) {
        return serveService.getServiceTags(json);
    }

    /**
     * 获取用户订单列表
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:46
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "queryOrgList", method = RequestMethod.POST)
    public JSONObject queryOrgList(@RequestParam String json) {
        return serveService.queryOrgList(json);
    }

    /**
     * 取消订单
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:14:59
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    public JSONObject cancelOrder(@RequestParam String json) {
        return serveService.cancelOrder(json);
    }

    /**
     * 获取已购买服务列表
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:15:02
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getUserServeList", method = RequestMethod.POST)
    public JSONObject getUserServeList(@RequestParam String json) {
        return serveService.getUserServeList(json);
    }

    /**
     * 获取已购买服务详细内容
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:15:05
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getUserServeDetail", method = RequestMethod.POST)
    public JSONObject getUserServeDetail(@RequestParam String json) {
        return serveService.getUserServeDetail(json);
    }

    /**
     * 通过环信账号查询服务师信息
     *
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午2:15:07
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getServeUserByHuanxinAcount", method = RequestMethod.POST)
    public JSONObject getServeUserByHuanxinAcount(@RequestParam String json) {
        return serveService.getServeUserByHuanxinAcount(json);
    }

    /**
     * 查看服务是否过期
     *
     * @author yuhang.weng
     * @DateTime 2016年11月8日 下午2:45:58
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "checkServeValid", method = RequestMethod.POST)
    public JSONObject checkServeValid(@RequestParam String json) {
        return serveService.checkServeValid(json);
    }

    /**
     * 减少用户服务次数
     *
     * @author yuhang.weng
     * @DateTime 2016年11月16日 下午1:53:59
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "reduceServeTime", method = RequestMethod.POST)
    public JSONObject reduceServeTime(@RequestParam String json) {
        return serveService.reduceServeTime(json);
    }

    /**
     * 获取群组所属的服务信息
     *
     * @author yuhang.weng
     * @DateTime 2016年11月22日 上午10:44:26
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getGroupServeInfo", method = RequestMethod.POST)
    public JSONObject getGroupServeInfo(@RequestParam String json) {
        return serveService.getGroupServeInfo(json);
    }

    /**
     * 获取图片
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月21日 下午5:13:20
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getImages", method = RequestMethod.POST)
    public JSONObject getImages(@RequestParam String json) {
        return serveService.getImages(json);
    }

    /**
     * 获取课堂设置信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月7日 下午7:44:52
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getLessonDetail", method = RequestMethod.POST)
    public JSONObject getLessonDetail(@RequestParam String json) {
        return serveService.getLessonDetail(json);
    }

    /**
     * 退出课堂
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月9日 下午4:18:41
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "outLessonGroup", method = RequestMethod.POST)
    public JSONObject outLessonGroup(@RequestParam String json) {
        return serveService.outLessonGroup(json);
    }

    /**
     * 获取用户短信剩余信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年4月28日 下午3:09:58
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getSMSRemain", method = RequestMethod.POST)
    public JSONObject getSMSRemain(@RequestParam String json) {
        return serveService.getSMSRemain(json);
    }

    /**
     * 输入短信充值邀请码
     * 
     * @author yuhang.weng
     * @DateTime 2017年4月28日 下午3:09:56
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "entrySMSInvication", method = RequestMethod.POST)
    public JSONObject entrySMSInvication(@RequestParam String json) {
        JSONObject returData = null;
        try {
            returData = serveService.entrySMSInvication(json);
        } catch (OperationException e) {
            return AppNormalService.error(e.getMessage(), e.getCode());
        }
        return returData;
    }

    /**
     * 获取用户短信充值记录列表
     * 
     * @author yuhang.weng
     * @DateTime 2017年4月28日 下午3:09:53
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getSMSRechargeRecordList", method = RequestMethod.POST)
    public JSONObject getSMSRechargeRecordList(@RequestParam String json) {
        return serveService.getSMSRechargeRecordList(json);
    }

    @RequestMapping(value = "addSMSOrder", method = RequestMethod.POST)
    public JSONObject addSMSOrder(@RequestParam String json) {
        return serveService.addSMSOrder(json);
    }

    @RequestMapping(value = "getFreeMeasureSiteList", method = RequestMethod.POST)
    public JSONObject getFreeMeasureSiteList(@RequestParam String json) {
        return serveService.getFreeMeasureSiteList(json);
    }
}
