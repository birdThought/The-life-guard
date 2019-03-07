package com.lifeshs.thirdservice;

import com.lifeshs.po.drugs.OrderPO;
import com.lifeshs.service1.page.Paging;

public interface DrugsOrderService {
    
    //查询订单
    public Paging<OrderPO> getDrugsOrderList(String orderNo,Integer paymentType,Integer status,int curPage,int pageSize);
    
    //根据订单编号查找订单详情
    public OrderPO findDrugsOrderInfo(String orderNo);
    
    
    
}
