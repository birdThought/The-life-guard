package com.lifeshs.controller.terminal;

import com.lifeshs.common.constants.common.TerminalType;

import com.lifeshs.controller.common.BaseController;

import com.lifeshs.pojo.client.MemberSharingData;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 版权归 TODO HL系列设备的WEB页面控制器
 *
 * @author yuhang.weng
 * @DateTime 2016年7月12日 上午10:25:14
 */
@RequestMapping("hLSeriesWebControl")
@Controller
public class HLSeriesWebController extends BaseController {
    private static final Logger logger = Logger.getLogger(HLSeriesWebController.class);

    /**
     * HL-03
     */
    private final TerminalType terminalType = TerminalType.HL031;

    /**
     * 进入用户HL03系列手环功能页面
     *
     * @author yuhang.weng
     * @DateTime 2016年8月2日 上午11:29:43
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "hLSeries", method = RequestMethod.GET)
    public String hLSeries(Model model) {
        MemberSharingData memberSharingData = getMemberSharingData();

        // 判断是否已绑定了HL031设备
        if (!memberSharingData.isBindHL031()) {
            logger.info("用户并未绑定HL03系列设备");
            model.addAttribute("error", "用户并未绑定HL03系列设备");

            return "com/lifeshs/member/error/error";
        }

        return "com/lifeshs/member/HLseries";
    }
}
