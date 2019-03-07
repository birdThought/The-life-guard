package com.lifeshs.service.org.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.TerminalType;
import com.lifeshs.common.exception.org.AuthorityException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.dao.device.IDeviceDao;
import com.lifeshs.dao.org.manage.OrgServiceDao;
import com.lifeshs.dao.record.IRecordDao;
import com.lifeshs.entity.device.TMeasureBloodlipid;
import com.lifeshs.entity.device.TMeasureBloodpressure;
import com.lifeshs.entity.device.TMeasureBodyfatscale;
import com.lifeshs.entity.device.TMeasureEcg;
import com.lifeshs.entity.device.TMeasureGlucometer;
import com.lifeshs.entity.device.TMeasureHeartrate;
import com.lifeshs.entity.device.TMeasureLunginstrument;
import com.lifeshs.entity.device.TMeasureOxygen;
import com.lifeshs.entity.device.TMeasureTemperature;
import com.lifeshs.entity.device.TMeasureUa;
import com.lifeshs.entity.device.TMeasureUran;
import com.lifeshs.entity.device.TSportBand;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.entity.org.TOrgGroup;
import com.lifeshs.entity.org.TOrgGroupOrguser;
import com.lifeshs.entity.org.TOrgServe;
import com.lifeshs.entity.org.TServe;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.entity.record.TRecordMedicalCourse;
import com.lifeshs.entity.record.TRecordPhysicals;
import com.lifeshs.pojo.data.AreaVO;
import com.lifeshs.pojo.health.OrgServeVO;
import com.lifeshs.pojo.health.ServiceDoctorVO;
import com.lifeshs.pojo.health.ServiceItem;
import com.lifeshs.pojo.healthDevice.EcgDTO;
import com.lifeshs.pojo.healthDevice.EcgDetailDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.org.OrgDTO;
import com.lifeshs.pojo.org.employee.OrgEmploy;
import com.lifeshs.pojo.org.server.OrgMember;
import com.lifeshs.pojo.org.server.OrgMemberBase;
import com.lifeshs.pojo.org.server.OrgMemberMessageDetailVO;
import com.lifeshs.pojo.org.server.OrgServeDTO;
import com.lifeshs.pojo.org.server.OrgServeRecord;
import com.lifeshs.pojo.org.server.OrgServer;
import com.lifeshs.pojo.org.server.OrgServerGroupBase;
import com.lifeshs.pojo.org.server.OrgServerGroupInfo;
import com.lifeshs.pojo.org.server.RecommendServe;
import com.lifeshs.pojo.org.server.ServeDetailDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.pojo.record.DietVO;
import com.lifeshs.pojo.record.MedicalVO;
import com.lifeshs.pojo.record.PhysicalsVO;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.device.impl.Ecg;
import com.lifeshs.service.org.IServiceOrgService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.ListUtil;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.ReflectUtils;

@Service("serviceOrgService")
public class ServiceOrgServiceImpl extends CommonServiceImpl implements IServiceOrgService {

    private static final Logger logger = Logger.getLogger(ServiceOrgServiceImpl.class);
    
    @Autowired
    private OrgServiceDao orgServiceDao;

    @Autowired
    private IDeviceDao deviceDao;

    @Autowired
    private IRecordDao recordDao;

    @Autowired
    private IDataAreaService areaService;
    
    @Autowired
    private Ecg ecgService;
    
    @Override
    public List<OrgServer> getOrgServiceListAndMemberCount(int orgId, Integer orgUserId) {
        return orgServiceDao.getOrgServiceListAndMemberCount(orgId, orgUserId);
    }

    @Override
    public List<OrgServerGroupBase> findGroupByKey(int orgId, int serveId, Integer orgUserId) {
        return orgServiceDao.findGroupByKey(orgId, serveId, orgUserId);
    }

    public List<OrgServerGroupBase> findGroupByKeyWithOrgServeId(int orgId, int orgServeId, Integer orgUserId) {
        return orgServiceDao.findGroupByKeyWithOrgServeId(orgId, orgServeId, orgUserId);
    }

    @Override
    public OrgServerGroupInfo getGroupInfo(int orgId, int serveId, int groupId) {
        OrgServerGroupInfo info = orgServiceDao.getGroupInfo(orgId, serveId, groupId);
        List<Map<String, Object>> data = orgServiceDao.findAdminAndServer(groupId, 0);
        if (!data.isEmpty() && info != null) {
            Map<String, Object> dataMsg = data.get(0);
            if (dataMsg != null) {
                info.setServer((String) dataMsg.get("realName"));
            }
        }
        return info;
    }

    @Override
    public List<OrgMemberBase> findAllUserInGroup(int groupId, int page) {
        page = (page - 1) * 10;
        return orgServiceDao.findAllUserInGroup(groupId, page, 10);
    }

    @Override
    public List<OrgMemberBase> findAllUserInGroupWithoutPageSplit(int groupId) {
        return orgServiceDao.findAllUserInGroupWithoutPageSplit(groupId);
    }

