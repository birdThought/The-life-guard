package com.lifeshs.customer.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.entity.huanxin.TUnregistHx;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.pojo.huanxin.HuanxinUserVO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service.member.FeedbackreportService;
import com.lifeshs.service.member.IMemberC3Service;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.member.TUnregistHxService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.tool.impl.ValidCodeServiceImpl;
import com.lifeshs.service1.agent.AgentService;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.service1.measure.MeasureAnalysisService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.record.UserRecordService;
import com.lifeshs.service1.store.StoreService;
import com.lifeshs.thirdservice.HuanXinService;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.vo.customer.CustomerSharingDataVO;
import com.lifeshs.vo.member.FeedbackreportVo;
import com.lifeshs.vo.member.MemberC3Vo;
import com.lifeshs.vo.member.UserVo;
import com.lifeshs.vo.member.memberCountVo;

/**
 * @author Administrator
 * @create 2018-01-25
 * 10:30
 * @desc
 */
@Controller
@RequestMapping("/member/data")
public class MemberDataController extends BaseController {
    private static final Logger logger = Logger.getLogger(MemberDataController.class);
    private final static int PAGE_SIZE = 15;

    @Autowired
    private UserRecordService userRecordService;
    
    @Autowired
    AgentService agentService;

    @Autowired
    private MeasureAnalysisService measureAnalysisService;
    @Autowired
    private IMemberC3Service iMemberC3Service;
    @Autowired
    private TUnregistHxService tUnregistHxService;

    @Autowired
    private HuanXinService huanXinService;

    @Autowired
    private FeedbackreportService feedbackreportService;
    @Autowired
    private CustomerUserService customerUserService;
    
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IOrgUserService orgUserService;
    @Autowired
    private ValidCodeServiceImpl validCodeService;
    
    @Autowired
    private SMSService sendSMS;
    @Autowired
    private StoreService storeService;

