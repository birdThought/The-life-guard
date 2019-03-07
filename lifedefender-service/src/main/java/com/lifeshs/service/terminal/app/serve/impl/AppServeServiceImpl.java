package com.lifeshs.service.terminal.app.serve.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Area;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.app.HuanXin;
import com.lifeshs.common.constants.app.InvitationCode;
import com.lifeshs.common.constants.app.MeasureSite;
import com.lifeshs.common.constants.app.MeasureSiteDevice;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.app.Org;
import com.lifeshs.common.constants.app.OrgServe;
import com.lifeshs.common.constants.app.OrgServeGroup;
import com.lifeshs.common.constants.app.OrgServeUser;
import com.lifeshs.common.constants.app.OrgUser;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.Serve;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.UserSMS;
import com.lifeshs.common.constants.common.BaseDefine;
import com.lifeshs.common.constants.common.jianKe.OrderStatusEnum;
import com.lifeshs.common.constants.common.order.OrderInfoTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.dao.serve.ServeDao;
import com.lifeshs.entity.org.TServe;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.drugs.OrderProductPO;
import com.lifeshs.po.user.UserElectronicCouponsPO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.data.AreaVO;
import com.lifeshs.pojo.data.MeasureSiteDTO;
import com.lifeshs.pojo.data.MeasureSiteDeviceDTO;
import com.lifeshs.pojo.data.MeasureSiteOpeningTimeDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserSMSRemainDTO;
import com.lifeshs.pojo.order.PaymentOrderDTO;
import com.lifeshs.pojo.order.SMSOrderDTO;
import com.lifeshs.pojo.order.UserServeDO;
import com.lifeshs.pojo.org.OrgDTO;
import com.lifeshs.pojo.org.group.CourseTimeDTO;
import com.lifeshs.pojo.org.group.LessonDTO;
import com.lifeshs.pojo.org.group.LessonGroupOrgUserDTO;
import com.lifeshs.pojo.org.server.OrgServeDTO;
import com.lifeshs.pojo.org.server.ServeDetailDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.data.IMeasureSiteService;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service.org.IServiceOrgService;
import com.lifeshs.service.org.lesson.ILessonGroup;
import com.lifeshs.service.shop.ShopService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service.terminal.app.serve.IAppServeService;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsService;
import com.lifeshs.service1.order.CustomOrderService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.serve.lesson.LessonService;
import com.lifeshs.service1.smsRemind.SmsRemindService;
import com.lifeshs.shop.OrderDTO;
import com.lifeshs.shop.OrderDecomposeDTO;
import com.lifeshs.thirdservice.DrugsService;
import com.lifeshs.utils.ConvertUtil;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.ListUtil;
import com.lifeshs.utils.NumberUtils;

@Service(value = "appServeService")
public class AppServeServiceImpl extends AppNormalService implements IAppServeService {

    @Autowired
    private IServiceOrgService serviceOrgService;

    @Autowired
    private IDataAreaService areaService;

    @Autowired
    private ILessonGroup lessonService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private OrderService orderService1;

    @Autowired
    private CustomOrderService customOrderService;
    
    @Autowired
    private DrugsService drugsService;
    
    @Autowired
	private ShopService shopService;
    
    @Resource(name = "measureSiteService")
    private IMeasureSiteService measureSiteService;

    @Resource(name = "v2LessonService")
    private LessonService lessonV24Service;
    
    @Resource(name = "serveDao")
    private ServeDao serveDao;
    
    @Resource(name = "smsRemindService")
    private SmsRemindService smsRemindService;
    
    @Resource(name = "electronicCouponsService")
    private ElectronicCouponsService couponsService;
    
