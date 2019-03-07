package com.lifeshs.service.terminal.app.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Diet;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.Org;
import com.lifeshs.common.constants.app.RecordSport;
import com.lifeshs.common.constants.app.healthPackage.HealthPackage;
import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.dao1.measure.EcgDao;
import com.lifeshs.entity.device.TMeasureBloodlipid;
import com.lifeshs.entity.device.TMeasureBloodpressure;
import com.lifeshs.entity.device.TMeasureBodyfatscale;
import com.lifeshs.entity.device.TMeasureGlucometer;
import com.lifeshs.entity.device.TMeasureHeartrate;
import com.lifeshs.entity.device.TMeasureLunginstrument;
import com.lifeshs.entity.device.TMeasureOxygen;
import com.lifeshs.entity.device.TMeasureTemperature;
import com.lifeshs.entity.device.TMeasureUa;
import com.lifeshs.entity.device.TMeasureUran;
import com.lifeshs.entity.device.TSportBand;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.healthDevice.EcgDTO;
import com.lifeshs.pojo.healthDevice.EcgDetailDTO;
import com.lifeshs.pojo.healthStandard.HealthStandardValueEx;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.device.impl.MeasureDevice;
import com.lifeshs.service.device.impl.healthStandard.HealthStandard;
import com.lifeshs.service.terminal.ITerminalService;
import com.lifeshs.service.terminal.impl.TerminalAdaptee;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.image.ImageUtilV2;
import com.lifeshs.vo.data.area.CodeVO;

public class AppBaseService {

    private static final Logger logger = Logger.getLogger(AppBaseService.class);

    /**
     * 适配源，标准功能的对象
     */
    @Autowired
    protected TerminalAdaptee terminal;

    @Autowired
    protected ITerminalService terminalService;

    @Autowired
    private IDataAreaService areaService;

    @Autowired
    EcgDao ecgDao;

    /**
     * 将data中的值全部转换为string类型的数据
     *
     * @author yuhang.weng
     * @DateTime 2016年12月19日 下午2:59:20
     *
     * @param data
     * @return
     */
    @SuppressWarnings("unchecked")
    protected static JSONObject value2String(Map<String, Object> data) {
        for (String key : data.keySet()) {
            Object value = data.get(key);

            // 非null处理
            if (value == null || value.equals("null")) {
                value = "";
            }

            // 特定date转换
            if (value instanceof Date) {
                Date date_cast_value = (Date) value;

                switch (key) {
                    case Diet.TIME:
                        data.put(key, DateTimeUtilT.time(date_cast_value));
                        break;
                    case HealthPackage.MEASUREDATE:
                    case Normal.CREATE_DATE:
                    case Normal.MODIFY_DATE:
                        data.put(key, DateTimeUtilT.dateTime(date_cast_value));
                        break;
                    case RecordSport.START_TIME:
                        data.put(key, DateTimeUtilT.time(date_cast_value).substring(0, 5));
                        break;
                    default:
                        data.put(key, DateTimeUtilT.date(date_cast_value));
                }

                continue;
            } else if (value instanceof Boolean) {
                Boolean boolean_cast_value = (Boolean) value;
                if (boolean_cast_value) {
                    data.put(key, "1");
                }
                if (!boolean_cast_value) {
                    data.put(key, "0");
                }

                continue;
            } else if (value instanceof Map) {
                // 调用map方法
                Map<String, Object> map_cast_value = (Map<String, Object>) value;
                data.put(key, value2String(map_cast_value));
            } else if (value instanceof List) {
                // 调用list方法
                List<Object> list_cast_value = (List<Object>) value;
                data.put(key, value2String(list_cast_value));
            } else {
                // 其它值转换
                if (value instanceof String) {
                    String string_cast_value = (String) value;

                    if (key.equals("image") || key.equals("img") || key.equals("img1") || key.equals("img2")
                            || key.equals("img3") || key.equals("photo") || key.equals("logo")
                            || key.equals(Org.BUSINESS_LICENSE) || key.equals("banner")) {

                        // 获取设备列表的图片路径内容为"1"
                        if (string_cast_value.equals("1")) {
                            data.put(key, value + "");
                            continue;
                        }

                        // 只要图片内容不为空，同时为了兼容之前的代码，在原有基础上如果包含了前缀就不坐考虑，就为其补上前缀
                        if (StringUtils.isNotBlank(string_cast_value)
                                && !string_cast_value.startsWith(Normal.PHOTO_PREFIX) && !string_cast_value.startsWith("http://www.lifekeepers.cn") && !string_cast_value.startsWith("http://apit.lifeshs.com") && !string_cast_value.startsWith("http://csc.lifekeepers.cn")) {
                            // TODO 对不规范的斜杠进行替换,暂时这么处理，后续会在保存图片的时候就对图片的斜杠进行规范
                            // 利用正则把斜杠替换
                            string_cast_value = string_cast_value.replaceAll("\\\\", "/");
                            string_cast_value = string_cast_value.replaceAll("//", "/");

                            // TODO 文件路径中缺少'/',导致图片路径错误
                            if (!string_cast_value.startsWith("/")) {
                                string_cast_value = "/" + string_cast_value;
                            }
                            data.put(key, Normal.PHOTO_PREFIX + string_cast_value);
                            continue;
                        }
                    }

                }
                data.put(key, value + "");
            }
        }

        return (JSONObject) JSONObject.toJSON(data);
    }

