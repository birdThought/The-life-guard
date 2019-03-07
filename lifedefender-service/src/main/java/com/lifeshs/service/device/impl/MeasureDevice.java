package com.lifeshs.service.device.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.FMRank;
import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthRank;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.constants.common.MessageType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.component.umeng.util.UMengOpenTypeEnum;
import com.lifeshs.dao.device.IDeviceDao;
import com.lifeshs.dao.healthDescription.IHealthDescriptionDao;
import com.lifeshs.dao1.measure.UserInfoReadDao;
import com.lifeshs.dao1.order.IOrderDao;
import com.lifeshs.po.UserInfoRead;
import com.lifeshs.po.vip.VipUserPO;
import com.lifeshs.pojo.health.healthWarningCacheModel;
import com.lifeshs.pojo.healthDescription.NormalHealthPackageDTO;
import com.lifeshs.pojo.healthStandard.BaseMemberDo;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.message.MessageAttachParamDTO;
import com.lifeshs.pojo.message.MessageDTO;
import com.lifeshs.pojo.message.MessagePlaceholderDTO;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.contacts.IContactsService;
import com.lifeshs.service.device.IDeviceService;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service1.measure.MeasureAnalysisService;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.service1.store.employee.IEmployeeService;
import com.lifeshs.service1.vip.IVipUserService;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.thirdservice.UMengPushService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.SMSCommand;

/**
 * 版权归 实现测量设备的数据保存功能,所有测量设备业务类继承此类
 *
 * @param <T> 测量设备的值类型
 * @author dengfeng
 * @DateTime 2016-5-16 下午02:32:45
 */
@Component
public abstract class MeasureDevice<T> extends CommonServiceImpl {

    protected static final Logger smsLogger = Logger.getLogger("SMS");
    
    protected static final Logger logger = Logger.getLogger(MeasureDevice.class);

    @Autowired
    protected IDeviceService deviceService;

    @Autowired
    protected IContactsService contactsService;

    @Autowired
    protected IDeviceDao deviceDao;

    @Autowired
    private SMSService smsService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    protected IHealthDescriptionDao healthDescriptionDao;

    @Autowired
    protected IMemberService memberService;

    @Autowired
    protected MessageService messageService;

    @Autowired
    protected IEmployeeService employeeService;

    @Autowired
    UserInfoReadDao userInfoReadDao;

    @Autowired
    IOrderDao orderDao1;
    
    @Autowired
    UMengPushService uMengPushService;

    @Autowired
    com.lifeshs.service1.member.IMemberService memberService1;

    @Resource(name = "vipUserService")
    protected IVipUserService vipUserService;

    @Resource(name = "measureAnalysisServiceImpl")
    protected MeasureAnalysisService measureAnaylsisService;

    /**
     * 短信的模板
     */
    private final SMSCommand smsCommand = SMSCommand.JKTX;

    private final VcodeTerminalType vcodeTerminalType = VcodeTerminalType.USER_PLATFORM;

    public void getPerfectData(T deviceEntity) {
        perfectData(deviceEntity);
    }

    /**
     * 保存提交的设备数据
     *
     * @param entity 值对象
     * @return
     * @throws Exception
     * @author dengfeng
     * @DateTime 2016-5-13 下午03:08:31
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void save(T entity) throws OperationException {

        // 如果终端未提交设备参数的正常范围值，则完善数据
        perfectData(entity);

        // 由子类重写deviantHandling方法来决定是否发送短信，或其它异常处理
        if (checkStatus(entity)) {
            deviantHandling(entity);
        }
        // 保存数据库
        int result = commonTrans.save(entity);
        if (result == 0) {
            throw new OperationException("保存数据失败", ErrorCodeEnum.FAILED);
        }
    }

    /**
     * 检查数据是否有异常，为true则执行deviantHandling()
     *
     * @param entity
     * @return
     */
    protected abstract boolean checkStatus(T entity);

    /**
     * 检查上传数据是否没有包含健康目标值，为true则执行perfectData()
     *
     * @param entity
     * @return
     */
    protected abstract boolean checkStatusIsNull(T entity);

