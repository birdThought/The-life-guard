package com.lifeshs.customer.controller.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.service.data.IDataAreaService;

/**
 * @author Administrator
 * @create 2018-01-25
 * 10:23
 * @desc
 */
@Controller("memberController")
@RequestMapping("/member")
public class MemberController {
    
    @Autowired
    IDataAreaService areaService;

    @RequestMapping("/page")
    public ModelAndView MemberList(){
        return new ModelAndView("platform/member/memberList");
    }

    @RequestMapping("/count/page")
    public ModelAndView MemberCount(){
        return new ModelAndView("platform/member/memberCount");
    }

    @RequestMapping("/c3/page")
    public ModelAndView MemberC3(){
        return new ModelAndView("platform/member/C3page");
    }

    @RequestMapping("/hx/page")
    public ModelAndView MemberHx(){
        return new ModelAndView("platform/member/hxUnregister");
    }

    @RequestMapping("/report/page")
    public ModelAndView MenberReport(){
        return new ModelAndView("platform/member/userReport");
    }
    
    @RequestMapping("/offline/page")
    public ModelAndView MemberOffline(){
        return new ModelAndView("platform/member/memberOffline");
    }
    
    @RequestMapping("/offline/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("login/register");
        List<Map<String, String>> province = areaService.findAllProvince();
        List<Map<String, String>> city = areaService.findCity("^11[0-9]{2}[0]{2}");// 默认为北京
        List<Map<String, String>> district = areaService.findDistrict("^11[0-9]{2}[0-9][1-9]");
        modelAndView.addObject("province", province);
        modelAndView.addObject("city", city);
        modelAndView.addObject("area", district);
        
        return modelAndView;
    }
}
