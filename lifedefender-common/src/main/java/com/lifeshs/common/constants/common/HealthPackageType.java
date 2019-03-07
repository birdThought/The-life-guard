package com.lifeshs.common.constants.common;

/**
 * 健康包设备类型
 * 
 * @author dengfeng
 *
 */
public enum HealthPackageType {
    /**
     * 心率(手环、HL）
     */
    HeartRate(1, "心率手环", "-", "#"),
    /**
     * 定位(c3、hl)
     */
    Location(2, "定位", "-", "#"),
    /**
     * 血压(APP、HL）
     */
    BloodPressure(4, "血压计", "高血压监测与预防，血压数据异常预警", "https://item.taobao.com/item.htm?id=43640689944"),
    /**
     * 血氧仪
     */
    Oxygen(8, "血氧仪", "测量人体血氧饱和度，血氧数据异常预警", "https://item.taobao.com/item.htm?id=42942388246"),
    /**
     * 体脂秤
     */
    BodyFatScale(16, "体脂秤", "测量内脏脂肪、体脂率、基础代谢等10项指标", "https://item.taobao.com/item.htm?id=42876003211"),
    /**
     * 手环(计步、睡眠)
     */
    Band(32, "手环", "实时心率、计步、卡路里等数据监测，预警功能", "https://item.taobao.com/item.htm?id=42924765296"),
    /**
     * 血糖仪
     */
    Glucometer(64, "血糖仪", "糖尿病监测及预防，血糖数据异常预警", "https://item.taobao.com/item.htm?id=529682087329"),
    /**
     * 肺活仪
     */
    Lunginstrument(128, "肺活仪", "肺活量测量与锻炼，可评测及提高肺部机能", "https://item.taobao.com/item.htm?id=529735262435"),
    /**
     * 心电
     */
    ECG(256, "心电", "-", "#"),
    /**
     * 体温
     */
    Temperature(512, "体温计", "24小时监测实时体温，体温异常预警", "#"),
    /**
     * 手环-睡眠
     */
    BandSleep(1024, "手环-睡眠", "-", "#"),
    /**
     * 手环-计步
     */
    BandStep(2048, "手环-计步", "-", "#"),
    /**
     * 尿检分析仪
     */
    URAN(4096, "尿检分析仪", "测量PH比值、蛋白质、白细胞等11项指标", "#"),
    /**
     * 尿酸分析仪
     */
    UA(8192, "尿酸分析仪", "集测尿酸、血糖、总胆固醇三种功能为一体", "#"),
    /**
     * 血脂仪
     */
    BloodLipid(16384, "血脂仪", "测量总胆固醇、甘油三酯等5项指标", "#");

    /** 设备值 */
    private int _value;
    /** 设备名（中文） */
    private String _name;
    /** 描述 */
    private String about;
    /** 链接地址 */
    private String shopUrl;

    private HealthPackageType(int value, String name) {
        _value = value;
        _name = name;
    }
    
    private HealthPackageType(int value, String name, String about, String shopUrl) {
        _value = value;
        _name = name;
        this.about = about;
        this.shopUrl = shopUrl;
    }

    public int value() {
        return _value;
    }

    public String getName() {
        return _name;
    }
    
    public String getAbout() {
        return about;
    }
    
    public String getShopUrl() {
        return shopUrl;
    }
    
    public static HealthPackageType getHealthPackageType(int value) {
        for (HealthPackageType h : HealthPackageType.values()) {
            if (h.value() == value) {
                return h;
            }
        }
        return null;
    }
}