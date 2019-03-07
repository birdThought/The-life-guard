package com.lifeshs.app.api.member.v24;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.store.ReturnDataAgent;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.common.Constant;
import com.lifeshs.po.drugs.OrderLogisticsPO;
import com.lifeshs.po.drugs.OrderPO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.thirdservice.DrugsService;

/**
 * 
 * 药品订单管理接口
 * 
 * @author liaoguo
 * @version 1.0
 * @DateTime 2018年6月11日 下午1:48:00
 */
@RestController
@RequestMapping("app/drugs")

public class DrugsController {

    static final Logger logger = Logger.getLogger(DrugsController.class);

    @Autowired
    private DrugsService drugsService;

    /**
     *
     * 用户批量查询订单
     * 
     * @author liaoguo
     * @DateTime 2018年6月11日 下午1:49:56
     *
     * @return
     */
    @RequestMapping(value = "findOrderList", method = RequestMethod.POST)
    public @ResponseBody JSONObject findOrderList(@RequestParam String json) {

        // 获取本地订单号
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int pageIndex = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);

        // 获取本地订单信息
        String method = Constant.JIANKE_ORDER_GETBATCHLIST;
        List<OrderLogisticsPO> orderList = drugsService.findOrderList(method, pageIndex, pageSize);

        return ReturnDataAgent.success(orderList);
    }

    /**
    *
    * 订单单条查询
    * 
    * @author liaoguo
    * @DateTime 2018年6月11日 下午1:49:35
    *
    * @return
    */
   @RequestMapping(value = "findOrderInfo", method = RequestMethod.POST)
   public @ResponseBody JSONObject findOrderInfo(@RequestParam String json) {
       
       // 获取本地订单号
       AppJSON appJSON = AppNormalService.parseAppJSON(json);
       JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
       String orderNo = mm_0.getString("orderNo");

       // 获取本地订单信息
       List<OrderPO> orderList = drugsService.getLocalOrderByParam(orderNo);
       if(orderList.size() > 0){
           String externalOrderNo = orderList.get(0).getExternalOrderNo();
           if(StringUtils.isNotBlank(externalOrderNo)){
               String method = Constant.JIANKE_ORDER_GET;
               List<OrderLogisticsPO> list = drugsService.findOrder(orderList, method);
               
               System.out.println("1@@@@:"+ReturnDataAgent.success("",list));
               return ReturnDataAgent.success("", list); 
           }
       }
       
       System.out.println("2@@@:"+ReturnDataAgent.success("", orderList));
       return ReturnDataAgent.success("", orderList);
   }
   
   
   
    
}