    /**
     * 设置用户为未处理异常状态
     *
     * @param deviceType  设备类型
     * @param hasWarning  告警设备累计值
     * @param measureDate 设备测量日期
     * @param userId      用户ID
     * @throws Exception
     * @author dengfeng
     * @DateTime 2016-5-16 下午02:06:50
     * @updateTime 2017-09-14 10:51:10
     * @updateReason t_order, t_userInfo_read这两张表都需要更新
     * @updateReason t_user_healthpackage_warning插入操作 2017-10-17
     * @author wuj
     */
    protected void updateUserIsOverstep(int userId, Integer hasWarning, HealthPackageType deviceType, Date measureDate) throws OperationException {
        int bValue = deviceType.value();
        if (hasWarning == null) {
            hasWarning = 0;
        }

        // 如果用户未被设置过未处理异常，设置用户为未处理异常状态
        if (((hasWarning | bValue) != hasWarning)) {
            memberService.updateHasWarning(userId, hasWarning | deviceType.value());
        }

        // TODO 异常 1.订单更新
        orderService.updateUserHealthWarningToOrder(userId, deviceType, measureDate);

        try {
            saveWarningMessage(userId, deviceType, measureDate);
        } catch (DataBaseException e) {
            logger.error("存储异常消息失败");
        }

        // 异常,更新t_userInfo_read
        String measureTime = DateFormatUtils.format(measureDate, "yyyy-MM-dd");
        List<UserInfoRead> list = userInfoReadDao.selectByUserId(userId, measureTime);
        if (list.isEmpty()) {
            // 列表为空,代表门店用户之前没有查看过,跳过数据生成,减小数据库负载

            /*List<Integer> orgUserIds = orderDao1.getOrgUserIds(userId);
            for (Integer orgUserId : orgUserIds) {
                UserInfoRead userInfoRead = new UserInfoRead();
                userInfoRead.setUserId(userId);
                userInfoRead.setMeasureDate(DateTimeUtilT.date(measureTime));
                userInfoRead.setOrgUserId(orgUserId);
                userInfoRead.setDevice(deviceType.value());
                userInfoRead.setIsRead(false);
                userInfoRead.setHasWarning(deviceType.value());
                list.add(userInfoRead);
            }

            userInfoReadDao.batchInsert(list);*/
        } else {
            for (UserInfoRead userInfoRead : list) {
                userInfoRead.setDevice(userInfoRead.getDevice() | deviceType.value());
                userInfoRead.setIsRead(false); // 新数据上传,将消息改成未读
                if (((hasWarning | bValue) != hasWarning)) {
                    memberService.updateHasWarning(userId, hasWarning | deviceType.value());
                }
            }
            userInfoReadDao.batchUpdate(list);
        }
        // 存储用户测量设备异常数据
        memberService1.saveHealthpackageWarning(userId, deviceType, measureDate);
        // 给会员添加一条测量
        VipUserPO vip = vipUserService.getUserVip1(userId);
        if (vip != null) {
            try {
                measureAnaylsisService.addAnalysis(userId, measureDate);
            } catch (BaseException e) {
                logger.error("添加分析异常:" + e.getMessage());
            }
        }
    }

    /**
     * 异常(数据走出正常值范围)处理 由子类重写
     *
     * @author dengfeng
     * @DateTime 2016-5-13 下午03:07:52
     */
    protected abstract void deviantHandling(T deviceEntity) throws OperationException;

    /**
     * 完善数据，给数据对象赋正常范围值和是否异常状态 由子类重写
     *
     * @author dengfeng
     * @DateTime 2016-5-16 下午11:07:52
     */
    protected abstract void perfectData(T deviceEntity);

