package com.lifeshs.service.device.impl.healthStandard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.lifeshs.common.constants.app.healthPackage.BloodLipid;
import com.lifeshs.common.constants.app.healthPackage.HealthPackage;
import com.lifeshs.common.constants.app.healthPackage.Ua;
import com.lifeshs.common.constants.app.healthPackage.Uran;
import com.lifeshs.pojo.healthStandard.HealthStandardValue;
import com.lifeshs.pojo.healthStandard.HealthStandardValueEx;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.NumberUtils;

/**
 * 健康范围值数据
 * 
 * @author dengfeng
 *
 */
public class HealthStandard {

    /**
     * 根据用户参数得到其健康范围值
     * 
     * @param sex
     *            性别 男：true
     * @param age
     *            年龄
     * @param height
     *            身高(cm)
     * @param weight
     *            体重(kg)
     * @return
     */
    public static Map<String, Object> getAllByUser(boolean sex, int age, float height, float weight) {
        Map<String, Object> map = new HashMap<String, Object>();

        HealthStandardValue<Integer> standardInt = getHeartRate(sex, age);
        map.put(HealthPackage.HEARTRATE, standardInt.toMap());

        standardInt = getTemperature();
        map.put(HealthPackage.TEMPERATURE, standardInt.toMap());

        standardInt = getSaturation();
        map.put(HealthPackage.SATURATION, standardInt.toMap());

        standardInt = getSystolic(sex, age);
        map.put(HealthPackage.SYSTOLIC, standardInt.toMap());

        standardInt = getDiastolic(sex, age);
        map.put(HealthPackage.DIASTOLIC, standardInt.toMap());

        standardInt = getWeight(age, height);
        map.put(HealthPackage.WEIGHT, standardInt.toMap());

        standardInt = getAxungeRatio(sex, age);
        map.put(HealthPackage.AXUNGERATIO, standardInt.toMap());

        standardInt = getMuscle(sex);
        map.put(HealthPackage.MUSCLE, standardInt.toMap());

        standardInt = getMoisture(sex);
        map.put(HealthPackage.MOISTURE, standardInt.toMap());

        standardInt = getBodyage(age);
        map.put(HealthPackage.BODY_AGE, standardInt.toMap());

        standardInt = getVisceralFat();
        map.put(HealthPackage.VISCERALFAT, standardInt.toMap());

        HealthStandardValue<String> standardFloat = getWHR(sex);
        map.put(HealthPackage.WHR, standardFloat.toMap());

        standardFloat = getBaseMetabolism(sex, age, weight, height);
        map.put(HealthPackage.BASE_METABOLISM, standardFloat.toMap());

        standardFloat = getBMI();
        map.put(HealthPackage.BMI, standardFloat.toMap());

        standardFloat = getBoneWeight(sex, weight);
        map.put(HealthPackage.BONE_WEIGHT, standardFloat.toMap());

        HealthStandardValueEx<String> standardExFloat = getBloodSugar();
        map.put(HealthPackage.BLOODSUGAR, standardExFloat.toMap());

        HealthStandardValueEx<Integer> standardEx = getVitalCapacity(sex, age);
        map.put(HealthPackage.VITALCAPACITY, standardEx.toMap());

        standardInt = getFatFreeWeight();
        map.put(HealthPackage.FATE_FREE_WEIGHT, standardInt.toMap());

        return map;
    }

    /**
     * 得到心率的健康范围值
     * 
     * @param sex
     *            性别 男：true
     * @param age
     *            年龄
     * @return
     */
    public static HealthStandardValue<Integer> getHeartRate(boolean sex, int age) {
        int min, max;
        if (sex) {
            if (age < 7) { // 0-6
                min = 73;
                max = 131;
            } else if (age < 18) { // 7-17
                min = 57;
                max = 119;
            } else if (age < 26) { // 18-25
                min = 55;
                max = 117;
            } else if (age < 36) { // 26-35
                min = 51;
                max = 115;
            } else if (age < 49) { // 36-48
                min = 49;
                max = 112;
            } else if (age < 60) { // 49-59
                min = 48;
                max = 107;
            } else if (age < 71) { // 60-70
                min = 47;
                max = 102;
            } else { // >70
                min = 45;
                max = 94;
            }
        } else {
            if (age < 7) { // 0-6
                min = 76;
                max = 130;
            } else if (age < 18) { // 7-17
                min = 59;
                max = 121;
            } else if (age < 26) { // 18-25
                min = 58;
                max = 119;
            } else if (age < 36) { // 26-35
                min = 54;
                max = 116;
            } else if (age < 49) { // 36-48
                min = 51;
                max = 113;
            } else if (age < 60) { // 49-59
                min = 50;
                max = 108;
            } else if (age < 71) { // 60-70
                min = 48;
                max = 102;
            } else { // >70
                min = 46;
                max = 96;
            }
        }
        HealthStandardValue<Integer> heartrate = new HealthStandardValue<Integer>();
        heartrate.setMin(min);
        heartrate.setMax(max);
        return heartrate;
    }

