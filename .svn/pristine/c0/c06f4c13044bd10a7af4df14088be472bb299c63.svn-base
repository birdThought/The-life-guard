package com.lifeshs.service.terminal.app.health.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.lifeshs.common.business.Conversion;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.Rhythm;
import com.lifeshs.common.constants.app.Terminal;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.healthPackage.BloodLipid;
import com.lifeshs.common.constants.app.healthPackage.Ecg;
import com.lifeshs.common.constants.app.healthPackage.HealthDescription;
import com.lifeshs.common.constants.app.healthPackage.HealthPackage;
import com.lifeshs.common.constants.app.healthPackage.Ua;
import com.lifeshs.common.constants.app.healthPackage.Uran;
import com.lifeshs.common.constants.common.FMRank;
import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthRank;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.constants.common.healthPackage.DataTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.measure.UserInfoReadDao;
import com.lifeshs.dao1.order.IOrderDao;
import com.lifeshs.dao1.user.UserRecordDao;
import com.lifeshs.entity.data.TDataHealthDescribe;
import com.lifeshs.entity.device.TMeasureBloodpressure;
import com.lifeshs.entity.device.TMeasureBodyfatscale;
import com.lifeshs.entity.device.TMeasureGlucometer;
import com.lifeshs.entity.device.TMeasureHeartrate;
import com.lifeshs.entity.device.TMeasureLunginstrument;
import com.lifeshs.entity.device.TMeasureOxygen;
import com.lifeshs.entity.device.TMeasureTemperature;
import com.lifeshs.entity.device.TSportBand;
import com.lifeshs.po.UserInfoRead;
import com.lifeshs.po.UserRecord;
import com.lifeshs.po.measure.MeasureAnalysisPO;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.app.member.AppJSONDataDTO;
import com.lifeshs.pojo.data.EcgRhythmDTO;
import com.lifeshs.pojo.data.ReasonDTO;
import com.lifeshs.pojo.data.ReasonSimpleDTO;
import com.lifeshs.pojo.healthDevice.BloodLipidDTO;
import com.lifeshs.pojo.healthDevice.EcgDTO;
import com.lifeshs.pojo.healthDevice.EcgDetailDTO;
import com.lifeshs.pojo.healthDevice.EcgHistoryDTO;
import com.lifeshs.pojo.healthDevice.UaDTO;
import com.lifeshs.pojo.healthDevice.UranDTO;
import com.lifeshs.pojo.healthStandard.HealthStandardValueEx;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.data.IHealthReasonService;
import com.lifeshs.service.device.impl.healthStandard.HealthStandard;
import com.lifeshs.service.record.IRecordService;
import com.lifeshs.service.terminal.app.health.IAppHealthService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.measure.MeasureAnalysisService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.NumberUtils;

/**
 * APP健康数据
 *
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月6日 下午4:23:10
 */
@Service(value = "appHealthService")
public class AppHealthServiceImpl extends AppNormalService implements IAppHealthService {

    private static Logger logger = Logger.getLogger(AppHealthServiceImpl.class);

    @Autowired
    IOrderDao orderDao1;

    @Autowired
    UserInfoReadDao userInfoReadDao;

    @Resource(name = "healthReasonService")
    private IHealthReasonService reasonService;

    @Autowired
    UserRecordDao userRecordDao;

    @Resource(name = "measureAnalysisServiceImpl")
    private MeasureAnalysisService measureAnalysisService;

    @Autowired
    private IRecordService recordService;

    @Override
    public JSONObject getHealthArea(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String terminalType = mm_0.getString(HealthPackage.GET_TYPE);// 0：按系统计算值，2：按服务（健康目标值），3-9：服务预留

        UserDTO user = appJSON.getAopData().getUser();

        Map<String, Object> map = new HashMap<>();

        switch (terminalType) {
            case "0":
                map = getSystemCalculateHealthArea(user, 1);
                break;
            default:
                break;
        }

        Map<String, String> extraMap = new HashMap<>();
        extraMap.put("method", "getHealthArea");

        return success(map, extraMap, true);
    }

    @Override
    public JSONObject getHealthArea2(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String terminalType = mm_0.getString(HealthPackage.GET_TYPE);// 0：按系统计算值，2：按服务（健康目标值），3-9：服务预留

        UserDTO user = appJSON.getAopData().getUser();

        Map<String, Object> map = new HashMap<>();
        switch (terminalType) {
            case "0":
                map = getSystemCalculateHealthArea(user, 2);
                break;
            default:
                break;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("sex", user.getRecordDTO().getGender());

        /* List<Map<String, Object>> describtions = new ArrayList<>(); */
        List<Map<String, Object>> describtions = terminal.getCommonTrans().findByMap(TDataHealthDescribe.class, params);
        for (Map<String, Object> describtion : describtions) {
            for (HealthType healthType : HealthType.values()) {
                if (StringUtils.equals(healthType.getName(), (String) describtion.get("param"))) {
                    describtion.put(HealthDescription.PARAM, healthType.name());
                }
            }

            describtion.remove("id");
            describtion.remove("sex");
        }

        map.put(HealthDescription.HEALTH_DESCRIPTION, describtions);

        return success(map, true);
    }

    @Override
    @Transactional(rollbackFor = OperationException.class)
    public JSONObject addHeartRate(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        String deviceType = appJSON.getType();
        int userId = appJSON.getData().getUserId();
        JSONArray mm = appJSON.getData().getMsg();

        for (int i = 0; i < mm.size(); i++) {
            String heartRate = mm.getJSONObject(i).getString(HealthPackage.HEARTRATE);
            String measureDate = mm.getJSONObject(i).getString(HealthPackage.MEASUREDATE);
            String manualEntry = mm.getJSONObject(i).getString(HealthPackage.MANUAL_ENTRY); // 新增参数，手动录入数据
            Integer sportMode = mm.getJSONObject(i).getInteger(HealthPackage.SPORT_MODE);   // 新增参数，运动模式
            /** 默认数据类型为实时数据 */
            Integer dataType = DataTypeEnum.REAL_TIME.value();
            if (manualEntry != null && Normal.TRUE.equals(manualEntry)) {
                dataType = DataTypeEnum.MANUAL.value(); // 手动录入数据
            }
            if (sportMode != null && Normal.TRUE.equals(sportMode)) {
                dataType = DataTypeEnum.SPORT.value();  // 运动模式
            }

            TMeasureHeartrate measureHeartrate = new TMeasureHeartrate();
            measureHeartrate.setUserId(userId);
            measureHeartrate.setDeviceType(deviceType);
            measureHeartrate.setHeartRate(Integer.parseInt(heartRate));
            measureHeartrate.setMeasureDate(DateTimeUtilT.dateTime(measureDate));
            measureHeartrate.setCreateDate(new Date());
            measureHeartrate.setDataType(dataType);

            terminal.getHeartRate().save(measureHeartrate); //

            /**
             * @updateTime 2017-09-14 10:10:16
             * @author wuj
             *  每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
             */
            saveToUserInfoRead(userId, measureDate, HealthPackageType.HeartRate.value());
        }

        Integer healthProduct = appJSON.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.HeartRate);

        return success();
    }

    @Override
    public JSONObject getHeartRate(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        AppJSONDataDTO data = appJSON.getData();
        int userId = data.getUserId();
        JSONArray mm = data.getMsg();
        JSONObject mm_0 = mm.getJSONObject(0);

        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE); // 终端条件：0:：不限，1:APP
        // 2：HL-031，
        // 3：C3
        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        List<Map<String, Object>> heartRateList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的心率数据
        switch (withDate) {
            case "0":
                PaginationDTO<Map<String, Object>> paginationDTO = terminal.getHeartRate().getMeasureDataWithSplit(userId,
                        deviceType, pageIndex, pageSize);
                heartRateList = paginationDTO.getData();
                break;
            case "1":
                heartRateList = terminal.getHeartRate().getMeasureDataWithDate(userId, deviceType, HealthPackage.DAY);
                break;
            case "2": // 周
                heartRateList = terminal.getHeartRate().getMeasureDataWithDate(userId, deviceType, HealthPackage.WEEK);
                break;
            case "3": // 一月
                heartRateList = terminal.getHeartRate().getMeasureDataWithDate(userId, deviceType, HealthPackage.MONTH);
                break;
            case "4": // 三月
                heartRateList = terminal.getHeartRate().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.THREEMONTH);
                break;
            default:
                // 若传送过来的withDate是日期格式，则将withDate作为查询条件，并获取某一天的全部数据
                heartRateList = terminal.getHeartRate().getMeasureDataWithDate(userId, deviceType, withDate);
                break;
        }

        if (!withDate.equals("0")) {
            java.util.Collections.reverse(heartRateList);
        }

        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(heartRateList, extraData, true);
    }

    @Override
    public JSONObject addTemperature(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        String deviceType = appJSON.getType();
        int userId = appJSON.getData().getUserId();
        JSONArray mm = appJSON.getData().getMsg();

        JSONObject sb = new JSONObject(true);
        sb.put("dateTime", DateTimeUtilT.dateTime(new Date()));
        sb.put("userId", userId);
        sb.put("data", mm);

        for (int i = 0; i < mm.size(); i++) {
            JSONObject mm_i = mm.getJSONObject(i);
            String temperature = mm_i.getString(HealthPackage.TEMPERATURE);
            String measureDate = mm_i.getString(HealthPackage.MEASUREDATE);
            Integer manualEntry = mm.getJSONObject(i).getInteger(HealthPackage.MANUAL_ENTRY); // 新增参数，手动录入数据
            /** 默认数据类型为实时数据 */
            Integer dataType = DataTypeEnum.REAL_TIME.value();
            if (manualEntry != null && Normal.TRUE.equals(manualEntry)) {
                dataType = DataTypeEnum.MANUAL.value();
            }

            TMeasureTemperature tMeasureTemperature = new TMeasureTemperature();
            tMeasureTemperature.setUserId(userId);
            tMeasureTemperature.setTemperature(Float.parseFloat((temperature)));
            tMeasureTemperature.setMeasureDate(DateTimeUtilT.dateTime(measureDate));
            tMeasureTemperature.setDeviceType(deviceType);
            tMeasureTemperature.setCreateDate(new Date());
            tMeasureTemperature.setDataType(dataType);
            terminal.getTemperature().save(tMeasureTemperature);
        }

        Integer healthProduct = appJSON.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.Temperature);
        return success();
    }

    @Override
    public JSONObject getTemperature(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);

        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        JSONObject mm_0 = mm.getJSONObject(0);

        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月,"2015-01-01":具体日期
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE); // 终端条件：0:：不限，1:APP
        // 2：HL-031，
        // 3：C3
        String deviceType = Conversion.getDeviceType(terminalType);
        String dataType = withDate;
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }
        if (DateTimeUtilT.valiDateTimeWithFormat(withDate)) {
            dataType = "5"; // 判断若为时间格式，则将withDate作为查询条件
        }

        List<Map<String, Object>> temperatureList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
        switch (dataType) {
            case "0":
                PaginationDTO<Map<String, Object>> paginationDTO = terminal.getTemperature().getMeasureDataWithSplit(userId,
                        deviceType, pageIndex, pageSize);
                temperatureList = paginationDTO.getData();
                break;
            case "1":
                temperatureList = terminal.getTemperature().getMeasureDataWithDate(userId, deviceType, HealthPackage.DAY);
                break;
            case "2": // 周
                temperatureList = terminal.getTemperature().getMeasureDataWithDate(userId, deviceType, HealthPackage.WEEK);
                break;
            case "3": // 一月
                temperatureList = terminal.getTemperature().getMeasureDataWithDate(userId, deviceType, HealthPackage.MONTH);
                break;
            case "4": // 三月
                temperatureList = terminal.getTemperature().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.THREEMONTH);
                break;
            case "5":
                // 若传送过来的withDate是日期格式，则将withDate作为查询条件，并获取某一天的全部数据
                temperatureList = terminal.getTemperature().getMeasureDataWithDate(userId, deviceType, withDate);
                break;
            default:

                break;
        }

        if (!withDate.equals("0")) {
            java.util.Collections.reverse(temperatureList);
        }

        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(temperatureList, extraData, true);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public JSONObject addBloodPressure(String json) throws OperationException {
        AppJSON appJSON = parseAppJSON(json);
        String deviceType = appJSON.getType();
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        Integer diastolic = mm_0.getIntValue(HealthPackage.DIASTOLIC);
        Integer systolic = mm_0.getIntValue(HealthPackage.SYSTOLIC);
        Integer heartRate = mm_0.getIntValue(HealthPackage.HEARTRATE);
        String measureDate = mm_0.getString(HealthPackage.MEASUREDATE);
        String manualEntry = mm_0.getString(HealthPackage.MANUAL_ENTRY); // 新增参数，手动录入数据
        /** 默认数据类型为实时数据 */
        Integer dataType = DataTypeEnum.REAL_TIME.value();
        if (manualEntry != null && Normal.TRUE.equals(manualEntry)) {
            dataType = DataTypeEnum.MANUAL.value();
        }

        TMeasureBloodpressure tMeasureBloodpressure = new TMeasureBloodpressure();
        tMeasureBloodpressure.setUserId(userId);
        tMeasureBloodpressure.setDiastolic(diastolic);
        tMeasureBloodpressure.setSystolic(systolic);
        tMeasureBloodpressure.setHeartRate(heartRate);
        tMeasureBloodpressure.setMeasureDate(DateTimeUtilT.dateTime(measureDate));
        tMeasureBloodpressure.setDeviceType(deviceType);
        tMeasureBloodpressure.setCreateDate(new Date());
        tMeasureBloodpressure.setDataType(dataType);

        terminal.getBloodPressure().save(tMeasureBloodpressure);

        /** 测量建议 */
        List<String> advices = new ArrayList<>();
        String bloodPressureAdvice = "";

        /** 形成原因 */
        HealthRank systolicStatus = tMeasureBloodpressure.getSystolicStatus();
        HealthRank diastolicStatus = tMeasureBloodpressure.getDiastolicStatus();
        HealthRank heartRateStatus = tMeasureBloodpressure.getHeartRateStatus();
        HealthRank bloodPressureStatus = HealthRank.normal;
        if (!systolicStatus.equals(HealthRank.normal)) {
            bloodPressureStatus = systolicStatus;
            bloodPressureAdvice = tMeasureBloodpressure.getSystolicStatusDescription();
        }
        if (!diastolicStatus.equals(HealthRank.normal) && bloodPressureStatus.equals(HealthRank.normal)) {
            bloodPressureStatus = diastolicStatus;
            bloodPressureAdvice = tMeasureBloodpressure.getDiastolicStatusDescription();
        }

        advices.add(bloodPressureAdvice);
        advices.add(tMeasureBloodpressure.getHeartRateStatusDescription());

        List<ReasonSimpleDTO> params = new ArrayList<>();
        params.add(getReasonSimpleDTO(bloodPressureStatus, HealthType.systolic));
        params.add(getReasonSimpleDTO(heartRateStatus, HealthType.heartRate));
        List<String> reasons = reasons(HealthPackageType.BloodPressure, params);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(HealthPackage.REASONS, reasons);
        returnData.put(HealthPackage.ADVICES, advices);

        Integer healthProduct = appJSON.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.BloodPressure);

        /**
         * @updateTime 2017-09-14 10:10:16
         * @author wuj
         *  每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
         */
        saveToUserInfoRead(userId, measureDate, HealthPackageType.BloodPressure.value());

