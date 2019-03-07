package com.lifeshs.service.alipay;

import com.lifeshs.service.alipay.config.AlipayAndroidConfig;
import com.lifeshs.service.alipay.config.AlipayConfig;
import com.lifeshs.service.alipay.sign.RSA;
import com.lifeshs.service.alipay.util.AlipayCore;
import com.lifeshs.service.alipay.util.OrderInfoUtil2_0;
import com.lifeshs.utils.*;
import com.lifeshs.utils.UUID;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.lifeshs.utils.JSONHelper.toHashMap;

/**
 * Created by XuZhanSi on 2016/10/13 0013.
 */
public class AlipayService {
	/**
	 * 支付宝提供给商户的服务接入网关URL(新)
	 */
//	private static final String ALIPAY_GATEWAY_NEW = "https://openapi.alipay.com/gateway.do?";	//新接口
	private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";		//旧接口


	/**
	 *
	 * @param subject
	 *            商品名称
	 * @param cash
	 *            支付金额
	 * @see ali旧接口回调参数为：extra_common_param
	 * 		ali新接口回调参数为：passback_params
	 */
	public static String createOrdeFlow(String orderNumber, String orderId, String subject, String cash, int payType) {
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_id", AlipayConfig.seller_id);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		sParaTemp.put("out_trade_no", orderNumber);
		sParaTemp.put("subject", subject);
		// try {
		// sParaTemp.put("subject", new
		// String(subject.getBytes("ISO-8859-1"),"UTF-8"));
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		sParaTemp.put("total_fee", cash);
		orderId = orderId== null ? "" : orderId;
		String extra_common_param = "{\"orderId\":\""+orderId+"\",\"payType\":\"" + payType + "\"}";
		
		try {
            extra_common_param = URLEncoder.encode(extra_common_param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		
		sParaTemp.put("extra_common_param", extra_common_param);
		/*if (payType != null) {	//新接口使用方式
			String passback_params = "merchantBizType=3C&merchantBizNo=2016010101111";
			sParaTemp.put("passback_params", URLEncoder.encode(passback_params));
		}*/
		return buildRequest(sParaTemp, "get");
	}

	public static String buildRequest(Map<String, String> sParaTemp, String method) {
		Map<String, String> sPara = buildRequestPara(sParaTemp);
		List<String> keys = new ArrayList<String>(sPara.keySet());
		StringBuffer sbHtml = new StringBuffer();
		sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW
				+ "_input_charset=" + AlipayConfig.input_charset + "\" method=\"" + method + "\">");
		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);
			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}
		// submit按钮控件请不要含有name属性
		sbHtml.append("<input type=\"submit\"  style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
		System.out.println(sbHtml.toString());
		return sbHtml.toString();
	}

	/**
	 * 生成签名结果
	 *
	 * @param sPara
	 *            要签名的数组
	 * @return 签名结果字符串
	 */
	public static String buildRequestMysign(Map<String, String> sPara) {
		String prestr = AlipayCore.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String mysign = "";
		if (AlipayConfig.sign_type.equals("RSA")) {
			mysign = RSA.sign(prestr, AlipayConfig.private_key, AlipayConfig.input_charset);
		}
		return mysign;
	}

