package com.lifeshs.customer.controller.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.order.OrderWithVipPO;
import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service1.combo.ComboManageService;
import com.lifeshs.service1.order.IIncomeService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.order.vip.VipUserOrderService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.vo.customer.CustomerSharingDataVO;
import com.lifeshs.vo.order.OrderAgentAmountVO;

/**
 *  订单查看控制器
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2018年1月30日 上午11:13:16
 */
@RestController
@RequestMapping(value = "/order")
public class OrderListController extends BaseController {

    @Resource(name = "v2OrderService")
    private OrderService orderService;
    
    @Resource(name = "incomeService")
    private IIncomeService incomeService;
    
    @Resource(name = "vipUserOrderServiceImpl")
    private VipUserOrderService vipUserOrderService;
    
    @Resource(name = "comboManageService")
    private ComboManageService comboManageService;
    
    final static Integer PAGE_SIZE = 10;
    
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("/platform/ordermanager/order-list");
    }
    
    /**
     *  获取订单列表
     *  @author yuhang.weng 
     *  @DateTime 2018年1月30日 上午11:56:09
     *
     *  @param curPage 当前页码
     *  @return
     */
    @RequestMapping(value = "/data/list/{curPage}", method = RequestMethod.GET)
    public JSONObject listOrder(@PathVariable(value = "curPage") int curPage,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) String orgName,
            @RequestParam(required = false) String projectType,
            @RequestParam(required = false) Integer orderStatus,
            @RequestParam(required = false) String orderType) {
        OrderStatus status = null;
        if (orderStatus != null) {
            status = OrderStatus.findStatus(orderStatus);
        }
        CustomerSharingDataVO user = getUserSharingData();
        
        //总盈利
        OrderAgentAmountVO amount = orderService.findTotalMoney(user.getUserNo(), userName,  realName,  orgName, projectType,  status, orderType);
        if(amount.getTotal()==null){
            amount.setTotal(0);
        }
        if(amount.getIntroduceTotal()==null){
            amount.setIntroduceTotal(0);
        }
        if(amount.getServiceTotal()==null){
            amount.setServiceTotal(0);
        }
        
        //订单列表
        Paging<OrderWithVipPO> p = orderService.listOrder(user.getUserNo(), curPage, PAGE_SIZE, userName, realName, orgName, projectType, status, orderType);
        PaginationDTO<OrderWithVipPO> pagination = p.getPagination();
        
        JSONObject root = new JSONObject();
        root.put("success", true);
        root.put("flag", user.getUserNo().substring(0, 1));
        root.put("userNo", user.getUserNo());
        root.put("introduceTotal", NumberUtils.changeF2Y(String.valueOf(amount.getIntroduceTotal())));
        root.put("serviceTotal", NumberUtils.changeF2Y(String.valueOf(amount.getServiceTotal())));
        root.put("toalMoeny", NumberUtils.changeF2Y(String.valueOf(amount.getTotal())));
        root.put("moeny", NumberUtils.changeF2Y(String.valueOf(amount.getTotal()+amount.getIntroduceTotal()+amount.getServiceTotal())));
        root.put("data", enclosureOrderList(pagination.getData()));
        root.put("nowPage", pagination.getNowPage());
        root.put("totalPage", pagination.getTotalPage());
        root.put("totalSize", pagination.getTotalSize());
        return root;
    }
    
    @RequestMapping(value = "/data/findServeList", method = RequestMethod.POST)
    public JSONObject findServeList(@RequestParam(value = "orderType") String orderType){
        JSONObject root = new JSONObject();
        List<VipComboPO> comboList = null;
        if(StringUtils.isBlank(orderType)){
            return root;
        }
        
        if("Vip".equals(orderType)){
            comboList = comboManageService.findL1All();
        }
        if("Service".equals(orderType)){
            VipComboPO comboPo = new VipComboPO();
            comboPo.setId(0);
            comboPo.setL1("选择服务类型");
            comboList.add(comboPo);
            
            comboPo.setId(1);
            comboPo.setL1("健康咨询");
            comboList.add(comboPo);
            
            comboPo.setId(4);
            comboPo.setL1("健康课堂");
            comboList.add(comboPo);
            
            comboPo.setId(2);
            comboPo.setL1("到店服务");
            comboList.add(comboPo);
            
            comboPo.setId(3);
            comboPo.setL1("上门服务");
            comboList.add(comboPo);
        }

        root.put("success", true);
        root.put("comboList", comboList);
        return root;
        
    }
    
    
    @RequestMapping(value = "/data/testAgentIncome", method = RequestMethod.POST)
    public JSONObject testAgentIncome(){
        JSONObject json = new JSONObject();
        
        
        
        try {
            
//            String orderNumber = "20181106170131985097";
//            String type = "VIP";
//            AgetnIncomeVo vo = incomeService.orderIncome(orderNumber,123000, type);
//            orderNumber = "20181022120012089064";
//            type = "Service";
//            AgetnIncomeVo vo1 = incomeService.orderIncome(orderNumber,100, type);
            
            
            
            String outNo = "20181022120044954195"; //订单号
            String tradeNo = "100000000000"; //支付宝交易号
            String payerAccount = "10000001"; //买家账号
            String sellerAccount= "10000000"; //卖家账号
            double totalCount = 135; //支付总金额
            int payType= 1; //支付方式 1.支付宝   2微信
            String deviceType = "app";
            Integer couponsId = 0;
//            orderService.finishOrder(outNo, tradeNo, payerAccount, sellerAccount, totalCount, payType, deviceType, couponsId);
//            System.out.println("服务订单支付宝支付完成/././");
            
            
            
            outNo = "20181022123002851397"; //订单号
             tradeNo = "200000000000"; //支付宝交易号
             payerAccount = "10000001"; //买家账号
             sellerAccount= "10000000"; //卖家账号
             totalCount = 12300; //支付总金额
             payType= 2; //支付方式 1.支付宝   2微信
             deviceType = "app";
             couponsId = 0;
//            orderService.finishOrder(outNo, tradeNo, payerAccount, sellerAccount, totalCount, payType, deviceType, couponsId);
//            System.out.println("服务订单微信支付完成/././");
            
            
            
            
            

            
            String orderNumber = "20181015142939946996";
            String tradeNumber ="300000000000"; //支付端（支付宝/微信）交易号
            payerAccount = "vip123";//买家账号
            sellerAccount = "v123456";
            totalCount = 5800; //总支付金额
            deviceType = "app";
            
            vipUserOrderService.finishOrder(orderNumber, tradeNumber, payerAccount, sellerAccount, totalCount, PayTypeEnum.getPayTypeEnum(1), deviceType);
            
            System.out.println("vip套餐订单支付宝支付完成/././");
//            
//            
//            
//            orderNumber = "20180426154400718805";
//            tradeNumber ="300000000000"; //支付端（支付宝/微信）交易号
//            payerAccount = "vip123";//买家账号
//            sellerAccount = "v123456";
//            totalCount = 500; //总支付金额
//            deviceType = "app";
//            
//            vipUserOrderService.finishOrder(orderNumber, tradeNumber, payerAccount, sellerAccount, totalCount, PayTypeEnum.getPayTypeEnum(2), deviceType);
//            
//            System.out.println("vip套餐订单微信支付完成/././");
            
            
        } catch (OperationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        
        return json;
    }
    
    
    
    
    
    
    private List<Map<String, Object>> enclosureOrderList(List<OrderWithVipPO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        
        
        for (OrderWithVipPO data : dataList) {
            int charge = data.getCharge();
            int sysIncome = data.getSysIncome() == null ? 0 : data.getSysIncome();
            String profitShare = data.getProfitShare() == null ? "-" : data.getProfitShare().toString();
            
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("orderNumber", data.getOrderNumber());
            returnData.put("userName", data.getUserName());
            returnData.put("realName", data.getRealName());
            returnData.put("orgName", data.getOrgName());
            returnData.put("serveName", data.getServeName());
            returnData.put("status", data.getStatus());
            returnData.put("charge", NumberUtils.changeF2Y(String.valueOf(charge)));
            returnData.put("sysIncome", NumberUtils.changeF2Y(String.valueOf(sysIncome)));
            returnData.put("profitShare", profitShare);
            returnData.put("createDate", data.getCreateDate());
            returnData.put("sysUserNo", data.getSysUserNo());
            returnData.put("agentUserNo", data.getAgentUserNo());
            returnData.put("agentIncome", NumberUtils.changeF2Y(String.valueOf(data.getAgentIncome())));
            returnData.put("salesmanUserNo", data.getSalesmanUserNo());
            returnData.put("salesmanIncome", NumberUtils.changeF2Y(String.valueOf(data.getSalesmanIncome())));
            returnData.put("introduceOrgUserNo", data.getIntroduceOrgUserNo());
            returnData.put("introduceOrgIncome", NumberUtils.changeF2Y(String.valueOf(data.getIntroduceOrgIncome())));
            returnData.put("serviceOrgUserNo", data.getServiceOrgUserNo());
            returnData.put("serviceOrgIncome", NumberUtils.changeF2Y(String.valueOf(data.getServiceOrgIncome())));
            returnData.put("orderType", data.getOrderType());
            returnDataList.add(returnData);
        }
        return returnDataList;
    }
    
    @RequestMapping(value = "data/status", method = RequestMethod.GET)
    public JSONObject listOrderStatus() {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (OrderStatus o : OrderStatus.values()) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("name", o.getRemark());
            returnData.put("status", o.getStatus());
            returnDataList.add(returnData);
        }
        JSONObject root = new JSONObject();
        root.put("success", true);
        root.put("data", returnDataList);
        return root;
    }
}
