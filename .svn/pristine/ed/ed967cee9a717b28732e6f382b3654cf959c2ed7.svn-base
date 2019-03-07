package com.lifeshs.service1.order.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dto.manager.order.GetOrderListData;
import com.lifeshs.po.OrderPO;
import com.lifeshs.service1.order.IStoreOrderService;
import com.lifeshs.vo.OrderCountAndChargeVO;
import com.lifeshs.vo.OrderStatisticsByDayVO;

/**
 * 门店的订单业务
 * Created by dengfeng on 2017/6/21 0021.
 */
@Service
public class StoreOrderService implements IStoreOrderService {

    @Autowired
    private com.lifeshs.dao1.order.IOrderDao orderDao;

    /**
     * 得到用户的订单
     * @param serveType 1线上，2线下，3上门，4全部
     * @param serveStatus 1未完成，2待回复，3已完成，4全部
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<OrderPO> findOrderList(int orgId, int serveType, int serveStatus, String search, int pageIndex, int pageSize,Integer memberId) {
        if (serveStatus == 2) {
            return orderDao.findOrderListWithComments(orgId, null, 0, (pageIndex - 1) * pageSize, pageSize,memberId);
        }
        Integer status = null;  //订单状态，状态：待付款_1, 付款失败_2，有效_3，已完成_4，已退款_5，已取消_6
        if (serveStatus == 1)
            status = 3;
        else if (serveStatus == 3)
            status = 4;
        return orderDao.findOrderList(orgId, null, serveType == 0 ? null : serveType, status, search,
                (pageIndex - 1) * pageSize, pageSize,memberId);
    }

    /**
     * 分页查询门店/服务师所属的订单(只查已有评论的订单)
     * @param orgId 门店ID
     * @param commentStatus 评论状态（0：未回复；1：已回复）
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<OrderPO> findOrderListWithComments(int orgId, int commentStatus, int pageIndex, int pageSize){
        return orderDao.findOrderListWithComments(orgId, null, commentStatus, (pageIndex-1)*pageSize, pageSize,null);
    }

    /**
     * 分页查询门店/服务师所属的订单(只关联用户)
     * @param orgId 门店ID
     * @param startDate 开始日期
     * @return
     */
    public List<GetOrderListData> findOrderListWithUser(int orgId, String startDate, String endDate, int pageIndex, int pageSize){
//        Paging<GetOrderListData> paging = new Paging<>(pageIndex, pageSize);
//        paging.setQueryProc(new IPagingQueryProc() {
//            @Override
//            public int queryTotal() {
//                return orderDao.findOrderCountWithUser(orgId, null, startDate, endDate);
//            }
//
//            @Override
//            public List queryData(int startRow, int pageSize) {
//                return orderDao.findOrderListWithUser(orgId, null, startDate, endDate, (pageIndex-1)*pageSize, pageSize);
//            }
//        });
        return orderDao.findOrderListWithUser(orgId, null, startDate, endDate, (pageIndex-1)*pageSize, pageSize);
    }

    /**
     * 门店按日期段订单数量及金额合计
     * @param orgId
     * @return
     */
    public OrderCountAndChargeVO getDateDiffOrderCountAndCharge(int orgId, String startDate, String endDate){
        return orderDao.getCountAndChargeByDateDiff(orgId, null, startDate, endDate);
    }

    /**
     * 门店的今日订单数量及金额合计
     * @param orgId
     * @return
     */
    public OrderCountAndChargeVO getTodayOrderCountAndCharge(int orgId) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return orderDao.getCountAndChargeByDay(orgId, null, dateString);
    }

    /**
     * 查询门店的指定月份的订单数量及金额合计
     * @param orgId
     * @param month 指定月份:"yyyy-MM"
     * @return
     */
    public OrderCountAndChargeVO getMonthOrderCountAndCharge(int orgId, String month){
        return orderDao.getCountAndChargeByMonth(orgId, null, month);
    }

    /**
     * 按日统计门店的指定月份的订单数量及金额合计
     * @param statementId 结算记录ID
     * @return
     */
    public List<OrderStatisticsByDayVO> getMonthOrderStatistics(int statementId){
        return orderDao.getMonthOrderStatistics(statementId);
    }
}
