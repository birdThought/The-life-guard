package com.lifeshs.service.terminal.app.user;

import com.alibaba.fastjson.JSONObject;

/**
 * 应用app个人设置
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月6日 下午8:37:08
 */
public interface IAppUserService {

    /**
     * app用户登录
     * 
     * @author dachang.luo
     * @DateTime 2016年5月26日下午5:52:02
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject login(String json, String ip) throws Exception;

    /**
     * 用户注册
     * 
     * @author dachang.luo
     * @DateTime 2016年5月27日上午9:46:39
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject register(String json) throws Exception;

    /**
     * 用户注册_限手机号注册
     *
     * @author dengfeng
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject registerMobile(String json) throws Exception;

    /**
     * 发送手机验证码
     * 
     * @author dachang.luo
     * @DateTime 2016年6月20日 上午9:52:27
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject sendVerifyCode(String json) throws Exception;

    /**
     * 核对验证码
     * 
     * @author dachang.luo
     * @DateTime 2016年6月16日下午4:55:26
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject checkVerifyCode(String json) throws Exception;

    /**
     * 忘记密码 (找回密码)
     * 
     * @author dachang.luo
     * @DateTime 2016年6月20日 上午9:52:57
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject setPasswod(String json, String ip) throws Exception;

    /**
     * 修改手机号码
     * 
     * @author dachang.luo
     * @DateTime 2016年6月16日下午5:51:47
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject modifyMobile(String json) throws Exception;

    /**
     * 修改密码
     * 
     * @author dachang.luo
     * @DateTime 2016年6月21日 下午8:22:58
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject modifyPassword(String json, String ip) throws Exception;
    
    /**
     * 用户登录认证的方式,为了验证修改密码时是否要填写旧密码
     * @param json
     * @return
     * @author liu
     * @时间 2018年12月26日 上午11:50:14
     * @remark
     */
    JSONObject userLoginType(String json);

    /**
     * 获取用户信息
     * 
     * @author dachang.luo
     * @DateTime 2016年5月27日上午9:52:01
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getUserInfo(String json) throws Exception;

    /**
     * 更新用户信息
     * 
     * @author dachang.luo
     * @DateTime 2016年5月27日上午9:51:29
     *
     * @param json
     * @return
     */
    JSONObject modifyUserBaseInfo(String json) throws Exception;

    /**
     * 更新用户信息 2
     * 
     * @author yuhang.weng
     * @DateTime 2016年12月24日 下午5:18:31
     *
     * @param json
     * @return
     */
    JSONObject modifyUserBaseInfo2(String json);

    /**
     * 修改用户身体数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月16日下午8:20:52
     *
     * @param json
     * @return
     */
    JSONObject modifyUserBodyInfo(String json);

    /**
     * 修改用户身体数据2
     * 
     * @author yuhang.weng
     * @DateTime 2016年12月24日 下午5:18:50
     *
     * @param json
     * @return
     */
    JSONObject modifyUserBodyInfo2(String json);

    /**
     * 获取用户的联系人列表（预警、亲情号）
     * 
     * @author dachang.luo
     * @DateTime 2016年6月17日上午9:33:37
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getUserContacts(String json) throws Exception;

    /**
     * 添加联系人
     * 
     * @author dachang.luo
     * @DateTime 2016年6月17日下午1:39:25
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject addUserContacts(String json) throws Exception;

    /**
     * 修改联系人
     * 
     * @author dachang.luo
     * @DateTime 2016年6月17日下午1:39:57
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject modifyUserContacts(String json) throws Exception;

    /**
     * 删除联系人
     * 
     * @author dachang.luo
     * @DateTime 2016年6月17日下午1:40:13
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject delUserContacts(String json) throws Exception;

    /**
     * 获取健康预警项目
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月14日 上午11:31:55
     *
     * @param json
     * @return
     */
    JSONObject getHealthWarningItem(String json);

    /**
     * 获取饮食习惯题目
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 上午11:54:04
     *
     * @param json
     * @return
     */
    JSONObject getDietQuestionnaire(String json);

    /**
     * 获取运动频率题目
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 上午11:54:09
     *
     * @param json
     * @return
     */
    JSONObject getSportQuestionnaire(String json);

    /**
     * 获取心理健康题目
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 上午11:54:13
     *
     * @param json
     * @return
     */
    JSONObject getMentalHealthQuestionnaire(String json);

    /**
     * 勾选饮食问卷选项
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午1:40:28
     *
     * @param json
     * @return
     */
    JSONObject tickDietQuestinonnaireOption(String json);

    /**
     * 勾选运动问卷选项
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午1:40:46
     *
     * @param json
     * @return
     */
    JSONObject tickSportQuestionnaireOption(String json);

    /**
     * 勾选心理健康问卷选项
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午1:40:58
     *
     * @param json
     * @return
     */
    JSONObject tickMentalHealthQuestionnaireOption(String json);

    /**
     * 获取用户个人档案
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月21日 上午10:17:36
     *
     * @param json
     * @return
     */
    JSONObject getUserRecord(String json);

    /**
     * 设置睡眠时长
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月21日 下午2:16:52
     *
     * @param json
     * @return
     */
    JSONObject setSleepHour(String json);
    
    /**
     *  服务注解
     *  @author yuhang.weng 
     *  @DateTime 2017年3月23日 下午1:49:52
     *
     *  @param json
     *  @return
     */
    JSONObject setCorporeityResult(String json);
    
    /**
     *  服务注解
     *  @author yuhang.weng 
     *  @DateTime 2017年3月23日 下午1:49:55
     *
     *  @param json
     *  @return
     */
    JSONObject setSubHealthSymptom(String json);

    /**
     * 第三方认证登录
     * @param json
     * @return
     */
    JSONObject oauthLogin(String json, String ip);

    /**
     * @Description: APP扫一扫登录pc
     * @author: wenxian.cai
     * @create: 2017/4/17 13:49
     */
    JSONObject pcLogin(String json);

    /**
     *  短信登录
     *  @author yuhang.weng 
     *	@DateTime 2017年4月14日 上午11:06:53
     *
     *  @param json
     *  @param ip
     *  @return
     */
    JSONObject smsLogin(String json, String ip);
    
    JSONObject logout(String json);
}
