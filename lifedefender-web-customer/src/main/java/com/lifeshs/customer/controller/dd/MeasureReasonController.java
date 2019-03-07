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
import com.lifeshs.service1.systemManage.measureReason.MeasureReasonService;
import com.lifeshs.vo.systemManage.MeasureReasonVo;

/**
 * 测量原因控制器
 * 
 * @author shiqiang.zeng
 * @date 2018.1.30 11:23
 */

@RestController
@RequestMapping(value = "/data/measure-reason")
public class MeasureReasonController {

	static final Logger logger = Logger.getLogger(MeasureReasonController.class);

	static final int PAGESIZE = 10;

	@Autowired
	MeasureReasonService measureReasonService;

	@RequestMapping(value = "/page")
	private ModelAndView ReasonPage() {
		return new ModelAndView("platform/systemmanage/measure-reason");
	}

	/**
	 * 测量原因页面
	 * 
	 * @param page
	 * @param healthPackageParamId
	 * @return
	 */
	@RequestMapping(value = "/list-reason/{page}", method = RequestMethod.GET)
	public AjaxJsonV2 listReason(@PathVariable("page") int page,
			@RequestParam(value = "healthPackageParamId", required = false) Integer healthPackageParamId) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		Paging paging = measureReasonService.findReason(healthPackageParamId, page, PAGESIZE);
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
		List<HealthPackParamPO> HealthParam = measureReasonService.getHealthParam();
		ajaxJsonV2.setObj(HealthParam);
		return ajaxJsonV2;
	}

	/**
	 * 添加测量原因
	 * 
	 * @param ReasonVo
	 * @return
	 */
	@RequestMapping(value = "add-reason", method = RequestMethod.POST)
	public AjaxJsonV2 addReason(MeasureReasonVo measureReasonVo) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			measureReasonService.addReason(measureReasonVo);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}

	/**
	 * 编辑测量原因
	 * 
	 * @param ReasonVo
	 * @return
	 */
	@RequestMapping(value = "edit-reason", method = RequestMethod.POST)
	public AjaxJsonV2 editReason(MeasureReasonVo measureReasonVo) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			measureReasonService.updateReason(measureReasonVo);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}

	/**
	 * 删除测量原因
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete-reason", method = RequestMethod.POST)
	public AjaxJsonV2 deleteReason(@RequestParam("id") Integer id) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			measureReasonService.deleteReason(id);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}

}
