package com.lifeshs.app.api.member.v24;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.component.umeng.util.UMengOpenTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Comment;
import com.lifeshs.common.constants.app.HuanXin;
import com.lifeshs.common.constants.app.Message;
import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.app.OrgServeGroup;
import com.lifeshs.common.constants.app.OrgUser;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.Serve;
import com.lifeshs.common.constants.app.user.Customer;
import com.lifeshs.common.constants.app.vip.VipId;
import com.lifeshs.common.constants.common.Error;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.LessonPO;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.po.vip.VipUserPO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.message.MessageDTO;
import com.lifeshs.pojo.message.MessagePlaceholderDTO;
import com.lifeshs.pojo.order.v2.OrderDTO;
import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.service.org.employee.IEmployeeManageService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.order.comment.CommentService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.serve.consult.ConsultService;
import com.lifeshs.service1.serve.lesson.LessonService;
import com.lifeshs.service1.vip.IVipUserService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.ListUtil;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.vo.serve.consult.ServeUserVO;

/**
 *  消息中心
 *  @author yuhang.weng
 *  @version 2.4
 *  @DateTime 2017年6月26日 上午10:26:13
 */
@RestController(value = "v24MessageController")
@RequestMapping(value = "app/message/v24")
public class MessageController {

    private static final UserType MEMBER = UserType.member;
    
    @Resource(name = "orderCommentService")
    private CommentService commentService;
    
    @Resource(name = "employeeManageService")
    private IEmployeeManageService employeeService;
    
    @Resource(name = "v2OrderService")
    private OrderService orderService;
    
    @Resource(name = "v2MessageService")
    private MessageService messageService;
    
    @Resource(name = "v2LessonService")
    private LessonService lessonService;
    
    @Resource(name = "customerUserService")
    private CustomerUserService customerUserService;
    
    @Resource(name = "vipUserService")
    private IVipUserService vipUserService;
    
    @Resource(name = "v2ConsultService")
    private ConsultService consultService;
    
    /**
     *  提交评价
     *  @author yuhang.weng 
     *	@DateTime 2017年6月17日 下午5:48:24
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "commitComment", method = RequestMethod.POST)
    public JSONObject commitComment(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        Integer score = mm_0.getInteger(Comment.SCORE);
        Integer orderId = mm_0.getInteger(Comment.ORDER_ID);
        
        if (orderId == null) {
            return AppNormalService.error(String.format(Error.MISSING, "订单号ID"), ErrorCodeEnum.REQUEST);
        }
        if (score == null) {
            return AppNormalService.error(String.format(Error.MISSING, "分数"), ErrorCodeEnum.REQUEST);
        }

        Integer id = null;
        try {
            id = commentService.commitComment(orderId, score, userId);
        } catch (OperationException oe) {
            return AppNormalService.error(oe.getMessage(), oe.getCode());
        }

        Map<String, Object> data = new HashMap<>();
        data.put(Comment.ID, id);

        return AppNormalService.success(data);
    }
    
    /**
     *  通过环信账号查找服务相关信息
     *  @author yuhang.weng 
     *	@DateTime 2017年7月21日 下午5:59:30
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getServeDataByHuanxinAcount", method = RequestMethod.POST)
    public JSONObject getServeDataByHuanxinAcount(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String userNames = mm_0.getString(HuanXin.USERNAMES);
        String groupUserNames = mm_0.getString(HuanXin.GROUP_USERNAME);
        List<String> names = new ArrayList<>();
        List<String> huanxinGroupUserNames = new ArrayList<>();

        if (StringUtils.isNotBlank(userNames)) {
            names = JSONArray.parseArray(userNames, String.class);
        }
        if (StringUtils.isNotBlank(groupUserNames)) {
            huanxinGroupUserNames = JSONArray.parseArray(groupUserNames, String.class);
        }

        List<Map<String, Object>> returnDatas = new ArrayList<>();
        
        if (names.size() > 0) {
            names = ListUtil.removeRepeatElement(names, String.class);
            
            /** 客服的环信code */
            List<String> customerCodeList = new ArrayList<>();
            Iterator<String> iterator = names.iterator();
            while(iterator.hasNext()) {
                String code = iterator.next();
                if (Toolkits.custom(code, "^kf\\d+")) {
                    customerCodeList.add(code);
                    iterator.remove();
                }
            }
            
