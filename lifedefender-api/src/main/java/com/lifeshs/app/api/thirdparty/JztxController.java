package com.lifeshs.app.api.thirdparty;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.store.ReturnDataAgent;
import com.lifeshs.dto.manager.JztxDTO;
import com.lifeshs.dto.thirdparty.ClientDTO;
import com.lifeshs.service.tool.ITokenService;
import com.lifeshs.service1.transfer.TransferDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 家政天下服务接口
 * Created by dengfeng on 2018/1/26 0026.
 */
@RestController
@RequestMapping("thirdparty/jztx")
public class JztxController {

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private TransferDeliveryService transferDeliveryService;

    /**
     * 家政天下派送家政员
     * @param submitDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "designateHousekeeper", method = RequestMethod.POST)
    public @ResponseBody JSONObject designateHousekeeper(ClientDTO submitDTO, JztxDTO jztxDTO) throws Exception {
       if (jztxDTO.getOrderNO() =="" && jztxDTO.getOrderNO() == null){
           String message = "订单号为空！！";
           return ReturnDataAgent.success(message);
       }
        String bySaveJztx = transferDeliveryService.findBySaveJztx(jztxDTO);
       if (bySaveJztx == null){
           return ReturnDataAgent.error("接口不存在或者accesstoken错误");
       }
        return ReturnDataAgent.success(bySaveJztx);
    }

    /**
     * 家政天下重新派送家政员
     * @param submitDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "reDesignateHousekeeper", method = RequestMethod.POST)
    public JSONObject reDesignateHousekeeper(ClientDTO submitDTO, String orderNo) throws Exception {

        return ReturnDataAgent.success();
    }
}