    /**
     * 调用此方法发送预警短信 由子类调用
     *
     * @throws SMSException
     * @author dengfeng
     * @DateTime 2016-5-13 下午02:58:55 (问题1：发送一次或者两次之后再隔三个小时，不会重新计算次数)
     */
    protected void sendWarningSMS(Integer userId, String name, String message, HealthPackageType deviceType, String deviceName, long status) {
        UserDTO user = memberService.getUser(userId);
        /* 1.判断缓存是否存在,没有则按照勾选项目生成 */
        if (cacheService.getCacheValue(CacheType.SMS_TIMES_BREAK, userId + "") == null) {
            Map<String, healthWarningCacheModel> map = new HashMap<>(); // 将数据存进map
            // {"userId":{"healthType":"healthWarningCacheModel"}}

            /* 心率 */
            if ((user.getHealthWarning() | HealthType.heartRate.value()) == user.getHealthWarning()) {
                healthWarningCacheModel healthWarningCacheModel = new healthWarningCacheModel();
                healthWarningCacheModel.setHealthType(HealthType.heartRate.name());
                healthWarningCacheModel.setTimes(0);
                healthWarningCacheModel.setTimestamp(System.currentTimeMillis());
                map.put("heartRate", healthWarningCacheModel);
            }
            /* 收缩压 */
            if ((user.getHealthWarning() | HealthType.systolic.value()) == user.getHealthWarning()) {
                healthWarningCacheModel healthWarningCacheModel = new healthWarningCacheModel();
                healthWarningCacheModel.setHealthType(HealthType.systolic.name());
                healthWarningCacheModel.setTimes(0);
                healthWarningCacheModel.setTimestamp(System.currentTimeMillis());
                map.put("systolic", healthWarningCacheModel);
            }
            /* 舒张压 */
            if ((user.getHealthWarning() | HealthType.diastolic.value()) == user.getHealthWarning()) {
                healthWarningCacheModel healthWarningCacheModel = new healthWarningCacheModel();
                healthWarningCacheModel.setHealthType(HealthType.diastolic.name());
                healthWarningCacheModel.setTimes(0);
                healthWarningCacheModel.setTimestamp(System.currentTimeMillis());
                map.put("diastolic", healthWarningCacheModel);
            }
            /* 血糖 */
            if ((user.getHealthWarning() | HealthType.bloodSugar.value()) == user.getHealthWarning()) {
                healthWarningCacheModel healthWarningCacheModel = new healthWarningCacheModel();
                healthWarningCacheModel.setHealthType(HealthType.bloodSugar.name());
                healthWarningCacheModel.setTimes(0);
                healthWarningCacheModel.setTimestamp(System.currentTimeMillis());
                map.put("bloodSugar", healthWarningCacheModel);
            }
            /* 血氧 */
            if ((user.getHealthWarning() | HealthType.saturation.value()) == user.getHealthWarning()) {
                healthWarningCacheModel healthWarningCacheModel = new healthWarningCacheModel();
                healthWarningCacheModel.setHealthType(HealthType.saturation.name());
                healthWarningCacheModel.setTimes(0);
                healthWarningCacheModel.setTimestamp(System.currentTimeMillis());
                map.put("saturation", healthWarningCacheModel);
            }
            /* 体温 */
            if ((user.getHealthWarning() | HealthType.temperature.value()) == user.getHealthWarning()) {
                healthWarningCacheModel healthWarningCacheModel = new healthWarningCacheModel();
                healthWarningCacheModel.setHealthType(HealthType.temperature.name());
                healthWarningCacheModel.setTimes(0);
                healthWarningCacheModel.setTimestamp(System.currentTimeMillis());
                map.put("temperature", healthWarningCacheModel);
            }
            /* 心电 */
            if ((user.getHealthWarning() | HealthType.ECG.value()) == user.getHealthWarning()) {
                healthWarningCacheModel healthWarningCacheModel = new healthWarningCacheModel();
                healthWarningCacheModel.setHealthType(HealthType.ECG.name());
                healthWarningCacheModel.setTimes(0);
                healthWarningCacheModel.setTimestamp(System.currentTimeMillis());
                map.put("ECG", healthWarningCacheModel);
            }
            cacheService.saveKeyValue(CacheType.SMS_TIMES_BREAK, userId + "", map);
        }
        /* 2.判断是哪个设备的数据-->得出数据参数-->判断是否可以发短信 */
        int healthWarning = user.getHealthWarning();
        String mobile = contactsService.findReceiveSms(userId); // 获取短信预警联系人
        if (StringUtils.isBlank(mobile)) {
            return;
        }
        boolean bool = false;
        switch (deviceType.name()) {
            case "Band":
                bool = setHealthWarningCache(CacheType.SMS_TIMES_BREAK, userId, HealthType.heartRate, healthWarning);
                break;
            case "BloodPressure":
                if ((status | HealthType.heartRate.value()) == status) { // 为心率异常
                    bool = setHealthWarningCache(CacheType.SMS_TIMES_BREAK, userId, HealthType.heartRate, healthWarning);
                    if (bool) {
                        break;
                    }
                }
                if ((status | HealthType.systolic.value()) == status) {
                    bool = setHealthWarningCache(CacheType.SMS_TIMES_BREAK, userId, HealthType.systolic, healthWarning);
                }
                if ((status | HealthType.diastolic.value()) == status) {
                    bool = setHealthWarningCache(CacheType.SMS_TIMES_BREAK, userId, HealthType.diastolic, healthWarning);
                }

                break;
            case "Oxygen":
                if ((status | HealthType.heartRate.value()) == status) { // 为心率异常
                    bool = setHealthWarningCache(CacheType.SMS_TIMES_BREAK, userId, HealthType.heartRate, healthWarning);
                    if (bool) {
                        break;
                    }
                }
                if ((status | HealthType.saturation.value()) == status) {
                    bool = setHealthWarningCache(CacheType.SMS_TIMES_BREAK, userId, HealthType.saturation, healthWarning);
                }
                break;
            case "Glucometer":
                bool = setHealthWarningCache(CacheType.SMS_TIMES_BREAK, userId, HealthType.bloodSugar, healthWarning);
                break;
            case "Temperature":
                bool = setHealthWarningCache(CacheType.SMS_TIMES_BREAK, userId, HealthType.temperature, healthWarning);
                break;
            case "ECG":
                bool = setHealthWarningCache(CacheType.SMS_TIMES_BREAK, userId, HealthType.ECG, healthWarning);
                break;

            default:
                break;
        }
        if (bool) {
            smsLogger.info("发送短信：" + message);
            try {
                smsService.send(userId, vcodeTerminalType, mobile, smsCommand, true, name, deviceName, message);
            } catch (OperationException e) {
                smsLogger.error(e.getMessage());
            } catch (SMSException e) {
                smsLogger.error(e.getMessage());
            }
//            messageService.saveSystemMessage(receiverId, UserType.orgUser, title, content);
        } else {
            smsLogger.info("不发送短信:" + message);
        }
    }

