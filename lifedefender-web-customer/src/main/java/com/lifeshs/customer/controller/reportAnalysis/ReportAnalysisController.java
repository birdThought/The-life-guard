package com.lifeshs.customer.controller.reportAnalysis;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service1.order.reportAnalysis.ReportAnalysisOrderService;
import com.lifeshs.service1.page.Paging;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;


/**
 * 分析报告
 * author: wenxian.cai
 * date: 2017/10/10 15:17
 */
@Controller
@RequestMapping("/report-analysis")
public class ReportAnalysisController extends BaseController {

    Logger logger = Logger.getLogger(ReportAnalysisController.class);

    @Autowired
    ReportAnalysisOrderService reportAnalysisOrderService;

    static final int REPORT_ANALYSIS_PAGE_SIZE = 10;

    /**
     * 分析报告页面
     * author: wenxian.cai
     * date: 2017/10/10 15:24
     */
    @RequestMapping("/page")
    private ModelAndView reportAnalysisPage() {
        return new ModelAndView("platform/reportanalysis/report-analysis");
    }

    /**
     * 获取分析报告订单
     * @param page
     * @param status
     * @param keyword 关键词
     * @return
     */
    @RequestMapping("/list-order/{page}")
    @ResponseBody
    public AjaxJson listOrder(@PathVariable("page") int page,
                               @RequestParam("orderDate") String month,
                               @RequestParam("orderStatus") Integer status,
                               @RequestParam("keyword") String keyword) {
        AjaxJson ajaxJson = new AjaxJson();
        LocalDate localDate = LocalDate.parse(month, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate startDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
        Paging paging = reportAnalysisOrderService.findOrderList(startDate.toString(), endDate.toString(), null, keyword, status, page, REPORT_ANALYSIS_PAGE_SIZE);
        ajaxJson.setObj(paging.getPagination());
        return ajaxJson;
    }

    /**
     * 客服填写分析报告结果
     * @param orderId
     * @param content 分析报告结果
     * @param doctorSign 医生署名
     * @return
     */
    @RequestMapping(value = "/reply-order", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson replyOrder(@RequestParam("content") String content,
                                @RequestParam("orderId") int orderId,
                                @RequestParam("doctorSign") String doctorSign) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser customer = getLoginUser();
        try {
            reportAnalysisOrderService.finishOrder(orderId, content, doctorSign, customer.getId());
        } catch (OperationException o) {
            logger.error(o.getMessage());
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }




}
