package com.lifeshs.business.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service.alipay.util.AlipayNotify;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.order.reportAnalysis.ReportAnalysisOrderService;
import com.lifeshs.utils.ParserParaUtil;
import com.lifeshs.utils.image.ImageUtilV2;

//import com.lifeshs.utils.ImageUtil;

@Controller("commonController")
@RequestMapping(value = "/common")
public class CommonController extends BaseController {

    private Logger payLogger = Logger.getLogger("pay");

    @Resource(name = "v2OrderService")
    protected OrderService orderService;
    @Autowired
    private ReportAnalysisOrderService reportAnalysisOrderService;

    /**
     * 创建支付宝收款页面
     *
     * @param cash
     *            金额
     * @param
     * @param payType 支付订单类型 1_机构短信充值订单；
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/alipay")
    public ModelAndView createAliPayPage(@RequestParam(value = "order") String order,
                                         @RequestParam(value = "cash") String cash,
                                         @RequestParam(value = "orderId", required = false) String orderId,
                                         @RequestParam(value = "payType", required = false) int payType,
                                         @RequestParam("subject") String subject) throws UnsupportedEncodingException {

//        String subject = request.getParameter("subject");
//        subject = URLDecoder.decode(subject, "UTF-8");

        ModelAndView modelAndView = new ModelAndView("common/pay/alipay");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
        if (subject.length() < 256) {
            // double money = Double.parseDouble(cash);
            String html = AlipayService.createOrdeFlow(order, orderId, subject, cash, payType);
            modelAndView.addObject("html", html);
        }
        return modelAndView;
    }

    /**
     * 支付宝交易状态的异步通知
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "aliNotify", method = { RequestMethod.GET, RequestMethod.POST })
    public void payStausNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> params = ParserParaUtil.getParams(request);
        JSONObject json = new JSONObject();
        for (String key : params.keySet()) {
            json.put(key, params.get(key));
        }
        payLogger.info("request param: " + json.toString());
        if (AlipayNotify.checkParams(request)) {// 验证通过
            boolean isAppCallBack = false;
            String appId = json.getString("app_id");
            if (StringUtils.isNotBlank(appId)) {
                isAppCallBack = true;
            }
            String out_trade_no = json.getString("out_trade_no");
            String trade_no = json.getString("trade_no");
            String trade_status = json.getString("trade_status");
            String sellerAccount = json.getString("seller_email");
            String payAccount = isAppCallBack ? json.getString("buyer_logon_id") : json.getString("buyer_email");
            Double payMoney = isAppCallBack ? json.getDouble("receipt_amount") : json.getDouble("total_fee");
            
//            JSONObject extraData = null;
//            if (isAppCallBack) {
//                extraData = json.getJSONObject("passback_params");
//            }
            switch (trade_status) {
            case "TRADE_FINISHED":// 交易完成

                break;
            case "TRADE_SUCCESS":// 支付成功
                try {
                    reportAnalysisOrderService.validOrder(out_trade_no, trade_no, payAccount, sellerAccount, payMoney, PayTypeEnum.ALIPAY, "app");
                } catch (OperationException e) {
                    payLogger.error("修改状态出错");
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 支付宝完成交易后跳转的页面
     *
     * @param request
     */
    @RequestMapping(value = "aliReturn", method = RequestMethod.GET)
    public String aliReturn(HttpServletRequest request) {
        String url = null;
        Map<String, Object> params = ParserParaUtil.getParams(request);
        JSONObject json = new JSONObject();
        for (String key : params.keySet()) {
            json.put(key, params.get(key));
        }
        payLogger.info("request param: " + json.toString());
        if (AlipayNotify.checkParams(request)) {// 验证通过
            boolean isAppCallBack = false;
            String appId = json.getString("app_id");
            if (StringUtils.isNotBlank(appId)) {
                isAppCallBack = true;
            }
            String out_trade_no = json.getString("out_trade_no");
            String trade_no = json.getString("trade_no");
            String trade_status = json.getString("trade_status");
            String sellerAccount = json.getString("seller_email");
            String payAccount = isAppCallBack ? json.getString("buyer_logon_id") : json.getString("buyer_email");
            Double payMoney = isAppCallBack ? json.getDouble("receipt_amount") : json.getDouble("total_fee");

//            String extraData = null;
//            if (isAppCallBack) {
//                extraData = request.getParameter("passback_params");
//            }

            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                try {
                    reportAnalysisOrderService.validOrder(out_trade_no, trade_no, payAccount, sellerAccount, payMoney, PayTypeEnum.ALIPAY, "app");
                    url = "common/redirect/goToOrderManager";
                } catch (OperationException e) {
                    if (ErrorCodeEnum.REPEAT.value() == e.getCode()) {
                        url = "common/redirect/goToOrderManager";
                    } else {
                        payLogger.error("修改状态出错");
                        e.printStackTrace();
                        url = "common/error/500";
                    }
                }
            }
        } else {
            url = "common/error/500";
        }
        return url;
    }

    @RequestMapping(value = "checkNotify", method = RequestMethod.GET)
    public void checkNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // orderService.finishOrder("1000000220161019135600","321","1232112","12421321",0.01,1);

        PrintWriter printWriter = response.getWriter();
        File file = new File(request.getContextPath(), "notify.txt");
        if (!file.exists()) {
            printWriter.println("没有执行过异步通知");
            printWriter.flush();
            return;
        }
        FileInputStream in = new FileInputStream(file);
        int len = 0;
        byte[] buf = new byte[1024];

        printWriter.println("开始获取notify：");
        printWriter.flush();
        while ((len = in.read(buf)) != -1) {
            String str = new String(buf, 0, len);
            printWriter.println(str);
            printWriter.flush();
        }
        printWriter.close();
        in.close();
    }


    /**
     * 文件上传
     * 
     * @param target
     * @param uploadFile
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "uploadFile/{target}", method = RequestMethod.POST)
    public @ResponseBody AjaxJson uploadFile(@PathVariable String target,
            @RequestParam(value = "path", required = false) MultipartFile uploadFile) throws IOException {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        switch (target) {
        case "img":// 图片
            String netPath = "";
            String uploadName = uploadFile.getOriginalFilename();
            String arr[] = uploadName.split("\\.");
            if (!"png".equals(arr[arr.length - 1]) && !"jpg".equals(arr[arr.length - 1])) {
                resObject.setMsg("图片类型不合法");
                break;
            }
            if (uploadFile.getSize() > 1024 * 1024) {
                resObject.setMsg("图片大小超出限制");
                break;
            }
            if (uploadFile.getSize() > 200 * 1024) {
                netPath = ImageUtilV2.saveByte(uploadFile.getBytes(), "", true, 500, 500);
            } else {
                netPath = ImageUtilV2.saveByte(uploadFile.getBytes(), "", true);
            }
            resObject.setSuccess(true);
            resObject.setObj(netPath);
            resObject.setMsg("上传文件成功");
            return resObject;
        }
        return resObject;
    }
}
