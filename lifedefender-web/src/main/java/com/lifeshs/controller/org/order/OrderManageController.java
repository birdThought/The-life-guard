package com.lifeshs.controller.org.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.po.LessonPO;
import com.lifeshs.po.UserPO;
import com.lifeshs.po.order.OrderWithVipPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.order.OrderCommentDTO;
import com.lifeshs.pojo.order.ServiceOrderDTO;
import com.lifeshs.pojo.order.homeOrderDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service1.order.IStoreOrderService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.order.comment.CommentService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.serve.lesson.LessonService;
import com.lifeshs.thirdservice.IAlipayService;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.vo.RefundOrderVO;
import com.lifeshs.vo.order.OrderAgentAmountVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单（任务）管理
 *
 * @author wenxian.cai
 * @create 2017-05-08 16:18
 **/

@RequestMapping({"orderManage","order"})
@RestController
public class OrderManageController extends BaseController{

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrderManageController.class);

    @Autowired
    private IOrderService orderService;
    
    @Resource(name = "v2OrderService")
    private OrderService orderMemberService;
    
    @Resource(name = "orderCommentService")
    private CommentService commentService;

    @Autowired
    LessonService lessonService;

    @Autowired
    private OrderService orderService1;

    @Autowired
    IStoreOrderService storeOrderService;

    @Resource(name = "iAlipayService")
    private IAlipayService alipayService;

    final static Integer PAGE_SIZE = 6;
    
    final static Integer LIST_ORDER_PAGE_SIZE = 10;
    
    /**
     * 
     *  用户订单管理
     *  @author liaoguo
     *  @DateTime 2018年12月25日 上午10:54:35
     *
     *  @return
     */
    @RequestMapping(value="/orderListManage")
    public ModelAndView orderListManage() {
        ModelAndView modelAndView = new ModelAndView("platform/org/ordermanage/order-member");
        
        return modelAndView;
    }
    
    /**
     * 
     *  获取订单列表
     *  @author liaoguo
     *  @DateTime 2018年12月25日 上午11:04:44
     *
     *  @param page
     *  @param realName
     *  @param mobile
     *  @return
     */
    @RequestMapping("/list-order/{page}")
    @ResponseBody
    public JSONObject listOrder(
            @PathVariable(value = "page") int page,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) String orgName,
            @RequestParam(required = false) String projectType,
            @RequestParam(required = false) Integer orderStatus,
            @RequestParam(required = false) String orderType) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        
        OrderStatus status = null;
        if (orderStatus != null) {
            status = OrderStatus.findStatus(orderStatus);
        }
        LoginUser loginUser = getLoginUser();
        
        //总盈利
        OrderAgentAmountVO amount = orderMemberService.findTotalMoney(loginUser.getUserNo(), userName,  realName,  orgName, projectType,  status, orderType);
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
        Paging<OrderWithVipPO> p = orderMemberService.listOrder(loginUser.getUserNo(), page, LIST_ORDER_PAGE_SIZE, userName, realName, orgName, projectType, status, orderType);
        PaginationDTO<OrderWithVipPO> pagination = p.getPagination();
        
        JSONObject root = new JSONObject();
        root.put("success", true);
        root.put("flag", loginUser.getUserNo().substring(0, 1));
        root.put("userNo", loginUser.getUserNo());
        root.put("introduceTotal", NumberUtils.changeF2Y(String.valueOf(amount.getIntroduceTotal())));
        root.put("serviceTotal", NumberUtils.changeF2Y(String.valueOf(amount.getServiceTotal())));
        root.put("toalMoeny", NumberUtils.changeF2Y(String.valueOf(amount.getTotal())));
        root.put("moeny", NumberUtils.changeF2Y(String.valueOf(amount.getTotal()+amount.getIntroduceTotal()+amount.getServiceTotal())));
        root.put("nowPage", pagination.getNowPage());
        root.put("totalPage", pagination.getTotalPage());
        root.put("totalSize", pagination.getTotalSize());
        root.put("data", enclosureOrderList(pagination.getData()));
        return root;
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
    
    

    /**
     * @Description: 订单（服务）管理-待办服务
     * @author: wenxian.cai
     * @create: 2017/5/9 10:40
     */
    @RequestMapping(value = {"/ordertodo","/ordertodo/{username}"})
    public ModelAndView orderTodo(@PathVariable(value = "username", required = false) String userName) {
        ModelAndView modelAndView = new ModelAndView("platform/org/ordermanage/order-todo");
        LoginUser user = getLoginUser();
        int orgUserId = user.getId();
        int status = OrderStatus.VALID.getStatus();
//        String userName = null;
        String type = "all";
        int pageIndex = 1;
        PaginationDTO<ServiceOrderDTO> paginationDTO = orderService.listOrderByOrgUser(orgUserId, status, userName, type, pageIndex, PAGE_SIZE);

        modelAndView.addObject("order", JSON.toJSON(paginationDTO));
        modelAndView.addObject("orgType",user.getType());
        Map orgUser = new HashMap();
        orgUser.put("userName", user.getUserName());
        orgUser.put("realName", user.getRealName());
        orgUser.put("userCode", user.getUserCode());
        orgUser.put("head", user.getHead());
        modelAndView.addObject("user", JSON.toJSON(orgUser));
        return modelAndView;
    }

    /**
     * @Description: 订单（服务）管理-已完成服务
     * @author: wenxian.cai
     * @create: 2017/5/9 10:41
     */
    @RequestMapping(value = "/orderdone")
    public ModelAndView orderDone() {
        ModelAndView modelAndView = new ModelAndView("platform/org/ordermanage/order-done");
        LoginUser user = getLoginUser();
        int orgUserId = user.getId();
        int status = OrderStatus.COMPLETED.getStatus();
        String userName = null;
        String type = "all";
        int pageIndex = 1;
        PaginationDTO<ServiceOrderDTO> paginationDTO = orderService.listOrderByOrgUser(orgUserId, status, userName, type, pageIndex, PAGE_SIZE);
        modelAndView.addObject("order", JSON.toJSON(paginationDTO));
        modelAndView.addObject("orgType",user.getType());
        return modelAndView;
    }

    /**
     * @Description: 订单（服务）管理-待评论服务
     * @author: wenxian.cai
     * @create: 2017/5/9 10:41
     */
    @RequestMapping(value = "/ordercomments")
    public ModelAndView orderComments() {
        ModelAndView modelAndView = new ModelAndView("platform/org/ordermanage/order-comments");
        LoginUser user = getLoginUser();
        int orgUserId = user.getId();
        int status = OrderStatus.COMPLETED.getStatus();
        String type = "all";
        String userName = null;
        int pageIndex = 1;
        Boolean commentsStatus = false; // 待评论
        PaginationDTO<ServiceOrderDTO> paginationDTO = orderService.listOrderByComments(orgUserId, status, type, userName, commentsStatus, pageIndex, PAGE_SIZE);
        modelAndView.addObject("order", JSON.toJSON(paginationDTO));
        modelAndView.addObject("orgType",user.getType());
        return modelAndView;
    }

    /**
     * @Description: 获取订单列表
     * @author: wenxian.cai
     * @create: 2017/5/9 10:16
     */
    @RequestMapping(value = "/listserveorder/{action}/{page}")
    public @ResponseBody AjaxJson listServeOrder(@PathVariable(value = "action") String action, @PathVariable(value = "page") Integer page,
                                                 @RequestParam(value = "projectType", required = false) String projectType,
                                                 @RequestParam(value = "keyword", required = false) String keyword) {
        AjaxJson ajaxJson = new AjaxJson();
        int orgUserId = getLoginUser().getId();
        Integer status = null;
        PaginationDTO<ServiceOrderDTO> paginationDTO = new PaginationDTO<>();
        switch (action) {
        case "todo":
            status = OrderStatus.VALID.getStatus();
            paginationDTO = orderService.listOrderByOrgUser(orgUserId, status, keyword, projectType, page, PAGE_SIZE);
            break;
        case "done":
            status = OrderStatus.COMPLETED.getStatus();
            paginationDTO = orderService.listOrderByOrgUser(orgUserId, status, keyword, projectType, page, PAGE_SIZE);
            break;
        case "comments":
            status = OrderStatus.COMPLETED.getStatus();
            Boolean commentsStatus = false;
            paginationDTO = orderService.listOrderByComments(orgUserId, status, projectType, keyword, commentsStatus, page, PAGE_SIZE);
            break;
        default: 
            paginationDTO = new PaginationDTO<>();
        }
        ajaxJson.setObj(paginationDTO);
        return ajaxJson;
    }

    /**
     * @Description: 服务订单详细
     * @author: wenxian.cai
     * @create: 2017/5/9 15:00
     */
    @RequestMapping(value = "/orderdetail/{orderId}")
    public ModelAndView orderDetail(@PathVariable Integer orderId) {
        LoginUser loginUser = getLoginUser();
        Integer orgId = loginUser.getOrgId();
        ModelAndView modelAndView = new ModelAndView("platform/org/ordermanage/order-detail");
        ServiceOrderDTO order = orderService.getOrderDetails(orderId, orgId);
        if (order == null) {
            return new ModelAndView("view/common/404");
        }
        modelAndView.addObject("order", JSON.toJSON(order));
        modelAndView.addObject("orgType", loginUser.getType());
        return modelAndView;
    }
    
    @RequestMapping(value = "/ordercomments/{orderId}")
    public ModelAndView orderCommentDetail(@PathVariable Integer orderId) {
        LoginUser loginUser = getLoginUser();
        Integer orgId = loginUser.getOrgId();
        ModelAndView modelAndView = new ModelAndView("platform/org/ordermanage/order-comment-detail");
        ServiceOrderDTO order = orderService.getOrderDetails(orderId, orgId);
        if (order == null) {
            return new ModelAndView("view/common/404");
        }
        OrderCommentDTO comment = order.getComment();
        modelAndView.addObject("comment", JSONObject.toJSON(comment));
        modelAndView.addObject("orgType", loginUser.getType());
        return modelAndView;
    }
    
    @RequestMapping(value = "/ordercomments/{orderId}", method = RequestMethod.POST)
    public AjaxJson orderCommentCommit(@PathVariable(name = "orderId") Integer orderId, @RequestBody OrderCommentDTO data) {
        AjaxJson result = new AjaxJson();
        result.setSuccess(false);
        LoginUser loginUser = getLoginUser();
        int userId = loginUser.getId();
        Integer orgId = loginUser.getOrgId();
        ServiceOrderDTO order = orderService.getOrderDetails(orderId, orgId);
        if (order != null) {
            result.setSuccess(true);
            data.setOrderId(orderId);   // 订单ID
            try {
                commentService.replyComment(data.getOrderId(), data.getReply(), userId);
            } catch (OperationException oe) {
                String errorMsg = oe.getMessage();
                result.setSuccess(false);
                result.setMsg(errorMsg);
                return result;
            }
            
            OrderCommentDTO comment = order.getComment();
            comment.setReply(data.getReply());
            comment.setStatus(true);
            result.setObj(JSONObject.toJSON(comment));
        }
        return result;
    }

    /**
     * @Description: 任务中心
     * @Author: wenxian.cai
     * @Date: 2017/6/7 20:26
     */
    @RequestMapping(value = {"/order", "/order/{username}"})
    public ModelAndView orderCenter(@PathVariable(value = "username", required = false) String userName) {
        ModelAndView modelAndView = new ModelAndView("platform/org/ordermanage/task-center");
        int orgId = getLoginUser().getOrgId();

        PaginationDTO paginationDTO = orderService.listOrderByOrg(orgId, userName, 3, null, 1, PAGE_SIZE);
//        PaginationDTO paginationDTO = storeOrderService.findOrderList(orgId, ProjectType.PROJECT_ALL.getValue(), OrderStatus.VALID.getStatus(), userName, 1, PAGE_SIZE);
        modelAndView.addObject("order", JSON.toJSON(paginationDTO));
        return modelAndView;
    }

    /**
     * @Description: 门店获取任务（订单）列表
     * @Author: wenxian.cai
     * @Date: 2017/6/8 9:43
     */
    @RequestMapping(value = "/listorder/{page}")
    public @ResponseBody AjaxJson listServeOrder(@PathVariable(value = "page") Integer page, @RequestParam(value = "type") String type,
                                                 @RequestParam(value = "status") Integer status, @RequestParam(value = "userName", required = false) String userName) {
        AjaxJson ajaxJson = new AjaxJson();
        int orgId = getLoginUser().getOrgId();
        userName = userName == "" ? null : userName;
        type = type == "" ? null : type;
        PaginationDTO paginationDTO = new PaginationDTO();
        /*if ("offline".equals(type)) {
            paginationDTO.setData(new ArrayList());
            ajaxJson.setObj(paginationDTO);
            return ajaxJson;
        }*/
        paginationDTO = orderService.listOrderByOrg(orgId, userName, status, type, page, PAGE_SIZE);
        ajaxJson.setObj(paginationDTO);
        return ajaxJson;
    }

    /**
     * @Description: 任务详情页面
     * @Author: wenxian.cai
     * @Date: 2017/6/8 15:12
     */
    @RequestMapping(value = "/orderdetails/{orderId}")
    public ModelAndView orderDetails(@PathVariable Integer orderId) {
        LoginUser loginUser = getLoginUser();
        Integer orgId = loginUser.getOrgId();
        ModelAndView modelAndView = new ModelAndView("platform/org/ordermanage/task-detail");
        ServiceOrderDTO order = orderService.getOrderDetails(orderId, orgId);
//        if (order == null) {
//            return new ModelAndView("view/common/404");
//        }
        modelAndView.addObject("order", JSON.toJSON(order));
        return modelAndView;
    }

    /**
     * @Description: 验证服务验证码
     * @Author: wenxian.cai
     * @Date: 2017/6/15 19:21
     * todo 安全性待完善
     */
    @RequestMapping(params = "checkorder")
    @ResponseBody
    public AjaxJson checkOrder(@RequestParam("orderId") int orderId, @RequestParam("orderNumber") String orderNumber,
                               @RequestParam("verifyCode") String verifyCode) {
        AjaxJson ajaxJson = new AjaxJson();
//        int orgId = getLoginUser().getOrgId();
        //todo 判断该订单是否属于该服务师

        boolean bool = orderService.checkVerifyCode(orderId, orderNumber, verifyCode);
        if (!bool) {
            ajaxJson.setMsg("验证不通过");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    /**
     * @Description: 主页获取订单列表
     * @Author: wenxian.cai
     * @Date: 2017/6/29 11:09
     */
    @RequestMapping(params = "listorder")
    @ResponseBody
    public AjaxJson listOrder(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
                              @RequestParam("type") String type) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        List<homeOrderDTO> list = new ArrayList<>();
        switch (type) {
            case "org":
                if (user.getUserType() != 0 && user.getUserType() != 2) {
                    ajaxJson.setMsg("您没有查看权限");
                    ajaxJson.setSuccess(false);
                    return ajaxJson;
                }
                list = orderService.listOrderByOrg(startDate, endDate, user.getOrgId());
                break;
            case "orgUser":
                list = orderService.listOrderByOrgUser(startDate, endDate, user.getId());
        }
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    /**
     * 获取课堂群成员
     * @param code
     * @return
     */
    @RequestMapping(params = "listlessonmember")
    @ResponseBody
    public AjaxJson listLessonMember(@RequestParam("code") String code) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        List<UserPO> list = lessonService.findLessonMemberList(code);
        ajaxJson.setObj(list);
        return ajaxJson;
    }


    /**
     * @Description: 获取退款信息详情
     * @Author: wenxian.cai
     * @Date: 2017/7/15 11:49
     */
    @RequestMapping(value = "/refund/details", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getRefundDetails(@RequestParam("orderNumber") String orderNumber) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        if (user.getUserType().equals(1)) {
            ajaxJson.setMsg("无权限执行操作");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        RefundOrderVO refundOrderVO = orderService1.getRefundOrderDetail(orderNumber);
        ajaxJson.setObj(refundOrderVO);
        return ajaxJson;
    }

    /**
     * @Description: 门店确认退款
     * @Author: wenxian.cai
     * @Date: 2017/7/14 9:51
     */
    @RequestMapping(value = "/confirm-refund-order", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson confirmRefundorder(@RequestParam("orderNumber") String orderNumber, @RequestParam("orderId") int orderId) throws Exception {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        if (user.getUserType().equals(1)) {
            ajaxJson.setMsg("无权限执行操作");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        /*//修改为审核装态
        orderService1.refundAuditedOrder(orderNumber, user.getId());
        try {
            com.alipay.api.response.AlipayTradeRefundResponse refund = alipayService.refund(orderId);
            if (refund.isSuccess()) {
                if ("10000".equals(refund.getCode())) {
                    orderService1.refundCompleteOrder(orderNumber);
                } else {
                    orderService1.refundFailOrder(orderNumber);
                }
            } else {
                orderService1.refundFailOrder(orderNumber);
            }
        } catch (Exception e) {
            logger.error("阿里退款异常");
        }*/
        try {
            orderService1.confirmRefundOrder(orderId, user.getId());
            return ajaxJson;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            ajaxJson.setMsg(e.getErrMsg());
        } catch (IOException e) {
            e.printStackTrace();
            ajaxJson.setMsg("退款失败");
        } catch (DataBaseException e) {
            e.printStackTrace();
            ajaxJson.setMsg("退款失败");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setMsg("退款失败");
        }
        ajaxJson.setSuccess(false);
        return ajaxJson;
    }

    /**
     * 课堂服务
     * @return
     */
    @RequestMapping(value = "/order-lesson")
    public ModelAndView lessonOrder() {
        ModelAndView modelAndView = new ModelAndView("platform/org/ordermanage/order-lesson");
        LoginUser user = getLoginUser();
        int orgUserId = user.getId();
        Paging<LessonPO> p = lessonService.findLessonListByServices(orgUserId, null, 1, PAGE_SIZE);

        modelAndView.addObject("order", JSON.toJSON(p.getPagination()));
        modelAndView.addObject("orgType",user.getType());
        Map orgUser = new HashMap();
        orgUser.put("userName", user.getUserName());
        orgUser.put("realName", user.getRealName());
        orgUser.put("userCode", user.getUserCode());
        orgUser.put("head", user.getHead());
        modelAndView.addObject("user", JSON.toJSON(orgUser));
        return modelAndView;
    }

    /**
     * 获取课堂列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/list-lesson/{page}")
    @ResponseBody
    public AjaxJson listLesson(@PathVariable ("page") int page, @RequestParam ("userName") String userName) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        int orgUserId = user.getId();
        Paging<LessonPO> p = lessonService.findLessonListByServices(orgUserId, userName, page, PAGE_SIZE);
        /*if (p.getPagination().getTotalSize() == 0) {
                ajaxJson.setObj(new PaginationDTO());
            return ajaxJson;
        }*/
        ajaxJson.setObj((p.getPagination()));
        return ajaxJson;
    }



}

