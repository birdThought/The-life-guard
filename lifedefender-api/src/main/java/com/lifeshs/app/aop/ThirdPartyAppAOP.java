package com.lifeshs.app.aop;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.dao1.transfer.ThirdPartyDao;
import com.lifeshs.dto.thirdparty.ClientDTO;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service.tool.ITokenService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;

/**
 * 第三方接口全局处理
 * Created by dengfeng on 2018/1/26 0026.
 */
@Aspect
@Component
public class ThirdPartyAppAOP {

    private static final Logger logger = Logger.getLogger("thirdparty");

    @Autowired
    private ITokenService tokenService;
    @Autowired
    private ThirdPartyDao thirdPartyDao;

    @Pointcut(value = "execution(public * com.lifeshs.app.api.thirdparty.*.*(..))")
    public void jztx() {}

    @Around(value = "jztx()")
    public JSONObject jztxAround(ProceedingJoinPoint point) throws Throwable {
        long s = System.currentTimeMillis();
        JSONObject returnJson = new JSONObject();

        Object[] args = point.getArgs();
        String param = ((ClientDTO) args[0]).getJson();

        // URL转义
        param = URLDecoder.decode(param, "UTF-8");
        JSONObject root = JSONObject.parseObject(param);

        String methodName = point.getSignature().getName();
        logger.info("时间戳:" + System.currentTimeMillis());
        logger.info("方法名:" + methodName);
        logger.info("json:" + root);

        ClientDTO clientDTO = (ClientDTO) args[0];
        String appid = root.getString(Normal.APPID);
        String ver = root.getString(Normal.VER);
        String token = root.getString(Normal.TOKEN);
        String timestamp = root.getString(Normal.TIME_STAMP);

        //检查不能为空的数据
        if (!isDataCompleted(appid, ver, token, timestamp)) {
            return  MAppNormalService.error("某些数据为空", 1);
        }
        //身份及时效性验证
        if (!validCheck(appid, token, timestamp)) {
            return MAppNormalService.error("身份验证出错", 2);

        }
        clientDTO.setAppid(appid);
        clientDTO.setVer(ver);
       /* if (args.length > 1) {
            JSONObject data = root.getJSONObject("data");
            if (args[1] == null) {
                args[1] = data.values().toArray()[0];
            } else {
                Object dataObj = JSONObject.toJavaObject(data, args[1].getClass());
                args[1] = dataObj;
            }
        }
*/
        try {
            returnJson = (JSONObject) point.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Object message = returnJson.get(Normal.MESSAGE);
        Object content = message == null ? returnJson.get(Normal.DATA) : message;

        logger.info("状态码:" + returnJson.getInteger(Normal.STATUS));
        logger.info("内容体:" + content);
        logger.info("总耗时:" + (System.currentTimeMillis() - s) + "毫秒\n");

        returnJson.put("methodName", methodName);
        return returnJson;
    }

    /**
     * 身份有效性校验，验证appid和token是否有效，验证timestamp在三分钟内
     * 
     * @param appid
     * @return
     */
    private boolean validCheck(String appid, String token, String timestamp) {
        //验证appid存在并有效，从表里按appid获得appsecert
        String appsecert =thirdPartyDao.findByAppsecert(appid);

        //验证token有效
        if(!tokenService.validThirdToken(token, timestamp, appsecert)){
            return false;
        }
        //验证token时效
        if(!tokenService.isThirdTokenOverTime(token, timestamp)){
            return false;
        }
        return true;
    }

    /**
     * 数据完整性校验
     * @param appId
     * @param ver
     * @param token
     * @param timestamp
     * @return
     */
    private boolean isDataCompleted(String appId, String ver, String token, String timestamp) {
        // 数据完整性校验
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(ver) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(token)) {
            return false;
        }
        return true;
    }

}
