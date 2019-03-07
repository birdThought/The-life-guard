package com.lifeshs.service.terminal.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lifeshs.service.common.impl.CommonServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.entity.device.TSportLocation;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.pojo.C3.C3Location;
import com.alibaba.fastjson.JSONArray;
import com.lifeshs.common.constants.common.TerminalErrorType;
import com.lifeshs.service.terminal.IC3Service;
import com.lifeshs.utils.DateTimeUtil;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.JSONHelper;

import net.sf.json.JSONObject;

/**
 * 版权归 TODO C3业务的实现类
 * 
 * @author duosheng.mo
 * @DateTime 2016年5月26日 下午5:18:37
 */
@Service("c3Service")
public class C3ServiceImpl extends CommonServiceImpl implements IC3Service {

    /**
     * 适配源，标准功能的对象
     */
    @Autowired
    TerminalAdaptee terminalAdaptee;

    private static final Logger logger = Logger.getLogger(C3ServiceImpl.class);

    @Override
    public String login(String imei, String password) {

        TUserTerminal userDevice = checkImei(imei, password);
        if (userDevice == null) {
            return returnErrorJson(TerminalErrorType.NotBound);
        }
        int heart = userDevice.getHeartFrequency();

        // 如果是关机状态，更新为开机
        if (userDevice.getStatus() == 0) {
            userDevice.setStatus(1);
            this.commonTrans.saveOrUpdate(userDevice);
        }
        return returnJson(String.valueOf(heart), password);
    }

    @Override
    public String datasync(String imei, String password) {

        return heartpackge(imei, password);
    }

    @Override
    public String heartpackge(String imei, String password) {
        TUserTerminal userDevice = checkImei(imei, password);
        if (userDevice == null) {
            return returnErrorJson(TerminalErrorType.NotBound);
        }

        List<Map<String, Object>> commonds = getCommonds(userDevice.getImei(), userDevice.getUserId());
        return returnJson(userDevice.getHeartFrequency().toString(), password, null, commonds);
    }

    @Override
    public String poweroff(String imei, String password) {
        TUserTerminal userDevice = checkImei(imei, password);
        if (userDevice == null) {
            return returnErrorJson(TerminalErrorType.NotBound);
        }
        int heart = userDevice.getHeartFrequency();

        // 如果是在线状态，更新为离线_0
        if (userDevice.getStatus() == 1) {
            userDevice.setStatus(0);
            this.commonTrans.saveOrUpdate(userDevice);
        }
        // 给亲情号发送关机提示短信
        // int userId = userDevice.getUserId();
        // String mobiles = contactsService.findFamilySos(userId);
        // if(StringUtils.isNotBlank(mobiles)){
        // try {
        // sendSms.send(mobiles, SMSCommand.SENPOWEROFF);
        // } catch (SMSException e) {
        // logger.error(e.getMessage(), e);
        // return returnJson(String.valueOf(heart), password,
        // TerminalErrorType.PoweroffSmsError, null);
        // }
        // }
        return returnJson(String.valueOf(heart), password);
    }

    @Override
    public String location(C3Location entity) {
        String result = "";
        String imei = entity.getImei();
        String password = entity.getPassword();
        TUserTerminal userDevice = checkImei(imei, password);
        if (userDevice == null) {
            return returnErrorJson(TerminalErrorType.NotBound);
        }
        int heart = userDevice.getHeartFrequency();
        TSportLocation sportLoca = null;
        if ("GPS".equalsIgnoreCase(entity.getLocaType())) {
            // 保存定位信息数据
            sportLoca = new TSportLocation();
            sportLoca.setLongitude(entity.getGps().getLng());
            sportLoca.setLatitude(entity.getGps().getLat());
            sportLoca.setLocationMode(1);
            sportLoca.setCreateDate(new Date());
        } else {
            // lbs转换为gps
            sportLoca = terminalAdaptee.getLocation().lbsGetGps(entity.getListLBS());
        }
        sportLoca.setUserId(userDevice.getUserId());
        sportLoca.setProductModel(entity.getType());
        sportLoca.setMeasureDate(DateTimeUtilT.dateTimeWithoutForm(entity.getDatetime()));
        sportLoca.setRealLoca(entity.getRealLoca());
        try {
            terminalAdaptee.getLocation().save(sportLoca);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = returnErrorJson(TerminalErrorType.DataFormatError);
        }
        result = returnJson(String.valueOf(heart), password, null, getCommonds(imei, userDevice.getUserId()));
        return result;
    }

