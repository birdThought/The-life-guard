package com.lifeshs.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lifeshs.dao.order.IOrderDao;
import com.lifeshs.thirdservice.HuanXinService;

/**
 *  订单任务服务类
 *  该方法已经废弃，详细任务请到com.lifeshs.task.Project查看
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月2日 下午3:40:31
 */
@Deprecated
public class Order {

    private static final Logger logger = Logger.getLogger(Order.class);
    
    @Autowired
    private IOrderDao orderDao;
    
    @Autowired
    private HuanXinService huanxinService;
    
    /**
     *  每天凌晨0点检查订单状态
     *  @author yuhang.weng 
     *  @DateTime 2016年8月25日 下午4:04:55
     *
     */
//    @Scheduled(cron = "0 0 0 * * ? ")
    public void checkOrderStatus() {
        logger.info("开始执行检查订单方法");
        // 课堂订单需要把用户退出群组
        logger.info("健康课堂需要移除的订单如下:");
        List<Map<String, Object>> result = orderDao.listNeedToBeFaildLessonOrder();
        Map<String, List<String>> map = new HashMap<>();
        for (Map<String, Object> r : result) {
            String huanxinId = (String) r.get("huanxinId");
            String userCode = (String) r.get("userCode");
            
            logger.info("群组环信ID:" + huanxinId + ", 用户环信ID:" + userCode);
            
            List<String> tmp = new ArrayList<>();
            if (map.containsKey(huanxinId)) {
                tmp = map.get(huanxinId);
            }
            tmp.add(userCode);
            map.put(huanxinId, tmp);
        }
        if (!map.isEmpty()) {
            logger.info("开始移除环信群组用户...");
            for (String key : map.keySet()) {
                huanxinService.removeGroupUser(key, map.get(key));
            }
            logger.info("结束...");
        } else {
            logger.info("无");
        }
        // 更新订单状态
        logger.info("订单状态开始修改...");
        orderDao.updateServeOrderStatus();
        logger.info("订单状态修改结束...");
    }
}
