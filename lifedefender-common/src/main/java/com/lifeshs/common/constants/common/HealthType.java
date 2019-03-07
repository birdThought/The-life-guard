package com.lifeshs.common.constants.common;

/**
 * 健康参数类型
 * 
 * @author dengfeng
 *
 */
public enum HealthType {
    /**
     * 心率
     */
    heartRate(1, "心率"),
    /**
     * 收缩压
     */
    systolic(2, "收缩压"),
    /**
     * 舒张压
     */
    diastolic(4, "舒张压"),
    /**
     * 血氧饱和度
     */
    saturation(8, "血氧饱和度"),
    /**
     * 血糖
     */
    bloodSugar(16, "血糖"),
    /**
     * 肺活量
     */
    vitalCapacity(32, "肺活量"),
    /**
     * 体温
     */
    temperature(64, "体温"),
    /**
     * 体重
     */
    weight(128, "体重"),
    /**
     * 心电
     */
    ECG(256, "心电"),
    /**
     * 体脂率
     */
    axungeRatio(512, "体脂率"),
    /**
     * 腰臀比
     */
    WHR(1024, "腰臀比"),
    /**
     * BMI
     */
    BMI(2048, "BMI"),
    /**
     * 肌肉
     */
    muscle(4096, "肌肉"),
    /**
     * 去脂体重
     */
    fatFreeWeight(8192, "去脂体重"),
    /**
     * 人体水份
     */
    moisture(16384, "人体水份"),
    /**
     * 骨骼重量
     */
    boneWeight(32768, "骨量"),
    /**
     * 基础代谢
     */
    baseMetabolism(65536, "基础代谢"),
    /**
     * 体年龄
     */
    bodyage(131072, "体年龄"),
    /**
     * 蛋白质
     */
    proteide(262144, "蛋白质"),
    /**
     * 内脏脂肪
     */
    visceralFat(524288, "内脏脂肪"),
    /** 白细胞 */
    LEU(1048576, "白细胞"),
    /** 亚硝酸盐 */
    NIT(2097152, "亚硝酸盐"),
    /** 尿胆原 */
    UBG(4194304, "尿胆原"),
    /** pH值 */
    pH(8388608, "pH值"),
    /** 潜血 */
    BLD(16777216, "潜血"),
    /** 比重 */
    SG(33554432, "比重"),
    /** 酮体 */
    KET(67108864, "酮体"),
    /** 胆红素 */
    BIL(134217728, "胆红素"),
    /** 葡萄糖红素 */
    GLU(268435456, "葡萄糖红素"),
    /** 维生素C */
    VC(536870912, "维生素C"),
    /** 尿液-蛋白质 */
    PRO(1073741824, "尿液-蛋白质"),
    /** 尿酸 */
    UA(2147483648L, "尿酸"),
    /** 高密度脂蛋白胆固醇 */
    HDL(4294967296L, "高密度脂蛋白胆固醇"),
    /** 低密度脂蛋白胆固醇 */
    LDL(8589934592L, "低密度脂蛋白胆固醇"),
    /** 甘油三酯 */
    TG(17179869184L, "甘油三酯"),
    /** 胆固醇 */
    TC(34359738368L, "总胆固醇"),
    /** 血脂比值 */
    BloodLipidRation(68719476736L, "血脂比值"),
    /** 峰流速 */
    pef(137438953472L, "峰流速"),
    /** 平均流速 */
    af(274877906944L, "平均流速");

    private long _value;
    private String _name;

    private HealthType(long value, String name) {
        _value = value;
        _name = name;
    }

    public long value() {
        return _value;
    }

    public String getName() {
        return _name;
    }
}