package com.lifeshs.app.api.member.v24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Combo;
import com.lifeshs.common.constants.app.Consult;
import com.lifeshs.common.constants.app.HuanXin;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.app.OrderRefund;
import com.lifeshs.common.constants.app.Org;
import com.lifeshs.common.constants.app.OrgServeGroup;
import com.lifeshs.common.constants.app.OrgUser;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.ProjectServe;
import com.lifeshs.common.constants.app.Serve;
import com.lifeshs.common.constants.common.Error;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.common.constants.common.order.ChargeModeEnum;
import com.lifeshs.common.constants.common.project.Status;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.po.LessonPO;
import com.lifeshs.po.OrderPO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.order.v2.OrderCountDTO;
import com.lifeshs.pojo.order.v2.OrderDTO;
import com.lifeshs.pojo.order.v2.visitServe.BodyDTO;
import com.lifeshs.pojo.order.v2.visitServe.BodyDetailDTO;
import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.service.ServiceCommonDTO;
import com.lifeshs.pojo.serve.visit.RecommendedComboDTO;
import com.lifeshs.service.org.employee.IEmployeeManageService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.serve.consult.ConsultService;
import com.lifeshs.service1.serve.visit.VisitService;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.vo.serve.consult.ServeUserVO;

@RestController(value = "v24OrderController")
@RequestMapping(value = "app/order/v24")
public class OrderController {

    @Resource(name = "v2OrderService")
    private OrderService orderService;

    @Resource(name = "v2ConsultService")
    private ConsultService consultService;
    
    @Resource(name = "v2VisitService")
    private VisitService visitService;

    @Resource(name = "employeeManageService")
    private IEmployeeManageService employeeService;


    @RequestMapping(value = "listOrderWithCondition", method = RequestMethod.POST)
    public JSONObject listOrderWithCondition(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int curPage = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        ProjectType projectType = ProjectType.PROJECT_ALL;
        if(mm_0.containsKey(Serve.CODE)){
            int serveCode = Integer.parseInt(mm_0.getString(Serve.CODE));
            projectType = ProjectType.values()[serveCode];
        }
        Integer type = mm_0.getInteger("type");
        
        Paging<OrderDTO> p = null;
        try {
            p = orderService.listOrder(userId, projectType, type, curPage, pageSize);
        } catch (OperationException oe) {
            return AppNormalService.error(oe.getMessage(), oe.getCode());
        }
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (OrderDTO o : p.getData()) {
            Map<String, Object> returnData = new HashMap<>();
            ServiceCommonDTO project = o.getCommonProject();
            
            returnData.put(Order.CREATEDATE, o.getCreateDate());
            returnData.put(Order.IMAGE, o.getProjectImage());
            returnData.put(Org.NAME, project.getOrg().getOrgName());
            String content = "";
            String footer = "";
            EmployeeDTO serveUser = o.getServeUser();   // 咨询跟线下服务该参数的值才不为null
            //ServeTypeSecondDTO serve = o.getServe();
            if (ProjectType.PROJECT_CONSULT.getValue().equals(o.getProjectType()) ) {
                content = "擅长:" + serveUser.getExpertise();
                footer = "服务类型:健康咨询";
            }
            if (ProjectType.PROJECT_LESSON.getValue().equals(o.getProjectType())) {
                EmployeeDTO employee = employeeService.getEmployee(o.getOrgUserId());
                if (employee == null) {
                    content = "主讲人：被外星人抓走了";
                } else {
                    content = "主讲人:" + employee.getRealName();
                }
                footer = "服务类型:健康课堂";
                
                returnData.put(OrgServeGroup.ID, o.getProjectLessonId());
            }

            if (ProjectType.PROJECT_TOSTORE.getValue().equals(o.getProjectType()) || ProjectType.PROJECT_VISIT.getValue().equals(o.getProjectType())) {
                BodyDTO body = JSONObject.parseObject(o.getBody(), BodyDTO.class);
                content = body.getName();
                footer = "服务师:" + serveUser.getRealName();
            }
            returnData.put(Order.TITLE, Toolkits.projectTilte(o.getSubject()));
            returnData.put(Order.CONTENT, content);
            returnData.put(Order.FOOTER, footer);
            returnData.put(Order.STATUS, o.getStatusEnum().getValue());
            returnData.put(Order.PRICE, NumberUtils.changeF2Y(o.getCharge().toString()));
            returnData.put(ProjectServe.CODE, o.getProjectCode());
            returnData.put(Order.ID, o.getId());
            returnData.put(Serve.CODE, o.getCommonProject().getProjectType());
            returnData.put(HuanXin.USERNAME, o.getHuanxinUserName());
            returnDataList.add(returnData);
        }
        return AppNormalService.success(returnDataList, true);
    }
    
