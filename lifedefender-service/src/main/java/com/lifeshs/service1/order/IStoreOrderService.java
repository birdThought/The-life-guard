package com.lifeshs.service1.order;

import com.lifeshs.dto.manager.order.GetPaymentListData;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.OrderCountAndChargeVO;
import com.lifeshs.vo.OrderStatisticsByDayVO;
import com.lifeshs.dto.manager.order.GetOrderListData;
import com.lifeshs.po.OrderPO;

import java.util.List;

/**
 * 门店的订单业务接口
 * Created by dengfeng on 2017/6/21 0021.
 */
public interface IStoreOrderService {

    /**
     * 分页查询门店所属的订单
     *
     * @param serveType 1线上，2线下，3上门，4全部
     * @param status    订单状态，状态：待付款_1, 付款失败_2，有效_3，已完成_4，已退款_5，已取消_6
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<OrderPO> findOrderList(int orgId, int serveType, int status, String search, int pageIndex, int pageSize,Integer memberId);

    /**
     * 分页查询门店/服务师所属的订单(只查已有评论的订单)
     *
     * @param orgId         门店ID
     * @param commentStatus 评论状态（0：未回复；1：已回复）
     * @return
     */
    List<OrderPO> findOrderListWithComments(int orgId, int commentStatus, int pageIndex, int pageSize);

    /**
     * 分页查询门店/服务师所属的订单(只关联用户)
     * @param orgId 门店ID
     * @param startDate 开始日期
     * @return
     */
    List<GetOrderListData> findOrderListWithUser(int orgId, String startDate, String endDate, int pageIndex, int pageSize);

    /**
     * 门店按日期段订单数量及金额合计
     * @param orgId
     * @return
     */
    OrderCountAndChargeVO getDateDiffOrderCountAndCharge(int orgId, String startDate, String endDate);

    /**
     * 门店的今日订单数量及金额合计
     * @param orgId
     * @return
     */
    OrderCountAndChargeVO getTodayOrderCountAndCharge(int orgId);

    /**
     * 查询门店的指定月份的订单数量及金额合计
     * @param orgId
     * @param month 指定月份:"yyyy-MM"
     * @return
     */
    OrderCountAndChargeVO getMonthOrderCountAndCharge(int orgId, String month);

    /**
     * 按日统计门店的指定月份的订单数量及金额合计
     * @param statementId 结算记录ID
     * @return
     */
    List<OrderStatisticsByDayVO> getMonthOrderStatistics(int statementId);

}