package com.lifeshs.po.org;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @create 2018-02-03
 * 17:09
 * @desc
 */
public @Data class TServerPO {

    private Integer id;//主键id
    private int serveType;//服务分类：1_健康问诊，2_慢病养护
    private String classify;//服务分类，以,分隔
    private String code;//服务代码
    private String name;//服务名称
    private String about;//服务摘要
    private int profitShare;//服务分成
    private String image;//服务图片
    private Date createDate;//服务的创建时间
    private String chargeMode;//服务的收费方式，以逗号分隔开  0_免费,1_按次收费,_2_按月收费,3_按年收费
    private int projectType;//项目类型(1：线上；2：线下；3：上门)
}