            if (!customerCodeList.isEmpty()) {
                List<CustomerUserPO> customerPOList = customerUserService.listUser(customerCodeList);
                List<Map<String, Object>> customerReturnDataList = new ArrayList<>();
                for (CustomerUserPO customerPO : customerPOList) {
                    Map<String, Object> customerReturnData = new HashMap<>();
                    customerReturnData.put(HuanXin.USERNAME, customerPO.getUserCode());
                    customerReturnData.put(Customer.PHOTO, customerPO.getPhoto());
                    customerReturnData.put(Customer.NAME, customerPO.getName());
                    customerReturnData.put(Order.HOUR_REMAINING, -1);
                    customerReturnData.put(Serve.CODE, "0");  //客服用0标识
                    customerReturnDataList.add(customerReturnData);
                }
                returnDatas.addAll(customerReturnDataList);
            }
            
            if (names.size() > 0) {
                List<EmployeeDTO> serveUserList = employeeService.listEmployee(names);
                // 查询用户是否为vip用户，如果是vip用户，app可能会将vip服务中的健康咨询环信账号一起发过来
                VipUserPO vip = vipUserService.getUserVip1(userId);
                List<ServeUserVO> vipServeUserList = new ArrayList<>();
                if (vip != null) {
                    vipServeUserList = consultService.listOrgConsultServe(VipId.CONSULT_SERVER_ORG_ID);
                }
                for (EmployeeDTO serveUser : serveUserList) {
                    Map<String, Object> consult = new HashMap<>();
                    consult.put(Serve.CODE, ProjectType.PROJECT_CONSULT.getValue());
                    consult.put(HuanXin.USERNAME, serveUser.getUserCode());
                    consult.put(OrgUser.PHOTO, serveUser.getPhoto());
                    consult.put(OrgUser.NAME, serveUser.getRealName());
                    OrderDTO order = orderService.getConsultOrder(userId, serveUser.getId());
                    Integer orderId = null;
                    long hourRemaining = 0;
                    // 这里如果找不到订单信息，说明该服务可能已经过期，直接返回0作为剩余时间数
                    if (order != null) {
                        orderId = order.getId();
                        // 计算剩余小时
                        Date end = order.getEndDate();
                        hourRemaining = DateTimeUtilT.calculateHourInterval(new Date(), end);
                    } else {
                        // 查询vip慢病咨询服务的用户信息是否相同，如果环信账号出现在这里就默认剩余时间为vip剩余时间
                        for (ServeUserVO s : vipServeUserList) {
                            if (s.getUserCode().equals(serveUser.getUserCode())) {
                                hourRemaining = DateTimeUtilT.calculateHourInterval(new Date(), vip.getEndTime());
                            }
                        }
                    }
                    
                    consult.put(Order.HOUR_REMAINING, hourRemaining);
                    consult.put(Order.ID, orderId);
                    consult.put(OrgUser.ID, serveUser.getId());
                    
                    returnDatas.add(consult);
                }
            }
        }
        if (huanxinGroupUserNames.size() > 0) {
            huanxinGroupUserNames = ListUtil.removeRepeatElement(huanxinGroupUserNames, String.class);

            List<Map<String, Object>> groups = new ArrayList<>();
            List<LessonPO> lessonList = lessonService.findLessonByHuanxinId(huanxinGroupUserNames);
            for (LessonPO lesson : lessonList) {
                Map<String, Object> group = new HashMap<>();
                /** 课堂默认code为02 */
                group.put(Serve.CODE, ProjectType.PROJECT_LESSON.getValue());
                group.put(HuanXin.USERNAME, lesson.getHuanxinId());
                group.put(OrgServeGroup.PHTOTO, lesson.getImage());
                group.put(OrgServeGroup.ID, lesson.getId());
                group.put(OrgServeGroup.NAME, Toolkits.projectTilte(lesson.getName()));
                groups.add(group);
            }
            returnDatas.addAll(groups);
        }
        if (returnDatas.size() > 0) {
            /** 把查询的环信ID合并到一起，标识未知账号 */
            names.addAll(huanxinGroupUserNames);
            returnDatas = setUnknowAccountForNotExist(names, returnDatas);
        }
        return AppNormalService.success(returnDatas);
    }
    
    /**
     * 将未知的账号设置提示“未知账号”
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月27日 下午1:31:33
     *
     * @param huanxinUserNames
     * @param users
     * @return
     */
    private List<Map<String, Object>> setUnknowAccountForNotExist(List<String> huanxinUserNames,
            List<Map<String, Object>> users) {
        List<String> name_tmp = new ArrayList<>();

        for (Map<String, Object> user : users) {
            name_tmp.add((String) user.get(HuanXin.USERNAME));
        }

        for (String name : huanxinUserNames) {
            if (!name_tmp.contains(name)) {
                Map<String, Object> user_blank = new HashMap<>();
                user_blank.put(HuanXin.USERNAME, name);
                user_blank.put(OrgUser.NAME, "未知账号");
                user_blank.put(OrgUser.PHOTO, "未知账号");
                user_blank.put(OrgUser.TYPE, "未知账号");
                user_blank.put(OrgUser.ID, "-1");

                users.add(user_blank);
            }
        }

        return users;
    }
    
    /**
     *  获取系统消息列表
     *  @author yuhang.weng 
     *	@DateTime 2017年6月27日 上午11:05:05
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "listSystemMessage", method = RequestMethod.POST)
    public JSONObject listSystemMessage(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int pageIndex = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        
        Paging<MessageDTO> pagination = messageService.listSystemMessage(userId, MEMBER, pageIndex, pageSize);
        List<MessageDTO> data = pagination.getData();
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        String afterOper= null;
        for (MessageDTO d : data) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put(Message.ID, d.getId());
            returnData.put(Message.TITLE, d.getTitle());
            returnData.put(Message.CONTENT, d.getContent());
            returnData.put(Message.READ, d.getRead());
            returnData.put(Message.DATE, d.getCreateDate());
            returnData.put(Message.OPEN_TYPE, d.getOpenType());
            returnData.put(Message.OPEN_TARGET, d.getOpenTarget());
            returnData.put(Message.OPEN_ATTACH, d.getOpenAttach());
            returnData.put("openTargetIOS", d.getOpenTargetIOS());
            returnData.put("openAttachIOS", d.getOpenAttachIOS());

            returnDataList.add(returnData);
        }
        
        return AppNormalService.success(returnDataList, true);
    }
    
    /**
     *  修改系统消息状态为已读
     *  @author yuhang.weng 
     *	@DateTime 2017年6月27日 上午11:05:16
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "modifySystemMessageToRead", method = RequestMethod.POST)
    public JSONObject modifySystemMessageToRead(@RequestParam String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        Integer id = mm_0.getInteger(Message.ID);
        messageService.updateSystemMessageToRead(id, userId, MEMBER);
        return AppNormalService.success();
    }
    
    /**
     *  删除一条系统消息
     *  @author yuhang.weng 
     *	@DateTime 2017年6月27日 上午11:05:28
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "deleteSystemMessage", method = RequestMethod.POST)
    public JSONObject deleteSystemMessage(@RequestParam String json) throws OperationException{
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        Integer id = mm_0.getInteger(Message.ID);
        messageService.deleteMessage(id, userId, MEMBER);
        return AppNormalService.success();
    }
    
    @RequestMapping(value = "getUnreadSystemMessageCount", method = RequestMethod.POST)
    public JSONObject getUnreadSystemMessageCount(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        int count = messageService.countSystemUnreadMessage(userId, MEMBER);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Message.UNREAD_MSG_COUNT, count);
        return AppNormalService.success(returnData);
    }
}
