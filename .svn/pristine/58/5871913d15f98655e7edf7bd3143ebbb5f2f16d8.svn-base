package com.lifeshs.controller.help;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.log4testng.Logger;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.pojo.help.HelpDTO;
import com.lifeshs.service.help.IHelpService;
import com.lifeshs.utils.HttpUtils;

/**
 * 帮助中心
 * @author zizhen.huang
 * @DateTime 2018年1月18日17:13:41
 */
@RestController
public class HelpController {

    private  static final Logger logger = Logger.getLogger(HelpController.class);

    @Autowired
    private IHelpService iHelpService;

    /**
     * 帮助中心主页数据
     * @author zizhen.huang
     * @DateTime 2018年1月18日17:20:14
     */
    @RequestMapping(value={"/helpcolumn"})
    public AjaxJson helpCenterIndex(HttpServletRequest request){
        AjaxJson ajaxJson = new AjaxJson();
        String parentName = "帮助中心";
        logger.debug(String.format("IP[%s]访问帮助中心",HttpUtils.getIpAddress(request)));
        HelpDTO help = iHelpService.listHelp(parentName);
        ajaxJson.setObj(help);
        return ajaxJson;
    }

    /**
     * 获取单个帮助文档的具体内容
     * @author zizhen.huang
     * @DateTime 2018年1月18日17:20:53
     */
    @RequestMapping(value={"/help/{id}"})
    public AjaxJson helpCenterIndex(@PathVariable Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        TInformation information = iHelpService.getHelpDetail(id);
        ajaxJson.setObj(information);
        return ajaxJson;
    }
}
