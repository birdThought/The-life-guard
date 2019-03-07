package com.lifeshs.customer.controller.systemManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.pojo.weixin.menu.ButtonDTO;
import com.lifeshs.pojo.weixin.menu.MenuDTO;
import com.lifeshs.thirdservice.WeiXinGZPTApi;

@RestController
@RequestMapping(value = "/weixin")
public class WeixinController {

    @Autowired
    private WeiXinGZPTApi weixinApi;
    
    @RequestMapping(value = "index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/platform/systemmanage/weixin-menu-setting");
        return modelAndView;
    }
    
    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public JSONObject getMenu() {
        JSONObject data = new JSONObject();
        data.put("menu", weixinApi.getMenu());
        data.put("success", true);
        return data;
    }
    
    @RequestMapping(value = "menu", method = RequestMethod.POST)
    public JSONObject updateMenu(@RequestBody List<ButtonDTO> buttonList) throws Exception {
        JSONObject returnData = new JSONObject();
        returnData.put("success", true);
        MenuDTO menu = new MenuDTO();
        menu.setButton(buttonList);
        weixinApi.createMenu(menu);
        return returnData;
    }
}