//        throw new OperationException("测试异常抛出OperationException.class", 404);
        return success(returnData);
    }


    @Override
    public JSONObject getBloodPressure(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);

        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        JSONObject mm_0 = mm.getJSONObject(0);

        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE);// 0:：不限，1：HL03，2：C3

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }
        List<Map<String, Object>> bloodPressureList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
        switch (withDate) {
            case "0":
                PaginationDTO<Map<String, Object>> paginationDTO = terminal.getBloodPressure()
                        .getMeasureDataWithSplit(userId, deviceType, pageIndex, pageSize);
                bloodPressureList = paginationDTO.getData();
                break;
            case "1": // 日
                bloodPressureList = terminal.getBloodPressure().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.DAY);
                break;
            case "2": // 周
                bloodPressureList = terminal.getBloodPressure().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.WEEK);
                break;
            case "3": // 一月
                bloodPressureList = terminal.getBloodPressure().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.MONTH);
                break;
            case "4": // 三月
                bloodPressureList = terminal.getBloodPressure().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.THREEMONTH);
                break;

            default:
                break;
        }

        if (!withDate.equals("0")) {
            java.util.Collections.reverse(bloodPressureList);
        }

        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(bloodPressureList, extraData, true);
    }

    @Override
    public JSONObject addOxygen(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        String deviceType = appJSON.getType();
        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        String saturation = mm_0.getString(HealthPackage.SATURATION);
        String heartRate = mm_0.getString(HealthPackage.HEARTRATE);
        String measureDate = mm_0.getString(HealthPackage.MEASUREDATE);
        String manualEntry = mm_0.getString(HealthPackage.MANUAL_ENTRY);
        Integer dataType = DataTypeEnum.REAL_TIME.value();
        if (manualEntry != null && Normal.TRUE.equals(manualEntry)) {
            dataType = DataTypeEnum.MANUAL.value();
        }

        TMeasureOxygen tMeasureOxygen = new TMeasureOxygen();
        tMeasureOxygen.setUserId(userId);
        tMeasureOxygen.setDeviceType(deviceType);
        tMeasureOxygen.setSaturation(Integer.parseInt(saturation));
        tMeasureOxygen.setHeartRate(Integer.parseInt(heartRate));
        tMeasureOxygen.setMeasureDate(DateTimeUtilT.dateTime(measureDate));
        tMeasureOxygen.setCreateDate(new Date());
        tMeasureOxygen.setDataType(dataType);

        terminal.getOxygen().save(tMeasureOxygen);

        /** 测量建议 */
        List<String> advices = new ArrayList<>();
        advices.add(tMeasureOxygen.getSaturationStatusDescription());
        advices.add(tMeasureOxygen.getHeartRateStatusDescription());

        /** 形成原因 */
        HealthRank saturationStatus = tMeasureOxygen.getSaturationStatus();
        HealthRank heartRateStatus = tMeasureOxygen.getHeartRateStatus();
        List<ReasonSimpleDTO> params = new ArrayList<>();
        params.add(getReasonSimpleDTO(saturationStatus, HealthType.saturation));
        params.add(getReasonSimpleDTO(heartRateStatus, HealthType.heartRate));
        List<String> reasons = reasons(HealthPackageType.Oxygen, params);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(HealthPackage.REASONS, reasons);
        returnData.put(HealthPackage.ADVICES, advices);

        Integer healthProduct = appJSON.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.Oxygen);

        /**
         * @updateTime 2017-09-14 10:10:16
         * @author wuj
         *  每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
         */
        saveToUserInfoRead(userId, measureDate, HealthPackageType.Oxygen.value());

        return success(returnData);
    }

    /**
     * 每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
     * t_userInfo_read 这张表记录的是服务师是否有阅读过用户的数据
     *
     * @param userId      用户ID
     * @param measureDate 测量日期 format: "yyyy-MM-dd HH:mm:ss" or "yyyy-MM-dd"
     * @param deviceType  设备INT value
     * @author wuj
     * @since 2017-09-14 10:37:33
     */
    private void saveToUserInfoRead(int userId, String measureDate, int deviceType) {
        String measureTime = measureDate.substring(0, !measureDate.contains(" ") ? measureDate.length() : measureDate.indexOf(" "));
        List<UserInfoRead> list = userInfoReadDao.selectByUserId(userId, measureTime);
        if (list.isEmpty()) {
            /**
             * 列表为空,则代表orgUser没有查看过这一天的数据,为了减轻数据库的负载,可以不用生成新的数据
             */
            /*List<Integer> orgUserIds = orderDao1.getOrgUserIds(userId);
            for (Integer orgUserId : orgUserIds) {
                UserInfoRead userInfoRead = new UserInfoRead();
                userInfoRead.setUserId(userId);
                userInfoRead.setMeasureDate(DateTimeUtilT.date(measureTime));
                userInfoRead.setOrgUserId(orgUserId);
                userInfoRead.setDevice(deviceType);
                userInfoRead.setIsRead(false);
                list.add(userInfoRead);
            }

            userInfoReadDao.batchInsert(list);*/
        } else {
            for (UserInfoRead userInfoRead : list) {
                userInfoRead.setDevice(userInfoRead.getDevice() | deviceType);
                userInfoRead.setIsRead(false); // 新数据上传,将消息改成未读
            }
            userInfoReadDao.batchUpdate(list);
        }
    }

    @Override
    public JSONObject getOxygen(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);

        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        JSONObject mm_0 = mm.getJSONObject(0);

        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE);

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        List<Map<String, Object>> oxygenList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
        switch (withDate) {
            case "0":
                PaginationDTO<Map<String, Object>> paginationDTO = terminal.getOxygen().getMeasureDataWithSplit(userId,
                        deviceType, pageIndex, pageSize);
                oxygenList = paginationDTO.getData();
                break;
            case "1":
                oxygenList = terminal.getOxygen().getMeasureDataWithDate(userId, deviceType, HealthPackage.DAY);
                break;
            case "2": // 周
                oxygenList = terminal.getOxygen().getMeasureDataWithDate(userId, deviceType, HealthPackage.WEEK);
                break;
            case "3": // 一月
                oxygenList = terminal.getOxygen().getMeasureDataWithDate(userId, deviceType, HealthPackage.MONTH);
                break;
            case "4": // 三月
                oxygenList = terminal.getOxygen().getMeasureDataWithDate(userId, deviceType, HealthPackage.THREEMONTH);
                break;
            default:
                break;
        }

        if (!withDate.equals("0")) {
            java.util.Collections.reverse(oxygenList);
        }

        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(oxygenList, extraData, true);
    }

    @Override
    public JSONObject addGlucometer(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        String deviceType = appJSON.getType();
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        String bldatadSugar = mm_0.getString(HealthPackage.BLOODSUGAR);
        String measureDate = mm_0.getString(HealthPackage.MEASUREDATE);
        int measureType = mm_0.getIntValue(HealthPackage.MEASURETYPE); // 测量类型：早餐前_1,早餐后_2,午餐前_3,午餐后_4,晚餐前_5,晚餐后_6,凌晨_7
        String manualEntry = mm_0.getString(HealthPackage.MANUAL_ENTRY);
        Integer dataType = DataTypeEnum.REAL_TIME.value();
        if (manualEntry != null && Normal.TRUE.equals(manualEntry)) {
            dataType = DataTypeEnum.MANUAL.value();
        }

        TMeasureGlucometer tMeasureGlucometer = new TMeasureGlucometer();
        tMeasureGlucometer.setUserId(userId);

        tMeasureGlucometer.setDeviceType(deviceType);
        tMeasureGlucometer.setBloodSugar(Float.parseFloat((bldatadSugar)));
        tMeasureGlucometer.setMeasureDate(DateTimeUtilT.dateTime(measureDate));
        tMeasureGlucometer.setMeasureType(measureType);
        tMeasureGlucometer.setCreateDate(new Date());
        tMeasureGlucometer.setDataType(dataType);

        terminal.getGlucometer().save(tMeasureGlucometer);

        /** 测量建议 */
        List<String> advices = new ArrayList<>();
        advices.add(tMeasureGlucometer.getBloodSugarStatusDescription());

        /** 形成原因 */
        HealthRank bloodSugarStatus = tMeasureGlucometer.getBloodSugarStatus();
        List<ReasonSimpleDTO> params = new ArrayList<>();
        params.add(getReasonSimpleDTO(bloodSugarStatus, HealthType.bloodSugar));
        List<String> reasons = reasons(HealthPackageType.Glucometer, params);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(HealthPackage.REASONS, reasons);
        returnData.put(HealthPackage.ADVICES, advices);

        Integer healthProduct = appJSON.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.Glucometer);

        /**
         * @updateTime 2017-09-14 10:10:16
         * @author wuj
         *  每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
         */
        saveToUserInfoRead(userId, measureDate, HealthPackageType.Glucometer.value());

        return success(returnData);
    }

    @Override
    public JSONObject getGlucometer(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        AppJSONDataDTO data = appJSON.getData();
        int userId = data.getUserId();
        JSONArray mm = data.getMsg();
        JSONObject mm_0 = mm.getJSONObject(0);

        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE);
        String measureType = null;
        if (mm_0.get(HealthPackage.MEASURETYPE) != null) {
            measureType = mm_0.getString(HealthPackage.MEASURETYPE); // 测量类型（可选参数）:
        }
        // 早餐前_1,早餐后_2,午餐前_3,午餐后_4,晚餐前_5,晚餐后_6
        String deviceType = Conversion.getDeviceType(terminalType);
        String dataType = withDate;
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }
        if (DateTimeUtilT.valiDateTimeWithShortFormat(withDate)) {
            dataType = "5"; // 判断若为"2016-01"时间格式，则将withDate作为查询条件
        }

        List<Map<String, Object>> glucometerList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
        int newMeasureType;
        if (measureType != null) { // 加上测量类型条件
            newMeasureType = Integer.parseInt(measureType);
            switch (dataType) {
                case "0":
                    PaginationDTO<Map<String, Object>> paginationDTO = terminal.getGlucometer()
                            .getMeasureDataWithSplit(userId, deviceType, pageIndex, pageSize, newMeasureType);
                    glucometerList = paginationDTO.getData();
                    break;
                case "1":
                    glucometerList = terminal.getGlucometer().getMeasureDataWithDate(userId, deviceType, HealthPackage.DAY,
                            newMeasureType);
                    break;
                case "2": // 周
                    glucometerList = terminal.getGlucometer().getMeasureDataWithDate(userId, deviceType, HealthPackage.WEEK,
                            newMeasureType);
                    break;
                case "3": // 一月
                    glucometerList = terminal.getGlucometer().getMeasureDataWithDate(userId, deviceType,
                            HealthPackage.MONTH, newMeasureType);
                    break;
                case "4": // 三月
                    glucometerList = terminal.getGlucometer().getMeasureDataWithDate(userId, deviceType,
                            HealthPackage.THREEMONTH, newMeasureType);
                    break;
                case "5": // 获取具体年月份全部数据
                    glucometerList = terminal.getGlucometer().getMeasureDataWithDate(userId, deviceType, withDate,
                            newMeasureType);
                    break;

                default:
                    break;
            }

        } else {
            switch (dataType) {
                case "0":
                    PaginationDTO<Map<String, Object>> paginationDTO = terminal.getGlucometer()
                            .getMeasureDataWithSplit(userId, deviceType, pageIndex, pageSize);
                    glucometerList = paginationDTO.getData();
                    break;
                case "1":
                    glucometerList = terminal.getGlucometer().getMeasureDataWithDate(userId, deviceType, HealthPackage.DAY);
                    break;
                case "2": // 周
                    glucometerList = terminal.getGlucometer().getMeasureDataWithDate(userId, deviceType,
                            HealthPackage.WEEK);
                    break;
                case "3": // 一月
                    glucometerList = terminal.getGlucometer().getMeasureDataWithDate(userId, deviceType,
                            HealthPackage.MONTH);
                    break;
                case "4": // 三月
                    glucometerList = terminal.getGlucometer().getMeasureDataWithDate(userId, deviceType,
                            HealthPackage.THREEMONTH);
                    break;
                case "5": // 获取具体年月份全部数据
                    glucometerList = terminal.getGlucometer().getMeasureDataWithDate(userId, deviceType, withDate);
                    break;

                default:
                    break;
            }
        }

        // 重新组装list
        List<Map<String, Object>> newList = new ArrayList<>();
        for (int i = 0; i < glucometerList.size(); i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            List<Map<String, Object>> list2 = new ArrayList<>();
            Map<Integer, Map<String, Object>> mapReplaceList = new HashMap<>();

            Timestamp measureDate_old_t = (Timestamp) glucometerList.get(i).get("measureDate");
            String measureDate_old = DateTimeUtilT.date(measureDate_old_t);
            hashMap.put(HealthPackage.MEASUREDATE, DateTimeUtilT.dateTime(measureDate_old_t));

            int listSize = glucometerList.size();
            for (int j = i; j < listSize; j++) {

                Timestamp measureDate_new_t = (Timestamp) glucometerList.get(j).get("measureDate");
                String measureDate_new = DateTimeUtilT.date(measureDate_new_t);

                if (measureDate_old.equals(measureDate_new)) {
                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    HashMap<String, Object> hashMap3 = new HashMap<>();

                    hashMap3.put(HealthPackage.STATUS, glucometerList.get(j).get("status") + "");
                    hashMap3.put(HealthPackage.BLOODSUGAR, (glucometerList.get(j).get("bloodSugar") + ""));
                    hashMap3.put(HealthPackage.BLOODSUGAR_AREA, glucometerList.get(j).get("bloodSugarArea"));
                    hashMap3.put(HealthPackage.MEASURETYPE, glucometerList.get(j).get("measureType") + "");
                    hashMap3.put(HealthPackage.MEASUREDATE, DateTimeUtilT.dateTime(measureDate_new_t));
                    hashMap2.put(HealthPackage.CHILD_DATA, hashMap3);

                    /**
                     * 将数据保存到map中，如果存在相同日期，并且相同的measureType的数据，就会被最后一条顶替掉
                     * 最后保存到这个map中的数据（相同的measureType）就会剩下0条或者1条
                     */
                    if (glucometerDataMeasureTypeSingle(withDate)) {
                        mapReplaceList.put((Integer) glucometerList.get(j).get("measureType"), hashMap2);
                    } else {
                        list2.add(hashMap2);
                    }

                    glucometerList.remove(j);
                    listSize = listSize - 1;
                    j--;
                }
            }

            if (glucometerDataMeasureTypeSingle(withDate)) {
                for (Integer key : mapReplaceList.keySet()) {
                    list2.add(mapReplaceList.get(key));
                }
            }

            hashMap.put(HealthPackage.PARAM_DATA, list2);
            newList.add(hashMap);
            i--;
        }

        if (!dataType.equals("0")) {
            Collections.reverse(newList);
        }

        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(newList, extraData, true);
    }

    @Override
    public JSONObject addLunginstrument(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        String deviceType = appJSON.getType();
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        int vitalCapacity = mm_0.getIntValue(HealthPackage.VITALCAPACITY);
        float pef = mm_0.getFloatValue(HealthPackage.PEF);
        float af = mm_0.getFloatValue(HealthPackage.AF);
        String measureDate = mm_0.getString(HealthPackage.MEASUREDATE);
        String manualEntry = mm_0.getString(HealthPackage.MANUAL_ENTRY);
        Integer dataType = DataTypeEnum.REAL_TIME.value();
        if (manualEntry != null && Normal.TRUE.equals(manualEntry)) {
            dataType = DataTypeEnum.MANUAL.value();
        }

        TMeasureLunginstrument tMeasureLungInstrumentr = new TMeasureLunginstrument();

        // Integer status_Integer = null;
        // if (StringUtils.isNotBlank(status)) {
        // status_Integer = Integer.parseInt(status);
        // }

        tMeasureLungInstrumentr.setUserId(userId);
        tMeasureLungInstrumentr.setDeviceType(deviceType);
        tMeasureLungInstrumentr.setVitalCapacity(vitalCapacity);
        tMeasureLungInstrumentr.setPef(pef);
        tMeasureLungInstrumentr.setAf(af);
        tMeasureLungInstrumentr.setMeasureDate(DateTimeUtilT.dateTime(measureDate));
        tMeasureLungInstrumentr.setCreateDate(new Date());
        tMeasureLungInstrumentr.setDataType(dataType);

        terminal.getLunginstrument().save(tMeasureLungInstrumentr);

        /** 测量建议 */
        List<String> advices = new ArrayList<>();
        advices.add(tMeasureLungInstrumentr.getVitalCapacityStatusDescription());

        /** 形成原因 */
        HealthRank vitalCapacityStatus = tMeasureLungInstrumentr.getVitalCapacityStatus();
        List<ReasonSimpleDTO> params = new ArrayList<>();
        params.add(getReasonSimpleDTO(vitalCapacityStatus, HealthType.vitalCapacity));
        List<String> reasons = reasons(HealthPackageType.Lunginstrument, params);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(HealthPackage.REASONS, reasons);
        returnData.put(HealthPackage.ADVICES, advices);

        Integer healthProduct = appJSON.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.Lunginstrument);

        /**
         * @updateTime 2017-09-14 10:10:16
         * @author wuj
         *  每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
         */
        saveToUserInfoRead(userId, measureDate, HealthPackageType.Lunginstrument.value());

        return success(returnData);
    }

    @Override
    public JSONObject getLunginstrument(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);

        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        JSONObject mm_0 = mm.getJSONObject(0);

        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE);

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        List<Map<String, Object>> lunginstrumentList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
        switch (withDate) {
            case "0":
                PaginationDTO<Map<String, Object>> paginationDTO = terminal.getLunginstrument()
                        .getMeasureDataWithSplit(userId, deviceType, pageIndex, pageSize);
                lunginstrumentList = paginationDTO.getData();
                break;
            case "1":
                lunginstrumentList = terminal.getLunginstrument().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.DAY);
                break;
            case "2": // 周
                lunginstrumentList = terminal.getLunginstrument().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.WEEK);
                break;
            case "3": // 一月
                lunginstrumentList = terminal.getLunginstrument().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.MONTH);
                break;
            case "4": // 三月
                lunginstrumentList = terminal.getLunginstrument().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.THREEMONTH);
                break;

            default:
                break;
        }

        if (!withDate.equals("0")) {
            java.util.Collections.reverse(lunginstrumentList);
        }

        /**
         * 计算用户肺活量分数
         */
        UserRecord userRecord = userRecordDao.selectByUserId(userId);
        for (Map<String, Object> map : lunginstrumentList) {
            if (map.containsKey("vitalCapacity")){
                int score = getLunginstrumentScore((int) map.get("vitalCapacity"),
                        userRecord.getGender(), userRecord.getBirthday());
                map.put("score", score);
            }
        }

        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(lunginstrumentList, extraData, true);
    }

    /**
     * 计算用户肺活量的评分
     *
     * @param value
     * @param sex 用户性别
     * @param birthday 出生日期
     * @return
     */
    private int getLunginstrumentScore(int value, boolean sex, Date birthday) {
        int age = DateTimeUtilT.calculateAge(birthday);
        HealthStandardValueEx<Integer> hv = HealthStandard.getVitalCapacity(sex, age);
        Integer less = hv.getLess(); // 暂时没用
        Integer min = hv.getMin();
        Integer max = hv.getMax();
        Integer more = hv.getMore(); // 暂时没用

        if (value < less)
            return 1;
        if (value < min)
            return 2;
        if (value < max)
            return 3;
        if (value < more)
            return 4;
        return 5;
    }

    @Override
    public JSONObject addBodyfatscale(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        String deviceType = appJSON.getType();
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        String weight = mm_0.getString(HealthPackage.WEIGHT);
        String axungeRatio = mm_0.getString(HealthPackage.AXUNGERATIO);
        String WHR = mm_0.getString(HealthPackage.WHR);
        String BMI = mm_0.getString(HealthPackage.BMI);
        String fatFreeWeight = mm_0.getString(HealthPackage.FATE_FREE_WEIGHT);
        String muscle = mm_0.getString(HealthPackage.MUSCLE);
        String moisture = mm_0.getString(HealthPackage.MOISTURE);
        String boneWeight = mm_0.getString(HealthPackage.BONE_WEIGHT);
        String bodyage = mm_0.getString(HealthPackage.BODY_AGE);
        String baseMetabolism = mm_0.getString(HealthPackage.BASE_METABOLISM);
        String measureDate = mm_0.getString(HealthPackage.MEASUREDATE);
        String manualEntry = mm_0.getString(HealthPackage.MANUAL_ENTRY);
        Integer dataType = DataTypeEnum.REAL_TIME.value();
        if (manualEntry != null && Normal.TRUE.equals(manualEntry)) {
            dataType = DataTypeEnum.MANUAL.value();
        }

        // TODO 新追加的数据 蛋白质 + 内脏脂肪
        String proteide = mm_0.getString(HealthPackage.PROTEIDE);
        String visceralFat = mm_0.getString(HealthPackage.VISCERALFAT);

        TMeasureBodyfatscale tMeasureBodyfatscaler = new TMeasureBodyfatscale();
        tMeasureBodyfatscaler.setUserId(userId);
        tMeasureBodyfatscaler.setDeviceType(deviceType);
        if (StringUtils.isNotBlank(axungeRatio)) {
            tMeasureBodyfatscaler.setAxungeRatio(new Float(axungeRatio));
        }
        if (StringUtils.isNotBlank(baseMetabolism)) {
            tMeasureBodyfatscaler.setBaseMetabolism((new Double(baseMetabolism).floatValue()));
        }
        if (StringUtils.isNotBlank(BMI)) {
            tMeasureBodyfatscaler.setBMI(new Float(BMI));
        }
        if (StringUtils.isNotBlank(bodyage)) {
            tMeasureBodyfatscaler.setBodyage((new Double(bodyage)).intValue());
        }
        if (StringUtils.isNotBlank(boneWeight)) {
            tMeasureBodyfatscaler.setBoneWeight(new Float(boneWeight));
        }
        if (StringUtils.isNotBlank(fatFreeWeight)) {
            tMeasureBodyfatscaler.setFatFreeWeight(new Float(fatFreeWeight));
        }
        if (StringUtils.isNotBlank(moisture)) {
            tMeasureBodyfatscaler.setMoisture(new Float(moisture));
        }
        if (StringUtils.isNotBlank(muscle)) {
            tMeasureBodyfatscaler.setMuscle(new Float(muscle));
        }
        if (StringUtils.isNotBlank(weight)) {
            tMeasureBodyfatscaler.setWeight(new Float(weight));
        }
        if (StringUtils.isNotBlank(WHR)) {
            tMeasureBodyfatscaler.setWHR(new Float(WHR));
        }
        tMeasureBodyfatscaler.setMeasureDate(DateTimeUtilT.dateTime(measureDate));
        tMeasureBodyfatscaler.setCreateDate(new Date());
        tMeasureBodyfatscaler.setDataType(dataType);

        // TODO 新追加的数据 蛋白质 + 内脏脂肪
        if (StringUtils.isNotBlank(proteide)) {
            tMeasureBodyfatscaler.setProteide(new Float(proteide));
        }
        if (StringUtils.isNotBlank(visceralFat)) {
            tMeasureBodyfatscaler.setVisceralFat(new Float(visceralFat));
        }

        terminal.getBodyfatscale().save(tMeasureBodyfatscaler);

        /** 测量建议 */
        List<String> advices = new ArrayList<>();
        advices.add(tMeasureBodyfatscaler.getWeightStatusDescription());
        advices.add(tMeasureBodyfatscaler.getAxungeRatioStatusDescription());
        advices.add(tMeasureBodyfatscaler.getBaseMetabolismStatusDescription());
        advices.add(tMeasureBodyfatscaler.getBMIStatusDescription());
        advices.add(tMeasureBodyfatscaler.getBoneWeightStatusDescription());
        advices.add(tMeasureBodyfatscaler.getBodyageStatusDescription());
        advices.add(tMeasureBodyfatscaler.getFatFreeWeightStatusDescription());
        advices.add(tMeasureBodyfatscaler.getMoistureStatusDescription());
        advices.add(tMeasureBodyfatscaler.getMuscleStatusDescription());
        advices.add(tMeasureBodyfatscaler.getWHRStatusDescription());
        advices.add(tMeasureBodyfatscaler.getProteideStatusDescription());
        advices.add(tMeasureBodyfatscaler.getVisceralFatStatusDescription());
        Iterator<String> iterator = advices.iterator();
        while (iterator.hasNext()) {
            String advice = iterator.next();
            if (StringUtils.isBlank(advice)) {
                iterator.remove();
            }
        }

        /** 形成原因 */
        HealthRank weightStatus = tMeasureBodyfatscaler.getWeightStatus();
        HealthRank axungeRatioStatus = tMeasureBodyfatscaler.getAxungeRatioStatus();
        HealthRank baseMetabolismStatus = tMeasureBodyfatscaler.getBaseMetabolismStatus();
        HealthRank bMIStatus = tMeasureBodyfatscaler.getBMIStatus();
        HealthRank boneWeightStatus = tMeasureBodyfatscaler.getBoneWeightStatus();
        HealthRank bodyageStatus = tMeasureBodyfatscaler.getBodyageStatus();
        HealthRank fatFreeWeightStatus = tMeasureBodyfatscaler.getFatFreeWeightStatus();
        HealthRank moistureStatus = tMeasureBodyfatscaler.getMoistureStatus();
        HealthRank muscleStatus = tMeasureBodyfatscaler.getMuscleStatus();
        HealthRank wHRStatus = tMeasureBodyfatscaler.getWHRStatus();
        HealthRank proteideStatus = tMeasureBodyfatscaler.getProteideStatus();
        HealthRank visceralFatStatus = tMeasureBodyfatscaler.getVisceralFatStatus();

        List<ReasonSimpleDTO> params = new ArrayList<>();
        params.add(getReasonSimpleDTO(weightStatus, HealthType.weight));
        params.add(getReasonSimpleDTO(axungeRatioStatus, HealthType.axungeRatio));
        params.add(getReasonSimpleDTO(baseMetabolismStatus, HealthType.baseMetabolism));
        params.add(getReasonSimpleDTO(bMIStatus, HealthType.BMI));
        params.add(getReasonSimpleDTO(boneWeightStatus, HealthType.boneWeight));
        params.add(getReasonSimpleDTO(bodyageStatus, HealthType.bodyage));
        params.add(getReasonSimpleDTO(fatFreeWeightStatus, HealthType.fatFreeWeight));
        params.add(getReasonSimpleDTO(moistureStatus, HealthType.moisture));
        params.add(getReasonSimpleDTO(muscleStatus, HealthType.muscle));
        params.add(getReasonSimpleDTO(wHRStatus, HealthType.WHR));
        params.add(getReasonSimpleDTO(proteideStatus, HealthType.proteide));
        params.add(getReasonSimpleDTO(visceralFatStatus, HealthType.visceralFat));
        List<String> reasons = reasons(HealthPackageType.BodyFatScale, params);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(HealthPackage.REASONS, reasons);
        returnData.put(HealthPackage.ADVICES, advices);

        Integer healthProduct = appJSON.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.BodyFatScale);

        /**
         * @updateTime 2017-09-14 10:10:16
         * @author wuj
         *  每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
         */
        saveToUserInfoRead(userId, measureDate, HealthPackageType.BodyFatScale.value());

        return success(returnData);
    }

    @Override
    public JSONObject getBodyfatscale(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);

        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        JSONObject mm_0 = mm.getJSONObject(0);

        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月， 5：按年
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE);

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        List<Map<String, Object>> bodyfatscaleList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
        switch (withDate) {
            case "0":
                PaginationDTO<Map<String, Object>> paginationDTO = terminal.getBodyfatscale()
                        .getMeasureDataWithSplit(userId, deviceType, pageIndex, pageSize);
                bodyfatscaleList = paginationDTO.getData();
                break;
            case "1":
                bodyfatscaleList = terminal.getBodyfatscale().getMeasureDataWithDate(userId, deviceType, HealthPackage.DAY);
                break;
            case "2": // 周
                bodyfatscaleList = terminal.getBodyfatscale().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.WEEK);
                break;
            case "3": // 一月
                bodyfatscaleList = terminal.getBodyfatscale().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.MONTH);
                break;
            case "4": // 三月
                bodyfatscaleList = terminal.getBodyfatscale().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.THREEMONTH);
                break;
            case "5": // 一年
                bodyfatscaleList = terminal.getBodyfatscale().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.YEAR);
                break;
            default:
                break;
        }

        if (!withDate.equals("0")) {
            java.util.Collections.reverse(bodyfatscaleList);
        }

        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(bodyfatscaleList, extraData, true);
    }

    @Override
    public JSONObject addBand(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        String deviceType = appJSON.getType();
        int userId = appJSON.getData().getUserId();
        JSONArray mm = appJSON.getData().getMsg();
        for (int i = 0; i < mm.size(); i++) {
            JSONObject mm_i = mm.getJSONObject(i);

            String date = mm_i.getString(HealthPackage.DATE);
            Integer steps = mm_i.getInteger(HealthPackage.STEP);// 总步数
            Integer mileage = mm_i.getInteger(HealthPackage.MILEAGE);// 总里程
            Integer kcal = mm_i.getInteger(HealthPackage.KCAL);// 卡路程
            Integer shallowDuration = mm_i.getInteger(HealthPackage.SHALLOW_DURATION);// 浅度睡眠时长(分钟)
            Integer deepDuration = mm_i.getInteger(HealthPackage.DEEP_DURATION);// 深度睡眠时长(分钟)
            Integer wakeupDuration = mm_i.getInteger(HealthPackage.WAKEUP_DURATION);// 苏醒时长(分钟)

            // 如果时间格式为yyyy-MM-dd HH:mm:ss 转换为 yyyy-MM-dd
            if (date.length() > 10) {
                date = DateTimeUtilT.date(DateTimeUtilT.dateTime(date));
            }

            TSportBand band = new TSportBand();
            band.setUserId(userId);
            band.setCreateDate(new Date());
            band.setDeviceType(deviceType);
            band.setDate(DateTimeUtilT.date(date));

            band.setShallowDuration(shallowDuration);
            band.setDeepDuration(deepDuration);
            band.setWakeupDuration(wakeupDuration);

            band.setKcal(kcal);
            band.setSteps(steps);
            band.setMileage(mileage);
            terminal.getBand().save(band);

            /**
             * @updateTime 2017-09-14 10:10:16
             * @author wuj
             *  每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
             */
            saveToUserInfoRead(userId, date, HealthPackageType.Band.value());
        }

        Integer healthProduct = appJSON.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.Band);


        return success();
    }

    @Override
    public JSONObject getBand(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);

        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        JSONObject mm_0 = mm.getJSONObject(0);

        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE);

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        List<Map<String, Object>> bandList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
        switch (withDate) {
            case "0":
                PaginationDTO<Map<String, Object>> paginationDTO = terminal.getBand().getMeasureDataWithSplit(userId,
                        deviceType, pageIndex, pageSize);
                bandList = paginationDTO.getData();
                break;
            case "1":
                bandList = terminal.getBand().getMeasureDataWithDate(userId, deviceType, HealthPackage.DAY);
                break;
            case "2": // 周
                bandList = terminal.getBand().getMeasureDataWithDate(userId, deviceType, HealthPackage.WEEK);
                break;
            case "3": // 一月
                bandList = terminal.getBand().getMeasureDataWithDate(userId, deviceType, HealthPackage.MONTH);
                break;
            case "4": // 三月
                bandList = terminal.getBand().getMeasureDataWithDate(userId, deviceType, HealthPackage.THREEMONTH);
                break;
            default: // 若传送过来的withDate是日期格式，则将withDate作为查询条件，并获取某一天的全部数据
                bandList = terminal.getBand().getMeasureDataWithDate(userId, deviceType, withDate);
                break;
        }

        if (!withDate.equals("0")) {
            java.util.Collections.reverse(bandList);
        }

        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(bandList, extraData, true);
    }

    @Override
    public JSONObject getBandDetail(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件
        String type = mm_0.getString("type");// 类型：sleep:睡眠,steps:计步
        String terminalType = mm_0.getString(Terminal.TYPE);
        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        TSportBand band = terminal.getBand().getBandData(userId, withDate);
        Map<String, Object> b = new HashMap<>();
        if (band != null) {
            b.put(HealthPackage.DATE, band.getDate());
            switch (type) {
                case "sleep":
                    b.put(HealthPackage.DEEP_DURATION, band.getDeepDuration());
                    b.put(HealthPackage.SLEEP_DURATION, band.getSleepDuration());
                    b.put(HealthPackage.SHALLOW_DURATION, band.getShallowDuration());
                    b.put(HealthPackage.WAKEUP_DURATION, band.getWakeupDuration());
                    break;
                case "steps":
                    b.put(HealthPackage.DATE, band.getDate());
                    b.put(HealthPackage.KCAL, band.getKcal());
                    b.put(HealthPackage.STEP, band.getSteps());
                    b.put(HealthPackage.MILEAGE, band.getMileage());
                    break;
            }
        }

        return success(b, true);
    }

    @Override
    public JSONObject getAllData(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String date = mm_0.getString(HealthPackage.WITH_DATE);
        String terminalType = mm_0.getString(Terminal.TYPE); // 终端条件：0:不限，1:APP
        // 2：HL-031
        // 3：C3
        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }
        UserDTO user = appJSON.getAopData().getUser();
        if (mm_0.containsKey(User.ID)) {
            user = memberService.getUser(mm_0.getIntValue(User.ID));
        }
        List<Map<String, Object>> list = getUserAllData(deviceType, user, date, false);
        if (list.size() == 1) {
            Object v = list.get(0).get("device");
            if (v != null) {
                return success(NormalMessage.NO_DATA);
            }
        }
        // 移除device
        Iterator<Map<String, Object>> iterator = list.iterator();
        while(iterator.hasNext()) {
            if (iterator.next().containsKey("device")) {
                iterator.remove();
            }
        }
        
        return success(list);
    }

    @Override
    public JSONObject getAllData2(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String date = mm_0.getString(HealthPackage.WITH_DATE);
        String terminalType = mm_0.getString(Terminal.TYPE); // 终端条件：0:不限，1:APP
        // 2：HL-031
        // 3：C3
        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }
        UserDTO user = appJSON.getAopData().getUser();
        if (mm_0.containsKey(User.ID)) {
            user = memberService.getUser(mm_0.getIntValue(User.ID));
        }
        
        Map<String, Object> returnData = new HashMap<>();
        
        if (StringUtils.isBlank(date)) {
            date = recordService.getLastDateWithinData(user.getId());
            if (date == null) {
                return success(NormalMessage.NO_DATA);
            }
        }
        // 测量数据列表
        List<Map<String, Object>> measureReturnData = getUserAllData(deviceType, user, date, false);
        
        if (measureReturnData.size() == 1) {
            Object v = measureReturnData.get(0).get("device");
            if (v != null) {
                // 暂未有数据
                return success(NormalMessage.NO_DATA);
            }
        }
        // 移除device
        Iterator<Map<String, Object>> iterator = measureReturnData.iterator();
        while(iterator.hasNext()) {
            if (iterator.next().containsKey("device")) {
                iterator.remove();
            }
        }
        
        // 批注列表
        List<Map<String, Object>> analysisReturnDataList = new ArrayList<>();
        List<MeasureAnalysisPO> analysisDataList = measureAnalysisService.listAnalysis(user.getId(), DateTimeUtilT.date(date));
        if (!analysisDataList.isEmpty()) {
            analysisReturnDataList = enclosureMeasureAnalysisList(analysisDataList);
        }
        
        // 批注状态修改为已读
        measureAnalysisService.readAnalysis(user.getId(), DateTimeUtilT.date(date));
        
        returnData.put("analysis", analysisReturnDataList);
        returnData.put("measureData", measureReturnData);
        return success(returnData);
    }

    private List<Map<String, Object>> enclosureMeasureAnalysisList(List<MeasureAnalysisPO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (MeasureAnalysisPO data : dataList) {
            // 需要过滤没有回复的记录
            if (StringUtils.isNotBlank(data.getReply())) {
                returnDataList.add(enclosureMeasureAnalysis(data));
            }
        }
        return returnDataList;
    }

    private Map<String, Object> enclosureMeasureAnalysis(MeasureAnalysisPO data) {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("doctorSign", data.getDoctorSign());
        returnData.put("content", data.getReply());
        return returnData;
    }

    @Override
    public JSONObject getDataCount(String json) {
        AppJSON appJson = parseAppJSON(json);
        int userId = appJson.getData().getUserId();
        JSONObject mm_0 = appJson.getData().getFirstJSONObject();

        String deviceType = mm_0.getString("deviceType"); // 设备类型(BloodPressure、Oxygen、BodyFatScale、Band、Glucometer、Lunginstrument、ECG、Temperature)
        String terminalType_1 = mm_0.getString(Terminal.TYPE); // 终端类型
        String terminalType = Conversion.getDeviceType(terminalType_1);
        HashMap<String, Object> map = new HashMap<>();
        int count = 0; // 设备数据总条数

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("deviceType", terminalType);
        switch (deviceType) {
            case "BloodPressure":
                count = terminal.getDeviceService().selectDeviceDataCountByMeasureDate(TMeasureBloodpressure.class, params);
                break;
            case "Oxygen":
                count = terminal.getCommonTrans().getEntityAmount(TMeasureOxygen.class, "userId", userId);
                break;
            case "BodyFatScale":
                count = terminal.getCommonTrans().getEntityAmount(TMeasureBodyfatscale.class, "userId", userId);
                break;
            case "Band": // 查询心率手环计步、睡眠数据
                count = terminal.getCommonTrans().getEntityAmount(TSportBand.class, "userId", userId);
                break;
            case "Heartrate": // 查询心率手环心率数据
                count = terminal.getCommonTrans().getEntityAmount(TMeasureHeartrate.class, "userId", userId);
                break;
            case "Glucometer":
                count = terminal.getCommonTrans().getEntityAmount(TMeasureGlucometer.class, "userId", userId);
                break;
            case "Lunginstrument":
                count = terminal.getDeviceService().selectDeviceDataCountByMeasureDate(TMeasureLunginstrument.class,
                        params);
                break;
            case "Temperature":
                count = terminal.getCommonTrans().getEntityAmount(TMeasureTemperature.class, "userId", userId);
                break;
            case "ECG":

                break;

            default:
                break;
        }
        map.put("count", count);
        return success(map);
    }

    /**
     * 血糖数据单一处理
     *
     * @param withDate
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月25日 上午9:52:42
     */
    private boolean glucometerDataMeasureTypeSingle(String withDate) {
        boolean flag = false;
        switch (withDate) {
            case "0":
            case "1":
                flag = false;
                break;
            case "2":
            case "3":
            case "4":
                flag = true;
                break;
            default:
                flag = false;
        }
        return flag;
    }

    /**
     * 获取一个形成原因精简版对象
     *
     * @param rank
     * @param type
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月15日 下午4:32:12
     */
    private ReasonSimpleDTO getReasonSimpleDTO(HealthRank rank, HealthType type) {
        ReasonSimpleDTO simpleDTO = new ReasonSimpleDTO();
        simpleDTO.setHealthParamBinaryValue(type.value());
        simpleDTO.setStatus(rank);
        return simpleDTO;
    }

    /**
     * 获取形成原因
     *
     * @param packageType
     * @param params
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月15日 上午11:44:12
     */
    private List<String> reasons(HealthPackageType packageType, List<ReasonSimpleDTO> params) {
        List<String> reasons = new ArrayList<>();
        Iterator<ReasonSimpleDTO> iterator = params.iterator();
        while (iterator.hasNext()) {
            ReasonSimpleDTO param = iterator.next();
            if (param.getStatus().equals(HealthRank.normal)) {
                iterator.remove();
            }
        }
        if (params.size() == 0) {
            return reasons;
        }
        List<ReasonDTO> reasonDTOs = reasonService.listReason(packageType, false);
        for (ReasonDTO reasonDTO : reasonDTOs) {
            for (ReasonSimpleDTO param : params) {
                int paramStatus = param.getStatus().getRankValue();
                if (paramStatus == HealthRank.less.getRankValue()) {
                    paramStatus = HealthRank.min.getRankValue();
                }
                if (paramStatus == HealthRank.more.getRankValue()) {
                    paramStatus = HealthRank.max.getRankValue();
                }
                /** 状态正常没有形成原因 */
                if (HealthRank.normal.getRankValue() == paramStatus) {
                    continue;
                }

                boolean sameStatus = reasonDTO.getStatus() == paramStatus;
                boolean sameParam = reasonDTO.getHealthParamBinaryValue() == param.getHealthParamBinaryValue();
                if (sameStatus && sameParam) {
                    reasons.add(reasonDTO.getReason());
                }
            }
        }
        return reasons;
    }

    private HealthRank transformFMRank2HealthRank(FMRank fmRank) {
        int value = fmRank.getValue();
        for (HealthRank hr : HealthRank.values()) {
            if (hr.getRankValue().intValue() == value) {
                return hr;
            }
        }
        return HealthRank.normal;
    }

    @Override
    public JSONObject addEcg(String json) throws OperationException {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        String deviceType = appJSON.getType();
        JSONArray mm = appJSON.getData().getMsg();
        
        EcgDTO ecgDTO = new EcgDTO();
        ecgDTO.setUserId(userId);
        ecgDTO.setDeviceType(deviceType);
        ecgDTO.setDataType(1);
        Date date = null;   // 心电记录时间
        List<EcgDetailDTO> detailDTOList = new ArrayList<>();
        for (int i = 0; i < mm.size(); i++) {
            JSONObject mm_i = mm.getJSONObject(i);
            int heartRate = mm_i.getIntValue(Ecg.HEART_RATE);
            String measureDate = mm_i.getString(HealthPackage.MEASUREDATE);
            String image = mm_i.getString(Ecg.IMAGE);
            Integer signType = mm_i.getInteger(Ecg.SIGN_TYPE);
            String tags = mm_i.getString(Ecg.TAGS);
            Integer rhythmId = mm_i.getInteger(Rhythm.ID);
            String ecgImgNetPath = "";
            if (StringUtils.isNotBlank(image)) {
                ImageDTO imageDTO = uploadPhoto(image, null, "ecg", false);
                if (imageDTO.getUploadSuccess()) {
                    ecgImgNetPath = imageDTO.getNetPath();
                }
            }

            // 如果signType为null说明数据没有异常，只需要做一次测量记录
            if (signType != null) {
                EcgDetailDTO detailDTO = new EcgDetailDTO();
                detailDTO.setEcgMeasureId(null);    // 填写id交由后面的service去完成,这里直接设置为null
                detailDTO.setHeartRate(heartRate);
                detailDTO.setMeasureDate(DateTimeUtilT.dateTime(measureDate));
                detailDTO.setImage(ecgImgNetPath);
                detailDTO.setSignType(signType);
                detailDTO.setTags(tags);
                detailDTO.setRhythmId(rhythmId);
                detailDTOList.add(detailDTO);
            }
            
            if (i == 0) {
                // 获取第一个数据的测量时间作为记录时间
                date = DateTimeUtilT.date(measureDate);
            }
        }
        
        ecgDTO.setDate(date);
        ecgDTO.setDetailList(detailDTOList);
        terminal.getEcg().save(ecgDTO);

        Integer healthProduct = appJSON.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.ECG);

        /**
         * @updateTime 2017-09-14 10:10:16
         * @author wuj
         *  每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
         */
        saveToUserInfoRead(userId, DateTimeUtilT.date(date), HealthPackageType.ECG.value());

        return success();
    }

    @Override
    public JSONObject getEcg(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE);

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        List<EcgHistoryDTO> hs = new ArrayList<>();// 存放符合日期条件的体温数据
        switch (withDate) {
            case "0":
                hs = terminal.getEcg().getMeasureDataWithSplit(userId, deviceType,
                        pageIndex, pageSize).getData();
                break;
            case "2": // 周
                hs = terminal.getEcg().getMeasureDataWithDate(userId, deviceType, HealthPackage.WEEK);
                break;
            case "3": // 一月
                hs = terminal.getEcg().getMeasureDataWithDate(userId, deviceType, HealthPackage.MONTH);
                break;
            case "4": // 三月
                hs = terminal.getEcg().getMeasureDataWithDate(userId, deviceType, HealthPackage.THREEMONTH);
                break;
            default:
                break;
        }

        List<Map<String, Object>> returnDatas = new ArrayList<>();
        // 对数据进行反转排序
        if (!withDate.equals("0")) {
            java.util.Collections.reverse(hs);
            
            for (EcgHistoryDTO h : hs) {
                int autoSize = 0;   // 自动标记的数据量
                int activeSize = 0; // 主动标记的数据量
                for (EcgDTO e : h.getDatas()) {
                    for (int i = 0; i < e.getDetailList().size(); i ++) {
                        EcgDetailDTO ed = e.getDetailList().get(i);
                        if (0 == ed.getSignType().intValue()) {
                            autoSize++;
                        }
                        if (1 == ed.getSignType().intValue()) {
                            activeSize++;
                        }
                    }
                }
                Map<String, Object> returnData = new HashMap<>();
                returnData.put(Ecg.DATE, h.getMeasureDate());
                returnData.put(Ecg.AUTO_SIZE, autoSize);
                returnData.put(Ecg.ACTIVE_SIZE, activeSize);

                returnDatas.add(returnData);
            }
        } else {
            // 分页获取
            // 将数据按 日期->测量次数 排序
            for (EcgHistoryDTO h : hs) {
                for (EcgDTO e : h.getDatas()) {
                    int autoSize = 0;
                    int activeSize = 0;
                    Date measureDate = e.getDate();
                    for (int i = 0; i < e.getDetailList().size(); i ++) {
                        EcgDetailDTO ed = e.getDetailList().get(i);
                        if (0 == ed.getSignType().intValue()) {
                            autoSize ++;
                        }
                        if (1 == ed.getSignType().intValue()) {
                            activeSize ++;
                        }
                        if (i == 0) {
                            measureDate = ed.getMeasureDate();
                        } else {
                            measureDate = ed.getMeasureDate().after(measureDate) ? measureDate : ed.getMeasureDate();
                        }
                    } 
                    Map<String, Object> returnData = new HashMap<>();
                    returnData.put(Ecg.DATE, DateTimeUtilT.dateTime(measureDate));
                    returnData.put(Ecg.AUTO_SIZE, autoSize);
                    returnData.put(Ecg.ACTIVE_SIZE, activeSize);
                    returnDatas.add(returnData);
                }
            }
        }
        
        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(returnDatas, extraData);
    }

    @Override
    public JSONObject getEcgWithDate(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        String withDate = mm_0.getString(Ecg.DATE);
        String terminalType = mm_0.getString(Terminal.TYPE);
        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        List<EcgHistoryDTO> hs = terminal.getEcg().getMeasureDataWithDate(userId, deviceType, withDate);
        Map<String, Object> returnData = new HashMap<>();
        if (hs.size() > 0) {
            List<EcgDTO> hs_0 = hs.get(0).getDatas();
            returnData.put(Ecg.AUTO_RECORD, ecgAutoMarkCount(hs_0));
            returnData.put(Ecg.ACTIVE_RECORD, ecgActiveMarkCount(hs_0));
        }

        return success(returnData, true);
    }

    private List<Map<String, Object>> ecgAutoMarkCount(List<EcgDTO> ecgDTOs) {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<Integer, Map<String, Object>> ecgDivideWithRhythmId = new HashMap<>();
        for (EcgDTO ecgDTO : ecgDTOs) {
            for (EcgDetailDTO ed : ecgDTO.getDetailList()) {
                if (0 == ed.getSignType().intValue()) {
                    Map<String, Object> ecgMap = new HashMap<>();
                    
                    Integer rhythmId = ed.getRhythmId();
                    
                    Integer count = 0;
                    if (ecgDivideWithRhythmId.containsKey(rhythmId)) {
                        ecgMap = ecgDivideWithRhythmId.get(rhythmId);
                        count = (Integer) ecgMap.get("count");
                    } else {
                        EcgRhythmDTO rhythmDTO = ed.getEcgRhythm();
                        ecgMap.put(Rhythm.NAME, rhythmDTO.getName());
                        ecgMap.put(Rhythm.ID, rhythmId);
                    }
                    count++;
                    ecgMap.put("count", count);
                    
                    ecgDivideWithRhythmId.put(rhythmId, ecgMap);
                }
            }
        }
        list.addAll(ecgDivideWithRhythmId.values());
        return list;
    }

    /**
     * 心电自动标记
     *
     * @param ecgDTOs
     * @return
     * @author yuhang.weng
     * @DateTime 2017年5月5日 下午1:49:46
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> ecgAutoMark(List<EcgDTO> ecgDTOs) {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<Integer, Map<String, Object>> ecgDivideWithRhythmId = new HashMap<>();

        for (EcgDTO ecgDTO : ecgDTOs) {
            for (EcgDetailDTO ed : ecgDTO.getDetailList()) {
                if (0 == ed.getSignType().intValue()) {
                    Map<String, Object> ecgMap = new HashMap<>();

                    Integer rhythmId = ed.getRhythmId();
                    List<Map<String, Object>> records = new ArrayList<>();
                    if (ecgDivideWithRhythmId.containsKey(rhythmId)) {
                        ecgMap = ecgDivideWithRhythmId.get(rhythmId);
                        records = (List<Map<String, Object>>) ecgMap.get(Ecg.RECORD);
                    } else {
                        EcgRhythmDTO rhythmDTO = ed.getEcgRhythm();
                        ecgMap.put(Rhythm.NAME, rhythmDTO.getName());
                        ecgMap.put(Rhythm.MEANING, rhythmDTO.getMeaning());
                        ecgMap.put(Rhythm.REASON, rhythmDTO.getReasonList());
                        ecgMap.put(Rhythm.REMEDY, rhythmDTO.getRemedyList());
                    }

                    Map<String, Object> record = new HashMap<>();
                    record.put(Ecg.IMAGE, ed.getImage());
                    record.put(Ecg.HEART_RATE, ed.getHeartRate());
                    record.put(Ecg.MEASURE_DATE, ed.getMeasureDate());
                    records.add(record);
                    ecgMap.put(Ecg.RECORD, records);

                    ecgDivideWithRhythmId.put(rhythmId, ecgMap);
                }
            }
        }
        list.addAll(ecgDivideWithRhythmId.values());
        return list;
    }

    private int ecgActiveMarkCount(List<EcgDTO> ecgDTOs) {
        int count = 0;
        for (EcgDTO ecgDTO : ecgDTOs) {
            for (EcgDetailDTO ed : ecgDTO.getDetailList()) {
                if (1 == ed.getSignType().intValue()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 心电主动标记
     *
     * @param ecgDTOs
     * @return
     * @author yuhang.weng
     * @DateTime 2017年5月5日 下午1:49:56
     */
    private List<Map<String, Object>> ecgActiveMark(List<EcgDTO> ecgDTOs) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (EcgDTO ecgDTO : ecgDTOs) {
            for (EcgDetailDTO ed : ecgDTO.getDetailList()) {
                if (1 == ed.getSignType().intValue()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put(Ecg.HEART_RATE, ed.getHeartRate());
                    map.put(Ecg.MEASURE_DATE, ed.getMeasureDate());
                    map.put(Ecg.TAGS, ed.getTagList());
                    map.put(Ecg.IMAGE, ed.getImage());
                    list.add(map);
                }
            }
        }
        return list;
    }

    @Override
    public JSONObject addUran(String json) throws OperationException {
        AppJSON appJson = parseAppJSON(json);
        int userId = appJson.getData().getUserId();
        String deviceType = appJson.getType();
        JSONObject mm_0 = appJson.getData().getFirstJSONObject();
        String leu = mm_0.getString(Uran.LEU);
        String nit = mm_0.getString(Uran.NIT);
        String ubg = mm_0.getString(Uran.UBG);
        String pro = mm_0.getString(Uran.PRO);
        Float pH = mm_0.getFloat(Uran.PH);
        String bld = mm_0.getString(Uran.BLD);
        Float sg = mm_0.getFloat(Uran.SG);
        String ket = mm_0.getString(Uran.KET);
        String bil = mm_0.getString(Uran.BIL);
        String glu = mm_0.getString(Uran.GLU);
        String vc = mm_0.getString(Uran.VC);
        String measureDate = mm_0.getString(HealthPackage.MEASUREDATE);
        String manualEntry = mm_0.getString(HealthPackage.MANUAL_ENTRY);
        Integer dataType = DataTypeEnum.REAL_TIME.value();
        if (manualEntry != null && Normal.TRUE.equals(manualEntry)) {
            dataType = DataTypeEnum.MANUAL.value();
        }

        UranDTO uran = new UranDTO();
        uran.setLEU(leu);
        uran.setNIT(nit);
        uran.setUBG(ubg);
        uran.setPRO(pro);
        uran.setPH(pH);
        uran.setBLD(bld);
        uran.setSG(sg);
        uran.setKET(ket);
        uran.setBIL(bil);
        uran.setGLU(glu);
        uran.setVC(vc);
        uran.setMeasureDate(DateTimeUtilT.dateTime(measureDate));
        uran.setUserId(userId);
        uran.setDeviceType(deviceType);
        uran.setDataType(dataType);
        terminal.getUran().save(uran);

        /** 测量建议 */
        List<String> advices = new ArrayList<>();
        advices.add(uran.getPHStatusDescription());
        advices.add(uran.getSGStatusDescription());
        advices.add(uran.getLEUStatusDescription());
        advices.add(uran.getNITStatusDescription());
        advices.add(uran.getUBGStatusDescription());
        advices.add(uran.getPROStatusDescription());
        advices.add(uran.getBLDStatusDescription());
        advices.add(uran.getKETStatusDescription());
        advices.add(uran.getBILStatusDescription());
        advices.add(uran.getGLUStatusDescription());
        advices.add(uran.getVCStatusDescription());
        Iterator<String> iterator = advices.iterator();
        while (iterator.hasNext()) {
            String i = iterator.next();
            if (StringUtils.isBlank(i)) {
                iterator.remove();
            }
        }

        /** 形成原因 */
        HealthRank pHStatus = uran.getPhRank();
        HealthRank sGStatus = uran.getSgRank();
        FMRank lEURank = uran.getLEURank();
        FMRank nITRank = uran.getNITRank();
        FMRank uBGRank = uran.getUBGRank();
        FMRank pRORank = uran.getPRORank();
        FMRank bLDRank = uran.getBLDRank();
        FMRank kETRank = uran.getKETRank();
        FMRank bILRank = uran.getBILRank();
        FMRank gLURank = uran.getGLURank();
        FMRank vCRank = uran.getVCRank();

        List<ReasonSimpleDTO> params = new ArrayList<>();
        params.add(getReasonSimpleDTO(pHStatus, HealthType.pH));
        params.add(getReasonSimpleDTO(sGStatus, HealthType.SG));
        params.add(getReasonSimpleDTO(transformFMRank2HealthRank(lEURank), HealthType.LEU));
        params.add(getReasonSimpleDTO(transformFMRank2HealthRank(nITRank), HealthType.NIT));
        params.add(getReasonSimpleDTO(transformFMRank2HealthRank(uBGRank), HealthType.UBG));
        params.add(getReasonSimpleDTO(transformFMRank2HealthRank(pRORank), HealthType.PRO));
        params.add(getReasonSimpleDTO(transformFMRank2HealthRank(bLDRank), HealthType.BLD));
        params.add(getReasonSimpleDTO(transformFMRank2HealthRank(kETRank), HealthType.KET));
        params.add(getReasonSimpleDTO(transformFMRank2HealthRank(bILRank), HealthType.BIL));
        params.add(getReasonSimpleDTO(transformFMRank2HealthRank(gLURank), HealthType.GLU));
        params.add(getReasonSimpleDTO(transformFMRank2HealthRank(vCRank), HealthType.VC));
        List<String> reasons = reasons(HealthPackageType.URAN, params);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(HealthPackage.REASONS, reasons);
        returnData.put(HealthPackage.ADVICES, advices);

        Integer healthProduct = appJson.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.URAN);

        /**
         * @updateTime 2017-09-14 10:10:16
         * @author wuj
         *  每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
         */
        saveToUserInfoRead(userId, measureDate, HealthPackageType.URAN.value());

        return success(returnData);
    }

    @Override
    public JSONObject getUran(String json) {
        AppJSON appJson = parseAppJSON(json);
        int userId = appJson.getData().getUserId();

        JSONObject mm_0 = appJson.getData().getFirstJSONObject();
        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE);

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        List<UranDTO> uranDTOList = new ArrayList<>();// 存放符合日期条件的体温数据
        switch (withDate) {
            case "0":
                PaginationDTO<UranDTO> paginationDTO = terminal.getUran().getMeasureDataWithSplit(userId, deviceType,
                        pageIndex, pageSize);
                uranDTOList = paginationDTO.getData();
                break;
            case "1":
                uranDTOList = terminal.getUran().getMeasureDataWithDate(userId, deviceType, HealthPackage.DAY);
                break;
            case "2": // 周
                uranDTOList = terminal.getUran().getMeasureDataWithDate(userId, deviceType, HealthPackage.WEEK);
                break;
            case "3": // 一月
                uranDTOList = terminal.getUran().getMeasureDataWithDate(userId, deviceType, HealthPackage.MONTH);
                break;
            case "4": // 三月
                uranDTOList = terminal.getUran().getMeasureDataWithDate(userId, deviceType, HealthPackage.THREEMONTH);
                break;
            default:
                break;
        }

        List<Map<String, Object>> uranList = new ArrayList<>();
        for (UranDTO uranDTO : uranDTOList) {
            Map<String, Object> uranMap = new HashMap<>();
            uranMap.put(Uran.BIL, uranDTO.getBIL());
            uranMap.put(Uran.BLD, uranDTO.getBLD());
            uranMap.put(Uran.GLU, uranDTO.getGLU());
            uranMap.put(Uran.KET, uranDTO.getKET());
            uranMap.put(Uran.LEU, uranDTO.getLEU());
            uranMap.put(Uran.NIT, uranDTO.getNIT());
            uranMap.put(Uran.PH, uranDTO.getPH());
            uranMap.put(Uran.PRO, uranDTO.getPRO());
            uranMap.put(Uran.SG, NumberUtils.format(uranDTO.getSG(), 3));
            uranMap.put(Uran.UBG, uranDTO.getUBG());
            uranMap.put(Uran.VC, uranDTO.getVC());
            uranMap.put(Uran.BIL_STATUS, uranDTO.getBILStatus());
            uranMap.put(Uran.BLD_STATUS, uranDTO.getBLDStatus());
            uranMap.put(Uran.GLU_STATUS, uranDTO.getGLUStatus());
            uranMap.put(Uran.KET_STATUS, uranDTO.getKETStatus());
            uranMap.put(Uran.LEU_STATUS, uranDTO.getLEUStatus());
            uranMap.put(Uran.NIT_STATUS, uranDTO.getNITStatus());
            uranMap.put(Uran.PH_STATUS, uranDTO.getPhStatus());
            uranMap.put(Uran.PRO_STATUS, uranDTO.getPROStatus());
            uranMap.put(Uran.SG_STATUS, uranDTO.getSgStatus());
            uranMap.put(Uran.UBG_STATUS, uranDTO.getUBGStatus());
            uranMap.put(Uran.VC_STATUS, uranDTO.getVCStatus());
            // uranMap.put(Uran.LEU_DESC, uranDTO.getLEUStatusDescription());
            // uranMap.put(Uran.NIT_DESC, uranDTO.getNITStatusDescription());
            // uranMap.put(Uran.UBG_DESC, uranDTO.getUBGStatusDescription());
            // uranMap.put(Uran.PRO_DESC, uranDTO.getPROStatusDescription());
            // uranMap.put(Uran.PH_DESC, uranDTO.getpHStatusDescription());
            // uranMap.put(Uran.BLD_DESC, uranDTO.getBLDStatusDescription());
            // uranMap.put(Uran.SG_DESC, uranDTO.getSGStatusDescription());
            // uranMap.put(Uran.KET_DESC, uranDTO.getKETStatusDescription());
            // uranMap.put(Uran.BIL_DESC, uranDTO.getBILStatusDescription());
            // uranMap.put(Uran.GLU_DESC, uranDTO.getGLUStatusDescription());
            // uranMap.put(Uran.VC_DESC, uranDTO.getVCStatusDescription());
            uranMap.put(Uran.PH_AREA, uranDTO.getPHArea());
            uranMap.put(Uran.SG_AREA, uranDTO.getSGArea());
            uranMap.put(Uran.MEASURE_DATE, uranDTO.getMeasureDate());
            uranMap.put(Uran.STATUS, uranDTO.getStatus());

            uranList.add(uranMap);
        }

        if (!withDate.equals("0")) {
            java.util.Collections.reverse(uranList);
        }

        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(uranList, extraData, true);
    }

    @Override
    public JSONObject addUa(String json) throws OperationException {
        AppJSON appJson = parseAppJSON(json);
        int userId = appJson.getData().getUserId();
        String deviceType = appJson.getType();
        JSONObject mm_0 = appJson.getData().getFirstJSONObject();

        Float ua = mm_0.getFloat(Ua.UA);
        String measureDate = mm_0.getString(HealthPackage.MEASUREDATE);
        String manualEntry = mm_0.getString(HealthPackage.MANUAL_ENTRY);
        Integer dataType = DataTypeEnum.REAL_TIME.value();
        if (manualEntry != null && Normal.TRUE.equals(manualEntry)) {
            dataType = DataTypeEnum.MANUAL.value();
        }

        UaDTO uaDTO = new UaDTO();
        uaDTO.setUA(ua);
        uaDTO.setMeasureDate(DateTimeUtilT.dateTime(measureDate));
        uaDTO.setUserId(userId);
        uaDTO.setDeviceType(deviceType);
        uaDTO.setDataType(dataType);
        terminal.getUa().save(uaDTO);

        /** 测量建议 */
        List<String> advices = new ArrayList<>();
        String uaStatusDescription = uaDTO.getUAStatusDescription();
        if (StringUtils.isNotBlank(uaStatusDescription)) {
            advices.add(uaStatusDescription);
        }

        /** 形成原因 */
        HealthRank uaRank = uaDTO.getUARank();
        List<ReasonSimpleDTO> params = new ArrayList<>();
        params.add(getReasonSimpleDTO(uaRank, HealthType.UA));

        List<String> reasons = reasons(HealthPackageType.URAN, params);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(HealthPackage.REASONS, reasons);
        returnData.put(HealthPackage.ADVICES, advices);

        Integer healthProduct = appJson.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.UA);

        /**
         * @updateTime 2017-09-14 10:10:16
         * @author wuj
         *  每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
         */
        saveToUserInfoRead(userId, measureDate, HealthPackageType.UA.value());

        return success(returnData);
    }

    @Override
    public JSONObject getUa(String json) {
        AppJSON appJson = parseAppJSON(json);
        int userId = appJson.getData().getUserId();

        JSONObject mm_0 = appJson.getData().getFirstJSONObject();
        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE);

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        List<UaDTO> uaDTOList = new ArrayList<>();// 存放符合日期条件的体温数据
        switch (withDate) {
            case "0":
                PaginationDTO<UaDTO> paginationDTO = terminal.getUa().getMeasureDataWithSplit(userId, deviceType, pageIndex,
                        pageSize);
                uaDTOList = paginationDTO.getData();
                break;
            case "1":
                uaDTOList = terminal.getUa().getMeasureDataWithDate(userId, deviceType, HealthPackage.DAY);
                break;
            case "2": // 周
                uaDTOList = terminal.getUa().getMeasureDataWithDate(userId, deviceType, HealthPackage.WEEK);
                break;
            case "3": // 一月
                uaDTOList = terminal.getUa().getMeasureDataWithDate(userId, deviceType, HealthPackage.MONTH);
                break;
            case "4": // 三月
                uaDTOList = terminal.getUa().getMeasureDataWithDate(userId, deviceType, HealthPackage.THREEMONTH);
                break;
            default:
                break;
        }

        List<Map<String, Object>> uaList = new ArrayList<>();
        for (UaDTO uaDTO : uaDTOList) {
            Map<String, Object> uaMap = new HashMap<>();
            uaMap.put(Ua.UA, uaDTO.getUA());
            uaMap.put(Ua.UA_AREA, uaDTO.getUAArea());
            uaMap.put(Ua.MEASURE_DATE, uaDTO.getMeasureDate());
            uaMap.put(Ua.STATUS, uaDTO.getStatus());
            uaMap.put(Ua.UA_STATUS, uaDTO.getUAStatus());

            uaList.add(uaMap);
        }

        if (!withDate.equals("0")) {
            java.util.Collections.reverse(uaList);
        }

        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(uaList, extraData, true);
    }

    @Override
    public JSONObject addBloodLipid(String json) throws OperationException {
        AppJSON appJson = parseAppJSON(json);
        int userId = appJson.getData().getUserId();
        String deviceType = appJson.getType();
        JSONObject mm_0 = appJson.getData().getFirstJSONObject();

        Float hdl = mm_0.getFloat(BloodLipid.HDL);
        Float ldl = mm_0.getFloat(BloodLipid.LDL);
        Float tc = mm_0.getFloat(BloodLipid.TC);
        Float tg = mm_0.getFloat(BloodLipid.TG);
        Float lapidRatio = mm_0.getFloat(BloodLipid.LAPID_RATIO);

        String measureDate = mm_0.getString(HealthPackage.MEASUREDATE);
        String manualEntry = mm_0.getString(HealthPackage.MANUAL_ENTRY);
        Integer dataType = DataTypeEnum.REAL_TIME.value();
        if (manualEntry != null && Normal.TRUE.equals(manualEntry)) {
            dataType = DataTypeEnum.MANUAL.value();
        }

        BloodLipidDTO bloodLipidDTO = new BloodLipidDTO();
        bloodLipidDTO.setHDL(hdl);
        bloodLipidDTO.setLDL(ldl);
        bloodLipidDTO.setTC(tc);
        bloodLipidDTO.setTG(tg);
        bloodLipidDTO.setBloodLipidRatio(lapidRatio);
        bloodLipidDTO.setMeasureDate(DateTimeUtilT.dateTime(measureDate));
        bloodLipidDTO.setUserId(userId);
        bloodLipidDTO.setDeviceType(deviceType);
        bloodLipidDTO.setDataType(dataType);

        terminal.getBloodLipid().save(bloodLipidDTO);

        /** 测量建议 */
        List<String> advices = new ArrayList<>();
        String hdlStatusDescription = bloodLipidDTO.getHDLStatusDescription();
        String ldlStatusDescription = bloodLipidDTO.getLDLStatusDescription();
        String tgStatusDescription = bloodLipidDTO.getTGStatusDescription();
        String tclStatusDescription = bloodLipidDTO.getTCStatusDescription();

        if (StringUtils.isNotBlank(hdlStatusDescription)) {
            advices.add(hdlStatusDescription);
        }
        if (StringUtils.isNotBlank(ldlStatusDescription)) {
            advices.add(ldlStatusDescription);
        }
        if (StringUtils.isNotBlank(tgStatusDescription)) {
            advices.add(tgStatusDescription);
        }
        if (StringUtils.isNotBlank(tclStatusDescription)) {
            advices.add(tclStatusDescription);
        }

        /** 形成原因 */
        HealthRank hdlRank = bloodLipidDTO.getHDLRank();
        HealthRank ldlRank = bloodLipidDTO.getLDLRank();
        HealthRank tcRank = bloodLipidDTO.getTCRank();
        HealthRank tgRank = bloodLipidDTO.getTGRank();
        List<ReasonSimpleDTO> params = new ArrayList<>();
        params.add(getReasonSimpleDTO(hdlRank, HealthType.HDL));
        params.add(getReasonSimpleDTO(ldlRank, HealthType.LDL));
        params.add(getReasonSimpleDTO(tcRank, HealthType.TC));
        params.add(getReasonSimpleDTO(tgRank, HealthType.TG));

        List<String> reasons = reasons(HealthPackageType.BloodLipid, params);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(HealthPackage.REASONS, reasons);
        returnData.put(HealthPackage.ADVICES, advices);

        Integer healthProduct = appJson.getAopData().getUser().getHealthProduct();
        bindHealthPackage(userId, healthProduct, HealthPackageType.BloodLipid);

        /**
         * @updateTime 2017-09-14 10:10:16
         * @author wuj
         *  每当用户上传测量数据,都需要插入或更新t_userInfo_read这张表
         */
        saveToUserInfoRead(userId, measureDate, HealthPackageType.BloodLipid.value());

        return success(returnData);
    }

    @Override
    public JSONObject getBloodLipid(String json) {
        AppJSON appJson = parseAppJSON(json);
        int userId = appJson.getData().getUserId();

        JSONObject mm_0 = appJson.getData().getFirstJSONObject();
        String withDate = mm_0.getString(HealthPackage.WITH_DATE);// 日期条件（按测量日期降序）：0：不限（分页）1：按日，2：按周，3：按月，4：按三月
        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        String terminalType = mm_0.getString(Terminal.TYPE);

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        List<BloodLipidDTO> bloodLipidDTOList = new ArrayList<>();// 存放符合日期条件的体温数据
        switch (withDate) {
            case "0":
                PaginationDTO<BloodLipidDTO> paginationDTO = terminal.getBloodLipid().getMeasureDataWithSplit(userId,
                        deviceType, pageIndex, pageSize);
                bloodLipidDTOList = paginationDTO.getData();
                break;
            case "1":
                bloodLipidDTOList = terminal.getBloodLipid().getMeasureDataWithDate(userId, deviceType, HealthPackage.DAY);
                break;
            case "2": // 周
                bloodLipidDTOList = terminal.getBloodLipid().getMeasureDataWithDate(userId, deviceType, HealthPackage.WEEK);
                break;
            case "3": // 一月
                bloodLipidDTOList = terminal.getBloodLipid().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.MONTH);
                break;
            case "4": // 三月
                bloodLipidDTOList = terminal.getBloodLipid().getMeasureDataWithDate(userId, deviceType,
                        HealthPackage.THREEMONTH);
                break;
            default:
                break;
        }

        List<Map<String, Object>> bloodLipidList = new ArrayList<>();
        for (BloodLipidDTO bloodLipidDTO : bloodLipidDTOList) {
            Map<String, Object> bloodLipidMap = new HashMap<>();
            bloodLipidMap.put(BloodLipid.HDL, bloodLipidDTO.getHDL());
            bloodLipidMap.put(BloodLipid.HDL_AREA, bloodLipidDTO.getHDLArea());
            bloodLipidMap.put(BloodLipid.LDL, bloodLipidDTO.getLDL());
            bloodLipidMap.put(BloodLipid.LDL_AREA, bloodLipidDTO.getLDLArea());
            bloodLipidMap.put(BloodLipid.TC, bloodLipidDTO.getTC());
            bloodLipidMap.put(BloodLipid.TC_AREA, bloodLipidDTO.getTCArea());
            bloodLipidMap.put(BloodLipid.TG, bloodLipidDTO.getTG());
            bloodLipidMap.put(BloodLipid.TG_AREA, bloodLipidDTO.getTGArea());
            bloodLipidMap.put(BloodLipid.MEASURE_DATE, bloodLipidDTO.getMeasureDate());
            bloodLipidMap.put(BloodLipid.STATUS, bloodLipidDTO.getStatus());
            bloodLipidMap.put(BloodLipid.HDL_STATUS, bloodLipidDTO.getHDLStatus());
            bloodLipidMap.put(BloodLipid.LDL_STATUS, bloodLipidDTO.getLDLStatus());
            bloodLipidMap.put(BloodLipid.TC_STATUS, bloodLipidDTO.getTCStatus());
            bloodLipidMap.put(BloodLipid.TG_STATUS, bloodLipidDTO.getTGStatus());
            bloodLipidMap.put(BloodLipid.LAPID_RATIO, bloodLipidDTO.getBloodLipidRatio());
            bloodLipidMap.put(BloodLipid.LIPID_RATIO_AREA, bloodLipidDTO.getBloodLipidRatioArea());
            bloodLipidMap.put(BloodLipid.LIPID_RATIO_STATUS, bloodLipidDTO.getBloodLipidRatioStatus());

            bloodLipidList.add(bloodLipidMap);
        }

        if (!withDate.equals("0")) {
            java.util.Collections.reverse(bloodLipidList);
        }

        Map<String, String> extraData = new HashMap<>();
        extraData.put(HealthPackage.WITH_DATE, withDate);

        return success(bloodLipidList, extraData, true);
    }

    @Override
    public JSONObject getEcgAutoDataWithDate(String json) {
        AppJSON appJson = parseAppJSON(json);
        Integer userId = appJson.getData().getUserId();

        JSONObject mm_0 = appJson.getData().getFirstJSONObject();
        String date = mm_0.getString(Ecg.DATE);
        Integer rhythmId = mm_0.getInteger(Rhythm.ID);
        Integer pageSize = mm_0.getInteger(Page.SIZE);
        Integer pageIndex = mm_0.getInteger(Page.INDEX);
        String terminalType = mm_0.getString(Terminal.TYPE);

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        if (StringUtils.isBlank(date) || rhythmId == null || pageSize == null || pageIndex == null) {
            logger.error(Error.PARAMETER_MISSING + ": date=" + date + "&rhythmId=" + rhythmId
                    + "&pageSize=" + pageSize + "&pageIndex=" + pageIndex);
            return error(Error.PARAMETER_MISSING);
        }

        PaginationDTO<EcgDTO> p = terminal.getEcg().listEcgWithDateAndType(userId, date, deviceType, 0, rhythmId,
                pageIndex, pageSize);
        List<EcgDTO> data = p.getData();

        return success(ecgAutoMark(data), true);
    }

    @Override
    public JSONObject getEcgActiveDataWithDate(String json) {
        AppJSON appJson = parseAppJSON(json);
        Integer userId = appJson.getData().getUserId();

        JSONObject mm_0 = appJson.getData().getFirstJSONObject();
        String date = mm_0.getString(Ecg.DATE);
        Integer pageSize = mm_0.getInteger(Page.SIZE);
        Integer pageIndex = mm_0.getInteger(Page.INDEX);
        String terminalType = mm_0.getString(Terminal.TYPE);

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        if (StringUtils.isBlank(date) || pageSize == null || pageIndex == null) {
            logger.error(Error.PARAMETER_MISSING + ": date=" + date
                    + "&pageSize=" + pageSize + "&pageIndex=" + pageIndex);
            return error(Error.PARAMETER_MISSING);
        }

        PaginationDTO<EcgDTO> p = terminal.getEcg().listEcgWithDateAndType(userId, date, deviceType, 1, null,
                pageIndex, pageSize);
        List<EcgDTO> data = p.getData();

        return success(ecgActiveMark(data), true);
    }

    private void bindHealthPackage(int userId, Integer userHealthProduct, HealthPackageType healthProduct) {
        // 避免空值
        if (userHealthProduct == null) {
            userHealthProduct = 0;
        }

        int tmp = userHealthProduct.intValue() | healthProduct.value();
        if (tmp == userHealthProduct) {
            return;
        }
        memberService.updateUserHealthProduct(userId, tmp);
    }
}
