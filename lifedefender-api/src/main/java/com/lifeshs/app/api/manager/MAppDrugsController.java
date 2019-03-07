package com.lifeshs.app.api.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.store.ReturnDataAgent;
import com.lifeshs.common.constants.common.Constant;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.dto.manager.drugs.DrugsClassNode;
import com.lifeshs.dto.manager.order.GetDrugsOrderData;
import com.lifeshs.po.drugs.DrugsPO;
import com.lifeshs.po.drugs.DrugsTypePO;
import com.lifeshs.po.drugs.OrderLogisticsPO;
import com.lifeshs.po.drugs.OrderPO;
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
@RequestMapping("mapp/drugs")

public class MAppDrugsController {

    static final Logger logger = Logger.getLogger(MAppDrugsController.class);

    @Autowired
    private DrugsService drugsService;

    /**
     * 
     *  查找药品类别
     *  @author liaoguo
     *  @DateTime 2018年6月25日 下午12:02:47
     *
     *  @return
     */
    @RequestMapping(value = "findDrusTypeList", method = RequestMethod.POST)
    public @ResponseBody JSONObject findDrusTypeList(SubmitDTO sumbitDTO){
        
        List<DrugsTypePO> drugsList = drugsService.findDrusType();

        List<DrugsClassNode> list = new ArrayList<DrugsClassNode>();
        for(int i = 0;i < drugsList.size(); i++){
            DrugsTypePO drugs = drugsList.get(i);
            DrugsClassNode level3Node = new DrugsClassNode(drugs.getThirdClassName());
            if(list.size() == 0 || !drugs.getFirstClassName().equals(list.get(list.size()-1).getName())) {
                DrugsClassNode level1Node = new DrugsClassNode(drugs.getFirstClassName());
                list.add(level1Node);
            }
            if(list.get(list.size()-1).getChildList().size() == 0 || !drugs.getSecondClassName().equals(list.get(list.size()-1).getChildList().get(list.get(list.size()-1).getChildList().size()-1).getName())){
                DrugsClassNode level2Node = new DrugsClassNode(drugs.getSecondClassName());
                list.get(list.size()-1).getChildList().add(level2Node);
            }
            list.get(list.size()-1).getChildList().get(list.get(list.size()-1).getChildList().size() -1).getChildList().add(level3Node);
        }
        
        JSONObject object = ReturnDataAgent.success(list);
        System.out.println("DDDD:"+object.toString());
        
        return ReturnDataAgent.success(list);
    }
    
    
    /**
     * 
     * 根据名称查找药品
     * 
     * @author liaoguo
     * @DateTime 2018年6月11日 下午1:49:35
     *
     * @return
     */
    @RequestMapping(value = "findDrusListByName", method = RequestMethod.POST)
    public @ResponseBody JSONObject findDrusListByName(SubmitDTO sumbitDTO, GetDrugsOrderData orderData) {
        int pageIndex = orderData.getPageIndex();
        int pageSize = orderData.getPageSize();
        String firstClassName = orderData.getFirstClassName();
        String secondClassName = orderData.getSecondClassName();
        String productName = orderData.getProductName();

        List<DrugsPO> drugsList = drugsService.findDrusListByName(productName, firstClassName, secondClassName, (pageIndex - 1) * pageSize, pageSize);
        return ReturnDataAgent.success(drugsList);
    }

    /**
     * 
     * 增加本地订单(未支付)
     * 
     * @author liaoguo
     * @DateTime 2018年6月13日 下午4:29:36
     *
     * @param sumbitDTO
     * @param orderPO
     * @return
     */
    @RequestMapping(value = "addLocalOrder", method = RequestMethod.POST)
    public @ResponseBody JSONObject addLocalOrder(SubmitDTO sumbitDTO, OrderPO orderPO) {
//        TOrgUser user = sumbitDTO.getUser();

        orderPO.setPhysCode(sumbitDTO.getUser().getId()+"");
        int result = drugsService.addLocalOrder(orderPO.getUserId(), orderPO);
        String msg = "Fail";
        HashMap<Object, Object> map = new HashMap<>();
        if(result > 0){
            msg = "Success";
        }
        map.put("orderNo", orderPO.getOrderNo());
        map.put("resultMsg", msg);
        return ReturnDataAgent.success(map);
    }

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
    public @ResponseBody JSONObject findOrderList(SubmitDTO sumbitDTO, GetDrugsOrderData orderData) {

        // 获取本地订单号
        int pageIndex = orderData.getPageIndex();
        int pageSize = orderData.getPageSize();

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
   public @ResponseBody JSONObject findOrderInfo(SubmitDTO sumbitDTO, String orderNo) {

    // 获取本地订单信息
       List<OrderPO> orderList = drugsService.getLocalOrderByParam(orderNo);
       if(orderList.size() > 0){
           String externalOrderNo = orderList.get(0).getExternalOrderNo();
           if(StringUtils.isNotBlank(externalOrderNo)){
               String method = Constant.JIANKE_ORDER_GET;
               List<OrderLogisticsPO> list = drugsService.findOrder(orderList, method);
               
               System.out.println("1@@@@:"+ReturnDataAgent.success(list));
               return ReturnDataAgent.success("", list); 
           }
       }

       return ReturnDataAgent.success("", orderList);
   }
    
}
