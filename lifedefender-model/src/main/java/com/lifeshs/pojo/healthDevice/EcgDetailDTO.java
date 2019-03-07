package com.lifeshs.pojo.healthDevice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.lifeshs.pojo.data.EcgRhythmDTO;

import lombok.Data;

/**
 *  心电详情
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年11月28日 上午10:04:18
 */
@Data
public class EcgDetailDTO {

    private Integer id;

    /** 心电测量id */
    private Integer ecgMeasureId;

    /** 异常和 */
    private Integer status;

    /** 心率 */
    private Integer heartRate;

    /** 测量时间 */
    private Date measureDate;

    /** 心电图片路径 */
    private String image;

    /** 标记类型,0_自动,1_手动 */
    private Integer signType;

    /** 标记内容 */
    private String tags;

    /** 心律ID */
    private Integer rhythmId;

    /** 心律 */
    private EcgRhythmDTO ecgRhythm;

    /** 创建日期 */
    private Date createDate;

    /**
     * 获取标记内容集合
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月20日 下午2:18:48
     *
     * @return
     */
    public List<String> getTagList() {
        List<String> tagList = new ArrayList<>();
        if (tags != null && tags.length() > 0) {
            tagList = JSONArray.parseArray(tags, String.class);
        }
        return tagList;
    }

}
