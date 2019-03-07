package com.lifeshs.controller.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lifeshs.pojo.serve.ServeTypeSecondDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.thirdservice.HuanXinService;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.health.ServiceDoctorVO;
import com.lifeshs.pojo.health.ServiceItem;
import com.lifeshs.pojo.org.OrgDTO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.org.group.GroupDTO;
import com.lifeshs.pojo.org.server.OrgServeDTO;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service.org.IServiceOrgService;

import net.sf.json.JSONArray;

/**
 * Created by XuZhanSi on 2016/10/14 0014.
 */
@Controller
@RequestMapping("serviceControl")
public class ServiceController extends BaseController {

    @Autowired
    private IServiceOrgService orgService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    protected HuanXinService huanXinService;

    /**
     * 健康问诊页面
     *
     * @param service
     * @param classify
     * @param sort
     * @return
     */
    @RequestMapping(params = "healthConsultPage")
    public ModelAndView healthConsultPage(@RequestParam(required = false) String service,
            @RequestParam(required = false) String classify, @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String filterName) {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/service/healthConsult");
        Map<String, Object> params = new HashMap<String, Object>();
        if (filterName != null)
            params.put("filterName", filterName);
        Map<String, Object> condition = new HashMap<String, Object>();
        if (service != null)
            condition.put("serviceList", service);
        if (classify != null)
            condition.put("classify", classify);
        if (!condition.isEmpty())
            params.put("condition", condition);

        List<ServiceItem> itemList = orgService.getConsultItemList(params, page, 10);
        int count = orgService.getHealthConsultListCount(params);
        List<Map<String, Object>> classifyTag = orgService.getClassifyTags();
        List<Map<String, Object>> serviceTag = orgService.getServiceTags();

        modelAndView.addObject("count", count);
        modelAndView.addObject("classifyTag", classifyTag);
        modelAndView.addObject("serviceTag", serviceTag);
        modelAndView.addObject("initPage", page);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("totalPage", count % 10 == 0 ? count / 10 : (int) Math.floor(count / 10) + 1);
        modelAndView.addObject("service", service);
        modelAndView.addObject("classify", classify);
        modelAndView.addObject("sort", sort);
        return modelAndView;
    }

    /**
     * 服务机构
     *
     * @param orgId
     * @param model
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月14日 下午2:37:21
     */
    @RequestMapping(params = "serviceOrganize", method = RequestMethod.GET)
    public String serviceOrganize(@RequestParam Integer orgId, Model model) {

        // 机构基本信息以及已开通的服务信息
        OrgDTO org = orgService.getServeOrgWithServeList(orgId);
        Map<String, Object> orgMap = new HashMap<>();
        orgMap.put("orgName", org.getOrgName());
        orgMap.put("about", org.getAbout());
        orgMap.put("detail", org.getDetail());
        
        List<Map<String, Object>> serves = new ArrayList<>();
        for(OrgServeDTO orgServeDTO : org.getOrgServes()) {
            Map<String, Object> serve = new HashMap<>();
            
            int serveId = orgServeDTO.getServeType().getId();
            String image = ""; //orgServeDTO.getServe().getImage();
            String name = orgServeDTO.getServeType().getName();
            int orgServeId = orgServeDTO.getId();
            
            serve.put("serveId", serveId);
            serve.put("image", image);
            serve.put("name", name);
            serve.put("orgServeId", orgServeId);
            serves.add(serve);
        }
        orgMap.put("serve", serves);

        if (org != null) {
            // 避免logo空值
            if (org.getLogo() == null) {
                orgMap.put("logo", "");
            }

            // 避免detail空值
            if (org.getDetail() == null) {
                orgMap.put("detail", "暂时没有该信息");
            }

            // 分类
            String classify = orgService.getServeOrgClassify(orgId);
            orgMap.put("classify", classify);

            // 机构会员数
            Map<String, Object> params = new HashMap<>();
            params.put("orgId", orgId);
            Integer count = orgService.getMemberCount(params);
            orgMap.put("memberCount", count);
        }

        model.addAttribute("org", orgMap);
        return "com/lifeshs/member/service/serviceOrganize";
    }

    /**
     * 服务师
     *
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月17日 下午5:39:29
     */
    @RequestMapping(params = "serviceDoctor", method = RequestMethod.GET)
    public String serviceDoctor(@RequestParam Integer orgUserId, Model model) {

        ServiceDoctorVO serviceDoctor = orgService.getServiceDoctor(orgUserId);

        model.addAttribute("orgUser", serviceDoctor);

        return "com/lifeshs/member/service/serviceDoctor";
    }