    /**
     * 将datas中的值全部转换为string类型的数据
     *
     * @author yuhang.weng
     * @DateTime 2016年12月19日 下午2:59:20
     *
     * @param datas
     * @return
     */
    @SuppressWarnings("unchecked")
    protected static JSONArray value2String(List<Object> datas) {
        for (int i = 0; i < datas.size(); i++) {
            Object data = datas.get(i);

            if (data instanceof List) {
                // 调用list方法
                List<Object> list_cast_value = (List<Object>) data;
                datas.set(i, value2String(list_cast_value));
                continue;
            }
            if (data instanceof Map) {
                // 调用map方法
                Map<String, Object> map_cast_value = (Map<String, Object>) data;
                datas.set(i, value2String(map_cast_value));
                continue;
            }
        }
        return (JSONArray) JSONArray.toJSON(datas);
    }


    /**
     * mApp上传图片处理方法
     *
     * @author yuhang.weng
     * @DateTime 2016年10月13日 上午10:11:37
     *
     * @param base64 base64字符串
     * @param oldNetPath 原图片网络路径
     * @param categoryPath 目录
     * @param tmpFile 是否为临时文件
     * @return
     */
    public static ImageDTO uploadPhoto(String base64, String oldNetPath, String categoryPath, boolean tmpFile) {
        ImageDTO imageVO = new ImageDTO();

        if ("".equals(base64)) {
            imageVO.setNetPath(""); // 如果base64的数值是空字符串，设置返回的相对路径为空字符串，替换原来的图片地址
        } else {
            // 图片保存
            String netPath = null;
            // 暂时通过手动方式将url中的空格修改回加号
            base64 = base64.replaceAll(" ", "\\+");
            try {
                netPath = ImageUtilV2.saveBase64(base64, categoryPath, tmpFile);
            } catch (Exception e) {
                logger.error("图片保存失败：" + e.getMessage());
                imageVO.setUploadSuccess(false);
                imageVO.setDelSuccess(false);
                return imageVO;
            }
            imageVO.setNetPath(netPath);
        }

        // 删除旧图片
        boolean delelte = ImageUtilV2.delImg(oldNetPath);

        imageVO.setUploadSuccess(true);
        imageVO.setDelSuccess(delelte);

        return imageVO;
    }

