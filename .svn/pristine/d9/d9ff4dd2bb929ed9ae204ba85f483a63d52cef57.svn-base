package com.lifeshs.customer.controller.serve;

import javax.annotation.Resource;

import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.vo.customer.CustomerSharingDataVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.service1.serve.ServeService;

/**
 *  服务统计
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2018年2月8日 下午6:02:44
 */
@RestController
@RequestMapping(value = "/serve/statistics")
public class ServeStatisticsController extends BaseController {

    @Resource(name = "v2ServeService")
    private ServeService serveService;
    
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/platform/serve/statistics");
    }
    
    /**
     *  获取统计数据
     *  @author yuhang.weng 
     *  @DateTime 2018年2月8日 下午6:02:33
     *
     *  @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public JSONObject listStatistics() {
        JSONObject root = new JSONObject();
        CustomerSharingDataVO user = getUserSharingData();
        root.put("success", true);
        root.put("data", serveService.listServeStatistics(user.getUserNo()));
        return root;
    }
}
