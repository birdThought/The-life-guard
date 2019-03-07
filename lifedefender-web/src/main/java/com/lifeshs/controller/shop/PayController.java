package com.lifeshs.controller.shop;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.service.alipay.util.AlipayNotify;
import com.lifeshs.service.shop.ShopService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.shop.AliPayConfig;
import com.lifeshs.shop.PayConfig;
import com.lifeshs.utils.HttpUtils;
import com.lifeshs.utils.PayCommonUtil;
import com.lifeshs.utils.XMLUtil;


@Controller
@RequestMapping("shop/pay")
public class PayController {
	
	static final Logger logger = Logger.getLogger(PayController.class);
		
	@Autowired
	private ShopService shopService;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
		
	private Boolean czflg;
		
	/**
	 * 微信H5支付
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/wxPayH5")
	public void wxPayH5(ModelMap model,HttpServletRequest request1) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		try {
			// 付款金额，必填
			String total_fee = request1.getParameter("WIDtotal_fee");
			//订单编号
			String orderNo = request1.getParameter("orderNo");
			// ip地址获取
			String basePath = request1.getServerName() + ":" + request1.getServerPort();
			// 账号信息
			String appid = PayConfig.getAppid(); // appid
			String mch_id = PayConfig.getMch_id(); // 商业号
			String key = PayConfig.getApi_key(); // key

			String currTime = PayCommonUtil.getCurrTime();
			String strTime = currTime.substring(8, currTime.length());
			String strRandom = PayCommonUtil.buildRandom(4) + "";
			String nonce_str = strTime + strRandom;
			// 价格 注意：价格的单位是分
			String order_price = new BigDecimal(total_fee).multiply(new BigDecimal(100)).toString().split("\\.")[0];
			//订单编码												
			String out_trade_no = orderNo;
			// 获取发起电脑 ip
			String spbill_create_ip = HttpUtils.getIpAddress(request1);
			System.out.println(spbill_create_ip);
			// 回调接口
			String notify_url = PayConfig.NOTIFY_URL_H5 + session.getAttribute("userId");
			// 页面跳转同步通知页面路径
			String trade_type = "MWEB";
 
			// 设置package订单参数
			SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
			packageParams.put("appid", appid);
			packageParams.put("mch_id", mch_id);
			// 生成签名的时候需要你自己设置随机字符串
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("out_trade_no", out_trade_no);
			packageParams.put("total_fee", order_price);
			packageParams.put("spbill_create_ip", spbill_create_ip);
			packageParams.put("notify_url", notify_url);
			packageParams.put("trade_type", trade_type);				
			packageParams.put("body", PayConfig.BODY);
							
			packageParams.put("scene_info", "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"http://www.lifekeepers.cn\",\"wap_name\": \"生命守护商城\"}}");
			String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
			packageParams.put("sign", sign);
			String requestXML = PayCommonUtil.getRequestXml(packageParams);
			String resXml = HttpUtils.postData(PayConfig.UFDODER_URL, requestXML);
			Map map = XMLUtil.doXMLParse(resXml);
			String urlCode = (String) map.get("code_url");
			logger.info("-------------ip="+spbill_create_ip+"-----------"); 				
			//确认支付过后跳的地址,需要经过urlencode处理
			String urlString = URLEncoder.encode("http://www.lifekeepers.cn/shop/index", "GBK");
			
			
			String mweb_url = map.get("mweb_url")+""+"&redirect_url="+urlString;
			czflg = true;
			System.out.println(map.get("mweb_url"));
			System.out.println(mweb_url);
			logger.info("---------------执行wx调用-----------"); 
				
			response.sendRedirect(mweb_url);
			logger.info("before ----" + czflg);
			
			result.put("sHtmlText", urlCode);
			result.put("success", true);
		} catch (Exception e) {
			logger.info(e);
			result.put("errormsg", e.getMessage());
		}
	}
	
	/**
	 * 执行回调 确认支付后处理事件 例如添加金额到数据库等操作
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/wxPayNotify")
	public void weixin_notify(ModelMap model)throws Exception {
		logger.info("-------------wx回调进入-----------"); 
		String xmlMsg = readData(request);
		System.out.println("pay notice---------"+xmlMsg);
		Map params = XMLUtil.doXMLParse(xmlMsg);
//			//商户号
		String mch_id  = params.get("mch_id")+"";
		String result_code  = params.get("result_code")+"";
//			//付款银行
		String bank_type = params.get("bank_type")+"";
//			// 总金额
//			Integer total_fee  = (Integer) params.get("total_fee");
//			//现金支付金额
//			String cash_fee     = params.get("cash_fee");
//			// 微信支付订单号
		String transaction_id = params.get("transaction_id")+"";
//			// 商户订单号
		String out_trade_no = params.get("out_trade_no")+"";
//			// 支付完成时间，格式为yyyyMMddHHmmss
//			String time_end      = params.get("time_end");
		
		/////////////////////////////以下是附加参数///////////////////////////////////
		
		String attach      = params.get("attach")+"";
//			String fee_type      = params.get("fee_type");
//			String is_subscribe      = params.get("is_subscribe");
//			String err_code      = params.get("err_code");
//			String err_code_des      = params.get("err_code_des");
				
		String userid = null;
		try { 
			// 过滤空 设置 TreeMap
			SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
			Iterator it = params.keySet().iterator();
			while (it.hasNext()) {
				String parameter = (String) it.next();
				String parameterValue = params.get(parameter)+"";
				String v = "";
				if (null != parameterValue) {
					v = parameterValue.trim();
				}				
				packageParams.put(parameter, v);
			}
			// 查看回调参数
			// LogUtil.writeMsgToFile(packageParams.toString());
			String total_fee = new BigDecimal((String) packageParams.get("total_fee")).divide(new BigDecimal(100)).toString();
			userid = (String) packageParams.get("userid");
			// 账号信息
			String resXml = "";
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				czflg = true;
								
				model.put("sHtmlText", "支付成功");
				try {
					synchronized (czflg) {
						if (czflg) {								
							shopService.finishOrder(out_trade_no,transaction_id,mch_id,bank_type,total_fee,PayTypeEnum.WECHAT);								
							czflg = false;
							
							logger.info("end -----微信回调结束-----" + czflg);
						}							
						logger.info("enderror ----微信回调异常----" + czflg);
 
					}
				} catch (Exception e) {
					logger.info(e);
				}				
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			} else {
				model.put("sHtmlText", "购买失败");
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[购买失败]]></return_msg>" + "</xml> ";
			}
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
		}
 
	}
	
	public static String readData(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			StringBuilder result = new StringBuilder();
			br = request.getReader();
			for (String line; (line=br.readLine())!=null;) {
				if (result.length() > 0) {
					result.append("\n");
				}
				result.append(line);
			}
 
			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (br != null)
				try {br.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	
//------------------------------------alibaba--------------------------------------
	
	
	/**
	 *  AliPay支付H5		 
	 * @throws IOException 
	 */
	@RequestMapping("/AliPayH5")
	public void CreatPayOrderForH5() throws IOException {			 
		AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.URL, AliPayConfig.getAppid(), 
				 AliPayConfig.getPrivate_Key(),AliPayConfig.FORMAT, AliPayConfig.CHARSET, AliPayConfig.getAlipayPublic_Key(), AliPayConfig.SIGNTYPE); 
		 //获得初始化的AlipayClient 
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest(); 
		
