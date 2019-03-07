package com.lifeshs.customer.controller.Callback;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.Callback;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.transfer.TransferCleaningService;
import com.lifeshs.service1.transfer.TransferDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @create 2018-01-18
 * 16:50         TODO 废弃
 * @desc
 */

@RestController(value = "familyCallbackController")
@RequestMapping(value = "Callback")
public class FamilyCallbackController {

    @Autowired
    private TransferDeliveryService transferDeliveryService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody AjaxJson addCallback(@RequestParam(value = "appid",required = true) String appId,@RequestParam(value = "accesstoken",required = true) String accesstoken,
                                              @RequestParam(value = "OrderNO")String orderNO, @RequestParam(value = "SFZ") String sfz,
                                              @RequestParam(value = "Name")String name,@RequestParam(value = "JzyInfo")String Info){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            Map<String, Object> map =transferDeliveryService.saveCallback(appId, accesstoken, orderNO, sfz, name, Info);
            ajaxJson.setAttributes(map);
            return ajaxJson;
        } catch (OperationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
