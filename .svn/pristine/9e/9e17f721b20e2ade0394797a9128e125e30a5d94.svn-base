package com.lifeshs.service.terminal.app;

import com.alibaba.fastjson.JSONObject;

/**
 * 面向上层的终端设备业务接口(APP)
 * 
 * @author wenxian.cai
 * @Datetime 2016年8月16日上午10:35:29
 */
public interface IAppService {
    /**
     * 设置终端号码
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午9:29:19
     * @serverComment 服务注解
     * @param json
     */
    public JSONObject setTerminalMobile(String json) throws Exception;

    /**
     * 验证用户
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月16日上午11:17:16
     * @serverComment
     * @param
     */
    public JSONObject checkUserPassword(String json) throws Exception;

    // public JSONObject queryAppVersion(String json) throws Exception;

    public JSONObject getTerminalMobile(String json) throws Exception;

    public JSONObject getRunMode(String json) throws Exception;

    public JSONObject setRunMode(String json) throws Exception;

    public JSONObject getFrequency(String json) throws Exception;

    public JSONObject setFrequency(String json) throws Exception;

    public JSONObject getNotices(String json) throws Exception;

    public JSONObject addNotice(String json) throws Exception;

    public JSONObject modifyNotice(String json) throws Exception;

    public JSONObject delNotice(String json) throws Exception;

    public JSONObject getBlackList(String json) throws Exception;

    public JSONObject setBlackList(String json) throws Exception;

    public JSONObject delBlackList(String json) throws Exception;

    public JSONObject getlocation(String json) throws Exception;

    public JSONObject setMonitor(String json) throws Exception;

    public JSONObject getElectronicFence(String json) throws Exception;

    public JSONObject addElectronicFence(String json) throws Exception;

    public JSONObject modifyElectronicFence(String json) throws Exception;

    public JSONObject delElectronicFence(String json) throws Exception;

    public JSONObject getMonitorTrack(String json) throws Exception;

    public JSONObject getMonitorTrackContent(String json) throws Exception;

    public JSONObject addMonitorTrack(String json) throws Exception;

    public JSONObject modifyMonitorTrack(String json) throws Exception;

    public JSONObject delMonitorTrack(String json) throws Exception;

    /**
     * 获取健康文字描述
     * 
     * @author yuhang.weng
     * @DateTime 2016年12月16日 上午11:15:24
     *
     * @param json
     * @return
     */
    public JSONObject getHealthDescription(String json);

    /**
     * 添加短信接收号码
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月14日 上午11:33:57
     *
     * @param json
     * @return
     */
    public JSONObject addSMSReceiveNumber(String json);

}
