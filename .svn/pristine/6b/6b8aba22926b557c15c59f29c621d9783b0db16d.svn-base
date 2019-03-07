package com.lifeshs.app.api.member.v24;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Area;
import com.lifeshs.common.constants.app.Combo;
import com.lifeshs.common.constants.app.Comment;
import com.lifeshs.common.constants.app.Consult;
import com.lifeshs.common.constants.app.DistanceCondition;
import com.lifeshs.common.constants.app.HuanXin;
import com.lifeshs.common.constants.app.Lesson;
import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.app.Org;
import com.lifeshs.common.constants.app.OrgServe;
import com.lifeshs.common.constants.app.OrgServeGroup;
import com.lifeshs.common.constants.app.OrgServeGroupUser;
import com.lifeshs.common.constants.app.OrgUser;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.PriceCondition;
import com.lifeshs.common.constants.app.ProjectServe;
import com.lifeshs.common.constants.app.Serve;
import com.lifeshs.common.constants.app.ServeCondition;
import com.lifeshs.common.constants.app.SortCondition;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.VisitServe;
import com.lifeshs.common.constants.app.banner.Banner;
import com.lifeshs.common.constants.app.banner.TypeEnum;
import com.lifeshs.common.constants.common.Error;
import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.common.constants.common.sort.serve.SortEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.OrgPO;
import com.lifeshs.po.UserPO;
import com.lifeshs.po.data.AppBannerPO;
import com.lifeshs.po.data.RecommendOrgServePO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.order.v2.OrderDTO;
import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.service.ServiceComboDTO;
import com.lifeshs.pojo.org.service.ServiceMediaDTO;
import com.lifeshs.pojo.org.service.VisitServiceDTO;
import com.lifeshs.pojo.org.v2.OrgDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.serve.ServeConditionDTO;
import com.lifeshs.pojo.serve.consult.ConsultConditionDTO;
import com.lifeshs.pojo.serve.lesson.LessonConditionDTO;
import com.lifeshs.pojo.serve.lesson.LessonProjectDTO;
import com.lifeshs.pojo.serve.lesson.RecommendedLessonDTO;
import com.lifeshs.pojo.serve.visit.RecommendedComboDTO;
import com.lifeshs.pojo.serve.visit.VisitConditionDTO;
import com.lifeshs.service.org.employee.IEmployeeManageService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service1.data.AppBannerService;
import com.lifeshs.service1.data.RecommendOrgServeService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.serve.ServeService;
import com.lifeshs.service1.serve.consult.ConsultService;
import com.lifeshs.service1.serve.lesson.LessonService;
import com.lifeshs.service1.serve.visit.VisitService;
import com.lifeshs.service1.store.StoreService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.vo.order.comment.CommentVO;
import com.lifeshs.vo.serve.consult.ServeUserVO;
import com.lifeshs.vo.serve.visit.ComboVO;
import com.lifeshs.vo.serve.visit.RecommendedVisitDetailVO;

/**
 *  服务
 *  @author yuhang.weng
 *  从V2.7开始，从服务类别（居家服务、中医养生、慢病康复、癌症康复、妇婴照护、减肥塑体）和服务类型（健康咨询、健康课堂、到店服务、上门服务）两个维度来组织服务结构，重新梳理接口
 *  @author dengfeng 2018-2-1
 *  @DateTime 2017年6月14日 上午10:27:22
 */
@RestController(value = "v24ServeController")
@RequestMapping(value = "app/serve/v24")
public class ServeController {

    @Resource(name = "v2LessonService")
    private LessonService lessonService;
    
    @Resource(name = "v2ConsultService")
    private ConsultService consultSerivce;
    
    @Resource(name = "v2VisitService")
    private VisitService visitService;
    
    @Resource(name = "v2ServeService")
    private ServeService serveService;
    
    @Resource(name = "v2StoreService")
    private StoreService storeService;
    
    @Resource(name = "employeeManageService")
    private IEmployeeManageService employeeService;
    
    @Resource(name = "v2OrderService")
    private OrderService orderService;
    
    @Resource(name = "recommendOrgServeService")
    private RecommendOrgServeService recommendOrgServeService;
    
    @Resource(name = "appBannerService")
    private AppBannerService bannerService;
    
