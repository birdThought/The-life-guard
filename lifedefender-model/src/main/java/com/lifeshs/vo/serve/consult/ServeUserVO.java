package com.lifeshs.vo.serve.consult;

import java.util.List;

import com.lifeshs.vo.order.comment.CommentVO;

import lombok.Data;

/**
 *  咨询服务师
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年7月4日 上午11:53:25
 */
public @Data class ServeUserVO {

    /** 服务id t_project_orguser_relation 的id */
    private Integer sid;
    /** 服务师id */
    private Integer userId;
    /** 名字 */
    private String realName;
    /** 职称 */
    private String professionalName;
    /** 专业特长 */
    private String expertise;
    /** 照片 */
    private String photo;
    /** 项目名称 */
    private String projectName;
    /** 机构名称 */
    private String orgName;
    /** 收费价格 按次 */
    private double price;
    /** 收费价格  按月*/
    private double monthPrice;
    /** 收费价格  按年*/
    private double yearPrice;
    /** 购买人数 */
    private int buyerCount;
    /** 评分总分 */
    private long scoreTotal;
    /** 评价次数 */
    private long commentCount;
    /** 所属项目code */
    private String projectCode;
    /** 服务类别 */
    private int serveId;
    /** 付费类型 1_按次，2_按月，3_按年  */
    private Integer chargeMode;
    /** 服务师的UserCode */
    private String userCode;
    /** 评论 */
    List<CommentVO> comment;
}
