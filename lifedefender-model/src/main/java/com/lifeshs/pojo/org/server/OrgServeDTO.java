package com.lifeshs.pojo.org.server;

import java.util.ArrayList;
import java.util.List;

import com.lifeshs.pojo.order.OrderDTO;
import com.lifeshs.pojo.org.group.GroupDTO;
import com.lifeshs.pojo.serve.ServeTypeSecondDTO;

/**
 * 机构服务
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月8日 上午10:59:10
 */
public class OrgServeDTO {

    private Integer id;

    /** 服务 */
    private ServeTypeSecondDTO serveType;
    /** 服务订单 */
    private List<OrderDTO> validOrders;

    /** 服务分类 */
    private String classify;

    /** 0_免费，1_收费 */
    private Boolean hasFree;

    /** 免费时长（月），0无限制 */
    private Integer freeDate;

    /** 有按次收费：1_是，0_否 */
    private Boolean hasTime;

    /** 按次的价格 */
    private Integer timePrice;

    /** 有按月收费：1_是，0_否 */
    private Boolean hasMonth;

    /** 按月的价格 */
    private Integer monthPrice;

    /** 有按年收费：1_是，0_否 */
    private Boolean hasYear;

    /** 按年的价格 */
    private Integer yearPrice;

    /** 机构下的所有群组 */
    private List<GroupDTO> groups;

    /** 服务介绍 */
    private String about;

    /**
     * 获取消费人次
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月8日 下午1:41:46
     *
     * @return
     */
    public Integer getComsumePersonTime() {
        List<OrderDTO> orderDTOs = getValidOrders();
        List<Integer> userIds = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOs) {
            Integer userId = orderDTO.getUserId();
            if (!userIds.contains(userId)) {
                userIds.add(userId);
            }
        }
        return userIds.size();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServeTypeSecondDTO getServeType() {
        return serveType;
    }

    public void setServeType(ServeTypeSecondDTO serveType) {
        this.serveType = serveType;
    }

    public List<OrderDTO> getValidOrders() {
        return validOrders;
    }

    public void setValidOrders(List<OrderDTO> validOrders) {
        this.validOrders = validOrders;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Boolean getHasFree() {
        return hasFree;
    }

    public void setHasFree(Boolean hasFree) {
        this.hasFree = hasFree;
    }

    public Integer getFreeDate() {
        return freeDate;
    }

    public void setFreeDate(Integer freeDate) {
        this.freeDate = freeDate;
    }

    public Boolean getHasTime() {
        return hasTime;
    }

    public void setHasTime(Boolean hasTime) {
        this.hasTime = hasTime;
    }

    public Integer getTimePrice() {
        return timePrice;
    }

    public void setTimePrice(Integer timePrice) {
        this.timePrice = timePrice;
    }

    public Boolean getHasMonth() {
        return hasMonth;
    }

    public void setHasMonth(Boolean hasMonth) {
        this.hasMonth = hasMonth;
    }

    public Integer getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(Integer monthPrice) {
        this.monthPrice = monthPrice;
    }

    public Boolean getHasYear() {
        return hasYear;
    }

    public void setHasYear(Boolean hasYear) {
        this.hasYear = hasYear;
    }

    public Integer getYearPrice() {
        return yearPrice;
    }

    public void setYearPrice(Integer yearPrice) {
        this.yearPrice = yearPrice;
    }

    public List<GroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDTO> groups) {
        this.groups = groups;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "OrgServeDTO [id=" + id + ", project=" + serveType + ", validOrders=" + validOrders + ", classify=" + classify
                + ", hasFree=" + hasFree + ", freeDate=" + freeDate + ", hasTime=" + hasTime + ", timePrice="
                + timePrice + ", hasMonth=" + hasMonth + ", monthPrice=" + monthPrice + ", hasYear=" + hasYear
                + ", yearPrice=" + yearPrice + ", groups=" + groups + ", about=" + about + "]";
    }

}
