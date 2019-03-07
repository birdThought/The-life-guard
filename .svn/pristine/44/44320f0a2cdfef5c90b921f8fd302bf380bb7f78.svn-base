package com.lifeshs.service.terminal.app.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Contact;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.app.Fence;
import com.lifeshs.common.constants.app.MonitorTracks;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.Notice;
import com.lifeshs.common.constants.app.Terminal;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.healthPackage.HealthDescription;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.entity.data.TDataHealthDescribe;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUser;
import com.lifeshs.entity.member.TUserBlackWhiteList;
import com.lifeshs.entity.member.TUserElectronicFence;
import com.lifeshs.entity.member.TUserMonitorTrack;
import com.lifeshs.entity.member.TUserNotice;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.contacts.IContactsService;
import com.lifeshs.service.terminal.ITerminalService;
import com.lifeshs.service.terminal.app.IAppService;
import com.lifeshs.utils.MD5Utils;

import jodd.util.StringUtil;

/**
 * 
 * @author dachang.luo
 * @DateTime 2016年6月20日上午9:26:46
 */
@Service("appService")
public class AppServiceImpl extends AppNormalService implements IAppService {

    @Autowired
    private IContactsService contactsService;

    @Autowired
    private ITerminalService terminalService;

    @Autowired
    private ICommonTrans commonDao;

    @Override
    public JSONObject getTerminalMobile(String json) throws Exception {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);

        List<TUserTerminal> terminals = terminalService.geTUserTerminals(userId);
        String terminalMobile = "";
        for (int i = 0; i < terminals.size(); i++) {
            if (terminals.get(i).getTerminalType().equals(terminalType))
                terminalMobile = terminals.get(i).getMobile();
        }
        if (StringUtil.isBlank(terminalMobile)) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", terminalMobile);

