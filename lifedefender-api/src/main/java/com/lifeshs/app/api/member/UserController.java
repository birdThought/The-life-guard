package com.lifeshs.app.api.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Record;
import com.lifeshs.common.constants.common.Error;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.user.IAppUserService;

/**
 * 应用app个人设置
 * 
 * @author yuhang.weng
 * @DateTime 2017年2月21日 下午4:33:49
 */
@RestController(value = "appUserController")
@RequestMapping(value = { "/app", "/app/user" })
public class UserController {

    @Resource(name = "appUserService")
    private IAppUserService userService;

    @Autowired
    private IMemberService memberService;
    
    /**
     * 修改手机号码
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:04:53
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "modifyMobile", method = RequestMethod.POST)
    public JSONObject modifyMobile(String json) throws Exception {
        return userService.modifyMobile(json);
    }
    
    /**
     * 用户认证类型(第三方认证,帐号密码认证)
     * @param json
     * @return
     * @author liu
     * @时间 2018年12月26日 上午11:24:38
     * @remark
     */
    @RequestMapping(value = "userLoginType", method = RequestMethod.POST)
    public JSONObject userLoginType(String json) {
    	return userService.userLoginType(json);
    }

    /**
     * 修改密码
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:05:06
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "modifyPassword", method = RequestMethod.POST)
    public JSONObject modifyPassword(String json, HttpServletRequest request) throws Exception {
        String ip = request.getHeader("X-Real-IP");
        return userService.modifyPassword(json, ip);
    }

    /**
     * 获取用户信息
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:05:16
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    public JSONObject getUserInfo(String json) throws Exception {
        return userService.getUserInfo(json);
    }

    /**
     * 更新用户基本信息
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:05:31
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "modifyUserBaseInfo", method = RequestMethod.POST)
    public JSONObject modifyUserBaseInfo(String json) throws Exception {
        return userService.modifyUserBaseInfo(json);
    }

    /**
     * 更新用户基本信息 2
     *
     * @author yuhang.weng
     * @DateTime 2016年12月24日 下午5:20:57
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "modifyUserBaseInfo2", method = RequestMethod.POST)
    public JSONObject modifyUserBaseInfo2(String json) throws Exception {
        return userService.modifyUserBaseInfo2(json);
    }

    /**
     * 修改用户身体数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:05:43
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "modifyUserBodyInfo", method = RequestMethod.POST)
    public JSONObject modifyUserBodyInfo(String json) throws Exception {
        return userService.modifyUserBodyInfo(json);
    }

    /**
     * 修改用户身体数据 2
     *
     * @author yuhang.weng
     * @DateTime 2016年12月24日 下午5:21:14
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "modifyUserBodyInfo2", method = RequestMethod.POST)
    public JSONObject modifyUserBodyInfo2(String json) throws Exception {
        return userService.modifyUserBodyInfo2(json);
    }

    /**
     * 获取用户的联系人列表（预警、亲情号）
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:06:26
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getUserContacts", method = RequestMethod.POST)
    public JSONObject getUserContacts(String json) throws Exception {
        return userService.getUserContacts(json);
    }

    /**
     * 添加联系人
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:06:57
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "addUserContacts", method = RequestMethod.POST)
    public JSONObject addUserContacts(String json) throws Exception {
        return userService.addUserContacts(json);
    }

    /**
     * 修改联系人
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:07:10
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "modifyUserContacts", method = RequestMethod.POST)
    public JSONObject modifyUserContacts(String json) throws Exception {
        return userService.modifyUserContacts(json);
    }

    /**
     * 删除联系人
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:07:22
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "delUserContacts", method = RequestMethod.POST)
    public JSONObject delUserContacts(String json) throws Exception {
        return userService.delUserContacts(json);
    }

    /**
     * 获取预警项
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月21日 下午4:22:54
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getHealthWarningItem", method = RequestMethod.POST)
    public JSONObject getHealthWarningItem(String json) {
        return userService.getHealthWarningItem(json);
    }

    /**
     *  获取饮食调查问卷题目
     *  @author yuhang.weng 
     *	@DateTime 2017年3月17日 下午1:34:36
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getDietQuestionnaire", method = RequestMethod.POST)
    public JSONObject getDietQuestionnaire(String json) {
        return userService.getDietQuestionnaire(json);
    }
    
    /**
     *  勾选饮食问卷选项
     *  @author yuhang.weng 
     *	@DateTime 2017年3月17日 下午2:24:37
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "tickDietQuestinonnaireOption", method = RequestMethod.POST)
    public JSONObject tickDietQuestinonnaireOption(String json) {
        return userService.tickDietQuestinonnaireOption(json);
    }
    
    /**
     *  获取运动调查问卷题目
     *  @author yuhang.weng 
     *	@DateTime 2017年3月17日 下午1:34:39
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getSportQuestionnaire", method = RequestMethod.POST)
    public JSONObject getSportQuestionnaire(String json) {
        return userService.getSportQuestionnaire(json);
    }
    
    /**
     *  勾选运动问卷选项
     *  @author yuhang.weng 
     *	@DateTime 2017年3月17日 下午2:24:27
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "tickSportQuestionnaireOption", method = RequestMethod.POST)
    public JSONObject tickSportQuestionnaireOption(String json) {
        return userService.tickSportQuestionnaireOption(json);
    }
    
    /**
     *  获取心理健康调查问卷题目
     *  @author yuhang.weng 
     *	@DateTime 2017年3月17日 下午1:34:42
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getMentalHealthQuestionnaire", method = RequestMethod.POST)
    public JSONObject getMentalHealthQuestionnaire(String json) {
        return userService.getMentalHealthQuestionnaire(json);
    }
    
    /**
     *  勾选心理健康问卷选项
     *  @author yuhang.weng 
     *	@DateTime 2017年3月17日 下午2:21:18
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "tickMentalHealthQuestionnaireOption", method = RequestMethod.POST)
    public JSONObject tickMentalHealthQuestionnaireOption(String json) {
        return userService.tickMentalHealthQuestionnaireOption(json);
    }
    
    /**
     *  获取用户个人档案信息
     *  @author yuhang.weng 
     *	@DateTime 2017年3月21日 上午10:17:05
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getUserRecord", method = RequestMethod.POST)
    public JSONObject getUserRecord(String json) {
        return userService.getUserRecord(json);
    }
    
    /**
     *  设置用户睡眠时长
     *  @author yuhang.weng 
     *	@DateTime 2017年3月21日 下午2:31:15
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "setSleepHour", method = RequestMethod.POST)
    public JSONObject setSleepHour(String json) {
        return userService.setSleepHour(json);
    }
    
    /**
     *  设置用户体质测试结果
     *  @author yuhang.weng 
     *  @DateTime 2017年3月23日 下午1:47:58
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "setCorporeityResult", method = RequestMethod.POST)
    public JSONObject setCorporeityResult(String json) {
        return userService.setCorporeityResult(json);
    }
    
    /**
     *  设置用户心理健康结果
     *  @author yuhang.weng 
     *  @DateTime 2017年3月23日 下午2:12:49
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "setSubHealthSymptom", method = RequestMethod.POST)
    public JSONObject setSubHealthSymptom(String json) {
        return userService.setSubHealthSymptom(json);
    }

    /**
     *  退出登录
     *  @author yuhang.weng 
     *  @DateTime 2017年8月15日 下午2:52:29
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public JSONObject logout(String json) {
        return userService.logout(json);
    }
    
    @RequestMapping(value = "setStrokeRiskScore", method = RequestMethod.POST)
    public JSONObject setStrokeRiskScore(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        Integer userId = appJSON.getData().getUserId();
        Integer strokeRiskScore = appJSON.getData().getFirstJSONObject().getInteger(Record.STROKE_RISK_SCORE);
        if (strokeRiskScore == null) {
            return AppNormalService.error(String.format(Error.MISSING, "分数"), ErrorCodeEnum.REQUEST);
        }
        
        memberService.updateStorkeRiskScore(userId, strokeRiskScore);
        return AppNormalService.success();
    }
}
