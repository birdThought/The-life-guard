package com.lifeshs.pojo.org.group;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lifeshs.pojo.order.UserOrderDTO;

import lombok.Data;

/**
 * 健康课堂
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年2月28日 上午9:56:22
 */
public @Data class LessonDTO {

    /** 群组ID */
    private Integer id;

    /** 群组环信id */
    private String huanxinId;

    /** 群组头像路径 */
    private String photo;

    /** 群组名称 */
    private String name;

    /** 课程时间 */
    private List<CourseTimeDTO> courseTime;

    /** 禁言 */
    private Boolean silence;

    /** 是否为默认群组 */
    private Boolean defaultGroup;

    /** 机构服务ID */
    private Integer orgServeId;

    /** 订单 */
    private List<UserOrderDTO> userOrders;

    /** 服务师 */
    private List<LessonGroupOrgUserDTO> orgUsers;

    /** 课堂描述 (从环信获取) */
    private String description;

    /** 群主ID */
    private Integer creatorId;

    /** 开始时间 */
    private Date startDate;

    /** 结束时间 */
    private Date endDate;

    /**
     *  获取群组用户数量
     *  @author yuhang.weng 
     *	@DateTime 2017年3月7日 下午3:13:53
     *
     *  @return
     */
    public Integer getLessonUserCount() {
        List<Integer> userIds = new ArrayList<>();
        for (UserOrderDTO userOrder : getUserOrders()) {
            Integer userId = userOrder.getUserId();
            if (!userIds.contains(userId)) {
                userIds.add(userId);
            }
        }
        /** 机构用户 */
        int orgUserSize = getOrgUsers().size();
        return userIds.size() + orgUserSize;
    }
}
