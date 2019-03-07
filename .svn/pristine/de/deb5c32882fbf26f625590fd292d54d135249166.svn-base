package com.lifeshs.app.api.store;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.terminal.app.health.IAppHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuj
 * @createTime 2017-09-07 14:22:33
 * <p>功能与{@link com.lifeshs.app.api.member.HealthController} 基本相同.
 * 因为管理APP与应用APP上传格式的不同和解耦的需要,重新建立管理APP端的用户健康数据获取,
 * 代码基本copy {@see HealthController},文档也请参考{@see HealthController};</p>
 *
 */
@RestController
@RequestMapping("/mapp/health")
public class HealthController {

    @Resource(name = "appHealthService")
    private IAppHealthService healthService;

    /**
     * 获取健康范围值
     *
     * @author wenxian.cai
     * @DateTime 2016年8月6日下午2:17:48
     * @serverComment 服务注解
     * @param submitDTO
     */
    @RequestMapping(value = "getHealthArea", method = RequestMethod.POST)
    public JSONObject getHealthArea(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getHealthArea(json);
    }

    /**
     * 获取健康范围值
     *
     * @author yuhang.weng
     * @DateTime 2017年2月21日 下午4:53:32
     *
     * @param submitDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getHealthArea2", method = RequestMethod.POST)
    public JSONObject getHealthArea2(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getHealthArea2(json);
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
    public JSONObject getHeartRate(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getHeartRate(json);
    }



    /**
     * 获取体温数据
     *
     * @author wenxian.cai
     * @DateTime 2016年8月4日下午1:55:25
     * @serverComment 服务注解
     * @param submitDTO
     *
     */
    @RequestMapping(value = "getTemperature", method = RequestMethod.POST)
    public JSONObject getTemperature(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getTemperature(json);
    }

    @Autowired
    IMemberService memberService;

    /**
     * 获取血压数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:09:34
     * @serverComment 服务注解
     *
     * @param submitDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getBloodPressure", method = RequestMethod.POST)
    public JSONObject getBloodPressure(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getBloodPressure(json);
    }

    /**
     * <p>适配 {@link com.lifeshs.app.api.member.HealthController},所以需要对参数进行解析和组装.
     * 实质上和AOP进行的预处理是一样的</p>
     *
     * @param submitDTO
     * @return
     */
    private JSONObject getJsonObject(SubmitDTO submitDTO) {
        JSONObject submitJSON = JSON.parseObject(submitDTO.getJson());

        // 获取aopData中的userDTO
        String userId = submitJSON.getJSONObject("data").getString("userId");
        UserDTO user = memberService.getUser(Integer.parseInt(userId));

        // 开始装配JSON
        JSONObject json = new JSONObject();
        json.put("type","APP_A");
        json.put("ver", 1);
        JSONObject data = new JSONObject();
        data.put("userId",userId);
        data.put("msg", submitJSON.getJSONObject("data").getJSONArray("msg"));
        json.put("data", data);
        JSONObject aopData = new JSONObject();
        aopData.put(User.USER, user);
        json.put("aopData", aopData);

        System.out.println(json.toJSONString()); // for debug
        return json;
    }


    /**
     * 获取血氧数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:13:03
     * @serverComment 服务注解
     *
     * @param submitDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getOxygen", method = RequestMethod.POST)
    public JSONObject getOxygen(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getOxygen(json);
    }



    /**
     * 获取血糖数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:14:01
     * @serverComment 服务注解
     *
     * @param submitDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getGlucometer", method = RequestMethod.POST)
    public JSONObject getGlucometer(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getGlucometer(json);
    }



    /**
     * 获取肺活仪数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:14:30
     * @serverComment 服务注解
     *
     * @param submitDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getLunginstrument", method = RequestMethod.POST)
    public JSONObject getLunginstrument(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getLunginstrument(json);
    }



    /**
     * 获取体脂秤数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:15:07
     * @serverComment 服务注解
     *
     * @param submitDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getBodyfatscale", method = RequestMethod.POST)
    public JSONObject getBodyfatscale(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getBodyfatscale(json);
    }



    /**
     * 获取手环数据
     *
     * @author dachang.luo
     * @DateTime 2016年6月23日 上午11:15:58
     * @serverComment 服务注解
     *
     * @param submitDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getBand", method = RequestMethod.POST)
    public JSONObject getBand(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
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
    public JSONObject getBandDetail(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
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
    public JSONObject getAllData(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getAllData(json);
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
    public JSONObject getDataCount(SubmitDTO submitDTO) throws Exception {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getDataCount(json);
    }

    @RequestMapping(value = "getEcg", method = RequestMethod.POST)
    public JSONObject getEcg(SubmitDTO submitDTO) {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getEcg(json);
    }

    @RequestMapping(value = "getEcgWithDate", method = RequestMethod.POST)
    public JSONObject getEcgWithDate(SubmitDTO submitDTO) {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getEcgWithDate(json);

    }
    @RequestMapping(value = "getEcgAutoDataWithDate", method = RequestMethod.POST)
    public JSONObject getEcgAutoDataWithDate(SubmitDTO submitDTO) {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getEcgAutoDataWithDate(json);
    }

    @RequestMapping(value = "getEcgActiveDataWithDate", method = RequestMethod.POST)
    public JSONObject getEcgActiveDataWithDate(SubmitDTO submitDTO) {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getEcgActiveDataWithDate(json);
    }

    /**
     *  获取尿液分析仪数据
     *  @author yuhang.weng
     *	@DateTime 2017年4月20日 上午9:17:41
     *
     *  @param submitDTO
     *  @return
     */
    @RequestMapping(value = "getUran", method = RequestMethod.POST)
    public JSONObject getUran(SubmitDTO submitDTO) {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getUran(json);
    }


    /**
     *  获取尿酸分析仪的数据
     *  @author yuhang.weng
     *	@DateTime 2017年4月24日 下午2:27:18
     *
     *  @param submitDTO
     *  @return
     */
    @RequestMapping(value = "getUa", method = RequestMethod.POST)
    public JSONObject getUa(SubmitDTO submitDTO) {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getUa(json);
    }

    @RequestMapping(value = "getBloodLipid", method = RequestMethod.POST)
    public JSONObject getBloodLipid(SubmitDTO submitDTO) {
        String json = getJsonObject(submitDTO).toJSONString();
        return healthService.getBloodLipid(json);
    }
}
