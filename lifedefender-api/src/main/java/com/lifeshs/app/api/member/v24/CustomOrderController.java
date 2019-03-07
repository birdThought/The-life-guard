package com.lifeshs.app.api.member.v24;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.CustomOrder;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.CustomOrderPo;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.member.address.AddressDTO;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.order.CustomOrderService;
import com.lifeshs.utils.NumberUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14.
 */
@RestController(value = "v24CustomOrderController")
@RequestMapping(value = "app/custom/v24")
public class CustomOrderController {

    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;

    /**
     *   输入确认订单
     * @param json
     * @return 返回待付款
     */
    @RequestMapping(value = "confirmOrder",method = RequestMethod.POST)
    public JSONObject confirmOrder(@RequestParam String json){
        AppJSON appJSON = AppNormalService.parseAppJSON(json);//转换对象
        int userId = appJSON.getAopData().getUser().getId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();//获取第一个对象实体
        Integer addressId = mm_0.getInteger("addressId"); //获取输入地址
        String orderNumber = mm_0.getString(CustomOrder.NUMBER);
            CustomOrderPo cp = null;
            try {
                cp = customOrderService.confirmPrivateOrder(userId,addressId,orderNumber);
            } catch (OperationException e) {
                e.printStackTrace();
            }
            Integer productPrice = cp.getProductPrice();
            double price = NumberUtils.changeF2Y(String.valueOf(productPrice));
            Map<String,Object> returnData = new HashMap<>();
            returnData.put(CustomOrder.NUMBER,orderNumber);
            returnData.put(CustomOrder.STATUS,cp.getStatus());
            returnData.put(CustomOrder.PRODUCT_PRICE,price);
            return AppNormalService.success(returnData);

    }

    /**
     * 用户查看订单
     * @param json
     * @return
     */
    @RequestMapping(value = "seeOrder",method = RequestMethod.POST)
    public JSONObject seeOrder(@RequestParam String json){
        AppJSON appJSON = AppNormalService.parseAppJSON(json);//转换对象
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();//获取第一个对象实体
        String orderNumber = mm_0.getString(CustomOrder.NUMBER);
        CustomOrderPo cp = customOrderService.getOrderDetails(orderNumber);
        AddressDTO byAdddress =null;
        if (cp.getUserId() != null || cp.getAddressId() != null) {   //为空就是未进行地址输入的订单
            Integer userId = cp.getUserId();
            Integer addressId = cp.getAddressId();
             byAdddress = customOrderService.findByAdddress(userId, addressId);
        }
        Integer productPrice = cp.getProductPrice();
        double price = NumberUtils.changeF2Y(String.valueOf(productPrice));
        Map<String,Object> returnData = new HashMap<>();
        returnData.put(CustomOrder.PRODUCT_NAME,cp.getProductName());
        returnData.put(CustomOrder.PRODUCT_SPEC,cp.getProductSpec());
        returnData.put(CustomOrder.PRODUCT_PRICE,price);
        returnData.put(CustomOrder.STATUS, cp.getStatus());
        if (byAdddress !=null) {
            returnData.put(CustomOrder.ADDRESS, byAdddress.getAddress());
            returnData.put(CustomOrder.USER_ADDRESS, byAdddress.getStreet());
            returnData.put("addressId",cp.getAddressId());
            returnData.put(CustomOrder.RECEIVER_NAME, byAdddress.getReceiverName());
            returnData.put(CustomOrder.RECEIVER_MOBILE, byAdddress.getContactNumber());
            return AppNormalService.success(returnData,true);
        }
        return AppNormalService.success(returnData,true);
    }
}
