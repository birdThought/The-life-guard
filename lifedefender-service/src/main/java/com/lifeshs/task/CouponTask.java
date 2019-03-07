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
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsService;

/**
 *  电子券计划任务
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2018年1月2日 上午10:52:53
 */
@Component
public class CouponTask {

    private static final Logger logger = Logger.getLogger("task");
    
    @Resource(name = "electronicCouponsService")
    private ElectronicCouponsService couponsService;
    
    @Autowired
    private TaskPoolService taskService;
    
    /**
     *  检查用户电子券状态
     *  <p>间隔为30分钟
     *  @author yuhang.weng 
     *  @DateTime 2018年1月2日 上午11:06:46
     *
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void checkUserCoupon() {
        // 把需要做的事情添加到任务池中
        taskService.put(new Runnable() {
            
            @Override
            public void run() {
                // 查询失效电子券列表
                List<UserElectronicCouponsPO> couponsList = couponsService.listCouponsOutOfEndDate(0);
                if (couponsList.isEmpty()) {
                    return;
                }
                List<Integer> idList = new ArrayList<>();
                for (UserElectronicCouponsPO c : couponsList) {
                    idList.add(c.getId());
                }
                // 更新电子券状态为失效
                try {
                    couponsService.updateCouponsOutOfEndDate(idList);
                } catch (ParamException e) {
                    logger.error(e.getMessage());
                }
            }
        });
    }
}
