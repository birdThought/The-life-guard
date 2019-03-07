package com.lifeshs.business.controller.reportAnalysis;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.po.BloodLipidPO;
import com.lifeshs.po.GluCometerPO;
import com.lifeshs.po.UaPO;
import com.lifeshs.po.UranPO;
import com.lifeshs.po.UserPO;
import com.lifeshs.po.user.ReportAnalysisPO;
import com.lifeshs.pojo.healthDevice.EcgDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.service.device.impl.BloodLipid;
import com.lifeshs.service.device.impl.Ecg;
import com.lifeshs.service.device.impl.Glucometer;
import com.lifeshs.service.device.impl.Ua;
import com.lifeshs.service.device.impl.Uran;
import com.lifeshs.service1.member.IMemberService;
import com.lifeshs.service1.order.reportAnalysis.ReportAnalysisOrderService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.order.reportAnalysis.ReportAnalysisOrderVO;

/**
 * 渠道商分析报告
 * author: wenxian.cai
 * date: 2017/10/10 15:17
 */

@Controller
@RequestMapping("/report-analysis")
public class ReportAnalysisController extends BaseController {

	Logger logger = Logger.getLogger(ReportAnalysisController.class);

	@Autowired
	ReportAnalysisOrderService reportAnalysisOrderService;
	@Autowired
	IMemberService memberService;
	@Autowired
	Uran uran;
	@Autowired
	BloodLipid bloodLipid;
	@Autowired
	Ua ua;
	@Autowired
	Ecg ecg;
	@Autowired
    Glucometer glucometer;


	static final int REPORT_ANALYSIS_PAGE_SIZE = 10;

	/**
	 * 分析报告订单中心页面
	 * author: wenxian.cai
	 * date: 2017/10/10 15:24
	 */
	@RequestMapping("/order-page")
	private ModelAndView reportAnalysisPage() {
		return new ModelAndView("platform/reportanalysis/order-center");
	}

