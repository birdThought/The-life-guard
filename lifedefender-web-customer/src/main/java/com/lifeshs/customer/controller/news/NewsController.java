package com.lifeshs.customer.controller.news;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.po.consult.InformationColumnPO;
import com.lifeshs.po.consult.InformationPO;
import com.lifeshs.service1.consult.ConsultManagerService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.consult.InformationColumnVO;
import com.lifeshs.vo.consult.InformationVO;

/**
 * 资讯管理Controller
 * @author zizhen.huang
 * @DateTime 2018年1月16日15:10:58
 */
@RestController
@RequestMapping(value = "/news")
public class NewsController {

    private static final int PAGESIZE = 10;

	@Resource(name = "consultManagerService")
    private ConsultManagerService consultManagerService;
	
	/**
	 * 资讯管理页面
	 * @author zizhen.huang
	 * @DateTime 2018年1月16日20:34:30
	 * @return
	 */
    @RequestMapping(value = "/page")
    public ModelAndView consultManagerPage() {
    	return new ModelAndView("platform/consult/consultManager");
    }
    
    /**
     * 咨询内容页面
     * @author zizhen.huang
     * @DateTime 2018年1月19日11:47:11
     * @return
     */
    @RequestMapping(value = "/details/page")
    public ModelAndView informationViewPage() {
    	return new ModelAndView("platform/consult/informationView");
    }
    
    /**
     * 资讯信息列表
     * @author zizhen.huang
     * @DateTime 2018年1月17日11:18:44
     * @return
     */
    @RequestMapping(value = "/data/list/{curPage}", method = RequestMethod.GET)
    public AjaxJsonV2 informationList(@RequestParam("parentId") int parentId, @PathVariable("curPage") int curPage) {
    	Paging<InformationPO> paging = consultManagerService.getInformationListById(parentId, curPage, PAGESIZE);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	ajaxJsonV2.setObj(paging.getPagination());
    	return ajaxJsonV2;
    }
    
    /**
     * 二级栏目资讯信息列表
     * @author zizhen.huang
     * @DateTime 2018年1月18日13:40:12
     * @return
     */
    @RequestMapping(value = "/data/list/second/{curPage}", method = RequestMethod.GET)
    public AjaxJsonV2 secondInformationList(@RequestParam("id") int id, @PathVariable("curPage") int curPage) {
    	Paging<InformationPO> paging = consultManagerService.getSecondInformationListById(id, curPage, PAGESIZE);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	ajaxJsonV2.setObj(paging.getPagination());
    	return ajaxJsonV2;
    }
    
    /**
     * 添加资讯
     * @author zizhen.huang
     * @DateTime 2018年1月18日19:54:48
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJsonV2 addInformation(@RequestBody InformationPO informationPO) {
    	boolean success =  consultManagerService.addInformation(informationPO);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	if(!success) {
    		ajaxJsonV2.setMsg("添加失败");
    		ajaxJsonV2.setSuccess(false);
    	}
    	return ajaxJsonV2;
    }
    
    /**
     * 删除资讯
     * @author zizhen.huang
     * @DateTime 2018年1月18日19:59:59
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public AjaxJsonV2 delInformation(@RequestParam("id") int id) {
    	boolean success =  consultManagerService.delInformationById(id);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	if(!success) {
    		ajaxJsonV2.setMsg("删除失败");
    		ajaxJsonV2.setSuccess(false);
    	}
    	return ajaxJsonV2;
    }
    
    /**
     * 修改资讯
     * @author zizhen.huang
     * @DateTime 2018年1月18日20:08:02
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AjaxJsonV2 updateInformation(@RequestBody InformationPO informationPO) {
    	boolean success = consultManagerService.updateInformation(informationPO);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	if(!success) {
    		ajaxJsonV2.setMsg("修改失败");
    		ajaxJsonV2.setSuccess(false);
    	}
    	return ajaxJsonV2;
    }
    
    /**
     * 获取资讯内容
     * @author zizhen.huang
     * @DateTime 2018年1月19日14:52:59
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJsonV2 getInformationContent(@RequestParam("id") int id) {
    	InformationVO informationPO = consultManagerService.getInformationById(id);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	ajaxJsonV2.setObj(informationPO);
    	return ajaxJsonV2;
    }

}
