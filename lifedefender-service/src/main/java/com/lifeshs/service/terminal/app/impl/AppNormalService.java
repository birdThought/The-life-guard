package com.lifeshs.service.terminal.app.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.HuanXin;
import com.lifeshs.common.constants.app.Message;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.hobby.Hobby;
import com.lifeshs.common.constants.app.hobby.UserHobby;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.entity.log.TLogLogin;
import com.lifeshs.po.push.UserDeviceTokenPO;
import com.lifeshs.po.user.UserHobbyPO;
import com.lifeshs.po.vip.VipUserPO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.healthStandard.HealthStandardValue;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.config.DeviceDTO;
import com.lifeshs.pojo.member.healthIndex.DetailDTO;
import com.lifeshs.pojo.member.healthIndex.HealthIndexDTO;
import com.lifeshs.pojo.paper.PaperOptionDTO;
import com.lifeshs.service.device.impl.product.Product;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.tool.ITokenService;
import com.lifeshs.service1.member.HobbyService;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.service1.push.PushDataService;
import com.lifeshs.service1.vip.IVipUserService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.FileUtils;

/**
 * APP通用的service，主要用于存放通用方法
 * 
 * @author yuhang.weng
 * @DateTime 2016年10月27日 下午5:35:44
 */
@Service(value = "appNormalService")
public class AppNormalService extends AppBaseService {

    public static final String SUCCESS_STATUS = "0";

    public static final String ERROR_NORMAL_STATUS = "1";

    /** 健康标准值必要参数缺失 */
    public static final String ERROR_HEALTH_STANDAR_NECESSARY_PARAM_MISSIING = "400";

    @Autowired
    protected IMemberService memberService;

    @Resource(name = "pushDataService")
    protected PushDataService pushDataService;

    @Autowired
    protected ITokenService tokenService;

    @Resource(name = "v2MessageService")
    private MessageService messageService;

    @Resource(name = "userHobbyService")
    protected HobbyService hobbyService;
    
    @Resource(name = "vipUserService")
    private IVipUserService vipUserService;
    
    /**
     * 错误返回
     * </p>
     * 如果不能够准确定义到错误码，就使用这个错误返回方法
     * </p>
     * 
     * @author dachang.luo
     * @DateTime 2016年6月16日下午2:24:12
     *
     * @param errorMsg
     *            服务端错误信息
     * @return
     */
    public static JSONObject error(String errorMsg) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(Normal.STATUS, ERROR_NORMAL_STATUS);
        resultMap.put(Normal.MESSAGE, errorMsg);

