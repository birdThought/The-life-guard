package com.lifeshs.task;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lifeshs.dao.member.IPushDao;
import com.lifeshs.dao1.member.IMemberDao;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.thirdservice.UMengPushService;
@Component
public class MemberserviceRemind {
	
	 private static final Logger logger = Logger.getLogger(MemberserviceRemind.class);
	    
	    @Autowired
	    private UMengPushService pushService;
	    
	    @Autowired
	    private TaskPoolService taskPoolService;
	    
	    /**
	     *  服务师设定的用户提示闹钟
	     *  1分钟运行一次检查
	     */
	    @Scheduled(cron = "0 0/1 * * * ? ")
	    public void MemberserviceRemind() {
	        // 把需要做的事情添加到任务池中
	        taskPoolService.put(new Runnable() {
	            
	            @Override
	            public void run() {
	                LocalDate date = LocalDate.now();
	                Integer day = date.getDayOfWeek().getValue();
	                // 定义星期天为0
	                if (day == 7) day = 0;

	                // 推送
	                pushService.MemberserviceRemind(null);
	            }
	        });
	    }
}
