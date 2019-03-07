package com.lifeshs.business.controller.codePackage;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.dao1.business.IUserDao;
import com.lifeshs.po.business.BusinessCodePO;
import com.lifeshs.po.business.BusinessUserPO;
import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service1.business.BusinessCodeService;
import com.lifeshs.service1.page.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-03-08
 * 11:46   二维码管理控制器
 * @desc
 */
@Controller("codePackageController")
@RequestMapping("/code")
public class CodePackageController extends BaseController{

    private static final int PAGESIZE = 15;

    @Autowired
    private BusinessCodeService businessCodeService;

    /**
     * 列表界面
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView findList(){
        return new ModelAndView("platform/package/package");
    }


    @RequestMapping(value = "pack",method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getPackage(){
        AjaxJson ajaxJson = new AjaxJson();
        List<VipComboPO> vipComboPO = businessCodeService.fingByName();
        ajaxJson.setObj(vipComboPO);
        return ajaxJson;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody AjaxJson addPackage(@RequestParam("ageId") Integer ageId){
        LoginUser loginUser = getLoginUser();
        Integer id = loginUser.getId();
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(false);
        int i = businessCodeService.addPackage(id, ageId);
        if (i == 0){
            ajaxJson.setMsg("添加失败！");
            return ajaxJson;
        }
        ajaxJson.setSuccess(true);
        return ajaxJson;
    }

    @RequestMapping(value = "/data/{page}",method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getDataList(@PathVariable("page")Integer page){
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = getLoginUser();
        Integer id = loginUser.getId();
        Paging<BusinessCodePO> data = businessCodeService.obtainData(id,page,PAGESIZE);
        PaginationDTO<BusinessCodePO> p = data.getPagination();
        ajaxJson.setObj(p);
        return ajaxJson;
    }

   @RequestMapping(value = "/del/{id}",method = RequestMethod.GET)
    public  @ResponseBody AjaxJson delData(@PathVariable("id")Integer id){
        AjaxJson ajaxJson = new AjaxJson();
       int del = businessCodeService.del(id);
       if (del == 0){
           ajaxJson.setSuccess(false);
           return ajaxJson;
       }
       return ajaxJson;
    }
}
