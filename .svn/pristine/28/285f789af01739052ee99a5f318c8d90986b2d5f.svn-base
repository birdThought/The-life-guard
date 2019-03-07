package com.lifeshs.service.device.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.dao.device.IDeviceDao;
import com.lifeshs.dao.member.IMemberDao;
import com.lifeshs.entity.device.TSportBand;
import com.lifeshs.entity.device.TSportBandSleep;
import com.lifeshs.entity.device.TSportBandStep;
import com.lifeshs.entity.device.TSportLocation;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUser;
import com.lifeshs.entity.member.TUserMonitorTrack;
import com.lifeshs.pojo.healthDevice.HealthPackageDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.device.IDeviceService;
import com.lifeshs.common.constants.app.healthPackage.HealthPackage;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.service.device.impl.healthStandard.HealthStandard;
import com.lifeshs.common.constants.common.TerminalType;
import com.lifeshs.utils.ConvertUtil;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.ReflectUtils;

@Component
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private IDeviceDao deviceDao;

    @Autowired
    private ICommonTrans commonTrans;
    
    @Autowired
    private IMemberDao memberDao;

    public <T> List<Map<String, Object>> selectDeviceDataWithSpecificDate(Class<T> entityName, List<String> keys,
            Map<String, Object> params) {
        Object deviceType = params.get("deviceType");
        params.remove("deviceType");
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        Map<String, Object> entity = ReflectUtils.queryEntityEnclosureParamsAssignColumn(entityName, keys, params);
        // 获取反射生成的map对象，并将其修改为 [selectDeviceDataWithSpecificDate]方法能够接受的Map对象
        @SuppressWarnings("unchecked")
        Map<String, Object> condition = (Map<String, Object>) entity.get("condition");
        Object measureDate = condition.get("measureDate");
        Object userId = condition.get("userId");
        // Object deviceType = condition.get("deviceType");
        Object date = condition.get("date");
        entity.remove("condition");
        if (entityName.equals(TSportBand.class)) {
            entity.put("date", date);
        } else {
            entity.put("measureDate", measureDate);
        }
        entity.put("userId", userId);
        entity.put("deviceType", deviceType);
        if (entityName.equals(TSportBand.class)) {
            resList = deviceDao.selectDeviceDataWithSpecificDateByDate(entity);
        } else {
            resList = deviceDao.selectDeviceDataWithSpecificDate(entity);
        }
        return resList;
    }

    public Map<String, Object> selectDeviceDataStandard(String healthType, Integer age, Boolean sex) {
        Map<String, Object> resMap = new HashMap<String, Object>(4);
        Map<String, Object> param = new HashMap<String, Object>(3);
        param.put("healthType", healthType);
        param.put("sex", sex); // TODO sex 在数据库中存储的方式可能不同 待测试
        param.put("age", age);
        resMap = deviceDao.selectDeviceDataStandard(param);

        return resMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T selectDeviceDataLastest(Class<T> entityName, int userId, String terminalType, String measureDate) {
        Object object = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);

        Map<String, Object> entity = ReflectUtils.queryEntityEnclosureParamsWholeColumn(entityName, params);
        entity.put("measureDate", measureDate);
        entity.put("deviceType", terminalType);
        Map<String, Object> resMap = deviceDao.selectDeviceDataLastest(entity);
        object = ReflectUtils.getBean(resMap, entityName);
        return (T) object;
    }

    @Override
    public List<Map<String, Object>> findCommond(String imei, int userId) {
        return deviceDao.findCommond(imei, userId);
    }

    @Override
    public List<Map<String, Object>> findCommond(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return deviceDao.findCommond(params);
//        return deviceDao.findCommond(imei, userId);
    }

    @Override
    public int updateCommond(String imei, int userId) {
        // TODO Auto-generated method stub
        return deviceDao.updateCommond(imei, userId);
    }

    @Override
    public TUserTerminal selectDeviceIsBinding(String imei, String terminalType) {
        return deviceDao.selectDeviceIsBindingLimitType(imei, terminalType);
    }

    @Override
    public List<TSportLocation> findLatestGpsMessage(int userId, String terminalType, int limit) {
        return deviceDao.selectLatestLocation(userId, terminalType, limit);
    }

    @Override
    public List<TSportLocation> findLocationByDateTime(int userId, String terminalType, String startTime, String endTime) {
        return deviceDao.selectLocationByDate(userId, terminalType, startTime, endTime);
    }

    @Override
    public List<TUserMonitorTrack> findTracksOrderByParam(int deviceId, String orderParam, int type) {
        String order = "ASC";
        if (type == 1)
            order = "DESC";
        return deviceDao.selectTracksOrderByParam(deviceId, orderParam, order);
    }

    @Override
    public <T> List<Map<String, Object>> selectDeviceDataWithSpecificDatePageSplit(Class<T> entityName,
            List<String> keys, Map<String, Object> params, int pageSize, int page) {
        Object deviceType = params.get("deviceType");
        params.remove("deviceType");
        List<Map<String, Object>> tmpList = new ArrayList<>();
        Map<String, Object> entity = ReflectUtils.queryEntityEnclosureParamsAssignColumn(entityName, keys, params);
        // 获取反射生成的map对象，并将其修改为 [selectDeviceDataWithSpecificDate]方法能够接受的Map对象
        @SuppressWarnings("unchecked")
        Map<String, Object> condition = (Map<String, Object>) entity.get("condition");
        // Object measureDate = condition.get("measureDate");
        // //去除某天的查询条件（获取全部，不需要具体时间）
        Object userId = condition.get("userId");
        // Object deviceType = condition.get("deviceType");
        entity.remove("condition");
        // entity.put("measureDate", measureDate);
        entity.put("userId", userId);
        entity.put("deviceType", deviceType);
        // 获取sqlSession
        tmpList = deviceDao.selectDeviceAllData(entity); // 获取全部数据

        /**
         * 重新修正pageSize大小
         */
        pageSize = pageSize <= 0 ? 1 : pageSize;

        // 总数据量
        int totalSize = tmpList.size();
        // 总页数
        int totalPage = 1;
        if (pageSize <= totalSize) {
            // 如果不能被整除，就数值+1
            totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : (totalSize / pageSize) + 1;
        }

        /**
         * 重新修正page 如果page比1小，就将page修改为1 如果page比总页数大，就将page修改为总页数
         */
        if (page <= 0)
            page = 1;
        if (page > totalPage)
            page = totalPage;

        int start = (page - 1) * pageSize; // 开始下标

        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<>();
        entity.put("start", start);
        entity.put("pageSize", pageSize);
        entity.put("orderby", "measureDate");
        if (entityName.equals(TSportBand.class) || entityName.equals(TSportBandStep.class)
                || entityName.equals(TSportBandSleep.class)) {
            entity.put("orderby", "date");
        }

        /*
         * System.out.println("=====分页数据====="); System.out.println("总数据量：" +
         * totalSize); System.out.println("总页数：" + totalPage);
         * System.out.println("开始下标：" + start); System.out.println("当前页码:" +
         * page);
         */

        // 获取sqlSession
        resList = deviceDao.selectDeviceDataWithSpecificDatePageSplit(entity);
        map.put("amount", totalSize); // 加入总条数
        resList.add(map);
        // System.out.println("resList:"+resList);
        return resList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T selectDeviceDataLastestDate(Class<T> entityName, int userId, String terminalType, String measureDate) {
        Object object = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);

        Map<String, Object> entity = ReflectUtils.queryEntityEnclosureParamsWholeColumn(entityName, params);
        entity.put("date", measureDate);
        entity.put("deviceType", terminalType);

        Map<String, Object> resMap = deviceDao.selectDeviceDataLastestDate(entity);
        object = ReflectUtils.getBean(resMap, entityName);
        return (T) object;
    }

    @Override
    public Map<String, Object> getHealthStandardValueByHealthType(Integer userId, List<HealthType> healthTypes) {
        List<String> healthTypeNames = new ArrayList<>();
        for (HealthType healthType : healthTypes) {
            healthTypeNames.add(healthType.name());
        }

        /** 获取用户的数据，可以考虑修改为从缓存中获取，但是要用ehcache中的，因为保存在session中的缓存更换的速度不够快 */
        UserRecordDTO recordDTO = memberDao.getUserRecord(userId);

        Integer age = null;
        if (recordDTO.getBirthday() != null) {
            age = DateTimeUtilT.calculateAge(recordDTO.getBirthday());
        }

        Integer height = recordDTO.getHeight() == null ? null : recordDTO.getHeight().intValue();
        Integer weight = recordDTO.getWeight() == null ? null : recordDTO.getWeight().intValue();

        Map<String, Object> hs = HealthStandard.getAllByUserNullCheck(recordDTO.getGender(), age, height, weight);
        
        Map<String, Object> hs_n = new HashMap<>();
        for (String key1 : hs.keySet()) {
            
            if (!healthTypeNames.contains(key1)) {
                continue;
            }
            
            @SuppressWarnings("unchecked")
            Map<String, String> cMap = (Map<String, String>) hs.get(key1);
            switch (key1) {
            case "vitalCapacity":

                String va = cMap.get("min") + "-" + cMap.get("max");
                hs_n.put(key1, va);
                
                List<Map<String, String>> vb = new ArrayList<>();
                
                Map<String, String> data1 = new LinkedHashMap<>();
                data1.put("area", 0 + "-" + cMap.get("less"));
                data1.put("score", "1分");
                vb.add(data1);
                
                Map<String, String> data2 = new LinkedHashMap<>();
                data2.put("area", cMap.get("less") + "-" + cMap.get("min"));
                data2.put("score", "2分");
                vb.add(data2);
                
                Map<String, String> data3 = new LinkedHashMap<>();
                data3.put("area", cMap.get("min") + "-" + cMap.get("max"));
                data3.put("score", "3分");
                vb.add(data3);
                
                Map<String, String> data4 = new LinkedHashMap<>();
                data4.put("area", cMap.get("max") + "-" + cMap.get("more"));
                data4.put("score", "4分");
                vb.add(data4);
                
                Map<String, String> data5 = new LinkedHashMap<>();
                data5.put("area", cMap.get("more") + "-" + 99999);
                data5.put("score", "5分");
                vb.add(data5);
                
                hs_n.put("vitalCapacityArea", vb);

                break;
            case "bloodSugar":

                String bm = cMap.get("less") + "-" + cMap.get("more");
                String am = cMap.get("min") + "-" + cMap.get("max");
                Map<String, String> bs = new HashMap<>();
                bs.put("beforeMeal", bm);
                bs.put("afterMeal", am);

                hs_n.put(key1, bs);

                break;
            default:

                String da = cMap.get("min") + "-" + cMap.get("max");
                hs_n.put(key1, da);

                break;
            }
        }

        return hs_n;
    }
    
    @Override
    public Map<String, Object> getHealthStandardValueByHealthType2(Integer userId, List<HealthType> healthTypes) {
        List<String> healthTypeNames = new ArrayList<>();
        for (HealthType healthType : healthTypes) {
            healthTypeNames.add(healthType.name());
        }
        // 如果是心率手环，需要额外添加一个运动模式
        if (healthTypeNames.contains(HealthPackage.HEARTRATE)) {
            healthTypeNames.add(HealthPackage.HEARTRATE_SPORT_MODE);
        }
        
        UserRecordDTO recordDTO = memberDao.getUserRecord(userId);

        Integer age = null;
        if (recordDTO.getBirthday() != null) {
            age = DateTimeUtilT.calculateAge(recordDTO.getBirthday());
        }

        Integer height = recordDTO.getHeight() == null ? null : recordDTO.getHeight().intValue();
        Integer weight = recordDTO.getWeight() == null ? null : recordDTO.getWeight().intValue();

        Map<String, Object> hs = HealthStandard.getAllByUserNullCheck(recordDTO.getGender(), age, height, weight);
        
        Map<String, Object> healthArea = new HashMap<>();
        List<Map<String, String>> body = new ArrayList<>();
        List<Map<String, String>> vb = new ArrayList<>();
        
        for (String key : hs.keySet()) {
            if (!healthTypeNames.contains(key)) {
                continue;
            }
            
            @SuppressWarnings("unchecked")
            Map<String, String> cMap = (Map<String, String>) hs.get(key);
            switch (key) {
            case "vitalCapacity":
                
                Map<String, String> data1 = new LinkedHashMap<>();
                data1.put("area", 0 + "-" + cMap.get("less"));
                data1.put("score", "1");
                vb.add(data1);
                
                Map<String, String> data2 = new LinkedHashMap<>();
                data2.put("area", cMap.get("less") + "-" + cMap.get("min"));
                data2.put("score", "2");
                vb.add(data2);
                
                Map<String, String> data3 = new LinkedHashMap<>();
                data3.put("area", cMap.get("min") + "-" + cMap.get("max"));
                data3.put("score", "3");
                vb.add(data3);
                
                Map<String, String> data4 = new LinkedHashMap<>();
                data4.put("area", cMap.get("max") + "-" + cMap.get("more"));
                data4.put("score", "4");
                vb.add(data4);
                
                Map<String, String> data5 = new LinkedHashMap<>();
                data5.put("area", cMap.get("more") + "-" + 99999);
                data5.put("score", "5");
                vb.add(data5);

                Map<String, String> va = new HashMap<>();
                va.put("param", key);
                va.put("min", cMap.get("min"));
                va.put("max", cMap.get("max"));
                va.put("less", cMap.get("less"));
                va.put("more", cMap.get("more"));
                
                body.add(va);
                break;
            case "bloodSugar":
                
                Map<String, String> ba = new HashMap<>();
                ba.put("param", key);
                ba.put("min", cMap.get("min"));
                ba.put("max", cMap.get("max"));
                ba.put("less", cMap.get("less"));
                ba.put("more", cMap.get("more"));
                
                body.add(ba);
                break;
            default:
                
                Map<String, String> da = new HashMap<>();
                da.put("param", key);
                da.put("min", cMap.get("min"));
                da.put("max", cMap.get("max"));
                da.put("less", "");
                da.put("more", "");
                
                body.add(da);
                break;
            }
        }

        healthArea.put("commonHealthArea", body);
        healthArea.put("vitalCapacityArea", vb);
        
        return healthArea;
    }

    @Override
    public Map<String, Object> getDeviceDataLatestByDateWithDeviceNameAndTerminalType(Integer userId, Date measureDate,
            List<Class<?>> devicesName, TerminalType terminalType) {

        Map<String, Object> data = new HashMap<>();

        for (Class<?> deviceName : devicesName) {
            if (deviceName.equals(TSportBand.class)) {
                Map<String, Object> band = deviceDao.selectLatestBand(userId,
                        "%" + terminalType.getName() + "%");
                data.put(getClassSimpleName(TSportBand.class), band);
            } else {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("userId", userId);

                Map<String, Object> entity = ReflectUtils.queryEntityEnclosureParamsWholeColumn(deviceName, params);
                /*entity.put("measureDate", DateTimeUtilT.date(measureDate));*/
                entity.put("deviceType", terminalType.getName());
                Map<String, Object> deviceData = deviceDao.selectDeviceDataLastest(entity);

                data.put(getClassSimpleName(deviceName), deviceData);
            }
        }

        return data;
    }

    /**
     * 获取一个更加简洁的名称
     * </p>
     * 规则是类名格式为(XxxXxxXxx……)，取类名的第三个大写字母的下标，将原名称进行substring
     * </p>
     * 比如：类名为TSportBand，转换的结果就是Band
     * 
     * @author zhiguo.lin
     * @DateTime 2016年9月13日 下午7:25:30
     *
     * @param name
     * @return
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
    public <T> Integer selectDeviceDataCountByMeasureDate(Class<T> entityClass, Map<String, Object> params) {
        String tableName = ReflectUtils.reflectTableName(entityClass);
        params.put("tableName", tableName);
        Integer amount = deviceDao.selectDeviceDataCountByMeasureDate(params);
        return amount;
    }

    @Override
    public List<HealthPackageDTO> getUserHealthWarningList(int userId) {
        TUser user = commonTrans.getEntity(TUser.class, userId);
        List<HealthPackageDTO> models = new ArrayList<>();
        String[] healthTypes = { "heartRate", "systolic", "diastolic", "saturation", "bloodSugar", "temperature",
                "ECG" }; // 心率参数、收缩压、舒张压、血氧饱和度、血糖、体温、心电
        int healthWarning = user.getHealthWarning();
        for (HealthType healthType : HealthType.values()) {
            HealthPackageDTO model = new HealthPackageDTO();
            model.setName_cn(healthType.getName());
            model.setName_en(healthType.name());
            if ((healthType.value() | healthWarning) == healthWarning) {
                model.setStatus(1);
            } else {
                model.setStatus(0);
            }
            if (ConvertUtil.isIn(model.getName_en(), healthTypes)) {
                models.add(model);
            }
        }
        return models;
    }

    @Override
    public ServiceMessage addWarningDevice(Map<String, Object> params) {
        ServiceMessage serviceMessage = new ServiceMessage();
        int userId = (int)params.get("userId");
        String healthTypeName = (String)params.get("healthTypeName");
        TUser user = commonTrans.getEntity(TUser.class, userId);
        int healthWarning = user.getHealthWarning();
        for (HealthType healthType : HealthType.values()) {
            if (StringUtils.equals(healthTypeName, healthType.name())) {
                healthWarning += healthType.value(); // 勾选该项健康包
                break;
            }
        }
        user.setHealthWarning(healthWarning);
        serviceMessage.setSuccess(commonTrans.updateEntitie(user) > 0 ? true : false);
        return serviceMessage;
    }
    
    @Override
    public ServiceMessage deleteWarningDevice(Map<String, Object> params) {
        ServiceMessage serviceMessage = new ServiceMessage();
        int userId = (int)params.get("userId");
        String healthTypeName = (String)params.get("healthTypeName");
        TUser user = commonTrans.getEntity(TUser.class, userId);
        int healthWarning = user.getHealthWarning();
        for (HealthType healthType : HealthType.values()) {
            if (StringUtils.equals(healthTypeName, healthType.name())) {
                if ((healthType.value() | healthWarning) == healthWarning) {
                    healthWarning -= healthType.value(); //取消健康项目
                }
                break;
            }
        }
        user.setHealthWarning(healthWarning);
        serviceMessage.setSuccess(commonTrans.updateEntitie(user) > 0 ? true : false);
        return serviceMessage;
    }
}
