package com.lifeshs.service.terminal.app.health;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.exception.common.OperationException;

public interface IAppHealthService {

    /**
     * 获取健康范围值
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月6日下午2:19:13
     * @param json
     */
    JSONObject getHealthArea(String json) throws Exception;

    /**
     * 获取健康范围值
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月6日下午2:19:13
     * @param json
     */
    JSONObject getHealthArea2(String json) throws Exception;

    /**
     * 心率数据上传
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月11日下午7:11:41
     * @param
     */
    JSONObject addHeartRate(String json) throws Exception;

    /**
     * 获取心率数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月24日下午7:14:44
     * @param
     */
    JSONObject getHeartRate(String json) throws Exception;

    /**
     * 上传体温数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月5日下午4:26:50
     * @param json
     */
    JSONObject addTemperature(String json) throws Exception;

    /**
     * 获取体温数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月4日下午1:56:45
     * @param json
     */
    JSONObject getTemperature(String json) throws Exception;

    /**
     * 上传血压数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日下午2:44:51
     *
     * @param json
     * @return
     * @throws OperationException
     */
    JSONObject addBloodPressure(String json) throws OperationException;

    /**
     * 获取血压数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日下午4:45:16
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getBloodPressure(String json) throws Exception;

    /**
     * 上传血氧仪数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日下午4:52:08
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject addOxygen(String json) throws Exception;

    /**
     * 获取血氧数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日下午4:56:25
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getOxygen(String json) throws Exception;

    /**
     * 上传血糖数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日下午5:16:28
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject addGlucometer(String json) throws Exception;

    /**
     * 获取体脂秤数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日下午5:23:40
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getGlucometer(String json) throws Exception;

    /**
     * 上传肺活仪数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日下午5:28:04
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject addLunginstrument(String json) throws Exception;

    /**
     * 获取肺活仪数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日下午5:23:40
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getLunginstrument(String json) throws Exception;

    /**
     * 上传体脂秤数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日下午5:28:04
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject addBodyfatscale(String json) throws Exception;

    /**
     * 获取体脂秤数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月18日下午5:23:40
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getBodyfatscale(String json) throws Exception;

    /**
     * 手环数据上传
     * 
     * @author dachang.luo
     * @DateTime 2016年6月20日 上午10:31:00
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject addBand(String json) throws Exception;

    /**
     * 获取手环数据
     * 
     * @author dachang.luo
     * @DateTime 2016年6月20日 上午10:31:43
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getBand(String json) throws Exception;

    /**
     * 获取心率手环单独计步或者睡眠数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年9月18日下午7:34:43
     * @param
     */
    JSONObject getBandDetail(String json) throws Exception;

    /**
     * 获取全部设备数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月23日下午3:56:34
     * @param
     */
    JSONObject getAllData(String json) throws Exception;

    /**
     *  获取全部设备数据
     *  @author yuhang.weng 
     *  @DateTime 2017年10月31日 上午10:32:35
     *
     *  @param json
     *  @exception Exception
     *  @return
     */
    JSONObject getAllData2(String json) throws Exception;

    /**
     * 获取设备数据总条数
     * 
     * @author wenxian.cai
     * @DateTime 2016年9月14日上午10:23:36
     * @param
     */
    JSONObject getDataCount(String json);
    
    /**
     *  添加心电
     *  @author yuhang.weng 
     *	@DateTime 2017年3月20日 上午11:07:50
     *
     *  @param json
     *  @return
     */
    JSONObject addEcg(String json) throws OperationException;
    
    /**
     *  获取心电数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月19日 下午1:36:37
     *
     *  @param json
     *  @return
     */
    JSONObject getEcg(String json);
    
    /**
     *  按日期获取心电数据
     *  @author yuhang.weng 
     *	@DateTime 2017年5月4日 下午3:09:59
     *
     *  @param json
     *  @return
     */
    JSONObject getEcgWithDate(String json);
    
    JSONObject getEcgAutoDataWithDate(String json);
    
    JSONObject getEcgActiveDataWithDate(String json);
    
    /**
     *  添加尿液分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月19日 下午1:36:34
     *
     *  @param json
     *  @return
     */
    JSONObject addUran(String json) throws OperationException;
    
    /**
     *  获取尿液分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月19日 下午1:37:25
     *
     *  @param json
     *  @return
     */
    JSONObject getUran(String json);
    
    /**
     *  添加一条尿酸分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月24日 下午2:01:47
     *
     *  @param json
     *  @return
     */
    JSONObject addUa(String json) throws OperationException;
    
    /**
     *  获取尿酸分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月24日 下午2:03:14
     *
     *  @param json
     *  @return
     */
    JSONObject getUa(String json);
    
    /**
     *  添加一条血脂仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月25日 下午5:18:11
     *
     *  @param json
     *  @return
     */
    JSONObject addBloodLipid(String json) throws OperationException;
    
    /**
     *  获取血脂仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月25日 下午5:18:26
     *
     *  @param json
     *  @return
     */
    JSONObject getBloodLipid(String json);
}