		// 付款金额，必填
		String total_fee = request.getParameter("WIDtotal_fee");
		//订单编号
		String out_trade_no = request.getParameter("orderNo");						
		// 价格 注意：价格的单位是分
		String order_price = total_fee/*new BigDecimal(total_fee).multiply(new BigDecimal(100)).toString().split("\\.")[0]*/;						
		
		 //发起支付宝支付 //设置支付完成后的返回地址 
		alipayRequest.setReturnUrl("http://www.lifekeepers.cn/shop/pay/gateway"); 
		
		
		 //设置回跳和通知地址
		alipayRequest.setNotifyUrl("http://www.lifekeepers.cn/shop/pay/AliPayNotify"); 
		alipayRequest.setBizContent("{" + "    \"out_trade_no\":\""+out_trade_no+"\"," + 
										"    \"total_amount\":\""+order_price+"\"," +
										"    \"subject\":\""+"生命守护商城购买"+"\"," + 
										"    \"product_code\":\"QUICK_WAP_PAY\"" + 
										"  }"); 
		String form=""; 
		try { 
			// 调用SDK生成表单 
			form = alipayClient.pageExecute(alipayRequest).getBody(); 
		} 
		catch (AlipayApiException e) {
			form = "err"; 
			e.printStackTrace(); 
		} 
		
		response.setContentType("text/html;charset=" + AliPayConfig.CHARSET); 
		//直接将完整的表单html输出到页面 
		response.getWriter().write(form); 
		response.getWriter().flush(); 
		response.getWriter().close(); 
	}
	
	@RequestMapping(value="AliPayNotify",method=RequestMethod.POST)
	public void AlipayTradePayNotify() throws AlipayApiException, IOException { 
		Map<String, String> params = new HashMap<String, String>(); 
		Map requestParams = request.getParameterMap();		
		for (Iterator<String> iter = requestParams.keySet().iterator();iter.hasNext();) { 
			String name = (String)iter.next(); 
			String[] values = (String[])requestParams.get(name); 
			String valueStr = ""; 
			for (int i = 0; i < values.length; i++) { 
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ","; 
			} 
			// 乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8"); 
			params.put(name, valueStr); 
		} 
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.getAlipayPublic_Key(), AliPayConfig.CHARSET, AliPayConfig.SIGNTYPE); 
		/*boolean signVerified = AlipayNotify.checkParams(request);*/
		logger.info("-------------signVerified="+signVerified+"------------"); 
		// 调用SDK验证签名		
		if (signVerified) { 
			//签名验证成功 
			response.getWriter().print("success");		
			//订单号 
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//交易金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			//卖家支付账号
			String seller_id = new String(request.getParameter("seller_id").getBytes("ISO-8859-1"),"UTF-8");
			//买家支付账号
			String buyer_id = new String(request.getParameter("buyer_id").getBytes("ISO-8859-1"),"UTF-8");
	
			//处理自己的逻辑
			shopService.finishOrder(out_trade_no,trade_no,seller_id,buyer_id,total_amount,PayTypeEnum.ALIPAY);
			System.out.println("success");
			logger.info("-------------回调结束------------"); 
		} else { 
			// 验证失败 						
			response.getWriter().print("failure");					
			// 调试用，写文本函数记录程序运行情况是否正常 
			String sWord = AlipaySignature.getSignCheckContentV1(params); 
			
			logger.info(sWord); 
			logger.info("--------------失败---------------"); 
		} 
				
	}
	
	@RequestMapping("/gateway") 
	public ModelAndView returnUrl(){ 
		ModelAndView mv=new ModelAndView("shop/index"); 
		logger.info("支付宝网关被调用"); 
		return mv; 
	}
		
						
}
	

