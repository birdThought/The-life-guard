package com.lifeshs.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.lifeshs.thirdservice.UMengPushService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.po.OrderPO;
import com.lifeshs.po.UserPO;
import com.lifeshs.pojo.message.MessageDTO;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.serve.consult.ConsultService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.vo.serve.consult.ServeUserVO;

/**
 * 项目计划任务
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年7月20日 下午4:50:49
 */
@Component
public class Project {

    private static final Logger logger = Logger.getLogger("task");

//    @Resource(name = "v2LessonService")
//    private LessonService lessonService;
//
//    @Autowired
//    private HuanXinService huanxinService;

    @Resource(name = "v2OrderService")
    private OrderService orderService;

    @Resource(name = "v2MessageService")
    private MessageService messageService;

//    @Resource(name = "orgServiceManage")
//    private IOrgServiceManage serviceManage;

    @Resource(name = "v2ConsultService")
    private ConsultService consultService;

    @Autowired
    private TaskPoolService taskService;

    @Autowired
    private UMengPushService pushService;
    
    /**
     * 检测课堂过期时间 触发时间: 每天凌晨0点
     * 取消了课堂到期处理，允许一直保留
     * @author yuhang.weng
     * @DateTime 2017年7月20日 下午4:51:52
     *
     */
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Scheduled(cron = "0 0 0 * * ? ")
//    public void checkLessonEndDate() {
//        // 把需要做的事情添加到任务池中
//        taskService.put(new Runnable() {
//            @Override
//            public void run() {
//                /**
//                 * 课堂服务自带有一个endDate结束日期，门店无法手动让课堂关闭 通过这个检测的计划任务，能够扫描到已经过期的课堂服务
//                 * 由于课堂服务不能编辑修改结束日期(暂定)，直接将该课堂的所有有效的订单全部修改为已完成 并且保存一条系统消息提示用户结束服务
//                 * （处于安全考虑，环信群组暂时保留，不删除）
//                 */
//
//                List<LessonPO> lessonList = lessonService.listLessonOutOfEndDate(0);
//                List<HuanxinGroupData> huanxinGroupList = new ArrayList<>();
//
//                List<MessageDTO> messageDTOList = new ArrayList<>(); // 用户待发送消息集合
//                for (LessonPO lesson : lessonList) {
//                    String projectCode = lesson.getCode();
//                    String huanxinGroupId = lesson.getHuanxinId();
//                    String lessonName = Toolkits.projectTilte(lesson.getName());
//
//                    List<String> memberHuanxinIdList = new ArrayList<>(); // 环信账号集合
//                    List<OrderPO> orderList = orderService.listOrderByProjectCode(projectCode);
//                    for (OrderPO order : orderList) {
//                        int orderId = order.getId();
//                        int orderStatus = order.getStatus();
//
//                        if (OrderStatus.VALID.getStatus().intValue() == orderStatus) {
//                            // 有效的订单修改为已完成
//                            orderService.completeOrder(orderId);
//                            memberHuanxinIdList.add(order.getUser().getUserCode()); // 添加环信账号
//                            MessageDTO messageDTO = new MessageDTO();
//                            String message = "由于课堂『" + lessonName + "』服务已经到期，您已被移出课堂";
//                            messageDTO.setTitle("课堂服务到期通知");
//                            messageDTO.setContent(message);
//                            messageDTO.setUserId(order.getUser().getId());
//                            messageDTO.setUserType(UserType.member.getValue());
//                            messageDTO.setOpenType(UMengOpenTypeEnum.TEXT.value());
//                            messageDTOList.add(messageDTO);
//                            logger.info("[orderId:" + orderId + "] " + messageDTO);
//                        }
//                    }
//
//                    // 课堂项目修改为下线
//                    serviceManage.updateLessonServiceStatus(projectCode, Status.OFFLINE.getValue());
//                    logger.info("课堂(" + projectCode + ")已下线");
//                    HuanxinGroupData huanxinGroup = new HuanxinGroupData();
//                    huanxinGroup.setHuanxinGroupId(huanxinGroupId);
//                    huanxinGroup.setMemberHuanxinIdList(memberHuanxinIdList);
//                    huanxinGroupList.add(huanxinGroup);
//                }
//
//                // 保存系统消息
//                if (!messageDTOList.isEmpty()) {
//                    messageService.saveMessage(messageDTOList, MessageType.SYSTEM);
//                }
//
//                // 移除环信群组的会员 这段代码放最后避免报错
//                for (HuanxinGroupData data : huanxinGroupList) {
//                    List<String> memberHuanxinIdList = data.getMemberHuanxinIdList();
//                    if (!memberHuanxinIdList.isEmpty()) {
//                        huanxinService.removeGroupUser(data.getHuanxinGroupId(), memberHuanxinIdList);
//                    }
//                }
//            }
//        });
//    }

