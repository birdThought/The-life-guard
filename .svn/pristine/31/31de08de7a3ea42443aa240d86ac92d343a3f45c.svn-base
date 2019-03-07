package com.lifeshs.app.aop;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.manager.MAppManagerController;
import com.lifeshs.app.api.manager.MAppServeController;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.OrgUser;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.pojo.app.manager.MappValidCheckDTO;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service.tool.ITokenService;

@Aspect
@Component
public class ManagementAppAOP {

    private static final Logger logger = Logger.getLogger("mapp");

    @Autowired
    private ICommonTrans common;

    @Autowired
    private ITokenService tokenService;

    /** 额外的管理员权限方法名称合集 */
    private static List<String> extraAdminMethodNames;

    /** 需要登录校验的方法名称合集 */
    private static List<String> includeMethods;

    static {
        extraAdminMethodNames = new ArrayList<>();
        extraAdminMethodNames.add("getNotOpenServeList");
        extraAdminMethodNames.add("openServe");
        extraAdminMethodNames.add("getOrgServe");
        extraAdminMethodNames.add("modifyOrgServe");

        includeMethods = new ArrayList<>();
        includeMethods.add("profile");
        includeMethods.add("modifyProfile");
        includeMethods.add("modifyMobile");
        includeMethods.add("modifyPassword");
        includeMethods.add("sendReport");
        includeMethods.add("orgProfile");
    }

    @Pointcut(value = "execution(public * com.lifeshs.app.api.manager.MAppVersionController..*(java.lang.String)) && args(param)", argNames = "param")
    public void version(String param) {}

    @Pointcut(value = "execution(public * com.lifeshs.app.api.manager.MAppMineController..*(String, ..))")
    public void mine() {}

    @Pointcut(value = "execution(public * com.lifeshs.app.api.manager.MAppServeController..*(..))")
    public void server() {}

    @Pointcut(value = "execution(public * com.lifeshs.app.api.manager.MAppManagerController..*(String))")
    public void manager() {}

    @Pointcut(value = "execution(public * com.lifeshs.app.api.manager.MAppIndexController..*(String))")
    public void index() {}
    
    @Pointcut(value = "execution(public * com.lifeshs.app.api.manager.MAppDrugsController..*(..))")
    public void drugs() {}

    //dengfeng add
    // 过滤release包下面的控制器 release目录下的文件为对外公开的，不需要做登录验证处理
    @Pointcut(value = "execution(public * com.lifeshs.app.api.store..*(..)) && !execution(public * com.lifeshs.app.api.store.release.*.*(..))")
    public void store() {}

    // 扫描release目录下的接口，只作字符解码，logger记录操作
    @Pointcut(value = "execution(public * com.lifeshs.app.api.store.release.*.*(..))")
    public void release() {}
    
    @Around(value = "mine()")
    public JSONObject mineAround(ProceedingJoinPoint point) throws Throwable {
        long s = System.currentTimeMillis();

        JSONObject returnJson = new JSONObject();

        Object[] args = point.getArgs();
        String param = (String) args[0];
        // URL转义
        param = URLDecoder.decode(param, "UTF-8");
        String methodName = point.getSignature().getName();

        JSONObject root = JSONObject.parseObject(param);

        JSONObject root_copy = JSONObject.parseObject(param);

        logger.debug("时间戳:" + System.currentTimeMillis());
        logger.debug("方法名:" + methodName);
        logger.debug("json:" + root);

        MappValidCheckDTO mao = mineValidCheck(methodName, root);
        if (mao.isChecked()) {
            if (!mao.isSuccess()) {
                returnJson = MAppNormalService.error(mao.getMessage(), mao.getStatus());
            } else {
                JSONObject aopData = new JSONObject();
                aopData.put(OrgUser.USER, mao.getUser());
                root_copy.put("aopData", aopData);
                
                /** 将已处理过的数据重新封装到args中 */
                args[0] = root_copy.toString();
                
                returnJson = (JSONObject) point.proceed(args);
            }
        } else {
            returnJson = (JSONObject) point.proceed();
        }

        int status = returnJson.getInteger(Normal.STATUS);
        Object message = returnJson.get(Normal.MESSAGE);
        Object data = returnJson.get(Normal.DATA);

        Object content = message == null ? data : message;
        long e = System.currentTimeMillis();

        logger.debug("状态码:" + status);
        logger.debug("内容体:" + content);
        logger.debug("总耗时:" + (e - s) + "毫秒\n");

        returnJson.put("methodName", methodName);

        return returnJson;
    }

