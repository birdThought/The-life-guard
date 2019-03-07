package com.lifeshs.app.api.member.release;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.Record;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.hobby.Hobby;
import com.lifeshs.common.constants.app.hobby.UserHobby;
import com.lifeshs.common.constants.app.img.Image;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.order.PayReturnOrderTypeEnum;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.data.HobbyPO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.service.alipay.util.AlipayNotify;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.user.IAppUserService;
import com.lifeshs.service.tool.ICacheService;
import com.lifeshs.service1.data.HobbyService;
import com.lifeshs.service1.order.CustomOrderService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.order.famousDoctor.FamousDoctorOrderService;
import com.lifeshs.service1.order.vip.VipUserOrderService;
import com.lifeshs.thirdservice.impl.DrugsServiceImpl;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.ParserParaUtil;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 *  app对外开放接口
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月8日 下午5:32:19
 */
@RestController(value = "releaseController")
@RequestMapping(value = "/app")
public class ReleaseController {

    @Resource(name = "appUserService")
    private IAppUserService userService;
    
    @Resource(name = "hobbyService")
    private HobbyService hobbyService;

    @Autowired
    private ICacheService cacheService;
    
    @Autowired
    private IMemberService memberService;
    
    @Resource(name = "userHobbyService")
    private com.lifeshs.service1.member.HobbyService userHobbyService;
    
    @Resource(name = "vipUserOrderServiceImpl")
    private VipUserOrderService vipOrderService;
    
    @Resource(name = "famousDoctorOrderServiceImpl")
    private FamousDoctorOrderService famousDoctorOrderService;
    
    @Resource(name = "v2OrderService")
    private OrderService normalOrderService;

    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    
    @Resource(name = "drugsService")
    private DrugsServiceImpl drugsService;
    
    /**
     * 终端登录
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午10:47:51
     *
     * @param request
     * @return
     */
    @RequestMapping(value = {"/login", "/user/login"}, method = RequestMethod.POST)
    public JSONObject login(String json, HttpServletRequest request) throws Exception {
        String ip = request.getHeader("X-Real-IP");
        return userService.login(json, ip);
    }

    /**
     * 用户注册
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午10:51:05
     *
     * @param json
     * @return
     */
    @RequestMapping(value = {"/register", "/user/register"}, method = RequestMethod.POST)
    public JSONObject register(String json) throws Exception {
        return userService.register(json);
    }

    /**
     * 用户注册
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午10:51:05
     *
     * @param json
     * @return
     */
    @RequestMapping(value = {"/registerMobile", "/user/registerMobile"}, method = RequestMethod.POST)
    public JSONObject registerMobile(String json) throws Exception {
        return userService.registerMobile(json);
    }

    @RequestMapping(value = {"perfectUserRegisterProfile", "/user/perfectUserRegisterProfile"}, method = RequestMethod.POST)
    public JSONObject perfectUserRegisterProfile(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        Integer userId = mm_0.getInteger(User.ID);
        String token = mm_0.getString(Normal.TOKEN);
        String sex = mm_0.getString(User.SEX);
        String birthdaySource = mm_0.getString(User.BIRTHDAY);
        Float height = mm_0.getFloat(Record.HEIGHT);
        Float weight = mm_0.getFloat(Record.WEIGHT);
        Float waist = mm_0.getFloat(Record.WAIST);
        Float bust = mm_0.getFloat(Record.BUST);
        Float hip = mm_0.getFloat(Record.HIP);
        String hobbyListStr = mm_0.getString(UserHobby.HOBBY);
        
        if (userId == null) {
            return AppNormalService.error(String.format(com.lifeshs.common.constants.common.Error.NOT_FOUND, "用户"));
        }
        if (StringUtils.isBlank(token)) {
            return AppNormalService.error("身份验证不通过", ErrorCodeEnum.AUTHORIZED);
        }
        
        String cToken = (String) cacheService.getCacheValue(CacheType.REGISTER_TOKEN_CACHE, "m_" + userId);
        if (!token.equals(cToken)) {
            return AppNormalService.error("身份验证不通过", ErrorCodeEnum.AUTHORIZED);
        }
        
        // 修改用户个人信息
        Boolean gender = null;
        if (Normal.TRUE.equals(sex)) {
            gender = true;
        }
        if (Normal.FALSE.equals(sex)) {
            gender = false;
        }
        Date birthday = null;
        if (StringUtils.isNotBlank(birthdaySource)) {
            birthday = DateTimeUtilT.date(birthdaySource);
        }
        
        UserDTO user = new UserDTO();
        user.setId(userId);
        UserRecordDTO record = new UserRecordDTO();
        record.setGender(gender);
        record.setBirthday(birthday);
        record.setHeight(height);
        record.setWeight(weight);
        record.setWaist(waist);
        record.setBust(bust);
        record.setHip(hip);
        user.setRecordDTO(record);
        
        memberService.updateUserInfo(user);
        
        List<Integer> hobbyIdList = null;
        if (StringUtils.isNotBlank(hobbyListStr)) {
            hobbyIdList = JSONArray.parseArray(hobbyListStr, Integer.class);
            userHobbyService.addUserHobby(userId, hobbyIdList);
        }
        
        // 清除缓存中的token
        cacheService.delCacheValue(CacheType.REGISTER_TOKEN_CACHE, "m_" + userId);
        return AppNormalService.success();
    }
    