    /**
     *  获取心率运动模式
     *  @author yuhang.weng 
     *  @DateTime 2017年11月9日 下午3:59:51
     *
     *  @param age 年龄
     *  @return
     */
    public static HealthStandardValue<Integer> getHeartRateSportMode(int age) {
        BigDecimal maxB = new BigDecimal((220 - age) * 0.85);
        maxB.setScale(0, RoundingMode.HALF_UP);
        int min = 0;
        int max = maxB.intValue();
        HealthStandardValue<Integer> heartrateSportMode = new HealthStandardValue<Integer>();
        heartrateSportMode.setMin(min);
        heartrateSportMode.setMax(max);
        return heartrateSportMode;
    }
    
    /**
     * 得到体温的健康范围值
     * 
     * @return
     */
    public static HealthStandardValue<Integer> getTemperature() {
        // int min,max;
        HealthStandardValue<Integer> temperature = new HealthStandardValue<Integer>();
        temperature.setMin(36);
        temperature.setMax(37);
        return temperature;
    }

    /**
     * 得到血糖的健康范围值
     * 
     * @return
     */
    public static HealthStandardValueEx<String> getBloodSugar() {

        HealthStandardValueEx<String> bloodSugar = new HealthStandardValueEx<String>();
        bloodSugar.setMin("6.7");
        bloodSugar.setMax("9.4");
        bloodSugar.setLess("3.9");
        bloodSugar.setMore("6.1");
        return bloodSugar;
    }

    /**
     * 得到血氧饱和度的健康范围值
     * 
     * @return
     */
    public static HealthStandardValue<Integer> getSaturation() {
        // int min,max;
        HealthStandardValue<Integer> saturation = new HealthStandardValue<Integer>();
        saturation.setMin(95);
        saturation.setMax(100);
        return saturation;
    }

