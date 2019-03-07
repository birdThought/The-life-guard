package com.lifeshs.app.api.member;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.terminal.app.health.IAppHealthService;

/**
 * 应用app健康数据
 * 
 * @author yuhang.weng
 * @DateTime 2017年2月21日 下午4:41:23
 */
@RestController(value = "appHealthController")
@RequestMapping(value = { "/app", "/app/health" })
public class HealthController {

    @Resource(name = "appHealthService")
    private IAppHealthService healthService;

    @Autowired
    protected IMemberService memberService;
    
    /**
     * 获取健康范围值
     *
     * @author wenxian.cai
     * @DateTime 2016年8月6日下午2:17:48
     * @serverComment 服务注解
     * @param json
     */
    @RequestMapping(value = "getHealthArea", method = RequestMethod.POST)
    public JSONObject getHealthArea(@RequestParam String json) throws Exception {
        return healthService.getHealthArea(json);
    }

    /**
     * 获取健康范围值
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月21日 下午4:53:32
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getHealthArea2", method = RequestMethod.POST)
    public JSONObject getHealthArea2(@RequestParam String json) throws Exception {
        return healthService.getHealthArea2(json);
    }

    /**
     * 心率数据上传
     *
     * @author wenxian.cai
     * @DateTime 2016年8月11日下午7:10:14
     * @serverComment
     * @param
     */
    @RequestMapping(value = "addHeartRate", method = RequestMethod.POST)
    public JSONObject addHeartRate(@RequestParam String json) throws Exception {
        return healthService.addHeartRate(json);
    }

    /**
     * 获取心率数据
     *
     * @author wenxian.cai
     * @DateTime 2016年8月24日下午7:13:40
     * @serverComment
     * @param
     */
    @RequestMapping(value = "getHeartRate", method = RequestMethod.POST)
    public JSONObject getHeartRate(@RequestParam String json) throws Exception {
        return healthService.getHeartRate(json);
    }

    /**
     * 上传体温数据
     *
     * @author wenxian.cai
     * @DateTime 2016年8月5日下午4:34:37
     * @serverComment 服务注解
     * @param json
     */
    @RequestMapping(value = "addTemperature", method = RequestMethod.POST)
    public JSONObject addTemperature(@RequestParam String json) throws Exception {
        return healthService.addTemperature(json);
    }

    /**
     * 获取体温数据
     *
     * @author wenxian.cai
     * @DateTime 2016年8月4日下午1:55:25
     * @serverComment 服务注解
     * @param json
     *
     */
    @RequestMapping(value = "getTemperature", method = RequestMethod.POST)
    public JSONObject getTemperature(@RequestParam String json) throws Exception {
        return healthService.getTemperature(json);
    }

    /**
     * 上传血压数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:09:15
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addBloodPressure", method = RequestMethod.POST)
    public JSONObject addBloodPressure(@RequestParam String json) throws OperationException {
        return healthService.addBloodPressure(json);
    }

    /**
     * 获取血压数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:09:34
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getBloodPressure", method = RequestMethod.POST)
    public JSONObject getBloodPressure(@RequestParam String json) throws Exception {
        return healthService.getBloodPressure(json);
    }

    /**
     * 上传血氧仪数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:12:37
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addOxygen", method = RequestMethod.POST)
    public JSONObject addOxygen(@RequestParam String json) throws Exception {
        return healthService.addOxygen(json);
    }

    /**
     * 获取血氧数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:13:03
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getOxygen", method = RequestMethod.POST)
    public JSONObject getOxygen(@RequestParam String json) throws Exception {
        return healthService.getOxygen(json);
    }

    /**
     * 上传血糖数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:13:40
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addGlucometer", method = RequestMethod.POST)
    public JSONObject addGlucometer(@RequestParam String json) throws Exception {
        return healthService.addGlucometer(json);
    }

    /**
     * 获取血糖数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:14:01
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getGlucometer", method = RequestMethod.POST)
    public JSONObject getGlucometer(@RequestParam String json) throws Exception {
        return healthService.getGlucometer(json);
    }

    /**
     * 上传肺活仪数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:14:15
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addLunginstrument", method = RequestMethod.POST)
    public JSONObject addLunginstrument(@RequestParam String json) throws Exception {
        return healthService.addLunginstrument(json);
    }

    /**
     * 获取肺活仪数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:14:30
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getLunginstrument", method = RequestMethod.POST)
    public JSONObject getLunginstrument(@RequestParam String json) throws Exception {
        return healthService.getLunginstrument(json);
    }

    /**
     * 上传体脂秤数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:14:46
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addBodyfatscale", method = RequestMethod.POST)
    public JSONObject addBodyfatscale(@RequestParam String json) throws Exception {
        return healthService.addBodyfatscale(json);
    }

    /**
     * 获取体脂秤数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:15:07
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getBodyfatscale", method = RequestMethod.POST)
    public JSONObject getBodyfatscale(@RequestParam String json) throws Exception {
        return healthService.getBodyfatscale(json);
    }

    /**
     * 上传手环数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:15:48
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addBand", method = RequestMethod.POST)
    public JSONObject addBand(@RequestParam String json) throws Exception {
        return healthService.addBand(json);
    }

    /**
     * 获取手环数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:15:58
     * @serverComment 服务注解
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getBand", method = RequestMethod.POST)
    public JSONObject getBand(@RequestParam String json) throws Exception {
        return healthService.getBand(json);
    }

    /**
     * 获取心率手环单独计步或者睡眠数据
     *
     * @author wenxian.cai
     * @DateTime 2016年9月18日下午7:32:02
     * @serverComment
     * @param
     */
    @RequestMapping(value = "getBandDetail", method = RequestMethod.POST)
    public JSONObject getBandDetail(@RequestParam String json) throws Exception {
        return healthService.getBandDetail(json);
    }