	/**
	 * 获取分析报告订单
	 * @param page
	 * @param status
	 * @param keyword 关键词
	 * @return
	 */
	@RequestMapping(value = "/orders/{page}", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJson listOrder(@PathVariable("page") int page,
							   @RequestParam("orderStatus") Integer status,
							   @RequestParam("keyword") String keyword) {
		AjaxJson ajaxJson = new AjaxJson();
		Paging<ReportAnalysisOrderVO> paging = reportAnalysisOrderService.findOrderList(null, null, getLoginUser().getId(), keyword, status, page, REPORT_ANALYSIS_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}

	/**
	 * 创建尿检分析报告订单
	 * @param userId 用户id
	 * @param content 分析报告内容
	 * @return
	 */
	@RequestMapping(value = "/order/uran", method = RequestMethod.POST)
	@ResponseBody
	private AjaxJson createOrder(@RequestParam("userId") int userId,
								 @RequestParam("content") String content,
								 @RequestParam("status") int status) {
		AjaxJson ajaxJson = new AjaxJson();
		ReportAnalysisOrderVO vo = new ReportAnalysisOrderVO();
		vo.setPrice(0.01);
		vo.setBusinessUserId(getLoginUser().getId());
		UserPO userPO = new UserPO();
		userPO.setId(userId);
		vo.setUserPO(userPO);
		ReportAnalysisPO reportAnalysisPO = new ReportAnalysisPO();
		try {
            content = URLDecoder.decode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);
        }
		reportAnalysisPO.setContent(content);
		reportAnalysisPO.setHealthProduct(HealthPackageType.URAN.value());
		reportAnalysisPO.setRequestorType(2);
		reportAnalysisPO.setStatus(status);
		vo.setReportAnalysisPO(reportAnalysisPO);
		try {
			reportAnalysisOrderService.createOrder(vo);
			Map<String, Object> map = new HashMap<>();
			map.put("orderNumber", vo.getOrderNumber());
			map.put("price", vo.getPrice());
			ajaxJson.setObj(map);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJson.setMsg("操作失败");
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

	/**
	 * 普通用户搜索
	 * author: wenxian.cai
	 * date: 2017/10/11 9:35
	 */
	@RequestMapping("/search-member-page")
	private ModelAndView searchMemberPage() {
		return new ModelAndView("platform/reportanalysis/search-member");
	}

	/**
	 * 根据关键词获取用户列表
	 * param keyword 关键词（账户名、姓名、手机号码）
	 * author: wenxian.cai
	 * date: 2017/10/11 9:38
	 */
	@RequestMapping(value = "/members", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJson listMember(@RequestParam("keyword") String keyword){
		AjaxJson ajaxJson = new AjaxJson();
		List<UserDTO> list = memberService.findUserList(keyword);
		ajaxJson.setObj(list);
		return ajaxJson;
	}

	/**
	 * 获取最新的一条尿检数据
	 * author: wenxian.cai
	 * date: 2017/10/24 10:46
	 */
	@RequestMapping(value = "/uran/lastest", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJson getLastestUran(@RequestParam("userId") int userId) {
		AjaxJson ajaxJson = new AjaxJson();
		UranPO po = uran.getLastestData(userId);
		ajaxJson.setObj(po);
		return ajaxJson;
	}

	/**
	 * 创建血脂分析报告订单
	 * @param userId 用户id
	 * @param content 分析报告内容
	 * @return
	 */
	@RequestMapping(value = "/order/blood", method = RequestMethod.POST)
	@ResponseBody
	private AjaxJson bloodOrder(@RequestParam("userId") int userId,
								 @RequestParam("content") String content,
                                 @RequestParam("status") int status) {
		AjaxJson ajaxJson = new AjaxJson();
		ReportAnalysisOrderVO vo = new ReportAnalysisOrderVO();
		vo.setPrice(0.01);
		vo.setBusinessUserId(getLoginUser().getId());
		UserPO userPO = new UserPO();
		userPO.setId(userId);
		vo.setUserPO(userPO);
		ReportAnalysisPO reportAnalysisPO = new ReportAnalysisPO();
		reportAnalysisPO.setContent(content);
		reportAnalysisPO.setHealthProduct(HealthPackageType.BloodLipid.value());
		reportAnalysisPO.setRequestorType(2);
        reportAnalysisPO.setStatus(status);
		vo.setReportAnalysisPO(reportAnalysisPO);
		try {
			reportAnalysisOrderService.createOrder(vo);
			Map<String, Object> map = new HashMap<>();
			map.put("orderNumber", vo.getOrderNumber());
			map.put("price", vo.getPrice());
			ajaxJson.setObj(map);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJson.setMsg("操作失败");
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

	/**
	 * 获取最新的一条血脂数据
	 * author: wenxian.cai
	 * date: 2017/10/24 10:46
	 */
	@RequestMapping(value = "/blood/lastest", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJson getLastestBlood(@RequestParam("userId") int userId) {
		AjaxJson ajaxJson = new AjaxJson();
		BloodLipidPO po = bloodLipid.getLastestData(userId);
		ajaxJson.setObj(po);
		return ajaxJson;
	}

	/**
	 * 获取最新的一条尿酸数据
	 * author: wenxian.cai
	 * date: 2017/10/24 10:46
	 */
	@RequestMapping(value = "/ua/lastest", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJson getLastestua(@RequestParam("userId") int userId) {
		AjaxJson ajaxJson = new AjaxJson();
		UaPO up = ua.getLastestData(userId);
		ajaxJson.setObj(up);
		return ajaxJson;
	}

	/**
	 * 创建尿酸分析报告订单
	 * @param userId 用户id
	 * @param content 分析报告内容
	 * @return
	 */
	@RequestMapping(value = "/order/ua", method = RequestMethod.POST)
	@ResponseBody
	private AjaxJson uAOrder(@RequestParam("userId") int userId,
								@RequestParam("content") String content,
                                @RequestParam("status") int status) {
		AjaxJson ajaxJson = new AjaxJson();
		ReportAnalysisOrderVO vo = new ReportAnalysisOrderVO();
		vo.setPrice(0.01);
		vo.setBusinessUserId(getLoginUser().getId());
		UserPO userPO = new UserPO();
		userPO.setId(userId);
		vo.setUserPO(userPO);
		ReportAnalysisPO reportAnalysisPO = new ReportAnalysisPO();
		reportAnalysisPO.setContent(content);
		reportAnalysisPO.setHealthProduct(HealthPackageType.UA.value());
		reportAnalysisPO.setRequestorType(2);
        reportAnalysisPO.setStatus(status);
		vo.setReportAnalysisPO(reportAnalysisPO);
		try {
			reportAnalysisOrderService.createOrder(vo);
			Map<String, Object> map = new HashMap<>();
			map.put("orderNumber", vo.getOrderNumber());
			map.put("price", vo.getPrice());
			ajaxJson.setObj(map);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJson.setMsg("操作失败");
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

	/**
	 * 获取最新的一条心电数据
	 * author: wenxian.cai
	 * date: 2017/10/24 10:46
	 */
	@RequestMapping(value = "/ecg/lastest", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJson getLastestEcg(@RequestParam("userId") int userId) {
		AjaxJson ajaxJson = new AjaxJson();
		EcgDTO data = ecg.getLastestData(userId);
		List<EcgDTO> list = Arrays.asList(data);
		ajaxJson.setObj(list);
		return ajaxJson;
	}

	/**
	 * 创建心电分析报告订单
	 * @param userId 用户id
	 * @param content 分析报告内容
	 * @return
	 */
	@RequestMapping(value = "/order/ecg", method = RequestMethod.POST)
	@ResponseBody
	private AjaxJson ecgOrder(@RequestParam("userId") int userId,
							 @RequestParam("content") String content,
                             @RequestParam("status") int status) {
		AjaxJson ajaxJson = new AjaxJson();
		ReportAnalysisOrderVO vo = new ReportAnalysisOrderVO();
		vo.setPrice(0.01);
		vo.setBusinessUserId(getLoginUser().getId());
		UserPO userPO = new UserPO();
		userPO.setId(userId);
		vo.setUserPO(userPO);
		ReportAnalysisPO reportAnalysisPO = new ReportAnalysisPO();
		reportAnalysisPO.setContent(content);
		reportAnalysisPO.setHealthProduct(HealthPackageType.ECG.value());
		reportAnalysisPO.setRequestorType(2);
        reportAnalysisPO.setStatus(status);
		vo.setReportAnalysisPO(reportAnalysisPO);
		try {
			reportAnalysisOrderService.createOrder(vo);
			Map<String, Object> map = new HashMap<>();
			map.put("orderNumber", vo.getOrderNumber());
			map.put("price", vo.getPrice());
			ajaxJson.setObj(map);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJson.setMsg("操作失败");
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

    /**
     * 获取最新的一条血糖数据
     * author: wenxian.cai
     * date: 2017/10/24 10:46
     */
    @RequestMapping(value = "/glu/lastest", method = RequestMethod.GET)
    @ResponseBody
    private AjaxJson getLastestGlu(@RequestParam("userId") int userId) {
        AjaxJson ajaxJson = new AjaxJson();
        GluCometerPO gp = glucometer.getLastestData(userId);
        ajaxJson.setObj(gp);
        return ajaxJson;
    }

    /**
     * 创建血糖分析报告订单
     * @param userId 用户id
     * @param content 分析报告内容
     * @return
     */
    @RequestMapping(value = "/order/glu", method = RequestMethod.POST)
    @ResponseBody
    private AjaxJson gluOrder(@RequestParam("userId") int userId,
                              @RequestParam("content") String content,
                              @RequestParam("status") int status) {
        AjaxJson ajaxJson = new AjaxJson();
        ReportAnalysisOrderVO vo = new ReportAnalysisOrderVO();
        vo.setPrice(0.01);
        vo.setBusinessUserId(getLoginUser().getId());
        UserPO userPO = new UserPO();
        userPO.setId(userId);
        vo.setUserPO(userPO);
        ReportAnalysisPO reportAnalysisPO = new ReportAnalysisPO();
        reportAnalysisPO.setContent(content);
        reportAnalysisPO.setHealthProduct(HealthPackageType.Glucometer.value());
        reportAnalysisPO.setRequestorType(2);
        reportAnalysisPO.setStatus(status);
        vo.setReportAnalysisPO(reportAnalysisPO);
        try {
            reportAnalysisOrderService.createOrder(vo);
            Map<String, Object> map = new HashMap<>();
            map.put("orderNumber", vo.getOrderNumber());
            map.put("price", vo.getPrice());
            ajaxJson.setObj(map);
        } catch (OperationException o) {
            logger.error(o.getMessage());
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
}