    /**
     * 得到肺活量的分数值
     * 
     * @param sex
     *            性别 男：true
     * @param age
     *            年龄
     * @return
     */
    public static HealthStandardValueEx<Integer> getVitalCapacity(boolean sex, int age) {
        int min, max, less, more;
        if (sex) {
            if (age < 10) { // 0-6
                less = 600;
                min = 900;
                max = 1500;
                more = 1800;
            } else if (age < 20) { // 10-14
                less = 1300;
                min = 1800;
                max = 2500;
                more = 3000;
            } else if (age < 20) { // 15-19
                less = 2400;
                min = 3400;
                max = 4000;
                more = 4600;
            } else if (age < 25) { // 20-24
                less = 2848;
                min = 3465;
                max = 3985;
                more = 4635;
            } else if (age < 30) { // 25-29
                less = 2850;
                min = 3460;
                max = 3970;
                more = 4625;
            } else if (age < 35) { // 30-34
                less = 2750;
                min = 3345;
                max = 3875;
                more = 4545;
            } else if (age < 40) { // 33-39
                less = 2620;
                min = 3210;
                max = 3740;
                more = 4350;
            } else if (age < 45) { // 40-44
                less = 2450;
                min = 3085;
                max = 3600;
                more = 4235;
            } else if (age < 50) { // 45-49
                less = 2308;
                min = 2965;
                max = 3465;
                more = 4100;
            } else if (age < 55) { // 50-54
                less = 2165;
                min = 2780;
                max = 3255;
                more = 3915;
            } else if (age < 60) { // 55-59
                less = 2060;
                min = 2645;
                max = 3125;
                more = 3770;
            } else if (age < 65) { // 60-64
                less = 1828;
                min = 2426;
                max = 2940;
                more = 3500;
            } else if (age < 70) { // 65-69
                less = 1661;
                min = 2230;
                max = 2750;
                more = 3335;
            } else { // >70
                less = 1661;
                min = 2230;
                max = 2750;
                more = 3335;
            }
        } else {
            if (age < 10) { // 0-6
                less = 500;
                min = 800;
                max = 1200;
                more = 1400;
            } else if (age < 20) { // 10-14
                less = 1000;
                min = 1400;
                max = 2000;
                more = 2400;
            } else if (age < 20) { // 15-19
                less = 1700;
                min = 2000;
                max = 2700;
                more = 3200;
            } else if (age < 25) { // 20-24
                less = 1874;
                min = 2355;
                max = 2780;
                more = 3260;
            } else if (age < 30) { // 25-29
                less = 1835;
                min = 2365;
                max = 2770;
                more = 3245;
            } else if (age < 35) { // 30-34
                less = 1782;
                min = 2340;
                max = 2760;
                more = 3242;
            } else if (age < 40) { // 35-39
                less = 1735;
                min = 2250;
                max = 2675;
                more = 3160;
            } else if (age < 45) { // 40-44
                less = 1630;
                min = 2150;
                max = 2574;
                more = 3075;
            } else if (age < 50) { // 45-49
                less = 1520;
                min = 2050;
                max = 2460;
                more = 2980;
            } else if (age < 55) { // 50-54
                less = 1470;
                min = 1978;
                max = 2375;
                more = 2900;
            } else if (age < 60) { // 55-59
                less = 1375;
                min = 1855;
                max = 2250;
                more = 2770;
            } else if (age < 65) { // 60-64
                less = 1220;
                min = 1685;
                max = 2070;
                more = 2550;
            } else if (age < 70) { // 65-69
                less = 1105;
                min = 1560;
                max = 1965;
                more = 2455;
            } else { // >70
                less = 1105;
                min = 1560;
                max = 1965;
                more = 2455;
            }
        }
        HealthStandardValueEx<Integer> vital = new HealthStandardValueEx<Integer>();
        vital.setMin(min);
        vital.setMax(max);
        vital.setLess(less);
        vital.setMore(more);
        return vital;
    }

    /**
     *  得到峰流速的健康标准
     *  @author yuhang.weng 
     *  @DateTime 2017年11月24日 上午9:36:41
     *
     *  @return
     */
    public static HealthStandardValue<String> getPef() {
        return null;
    }
    
    /**
     * 得到收缩压的健康标准
     * 
     * @param sex
     *            性别 男：true
     * @param age
     *            年龄
     * @return
     */
    public static HealthStandardValue<Integer> getSystolic(boolean sex, int age) {
        int min, max;
        if (sex) {
            if (age < 7) { // 0-6
                min = 70;
                max = 110;
            } else if (age < 18) { // 7-17
                min = 94;
                max = 123;
            } else if (age < 26) { // 18-25
                min = 92;
                max = 129;
            } else if (age < 36) { // 26-35
                min = 91;
                max = 131;
            } else if (age < 49) { // 36-48
                min = 92;
                max = 135;
            } else if (age < 60) { // 49-59
                min = 93;
                max = 138;
            } else if (age < 71) { // 60-70
                min = 93;
                max = 144;
            } else { // >70
                min = 91;
                max = 148;
            }
        } else {
            if (age < 7) { // 0-6
                min = 70;
                max = 110;
            } else if (age < 18) { // 7-17
                min = 94;
                max = 122;
            } else if (age < 26) { // 18-25
                min = 92;
                max = 128;
            } else if (age < 36) { // 26-35
                min = 91;
                max = 130;
            } else if (age < 49) { // 36-48
                min = 94;
                max = 133;
            } else if (age < 60) { // 49-59
                min = 95;
                max = 135;
            } else if (age < 71) { // 60-70
                min = 95;
                max = 141;
            } else { // >70
                min = 93;
                max = 145;
            }
        }
        HealthStandardValue<Integer> systolic = new HealthStandardValue<Integer>();
        systolic.setMin(min);
        systolic.setMax(max);
        return systolic;
    }