    /**
     * 检查用户咨询服务
     * 
     * @author yuhang.weng
     * @DateTime 2017年7月26日 下午2:21:47
     *
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Scheduled(cron = "0 0 0/1 * * ?") 
    public void checkUserConsultServe() {
        // 把需要做的事情添加到任务池中
        taskService.put(new Runnable() {
            
            @Override
            public void run() {

                // 即将到期服务，只查询了健康咨询
            	int remainday = 7;
            	Calendar end = Calendar.getInstance();
            	end.add(Calendar.DAY_OF_YEAR, remainday);
            	Calendar start = Calendar.getInstance();
            	start.setTime(end.getTime());
            	int field = Calendar.HOUR, amount = 1;
            	start.set(field, start.get(field) - amount);// 根据此定时任务的cron表达式来设置参数,比如:如果1小时执行1次,则时间减去1小时,是为了限制重复多次发送消息
//            	start.add(field, -amount); 
            	List<OrderPO> remainOrderList = null;
//            	try {
					
            		remainOrderList = orderService.listConsultOrderOutOfEndDate(end.getTime(), start.getTime());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
                List<MessageDTO> messageUserList = new ArrayList<>(); // 用户待发送消息集合
                List<MessageDTO> messageEmployeeList = new ArrayList<>(); // 服务师待发送消息集合
                for (OrderPO order : remainOrderList) {
                    String serveUserRealName = Toolkits.projectTilte(order.getSubject());
                    serveUserRealName = serveUserRealName.replaceAll("$1", ""); // 避免占位符被占用
                    // 消息通知
                    int employeeUserId = order.getEmployee().getId();
                    UserPO user = order.getUser();
                    ServeUserVO serveUser = consultService.getServeUser(employeeUserId, order.getProjectCode());

                    // 用户通知
                    MessageDTO messageDTO = new MessageDTO();
                    messageDTO.setContent("由于您购买的咨询服务『" + serveUserRealName + "』服务即将到期，若要续费，可以点击立即续费进行购买");
                    messageDTO.setUserId(user.getId());
                    messageDTO.setOpenAttach("fragmentKey:com.mgc.lifeguardian.business.order.view.Order_Serve_Advisory_DetailFragment,clickText:立即续费,orderId:" + order.getId());
                    messageDTO.setOpenAttachIOS("clickText:立即续费,orderId:" + order.getId());
                    messageUserList.add(messageDTO);


                    // 服务师通知
                    MessageDTO messageServeUser = new MessageDTO();
                    messageServeUser.setContent("用户『" + user.getRealName() + "』 健康咨询服务于" + DateTimeUtilT.date(order.getEndDate()) + "日到期，可以立即沟通");
                    messageServeUser.setUserId(employeeUserId);
                    messageServeUser.setOpenAttach("clickText:立即沟通,userId:"+user.getUserCode()+",name:" + user.getRealName());
                    messageServeUser.setOpenAttachIOS(messageServeUser.getOpenAttach());
                    messageEmployeeList.add(messageServeUser);
                }

                //向服务师和用户发送消息
                pushService.pushWillFailToUser(messageUserList);
                pushService.pushWillFailToEmployee(messageEmployeeList);

                // 到期服务，只查询了健康咨询
                List<MessageDTO> messageUserFailList = new ArrayList<>(); // 用户服务到期消息集合
                List<OrderPO> orderList = orderService.listConsultOrderOutOfEndDate(new Date(), null);
                for (OrderPO order : orderList) {
                    int orderId = order.getId();
                    String serveUserRealName = Toolkits.projectTilte(order.getSubject());
                    // 有效的订单修改为已完成
                    orderService.completeOrder(orderId);

                    // 消息通知
                    MessageDTO messageDTO = new MessageDTO();
                    String message = "由于咨询服务『" + serveUserRealName + "』已超出有效时间，您的咨询服务已经结束，您可以再次使用。";
                    messageDTO.setContent(message);
                    messageDTO.setUserId(order.getUser().getId());
                    messageDTO.setOpenAttach("fragmentKey:com.mgc.lifeguardian.business.service.serviceview.HealthConsultDetailFragment,clickText:再次使用,id:"+order.getServeItemId());
                    messageDTO.setOpenAttachIOS("orderId:"+order.getId());
                    messageUserFailList.add(messageDTO);
                }

                // 发送系统消息
                pushService.pushFailToUser(messageUserFailList);
            }
        });
    }
}

class HuanxinGroupData {
    private String huanxinGroupId;
    private List<String> memberHuanxinIdList;

    public String getHuanxinGroupId() {
        return huanxinGroupId;
    }

    public void setHuanxinGroupId(String huanxinGroupId) {
        this.huanxinGroupId = huanxinGroupId;
    }

    public List<String> getMemberHuanxinIdList() {
        return memberHuanxinIdList;
    }

    public void setMemberHuanxinIdList(List<String> memberHuanxinIdList) {
        this.memberHuanxinIdList = memberHuanxinIdList;
    }
}