    private MappValidCheckDTO mineValidCheck(String methodName, JSONObject root) {
        MappValidCheckDTO mao = new MappValidCheckDTO();
        mao.setChecked(false);

        if (includeMethods.contains(methodName)) {
            String type = root.getString(Normal.TYPE);
            if (methodName.equals("modifyProfile") && type.equals(Normal.ANDROID_TYPE)) {
                mao = validCheck(root, true);
            } else {
                mao = validCheck(root, false);
            }
        }

        // 特殊
        if (methodName.equals("updateOrg")) {
            Integer isImprove = root.getJSONObject(Normal.DATA).getInteger(Normal.IS_IMPROVE);
            if (isImprove != null && isImprove.intValue() == 0) {
                mao = validCheck(root, true);
            }
        }

        return mao;
    }

    @Around(value = "server() or manager() or index() or store() or drugs()")
    public JSONObject serveAround(ProceedingJoinPoint point) throws Throwable {
        long s = System.currentTimeMillis();
        JSONObject returnJson = new JSONObject();
        
        Object[] args = point.getArgs();
        String param = null;
        if(args[0] instanceof String) {
            param = (String) args[0];
        }else {
            param = ((SubmitDTO) args[0]).getJson();
        }
        System.out.println("param:"+param);
        logger.info(String.format("param:%s", param));
        
        // URL转义
        param = URLDecoder.decode(param, "UTF-8");
        JSONObject root = JSONObject.parseObject(param);
        JSONObject root_copy = JSONObject.parseObject(param);
        logger.info(String.format("rootJson:%s",root.toJSONString()));//for test
        logger.info(String.format("root_copy:%s",root_copy));
        System.out.println("rootJson:"+root.toJSONString());
        System.out.println("root_copy:"+root_copy);

        String methodName = point.getSignature().getName();
        logger.debug("时间戳:" + System.currentTimeMillis());
        logger.debug("方法名:" + methodName);
        logger.debug("json:" + root);
        System.out.println("json:"+root);

        MappValidCheckDTO mv = validCheck(root, false);
        if (!mv.isSuccess()) {
            returnJson = MAppNormalService.error(mv.getMessage(), mv.getStatus());
        } else {
            TOrgUser user = mv.getUser();
            if (isAdminAction(user, point)) {
                returnJson = MAppNormalService.error(Error.FORBIDDEN, 403);
            }
            if(args[0] instanceof String) {
                JSONObject aopData = new JSONObject();
                aopData.put(OrgUser.USER, mv.getUser());
                root_copy.put("aopData", aopData);
                /** 将已处理过的数据重新封装到args中 */
                args[0] = root_copy.toString();
            }else {
                try {
                    SubmitDTO sumbitDTO = (SubmitDTO) args[0];
                    sumbitDTO.setType(root_copy.getString("type"));
                    sumbitDTO.setVer(root_copy.getString("ver"));
                    sumbitDTO.setUser(mv.getUser());
                    if(args.length>1){
                        JSONObject data = root_copy.getJSONObject("data");
                        if(args[1] == null){
                            args[1] = data.values().toArray()[0];
                        }else {
                            Object dataObj = JSONObject.toJavaObject(data, args[1].getClass());
                            args[1] = dataObj;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                returnJson = (JSONObject) point.proceed(args);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        int status = returnJson.getInteger(Normal.STATUS);
        Object message = returnJson.get(Normal.MESSAGE);
        Object data = returnJson.get(Normal.DATA);

        Object content = message == null ? data : message;
        long e = System.currentTimeMillis();

        logger.debug("状态码:" + status);
        logger.debug("内容体:" + content);
        logger.debug("总耗时:" + (e - s) + "毫秒\n");

        returnJson.put("methodName", methodName);

        return returnJson;
    }

    @Around(value = "release()")
    private JSONObject releaseAround(ProceedingJoinPoint point) throws Throwable {
        long s = System.currentTimeMillis();

        JSONObject returnJson = new JSONObject();

        Object[] args = point.getArgs();
        String param = ((SubmitDTO) args[0]).getJson();
        // URL解码
        param = URLDecoder.decode(param, "UTF-8");
        String methodName = point.getSignature().getName();

        JSONObject root = JSONObject.parseObject(param);

        logger.debug("时间戳:" + System.currentTimeMillis());
        logger.debug("方法名:" + methodName);
        logger.debug("json:" + root);

        SubmitDTO sumbitDTO = new SubmitDTO();
        sumbitDTO.setType(root.getString("type"));
        sumbitDTO.setVer(root.getString("ver"));
        if(args.length>1){
            JSONObject data = root.getJSONObject("data");
            if(args[1] == null){
                args[1] = data.values().toArray()[0];
            } else {
                Object dataObj = JSONObject.toJavaObject(data, args[1].getClass());
                args[1] = dataObj;
            }
        }
        returnJson = (JSONObject) point.proceed(args);

        int status = returnJson.getInteger(Normal.STATUS);
        Object message = returnJson.get(Normal.MESSAGE);
        Object data = returnJson.get(Normal.DATA);

        Object content = message == null ? data : message;
        long e = System.currentTimeMillis();

        logger.debug("状态码:" + status);
        logger.debug("内容体:" + content);
        logger.debug("总耗时:" + (e - s) + "毫秒\n");

        returnJson.put("methodName", methodName);

        return returnJson;
    }
    
    /**
     * 有效性校验
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月25日 下午1:50:43
     *
     * @param root
     * @return
     */
    private MappValidCheckDTO validCheck(JSONObject root, boolean ignorePhoto) {
        MappValidCheckDTO mv = new MappValidCheckDTO();
        mv.setSuccess(false);
        mv.setChecked(true);

        String type = root.getString(Normal.TYPE);
        String ver = root.getString(Normal.VER);
        Integer userId = root.getInteger(OrgUser.M_ID);
        String token = root.getString(Normal.TOKEN);
        String timestamp = root.getString(Normal.TIME_STAMP);
        String sign = root.getString(Normal.SIGN);

        if (!isDataCompleted(type, ver, userId, token, timestamp, sign)) {
            mv.setMessage(Error.PARAMETER_MISSING);
            mv.setStatus(400);
            return mv;
        }

        // TOrgUser
        TOrgUser user = common.getEntity(TOrgUser.class, userId);
        if (user == null) {
            mv.setMessage(NormalMessage.NO_SUCH_ACCOUNT);
            mv.setStatus(404);
            return mv;
        }
        // userToken
        String userToken = user.getToken();

        if (!isTokenValid(userToken, userId, token)) {
            mv.setMessage("token验证不通过");
            mv.setStatus(401);
            return mv;
        }

        mv.setSuccess(true);
        mv.setUser(user);
        return mv;
    }

    /**
     * 数据完整性校验
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月25日 下午1:56:45
     *
     * @param type
     * @param ver
     * @param token
     * @param timestamp
     * @param sign
     * @return
     */
    private boolean isDataCompleted(String type, String ver, Integer userId, String token, String timestamp,
            String sign) {
        // 数据完整性校验
        if (StringUtils.isBlank(type) || StringUtils.isBlank(ver) || StringUtils.isBlank(token)) {
            return false;
        }
        if (userId == null) {
            return false;
        }
        return true;
    }

    /**
     * token有效性校验
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月25日 下午2:44:52
     *
     * @param userToken
     * @param userId
     * @param dataToken
     * @return
     */
    private boolean isTokenValid(String userToken, Integer userId, String dataToken) {

        // same
        boolean isSameToken = StringUtils.equals(userToken, dataToken);
        if (!isSameToken) {
            return false;
        }

        // over time
        /*boolean isTokenOverTime = tokenService.isTokenOverTime(userId.toString(), OrgUser.SALT, dataToken);
        if (isTokenOverTime) {
            return false;
        }*/

        return true;
    }

    private boolean isAdminUser(TOrgUser user) {
        int userType = user.getUserType().intValue();
        if (userType == 0 || userType == 2) {
            return true;
        }
        if (userType == 1) {
            return false;
        }
        return false;
    }

    private boolean isAdminAction(TOrgUser user, ProceedingJoinPoint point) {
        if (!isAdminUser(user) && point.getTarget() instanceof MAppManagerController) {
            return true;
        }

        String methodName = point.getSignature().getName();

        if (!isAdminUser(user) && point.getTarget() instanceof MAppServeController
                && extraAdminMethodNames.contains(methodName)) {
            return true;
        }

        return false;
    }
}
