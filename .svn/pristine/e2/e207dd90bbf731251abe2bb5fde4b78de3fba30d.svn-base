package com.lifeshs.controller.org.push;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.PushMsgType;
import com.lifeshs.common.constants.common.PushSendType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.component.umeng.util.UMengOpenTypeEnum;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.po.OrderStoreSmsPO;
import com.lifeshs.po.ProjectPO;
import com.lifeshs.po.data.DiseasesPO;
import com.lifeshs.po.push.PushMessagePO;
import com.lifeshs.po.push.UserDeviceTokenPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.member.UserSMSRemainDTO;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service1.data.DiseasesService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.push.PushDataService;
import com.lifeshs.service1.push.PushMessageService;
import com.lifeshs.service1.serve.ProjectService;
import com.lifeshs.service1.smsRemind.SmsRemindService;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.thirdservice.UMengPushService;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.SMSCommand;


@RestController("pushController")
@RequestMapping(value = "org/push")
public class pushController extends BaseController {
    private static final Logger logger = Logger.getLogger(pushController.class);
    @Autowired
    ProjectService projectService;
    @Autowired
    DiseasesService diseasesService;
    @Autowired
    PushMessageService pushMessageService;
    @Autowired
    SMSService smsService;
    @Autowired
    OrderService orderService;
    @Autowired
    SmsRemindService smsRemindService;
    final int PUSH_MESSAGE_PAGE_SIZE = 12;
    @Autowired
    private UMengPushService pushService;
    @Autowired
    PushDataService pushDataService;
    
    @Resource(name = "pushDataService")
    private PushDataService pDataService;
    
    @Autowired
    private com.lifeshs.dao1.order.IOrderDao orderDao1;
    
    /**
     * 推送管理
     * @return
     */
    @RequestMapping()
    public ModelAndView push() {
        ModelAndView modelAndView = new ModelAndView(
                "platform/org/push/push-manage");
        LoginUser loginUser = getLoginUser();
        List<ProjectPO> projectPOS = projectService.findProjectList(loginUser.getOrgId());
        List<DiseasesPO> diseasesPOS = diseasesService.findDiseasesList();

        modelAndView.addObject("project", JSON.toJSON(projectPOS));
        modelAndView.addObject("diseases", JSON.toJSON(diseasesPOS));
        modelAndView.addObject("orgType", loginUser.getType());

        return modelAndView;
    }

