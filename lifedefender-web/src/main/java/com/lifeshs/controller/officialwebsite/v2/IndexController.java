package com.lifeshs.controller.officialwebsite.v2;


import com.alibaba.fastjson.JSON;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.common.model.DataResult;
import com.lifeshs.po.OrgPO;
import com.lifeshs.po.visitor.FeedBackPO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.paper.PaperDTO;
import com.lifeshs.pojo.paper.PaperOptionDTO;
import com.lifeshs.pojo.paper.PaperPhysicalStandardDTO;
import com.lifeshs.service.information.InformationService;
import com.lifeshs.service.paper.IPaperService;
import com.lifeshs.service1.serve.consult.ConsultService;
import com.lifeshs.service1.serve.lesson.LessonService;
import com.lifeshs.service1.serve.visit.VisitService;
import com.lifeshs.service1.store.StoreService;
import com.lifeshs.service1.visitor.IVisitorService;
import com.lifeshs.utils.HttpUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 官网新版版  V2.5
 * author: wenxian.cai
 * date: 2017/11/28 10:03
 */
@Controller("indexControllerV2.5")
@RequestMapping("/index/v2.5")
public class IndexController {

	@Resource(name = "v2StoreService")
	private StoreService storeService;

	@Resource(name = "v2LessonService")
	private LessonService lessonService;

	@Resource(name = "v2ConsultService")
	private ConsultService consultSerivce;

	@Resource(name = "v2VisitService")
	private VisitService visitService;

	@Autowired
	IPaperService question;

	@Autowired
	private InformationService informationService;

