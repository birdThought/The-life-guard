package com.lifeshs.controller.org.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.lifeshs.common.constants.common.MessageType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.component.umeng.util.CallBackDTO;
import com.lifeshs.component.umeng.util.UMengOpenTypeEnum;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.po.message.ImMessagePO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.message.MessageDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.thirdservice.HuanXinService;
import com.lifeshs.thirdservice.RedisService;
import com.lifeshs.thirdservice.UMengPushService;
import com.lifeshs.utils.JSONHelper;

@RestController
@RequestMapping(value = "/message")
public class MessageController extends BaseController {

    private static final Logger logger = Logger.getLogger(MessageController.class);

    @Resource(name = "v2MessageService")
    private MessageService messageService;
    
    private final Integer PAGE_SIZE = 8;
    
    private final UserType ORG_USER = UserType.orgUser;
    
    @Autowired
    private HuanXinService huanXinService;

    @Autowired
    OrderService orderService;

    @Autowired
    UMengPushService uMengPushService;

    @Autowired
    RedisService redisService;

    /**
     * @Description: 机构-消息中心
     * @author: wenxian.cai
     * @create: 2017/5/4 11:38
     */
    /*@RequestMapping(value = "/org")
    public ModelAndView orgMessage() {
        ModelAndView modelAndView = new ModelAndView("platform/org/message/message");
        
        String userCode = getLoginUser().getUserCode();
        LoginUser user = getLoginUser();
        Integer userId = user.getId();
//        int offLineCount = huanXinService.offlineMsgCount(userCode);
        int offLineCount = 1;
        modelAndView.addObject("offLineCount", offLineCount);
        
        PaginationDTO<MessageDTO> p = messageService.listSystemMessage(userId, ORG_USER, 1, PAGE_SIZE).getPagination();
        Map<String, Object> systemMsg = new HashMap<>();
        systemMsg.put("data", p.getData());
        systemMsg.put("total", p.getTotalSize());
        systemMsg.put("totalPage", p.getTotalPage());
        systemMsg.put("curPage", p.getNowPage());
        modelAndView.addObject("systemMsg", JSON.toJSON(systemMsg));
        modelAndView.addObject("orgType", user.getType());
        modelAndView.addObject("userType", user.getUserType());
        Map orgUser = new HashMap();
        orgUser.put("uerCode", user.getUserCode());
        orgUser.put("uerName", user.getUserName());
        orgUser.put("head", user.getHead());
        modelAndView.addObject("orgUser", JSON.toJSON(orgUser));
        return  modelAndView;
    }*/
    
    @RequestMapping("/service/{page}")
    public AjaxJson serviceMsg(@PathVariable Integer page) {
        AjaxJson result = new AjaxJson();
        Integer userId = getLoginUser().getId();
        
        PaginationDTO<MessageDTO> p = messageService.listSystemMessage(userId, ORG_USER, page, PAGE_SIZE).getPagination();
        Map<String, Object> systemMsg = new HashMap<>();
        systemMsg.put("data", p.getData());
        systemMsg.put("total", p.getTotalSize());
        systemMsg.put("totalPage", p.getTotalPage());
        systemMsg.put("curPage", p.getNowPage());
        result.setObj(JSON.toJSON(systemMsg));
        return result;
    }
    
    @RequestMapping(value = {"/services", "/service/{id}"}, method = RequestMethod.DELETE)
    public AjaxJson serviceMsgDelete(@PathVariable(required = false) Integer id, @RequestBody(required = false) List<Integer> ids) throws OperationException {
        AjaxJson result = new AjaxJson();
        Integer userId = getLoginUser().getId();
        
        if (id != null) {
            messageService.deleteMessage(id, userId, ORG_USER);
        }
        if (ids != null) {
            messageService.deleteMessage(ids, userId, ORG_USER);
        }
        
        PaginationDTO<MessageDTO> p = messageService.listSystemMessage(userId, ORG_USER, 1, PAGE_SIZE).getPagination();
        Map<String, Object> systemMsg = new HashMap<>();
        systemMsg.put("data", p.getData());
        systemMsg.put("total", p.getTotalSize());
        systemMsg.put("totalPage", p.getTotalPage());
        systemMsg.put("curPage", p.getNowPage());
        
        result.setObj(systemMsg);
        result.setSuccess(true);
        
        return result;
    }


