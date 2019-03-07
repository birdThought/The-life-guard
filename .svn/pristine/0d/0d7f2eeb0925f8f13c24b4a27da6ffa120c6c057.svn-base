package com.lifeshs.customer.controller.push;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lifeshs.thirdservice.UMengPushService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.common.PushMsgType;
import com.lifeshs.common.constants.common.PushSendType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.component.umeng.util.UMengOpenTypeEnum;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.dao1.user.UserRecordDao;
import com.lifeshs.po.push.PushMessagePO;
import com.lifeshs.po.push.UserDeviceTokenPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.push.PushMessageService;
import com.lifeshs.service1.record.UserRecordService;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.utils.SMSCommand;
import com.lifeshs.vo.StatisticsVO;


/**
 * @author Administrator
 * @create 2018-01-03
 * 15:37
 * @desc
 */

@RestController("customerPushController")
@RequestMapping(value = "/push")
public class customerPushController extends BaseController {

    private static final Logger logger = Logger.getLogger(customerPushController.class);
    
    @Autowired
    UMengPushService pushService;

    @Autowired
    SMSService smsService;
    
    @Autowired
    private UserRecordDao userRecordDao;

    @RequestMapping(value = "/page")
    public ModelAndView  push(){
        ModelAndView modelAndView = new ModelAndView("platform/push/push-manage");
        return modelAndView;
    }
    
    /**
     * 
     *  发送app推送消息
     *  @author NaN
     *  @DateTime 2018年5月17日 上午10:49:00
     *
     *  @param ids 用户id数组
     *  @param params 服务师id
     *  @param map 页面检索及文本参数
     *  @return
     */
    @RequestMapping(value = "/app", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson pushStoreMessage(
            @RequestParam(value = "ids[]") Integer[] ids,
            @RequestParam(value = "params") String[] params,
            @RequestParam Map<String,String> map) {
       
        AjaxJson ajaxJson = new AjaxJson();
        List<UserDeviceTokenPO> list = null;
        
        //参数值获取
        int resultNum = 0;
        Integer openType = 0;
        String title = "消息推送";
        String checkAll = map.get("checkAll");
        String openTargetOrUrl = map.get("paramUrl").toString();
        String content = map.get("content").toString();

        if(StringUtils.isNotBlank(map.get("openType").toString())){
            openType = Integer.parseInt(map.get("openType"));
        }
        if(StringUtils.isNotBlank(map.get("title"))){
            title = map.get("title").toString();
        }
        UMengOpenTypeEnum openTypeEnum = UMengOpenTypeEnum.parseOf(openType);
        
        //全选
        if("true".equals(checkAll)){
            Integer weight = null; //体重暂时不做。
            String province = null;
            if(StringUtils.isNotBlank(map.get("province").toString())){
                province = map.get("province").toString();
            }
            
            String startAge = null;
            if(StringUtils.isNotBlank(map.get("startAge").toString())){
                startAge = map.get("startAge").toString();
            }
            
            String endAge = null;
            if(StringUtils.isNotBlank(map.get("endAge").toString())){
                endAge = map.get("endAge").toString();
            }
            
            Integer label = null;
            if(StringUtils.isNotBlank(map.get("label").toString())){
                label = Integer.parseInt(map.get("label").toString());
            }
            
            Integer users = null;
            if(StringUtils.isNotBlank(map.get("users").toString())){
                users = Integer.parseInt(map.get("users").toString());
            }
            
            Integer gender = null;
            if(StringUtils.isNotBlank(map.get("gender").toString())){
                gender = Integer.parseInt(map.get("gender").toString());
            }
                
            String mobile = null;
            if(StringUtils.isNotBlank(map.get("mobile").toString())){
                mobile = map.get("mobile").toString();
            }
            
            //获取所有用户
            list = userRecordDao.findUserDeviceTokenList(province, users, gender, startAge, endAge, weight, label, mobile);
            if(list.size() < 1){
                ajaxJson.setMsg("未查找到用户！");
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
            
            pushService.manualPushBatch(list, title, content , openTypeEnum, openTargetOrUrl, params);
            resultNum = list.size();
        }else{
            pushService.pushStoreMessage(ids,title, getLoginUser().getId(), content, openTypeEnum, openTargetOrUrl, params);
            resultNum = ids.length;
        }
        
        ajaxJson.setObj(resultNum);
        return ajaxJson;
    }

    /**
     * 机构发送短信
     * @param ids
     * @return
     */
    @RequestMapping(value = "/sms", method = RequestMethod.POST)
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

        SMSCommand smsCommand = SMSCommand.ORG_PUSH;

        for (int i = 0; i < mobiles.length; i++) {
            String contentTemp = content.replace("*", names[i]);

            try {
                smsService.send(user.getId(), VcodeTerminalType.CUSTOMER,
                        mobiles[i], smsCommand, false, contentTemp);
            } catch (OperationException e) {
                logger.error(e.getMessage());
                ajaxJson.setMsg("发送客服短信失败");

                return ajaxJson;
            } catch (SMSException e) {
                logger.error(e.getMessage());
                ajaxJson.setMsg("发送客服短信失败");

                return ajaxJson;
            }
        }
        String openTarget = null;
        String[] params = null;
        pushService.pushStoreMessage(ids,"消息推送", getLoginUser().getId(), content, UMengOpenTypeEnum.TEXT, openTarget, params);

        ajaxJson.setSuccess(true);
        ajaxJson.setMsg("发送成功");
        return ajaxJson;
    }
}