    /**
     * 得到舒张压的健康标准
     * 
     * @param sex
     *            性别 男：true
     * @param age
     *            年龄
     * @return
     */
    public static HealthStandardValue<Integer> getDiastolic(boolean sex, int age) {
        int min, max;
        if (sex) {
            if (age < 7) { // 0-6
                min = 30;
                max = 75;
            } else if (age < 18) { // 7-17
                min = 62;
                max = 85;
            } else if (age < 26) { // 18-25
                min = 64;
                max = 86;
            } else if (age < 36) { // 26-35
                min = 63;
                max = 87;
            } else if (age < 49) { // 36-48
                min = 62;
                max = 88;
            } else if (age < 60) { // 49-59
                min = 61;
                max = 91;
            } else if (age < 71) { // 60-70
                min = 61;
                max = 92;
            } else { // >70
                min = 62;
                max = 92;
            }
        } else {
            if (age < 7) { // 0-6
                min = 30;
                max = 75;
            } else if (age < 18) { // 7-17
                min = 59;
                max = 79;
            } else if (age < 26) { // 18-25
                min = 60;
                max = 81;
            } else if (age < 36) { // 26-35
                min = 59;
                max = 82;
            } else if (age < 49) { // 36-48
                min = 59;
                max = 83;
            } else if (age < 60) { // 49-59
                min = 58;
                max = 85;
            } else if (age < 71) { // 60-70
                min = 58;
                max = 87;
            } else { // >70
                min = 59;
                max = 85;
            }
        }
        HealthStandardValue<Integer> diastolic = new HealthStandardValue<Integer>();
        diastolic.setMin(min);
        diastolic.setMax(max);
        return diastolic;
    }

    /**
     * 得到体重的健康范围值
     * 
     * @param age
     *            年龄
     * @param height
     *            身高(cm)
     * @return
     */
    public static HealthStandardValue<Integer> getWeight(int age, float height) {
        Integer min = 0, max = 0, normal = 0;
        if (age < 17) {
            normal = age * 2 + 8;
        } else {
            if (height <= 120)
                height = 120;
            normal = (int) ((height - 100) * 0.9);
        }

        min = (int) (normal * 0.9);
        max = (int) (normal * 1.1);
        HealthStandardValue<Integer> weight = new HealthStandardValue<Integer>();
        weight.setMin(min);
        weight.setMax(max);
        return weight;
    }

    /**
     * 得到体脂率的健康标准
     * 
     * @param sex
     *            性别 男：true
     * @param age
     *            年龄
     * @return
     */
    public static HealthStandardValue<Integer> getAxungeRatio(boolean sex, int age) {
        int min, max;
        if (sex) {
            if (age < 30) { // 0-29
                min = 10;
                max = 20;
            } else { // >29
                min = 11;
                max = 21;
            }
        } else {
            if (age < 30) {
                min = 16;
                max = 23;
            } else {
                min = 19;
                max = 26;
            }
        }
        HealthStandardValue<Integer> axungeRatio = new HealthStandardValue<Integer>();
        axungeRatio.setMin(min);
        axungeRatio.setMax(max);
        return axungeRatio;
    }

    /**
     * 得到腰臀比的健康标准
     * 
     * @param sex
     *            性别 男：true
     * @return
     */
    public static HealthStandardValue<String> getWHR(boolean sex) {
        String min = "0", max;
        if (sex) {
            max = "0.9";
        } else {
            max = "0.85";
        }
        HealthStandardValue<String> whr = new HealthStandardValue<String>();
        whr.setMin(min);
        whr.setMax(max);
        return whr;
    }

    /**
     * 得到BMI的健康范围值
     * 
     * @return
     */
    public static HealthStandardValue<String> getBMI() {
        // int min,max;
        HealthStandardValue<String> bmi = new HealthStandardValue<String>();
        bmi.setMin("18.5");
        bmi.setMax("24");
        return bmi;
    }

    /**
     * 得到肌肉率的健康范围值
     * 
     * @param sex
     *            性别 男：true
     * @return
     */
    public static HealthStandardValue<Integer> getMuscle(boolean sex) {
        Integer min = 30, max = 50;
        if (sex) {
            min = 40;
            max = 60;
        }
        HealthStandardValue<Integer> muscle = new HealthStandardValue<Integer>();
        muscle.setMin(min);
        muscle.setMax(max);
        return muscle;
    }

    /**
     * 得到人体水份的健康范围值
     * 
     * @param sex
     *            性别 男：true
     * @return
     */
    public static HealthStandardValue<Integer> getMoisture(boolean sex) {
        Integer min = 45, max = 60;
        if (sex) {
            min = 55;
            max = 65;
        }
        HealthStandardValue<Integer> moisture = new HealthStandardValue<Integer>();
        moisture.setMin(min);
        moisture.setMax(max);
        return moisture;
    }

