package com.lifeshs.app.api.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.service.terminal.app.IAppService;

/**
 * 应用app终端设置和功能
 * 
 * @author yuhang.weng
 * @DateTime 2017年2月21日 下午4:42:57
 */
@Controller(value = "appWearableController")
@RequestMapping(value = { "/app", "/app/wearable" })
public class WearableController {

    @Autowired
    private IAppService appService;

    /**
     * 设置终端号码
     *
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:32:56
     * @serverComment
     * @param
     */
    @RequestMapping(value = "setTerminalMobile", method = RequestMethod.POST)
    public @ResponseBody JSONObject setTerminalMobile(@RequestParam String json) throws Exception {

        return appService.setTerminalMobile(json);
    }

    /**
     * 获取提醒设置列表
     *
     * @author yuhang.weng
     * @DateTime 2016年11月23日 上午9:30:17
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getNotices", method = RequestMethod.POST)
    public @ResponseBody JSONObject getNotices(@RequestParam String json) throws Exception {
        return appService.getNotices(json);
    }

    /**
     * 添加提醒
     *
     * @author yuhang.weng
     * @DateTime 2016年11月23日 上午9:32:48
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addNotice", method = RequestMethod.POST)
    public @ResponseBody JSONObject addNotice(@RequestParam String json) throws Exception {
        return appService.addNotice(json);
    }

    /**
     * 修改提醒
     *
     * @author yuhang.weng
     * @DateTime 2016年11月23日 上午9:32:50
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "modifyNotice", method = RequestMethod.POST)
    public @ResponseBody JSONObject modifyNotice(@RequestParam String json) throws Exception {
        return appService.modifyNotice(json);
    }

    /**
     * 删除提醒
     *
     * @author yuhang.weng
     * @DateTime 2016年11月23日 上午9:32:46
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delNotice", method = RequestMethod.POST)
    public @ResponseBody JSONObject delNotice(@RequestParam String json) throws Exception {
        return appService.delNotice(json);
    }

}
