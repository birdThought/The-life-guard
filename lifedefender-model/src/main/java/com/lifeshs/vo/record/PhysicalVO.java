package com.lifeshs.vo.record;

import java.util.Date;
import java.util.List;

import com.lifeshs.po.record.PhysicalAnalysisPO;
import com.lifeshs.po.record.PhysicalImgPO;

import lombok.Data;

/**
 *  体检报告
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月11日 上午10:59:47
 */
public @Data class PhysicalVO {

    private Integer id;
    /** 用户ID */
    private Integer userId;
    /** 标题 */
    private String title;
    /** 体检机构 */
    private String physicalsOrg;
    /** 体检日期 */
    private Date physicalsDate;
    /** 创建时间 */
    private Date createDate;
    /** 描述 */
    private String description;
    /** 图片列表 */
    private List<PhysicalImgPO> imgList;
    /** 体检报告分析列表 */
    private List<PhysicalAnalysisPO> analysisList;
}
