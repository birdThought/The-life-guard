package com.lifeshs.common.business;

/**
 * @author Yue.Li
 * @date 3/10/17.
 */
public class Conversion {

    public static Integer courseDescToTypeNumber(String courseDesc) {
        /** 类型号码 */
        Integer typeNumber = 0;

        if (courseDesc.equals("首诊")) {
            typeNumber = 1;
        }
        if (courseDesc.equals("复诊")) {
            typeNumber = 2;
        }
        if (courseDesc.equals("出院")) {
            typeNumber = 3;
        }
        if (courseDesc.equals("晚期")) {
            typeNumber = 4;
        }
        return typeNumber;
    }

    /**
     * 获取终端类型
     *
     * @author wenxian.cai
     * @DateTime 2016年8月26日上午9:51:44
     * @serverComment
     * @param
     */
    public static String getDeviceType(String terminalType) {
        String deviceType = null;
        switch (terminalType) {
            case "0":
                break;
            case "1":
                deviceType = "APP";
                break;
            case "2":
                deviceType = "HL-031";
                break;
            case "3":
                deviceType = "C3";
                break;
            default:
                deviceType = "-1";
                break;
        }
        return deviceType;
    }
}