    @RequestMapping(value = "countOrder", method = RequestMethod.POST)
    public JSONObject countOrder(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        
        OrderCountDTO data = orderService.getOrderCount(userId);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Order.UNCOMMENT, data.getUnComment());
        returnData.put(Order.TO_BE_PAID, data.getToBePaid());
        returnData.put(Order.TO_BE_USED, data.getToBeUsed());
        returnData.put(Order.REFUND, data.getRefund());
        return AppNormalService.success(returnData);
    }

    /**
     * 订单详情
     * @param json
     * @return
     */
    @RequestMapping(value = "getOrderDetails", method = RequestMethod.POST)
    public JSONObject getOrderDetails(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int userId = appJSON.getData().getUserId();
        int orderId = mm_0.getIntValue(Order.ID);
        boolean bool = orderService.isOrderBelongToUser(orderId, userId);
        if (!bool) {
            return AppNormalService.error(String.format(Error.NOT_FOUND, "订单"), ErrorCodeEnum.NOT_FOUND);
        }
        OrderPO order = orderService.getOrderDetail(orderId);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Order.IMAGE, order.getProjectImage());
        returnData.put(Order.PROJECT_NAME, Toolkits.projectTilte(order.getSubject()));
        returnData.put(Order.PRICE, NumberUtils.changeF2Y(String.valueOf(order.getPrice())));
        
        int serveCode = order.getProject().getProjectType();
        if (!onlineProject(serveCode)) {   //todo 暂时根据serveCode判断
            returnData.put(Order.VERIFY_CODE, order.getVerifyCode());   //线下和上门添加验证码
        } else {
            returnData.put(Org.NAME, order.getStore().getOrgName());
        }
        returnData.put(Order.STATUS, order.getStatusEnum().getValue());
        // 课堂添加serveGroupId
        if (ProjectType.PROJECT_LESSON.getValue().equals(serveCode)) {
            LessonPO lesson = order.getLesson();
            // 这里会出现null的状况，表示该群组已下线
            Integer lessonId = null;
            if (Status.ONLINE.getValue() == lesson.getStatus()) {
                // 如果项目已下线，就不返回课堂id
                lessonId = lesson.getId();
            }
            returnData.put(OrgServeGroup.ID, lessonId);
        }
        if (ProjectType.PROJECT_CONSULT.getValue().equals(serveCode)) {
            ServeUserVO serveUser = consultService.getServeUser(order.getEmployee().getId(), order.getProjectCode());
            Integer consultId = null;
            String expertField = "该信息已经被黑洞带走了";
            // serveUser当这个projectCode对应的服务已经下线，或者服务师不是正常状态（离职等）就会得到null
            // sid也会null
            if (serveUser != null) {
                consultId = serveUser.getSid();
                expertField = serveUser.getExpertise();
            }
            returnData.put("consultId", consultId);
            returnData.put(OrgUser.EXPERT_FIELD, expertField);
        }

        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put(Order.NUMBER, order.getOrderNumber());
        orderDetails.put(Order.CREATEDATE, order.getCreateDate());
        orderDetails.put(OrgUser.NAME, order.getEmployee().getRealName());
        orderDetails.put(Order.COUNT, order.getNumber());
        orderDetails.put(Order.TOTAL, NumberUtils.changeF2Y(String.valueOf(order.getCharge())));
        if (!onlineProject(serveCode)) {   // 线下与上门服务添加服务地址
            orderDetails.put(Order.USER_ADDRESS, order.getAddress());
            orderDetails.put(Order.RECEIVER_MOBILE, order.getReceiverMobile());
            orderDetails.put(Order.RECEIVER_NAME, order.getReceiverName());
        }
        // 健康咨询 添加 咨询方式
        if (ProjectType.PROJECT_CONSULT.getValue().equals(serveCode)) {
            orderDetails.put(Consult.MODE, "图文咨询");
        }
        orderDetails.put(ProjectServe.TYPE, Toolkits.projectType(order.getSubject()));
        // 课堂 添加 服务类型 结束日期
        if (ProjectType.PROJECT_LESSON.getValue().equals(serveCode)) {
            orderDetails.put(Order.END_DATE, order.getEndDate());
        }
        // 健康咨询跟课堂加上环信username返回
        if (onlineProject(serveCode)) {
            orderDetails.put(HuanXin.USERNAME, order.getHuanxinUserName());
        }
        
        returnData.put(Order.ORDER_DETAILS, orderDetails);

        // 线下服务添加机构信息
        if (!onlineProject(serveCode)) {
            Map<String, Object> store = new HashMap<>();
            store.put(Org.NAME, order.getStore().getOrgName());
            store.put(Org.STREET, order.getStore().getStreet());
            store.put(Org.LONGITUDE, order.getStore().getLongitude());
            store.put(Org.LATITUDE, order.getStore().getLatitude());
            store.put(Org.TEL, order.getStore().getTel());
            returnData.put(Order.STORE_DETAILS, store);
            
            BodyDTO body = JSONObject.parseObject(order.getBody(), BodyDTO.class);
            Map<String, Object> comboDetails = new HashMap<>();
            Integer comboId = body.getId();
            // 当套餐已经下架的时候这个方法就会返回null，这时候需要手动将comboId修改为null，避免用户通过这个comboId去查找这个套餐详情
            RecommendedComboDTO comboDTO = visitService.getProjectCombo(comboId);
            if (comboDTO == null) {
                comboId = null;
            }
            
            comboDetails.put(Combo.ID, comboId);
            comboDetails.put(Combo.NAME, body.getName());
            List<Map<String, Object>> details = new ArrayList<>();
            for (BodyDetailDTO b : body.getDetail()) {
                Map<String, Object> detail = new HashMap<>();
                detail.put(Combo.DETAILS_NAME, b.getName());
                detail.put(Combo.PRICE_MARKET, b.getPrice());
                detail.put(Combo.DETAILS_UNIT, b.getUnit());
                details.add(detail);
            }
            comboDetails.put(Combo.DETAILS, details);
            returnData.put(Combo.COMBO, comboDetails);
        }

        returnData.put(Serve.CODE, serveCode);
        returnData.put(ProjectServe.CODE, order.getProjectCode());
        returnData.put("serveItemId", order.getServeItemId());
        return AppNormalService.success(returnData);
    }

    private Boolean onlineProject(int code) {
        if (ProjectType.PROJECT_TOSTORE.getValue().equals(code) || ProjectType.PROJECT_VISIT.getValue().equals(code)) {
            return false;
        }
        if (ProjectType.PROJECT_CONSULT.getValue().equals(code) || ProjectType.PROJECT_LESSON.getValue().equals(code)) {
            return true;
        }
        return null;
    }

    /**
     * 订单退款.
     *
     * @param json 应用上传JSON格式字符串
     * @edit wuj
     * @return
     */
    @RequestMapping(value = "refund", method = RequestMethod.POST)
    public JSONObject refund(@RequestParam String json) {
        //解析上传数据
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int userId = appJSON.getData().getUserId();
        int orderId = mm_0.getIntValue(Order.ID);
        //退款原因
        String refundCause = mm_0.getString(OrderRefund.REFUND_CAUSE);

        //判断订单是否属于用户
        boolean bool = orderService.isOrderBelongToUser(orderId, userId);
        if (!bool) {
            return AppNormalService.error("无权限查看该订单");
        }

        //获取订单状态
        int status = orderService.getOrderDetail(orderId).getStatus();
        if (status == OrderStatus.VALID.getStatus()) {
            //订单状态有效，开始退款流程

            //返回数据
            Map<String, Object> returnData;

            //将订单状态设置为 退款中_7
            try {
                returnData = orderService.updateRefundOrder(orderId, refundCause, userId);
            } catch (DataBaseException e) {
                e.printStackTrace();
                return AppNormalService.error(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                return AppNormalService.error("退款失败");
            }

            return AppNormalService.success(returnData);
        }

        return AppNormalService.error("当前订单状态为:"+OrderStatus.findStatus(status).getRemark()+",不支持退款");
    }

    /**
     * 获取退款详情
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getRefundDetail",method = RequestMethod.POST)
    public JSONObject getRefundDetail(String json) {
        //解析上传数据
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int userId = appJSON.getData().getUserId();
        int orderId = mm_0.getIntValue(Order.ID);

        //判断订单是否属于用户
        boolean bool = orderService.isOrderBelongToUser(orderId, userId);
        if (!bool) {
            return AppNormalService.error("无权限查看该订单");
        }

        //返回数据
        Map<String, Object> returnData = new HashMap<>();

        try {
            returnData = orderService.getRefundDetail(orderId);
        } catch (DataBaseException e) {
            e.printStackTrace();
            return AppNormalService.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AppNormalService.error("查询异常");
        }

        return AppNormalService.success(returnData);
    }

    /**
     *  添加订单
     *  @author yuhang.weng 
     *	@DateTime 2017年7月14日 下午5:33:46
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "addOrder", method = RequestMethod.POST)
    public JSONObject addOrder(@RequestParam String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        
        Integer id = mm_0.getInteger(Normal.ID);
        int code = Integer.parseInt(mm_0.getString(Serve.CODE));
        ProjectType projectType = ProjectType.values()[code];
        Integer serveUserId = mm_0.getInteger(OrgUser.ID);
        Integer number = mm_0.getInteger(Order.COUNT);
        Integer addressId = mm_0.getInteger("addressId");
//        Integer couponId = mm_0.getInteger("couponId");    // 电子券id
        int chargeMode = ChargeModeEnum.TIMES.getValue();
        if (ProjectType.PROJECT_CONSULT.equals(projectType)) {
            chargeMode = mm_0.getInteger(OrgUser.CHARGEMODE);
        }
        
        OrderDTO order = orderService.addServeOrder(userId, projectType, id, serveUserId, number, addressId, chargeMode);
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Order.ID, order.getId());
        returnData.put(Order.NUMBER, order.getOrderNumber());
        return AppNormalService.success(returnData);
    }

    /**
     *  续费（暂时只支持健康咨询服务）
     *  @author yuhang.weng 
     *  @DateTime 2017年11月7日 下午6:19:45
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "keepServe", method = RequestMethod.POST)
    public JSONObject keepServe(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        Integer id = mm_0.getInteger(Normal.ID);
        
        OrderDTO order = orderService.addRenewalOrder(userId, ProjectType.PROJECT_CONSULT, id, 1);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Order.ID, order.getId());
        returnData.put(Order.NUMBER, order.getOrderNumber());
        return AppNormalService.success(returnData);
    }
    
    /**
     *  使用全额抵扣券
     *  @author yuhang.weng 
     *  @DateTime 2017年12月27日 下午3:51:08
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "finishOrderWithCoupon", method = RequestMethod.POST)
    public JSONObject finishOrderWithCoupon(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        Integer orderId = mm_0.getInteger(Order.ID);
        Integer couponId = mm_0.getInteger("couponId");    // 电子券id
        orderService.finishOrderWithCoupon(orderId, couponId);
        return AppNormalService.success();
    }
    
    /**
     * 删除订单
     * @param json
     * @return
     */
    @RequestMapping(value = "deleteOrder", method = RequestMethod.POST)
    public JSONObject deleteOrder(String json) {
        //解析上传数据
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int userId = appJSON.getData().getUserId();
        int orderId = mm_0.getIntValue(Order.ID);

        //判断订单是否属于用户
        boolean bool = orderService.isOrderBelongToUser(orderId, userId);
        if (!bool) {
            return AppNormalService.error("无权限删除该订单");
        }

        //返回数据
        Map<String, Object> returnData = new HashMap<>();

        try {
            orderService.deleteOrder(orderId);
        } catch (DataBaseException e) {
            e.printStackTrace();
            return AppNormalService.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AppNormalService.error("删除异常");
        }

        return AppNormalService.success(returnData);
    }

}