    /**
     * 用户的服务
     *
     * @return
     */
    @RequestMapping(params = "myService")
    public ModelAndView myService() {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/myService");

        List<Map<String, Object>> datas = orderService.getUserServeList(getLoginUser().getId());
        modelAndView.addObject("data", datas);
        return modelAndView;
    }

    /**
     * 用户的服务详情
     *
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月26日 上午11:48:34
     */
    @RequestMapping(params = "myServiceDetail", method = RequestMethod.GET)
    public String myServiceDetail(@RequestParam Integer orgServeId, Model model) {
        LoginUser user = getLoginUser();

        Map<String, Object> serve = orderService.getUserServeDetail(user.getId(), orgServeId);
        // classify
        int orgId = (int) serve.get("orgId");
        String classify = orgService.getServeOrgClassify(orgId);
        serve.put("classify", classify);
        // remaining
        String daysRemaining_s = (String) serve.get("daysRemaining");
        int timesRemaining = (int) serve.get("timesRemaining");

        String remaining = "";
        if (timesRemaining == 0) {
            remaining = daysRemaining_s.equals("0") ? "不足1天" : daysRemaining_s + "天";
        } else {
            if (daysRemaining_s.equals("0")) {
                remaining = timesRemaining + "次";
            } else {
                remaining = daysRemaining_s + "天、" + timesRemaining + "次";
            }
        }
        serve.remove("timesRemaining");
        serve.remove("daysRemaining");
        serve.put("remaining", remaining);

        model.addAttribute("serve", serve);

        return "com/lifeshs/member/choseService";
    }

    /**
     * 服务咨询
     *
     * @return
     */
    @RequestMapping(params = "lookMessage")
    public ModelAndView lookMessage(@RequestParam(required = false) Integer serverId,
            @RequestParam(required = false) Integer orgServeId) {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/messageCenter");
        LoginUser user = getLoginUser();
        MemberSharingData memberSharingData = getMemberSharingData();

        if (serverId != null && orgServeId != null) {
            Map<String, Object> detail = orderService.getUserServeDetail(user.getId(), orgServeId);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> servers = (List<Map<String, Object>>) detail.get("serveUsers");
            modelAndView.addObject("targetServer", serverId);
            modelAndView.addObject("servers", JSONArray.fromObject(servers));
            Integer chargeMode = (Integer) detail.get("chargeMode");
            String remain = "";
            if (chargeMode == 1) {// 按次
                remain = detail.get("timesRemaining") + "次";
            } else {
                remain = detail.get("daysRemaining") + "天";
            }
            modelAndView.addObject("remain", remain);
        }
        modelAndView.addObject("orgServeId", orgServeId);
        modelAndView.addObject("headUrl", Normal.PHOTO_PREFIX + memberSharingData.getPhotoPath());
        modelAndView.addObject("nick", memberSharingData.getRealName());
        modelAndView.addObject("hxCode", user.getUserCode());
        /** 测试添加群组 start */
        /*List<GroupDTO> groupList = new ArrayList<>();
        List<String> groupIdlist = huanXinService.listUserJoinGroupId(user.getUserCode());
        for (String groupId : groupIdlist) {
            GroupDTO groupDTO = huanXinService.getGroup(groupId);
            groupList.add(groupDTO);

        }
        System.out.println("groupList:" + groupList.toString());
        modelAndView.addObject("groupList", JSONObject.toJSON(groupList));*/
        /** 测试添加群组 end */
        return modelAndView;
    }

