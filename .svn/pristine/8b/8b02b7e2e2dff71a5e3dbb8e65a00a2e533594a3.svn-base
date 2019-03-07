package com.lifeshs.service1.util;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthType;

/**
 * 对健康参数进行相关的分析处理
 * Created by dengfeng on 2017/6/27 0027.
 */
public class HealthTypeAnalysis {



    /**
     * 根据健康参数值（或多个参数的和值）得到相关的参数名称
     *
     * @updateBy wuj
     *
     * @param healthTypeValue
     * @return
     */
    public static String parseString(Long healthTypeValue) {
        /*if(healthTypeValue == null)
            return "";
        String result = "";
        for (HealthType ht : HealthType.values()) {
            if ((ht.value() & healthTypeValue) == ht.value()) {
                result += "," + ht.getName();
            }
        }
        if(!"".equals(result))
            result = result.substring(1);
        return result;*/

        if(healthTypeValue == null)
            return "";
        String result = "";
        for (HealthPackageType ht : HealthPackageType.values()) {
            if ((ht.value() & healthTypeValue) == ht.value()) {
                result += "," + ht.getName();
            }
        }
        if(!"".equals(result))
            result = result.substring(1);

        return result;
    }


}
