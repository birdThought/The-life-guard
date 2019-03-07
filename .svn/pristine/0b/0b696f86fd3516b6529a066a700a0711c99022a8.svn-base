package com.lifeshs.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.pojo.message.MessageDTO;
import com.lifeshs.service1.order.vip.VipUserOrderService;
import com.lifeshs.thirdservice.UMengPushService;

/**
 * 套餐过期消息通知
 * @author liu
 * @时间 2018年12月26日 下午3:08:11
 * @说明
 */
@Component
public class OrderVipOutdateMsg {
	
	@Autowired @Qualifier("vipUserOrderServiceImpl")
	private VipUserOrderService vipUserOrderService;
	
	@Autowired
	private UMengPushService pushService;
	
    @Autowired
    private TaskPoolService taskService;
	
	@Scheduled(cron = "0 0 0/1 * * ?") @Transactional(propagation = Propagation.REQUIRED)
	public void execute() {
		// 把需要做的事情添加到任务池中
        taskService.put(new Runnable() {
            
            @Override
            public void run() {
				// 发送即将到期通知
				int remainday = 7;
		    	Calendar end = Calendar.getInstance();
		    	end.add(Calendar.DAY_OF_YEAR, remainday);
		    	Calendar start = Calendar.getInstance();
		    	start.setTime(end.getTime());
		    	int field = Calendar.HOUR, amount = 1;
		    	start.set(field, start.get(field) - amount);// 根据此定时任务的cron表达式来设置参数,比如:如果1小时执行1次,则时间减去1小时,是为了限制重复多次发送消息
		    	List<Map<String, Object>> wiilOutDateComboOrders = null;
		    	try {
					
		    		wiilOutDateComboOrders = vipUserOrderService.findVipOrderOutOfEndDateList(end.getTime(), start.getTime());
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	List<MessageDTO> willFailComboOrderList = new ArrayList<>(); // 用户待发送消息集合
		    	for (Map<String, Object> comboOrder : wiilOutDateComboOrders) {
		    		Integer userId = (Integer)comboOrder.get("userId");
		    		String comboName = (String)comboOrder.get("name");
		    		// 用户通知
		            MessageDTO messageDTO = new MessageDTO();
		            messageDTO.setContent("您购买的套餐『" + comboName + "』服务即将到期,还剩" + remainday + "天到期!");
		            messageDTO.setUserId(userId);
		            messageDTO.setUserType(UserType.member.getValue());
		//            messageDTO.setOpenAttach("fragmentKey:com.mgc.lifeguardian.business.order.view.Order_Serve_Advisory_DetailFragment,clickText:立即续费,orderId:" + order.getId());
		//            messageDTO.setOpenAttachIOS("clickText:立即续费,orderId:" + order.getId());
		            willFailComboOrderList.add(messageDTO);
				}
		    	pushService.pushWillFailComboToUser(willFailComboOrderList);
		    	
		    	// 发送到期通知
		    	List<Map<String, Object>> outDateComboOrders = vipUserOrderService.findVipOrderOutOfEndDateList(new Date(), null);
		    	List<MessageDTO> failedComboOrderList = new ArrayList<>(); // 到期通知集合
		    	for (Map<String, Object> comboOrder : outDateComboOrders) {
		    		Integer userId = (Integer)comboOrder.get("userId");
		    		String comboName = (String)comboOrder.get("name");
		    		Integer orderId = (Integer)comboOrder.get("id");
		    		// 用户通知
		            MessageDTO messageDTO = new MessageDTO();
		            messageDTO.setContent("您购买的套餐『" + comboName + "』服务已到期!");
		            messageDTO.setUserId(userId);
		            messageDTO.setUserType(UserType.member.getValue());
		//            messageDTO.setOpenAttach("fragmentKey:com.mgc.lifeguardian.business.order.view.Order_Serve_Advisory_DetailFragment,clickText:立即续费,orderId:" + order.getId());
		//            messageDTO.setOpenAttachIOS("clickText:立即续费,orderId:" + order.getId());
		            failedComboOrderList.add(messageDTO);
		            // 完成订单
		            vipUserOrderService.finishOrder(orderId);
				}
		    	pushService.pushFailedComboToUser(failedComboOrderList);
            }
        });
	}
}
