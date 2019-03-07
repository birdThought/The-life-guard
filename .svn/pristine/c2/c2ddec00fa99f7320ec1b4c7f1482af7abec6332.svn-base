package com.lifeshs.customer.controller.dd;

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
import com.lifeshs.service1.systemManage.sportKind.SportKindService;


@RestController
@RequestMapping(value="/data/sportKind")
public class SportKindController {
	
	static final Logger logger = Logger.getLogger(SportKindController.class);

	static final int PAGESIZE = 10;

	@Autowired
	SportKindService sportKindService;
	
	/**
	 * 运动种类页面
	 * @return
	 */
	@RequestMapping(value = "/page")

	private ModelAndView SportKindPage() {
		return new ModelAndView("platform/systemmanage/sportKind");
	}

	@RequestMapping(value = "/list-sportKind/{page}", method = RequestMethod.GET)
	public AjaxJsonV2 listSportKind(@PathVariable("page") int page) {
		Paging paging = sportKindService.findSportKind(page, PAGESIZE);
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		ajaxJsonV2.setObj(paging.getPagination());
		return ajaxJsonV2;

	}
	
	/**
	 * 添加运动种类
	 * @param tDataSportKind
	 * @return
	 */
	@RequestMapping(value="add-sportKind",method=RequestMethod.POST)
	public AjaxJsonV2 addSportKind(TDataSportKind tDataSportKind) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		try {
			sportKindService.addSportKind(tDataSportKind);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
		
	}
	
	/**
	 * 更改运动种类
	 * @param tDataSportKind
	 * @return
	 */
	@RequestMapping(value="update-sportKind",method=RequestMethod.POST)
	public AjaxJsonV2 updateSportKind(TDataSportKind tDataSportKind) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		try {
			sportKindService.updateSportKind(tDataSportKind);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}
	
	/**
	 * 删除运动种类
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete-sportKind",method=RequestMethod.POST)
	public AjaxJsonV2 deleteSportKind(@RequestParam("id") Integer id) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		try {
			sportKindService.deleteSportKind(id);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}
}
