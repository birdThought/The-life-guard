package com.lifeshs.pojo.data;

import java.util.List;

import lombok.Data;

/**
 *  心电心律
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年3月20日 下午2:31:20
 */
@Data
public class EcgRhythmDTO {

    private Integer id;

    /** 心律名称 */
    private String name;

    /** 释义 */
    private String meaning;

    /** 病因 */
    private List<String> reasonList;

    /** 治疗 */
    private List<String> remedyList;

}
