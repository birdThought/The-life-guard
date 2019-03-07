package com.lifeshs.customer.controller.dd;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.po.data.HealthPackParamPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.measureSuggestion.MeasureSuggestionService;
import com.lifeshs.vo.systemManage.MeasureSuggestionVo;

/**
 * 测量建议控制器
 * 
 * @author shiqiang.zeng
 * @date 2018.1.29 14:31
 */

@RestController
@RequestMapping(value = "/data/measure-suggection")
public class MeasureSuggectionController {

	static final Logger logger = Logger.getLogger(MeasureSuggectionController.class);

	static final int PAGESIZE = 10;

	@Autowired
	MeasureSuggestionService measureSuggestionService;

	@RequestMapping(value = "/page")
	private ModelAndView SuggestionPage() {
		return new ModelAndView("platform/systemmanage/measure-suggection");
	}

	/**
	 * 测量建议页面
	 * 
	 * @param page
	 * @param healthPackageParamId
	 * @return
	 */
	@RequestMapping(value = "/list-suggection/{page}", method = RequestMethod.GET)
	public AjaxJsonV2 listSuggestion(@PathVariable("page") int page,
			@RequestParam(value = "healthPackageParamId", required = false) Integer healthPackageParamId) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		Paging paging = measureSuggestionService.findSuggestion(healthPackageParamId, page, PAGESIZE);
		ajaxJsonV2.setObj(paging.getPagination());
		return ajaxJsonV2;

	}

	/**
	 * 获取健康参数列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/list-healthParam", method = RequestMethod.GET)
	public AjaxJsonV2 listHealthParam() {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		List<HealthPackParamPO> HealthParam = measureSuggestionService.getHealthParam();
		ajaxJsonV2.setObj(HealthParam);
		return ajaxJsonV2;
	}

	/**
	 * 添加测量建议
	 * 
	 * @param SuggestionVo
	 * @return
	 */
	@RequestMapping(value = "add-suggestion", method = RequestMethod.POST)
	public AjaxJsonV2 addSuggestion(MeasureSuggestionVo measureSuggestionVo) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			measureSuggestionService.addSuggestion(measureSuggestionVo);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}

	/**
	 * 编辑测量建议
	 * 
	 * @param SuggestionVo
	 * @return
	 */
	@RequestMapping(value = "edit-suggestion", method = RequestMethod.POST)
	public AjaxJsonV2 editSuggestion(MeasureSuggestionVo measureSuggestionVo) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			measureSuggestionService.updateSuggestion(measureSuggestionVo);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}

	/**
	 * 删除测量建议
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete-suggestion", method = RequestMethod.POST)
	public AjaxJsonV2 delete(@RequestParam("id") Integer id) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			measureSuggestionService.deleteSuggestion(id);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}

}