    /*
     * 判断是否发送短信
     */
    private boolean setHealthWarningCache(CacheType cacheType, int userId, HealthType healthType, int healthWarning) {
        boolean bool = false;
        if ((healthWarning | healthType.value()) == healthWarning) { // 判断是否勾选
            @SuppressWarnings("unchecked")
            Map<String, healthWarningCacheModel> map = (Map<String, healthWarningCacheModel>) cacheService.getCacheValue(cacheType,
                    userId + "");
            if (map.get(healthType.name()) == null) { // 缓存不存在
                bool = true;
                healthWarningCacheModel healthWarningCacheModel = new healthWarningCacheModel();
                healthWarningCacheModel.setHealthType(healthType.name());
                healthWarningCacheModel.setTimes(0);
                healthWarningCacheModel.setTimestamp(System.currentTimeMillis());
                map.put(healthType.name(), healthWarningCacheModel);
                cacheService.saveKeyValue(cacheType, userId + "", map);
            } else { // 缓存存在，进行判断
                /*
                 * System.out.println("size:" + map.size()); for(Entry<String,
                 * healthWarningCacheModel> entry:map.entrySet()){
                 * System.out.println(entry.getKey()+"--->"+entry.getValue() +
                 * "--" + entry.getValue().getHealthType() + "--" +
                 * entry.getValue().getTimes()); }
                 */
                healthWarningCacheModel healthWarningCacheModel = map.get(healthType.name());
                if (healthWarningCacheModel.getTimestamp() < System.currentTimeMillis()) { // 缓存时间戳小于当前系统时间戳
                    bool = true;
                    if (healthWarningCacheModel.getTimes() == 0) { // 第一次发送
                        /* 发送成功之后更新缓存 */
                        healthWarningCacheModel.setTimes(1);
                        healthWarningCacheModel.setTimestamp(System.currentTimeMillis() + 180000);
                        map.put(healthType.name(), healthWarningCacheModel);
                        cacheService.saveKeyValue(cacheType, userId + "", map);
                    } else if (healthWarningCacheModel.getTimes() == 1) { // 第二次发送
                        /* 发送成功之后更新缓存 */
                        healthWarningCacheModel.setTimes(2);
                        healthWarningCacheModel.setTimestamp(System.currentTimeMillis() + 180000);
                        map.put(healthType.name(), healthWarningCacheModel);
                        cacheService.saveKeyValue(cacheType, userId + "", map);
                    } else if (healthWarningCacheModel.getTimes() == 2) { // 第三次发送
                        /* 发送成功之后更新缓存 */
                        healthWarningCacheModel.setTimes(0);
                        healthWarningCacheModel.setTimestamp(System.currentTimeMillis() + 10800000);
                        map.put(healthType.name(), healthWarningCacheModel);
                        cacheService.saveKeyValue(cacheType, userId + "", map);
                    }
                }

            }
        }
        return bool;
    }

