package com.lifeshs.app.aop;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.app.HealthIndex;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.common.UserStatus;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.pojo.app.member.AppValidCheckDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.healthIndex.HealthIndexDTO;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.tool.ITokenService;
import com.lifeshs.utils.StringUtil;

@Aspect
@Component
public class MemberAPPAOP {

    private static final Logger logger = Logger.getLogger("app");

    @Autowired
    private IMemberService memberService;
    @Autowired
    private ITokenService tokenService;

    private static List<String> returnHealthPointMethodName;
    static {
        returnHealthPointMethodName = new ArrayList<>();
        returnHealthPointMethodName.add("addBloodPressure");
        returnHealthPointMethodName.add("addOxygen");
        returnHealthPointMethodName.add("addLunginstrument");
        returnHealthPointMethodName.add("addBodyfatscale");
        returnHealthPointMethodName.add("modifyUserBaseInfo2");
        returnHealthPointMethodName.add("modifyUserBodyInfo2");
//        returnHealthPointMethodName.add("getHealthPoint");
        returnHealthPointMethodName.add("tickDietQuestinonnaireOption");
        returnHealthPointMethodName.add("tickSportQuestionnaireOption");
        returnHealthPointMethodName.add("tickMentalHealthQuestionnaireOption");
        returnHealthPointMethodName.add("setSleepHour");
        returnHealthPointMethodName.add("setCorporeityResult");
        returnHealthPointMethodName.add("setSubHealthSymptom");
        
        returnHealthPointMethodName.add("updateUserInfo");  // V24第二版的用户个人信息更新
    }

    /** 第一期的app切面 */
    @Pointcut(value = "execution(public com.alibaba.fastjson.JSONObject com.lifeshs.app.api.member.*.*(String, ..))")
    public void app() {
    }

    /** 第二期的app切面 */
    @Pointcut(value = "execution(public com.alibaba.fastjson.JSONObject com.lifeshs.app.api.member.v24.*.*(String))")
    public void v24() {
    }

    /** 免登录切面 */
    @Pointcut(value = "execution(public com.alibaba.fastjson.JSONObject com.lifeshs.app.api.member.release.*.*(..))")
    public void release() {
    }

    @Around(value = "release()")
    public JSONObject releaseAround(ProceedingJoinPoint point) throws Throwable {
        long s = System.currentTimeMillis();
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        
        String requestParam = null;
        if (args[0] instanceof String) {
            String param = (String) args[0];
            // URL转义
            param = URLDecoder.decode(param, "UTF-8");
            JSONObject root = JSONObject.parseObject(param);
            requestParam = root.toString();
            args[0] = param;
        }
        inAOP(s, methodName, requestParam);
        
        /** 执行原来的任务 */
        JSONObject returnJson = (JSONObject) point.proceed(args);
        outAOP(s, returnJson, methodName, null);
        return returnJson;
    }
    
    @Around(value = "app() or v24()")
    public JSONObject appAround(ProceedingJoinPoint point) throws Throwable {
        long s = System.currentTimeMillis();
        
        String methodName = point.getSignature().getName();

        Object[] args = point.getArgs();
        String param = (String) args[0];
        param = StringUtil.joinLine(param);
        // URL转义
        param = URLDecoder.decode(param, "UTF-8");
        
        JSONObject root = JSONObject.parseObject(param);
        JSONObject root_copy = JSONObject.parseObject(param);

        inAOP(s, methodName, root.toString());

        // 对拦截的json字符串数据进行处理
        AppValidCheckDTO returnDTO = appJsonValidCheck(root);
        JSONObject returnJson = null;

        if (!returnDTO.isSuccess()) {
            String msg = returnDTO.getMessage();
            Integer status = returnDTO.getStatus();
            returnJson = AppNormalService.error(msg, status);
            outAOP(s, returnJson, methodName, null);
            return returnJson;
        }

        /** 将已处理过的数据重新封装到args中 */
        UserDTO user = returnDTO.getUser();
        JSONObject aopData = new JSONObject();
        aopData.put(User.USER, user);
        root_copy.put("aopData", aopData);
        args[0] = root_copy.toString();

        /** 执行原本的任务 */
        returnJson = (JSONObject) point.proceed(args);
        logger.info("Member returnJson: "+returnJson.toString());
        outAOP(s, returnJson, methodName, user.getId());
        return returnJson;
    }