    /**
     * 发送app推送消息
     * @param ids
     * @return
     */
    @RequestMapping(value = "/push-store-message", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson pushStoreMessage(
            @RequestParam(value = "ids[]") Integer[] ids,
            @RequestParam(value = "params") String[] params,
            @RequestParam Map<String,String> map) {
        
        //处理信息数量
        int resultNum = 0;
        AjaxJson ajaxJson = new AjaxJson();
        String checkAll = map.get("checkAll");
        LoginUser user = getLoginUser();
        List<UserDeviceTokenPO> list = null;

        //参数处理
        String title = map.get("title").toString();
        String content = map.get("content").toString();
        String paramUrl = map.get("paramUrl").toString();
        Integer openType = Integer.parseInt(map.get("openType"));
        UMengOpenTypeEnum openTypeEnum = UMengOpenTypeEnum.parseOf(openType);
        
        //全选
        if("true".equals(checkAll)){
            
          //参数处理
            String projectCode = null;
            if(!"-1".equals(map.get("projectCode"))){
                projectCode = map.get("projectCode").toString();
            }
            
            Integer diseasesId = null;
            if(!"-1".equals(map.get("diseasesId"))){
                diseasesId = Integer.parseInt(map.get("diseasesId").toString());
            }
            
            Integer gender = null;
            if(!"-1".equals(map.get("gender"))){
                gender = Integer.parseInt(map.get("gender").toString());
            }
            
            String startAge = null;
            if(!"-1".equals(map.get("startAge"))){
                startAge = map.get("startAge").toString();
            }
            
            String endAge = null;
            if(!"-1".equals(map.get("endAge"))){
                endAge = map.get("endAge").toString();
            }
            
            String mobile = null;
            if(StringUtils.isNotBlank(map.get("mobile").toString())){
                mobile = map.get("mobile").toString();
            }
            list = pDataService.findUserDeviceTokenPOList(projectCode, diseasesId, gender, startAge, endAge, mobile, user.getOrgId());
//            list = orderDao1.findStatisticsDetailsList(projectCode, diseasesId, gender, startAge, endAge, mobile, user.getOrgId(), 0, 0);
            if(list.size()<1){
                ajaxJson.setMsg("未查找到用户！");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            
            pushService.manualPushBatch(list, title, content , openTypeEnum, paramUrl, params);
            resultNum = list.size();
        }else{
            pushService.manualPush(ids, title, content , openTypeEnum, paramUrl, params);
            resultNum = ids.length;
        }
        
        ajaxJson.setObj(resultNum);
        return ajaxJson;
    }
    
    
    public static void main(String[] args) {
        System.out.println(12/500);
        System.out.println(12%500);
        
        System.out.println("----------------");
        
        System.out.println(500/500);
        System.out.println(500%500);
        System.out.println("----------------");
        
        System.out.println(505/500);
        System.out.println(505%500);
    }

    /**
     * 获取门店推送消息列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/list-store-push-message/{page}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listStorePushMessage(@PathVariable("page")
    int page) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();

        try {
            Paging<PushMessagePO> paging = pushMessageService.listStorePushMessage(UserType.orgUser,
                    user.getId(), null, page, PUSH_MESSAGE_PAGE_SIZE);
            ajaxJson.setObj(paging.getPagination());

            return ajaxJson;
        } catch (OperationException e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(e.getMessage());

            return ajaxJson;
        }
    }

    /**
     * 删除推送消息
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete-store-push-message", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson deleteStorePushMessage(
        @RequestParam(value = "ids[]", required = false)
    Integer[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();

        try {
            pushMessageService.batchDeletePushMessage(Arrays.asList(ids),
                user.getId());

            return ajaxJson;
        } catch (OperationException e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(e.getMessage());

            return ajaxJson;
        }
    }

    /**
     * 机构发送短信
     * @param ids
     * @return
     */
    @RequestMapping(value = "/send-store-sms-message", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson sendStoreSmsMessage(
        @RequestParam(value = "ids[]", required = false)
    Integer[] ids,
        @RequestParam(value = "mobiles[]", required = false)
    String[] mobiles,
        @RequestParam(value = "names[]", required = false)
    String[] names, @RequestParam(value = "content")
    String content) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(false);

        LoginUser user = getLoginUser();

        if ((mobiles == null) || (mobiles.length < 1)) {
            ajaxJson.setMsg("至少勾选一个用户!");

            return ajaxJson;
        }

        UserSMSRemainDTO dto = smsRemindService.getOrgSmsRemind(user.getOrgId());
        long remain = (dto == null) ? 0 : dto.getRemainNumber();

        if (remain < mobiles.length) {
            ajaxJson.setMsg("短信剩余条数不足，无法发送短信！");

            return ajaxJson;
        }

        SMSCommand smsCommand = SMSCommand.ORG_PUSH;

        //        String mobile = StringUtils.join(mobiles, ",");
        for (int i = 0; i < mobiles.length; i++) {
            String contentTemp = content.replace("*", names[i]);

            try {
                smsService.send(user.getId(), VcodeTerminalType.ORG_PLATFORM,
                    mobiles[i], smsCommand, false, contentTemp);
            } catch (OperationException e) {
                logger.error(e.getMessage());
                ajaxJson.setMsg("发送机构短信失败");

                return ajaxJson;
            } catch (SMSException e) {
                logger.error(e.getMessage());
                ajaxJson.setMsg("发送机构短信失败");

                return ajaxJson;
            }
        }

        try {
            smsRemindService.reduceOrgSmsRemind(user.getOrgId(), mobiles.length);
        } catch (OperationException e) {
            logger.error(e.getMessage());
            ajaxJson.setMsg("发送机构短信失败");

            return ajaxJson;
        }

        PushMessagePO po = new PushMessagePO();
        po.setReceiveId(StringUtils.join(ids, ";"));
        po.setTitle("短信推送");
        po.setUserName(names);
        po.setContent(content.replace("*", StringUtils.join(names, "，")));
        po.setMsgType(PushMsgType.store.getValue());
        po.setUserType(UserType.orgUser.getValue());
        po.setSendType(PushSendType.sms.getValue());
        po.setOpenType(UMengOpenTypeEnum.TEXT.value());
        po.setSendId(getLoginUser().getId());
        try {
            pushMessageService.addPushMessage(po);
        } catch (OperationException e) {
            e.printStackTrace();
        }
        
        ajaxJson.setSuccess(true);
        ajaxJson.setMsg("发送成功");

        return ajaxJson;
    }

    /**
     * 获取剩余短信条数
     * @return
     */
    @RequestMapping(value = "/get-store-sms-remain", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getStoreSmsRemain() {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        UserSMSRemainDTO dto = smsRemindService.getOrgSmsRemind(user.getOrgId());

        if (dto == null) {
            ajaxJson.setObj(0);

            return ajaxJson;
        }

        ajaxJson.setObj(dto.getRemainNumber());

        return ajaxJson;
    }

    /**
     * 创建短信充值订单
     * @param
     * @return
     */
    @RequestMapping(value = "/create-store-sms-order", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson createStoreSmsOrder(@RequestParam("charge")
    Double charge, @RequestParam("count")
    int count) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();

        if (charge != (count * 0.1)) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("请勿违规操作");

            return ajaxJson;
        }

        String orderNumber = AlipayService.createOrderNumber();
        String subject = "短信充值";
        String body = "充值" + charge + "元";

        OrderStoreSmsPO po = new OrderStoreSmsPO();
        po.setOrderNumber(orderNumber);
        po.setSubject(subject);
        po.setBody(body);
        po.setPrice(10);
        po.setNumber(count);
        po.setCharge(NumberUtils.changeY2F(charge + ""));
        po.setStatus(1);
        po.setOrgId(user.getOrgId());

        try {
            orderService.createOrderStoreSms(po);

            Map map = new HashMap();
            map.put("orderNumber", orderNumber);
            map.put("charge", charge);
            //            map.put("qrCode", "sssss");
            ajaxJson.setObj(map);

            return ajaxJson;
        } catch (OperationException op) {
            logger.error("创建短信充值订单异常:" + op.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 判断订单-机构短信充值订单状态是否已完成
     * @param orderNumber
     * @return
     */
    @RequestMapping(value = "/is-store-sms-order-complete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson isStoreSmsOrderComplete(
        @RequestParam("orderNumber")
    String orderNumber) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();

        try {
            OrderStatus status = orderService.getOrderStoreSmsStatus(orderNumber);

            if (status.getStatus().equals(4)) {
                ajaxJson.setObj(true);

                return ajaxJson;
            }
        } catch (OperationException op) {
            logger.error("判断订单-机构短信充值订单状态失败:" + op.getMessage());
        }

        ajaxJson.setObj(false);

        return ajaxJson;
    }
}
