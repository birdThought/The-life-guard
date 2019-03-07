package com.lifeshs.app.api.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.MessageType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.component.umeng.util.UMengOpenTypeEnum;
import com.lifeshs.pojo.app.manager.MAppJSON;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.thirdservice.UMengPushService;

/**
 * 门店业务控制类
 * Created by dengfeng on 2017/6/21 0021.
 */
@RestController
@RequestMapping("mapp/store")
public class StoreController {
    @Autowired
    MessageService messageService;
    
    @Autowired
    UMengPushService uMengPushService;

    /**
     * 门店管理员提醒服务师
     * @param json
     * @return
     */
    @RequestMapping(value = "sendRemindToEmployee", method = RequestMethod.POST)
    public @ResponseBody JSONObject sendRemindToEmployee(@RequestParam String json) throws OperationException {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();
        int orderId = data.getIntValue("orderId");
        int employeeId = data.getIntValue("employeeId");
        String realName = data.getString("realName");
        String subject = data.getString("subject");
        uMengPushService.notifyEmployeeFinishOrder(orderId, employeeId, realName, subject);

        return ReturnDataAgent.success();
    }
}
//int receiverId, int receiverType, String title, String content, String openTarget,
//Map<String, Object> openAttach,String paramAttach, int openType,int msgType