    @RequestMapping(value = "")
    public ModelAndView message() {
        ModelAndView modelAndView = new ModelAndView("platform/org/message/store-message");
        LoginUser user = getLoginUser();
        Integer userId = user.getId();
        PaginationDTO<MessageDTO> p = messageService.listSystemMessage(userId, ORG_USER, 1, PAGE_SIZE).getPagination();
        Map<String, Object> systemMsg = new HashMap<>();
        systemMsg.put("data", p.getData());
        systemMsg.put("total", p.getTotalSize());
        systemMsg.put("totalPage", p.getTotalPage());
        systemMsg.put("curPage", p.getNowPage());
        modelAndView.addObject("systemMsg", JSON.toJSON(systemMsg));
        /*PaginationDTO<MessageDTO> storePushMsg = messageService.listStorePushMessage(userId, ORG_USER, 1, PAGE_SIZE).getPagination();
        modelAndView.addObject("storePushMsg", JSON.toJSON(storePushMsg));*/
        int sysCount = 0;
        int orderCount = 0;
        sysCount = messageService.countSystemUnreadMessage(userId, ORG_USER);
        orderCount = messageService.countOrderUnreadMessage(userId, ORG_USER);
        modelAndView.addObject("sysCount", sysCount);
        modelAndView.addObject("orderCount", orderCount);
        modelAndView.addObject("orgType", user.getType());
        modelAndView.addObject("userType", user.getUserType());
        Map orgUser = new HashMap();
        orgUser.put("userCode", user.getUserCode());
        orgUser.put("userName", user.getUserName());
        orgUser.put("realName", user.getRealName());
        orgUser.put("head", user.getHead());
        modelAndView.addObject("orgUser", JSON.toJSON(orgUser));
        return modelAndView;
    }
    
    @RequestMapping(value = "/read/{id}", method = RequestMethod.PUT)
    public AjaxJson read(@PathVariable Integer id) throws OperationException {
        AjaxJson result = new AjaxJson();
        result.setSuccess(false);
        
        int userId = getLoginUser().getId();
        
        messageService.updateSystemMessageToRead(id, userId, ORG_USER);
        
        result.setSuccess(true);
        result.setMsg("更新成功");
        return result;
    }
    
    @RequestMapping(value = "/unreadCount")
    public AjaxJson getUnreadMsgCount() {
        AjaxJson result = new AjaxJson();
        result.setSuccess(true);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("count", 0);
        result.setAttributes(attributes);
        return result;
    }

    /**
     * @Description: 门店添加服务提醒消息
     * @Author: wenxian.cai
     * @Date: 2017/7/12 17:42
     */
    @RequestMapping(value = "/add-remind", method = RequestMethod.POST)
    public AjaxJson addRemind(@RequestParam("content") String content, @RequestParam("receiverId") int receiverId) throws OperationException {
        AjaxJson ajaxJson = new AjaxJson();
        String title = "门店提醒";
        //todo 暂时将门店提醒放在系统消息里
//        messageService.saveMessage(receiverId, UserType.orgUser.getValue(), title, content, MessageType.SYSTEM.value());
        //messageService.saveMessage(receiverId, UserType.orgUser.getValue(), title, content, null, null, null, MessageType.SYSTEM.value(), UMengOpenTypeEnum.TEXT.value());
        return ajaxJson;
    }


    /**
     * @Description: 获取退款信息
     * @Author: wenxian.cai
     * @Date: 2017/7/13 11:47
     */
    @RequestMapping(value = "/list-order-message/{page}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listOrderMessage(@PathVariable("page") int page) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        if (user.getUserType().equals(1)) {
            ajaxJson.setMsg("无权限执行操作");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        if (page < 1) {
            page = 1;
        }
        PaginationDTO<MessageDTO> p = messageService.listOrderMessage(user.getId(), ORG_USER, page, PAGE_SIZE).getPagination();
        ajaxJson.setObj(p);
        return ajaxJson;
    }

    @RequestMapping(value = "/push-message", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson pushMessage() {
        AjaxJson ajaxJson = new AjaxJson();
//        CallBackDTO c;
//        c = uMengPushService.measureRemindPush(0,true, "4.5", "f99d60251a05fbcdcb3107107ddda4fd58546aaf6f06a81f98177cb78c686c39", "ticker", "title", "text", "description",
//                UserType.member.getValue(),MessageType.SYSTEM.value());
        //uMengPushService.measureRemindPush(true, 0, "f99d60251a05fbcdcb3107107ddda4fd58546aaf6f06a81f98177cb78c686c39", "title", "text");
        return ajaxJson;
    }

    /**
     * 存储im聊天信息（单条）
     * author: wenxian.cai
     * date: 2017/9/21 15:19
     */
    @RequestMapping(value = "/save-im-message", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson saveImMessage(@RequestBody ImMessagePO message) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        redisService.set("lifedefender.im." + message.getOwner(), message);
        System.out.println(JSONHelper.toBean(redisService.get("lifedefender.im." + message.getOwner()), ImMessagePO.class));
//        cacheService.saveKeyValue(CacheType.IM_MESSAGE_CACHE, user.getUserCode(), message);
        return ajaxJson;
    }

}