	/**
	 * 生成要请求给支付宝的参数数组
	 *
	 * @param sParaTemp
	 *            请求前的参数数组
	 * @return 要请求的参数数组
	 */
	private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
		// 除去数组中的空值和签名参数
		Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
		// 生成签名结果
		String mysign = buildRequestMysign(sPara);
		// 签名结果与签名方式加入请求提交参数组中
		sPara.put("sign", mysign);
		sPara.put("sign_type", AlipayConfig.sign_type);
		return sPara;
	}

	/**
	 *  获取数字签名
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月17日 下午4:53:25
	 *
	 *  @param totalAmount_y 支付金额
     *  @param subject 标题
     *  @param body 内容
     *  @param orderNumber 订单编号
     *  @param extraData 额外的参数
	 *  @return
	 */
	public static String getOrderInfo(Double totalAmount_y, String subject, String body, String orderNumber,
            Map<String, String> extraData) {
	    String orderInfo = orderInfo(totalAmount_y, subject, body, orderNumber, extraData, AlipayConfig.notify_url);
        return orderInfo;
	}
	
	/**
	 *  获取数字签名
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月17日 下午4:53:37
	 *
	 *  @param totalAmount_y 支付金额
	 *  @param subject 标题
	 *  @param body 内容
	 *  @param orderNumber 订单编号
	 *  @param extraData 额外的参数
	 *  @param notifyUrl 通知url
	 *  @return
	 */
	public static String getOrderInfo(Double totalAmount_y, String subject, String body, String orderNumber,
            Map<String, String> extraData, String notifyUrl) {
	    String orderInfo = orderInfo(totalAmount_y, subject, body, orderNumber, extraData, notifyUrl);
        return orderInfo;
	}
	
	/**
	 *  获取数字签名
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月17日 下午4:54:13
	 *
	 *  @param totalAmount_y 支付金额
     *  @param subject 标题
     *  @param body 内容
     *  @param orderNumber 订单编号
     *  @param extraData 额外的参数
	 *  @param notifyUrl 通知url
	 *  @return
	 */
	private static String orderInfo(Double totalAmount_y, String subject, String body, String orderNumber,
            Map<String, String> extraData, String notifyUrl) {
	    String appId = AlipayAndroidConfig.appId;
        String rsaPrivate = AlipayAndroidConfig.rsaPrivate;

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         * 
         * orderInfo的获取必须来自服务端；
         */
        Map<String, String> params = null;
        try {
            params = OrderInfoUtil2_0.buildOrderParamMap(totalAmount_y, subject, body, orderNumber, appId, notifyUrl,
                    extraData);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String sign = OrderInfoUtil2_0.getSign(params, rsaPrivate);
        final String orderInfo = orderParam + "&" + sign;

        return orderInfo;
	}
	
	/**
	 * 生成一个订单号
	 * 
	 * @param userCode
	 * @return
	 */
	public static String createOrderNumber(String userCode) {
	    String pre = userCode + DateTimeUtilT.dateTimeWithoutForm(new Date());
	    int startIndex = pre.length();
	    String uuid = UUID.generate().substring(startIndex, 32);   // 11位
	    String orderNumber = userCode + DateTimeUtilT.dateTimeWithoutForm(new Date()) + uuid;  // 32位
	    orderNumber = StringUtils.upperCase(MD5Utils.encryptStringWithUTF8(orderNumber));
		return orderNumber;
	}
	
	public static String createOrderNumber() {
	    String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	    String orderNumber = dateTime + RandCodeUtil.randNumberCodeByCustom("1", 6);
	    
	    // 服务订单16位
//	    String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssS"));
//        String orderNumber = dateTime + RandCodeUtil.randNumberCodeByCustom("1", 3);
	    return orderNumber;
	}

	/**
	 *
	 * @param sign 商户请求参数的签名串
	 * @param tradeNo 支付宝交易号，和商户订单号不能同时为空
	 * @param refundAmount 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
	 * @param refundReason 退款的原因说明
	 * @return
	 */
	public static String requestTradeRefund (String sign, String tradeNo, Double refundAmount, String refundReason) {
		String timestamp = DateTimeUtilT.dateTime(new Date());
		String appId = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMmnPgJUIqMQhDpls76kUgGYFzni8J9YCZHE2vv4";
		String signType = AlipayConfig.sign_type;
		String version = "1.0";
		String url = ALIPAY_GATEWAY_NEW +
				"timestamp=" +timestamp+ "&" +
				"method=alipay.trade.refund&" +
				"app_id=" + appId + "&" +
				"sign_type=" + signType + "&" +
				"sign=" + sign + "&" +
				"version=" + version + "&" +
				"biz_content={" +
				"\"trade_no\":\"" + tradeNo + "\"," +
				"\"refund_amount\":\"" + refundAmount + "\"," +
				"\"refund_reason\":\""+ refundReason +"\"" +
				"}";
		HttpUtils.getResultEntity(url, null, null, HttpUtils.GET, true,  new HttpUtils.HttpCallBack() {
			@Override
			public void resultCallBack(Object result) {
				System.out.println("请求支付退款完成");
				Map map = toHashMap(result);
				System.out.println("map:" + map);
			}
		});
		return null;
	};



	public static void main(String[] args) throws UnsupportedEncodingException {
		// Map<String, String> extraData = new HashMap<>();
		// extraData.put("serveGroupId", "啊");
		// Map<String, String> param =
		// OrderInfoUtil2_0.buildOrderParamMap("100", "subjectName",
		// "bodyContent", "orderNumber", "appId", "notifyUrl", extraData);
		// System.out.println(param.toString());

//	    System.out.println(createOrderNumber("1000001"));
		//requestTradeRefund("ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE", "20150320010101001" ,0.0 , "正常退狂");
	    createOrderNumber();
	}
}