    public List<Map<String, Object>> getUserAllData(String deviceType, UserDTO user, String date,
                                                    boolean addVitalCapacityScore) {
        List<Map<String, Object>> list = new ArrayList<>();
        int device = 0; // 存在数据的设备
        int userId = user.getId();

        switch (deviceType) {
            case "0":
                break;
            case "APP":
                // 判断用户有哪些健康设备
                Map<String, Object> map = new HashMap<>();

                TSportBand sportBand = terminal.getBand().getMeasureLastedData(TSportBand.class, userId, deviceType,
                        date);// 运动腕表
                TMeasureHeartrate measureHeartrate = terminal.getHeartRate().getMeasureLastedData(TMeasureHeartrate.class, userId,
                        deviceType, date); // 心率

                if (sportBand == null && measureHeartrate != null) {
                    Long status = measureHeartrate.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.Band.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureHeartrate.getMeasureDate()));
                    List<Map<String, Object>> list2 = new ArrayList<>();
                    getHashMap(HealthType.heartRate, measureHeartrate.getHeartRate(), status, list2);
                    map.put("param", list2);
                    list.add(map);

                    device |= HealthPackageType.Band.value();
                }

                if (sportBand != null) {
                    Long status = measureHeartrate == null ? null : measureHeartrate.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.Band.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME,date+" 00:00:00");
                    List<Map<String, Object>> list2 = new ArrayList<>();
                    getHashMap(HealthType.heartRate, measureHeartrate == null ? null : measureHeartrate.getHeartRate(), status, list2);
                    getHashMap(HealthPackage.STEP, sportBand.getSteps(), list2);
                    getHashMap(HealthPackage.MILEAGE, sportBand.getMileage(), list2);
                    getHashMap(HealthPackage.KCAL, sportBand.getKcal(), list2);
                    getHashMap(HealthPackage.SHALLOW_DURATION, sportBand.getShallowDuration(), list2);
                    getHashMap(HealthPackage.DEEP_DURATION, sportBand.getDeepDuration(), list2);
                    getHashMap(HealthPackage.WAKEUP_DURATION, sportBand.getWakeupDuration(), list2);
                    map.put("param", list2);
                    list.add(map);

