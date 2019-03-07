package com.lifeshs.customer.controller.drugs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.common.Constant;
import com.lifeshs.common.constants.common.jianKe.JianKe;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.component.weChat.WXPayUtil;
import com.lifeshs.po.drugs.DrugsPO;
import com.lifeshs.po.drugs.OrderPO;
import com.lifeshs.po.drugs.PrescriptionType;
import com.lifeshs.po.drugs.ProductAttribute;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.security.sessionmgr.ClientManager;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.thirdservice.DrugsOrderService;
import com.lifeshs.thirdservice.DrugsService;
import com.lifeshs.thirdservice.impl.WeChatHttpUtils;
import com.lifeshs.utils.JSONHelper;

import net.sf.json.JSONObject;

/**
 * 
 *  药品订单
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年7月12日 下午2:07:28
 */
@RestController
@RequestMapping("/drugs/order")
public class DrugsOrderManageController {

    static final Logger logger = Logger.getLogger(DrugsOrderManageController.class);
    private static final int CUSTOMER_ORDER_PAGE_SIZE = 10;

    @Autowired
    private DrugsOrderService drugsOrderService;
  
    /**
     * 套餐管理页面
     * 
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView ComboControllerPage() {
        return new ModelAndView("platform/drugs/drugs-order-manager");
    }

    /**
     * 
     *  获取订单列表
     *  @author liaoguo
     *  @DateTime 2018年7月13日 下午3:21:52
     *
     *  @param page
     *  @param orderNo
     *  @param paymentType
     *  @param status
     *  @return
     */
    @RequestMapping("/data/list/{page}")
    @ResponseBody
    public AjaxJson listComboItem(@PathVariable("page") int page,
            @RequestParam(value = "orderNo", required = false) String orderNo,
            @RequestParam(value = "paymentType", required = false) Integer paymentType,
            @RequestParam(value = "status", required = false) Integer status) {
        AjaxJson ajaxJson = new AjaxJson();

        Paging<OrderPO> paging = drugsOrderService.getDrugsOrderList(orderNo, paymentType, status, page, CUSTOMER_ORDER_PAGE_SIZE);
        ajaxJson.setObj(paging.getPagination());
        return ajaxJson;
    }

    @RequestMapping(value = "/data/get", method = RequestMethod.POST)
    public AjaxJson findDrugsOrderInfo(@RequestParam(value = "orderNo", required = false) String orderNo) {
        AjaxJson ajaxJson = new AjaxJson();
        OrderPO list = drugsOrderService.findDrugsOrderInfo(orderNo);
        ajaxJson.setObj(list);
        return ajaxJson;
    }
}
