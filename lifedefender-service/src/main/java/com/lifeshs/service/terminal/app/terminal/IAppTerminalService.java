package com.lifeshs.service.terminal.app.terminal;

import com.alibaba.fastjson.JSONObject;

/**
 * app终端管理
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月6日 下午7:35:50
 */
public interface IAppTerminalService {

    /**
     * 获取用户的设备列表（含健康包勾选、HL/C3绑定的）
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日上午9:44:54
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getUserDevices(String json) throws Exception;

    /**
     * 选择健康包设备
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日上午10:17:32
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject setHealthDevice(String json) throws Exception;

    /**
     * 绑定HL、C3等设备(同一设备一个用户只能绑定一个)
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日上午11:03:46
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject bindTerminal(String json) throws Exception;

    /**
     * 解绑用户设备
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日下午2:31:31
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject unBindTerminal(String json) throws Exception;
    
    /**
     *  获取健康指数
     *  @author yuhang.weng 
     *	@DateTime 2017年3月16日 下午4:42:59
     *
     *  @param json
     *  @return
     */
    JSONObject getHealthPoint(String json);
}
