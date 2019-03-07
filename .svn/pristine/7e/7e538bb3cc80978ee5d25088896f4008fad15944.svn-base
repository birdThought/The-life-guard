package com.lifeshs.app.api.member;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.service.terminal.app.terminal.IAppTerminalService;

/**
 * 应用app终端管理
 * 
 * @author yuhang.weng
 * @DateTime 2017年2月21日 下午4:40:10
 */
@RestController(value = "appTerminalController")
@RequestMapping(value = { "/app", "/app/terminal" })
public class TerminalController {

    @Resource(name = "appTerminalService")
    private IAppTerminalService terminalService;

    /**
     * 获取用户的设备列表（含健康包勾选、HL/C3绑定的）
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:07:34
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getUserDevices", method = RequestMethod.POST)
    public JSONObject getUserDevices(@RequestParam String json) throws Exception {
        return terminalService.getUserDevices(json);
    }

    /**
     * 选择健康包设备
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:07:55
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "setHealthDevice", method = RequestMethod.POST)
    public JSONObject setHealthDevice(@RequestParam String json) throws Exception {
        return terminalService.setHealthDevice(json);
    }

    /**
     * 绑定HL、C3等设备(同一设备一个用户只能绑定一个)
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:08:40
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "bindTerminal", method = RequestMethod.POST)
    public JSONObject bindTerminal(@RequestParam String json) throws Exception {
        return terminalService.bindTerminal(json);
    }

    /**
     * 解绑设备
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:09:01
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "unBindTerminal", method = RequestMethod.POST)
    public JSONObject unBindTerminal(@RequestParam String json) throws Exception {
        return terminalService.unBindTerminal(json);
    }
    
    /**
     *  获取健康指数
     *  @author yuhang.weng 
     *	@DateTime 2017年3月16日 下午5:15:56
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getHealthPoint", method = RequestMethod.POST)
    public JSONObject getHealthPoint(@RequestParam String json) {
        return terminalService.getHealthPoint(json);
    }
}