    private AppValidCheckDTO appJsonValidCheck(JSONObject root) {
        AppValidCheckDTO returnDTO = new AppValidCheckDTO();
        String msg = "";

        if (root.isEmpty()) {
            returnDTO.setSuccess(false);
            returnDTO.setMessage(Error.PARAMETER_MISSING);
            returnDTO.setStatus(ErrorCodeEnum.REQUEST.value());
            return returnDTO;
        }

        String version = root.getString(Normal.VER);
        JSONObject data = root.getJSONObject(Normal.DATA);
        Integer userId = data.getInteger(User.ID);
        String token = data.getString(Normal.TOKEN);

        if (userId == null || StringUtils.isBlank(token)) {
            returnDTO.setSuccess(false);
            returnDTO.setMessage(Error.PARAMETER_MISSING);
            returnDTO.setStatus(ErrorCodeEnum.REQUEST.value());
            return returnDTO;
        }
        UserDTO user = memberService.getUser(userId);
        /*try {
            user = memberService.getUser(userId);
        } catch (Exception e) {
            System.out.println("e:" + e);
        }*/

        if (user == null) {
            returnDTO.setSuccess(false);
            returnDTO.setMessage(NormalMessage.NO_SUCH_ACCOUNT);
            returnDTO.setStatus(ErrorCodeEnum.NOT_FOUND.value());
            return returnDTO;
        }

        int status = user.getStatus();
        if (UserStatus.normal.value() != status) {
            returnDTO.setSuccess(false);
            returnDTO.setMessage("该账号已被禁用");
            returnDTO.setStatus(ErrorCodeEnum.DENY.value());
            return returnDTO;
        }
        
        boolean versionEqualsNull = (version == null);
        if (!isTokenValid(versionEqualsNull ? null : userId, user.getToken(), token)) {
            returnDTO.setSuccess(false);
            returnDTO.setMessage("token不正确或已失效");
            returnDTO.setStatus(ErrorCodeEnum.AUTHORIZED.value());
            return returnDTO;
        }

        returnDTO.setUser(user);
        returnDTO.setSuccess(true);
        returnDTO.setMessage(msg);
        return returnDTO;
    }

    /**
     * token有效性校验
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月22日 上午10:33:40
     *
     * @param userId
     *            用户ID
     * @param userToken
     *            用户正确token
     * @param jsonToken
     *            待校验token
     * @return
     */
    private boolean isTokenValid(Integer userId, String userToken, String jsonToken) {
        /** token是否相同 */
        if (!StringUtils.equals(userToken, jsonToken)) {
            return false;
        }
        /** 这里对userId进行非空判断，如果userId的值为空，就不对token是否过期验证，避免之前发布的版本出现那不兼容的现象 */
        if (userId == null) {
            return true;
        }

        if (tokenService.isTokenOverTime(userId.toString(), User.SALT, jsonToken)) {
            return false;
        }
        return true;
    }

    /**
     * 进入切面后进行的操作
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月21日 下午8:13:05
     *
     * @param startMillis
     * @param methodName
     * @param param
     */
    private void inAOP(long startMillis, String methodName, String param) {
        logger.debug("时间戳:" + startMillis);
        logger.debug("方法名:" + methodName);
        logger.debug("json:" + param);
    }

    /**
     * 离开切面后进行的操作
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月21日 下午8:15:44
     *
     * @param startMillis
     *            开始时间戳
     * @param returnJson
     *            返回的json
     * @param methodName
     *            方法名
     */
    private void outAOP(long startMillis, JSONObject returnJson, String methodName, Integer userId) {
        String status = returnJson.getString(Normal.STATUS);
        Object message = returnJson.get(Normal.MESSAGE);
        Object data = returnJson.get(Normal.DATA);

        Object content = message == null ? data : message;

        if (returnHealthPointMethodName.contains(methodName) && userId != null) {
            UserRecordDTO recordDTO = memberService.getRecord(userId);
            HealthIndexDTO pointDTO = AppNormalService.getHealthIndex(recordDTO);
            JSONArray data_json = returnJson.getJSONArray(Normal.DATA);
            data_json = returnJson.getJSONArray(Normal.DATA);
            
            int point = pointDTO.getPoint();
            int healthStatus = AppNormalService.getHealthStatus(point);
            String healthSuggest = AppNormalService.getHealthSuggest(healthStatus);
            
            Map<String, Object> map = new HashMap<>();
            map.put(HealthIndex.POINT, String.valueOf(point));
            map.put(HealthIndex.EVALUATION_DATE, pointDTO.getEvaluationDate());
            map.put(HealthIndex.STATUS, healthStatus);
            map.put(HealthIndex.SUGGEST, healthSuggest);
            JSONObject data_json_0 = null;
            if (data_json.size() == 0) {
                data_json_0 = new JSONObject();
                data_json_0.put(HealthIndex.HEALTH_INDEX, map);
                data_json.add(data_json_0);
            } else {
                data_json_0 = data_json.getJSONObject(0);
                data_json_0.put(HealthIndex.HEALTH_INDEX, map);
            }
        }

        long e = System.currentTimeMillis();
        logger.debug("状态码:" + status);
        logger.debug("内容体:" + content);
        logger.debug("总耗时:" + (e - startMillis) + "毫秒\n");

        returnJson.put("methodName", methodName);
    }
}