    @Override
    public String returnJson(String heart, String passwrod) {
        // 把未发送的命令返回
        return returnJson(heart, passwrod, null, null);
    }

    public String returnJson(String heart, String passwrod, TerminalErrorType errorType,
            List<Map<String, Object>> action) {
        // 把未发送的命令返回
        if (action == null) {
            action = new ArrayList<>();
        }
        if (errorType == null) {
            errorType = TerminalErrorType.Normal;
        }

        return resultToJosn("", "ok", heart, passwrod, errorType.getName(), action);
    }

    @Override
    public String returnErrorJson(TerminalErrorType errorType) {
        logger.info("ErrorType:" + errorType.getName() + ", 值:" + errorType.value());
        return resultToJosn("", "fail", "300", null, errorType.getName(), new ArrayList<Map<String, Object>>());
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年6月1日 下午2:23:16
     * @serverComment 查看imei是否存在、绑定用户等信息
     *
     * @param imei
     * @param password
     * @return
     */
    private TUserTerminal checkImei(String imei, String password) {
        TUserTerminal userDevice = this.commonTrans.findUniqueByProperty(TUserTerminal.class, "imei", imei);
        if (userDevice != null && userDevice.getHeartFrequency().equals(0))
            userDevice.setHeartFrequency(300);
        return userDevice;
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年6月13日 下午5:34:47
     * @serverCode 查询没有同步的指令
     *
     * @param imei
     * @param userId
     * @return
     */
    private List<Map<String, Object>> getCommonds(String imei, int userId) {
        // 查询没有同步的指令
        List<Map<String, Object>> commonds = terminalAdaptee.getDeviceService().findCommond(imei, userId);
        // 同步后更新指令状态
        terminalAdaptee.getDeviceService().updateCommond(imei, userId);
        return commonds;
    }

    /**
     * @author duosheng.mo
     * @DateTime 2015-4-25 下午04:07:05
     * @serverCode 服务代码
     * @serverComment 返回给终端的数据
     *
     * @param command
     *            指令
     * @param status
     *            状态
     * @param heart
     *            心跳包频率
     * @param action
     *            终端修改的数据
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private String resultToJosn(Object command, String status, String heart, String password, String errorMsg,
            List<Map<String, Object>> action) {
        Map resultMap = new HashMap();
        if (command == null || command == "") {
            command = "result";
        }
        /*
         * for(Map<String,Object> mapAction : action){ Object obj =
         * mapAction.get("repeats"); mapAction.remove("repeats");
         * mapAction.put("repeat", obj); }
         */
        for (int i = 0; i < action.size(); i++) {
            if (action.get(i).get("eventname").equals("setmode")) { // 运行模式，特殊处理
                action.get(i).put("defaultmode", action.get(i).get("repeats"));
                action.get(i).put("repeat", new JSONObject());
                action.get(i).remove("repeats");
            } else {
//                System.out.println("repeats:" + action.get(i).get("repeats"));
                JSONObject repeat_json = JSONObject.fromObject((String) action.get(i).get("repeats"));
                action.get(i).put("repeat", repeat_json);
                action.get(i).remove("repeats");
            }
            JSONArray msg_json = JSONArray.parseArray((String) action.get(i).get("msg"));
            action.get(i).put("msg", msg_json);
            JSONArray time_json = JSONArray.parseArray((String) action.get(i).get("time"));
            action.get(i).put("time", time_json);
        }
        resultMap.put("command", command);
        Map dataMap = new HashMap();
        dataMap.put("status", status);
        dataMap.put("heart", heart);
        dataMap.put("datetime", DateTimeUtil.yyyy_MM_ddHHmmss(new Date()));
        dataMap.put("action", action);
        dataMap.put("password", password);
        // dataMap.put("errorMsg", errorMsg);
        resultMap.put("data", dataMap);

        return JSONHelper.map2json(resultMap);
    }
}
