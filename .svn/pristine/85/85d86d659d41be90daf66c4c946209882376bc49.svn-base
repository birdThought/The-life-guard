package com.lifeshs.customer.controller.order;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;

import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.vo.customer.CustomerSharingDataVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.vo.order.customer.OrderCountVO;

@RestController
@RequestMapping(value = "/order/count")
public class OrderCountController extends BaseController {

    @Resource(name = "v2OrderService")
    private OrderService orderService;
    
    @RequestMapping(value = "/page")
    public ModelAndView index() {
        return new ModelAndView("platform/ordermanager/order-count");
    }
    
    @RequestMapping(value = "/data/{curPage}", method = RequestMethod.GET)
    public JSONObject listOrderCount(@PathVariable(name = "curPage") int curPage,
        @RequestParam(required = false) String orgName,
        @RequestParam(required = false) String serveCode,
        @RequestParam(required = false) String start,
        @RequestParam(required = false) String end) throws UnsupportedEncodingException {
        
        Date startDate = null;
        Date endDate = null;
        if (StringUtils.isNotBlank(start)) {
            startDate = DateTimeUtilT.date(start);
        }
        if (StringUtils.isNotBlank(end)) {
            endDate = DateTimeUtilT.date(end);
        }
        CustomerSharingDataVO user = getUserSharingData();
        Paging<OrderCountVO> p = orderService.listOrderCount(user.getUserNo(), curPage, 10, orgName, serveCode, startDate, endDate);
        PaginationDTO<OrderCountVO> pagination = p.getPagination();
        
        JSONObject root = new JSONObject();
        root.put("success", true);
        root.put("data", pagination.getData());
        root.put("nowPage", pagination.getNowPage());
        root.put("totalPage", pagination.getTotalPage());
        root.put("totalSize", pagination.getTotalSize());
        return root;
    }
}
