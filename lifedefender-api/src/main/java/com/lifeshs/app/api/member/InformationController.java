package com.lifeshs.app.api.member;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.service.terminal.app.information.IAppInformationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 资讯信息接口
 *
 * @author wenxian.cai
 * @create 2017-05-02 13:38
 **/

@RestController(value = "appInformationController")
@RequestMapping(value = { "/app", "/app/information" })
public class InformationController {

    @Resource(name = "appInformationService")
    private IAppInformationService informationService;

    /**
     * @Description: 添加资讯收藏
     * @author: wenxian.cai
     * @create: 2017/5/2 14:58
     */
    @RequestMapping(value = "addInformationCollect", method = RequestMethod.POST)
    public JSONObject addInformationCollect(@RequestParam String json) throws Exception {
        return informationService.addInformationCollect(json);
    }

    /**
     * @Description: 删除资讯收藏
     * @author: wenxian.cai
     * @create: 2017/5/2 14:59
     */
    @RequestMapping(value = "deleteInformationCollect", method = RequestMethod.POST)
    public JSONObject deleteInformationCollect(@RequestParam String json) throws Exception {
        return informationService.deleteInformationCollect(json);
    }

    /**
     * @Description: 获取用户已收藏资讯列表（分页）
     * @author: wenxian.cai
     * @create: 2017/5/2 15:53
     */
    @RequestMapping(value = "listInformationCollects", method = RequestMethod.POST)
    public JSONObject listInformationCollects(@RequestParam String json) throws Exception {
        return informationService.listInformationCollects(json);
    }

    /**
     * @Description: 判断该咨询是否已被用户收藏
     * @author: wenxian.cai
     * @create: 2017/5/9 17:41
     */
    @RequestMapping(value = "isCollectedOfInformation", method = RequestMethod.POST)
    public JSONObject isCollectedOfInformation(@RequestParam String json) throws Exception {
        return informationService.isCollectedOfInformation(json);
    }

}