    /**
     * @return
     * @author zhiguo.lin
     * @DateTime 2016年8月31日 下午2:15:54
     * @serverComment 服务注解
     */
    protected long getStatus(Float min, Float max, Float value, long status, long healthType) {
        if ((value != null) && (min > value || value > max)) {
            status |= healthType;
        }
        return status;
    }

    protected long getStatus(FMRank fMRank, long status, long healthType) {
        if (!FMRank.Normal.equals(fMRank)) {
            status |= healthType;
        }
        return status;
    }

    /**
     * @param min
     * @param max
     * @return
     * @author zhiguo.lin
     * @DateTime 2016年8月31日 下午2:11:22
     * @serverComment 获取正常范围值
     */
    protected String getArea(Float min, Float max) {
        StringBuilder sb = new StringBuilder();
        sb.append(min).append("-").append(max);
        return sb.toString();
    }

    /**
     * 获取健康数值状态
     * <p>其中几个参数的大小关系为 min < less < more < max
     *
     * @param min   最小值
     * @param less  偏小值
     * @param more  偏大值
     * @param max   最大值
     * @param value 测量数值
     * @return 状态(低_1, 偏低_2, 正常_3, 偏高_4, 高_5) value值为空就返回正常
     * @author yuhang.weng
     * @DateTime 2017年2月16日 上午9:01:10
     */
    protected HealthRank getHealthValueStatus(Float min, Float less, Float more, Float max, Float value) {
        /** 如果不提供数值就直接返回正常 */
        if (value == null) {
            return HealthRank.normal;
        }
        /** 区间点数数量 */
        int pointCount = 0;
        if (min != null) {
            pointCount++;
        }
        if (less != null) {
            pointCount++;
        }
        if (more != null) {
            pointCount++;
        }
        if (max != null) {
            pointCount++;
        }

        switch (pointCount) {
            case 1: /** (min | max) */
                return oneStandardPoint(min, max, value);
            case 2: /** ((min & less) | (min & max) | (more & max)) */
                return twoStandardPoint(min, less, more, max, value);
            case 3: /** ((min & less & max) | (min & more & max)) */
                return threeStandardPoint(min, less, more, max, value);
            case 4: /** (min & less & more & max) */
                return fourStandardPoint(min, less, more, max, value);
            default:
                return HealthRank.normal;
        }
    }

    private HealthRank oneStandardPoint(Float min, Float max, Float value) {
        boolean minIsNull = (min == null);

        /** 只有最大值 */
        if (minIsNull) {
            if (value < max) {
                return HealthRank.normal;
            }
            return HealthRank.more;
        }

        /** 只有最小值 */
        if (value < min) {
            return HealthRank.less;
        }
        return HealthRank.normal;
    }

    private HealthRank twoStandardPoint(Float min, Float less, Float more, Float max, Float value) {
        boolean minIsNull = (min == null);
        boolean lessIsNull = (less == null);

        if (minIsNull) {
            if (value < more) {
                return HealthRank.normal;
            }
            if (value < max) {
                return HealthRank.max;
            }
            return HealthRank.more;
        }

        if (lessIsNull) {
            if (value < min) {
                return HealthRank.less;
            }
            if (value <= max) {
//          if (value < max) {  // 正常值 修改为包左包右
                return HealthRank.normal;
            }
            return HealthRank.more;
        }

        if (value < min) {
            return HealthRank.less;
        }
        if (value < less) {
            return HealthRank.min;
        }
        return HealthRank.normal;
    }