        JSONObject jsonObj = new JSONObject();
        jsonObj.putAll(resultMap);
        return jsonObj;
    }

    /**
     * 错误返回
     * </p>
     * 
     * @author yuhang.weng
     * @DateTime 2017年7月10日 上午10:13:40
     *
     * @param errorMsg
     * @param status
     *            错误码，具体参考http状态码
     * @return
     */
    public static JSONObject error(String errorMsg, Integer status) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(Normal.STATUS, String.valueOf(status));
        resultMap.put(Normal.MESSAGE, errorMsg);

        JSONObject jsonObj = new JSONObject();
        jsonObj.putAll(resultMap);
        return jsonObj;
    }

    /**
     * 错误返回
     * </p>
     * 
     * @author yuhang.weng
     * @DateTime 2017年7月10日 上午10:32:40
     *
     * @param errorMsg
     *            错误信息
     * @param code
     *            错误码
     * @return
     */
    public static JSONObject error(String errorMsg, ErrorCodeEnum code) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(Normal.STATUS, String.valueOf(code.value()));
        resultMap.put(Normal.MESSAGE, errorMsg);

        JSONObject jsonObj = new JSONObject();
        jsonObj.putAll(resultMap);
        return jsonObj;
    }

    private static JSONObject returnSuccessWithExtraMap(JSONObject root, Map<String, String> extraMap) {
        for (String key : extraMap.keySet()) {
            if (root.containsKey(key)) {
                // 包含相同的key的话，优先保留原数据
                continue;
            }
            root.put(key, extraMap.get(key));
        }
        return root;
    }

    public static JSONObject success() {
        JSONObject root = new JSONObject();
        root.put(Normal.STATUS, "0");

        JSONArray array = new JSONArray();
        root.put(Normal.DATA, array);

        return root;
    }

    /**
     * 
     * @author yuhang.weng
     * @DateTime 2016年12月19日 下午2:59:20
     *
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public static JSONObject success(Object object) {
        JSONObject root = new JSONObject();
        root.put(Normal.STATUS, "0");

        JSONArray array = new JSONArray();

        boolean ok = false;
        if (object instanceof Map) {
            Map<String, Object> data = (Map<String, Object>) object;
            array.add(value2String(data));
            ok = true;
        }
        if (object instanceof List) {
            List<Object> datas = (List<Object>) object;
            array.addAll(value2String(datas));
            ok = true;
        }
        if (!ok) {
            array.add(JSONObject.toJSON(object));
            ok = true;
        }

        root.put(Normal.DATA, array);

        return root;
    }
    @SuppressWarnings("unchecked")
    public static JSONObject successJia(Object object,String appId,String token,Long l) {
        JSONObject root = new JSONObject();
        JSONArray array = new JSONArray();

        boolean ok = false;
        if (object instanceof Map) {
            Map<String, Object> data = (Map<String, Object>) object;
            array.add(value2String(data));
            ok = true;
        }
        if (object instanceof List) {
            List<Object> datas = (List<Object>) object;
            array.addAll(value2String(datas));
            ok = true;
        }
        if (!ok) {
            array.add(JSONObject.toJSON(object));
            ok = true;
        }

        root.put(Normal.DATA, array);
        root.put(Normal.APPID, appId);
        root.put(Normal.TOKEN, token);
        root.put(Normal.VER, "1");
        root.put(Normal.TIME_STAMP, l.toString());

        return root;
    }

    public static JSONObject success(Object data, boolean emptyCheck) {
        if (emptyCheck) {
            if (data instanceof Map) {
                Map<?, ?> castMap = (Map<?, ?>) data;
                if (castMap.isEmpty()) {
                    return success(NormalMessage.NO_DATA);
                }
            }

            if (data instanceof List) {
                List<?> castList = ((List<?>) data);
                if (castList.size() == 0) {
                    return success(NormalMessage.NO_DATA);
                }
            }

            // 这段检测是为了避免null情况的产生
            if (data == null) {
                System.out.println("通用接入点");
                return success(NormalMessage.NO_DATA);
            }
        }
        return success(data);
    }

    /**
     * 返回正确的app请求，并且在root层携带更多的参数
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月11日 下午2:28:58
     *
     * @param data
     * @param extraMap
     * @return
     */
    public static JSONObject success(Object data, Map<String, String> extraMap) {
        JSONObject json = success(data);
        return returnSuccessWithExtraMap(json, extraMap);
    }

    public static JSONObject success(Object data, Map<String, String> extraMap, boolean emptyCheck) {
        JSONObject root = success(data, emptyCheck);
        return returnSuccessWithExtraMap(root, extraMap);
    }

    /**
     * 返回正确的app请求，携带返回信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月15日 下午7:19:10
     *
     * @param message
     * @return
     */
    public static JSONObject success(String message) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(Normal.STATUS, SUCCESS_STATUS);
        resultMap.put(Normal.MESSAGE, message);

        JSONObject jsonObj = new JSONObject();
        jsonObj.putAll(resultMap);
        return jsonObj;
    }

    /**
     * 返回正确的app请求，携带返回信息,并且在root层携带更多的参数
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月11日 下午2:28:58
     *
     * @param data
     * @param extraMap
     * @return
     */
    public static JSONObject success(String message, Map<String, String> extraMap) {
        JSONObject json = success(message);

        for (String key : extraMap.keySet()) {
            if (json.containsKey(key)) {
                // 包含相同的key的话，优先保留原数据
                continue;
            }
            json.put(key, extraMap.get(key));
        }
        return json;
    }

    /**
     * 对饮食记录一天内的数据进行排序（按照早餐，早餐加餐，午餐，午餐加餐，晚餐，晚餐加餐排列）
     * 
     * @author yuhang.weng
     * @DateTime 2016年12月19日 上午11:22:53
     *
     * @param dietDatas
     * @return
     */
    public static List<Map<String, Object>> dietTimeSort(List<Map<String, Object>> dietDatas) {

        Collections.sort(dietDatas, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                // Date d1 = DateTimeUtilT.time((String) o1.get("dietTime"));
                // Date d2 = DateTimeUtilT.time((String) o2.get("dietTime"));
                Date d1 = (Date) o1.get("dietTime");
                Date d2 = (Date) o2.get("dietTime");

                if (d1.before(d2)) {
                    return -1;
                }

                if (d1.equals(d2)) {
                    String type1 = (String) o1.get("dietType");
                    String type2 = (String) o2.get("dietType");
                    return type1.length() - type2.length();
                }
                return 0;
            }
        });

        return dietDatas;
    }

    /**
     * 将接收到的string转换为AppJSON对象
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月21日 下午7:33:12
     *
     * @param json
     * @return
     */
    public static AppJSON parseAppJSON(String json) {
        JSONObject root = JSONObject.parseObject(json);
        AppJSON appJSON = JSONObject.toJavaObject(root, AppJSON.class);
        return appJSON;
    }

    /**
     * 获取用户设备配置对象
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月13日 下午2:30:25
     *
     * @param userId
     *            用户ID
     * @param indexChar
     *            首字母，用于分目录保存文件
     * @return
     */
    public static DeviceDTO getUserDeviceConfig(int userId, Character indexChar) {
        /** 格式形如: /life/lifeshs/.../Q/2.config */
        String fileName = "/life/lifeshs/lifekeepers_files/user/config/device/" + Character.toUpperCase(indexChar) + "/"
                + userId + ".config";
        File deviceConfigFile = FileUtils.getFile(fileName);

        String content = FileUtils.readFile(deviceConfigFile);

        DeviceDTO deviceDTO = new DeviceDTO();
        if (StringUtils.isNotBlank(content)) {
            JSONObject root = JSONObject.parseObject(content);
            deviceDTO = JSONObject.toJavaObject(root, DeviceDTO.class);
        } else {
            /* 如果没有该文件的记录，就重新创建一个，同时在文件中写入默认顺序 */
            List<Integer> orderList = Product.listProductValue();
            deviceDTO.setOrderList(orderList);

            JSONObject deviceDTO2Json = (JSONObject) JSONObject.toJSON(deviceDTO);

            FileUtils.writeFile(deviceConfigFile, deviceDTO2Json.toString(), false);
        }

        return deviceDTO;
    }

    /**
     * 获取系统计算的健康标准范围值 根据type转换获取的结果(1是第一套标准， 建议填值2) 备注：
     * user对象只需要填写user.id，user.recordDTO.birthday，user.recordDTO.gender的值
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月10日 下午1:39:12
     *
     * @param userId
     * @param type
     * @return
     */
    public Map<String, Object> getSystemCalculateHealthArea(UserDTO user, int type) {
        Map<String, Object> map = new HashMap<>();

        user.getRecordDTO().getBirthday();
        user.getRecordDTO().getGender();
        if (user.getRecordDTO().getBirthday() == null || user.getRecordDTO().getGender() == null) {
            return map;
        }
        int userId = user.getId();

        List<HealthType> healthTypes = new ArrayList<>();

        healthTypes.add(HealthType.heartRate);
        healthTypes.add(HealthType.systolic);
        healthTypes.add(HealthType.diastolic);
        healthTypes.add(HealthType.saturation);
        healthTypes.add(HealthType.bloodSugar);
        healthTypes.add(HealthType.vitalCapacity);
        healthTypes.add(HealthType.temperature);
        healthTypes.add(HealthType.ECG);
        // 体脂秤
        healthTypes.add(HealthType.weight);
        healthTypes.add(HealthType.axungeRatio);
        healthTypes.add(HealthType.WHR);
        healthTypes.add(HealthType.BMI);
        healthTypes.add(HealthType.fatFreeWeight);
        healthTypes.add(HealthType.muscle);
        healthTypes.add(HealthType.moisture);
        healthTypes.add(HealthType.boneWeight);
        healthTypes.add(HealthType.bodyage);
        healthTypes.add(HealthType.baseMetabolism);
        healthTypes.add(HealthType.proteide);
        healthTypes.add(HealthType.visceralFat);
        healthTypes.add(HealthType.pH);
        // TODO 添加新设备的健康标准
        healthTypes.add(HealthType.SG);
        healthTypes.add(HealthType.LDL);
        healthTypes.add(HealthType.TG);
        healthTypes.add(HealthType.TC);
        healthTypes.add(HealthType.UA);
        healthTypes.add(HealthType.HDL);
        healthTypes.add(HealthType.BloodLipidRation);

        Map<String, Object> healthStandardValues = new HashMap<>();

        if (type == 1) {
            healthStandardValues = getHealthAreaData(userId, healthTypes);
        }
        if (type == 2) {
            healthStandardValues = getHealthAreaData2(userId, healthTypes);
        }

        return healthStandardValues;
    }

    private Map<String, Object> getHealthAreaData(int userId, List<HealthType> healthTypes) {
        return terminal.getDeviceService().getHealthStandardValueByHealthType(userId, healthTypes);
    }

    private Map<String, Object> getHealthAreaData2(int userId, List<HealthType> healthTypes) {
        return terminal.getDeviceService().getHealthStandardValueByHealthType2(userId, healthTypes);
    }

    /**
     * 获取用户TOKEN
     */
    protected String getUserToken(Integer userId, String userCode, String token, boolean checkOverTime) {
        /** 判断是否过期 */
        if (checkOverTime) {
            if (StringUtils.isBlank(token) || tokenService.isTokenOverTime(userCode, User.SALT, token)) {
                token = tokenService.createToken(userCode, User.SALT);
                memberService.setToken(userId, token);
            }
            return token;
        }

        /** 不进行过期判断 */
        if (StringUtils.isBlank(token)) {
            token = tokenService.createToken(userCode, User.SALT);
            memberService.setToken(userId, token);
        }
        return token;
    }

    /**
     * 登录操作
     */
    protected Map<String, Object> loginAction(UserDTO user, String terminalType, String ip, String ver,
            String systemVersion, String deviceToken) {
        Integer OS = null;
        switch (terminalType) {
        case Normal.ANDROID_TYPE:
            terminalType = "android";
            OS = 1;
            break;
        case Normal.IOS_TYPE:
            terminalType = "ios";
            OS = 2;
            break;
        default:
            terminalType = "";
            break;
        }
        // 添加登录记录
        addALoginLog(user.getId(), user.getUserName(), terminalType, ip);

        // 获取未读消息数
        int unreadMsgCount = messageService.countSystemUnreadMessage(user.getId(), UserType.member);
        /** 根据版本号判断是否对token过期进行验证，如果是未提供版本号，或者版本号为0的，概不检查token是否过期 */
        boolean checkOverTime = (ver != null);
        String token = user.getToken();
        token = getUserToken(user.getId(), user.getUserCode(), token, checkOverTime);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(User.ID, user.getId());
        map.put(Normal.TOKEN, token);
        map.put(User.CODE, user.getUserCode());
        map.put(HuanXin.USERNAME, user.getUserCode());
        map.put(HuanXin.PASSWORD, HuanXin.NORMAL_PASSWORD);
        map.put(Message.UNREAD_MSG_COUNT, unreadMsgCount);

        // android可能不传systemVersion这个参数
        if (StringUtils.isNotBlank(deviceToken)) {
            // 推送终端信息记录
            UserDeviceTokenPO pushData = new UserDeviceTokenPO();
            pushData.setUserId(user.getId());
            pushData.setOS(OS);
            pushData.setSystemVersion(systemVersion);
            pushData.setDeviceToken(deviceToken);
            try {
                pushDataService.addUserPushToken(pushData);
            } catch (BaseException e) {
                e.printStackTrace();
            }
        }

        /** 2017年8月9日 添加用户的兴趣爱好标签返回 */
        List<UserHobbyPO> userHobbyList = hobbyService.listUserHobby(user.getId());
        List<Map<String, Object>> hobbyList = new ArrayList<>();
        for (UserHobbyPO h : userHobbyList) {
            Map<String, Object> hobby = new HashMap<>();
            hobby.put(Hobby.ID, h.getHobbyId());
            hobby.put(Hobby.NAME, h.getHobbyName());
            hobbyList.add(hobby);
        }
        map.put(UserHobby.HOBBY, hobbyList);
        
        /** 2017年10月30日10:57:23 添加用户vip */
        int vipStatus = 0;
        VipUserPO vipPO = vipUserService.getUserVip1(user.getId());
        if (vipPO != null) {
            vipStatus = 1;
        }
        map.put("vipStatus", vipStatus);
        
        return map;
    }

    /**
     * 添加登录记录
     */
    private void addALoginLog(int userId, String userName, String terminalType, String ip) {
        TLogLogin login = new TLogLogin();
        login.setIp(ip);
        login.setLoginTime(new Date());
        login.setTerminalType(terminalType);
        login.setUserId(userId);
        login.setUserName(userName);
        login.setUserType(1);

        terminal.getCommonTrans().saveLogin(login);
    }

    /**
     * 获取用户的健康指数
     * 
     * @author yuhang.weng
     * @DateTime 2017年8月3日 下午5:55:29
     *
     * @param recordDTO
     * @return
     */
    public static HealthIndexDTO getHealthIndex(UserRecordDTO recordDTO) {
        int point = 0;
        
        // 基础分数（年龄 + 性别）
        int basic = agePoint(recordDTO.getBirthday());
        basic += genderHealthIndex(recordDTO.getGender());
        // 生命体征（腰臀比）
        int vitalSign = wHRHealthIndex(recordDTO.getWHRStatus());
        // 日常测量（BMI）
        int measure = measureDevicePoint(recordDTO);
        // 体质辩证
        int physique = corporeityResultPoint(recordDTO.getCorporeityResult());
        // 亚健康加分点
        int healthStatus = subHealthSymptomHealthIndex(recordDTO);
        // 睡眠加分点
        // point += sleepPoint(recordDTO.getSleepHour(),
        // recordDTO.getBirthday());
        // 问卷加分点
        // point += questionnariePoint(recordDTO);

        point = basic + vitalSign + measure + physique + healthStatus;
        Date date = recordDTO.getModifyDate();
        if (date == null) {
            date = new Date();
        }
        HealthIndexDTO pointDTO = new HealthIndexDTO();
        pointDTO.setPoint(point);
        pointDTO.setEvaluationDate(DateTimeUtilT.date(date));
        
        DetailDTO detail = new DetailDTO();
        detail.setBasic(basic);
        detail.setHealthStatus(healthStatus);
        detail.setMeasure(measure);
        detail.setPhysique(physique);
        detail.setVitalSign(vitalSign);
        pointDTO.setDetail(detail);
        
        return pointDTO;
    }

    /**
     * 1-35岁 15分 36-55 10分 56-64 6分 65岁以上3分
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 上午9:37:44
     *
     * @param birthday
     * @return
     */
    private static int agePoint(Date birthday) {
        int agePoint = 0;
        if (birthday == null) {
            return agePoint;
        }
        int age = DateTimeUtilT.calculateAge(birthday);
        if (age <= 0) {
            return 0;
        }
        if (age <= 35) {
            return 15;
        }
        if (age <= 55) {
            return 10;
        }
        if (age <= 64) {
            return 6;
        }
        return 3;
    }

    /**
     *  性别健康指数值
     *  @author yuhang.weng 
     *  @DateTime 2017年8月7日 下午5:00:46
     *
     *  @param gender
     *  @return
     */
    private static int genderHealthIndex(Boolean gender) {
        // 没有设置返回0分
        if (gender == null) {
            return 0;
        }
        // 男性8分，女性10分
        if (gender) {
            return 8;
        }
        return 10;
    }

    /**
     * 体质
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 上午9:44:13
     *
     * @param corporeityResult
     * @return
     */
    private static int corporeityResultPoint(String corporeityResult) {
        if (StringUtils.isBlank(corporeityResult)) {
            return 0;
        }
        switch (corporeityResult) {
        case "平和质":
            return 20;
        case "阳虚质":
        case "阴虚质":
        case "气虚质":
            return 15;
        case "痰湿质":
        case "湿热质":
            return 10;
        case "气郁质":
        case "血瘀质":
            return 8;
        case "特禀质":
            return 5;
        default:
            return 0;
        }
    }

    /**
     * 亚健康健康指数计算
     * 
     * @author yuhang.weng
     * @DateTime 2017年8月7日 下午4:52:49
     *
     * @param record
     * @return
     */
    private static int subHealthSymptomHealthIndex(UserRecordDTO record) {
        int subHealthSymptomHealthIndex = 0;
        if (record.getSubHealthSymptomScore() != null) {
            subHealthSymptomHealthIndex = record.getSubHealthSymptomScore();
        }
        return subHealthSymptomHealthIndex;
    }

    /**
     * <p>
     * 基础分 15
     * <p>
     * 大于或小于范围内1小时 - 3
     * <p>
     * 大于或小于范围内2小时 - 5
     * <p>
     * 大于或小于范围内3小时以上 - 8
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 上午10:18:14
     *
     * @param hour
     * @param birthday
     * @return
     */
    @SuppressWarnings("unused")
    private static int sleepPoint(Float hour, Date birthday) {
        if (hour == null || birthday == null) {
            return 0;
        }
        int age = DateTimeUtilT.calculateAge(birthday);
        HealthStandardValue<Integer> standard = sleepArea(age);
        Integer min = standard.getMin();
        Integer max = standard.getMax();

        int hourInt = hour.intValue();
        int minInt = min.intValue();
        int maxInt = max.intValue();

        if (hourInt >= minInt && hourInt <= maxInt) {
            return 15;
        }
        if (hourInt >= (minInt + 1) && hourInt <= (maxInt + 1)) {
            return (15 - 3);
        }
        if (hourInt >= (minInt + 2) && hourInt <= (maxInt + 2)) {
            return (15 - 5);
        }
        return (15 - 8);
    }

    /**
     * 睡眠正常范围
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 上午10:19:27
     *
     * @param age
     * @return
     */
    private static HealthStandardValue<Integer> sleepArea(int age) {
        HealthStandardValue<Integer> standard = new HealthStandardValue<>();
        if (age < 1) {
            standard.setMin(20);
            standard.setMax(22);
            return standard;
        }
        if (age == 1) {
            standard.setMin(15);
            standard.setMax(15);
            return standard;
        }
        if (age == 2) {
            standard.setMin(14);
            standard.setMax(14);
            return standard;
        }
        if (age <= 4) {
            standard.setMin(13);
            standard.setMax(13);
            return standard;
        }
        if (age <= 7) {
            standard.setMin(12);
            standard.setMax(12);
            return standard;
        }
        if (age <= 12) {
            standard.setMin(9);
            standard.setMax(9);
            return standard;
        }
        if (age <= 18) {
            standard.setMin(9);
            standard.setMax(9);
            return standard;
        }
        if (age <= 59) {
            standard.setMin(7);
            standard.setMax(8);
            return standard;
        }
        if (age <= 70) {
            standard.setMin(9);
            standard.setMax(9);
            return standard;
        }
        if (age <= 90) {
            standard.setMin(10);
            standard.setMax(10);
            return standard;
        }
        standard.setMin(10);
        standard.setMax(24);
        return standard;
    }

    /**
     * 问卷分数
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 上午10:22:05
     *
     * @param recordDTO
     * @return
     */
    @SuppressWarnings("unused")
    private static int questionnariePoint(UserRecordDTO recordDTO) {
        int point = 0;
        PaperOptionDTO diet = recordDTO.getDietQuestionnaireOption();
        PaperOptionDTO sport = recordDTO.getSportQuestionnaireOption();
        PaperOptionDTO mentalHealth = recordDTO.getMentalHealthQuestionnaireOption();
        if (diet != null) {
            point += diet.getScore();
        }
        if (sport != null) {
            point += sport.getScore();
        }
        if (mentalHealth != null) {
            point += mentalHealth.getScore();
        }
        return point;
    }

    private static int measureDevicePoint(UserRecordDTO recordDTO) {
        int point = 0;

        Integer BMIRankStatus = recordDTO.getBMIRankStatus();

        if (BMIRankStatus != null) {
            int BMIRankStatusInt = BMIRankStatus.intValue();
            // 偏低跟低
            if (BMIRankStatusInt == 1 || BMIRankStatusInt == 2) {
                point += 10;
            }
            // 正常
            if (BMIRankStatusInt == 3) {
                point += 15;
            }
            // 偏高
            if (BMIRankStatusInt == 4) {
                point += 10;
            }
            if (BMIRankStatusInt == 5) {
                point += 8;
            }
        }
//        Boolean heartRateStatus = recordDTO.getHeartRateStatus();
//        Boolean bloodPressureStatus = recordDTO.getBloodPressureStatus();
//        Boolean saturationStatus = recordDTO.getSaturationStatus();
//        Integer vitalCapacityScore = recordDTO.getVitalCapacityScore();
//        Boolean WHRStatus = recordDTO.getWHRStatus();
//        Boolean baseMetabolismStatus = recordDTO.getBaseMetabolismStatus();

//        if (heartRateStatus != null) {
//            point += 10;
//        }
//        if (bloodPressureStatus != null) {
//            if (bloodPressureStatus) {
//                point += 5;
//            } else {
//                point += 10;
//            }
//        }
//        if (saturationStatus != null) {
//            if (saturationStatus) {
//                point += 0;
//            } else {
//                point += 5;
//            }
//        }
//        if (vitalCapacityScore != null) {
//            point += vitalCapacityScore.intValue();
//        }
//        if (WHRStatus != null) {
//            if (WHRStatus) {
//                point += (10 - 5);
//            } else {
//                point += 10;
//            }
//        }
//        if (baseMetabolismStatus != null) {
//            point += 5;
//        }
        return point;
    }

    private static int wHRHealthIndex(Boolean WHRStatus) {
        if (WHRStatus == null) {
            return 0;
        }
        // 异常为true，正常为false
        if (WHRStatus) {
            return 8;
        }
        return 15;
    }
    
    public static int getHealthStatus(int point) {
        if (point < 20) {
            return 1;
        }
        if (point < 40) {
            return 2;
        }
        if (point < 60) {
            return 3;
        }
        if (point < 80) {
            return 4;
        }
        return 5;
    }

    public static String getHealthSuggest(int status) {
        switch (status) {
        case 1:
            return "您的健康状态不在理想状态，请平时养成健康作息，饮食上清淡为主请控制体重，每周至少做3次运动，每次锻炼坚持在30分钟到60分钟，可根据个人情况做调整。建议平时养成每天检测血压、体重的好习惯。";
        case 2:
            return "您的健康属于中等水平，平时饮食搭配均衡，晚上切忌吃宵夜，每周可选择2天到户外锻炼，运动前要先热身。控制好体重对健康尤为重要，和志同道合的朋友多做运动吧。";
        case 3:
            return "您的健康状态很不错，打败了全国75%的人，平时请加强锻炼身体，持之以恒后您会得到意想不到的结果哦。如果结合专业的运动饮食指导，效果会事半功倍噢！";
        case 4:
            return "您的健康指数杠杠滴，打败了全国90%的人哦，请多加保持锻炼，好的健康状态可以让您处理工作时加倍出色！";
        case 5:
            return "您的健康状态非常棒，打败了全国99%的人，需要保持哦！";
        default:
            return "您的健康状态已经爆表，超脱三界之外，强！无敌！";
        }
    }

}
