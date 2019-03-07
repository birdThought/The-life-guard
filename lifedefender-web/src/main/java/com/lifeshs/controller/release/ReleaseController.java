package com.lifeshs.controller.release;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.model.AjaxJson;

import com.lifeshs.controller.common.BaseController;

import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.tool.impl.ValidCodeServiceImpl;

import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 版权归
 * TODO 用户没有登录前统一入口
 *
 * @author duosheng.mo
 * @DateTime 2016年5月19日 下午5:20:56
 */
@Controller
@RequestMapping("releaseControl")
public class ReleaseController extends BaseController {
    private static final Logger logger = Logger.getLogger(ReleaseController.class);
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IOrgUserService orgUserService;
    @Autowired
    private IManagerOrgService orgService;
    @Autowired
    private ValidCodeServiceImpl validCodeService;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author duosheng.mo
     * @DateTime 2016年5月19日 下午5:08:28
     * @serverComment 验证用户是否已存在
     */
    @RequestMapping(params = "userIsExist", method = RequestMethod.POST)
    @ResponseBody
    public boolean userIsExist(HttpServletRequest request,
        HttpServletResponse response) {
        //String id = request.getParameter("id");
        //String column = request.getParameter("column");
        String userName = request.getParameter("val");

        //String type = request.getParameter("type");
        boolean bool = false;

        try {
            bool = memberService.userIsExist(userName);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }

        return !bool;
    }

    /**
     * 验证机构是否已存在
     *
     * @param orgName
     * @return
     * @author dengfeng
     * @DateTime 2016-6-2 上午10:56:53
     */
    @RequestMapping(params = "ogrIsExist", method = RequestMethod.POST)
    public @ResponseBody
    boolean ogrIsExist(@RequestParam
    String orgName) {
        boolean bool = orgService.orgIsExist(orgName);

        return !bool;
    }

    /**
     * @param userId 用户ID
     * @param code   验证码
     * @param model  模型
     * @return
     * @author yuhang.weng
     * @DateTime 2016年5月13日 下午3:56:03
     * @serverComment 重置密码-检查验证码是否有效
     * @updateTime 2017/3/17
     * @modifier wenxian.cai
     */
    @RequestMapping(params = "checkValidCode", method = RequestMethod.POST)
    public String checkValidCode(@RequestParam
    String userId, @RequestParam
    String code, Model model) {
        if (!validCodeService.valid(userId, code, CacheType.USER_RESET_CACHE)) {
            return "com/lifeshs/member/findPwd_step_one"; // 失败页面
        }

        model.addAttribute("userId", userId);
        model.addAttribute("validCode", code);

        return "com/lifeshs/member/findPwd_step_two"; // 成功页面
    }

    /**
     * @param userId    用户ID
     * @param validCode 验证码
     * @param password  新密码
     * @return
     * @author yuhang.weng
     * @DateTime 2016年6月6日 下午12:30:30
     * @serverComment 重置密码
     */
    @RequestMapping(params = "resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson resetPassword(@RequestParam
    String userId, @RequestParam
    String validCode, @RequestParam
    String password, HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");

        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "重置密码失败";
        String userName = "";

        if (StringUtils.isBlank(validCode)) {
            resObject.setMsg(msg + ":请勿违规操作");

            return resObject;
        }

        //校验验证码是否正确或者过期
        if (!validCodeService.valid(userId, validCode,
                    CacheType.USER_RESET_CACHE)) {
            resObject.setMsg(msg + ":验证码已失效");

            return resObject;
        }

        // 普通用户
        userName = memberService.modifyPasswordByUserId(userId, password, ip);

        if (userName == null) {
            // 企业用户
            userName = orgUserService.modifyPasswordByUserId(Integer.valueOf(
                        userId), password, ip);
        }

        if (userName == null) {
            resObject.setMsg(msg);

            return resObject;
        }

        if (!validCodeService.destory(userId, CacheType.USER_RESET_CACHE)) {
            resObject.setMsg(msg);

            return resObject;
        }

        msg = "重置密码成功";
        resObject.setSuccess(true);
        resObject.setObj(userName);
        resObject.setMsg(msg);

        return resObject;
    }

    /**
     * @param userName 用户名
     * @param model
     * @return
     * @author yuhang.weng
     * @DateTime 2016年6月6日 下午12:31:17
     * @serverComment 重置密码-成功页面跳转
     */
    @RequestMapping(params = "success")
    public String redirectSuccess(@RequestParam
    String userName, Model model) {
        model.addAttribute("userName", userName);

        return "com/lifeshs/member/findPwd_step_three";
    }

    /**
     * @param request
     * @param response
     * @return
     * @author duosheng.mo
     * @DateTime 2016年6月2日 下午1:51:25
     * @serverComment 服务注解
     */
    @RequestMapping(params = "showMap")
    public String showMap(HttpServletRequest request,
        HttpServletResponse response) {
        return "map/showMap";
    }

    /**
     * @param request
     * @param response
     * @return
     * @author duosheng.mo
     * @DateTime 2016年6月2日 下午1:51:25
     * @serverComment 服务注解
     */
    @RequestMapping(params = "xlpTest")
    public String xlpTest(HttpServletRequest request,
        HttpServletResponse response) {
        return "com/lifeshs/member/findPwd_2";
    }

    /**
     *  应用app访问 用户注册协议 重定向
     *  @author yuhang.weng
     *        @DateTime 2017年7月27日 上午11:28:29
     *
     *  @return
     */
    @RequestMapping(params = "showAgreement", method = RequestMethod.GET)
    public String showAgreement() {
        return "redirect:/register?showAgreement";
    }

    /**
     *  管理应用app访问 机构注册协议 重定向
     *  @author yuhang.weng
     *        @DateTime 2017年7月27日 上午11:28:31
     *
     *  @return
     */
    @RequestMapping(params = "showOrgAgreement", method = RequestMethod.GET)
    public String showOrgAgreement() {
        return "redirect:/register?orgAgreement";
    }
}