    private HealthRank threeStandardPoint(Float min, Float less, Float more, Float max, Float value) {
        boolean lessIsNull = (less == null);

        /** 只有三个点，并且三个点中min与max必有 */
        if (value < min) {
            return HealthRank.less;
        }
        if (value >= max) {
            return HealthRank.more;
        }

        /** 只有min，more，max三个点 */
        if (lessIsNull) {
            /** 只要小于more的都算正常，大于的算偏高 */
            if (value < more) {
                return HealthRank.normal;
            }
            return HealthRank.max;
        }

        /** 只有min，less，max三个点 */
        /** 只要小于less的算偏低，大于的算正常 */
        if (value < less) {
            return HealthRank.min;
        }
        return HealthRank.normal;
    }

    private HealthRank fourStandardPoint(Float min, Float less, Float more, Float max, Float value) {
        if (value < min) {
            return HealthRank.less;
        }
        if (value < less) {
            return HealthRank.min;
        }
        if (value < more) {
            return HealthRank.normal;
        }
        if (value < max) {
            return HealthRank.max;
        }
        return HealthRank.more;
    }

    /**
     * 获取健康描述文字
     *
     * @param sex                性别
     * @param age                年龄
     * @param healthPackageValue 健康包二进制数值
     * @param status             状态
     * @return
     * @author yuhang.weng
     * @DateTime 2017年2月20日 下午1:44:21
     */
    protected List<NormalHealthPackageDTO> getHealthValueDescription(Boolean sex, Integer age, Integer healthPackageValue, Integer status) {
        return healthDescriptionDao.listNormalHealthPackageDescription(sex, age, healthPackageValue, status);
    }

    @SuppressWarnings({"hiding"})
    public <T> T getMeasureLastedData(Class<T> clazz, int userId, String deviceType, String measureDate) {
        return deviceService.selectDeviceDataLastest(clazz, userId, deviceType, measureDate);
    }

    public static boolean isHealthDeviceUnusual(long status, HealthType healthType) {
        boolean isUnususl = false;
        long hValue = healthType.value();
        if ((status & hValue) == hValue) {
            isUnususl = true;
        }

        return isUnususl;
    }

    protected BaseMemberDo getMemberBaseData(UserRecordDTO recordDTO) {
        BaseMemberDo base = new BaseMemberDo();

        // 性别
        Boolean sex = recordDTO.getGender();
        if (sex == null) {
            sex = true;
        }
        // 年龄
        Date birthday = recordDTO.getBirthday();
        Integer age = 10;
        if (birthday != null) {
            age = DateTimeUtilT.calculateAge(birthday);
        }
        // 身高
        float height = recordDTO.getHeight() == null ? 150 : recordDTO.getHeight();
        // 体重
        float weight = recordDTO.getWeight() == null ? 50 : recordDTO.getWeight();

        base.setSex(sex);
        base.setAge(age);
        base.setHeight(height);
        base.setWeight(weight);
        return base;
    }

    /**
     * 判断级别是否为异常
     *
     * @param healthRank
     * @return
     */
    protected boolean isRankStatusUnusual(HealthRank healthRank) {
        return !healthRank.equals(HealthRank.normal);
    }

    protected FMRank getFMValueUnusual(String FMValue) {
        if (!StringUtils.contains(FMValue, "+")) {
            return FMRank.Normal;
        }
        String tail = FMValue.substring(1);
        if ("-".equals(tail) || "".equals(tail)) {
            return FMRank.UNUSUAL;
        }
        Integer rank = Integer.valueOf(tail);
        if (rank.intValue() < 3) {
            return FMRank.UNUSUAL;
        }
        return FMRank.SERIOUS;
    }

    protected String compareArea(Object value, String area) {
        if (StringUtils.isBlank(area)) {
            return null;
        }
        Float v = Float.valueOf(String.valueOf(value));
        String[] a = area.split("-");
        Float min = Float.valueOf(a[0]);
        Float max = Float.valueOf(a[1]);
        if (v < min) {
            return "低于";
        }
        if (v >= max) {
            return "高于";
        }
        return null;
    }

    /**
     *  @param userId 用户id
     *  @param deviceType 异常设备类型
     *  @param date 异常日期
     */
    protected void saveWarningMessage(int userId, HealthPackageType deviceType, Date date) throws DataBaseException {

        uMengPushService.saveWarningMessage(userId, deviceType, date);
    }
}
