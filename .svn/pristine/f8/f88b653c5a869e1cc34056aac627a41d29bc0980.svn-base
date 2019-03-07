package com.lifeshs.app.api.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.service.terminal.app.family.IAppFamilyService;

/**
 * 应用app家庭组
 * 
 * @author yuhang.weng
 * @DateTime 2017年2月21日 下午4:36:53
 */
@RestController(value = "appFamilyController")
@RequestMapping(value = { "/app", "/app/family" })
public class FamilyController {

    @Resource(name = "appFamilyService")
    private IAppFamilyService familyService;

    /**
     * 获取家庭成员列表
     *
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午9:56:38
     * @serverComment 服务注解
     * @param json
     */
    @RequestMapping(value = "getFamilyMemberList", method = RequestMethod.POST)
    public JSONObject getFamilyMemberList(@RequestParam String json) throws Exception {
        return familyService.getFamilyMemberList(json);
    }

    /**
     * 添加新家庭成员
     *
     * @author wenxian.cai
     * @DateTime 2016年8月16日上午9:06:28
     * @serverComment
     * @param
     */
    @RequestMapping(value = "addNewFamilyMember", method = RequestMethod.POST)
    public JSONObject addNewFamilyMember(@RequestParam String json) throws Exception {
        return familyService.addNewFamilyMember(json);
    }

    /**
     * 修改家庭成员信息
     *
     * @author wenxian.cai
     * @DateTime 2016年8月16日上午9:47:41
     * @serverComment
     * @param
     */
    @RequestMapping(value = "modifyFamilyMember", method = RequestMethod.POST)
    public JSONObject modifyFamilyMember(@RequestParam String json) throws Exception {
        return familyService.modifyFamilyMember(json);
    }

    /**
     * 查找用户列表
     *
     * @author wenxian.cai
     * @DateTime 2016年8月16日上午10:34:46
     * @serverComment
     * @param
     */
    @RequestMapping(value = "queryUserList", method = RequestMethod.POST)
    public JSONObject queryUserList(@RequestParam String json) throws Exception {
        return familyService.queryUserList(json);
    }

    /**
     * 加入家庭成员
     *
     * @author wenxian.cai
     * @DateTime 2016年8月16日上午11:22:33
     * @serverComment
     * @param
     */
    @RequestMapping(value = "addFamilyMember", method = RequestMethod.POST)
    public JSONObject addFamilyMember(@RequestParam String json) throws Exception {
        return familyService.addFamilyMember(json);
    }

    /**
     * 删除家庭成员
     *
     * @author wenxian.cai
     * @DateTime 2016年8月16日下午1:46:39
     * @serverComment
     * @param
     */
    @RequestMapping(value = "delFamilyMember", method = RequestMethod.POST)
    public JSONObject delFamilyMember(@RequestParam String json) throws Exception {
        return familyService.delFamilyMember(json);
    }

    /**
     * 验证用户名是否存在
     * 目前移动到对外开放的release控制器中 com.lifeshs.app.api.member.release.ReleaseController.isUserNameExist
     *
     * @author yuhang.weng
     * @DateTime 2016年12月20日 下午1:49:34
     *
     * @param json
     * @return
     */
    /*@RequestMapping(value = "isUserNameExist", method = RequestMethod.POST)
    public JSONObject isUserNameExist(@RequestParam String json) {
        return familyService.isUserNameExist(json);
    }*/

    /**
     * 切换家庭组账号
     *
     * @author yuhang.weng
     * @DateTime 2016年12月20日 下午1:50:06
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "switchFamilyAccount", method = RequestMethod.POST)
    public JSONObject switchFamilyAccount(@RequestParam String json, HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        return familyService.switchFamilyAccount(json, ip);
    }

    /**
     * 获取提醒设置
     * @param json
     * @return
     */
    @RequestMapping(value = "getUserMeasurementReminder", method = RequestMethod.POST)
    public JSONObject getUserMeasurementReminder(@RequestParam String json) {
        return familyService.getUserMeasurementReminder(json);
    }

    /**
     * 获取提醒项目列表
     * @param json
     * @return
     */
    @RequestMapping(value = "getUserMeasurementReminderDetailList", method = RequestMethod.POST)
    public JSONObject getUserMeasurementReminderDetailList(@RequestParam String json) {
        return familyService.listUserMeasurementReminderDetail(json);
    }

    /**
     * 设置短信提醒
     * @param json
     * @return
     */
    @RequestMapping(value = "setUserMeasurementReminderSmsSwitch", method = RequestMethod.POST)
    public JSONObject setUserMeasurementReminderSmsSwitch(@RequestParam String json) {
        return familyService.setUserMeasurementReminderSmsSwitch(json);
    }

    /**
     * 设置推送提醒
     * @param json
     * @return
     */
    @RequestMapping(value = "setUserMeasurementReminderPushSwitch", method = RequestMethod.POST)
    public JSONObject setUserMeasurementReminderPushSwitch(@RequestParam String json) {
        return familyService.setUserMeasurementReminderPushSwitch(json);
    }

    /**
     * 新增提醒项目
     * @param json
     * @return
     */
    @RequestMapping(value = "addUserMeasurementReminderDetail", method = RequestMethod.POST)
    public JSONObject addUserMeasurementReminderDetail(@RequestParam String json) {
        return familyService.addUserMeasurementReminderDetail(json);
    }

    /**
     * 更新提醒项目
     * @param json
     * @return
     */
    @RequestMapping(value = "updateUserMeasurementReminderDetail", method = RequestMethod.POST)
    public JSONObject updateUserMeasurementReminderDetail(@RequestParam String json) {
        return familyService.updateUserMeasurementReminderDetail(json);
    }

    /**
     * 逻辑删除提醒项目
     * @param json
     * @return
     */
    @RequestMapping(value = "delUserMeasurementReminderDetail", method = RequestMethod.POST)
    public JSONObject delUserMeasurementReminderDetail(@RequestParam String json) {
        return familyService.delUserMeasurementReminderDetail(json);
    }

    /**
     * 设置提醒项目状态
     * @param json
     * @return
     */
    @RequestMapping(value = "setUserMeasurementReminderDetailStatus", method = RequestMethod.POST)
    public JSONObject setUserMeasurementReminderDetailStatus(@RequestParam String json) {
        return familyService.setUserMeasurementReminderDetailStatus(json);
    }
}
