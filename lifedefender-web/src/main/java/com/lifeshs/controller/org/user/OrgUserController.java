package com.lifeshs.controller.org.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.exception.org.AuthorityException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.OrgUserSharingData;
import com.lifeshs.pojo.org.OrgIndexVO;
import com.lifeshs.pojo.org.server.OrgMember;
import com.lifeshs.pojo.org.server.OrgServer;
import com.lifeshs.pojo.org.server.OrgServerGroupBase;
import com.lifeshs.service.org.IServiceOrgService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.utils.StringUtil;

import net.sf.json.JSONArray;

/**
 * 机构用户的基本控制器
 * 
 * @author yuhang.weng
 * @DateTime 2016年5月13日 下午2:31:58
 */
@Controller
@RequestMapping("/orgUserControl")
public class OrgUserController extends BaseController {

    @Autowired
    private IServiceOrgService serviceOrgService;

    @Autowired
    private IOrgUserService orgUserService;

    /**
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 员工管理页面
     */
    @RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
    @RequestMapping(params = "employManage")
    public ModelAndView employManage() {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/orgEmploymanage");
        LoginUser loginUser = getLoginUser();
        Integer orgId = loginUser.getOrgId();
        if (loginUser.getType() == 2)
            return null;
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("orgId", orgId);
        conditions.put("begin", 1);
        List<TOrgUser> emList = orgUserService.getEmployList(conditions);
        modelAndView.addObject("emList", emList);

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("orgId", orgId);

        int count = commonTrans.getCount(TOrgUser.class,conditionMap);
        modelAndView.addObject("employCount", count);
        count = (int) (count % 15 == 0 ? count / 15 : Math.floor(count / 15) + 1);
        modelAndView.addObject("pageCount", count);
        modelAndView.addObject("orgType", loginUser.getType());
        return modelAndView;
    }