        return success(map);
    }

    @Override
    public JSONObject setTerminalMobile(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);
        String mobile = mm_0.getString(Normal.MOBILE);

        List<Map<String, Object>> terminals = commonDao.findByPropertyByMap(TUserTerminal.class, User.ID, userId); // 修改获取方式
        boolean bool = false;
        for (int i = 0; i < terminals.size(); i++) {
            if (terminals.get(i).get(Terminal.TYPE).equals(terminalType)) {
                bool = terminalService.setTerminalMobile(userId, terminalType, mobile);
                break;
            }
        }
        if (!bool) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }

        return success();
    }

    @Override
    public JSONObject getRunMode(String json) throws Exception {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);

        int operationMode = terminalService.getTerminalOperationModeNumber(userId, terminalType);
        Map<String, Object> mode = new HashMap<String, Object>();
        mode.put(Terminal.OPERATION_MODE, operationMode);
        mode.put(Terminal.TYPE, terminalType);

        return success(mode);
    }

    @Override
    public JSONObject setRunMode(String json) throws Exception {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);
        int mode = mm_0.getIntValue(Terminal.RUN_MODE);

        boolean bool = terminalService.setTerminalOperationMode(userId, terminalType, mode);
        if (!bool) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject getFrequency(String json) throws Exception {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);

        TUserTerminal terminal = terminalService.getMoniterFrequency(userId, terminalType);
        if (terminal == null) {
            return success(NormalMessage.TERMINAL_NO_FOUND);
        }
        if (terminal.getHeartFrequency() == null) {
            return success(NormalMessage.NO_DATA);
        }

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put(Terminal.HEARTRATE_FREQUENCY, terminal.getHeartFrequency());
        returnMap.put(Terminal.LOCATION_FREQUENCY, terminal.getLocationFrequency());
        returnMap.put(Terminal.AUTO_FREQUENCY_70, terminal.getAutoFrequency70());
        returnMap.put(Terminal.AUTO_FREQUENCY_50, terminal.getAutoFrequency50());
        returnMap.put(Terminal.AUTO_FREQUENCY_30, terminal.getAutoFrequency30());

        return success(returnMap);
    }

    @Override
    public JSONObject setFrequency(String json) throws Exception {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);
        int heartFrequency = mm_0.getIntValue(Terminal.HEARTRATE_FREQUENCY);
        int locationFrequency = mm_0.getIntValue(Terminal.LOCATION_FREQUENCY);
        int autoFrequency70 = mm_0.getIntValue(Terminal.AUTO_FREQUENCY_70);
        int autoFrequency50 = mm_0.getIntValue(Terminal.AUTO_FREQUENCY_50);
        int autoFrequency30 = mm_0.getIntValue(Terminal.AUTO_FREQUENCY_30);

        boolean isSuccessModify = terminalService.modifyMoniterFrequency(userId, terminalType, heartFrequency,
                locationFrequency, autoFrequency70, autoFrequency50, autoFrequency30);
        if (isSuccessModify) {
            return success();
        } else {
            return error(Error.FAIL_ACTION);
        }
    }

    @Override
    public JSONObject getNotices(String json) throws Exception {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int terminalType_i = mm_0.getIntValue(Terminal.TYPE);
        String terminalType = terminalTypeToString(terminalType_i);

        List<TUserNotice> notices = terminalService.getTerminalNotices(userId, terminalType);
        if (notices == null || notices.size() == 0) {
            return success(NormalMessage.NO_DATA);
        }
        List<Map<String, Object>> noticesMap = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < notices.size(); i++) {
            Map<String, Object> noticeMap = new HashMap<String, Object>();
            noticeMap.put(Notice.ID, notices.get(i).getId());
            noticeMap.put(Notice.TIME, notices.get(i).getTime());
            noticeMap.put(Notice.WEEKS, notices.get(i).getWeeks());
            noticeMap.put(Notice.CONTENT, notices.get(i).getContent());
            noticeMap.put(Notice.STATUS, notices.get(i).getStatus());

            noticesMap.add(noticeMap);
        }

        return success(noticesMap);
    }

    @Override
    public JSONObject addNotice(String json) throws Exception {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String time = mm_0.getString(Notice.TIME);
        String weeks = mm_0.getString(Notice.WEEKS);
        String content = mm_0.getString(Notice.CONTENT);
        // String status = mm_0.getString(AppNotice.STATUS);
        Double intervalM = mm_0.getDouble(Notice.INTERVAL_MINUTE);
        int terminalType_i = mm_0.getIntValue(Terminal.TYPE);
        String terminalType = terminalTypeToString(terminalType_i);

        boolean isSuccessAdd = terminalService.addTerminalNotice(userId, terminalType, weeks, time, content, intervalM);
        if (isSuccessAdd) {
            return success();
        } else {
            return error(Error.FAIL_ACTION);
        }
    }

    @Override
    public JSONObject modifyNotice(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int noticeId = mm_0.getIntValue(Notice.ID);
        String time = mm_0.getString(Notice.TIME);
        String weeks = mm_0.getString(Notice.WEEKS);
        String content = mm_0.getString(Notice.CONTENT);
        Double intervalM = mm_0.getDouble(Notice.INTERVAL_MINUTE);
        int status = mm_0.getIntValue(Notice.STATUS);
        int terminalType_i = mm_0.getIntValue(Terminal.TYPE);
        String terminalType = terminalTypeToString(terminalType_i);

        boolean bool = terminalService.modTerminalNotice(userId, terminalType, weeks, time, content, intervalM, status,
                noticeId);
        if (!bool) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject delNotice(String json) throws Exception {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int noticeId = mm_0.getIntValue(Notice.ID);
        int terminalType_i = mm_0.getIntValue(Terminal.TYPE);
        String terminalType = terminalTypeToString(terminalType_i);

        boolean bool = terminalService.delTerminalNotice(userId, noticeId, terminalType);
        if (!bool) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    private String terminalTypeToString(int terminalType) {
        String terminalType_s = "";
        switch (terminalType) {
        case 0:
            terminalType_s = null;
            break;
        case 1:
            terminalType_s = "HL03";
            break;
        case 2:
            terminalType_s = "C3";
            break;
        }
        return terminalType_s;
    }

    @Override
    public JSONObject getBlackList(String json) throws Exception {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);

        List<TUserBlackWhiteList> blacksList = terminalService.getDeviceBlackList(userId, terminalType);
        if (blacksList == null || blacksList.size() == 0) {
            return success(NormalMessage.NO_DATA);
        }
        List<Map<String, Object>> blacksMapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < blacksList.size(); i++) {
            Map<String, Object> blackMap = new HashMap<String, Object>();
            blackMap.put(Contact.BLACK_PHONE_ID, blacksList.get(i).getId());
            blackMap.put(Contact.BLACK_PHONE, blacksList.get(i).getLimited());
            blacksMapList.add(blackMap);
        }

        return success(blacksList);
    }

    @Override
    public JSONObject setBlackList(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);
        String phone = mm_0.getString(Contact.BLACK_PHONE);
        String name = mm_0.getString(Contact.BLACK_NAME);

        boolean bool = terminalService.addBlackList(userId, phone, name, terminalType);
        if (!bool) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject delBlackList(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);
        int phoneId = mm_0.getIntValue(Contact.BLACK_PHONE_ID);

        boolean bool = terminalService.delBlackList(userId, phoneId, terminalType);
        if (!bool) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject getlocation(String json) throws Exception {
        // JSONObject root = JSONObject.parseObject(json);
        // JSONObject data = root.getJSONObject(AppNormal.DATA);
        // JSONArray mm = data.getJSONArray(AppNormal.MESSAGE);
        // int userId = data.getIntValue(AppUser.ID);
        //
        // String terminalType =
        // mm.getJSONObject(0).getString(AppTerminal.TYPE);
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONObject setMonitor(String json) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONObject getElectronicFence(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);

        List<TUserElectronicFence> tUserElectronicFence = terminalService.getElectronicFences(userId, terminalType);
        if (tUserElectronicFence.size() == 0) {
            return success(NormalMessage.NO_DATA);
        }
        List<Map<String, Object>> fencesMap = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < tUserElectronicFence.size(); i++) {

            TUserElectronicFence electronicFence_i = tUserElectronicFence.get(i);

            Map<String, Object> fenceMap = new HashMap<String, Object>();
            fenceMap.put(Fence.ID, electronicFence_i.getId());
            fenceMap.put(Fence.NUMBER, electronicFence_i.getNumber());
            fenceMap.put(Fence.LONGTITUDE, electronicFence_i.getLongitude());
            fenceMap.put(Fence.LATITUDE, electronicFence_i.getLatitude());
            fenceMap.put(Fence.RADIUS, electronicFence_i.getRadius());
            fenceMap.put(Fence.ADDRESS, electronicFence_i.getAddress());
            fenceMap.put(Fence.WARNING_TYPE, electronicFence_i.getWarningType());
            fenceMap.put(Fence.WARNING_PHONE, electronicFence_i.getWarningPhone());
            fenceMap.put(Fence.START_TIME_1, electronicFence_i.getStartTime1());
            fenceMap.put(Fence.END_TIME_1, electronicFence_i.getEndTime1());
            fenceMap.put(Fence.START_TIME_2, electronicFence_i.getStartTime2());
            fenceMap.put(Fence.END_TIME_2, electronicFence_i.getEndTime2());
            fenceMap.put(Fence.START_TIME_3, electronicFence_i.getStartTime3());
            fenceMap.put(Fence.END_TIME_3, electronicFence_i.getEndTime3());
            fenceMap.put(Fence.ENABLED, electronicFence_i.getEnabled());

            fencesMap.add(fenceMap);
        }

        return success(fencesMap);
    }

    @Override
    public JSONObject addElectronicFence(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);
        int number = mm_0.getIntValue(Fence.NUMBER);
        double longitude = mm_0.getDouble(Fence.LONGTITUDE);
        double latitude = mm_0.getDouble(Fence.LATITUDE);
        int radius = mm_0.getIntValue(Fence.RADIUS);
        String address = mm_0.getString(Fence.ADDRESS);
        int warningType = mm_0.getIntValue(Fence.WARNING_TYPE);
        String warningPhone = mm_0.getString(Fence.WARNING_PHONE);
        Time startTime1 = (Time) mm_0.get(Fence.START_TIME_1);
        Time endTime1 = (Time) mm_0.get(Fence.END_TIME_1);
        Time startTime2 = (Time) mm_0.get(Fence.START_TIME_2);
        Time endTime2 = (Time) mm_0.get(Fence.END_TIME_2);
        Time startTime3 = (Time) mm_0.get(Fence.START_TIME_3);
        Time endTime3 = (Time) mm_0.get(Fence.END_TIME_3);
        // String enable = (String) mm_0.get(AppFence.ENABLED);

        TUserElectronicFence fence = new TUserElectronicFence();
        fence.setNumber(number);
        fence.setLongitude(longitude);
        fence.setLatitude(latitude);
        fence.setRadius(radius);
        fence.setAddress(address);
        fence.setWarningType(warningType);
        fence.setWarningPhone(warningPhone);
        fence.setStartTime1(startTime1);
        fence.setStartTime2(startTime2);
        fence.setStartTime3(startTime3);
        fence.setEndTime1(endTime1);
        fence.setEndTime2(endTime2);
        fence.setEndTime3(endTime3);

        boolean bool = terminalService.addElectronicFence(userId, terminalType, fence);
        if (!bool) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject modifyElectronicFence(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);
        int number = mm_0.getIntValue(Fence.NUMBER);
        double longitude = mm_0.getDouble(Fence.LONGTITUDE);
        double latitude = mm_0.getDouble(Fence.LATITUDE);
        int radius = mm_0.getIntValue(Fence.RADIUS);
        String address = mm_0.getString(Fence.ADDRESS);
        int warningType = mm_0.getIntValue(Fence.WARNING_TYPE);
        String warningPhone = mm_0.getString(Fence.WARNING_PHONE);
        Time startTime1 = (Time) mm_0.get(Fence.START_TIME_1);
        Time endTime1 = (Time) mm_0.get(Fence.END_TIME_1);
        Time startTime2 = (Time) mm_0.get(Fence.START_TIME_2);
        Time endTime2 = (Time) mm_0.get(Fence.END_TIME_2);
        Time startTime3 = (Time) mm_0.get(Fence.START_TIME_3);
        Time endTime3 = (Time) mm_0.get(Fence.END_TIME_3);
        // String enable = (String) mm_0.get(AppFence.ENABLED);

        TUserElectronicFence fence = new TUserElectronicFence();
        fence.setNumber(number);
        fence.setLongitude(longitude);
        fence.setLatitude(latitude);
        fence.setRadius(radius);
        fence.setAddress(address);
        fence.setWarningType(warningType);
        fence.setWarningPhone(warningPhone);
        fence.setStartTime1(startTime1);
        fence.setStartTime2(startTime2);
        fence.setStartTime3(startTime3);
        fence.setEndTime1(endTime1);
        fence.setEndTime2(endTime2);
        fence.setEndTime3(endTime3);

        boolean bool = terminalService.modifyElectronicFence(userId, terminalType, fence);
        if (!bool) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject delElectronicFence(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);
        int number = mm_0.getIntValue(Fence.NUMBER);

        boolean bool = terminalService.delElectronicFence(userId, terminalType, number);
        if (bool) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject getMonitorTrack(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        int userId = data.getIntValue(User.ID);
        String terminalType = mm.getJSONObject(0).getString(Terminal.TYPE);

        List<TUserMonitorTrack> tUserMonitorTracks = terminalService.getMonitorTrack(userId, terminalType);
        if (tUserMonitorTracks.size() == 0) {
            return success(NormalMessage.NO_DATA);
        }
        List<Map<String, Object>> tracksMap = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < tUserMonitorTracks.size(); i++) {

            TUserMonitorTrack monitorTrack_i = tUserMonitorTracks.get(i);

            Map<String, Object> trackMap = new HashMap<String, Object>();
            trackMap.put(MonitorTracks.ID, monitorTrack_i.getId());
            trackMap.put(MonitorTracks.NUMBER, monitorTrack_i.getNumber());
            trackMap.put(MonitorTracks.NAME, monitorTrack_i.getName());
            trackMap.put(MonitorTracks.START_TIME, monitorTrack_i.getStartTime());
            trackMap.put(MonitorTracks.END_TIME, monitorTrack_i.getEndTime());
            trackMap.put(MonitorTracks.ENABLED, monitorTrack_i.getEnabled());
            tracksMap.add(trackMap);
        }

        return success(tracksMap);
    }

    @Override
    public JSONObject getMonitorTrackContent(String json) throws Exception {
        // TODO
        return null;
    }

    @Override
    public JSONObject addMonitorTrack(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);
        int number = mm_0.getIntValue(MonitorTracks.NUMBER);
        String name = mm_0.getString(MonitorTracks.NAME);
        Time startTime = (Time) mm_0.get(MonitorTracks.START_TIME);
        Time endTime = (Time) mm_0.get(MonitorTracks.END_TIME);
        String enabled = (String) mm_0.get(MonitorTracks.ENABLED);
        boolean isEnabled = false;

        if (enabled.equals("")) {
            isEnabled = true;
        }
        boolean bool = terminalService.addMonitorTrack(userId, terminalType, number, name, startTime, endTime,
                isEnabled);
        if (!bool) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject modifyMonitorTrack(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);
        int number = mm_0.getIntValue(MonitorTracks.NUMBER);
        String name = mm_0.getString(MonitorTracks.NAME);
        Time startTime = (Time) mm_0.get(MonitorTracks.START_TIME);
        Time endTime = (Time) mm_0.get(MonitorTracks.END_TIME);
        String enabled = (String) mm_0.get(MonitorTracks.ENABLED);
        boolean isEnabled = false;

        if (enabled.equals("")) {
            isEnabled = true;
        }
        boolean bool = terminalService.modifyMonitorTrack(userId, terminalType, number, name, startTime, endTime,
                isEnabled);
        if (!bool) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject delMonitorTrack(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String terminalType = mm_0.getString(Terminal.TYPE);
        int number = mm_0.getIntValue(Fence.NUMBER);

        boolean bool = terminalService.deleteMonitorTrack(userId, terminalType, number);
        if (bool) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject checkUserPassword(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String userName = mm_0.getString(User.USERNAME); // 用户登录名
        String password = mm_0.getString(User.PASSWORD);
        TUser user = commonDao.findUniqueByProperty(TUser.class, "userName", userName);
        if (user == null) {
            return success(NormalMessage.NO_SUCH_ACCOUNT);
        }

        String password_md5 = MD5Utils.encryptPassword(password);
        if (!StringUtils.equals(password_md5, user.getPassword())) {
            return success(NormalMessage.PASSWORD_INCORRECT);
        }
        return success();
    }

    @Override
    public JSONObject getHealthDescription(String json) {

        List<TDataHealthDescribe> datas = commonDao.loadAll(TDataHealthDescribe.class);
        List<Map<String, String>> showDatas = new ArrayList<>();
        for (TDataHealthDescribe data : datas) {
            Map<String, String> showData = new HashMap<>();

            for (HealthType healthType : HealthType.values()) {
                if (StringUtils.equals(healthType.getName(), data.getParam())) {
                    showData.put(HealthDescription.PARAM, healthType.name());
                }
            }

            showData.put(HealthDescription.SEX, data.getSex() + "");
            showData.put(HealthDescription.LESS, data.getLess());
            showData.put(HealthDescription.MIN, data.getMin());
            showData.put(HealthDescription.NORMAL, data.getNormal());
            showData.put(HealthDescription.MAX, data.getMax());
            showData.put(HealthDescription.MORE, data.getMore());

            showDatas.add(showData);
        }

        return success(showDatas);
    }

    @Override
    public JSONObject addSMSReceiveNumber(String json) {
        AppJSON appJSON = parseAppJSON(json);
        Integer userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int contactId = mm_0.getIntValue(Contact.ID);
        int healthWarning = mm_0.getIntValue(User.HEALTH_WARNING);

        contactsService.modifyContactReceiveSMS(contactId, userId);
        memberService.updateUserHealthWarning(userId, healthWarning);
        return success();
    }
}