                    device |= HealthPackageType.Band.value();
                }

                map = new HashMap<>();
                TMeasureBodyfatscale measureBodyfatscale = new TMeasureBodyfatscale();

                measureBodyfatscale.setUserId(userId);
                measureBodyfatscale = terminal.getBodyfatscale().getMeasureLastedData(TMeasureBodyfatscale.class,
                        userId, deviceType, date); //体脂称
                if (measureBodyfatscale != null) {
                    Long status = measureBodyfatscale.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.BodyFatScale.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureBodyfatscale.getMeasureDate()));
                    List<Map<String, Object>> list2 = new ArrayList<>();
                    getHashMap(HealthType.weight, measureBodyfatscale.getWeight(), status, list2);
                    getHashMap(HealthType.axungeRatio, measureBodyfatscale.getAxungeRatio(), status, list2);
                    getHashMap(HealthType.WHR, measureBodyfatscale.getWHR(), status, list2);
                    getHashMap(HealthType.BMI, measureBodyfatscale.getBMI(), status, list2);
                    getHashMap(HealthType.fatFreeWeight, measureBodyfatscale.getFatFreeWeight(), status, list2);
                    getHashMap(HealthType.muscle, measureBodyfatscale.getMuscle(), status, list2);
                    getHashMap(HealthType.moisture, measureBodyfatscale.getMoisture(), status, list2);
                    getHashMap(HealthType.boneWeight, measureBodyfatscale.getBoneWeight(), status, list2);
                    getHashMap(HealthType.bodyage, measureBodyfatscale.getBodyage(), status, list2);
                    getHashMap(HealthType.baseMetabolism, measureBodyfatscale.getBaseMetabolism(), status, list2);
                    getHashMap(HealthPackage.PROTEIDE, measureBodyfatscale.getProteide(), list2);
                    getHashMap(HealthType.visceralFat, measureBodyfatscale.getVisceralFat(), status, list2);

                    map.put("param", list2);
                    list.add(map);

                    device |= HealthPackageType.BodyFatScale.value();
                }

                map = new HashMap<>();
                TMeasureBloodpressure measureBloodpressure = new TMeasureBloodpressure();

                measureBloodpressure.setUserId(userId);
                measureBloodpressure = terminal.getBloodPressure().getMeasureLastedData(TMeasureBloodpressure.class,
                        userId, deviceType, date);  //血压仪
                if (measureBloodpressure != null) {
                    Long status = measureBloodpressure.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.BloodPressure.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureBloodpressure.getMeasureDate()));
                    List<Map<String, Object>> list2 = new ArrayList<>();
                    getHashMap(HealthType.diastolic, measureBloodpressure.getDiastolic(), status, list2);
                    getHashMap(HealthType.systolic, measureBloodpressure.getSystolic(), status, list2);
                    getHashMap(HealthType.heartRate, measureBloodpressure.getHeartRate(), status, list2);
                    map.put("param", list2);
                    list.add(map);

                    device |= HealthPackageType.BloodPressure.value();
                }

                map = new HashMap<>();
                TMeasureLunginstrument measureLunginstrument = new TMeasureLunginstrument();

                measureLunginstrument.setUserId(userId);
                measureLunginstrument = terminal.getLunginstrument().getMeasureLastedData(TMeasureLunginstrument.class,
                        userId, deviceType, date);  //肺活仪
                if (measureLunginstrument != null) {
                    Long status = measureLunginstrument.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.Lunginstrument.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureLunginstrument.getMeasureDate()));
                    List<Map<String, Object>> list2 = new ArrayList<>();
                    if (addVitalCapacityScore) {
                        UserRecordDTO recordDTO = user.getRecordDTO();
                        int score = getLunginstrumentScore(measureLunginstrument.getVitalCapacity(), recordDTO.getGender(),
                                recordDTO.getBirthday());

                        Map<String, Object> vm = new HashMap<>();
                        boolean isUnusual = MeasureDevice.isHealthDeviceUnusual(status, HealthType.vitalCapacity);
                        vm.put("paramName", HealthType.vitalCapacity.name());
                        vm.put("paramValue", measureLunginstrument.getVitalCapacity() + "/" + score);
                        vm.put("status", isUnusual ? "1" : "0");

                        list2.add(vm);
                        getHashMap(HealthType.pef, measureLunginstrument.getPef(), status, list2);
                        getHashMap(HealthType.af, measureLunginstrument.getAf(), status, list2);
                        map.put("param", list2);
                        list.add(map);
                    } else {
                        getHashMap(HealthType.vitalCapacity, measureLunginstrument.getVitalCapacity(), status, list2);
                        getHashMap(HealthType.pef, measureLunginstrument.getPef(), status, list2);
                        getHashMap(HealthType.af, measureLunginstrument.getAf(), status, list2);
                        map.put("param", list2);
                        list.add(map);
                    }

                    device |= HealthPackageType.Lunginstrument.value();
                }

                map = new HashMap<>();
                TMeasureGlucometer measureGlucometer = new TMeasureGlucometer(); //血糖仪
                measureGlucometer.setUserId(userId);

                measureGlucometer = terminal.getGlucometer().getMeasureLastedData(TMeasureGlucometer.class, userId,
                        deviceType, date);
                if (measureGlucometer != null) {
                    Long status = measureGlucometer.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.Glucometer.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureGlucometer.getMeasureDate()));
                    List<Map<String, Object>> list2 = new ArrayList<>();
                    getHashMap(HealthType.bloodSugar, measureGlucometer.getBloodSugar(), status, list2);
                    map.put("param", list2);
                    list.add(map);

                    device |= HealthPackageType.Glucometer.value();
                }

                map = new HashMap<>();
                EcgDTO ecgDTO = terminal.getEcg().selectByUserIdAndDate(userId, DateTimeUtilT.date(date));
                if (ecgDTO != null && !ecgDTO.getDetailList().isEmpty()) {
                    EcgDetailDTO ed = ecgDTO.getDetailList().get(0);    // 获取第一条数据
                    Integer status = ed.getStatus() == null ? 0 : ed.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.ECG.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(ed.getMeasureDate()));
                    List<Map<String, Object>> list2 = new ArrayList<>();
                    getHashMap(HealthType.heartRate, ed.getHeartRate(), status.longValue(), list2);
                    getHashMap(HealthType.ECG, ed.getImage(), status.longValue(), list2);
                    map.put("param", list2);
                    list.add(map);

                    device |= HealthPackageType.ECG.value();
                }


                map = new HashMap<>();
                TMeasureOxygen measureOxygen = new TMeasureOxygen(); //血氧仪

                measureOxygen.setUserId(userId);
                measureOxygen = terminal.getOxygen().getMeasureLastedData(TMeasureOxygen.class, userId, deviceType,
                        date);
                if (measureOxygen != null) {
                    Long status = measureOxygen.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.Oxygen.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureOxygen.getMeasureDate()));
                    List<Map<String, Object>> list2 = new ArrayList<>();
                    getHashMap(HealthType.saturation, measureOxygen.getSaturation(), status, list2);
                    getHashMap(HealthType.heartRate, measureOxygen.getHeartRate(), status, list2);
                    map.put("param", list2);
                    list.add(map);

                    device |= HealthPackageType.Oxygen.value();
                }

                map = new HashMap<>();
                TMeasureTemperature measureTemperature = new TMeasureTemperature(); //体温计

                measureTemperature.setUserId(userId);
                measureTemperature = terminal.getTemperature().getMeasureLastedData(TMeasureTemperature.class, userId,
                        deviceType, date);
                if (measureTemperature != null) {
                    Long status = measureTemperature.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.Temperature.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureTemperature.getMeasureDate()));
                    List<Map<String, Object>> list2 = new ArrayList<>();
                    getHashMap(HealthType.temperature, Math.floor(measureTemperature.getTemperature() * 10) / 10, status, list2);
                    map.put("param", list2);
                    list.add(map);

                    device |= HealthPackageType.Temperature.value();
                }

                map = new HashMap<>();

                TMeasureBloodlipid measureBloodLipid = terminal.getBloodLipid().getMeasureLastedData(TMeasureBloodlipid.class,
                        userId, deviceType, date);  //血脂仪

                if (measureBloodLipid != null) {
                    Long status = measureBloodLipid.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.BloodLipid.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureBloodLipid.getMeasureDate()));

                    List<Map<String, Object>> list2 = new ArrayList<>();
                    getHashMap(HealthType.HDL, measureBloodLipid.getHDL(), status, list2);
                    getHashMap(HealthType.LDL, measureBloodLipid.getLDL(), status, list2);
                    getHashMap(HealthType.TC, measureBloodLipid.getTC(), status, list2);
                    getHashMap(HealthType.TG, measureBloodLipid.getTG(), status, list2);
                    getHashMap(HealthType.BloodLipidRation, measureBloodLipid.getBloodLipidRatio(), status, list2);
                    map.put("param", list2);
                    list.add(map);

                    device |= HealthPackageType.BloodLipid.value();
                }

                map = new HashMap<>();

                TMeasureUran measureUran = terminal.getUran().getMeasureLastedData(TMeasureUran.class, userId, deviceType,
                        date); //尿液分析仪

                if (measureUran != null) {
                    Long status = measureUran.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.URAN.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureUran.getMeasureDate()));

                    List<Map<String, Object>> list2 = new ArrayList<>();
                    getHashMap(HealthType.LEU, measureUran.getLEU(), status, list2);
                    getHashMap(HealthType.NIT, measureUran.getNIT(), status, list2);
                    getHashMap(HealthType.UBG, measureUran.getUBG(), status, list2);
                    getHashMap(HealthType.PRO, measureUran.getPRO(), status, list2);
                    getHashMap(HealthType.pH, measureUran.getPH(), status, list2);
                    getHashMap(HealthType.BLD, measureUran.getBLD(), status, list2);
                    getHashMap(HealthType.SG, measureUran.getSG(), status, list2);
                    getHashMap(HealthType.KET, measureUran.getKET(), status, list2);
                    getHashMap(HealthType.BIL, measureUran.getBIL(), status, list2);
                    getHashMap(HealthType.GLU, measureUran.getGLU(), status, list2);
                    getHashMap(HealthType.VC, measureUran.getVC(), status, list2);
                    map.put("param", list2);
                    list.add(map);

                    device |= HealthPackageType.URAN.value();
                }

                map = new HashMap<>();

                TMeasureUa measureUa = terminal.getUa().getMeasureLastedData(TMeasureUa.class, userId, deviceType,
                        date);  //尿检分析仪
                if (measureUa != null) {
                    Long status = measureUa.getStatus();
                    map.put(Normal.DEVICE_NAME, HealthPackageType.UA.name());
                    map.put(HealthPackage.STATUS, status + "");
                    map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureUa.getMeasureDate()));

                    List<Map<String, Object>> list2 = new ArrayList<>();
                    getHashMap(HealthType.UA, measureUa.getUA(), status, list2);
                    map.put("param", list2);
                    list.add(map);

                    device |= HealthPackageType.UA.value();
                }

                map = new HashMap<>(1);
                map.put("device",device);
                list.add(map);
                break;
            case "HL-031":
                break;
            default:
                break;
        }
        return list;
    }

    private void getHashMap(String param, Object value, List<Map<String, Object>> root) {
        if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
            Map<String, Object> map = new HashMap<>();
            map.put("paramName", param);
            map.put("paramValue", "" + value);
            root.add(map);
        }
    }

    private void getHashMap(HealthType healthType, Object value, Long status, List<Map<String, Object>> root) {
        if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
            Map<String, Object> map = new HashMap<>();
            boolean isUnusual = true;
            if (status != null) {
                isUnusual = MeasureDevice.isHealthDeviceUnusual(status, healthType);
            }
            map.put("paramName", healthType.name());
            map.put("paramValue", "" + value);
            map.put("status", isUnusual ? "1" : "0");
            root.add(map);
        }
    }

    private int getLunginstrumentScore(int value, boolean sex, Date birthday) {
        int age = DateTimeUtilT.calculateAge(birthday);
        HealthStandardValueEx<Integer> hv = HealthStandard.getVitalCapacity(sex, age);
        Integer less = hv.getLess();
        Integer min = hv.getMin();
        Integer max = hv.getMax();
        Integer more = hv.getMore();

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

    public static String getProjectName(String name) {
        String regex = "\\[+\\]";
        String projectName = name.replaceAll(regex, name);
        return projectName;
    }

    /**
     *  规范化处理图片路径
     *  @author yuhang.weng
     *	@DateTime 2017年7月11日 下午2:36:41
     *  @updateBy wuj 2017-08-29 11:01:50
     *  @updateReason 增加参数合法性判断
     *
     *  @param image
     *  @return
     */
    public static String normativeApproachImagePath(String image) {
        if (StringUtils.isBlank(image)){
            return "";
        }

        if (image.startsWith(Normal.PHOTO_PREFIX)) {
            return image;
        }
        if (StringUtils.isNotBlank(image)) {
            // TODO 对不规范的斜杠进行替换,暂时这么处理，后续会在保存图片的时候就对图片的斜杠进行规范
            image = image.replaceAll("\\\\", "/");
            image = image.replaceAll("//", "/");
            if (image.startsWith("/")) {
                image = image.substring(1);
            }
            image = Normal.PHOTO_PREFIX + "/" + image;
        } else {
            image = "";
        }
        return image;
    }

    /**
     * 设置地址
     */
    public CodeVO setAddress(String district, String city, String province) {
        String code = "";
        String provinceCode = null;
        String cityCode = null;
        String districtCode = null;
        boolean isDistrictBlank = StringUtils.isBlank(district);
        if (!isDistrictBlank) {

            if (district.equals("其它") || district.equals("其他")) {

            } else {

            }

            List<String> codes = areaService.getAreaCode(district);
            if (codes.size() == 0) {

            }
            if (codes.size() == 1) {
                code = codes.get(0);
            }
            if (codes.size() > 1) {
                for (String c : codes) {
                    String c_city = areaService.getAreaCode(city).get(0).substring(0, 4);
                    String d_city = c.substring(0, 4);
                    if (StringUtils.equals(c_city, d_city)) {
                        code = c;
                        break;
                    }
                }
            }
            boolean isCodeBlank = StringUtils.isBlank(code);
            if (!isCodeBlank) {
                provinceCode = code.substring(0, 2);
                cityCode = code.substring(2, 4);
                districtCode = code.substring(4, 6);
            }
        }

        CodeVO codeVO = new CodeVO();
        codeVO.setProvince(provinceCode);
        codeVO.setCity(cityCode);
        codeVO.setCounty(districtCode);

        return codeVO;
    }
}
