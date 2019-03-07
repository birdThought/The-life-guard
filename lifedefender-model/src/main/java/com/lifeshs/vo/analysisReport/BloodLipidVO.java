package com.lifeshs.vo.analysisReport;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  分析报告 - 血脂仪
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月10日 下午5:02:56
 */
@Data
public class BloodLipidVO {

    /** 总胆固醇 */
    @JSONField(name = "TC")
    private Float tc;
    /** 甘油三酯 */
    @JSONField(name = "TG")
    private Float tg;
    /** 高密度脂蛋白 */
    @JSONField(name = "HDL")
    private Float hdl;
    /** 低密度脂蛋白 */
    @JSONField(name = "LDL")
    private Float ldl;
}
