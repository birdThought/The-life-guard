package com.lifeshs.controller.org;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.OrgUserSharingData;
import com.lifeshs.pojo.org.management.ManagementVO;
import com.lifeshs.pojo.org.management.OrgDetailVO;
import com.lifeshs.pojo.org.management.OrgServiceAndMemberDO;
import com.lifeshs.pojo.org.server.OrgDataCount;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.information.InformationService;
import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service.org.IServiceOrgService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.utils.ParserParaUtil;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 * 机构控制器
 *
 * @author dengfeng
 * @DateTime 2016-6-1 下午04:28:00
 */
@Controller
@RequestMapping(value = {"/orgControl","/org"})
public class OrgController extends BaseController {
    @Autowired
    private IManagerOrgService managerOrgService;

    @Autowired
    private IServiceOrgService serviceOrgService;

    @Autowired
    private IOrgUserService orgUserService;

    @Autowired
    private IDataAreaService areaService;

    private static final Logger logger = Logger.getLogger(OrgController.class);

    /**
     * @param request
     * @param response
     * @return
     * @author yuhang.weng
     * @DateTime 2016年5月16日 下午5:24:36
     * @serverComment 机构注册
     */
    @RequestMapping(params = "registerOrg", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerOrg(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "注册用户失败";

        // 获取request中传递的参数
        Map<String, Object> param = ParserParaUtil.getParams(request);
        String orgName = (String) param.get("orgName");
        String userName = (String) param.get("userName");
        String password = (String) param.get("password");
        Integer orgType = Integer.parseInt((String) param.get("orgType"));
        String xingzhi = (String) param.get("orgXz");

        // 检查参数是否为空
        if (StringUtils.isBlank(orgName) || StringUtils.isBlank(userName) || StringUtils.isBlank(password)
                || orgType == null || StringUtils.isBlank(xingzhi)) {
            resObject.setMsg(msg + "：参数为空");
            return resObject;
        }
        // 检查用户名(mobile)是否被占用
        if (orgUserService.userIsExist(userName)) {
            resObject.setMsg(msg + ":用户名已被注册");
            return resObject;
        }
        // 新增一个机构及其初始帐号
        int orgId = managerOrgService.registOrg(orgName, userName, password, orgType, xingzhi);
        msg = "注册成功";
        resObject.setObj(orgId);
        resObject.setSuccess(true);
        resObject.setMsg(msg);
        return resObject;
    }

    /**
     * 完善机构信息页面
     *
     * @author zhansi.Xu
     * @DateTime 2016年10月10日
     * @serverComment
     */
    @RequestMapping(params = "improveOrgPage")
    public ModelAndView improveOrgPage(@RequestParam int orgId) {
        ModelAndView modelAndView = new ModelAndView("login/uploadCertificate");
        modelAndView.addObject("orgId", orgId);
        return modelAndView;
    }

    /**
     * 完善机构信息
     *
     * @author zhansi.Xu
     * @DateTime 2016年10月10日
     * @serverComment
     */
    @RequestMapping(params = "improveOrgMsg")
    public
    @ResponseBody
    AjaxJson improveOrgMsg(@RequestBody Map<String, Object> params) {
        AjaxJson resJson = new AjaxJson();
        Integer orgId = (Integer) params.get("id");
        params.remove("id");
        managerOrgService.updateOrgDetails(params, orgId);
        resJson.setSuccess(true);
        return resJson;
    }

    /**
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 机构管理页面
     */
    @RequestMapping(params = "orgManage", method = RequestMethod.GET)
    public String orgManage(Model model) {

        LoginUser user = getLoginUser();
        Integer orgId = user.getOrgId();
        if (user.getType() != 0)// 不是管理机构
            return null;
        List<Map<String, Object>> datas = managerOrgService.getChildOrgMessage(orgId);
        model.addAttribute("datas", datas);
        // List<Map<String, Object>> services =
        // managerOrgService.getChildOrgServiceMessage(orgId);

        // model.addAttribute("services", services);

        return "com/QYPart/organizeManage";
    }

    @RequestMapping(params = "getOrgChildData", method = RequestMethod.GET)
    public
    @ResponseBody
    AjaxJson getOrgChildData(Integer orgId) {
        AjaxJson resObject = new AjaxJson();

        LoginUser user = getLoginUser();
        Integer parentId = user.getOrgId();
        if (user.getType() != 0)// 不是管理机构
            return null;
        managerOrgService.isThisOrgBelongToAnotherOne(parentId, orgId);

        resObject.setSuccess(true);
        resObject.setObj(managerOrgService.getOrganizeTree(orgId));
        return resObject;
    }

    @RequestMapping(params = "getOrgChildServiceData", method = RequestMethod.GET)
    public
    @ResponseBody
    AjaxJson getOrgChildServiceData() {
        AjaxJson resObject = new AjaxJson();

        LoginUser user = getLoginUser();
        Integer orgId = user.getOrgId();
        if (user.getType() != 0)// 不是管理机构
            return null;
        resObject.setSuccess(true);
        List<Map<String, Object>> datas = managerOrgService.getChildOrgServiceMessage(orgId);
        resObject.setObj(datas);

        return resObject;
    }

    @RequestMapping(params = "getOrgDetail", method = RequestMethod.GET)
    public
    @ResponseBody
    AjaxJson getOrgDetail(Integer orgId) {
        AjaxJson resObject = new AjaxJson();

        LoginUser user = getLoginUser();
        Integer parentId = user.getOrgId();

        managerOrgService.isThisOrgBelongToAnotherOne(parentId, orgId);

        OrgDetailVO detail = managerOrgService.getOrgDetailById(orgId);

        resObject.setSuccess(true);
        resObject.setObj(detail);

        return resObject;
    }

    /**
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 添加子机构页面
     */
    @RequestMapping(params = "addChildOrgPage")
    public ModelAndView addChildOrgPage() {
        LoginUser user = getLoginUser();
        if (user.getType() != 0)// 不是管理机构
            return null;
        ModelAndView modelAndView = new ModelAndView("com/QYPart/childOrAdd");
        List<Map<String, String>> province = areaService.findAllProvince();
        List<Map<String, String>> city = areaService.findCity("^11[0-9]{2}[0]{2}");// 默认为北京
        List<Map<String, String>> district = areaService.findDistrict("^11[0-9]{2}[0-9][1-9]");
        modelAndView.addObject("province", province);
        modelAndView.addObject("city", city);
        modelAndView.addObject("district", district);
        return modelAndView;
    }

    /**
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 添加子机构 (未绑定对应的机构ID)
     */
    @RequestMapping(params = "addChildOrg", method = RequestMethod.POST)
    public
    @ResponseBody
    AjaxJson addChildOrg(ManagementVO vo) {
        AjaxJson resJson = new AjaxJson();

        LoginUser user = getLoginUser();
        Integer parent = user.getOrgId();
        if (user.getType() != 0)// 不是管理机构
            return null;
        if (managerOrgService.orgIsExist(vo.getOrgName())) {
            resJson.setMsg("0");// 机构名已存在
            resJson.setSuccess(false);
            return resJson;
        }
        if (vo.getUserName() != null) {
            if (orgUserService.userIsExist(vo.getUserName())) {
                resJson.setMsg("1");// 用户名已存在
                resJson.setSuccess(false);
                return resJson;
            }
        }

        if (StringUtils.isNotBlank(vo.getBusinessLicense())) {
            // 将图片从tmp目录转移到upload下
            String newNetPath = null;
            try {
                newNetPath = ImageUtilV2.copyImgFileToUploadFolder(Normal.PHOTO_PREFIX + vo.getBusinessLicense(), "license");
            } catch (IOException e) {
                logger.error("图片转移地址失败");
                resJson.setMsg("图片保存失败");
            }
            vo.setBusinessLicense(newNetPath);
        }

        Integer orgId = managerOrgService.registChildOrg(vo, parent);

        resJson.setSuccess(orgId != null);
        return resJson;
    }

    /**
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 数据统计页面
     */
    @RequestMapping(params = "dataCount")
    public String dataCount() {
        return "com/QYPart/dataCount";
    }

    /**
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 机构主页
     */
    @RequestMapping(value = {"/home"})
    public String homeIndex(Model model) {
        LoginUser user = getLoginUser();
        model.addAttribute("orgType", user.getType());
        if (user.getType() == 0) {
            return "com/QYPart/Home";
        } else {
            return "com/QYPart/serviceHome";
        }
    }

    /**
     * 首页数据获取
     *
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月6日 下午8:11:39
     */
    @RequestMapping(params = "getHomeData", method = RequestMethod.GET)
    public
    @ResponseBody
    AjaxJson getHomeData() {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(true);

        Map<String, Object> data = new HashMap<>();

        LoginUser user = getLoginUser();
        Integer orgId = user.getOrgId();

        OrgUserSharingData orgUserSharingData = getOrgUserSharingData();

        /** 机构 */
        TOrg org = managerOrgService.getOrgMessage(orgId);

        data.put("orgName", org.getOrgName());
        data.put("orgVerified", org.getOrgVerified());

        // TODO 企业头像、号码认证、邮箱认证、安全等级
        data.put("logo", org.getLogo());

        String userType = "服务师";
        switch (orgUserSharingData.getUserType()) {
            case 0:
                userType = "管理员";
                break;
            case 1:
                userType = "服务师";
                break;
            case 2:
                userType = "管理员&服务师";
                break;
        }
        data.put("userType", userType); // 用户类型 管理员，服务师

        Integer managementAmount = managerOrgService.getTheAmountOfChildManagement(orgId);
        OrgServiceAndMemberDO serviceAndMemberAmount = managerOrgService.getTheAmountOfChildService(orgId);

        data.put("managementAmount", managementAmount);
        data.put("serviceAmount", serviceAndMemberAmount.getAmountOfService());
        data.put("memberAmount", serviceAndMemberAmount.getAmountOfMember());

        resObject.setAttributes(data);

        return resObject;
    }

    /**
     * 获取门店数据统计页面数据
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年9月13日下午7:48:04
     * @serverComment
     */
    @RequestMapping(params = "getTradeData", method = RequestMethod.POST)
    public
    @ResponseBody
    OrgDataCount getTradeData(@RequestBody Map<String, Object> data) {
        OrgDataCount OrgDataCount = new OrgDataCount();
        String dateType = (String) data.get("dateType");
        HashMap<String, Object> baseData = new HashMap<>(); // 门店基本信息
        HashMap<String, Object> tradeData; // 交易数据信息
        String trafficData = "5000"; // 流量信息
        HashMap<String, Object> membersData = new HashMap<>();// 会员增加信息
        // HashMap<String, Object> otherData; // 其他信息

        LoginUser user = getLoginUser();
        Integer orgId = user.getOrgId();
        OrgUserSharingData orgUserSharingData = getOrgUserSharingData();

        switch (dateType) {
            case "WEEK":
                /** 门店基本信息 */
                TOrg org = managerOrgService.getOrgMessage(orgId);
                baseData.put("org", org);
                String userType = "服务师";
                switch (orgUserSharingData.getUserType()) {
                    case 0:
                        userType = "管理员";
                        break;
                    case 1:
                        userType = "服务师";
                        break;
                    case 2:
                        userType = "管理员&服务师";
                        break;
                }
                baseData.put("userType", userType);
                OrgDataCount.setOrgMap(baseData);
                tradeData = serviceOrgService.getTradeData(orgId, dateType);
                OrgDataCount.setTradeMap(tradeData);
                OrgDataCount.setTraffic(trafficData);
                membersData.put("TodayCount", serviceOrgService.getMemberData(orgId, "DAY").get("memberCount"));
                membersData.put("YestodayCount", serviceOrgService.getMemberData(orgId, "YESTODAY").get("memberCount"));
                membersData.put("MonthCount", serviceOrgService.getMemberData(orgId, "MONTH").get("memberCount"));
                membersData.put("AllCount", serviceOrgService.getMemberData(orgId, "ALL").get("memberCount"));
                OrgDataCount.setMemberMap(membersData);
                break;
            case "MONTH": // 单独请求交易数据
                tradeData = serviceOrgService.getTradeData(orgId, dateType);
                OrgDataCount.setTradeMap(tradeData);
                break;
            case "SERVENDAY": // 单独请求流量数据
                trafficData = "10000";
                OrgDataCount.setTraffic(trafficData);
                break;
            default:
                break;
        }
        // System.out.println("resObject:"+OrgDataCount.toString());
        return OrgDataCount;
    }

    /**
     * 公司信息页面
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月28日
     * @serverComment
     */
    @RequestMapping(params = "companyDetailsPage")
    public ModelAndView companyDetailsPage() {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/companyDetails");
        LoginUser user = getLoginUser();
        Integer orgId = user.getOrgId();
        TOrg org = managerOrgService.getOrgDetail(orgId);
        List<Map<String, String>> province = areaService.findAllProvince();
        List<Map<String, String>> city = null;
        List<Map<String, String>> district = null;
        if (org.getProvince() == null) {
            city = areaService.findCity("^11[0-9]{2}[0]{2}");// 默认为北京
            district = areaService.findDistrict("^11[0-9]{2}[0-9][1-9]");
        } else {
            String cityRegex = "^" + org.getProvince() + "[0-9][0-9][0]{2}";
            String disRegex = "^" + org.getProvince() + org.getCity() + "[0-9][0-9]";
            city = areaService.findCity(cityRegex);
            district = areaService.findDistrict(disRegex);
            if (city.size() > 1)
                city.remove(0);
            /*
             * if(district.size()>1) district.remove(0);
             */
        }
        String detail = org.getDetail();
        if (StringUtils.isNotBlank(detail)) {
            org.setDetail(detail.replace("\n", ""));
        }
        modelAndView.addObject("province", province);
        modelAndView.addObject("city", city);
        modelAndView.addObject("district", district);
        modelAndView.addObject("org", org);
        modelAndView.addObject("orgType", org.getType());
        return modelAndView;
    }

    /**
     * 更新公司的信息
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月28日
     * @serverComment
     */
    @RequestMapping(params = "updateOrgDetails")
    public
    @ResponseBody
    AjaxJson updateOrgDetails(@RequestBody Map<String, Object> vo) {
        AjaxJson resJson = new AjaxJson();
        LoginUser user = getLoginUser();
        Integer orgId = user.getOrgId();
        managerOrgService.updateOrgDetails(vo, orgId);
        return resJson;
    }

    @Autowired
    InformationService informationService;

    /**
     * 机构的帮助中心
     *
     * @return
     */
    @RequestMapping(params = "orgHelp")
    public ModelAndView orgHelp(@RequestParam(required = false) Integer f, @RequestParam(required = false) Integer page, @RequestParam(required = false) String search) {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/orgHelp");
        modelAndView.addObject("data", informationService.loadHelpInformationDatas("帮助中心_机构", f, page, search));
        if (f != null)
            modelAndView.addObject("f", f);
        if (search != null)
            modelAndView.addObject("search", search);
        return modelAndView;
    }

    /**
     * 看一个机构帮助文档
     *
     * @return
     */
    @RequestMapping(params = "orgHelpLook")
    public ModelAndView orgHelpLook(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/orgHelpLook");
        TInformation information = commonTrans.get(TInformation.class, id);
        String columnName = commonTrans.get(TInfomationColumn.class, information.getColumnId()).getName();
        modelAndView.addObject("columns", informationService.loadColumnByParentColName("帮助中心_机构"));
        modelAndView.addObject("info", information);
        modelAndView.addObject("columnName", columnName);
        return modelAndView;
    }

    /**
     * 获取当前登录用户的企业类型
     *
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月28日 上午11:13:51
     */
    @RequestMapping(params = "orgType", method = RequestMethod.GET)
    public
    @ResponseBody
    AjaxJson orgType() {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(true);
        resObject.setMsg("获取信息成功");

        Map<String, Object> attributes = new HashMap<>();

        OrgUserSharingData orgUserSharingData = getOrgUserSharingData();

        attributes.put("orgType", orgUserSharingData.getOrgType());
        resObject.setAttributes(attributes);
        return resObject;
    }

    /**
     * 基础信息
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月28日
     * @serverComment
     */
    @RequestMapping(params = "orgBasePage")
    public ModelAndView orgBasePage() {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/orgBase");
        LoginUser user = getLoginUser();
        int target = orgUserService.getOrgType(user.getOrgId());
        modelAndView.addObject("orgType", target);// 0：管理机构，1：服务机构,2:个人服务机构
        return modelAndView;
    }
}
