package com.lifeshs.customer.controller.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.tool.impl.ValidCodeServiceImpl;
import com.lifeshs.service1.agent.AgentService;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.Toolkits;

/**
 * 
 *  业务员注册
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年10月24日 下午1:48:06
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController{

    private static final Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    private IMemberService memberService;
    @Autowired
    IDataAreaService areaService;
    @Autowired
    private IOrgUserService orgUserService;
    @Autowired
    private ValidCodeServiceImpl validCodeService;
    @Resource(name = "customerUserService")
    private CustomerUserService customerUserService;
    @Autowired
    AgentService agentService;
    
    
    /**
     * 根据省份获取城市
     * @param provinceCode
     * @return
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
     * @author liaoguo
     * @DateTime 2018年11月16日
     * @serverComment 根据省份城市code正则表达动态获取对应的区域
     */
    @RequestMapping(params = "getArea", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getArea(@RequestParam String cityCode) {
        AjaxJson json = new AjaxJson();
        if (cityCode == null){
            return null;
        }
        String regex = "^" + cityCode + "[0-9][1-9]";
        List<Map<String, String>> districts = dataAreaService.findDistrict(regex);
        json.setObj(districts);
        return json;
    }
    
    /**
     * 
     *  发送验证码
     *  @author NaN
     *  @DateTime 2018年10月24日 下午1:47:06
     *  @param request
     *  @param response
     *  @return
     */
    @RequestMapping(value = "/sendValidCode", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson sendValidCode(@RequestParam("mobile") String mobile,@RequestParam("cache") String cache,
            @RequestParam("userType") String userType) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "发送验证码失败";
        // 获取参数
        CacheType cacheType = null;
        String cacheKey = "", userId = "";

        switch (cache) {
        case "register":
            if (!"org".equals(userType)) {
                if (StringUtils.isNotEmpty(memberService.checkMobile(mobile))) {
                    resObject.setMsg("该手机号已被注册");
                    return resObject;
                }
            }
            userId = "100000";  //系统发送
            cacheType = CacheType.USER_REGISTERY_CACHE;    // 注册 验证码
            cacheKey = mobile;
            break;
        case "reset":
            cacheType = CacheType.USER_RESET_CACHE;    // 重置密码 验证码
            if (StringUtils.isBlank(mobile)) {
                resObject.setMsg(msg + ":手机号码为空");
                return resObject;
            }
            // 查找手机号是否登记在系统中
            try {

                // 普通用户
                userId = memberService.checkMobile(mobile);

                // 企业用户
                if (StringUtils.isEmpty(userId)) {
                    userId = orgUserService.checkMobile(mobile);
                }

                if (StringUtils.isEmpty(userId)) {
                    resObject.setMsg(msg + "手机号不存在或未验证");
                    return resObject;
                }
                cacheKey = userId;
                break;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                resObject.setMsg(msg + ":" + e.getMessage());
                return resObject;
            }
        case "mobile":
            // TODO 改为验证该手机号是否被验证，而不是被注册
            if (StringUtils.isNotEmpty(memberService.checkMobile(mobile))) {
                resObject.setMsg("该手机号已被注册");
                return resObject;
            }
            cacheType = CacheType.USER_MOBILE_MODIFY;    // 修改手机邮箱 验证码
            cacheKey = mobile;
            userId = String.valueOf(getLoginUser().getId());
            break;
    }
    if (StringUtils.isBlank(cacheKey) || cacheType == null) {
        resObject.setMsg(msg + ":非法操作");
        return resObject;
    }
    try {
        // 发送验证码
        if (StringUtils.isEmpty(validCodeService.sendToMobile(Integer.parseInt(userId), mobile, cacheKey, cacheType, VcodeTerminalType.CUSTOMER, false))) {
            // 发送失败
            resObject.setMsg(msg);
            return resObject;
        }
        // 发送成功
        resObject.setObj(cacheKey);    // 保存userId到页面上
        resObject.setMsg("手机验证码已发送");
        resObject.setSuccess(true);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        resObject.setMsg(msg + ":" + e.getMessage());
    }

    return resObject;
    }
    
    
//    /**
//     * 添加代理商或业务员
//     * 
//     * @param name
//     * @return
//     */
//    @RequestMapping(value = "/addSalesman", method = RequestMethod.POST)
//    @ResponseBody
//    public AjaxJson addSumit(CustomerUserPO customerUserPo, @RequestParam("verifyCode") String verifyCode) {
//        AjaxJson ajaxJson = new AjaxJson();
//        String referrer = "";
//        //检查登录账户名是否存在
//        boolean b = customerUserService.checkUserName(customerUserPo.getUserName());
//        if(b) {
//            ajaxJson.setMsg("登录账号已存在,请重新输入!");
//            ajaxJson.setSuccess(false);
//            return ajaxJson;
//        }
//        
//        boolean isUserNameValid = Toolkits.isVerifyUserName(customerUserPo.getUserName());
//        if (!isUserNameValid) {
//            ajaxJson.setMsg("请输入6~16位的登录名称");
//            return ajaxJson;
//        }
//        
//        boolean isPasswordValid = Toolkits.isVerifyPassword(customerUserPo.getPassword());
//        if (!isPasswordValid) {
//            ajaxJson.setMsg("请输入6~16位包含大小写英文和数字的密码。");
//            return ajaxJson;
//        }
//        
//        //存在引荐人
//        if(StringUtils.isNotBlank(customerUserPo.getParentId())){
//            List<CustomerUserPO> list = customerUserService.getUserByParam(null, customerUserPo.getParentId(), null);
//            if(list.size() < 1){
//                ajaxJson.setMsg("引荐人编号有误,请重新输入!");
//                ajaxJson.setSuccess(false);
//                return ajaxJson;
//            }
//            
//            //设置业务员所属代理商
//            customerUserPo.setAgentId(list.get(0).getAgentId());
//            //设置引荐人id
//            referrer = customerUserPo.getParentId();
//        }else{
//            //设置默认引荐人,默认为kf1002
//            referrer = AgentConstant.AGENT_DEFUALT_PARENT_ID_A2;
//        }
//        
////        //校验验证码是否正确或者过期
////        if (!validCodeService.valid(customerUserPo.getMoblie(), verifyCode, CacheType.USER_REGISTERY_CACHE)) {
////            ajaxJson.setMsg("验证码不正确或已失效");
////            ajaxJson.setSuccess(false);
////            return ajaxJson;
////        }
//        
//        //设置引荐人
//       customerUserPo.setParentId(referrer);
//       //设置状态
//       customerUserPo.setStatus(AgentConstant.AGENT_USER_STATUS_0);
//       customerUserPo.setProvinceCode(customerUserPo.getProvinceCode().substring(0, 2));
//       customerUserPo.setCityCode(customerUserPo.getCityCode().substring(2, 4));
//       customerUserPo.setAreaCode(customerUserPo.getAreaCode().substring(4, 6));
//       customerUserPo.setAddress(customerUserPo.getAddress());
//       customerUserPo.setPassword(MD5Utils.encryptPassword(customerUserPo.getPassword()));
//       customerUserPo.setType(AgentConstant.AGENT_USER_ATTRIBUTE_2); //用户类型 (1.管理员 2.普通用户 3.财务 4.其它)
//       customerUserPo.setAgentId(AgentConstant.AGENT_USER_TYPE_2);
//       //账户类型
//       boolean bol = agentService.addAgentAndCustomerUser(2, customerUserPo, null);
//       if(bol){
//           ajaxJson.setMsg("保存成功!");
//           ajaxJson.setSuccess(true);
//       }else{
//           ajaxJson.setMsg("保存失败!");
//           ajaxJson.setSuccess(false);
//       }
//        return ajaxJson;
//    }
}
