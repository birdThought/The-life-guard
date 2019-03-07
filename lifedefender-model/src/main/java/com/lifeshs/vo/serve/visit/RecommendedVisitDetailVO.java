package com.lifeshs.vo.serve.visit;

import java.util.List;

import com.lifeshs.pojo.org.service.ServiceMediaDTO;
import com.lifeshs.vo.order.comment.CommentVO;
import com.lifeshs.vo.serve.ServeUserVO;

import lombok.Data;

public @Data class RecommendedVisitDetailVO {

    /** 套餐id */
    private int comboId;
    /** 项目图片 */
    private String image;
    /** 评价总分 */
    private long scoreTotal;
    /** 项目名 */
    private String visitServeName;
    /** 套餐价格 */
    private double price;
    /** 套餐门市价 */
    private double marketPrice;
    /** 机构名称 */
    private String orgName;
    /** 机构地址 */
    private String orgAddress;
    /** 机构经度 */
    private String longitude;
    /** 机构纬度 */
    private String latitude;
    /** 机构联系电话 */
    private String orgTel;
    /** 适用人群 */
    private String userType;
    /** 预约信息 */
    private String appointment;
    /** 富文本 */
    private String detail;
    /** 总评价人数 */
    private long commentCount;
    /** 项目code */
    private String projectCode;
    /** 套餐列表 */
    private List<ComboVO> combo;
    /** 评论列表 */
    private List<CommentVO> comment;
    /** 服务师 */
    private List<ServeUserVO> serveUser;
    
    /** 服务code */
    private Integer serveCode;
    /**相关媒体资料*/
    private ServiceMediaDTO media;
}