	@Autowired
	private IVisitorService visitorService;
	/**
	 * 首页
	 * author: wenxian.cai
	 * date: 2017/11/24 14:22
	 */
	@RequestMapping(value = "/home")
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/home/home");
		DataResult dataResult = informationService.loadNewsInformationDatas("资讯信息", null, 1, null, 4);
		PaginationDTO informations = (PaginationDTO) dataResult.getAttr().get("informations");
		List list = informations.getDataObject();
		modelAndView.addObject("information", JSON.toJSON(informations.getDataObject()));
		return modelAndView;
	}

	/**
	 * 首页-公司动态
	 * author: wenxian.cai
	 * date: 2017/11/24 17:02
	 */
	@RequestMapping(value = "/home/news")
	public ModelAndView newsPage(@RequestParam(required = false) Integer f,
								 @RequestParam(required = false) Integer page,
								 @RequestParam(required = false) String search) {
		ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/home/news");
		modelAndView.addObject("data", informationService.loadNewsInformationDatas("资讯信息",f, page, search, null));
		if (f!=null)
			modelAndView.addObject("f",f);
		if (search!=null)
			modelAndView.addObject("search",search);

		return modelAndView;
	}

	/**
	 * 首页-公司动态详细
	 * author: wenxian.cai
	 * date: 2017/11/27 11:55
	 */
	@RequestMapping(value = "/home/news-details")
	public ModelAndView newsDetailsPage(@RequestParam Integer id) {
		ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/home/news-details");
		DataResult result = informationService.lookNewsById(id);
		modelAndView.addObject("info", result.get("info"));
		modelAndView.addObject("columnName", result.get("columnName"));
		modelAndView.addObject("hot",result.get("hot"));
		modelAndView.addObject("hangye",result.get("hangye"));
		return modelAndView;
	}

	/**
	 * 知识库
	 * author: wenxian.cai
	 * date: 2017/11/27 11:57
	 */
	@RequestMapping(value = "/home/repository")
	public ModelAndView repositoryPage() {
		return new ModelAndView("officialwebsite/v2.5.0/home/repository");
	}

	/**
	 * 健康管理平台与系统
	 * author: wenxian.cai
	 * date: 2017/11/24 16:58
	 */
	@RequestMapping(value = "/product/platform")
	public ModelAndView platformPage() {
		return new ModelAndView("officialwebsite/v2.5.0/product/platform");
	}

	/**
	 * 产品与平台
	 * author: wenxian.cai
	 * date: 2017/11/27 10:56
	 */
	@RequestMapping(value = "/product/user-app")
	public ModelAndView userAppPage() {
		return new ModelAndView("officialwebsite/v2.5.0/product/user-app");
	}

	/**
	 * 产品与平台-机构app
	 * author: wenxian.cai
	 * date: 2017/11/27 10:53
	 */
	@RequestMapping(value = "/product/store-app")
	public ModelAndView storeAppPage() {
		return new ModelAndView("officialwebsite/v2.5.0/product/store-app");
	}

	/**
	 * 智能设备
	 * author: wenxian.cai
	 * date: 2017/11/24 16:16
	 */
	@RequestMapping(value = "/product/device")
	public ModelAndView devicePage() {
		return new ModelAndView("officialwebsite/v2.5.0/product/device");
	}
	
	/**
     * 隐私政策
     * author: wenxian.cai
     * date: 2017/11/24 16:16
     */
    @RequestMapping(value = "/product/privacy-policy")
    public ModelAndView privacyPolicyPage() {
        return new ModelAndView("officialwebsite/v2.5.0/product/privacy-policy");
    }

	/**
	 * 专业服务-全科医生
	 * author: wenxian.cai
	 * date: 2017/11/24 14:22
	 */
	@RequestMapping(value = "/serve/doctor")
	public ModelAndView doctorPage() {
		return new ModelAndView("officialwebsite/v2.5.0/serve/doctor");
	}

	/**
	 * 专业服务-全科医生详细
	 * author: wenxian.cai
	 * date: 2017/11/27 9:30
	 */
	@RequestMapping(value = "/serve/doctor-details/{id}")
	public ModelAndView doctorDetailsPage(@PathVariable("id") int id) {

		return new ModelAndView("officialwebsite/v2.5.0/serve/doctor-details-" + id);
	}

	/**
	 * 专业服务-健康套餐
	 * author: wenxian.cai
	 * date: 2017/11/24 17:00
	 */
	@RequestMapping(value = "/serve/health-combo")
	public ModelAndView healthComboPage() {
		return new ModelAndView("officialwebsite/v2.5.0/serve/health-combo");
	}

	/**
	 * 专业服务-健康套餐详情
	 * author: wenxian.cai
	 * date: 2017/11/27 10:58
	 */
	@RequestMapping(value = "/serve/health-combo-details")
	public ModelAndView healthComboDetailsPage() {
		return new ModelAndView("officialwebsite/v2.5.0/serve/health-combo-details");
	}

	/**
	 * 健康评估(体质分析)
	 * author: wenxian.cai
	 * date: 2017/11/30 13:59
	 */
	@RequestMapping(value = "/serve/paper/physical")
	public ModelAndView physicalPage() {
		ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/serve/paper/physical-paper");
		List<PaperDTO> questionnaireDTOs = question.listPaper("体质");
		List<PaperOptionDTO> optionDTOs = question.listPaperOption("体质");
		modelAndView.addObject("paper", JSON.toJSON(questionnaireDTOs));
		modelAndView.addObject("paperOption", JSON.toJSON(optionDTOs));
		return modelAndView;
	}

	/**
	 * 健康评估结果
	 * author: wenxian.cai
	 * date: 2017/11/30 14:00
	 */
	@RequestMapping(value = "/serve/paper/physical-analyze")
	public ModelAndView physicalAnalyzePage(@RequestParam(value = "result") String physicalName,
											@RequestParam(value = "scoreArr") List<Integer> scoreArr) {
		ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/serve/paper/physical-analyze");
		PaperPhysicalStandardDTO physicalStandardDTO = question.getPaperPhysicalStandard(physicalName);
		modelAndView.addObject("physicalStandard", JSON.toJSON(physicalStandardDTO));
		modelAndView.addObject("scoreArr", scoreArr);
		return modelAndView;
	}

	/**
	 * 健康机构服务
	 * author: wenxian.cai
	 * date: 2017/11/24 15:32
	 */
	@RequestMapping(value = "/serve/store")
	public ModelAndView storePage() {
		return new ModelAndView("officialwebsite/v2.5.0/serve/store");
	}

	/**
	 * 健康机构服务详情
	 * author: wenxian.cai
	 * date: 2017/11/24 16:13
	 */
	@RequestMapping(value = "/serve/store-details/{id}")
	public ModelAndView storeDetailsPage(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/serve/store-details");
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	/**
	 * 合作案例
	 * author: wenxian.cai
	 * date: 2017/11/24 15:51
	 */
	@RequestMapping(value = "/case")
	public ModelAndView casePage() {
		return new ModelAndView("officialwebsite/v2.5.0/case/case");
	}

	/**
	 * 合作案例1
	 * author: wenxian.cai
	 * date: 2017/11/27 9:31
	 */
	@RequestMapping(value = "/case/case-1")
	public ModelAndView case1Page() {
		return new ModelAndView("officialwebsite/v2.5.0/case/case-1");
	}

	/**
	 * 合作案例2
	 * author: wenxian.cai
	 * date: 2017/11/27 9:31
	 */
	@RequestMapping(value = "/case/case-2")
	public ModelAndView case2Page() {
		return new ModelAndView("officialwebsite/v2.5.0/case/case-2");
	}


	/**
	 * 合作案例3
	 * author: wenxian.cai
	 * date: 2017/11/27 9:31
	 */
	@RequestMapping(value = "/case/case-3")
	public ModelAndView case3Page() {
		return new ModelAndView("officialwebsite/v2.5.0/case/case-3");
	}


	/**
	 * 合作案例4
	 * author: wenxian.cai
	 * date: 2017/11/27 9:31
	 */
	@RequestMapping(value = "/case/case-4")
	public ModelAndView case4Page() {
		return new ModelAndView("officialwebsite/v2.5.0/case/case-4");
	}


	/**
	 * 合作案例5
	 * author: wenxian.cai
	 * date: 2017/11/27 9:31
	 */
	@RequestMapping(value = "/case/case-5")
	public ModelAndView case5Page() {
		return new ModelAndView("officialwebsite/v2.5.0/case/case-5");
	}


	/**
	 * 合作案例6
	 * author: wenxian.cai
	 * date: 2017/11/27 9:31
	 */
	@RequestMapping(value = "/case/case-6")
	public ModelAndView case6Page() {
		return new ModelAndView("officialwebsite/v2.5.0/case/case-6");
	}

	/**
	 * 帮助中心-产品说明书
	 * author: wenxian.cai
	 * date: 2017/11/24 16:59
	 */
	@RequestMapping(value = "/help/product-instruction")
	public ModelAndView productIntroductionPage() {
		return new ModelAndView("officialwebsite/v2.5.0/help/product-instruction");
	}

	/**
	 * 帮助中心-产品视频
	 * author: wenxian.cai
	 * date: 2017/11/27 10:54
	 */
	@RequestMapping(value = "/help/product-video")
	public ModelAndView productVideoPage() {
		return new ModelAndView("officialwebsite/v2.5.0/help/product-video");
	}


	/**
	 * 帮助中心-问题回馈
	 * author: wenxian.cai
	 * date: 2017/11/27 9:28
	 */
	@RequestMapping(value = "/help/feedback")
	public ModelAndView feedbackPage() {
		return new ModelAndView("officialwebsite/v2.5.0/help/feedback");
	}

	/**
	 * 公司简介
	 * author: wenxian.cai
	 * date: 2017/11/24 16:17
	 */
	@RequestMapping(value = "/about/introduction")
	public ModelAndView introductionPage() {
		return new ModelAndView("officialwebsite/v2.5.0/about/introduction");
	}

	/**
	 * 招聘合作伙伴
	 * author: wenxian.cai
	 * date: 2017/11/24 15:32
	 */
	@RequestMapping(value = "/about/partner")
	public ModelAndView partnerPage() {
		return new ModelAndView("officialwebsite/v2.5.0/about/partner");
	}

	/**
	 * 关于我们-联系我们
	 * author: wenxian.cai
	 * date: 2017/11/27 10:32
	 */
	@RequestMapping(value = "/about/contact")
	public ModelAndView contactPage() {
		return new ModelAndView("officialwebsite/v2.5.0/about/contact");
	}

	/**
	 * 招聘人才
	 * author: wenxian.cai
	 * date: 2017/11/24 15:29
	 */
	@RequestMapping(value = "/about/recruitment")
	public ModelAndView recruitmentPage() {
		return new ModelAndView("officialwebsite/v2.5.0/about/recruitment");
	}

	/**
	 * 关于我们-二维码下载
	 * author: wenxian.cai
	 * date: 2017/11/27 10:50
	 */
	@RequestMapping(value = "/about/download-code")
	public ModelAndView downloadCodePage() {
		return new ModelAndView("officialwebsite/v2.5.0/about/download-code");
	}


	/**
	 * 获取医生列表
	 * author: wenxian.cai
	 * date: 2017/11/28 10:09
	 */
	@RequestMapping(value = "/serve/doctors/{page}", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJsonV2 listDoctor(@PathVariable("page") int page) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		return ajaxJsonV2;
	}

	/**
	 * 获取医生详细信息
	 * author: wenxian.cai
	 * date: 2017/11/28 10:07
	 */
	@RequestMapping(value = "/serve/doctor/{id}", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJsonV2 getDoctor(@PathVariable("id") int id) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		return ajaxJsonV2;
	}

	/**
	 * 获取机构列表
	 * author: wenxian.cai
	 * date: 2017/11/28 10:30
	 */
	@RequestMapping(value = "/serve/stores/{page}", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJsonV2 listStore(@PathVariable("page") int page) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		List<OrgPO> ros = storeService.listStore(0);
//		List<Map<String, Object>> returnDataList = enclosureOrg(ros, false);
		ajaxJsonV2.setObj(ros);
		return ajaxJsonV2;
	}

	/**
	 * 获取机构详情
	 * author: wenxian.cai
	 * date: 2017/11/28 10:31
	 */
	@RequestMapping(value = "/serve/store/{id}", method = RequestMethod.GET)
	@ResponseBody
	private AjaxJsonV2 getStore(@PathVariable("id") int id) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		OrgPO data = storeService.getStore(id);
//		List<Map<String, Object>> returnDataList = enclosureOrg(ros, false);
		ajaxJsonV2.setObj(data);
		return ajaxJsonV2;
	}


	/**
	 * 添加游客反馈信息
	 * author: wenxian.cai
	 * date: 2017/11/30 15:08
	 */
	@RequestMapping(value = "/help/feedback", method = RequestMethod.POST)
	@ResponseBody
	private AjaxJsonV2 addFeedBack(@RequestBody FeedBackPO po, HttpServletRequest request) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			po.setIp(HttpUtils.getIpAddress(request));
			visitorService.addFeedBack(po);
		} catch (OperationException e) {
			e.printStackTrace();
			ajaxJsonV2.setSuccess(false);
			ajaxJsonV2.setMsg(e.getMessage());
		}
		return ajaxJsonV2;
	}


	/**
	 *  机构模型封装
	 *  @author yuhang.weng
	 *	@DateTime 2017年7月12日 下午2:52:10
	 *
	 *  @param data
	 *  @param total
	 *  @return
	 */
	/*private Map<String, Object> enclosureOrg(OrgPO data, boolean total) {
		int orgId = data.getId();

		Map<String, Object> org = new HashMap<>();
		org.put(Org.LOGO, data.getLogo());
		org.put(Org.NAME, data.getOrgName());
		org.put(Org.ID, orgId);

		if (total) {
			int consumeCount = storeService.getStoreConsumeMemberNumber(orgId);
			List<ServeUserVO> orgServeUserList = consultSerivce.listOrgConsultServe(orgId);
			List<Map<String, Object>> consult = enclosureConsult(orgServeUserList, true);

			List<RecommendedLessonDTO> orgLessonList = lessonService.listLessonByOrgId(orgId);
			List<Map<String, Object>> lesson = enclosureLessonServe(orgLessonList, true);

			List<RecommendedComboDTO> orgComboList = visitService.listOrgProjectCombo(orgId, null);
			// 健康养生
			List<RecommendedComboDTO> heathComboT = new ArrayList<>();
			// 居家养老
			List<RecommendedComboDTO> homeBaseCareComboT = new ArrayList<>();
			for (RecommendedComboDTO c : orgComboList) {
				String code = c.getCombo().getVisitServe().getServe().getCode();
				if (ServeType.SERVE_HEALTH.getCode().equals(code)) {
					heathComboT.add(c);
				}
				if (ServeType.SERVE_VISIT.getCode().equals(code)) {
					homeBaseCareComboT.add(c);
				}
			}
			List<Map<String, Object>> health = enclosureVisitServe(heathComboT);
			List<Map<String, Object>> homeBaseCare = enclosureVisitServe(homeBaseCareComboT);

			org.put(OrgServe.CONSUME_PERSON_TIME, consumeCount);
			org.put(Org.ABOUT, data.getAbout());
			org.put(Area.LONGITUDE, data.getLongitude());
			org.put(Area.LATITUDE, data.getLatitude());
			org.put(Area.ADDRESS, data.getStreet());
			org.put(Org.TEL, data.getTel());

			org.put(ProjectServe.CONSULT, consult);
			org.put(ProjectServe.LESSON, lesson);
			org.put(ProjectServe.HEALTH, health);
			org.put(ProjectServe.HOME_BASED_CARE, homeBaseCare);
		}

		return org;
	}*/
}