    /**
     * 获取用户全部设备数据
     *
     * @author wenxian.cai
     * @DateTime 2016年8月24日下午7:12:54
     * @serverComment
     * @param
     */
    @RequestMapping(value = "getAllData", method = RequestMethod.POST)
    public JSONObject getAllData(@RequestParam String json) throws Exception {
        return healthService.getAllData(json);
    }

    /**
     *  获取用户全部设备数据
     *  @author yuhang.weng 
     *  @DateTime 2017年10月31日 上午10:33:30
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getAllData2", method = RequestMethod.POST)
    public JSONObject getAllData2(@RequestParam String json) throws Exception {
        return healthService.getAllData2(json);
    }
    
    /**
     * 获取设备数据总条数
     *
     * @author wenxian.cai
     * @DateTime 2016年9月14日上午10:21:25
     * @serverComment
     * @param
     */
    @RequestMapping(value = "getDataCount", method = RequestMethod.POST)
    public JSONObject getDataCount(@RequestParam String json) throws Exception {
        return healthService.getDataCount(json);
    }
    
    @RequestMapping(value = "addEcg", method = RequestMethod.POST)
    public JSONObject addEcg(@RequestParam String json) throws OperationException {
        return healthService.addEcg(json);
    }

    @RequestMapping(value = "getEcg", method = RequestMethod.POST)
    public JSONObject getEcg(@RequestParam String json) {
        return healthService.getEcg(json);
    }
    
    @RequestMapping(value = "getEcgWithDate", method = RequestMethod.POST)
    public JSONObject getEcgWithDate(@RequestParam String json) {
        return healthService.getEcgWithDate(json);
    }
    
    @RequestMapping(value = "getEcgAutoDataWithDate", method = RequestMethod.POST)
    public JSONObject getEcgAutoDataWithDate(@RequestParam String json) {
        return healthService.getEcgAutoDataWithDate(json);
    }
    
    @RequestMapping(value = "getEcgActiveDataWithDate", method = RequestMethod.POST)
    public JSONObject getEcgActiveDataWithDate(@RequestParam String json) {
        return healthService.getEcgActiveDataWithDate(json);
    }
    
    /**
     *  添加一条尿液分析仪数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月20日 上午9:17:07
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "addUran", method = RequestMethod.POST)
    public JSONObject addUran(@RequestParam String json) throws OperationException {
        return healthService.addUran(json);
    }
    
    /**
     *  获取尿液分析仪数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月20日 上午9:17:41
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getUran", method = RequestMethod.POST)
    public JSONObject getUran(@RequestParam String json) {
        return healthService.getUran(json);
    }
    
    /**
     *  添加一条尿酸分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月24日 下午2:27:03
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "addUa", method = RequestMethod.POST)
    public JSONObject addUa(@RequestParam String json) throws OperationException {
        return healthService.addUa(json);
    }
    
    /**
     *  获取尿酸分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月24日 下午2:27:18
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getUa", method = RequestMethod.POST)
    public JSONObject getUa(@RequestParam String json) {
        return healthService.getUa(json);
    }
    
    @RequestMapping(value = "addBloodLipid", method = RequestMethod.POST)
    public JSONObject addBloodLipid(@RequestParam String json) throws OperationException {
        return healthService.addBloodLipid(json);
    }
    
    @RequestMapping(value = "getBloodLipid", method = RequestMethod.POST)
    public JSONObject getBloodLipid(@RequestParam String json) {
        return healthService.getBloodLipid(json);
    }
}