    /**
     *  获取首页信息
     *  @author yuhang.weng 
     *	@DateTime 2017年6月19日 下午2:14:52
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "index", method = RequestMethod.POST)
    public JSONObject index(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        
        Double longitude = mm_0.getDouble(Area.LONGITUDE);
        Double latitude = mm_0.getDouble(Area.LATITUDE);
        Map<String, Object> returnData = new HashMap<>();
        
        // Banner
        List<Map<String, Object>> bannerList = new ArrayList<>();
        List<AppBannerPO> appBannerList = bannerService.listBanner(TypeEnum.SERVICE);
        for (AppBannerPO appBanner : appBannerList) {
            Map<String, Object> banner = new HashMap<>();
            banner.put(Banner.IMAGE, appBanner.getNetPath());
            banner.put(Banner.CUSTOM, appBanner.getCustom());
            bannerList.add(banner);
        }
        returnData.put(Banner.BANNER, bannerList);
        
        // 课堂
        List<RecommendedLessonDTO> rls = lessonService.listRecommendedLesson(3);
        List<Map<String, Object>> lessonList = enclosureLessonServe(rls, true);
        returnData.put(Lesson.LESSON, lessonList);
        
        // 咨询
        List<ServeUserVO> rcs = consultSerivce.listRecommendedConsult(2);
        List<Map<String, Object>> consultList = enclosureConsult(rcs, true);
        returnData.put(Consult.CONSULT, consultList);
        
        // 推荐服务
        List<RecommendedComboDTO> rCs = visitService.listRecommendedCombo(longitude, latitude, 2);
        List<Map<String, Object>> visitServeList = enclosureVisitServe(rCs);
        returnData.put(VisitServe.VISIT_SERVE, visitServeList);
        
        // 推荐机构
        List<OrgPO> ros = storeService.listRecommendedStore(3);
        List<Map<String, Object>> orgList = enclosureOrg(ros, false);
        returnData.put(Org.ORG, orgList);
        
        return AppNormalService.success(returnData);
    }


    /**
     * 获取服务查询的筛选条件，服务类型变更为二级目录,此接口用于替代getLessonCondition、getConsultCondition 、getVisitServeCondition
     * @author dengfeng
     * @param json
     * @return
     */
    @RequestMapping(value = "getServeCondition", method = RequestMethod.POST)
    public JSONObject getServeCondition(@RequestParam String json) {
//        AppJSON appJSON = AppNormalService.parseAppJSON(json);
//        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        ServeConditionDTO condition = serveService.getServeCondition();
        return AppNormalService.success(condition);
    }

    /**
     *  获取课堂筛选条件
     *  @author yuhang.weng 
     *	@DateTime 2017年6月23日 下午3:02:21
     *  此方法改为从服务类型（健康咨询、健康课堂、到店服务、上门服务）查询服务的接口
     *  @author dengfeng  2018-2-1
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getLessonCondition", method = RequestMethod.POST)
    public JSONObject getLessonCondition(@RequestParam String json) {
        LessonConditionDTO condition = lessonService.getLessonCondition();
        return AppNormalService.success(condition);
    }
    
    /**
     *  筛选课堂信息
     *  @author yuhang.weng 
     *	@DateTime 2017年6月23日 下午3:02:31
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "listLessonWithCondition", method = RequestMethod.POST)
    public JSONObject listLessonWithCondition(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        
        String priceArea = mm_0.getString(PriceCondition.AREA);
        String type = mm_0.getString(ServeCondition.TYPE); //服务类别code，一级目录2位，二级目录4位
        String likeName = mm_0.getString(ServeCondition.LIKE_NAME);
        int curPage = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        
        PaginationDTO<RecommendedLessonDTO> p = lessonService.listProjectLesson(priceArea, type, likeName, curPage, pageSize);
        List<Map<String, Object>> returnDataList = enclosureLessonServe(p.getData(), true);
        return AppNormalService.success(returnDataList, true);
    }
    
    @RequestMapping(value = "getLessonDetail", method = RequestMethod.POST)
    public JSONObject getLessonDetail(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int lessonId = mm_0.getIntValue(Lesson.ID);
        RecommendedLessonDTO lesson = lessonService.getLesson(lessonId);
        if (lesson.getLessonProject() == null) {
            return AppNormalService.error("课堂已下线或不存在", ErrorCodeEnum.NOT_FOUND);
        }
        Map<String, Object> returnData = enclosureLessonServe(lesson, true);
        return AppNormalService.success(returnData);
    }
    
    @RequestMapping(value = "getLessonSetting", method = RequestMethod.POST)
    public JSONObject getLessonSetting(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int serveGroupId = mm_0.getIntValue(Lesson.ID);
        RecommendedLessonDTO lessonDTO = lessonService.getLesson(serveGroupId);
        if (lessonDTO == null) {
            return AppNormalService.error(String.format(Error.NOT_FOUND, "课堂"), ErrorCodeEnum.NOT_FOUND);
        }
        LessonProjectDTO project = lessonDTO.getLessonProject();
        /** 开课时间 */
        String lessonTime = project.getLessonTime();
        JSONArray courseTimes = new JSONArray();
        if (StringUtils.isNotBlank(lessonTime)) {
            courseTimes = JSONArray.parseArray(lessonTime);
            for (int i = 0; i < courseTimes.size(); i++) {
                JSONObject t_i = courseTimes.getJSONObject(i);
                String weeks = t_i.getString("weeks");
                t_i.remove("weeks");
                t_i.put(Lesson.WEEK, MAppNormalService.weeksToInt(weeks));
            }
            
        }
        
