package com.lifeshs.thirdservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.app.notice.ActivityMemberEnum;
import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.MessageType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.component.umeng.UMengPushUtil;
import com.lifeshs.component.umeng.util.CallBackDTO;
import com.lifeshs.component.umeng.util.Key;
import com.lifeshs.component.umeng.util.UMengOpenTypeEnum;
import com.lifeshs.dao.member.IPushDao;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.push.MeasureReminderTaskPo;
import com.lifeshs.po.push.OrgUserDeviceTokenPO;
import com.lifeshs.po.push.UserDeviceTokenPO;
import com.lifeshs.pojo.app.PushTaskDTO;
import com.lifeshs.pojo.member.MeasureReminderTaskDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.message.MessageDTO;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.service1.push.PushDataService;
import com.lifeshs.service1.store.employee.IEmployeeService;
import com.lifeshs.utils.DateTimeUtilT;

/**
 * 友盟消息推送
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年9月8日 上午11:06:54
 */
@Component
public class UMengPushService {

    @Autowired
    protected MessageService messageService;

    @Resource(name = "memberPushDao")
    private IPushDao memberPushDao;

    @Resource(name = "pushDataService")
    private PushDataService pushDataService;
    
    @Resource(name = "pushDataService")
    private PushDataService pDataService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    protected IMemberService memberService;
    
    private Key appAndroidKey;
    private Key appIOSKey;
    private Key mappAndroidKey;
    private Key mappIOSKey;
    private String productionMode;
    private UMengPushUtil uMengPushUtil;
    private final Logger logger = Logger.getLogger(UMengPushService.class);
    private final int UM_SMS_MAX_NUM = 500;  //友盟单次发送短信最大为500条

    public UMengPushService(Key appAndroidKey, Key appIOSKey, Key mappAndroidKey, Key mappIOSKey,
            String productionMode) {
        this.appAndroidKey = appAndroidKey;
        this.appIOSKey = appIOSKey;
        this.mappAndroidKey = mappAndroidKey;
        this.mappIOSKey = mappIOSKey;
        this.productionMode = productionMode;
        this.uMengPushUtil = new UMengPushUtil(appAndroidKey, appIOSKey, mappAndroidKey, mappIOSKey, productionMode);
    }
    
