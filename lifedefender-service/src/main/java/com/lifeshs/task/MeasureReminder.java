package com.lifeshs.task;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.MessageType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao.member.IPushDao;
import com.lifeshs.pojo.member.MeasureReminderTaskDTO;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.thirdservice.UMengPushService;

/**
 *  测量提醒
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月23日 上午10:11:26
 */
@Component
public class MeasureReminder {

    private static final Logger logger = Logger.getLogger(MeasureReminder.class);

    @Resource(name = "memberPushDao")
    private IPushDao memberPushDao;

    @Resource(name = "v2MessageService")
    private MessageService messageService;
    
    @Autowired
    private UMengPushService pushService;
    
    @Autowired
    private TaskPoolService taskPoolService;
    
    /**
     * 用户设定的测量提醒推送
     *  5分钟运行一次检查
     *  @author yuhang.weng 
     *  @DateTime 2017年5月23日 上午10:49:17
     *
     */
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void measureReminderMsgPush() {
        // 把需要做的事情添加到任务池中
        taskPoolService.put(new Runnable() {
            
            @Override
            public void run() {
                LocalDate date = LocalDate.now();
                int day = date.getDayOfWeek().getValue();
                // 定义星期天为0
                if (day == 7) day = 0;

                // 推送
                pushService.measureRemindPush(day);
            }
        });
    }
}
