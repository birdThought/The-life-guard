package com.lifeshs.service.terminal.app.terminal.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.HealthIndex;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.Terminal;
import com.lifeshs.common.constants.app.healthPackage.HealthDevice;
import com.lifeshs.common.constants.app.healthPackage.HealthPackage;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.healthDevice.HealthPackageBaseDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.config.DeviceDTO;
import com.lifeshs.pojo.member.healthIndex.HealthIndexDTO;
import com.lifeshs.service.device.impl.product.Product;
import com.lifeshs.service.terminal.ITerminalService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.terminal.IAppTerminalService;

@Service(value = "appTerminalService")
public class AppTerminalServiceImpl extends AppNormalService implements IAppTerminalService {

    @Autowired
    private ITerminalService terminalService;

    @Override
    public JSONObject getUserDevices(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        int userId = appJSON.getData().getUserId();
        UserDTO user = appJSON.getAopData().getUser();

        int order = 0;
        JSONArray mm = appJSON.getData().getMsg();
        if (mm != null && (mm.get(0) != null)) {
            JSONObject mm_0 = mm.getJSONObject(0);
            order = mm_0.getIntValue(HealthDevice.ORDER);
        }

        Integer bvalue = user.getHealthProduct();

        // 获取用户的健康包
        Map<String, Object> healthPackage = new HashMap<String, Object>();
        healthPackage.put(HealthPackage.TITLE, "健康包");

        List<Map<String, Object>> healthPackageArray = new ArrayList<>();

        List<HealthPackageBaseDTO> dbms = new ArrayList<>();
        if (order == 0) {
            // 不排序
            dbms = Product.listHealthPackageBaseDTO();
        } else {
            // 按照用户设定顺序显示设备列表
            Character indexChar = user.getUserName().charAt(0);
            DeviceDTO deviceDTO = getUserDeviceConfig(userId, indexChar);
            List<Integer> deviceOrders = deviceDTO.getOrderList();
            dbms = Product.listHealthPackageBaseDTO(deviceOrders);
        }

        for (int i = 0; i < dbms.size(); i++) {
            HealthPackageBaseDTO dbm = dbms.get(i);

            Map<String, Object> hp = new HashMap<String, Object>();
            hp.put(HealthPackage.NAME, dbm.getName_cn());
            hp.put(HealthPackage.PHOTO, "lifekeepers_files/device/mobile/" + dbm.getName_en() + ".png");
            hp.put(HealthPackage.ABOUT, dbm.getAbout());
            hp.put(Normal.INDEX, i + 1);

            if (bvalue != null && ((dbm.getDeviceValue() | bvalue) == bvalue)) {
                hp.put(HealthPackage.STATUS, "1");
                // TODO APP功能尚未推出，目前先把未绑定的设备隐藏
                healthPackageArray.add(hp);
            } else {
                hp.put(HealthPackage.STATUS, "0");
            }
            // TODO APP功能尚未推出，目前先把未绑定的设备隐藏
            // healthPackageArray.add(hp);
        }

        healthPackage.put(HealthPackage.ARRAY, healthPackageArray);
        Map<String, Object> wearable = new HashMap<String, Object>();

        // 获取用户的智能穿戴设备
        List<TUserTerminal> devices = terminalService.geTUserTerminals(userId);
        wearable.put(HealthPackage.TITLE, "智能穿戴");
        List<Map<String, Object>> wearableArray = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < devices.size(); i++) {
            Map<String, Object> wearable1 = new HashMap<String, Object>();
            wearable1.put(HealthPackage.NAME, devices.get(i).getTerminalType());
            wearable1.put(HealthPackage.IMEI, devices.get(i).getImei());
            wearable1.put(HealthPackage.STATUS, devices.get(i).getStatus());
            wearable1.put(HealthPackage.PHOTO, "1");

            wearableArray.add(wearable1);
        }
        wearable.put(HealthPackage.ARRAY, wearableArray);

        // 获取用户的安防设备
        Map<String, Object> security = new HashMap<String, Object>();
        security.put(HealthPackage.TITLE, "安防");
        List<Map<String, Object>> securityArray = new ArrayList<Map<String, Object>>();
        security.put(HealthPackage.ARRAY, securityArray);

        // 把三种设备信息存放到map中
        Map<String, Object> deviceMap = new HashMap<String, Object>();
        deviceMap.put(HealthPackage.HEALTH_PACKAGE, healthPackage);
        deviceMap.put(HealthPackage.WEARABLE, wearable);
        deviceMap.put(HealthPackage.SECURITY, security);

        return success(deviceMap);
    }

    @Override
    public JSONObject setHealthDevice(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int selectValue = mm_0.getIntValue(HealthPackage.SELECT_VALUE);
        UserDTO user = appJSON.getAopData().getUser();

        user.setHealthProduct(selectValue);
        memberService.updateUserHealthProduct(user.getId(), selectValue);
//        boolean isSuccessUpdate = member.updateUser(user);
//        if (isSuccessUpdate) {
//            return success();
//        } else {
//            return error(Error.FAIL_ACTION);
//        }
        return success();
    }

    @Override
    public JSONObject bindTerminal(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String terminalType = mm_0.getString(Terminal.TYPE);
        String imei = mm_0.getString(Terminal.IMEI);
        String name = mm_0.getString(Terminal.NICK_NAME);
        String terminalMobile = mm_0.getString(Terminal.MOBILE);

        if (terminalService.isUserBindSameTerminal(userId, terminalType)) {
            return success(NormalMessage.TERMINAL_REPEAT);
        }
        ServiceMessage sm = terminalService.bindTerminal(userId, imei, name, terminalMobile, terminalType);
        if (!sm.isSuccess()) {
            return error("绑定失败");
        }
        return success();
    }

    @Override
    public JSONObject unBindTerminal(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String terminalType = mm_0.getString(Terminal.TYPE);
        String imei = mm_0.getString(Terminal.IMEI);

        boolean isBindSuccess = terminalService.unBindTerminal(userId, imei, terminalType);
        if (!isBindSuccess) {
            return error(NormalMessage.TERMINAL_NO_FOUND);
        }
        return success();
    }

    @Override
    public JSONObject getHealthPoint(String json) {
        AppJSON appJSON = parseAppJSON(json);
        UserRecordDTO recordDTO = appJSON.getAopData().getUser().getRecordDTO();
        
        HealthIndexDTO healthIndex = AppNormalService.getHealthIndex(recordDTO);
        int point = healthIndex.getPoint();
        
        int healthStatus = AppNormalService.getHealthStatus(point);
        String healthSuggest = AppNormalService.getHealthSuggest(healthStatus);
        
        Map<String, Object> map = new HashMap<>();
        map.put(HealthIndex.POINT, String.valueOf(point));
        map.put(HealthIndex.EVALUATION_DATE, healthIndex.getEvaluationDate());
        map.put(HealthIndex.STATUS, healthStatus);
        map.put(HealthIndex.SUGGEST, healthSuggest);
        map.put(HealthIndex.DETAIL, JSONObject.toJSON(healthIndex.getDetail()));
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(HealthIndex.HEALTH_INDEX, map);
        
        return success(returnData);
    }

}