    /**
     * 得到骨骼重量的健康标准
     * 
     * @param sex
     *            性别 男：true
     * @param weight
     *            体重(kg)
     * @return
     */
    public static HealthStandardValue<String> getBoneWeight(boolean sex, float weight) {
        String min, max;
        if (sex) {
            if (weight < 60) { // 0-59
                min = "2.4";
                max = "2.6";
            } else if (weight < 75) { // 60-75
                min = "2.8";
                max = "3.0";
            } else { // >75
                min = "3.1";
                max = "3.3";
            }
        } else {
            if (weight < 45) {
                min = "1.7";
                max = "1.9";
            } else if (weight < 60) { // 0-59
                min = "2.1";
                max = "2.3";
            } else { // >75
                min = "2.4";
                max = "2.6";
            }
        }
        HealthStandardValue<String> boneWeight = new HealthStandardValue<String>();
        boneWeight.setMin(min);
        boneWeight.setMax(max);
        return boneWeight;
    }

    /**
     * 得到基础代谢的健康范围值
     * 
     * @param sex
     *            性别 男：true
     * @param age
     *            年龄
     * @param weight
     *            体重(kg)
     * @return
     */
    public static HealthStandardValue<String> getBaseMetabolism(boolean gender, int age, float weight, float height) {
        /*Integer min, max = 9999;
        if (sex) {
            if (age < 3) { // 0-2
                min = (int) (60.9 * weight - 54);
            } else if (age < 10) { // 3-9
                min = (int) (22.7 * weight + 495);
            } else if (age < 18) { // 10-17
                min = (int) (17.5 * weight + 651);
            } else if (age < 30) { // 18-29
                min = (int) (15.3 * weight + 679);
            } else { // >29
                min = (int) (11.6 * weight + 879);
            }
        } else {
            if (age < 3) { // 0-2
                min = (int) (61.0 * weight - 51);
            } else if (age < 10) { // 3-9
                min = (int) (22.5 * weight + 499);
            } else if (age < 18) { // 10-17
                min = (int) (12.2 * weight + 746);
            } else if (age < 30) { // 18-29
                min = (int) (14.7 * weight + 496);
            } else { // >29
                min = (int) (8.7 * weight + 820);
            }
        }
        HealthStandardValue<Integer> baseMetabolism = new HealthStandardValue<Integer>();
        baseMetabolism.setMin(min);
        baseMetabolism.setMax(max);
        return baseMetabolism;*/
        
        // 2017-03-28 修改基础代谢率标准值
        /*String min = "0", max = "0";
        float standardValue = 0;
        if (gender) {
            // 男性
            standardValue = (float) (67.0 + (13.73 * weight) + (5.0 * height) - (6.9 * age));
        } else {
            // 女性
            standardValue = (float) (661.0 + (9.6 * weight) + (1.72 * height) - (4.7 * age));
        }
        min = String.valueOf(NumberUtils.multiply(standardValue, 0.8f, 2));
        max = String.valueOf(NumberUtils.multiply(standardValue, 1.2f, 2));*/
        
        // 2017-04-19 修改基础代谢率标准值
        String min, max;
        Float standardValue;;
        if (gender) {
            if (age < 3) { // 0-2
                standardValue = NumberUtils.multiply(60.9f, weight, 2) - 54;
            } else if (age < 10) { // 3-9
                standardValue = NumberUtils.multiply(22.7f, weight, 2) + 495;
            } else if (age < 18) { // 10-17
                standardValue = NumberUtils.multiply(17.5f, weight, 2) + 651;
            } else if (age < 30) { // 18-29
                standardValue = NumberUtils.multiply(15.3f, weight, 2) + 679;
            } else { // >29
                standardValue = NumberUtils.multiply(11.6f, weight, 2) + 879;
            }
        } else {
            if (age < 3) { // 0-2
                standardValue = NumberUtils.multiply(61.0f, weight, 2) - 51;
            } else if (age < 10) { // 3-9
                standardValue = NumberUtils.multiply(22.5f, weight, 2) + 499;
            } else if (age < 18) { // 10-17
                standardValue = NumberUtils.multiply(12.2f, weight, 2) + 746;
            } else if (age < 30) { // 18-29
                standardValue = NumberUtils.multiply(14.7f, weight, 2) + 496;
            } else { // >29
                standardValue = NumberUtils.multiply(8.7f, weight, 2) + 820;
            }
        }
        
        min = String.valueOf(NumberUtils.multiply(standardValue, 0.8f, 2));
        max = String.valueOf(NumberUtils.multiply(standardValue, 1.2f, 2));
        
        HealthStandardValue<String> healthStandard = new HealthStandardValue<>();
        healthStandard.setMin(min);
        healthStandard.setMax(max);
        return healthStandard;
    }

