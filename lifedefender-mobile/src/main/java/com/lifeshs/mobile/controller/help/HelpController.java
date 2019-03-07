package com.lifeshs.mobile.controller.help;

import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.mobile.controller.common.BaseController;
import com.lifeshs.pojo.help.HelpDetailDto;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.information.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author Yue.Li
 * @Date 2017/4/26.
 */
@Controller
@RequestMapping(value = {"/help"})
public class HelpController extends BaseController{

    /*@Autowired
    private InformationService informationService;

    @Autowired
    ICommonTrans commonTrans;*/

    @RequestMapping(value = {"/index",""})
    public ModelAndView index(@RequestParam(required = false) Integer column, @RequestParam(required = false) Integer page, @RequestParam(required = false) String search){
        ModelAndView modelAndView = new ModelAndView("help/index");

        return modelAndView;
    }
    @RequestMapping(value = {"/help-detail/{id}"})
    public ModelAndView helpDetail(@PathVariable("id") int id){
        HelpDetailDto helpDetailsDTO  =new HelpDetailDto();
        helpDetailsDTO.setContent("内容详情"+id);
        ModelAndView modelAndView = new ModelAndView("help/help-detail");
        /*TInformation information = commonTrans.get(TInformation.class, id);
        String columnName = commonTrans.get(TInfomationColumn.class, information.getColumnId()).getName();
        modelAndView.addObject("columns",informationService.loadColumnByParentColName("帮助中心"));
        modelAndView.addObject("info", information);
        modelAndView.addObject("columnName", columnName);*/
        return modelAndView;
    }
}
