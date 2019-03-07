package com.lifeshs.thirdservice.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao1.drugs.DrugsOrderDao;
import com.lifeshs.po.drugs.DrugsPO;
import com.lifeshs.po.drugs.OrderPO;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.thirdservice.DrugsOrderService;

@Service("drugsOrderService")
public class DrugsOrderServiceImpl implements DrugsOrderService {
    private final Logger logger = LoggerFactory.getLogger(DrugsOrderServiceImpl.class);

    @Autowired
    DrugsOrderDao drugsOrderDao;
    
    
    @Override
    public Paging<OrderPO> getDrugsOrderList(String orderNo,Integer paymentType,Integer status,int curPage,int pageSize){
        // TODO Auto-generated method stub
        Paging<OrderPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<OrderPO>() {

            @Override
            public int queryTotal() {
                // TODO Auto-generated method stub
                return drugsOrderDao.countDrugsOrder(orderNo, paymentType, status);
            }

            @Override
            public List<OrderPO> queryData(int startRow, int pageSize) {
                // TODO Auto-generated method stub
                return drugsOrderDao.getDrugsOrderList(orderNo, paymentType, status, startRow, pageSize);
            }
        });
        return p;
    }


    @Override
    public OrderPO findDrugsOrderInfo(String orderNo) {
        // TODO Auto-generated method stub
        return drugsOrderDao.findDrugsOrderInfo(orderNo);
    }

    
    

}