    /**
     * 得到体年龄的分数值
     * 
     * @param age
     *            年龄
     * @return
     */
    public static HealthStandardValue<Integer> getBodyage(int age) {
        HealthStandardValue<Integer> bodyage = new HealthStandardValue<Integer>();
        bodyage.setMin(0);
        bodyage.setMax(age);
        return bodyage;
    }

    /**
     * 得到内脏脂肪的分数值
     * 
     * @param age
     *            年龄
     * @return
     */
    public static HealthStandardValue<Integer> getVisceralFat() {
        HealthStandardValue<Integer> visceralFat = new HealthStandardValue<Integer>();
        visceralFat.setMin(0);
        visceralFat.setMax(9);
        return visceralFat;
    }

    /**
     * 得到蛋白质的分数值
     *
     * @param sex
     *            性别 男：true
     * @return
     */
    public static HealthStandardValue<Integer> getProteideArea(boolean sex) {
        HealthStandardValue<Integer> proteideArea = new HealthStandardValue<>();
        if (sex) {
            proteideArea.setMin(16);
            proteideArea.setMax(18);
        } else {
            proteideArea.setMin(14);
            proteideArea.setMax(16);
        }
        return proteideArea;
    }

    public static HealthStandardValue<Integer> getFatFreeWeight() {
        HealthStandardValue<Integer> fatFreeWeight = new HealthStandardValue<>();
        fatFreeWeight.setMin(1);
        fatFreeWeight.setMax(9999);
        return fatFreeWeight;
    }
    
    /**
     *  获取ph的正常值范围
     *  @author yuhang.weng 
     *	@DateTime 2017年4月20日 下午3:12:23
     *
     *  @return
     */
    public static HealthStandardValue<String> getPh() {
        HealthStandardValue<String> ph = new HealthStandardValue<>();
        ph.setMin("4.5");
        ph.setMax("8");
        return ph;
    }
    
    /**
     *  获取尿比重正常范围值
     *  @author yuhang.weng 
     *	@DateTime 2017年4月20日 下午3:13:33
     *
     *  @return
     */
    public static HealthStandardValue<String> getSG() {
        HealthStandardValue<String> sg = new HealthStandardValue<>();
        sg.setMin("1.005");
        sg.setMax("1.025");
        return sg;
    }
    
    /**
     *  获取尿酸正常范围值
     *  @author yuhang.weng 
     *	@DateTime 2017年4月24日 上午11:03:04
     *
     *  @param age
     *  @param gender
     *  @return
     */
    public static HealthStandardValue<String> getUA(int age, boolean gender) {
        HealthStandardValue<String> ua = new HealthStandardValue<>();
        if (age <= 12) {
            ua.setMin("0.12");
            ua.setMax("0.33");
            return ua;
        }
        if (gender) {
            ua.setMin("0.2");
            ua.setMax("0.42");
            return ua;
        }
        ua.setMin("0.14");
        ua.setMax("0.36");
        return ua;
    }
    
    public static HealthStandardValue<String> getHDL(boolean gender) {
        HealthStandardValue<String> hdl = new HealthStandardValue<>();
        if (gender) {
            hdl.setMin("0.96");
            hdl.setMax("1.15");
            return hdl;
        }
        hdl.setMin("0.90");
        hdl.setMax("1.55");
        return hdl;
    }
    
    public static HealthStandardValue<String> getLDL() {
        HealthStandardValue<String> ldl = new HealthStandardValue<>();
        ldl.setMin("0");
        ldl.setMax("3.1");
        return ldl;
    }
    
    public static HealthStandardValue<String> getTG() {
        HealthStandardValue<String> tg = new HealthStandardValue<>();
        tg.setMin("0.56");
        tg.setMax("1.7");
        return tg;
    }
    
    public static HealthStandardValue<String> getTC() {
        HealthStandardValue<String> tc = new HealthStandardValue<>();
        tc.setMin("2.8");
        tc.setMax("5.17");
        return tc;
    }
    
