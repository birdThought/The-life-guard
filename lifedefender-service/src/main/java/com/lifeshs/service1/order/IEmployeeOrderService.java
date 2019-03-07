package com.lifeshs.service1.order;

import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.OrderCountAndChargeVO;
import com.lifeshs.dto.manager.order.GetOrderListData;
import com.lifeshs.po.OrderPO;

import java.util.List;

/**
 * 服务师的订单业务接口
 * Created by dengfeng on 2017/6/19 0019.
 */
public interface IEmployeeOrderService {

    /**
     * 服务师的待办任务（未完成订单）数量（总）
     * @param employeeId
     * @return
     */
    int getOrderCount(Integer employeeId) ;

    /**
     * 服务师的今日有效订单（未完成订单）数量
     * @param employeeId
     * @return
     */
    int getTodayOrderCount(Integer employeeId) ;

    /**
     * 分页查询服务师所属的订单
     * @param serveType 1线上，2线下，3上门，4全部
     * @param status 1未完成，2待回复，3已完成，4全部
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<OrderPO> findOrderList(int employeeId, int serveType, int status, String search, int pageIndex, int pageSize,Integer memberId);

    /**
     * 分页查询门店/服务师所属的订单(只查已有评论的订单)
     * @param employeeId 服务师ID，与orgId二选一
     * @param commentStatus 评论状态（0：未回复；1：已回复）
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<OrderPO> findOrderListWithComments(int employeeId, int commentStatus, int pageIndex, int pageSize);

    /**
     * 分页查询门店/服务师所属的订单(只关联用户)
     * @param employeeId 服务师ID
     * @param startDate 开始日期
     * @return
     */
    List<GetOrderListData> findOrderListWithUser(int employeeId, String startDate, String endDate, int pageIndex, int pageSize);

    /**
     * 门店按日期段订单数量及金额合计
     * @param employeeId
     * @return
     */
    OrderCountAndChargeVO getDateDiffOrderCountAndCharge(int employeeId, String startDate, String endDate);

    /**
     * 得到服务师的本月订单数和订单合计金额
     * @param employeeId
     * @return
     */
    OrderCountAndChargeVO getCountAndMoneyByMonth(int employeeId);

}
