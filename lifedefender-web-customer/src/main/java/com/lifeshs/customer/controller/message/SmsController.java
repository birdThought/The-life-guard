package com.lifeshs.customer.controller.message;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.smsRemind.SmsRecordService;
import com.lifeshs.vo.sms.SmsRecordVO;

/**
 * 短信Controller
 * @author zizhen.huang
 * @DateTime 2018年1月23日20:24:53
 */
@RestController
@RequestMapping(value = "/sms")
public class SmsController{

    private static final int PAGESIZE = 10;

    @Resource(name = "smsRecordService")
    private SmsRecordService smsRecordService;

    /**
     * 短信记录页面
     * @author zizhen.huang
     * @DateTime 2018年1月24日09:17:47
     * @return
     */
    @RequestMapping(value = "/page")
	public ModelAndView SmsRecordPage() {
		return new ModelAndView("platform/message/smsRecord");
	}
    
    /**
     * 发送短信页面
     * @author zizhen.huang
     * @DateTime 2018年1月24日09:56:54
     * @return
     */
    @RequestMapping(value = "/send/page")
    public ModelAndView sendSmsPage() {
    	return new ModelAndView("platform/message/sendSms");
    }
    
    /**
     * 短信记录列表
     * @author zizhen.huang
     * @DateTime 2018年1月24日09:20:23
     * @return
     */
    @RequestMapping(value = "/data/list/{curPage}", method = RequestMethod.GET)
    public AjaxJsonV2 smsRecordList(@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "receiveMobile", required = false) String receiveMobile,  @PathVariable(value = "curPage") int curPage) {
    	if (StringUtils.isBlank(userName)) {
    		userName = null;
    	}
    	if (StringUtils.isBlank(receiveMobile)) {
    		receiveMobile = null;
    	}
    	Paging<SmsRecordVO> paging = smsRecordService.findSmsRecordList(userName, receiveMobile, curPage, PAGESIZE);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	ajaxJsonV2.setObj(paging.getPagination());
    	return ajaxJsonV2;
    }
    
    /**
     * 发送短信
     * @author zizhen.huang
     * @DateTime 2018年1月26日15:03:18
     * @return
     */
    @RequestMapping(value = "/data/send", method = RequestMethod.POST)
    public AjaxJsonV2 sendSms(@RequestBody Map<String, Object> map) {
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
        String mobile = (String) map.get("mobile");
        String content = (String) map.get("content");
        Boolean bool = smsRecordService.send(mobile, content);
        ajaxJsonV2.setSuccess(bool);
        return ajaxJsonV2;
    }
}
