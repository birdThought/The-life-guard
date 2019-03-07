package com.lifeshs.controller.account;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.po.admin.AdminUserDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.org.OrgRegisterDTO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.org.employee.EmployeeRegisterDTO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service.org.employee.IEmployeeManageService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.tool.impl.ValidCodeServiceImpl;
import com.lifeshs.utils.ParserParaUtil;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.utils.UUID;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 * @Author Yue.Li
 * @Date 2017/4/20.
 *  注册功能
 *    1、个人
 *    2、机构
 *    3、服务师
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
    private IManagerOrgService managerOrgService;

    @Autowired
    private ValidCodeServiceImpl validCodeService;

    @Autowired
    private IEmployeeManageService employeeManageService;

    /**
     * @Description: 注册-选择注册类型
     * @author: wenxian.cai
     * @create: 2017/4/24 16:20
     */
    @RequestMapping(value = "")
    public ModelAndView register() {
        return new ModelAndView("platform/register/register");
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年5月19日 下午2:10:42
     * @serverComment 跳转注册用户页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/member")
    public String register(HttpServletRequest request) {
        return "platform/register/member/register-1";
    }

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月13日 上午10:12:54
     * @serverComment 用户注册
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value ={"/register"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerMember(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "注册用户失败";

        // 获取request中传递的参数
        Map<String, Object> param = ParserParaUtil.getParams(request);
        String userName = (String) param.get("userName");
        String password = (String) param.get("password");
        userName = StringUtil.decodeStr(userName);
        password = StringUtil.decodeStr(password);
        // 检查参数是否为空
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            resObject.setMsg(msg + "：参数为空");
            return resObject;
        }

        boolean isUserNameValid = Toolkits.isVerifyUserName(userName);
        if (!isUserNameValid) {
            resObject.setMsg("请输入6~16位的登录名称");
            return resObject;
        }
        boolean isPasswordValid = Toolkits.isVerifyPassword(password);
        if (!isPasswordValid) {
            resObject.setMsg("密码不合法");
            return resObject;
        }

        // 检查用户是否已经注册
        // 获取添加一个新的普通用户的返回结果
        int userId = memberService.registMember(userName, password);
        if (userId == -1) {
            resObject.setMsg(msg + "：用户名已被注册");
            return resObject;
        }
        resObject.setSuccess(true);
        return resObject;
    }

    /**
     * 手机注册第一步
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/member/registerbymobile"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerMemberByMobile(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "注册用户失败";

        // 获取request中传递的参数
        Map<String, Object> param = ParserParaUtil.getParams(request);
        String userName = (String) param.get("userName");
        String password = (String) param.get("password");
        String mobile = (String) param.get("mobile");
        String code = (String) param.get("code");
        userName = StringUtil.decodeStr(userName);
        password = StringUtil.decodeStr(password);
        mobile = StringUtil.decodeStr(mobile);
        code = StringUtil.decodeStr(code);
        // 检查参数是否为空
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)
                || StringUtils.isBlank(mobile) || StringUtils.isBlank(code)) {
            resObject.setMsg(msg + "：参数为空");
            return resObject;
        }

        boolean isUserNameValid = Toolkits.isVerifyUserName(userName);
        if (!isUserNameValid) {
            resObject.setMsg("用户名不合法");
            return resObject;
        }
        boolean isPasswordValid = Toolkits.isVerifyPassword(password);
        if (!isPasswordValid) {
            resObject.setMsg("密码不合法");
            return resObject;
        }
        boolean isMobileValid = Toolkits.verifyPhone(mobile);
        if (!isMobileValid) {
            resObject.setMsg("电话号码不合法");
            return resObject;
        }
        if (StringUtils.isNotEmpty(memberService.checkMobile(mobile))) {
            resObject.setMsg("该手机号已被注册");
            return resObject;
        }
        String cacheCode = String.valueOf(cacheService.getCacheValue(CacheType.USER_REGISTERY_CACHE, mobile));
        if (!cacheCode.equals(code)) {
            resObject.setMsg("输入验证码不正确");
            return resObject;
        }
        // 检查用户是否已经注册
        // 获取添加一个新的普通用户的返回结果
//        int userId = memberService.registMember(userName, password);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userName);
        userDTO.setPassword(password);
        userDTO.setMobile(mobile);
        userDTO.setMobileVerified(true);
        int userId  = memberService.registMember(userDTO);
        if (userId == -1) {
            resObject.setMsg(msg + "：用户名已被注册");
            return resObject;
        }
        Map<String, Object> map = new HashedMap();
        map.put("userId", userId);
        resObject.setAttributes(map);
        resObject.setSuccess(true);
        return resObject;
    }

    /**
     * 手机注册第二步
     * @param id
     * @return
     */
    @RequestMapping(value = {"/member/regbymobiletwo"})
    public ModelAndView registerMemberByMobileTwo(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("platform/register/member/register-2");
        modelAndView.addObject("userId", id);
        return modelAndView;
    }

    /**
     * 手机注册第三步
     * @return
     */
    @RequestMapping(value = "/member/regbymobilethree")
    public ModelAndView registerMemberByMobileThree() {
        ModelAndView modelAndView = new ModelAndView("platform/register/member/register-3");
        return modelAndView;
    }

    /**
     * @Description: 机构注册第一步页面
     * @author: wenxian.cai
     * @create: 2017/4/24 16:21
     */
    @RequestMapping(value = "/store")
    public ModelAndView registerOrg() {
        ModelAndView modelAndView = new ModelAndView("platform/register/org/register-org-1");
        List<Map<String, String>> province = areaService.findAllProvince();
        List<Map<String, String>> city = areaService.findCity("^11[0-9]{2}[0]{2}");// 默认为北京
        List<Map<String, String>> district = areaService.findDistrict("^11[0-9]{2}[0-9][1-9]");
        modelAndView.addObject("province", JSON.toJSON(province));
        modelAndView.addObject("city", JSON.toJSON(city));
        modelAndView.addObject("district", JSON.toJSON(district));
        return modelAndView;
    }

    /**
     * @Description: 机构注册第二步页面
     * @author: wenxian.cai
     * @create: 2017/4/26 14:11
     */
    @RequestMapping(value = "/org/steptwo")
    public ModelAndView registerOrgTwo(@RequestParam(value = "id") String id, @RequestParam(value = "token") String token, String orgName) {
        ModelAndView modelAndView = new ModelAndView("platform/register/org/register-org-2");
        String oldToken = (String) cacheService.getCacheValue(CacheType.REGISTER_TOKEN_CACHE, id);
        //验证注册token
        if (!token.equals(oldToken)) {
            return new ModelAndView("view/common/404");
        }
        modelAndView.addObject("token", token);
        modelAndView.addObject("id", id);
        modelAndView.addObject("orgName", orgName);
        return modelAndView;
    }

    /**
     * @Description: 机构注册第三步页面
     * @author: wenxian.cai
     * @create: 2017/4/26 15:44
     */
    @RequestMapping(value = "/org/stepthree")
    public ModelAndView registerOrgThree(@RequestParam(value = "id") String id, @RequestParam(value = "token") String token) {
        ModelAndView modelAndView = new ModelAndView("platform/register/org/register-org-3");
        String oldToken = (String) cacheService.getCacheValue(CacheType.REGISTER_TOKEN_CACHE, id);
        //验证注册token
        if (!token.equals(oldToken)) {
            return new ModelAndView("view/common/404");
        }
        modelAndView.addObject("token", token);
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    /**
     * @Description: 机构注册第四步页面
     * @author: wenxian.cai
     * @create: 2017/4/26 18:04
     */
    @RequestMapping(value = "/org/stepfour")
    public ModelAndView registerOrgFour(@RequestParam(value = "id") String id, @RequestParam(value = "token") String token) {
        ModelAndView modelAndView = new ModelAndView("platform/register/org/register-org-4");
        String oldToken = (String) cacheService.getCacheValue(CacheType.REGISTER_TOKEN_CACHE, id);
        //验证注册token
        if (!token.equals(oldToken)) {
            return new ModelAndView("view/common/404");
        }
        modelAndView.addObject("token", token);
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    /**
     * @Description: 机构注册第五步页面
     * @Author: wenxian.cai
     * @Date: 2017/6/30 11:59
     */
    @RequestMapping(value = "/org/stepfive")
    public ModelAndView registerOrgFive() {
        ModelAndView modelAndView = new ModelAndView("platform/register/org/register-org-5");
        /*String oldToken = (String) cacheService.getCacheValue(CacheType.REGISTER_TOKEN_CACHE, id);
        //验证注册token
        if (!token.equals(oldToken)) {
            return new ModelAndView("view/common/404");
        }*/
        return modelAndView;
    }

    /**
     * @Description: 机构注册(提交第一步数据)
     * @author: wenxian.cai
     * @create: 2017/4/26 9:26
     */
    @RequestMapping(value = "/registerOrg", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerOrg(@RequestParam String orgName, @RequestParam String businessLicenseNumber,
                                @RequestParam(required = false) String businessPic1, @RequestParam(required = false) String businessPic2,
                                @RequestParam String orgCharacter, @RequestParam String province,
                                @RequestParam String city, @RequestParam String district,
                                @RequestParam String address, @RequestParam String workField,
                                @RequestParam String mobile, @RequestParam(required = false) String longitude,
                                @RequestParam(required = false) String latitude, @RequestParam() String photo) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtils.isBlank(orgName) || StringUtils.isBlank(businessLicenseNumber) || StringUtils.isBlank(orgCharacter)
                || StringUtils.isBlank(province) || StringUtils.isBlank(city) || StringUtils.isBlank(district)
                || StringUtils.isBlank(address) || StringUtils.isBlank(workField) || StringUtils.isBlank(mobile)) {
            ajaxJson.setMsg("参数为空");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        String newBusinessPic1 = null;
        String newBusinessPic2 = null;
        if (!businessPic1.equals("")) {
            try {
                if (!"".equals(businessPic1)) {
                    newBusinessPic1 = ImageUtilV2.copyImgFileToUploadFolder(businessPic1, "org");
                }
                /*if (!businessPic2.equals("")) {
                    newBusinessPic2 = ImageUtilV2.copyImgFileToUploadFolder(businessPic2, "org");
                }*/
                if (!photo.equals("")) {
                    photo = ImageUtilV2.copyImgFileToUploadFolder(photo, "org");
                }
                } catch (IOException e) {
                logger.error("图片转移地址失败");
                System.out.println("图片转移地址失败");
            }
        }

        OrgRegisterDTO org = new OrgRegisterDTO();
        org.setOrgName(orgName);
        org.setBusinessLicenseNumber(businessLicenseNumber);
        org.setBusinessPic1(newBusinessPic1);
        org.setBusinessPic2(newBusinessPic2);
        org.setType(1);
        org.setOrgType(orgCharacter);
        org.setProvince(province.substring(0, 2));
        org.setCity(city.substring(2, 4));
        org.setDistrict(district.substring(4, 6));
        org.setAddress(address);
        org.setWorkField(workField);
        org.setMobile(mobile);
        org.setLongitude(longitude);
        org.setLatitude(latitude);
        org.setLogo(photo);
        org.setParentId(null);
        int orgId = managerOrgService.registOrg(org);
        if (orgId < 0) {
            ajaxJson.setMsg("注册失败");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        Map<String, Object> map = new HashMap<>();
        String token = UUID.generate(); //生成注册随机token
        System.out.println("token:" + token);
        cacheService.saveKeyValue(CacheType.REGISTER_TOKEN_CACHE, String.valueOf(orgId), token);
        map.put("id", orgId);
        map.put("token", token);
        map.put("orgName", orgName);
        ajaxJson.setAttributes(map);
        ajaxJson.setMsg("注册成功");
        return ajaxJson;
    }

    /**
     * @Description: 完善注册机构信息(提交第二步、第三步信息)
     * @author: wenxian.cai
     * @create: 2017/4/26 15:43
     */
    @RequestMapping(value = "/org/improve", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerOrg(@RequestParam(required = false) String bankName, @RequestParam(required = false) String bankAccount,
                                @RequestParam(required = false) String bankDistrict, @RequestParam(required = false) String bankBranch,
                                @RequestParam(required = false) String legalPerson, @RequestParam(required = false) boolean legalPersonGender,
                                @RequestParam(required = false) String legalPersonIdCard, @RequestParam(required = false) String legalPersonPic1,
                                @RequestParam(required = false) String legalPersonPic2, @RequestParam(value = "id") int id, @RequestParam(value = "token") String token) {
        AjaxJson ajaxJson = new AjaxJson();

        String newPersonPicOne = null;
        String newPersonPicTwo = null;
        if (legalPersonPic1 != null && !legalPersonPic1.equals("")) {
            try {
                newPersonPicOne = ImageUtilV2.copyImgFileToUploadFolder(legalPersonPic1, "org");
            } catch (IOException e) {
                logger.error("图片转移地址失败");
                System.out.println("图片转移地址失败");
            }
        }
        if (legalPersonPic2 != null && !legalPersonPic2.equals("")) {
            try {
                newPersonPicTwo = ImageUtilV2.copyImgFileToUploadFolder(legalPersonPic2, "org");
            } catch (IOException e) {
                logger.error("图片转移地址失败");
                System.out.println("图片转移地址失败");
            }
        }
        
        OrgRegisterDTO org = new OrgRegisterDTO();
        org.setId(id);
        org.setBankAccount(bankAccount);
        org.setBankDistrict(bankDistrict);
        org.setBankBranch(bankBranch);
//        org.setContacts(legalPerson);
        org.setLegalPerson(legalPerson);
        org.setLegalPersonGender(legalPersonGender);
        org.setLegalPersonIdCard(legalPersonIdCard);
        org.setLegalPersonPicOne(newPersonPicOne);
        org.setLegalPersonPicTwo(newPersonPicTwo);
        boolean bool = managerOrgService.updateOrg(org);
        if (!bool) {
            ajaxJson.setMsg("提交失败");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        Map map = new HashMap();
        map.put("id", id);
        map.put("token", token);
        ajaxJson.setAttributes(map);
        ajaxJson.setMsg("提交成功");
        return ajaxJson;
    }
    /**
     * @Description: 机构用户注册(提交机构注册第四步数据)
     * @author: wenxian.cai
     * @create: 2017/4/27 9:35
     */
    @RequestMapping(value = "/org/registerOrgUser", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerOrgUser(@RequestParam String username, @RequestParam String password,
                                @RequestParam String mobile, @RequestParam String code, @RequestParam Integer orgId,
                                @RequestParam String parentId) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(mobile)
                || StringUtils.isBlank(code) ) {
            ajaxJson.setMsg("参数为空");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        boolean isUserNameValid = Toolkits.isVerifyUserName(username);
        if (!isUserNameValid) {
            ajaxJson.setMsg("用户名不合法");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        boolean isPasswordValid = Toolkits.isVerifyPassword(password);
        if (!isPasswordValid) {
            ajaxJson.setMsg("密码不合法");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        boolean isMobileValid = Toolkits.verifyPhone(mobile);
        if (!isMobileValid) {
            ajaxJson.setMsg("电话号码不合法");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        // 检查用户名(mobile)是否被占用
        if (orgUserService.userIsExist(username)) {
            ajaxJson.setMsg("用户名已被注册");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        //校验验证码是否正确或者过期
        if (!validCodeService.valid(mobile, code, CacheType.USER_REGISTERY_CACHE)) {
            ajaxJson.setMsg("验证码不正确或已失效");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        
        // 判断是否有引荐人
        if(StringUtils.isNotBlank(parentId)) {
            parentId = parentId.toUpperCase();
            // 查找引荐人ID否登记在系统中
            //获取字符串的第一个字符
            Object fir = parentId.subSequence(0, 1);//获取字符串的第一个字符
            if(fir.equals("A") || fir.equals("Y") || fir.equals("D")) {
                AdminUserDTO AdminUserDTO= memberService.getAdminUserUserNo(parentId);
                 if(AdminUserDTO ==null) {
                     ajaxJson.setMsg("推荐人ID不存在!");
                     ajaxJson.setSuccess(false);
                     return ajaxJson;
                 }
            }else {
                ajaxJson.setMsg("推荐人ID不存在!");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
        }else {
            //设置默认推荐人ID
            parentId = AgentConstant.AGENT_DEFUALT_PARENT_ID_A2;
        }
        
        int type = 1;
        int userId = managerOrgService.registOrgUser(username, password, orgId, type, mobile);
        if (userId == 0) {
            ajaxJson.setMsg("提交失败");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        
        OrgRegisterDTO org = new OrgRegisterDTO();
        org.setId(orgId);
        org.setUserNo(AgentConstant.AGENT_USER_TYPE_O + orgId);
        org.setParentId(parentId);
        try {
            managerOrgService.updateOrg(org);
        } catch (Exception e) {
            logger.info("注册个体门店：修改门店信息失败");
        }
        
        
        ajaxJson.setMsg("注册成功");
        return ajaxJson;
    }

    /**
     * @Description: 专业人士注册第一步页面
     * @author: wenxian.cai
     * @create: 2017/4/27 11:50
     */
    @RequestMapping(value = "/org/services")
    public ModelAndView registerOrgServices() {
        ModelAndView modelAndView = new ModelAndView("platform/register/org/register-services-1");

        return modelAndView;
    }

    /**
     * @Description: 专业人士注册第二、三、四、五步
     * @author: wenxian.cai
     * @create: 2017/4/27 11:50
     */
    @RequestMapping(value = "/org/services/{step}")
    public ModelAndView registerOrgServices(@PathVariable String step, @RequestParam(value = "userId") String userId, @RequestParam(value = "token") String token) {
        String url = null;
        switch (step) {
            case "steptwo":
                url = "platform/register/org/register-services-2";
                break;
            case "stepthree":
                url = "platform/register/org/register-services-3";
                break;
            case "stepfour":
                url = "platform/register/org/register-services-4";
                break;
            case "stepfive":
                url = "platform/register/org/register-services-5";
                break;

        }
        ModelAndView modelAndView = new ModelAndView(url);
        String oldToken = (String) cacheService.getCacheValue(CacheType.REGISTER_TOKEN_CACHE, userId);
        //验证注册token
        if (!token.equals(oldToken)) {
            return new ModelAndView("view/common/404");
        }
        modelAndView.addObject("token", token);
        modelAndView.addObject("id", userId);
        return modelAndView;
    }


    /**
     * @Description: 注册服务师（提交注册专业人士第一步数据）
     * @author: wenxian.cai
     * @create: 2017/4/27 11:20
     */
    @RequestMapping(value = "/org/services/register", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerOrgServices(@RequestParam String username, @RequestParam String password,
                                    @RequestParam String mobile, @RequestParam String code, @RequestParam Integer type,
                                    @RequestParam String parentId) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(mobile)
                || StringUtils.isBlank(code)) {
            ajaxJson.setMsg("参数为空");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        boolean isUserNameValid = Toolkits.isVerifyUserName(username);
        if (!isUserNameValid) {
            ajaxJson.setMsg("用户名不合法");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        boolean isPasswordValid = Toolkits.isVerifyPassword(password);
        if (!isPasswordValid) {
            ajaxJson.setMsg("密码不合法");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        boolean isMobileValid = Toolkits.verifyPhone(mobile);
        if (!isMobileValid) {
            ajaxJson.setMsg("手机号码不合法");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        // 检查用户名(mobile)是否被占用
        if (orgUserService.userIsExist(username)) {
            ajaxJson.setMsg("用户名已被注册");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        //校验验证码是否正确或者过期
        if (!validCodeService.valid(mobile, code, CacheType.USER_REGISTERY_CACHE)) {
            ajaxJson.setMsg("验证码不正确或已失效");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        
        // 判断是否有引荐人
        if(StringUtils.isNotBlank(parentId)) {
            // 查找引荐人ID否登记在系统中
            parentId = parentId.toUpperCase();
            //获取字符串的第一个字符
            Object fir = parentId.subSequence(0, 1);//获取字符串的第一个字符
            if(fir.equals("A") || fir.equals("Y") || fir.equals("D")) {
                AdminUserDTO AdminUserDTO= memberService.getAdminUserUserNo(parentId);
                 if(AdminUserDTO ==null) {
                     ajaxJson.setMsg("推荐人ID不存在!");
                     ajaxJson.setSuccess(false);
                     return ajaxJson;
                 }
            }else if(fir.equals("O")) {
                OrgUserDTO orgUserDTO= memberService.getOrgUserNo(parentId);
                 if(orgUserDTO ==null) {
                     ajaxJson.setMsg("推荐人ID不存在!");
                     ajaxJson.setSuccess(false);
                     return ajaxJson;
                 }
            }else {
                ajaxJson.setMsg("推荐人ID不存在!");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
        }else {
            //设置默认推荐人ID
            parentId = AgentConstant.AGENT_DEFUALT_PARENT_ID_A2;
        }
        
        int userId = managerOrgService.registOrgUser(username, password, null, type, mobile);
        if (userId == 0) {
            ajaxJson.setMsg("注册失败");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        Map map = new HashMap();
        String token = UUID.generate(); //生成注册随机token
        cacheService.saveKeyValue(CacheType.REGISTER_TOKEN_CACHE, String.valueOf(userId), token);
        map.put("id", userId);
        map.put("token", token);
        ajaxJson.setAttributes(map);
        ajaxJson.setMsg("注册成功");
        return ajaxJson;
    }

    /**
     * @Description: 完善专业人士注册信息(提交专业人士注册第二、三、四步数据)
     * @author: wenxian.cai
     * @create: 2017/4/27 14:23
     */
    /*@RequestMapping(value = "/org/services/improve", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerOrgServicesImprove(@RequestParam String realname, @RequestParam String photo,
                                               @RequestParam String idCard, @RequestParam String idPic1,
                                               @RequestParam String idPic2) {
        AjaxJson ajaxJson = new AjaxJson();
        if (StringUtils.isBlank(realname) || StringUtils.isBlank(idCard) || StringUtils.isBlank(idPic1)
                || StringUtils.isBlank(idPic2)) {
            ajaxJson.setMsg("参数为空");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        int userId = managerOrgService.registOrgUser(username, password, null, type);
        if (userId == 0) {
            ajaxJson.setMsg("注册失败");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        Map map = new HashMap();
        String token = UUID.generate(); //生成注册随机token
        System.out.println("token:" + token);
        cacheService.saveKeyValue(CacheType.REGISTER_TOKEN_CACHE, String.valueOf(userId), token);
        map.put("id", userId);
        map.put("token", token);
        ajaxJson.setAttributes(map);
        ajaxJson.setMsg("注册成功");
        return ajaxJson;
    }*/

    /**
     * @Description: 个体门店注册
     * @Author: wenxian.cai
     * @Date: 2017/6/20 11:48
     */
    @RequestMapping(value = "/individual/store")
    public ModelAndView individualStore() {
        ModelAndView modelAndView = new ModelAndView("platform/register/individualstore/register-step-1");
        return modelAndView;
    }

    /**
     * @Description: 提交个体门店注册第一步数据
     * @Author: wenxian.cai
     * @Date: 2017/6/20 15:02
     */
    @RequestMapping(value = "/individual/store/apply", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson applyIndividualStore(@RequestBody OrgUserDTO user){
        AjaxJson ajaxJson = new AjaxJson();
        // 检查参数是否为空
        if (StringUtils.isBlank(user.getPhoto()) || StringUtils.isBlank(user.getRealName())
            || StringUtils.isBlank(user.getIdCard()) || StringUtils.isBlank(user.getIdCardPicOne())
            || StringUtils.isBlank(user.getIdCardPicTwo())) {
            ajaxJson.setMsg("操作失败：参数为空");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        return ajaxJson;
    }

    /**
     * @Description: 提交个体门店注册第二步数据
     * @Author: wenxian.cai
     * @Date: 2017/6/24 15:33
     */
    @RequestMapping(value = "/individual/store/apply/two", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson applyIndividualStoreTwo(@RequestBody OrgUserDTO user){
        AjaxJson ajaxJson = new AjaxJson();
        // 检查参数是否为空
        if (StringUtils.isBlank(user.getAddress()) || StringUtils.isBlank(user.getProfessionalName())
                || StringUtils.isBlank(user.getProfessionalPic()) || StringUtils.isBlank(user.getExpertise())
                || StringUtils.isBlank(user.getAbout())) {
            ajaxJson.setMsg("操作失败：参数为空");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        return ajaxJson;
    }

    /**
     * @Description: 提交个体门店注册第三步数据(注册门店)
     * @Author: wenxian.cai
     * @Date: 2017/6/24 16:09
     */
    @RequestMapping(value = "/individual/store/apply/three", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson applyIndividualStoreThree(@RequestBody OrgRegisterDTO org){
        AjaxJson ajaxJson = new AjaxJson();
        // 检查参数是否为空
        if (StringUtils.isBlank(org.getOrgName()) || StringUtils.isBlank(org.getOrgType())
                || StringUtils.isBlank(org.getWorkField()) || StringUtils.isBlank(org.getAbout())
                ) {
            ajaxJson.setMsg("操作失败：参数为空");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        org.setType(2);
        org.setParentId(null);
        int orgId = managerOrgService.registOrg(org);
        if (orgId < 0) {
            ajaxJson.setMsg("注册失败");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        ajaxJson.setObj(orgId);
        return ajaxJson;
    }

    /**
     * @Description: 提交个体门店注册第四步数据(注册用户)
     * @Author: wenxian.cai
     * @Date: 2017/6/26 9:50
     */
    @RequestMapping(value = "/individual/store/apply/four", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson applyIndividualStoreFour(@RequestBody OrgUserDTO user){
        AjaxJson ajaxJson = new AjaxJson();
        // 检查参数是否为空
        if (StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())
                || StringUtils.isBlank(user.getMobile()) || StringUtils.isBlank(user.getVerifyCode())
                ) {
            ajaxJson.setMsg("操作失败：参数为空");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        // 检查用户名(mobile)是否被占用
        if (orgUserService.userIsExist(user.getUserName())) {
            ajaxJson.setMsg("登录名已被注册");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        
        // 判断是否有引荐人
        String parentId = user.getParentId();
        if(StringUtils.isNotBlank(parentId)) {
            parentId = parentId.toUpperCase();
            // 查找引荐人ID否登记在系统中
            //获取字符串的第一个字符
            Object fir = parentId.subSequence(0, 1);//获取字符串的第一个字符
            if(fir.equals("A") || fir.equals("Y") || fir.equals("D")) {
                AdminUserDTO AdminUserDTO= memberService.getAdminUserUserNo(parentId);
                 if(AdminUserDTO ==null) {
                     ajaxJson.setMsg("推荐人ID不存在!");
                     ajaxJson.setSuccess(false);
                     return ajaxJson;
                 }
            }else {
                ajaxJson.setMsg("推荐人ID不存在!");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
        }else {
            //设置默认推荐人ID
            parentId = AgentConstant.AGENT_DEFUALT_PARENT_ID_A2;
        }
        
        //校验验证码是否正确或者过期
        if (!validCodeService.valid(user.getMobile(), user.getVerifyCode(), CacheType.USER_REGISTERY_CACHE)) {
            ajaxJson.setMsg("验证码不正确或已失效");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        try {
            String newRelativePath = null;
            if (StringUtils.isNotBlank(user.getPhoto())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(user.getPhoto(), "photo");
                ImageUtilV2.delImg(user.getPhoto());
                user.setPhoto(newRelativePath);
            }
            if (StringUtils.isNotBlank(user.getIdCardPicOne())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(user.getIdCardPicOne(), "photo");
                ImageUtilV2.delImg(user.getIdCardPicOne());
                user.setIdCardPicOne(newRelativePath);
            }
            if (StringUtils.isNotBlank(user.getIdCardPicTwo())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(user.getIdCardPicTwo(), "photo");
                ImageUtilV2.delImg(user.getIdCardPicTwo());
                user.setIdCardPicTwo(newRelativePath);
            }
            if (StringUtils.isNotBlank(user.getProfessionalPic())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(user.getProfessionalPic(), "photo");
                ImageUtilV2.delImg(user.getProfessionalPic());
                user.setProfessionalPic(newRelativePath);
            }
        } catch (Exception e) {
            logger.info("移动图片失败");
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }


        EmployeeRegisterDTO employee = new EmployeeRegisterDTO();
        employee.setUserName(user.getUserName());
        employee.setPassword(user.getPassword());
        employee.setRealName(user.getRealName());
        employee.setMobile(user.getMobile());
        employee.setUserType(user.getUserType());
        employee.setPhoto(user.getPhoto());
        employee.setIdCard(user.getIdCard());
        employee.setIdCardPicOne(user.getIdCardPicOne());
        employee.setIdCardPicTwo(user.getIdCardPicTwo());
        employee.setAddress(user.getAddress());
        employee.setProfessionalName(user.getProfessionalName());
        employee.setProfessionalPic(user.getProfessionalPic());
        employee.setExpertise(user.getExpertise());
        employee.setAbout(user.getAbout());
        employee.setOrgId(user.getOrgId());
        employee.setGender(user.getSex());
        employee.setBirthday(user.getBirthday());
        employee.setParentId(AgentConstant.AGENT_USER_TYPE_O + user.getOrgId());

        boolean bool = employeeManageService.addEmployee(employee);
        if (!bool) {
            ajaxJson.setMsg("注册失败");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        OrgRegisterDTO org = new OrgRegisterDTO();
        org.setId(user.getOrgId());
        org.setUserNo(AgentConstant.AGENT_USER_TYPE_O + user.getOrgId());
        org.setParentId(parentId);
        org.setContacts(user.getRealName());
        org.setContactInformation(user.getMobile());
        org.setLogo(user.getPhoto());
        try {
            managerOrgService.updateOrg(org);
        } catch (Exception e) {
            logger.info("注册个体门店：修改门店信息失败");
        }


        return ajaxJson;
    }

    /**
     * @Description: 个体门店注册步骤
     * @Author: wenxian.cai
     * @Date: 2017/6/20 15:50
     */
    @RequestMapping(value = "/individual/store/{step}")
    public ModelAndView individualStoreStep(@PathVariable String step) {
        String url = null;
        switch (step) {
            case "steptwo":
                url = "platform/register/individualstore/register-step-2";
                break;
            case "stepthree":
                url = "platform/register/individualstore/register-step-3";
                break;
            case "stepfour":
                url = "platform/register/individualstore/register-step-4";
                break;
            case "stepfive":
                url = "platform/register/individualstore/register-step-5";
                break;

        }
        ModelAndView modelAndView = new ModelAndView(url);
        return modelAndView;
    }



    /**
     * @param request
     * @param response
     * @return
     * @author duosheng.mo
     * @DateTime 2016年5月20日 下午1:45:37
     * @serverComment 发送验证码到手机上
     */
    @RequestMapping(params = "sendValidCode")
    @ResponseBody
    public AjaxJson sendValidCode(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "发送验证码失败";
        // 获取参数
        String mobile = (String) request.getParameter("mobile");
        String cache = (String) request.getParameter("cache");
        CacheType cacheType = null;
        String cacheKey = "", userId = "";

        switch (cache) {
            case "register":
                String userType = null;
                if (request.getParameter("userType") != null) {
                    userType = (String) request.getParameter("userType");
                }
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
            if (StringUtils.isEmpty(validCodeService.sendToMobile(Integer.parseInt(userId), mobile, cacheKey, cacheType, VcodeTerminalType.USER_PLATFORM, false))) {
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

    /**
     * @param request
     * @param response
     * @return
     * @author yuhang.weng
     * @DateTime 2016年6月3日 上午10:42:01
     * @serverComment 发送验证码到邮箱
     */
    @RequestMapping(params = "sendValidCodeToEmail")
    @ResponseBody
    public AjaxJson sendValidCodeToEmail(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "发送验证码失败";

        // 获取参数
        String email = (String) request.getParameter("email");
        String cache = (String) request.getParameter("cache");
        CacheType cacheType;
        switch (cache) {
            case "registery":
                cacheType = CacheType.USER_REGISTERY_CACHE;    // 注册 验证码
                // TODO 暂未提供该服务
                break;
            case "reset":
                cacheType = CacheType.USER_RESET_CACHE;    // 重置密码 验证码
                if (StringUtils.isBlank(email)) {
                    resObject.setMsg(msg + ":邮箱地址为空");
                    return resObject;
                }
                try {
                    String userId = memberService.checkEmail(email);
                    // 普通用户
                    if (StringUtils.isEmpty(userId)) {
                        userId = orgUserService.checkEmail(email);
                    }
                    // 企业用户
                    if (StringUtils.isEmpty(userId)) {
                        resObject.setMsg(msg + "邮箱地址不存在或未验证");
                        return resObject;
                    }
                    // 发送验证码
                    if (StringUtils.isEmpty(validCodeService.sendToEmail(email, userId, cacheType))) {
                        // 发送失败
                        resObject.setMsg(msg);
                        return resObject;
                    }
                    // 发送成功
                    resObject.setObj(userId);    // 保存userId到页面上
                    resObject.setSuccess(true);
                    resObject.setMsg("邮件已发送");
                    return resObject;
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    resObject.setMsg(msg + ":" + e.getMessage());
                }
            case "email":
                cacheType = CacheType.USER_EMAIL_MODIFY;    // 修改邮箱验证码
                try {

                    if (StringUtils.isEmpty(validCodeService.sendToEmail(email, email, cacheType))) {
                        // 发送验证码失败
                        resObject.setMsg(msg);
                        return resObject;
                    }
                    // 成功发送验证码
                    resObject.setSuccess(true);
                    resObject.setMsg("发送验证码成功");
                    return resObject;
                } catch (Exception e) {
                    resObject.setMsg(msg + ":" + e.getMessage());
                    return resObject;
                }

        }
        resObject.setMsg(msg + ":未知错误");
        return resObject;
    }


    /**
     * @Description: 用户注册协议
     * @author: wenxian.cai
     * @create: 2017/4/26 19:53
     */
    @RequestMapping(params = "showAgreement", method = RequestMethod.GET)
    public String showAgreement() {

        return "login/agreement";
    }

    /**
     * @Description: 机构注册协议
     * @author: wenxian.cai
     * @create: 2017/4/26 19:53
     */
    @RequestMapping(params = "showOrgAgreement", method = RequestMethod.GET)
    public String showOrgAgreement() {
        return "login/orgAgreement";
    }
}