    @Override
    public OrgEmploy getUserInfo(int orgId, int serverId) {
        return orgServiceDao.getUserInfo(orgId, serverId);
    }

    @Override
    public List<OrgServeRecord> findHistoryServeRecord(Map<String, Object> params) {
        int dPage = (int) params.get("dPage");
        int page = (int) params.get("page");
        Object condition = params.get("condition");
        if (condition != null) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) condition;
            if (map.containsKey("status") && !(map.get("status") instanceof List)) {
                List<Object> list = new ArrayList<>();
                list.add(map.get("status"));
                map.put("status", list);
            }
        }
        params.put("page", (page - 1) * dPage);
        List<OrgServeRecord> datas = orgServiceDao.findHistoryServeRecord(params);
        return datas;
    }

    @Override
    public int getRecordCount(Map<String, Object> params) {
        return orgServiceDao.getHistoryServeRecordCount(params);
    }

    @Override
    public List<OrgEmploy> getServers(int orgId) {
        return orgServiceDao.getServer(orgId);
    }

    @Override
    public HashMap<String, Object> getTradeData(int orgId, String dateType) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("orgId", orgId);
        params.put("dateType", dateType);
        return orgServiceDao.getTradeData(params);
    }

    @Override
    public HashMap<String, Object> getMemberData(int orgId, String dateType) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("orgId", orgId);
        params.put("dateType", dateType);
        return orgServiceDao.getMemberData(params);
    }

    @Override
    public List<OrgMember> findMemberList(Map<String, Object> params) {

        int page = (int) (params.get("page"));
        int dPage = (int) (params.get("dPage"));

        params.put("page", (page - 1) * dPage);
        return orgServiceDao.findMemberList(params);
    }

    @Override
    public int getMemberCount(Map<String, Object> params) {
        return orgServiceDao.getMemberCount(params);
    }

    @Override
    public List<TServe> getServeDatas(int orgId) {
        return orgServiceDao.getServeDatas(orgId);
    }

    @Transactional
    @Override
    // TODO 添加创建参数
    public Integer openOrderService(TOrgServe serve, String createrName, Integer orgId) {
        serve.setCreater(createrName);
        serve.setOrgId(orgId);
        serve.setCreateDate(new Date());

        /** 获取服务详情，判断服务类型 */
        TServe tServe = commonTrans.getEntity(TServe.class, serve.getServeId());
        List<String> serveCharMode = Arrays.asList(tServe.getChargeMode().split(","));

        if (serve.getHasFree() == null || !serve.getHasFree() || !serveCharMode.contains("0")) {
            serve.setHasFree(false);
        } else {
            serve.setHasFree(true);
        }
        /*if (project.getHasFree() == null) {
            project.setHasFree(false);
        } else {
            if (project.getHasFree()) {
                project.setHasFree(serveCharMode.contains("0"));
            }
        }*/
        if (serve.getHasTime() == null || !serve.getHasTime() || !serveCharMode.contains("1")) {
            serve.setHasTime(false);
        } else {
            serve.setHasTime(true);
        }
        /*if (project.getHasTime() == null) {
            project.setHasTime(false);
        } else {
            if (project.getHasTime()) {
                project.setHasTime(serveCharMode.contains("1"));
            }
        }*/
        if (serve.getHasMonth() == null || !serve.getHasMonth() || !serveCharMode.contains("2")) {
            serve.setHasMonth(false);
        } else {
            serve.setHasMonth(true);
        }
        /*if (project.getHasMonth() == null) {
            project.setHasMonth(false);
        } else {
            if (project.getHasMonth()) {
                project.setHasMonth(serveCharMode.contains("2"));
            }
        }*/
        if (serve.getHasYear() == null || !serve.getHasYear() || !serveCharMode.contains("3")) {
            serve.setHasYear(false);
        } else {
            serve.setHasYear(true);
        }
        /*if (project.getHasYear() == null && serveCharMode.contains("3")) {
            project.setHasYear(false);
        } else {
            if (project.getHasYear()) {
                project.setHasYear(serveCharMode.contains("3"));
            }
        }*/
        /** 保存服务 */
        commonTrans.save(serve);

        if ("02".equals(tServe.getCode())) {
            // 课堂不需要创建默认群组
        } else {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", "默认群组");
            params.put("defaultGroup", true);
            /** 创建默认群组 */
            addGroup(serve.getOrgId(), serve.getServeId(), params, null);
        }

        return serve.getId();
    }

    public OrgMemberMessageDetailVO getMemberMessageDetail(Integer memberId, Integer serveGroupId, Integer mode) {
        OrgMemberMessageDetailVO detail = orgServiceDao.getMemberMessageDetail(memberId, serveGroupId, mode);

        if (detail.getChargeMode().intValue() <= 0) {
            detail.setConsumeMode("免费");
        } else {
            StringBuffer sb = new StringBuffer(NumberUtils.changeF2Y(detail.getPrice() + "") + "元/");

            int chargeMode = detail.getChargeMode().intValue();
            switch (chargeMode) {
            case 1:
                sb.append("次");
                break;
            case 2:
                sb.append("月");
                break;
            case 3:
                sb.append("年");
                break;
            }

            detail.setConsumeMode(sb.toString());
        }

        if (StringUtils.isBlank(detail.getServeUserName())) {
            detail.setServeUserName("未指定服务师");
        }

        return detail;
    }

    @Override
    public void isThisMemberBelongToTheOrg(Integer orgId, Integer userId) throws AuthorityException {
        Integer rowCount = orgServiceDao.isThisMemberBelongToTheOrg(orgId, userId);
        if (rowCount.intValue() == 0) {
            throw new AuthorityException("您无权查看该用户的信息");
        }
    }

    @Override
    public Map<String, Object> getMemberHealthDataSplitByMeasureDate(Integer userId, String measureDate) {
        Map<String, Object> data = new HashMap<>();

        List<Class<?>> devicesName = new ArrayList<>();
        devicesName.add(TSportBand.class);
        devicesName.add(TMeasureHeartrate.class);
        devicesName.add(TMeasureBodyfatscale.class);
        devicesName.add(TMeasureBloodpressure.class);
        devicesName.add(TMeasureLunginstrument.class);
        devicesName.add(TMeasureGlucometer.class);
        devicesName.add(TMeasureOxygen.class);
        devicesName.add(TMeasureTemperature.class);
        devicesName.add(TMeasureBloodlipid.class);
        devicesName.add(TMeasureUa.class);
        devicesName.add(TMeasureUran.class);
        devicesName.add(TMeasureEcg.class);
        // 获取数据，以一个健康包的简单名称作为键，数据作为值，保存到data中
        for (Class<?> deviceName : devicesName) {
            // 心电
            if (deviceName.equals(TMeasureEcg.class)) {
                EcgDTO ecgData = ecgService.getLastestData(userId);
                
                Map<String, Object> ecgMap = null;
                if (ecgData != null) {
                    EcgDetailDTO ecgDetailData = ecgData.getDetailList().get(0);
                    for (EcgDetailDTO d : ecgData.getDetailList()) {
                        if (d.getStatus().intValue() > 0) {
                            ecgDetailData = d;
                        }
                    }
                    ecgMap = new HashMap<>();
                    ecgMap.put("heartRate", ecgDetailData.getHeartRate());
                    ecgMap.put("status", ecgDetailData.getStatus());
                    ecgMap.put("measureDate", ecgDetailData.getMeasureDate());
                }
                
                data.put(getClassSimpleName(TMeasureEcg.class), ecgMap);
                continue;
            }
            // 手环
            if (deviceName.equals(TSportBand.class)) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("userId", userId);
                Map<String, Object> entity = ReflectUtils.queryEntityEnclosureParamsWholeColumn(TSportBand.class, params);
                entity.put("date", measureDate);
                entity.put("deviceType", "APP");

                Map<String, Object> band = deviceDao.selectDeviceDataLastestDate(entity);
//                            Map<String, Object> band = deviceDao.selectLatestBandWithDate(userId, measureDate,
//                                    TerminalType.APP.getName());
                data.put(getClassSimpleName(TSportBand.class), band);
                continue;
            }
            
            // 其它通用类型
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userId", userId);

            Map<String, Object> entity = ReflectUtils.queryEntityEnclosureParamsWholeColumn(deviceName,
                    params);
            entity.put("measureDate", measureDate);
            entity.put("deviceType", TerminalType.APP.getName());
            Map<String, Object> deviceData = null;
            List<Map<String, Object>> heartRateData = null;
            if ("t_measure_heartrate".equals(entity.get("table"))){
                String targetVal = entity.get("columnNames").toString().replace("id,","max(id),");
                entity.replace("columnNames", entity.get("columnNames"),targetVal);
                heartRateData = deviceDao.selectDeviceDataByDayHour(entity);
                if (heartRateData.size() > 0) {
                    data.put(getClassSimpleName(deviceName), heartRateData);
                }
            } else {
                deviceData = deviceDao.selectDeviceDataLastest(entity);
                data.put(getClassSimpleName(deviceName), deviceData);
            }
        }
        return data;
    }

    @Override
    public List<MedicalVO> getMemberMedicalDataSplitByVisitingDate(Integer userId, String visitingDate) {

        List<MedicalVO> medicals = new ArrayList<>();

        List<Map<String, Object>> datas = recordDao.selectMedicalByVisitingDateAndUserId(userId, visitingDate);

        for (Map<String, Object> data : datas) {
            MedicalVO medical = new MedicalVO();

            medical.setId((Integer) data.get("id"));
            medical.setTitle((String) data.get("title"));
            medical.setName((String) data.get("realName"));

            String sex = "女";
            if ((Boolean) data.get("sex")) {
                sex = "男";
            }
            medical.setSex(sex);

            Integer age = 0;
            if ((Date) data.get("birthday") != null) {
                age = DateTimeUtilT.calculateAge((Date) data.get("birthday"));
            }
            medical.setAge(age);

            medical.setBasicCondition((String) data.get("basicCondition"));
            medical.setVisitingDate(visitingDate);

            // photo
            List<TRecordMedicalCourse> courses = commonTrans.findByProperty(TRecordMedicalCourse.class, "medicalId",
                    data.get("id"));
            if (courses != null) {
                String photo = null;
                for (TRecordMedicalCourse course : courses) {
                    if (StringUtils.isNotBlank(course.getImg1())) {
                        photo = course.getImg1();
                        break;
                    }
                    if (StringUtils.isNotBlank(course.getImg2())) {
                        photo = course.getImg2();
                        break;
                    }
                    if (StringUtils.isNotBlank(course.getImg3())) {
                        photo = course.getImg3();
                        break;
                    }
                }
                // if(photo == null) {
                // photo = "static/images/head.png";
                // }
                medical.setPhoto(photo);
            }

            medicals.add(medical);
        }

        return medicals;
    }

    @Override
    public List<PhysicalsVO> getPhysicalDataSplitByPhysicalsDate(Integer userId, String physicalsDate) {
        List<PhysicalsVO> datas = new ArrayList<>();

        List<TRecordPhysicals> physicals = recordDao.selectPhysicalsByPhysicalsDateAndUserId(userId, physicalsDate);

        if (physicals != null) {
            for (TRecordPhysicals physical : physicals) {
                PhysicalsVO data = new PhysicalsVO();
                data.setId(physical.getId());
                data.setUserId(physical.getUserId());
                data.setTitle(physical.getTitle());
                data.setPhysicalsOrg(physical.getPhysicalsOrg());
                data.setDescription(physical.getDescription());
                // 图片
                List<String> photos = new ArrayList<>();
                if (StringUtils.isNotBlank(physical.getImg1())) {
                    photos.add(physical.getImg1());
                }
                if (StringUtils.isNotBlank(physical.getImg2())) {
                    photos.add(physical.getImg2());
                }
                if (StringUtils.isNotBlank(physical.getImg3())) {
                    photos.add(physical.getImg3());
                }
                data.setPhotos(photos);

                datas.add(data);
            }
        }

        return datas;
    }

    @Override
    public List<DietVO> getDietDataSplitByRecordDate(Integer userId, String recordDate) {
        List<DietVO> datas = new ArrayList<>();

        List<Map<String, Object>> diets = recordDao.selectDietByUserIdWithDate(userId, recordDate);
        if (diets != null) {
            for (Map<String, Object> diet : diets) {
                DietVO data = new DietVO();
                data.setId((Integer) diet.get("id"));
                data.setDietType((String) diet.get("dietType"));
                data.setEnergy((Integer) diet.get("energy") + "kcal");
                data.setDietTime(String.valueOf(diet.get("dietTime")));

                List<String> dietDetails = new ArrayList<>();

                List<Map<String, Object>> dietFoods = recordDao.selectDietFoodByDietId((Integer) diet.get("id"));
                if (dietDetails != null) {
                    for (Map<String, Object> dietFood : dietFoods) {
                        String dietDetail = (String) dietFood.get("name") + (Integer) dietFood.get("foodWeight") + "g";
                        dietDetails.add(dietDetail);
                    }
                }
                data.setDietDetails(dietDetails);

                datas.add(data);
            }
        }

        return datas;
    }

    /**
     * 获取一个更加简洁的名称
     * </p>
     * 规则是类名格式为(XxxXxxXxx……)，取类名的第三个大写字母的下标，将原名称进行substring
     * </p>
     * 比如：类名为TSportBand，转换的结果就是Band
     *
     * @param name
     * @return
     * @author zhiguo.lin
     * @DateTime 2016年9月13日 下午7:25:30
     */
    private String getClassSimpleName(Class<?> name) {
        int upperCaseNumber = 0;
        int thirdUpperCaseCharacterIndex = -1;

        for (int i = 0; i < name.getSimpleName().length(); i++) {
            char c = name.getSimpleName().charAt(i);
            if (Character.isUpperCase(c)) {
                upperCaseNumber++;
            }
            if (upperCaseNumber == 3) {
                thirdUpperCaseCharacterIndex = i;
                break;
            }
        }
        String simpleName = name.getSimpleName().substring(thirdUpperCaseCharacterIndex);
        return simpleName;
    }

    @Override
    public List<OrgEmploy> getOrgServersWithGroupCount(int orgId) {
        return orgServiceDao.getOrgGroupManager(orgId);
    }

    @Override
    public int addGroup(int orgId, Integer serveId, Map<String, Object> params, Integer orgServeId) {

        int os = 0;

        if (orgServeId == null) {
            Map<String, Object> condition = new HashMap<String, Object>();
            condition.put("orgId", orgId);
            condition.put("serveId", serveId);
            List<Map<String, Object>> serve = commonTrans.findByMap(TOrgServe.class, condition);
            os = (Integer) serve.get(0).get("id");
        } else {
            os = orgServeId;
        }

        TOrgGroup group = new TOrgGroup();
        group.setOrgServeId(os);
        group.setName((String) params.get("name"));
        group.setDefaultGroup((boolean) params.get("defaultGroup"));
        group.setCreateDate(new Date());
        commonTrans.save(group);
        if (params.containsKey("servers")) {
            JSONArray servers = (JSONArray) params.get("servers");
            List<TOrgGroupOrguser> userList = new ArrayList<TOrgGroupOrguser>();
            for (Object obj : servers) {
                TOrgGroupOrguser user = new TOrgGroupOrguser();
                user.setGroupId(group.getId());
                user.setCreateDate(new Date());
                user.setOrgUserId(Integer.parseInt((String) obj));
                userList.add(user);
            }
            if (userList.size() > 0) {
                commonTrans.batchSave(userList);
            }
        }
        return group.getId();
    }

    @Override
    public List<Map<String, Object>> getControlGroupServer(int groupId) {
        return orgServiceDao.findAdminAndServer(groupId, 1);
    }

    @Override
    public void editGroup(Map<String, Object> params) {
        int groupId = Integer.parseInt((String) params.get("groupId"));
        if (params.containsKey("groupName")) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("table", "t_org_group");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("name", (String) params.get("groupName"));
            Map<String, Object> condition = new HashMap<String, Object>();
            condition.put("id", groupId);
            map.put("column", param);
            map.put("condition", condition);
            commonTrans.updateByMap(map);// 更新群组的名字
        }
        if (params.containsKey("needDel")) {
            JSONArray dels = (JSONArray) params.get("needDel");
            List<TOrgGroupOrguser> userList = new ArrayList<TOrgGroupOrguser>();
            for (Object obj : dels) {
                TOrgGroupOrguser user = new TOrgGroupOrguser();
                user.setId((Integer) obj);
                user.setGroupId(groupId);
                userList.add(user);
            }
            commonTrans.deleteAllEntitie(userList);// 删除之前在该群上管理的服务师
        }
        if (params.containsKey("needInsert")) {
            JSONArray inserts = (JSONArray) params.get("needInsert");
            List<TOrgGroupOrguser> userList = new ArrayList<TOrgGroupOrguser>();
            for (Object obj : inserts) {
                TOrgGroupOrguser user = new TOrgGroupOrguser();
                user.setGroupId(groupId);
                user.setOrgUserId(Integer.parseInt((String) obj));
                user.setCreateDate(new Date());
                userList.add(user);
            }
            commonTrans.batchSave(userList);// 插入服务师
        }
    }

    @Override
    public TOrgServe getOrderServiceDetail(Integer orgId, Integer serId) {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("orgId", orgId);
        condition.put("serveId", serId);
        List<Map<String, Object>> orgServe = commonTrans.findByMap(TOrgServe.class, condition);
        TOrgServe service = ReflectUtils.getBean(orgServe.get(0), TOrgServe.class);
        return service;
    }

    @Override
    public void updateOrderServiceDetail(Integer orgId, TOrgServe serve) {
        serve.setOrgId(orgId);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("orgId", orgId);
        condition.put("serveId", serve.getServeId());
        Map<String, Object> orgServe = commonTrans.findByMap(TOrgServe.class, condition).get(0);
        serve.setCreater((String) orgServe.get("creater"));
        serve.setId((Integer) orgServe.get("id"));
        serve.setCreateDate((Date) orgServe.get("createDate"));

        List<String> serveCharMode = Arrays
                .asList(((TServe) commonTrans.getEntity(TServe.class, serve.getServeId())).getChargeMode().split(","));

        if (serve.getHasFree() == null) {
            serve.setHasFree(false);
        } else {
            if (serve.getHasFree()) {
                serve.setHasFree(serveCharMode.contains("0"));
            }
        }
        if (serve.getHasTime() == null) {
            serve.setHasTime(false);
        } else {
            if (serve.getHasTime()) {
                serve.setHasTime(serveCharMode.contains("1"));
            }
        }
        if (serve.getHasMonth() == null) {
            serve.setHasMonth(false);
        } else {
            if (serve.getHasMonth()) {
                serve.setHasMonth(serveCharMode.contains("2"));
            }
        }
        if (serve.getHasYear() == null) {
            serve.setHasYear(false);
        } else {
            if (serve.getHasYear()) {
                serve.setHasYear(serveCharMode.contains("3"));
            }
        }

        commonTrans.updateEntitie(serve);
    }

    @Override
    public List<TServe> getTServers() {
        return orgServiceDao.geTServers();
    }

    @Override
    public List<OrgDTO> getRecommendManage(Integer count) {
        return orgServiceDao.getRecommendManage(count);
    }

    @Override
    public PaginationDTO<Map<String, Object>> getServeOrgsPageSplit(int curPage, int pageSize, String code, String classify, String areaCode, String searchValue) {
        PaginationDTO<Map<String, Object>> pagination = new PaginationDTO<>();
        List<Map<String, Object>> data = new ArrayList<>();
        
        String areaCodeRegex = null;
        if (StringUtils.isNotBlank(areaCode)) {
            /** 地区编码的头部 */
            String codeHead = areaCode.substring(0, 4);
            /** 地区编码的尾部 */
            String codeTail = areaCode.substring(4, 6);
            if ("00".equals(codeTail)) {
                areaCodeRegex = "^" + codeHead + "[[:digit:]]{2}$";
            } else {
                areaCodeRegex = "^" + codeHead + codeTail + "$";
            }
        }

        int totalSize = orgServiceDao.getCountOfOrgListByClassifyOrServeCode(code, classify, areaCodeRegex, searchValue);

        if ((curPage - 1) * pageSize > totalSize) {
            pagination.setData(data);
            return pagination;
        }

        QueryPageData queryResult = PaginationDTO.getQueryPageData(curPage, pageSize, totalSize);

        int startIndex = queryResult.getStartIndex();
        int totalPage = queryResult.getTotalPage();
        curPage = queryResult.getCurPage();

        data = orgServiceDao.orgListByClassifyOrServeCode(code, classify, areaCodeRegex, searchValue, startIndex, pageSize);

        pagination.setData(data);
        pagination.setNowPage(curPage);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(totalSize);

        return pagination;
    }

    @Override
    public PaginationDTO getServeOrgsPageSplitForValueName(int curPage, int pageSize, String orgNameOrClassifyName, String areaCode) {
        PaginationDTO pagination = new PaginationDTO();
        List<Map<String, Object>> data = new ArrayList<>();

        String areaCodeRegex = null;
        if (areaCode != null) {
            /** 地区编码的头部 */
            String codeHead = areaCode.substring(0, 3);
            /** 地区编码的尾部 */
            String codeTail = areaCode.substring(4, 5);
            if ("00".equals(codeTail)) {
                areaCodeRegex = "^" + codeHead + "[[:digit:]]{2}$";
            } else {
                areaCodeRegex = "^" + codeHead + codeTail + "$";
            }
        }
        
        int totalSize = orgServiceDao.getCountOfQueryOrgListByValueName(orgNameOrClassifyName, areaCodeRegex);

        if ((curPage - 1) * pageSize > totalSize) {
            pagination.setData(data);
            return pagination;
        }

        QueryPageData queryResult = PaginationDTO.getQueryPageData(curPage, pageSize, totalSize);

        int startIndex = queryResult.getStartIndex();
        int totalPage = queryResult.getTotalPage();
        curPage = queryResult.getCurPage();

        data = orgServiceDao.queryOrgListByValueName(orgNameOrClassifyName, areaCodeRegex, startIndex, pageSize);

        pagination.setData(data);
        pagination.setNowPage(curPage);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(totalSize);

        return pagination;
    }

    @Override
    public OrgDTO getServeOrgWithServeList(int orgId) {
        OrgDTO org = orgServiceDao.getTOrg(orgId);

        // 地址
        String province = "";
        String city = "";
        String district = "";
        if (StringUtils.isNotBlank(org.getProvince()) || StringUtils.isNotBlank(org.getCity())
                || StringUtils.isNotBlank(org.getDistrict())) {
            AreaVO areaVO = areaService.getAreaNameByCode(org.getProvince(), org.getCity(), org.getDistrict());
            province = areaVO.getProvinceName();
            city = areaVO.getCityName();
            district = areaVO.getDistrictName();
        }
        org.setProvince(province);
        org.setCity(city);
        org.setDistrict(district);
        return org;
    }

    @Override
    public List<OrgServeVO> getOrgServeList(int orgId) {
        return orgServiceDao.selectOrgServes(orgId);
    }

    @Override
    public Map<String, Object> getServeDetailByOrgServeId(int orgServeId) {
        Map<String, Object> serveDetail = orgServiceDao.selectServeDetailByOrgServeId(orgServeId);
        serveDetail.put("orgServeId", orgServeId);
        return serveDetail;
    }

    @Override
    public ServeDetailDTO getServeDetailByServeGroupId(int serveGroupId) {
        return orgServiceDao.selectServeDetailByServeGroupId(serveGroupId);
    }

    @Override
    public List<Map<String, Object>> getServeUserByServeId(int orgServeId) {

        List<Map<String, Object>> users = orgServiceDao.selectServeUserByServeId(orgServeId);
        Map<Integer, Map<String, Object>> users_map = new HashMap<>();
        for (Map<String, Object> user : users) {
            Integer userId = (Integer) user.get("userId");
            if (!users_map.containsKey(userId)) {
                users_map.put(userId, user);
            }
        }
        users.clear();
        for (Integer key : users_map.keySet()) {
            users.add(users_map.get(key));
        }

        return users;
    }

    @Override
    public List<Map<String, Object>> getClassifyTags() {
        List<Map<String, Object>> classifytags = new ArrayList<>();
        Map<String, Object> allClassify = new HashMap<>();
        allClassify.put("name", "全部分类");
        classifytags.add(allClassify);

        List<String> tags = new ArrayList<>();

        List<TServe> servers = orgServiceDao.geTServers();
        for (TServe server : servers) {
            String classifyTags = server.getClassify();
            for (String classifyTag : classifyTags.split(",")) {
                // Map<String, Object> dataMap = new HashMap<>();
                // dataMap.put("name", classifyTag);
                // tags.add(dataMap);
                tags.add(classifyTag);
            }
        }

        tags = ListUtil.removeRepeatElement(tags, String.class);
        for (String tag : tags) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("name", tag);
            classifytags.add(temp);
        }

        return classifytags;
    }

    @Override
    public List<Map<String, Object>> getServiceTags() {
        List<Map<String, Object>> dataList = new ArrayList<>();

        List<TServe> servers = orgServiceDao.geTServers();
        for (TServe server : servers) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("id", server.getId());
            temp.put("code", server.getCode());
            temp.put("name", server.getName());
            dataList.add(temp);
        }

        return dataList;
    }

    @Override
    public List<ServiceItem> getConsultItemList(Map<String, Object> params, int startPage, int max) {

        List<ServiceItem> items = orgServiceDao.getHealthConsultList(params, (startPage - 1) * max, max);

        for (ServiceItem item : items) {
            String p = item.getProvinceCode();
            String c = item.getCityCode();
            String d = item.getDistrictCode();

            String pN = "";
            String cN = "";
            String dN = "";

            if (StringUtils.isNotBlank(p) && StringUtils.isNotBlank(c) && StringUtils.isNotBlank(d)) {
                AreaVO areaVO = areaService.getAreaNameByCode(p, c, d);
                pN = areaVO.getProvinceName();
                cN = areaVO.getCityName();
                dN = areaVO.getDistrictName();

                if (StringUtils.equals(dN, cN)) {
                    dN = "";
                }
                if (StringUtils.equals(pN, cN)) {
                    cN = "";
                }
            }

            String address = pN + " " + cN + " " + dN + " " + item.getStreet();

            if (StringUtils.isBlank(address) || address.trim().equals("null")) {
                address = "";
            }
            item.setAddress(address);

            String about = item.getAbout();
            if (StringUtils.isNotBlank(about) && about.length() > 75) {
                about = about.substring(0, 74) + "...";
                item.setAbout(about);
            }
        }

        return items;
    }

    @Override
    public int getHealthConsultListCount(Map<String, Object> params) {
        return orgServiceDao.getHealthConsultListCount(params);
    }

    @Override
    public String getServeOrgClassify(int orgId) {
        String classify = "";

        List<String> classifies = orgServiceDao.getServeOrgClassify(orgId);
        if (classifies != null) {
            classify = classifies2String(classifies);
        }

        return classify;
    }

    @Override
    public ServiceDoctorVO getServiceDoctor(int orgUserId) {
        ServiceDoctorVO serviceDoctor = new ServiceDoctorVO();

        TOrgUser orgUser = commonTrans.getEntity(TOrgUser.class, orgUserId);

        if (orgUser != null && (orgUser.getUserType() == 1 || orgUser.getUserType() == 2)
                && orgUser.getStatus().intValue() != 0) {
            // 基础信息
            serviceDoctor.setId(orgUserId);
            serviceDoctor.setRealName(orgUser.getRealName());
            serviceDoctor.setPhoto(orgUser.getPhoto());
            serviceDoctor.setAbout(orgUser.getAbout());
            serviceDoctor.setDetail(orgUser.getDetail());
            // 企业信息
            TOrg org = commonTrans.getEntity(TOrg.class, orgUser.getOrgId());
            serviceDoctor.setOrgName(org.getOrgName());
            // 分类信息
            String classify = "";
            List<String> classifies = orgServiceDao.getOrgUserClassifyByUserId(orgUserId);
            if (classifies != null) {
                classify = classifies2String(classifies);
            }
            serviceDoctor.setClassify(classify);
            // 服务列表
            List<OrgServeVO> serves = orgServiceDao.getOrgUserServeList(orgUserId);
            if (serves != null) {
                /**
                 * 使用一个key数组存放key，如果该code已经存在，就不继续存放该数据 避免数据重复
                 */
                List<OrgServeVO> servesTemp = new ArrayList<>();
                List<String> serveCodeKeys = new ArrayList<>();
                for (OrgServeVO serve : serves) {
                    if (!serveCodeKeys.contains(serve.getCode())) {
                        servesTemp.add(serve);
                    }
                }
                serviceDoctor.setServes(servesTemp);
            }
        }

        return serviceDoctor;
    }

    private String classifies2String(List<String> classifies) {
        String classifiesString = "";

        List<String> newClassifyList = new ArrayList<>();

        for (String classify : classifies) {
            if (StringUtils.isNotBlank(classify)) {
                for (String c : classify.split(",")) {
                    newClassifyList.add(c);
                }
            }
        }
        if (newClassifyList.size() > 0) {
            newClassifyList = ListUtil.removeRepeatElement(newClassifyList, String.class);
            for (String cs : newClassifyList) {
                classifiesString += cs + ",";
            }
            classifiesString = classifiesString.substring(0, classifiesString.length() - 1);
        }
        return classifiesString;
    }

    @Override
    public List<Map<String, Object>> getUsersByHuanxinAccount(List<String> huanxinUserNames) {
        return orgServiceDao.getUsersByHuanxinAccount(huanxinUserNames);
    }

    @Override
    public OrgServeDTO getOrgServeDetail(Integer orgServeId) {
        OrgServeDTO orgServe = orgServiceDao.getOrgServeById(orgServeId);
        
        /*Map<String, Object> serveDetail = getServeDetailByOrgServeId(orgServeId);// 获取这个机构服务对应的服务详情及其他信息
        serveDetail.put("price", NumberUtils.changeF2Y(serveDetail.get("price") + ""));
        List<String> groupIds = Arrays.asList(((String) serveDetail.get("groupIds")).split(","));// 该服务机构下的所有群组id

        List<Map<String, Object>> servers = orgServiceDao.getServersInOrgServeGroupId(groupIds);// 获取所有组下的服务师

        // 清除重复的服务师
        Map<Integer, Map<String, Object>> servers_map = new HashMap<>();
        for (Map<String, Object> server : servers) {
            Integer id = (Integer) server.get("id");
            if (!servers_map.containsKey(id)) {
                servers_map.put(id, server);
            }
        }
        servers.clear();
        for (Integer key : servers_map.keySet()) {
            servers.add(servers_map.get(key));
        }

        serveDetail.put("servers", servers);*/
        return orgServe;
    }

    @Override
    public boolean moveGroup(Map<String, Object> params, Integer orgId) {
        params.put("orgId", orgId);
        return orgServiceDao.moveGroup(params) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ServiceMessage delGroup(Map<String, Object> params, Integer orgId, boolean moveToDefaultGroup) {
        ServiceMessage resultMsg = new ServiceMessage();
        TOrgGroup group = commonTrans.get(TOrgGroup.class, Integer.parseInt(String.valueOf(params.get("oldGroup"))));

        if (group.getDefaultGroup()) {// 默认群组不能删除
            resultMsg.setSuccess(false);
            resultMsg.setMessage("默认群组不能删除");
            return resultMsg;
        }

        Map<String, Object> newGroup = new HashMap<>();

        if (moveToDefaultGroup) {
            int orgServeId = group.getOrgServeId();
            Map<String, Object> searchDefaultGroupParams = new HashMap<>();
            searchDefaultGroupParams.put("orgServeId", orgServeId);
            searchDefaultGroupParams.put("defaultGroup", true);

            /** 查询该服务的默认群组 */
            List<Map<String, Object>> groups = commonTrans.findByMap(TOrgGroup.class, searchDefaultGroupParams);
            if (groups.size() > 0) {
                newGroup = groups.get(0);
                params.put("newGroup", String.valueOf(newGroup.get("id")));
            }
        }

        if (params.containsKey("newGroup")) {// 需要迁移群内成员
            newGroup = commonTrans.getByMap(TOrgGroup.class, String.valueOf(params.get("newGroup")));
            moveGroup(params, orgId);// 迁移群内成员到另外一个群组
        }

        Map<String, Object> orgGroupUsers = new HashMap<>();// 删除群组下绑定的服务师
        orgGroupUsers.put("table", "t_org_group_orguser");
        Map<String, Object> orgGroupUsersColums = new HashMap<>();
        orgGroupUsersColums.put("groupId", group.getId());
        orgGroupUsers.put("column", orgGroupUsersColums);
        int row = baseDao.delete(orgGroupUsers);

        row += commonTrans.delete(group);// 删除群组
        resultMsg.setSuccess(row >= 1);
        JSONObject groupRoot = new JSONObject();
        groupRoot.put("id", newGroup.get("id"));
        groupRoot.put("name", newGroup.get("name"));
        resultMsg.setMessage(groupRoot.toString());

        return resultMsg;
    }

    @Override
    public Map<String, Object> getServeAndOrgInfoByGroupId(int groupId) {
        return orgServiceDao.getServeAndOrgInfoByGroupId(groupId);
    }

    @Override
    public OrgServerGroupBase getOrgGroupBaseInfo(int groupId) {
        return orgServiceDao.getGroupBaseInfo(groupId);
    }

    @Override
    public List<RecommendServe> getRecommendServes(Integer count) {
        return orgServiceDao.getRecommendServes(count);
    }

    @Override
    public OrgDTO getOrg(Integer id) {
        return orgServiceDao.getTOrg(id);
    }

    @Override
    public Integer getOrgServeConsumePersonTime(int orgServeId) {
        return orgServiceDao.getOrgServeConsumePersonTime(orgServeId);
    }

    @Override
    public List<UserDTO> listOrgMember(Integer orgId) {
//        return orgServiceDao.listOrgMember(orgId);
        return null;
    }
}
