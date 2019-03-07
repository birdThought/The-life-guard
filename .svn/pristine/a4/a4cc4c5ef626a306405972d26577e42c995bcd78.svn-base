package com.lifeshs.service.terminal.app.family;

import com.alibaba.fastjson.JSONObject;

/**
 *  应用app家庭组接口
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年3月7日 上午9:44:11
 */
public interface IAppFamilyService {

    /**
     * 获取家庭成员列表
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午9:28:19
     * @param json
     */
    JSONObject getFamilyMemberList(String json) throws Exception;
    
    /**
     * 添加新家庭成员
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月15日下午5:59:07
     * @param
     */
    JSONObject addNewFamilyMember(String json) throws Exception;
    
    /**
     * 修改家庭成员信息
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月16日上午9:48:43
     * @param
     */
    JSONObject modifyFamilyMember(String json) throws Exception;
    
    /**
     * 查找用户列表
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月16日上午10:35:34
     * @param
     */
    JSONObject queryUserList(String json) throws Exception;
    
    /**
     * 加入家庭成员
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月16日上午11:23:22
     * @param
     */
    JSONObject addFamilyMember(String json) throws Exception;
    
    /**
     * 删除家庭成员
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月16日下午1:47:25
     * @param
     */
    JSONObject delFamilyMember(String json) throws Exception;
    
    /**
     * 切换家庭组账号
     * 
     * @author yuhang.weng
     * @DateTime 2016年12月20日 下午1:48:42
     *
     * @param json
     * @return
     */
    JSONObject switchFamilyAccount(String json, String ip);
    /**
     * 获取提醒设置
     *
     * @param json
     * @return
     */
    JSONObject getUserMeasurementReminder(String json);
    /**
     * 获取提醒项目列表
     *
     * @param json
     * @return
     */
    JSONObject listUserMeasurementReminderDetail(String json);
    /**
     * 设置短信提醒
     *
     * @param json
     * @return
     */
    JSONObject setUserMeasurementReminderSmsSwitch(String json);
    /**
     * 设置推送提醒
     *
     * @param json
     * @return
     */
    JSONObject setUserMeasurementReminderPushSwitch(String json);
    /**
     * 新增提醒项目
     *
     * @param json
     * @return
     */
    JSONObject addUserMeasurementReminderDetail(String json);
    /**
     * 设置提醒项目状态
     * @param json
     * @return
     */
    JSONObject setUserMeasurementReminderDetailStatus(String json);
    /**
     * 逻辑删除提醒项目
     * @param json
     * @return
     */
    JSONObject delUserMeasurementReminderDetail(String json);
    /**
     * 更新提醒项目
     * @param json
     * @return
     */
    JSONObject updateUserMeasurementReminderDetail(String json);
}