    /**
     * 发送验证码
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:03:40
     *
     * @param json
     * @return
     */
    @RequestMapping(value = {"/sendVerifyCode", "/user/sendVerifyCode"}, method = RequestMethod.POST)
    public JSONObject sendVerifyCode(String json) throws Exception {
        return userService.sendVerifyCode(json);
    }

    /**
     * 校验验证码
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:03:46
     *
     * @param json
     * @return
     */
    @RequestMapping(value = {"/checkVerifyCode", "/user/checkVerifyCode"}, method = RequestMethod.POST)
    public JSONObject checkVerifyCode(String json) throws Exception {
        return userService.checkVerifyCode(json);
    }

    /**
     * 找回密码
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:04:14
     *
     * @param json
     * @return
     */
    @RequestMapping(value = {"/setPassword", "/user/setPassword"}, method = RequestMethod.POST)
    public JSONObject setPassword(String json, HttpServletRequest request) throws Exception {
        String ip = request.getHeader("X-Real-IP");
        return userService.setPasswod(json, ip);
    }
    
    /**
     * 第三方认证登录
     * @param json
     * @return
     */
    @RequestMapping(value = {"/oauthLogin", "/user/oauthLogin"}, method = RequestMethod.POST)
    public JSONObject oauthLogin(String json, HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        return userService.oauthLogin(json, ip);
    }
    /**
     * @Description: pc端登录(APP扫一扫登录pc端)
     * @author: wenxian.cai
     * @create: 2017/4/17 13:47
     */
    @RequestMapping(value = {"/pcLogin", "/user/pcLogin"}, method = RequestMethod.POST)
    public JSONObject pcLogin(String json) {
        return userService.pcLogin(json);
    }
    
    /**
     *  短信登录
     *  @author yuhang.weng 
     *  @DateTime 2017年4月14日 下午12:00:52
     *
     *  @param json
     *  @param request
     *  @return
     */
    @RequestMapping(value = {"/smsLogin", "/user/smsLogin"}, method = RequestMethod.POST)
    public JSONObject smsLogin(String json, HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        return userService.smsLogin(json, ip);
    }
    