        /** 遍历群组用户 */
        List<Map<String, Object>> users = new ArrayList<>();
        List<OrderPO> orderList = orderService.listOrderByProjectCode(project.getCode());
        for (OrderPO o : orderList) {
            //int orderStatus = o.getStatus();
            // 将有效的订单用户添加到用户列表中
            //if (OrderStatus.VALID.getStatus().intValue() == orderStatus) {
                UserPO u = o.getUser();
                Map<String, Object> user = new HashMap<>();
                user.put(OrgServeGroupUser.TYPE, 2);
                user.put(OrgServeGroupUser.PHOTO, u.getPhoto());
                user.put(OrgServeGroupUser.NAME, u.getRealName());
                user.put(OrgServeGroupUser.ID, u.getId());
                user.put(OrgServeGroupUser.CODE, u.getUserCode());
                users.add(user);
            //}
        }
        
        for (EmployeeDTO orgUser : project.getServeUserList()) {
            Map<String, Object> user = new HashMap<>();
            user.put(OrgServeGroupUser.TYPE, orgUser.getUserType());
            user.put(OrgServeGroupUser.PHOTO, orgUser.getPhoto());
            user.put(OrgServeGroupUser.NAME, orgUser.getRealName());
            user.put(OrgServeGroupUser.ID, orgUser.getId());
            user.put(OrgServeGroupUser.CODE, orgUser.getUserCode());
            users.add(user);
        }

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(OrgServeGroup.HUANXIN_ID, project.getHuanxinId());
        returnData.put(OrgServeGroup.NAME, Toolkits.projectTilte(project.getName()));
        returnData.put(OrgServeGroup.DESCRIPTION, project.getDescription());
        returnData.put(OrgServeGroup.COURSE_TIME, courseTimes);
        returnData.put(OrgServeGroupUser.USERS, users);
        return AppNormalService.success(returnData);
    }
    
    /**
     *  获取线下服务筛选条件
     *  @author yuhang.weng 
     *	@DateTime 2017年6月23日 下午5:29:48
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getVisitServeCondition", method = RequestMethod.POST)
    public JSONObject getVisitServeCondition(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String serveCode = mm_0.getString(Serve.CODE);
        VisitConditionDTO condition = visitService.getVisitServeCondition(serveCode);
        return AppNormalService.success(condition);
    }
    
    @RequestMapping(value = "listVisitServeWithCondition", method = RequestMethod.POST)
    public JSONObject listVisitServeWithCondition(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        Double longitude = mm_0.getDouble(Area.LONGITUDE);
        Double latitude = mm_0.getDouble(Area.LATITUDE);
        String areaCode = mm_0.getString(Area.CITY_AREA_CODE);
        String distanceArea = mm_0.getString(DistanceCondition.AREA);
        String priceArea = mm_0.getString(PriceCondition.AREA);
        String type = mm_0.getString(ServeCondition.TYPE);
        String likeName = mm_0.getString(ServeCondition.LIKE_NAME);
        int serveCode = Integer.parseInt(mm_0.getString(Serve.CODE));
        int sortValue = mm_0.getIntValue(ServeCondition.SORT);
        int curPage = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        
        SortEnum sort = SortEnum.getSortEnum(sortValue);
        ProjectType projectType = ProjectType.values()[serveCode];
        Paging<RecommendedComboDTO> p = null;
        try {
            p = visitService.listProjectCombo(areaCode, longitude, latitude, distanceArea, priceArea, type, sort, likeName, projectType, curPage, pageSize);
        } catch (OperationException oe) {
            return AppNormalService.error(oe.getMessage(), oe.getCode());
        }
        List<Map<String, Object>> returnDataList = enclosureVisitServe(p.getData());
        return AppNormalService.success(returnDataList, true);
    }
    
    @RequestMapping(value = "getConsultCondition", method = RequestMethod.POST)
    public JSONObject getConsultCondition(@RequestParam String json) {
        ConsultConditionDTO condition = consultSerivce.getConsultCondition();
        return AppNormalService.success(condition);
    }
    
    @RequestMapping(value = "listConsultWithCondition", method = RequestMethod.POST)
    public JSONObject listConsultWithCondition(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String priceArea = mm_0.getString(PriceCondition.AREA);
        String type = mm_0.getString(ServeCondition.TYPE);
        int sortValue = mm_0.getIntValue(SortCondition.VALUE);
        String likeName = mm_0.getString(ServeCondition.LIKE_NAME);
        int curPage = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        SortEnum sort = SortEnum.getSortEnum(sortValue);
        Paging<ServeUserVO> p = consultSerivce.listProjectConsult(priceArea, type, sort, likeName, curPage, pageSize);
        List<Map<String, Object>> returnDataList = enclosureConsult(p.getData(), true);
        return AppNormalService.success(returnDataList, true);
    }
    
    @RequestMapping(value = "getConsultDetail", method = RequestMethod.POST)
    public JSONObject getConsultDetail(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int consultId = mm_0.getIntValue(Consult.ID);
        
        ServeUserVO data = consultSerivce.getServeUser(consultId);
        if (data == null) {
            return AppNormalService.error(String.format(Error.NOT_FOUND, "服务"), ErrorCodeEnum.NOT_FOUND);
        }
        
        Map<String, Object> returnData = enclosureConsult(data, true);
        return AppNormalService.success(returnData);
    }
    
    @RequestMapping(value = "listServeCode", method = RequestMethod.POST)
    public JSONObject listServeCode(@RequestParam String json) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (ProjectType serve : ProjectType.values()) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put(Serve.CODE, serve.getValue());
            returnData.put(Serve.NAME, serve.getName());
            returnDataList.add(returnData);
        }
        return AppNormalService.success(returnDataList);
    }
    
    @RequestMapping(value = "listRecommendedServe", method = RequestMethod.POST)
    public JSONObject listRecommendedServe(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        Double longitude = mm_0.getDouble(Area.LONGITUDE);
        Double latitude = mm_0.getDouble(Area.LATITUDE);
        
        List<RecommendedComboDTO> rcs = visitService.listRecommendedCombo(longitude, latitude, 2);
        List<Map<String, Object>> returnDataList = enclosureVisitServe(rcs);
        return AppNormalService.success(returnDataList);
    }
    
    @RequestMapping(value = "getVisitComboDetail", method = RequestMethod.POST)
    public JSONObject getVisitComboDetail(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int comboId = mm_0.getIntValue(Combo.ID);
        
        RecommendedVisitDetailVO data = null;
        try {
            data = visitService.getVisitComboDetail(comboId);
        } catch (OperationException e) {
            return AppNormalService.error(e.getMessage(), e.getCode());
        }
        Map<String, Object> returnData = enclosureVisitServe(data);
        List<RecommendedComboDTO> comboData = visitService.listRecommendedCombo(null, null, 2);
        returnData.put(VisitServe.VISIT_SERVE, enclosureVisitServe(comboData));
        
        return AppNormalService.success(returnData);
    }
    
    @RequestMapping(value = "listStore", method = RequestMethod.POST)
    public JSONObject listStore(@RequestParam String json) {
        // 推荐机构
        List<OrgPO> ros = storeService.listStore(0);
        List<Map<String, Object>> returnDataList = enclosureOrg(ros, false);
        return AppNormalService.success(returnDataList);
    }
    
    @RequestMapping(value = "getStoreDetail", method = RequestMethod.POST)
    public JSONObject getStoreDetail(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int orgId = mm_0.getIntValue(Org.ID);
        
        OrgPO data = storeService.getStore(orgId);
        
        Map<String, Object> returnData = enclosureOrg(data, true);
        return AppNormalService.success(returnData);
    }
    
    @RequestMapping(value = "getServeUserDetail", method = RequestMethod.POST)
    public JSONObject getServeUserDetail(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        //支持按用户名和用户CODE查询
        EmployeeDTO employee = null;
        if(mm_0.containsKey(OrgUser.ID)) {
            int serveUserId = mm_0.getIntValue(OrgUser.ID);
            employee = employeeService.getEmployee(serveUserId);
        }else if(mm_0.containsKey("userCode")) {
            List<String> userCodes = new ArrayList<String>(){};
            userCodes.add(mm_0.getString("userCode"));
            List<EmployeeDTO> employeeS = employeeService.listEmployee(userCodes);
            if(employeeS != null && employeeS.size() > 0)
                employee = employeeS.get(0);
        }

        if (employee == null) {
            return AppNormalService.error("服务师已离职或不存在", ErrorCodeEnum.NOT_FOUND);
        }
        Map<String, Object> returnData = enclosureServeUser(employee);
        
        return AppNormalService.success(returnData);
    }
    
    /**
     *  设备推荐咨询
     *  @author yuhang.weng 
     *  @DateTime 2017年8月18日 上午10:55:35
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "deviceRecommendConsult", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject deviceRecommendConsult(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        Integer userId = appJSON.getData().getUserId();
        
        boolean own = false;
        RecommendOrgServePO serve = recommendOrgServeService.getLifeshsRecommendConsult();
        int relationId = serve.getOrgServeId();
        
        ServeUserVO consultServe = consultSerivce.getServeUser(relationId);
        int serveUserId = consultServe.getUserId();
        OrderDTO order = orderService.getConsultOrder(userId, serveUserId);
        
        Map<String, Object> returnData = new HashMap<>();
        
        // 查询用户与服务师之间是否存在健康咨询关系，如果已经有了，就直接返回服务师的信息
        if (order != null) {
            own = true; // 存在这种咨询服务
            
            returnData.put(HuanXin.USERNAME, consultServe.getUserCode());
            returnData.put(OrgUser.PHOTO, consultServe.getPhoto());
            returnData.put(OrgUser.NAME, consultServe.getRealName());
            long hourRemaining = 0;
            // 计算剩余小时
            Date end = order.getEndDate();
            hourRemaining = DateTimeUtilT.calculateHourInterval(new Date(), end);
            returnData.put(Order.HOUR_REMAINING, hourRemaining);
        }
        returnData.put(com.lifeshs.common.constants.app.recommend.Consult.ID, serve.getOrgServeId());
        returnData.put(com.lifeshs.common.constants.app.recommend.Consult.OWN, own);
        return AppNormalService.success(returnData);
    }
    
    /**
     *  线下，上门服务模型封装
     *  @author yuhang.weng 
     *	@DateTime 2017年6月30日 下午6:12:35
     *
     *  @param dataList
     *  @return
     */
    private List<Map<String, Object>> enclosureVisitServe(List<RecommendedComboDTO> dataList) {
        List<Map<String, Object>> visitServeList = new ArrayList<>();
        for (RecommendedComboDTO data : dataList) {
            ServiceComboDTO combo = data.getCombo();
            VisitServiceDTO vs = combo.getVisitServe();
            Double price = combo.getPrice();
            Double marketPrice = combo.getMarketPrice();
            
            int score = 0;
            if (data.getScore() != null) {
                BigDecimal scoreBigDecimal = new BigDecimal(data.getScore());
                score = scoreBigDecimal.setScale(0, RoundingMode.HALF_UP).intValue();
            }
            
            Map<String, Object> visitServe = new HashMap<>();
            visitServe.put(Combo.ID, combo.getId());
            visitServe.put(VisitServe.NAME, Toolkits.projectTilte(vs.getName()));
            visitServe.put(Combo.PRICE, price);
            visitServe.put(Combo.PRICE_MARKET, marketPrice);
            visitServe.put(VisitServe.DISTANCE, data.getDistance());
            visitServe.put(VisitServe.PHOTO, vs.getImage());
            visitServe.put(VisitServe.SCORE, score);
            visitServe.put(ProjectServe.CODE, data.getCombo().getProjectCode());    // 2017-12-15 添加projectCode
            
            visitServeList.add(visitServe);
        }
        return visitServeList;
    }
    
    /**
     *  课堂模型封装
     *  @author yuhang.weng 
     *	@DateTime 2017年7月1日 下午5:10:15
     *
     *  @param dataList
     *  @param total 包含全字段
     *  @return
     */
    private List<Map<String, Object>> enclosureLessonServe(List<RecommendedLessonDTO> dataList, boolean total) {
        List<Map<String, Object>> lessonList = new ArrayList<>();
        for (RecommendedLessonDTO data : dataList) {
            lessonList.add(enclosureLessonServe(data, total));
        }
        
        return lessonList;
    }
    
    /**
     *  课堂模型封装
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 下午6:25:51
     *
     *  @param data
     *  @param total 包含全字段
     *  @return
     */
    private Map<String, Object> enclosureLessonServe(RecommendedLessonDTO data, boolean total) {
        LessonProjectDTO p = data.getLessonProject();
        // 获取第一个服务师
        EmployeeDTO u_1 = p.getServeUserList().get(0);
        String mainServeUserName = u_1.getRealName();   // 主讲人姓名
        Map<String, Object> lesson = new HashMap<>();
        lesson.put(ProjectServe.CODE, p.getCode());
        lesson.put(Lesson.ID, p.getId());
        lesson.put(Lesson.REMARK, mainServeUserName + "的直播间");
        lesson.put(OrgServe.MEMBER_COUNT, data.getBuyerCount());
        lesson.put(Lesson.HEAD_PHOTO, p.getImage());
        lesson.put(Lesson.NAME, Toolkits.projectTilte(p.getName()));
        lesson.put(Serve.CODE, ProjectType.PROJECT_LESSON.getValue());
        
        // 详细信息
        if (total) {
            lesson.put(Lesson.PRICE, p.getPrice());
            lesson.put(Lesson.DESCRIPTION, p.getDescription());
            /** 开课时间 */
            String lessonTime = p.getLessonTime();
            List<Map<String, Object>> courseTimeList = new ArrayList<>();
            if (StringUtils.isNotBlank(lessonTime)) {
                JSONArray t = JSONArray.parseArray(lessonTime);
                for (int i = 0; i < t.size(); i++) {
                    JSONObject t_i = t.getJSONObject(i);
                    String weeks = t_i.getString("weeks");
                    List<Integer> weekList = MAppNormalService.weeksToIntList(weeks);
                    for (int w = 0; w < weekList.size(); w++) {
                        Map<String, Object> courseTime = new HashMap<>();
                        courseTime.put(Lesson.START_TIME, t_i.getString("startTime"));
                        courseTime.put(Lesson.WEEK, weekList.get(w));
                        courseTimeList.add(courseTime);
                    }
                }
                
            }
            lesson.put(Lesson.COURSE_TIME, courseTimeList);
            List<String> bannerList = new ArrayList<>();
            String banner1 = AppNormalService.normativeApproachImagePath(p.getImage());
            
            bannerList.add(banner1);
            lesson.put(Lesson.BANNER, bannerList);
            
            OrgDTO orgDTO = p.getOrg();
            Map<String, Object> orgMap = new HashMap<>();
            orgMap.put(Org.ID, orgDTO.getId());
            orgMap.put(Org.LOGO, orgDTO.getLogo());
            orgMap.put(Org.NAME, orgDTO.getOrgName());
            lesson.put(Org.ORG, orgMap);
            
            lesson.put(HuanXin.USERNAME, p.getHuanxinId());
        }
        
        return lesson;
    }
    
    /**
     *  健康咨询模型封装
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 上午10:06:59
     *
     *  @param dataList
     *  @return
     */
    private List<Map<String, Object>> enclosureConsult(List<ServeUserVO> dataList, boolean total) {
        List<Map<String, Object>> consultList = new ArrayList<>();
        for (ServeUserVO rc : dataList) {
            consultList.add(enclosureConsult(rc, total));
        }
        return consultList;
    }
    
    /**
     *  健康咨询模型封装
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 下午6:31:43
     *
     *  @param data
     *  @param total 包含全字段
     *  @return
     */
    private Map<String, Object> enclosureConsult(ServeUserVO data, boolean total) {
        long score = 0;
        long commentCount = data.getCommentCount();
        Double applauseRate = 0D;
        if (commentCount != 0) {
            score = NumberUtils.divide(data.getScoreTotal(), data.getCommentCount(), 0);
            applauseRate = NumberUtils.divide(new Long(data.getScoreTotal()).doubleValue(), Double.valueOf(commentCount * 5), 2);
            applauseRate = Float.valueOf(NumberUtils.multiply(applauseRate.floatValue(), 100F, 0)).doubleValue();
        }
        
        Map<String, Object> consult = new HashMap<>();
        consult.put(OrgUser.NAME, data.getRealName());
        consult.put(ProjectServe.TYPE, Toolkits.projectType(data.getProjectName()));
        consult.put(ProjectServe.CODE, data.getProjectCode());  // 2017-12-15 添加projectCode
        consult.put(OrgUser.PRO_NAME, data.getProfessionalName());
        consult.put(HuanXin.USERNAME, data.getUserCode());
        consult.put(Org.NAME, data.getOrgName());
        consult.put(OrgUser.EXPERT_FIELD, data.getExpertise());
        consult.put(OrgUser.PHOTO, data.getPhoto());
        consult.put(Consult.ID, data.getSid());
        consult.put(Serve.CODE, ProjectType.PROJECT_CONSULT.getValue());        
        consult.put(OrgUser.CODE, data.getUserCode());
        // 是否需要全字段
        if (total) {
            consult.put(OrgUser.ID, data.getUserId());
            consult.put(Consult.PRICE, data.getPrice());
            consult.put(Consult.MONTHPRICE, data.getMonthPrice());
            consult.put(Consult.YEARPRICE, data.getYearPrice());
            consult.put(Consult.SCORE, score);
            consult.put(OrgServe.CONSUME_PERSON_TIME, data.getBuyerCount());
            consult.put(Consult.APPLAUSE_RATE, applauseRate);
            consult.put(Consult.CHARGE_MODE, data.getChargeMode());
            
            List<Map<String, Object>> commentList = enclosureCommentList(data.getComment());
            consult.put(Comment.COMMENT, commentList);
            consult.put(Comment.COUNT, data.getCommentCount());
        }
        
        return consult;
    }
    
    /**
     *  线下服务详情模型封装
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 上午10:06:41
     *
     *  @param data
     *  @return
     */
    private Map<String, Object> enclosureVisitServe(RecommendedVisitDetailVO data) {
        long scoreAvg = 0;
        long commentCount = data.getCommentCount();
        if (commentCount != 0) {
            scoreAvg = NumberUtils.divide(data.getScoreTotal(), commentCount, 0);
        }
        String purchaseNotes = enclosurePurchaseNotes(data.getUserType(), data.getAppointment());
        List<Map<String, Object>> serveUserList = new ArrayList<>();
        for (com.lifeshs.vo.serve.ServeUserVO s : data.getServeUser()) {
            Map<String, Object> serveUserMap = new HashMap<>();
            serveUserMap.put(OrgUser.NAME, s.getRealName());
            serveUserMap.put(OrgUser.PHOTO, s.getHead());
            serveUserMap.put(OrgUser.ID, s.getId());
            serveUserList.add(serveUserMap);
        }
        List<Map<String, Object>> comboList = new ArrayList<>();
        for (ComboVO c : data.getCombo()) {
            Map<String, Object> comboMap = new HashMap<>();
            comboMap.put(Combo.ID, c.getComboId());
            comboMap.put(Combo.NAME, c.getName());
            comboMap.put(Combo.PRICE_MARKET, c.getMarketPrice());
            comboMap.put(Combo.PRICE, c.getPrice());
            
            JSONArray d = JSONArray.parseArray(c.getIntroduce());
            /**
             * 由于数据库中存储的price是门市价，需要在此处修改其key的值
             */
            Iterator<Object> iterator = d.iterator();
            while(iterator.hasNext()) {
                JSONObject obj = (JSONObject)iterator.next();
                Object p = obj.get("price");
                obj.put(Combo.PRICE_MARKET, p);
                obj.remove("price");
            }
            comboMap.put(Combo.DETAILS, d);
            comboList.add(comboMap);
        }
        Map<String, Object> comment = new HashMap<>();
        comment.put(Comment.COUNT, commentCount);
        List<Map<String, Object>> commentList = enclosureCommentList(data.getComment());
        comment.put(Comment.COMMENT, commentList);
        
        List<String> bannerList = new ArrayList<>();
        ServiceMediaDTO media = data.getMedia();
        String b1 = media.getPictureOne();
        String b2 = media.getPictureTwo();
        String b3 = media.getPictureThree();
        String b4 = media.getPictureFour();
        if (StringUtils.isNotBlank(b1)) {
            bannerList.add(AppNormalService.normativeApproachImagePath(b1));
        }
        if (StringUtils.isNotBlank(b2)) {
            bannerList.add(AppNormalService.normativeApproachImagePath(b2));
        }
        if (StringUtils.isNotBlank(b3)) {
            bannerList.add(AppNormalService.normativeApproachImagePath(b3));
        }
        if (StringUtils.isNotBlank(b4)) {
            bannerList.add(AppNormalService.normativeApproachImagePath(b4));
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put(Combo.ID, data.getComboId());
        result.put(VisitServe.BANNER, bannerList);
        result.put(VisitServe.SCORE, scoreAvg);
        result.put(VisitServe.NAME, Toolkits.projectTilte(data.getVisitServeName()));
        result.put(Org.NAME, data.getOrgName());
        
        result.put(Combo.PRICE, data.getPrice());
        result.put(Combo.PRICE_MARKET, data.getMarketPrice());
        result.put(Combo.COMBO, comboList);
        result.put(Area.ADDRESS, data.getOrgAddress());
        result.put(VisitServe.DETAIL, data.getDetail());
        result.put(VisitServe.PURCHASE_NOTES, purchaseNotes);
        result.put(OrgUser.USER, serveUserList);
        result.put(Comment.SERVE_COMMENT, comment);
        result.put(Serve.CODE, data.getServeCode());
        result.put(Org.NAME, data.getOrgName());
        result.put(Area.LONGITUDE, data.getLongitude());
        result.put(Area.LATITUDE, data.getLatitude());
        result.put(Org.TEL, data.getOrgTel());
        return result;
    }
    
    /**
     *  评论列表模型封装
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 上午10:06:23
     *
     *  @param data
     *  @return
     */
    private List<Map<String, Object>> enclosureCommentList(List<CommentVO> data) {
        List<Map<String, Object>> commentList = new ArrayList<>();
        for (CommentVO c : data) {
            String name = c.getName();
            if (StringUtils.isBlank(name)) {
                name = "匿名";
            } else {
                name = StringUtil.cover(name, 1, 0, "***");
            }
            Map<String, Object> commentMap = new HashMap<>();
            commentMap.put(Comment.SCORE, c.getScore());
            commentMap.put(User.PHOTO, c.getHead());
            commentMap.put(User.REALNAME, name);
            commentList.add(commentMap);
        }
        return commentList;
    }
    
    /**
     *  购买须知Html字符串获取
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 上午10:05:25
     *
     *  @param userType
     *  @param appointment
     *  @return
     */
    private String enclosurePurchaseNotes(String userType, String appointment) {
        String html = "<div class='content'><div><div>温馨提示</div><div>" + appointment
                + "</div></div><div><div>适用人群</div><div>" + userType
                + "</div></div><div><div>预约信息</div><div><ul><li class='red'>下单之后可以快速联系服务师进行上门时间安排</li><li class='red'>7天之内可以退款</li><li>如发现有问题，请您在消费时间向商户咨询</li><li>为了保障您的权益，建议使用线上支付。若使用其他的支付方式导致纠纷</li><li>本商家不承担任何责任，感谢您的理解和支持</li></ul></div></div><div><div>规则提醒</div><div>不与其他优惠同享</div></div></div><style>div.content{width:100%;font-size:12px;color:#666}div.content>div{margin-bottom:5px}div.content>div>div:nth-child(1){width:20%;display:inline-table;vertical-align:top}div.content>div>div:nth-child(2){width:80%;display:inline-table}ul{list-style-type:none;padding:0;margin:0}.red{color:red}</style>";
        return html;
    }
    
    private List<Map<String, Object>> enclosureOrg(List<OrgPO> dataList, boolean total) {
        List<Map<String, Object>> orgList = new ArrayList<>();
        for (OrgPO o : dataList) {
            orgList.add(enclosureOrg(o, total));
        }
        return orgList;
    }
    
    /**
     *  机构模型封装
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 下午2:52:10
     *
     *  @param data
     *  @param total
     *  @return
     */
    private Map<String, Object> enclosureOrg(OrgPO data, boolean total) {
        int orgId = data.getId();
        
        Map<String, Object> org = new HashMap<>();
        org.put(Org.LOGO, data.getLogo());
        org.put(Org.NAME, data.getOrgName());
        org.put(Org.ID, orgId);
        
        if (total) {
            int consumeCount = storeService.getStoreConsumeMemberNumber(orgId);
            List<ServeUserVO> orgServeUserList = consultSerivce.listOrgConsultServe(orgId);
            List<Map<String, Object>> consult = enclosureConsult(orgServeUserList, true);
            
            List<RecommendedLessonDTO> orgLessonList = lessonService.listLessonByOrgId(orgId);
            List<Map<String, Object>> lesson = enclosureLessonServe(orgLessonList, true);
            
            List<RecommendedComboDTO> orgComboList = visitService.listOrgProjectCombo(orgId, null);
            // 健康养生
            List<RecommendedComboDTO> heathComboT = new ArrayList<>();
            // 居家养老
            List<RecommendedComboDTO> homeBaseCareComboT = new ArrayList<>();
            for (RecommendedComboDTO c : orgComboList) {
                int code = c.getCombo().getVisitServe().getProjectType();
                ProjectType projectType = ProjectType.values()[code];
                if (ProjectType.PROJECT_TOSTORE.equals(projectType)) {
                    heathComboT.add(c);
                }
                if (ProjectType.PROJECT_VISIT.equals(projectType)) {
                    homeBaseCareComboT.add(c);
                }
            }
            List<Map<String, Object>> health = enclosureVisitServe(heathComboT);
            List<Map<String, Object>> homeBaseCare = enclosureVisitServe(homeBaseCareComboT);
            
            org.put(OrgServe.CONSUME_PERSON_TIME, consumeCount);
            org.put(Org.ABOUT, data.getAbout());
            org.put(Area.LONGITUDE, data.getLongitude());
            org.put(Area.LATITUDE, data.getLatitude());
            org.put(Area.ADDRESS, data.getStreet());
            org.put(Org.TEL, data.getTel());
            
            org.put(ProjectServe.CONSULT, consult);
            org.put(ProjectServe.LESSON, lesson);
            org.put(ProjectServe.HEALTH, health);
            org.put(ProjectServe.HOME_BASED_CARE, homeBaseCare);
        }
        
        return org;
    }
    
    /**
     *  服务师模型封装
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 下午2:51:54
     *
     *  @param data
     *  @return
     */
    private Map<String, Object> enclosureServeUser(EmployeeDTO data) {
        int serveUserId = data.getId();
        OrgDTO org = data.getOrg();
        Map<String, Object> serveUser = new HashMap<>();
        serveUser.put(OrgUser.NAME, data.getRealName());
        serveUser.put(OrgUser.PHOTO, data.getPhoto());
        serveUser.put(OrgUser.PRO_NAME, data.getProfessionalName());
        serveUser.put(Org.NAME, org.getOrgName());
        serveUser.put(OrgUser.EXPERT_FIELD, data.getExpertise());
        serveUser.put(OrgUser.ABOUT, data.getAbout());
        
        List<ServeUserVO> orgServeUserList = consultSerivce.listUserConsultServe(serveUserId);
        List<Map<String, Object>> consult = enclosureConsult(orgServeUserList, true);
        
        List<RecommendedLessonDTO> orgLessonList = lessonService.listLessonByServeUserId(serveUserId);
        List<Map<String, Object>> lesson = enclosureLessonServe(orgLessonList, true);
        
        List<RecommendedComboDTO> orgComboList = visitService.listServeUserProjectCombo(serveUserId, null);
        // 健康养生
        List<RecommendedComboDTO> heathComboT = new ArrayList<>();
        // 居家养老
        List<RecommendedComboDTO> homeBaseCareComboT = new ArrayList<>();
        for (RecommendedComboDTO c : orgComboList) {
            int code = c.getCombo().getVisitServe().getProjectType();
            ProjectType projectType = ProjectType.values()[code];
            if (ProjectType.PROJECT_TOSTORE.equals(projectType)) {
                heathComboT.add(c);
            }
            if (ProjectType.PROJECT_VISIT.equals(projectType)) {
                homeBaseCareComboT.add(c);
            }
        }
        List<Map<String, Object>> health = enclosureVisitServe(heathComboT);
        List<Map<String, Object>> homeBaseCare = enclosureVisitServe(homeBaseCareComboT);
        
        serveUser.put(ProjectServe.CONSULT, consult);
        serveUser.put(ProjectServe.LESSON, lesson);
        serveUser.put(ProjectServe.HEALTH, health);
        serveUser.put(ProjectServe.HOME_BASED_CARE, homeBaseCare);
        
        return serveUser;
    }
}
