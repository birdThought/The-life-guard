package com.lifeshs.app.api.store.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.store.ReturnDataAgent;
import com.lifeshs.common.constants.app.CustomOrder;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dto.manager.CustomDTO;
import com.lifeshs.dto.manager.CustomData;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.po.CustomOrderPo;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.address.AddressDTO;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.order.CustomOrderService;
import com.lifeshs.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理的
 */
@RestController
@RequestMapping("mapp/custom")
public class CustomOrderController {

    @Autowired
    private CustomOrderService customOrderService;

    @Autowired
    IMemberService memberService;

    /**
     *  创建订单  管理的
     * @param
     * @return  返回未支付
     * @throws OperationException
     */
    @RequestMapping(value = "addPrivateOrder", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject addPrivateOrder(SubmitDTO submitDTO, CustomDTO customDTO) throws OperationException {
        String productName = customDTO.getProductName();
        String productSpec = customDTO.getProductSpec();
        String productPrice = customDTO.getProductPrice();
        String physCode = customDTO.getPhysCode();
        String userCode = customDTO.getUserCode();
        CustomOrderPo cp = customOrderService.addPrivateOrder(physCode,userCode,productName,productSpec,productPrice);
        CustomData cd = new CustomData();
        cd.setOrderNumber(cp.getOrderNumber());
        cd.setStatus(cp.getStatus());
        return ReturnDataAgent.success(cd);
    }

    /**
     *  医师点击查看 管理的
     * @param
     * @return
     */
    @RequestMapping(value = "getPrivateOrderDoctor",method = RequestMethod.POST)
    public JSONObject getPrivateOrderDoctor(SubmitDTO submitDTO,CustomDTO customDTO){
        String orderNumber = customDTO.getOrderNumber();
        CustomOrderPo cp = customOrderService.getOrderDetails(orderNumber);
        AddressDTO  byAdddress = null;
        if (cp.getUserId() != null || cp.getAddressId() != null) {   //为空就是未进行地址输入的订单
            Integer userId = cp.getUserId();
            Integer addressId = cp.getAddressId();
            byAdddress = customOrderService.findByAdddress(userId, addressId);
        }
        Integer productPrice = cp.getProductPrice();
        double price = NumberUtils.changeF2Y(String.valueOf(productPrice));
        CustomData cd  = new CustomData();
        cd.setProductName(cp.getProductName());
        cd.setProductSpec(cp.getProductSpec());
        cd.setProductPrice(price);
        if (byAdddress != null) {
            cd.setAddress(byAdddress.getAddress());
            cd.setReceiverName(byAdddress.getReceiverName());
            cd.setStreet(byAdddress.getStreet());
            cd.setContactNumber(byAdddress.getContactNumber());
            return ReturnDataAgent.success(cd);
        }
        return ReturnDataAgent.success(cd);
    }


    private JSONObject getJsonObject(SubmitDTO submitDTO) {
        JSONObject submitJSON = JSON.parseObject(submitDTO.getJson());

        // 获取aopData中的userDTO
        String userId = submitJSON.getJSONObject("data").getString("userId");
        UserDTO user = memberService.getUser(Integer.parseInt(userId));

        // 开始装配JSON
        JSONObject json = new JSONObject();
        json.put("type","APP_A");
        json.put("ver", 1);
        JSONObject data = new JSONObject();
        data.put("userId",userId);
        data.put("msg", submitJSON.getJSONObject("data").getJSONArray("msg"));
        json.put("data", data);
        JSONObject aopData = new JSONObject();
        aopData.put(User.USER, user);
        json.put("aopData", aopData);

        System.out.println(json.toJSONString()); // for debug
        return json;
    }
}
