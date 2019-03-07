package com.lifeshs.app.api.wechat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifeshs.common.constants.common.order.PayReturnOrderTypeEnum;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.component.weChat.UnifiedOrderUtils;
import com.lifeshs.component.weChat.WXPayUtil;
import com.lifeshs.service1.order.CustomOrderService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.order.famousDoctor.FamousDoctorOrderService;
import com.lifeshs.service1.order.vip.VipUserOrderService;
import com.lifeshs.thirdservice.DrugsService;
import com.lifeshs.thirdservice.impl.WeChatHttpUtils;

@RestController(value = "weChatController")
@RequestMapping(value = "app/wxpay")
public class WeChatController {

    private static final Logger logger = Logger.getLogger(WeChatController.class);
    
    @Resource(name = "v2OrderService")
    protected OrderService orderService;
    
    @Resource(name = "vipUserOrderServiceImpl")
    private VipUserOrderService vipOrderService;
    
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    
    @Resource(name = "famousDoctorOrderServiceImpl")
    private FamousDoctorOrderService famousDoctorOrderService;
    
    @Resource(name = "v2OrderService")
    private OrderService normalOrderService;
    
    @Resource(name = "drugsService")
    private DrugsService drugsService;
    
    /**
     * 支付结果通知
     *  服务注解
     *  @author NaN
     *  @DateTime 2018年4月23日 下午5:04:35
     *
     *  @return
     * @throws Exception 
     */
    @RequestMapping(value="notify",method=RequestMethod.POST)
    public String payStausNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> orderInfoMap = new HashMap<>();
        StringBuffer result = new StringBuffer();
        //读取提交的数据
        InputStream in = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line = "";
        while ((line = br.readLine()) != null) {
            result.append(line);
        }
        logger.info("收到微信回调:"+result.toString());
        System.out.println("result****:"+result.toString());

        //解析参数
        Map<String,String> params;
        try {
            params = WXPayUtil.xmlToMap(result.toString());
        } catch (Exception e){
            logger.info("收到微信回调:参数格式校验错误");
            orderInfoMap.put("return_msg", "参数格式校验错误");
            orderInfoMap.put("return_code", "FAIL");
            return WXPayUtil.mapToXml(orderInfoMap);
        }
        
        //验证回调签名
        String apiKey = UnifiedOrderUtils.getApi_key();
        boolean isSign = WeChatHttpUtils.isTenpaySign(params, apiKey);
        if (!isSign) {   // 签名验证没通过
            logger.info("收到微信回调:签名验证失败");
            orderInfoMap.put("return_msg", "签名失败");
            orderInfoMap.put("return_code", "FAIL");
            return WXPayUtil.mapToXml(orderInfoMap);
        }
        //验证通信标识
            if(!params.get("return_code").equals("SUCCESS")) {
                orderInfoMap.put("return_msg", "return_code不正确");
                orderInfoMap.put("return_code", "FAIL");
                return WXPayUtil.mapToXml(orderInfoMap);
            }
        //验证交易结果
        if(params.get("result_code").equals("SUCCESS")) {
            String out_trade_no = params.get("out_trade_no");// 商户订单号
            String transaction_id = params.get("transaction_id");// 微信交易号
//            String result_code = params.get("result_code");// 交易状态
            //int payMoney = Integer.parseInt(params.get("total_fee")); // 支付金额 分
            double payMoney = Double.parseDouble(params.get("total_fee")); // 支付金额
            String sellerAccount = ""; //卖家账号
            String payerAccont = "";//买家账号
            
            //解析自定义参数
            String attach = params.get("attach");
            Map<String,String> map = new HashMap<String,String>();
            String[] param = attach.split(",");
            for(int i=0;i<param.length;i++){
                String [] child = param[i].split(":");
                map.put(child[0], child[1]);
            }
            
            int orderType = Integer.parseInt(map.get("orderType"));
            PayReturnOrderTypeEnum orderTypeEnum = PayReturnOrderTypeEnum.getPayReturnOrderType(orderType);
            switch (orderTypeEnum){
                case SERVE:   //门店服务
                case SMS:     //短信充值
                    Integer couponsId = 0;
                    if (null != map.get("coupon_id") && !map.get("coupon_id").equals("null")) {
                        couponsId = Integer.parseInt(map.get("coupon_id"));
                    }
                    orderService.finishOrder(out_trade_no, transaction_id, payerAccont, sellerAccount, payMoney, PayTypeEnum.WECHAT.getValue(), "app", couponsId);
                    break;
                case SERVE_REFUND:   //门店服务退款
                    break;
                case REPORT_ANALYSIS:  //健康数据分析服务
                    break;
                case VIP:       //套餐订单
                    vipOrderService.finishOrder(out_trade_no, transaction_id, payerAccont, sellerAccount, payMoney, PayTypeEnum.WECHAT, "app");
                    break;
                case FAMOUS_DOCTOR:   //名医预约
                    famousDoctorOrderService.validOrder(out_trade_no, transaction_id, payerAccont, sellerAccount, payMoney, PayTypeEnum.WECHAT, "app");
                    break;
                case PRIVATE_ORDER:   //自定义订单
                    customOrderService.finishOrderPrivate(out_trade_no, transaction_id, payerAccont, sellerAccount, payMoney, PayTypeEnum.WECHAT, "app");
                    break;
                case DRUGS: //药品订单
                    drugsService.finishOrder(out_trade_no, transaction_id, payerAccont, sellerAccount, payMoney, PayTypeEnum.WECHAT, "app" , map);
                    break;
            }
        }
//        }else {
//            //暂不处理支付失败的情况
//        }
        //直接写流来返回数据
        //response.setHeader( "Content-type", "text/html;charset=UTF-8" );
        //PrintWriter printWriter = response.getWriter();
        //printWriter.print("success");

        logger.info("收到微信回调: resultMsg: SUCCESS");
        orderInfoMap.put("return_msg", "OK");
        orderInfoMap.put("return_code", "SUCCESS");
        return WXPayUtil.mapToXml(orderInfoMap);
    }
}
