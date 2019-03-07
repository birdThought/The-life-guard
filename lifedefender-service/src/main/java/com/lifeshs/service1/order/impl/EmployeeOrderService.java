package com.lifeshs.service1.order.impl;

import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.OrderCountAndChargeVO;
import com.lifeshs.dto.manager.order.GetOrderListData;
import com.lifeshs.po.OrderPO;
import com.lifeshs.service1.order.IEmployeeOrderService;
import com.lifeshs.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 服务师的订单业务类
 * Created by dengfeng on 2017/6/19 0019.
 */
@Service
public class EmployeeOrderService implements IEmployeeOrderService {
    @Autowired
    private com.lifeshs.dao.order.IOrderDao orderDao;

    @Autowired
    private com.lifeshs.dao1.order.IOrderDao orderDao1;

    /**
     * 服务师的待办任务（未完成订单）数量
     * @param employeeId
     * @return
     */
    public int getOrderCount(Integer employeeId) {
        return orderDao.getCountOfServiceOrderByOrgUser(employeeId, 3, null, "all"); //status=3是未完成订单
    }

    /**
     * 服务师的今日有效订单（未完成订单）数量
     * @param employeeId
     * @return
     */
    public int getTodayOrderCount(Integer employeeId) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return orderDao.getTodayCountOfServiceOrderByOrgUser(employeeId, 3, dateString); //status=3是未完成订单
    }

    /**
     * 得到用户的订单
     * @param employeeId 服务师ID
     * @param serveType 1线上，2线下，3上门，4全部
     * @param serveStatus 1未完成，2待回复，3已完成，4全部
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<OrderPO> findOrderList(int employeeId, int serveType, int serveStatus, String search, int pageIndex, int pageSize,Integer memberId){
        if (serveStatus == 2) {
            return orderDao1.findOrderListWithComments(null, employeeId, 0, (pageIndex - 1) * pageSize, pageSize,memberId);
        }
        Integer status = null;  //订单状态，状态：待付款_1, 付款失败_2，有效_3，已完成_4，已退款_5，已取消_6
        if (serveStatus == 1)
            status = 3;
        else if (serveStatus == 3)
            status = 4;
        return orderDao1.findOrderList(null, employeeId, serveType == 0 ? null : serveType, status, search,
                (pageIndex - 1) * pageSize, pageSize,memberId);
    }

    /**
     * 分页查询门店/服务师所属的订单(只查已有评论的订单)
     * @param employeeId 服务师ID
     * @param commentStatus 评论状态（0：未回复；1：已回复）
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<OrderPO> findOrderListWithComments(int employeeId, int commentStatus, int pageIndex, int pageSize){
        return orderDao1.findOrderListWithComments(null, employeeId, commentStatus, (pageIndex-1)*pageSize, pageSize,null);
    }

    /**
     * 分页查询门店/服务师所属的订单(只关联用户)
     * @param employeeId 服务师ID
     * @param startDate 开始日期
     * @return
     */
    public List<GetOrderListData> findOrderListWithUser(int employeeId, String startDate, String endDate, int pageIndex, int pageSize){
//        Paging<GetOrderListData> paging = new Paging<>(pageIndex, pageSize);
//        paging.setQueryProc(new IPagingQueryProc() {
//            @Override
//            public int queryTotal() {
//                return orderDao1.findOrderCountWithUser(null, employeeId, startDate, endDate);
//            }
//
//            @Override
//            public List queryData(int startRow, int pageSize) {
//                return orderDao1.findOrderListWithUser(null, employeeId, startDate, endDate, (pageIndex-1)*pageSize, pageSize);
//            }
//        });
        return orderDao1.findOrderListWithUser(null, employeeId, startDate, endDate, (pageIndex-1)*pageSize, pageSize);
    }

    /**
     * 门店按日期段订单数量及金额合计
     * @param employeeId
     * @return
     */
    public OrderCountAndChargeVO getDateDiffOrderCountAndCharge(int employeeId, String startDate, String endDate){
        return orderDao1.getCountAndChargeByDateDiff(null, employeeId, startDate, endDate);
    }

    /**
     * 得到服务师的本月订单数和订单合计金额
     * @param employeeId
     * @return
     */
    public OrderCountAndChargeVO getCountAndMoneyByMonth(int employeeId){
        String month = DateTimeUtil.yyyy_MM(new Date());
        return orderDao1.getCountAndChargeByMonth(null, employeeId, month);
    }
}
