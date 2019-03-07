package com.lifeshs.vo.record;

import java.util.Date;

import lombok.Data;

/**
 *  用户病历基本信息</p>
 *  包含信息</p>
 *  科室名称：√</p>
 *  病程：✗</p>
 *  病程图片：✗</p>
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月14日 上午10:39:32
 */
public @Data class MedicalBasicVO {

    private Integer id;
    /** 用户id */
    private Integer userId;
    /** 标题 */
    private String title;
    /** 就诊日期 */
    private Date visitingDate;
    /** 就诊医院 */
    private String hospital;
    /** 科室名称 */
    private String departments;
    /** 医生诊断 */
    private String doctorDiagnosis;
    /** 基本病情 */
    private String basicCondition;
    /** 创建日期 */
    private Date createDate;
    /** 修改日期 */
    private Date modifyDate;
}