    public static HealthStandardValue<String> getBloodLipidRatio() {
        HealthStandardValue<String> bloodLipidRatio = new HealthStandardValue<>();
        bloodLipidRatio.setMin("0");
        bloodLipidRatio.setMax("5");
        return bloodLipidRatio;
    }

    public static Map<String, Object> getAllByUserNullCheck(Boolean sex, Integer age, Integer height, Integer weight) {
        Map<String, Object> map = new HashMap<String, Object>();

        HealthStandardValue<Integer> standardInt = new HealthStandardValue<>();
        HealthStandardValue<String> standardFloat = new HealthStandardValue<>();
        HealthStandardValueEx<String> standardExFloat = new HealthStandardValueEx<>();
        HealthStandardValueEx<Integer> standardEx = new HealthStandardValueEx<>();

        if (sex != null) {
            standardInt = getMuscle(sex);
            map.put(HealthPackage.MUSCLE, standardInt.toMap());

            standardInt = getMoisture(sex);
            map.put(HealthPackage.MOISTURE, standardInt.toMap());

            standardFloat = getWHR(sex);
            map.put(HealthPackage.WHR, standardFloat.toMap());

            standardInt = getProteideArea(sex);
            map.put(HealthPackage.PROTEIDE, standardInt.toMap());
            
            standardFloat = getHDL(sex);
            map.put(BloodLipid.HDL, standardFloat.toMap());
        }

        if (age != null) {
            standardInt = getBodyage(age);
            map.put(HealthPackage.BODY_AGE, standardInt.toMap());
            
            standardInt = getHeartRateSportMode(age);
            map.put(HealthPackage.HEARTRATE_SPORT_MODE, standardInt.toMap());
        }

        if (sex != null && age != null) {
            standardInt = getHeartRate(sex, age);
            map.put(HealthPackage.HEARTRATE, standardInt.toMap());

            standardInt = getSystolic(sex, age);
            map.put(HealthPackage.SYSTOLIC, standardInt.toMap());

            standardInt = getDiastolic(sex, age);
            map.put(HealthPackage.DIASTOLIC, standardInt.toMap());

            standardInt = getAxungeRatio(sex, age);
            map.put(HealthPackage.AXUNGERATIO, standardInt.toMap());

            standardEx = getVitalCapacity(sex, age);
            map.put(HealthPackage.VITALCAPACITY, standardEx.toMap());
            
            standardFloat = getUA(age, sex);
            map.put(Ua.UA, standardFloat.toMap());
        }

        if (sex != null && weight != null) {
            standardFloat = getBoneWeight(sex, weight);
            map.put(HealthPackage.BONE_WEIGHT, standardFloat.toMap());
        }

        if (age != null && height != null) {
            standardInt = getWeight(age, height);
            map.put(HealthPackage.WEIGHT, standardInt.toMap());
        }

        if (sex != null && age != null && weight != null && height != null) {
            standardFloat = getBaseMetabolism(sex, age, weight, height);
            map.put(HealthPackage.BASE_METABOLISM, standardFloat.toMap());
        }

        standardInt = getTemperature();
        map.put(HealthPackage.TEMPERATURE, standardInt.toMap());

        standardInt = getSaturation();
        map.put(HealthPackage.SATURATION, standardInt.toMap());

        standardInt = getVisceralFat();
        map.put(HealthPackage.VISCERALFAT, standardInt.toMap());

        standardFloat = getBMI();
        map.put(HealthPackage.BMI, standardFloat.toMap());

        standardExFloat = getBloodSugar();
        map.put(HealthPackage.BLOODSUGAR, standardExFloat.toMap());

        standardInt = getFatFreeWeight();
        map.put(HealthPackage.FATE_FREE_WEIGHT, standardInt.toMap());

        standardFloat = getPh();
        map.put(Uran.PH, standardFloat.toMap());
        
        standardFloat = getSG();
        map.put(Uran.SG, standardFloat.toMap());

        standardFloat = getLDL();
        map.put(BloodLipid.LDL, standardFloat.toMap());
        
        standardFloat = getTG();
        map.put(BloodLipid.TG, standardFloat.toMap());
        
        standardFloat = getTC();
        map.put(BloodLipid.TC, standardFloat.toMap());
        
        standardFloat = getBloodLipidRatio();
        map.put(HealthPackage.BLOOD_LIPID_RATION, standardFloat.toMap());
        
        return map;
    }
}
