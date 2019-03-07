package com.lifeshs.controller.org.datastatistics;


/*
 * 数据统计
 * author: wenxian.cai
 * date: 2017/8/16 11:38
 */
import com.alibaba.fastjson.JSON;

import com.lifeshs.common.model.AjaxJson;

import com.lifeshs.controller.common.BaseController;

import com.lifeshs.po.ProjectPO;
import com.lifeshs.po.data.DiseasesPO;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.org.offline.IOfflineManageService;
import com.lifeshs.service1.data.DiseasesService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.serve.ProjectService;

import com.lifeshs.vo.StatisticsVO;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller()
@RequestMapping(value = "org/data-statistics")
public class DataStatisticsController extends BaseController {
    private static final Logger logger = Logger.getLogger(DataStatisticsController.class);
    final static int STATISTICS_PAGE_SIZE = 12;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private DiseasesService diseasesService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private IOfflineManageService offlineManageService;

    /*
     * 数据统计报表页面
     * author: wenxian.cai
     * date: 2017/8/16 11:44
     */
    @RequestMapping()
    public ModelAndView dataStatistics() {
        ModelAndView modelAndView = new ModelAndView(
                "platform/org/datastatistics/data-statistics");
        LoginUser loginUser = getLoginUser();
        List<ProjectPO> projectPOS = projectService.findProjectList(loginUser.getOrgId());
        List<DiseasesPO> diseasesPOS = diseasesService.findDiseasesList();

        modelAndView.addObject("project", JSON.toJSON(projectPOS));
        modelAndView.addObject("diseases", JSON.toJSON(diseasesPOS));
        modelAndView.addObject("orgType", loginUser.getType());

        return modelAndView;
    }

    /*
     * 数据统计详细页面
     * author: wenxian.cai
     * date: 2017/8/16 11:46
     */
    @RequestMapping(value = "/details")
    public ModelAndView dataStatisticsDetails(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(
                "platform/org/datastatistics/data-statistics-details");
        String projectCode = request.getParameter("projectCode");
        String diseasesId = request.getParameter("diseasesId");
        Integer gender = Integer.valueOf(request.getParameter("gender"));
        String startAge = request.getParameter("startAge");
        String endAge = request.getParameter("endAge");
        LoginUser loginUser = getLoginUser();
        List<ProjectPO> projectPOS = projectService.findProjectList(loginUser.getOrgId());
        List<DiseasesPO> diseasesPOS = diseasesService.findDiseasesList();
        Map condition = new HashMap();
        condition.put("projectCode", projectCode);
        condition.put("diseasesId", diseasesId);
        condition.put("gender", gender);
        condition.put("startAge", startAge);
        condition.put("endAge", endAge);

        modelAndView.addObject("project", JSON.toJSON(projectPOS));
        modelAndView.addObject("diseases", JSON.toJSON(diseasesPOS));
        modelAndView.addObject("orgType", loginUser.getType());
        modelAndView.addObject("condition", JSON.toJSON(condition));

        return modelAndView;
    }

    /**
     * 获取数据统计报表列表
     * @param projectCode
     * @param diseasesId
     * @param gender
     * @param startAge
     * @param endAge
     * @return
     */
    @RequestMapping("/list-data-statistics/{page}")
    @ResponseBody
    public AjaxJson listDataStatistics(@PathVariable(value = "page")
    int page, @RequestParam(value = "projectCode")
    String projectCode, @RequestParam(value = "diseasesId")
    Integer diseasesId, @RequestParam(value = "gender")
    Integer gender, @RequestParam(value = "startAge")
    Integer startAge, @RequestParam(value = "endAge")
    Integer endAge) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();

        if ("-1".equals(projectCode)) {
            projectCode = null;
        }

        if (-1 == diseasesId) {
            diseasesId = null;
        }

        if (-1 == gender) {
            gender = null;
        }

        if (-1 == startAge) {
            startAge = null;
        }

        if (-1 == endAge) {
            endAge = null;
        }

        String start = null;
        String end = null;

        if ((startAge != null) && (endAge != null)) {
            LocalDate date = LocalDate.now();
            start = date.minus(endAge, ChronoUnit.YEARS).toString();
            end = date.minus(startAge, ChronoUnit.YEARS).toString();
        }

        Paging<StatisticsVO> data = orderService.findStatistics(projectCode,
                diseasesId, gender, start, end, user.getOrgId(), page,
                STATISTICS_PAGE_SIZE);
        PaginationDTO<StatisticsVO> paginationDTO = data.getPagination();
        ajaxJson.setObj(paginationDTO);

        return ajaxJson;
    }

    /**
     * 获取数据统计详细报表
     * @param page
     * @param projectCode
     * @param diseasesId
     * @param gender
     * @param startAge
     * @param endAge
     * @return
     */
    @RequestMapping("/list-data-statistics-details/{page}")
    @ResponseBody
    public AjaxJson listDataStatisticsDetails(
        @PathVariable(value = "page")
    int page, @RequestParam(value = "projectCode")
    String projectCode, @RequestParam(value = "diseasesId")
    Integer diseasesId, @RequestParam(value = "gender")
    Integer gender, @RequestParam(value = "startAge")
    Integer startAge, @RequestParam(value = "endAge")
    Integer endAge,
        @RequestParam(value = "mobile", required = false)
    String mobile) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();

        if ("-1".equals(projectCode)) {
            projectCode = null;
        }

        if (-1 == diseasesId) {
            diseasesId = null;
        }

        if (-1 == gender) {
            gender = null;
        }

        if (-1 == startAge) {
            startAge = null;
        }

        if (-1 == endAge) {
            endAge = null;
        }

        if ("".equals(mobile)) {
            mobile = null;
        }

        String start = null;
        String end = null;

        if ((startAge != null) && (endAge != null)) {
            LocalDate date = LocalDate.now();
            start = date.minus(endAge, ChronoUnit.YEARS).toString();
            end = date.minus(startAge, ChronoUnit.YEARS).toString();
        }

        Paging<StatisticsVO> data = orderService.findStatisticsDetails(projectCode,
                diseasesId, gender, start, end, mobile, user.getOrgId(), page,
                STATISTICS_PAGE_SIZE);
        PaginationDTO<StatisticsVO> paginationDTO = data.getPagination();
        ajaxJson.setObj(paginationDTO);

        return ajaxJson;
    }
    
    
    /**
     * 
     *  获取下线用户
     *  @author liaoguo
     *  @DateTime 2018年12月12日 下午8:11:30
     *
     *  @return
     */
    @RequestMapping(value = "/list-offline/{page}")
    @ResponseBody
    public AjaxJson listOffline(@PathVariable(value = "page") Integer page,
            @RequestParam(value = "realName") String realName, @RequestParam(value = "mobile") String mobile) {
        AjaxJson ajaxJson = new AjaxJson();
        String userNo = getLoginUser().getUserNo();
        PaginationDTO<UserPO> paginationDTO = offlineManageService.listOffile(userNo, realName, mobile, page, STATISTICS_PAGE_SIZE);
        ajaxJson.setObj(paginationDTO);

        return ajaxJson;
    }
    
    
    
    
    
}
