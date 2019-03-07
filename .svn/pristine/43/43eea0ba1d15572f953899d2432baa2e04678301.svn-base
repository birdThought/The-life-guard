package com.lifeshs.controller.realtime;

import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.common.model.AjaxJson;

import com.lifeshs.service.realTime.IRealTimeService;

import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *  版权归
 *  TODO 用户没有登录前统一入口
 *  @author duosheng.mo
 *  @DateTime 2016年5月19日 下午5:20:56
 */
@Controller
@RequestMapping("realTimeControl")
public class RealTimeController {
    private static final Logger logger = Logger.getLogger(RealTimeController.class);
    @Autowired
    private IRealTimeService realTime;

    /**
     *  @author duosheng.mo
     *        @DateTime 2016年6月20日 下午3:45:56
     *  @serverCode 请求实时定位
     *
     *  @param request
     *  @return
     */
    @RequestMapping(params = "initRealTime")
    public ModelAndView initRealTime(HttpServletRequest request,
        HttpServletResponse response) {
        try {
            String imei = request.getParameter("imei");

            if (StringUtils.isNotBlank(imei)) {
                // ? 选择定位设备传入设备imei或设备类型
                boolean bool = realTime.sendLocationCommand(imei);
                request.setAttribute("result", bool);
            }
        } catch (SMSException e) {
            request.setAttribute("result", false);
            logger.error("实时定位请求异常：" + e.getMessage());
        } catch (Exception e) {
            request.setAttribute("result", false);
            logger.error("实时定位请求异常：" + e.getMessage());
        }

        ModelAndView view = new ModelAndView("");

        return view;
    }

    /**
     *  @author duosheng.mo
     *        @DateTime 2016年6月20日 下午4:44:42
     *  @serverCode 跳转亲情号码页面
     *
     *  @param request
     *  @param response
     *  @return
     */
    @RequestMapping(params = "initFamilyNum")
    public ModelAndView initFamilyNum(HttpServletRequest request,
        HttpServletResponse response) {
        ModelAndView view = new ModelAndView("");

        return view;
    }

    /**
     *  @author duosheng.mo
     *        @DateTime 2016年6月20日 下午4:47:21
     *  @serverCode 发送指令到终端回拨
     *
     *  @param request
     *  @param response
     *  @return
     */
    @RequestMapping(params = "monitor")
    public AjaxJson monitor(HttpServletRequest request,
        HttpServletResponse response) {
        AjaxJson resObj = new AjaxJson();
        String mobile = request.getParameter("mobile");
        String imei = request.getParameter("imei");

        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(imei)) {
            boolean bool = false;

            try {
                bool = realTime.sendMonitorCommand(mobile, imei);
            } catch (SMSException e) {
                request.setAttribute("result", false);
                logger.error("发送指令到终端回拨发送短信请求异常：" + e.getMessage());
            } catch (Exception e) {
                request.setAttribute("result", false);
                logger.error("发送指令到终端回拨请求异常：" + e.getMessage());
            }

            resObj.setSuccess(bool);
        } else {
            resObj.setSuccess(false);
        }

        return resObj;
    }
}
