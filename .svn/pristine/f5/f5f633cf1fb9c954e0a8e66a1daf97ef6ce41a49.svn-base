package com.lifeshs.openservice.help;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.config.TestLYConfig;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.pojo.help.HelpDTO;
import com.lifeshs.service.help.IHelpService;
import com.lifeshs.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Yue.Li
 * @Date 2017/4/27.
 */

@RestController
public class HelpController {

    private static final Logger logger = Logger.getLogger(HelpController.class);
    @Autowired
    private IHelpService iHelpService;
    @Autowired
    TestLYConfig testLYConfig;


//    @RequestMapping(value={"/help/{test}"})
//    public String getTest(@PathVariable String test){
//        List<TInfomationColumn> infomationColumnIList= iHelpService.getTest(test);
//        Gson gson = new Gson();
//        String ret =gson.toJson(infomationColumnIList);
//        System.out.println("ret:" + ret);
//        return ret;
//    }

    /**
     * @Description: 帮助中心主页数据
     * @author: wenxian.cai
     * @create: 2017/4/27 17:14
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
     * @Description: 获取单个帮助文档的具体内容
     * @author: wenxian.cai
     * @create: 2017/4/28 11:36
     */
    @RequestMapping(value={"/help/{id}"})
    public AjaxJson helpCenterIndex(@PathVariable Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        TInformation information = iHelpService.getHelpDetail(id);
        ajaxJson.setObj(information);
        return ajaxJson;
    }
}