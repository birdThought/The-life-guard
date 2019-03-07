package com.lifeshs.customer.controller.business;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.business.BusinessPo;
import com.lifeshs.service1.business.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 * @create 2018-03-22
 * 9:45     客服添加渠道商管理员
 * @desc
 */
@Controller
@RequestMapping("/business/add")
public class BusinessAddController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping("/page")
    public ModelAndView DisplayAddList(){
        return new ModelAndView("platform/business/addbusiness");
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public @ResponseBody AjaxJson addBusinessData(@RequestParam("name")String name, @RequestParam("status")Integer status, @RequestParam("logo")String logo,
                                                  @RequestParam("contactMan")String contactMan, @RequestParam("phone")String phone, @RequestParam("email")String email,
                                                  @RequestParam("type")Integer type, @RequestParam("userName")String userName, @RequestParam("password")String password,
                                                  BusinessPo bp){
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(false);
        if (userName != "" && password != ""){
            int i = userService.saveBusiness(bp, userName, password);
            if (i != 0){
                ajaxJson.setSuccess(true);
                return ajaxJson;
            }
        }
        return ajaxJson;
    }
}