    /**
     * 根据条件查询筛选过滤员工列表
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月6日
     * @serverComment
     */
    @RequestMapping(params = "filterEmploy")
    public @ResponseBody AjaxJson filterEmployList(@RequestBody Map<String, Object> map) {
        AjaxJson resJson = new AjaxJson();

        LoginUser loginUser = getLoginUser();
        Integer orgId = loginUser.getOrgId();

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> m : map.entrySet()) {
            conditionMap.put(m.getKey(), m.getValue());
        }
        map.put("orgId", orgId);
        List<TOrgUser> emList = orgUserService.getEmployList(map);
        conditionMap.put("orgId", orgId);
        conditionMap.remove("begin");
        int count = commonTrans.getCount(TOrgUser.class,conditionMap);
        count = (int) (count % 15 == 0 ? count / 15 : Math.floor(count / 15) + 1);
        resJson.setSuccess(true);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("emList", emList);
        result.put("count", count);
        resJson.setAttributes(result);
        return resJson;
    }

    /**
     * 操作员工的方法入口，例：删除、离职等
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    @RequestMapping(params = "controlEmploy")
    @RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
    public @ResponseBody AjaxJson controlEmploy(@RequestBody Map<String, Object> params) {
        AjaxJson resJson = new AjaxJson();
        /*int userId = orgUserService.controlEmploy(params);
        if( (int) params.get("target") == 0){
            LoginUser current = getLoginUser();
            if (current.getId().equals(userId)) {// 自己离职
                removeClient();// 移除当前用户
                userRealm.clearCache(SecurityUtils.getSubject().getPrincipals());
            }
        }*/
        resJson.setSuccess(true);
        return resJson;
    }

    /**
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月6日
     * @serverComment 添加员工页面
     */
    @RequestMapping(params = "addEmployPage")
    @RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
    public ModelAndView addEmployPage() {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/addEmploy");
        LoginUser loginUser = getLoginUser();
        if (loginUser.getType() == 2)
            return null;
        Integer orgId = loginUser.getOrgId();
        modelAndView.addObject("orgType", loginUser.getType());

        if (loginUser.getType() == 1) {// 服务机构，进行服务的获取与群组的
            List<OrgServer> serList = serviceOrgService.getOrgServiceListAndMemberCount(orgId, null);
            modelAndView.addObject("serList", serList);
            if (serList != null && !serList.isEmpty()) {
                List<OrgServerGroupBase> groupList = serviceOrgService.findGroupByKey(orgId, serList.get(0).getId(),
                        null);
                modelAndView.addObject("groupList", groupList);
            }
        }
        return modelAndView;
    }

    @RequestMapping(params = "editEmployPage", method = RequestMethod.GET)
    @RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
    public String editEmployPage(@RequestParam(value = "userId") Integer editUserId, Model model) {
        LoginUser loginUser = getLoginUser();
        int orgId = loginUser.getOrgId();
        model.addAttribute("orgType", loginUser.getType());
        Map<String, Object> employ = orgUserService.getEmploy(editUserId);

        model.addAttribute("employ", employ);
        if (loginUser.getType() == 1) {// 服务机构，进行服务的获取与群组的
            List<OrgServer> serList = serviceOrgService.getOrgServiceListAndMemberCount(orgId, null);
            model.addAttribute("serList", serList);
            if (serList != null && !serList.isEmpty()) {
                List<OrgServerGroupBase> groupList = serviceOrgService.findGroupByKey(orgId, serList.get(0).getId(),
                        null);
                model.addAttribute("groupList", groupList);
            }
        }

        model.addAttribute("showMenuDetail", 1);
        return "com/QYPart/editEmployInfo";
    }

    @RequestMapping(params = "editEmployPage", method = RequestMethod.POST)
    // @RequiresPermissions(value = {"user:0","user:2"},logical = Logical.OR)
    public @ResponseBody AjaxJson editEmployPage(@RequestBody Map<String, Object> data) {
        LoginUser user = getLoginUser();
        OrgUserSharingData orgUserSharingData = getOrgUserSharingData();
        
        int userId = user.getId();
        int userType = orgUserSharingData.getUserType();
        
        int orgId = user.getOrgId();

        int userId_data = (int) data.get("id");

        /** 判断修改的信息是否为用户本身 */
        boolean isOwnerUser = userId_data == userId;

        if (!isOwnerUser && userType == 1) {
            throw new AuthorityException("您无权修改该用户的信息");
        }

        /** 用户性质为服务师的时候，去除修改用户性质参数，避免服务师做出越界操作 */
        if (isOwnerUser && userType == 1) {
            data.remove("userType");
        }
        /** 机构类型为个体机构(type==2)或管理机构(type==0)的时候，不允许修改用户类型 */
        if (user.getType() == 2 || user.getType() == 0) {
            data.remove("userType");
        }

        // 判断手机号码是否被占用
        String mobile = (String) data.get("mobile");
        String mobileOwnerId = orgUserService.checkMobile(mobile);
        
        boolean isMyMobile = StringUtils.equals(mobileOwnerId, userId + "");
        if (isMyMobile) {
            mobileOwnerId = "";
            data.remove("mobile");
        }

        AjaxJson resObject = new AjaxJson();
        if (StringUtils.isNotBlank(mobileOwnerId)) {
            resObject.setSuccess(false);
            resObject.setMsg("手机号已被占用");
            return resObject;
        }

        String email = (String) data.get("email");
        if (StringUtils.isNotBlank(email)) {
            String emailOwnerId = orgUserService.checkEmail(email);
            if (StringUtils.isNotBlank(emailOwnerId)) {
                resObject.setSuccess(false);
                resObject.setMsg("邮箱已被占用");
                return resObject;
            }
        }

        /** 查找该机构下的管理员数量，避免机构中不存在管理员 */
        Integer userType_data = (Integer) data.get("userType");
        if (userType_data != null && userType_data.intValue() == 1 ) {
            boolean onlyOne = orgUserService.isOrgRemainOneAdmin(orgId);
            if (onlyOne) {
                resObject.setSuccess(false);
                resObject.setMsg("至少要保留一个管理员");
                return resObject;
            }
        }
        
        boolean flag = orgUserService.updateEmploy(data);
        resObject.setSuccess(flag);

        /** 如果是用户修改自身的信息，就不让页面重新跳到员工管理页面 */
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("redirect", isOwnerUser ? 0 : 1);
        resObject.setAttributes(attributes);

        if (isOwnerUser) {
            /** 更新缓存中的用户信息 */
            updateOrgUserData();
        }
        
        return resObject;
    }

    @RequestMapping(params = "userInfo", method = RequestMethod.GET)
    public String userInfo(Model model) {

        LoginUser user = getLoginUser();
        OrgUserSharingData orgUserSharingData = getOrgUserSharingData();
        int userId = user.getId();
        
        int orgId = user.getOrgId();
        model.addAttribute("orgType", user.getType());
        model.addAttribute("orgUserType", orgUserSharingData.getUserType());
        Map<String, Object> employ = orgUserService.getEmploy(userId);

        model.addAttribute("employ", employ);
        if (user.getType() == 1) {// 服务机构，进行服务的获取与群组的
            List<OrgServer> serList = serviceOrgService.getOrgServiceListAndMemberCount(orgId, null);
            model.addAttribute("serList", serList);
            if (serList != null && !serList.isEmpty()) {
                List<OrgServerGroupBase> groupList = serviceOrgService.findGroupByKey(orgId, serList.get(0).getId(),
                        null);
                model.addAttribute("groupList", groupList);
            }
        }

        model.addAttribute("showMenuDetail", 0);

        return "com/QYPart/editEmployInfo";
    }

    /**
     * 获取该机构的该服务下的群组列表
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月29日
     * @serverComment
     */
    @RequestMapping(params = "getGroupListByService")
    public @ResponseBody AjaxJson getGroupListByService(@RequestParam int server) {
        LoginUser loginUser = getLoginUser();
        Integer orgId = loginUser.getOrgId();
        List<OrgServerGroupBase> groupList = serviceOrgService.findGroupByKey(orgId, server, null);
        AjaxJson resJson = new AjaxJson();
        resJson.setObj(groupList);
        return resJson;
    }

    /**
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月6日
     * @serverComment 添加员工的方法
     * @tips
     */
    @RequestMapping(params = "addEmployee")
    @RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
    public @ResponseBody AjaxJson addEmployee(@RequestBody Map<String, Object> user) {
        AjaxJson resJson = new AjaxJson();
        LoginUser loginUser = getLoginUser();
        Integer orgId = loginUser.getOrgId();
        String userName = (String) user.get("userName");
        userName = StringUtil.decodeStr(userName);

        try {
            if (orgUserService.userIsExist(userName)) {
                resJson.setSuccess(false);
                resJson.setMsg("0");
                return resJson;
            }
            orgUserService.addEmploy(user, orgId);
            resJson.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resJson.setSuccess(false);
        }
        return resJson;
    }

    /**
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 用户管理页面
     */
    @RequestMapping(params = "userManage")
    @RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
    public ModelAndView userManage() {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/userManage");
        LoginUser loginUser = getLoginUser();
        if (loginUser.getType() == 0)
            return null;
        Integer orgId = loginUser.getOrgId();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orgId", orgId);
        params.put("page", 1);
        params.put("dPage", 15);
        List<OrgMember> memberList = serviceOrgService.findMemberList(params);
        int count = serviceOrgService.getMemberCount(params);
        count = (int) (count % 15 == 0 ? count / 15 : Math.floor(count / 15) + 1);
        List<OrgServer> serList = serviceOrgService.getOrgServiceListAndMemberCount(orgId, null);
        modelAndView.addObject("memberList", memberList);
        modelAndView.addObject("pageCount", count);
        modelAndView.addObject("serList", serList);
        return modelAndView;
    }

    /**
     * 分页或者筛选查询会员数据
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月13日
     * @serverComment
     */
    @RequestMapping(params = "getMemberDatas")
    public @ResponseBody AjaxJson getMemberDatas(@RequestBody Map<String, Object> params) {
        AjaxJson resJson = new AjaxJson();
        LoginUser loginUser = getLoginUser();
        Integer orgId = loginUser.getOrgId();
        params.put("orgId", orgId);
        params.put("dPage", 15);
        List<OrgMember> memberList = serviceOrgService.findMemberList(params);
        int count = serviceOrgService.getMemberCount(params);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", memberList);
        data.put("count", count);
        resJson.setAttributes(data);
        return resJson;
    }

    /**
     * 修改机构用户密码
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月28日
     * @serverComment
     */
    @RequestMapping(params = "changeOrgPsw")
    public @ResponseBody AjaxJson changeOrgPsw(@RequestBody Map<String, Object> param) {
        AjaxJson resJson = new AjaxJson();
        LoginUser user = getLoginUser();
        String oldKey = (String) param.get("oldKey");
        String newKey = (String) param.get("newKey");
        int target = orgUserService.updatePassword(user.getId(), StringUtil.decodeStr(oldKey),
                StringUtil.decodeStr(newKey));
        resJson.setSuccess(target == 0 ? false : true);
        return resJson;
    }

    /**
     * 添加用户界面
     * 
     * @author zhansi.Xu
     * @DateTime 2016年10月9日
     * @serverComment
     */
    @RequestMapping(params = "addUserPage")
    public ModelAndView addUserPage() {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/addUser");
        LoginUser user = getLoginUser();
        if (user.getType() == 0)
            return null;
        List<OrgServer> serList = serviceOrgService.getOrgServiceListAndMemberCount(user.getOrgId(), null);
        modelAndView.addObject("serList", JSONArray.fromObject(serList));
        if (serList != null && !serList.isEmpty()) {
            List<OrgServerGroupBase> groupList = serviceOrgService.findGroupByKey(user.getOrgId(),
                    serList.get(0).getId(), null);
            modelAndView.addObject("groupList", groupList);
        }
        return modelAndView;
    }

    /**
     * 添加用户方法入口
     * 
     * @author zhansi.Xu
     * @DateTime 2016年10月9日
     * @serverComment
     */
    @RequestMapping(params = "addUser")
    @RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
    public @ResponseBody AjaxJson addUser(@RequestBody Map<String, Object> params) {
        AjaxJson resjJson = new AjaxJson();
        int result = orgUserService.addUser(params);

        resjJson.setSuccess(result == 0 ? false : true);
        
        if (result > 0)
            result = 1;
        resjJson.setMsg(result + "");
        return resjJson;
    }

    /**
     *  獲取用戶信息
     *  @author yuhang.weng
     *  @DateTime 2016年12月9日 下午4:08:01
     *
     *  @return
     */
    /*@RequestMapping(params = "orgUser", method = RequestMethod.GET)
    public @ResponseBody AjaxJson orgUser() {
        AjaxJson resObject = new AjaxJson();

        LoginUser user = getLoginUser();
        int userId = user.getId();
        String userCode = user.getUserCode();
        OrgUserSharingData data = loginService.getCacheOrgMemberSharingData(userId);

        resObject.setSuccess(true);
        String name = data.getRealName();
        String head = data.getPhotoPath();
        int userTypeValue = data.getUserType();
        Boolean isEmailVerified = data.getEmailVerified();
        Boolean isMobileVerified = data.getMobileVerified();
        String userType = null;
        switch (userTypeValue) {
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
        String professionalName = data.getProfessionalName();
        TOrg org = managerOrgService.getOrgMessage(user.getOrgId());
        String orgName = org.getOrgName();

        int newCount = 10;  //TODO
        boolean isQualificated = false;
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("userName", name);
        attributes.put("userHead", head);
        attributes.put("orgName", orgName);
        attributes.put("userType", userType);
        attributes.put("isQualificated", isQualificated);
        attributes.put("isMobileVerified", isMobileVerified != null && isMobileVerified);
        attributes.put("isEmailVerified", isEmailVerified != null && isEmailVerified);
        attributes.put("newCount", newCount);
        attributes.put("userCode", userCode);
        attributes.put("professionalName", professionalName);
        resObject.setAttributes(attributes);

        return resObject;
    }*/

    /**
     * @Description: 获取机构主页信息
     * @author: wenxian.cai
     * @create: 2017/5/2 19:27
     */
    @RequestMapping(params = "gethomedata", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getHomeData() {
        AjaxJson resObject = new AjaxJson();

        LoginUser user = getLoginUser();
        int memberCount = ((Long) serviceOrgService.getMemberData(user.getOrgId(), "ALL").get("memberCount")).intValue();
        int taskCount = 10; //TODO
        Map trade = serviceOrgService.getTradeData(user.getOrgId(), "DAY");
        int earning = 0;
        int orderCount = 0 ;
        if (!trade.get("tradeCount").equals(null)) {
            orderCount = ((Long)trade.get("tradeCount")).intValue();
        }
        if (trade.get("charge") != null) {
            earning = ((Long)trade.get("charge")).intValue();
        }
        int visitorCount = 0;
        visitorCount = (int)(Math.random() * 100);  //TODO
        List visitor = new ArrayList(); //TODO
        visitorCount = visitorCount/2;
        int visitor_1 = (int)(Math.random() * visitorCount);
        int visitor_2 = (int)(Math.random() * visitorCount);
        int visitor_3 = (int)(Math.random() * visitorCount);
        int visitor_4 = (int)(Math.random() * visitorCount);
        int visitor_5 = (int)(Math.random() * visitorCount);
        int visitor_6 = (int)(Math.random() * visitorCount);
        int visitor_7 = (int)(Math.random() * visitorCount);
        visitor.add(visitor_1);
        visitor.add(visitor_2);
        visitor.add(visitor_3);
        visitor.add(visitor_4);
        visitor.add(visitor_5);
        visitor.add(visitor_6);
        visitor.add(visitor_7);
        OrgIndexVO vo = new OrgIndexVO();
        vo.setMemberCount(memberCount);
        vo.setMemberCount(taskCount);
        vo.setEarning(earning);
        vo.setOrderCount(orderCount);
        vo.setVisitorCount(visitorCount * 2);
        vo.setVisitor(visitor);
        resObject.setObj(vo);
        return resObject;
    }

    /**
     * 根据姓名查询用户
     *  服务注解
     *  @author NaN
     *  @DateTime 2018年5月11日 上午11:42:41
     *
     *  @param realName 用户姓名
     *  @return 用户姓名+手机号码
     */
    @RequestMapping("/getEmployListByRealName")
    @ResponseBody
    public AjaxJson getEmployListByRealName(@RequestParam String rName){
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        
        if(StringUtils.isNotBlank(rName)){
            int orgId = user.getOrgId();
            List<TOrgUser> orgUserList = orgUserService.getEmployListByRealName(rName, orgId);
            ajaxJson.setObj(orgUserList);
        }
        
        return ajaxJson;
    }

}
