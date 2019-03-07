package com.lifeshs.customer.controller.news;

import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.po.consult.InformationColumnPO;
import com.lifeshs.po.consult.InformationPO;
import com.lifeshs.service1.consult.ConsultManagerService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.consult.InformationColumnVO;
import com.lifeshs.vo.consult.InformationVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资讯栏目管理Controller
 * @author zizhen.huang
 * @DateTime 2018年1月16日15:10:58
 */
@RestController
@RequestMapping(value = "/news/column")
public class NewsColumnController {

    private static final int PAGESIZE = 10;

	@Resource(name = "consultManagerService")
    private ConsultManagerService consultManagerService;
	
    /**
     * 栏目管理页面
     * @author zizhen.huang
     * @DateTime 2018年1月24日15:45:46
     * @return
     */
    @RequestMapping(value = "/page")
    public ModelAndView columnManagerPage() {
    	return new ModelAndView("platform/consult/columnManager");
    }
    
    /**
     * 初始化显示的资讯栏目
     * @author zizhen.huang
     * @DateTime 2018年1月17日11:10:22
     * @return
     */
    @RequestMapping(value = "/data/list", method = RequestMethod.GET)
    public AjaxJsonV2 initList() {
    	List<InformationColumnVO> columnList =  consultManagerService.getColumnListById(0);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	ajaxJsonV2.setObj(columnList);
    	return ajaxJsonV2;
    }
    
    /**
     * 获取子栏目
     * @author zizhen.huang
     * @DateTime 2018年1月22日10:40:38
     * @return
     */
    @RequestMapping(value = "/getChild", method = RequestMethod.GET)
    public AjaxJsonV2 getChildColumns(@RequestParam("id") int id) {
    	List<InformationColumnPO> childColumns =  consultManagerService.getChildColumnById(id);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	ajaxJsonV2.setObj(childColumns);
    	return ajaxJsonV2;
    }
    
    /**
     * 添加栏目
     * @author zizhen.huang
     * @DateTime 2018年1月19日16:18:41
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJsonV2 addColumn(@RequestBody InformationColumnPO informationColumnPO) {
    	boolean success = consultManagerService.addColumn(informationColumnPO);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	if(!success) {
    		ajaxJsonV2.setMsg("添加失败");
    		ajaxJsonV2.setSuccess(false);
    	}
    	return ajaxJsonV2;
    }
    
    /**
     * 删除栏目
     * @author zizhen.huang
     * @DateTime 2018年1月19日16:21:29
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public AjaxJsonV2 delColumn(@RequestParam("id") int id) {
    	boolean success = consultManagerService.delColumnById(id);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	if(!success) {
    		ajaxJsonV2.setMsg("删除失败");
    		ajaxJsonV2.setSuccess(false);
    	}
    	return ajaxJsonV2;
    }
    
    /**
     * 修改栏目
     * @author zizhen.huang
     * @DateTime 2018年1月19日16:25:06
     * @param informationColumnPO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AjaxJsonV2 updateColumn(@RequestBody InformationColumnPO informationColumnPO) {
    	boolean success = consultManagerService.updateColumn(informationColumnPO);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	if(!success) {
    		ajaxJsonV2.setMsg("修改失败");
    		ajaxJsonV2.setSuccess(false);
    	}
    	return ajaxJsonV2;
    }
}
