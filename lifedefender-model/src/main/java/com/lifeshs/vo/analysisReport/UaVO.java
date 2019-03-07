package com.lifeshs.vo.analysisReport;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  检测报告 - 尿酸
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月12日 下午4:34:15
 */
@Data
public class UaVO {

    @JSONField(name = "UA")
    /** 尿酸 */
    private Float ua;
}
