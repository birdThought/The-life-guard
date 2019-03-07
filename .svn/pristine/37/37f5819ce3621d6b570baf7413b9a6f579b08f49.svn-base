package com.lifeshs.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.po.user.UserElectronicCouponsPO;
import com.lifeshs.service.shop.ShopService;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsService;


@Component
public class ShopOrderClear {
	
    private static final Logger logger = Logger.getLogger("task");
    
    
    @Autowired
    private TaskPoolService taskService;
    
    @Autowired
	private ShopService shopService;
    
    /**
     *  每小时检查商城未支付订单，超24小时过期清空
     */
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void OrderOut() {
        // 把需要做的事情添加到任务池中
        taskService.put(new Runnable() {
            
            @Override
            public void run() {                
            	shopService.OrderOut();
            	logger.info("-----清除时间超过24小时但是未支付的订单----");
            }
        });
    }
    
    /**
     *  每小时检查商城已支付订单，超7*24小时未做操作的订单，确认收货，完成订单
     */
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void AutoCompletionOrder() {
        // 把需要做的事情添加到任务池中
        taskService.put(new Runnable() {
        	
            @Override
            public void run() {                
            	shopService.AutoCompletionOrder();
            	logger.info("-----超过时间的订单自动收货完成----");
            }
        });
    }
	
}
