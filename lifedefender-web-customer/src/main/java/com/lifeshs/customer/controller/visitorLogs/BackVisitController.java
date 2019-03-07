package com.lifeshs.customer.controller.visitorLogs;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.admin.adminPO;
import com.lifeshs.po.user.AdminRolePO;
import com.lifeshs.service1.admin.AdminRoleService;
import com.lifeshs.service1.admin.adminService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.visit.OperatingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-02-06
 * 15:19  获取后台操作数据
 * @desc
 */
@Controller
@RequestMapping("/pv/back")
public class BackVisitController extends BaseController {

    private static final int PAGE_SIZE = 15;
    @Autowired
    private adminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;

    @RequestMapping("/login/page")
    public ModelAndView getBackLog(){
        return new ModelAndView("platform/visitorLog/BackstageLog");
    }

    @RequestMapping("/visit/page")
    public ModelAndView getOpera(){
        return new ModelAndView("platform/visitorLog/controlRecord");
    }

    @RequestMapping(value = "/login/{page}")
    public @ResponseBody AjaxJson getBack(@PathVariable("page")Integer page){
        AjaxJson ajaxJson = new AjaxJson();
        Paging<adminPO> p = adminService.getBackLogData(page, PAGE_SIZE);
        ajaxJson.setObj(p.getPagination());
        return ajaxJson;
    }


    @RequestMapping(value = "/visit/{page}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getOperatingData(@PathVariable("page")Integer page, @RequestParam("type")Integer type,@RequestParam("date") String date,
                                                   @RequestParam("realName") String realName){
        AjaxJson ajaxJson = new AjaxJson();
        if (type == -1){
            type = null;
        }
        if ("".equals(date)){
            date = null;
        }
        if ("".equals(realName)){
            realName = null;
        }
        Paging<OperatingVo> p = adminService.getByOperatingData(type,date,realName,page,PAGE_SIZE);
        ajaxJson.setObj(p.getPagination());
        return ajaxJson;
    }

}
