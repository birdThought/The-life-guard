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
import com.lifeshs.entity.data.TDataSportKind;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.sport.SportService;
import com.lifeshs.service1.systemManage.sportKind.SportKindService;
import com.lifeshs.vo.systemManage.SportVo;

@RestController
@RequestMapping(value = "/data/sport")
public class SportController {

	static final Logger logger = Logger.getLogger(SportController.class);

	static final int PAGESIZE = 10;

	@Autowired
	SportService sportService;

	@Autowired
	SportKindService sportKindService;

	@RequestMapping(value = "/page")

	private ModelAndView SportPage() {
		return new ModelAndView("platform/systemmanage/sport");
	}

	/**
	 * 运动管理页面
	 * 
	 * @param page
	 * @param kind
	 * @return
	 */
	@RequestMapping(value = "/list-sport/{page}", method = RequestMethod.GET)
	public AjaxJsonV2 listSport(@PathVariable("page") int page,
			@RequestParam(value = "kind", required = false) Integer kind,
			@RequestParam(value = "name", required = false) String name) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		Paging paging = sportService.findSport(kind, name, page, PAGESIZE);
		ajaxJsonV2.setObj(paging.getPagination());
		return ajaxJsonV2;
	}

	/**
	 * 获取运动种类列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/list-sportKind", method = RequestMethod.GET)
	public AjaxJsonV2 listSportKind() {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		List<TDataSportKind> SportKinds = sportKindService.getSportKind();
		ajaxJsonV2.setObj(SportKinds);
		return ajaxJsonV2;
	}

	/**
	 * 添加运动
	 * 
	 * @param SportVo
	 * @return
	 */
	@RequestMapping(value = "add-sport", method = RequestMethod.POST)
	public AjaxJsonV2 addSport(SportVo SportVo) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			sportService.addSport(SportVo);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}

	/**
	 * 编辑运动
	 * 
	 * @param SportVo
	 * @return
	 */
	@RequestMapping(value = "edit-sport", method = RequestMethod.POST)
	public AjaxJsonV2 editSport(SportVo SportVo) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			sportService.updateSport(SportVo);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}

	/**
	 * 删除运动
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete-sport", method = RequestMethod.POST)
	public AjaxJsonV2 delete(@RequestParam("id") Integer id) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			sportService.deleteSport(id);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}
}