    @Override
    public JSONObject getServeList(String json) {
        List<TServe> servers = serviceOrgService.getTServers();

        if (servers.size() == 0) {
            return success(NormalMessage.NO_DATA);
        }

        List<Map<String, Object>> dataList = new ArrayList<>();
        for (TServe server : servers) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put(Serve.ID, server.getId());
            dataMap.put(Serve.NAME, server.getName());
            dataMap.put(Serve.CODE, server.getCode());
            dataMap.put(Serve.ABOUT, server.getAbout());
            dataMap.put(Serve.IMAGE, server.getImage());
            dataMap.put(Serve.TYPE, server.getServeType());
            dataMap.put(Serve.CLASSIFY, server.getClassify());
            dataList.add(dataMap);
        }
        return success(dataList);
    }

    @Override
    public JSONObject getRecommendOrgList(String json) {
        AppJSON appJSON = parseAppJSON(json);
        JSONArray mm = appJSON.getData().getMsg();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        Integer count = null;
        if (!mm.isEmpty() || mm.size() > 0) {
            count = mm_0.getIntValue(Normal.COUNT) == 0 ? null : mm_0.getIntValue(Normal.COUNT);
        }

        List<OrgDTO> orgs = serviceOrgService.getRecommendManage(count);

        if (orgs.size() == 0) {
            return success(NormalMessage.NO_DATA);
        }

        List<Map<String, Object>> dataList = new ArrayList<>();
        for (OrgDTO org : orgs) {
            Map<String, Object> dataMap = new HashMap<>();
            // 地区
            AreaVO areaVO = areaService.getAreaNameByCode(org.getProvince(), org.getCity(), org.getDistrict());
            String provinceName = areaVO.getProvinceName();
            String cityName = areaVO.getCityName();
            String districtName = areaVO.getDistrictName();
            // 分类
            List<OrgServeDTO> orgServes = org.getOrgServes();
            /** 机构服务名称 */
            List<String> tags = new ArrayList<>();
            List<String> classifyList = new ArrayList<>();
            for (OrgServeDTO orgServe : orgServes) {
                tags.add(orgServe.getServeType().getName());

                String[] cs = orgServe.getClassify().split(",");
                for (String c : cs) {
                    if (!classifyList.contains(c)) {
                        classifyList.add(c);
                    }
                }
            }
            /** 下级分类 */
            StringBuffer classify = new StringBuffer();
            for (int i = 0; i < classifyList.size(); i++) {
                if (i == 0) {
                    classify.append(classifyList.get(0));
                } else {
                    classify.append("," + classifyList.get(i));
                }
            }

            dataMap.put(Org.ID, org.getId());
            dataMap.put(Org.NAME, org.getOrgName());

            dataMap.put(Org.LOGO, org.getLogo());
            dataMap.put(Org.ABOUT, org.getAbout());
            dataMap.put(Area.PROVINCE, provinceName);
            dataMap.put(Area.CITY, cityName);
            dataMap.put(Area.DISTRICT, districtName);
            dataMap.put(Area.STREET, org.getStreet());
            dataMap.put(OrgServe.CLASSIFY, classify.toString());
            dataMap.put(OrgServe.TAGS, tags);

            dataList.add(dataMap);
        }

        return success(dataList);
    }

    @Override
    public JSONObject getOrgList(String json) {
        AppJSON appJSON = parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String serveCode = mm_0.getString("serveCode"); // TODO 特立独行的 SERVE CODE
        String classify = mm_0.getString(OrgServe.CLASSIFY);
        // 排序，暂不起用
        // String order = mm.getJSONObject(0).getString("order"); // TODO
        String areaCode = mm_0.getString(Area.CODE);
        String searchName = mm_0.getString(Normal.SEARCH_NAME);

        Integer pageIndex = mm_0.getIntValue(Page.INDEX);
        Integer pageSize = mm_0.getIntValue(Page.SIZE);

        if (StringUtils.isBlank(serveCode) || StringUtils.isBlank(classify) || pageIndex == null || pageSize == null) {
            return error(Error.PARAMETER_MISSING);
        }

        if (StringUtils.equals(serveCode, "0")) {
            serveCode = null;
        }
        if (StringUtils.equals(classify, "全部分类")) {
            classify = null;
        }
        if (StringUtils.isBlank(searchName)) {
            searchName = null;
        }

        PaginationDTO<Map<String, Object>> pagination = serviceOrgService.getServeOrgsPageSplit(pageIndex, pageSize,
                serveCode, classify, areaCode, searchName);
        List<Map<String, Object>> services = pagination.getData();
        for (int i = 0; i < services.size(); i++) {
            // 地址
            String province = "";
            String city = "";
            String district = "";
            if (StringUtils.isNotBlank((String) services.get(i).get(Area.PROVINCE))
                    || StringUtils.isNotBlank((String) services.get(i).get(Area.CITY))
                    || StringUtils.isNotBlank((String) services.get(i).get(Area.DISTRICT))) {
                AreaVO areaVO = areaService.getAreaNameByCode((String) services.get(i).get(Area.PROVINCE),
                        (String) services.get(i).get(Area.CITY), (String) services.get(i).get(Area.DISTRICT));
                province = areaVO.getProvinceName();
                city = areaVO.getCityName();
                district = areaVO.getDistrictName();
                services.get(i).put(Area.PROVINCE, province);
                services.get(i).put(Area.CITY, city);
                services.get(i).put(Area.DISTRICT, district);
            }
            // 分类
            String classifyTmp = serviceOrgService.getServeOrgClassify((int) services.get(i).get(Org.ID));
            services.get(i).put(OrgServe.CLASSIFY, classifyTmp);
            // 服务标签
            String tags_s = (String) services.get(i).get("tags");
            List<String> tags = new ArrayList<>();
            for (String tag : tags_s.split(",")) {
                tags.add(tag);
            }
            services.get(i).put(OrgServe.TAGS, tags);
        }

        return success(pagination.getData(), true);
    }

    @Override
    public JSONObject getOrg(String json) {
        AppJSON appJSON = parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int orgId = mm_0.getIntValue(Org.ID);

        OrgDTO org = serviceOrgService.getServeOrgWithServeList(orgId);
        /** 总 消费人次 */
        Integer consumePeronTime = org.getOrgConsumePersonTime();

        Map<String, Object> orgMap = new HashMap<>();
        orgMap.put(Org.ID, org.getId());
        orgMap.put(Org.NAME, org.getOrgName());
        orgMap.put(Org.LOGO, org.getLogo());
        orgMap.put(Org.ABOUT, org.getAbout());
        orgMap.put(Area.PROVINCE, org.getProvince());
        orgMap.put(Area.CITY, org.getCity());
        orgMap.put(Area.DISTRICT, org.getDistrict());
        orgMap.put(Area.STREET, org.getStreet());
        orgMap.put(Org.LONGITUDE, org.getLongitude());
        orgMap.put(Org.LATITUDE, org.getLatitude());
        orgMap.put(Org.DETAIL, org.getDetail());
        // tel字段没有使用，暂时用contactinformation代替
        orgMap.put(Org.TEL, org.getContactInformation());
        orgMap.put(OrgServe.CONSUME_PERSON_TIME, consumePeronTime);

        List<Map<String, Object>> serves = new ArrayList<>();
        List<OrgServeDTO> servesTmp = org.getOrgServes();
        if (servesTmp != null) {
            for (int i = 0; i < servesTmp.size(); i++) {
                Map<String, Object> tmp = new HashMap<>();
                tmp.put(OrgServe.ID, servesTmp.get(i).getId());
                tmp.put(Serve.ID, servesTmp.get(i).getServeType().getId());
                tmp.put(Serve.NAME, servesTmp.get(i).getServeType().getName());
                tmp.put(Serve.CODE, servesTmp.get(i).getServeType().getCode());
                tmp.put(Serve.IMAGE, ""); //servesTmp.get(i).getServeType().getImage());
                tmp.put(Serve.ABOUT, ""); //servesTmp.get(i).getServeType().getAbout());
                serves.add(tmp);
            }
            orgMap.put(Org.SERVE, serves);
        }

        String detail = (String) orgMap.get(Org.DETAIL);
        if (StringUtils.isNotBlank(detail)) {
            detail = ConvertUtil.delHTMLTag(detail);
            orgMap.put(Org.DETAIL, detail);
        }

        return success(orgMap);
    }

    @Override
    public JSONObject getOrgServe(String json) {
        AppJSON appJSON = parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int orgServeId = mm_0.getIntValue(OrgServe.ID);

        Map<String, Object> orgServe = serviceOrgService.getServeDetailByOrgServeId(orgServeId);
        orgServe = orgServeDetailFormatTransform(orgServe);
        int consumePersonTime = serviceOrgService.getOrgServeConsumePersonTime(orgServeId);
        /** 健康咨询添加消费人次 */
        orgServe.put(OrgServe.CONSUME_PERSON_TIME, consumePersonTime);
        /** 修改about */
        Object orgServeAbout = orgServe.get("orgServeAbout");
        orgServe.put(OrgServe.ABOUT, orgServeAbout);
        return success(orgServe);
    }

    @Override
    public JSONObject getLessonGroup(String json) {
        AppJSON appJSON = parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int orgServeId = mm_0.getIntValue(OrgServe.ID);

        List<LessonDTO> lessons = lessonService.listServeLesson(orgServeId);
        List<Map<String, Object>> returnDatas = new ArrayList<>();
        for (LessonDTO lesson : lessons) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put(OrgServeGroup.ID, lesson.getId());
            returnData.put(OrgServeGroup.NAME, lesson.getName());
            returnDatas.add(returnData);
        }
        return success(returnDatas);
    }

    @Override
    public JSONObject getOrgLessonServe(String json) {
        AppJSON appJSON = parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int serveGroupId = mm_0.getIntValue(OrgServeGroup.ID);

        ServeDetailDTO serveDetailDTO = serviceOrgService.getServeDetailByServeGroupId(serveGroupId);
        Map<String, Object> orgServe = ConvertUtil.entityToMap(serveDetailDTO);
        /** 健康课堂添加课堂人数 */
        orgServe = lessonDetailTransform(orgServe, serveGroupId);
        Object description = orgServe.get("description");
        orgServe.put(OrgServeGroup.DESCRIPTION, description);
        orgServe.remove("description");
        return success(orgServe);
    }

    @Override
    public JSONObject getServeUser(String json) {
        AppJSON appJSON = parseAppJSON(json);

        int serveUserId = appJSON.getData().getFirstJSONObject().getIntValue(OrgUser.ID);
        TOrgUser orgUser = terminal.getCommonTrans().getEntity(TOrgUser.class, serveUserId);

        if (orgUser == null) {
            return success(NormalMessage.NO_DATA);
        }
        if (orgUser.getStatus().intValue() != 0) {
            return success(NormalMessage.NO_DATA);
        }

        Map<String, Object> dataMap = new HashMap<>();

        String detail = "";
        if (StringUtils.isNotBlank(orgUser.getDetail())) {
            detail = ConvertUtil.delHTMLTag(orgUser.getDetail());
        }

        dataMap.put(OrgUser.ID, serveUserId);
        dataMap.put(OrgUser.NAME, orgUser.getRealName());
        dataMap.put(OrgUser.PHOTO, orgUser.getPhoto());
        dataMap.put(OrgUser.ABOUT, orgUser.getAbout());
        dataMap.put(OrgUser.DETAIL, detail);

        return success(dataMap);
    }

    @Override
    public JSONObject getClassifyTags(String json) {
        List<Map<String, Object>> tags = serviceOrgService.getClassifyTags();
        return success(tags);
    }

    @Override
    public JSONObject getServiceTags(String json) {
        List<Map<String, Object>> tags = serviceOrgService.getServiceTags();
        return success(tags);
    }

    @Override
    public JSONObject queryOrgList(String json) {
        AppJSON appJSON = parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        String name = mm_0.getString(Normal.SEARCH_NAME);
        String areaCode = mm_0.getString(Area.CODE);
        Integer pageIndex = mm_0.getIntValue(Page.INDEX);
        Integer pageSize = mm_0.getIntValue(Page.SIZE);

        if (StringUtils.isBlank(name)) {
            name = null;
        }

        PaginationDTO pagination = serviceOrgService.getServeOrgsPageSplitForValueName(pageIndex, pageSize, name,
                areaCode);

        List<Map<String, Object>> services = pagination.getData();
        for (int i = 0; i < services.size(); i++) {

            // 地址
            String province = "";
            String city = "";
            String district = "";
            if (StringUtils.isNotBlank((String) services.get(i).get(Area.PROVINCE))
                    || StringUtils.isNotBlank((String) services.get(i).get(Area.CITY))
                    || StringUtils.isNotBlank((String) services.get(i).get(Area.DISTRICT))) {

                AreaVO areaVO = areaService.getAreaNameByCode((String) services.get(i).get(Area.PROVINCE),
                        (String) services.get(i).get(Area.CITY), (String) services.get(i).get(Area.DISTRICT));
                province = areaVO.getProvinceName();
                city = areaVO.getCityName();
                district = areaVO.getDistrictName();
                services.get(i).put(Area.PROVINCE, province);
                services.get(i).put(Area.CITY, city);
                services.get(i).put(Area.DISTRICT, district);
            }
            // 分类
            String classify = serviceOrgService.getServeOrgClassify((int) services.get(i).get(Org.ID));
            services.get(i).put(OrgServe.CLASSIFY, classify);
        }

        return success(pagination.getData(), true);
    }

    @Override
    public JSONObject getServeUserByHuanxinAcount(String json) {
        return error(Error.ABOLISHED_METHOD);
        
//        AppJSON appJSON = parseAppJSON(json);
//        int userId = appJSON.getData().getUserId();
//
//        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
//        String userNames = mm_0.getString(HuanXin.USERNAMES);
//        String groupUserNames = mm_0.getString(HuanXin.GROUP_USERNAME);
//        List<String> names = new ArrayList<>();
//        List<String> huanxinGroupUserNames = new ArrayList<>();
//
//        if (StringUtils.isNotBlank(userNames)) {
//            names = JSONArray.parseArray(userNames, String.class);
//        }
//        if (StringUtils.isNotBlank(groupUserNames)) {
//            huanxinGroupUserNames = JSONArray.parseArray(groupUserNames, String.class);
//        }
//
//        List<Map<String, Object>> returnDatas = new ArrayList<>();
//        if (names.size() > 0) {
//            names = ListUtil.removeRepeatElement(names, String.class);
//            List<Map<String, Object>> users = serviceOrgService.getServeUserByHuanxinAcount(names, userId);
//            returnDatas.addAll(users);
//        }
//        if (huanxinGroupUserNames.size() > 0) {
//            huanxinGroupUserNames = ListUtil.removeRepeatElement(huanxinGroupUserNames, String.class);
//
//            List<Map<String, Object>> groups = new ArrayList<>();
//            List<LessonDTO> lessonDTOs = lessonService.listLesson(huanxinGroupUserNames, userId);
//            for (LessonDTO lessonDTO : lessonDTOs) {
//                Map<String, Object> group = new HashMap<>();
//                /** 课堂默认code为02 */
//                group.put(Serve.CODE, "02");
//                group.put(HuanXin.USERNAME, lessonDTO.getHuanxinId());
//                group.put(OrgServeGroup.PHTOTO, lessonDTO.getPhoto());
//                group.put(OrgServeGroup.ID, lessonDTO.getId());
//                group.put(OrgServeGroup.NAME, lessonDTO.getName());
//                group.put(OrgServeGroup.USER_COUNT, lessonDTO.getLessonUserCount());
//                group.put(OrgServeGroup.SILENCE, lessonDTO.getSilence());
//                groups.add(group);
//            }
//            returnDatas.addAll(groups);
//        }
//        if (returnDatas.size() > 0) {
//            /** 把查询的环信ID合并到一起，标识未知账号 */
//            names.addAll(huanxinGroupUserNames);
//            returnDatas = setUnknowAccountForNotExist(names, returnDatas);
//        }
//        return success(returnDatas);
    }

    @Override
    public JSONObject getGroupServeInfo(String json) {
        AppJSON appJSON = parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String serveGroupId = mm_0.getString(OrgServeGroup.ID);

        if (serveGroupId.length() > 9) {
            return success("群组ID长度没有那么长");
        }
        int groupId = Integer.valueOf(serveGroupId);

        ServeDetailDTO serveDetailDTO = serviceOrgService.getServeDetailByServeGroupId(groupId);
        // 扫一扫添加找不到该编码提示
        if (serveDetailDTO == null) {
            return success(NormalMessage.CODE_NOT_FOUND);
        }

        String code = serveDetailDTO.getCode();

        Map<String, Object> orgServe = new HashMap<>();
        orgServe.put(OrgServe.ID, serveDetailDTO.getOrgServeId());
        orgServe.put(Serve.ID, serveDetailDTO.getServeId());
        orgServe.put(Serve.NAME, serveDetailDTO.getName());
        orgServe.put(Serve.CODE, serveDetailDTO.getCode());
        orgServe.put(Serve.IMAGE, serveDetailDTO.getImage());
        orgServe.put(Serve.TYPE, serveDetailDTO.getServeType());
        orgServe.put(OrgServe.CLASSIFY, serveDetailDTO.getClassify());
        orgServe.put(OrgServe.HAS_FREE, serveDetailDTO.getHasFree());
        orgServe.put(OrgServe.HAS_TIME, serveDetailDTO.getHasTime());
        orgServe.put(OrgServe.TIME_PRICE, serveDetailDTO.getTimePrice());
        orgServe.put(OrgServe.HAS_MONTH, serveDetailDTO.getHasMonth());
        orgServe.put(OrgServe.MONTH_PRICE, serveDetailDTO.getMonthPrice());
        orgServe.put(OrgServe.HAS_YEAR, serveDetailDTO.getHasYear());
        orgServe.put(OrgServe.YEAR_PRICE, serveDetailDTO.getYearPrice());
        orgServe.put(OrgServe.ABOUT, serveDetailDTO.getOrgServeAbout());

        orgServe = orgServeDetailFormatTransform(orgServe);
        /** 健康课堂 */
        if ("02".equals(code)) {
            LessonDTO lesson = lessonService.getLesson(groupId);
            int userCount = lesson.getLessonUserCount();
            orgServe.put(OrgServeGroup.USER_COUNT, userCount);
            orgServe.put(OrgServeGroup.NAME, lesson.getName());
        } else {
            /** 健康咨询添加消费人次 */
            int consumePersonTime = serviceOrgService.getOrgServeConsumePersonTime(serveDetailDTO.getOrgServeId());
            orgServe.put(OrgServe.CONSUME_PERSON_TIME, consumePersonTime);
        }

        orgServe.put(OrgServeGroup.ID, serveGroupId);
        return success(orgServe);
    }

    @Override
    public JSONObject getImages(String json) {
        Map<String, Object> images = new HashMap<>();
        List<String> banners = new ArrayList<>();
        banners.add("http://www.lifeshs.com/static/images/project/banner01.jpg");
        banners.add("http://www.lifeshs.com/static/images/project/banner02.jpg");
        images.put("serviceBanner", banners);
        JSONObject root = (JSONObject) JSONObject.toJSON(images);
        return success(root);
    }

    /**
     * orgServeDetail数据处理
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月22日 上午10:40:50
     *
     * @param orgServe
     * @return
     */
    private Map<String, Object> orgServeDetailFormatTransform(Map<String, Object> orgServe) {
        if (orgServe == null) {
            return null;
        }

        int orgServeId = (int) orgServe.get("orgServeId");
        List<Map<String, Object>> serveUsers = serviceOrgService.getServeUserByServeId(orgServeId);
        for (int i = 0; i < serveUsers.size(); i++) {
            // HTML处理
            String detail = (String) serveUsers.get(i).get("detail");
            if (StringUtils.isNotBlank(detail)) {
                detail = ConvertUtil.delHTMLTag(detail);
                serveUsers.get(i).put(OrgUser.DETAIL, detail);
            }
        }
        orgServe.put(OrgServeUser.USERS, serveUsers);
        return orgServe;
    }

    /**
     * 健康课堂信息处理
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月7日 下午3:05:56
     *
     * @param orgServe
     * @param serveGroupId
     * @return
     */
    private Map<String, Object> lessonDetailTransform(Map<String, Object> orgServe, Integer serveGroupId) {
        LessonDTO lesson = lessonService.getLesson(serveGroupId);
        int userCount = lesson.getLessonUserCount();
        orgServe.put(OrgServeGroup.USER_COUNT, userCount);

        /** 机构用户信息 */
        List<Map<String, Object>> serveUsers = new ArrayList<>();
        for (LessonGroupOrgUserDTO orgUser : lesson.getOrgUsers()) {
            String detail = orgUser.getDetail();
            if (StringUtils.isNotBlank(detail)) {
                detail = ConvertUtil.delHTMLTag(detail);
            }

            Map<String, Object> serveUser = new HashMap<>();
            serveUser.put(OrgUser.ID, orgUser.getId());
            serveUser.put(OrgUser.NAME, orgUser.getRealName());
            serveUser.put(OrgUser.PHOTO, orgUser.getPhoto());
            serveUser.put(OrgUser.ABOUT, orgUser.getAbout());
            serveUser.put(OrgUser.DETAIL, detail);
            serveUsers.add(serveUser);
        }
        orgServe.put(OrgServeUser.USERS, serveUsers);
        return orgServe;
    }

    /**
     * 将未知的账号设置提示“未知账号”
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月27日 下午1:31:33
     *
     * @param huanxinUserNames
     * @param users
     * @return
     */
    private List<Map<String, Object>> setUnknowAccountForNotExist(List<String> huanxinUserNames,
            List<Map<String, Object>> users) {
        List<String> name_tmp = new ArrayList<>();

        for (Map<String, Object> user : users) {
            name_tmp.add((String) user.get(HuanXin.USERNAME));
        }

        for (String name : huanxinUserNames) {
            if (!name_tmp.contains(name)) {
                Map<String, Object> user_blank = new HashMap<>();
                user_blank.put(HuanXin.USERNAME, name);
                user_blank.put(OrgUser.NAME, "未知账号");
                user_blank.put(OrgUser.PHOTO, "未知账号");
                user_blank.put(OrgUser.TYPE, "未知账号");
                user_blank.put(OrgUser.ID, "-1");

                users.add(user_blank);
            }
        }

        return users;
    }

    @Override
    public JSONObject getLessonDetail(String json) {
        return error(Error.ABOLISHED_METHOD);
//        AppJSON appJSON = parseAppJSON(json);
//        int groupId = appJSON.getData().getFirstJSONObject().getIntValue(OrgServeGroup.ID);
//        LessonDTO lessonDTO = lessonService.getLesson(groupId);
//        /** 开课时间 */
//        List<Map<String, Object>> courseTimes = new ArrayList<>();
//        List<CourseTimeDTO> courseTimeDTOs = lessonDTO.getCourseTime();
//        for (CourseTimeDTO courseTimeDTO : courseTimeDTOs) {
//            Map<String, Object> courseTime = new HashMap<>();
//            courseTime.put(OrgServeGroup.START_TIME, courseTimeDTO.getStartTime());
//
//            int weekday = MAppNormalService.weeksToInt(courseTimeDTO.getWeeks());
//            courseTime.put(OrgServeGroup.WEEK, weekday);
//            courseTimes.add(courseTime);
//        }
//        /** 遍历群组用户 */
//        List<Map<String, Object>> users = new ArrayList<>();
//        for (LessonGroupOrgUserDTO orgUser : lessonDTO.getOrgUsers()) {
//            Map<String, Object> user = new HashMap<>();
//            user.put(OrgServeGroupUser.TYPE, orgUser.getGroupUserType());
//            user.put(OrgServeGroupUser.PHOTO, orgUser.getPhoto());
//            user.put(OrgServeGroupUser.NAME, orgUser.getRealName());
//            user.put(OrgServeGroupUser.ID, orgUser.getId());
//            user.put(OrgServeGroupUser.CODE, orgUser.getUserCode());
//            users.add(user);
//        }
//        for (UserOrderDTO member : lessonDTO.getUserOrders()) {
//            Map<String, Object> user = new HashMap<>();
//            user.put(OrgServeGroupUser.TYPE, 2);
//            user.put(OrgServeGroupUser.PHOTO, member.getUserPhoto());
//            user.put(OrgServeGroupUser.NAME, member.getUserRealName());
//            user.put(OrgServeGroupUser.ID, member.getUserId());
//            user.put(OrgServeGroupUser.CODE, member.getUserCode());
//            users.add(user);
//        }
//
//        Map<String, Object> returnData = new HashMap<>();
//        returnData.put(OrgServeGroup.HUANXIN_ID, lessonDTO.getHuanxinId());
//        returnData.put(OrgServeGroup.NAME, lessonDTO.getName());
//        returnData.put(OrgServeGroup.DESCRIPTION, lessonDTO.getDescription());
//        returnData.put(OrgServeGroup.COURSE_TIME, courseTimes);
//        returnData.put(OrgServeGroupUser.USERS, users);
//
//        return success(returnData);
    }

    @Override
    public JSONObject addOrder(String json) {
        AppJSON appJSON = parseAppJSON(json);
        UserDTO userDTO = appJSON.getAopData().getUser();
        int userId = userDTO.getId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int orgServeId = mm_0.getIntValue(OrgServe.ID);
        int priceType = mm_0.getIntValue(Order.PRICE_TYPE);
        int count = mm_0.getIntValue(Order.COUNT);
        String body = mm_0.getString(Order.BODY);
        String subject = mm_0.getString(Order.SUBJECT);
        String serveGroupId = (String) mm_0.get(OrgServeGroup.ID);

        Integer serveGroupId_i = null;
        boolean isServeGroupIdBlank = StringUtils.isBlank(serveGroupId);
        if (!isServeGroupIdBlank) {
            serveGroupId_i = Integer.valueOf(serveGroupId);
        }

        ServiceMessage addOrderSM = orderService.addServeOrder(userId, orgServeId,
                AlipayService.createOrderNumber(userDTO.getUserCode()), priceType, count, subject, body,
                priceType == 0 ? 3 : 1, serveGroupId_i);
        if (addOrderSM.isSuccess()) {
            Map<String, Object> tmp = new HashMap<>();
            Map<String, Object> attr = addOrderSM.getAttributes();
            tmp.put(Order.ID, attr.get("id"));
            tmp.put(Order.NUMBER, attr.get("number"));
            return success(tmp);
        } else {
            return success(addOrderSM.getMessage());
        }
    }

    @Override
    public JSONObject finishOrder(String json) {
        String msg = Error.ABOLISHED_METHOD;
        return error(msg);
    }

    @Override
    public JSONObject getOrderList(String json) {
        AppJSON appJSON = parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int userId = appJSON.getData().getUserId();

        int pageIndex = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        List<Integer> statusList = JSONArray.parseArray(mm_0.getString(Normal.GET_TYPE), Integer.class);

        if (statusList.contains(0) || statusList.size() == 0) {
            statusList = null;
        } else {
            statusList = ListUtil.removeRepeatElement(statusList, Integer.class);
        }

        List<Map<String, Object>> orders = orderService
                .getOrderListWithPageSplit(statusList, null, userId, pageIndex, pageSize).getData();
        if (orders.size() == 0) {
            return success(NormalMessage.NO_DATA);
        }

        for (Map<String, Object> order : orders) {
            String daysRemaining = null;

            Object startDate = order.get("startDate");
            int status_i = (int) order.get("status");
            int chargeMode = (int) order.get("chargeMode");
            int number = (int) order.get("number");
            String code = (String) order.get("code");

            String startDate_s = "";
            // 开始日期，如果是null表示订单还未生效，需要主动录入new Date()
            if (startDate != null) {
                startDate_s = DateTimeUtilT.date((Date) startDate);
            } else {
                startDate_s = DateTimeUtilT.date(new Date());
            }
            order.put(Order.START_DATE, startDate_s);
            // 结束日期，需要判断订单的chargeMode类型，如果是3（已生效）
            Object endDate = order.get("endDate");
            if (endDate != null) {
                daysRemaining = DateTimeUtilT.calculateDayInterval(new Date(), (Date) endDate) + "";
                if (Integer.valueOf(daysRemaining) < 0)
                    daysRemaining = "0";
            } else if (status_i == 3) { // endDate == null && chargeMode == 3
                daysRemaining = "∞";
            } else {
                // endDate == null && chargeMode != 3
                // 以上条件表示该订单未生效，需要根据购买的数量，以及购买的是按月或者按年来计算实际剩余天数
                switch (chargeMode) {
                case 0:
                    // 免费服务status默认为3，不会进入该分支
                    break;
                case 2:
                    // 按月收费
                    daysRemaining = (number * 30) + "";
                    break;
                case 3:
                    // 按年收费
                    daysRemaining = (number * 365) + "";
                    break;
                }
            }
            order.put(Order.DAYS_REMAINING, daysRemaining);
            order.remove("endDate");

            order.remove("userId");
            order.remove("recard");

            order.put(Order.PRICE, NumberUtils.changeY2F(((Double) order.get("price")).toString()));
            order.put(Normal.IMAGE, order.get("image"));
            order.remove("logo");

            /** 隐藏一些相关信息 */
            /** 1.订单状态不是有效 2.服务类型不是健康课堂 */
            if (status_i != 3 || !"02".equals(code)) {
                order.remove("serveGroupId");
                order.remove("huanxinId");
            } else {
                LessonDTO lessonDTO = lessonService.getLesson((int) order.get("serveGroupId"));
                int userCount = lessonDTO.getLessonUserCount();
                order.put(OrgServeGroup.USER_COUNT, userCount);
                order.put(OrgServeGroup.SILENCE, lessonDTO.getSilence());
                order.put(OrgServeGroup.NAME, lessonDTO.getName());
            }
        }
        return success(orders);
    }

    @Override
    public JSONObject getOrder(String json) {
        AppJSON appJSON = parseAppJSON(json);
        Integer userId = appJSON.getData().getUserId();
        JSONObject msg_0 = appJSON.getData().getFirstJSONObject();
        int orderId = msg_0.getIntValue(Order.ID);
        Map<String, Object> order = orderService.getOrder(userId, orderId);
        if (order == null) {
            return success(NormalMessage.NO_DATA);
        }

        String daysRemaining = null;

        Object startDate = order.get("startDate");
        int status_i = (int) order.get("status");
        int chargeMode = (int) order.get("chargeMode");
        int number = (int) order.get("number");
        String code = (String) order.get("code");
        Integer groupId = null;
        if (order.get("serveGroupId") != null) {
            groupId = (Integer) order.get("serveGroupId");
        }

        String startDate_s = "";
        if (startDate != null) {
            startDate_s = DateTimeUtilT.date((Date) startDate);
        } else {
            startDate_s = DateTimeUtilT.date(new Date());
        }
        order.put(Order.START_DATE, startDate_s);

        // 结束日期，需要判断订单的chargeMode类型，如果是3（已生效）
        Object endDate = order.get("endDate");
        if (endDate != null) {
            daysRemaining = DateTimeUtilT.calculateDayInterval(new Date(), (Date) endDate) + "";
            if (Integer.valueOf(daysRemaining) < 0)
                daysRemaining = "0";
        } else if (status_i == 3) { // endDate == null && chargeMode == 3
            daysRemaining = "∞";
        } else {
            // endDate == null && chargeMode != 3
            // 以上条件表示该订单未生效，需要根据购买的数量，以及购买的是按月或者按年来计算实际剩余天数
            switch (chargeMode) {
            case 0:
                // 免费服务status默认为3，不会进入该分支
                break;
            case 2:
                // 按月收费
                daysRemaining = (number * 30) + "";
                break;
            case 3:
                // 按年收费
                daysRemaining = (number * 365) + "";
                break;
            }
        }
        order.put(Order.DAYS_REMAINING, daysRemaining);
        order.remove("endDate");

        order.put(Order.PRICE, NumberUtils.changeY2F(((Double) order.get("price")).toString()));

        String newImage = Normal.PHOTO_PREFIX + order.get("image");
        order.put(Normal.IMAGE, newImage);

        order.remove("userId");
        order.remove("recard");

        String newLogo = (String) order.get("logo");
        if (StringUtils.isNotBlank(newLogo)) {
            newLogo = Normal.PHOTO_PREFIX + newLogo;
        }
        order.put(Org.LOGO, newLogo);

        /** 健康课堂 */
        Integer status = (Integer) order.get("status");
        if ("02".equals(code) && (status.intValue() == 3 || status.intValue() == 1)) {
            LessonDTO lessonDTO = lessonService.getLesson(groupId);
            int userCount = lessonDTO.getLessonUserCount();
            order.put(OrgServeGroup.USER_COUNT, userCount);
            order.put(OrgServeGroup.SILENCE, lessonDTO.getSilence());
            order.put(OrgServeGroup.NAME, lessonDTO.getName());
        }

        return success(order);
    }

    @Override
    public JSONObject cancelOrder(String json) {
        String msg = "";

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);
        String orderNumber = mm_0.getString(Order.NUMBER);

        boolean flag = orderService.cancelOrder(userId, orderNumber);
        if (!flag) {
            msg = Error.FAIL_ACTION;
            return error(msg);
        }

        return success();
    }

    @Override
    public JSONObject getUserServeList(String json) {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);

        List<UserServeDO> userServes = orderService.getUserServeDOList(userId);
        if (userServes.size() == 0) {
            return success(NormalMessage.NO_DATA);
        }
        List<Map<String, Object>> serves = new ArrayList<>();
        for (int i = 0; i < userServes.size(); i++) {
            Map<String, Object> serve = new HashMap<>();
            String daysRemaining_s = "";
            int timesRemaining = 0;
            Date endDate = userServes.get(i).getEndDate();
            if (userServes.get(i).getChargeMode() != 1) {
                // 按时间购买
                if (endDate == null) {
                    daysRemaining_s = "999";
                } else {
                    daysRemaining_s = DateTimeUtilT.calculateDayInterval(new Date(), endDate) + "";
                }
            } else {
                // 按次购买
                timesRemaining = userServes.get(i).getTimesRemaining();
            }

            List<Map<String, Object>> courseTimes = new ArrayList<>();
            if ("02".equals(userServes.get(i).getServeCode())) {
                LessonDTO lesson = lessonService.getLesson(userServes.get(i).getServeGroupId());
                for (CourseTimeDTO courseTimeDTO : lesson.getCourseTime()) {
                    Map<String, Object> courseTime = new HashMap<>();
                    int weekday = MAppNormalService.weeksToInt(courseTimeDTO.getWeeks());
                    courseTime.put(OrgServeGroup.WEEK, weekday);
                    courseTime.put(OrgServeGroup.START_TIME, courseTimeDTO.getStartTime());
                    courseTimes.add(courseTime);
                }
                int userCount = lesson.getLessonUserCount();

                serve.put(OrgServeGroup.COURSE_TIME, courseTimes);
                serve.put(OrgServeGroup.USER_COUNT, userCount);
                serve.put(OrgServeGroup.SILENCE, lesson.getSilence());
            }

            serve.put(OrgServe.NAME, userServes.get(i).getServeName());
            serve.put(Order.DAYS_REMAINING, daysRemaining_s);
            serve.put(Order.TIMES_REMAINING, timesRemaining);
            serve.put(Org.NAME, userServes.get(i).getOrgName());
            serve.put(Org.LOGO, userServes.get(i).getLogo());
            serve.put(OrgServe.ID, userServes.get(i).getOrgServeId());
            serve.put(Order.CHARGE_MODE, userServes.get(i).getChargeMode());
            serve.put(OrgServeGroup.HUANXIN_ID, userServes.get(i).getHuanxinId());
            serve.put(Serve.CODE, userServes.get(i).getServeCode());
            serve.put(OrgServeGroup.ID, userServes.get(i).getServeGroupId());
            serve.put(OrgServeGroup.NAME, userServes.get(i).getServeGroupName());
            serves.add(serve);
        }

        return success(serves);
    }

    @Override
    public JSONObject getUserServeDetail(String json) {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);
        int orgServeId = mm_0.getIntValue(OrgServe.ID);

        Map<String, Object> detail = orderService.getUserServeDetail(userId, orgServeId);
        if (detail == null || detail.isEmpty()) {
            return success(NormalMessage.NO_DATA);
        }
        // remove extra data
        // detail.remove(AppOrder.ID);
        detail.remove(Org.ID);
        // detail.remove(AppOrder.END_DATE);
        detail.remove(OrgServe.ID);
        // logo
        String logo = (String) detail.get(Org.LOGO);
        if (StringUtils.isNotBlank(logo)) {
            logo = Normal.PHOTO_PREFIX + logo;
            detail.put(Org.LOGO, logo);
        }
        // serveUser
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> serveUsers = (List<Map<String, Object>>) detail.get("serveUsers");

        for (int i = 0; i < serveUsers.size(); i++) {
            String photo = (String) serveUsers.get(i).get(OrgUser.PHOTO);
            if (StringUtils.isNotBlank(photo)) {
                photo = Normal.PHOTO_PREFIX + photo;
            }
            serveUsers.get(i).put(OrgUser.PHOTO, photo);

            String serveUserDetail = (String) serveUsers.get(i).get("detail");
            if (StringUtils.isNotBlank(serveUserDetail)) {
                serveUserDetail = ConvertUtil.delHTMLTag(serveUserDetail);
                serveUsers.get(i).put(Org.DETAIL, serveUserDetail);
            }
        }

        detail.put(OrgServeUser.USERS, serveUsers);

        return success(detail);
    }

    private static final Logger logger = Logger.getLogger(AppServeServiceImpl.class);
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = BaseException.class)
    public JSONObject getOrderInfo(String json) throws BaseException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        Integer orderId = mm_0.getInteger(Order.ID);
        String orderNumber = mm_0.getString(Order.NUMBER);
        
        Integer orderType = mm_0.getInteger(Order.ORDER_TYPE);
        
        if (orderId == null && orderNumber == null) {
            return error("获取支付签名失败");
        }

        String orderInfo = null;
        if (OrderInfoTypeEnum.PRIVATE.getValue() == orderType) {
            orderInfo = customOrderService.finishOrder(orderNumber);
        } else {
            // 电子券id
            Integer couponsId = mm_0.getInteger("couponId");

            OrderPO order = null;
            if (orderNumber != null) {
                order = orderService1.getOrderByOrderNumber(orderNumber);
            }
            if (orderId != null) {
                 order = orderService1.getOrderDetail(orderId);
            }
            Map<String, String> extraData = new HashMap<>();
            if (couponsId != null && order != null) {
                String serveCode = order.getServeType().getFirstCode();
                String projectCode = order.getProjectCode();
                int serveItemId = order.getServeItemId();
                
                // 用户使用电子券需要把电子券修改为使用中的状态
                couponsService.useCoupons(couponsId, userId, serveCode, projectCode, serveItemId);
                // 同时把电子券的id加入到支付签名中
                extraData.put("couponsId", String.valueOf(couponsId));
                // 修改订单需要支付费用
                UserElectronicCouponsPO coupon = couponsService.getCoupons(couponsId);
                int couponPrice = coupon.getPrice();
                int orderCharge = order.getCharge();
                order.setCharge(orderCharge - couponPrice);
            }
            if (order != null) {
                orderInfo = orderService1.getOrderAlipaySignInfo(order, Normal.APP_RECHARGE_NOTIFY_URL, extraData);
            }
        }

        Map<String, Object> orderInfoMap = new HashMap<>();
        orderInfoMap.put(Order.INFO, orderInfo);
        return success(orderInfoMap);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = BaseException.class)
    public JSONObject getOrderInfoNew(String json) throws BaseException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        Integer orderId = mm_0.getInteger(Order.ID);
        String orderNumber = mm_0.getString(Order.NUMBER);
        Integer orderType = mm_0.getInteger(Order.ORDER_TYPE);
        
        if (orderId == null && orderNumber == null) {
            return error("获取支付签名失败");
        }

        String orderInfo = null;
        if (OrderInfoTypeEnum.PRIVATE.getValue() == orderType) {
            orderInfo = customOrderService.finishOrder(orderNumber);
        }else if (OrderInfoTypeEnum.GRUDS.getValue() == orderType){
            Map<String,String> extraData = new HashMap<>();
            List<com.lifeshs.po.drugs.OrderPO> orderList = drugsService.getLocalOrderByParam(orderNumber);
            if(orderList.size() > 0){
                int total = 0;
                com.lifeshs.po.drugs.OrderPO order = orderList.get(0);
                int money = Integer.parseInt(mm_0.getString(Order.MONEY));
                //List<OrderProductPO> productList = drugsService.getOrderProductList(orderNumber);
                for(OrderProductPO p : order.getOrderProductList()){
                    total += p.getActualPrice() * p.getAmount();
                }
                if (OrderStatusEnum.UNPAID.getValue() != order.getStatus()){
                    throw new OperationException("该订单状态为不可支付", ErrorCodeEnum.FAILED);
                }
                if(money != total){
                    throw new OperationException("金额不匹配 单位 分 (" + money + "=>" + total,ErrorCodeEnum.FAILED);    
                }
            
                PaymentOrderDTO paymentOrder = new PaymentOrderDTO();
                paymentOrder.setOrderNumber(orderNumber);
                paymentOrder.setCharge(money);
                paymentOrder.setOrderType(orderType);
                paymentOrder.setStatus(order.getStatus());
                
                Integer addressId = mm_0.getInteger(Order.ADDRESSID);
                int payType = mm_0.getInteger(Order.PAY_TYPE);
                
                extraData.put(Order.ADDRESSID, String.valueOf(addressId));
                extraData.put(Order.PAYMENTTYPE, String.valueOf(payType));
                orderInfo = orderService1.getOrderAlipaySignInfoNew(paymentOrder, Normal.APP_RECHARGE_NOTIFY_URL, extraData);
            }
        }else if (OrderInfoTypeEnum.SHOP.getValue() == orderType){
        	
        	Map<String,String> extraData = new HashMap<>();
            List<OrderDTO> orderList = shopService.getLocalOrderByParam(orderNumber);
            if(orderList.size() > 0){
                int total = 0;
                OrderDTO order = orderList.get(0);
                int money = Integer.parseInt(mm_0.getString(Order.MONEY));
                List<OrderDecomposeDTO> productList = shopService.getOrderDecList(orderNumber);
                for(OrderDecomposeDTO p : productList){
                    total += p.getPrice() * p.getNum();  
                }
                if (OrderStatusEnum.UNPAID.getValue() != order.getStatus()){
                    throw new OperationException("该订单状态为不可支付", ErrorCodeEnum.FAILED);
                }
                if(money != total){
                    throw new OperationException("金额不匹配 单位 分 (" + money + "=>" + total,ErrorCodeEnum.FAILED);    
                }
            
                PaymentOrderDTO paymentOrder = new PaymentOrderDTO();
                paymentOrder.setOrderNumber(orderNumber);
                paymentOrder.setCharge(money);
                paymentOrder.setOrderType(orderType);
                paymentOrder.setStatus(order.getStatus());
                
                Integer addressId = mm_0.getInteger(Order.ADDRESSID);
                int payType = mm_0.getInteger(Order.PAY_TYPE);
                
                extraData.put(Order.ADDRESSID, String.valueOf(addressId));
                extraData.put(Order.PAYMENTTYPE, String.valueOf(payType));
                orderInfo = shopService.getOrderAlipaySignInfo(paymentOrder, Normal.APP_RECHARGE_NOTIFY_URL, extraData);
        	
            }
    	
        	
        }else {
            // 电子券id
            Integer couponsId = mm_0.getInteger("couponId");

            OrderPO order = null;
            if (orderNumber != null) {
                order = orderService1.getOrderByOrderNumber(orderNumber);
            }
            if (orderId != null) {
                 order = orderService1.getOrderDetail(orderId);
            }
            Map<String, String> extraData = new HashMap<>();
            if (couponsId != null && order != null) {
                String serveCode = order.getServeType().getFirstCode();
                String projectCode = order.getProjectCode();
                int serveItemId = order.getServeItemId();
                
                // 用户使用电子券需要把电子券修改为使用中的状态
                couponsService.useCoupons(couponsId, userId, serveCode, projectCode, serveItemId);
                // 同时把电子券的id加入到支付签名中
                extraData.put("couponsId", String.valueOf(couponsId));
                // 修改订单需要支付费用
                UserElectronicCouponsPO coupon = couponsService.getCoupons(couponsId);
                int couponPrice = coupon.getPrice();
                int orderCharge = order.getCharge();
                order.setCharge(orderCharge - couponPrice);
            }
            if (order != null) {
                orderInfo = orderService1.getOrderAlipaySignInfo(order, Normal.APP_RECHARGE_NOTIFY_URL, extraData);
            }
        }

        Map<String, Object> orderInfoMap = new HashMap<>();
        orderInfoMap.put(Order.INFO, orderInfo);
        return success(orderInfoMap);
    }

    @Override
    public JSONObject checkServeValid(String json) {

        return error(Error.ABOLISHED_METHOD);
//        JSONObject root = JSONObject.parseObject(json);
//        JSONObject data = root.getJSONObject(Normal.DATA);
//        int userId = data.getIntValue(User.ID);
//        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
//
//        JSONObject mm_0 = mm.getJSONObject(0);
//        int orgUserId = mm_0.getIntValue(OrgUser.ID);
//        boolean isValid = orderService.checkServeValid(userId, orgUserId);
//        Map<String, String> returnData = new HashMap<>();
//        if (isValid) {
//            returnData.put(OrgServe.IS_SERVE_INVALID, "0");
//        } else {
//            returnData.put(OrgServe.IS_SERVE_INVALID, "1");
//        }
//        return success(returnData);
    }

    @Override
    public JSONObject reduceServeTime(String json) {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);
        int orgServeId = mm_0.getIntValue(OrgServe.ID);

        boolean isSuccessReduce = orderService.reduceServeTime(userId, orgServeId);
        if (isSuccessReduce) {
            return success();
        } else {
            return error(Error.FAIL_ACTION);
        }
    }

    @Override
    public JSONObject outLessonGroup(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        int groupId = appJSON.getData().getFirstJSONObject().getIntValue(OrgServeGroup.ID);

        try {
            lessonV24Service.removeUser(groupId, userId);
        } catch (OperationException e) {
            return error(e.getMessage(), e.getCode());
        }
        return success();
    }

    @Override
    public JSONObject addSMSOrder(String json) {
        AppJSON appJson = parseAppJSON(json);
        int userId = appJson.getData().getUserId();
        JSONObject mm_0 = appJson.getData().getFirstJSONObject();
        
        int number = mm_0.getIntValue("number");
        
        if (number <= 0) {
            return success("购买数量不正确");
        }
        
        String userCode = appJson.getAopData().getUser().getUserCode();
        String orderNumber = AlipayService.createOrderNumber(userCode);
        String subject = "短信充值";
        Float charge = NumberUtils.multiply(BaseDefine.SMS_PRICE, Integer.valueOf(number).floatValue(), 2);
        
        orderService.addSMSOrder(orderNumber, userId, Float.valueOf(BaseDefine.SMS_PRICE * 100).intValue(), number, subject, "充值" + (0.1 * number) + "元");
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("orderNumber", orderNumber);
        returnData.put("createDate", new Date());
        returnData.put("subject", subject);
        returnData.put("charge", charge);
        returnData.put("number", number);
        
        return success(returnData);
    }
    
    @Override
    public JSONObject getSMSRemain(String json) {
        AppJSON appJson = parseAppJSON(json);
        int userId = appJson.getData().getUserId();
        
        long remainNumber = 0;
        UserSMSRemainDTO smsRemainDTO = smsRemindService.getMemberSmsRemind(userId);
        if (smsRemainDTO != null) {
            remainNumber = smsRemainDTO.getRemainNumber();
        }
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(UserSMS.REMAIN_NUMBER, remainNumber);
        returnData.put(UserSMS.PRICE, BaseDefine.SMS_PRICE);
        
        return success(returnData);
    }

    @Override
    public JSONObject entrySMSInvication(String json) throws OperationException {
        AppJSON appJson = parseAppJSON(json);
        int userId = appJson.getData().getUserId();
        JSONObject mm_0 = appJson.getData().getFirstJSONObject();
        String code = mm_0.getString(InvitationCode.CODE);
        orderService.addSmsInvitationCode(code, userId);
        return success();
    }

    @Override
    public JSONObject getSMSRechargeRecordList(String json) {
        AppJSON appJson = parseAppJSON(json);
        int userId = appJson.getData().getUserId();
        
        JSONObject mm_0 = appJson.getData().getFirstJSONObject();
        Integer index = mm_0.getInteger(Page.INDEX);
        Integer size = mm_0.getInteger(Page.SIZE);
        
        if (index == null) {
            index = 1;
        }
        if (size == null) {
            size = 5;
        }
        
        List<SMSOrderDTO> orders = orderService.listSMSOrder(userId, index, size).getData();
        if (orders.size() == 0) {
            return success(NormalMessage.NO_DATA);
        }
        
        List<Map<String, Object>> returnDatas = new ArrayList<>();
        for (SMSOrderDTO order : orders) {
            double charge = NumberUtils.changeF2Y(String.valueOf(order.getCharge()));
            String title = "-" + charge;
            String remark = "充值" + order.getNumber() + "条";
            String logo = "alipay";
            if (order.getOrderFlow() == null) {
                logo = "telecom";
                title = order.getSubject();
                remark = order.getBody();
            }
            
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("createDate", order.getCreateDate());
            returnData.put("logoType", logo);
            returnData.put("title", title);
            returnData.put("remark", remark);
            returnDatas.add(returnData);
        }
        
        return success(returnDatas);
    }

    @Override
    public JSONObject getFreeMeasureSiteList(String json) {
        AppJSON appJson = parseAppJSON(json);
        JSONObject mm_0 = appJson.getData().getFirstJSONObject();
        
        String cityAreaCode = mm_0.getString(Area.CITY_AREA_CODE);
        if(StringUtils.isNotBlank(cityAreaCode)){
            cityAreaCode = cityAreaCode.substring(0,cityAreaCode.length()-2);
        }
        
        List<MeasureSiteDTO> siteList = measureSiteService.listFreeMeasureSite(cityAreaCode);
        
        List<Map<String, Object>> returnDatas = new ArrayList<>();
        for (MeasureSiteDTO site : siteList) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put(MeasureSite.LOGO, site.getLogo());
            returnData.put(MeasureSite.NAME, site.getName());
            returnData.put(Area.ADDRESS, site.getAddress());
            returnData.put(Area.LONGITUDE, site.getLongitude());
            returnData.put(Area.LATITUDE, site.getLatitude());
            returnData.put(MeasureSite.PHONE_NUMBER, site.getPhoneNumber());
            returnData.put(MeasureSite.BANNER, site.getBanner());
            
            StringBuffer ots = new StringBuffer();
            for (MeasureSiteOpeningTimeDTO ot : site.getOpeningTimeList()) {
                if (ots.length() > 0) {
                    ots.append(" ");
                }
                ots.append(ot.getStartTime() + "-" + ot.getEndTime());
            }
            returnData.put(MeasureSite.OPENINT_TIME, ots.toString());
            
            List<Map<String, Object>> devices = new ArrayList<>();
            StringBuffer tag = new StringBuffer();
            for (MeasureSiteDeviceDTO deviceDTO : site.getDeviceList()) {
                Map<String, Object> device = new HashMap<>();
                String name = deviceDTO.getName();
                device.put(MeasureSiteDevice.NAME, name);
                device.put(MeasureSiteDevice.PHOTO, deviceDTO.getPhoto());
                devices.add(device);
                
                if (tag.length() > 0) {
                    tag.append("/");
                }
                if (tag.length() > 8 && !StringUtils.endsWith(tag, "...")) {
                    tag =  new StringBuffer(tag.substring(0, 8) + "...");
                } else {
                    tag.append(name);
                }
            }
            returnData.put(MeasureSiteDevice.DEVICES, devices);
            returnData.put(MeasureSite.TAG, tag.toString());
            
            returnDatas.add(returnData);
        }
        
        return success(returnDatas, true);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = BaseException.class)
    public JSONObject getOrderInfoNew(Map<String, Object> map) throws BaseException {
        
        /*int userId = (int) map.get("userId");*/
    	int userId = 1212;
        
        /*Integer orderId = (Integer) map.get(Order.ID);*/
        String orderNumber = (String) map.get(Order.NUMBER);
        Integer orderType = (Integer) map.get(Order.ORDER_TYPE);
        
        if (orderNumber == null) {
            return error("获取支付签名失败");
        }

        String orderInfo = null;
        if (OrderInfoTypeEnum.PRIVATE.getValue() == orderType) {
            orderInfo = customOrderService.finishOrder(orderNumber);
        }else if (OrderInfoTypeEnum.GRUDS.getValue() == orderType){
            Map<String,String> extraData = new HashMap<>();
            List<com.lifeshs.po.drugs.OrderPO> orderList = drugsService.getLocalOrderByParam(orderNumber);
            if(orderList.size() > 0){
                int total = 0;
                com.lifeshs.po.drugs.OrderPO order = orderList.get(0);
                int money = (int) map.get(Order.MONEY);
                //List<OrderProductPO> productList = drugsService.getOrderProductList(orderNumber);
                for(OrderProductPO p : order.getOrderProductList()){
                    total += p.getActualPrice() * p.getAmount();
                }
                if (OrderStatusEnum.UNPAID.getValue() != order.getStatus()){
                    throw new OperationException("该订单状态为不可支付", ErrorCodeEnum.FAILED);
                }
                if(money != total){
                    throw new OperationException("金额不匹配 单位 分 (" + money + "=>" + total,ErrorCodeEnum.FAILED);    
                }
            
                PaymentOrderDTO paymentOrder = new PaymentOrderDTO();
                paymentOrder.setOrderNumber(orderNumber);
                paymentOrder.setCharge(money);
                paymentOrder.setOrderType(orderType);
                paymentOrder.setStatus(order.getStatus());
                
                Integer addressId = (Integer) map.get(Order.ADDRESSID);
                int payType = (int) map.get (Order.PAY_TYPE);
                
                extraData.put(Order.ADDRESSID, String.valueOf(addressId));
                extraData.put(Order.PAYMENTTYPE, String.valueOf(payType));
                orderInfo = orderService1.getOrderAlipaySignInfoNew(paymentOrder, Normal.APP_RECHARGE_NOTIFY_URL, extraData);
            }
        }else if (OrderInfoTypeEnum.SHOP.getValue() == orderType){
        	
        	Map<String,String> extraData = new HashMap<>();
            List<OrderDTO> orderList = shopService.getLocalOrderByParam(orderNumber);
            if(orderList.size() > 0){
                int total = 0;
                OrderDTO order = orderList.get(0);
                int money = (int) map.get(Order.MONEY);
                List<OrderDecomposeDTO> productList = shopService.getOrderDecList(orderNumber);
                for(OrderDecomposeDTO p : productList){
                    total += p.getPrice() * p.getNum();  //优惠价乘数量
                }
                if (OrderStatusEnum.UNPAID.getValue() != order.getStatus()){
                    throw new OperationException("该订单状态为不可支付", ErrorCodeEnum.FAILED);
                }
                if(money != total){
                    throw new OperationException("金额不匹配 单位 分 (" + money + "=>" + total,ErrorCodeEnum.FAILED);    
                }
            
                PaymentOrderDTO paymentOrder = new PaymentOrderDTO();
                paymentOrder.setOrderNumber(orderNumber);
                paymentOrder.setCharge(money);
                paymentOrder.setOrderType(orderType);
                paymentOrder.setStatus(order.getStatus());
                
                Integer addressId = (Integer) map.get(Order.ADDRESSID);
                int payType = (int) map.get(Order.PAY_TYPE);
                
                extraData.put(Order.ADDRESSID, String.valueOf(addressId));
                extraData.put(Order.PAYMENTTYPE, String.valueOf(payType));
                orderInfo = shopService.getOrderAlipaySignInfo(paymentOrder, Normal.APP_RECHARGE_NOTIFY_URL, extraData);
        	
            }
    	        	
        }else {
            /*// 电子券id
            Integer couponsId = (Integer) map.get("couponId");

            OrderPO order = null;
            if (orderNumber != null) {
                order = orderService1.getOrderByOrderNumber(orderNumber);
            }
            if (orderId != null) {
                 order = orderService1.getOrderDetail(orderId);
            }
            Map<String, String> extraData = new HashMap<>();
            if (couponsId != null && order != null) {
                String serveCode = order.getServeType().getFirstCode();
                String projectCode = order.getProjectCode();
                int serveItemId = order.getServeItemId();
                
                // 用户使用电子券需要把电子券修改为使用中的状态
                couponsService.useCoupons(couponsId, userId, serveCode, projectCode, serveItemId);
                // 同时把电子券的id加入到支付签名中
                extraData.put("couponsId", String.valueOf(couponsId));
                // 修改订单需要支付费用
                UserElectronicCouponsPO coupon = couponsService.getCoupons(couponsId);
                int couponPrice = coupon.getPrice();
                int orderCharge = order.getCharge();
                order.setCharge(orderCharge - couponPrice);
            }
            if (order != null) {
                orderInfo = orderService1.getOrderAlipaySignInfo(order, Normal.APP_RECHARGE_NOTIFY_URL, extraData);
            }*/
        }

        Map<String, Object> orderInfoMap = new HashMap<>();
        orderInfoMap.put(Order.INFO, orderInfo);
        return success(orderInfoMap);
    }
}
