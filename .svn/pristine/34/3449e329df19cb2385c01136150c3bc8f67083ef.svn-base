package com.lifeshs.controller.org.manage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.org.manage.IManageOrgService;
import com.lifeshs.service.tool.impl.ValidCodeServiceImpl;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.page.Paging;


/**
 * 
 *  管理机构
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2019年1月19日 上午11:27:48
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController("manageOrgController")
@RequestMapping(value = "org/manageOrg")
public class manageOrgController extends BaseController {
    private static final Logger logger = Logger.getLogger(manageOrgController.class);
    final static Integer PAGE_SIZE = 10;
    
    @Resource(name = "v2OrderService")
    private OrderService orderService;
    
    @Autowired
    private ValidCodeServiceImpl validCodeService;
    
    @Autowired
    private IManageOrgService manageOrgService;
    
    @Autowired
    private ValidCodeServiceImpl validCodeUtil;
    
    
    /**
     * 
     *  获取下线机构
     *  @author liaoguo
     *  @DateTime 2018年12月12日 下午8:11:30
     *
     *  @return
     */
    @RequestMapping()
    public ModelAndView orgManage() {
        ModelAndView modelAndView = new ModelAndView("platform/org/。。。"); 
        return modelAndView;
    }
    
    /**
     *  机构列表
     * @param page
     * @param userName
     * @param realName
     * @param orgName
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/list/{page}")
    public @ResponseBody AjaxJson getMemberDataList(@PathVariable(value = "page") Integer page, 
                                                    @RequestParam(value = "orgId",required = false) Integer orgId,
                                                    @RequestParam(value = "orgName",required = false) String orgName){
        if ("".equals(orgName)){
            orgName =null;
        }

        AjaxJson ajaxJson = new AjaxJson();
        Paging<TOrg> p = manageOrgService.listManageOrg(orgId, orgName, page, PAGE_SIZE);
        PaginationDTO<TOrg> pagination = p.getPagination();
        ajaxJson.setObj(pagination);
        return ajaxJson;
    }
    
    
    /**
     *  点击+号，获取下一级机构
     * @param page
     * @param userName
     * @param realName
     * @param orgName
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/list/findOrgListByOrgId")
    public @ResponseBody AjaxJson findOrgListByOrgId(@RequestParam(value = "orgId",required = false) Integer orgId){

        AjaxJson ajaxJson = new AjaxJson();
        List<TOrg> orgList = manageOrgService.findOrgListByOrgId(orgId);
        
        ajaxJson.setObj(orgList);
        return ajaxJson;
    }
    
    /**
     *  根据机构id查找员工
     * @param page
     * @param userName
     * @param realName
     * @param orgName
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/list/findOrgUserByOrgId")
    public @ResponseBody AjaxJson findOrgUserByOrgId(@RequestParam(value = "orgId",required = false) Integer orgId){

        AjaxJson ajaxJson = new AjaxJson();
        List<OrgUserPO> orgUserList = manageOrgService.findOrgUserByOrgId(orgId);
        
        ajaxJson.setObj(orgUserList);
        return ajaxJson;
    }
    
    
    /**
     * 
     *  消息推送-添加下级机构
     *  @author liaoguo
     *  @DateTime 2019年1月19日 下午4:46:00
     *
     *  @param orgName
     *  @param pushOrgName
     *  @param mobile
     *  @return
     */
    @RequestMapping(value = "/addLowerOrgOfPushMessage")
    public @ResponseBody AjaxJson addLowerOrgOfPushMessage(@RequestParam(value = "orgName",required = false) String orgName,
                                                           @RequestParam(value = "pushOrgName",required = false) String pushOrgName,
                                                           @RequestParam(value = "mobile",required = false) String mobile){

        AjaxJson ajaxJson = new AjaxJson();
        //后续实现，目前暂支持短信添加
        //.........
        
        return ajaxJson;
    }
    
    
    /**
     * 
     *  发送短信-添加下级机构
     *  @author liaoguo
     *  @DateTime 2019年1月19日 下午4:41:23
     *
     *  @param orgId
     *  @param mobile
     *  @param verifyCode
     *  @return
     */
    @RequestMapping("/addLowerOrgOfSMS")
    @ResponseBody
    public AjaxJson addLowerOrgOfSMS(@RequestParam(value = "orgId",required = false) Integer orgId,
                                    @RequestParam(value = "mobile",required = false) String mobile,
                                    @RequestParam(value = "verifyCode",required = false) String verifyCode) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(false);
        String cacheKey = mobile;
        CacheType cacheType = CacheType.USER_REGISTERY_CACHE;

        // 验证不通过
        if (!validCodeUtil.valid(cacheKey, verifyCode, cacheType)) {
            
            ajaxJson.setMsg(NormalMessage.CODE_UNRECOGNIZED);
            return ajaxJson;
        }
        
        //修改机构的parent
        int result = manageOrgService.updateOrgByMobile(orgId, mobile);
        if(result > 0){
            ajaxJson.setMsg("添加失败，请核对手机是否正确！");
            return ajaxJson;
        }
        
        ajaxJson.setSuccess(true);
        ajaxJson.setMsg("添加成功!");
        return ajaxJson;
    }
    
    
    /**
     * 
     *  发送短信
     *  @author liaoguo
     *  @DateTime 2019年1月19日 下午4:26:26
     *
     *  @param request
     *  @param response
     *  @return
     */
    @RequestMapping("sendValidCode")
    @ResponseBody
    public AjaxJson sendValidCode(@RequestParam(value = "mobile",required = false) String mobile) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "发送验证码失败";
        CacheType cacheType = null;
        String cacheKey = "", userId = "";
        
        userId = "100000";  //系统发送
        cacheType = CacheType.USER_REGISTERY_CACHE;    // 注册 验证码
        cacheKey = mobile;
        
        
        if (StringUtils.isBlank(cacheKey) || cacheType == null) {
            resObject.setMsg(msg + ":非法操作");
            return resObject;
        }
        try {
            // 发送验证码
            if (StringUtils.isEmpty(validCodeService.sendToMobile(Integer.parseInt(userId), mobile, cacheKey, cacheType, VcodeTerminalType.USER_PLATFORM, false))) {
                // 发送失败
                resObject.setMsg(msg);
                return resObject;
            }
            // 发送成功
            resObject.setObj(cacheKey);    // 保存userId到页面上
            resObject.setMsg("手机验证码已发送");
            resObject.setSuccess(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resObject.setMsg(msg + ":" + e.getMessage());
        }

        return resObject;
    }
    
}