    /**
     * 购买服务的页面（下订单的页面）
     *
     * @return
     */
    @RequestMapping(params = "buyServicePage")
    public ModelAndView buyServicePage(@RequestParam Integer orgServeId) {
        ModelAndView modelAndView = null;
        OrgServeDTO orgServeDetail = orgService.getOrgServeDetail(orgServeId);
        ServeTypeSecondDTO serveDTO = orgServeDetail.getServeType();
        
        Map<String, Object> data = new HashMap<>();
        data.put("name", serveDTO.getName());
        data.put("image", ""); //serveDTO.getImage());
        String about = orgServeDetail.getAbout();
        if (StringUtils.isNotBlank(about) && about.length() > 60) {
            about = about.substring(0, 59) + "...";
        }
        data.put("about", about);
        data.put("hasFree", orgServeDetail.getHasFree());
        Boolean hasTime = orgServeDetail.getHasTime();
        if (hasTime != null && hasTime) {
            data.put("hasTime", true);
            data.put("timePrice", NumberUtils.changeF2Y(orgServeDetail.getTimePrice().toString()));
        } else {
            data.put("hasTime", false);
        }
        Boolean hasMonth = orgServeDetail.getHasMonth();
        if (hasMonth != null && hasMonth) {
            data.put("hasMonth", true);
            data.put("monthPrice", NumberUtils.changeF2Y(orgServeDetail.getMonthPrice().toString()));
        } else {
            data.put("hasMonth", false);
        }
        Boolean hasYear = orgServeDetail.getHasYear();
        if (hasYear != null && hasYear) {
            data.put("hasYear", true);
            data.put("yearPrice", NumberUtils.changeF2Y(orgServeDetail.getYearPrice().toString()));
        } else {
            data.put("hasYear", false);
        }
        data.put("id", orgServeDetail.getId());
        
        List<GroupDTO> groups = orgServeDetail.getGroups();
        String code = serveDTO.getCode();
        if ("02".equals(code)) {
            /** 健康课堂 */
            modelAndView = new ModelAndView("com/lifeshs/member/service/serviceDetails_lesson");
            // 群组ID与名称的集合
            List<Map<String, Object>> groupIdAndNameList = new ArrayList<>();
            // 群组信息集合
            List<Map<String, Object>> groupList = new ArrayList<>();
            for (GroupDTO group : groups) {
                Map<String, Object> groupIdAndNameMap = new HashMap<>();
                groupIdAndNameMap.put("id", group.getId());
                groupIdAndNameMap.put("name", group.getName());
                groupIdAndNameList.add(groupIdAndNameMap);
                
                Map<String, Object> groupMap = new HashMap<>();
                groupMap.put("id", group.getId());
                groupMap.put("photo", group.getPhoto());
                groupMap.put("description", group.getDescription());
                
                StringBuffer orgUserNames = new StringBuffer("");
                for (OrgUserDTO orgUser : group.getOrgUsers()) {
                    String realName = orgUser.getRealName();
                    if (orgUserNames.length() > 0) {
                        orgUserNames.append(",");
                    }
                    orgUserNames.append(realName);
                }
                groupMap.put("orgUserNames", orgUserNames);
                groupList.add(groupMap);
            }
            data.put("groupIdAndNameList", groupIdAndNameList);
            data.put("groupList", groupList);
        } else {
            modelAndView = new ModelAndView("com/lifeshs/member/service/serviceDetails");
            // 清除重复的服务师信息
            Map<Integer, OrgUserDTO> orgUserIdMap = new HashMap<>();
            for (GroupDTO group : groups) {
                for (OrgUserDTO user : group.getOrgUsers()) {
                    Integer id = user.getId();
                    if (!orgUserIdMap.containsKey(id)) {
                        orgUserIdMap.put(id, user);
                    }
                }
            }
            List<Map<String, Object>> userList = new ArrayList<>();
            for (Integer id : orgUserIdMap.keySet()) {
                OrgUserDTO user = orgUserIdMap.get(id);
                // 排除管理员身份
                if (user.getUserType() == 0) {
                    continue;
                }
                // 排除用户状态不为正常的服务师
                if (user.getStatus() != 0) {
                    continue;
                }
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("photo", user.getPhoto());
                userMap.put("realName", user.getRealName());
                userMap.put("about", user.getAbout());
                userList.add(userMap);
            }
            data.put("servers", userList);
        }
        modelAndView.addObject("data", data);
        return modelAndView;
    }

    /**
     * 生成订单页面
     *
     * @return
     */
    @RequestMapping(params = "createOrder")
    public ModelAndView createOrderPage(@RequestParam Integer orgServeId, @RequestParam(required = false) Integer count,
            @RequestParam Integer mode, @RequestParam(required = false, name = "groupId") String groupId) {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/service/orderDetails_1");
        LoginUser user = getLoginUser();
        String lut = user.getLut();

        if (StringUtils.equals(lut, "o")) {// 防止机构用户跳进该页面刷单
            modelAndView.setViewName("redirect:/orgControl.do?home");
            return modelAndView;
        }

        Map<String, Object> data = orderService.createOrderMsg(orgServeId, user.getId(), count, mode);
        data.put("groupId", groupId);
        modelAndView.addObject("data", data);
        return modelAndView;
    }

}