    /**
     *  用户查看数据
     * @param page
     * @param userName
     * @param realName
     * @param orgName
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/list/{page}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getMemberDataList(@PathVariable(value = "page") int page, @RequestParam(value = "userName",required = false) String userName,
                                                    @RequestParam(value = "realName",required = false) String realName,@RequestParam(value = "orgName",required = false)String orgName,
                                                    @RequestParam(value = "mobile",required = false) String mobile){

        if ("".equals(userName)){
            userName =null;
        }
        if ("".equals(realName)){
            realName =null;
        }
        if ("".equals(orgName)){
            orgName =null;
        }
        if ("".equals(mobile)){
            mobile =null;
        }

        AjaxJson ajaxJson = new AjaxJson();
        CustomerSharingDataVO user = getUserSharingData();
        Paging<UserVo> p = userRecordService.listpackage(user.getUserNo(),user.getAgentId(), userName,realName,orgName,mobile,page, PAGE_SIZE);
        PaginationDTO<UserVo> pagination = p.getPagination();
        ajaxJson.setObj(pagination);
        return ajaxJson;
    }

    /**
     * 获取用户健康数据
     * @param userId
     * @param date
     * @return
     */
    @RequestMapping(value = "DeviceData",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getDeviceData(@RequestParam(value = "userId")int userId,@RequestParam(value = "nowDate")String date)  {
        AjaxJson ajaxJson = new AjaxJson();
        Map<String, Object> dateData = measureAnalysisService.getSpecifiedDateData(userId, date);
        ajaxJson.setObj(dateData);
        return ajaxJson;
    }

    /**
     *  用户统计
     * @param page
     * @param orgName
     * @param province
     * @param avgAge
     * @param radioValue
     * @return
     */
    @RequestMapping(value = "/count/{page}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getCountData(@PathVariable(value = "page")int page,@RequestParam(value = "orgName",required = false)String orgName,@RequestParam(value = "province",required = false)String province,
                                               @RequestParam(value = "avgAge",required = false)Integer avgAge,@RequestParam(value = "radioValue",required = false) int radioValue){

        AjaxJson ajaxJson = new AjaxJson();
        if ("".equals(orgName)){
            orgName = null;
        }
        if ("".equals(province)){
            province = null;
        }
        if (-1 == avgAge){
            avgAge = null;
        }
        CustomerSharingDataVO user = getUserSharingData();
        Paging<memberCountVo> countData = userRecordService.getCountData(user.getUserNo(), orgName, province,avgAge, radioValue, page, PAGE_SIZE);
        ajaxJson.setObj(countData.getPagination());
        return ajaxJson;
    }

    /**
     * 获取用户总数量
     * @return
     */
    @RequestMapping(value = "/count/gender")
    public @ResponseBody AjaxJson getCountGenderSum(){
        AjaxJson ajaxJson = new AjaxJson();
        CustomerSharingDataVO user = getUserSharingData();
        memberCountVo byGender = userRecordService.findByGender(user.getUserNo());
        ajaxJson.setObj(byGender);
        return ajaxJson;
    }

    /**
     *  C3 设备
     * @param page
     * @param condition
     * @param userName
     * @param imei
     * @param createDate
     * @param status
     * @return
     */
    @RequestMapping(value = "/c3/{page}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getC3UserDataList(@PathVariable(value = "page")int page,@RequestParam(value = "condition",required = true)Integer condition,@RequestParam(value = "userName",required = false)String userName,
                                                    @RequestParam(value = "imei",required = false)String imei,@RequestParam(value = "createDate",required = false)String createDate,@RequestParam(value = "status",required = true)Integer status){

        AjaxJson ajaxJson = new AjaxJson();
        if ("".equals(userName)){
            userName =null;
        }
        if ("".equals(imei)){
            imei =null;
        }
        if ("".equals(createDate)){
            createDate =null;
        }
        if (status == -1){
            status = null;
        }
        if (condition == -1){
            condition = null;
        }

        Paging<MemberC3Vo> p  = iMemberC3Service.findByC3Data(condition,userName,imei,createDate,status,page,PAGE_SIZE);
        ajaxJson.setObj(p.getPagination());
        return ajaxJson;
    }

    @RequestMapping(value = "/hx/{page}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getHxDataList(@PathVariable(value = "page")int page){
        AjaxJson ajaxJson = new AjaxJson();
        Paging<TUnregistHx> p =tUnregistHxService.findByListDate(page,PAGE_SIZE);
        ajaxJson.setObj(p.getPagination());
        return ajaxJson;
    }

    /**
     * 注册单个用户
     * @param code
     * @return
     */
    @RequestMapping(value = "/hx/code/{code}")
    public @ResponseBody AjaxJson registHxUser(@PathVariable(value = "code")String code){
    AjaxJson ajaxJson = new AjaxJson();
    huanXinService.registryUser(code);
    return ajaxJson;
    }

    /**
     * 一键注册
     * @return
     */
    @RequestMapping(value = "/hx/ids",method = RequestMethod.POST)
    public @ResponseBody AjaxJson registAll(){
        AjaxJson ajaxJson = new AjaxJson();
        List<HuanxinUserVO> byAll = tUnregistHxService.findByAll();
        huanXinService.registryUsers(byAll);
        return ajaxJson;
    }

    /**
     * 获取反馈信息
     * @param page
     * @return
     */
    @RequestMapping(value = "/report/{page}")
    public @ResponseBody AjaxJson getReport(@PathVariable(value = "page")int page){
        AjaxJson ajaxJson = new AjaxJson();
        CustomerSharingDataVO user = getUserSharingData();
        Paging<FeedbackreportVo> p = feedbackreportService.findByReportAll(user.getUserNo(),page,PAGE_SIZE);
        ajaxJson.setObj(p.getPagination());
        return ajaxJson;
    }
    @RequestMapping(value = "/report/del/{id}")
    public @ResponseBody AjaxJson deleteReport(@PathVariable(value = "id") Integer id){
        AjaxJson ajaxJson = new AjaxJson();
        if (id == null){
            ajaxJson.setMsg("未知错误！");
            return ajaxJson;
        }
        feedbackreportService.delId(id);
        return ajaxJson;
    }

    @RequestMapping(value = "/report/findId",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getUserTypeData(@RequestParam(value = "userType") Integer userType,@RequestParam(value = "userId") Integer userId){
        AjaxJson ajaxJson = new AjaxJson();
        // TODO 会员用户
        if (userType == 0){
            UserPO up  = feedbackreportService.findByUserData(userId);
            ajaxJson.setObj(up);
        }else{ // TODO 机构用户
            OrgUserPO oup = feedbackreportService.findByUSerOrgData(userId);
            ajaxJson.setObj(oup);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/report/update",method = RequestMethod.POST)
    public @ResponseBody AjaxJson setReport(@RequestParam(value = "id") Integer id,@RequestParam(value = "reply")String report){
        AjaxJson ajaxJson = new AjaxJson();
        feedbackreportService.updateReportData(id,report);
        return ajaxJson;
    }
    
    
    /**
     *  查看业务员数据
     * @param page
     * @param userName
     * @param realName
     * @param orgName
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/offline/list/{page}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getMemberOfflineList(@PathVariable(value = "page") int page, 
                                                    @RequestParam(value = "realName",required = false) String realName,
                                                    @RequestParam(value = "mobile",required = false)String mobile){

        if ("".equals(realName)){
            realName =null;
        }
        if ("".equals(mobile)){
            mobile =null;
        }

        AjaxJson ajaxJson = new AjaxJson();
        CustomerSharingDataVO user = getUserSharingData();
        Paging paging = customerUserService.findMemberOfflineList(user.getUserNo(), realName, mobile, page, PAGE_SIZE);
        ajaxJson.setObj(paging.getPagination());
        
        return ajaxJson;
    }
    
    /**
     * 添加业务员
     * 
     * @param name
     * @return
     */
    @RequestMapping(value = "/offline/addSalesman", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson addSumit(CustomerUserPO customerUserPo, @RequestParam("verifyCode") String verifyCode) {
        AjaxJson ajaxJson = new AjaxJson();
//        String referrer = "";
        //检查登录账户名是否存在
        boolean b = customerUserService.checkUserName(customerUserPo.getUserName());
        if(b) {
            ajaxJson.setMsg("登录账号已存在,请重新输入!");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        
        boolean isUserNameValid = Toolkits.isVerifyUserName(customerUserPo.getUserName());
        if (!isUserNameValid) {
            ajaxJson.setMsg("请输入6~16位的登录名称");
            return ajaxJson;
        }
        
        boolean isPasswordValid = Toolkits.isVerifyPassword(customerUserPo.getPassword());
        if (!isPasswordValid) {
            ajaxJson.setMsg("请输入6~16位包含大小写英文和数字的密码。");
            return ajaxJson;
        }
        
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
        
        //校验验证码是否正确或者过期
        if (!validCodeService.valid(customerUserPo.getMoblie(), verifyCode, CacheType.USER_REGISTERY_CACHE)) {
            ajaxJson.setMsg("验证码不正确或已失效");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        
        CustomerSharingDataVO user = getUserSharingData();
        List<CustomerUserPO> list = customerUserService.getUserByParam(null, user.getUserNo(), null);
        if(list.get(0).getAgentNum() == null || list.get(0).getAgentNum() ==0 ){
          ajaxJson.setMsg("对不起，业务员应由代理商添加!");
          ajaxJson.setSuccess(true);
          return ajaxJson;
        }
        
       //设置引荐人
       customerUserPo.setParentId(user.getUserNo());
       //设置状态
       customerUserPo.setStatus(AgentConstant.AGENT_USER_STATUS_0);
       customerUserPo.setProvinceCode(customerUserPo.getProvinceCode().substring(0, 2));
       customerUserPo.setCityCode(customerUserPo.getCityCode().substring(2, 4));
       customerUserPo.setAreaCode(customerUserPo.getAreaCode().substring(4, 6));
       customerUserPo.setAddress(customerUserPo.getAddress());
       customerUserPo.setPassword(customerUserPo.getPassword());
       customerUserPo.setType(AgentConstant.AGENT_USER_ATTRIBUTE_2); //用户类型 (1.管理员 2.普通用户 3.财务 4.其它)
       customerUserPo.setAgentId(AgentConstant.AGENT_USER_TYPE_2);
       customerUserPo.setAgentNum(list.get(0).getAgentNum());
       //账户类型
       boolean bol = agentService.addAgentAndCustomerUser(2, customerUserPo, null);
       if(bol){
           ajaxJson.setMsg("保存成功!");
           ajaxJson.setSuccess(true);
       }else{
           ajaxJson.setMsg("保存失败!");
           ajaxJson.setSuccess(false);
       }
        return ajaxJson;
    }
    
//    /**
//     * 根据省份获取城市
//     * @param provinceCode
//     * @return
//     */
//    @RequestMapping(params = "/offline/getCity", method = RequestMethod.GET)
//    public @ResponseBody AjaxJson getCitiesByCode(@RequestParam String provinceCode) {
//        if (provinceCode == null)
//            return null;
//        String regex = "^" + provinceCode + "[0-9][0-9][0]{2}";
//        List<Map<String, String>> cities = dataAreaService.findCity(regex);
//        AjaxJson json = new AjaxJson();
//        Map<String, Object> city = new HashMap<String, Object>();
//        if (cities.size() > 1) {
//            cities.remove(0);
//        }
//        city.put("city", cities);
//        List<Map<String, String>> dis = dataAreaService.findDistrict("^" + provinceCode + "01[0-9][1-9]");
//        json.setAttributes(city);
//        json.setObj(dis);
//        return json;
//    }
//    
//    /**
//     * @author liaoguo
//     * @DateTime 2018年11月16日
//     * @serverComment 根据省份城市code正则表达动态获取对应的区域
//     */
//    @RequestMapping(params = "/offline/getArea", method = RequestMethod.GET)
//    public @ResponseBody AjaxJson getArea(@RequestParam String cityCode) {
//        AjaxJson json = new AjaxJson();
//        if (cityCode == null){
//            return null;
//        }
//        String regex = "^" + cityCode + "[0-9][1-9]";
//        List<Map<String, String>> districts = dataAreaService.findDistrict(regex);
//        json.setObj(districts);
//        return json;
//    }
    
    /**
     * 
     *  发送验证码
     *  @author NaN
     *  @DateTime 2018年10月24日 下午1:47:06
     *  @param request
     *  @param response
     *  @return
     */
    @RequestMapping(value = "/offline/sendValidCode", method = RequestMethod.POST)
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
}