    /**
     *  获取兴趣爱好列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月9日 上午9:29:59
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "/listHobby", method = RequestMethod.POST)
    public JSONObject listHobby(String json) {
        List<HobbyPO> hobbyList = hobbyService.listHobby();
        
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (HobbyPO h : hobbyList) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put(Hobby.ID, h.getId());
            returnData.put(Hobby.NAME, h.getName());
            returnData.put(Hobby.IMAGE, h.getImage());
            returnDataList.add(returnData);
        }
        
        return AppNormalService.success(returnDataList);
    }
    
    /**
     *  APP上传图片
     *  <p>图片上传限制大小不能超过1024*1024，同时如果是200*1024的话，图片会自动压缩成宽度为500，高度为500的新文件
     *  @author yuhang.weng 
     *  @DateTime 2017年9月6日 下午3:59:27
     *
     *  @param file 文件流
     *  @exception IOException 这里抛出的IO异常，会直接通过全局异常类返回，"服务器异常"这样的提示信息
     *  @return
     */
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public JSONObject uploadImg(@RequestParam MultipartFile file) throws IOException {
        String netPath = "";
        if (file.getSize() > 1024 * 1024) {
            return AppNormalService.error("图片大小超出限制", ErrorCodeEnum.FAILED);
        }
        if (file.getSize() > 200 * 1024) {
            netPath = ImageUtilV2.saveByte(file.getBytes(), "", true, 500, 500);
        } else {
            netPath = ImageUtilV2.saveByte(file.getBytes(), "", true);
        }
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Image.NET_PATH, netPath);
        return AppNormalService.success(returnData);
    }

    /**
     *  判断用户名是否存在
     *  @author yuhang.weng 
     *  @DateTime 2017年9月7日 下午2:43:42
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = {"/isUserNameExist", "/family/isUserNameExist"}, method = RequestMethod.POST)
    public JSONObject isUserNameExist(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String userName = mm_0.getString(User.USERNAME);

        boolean isExist = true; // 默认已关联
        String mobile = null;   // 默认手机号码为null
        // 判断手机号码是否已绑定
        UserDTO user = memberService.getUserByMobile(userName);
        if (user == null) {
            // 再判断用户名是否已被占用
            user = memberService.getUser(userName);
            if (user == null) {
                // 没有关联
                isExist = false;
            }
        }
        
        // 获取该用户的手机号码
        if (user != null) {
            mobile = user.getMobile();
        }

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Normal.IS_EXIST, isExist);
        returnData.put(User.MOBILE, mobile);
        return AppNormalService.success(returnData);
    }
    
    /**
     *  通过用户登录名查找绑定的手机号码
     *  @author yuhang.weng 
     *  @DateTime 2017年9月29日 下午2:22:36
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getBindingMobileByUserName", method = RequestMethod.POST)
    public JSONObject getBindingMobileByUserName(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String userName = mm_0.getString(User.USERNAME);
        
        UserDTO user = memberService.getUser(userName);
        if (user == null) {
            return AppNormalService.error("找不到该用户", 404);
        }
        
        String mobile = "";
        if (!user.getMobileVerified()) {
            return AppNormalService.success("该用户尚未绑定手机");
        }
        mobile = user.getMobile();
        /** 打码 */
        mobile = StringUtil.cover(mobile, 3, 4, "***");
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("mobile", mobile);
        return AppNormalService.success(returnData);
    }
    
    /** 支付日志 */
    private Logger payLogger = Logger.getLogger("pay");
    
    /**
     *  支付宝通知接口
     *  @author yuhang.weng 
     *  @DateTime 2017年10月18日 上午11:50:12
     *
     *  @param request
     */
    @RequestMapping(value = "aliNotify", method = {RequestMethod.GET, RequestMethod.POST})
    public void aliNotify(HttpServletRequest request) {
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
            
            JSONObject extraData = null;
            if (isAppCallBack) {
                extraData = json.getJSONObject("passback_params");
            }
            switch (trade_status) {
            case "TRADE_FINISHED":// 交易完成
                break;
            case "TRADE_SUCCESS":// 支付成功
                int orderType = extraData.getIntValue("orderType");
                // 对vip会员充值的订单进行处理
                if (PayReturnOrderTypeEnum.VIP.getValue() == orderType) {
                    try {
                        vipOrderService.finishOrder(out_trade_no, trade_no, payAccount, sellerAccount, payMoney, PayTypeEnum.ALIPAY, "app");
                    } catch (OperationException e) {
                        payLogger.error(e.getMessage());
                        return; // 跳出方法
                    }
                }
                // 自定义订单
                if (PayReturnOrderTypeEnum.PRIVATE_ORDER.getValue() == orderType){
                    try {
                        customOrderService.finishOrderPrivate(out_trade_no, trade_no, payAccount, sellerAccount, payMoney, PayTypeEnum.ALIPAY, "app");
                    } catch(OperationException e) {
                        payLogger.error(e.getMessage());
                        return; // 跳出方法
                    }
                }

                // 对名医预约的订单进行处理
                if (PayReturnOrderTypeEnum.FAMOUS_DOCTOR.getValue() == orderType) {
                    try {
                        famousDoctorOrderService.validOrder(out_trade_no, trade_no, payAccount, sellerAccount, payMoney, PayTypeEnum.ALIPAY, "app");
                    } catch (OperationException e) {
                        payLogger.error(e.getMessage());
                        return; // 跳出方法
                    }
                }
                // 服务订单
                // 短信充值订单
                if ((PayReturnOrderTypeEnum.SERVE.getValue() == orderType) || (PayReturnOrderTypeEnum.SMS.getValue() == orderType)) {
                    Integer couponsId = extraData.getInteger("couponsId");
                    try {
                        normalOrderService.finishOrder(out_trade_no, trade_no, payAccount, sellerAccount, payMoney, 1, "app", couponsId);
                    } catch (OperationException e) {
                        payLogger.error(e.getMessage());
                        return; // 跳出方法
                    }
                }
                
                //药品订单
                if ((PayReturnOrderTypeEnum.DRUGS.getValue() == orderType)) {
                    try {
                        Map<String,String> map = new HashMap<String,String>();
                        int addressId = extraData.getIntValue("addressId");
                        int paymentType = extraData.getIntValue("paymentType");
                        map.put("addressId", String.valueOf(addressId));
                        map.put("paymentType", String.valueOf(paymentType));
                        int money = NumberUtils.changeY2F(payMoney.toString());
                        
                        drugsService.finishOrder(out_trade_no, trade_no, payAccount, sellerAccount, money, PayTypeEnum.ALIPAY, "app", map);
                    } catch(OperationException e) {
                        payLogger.error(e.getMessage());
                        return; // 跳出方法
                    }
                }
//                String orderNo, String tradeNumber, String payerAccount, String sellerAccount,
//                int payMoney, PayTypeEnum alipay, String deviceType, Map<String,String> map
                
                break;
            }
        }
    }
}
