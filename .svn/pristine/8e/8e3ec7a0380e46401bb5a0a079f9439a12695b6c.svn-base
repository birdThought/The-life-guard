package com.lifeshs.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.component.uedit.ActionEnter;
import com.lifeshs.entity.report.TReport;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service.alipay.util.AlipayNotify;
import com.lifeshs.service1.order.OrderService;
//import com.lifeshs.utils.ImageUtil;
import com.lifeshs.utils.JSONHelper;
import com.lifeshs.utils.ParserParaUtil;
import com.lifeshs.utils.image.ImageUtilV2;

@Controller("commonController")
@RequestMapping(value = "commonControl")
public class CommonController extends BaseController {

    private static final Logger logger = Logger.getLogger("pay");

    @Resource(name = "v2OrderService")
    protected OrderService orderService;
    
    /**
     * @Description: 获取全部省份
     * @Author: wenxian.cai
     * @Date: 2017/6/24 11:20
     */
    @RequestMapping(params = "getProvince", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getAllProvince() {
        AjaxJson ajaxJson = new AjaxJson();
        List<Map<String, String>> province = dataAreaService.findAllProvince();
        ajaxJson.setObj(province);
        return ajaxJson;
    }

    /**
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 根据省份code正则表达动态获取对应的城市
     */
    @RequestMapping(params = "getCity", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getCitiesByCode(@RequestParam String provinceCode) {
        if (provinceCode == null)
            return null;
        String regex = "^" + provinceCode + "[0-9][0-9][0]{2}";
        List<Map<String, String>> cities = dataAreaService.findCity(regex);
        AjaxJson json = new AjaxJson();
        Map<String, Object> city = new HashMap<String, Object>();
        if (cities.size() > 1) {
            cities.remove(0);
        }
        city.put("city", cities);
        List<Map<String, String>> dis = dataAreaService.findDistrict("^" + provinceCode + "01[0-9][1-9]");
        json.setAttributes(city);
        json.setObj(dis);
        return json;
    }

    /**
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 根据省份城市code正则表达动态获取对应的区域
     */
    @RequestMapping(params = "getDistrict", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, String>> getDistrictsByCode(@RequestParam String cityCode) {
        if (cityCode == null)
            return null;
        String regex = "^" + cityCode + "[0-9][1-9]";
        List<Map<String, String>> districts = dataAreaService.findDistrict(regex);
        return districts;
    }

    /**
     * @Description: 获取地区
     * @Author: wenxian.cai
     * @Date: 2017/6/24 14:19 param：provinceCode 城市code前两位
     */
    @RequestMapping(params = "getDistrictByProvinceCode", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getDistrictByProvinceCode(@RequestParam String provinceCode) {
        AjaxJson ajaxJson = new AjaxJson();
        if (provinceCode == null)
            return null;
        String regex = "^" + provinceCode + "01[0-9][1-9]";
        List<Map<String, String>> districts = dataAreaService.findDistrict(regex);
        ajaxJson.setObj(districts);
        return ajaxJson;
    }

    /**
     * uEidt的配置
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月30日
     * @serverComment
     */
    @RequestMapping(params = "ueditConf", method = RequestMethod.GET)
    public void config(HttpServletRequest request, HttpServletResponse response, String action) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建支付宝收款页面
     *
     * @param cash
     *            金额
     * @param
     * @param response
     * @param payType 支付订单类型 1_机构短信充值订单；
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(params = "alipay", method = RequestMethod.GET)
    public ModelAndView createAliPayPage(@RequestParam(required = true) String order,
                                         @RequestParam(required = true) String cash,
                                         @RequestParam(required = false) String orderId,
                                         @RequestParam(required = false) int payType,
                                         HttpServletRequest request,
                                         HttpServletResponse response) throws UnsupportedEncodingException {

        String subject = request.getParameter("subject");
        subject = java.net.URLDecoder.decode(subject, "UTF-8");

        ModelAndView modelAndView = new ModelAndView("com/pay/aliPay");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
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
        logger.info("request param: " + json.toString());
        try {
            PrintWriter printWriter = response.getWriter();
            if (AlipayNotify.checkParams(request)) {// 验证通过
                printWriter.print("success");
                String appId = request.getParameter("app_id");

                boolean isAppCallBack = StringUtils.isNotBlank(appId);

                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");// 商户订单号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");// 支付宝交易号
                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");// 交易状态
                String sellerAccount = new String(request.getParameter("seller_email").getBytes("ISO-8859-1"), "UTF-8");// 卖家账号
                String payerAccont = isAppCallBack ? request.getParameter("buyer_logon_id")
                        : new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");// 买家账号
                double payMoney = isAppCallBack ? Double.parseDouble(request.getParameter("receipt_amount"))
                        : Double.parseDouble(
                                new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8")); // 支付金额
                JSONObject extraData = null;
                if (isAppCallBack) {
                    extraData = json.getJSONObject("passback_params");
                }
                String deviceType = isAppCallBack ? "app" : "web";
                switch (trade_status) {
                case "TRADE_FINISHED":// 交易完成

                    break;
                case "TRADE_SUCCESS":// 支付成功
                    Integer couponsId = extraData.getInteger("couponsId");
                    orderService.finishOrder(out_trade_no, trade_no, payerAccont, sellerAccount, payMoney, 1, "app", couponsId);
                    break;
                }
            } else {
                printWriter.print("fail");
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OperationException e) {
            // finishOrder抛出异常
            logger.error("finishOrder抛出异常:" + e.getMessage());
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
        if (true) {// 验证通过
            String out_trade_no = null;// 商户订单号
            try {
                String appId = request.getParameter("app_id");

                boolean isAppCallBack = StringUtils.isNotBlank(appId);

                out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");// 支付宝交易号
                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");// 交易状态
                String sellerAccount = new String(request.getParameter("seller_email").getBytes("ISO-8859-1"), "UTF-8");// 卖家账号
                String payerAccont = isAppCallBack ? request.getParameter("buyer_logon_id")
                        : new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");// 买家账号
                double payMoney = isAppCallBack ? Double.parseDouble(request.getParameter("receipt_amount"))
                        : Double.parseDouble(
                                new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8")); // 支付金额

                String extraData = null;
                if (isAppCallBack) {
                    extraData = request.getParameter("passback_params");
                }

                String deviceType = isAppCallBack ? "app" : "web";
                if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                    String extra_common_param = request.getParameter("extra_common_param");
                    extra_common_param = URLDecoder.decode(extra_common_param, "UTF-8");
                    Map extra = JSONHelper.toHashMap(extra_common_param);
                    Integer payType = null;
                    if (!"".equals(extra.get("payType"))) {
                        payType =  Integer.parseInt((String) extra.get("payType"));
                    }
                    Integer orderId = null;
                    if (!"".equals(extra.get("orderId"))) {
                        orderId = Integer.parseInt(String.valueOf(extra.get("orderId")));
                    }

                    switch (payType) {  //判断支付订单类型
                        case 1:     //机构短信充值订单
                            orderService.finishOrderStoreSms(out_trade_no, trade_no, payerAccont, sellerAccount, payMoney, 1,
                            deviceType, extraData);
                            url = "/org/push";
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                url = "common/500";
            }
        }
//        return "forward:memberControl.do?finishOrde&order=" + request.getParameter("extra_common_param");
        return "forward:" + url;
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
    }

    /**
     * 提交意见反馈或分页获取数据入口
     *
     * @return
     */
    @RequestMapping(params = "reportSet")
    public @ResponseBody AjaxJson commitReport(@RequestParam String action,
            @RequestParam(required = false) Integer page, @RequestBody(required = false) String text) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        switch (action) {
        case "add":// 添加
            if (text == null) {
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            TReport report = new TReport();
            report.setCreateDate(new Date());
            report.setUserType(user.getLut().equals("m") ? 0 : 1);
            report.setMessage(text);
            report.setIsRead(false);
            report.setContactInformation(user.getMobile());
            report.setUserId(user.getId());
            ajaxJson.setSuccess(commonTrans.save(report) > 0);
            break;
        case "get":// 分页获取
            Map<String, Object> condition = new HashMap<>();
            condition.put("userId", user.getId());
            ajaxJson.setObj(commonTrans.findEntityByPageDesc(TReport.class, condition, page == null ? 1 : page, 10,
                    "createDate"));
            break;
        }
        return ajaxJson;
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