    //平台手动批量推送消息
    public void manualPushBatch(List<UserDeviceTokenPO> list, String title, String content, UMengOpenTypeEnum openType, String openTargetOrUrl, String[] params){
        MessageType msgType = MessageType.SYSTEM;
        UserType userType =  UserType.member;  //此参数应由外面给入，暂时只实现member
        String openAttach = null,openTarget = null, openAttachIOS = null, openTargetIOS = null;
        
        //如果是URL和打开APP窗口，处理数据，TEXT无需处理
        if(openType == UMengOpenTypeEnum.URL){
            openTarget = openTargetOrUrl;
            openTargetIOS = openTargetOrUrl;
        }
        if(openType == UMengOpenTypeEnum.Activity){
            ActivityMemberEnum activityEnum = ActivityMemberEnum.valueOf(openTargetOrUrl);
            userType = UserType.parseOf(activityEnum.userType());
            msgType = MessageType.parseOf(activityEnum.messageType());
            openTarget = activityEnum.openTarget();
            openTargetIOS = activityEnum.openTargetIOS();
            
            //装配参数
            openAttach = activityEnum.openAttach();
            openAttachIOS = activityEnum.openAttachIOS();
            if(params != null && params.length > 0) {
                for (int i=0; i<params.length; i++) {
                    openAttach = openAttach.replace("$"+(i+1), params[i]);
                    openAttachIOS = openAttachIOS.replace("$"+(i+1), params[i]);
                }
            }
        }
        
        //对用户进行分类处理
        List<List<UserDeviceTokenPO>> listAll = new ArrayList<List<UserDeviceTokenPO>>();
        boolean isManager = UserType.orgUser == userType ? true : false;
        List<UserDeviceTokenPO> iosIsManagerlist = null;
        List<UserDeviceTokenPO> iosNotIsManagerlist = null;
        List<UserDeviceTokenPO> androidIsManagerlist = null;
        List<UserDeviceTokenPO> androidNotIsManagerlist = null;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getOS() ==1 && isManager){
                androidIsManagerlist = paramAssemble(listAll, androidIsManagerlist, list.get(i));  //安卓、机构用户
            }else if (list.get(i).getOS() ==1 && !isManager){
                androidNotIsManagerlist = paramAssemble(listAll, androidNotIsManagerlist, list.get(i));  //安卓、非机构用户
            }else if(list.get(i).getOS() ==2 && isManager){
                iosIsManagerlist = paramAssemble(listAll, iosIsManagerlist, list.get(i));  //苹果、机构用户
            }else if(list.get(i).getOS() ==2 && !isManager){
                iosNotIsManagerlist = paramAssemble(listAll, iosNotIsManagerlist, list.get(i));  //苹果、非机构用户
            }else{
                logger.error("友盟推送数据类型超出范围("+title+"): system:" +list.get(i).getOS()+",isManager:"+isManager);
            }
        }
        
        //分批推送信息
        for(int i = 0; i<listAll.size(); i++){
            if(listAll.get(i).size() == 0)
                continue;
            List<Integer> userList = new ArrayList<>();
            List<String> tokenList = new ArrayList<String>();
            for(UserDeviceTokenPO deviceTokenPO : listAll.get(i)){
                userList.add(deviceTokenPO.getUserId());
                tokenList.add(deviceTokenPO.getDeviceToken());
            }
            //保存到表，放在此处分批保存(一次全部保存的话数据量过大时会崩溃)
            messageService.saveMessage(userList, openType, userType, title, content, openTarget, openAttach, openTargetIOS, openAttachIOS, msgType);
            
            try {
                //推送消息
                boolean isIos = listAll.get(i).get(0).getOS() == 2 ? true : false;
                afterSend(uMengPushUtil.pushMessage(isIos, isManager, tokenList, title, content, openType, openTarget, openAttach, openTargetIOS, openAttachIOS),0);
            } catch (ParamException e) {
                logger.error("UMengPushService数据推送:",e);
            }
        }
    }
    
    /**
     * 
     *  推送信息分类处理
     *  @author liaoguo
     *  @DateTime 2018年5月22日 上午11:50:12
     *
     *  @param listAll 分类后的集合
     *  @param typelist 需要分类的数据
     *  @param tokenPO 要加入的数据
     */
    private List<UserDeviceTokenPO> paramAssemble(List<List<UserDeviceTokenPO>> listAll, List<UserDeviceTokenPO> typelist, UserDeviceTokenPO tokenPO){
        if(typelist == null || typelist.size() == UM_SMS_MAX_NUM){
            typelist = new ArrayList<UserDeviceTokenPO>();
            listAll.add(typelist);
        }
        typelist.add(tokenPO);
        return typelist;
    }
    
    //平台手动推送消息,由于未给入用户devicetoken，因此做循环单条发
    public void manualPush(Integer[] ids, String title, String content, UMengOpenTypeEnum openType, String openTarget, String[] params){

        for(int i=0;i<ids.length;i++){
            if(openType == UMengOpenTypeEnum.TEXT)
                pushText(UserType.member, ids[i], title, content, MessageType.SYSTEM);
            else if(openType == UMengOpenTypeEnum.URL)
                pushURL(UserType.member, ids[i], title, content, openTarget, MessageType.SYSTEM);
            else
                pushActivity(ids[i], content, ActivityMemberEnum.valueOf(openTarget), params);
        }
    }

     //门店手动推送消息
     public void pushStoreMessage(Integer[] userIdList,String title, int sendId, String content, UMengOpenTypeEnum openType, String openTarget, String[] params){
         String text = content;
         //暂时按条推, 后期改为批量推送保存
         //pushAndSaveManage(UserType.member, StringUtils.join(userIdList, ","), title, content, openType, openTarget, params, MessageType.SYSTEM);
         for (int i = 0; i < userIdList.length; i++) {
             if(openType == UMengOpenTypeEnum.TEXT)
                 pushText(UserType.member, userIdList[i], title, content, MessageType.SYSTEM);
             else if(openType == UMengOpenTypeEnum.URL)
                 pushURL(UserType.member, userIdList[i], title, content, openTarget, MessageType.SYSTEM);
             else
                 pushActivity(userIdList[i], content, ActivityMemberEnum.valueOf(openTarget), params);
         }
     }

     //门店管理员提醒服务师赶紧完成用户订单
     public void notifyEmployeeFinishOrder(int orderId, int employeeId, String realName, String subject){
         String[] attachParam = new String[] {String.valueOf(orderId)};
         String content = "用户["+realName+"]的订单["+subject+"]未完成，请及时服务用户。";
         pushActivity(employeeId, content, ActivityMemberEnum.EMPLOYEE_FINISH_ORDER, attachParam);
     }

     //分析报告完成后给用户的推送
    public void replyReportEnd(int userId, int reportAnalysisId, Date date){
        String[] attachParam = new String[] {String.valueOf(reportAnalysisId)};
        String content = "您于" + DateTimeUtilT.date(date) + "提交的分析报告结果已给出";
        pushActivity(userId, content, ActivityMemberEnum.REPLY_END, attachParam);
    }

    //渠道商提交用户分析报告申请时给用户的推送
    public void replyReportStart(int userId, int reportAnalysisId){
        String[] attachParam = new String[] {String.valueOf(reportAnalysisId)};
        String content = "您于" + DateTimeUtilT.date(new Date()) + "提交的分析报告正在处理中。";
        pushActivity(userId, content, ActivityMemberEnum.REPLY_START, attachParam);
    }

    //用户设定的测量提醒推送
    public void measureRemindPush(int weekDay) {
        String title = "测量提醒";
        String text = "";
        List<MeasureReminderTaskDTO> datas = memberPushDao.listMeasureReminderTask(weekDay);
        for (MeasureReminderTaskDTO data : datas) {
            String deviceToken = data.getDeviceToken();
            if (StringUtils.isEmpty(deviceToken))
                continue;    // deviceToken不存在的数据不进行推送处理
            text = "生命守护提醒您，您需要测量"+data.getDevices() + "了";
            int receiverId = data.getReceiverId();
            boolean ios = data.getOS() == 2 ? true : false;
            pushText(UserType.member, receiverId, title, text, MessageType.SYSTEM);
        }
    }

    //订单退款通知
    public void refundMsgPush(OrderPO orderPO) {
        String[] attachParam = new String[] {String.valueOf(orderPO.getId())};

        // 推送门店管理员
        int orgId = orderPO.getStore().getId();
        int receiveId = employeeService.getManage(orgId).getId();
        String content = "用户[" + orderPO.getUser().getRealName() + "]对订单[" + orderPO.getOrderNumber() + "]进行了退款操作。";
        pushActivity(receiveId, content, ActivityMemberEnum.REFUND_EMPLOYEE, attachParam);

        // 推送门店服务师
        int employeeId = orderPO.getEmployee().getId();
        content = "用户[" + orderPO.getUser().getRealName() + "]对订单[" + orderPO.getOrderNumber() + "]进行了退款操作。";
        pushActivity(employeeId, content, ActivityMemberEnum.REFUND_EMPLOYEE, attachParam);

        //推送用户
        int userId = orderPO.getUser().getId();
        content = "您的订单[" + orderPO.getOrderNumber() + "]已退款成功，订单金额将原路返还到您的帐户。";
        //pushActivity(userId, content, ActivityMemberEnum.REFUND_USER, attachParam);  暂时用TEXT方式，因为APP的订单详情有多个，短时间处理不好
        pushText(UserType.member, userId, "订单退款通知", content, MessageType.SYSTEM);
    }

    /**
     *
     *  向服务师推送新用户购买信息
     *  @author NaN
     *  @DateTime 2018年5月11日 上午10:19:00
     *
     *  @param employeeId
     *  @param userId
     *  @param orderNumber
     *  @param orderId
     */
    public void pushOrderMessage(int employeeId, int userId, String subject, String orderNumber, int orderId){
        String[] attachParam = new String[] { String.valueOf(orderId) };
        UserDTO userDTO = memberService.getUser(userId);
        String content = "新用户[" + userDTO.getRealName() + "]购买了[" + subject + "]服务，订单号:" + orderNumber + "。";
        pushActivity(employeeId, content, ActivityMemberEnum.ORDER_BUY_EMPLOYEE, attachParam);
    }

    //用户测量时有异常数据向门店服务师推送
    public void saveWarningMessage(int userId, HealthPackageType deviceType, Date date) {
        UserDTO userDTO = memberService.getUser(userId);
        List<Integer> list = employeeService.findEmployeeListByUserId(userId);
        for (Integer orgUserId : list) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String[] params = new String[]{String.valueOf(userId), formatter.format(date)};
            String content = "用户『" + (userDTO.getRealName() == null ? userDTO.getUserName() : userDTO.getRealName()) + "』 " + deviceType.getName() + "设备数据异常。";
            pushActivity(orgUserId, content, ActivityMemberEnum.MEASURE_WARNING, params);
        }
    }

    //推送服务将到期通知给用户
    public void pushWillFailToUser(List<MessageDTO> list){
        if(list == null || list.size() == 0)
            return;
        for(MessageDTO messageDTO : list){
            messageDTO.setTitle("用户服务即将到期通知");
            messageDTO.setUserType(UserType.member.getValue());
            messageDTO.setOpenType(UMengOpenTypeEnum.Activity.value());
            messageDTO.setOpenTarget(ActivityMemberEnum.SERVE_WILL_END_USER.openTarget());
            messageDTO.setOpenTargetIOS(ActivityMemberEnum.SERVE_WILL_END_USER.openTargetIOS());
        }
        pushList(list);
    }

    //推送服务到期通知给用户
    public void pushFailToUser(List<MessageDTO> list){
        if(list == null || list.size() == 0)
            return;
        for(MessageDTO messageDTO : list){
            messageDTO.setTitle("用户服务到期通知");
            messageDTO.setUserType(UserType.member.getValue());
            messageDTO.setOpenType(UMengOpenTypeEnum.Activity.value());
            messageDTO.setOpenTarget(ActivityMemberEnum.SERVE_END_USER.openTarget());
            messageDTO.setOpenTargetIOS(ActivityMemberEnum.SERVE_END_USER.openTargetIOS());
        }
        pushList(list);
    }
    
    public void pushWillFailComboToUser(List<MessageDTO> list) {
    	if(list == null || list.size() == 0)
            return;
    	for(MessageDTO messageDTO : list){
            messageDTO.setTitle("套餐提前通知");
            messageDTO.setUserType(UserType.member.getValue());
            messageDTO.setOpenType(UMengOpenTypeEnum.TEXT.value());
            messageDTO.setOpenTarget(ActivityMemberEnum.SERVE_END_USER.openTarget());
            messageDTO.setOpenTargetIOS(ActivityMemberEnum.SERVE_END_USER.openTargetIOS());
        }
    	pushList(list);
    }
    
    public void pushFailedComboToUser(List<MessageDTO> list) {
    	if(list == null || list.size() == 0)
            return;
    	for(MessageDTO messageDTO : list){
            messageDTO.setTitle("套餐到期通知");
            messageDTO.setUserType(UserType.member.getValue());
            messageDTO.setOpenType(UMengOpenTypeEnum.TEXT.value());
            messageDTO.setOpenTarget(ActivityMemberEnum.SERVE_END_USER.openTarget());
            messageDTO.setOpenTargetIOS(ActivityMemberEnum.SERVE_END_USER.openTargetIOS());
        }
    	pushList(list);
    }

    //推送用户服务将到期通知给服务师
    public void pushWillFailToEmployee(List<MessageDTO> list){
        if(list == null || list.size() == 0)
            return;
        for(MessageDTO messageDTO : list){
            messageDTO.setTitle("用户服务即将到期通知");
            messageDTO.setUserType(UserType.orgUser.getValue());
            messageDTO.setOpenType(UMengOpenTypeEnum.Activity.value());
            messageDTO.setOpenTarget(ActivityMemberEnum.WILL_FAIL_EMPLOYEE.openTarget());
            messageDTO.setOpenTargetIOS(ActivityMemberEnum.WILL_FAIL_EMPLOYEE.openTargetIOS());
        }
        pushList(list);
    }

    //批量推送和保存消息，推送为单条发送方式，给入条件不含token
    private void pushList(List<MessageDTO> list){
        messageService.saveMessage(list, MessageType.SYSTEM);
        for(MessageDTO messageDTO : list) {
            pushManage(UserType.parseOf(messageDTO.getUserType()), messageDTO.getUserId(), messageDTO.getTitle(), messageDTO.getContent(), UMengOpenTypeEnum.parseOf(messageDTO.getOpenType()),
                    messageDTO.getOpenTarget(), messageDTO.getOpenAttach(), messageDTO.getOpenTargetIOS(), messageDTO.getOpenAttachIOS());
        }
    }
    //推送保存消息—APP窗体，不再保存到表t_message
    private void pushActivity(int userId, String content, ActivityMemberEnum activityEnum, String[] params){
        //装配参数
        String openAttach = activityEnum.openAttach();
        String openAttachIOS = activityEnum.openAttachIOS();
        if(params != null && params.length > 0) {
            for (int i=0; i<params.length; i++) {
                openAttach = openAttach.replace("$"+(i+1), params[i]);
                openAttachIOS = openAttachIOS.replace("$"+(i+1), params[i]);
            }
        }
        pushAndSaveManage(UserType.parseOf(activityEnum.userType()), userId, activityEnum.title(), content, UMengOpenTypeEnum.Activity,
                activityEnum.openTarget(), openAttach, activityEnum.openTargetIOS(), openAttachIOS, MessageType.parseOf(activityEnum.messageType()));
    }

    //推送保存消息—URL
    private void pushURL(UserType userType, int userId, String title, String content, String openTarget, MessageType msgType){
        pushAndSaveManage(userType, userId, title, content, UMengOpenTypeEnum.URL, openTarget, null, openTarget, null, MessageType.SYSTEM);
    }

    //推送保存消息—文本
    private void pushText(UserType userType, int userId, String title, String content, MessageType msgType){
        pushAndSaveManage(userType, userId, title, content, UMengOpenTypeEnum.TEXT, null, null, null, null, MessageType.SYSTEM);
    }

    /**
     * 通用推送消息和保存信息
     *  @author NaN
     *  @DateTime 2018年5月9日 下午4:32:01
     *  @param userId
     *  @param userType 门店服务师，用户
     *  @param openType
     *  @param title
     *  @param content
     */
    private void pushAndSaveManage(UserType userType, int userId, String title, String content, UMengOpenTypeEnum openType, String openTarget, String openAttach, String openTargetIOS, String openAttachIOS, MessageType msgType){
        int iosNum = 0;
        String deviceToken = null;
        boolean isManager = false;

        //保存到消息表
        int id = messageService.saveMessage(userId, userType, title, content, openType, openTarget, openAttach, openTargetIOS, openAttachIOS, msgType);
        //获取token码
        if(UserType.member == userType){    //用户
            UserDeviceTokenPO tokenPO = pDataService.getUserPushToken(userId);
            if (tokenPO != null) {
                iosNum = tokenPO.getOS();
                deviceToken = tokenPO.getDeviceToken();
            }
        }else if (UserType.orgUser == userType){  //机构用户
            isManager = true;
            OrgUserDeviceTokenPO deviceTokenPO = pushDataService.getOrgUserPushToken(userId);
            if (deviceTokenPO != null) {
                iosNum = deviceTokenPO.getOS();
                deviceToken = deviceTokenPO.getDeviceToken();
            }
        }
        if(org.springframework.util.StringUtils.isEmpty(deviceToken))   //取不到token则不做推送
            return ;
        boolean ios = (iosNum == 2) ? true : false;
        try {
            afterSend(uMengPushUtil.pushMessage(ios, isManager, deviceToken, title, content, openType, openTarget, openAttach, openTargetIOS, openAttachIOS), userId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 通用推送消息，不保存信息
     *  @author NaN
     *  @DateTime 2018年5月9日 下午4:32:01
     *  @param userId
     *  @param userType 门店服务师，用户
     *  @param openType
     *  @param title
     *  @param content
     */
    private void pushManage(UserType userType, int userId, String title, String content, UMengOpenTypeEnum openType, String openTarget, String openAttach, String openTargetIOS, String openAttachIOS){
        int iosNum = 0;
        String deviceToken = null;
        boolean isManager = false;

        //获取token码
        if(UserType.member == userType){    //用户
            UserDeviceTokenPO tokenPO = pDataService.getUserPushToken(userId);
            if (tokenPO != null) {
                iosNum = tokenPO.getOS();
                deviceToken = tokenPO.getDeviceToken();
            }
        }else if (UserType.orgUser == userType){  //机构用户
            isManager = true;
            OrgUserDeviceTokenPO deviceTokenPO = pushDataService.getOrgUserPushToken(userId);
            if (deviceTokenPO != null) {
                iosNum = deviceTokenPO.getOS();
                deviceToken = deviceTokenPO.getDeviceToken();
            }
        }
        if(org.springframework.util.StringUtils.isEmpty(deviceToken))   //取不到token则不做推送
            return ;
        boolean ios = (iosNum == 2) ? true : false;
        try {
            afterSend(uMengPushUtil.pushMessage(ios, isManager, deviceToken, title, content, openType, openTarget, openAttach, openTargetIOS, openAttachIOS), userId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    //推送后处理，如果失败保存到表里
    private void afterSend(CallBackDTO callBack, int messageId){
        String errorCode = callBack.getData().getErrorCode();
        // 失败
        if (errorCode != null) {
            String errorMsg = String.format("消息ID为%d的推送任务发送失败，错误码为%s，返回内容:%s", messageId, errorCode, callBack.getData().getErrorMsg());
            logger.error(errorMsg);
            PushTaskDTO pushTask = new PushTaskDTO();
            pushTask.setReminderDetailId(messageId);
            pushTask.setMsgId(callBack.getData().getMsgId());
            pushTask.setTaskId(callBack.getData().getTaskId());
            pushTask.setStatus(3);
            pushTask.setErrorMsg(errorMsg);
            memberPushDao.addPushTask(pushTask);
        }
    }
    
    
    /**
     * 服务师设定的用户提示闹钟
     * @param day
     */
	public void MemberserviceRemind(Integer weekDay) {
		 String title = "服务提醒";
	        String text = "";
	        List<MeasureReminderTaskPo> datas = memberPushDao.MemberserviceRemind(weekDay);
	        for (MeasureReminderTaskPo data : datas) {	        	
	            String deviceToken = data.getDeviceToken();
	            if (StringUtils.isEmpty(deviceToken))
	                continue;    // deviceToken不存在的数据不进行推送处理
	            text = data.getContent();
	            int receiverId = data.getReceiverId();
	            boolean ios = data.getOS() == 2 ? true : false;
	            pushText(UserType.member, receiverId, title, text, MessageType.SERVICES);
	        }
		
	}
}
