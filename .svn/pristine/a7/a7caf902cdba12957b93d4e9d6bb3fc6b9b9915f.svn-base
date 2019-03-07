package com.lifeshs.controller.terminal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.member.TUserContacts;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.healthDevice.HealthPackageDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.contacts.IContactsService;
import com.lifeshs.service.device.IDeviceService;
import com.lifeshs.service.device.impl.Band;
import com.lifeshs.service.device.impl.BloodPressure;
import com.lifeshs.service.device.impl.Bodyfatscale;
import com.lifeshs.service.device.impl.Glucometer;
import com.lifeshs.service.device.impl.HeartRate;
import com.lifeshs.service.device.impl.Lunginstrument;
import com.lifeshs.service.device.impl.Oxygen;
import com.lifeshs.service.device.impl.Temperature;
import com.lifeshs.service.terminal.ITerminalService;
import com.lifeshs.support.plantform.security.sessionmgr.ISessionManageService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.ParserParaUtil;

@Controller
@RequestMapping("healthDataControl")
public class HealthDataController extends BaseController{

    @Autowired
    private ITerminalService tService;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private ISessionManageService loginService;

    @Autowired
    private IContactsService contactsService;

    @Resource(name = "bloodPressure")
    private BloodPressure bloodPressure;
    @Resource(name = "lunginstrument")
    private Lunginstrument lunginstrument;
    @Resource(name = "glucometer")
    private Glucometer glucometer;
    @Resource(name = "oxygen")
    private Oxygen oxygen;
    @Resource(name = "heartRate")
    private HeartRate heartRate;
    @Resource(name = "bodyfatscale")
    private Bodyfatscale bodyfatscale;
    @Resource(name = "band")
    private Band band;
    @Resource(name = "temperature")
    private Temperature temperature;

    /**
     * @author wenxian.cai
     * @DateTime 2016年7月18日上午10:51:09
     * @serverComment 进入健康数据页面
     * @param
     */
    @RequestMapping(params = "hLEnter")
    public String hLEnter() {
        // logger.info("用户进入健康包");
        return "com/lifeshs/member/HealthPackage/health_package"; // main/testAjaxDataSend
    }

    /**
     * 进入短信预警页面
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月14日 下午2:19:21
     *
     * @return
     */
    @RequestMapping(params = "smsWarning", method = RequestMethod.GET)
    public String smsWarning() {
        return "com/lifeshs/member/HealthPackage/SmsWarning";
    }

    /**
     * 获取各个设备最近一个星期的数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年9月2日下午3:02:32
     * @serverComment
     * @param
     */
    @RequestMapping(params = "getHealthDataByWeek", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getHealthDataByWeek(HttpServletRequest request, HttpServletResponse response) {

        LoginUser user = getLoginUser();

        Map<String, Object> param = ParserParaUtil.getParams(request);
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String type = (String) param.get("type"); // 设备类型
        String xlType = (String) param.get("xlType"); // 心率手环里的参数类型（计步、睡眠、心率）
        String deviceType = (String) param.get("deviceType"); // 终端类型：APP,HL-031,C3,browser（空为全部--》模糊查询）
        String dateType = "WEEK"; // 查询日期类型：WEEK,MONTH,THREEMONTH
        int userId = user.getId();
        List<Map<String, Object>> listWeek = new ArrayList<>();
        switch (type) {
        case "xyj":
            listWeek = bloodPressure.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "fhy":
            listWeek = lunginstrument.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "xty":
            listWeek = glucometer.getMeasureDataWithDate(userId, deviceType, dateType);
            listWeek = getGlucoseSpecialData(listWeek); // 将血糖数据进行重新组装
            break;
        case "xyy":
            listWeek = oxygen.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "xl":
            switch (xlType) {
            case "step":
                listWeek = band.getMeasureDataWithDate(userId, deviceType, dateType);
                break;
            case "heartRate":
                listWeek = heartRate.getMeasureDataWithDate(userId, deviceType, dateType);
                break;
            case "sleep":
                listWeek = band.getMeasureDataWithDate(userId, deviceType, dateType);
                break;
            default:
                break;
            }
            break;
        case "tzc":
            listWeek = bodyfatscale.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "twj":
            listWeek = temperature.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        default:
            break;
        }

        if (listWeek.size() > 0) {
            resObject.setSuccess(true);
        }

        Collections.reverse(listWeek);

        resObject.setObj(listWeek);

        return resObject;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年7月14日下午3:00:07
     * @serverComment 获取最近一个月的健康数据
     * @param
     */
    @RequestMapping(params = "getHealthDataByMonth", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getHealthDataByMonth(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        Map<String, Object> param = ParserParaUtil.getParams(request);
        String type = (String) param.get("type");
        String xlType = (String) param.get("xlType");
        String deviceType = (String) param.get("deviceType");
        String dateType = "MONTH";
        int userId = getLoginUser().getId();
        List<Map<String, Object>> listMonth = new ArrayList<>();
        switch (type) {
        case "xyj":
            listMonth = bloodPressure.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "fhy":
            listMonth = lunginstrument.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "xty":
            listMonth = glucometer.getMeasureDataWithDate(userId, deviceType, dateType);
            listMonth = getGlucoseSpecialData(listMonth); // 将血糖数据进行重新组装
            break;
        case "xyy":
            listMonth = oxygen.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "xl":
            switch (xlType) {
            case "step":
                listMonth = band.getMeasureDataWithDate(userId, deviceType, dateType);
                break;
            case "heartRate":
                listMonth = heartRate.getMeasureDataWithDate(userId, deviceType, dateType);
                break;
            case "sleep":
                listMonth = band.getMeasureDataWithDate(userId, deviceType, dateType);
                break;
            default:
                break;
            }
            break;
        case "tzc":
            listMonth = bodyfatscale.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "twj":
            listMonth = temperature.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        default:
            break;
        }

        if (listMonth.size() > 0) {
            resObject.setSuccess(true);
        }

        Collections.reverse(listMonth);
        resObject.setObj(listMonth);
        return resObject;
    }

    @RequestMapping(params = "getHealthDataByThreeMonth", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getHealthDataByThreeMonth(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        Map<String, Object> param = ParserParaUtil.getParams(request);
        String type = (String) param.get("type");
        String xlType = (String) param.get("xlType");
        String deviceType = (String) param.get("deviceType");
        String dateType = "THREEMONTH";
        int userId = getLoginUser().getId();
        List<Map<String, Object>> listMonth = new ArrayList<>();
        switch (type) {
        case "xyj":
            listMonth = bloodPressure.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "fhy":
            listMonth = lunginstrument.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "xty":
            listMonth = glucometer.getMeasureDataWithDate(userId, deviceType, dateType);
            listMonth = getGlucoseSpecialData(listMonth); // 将血糖数据进行重新组装
            break;
        case "xyy":
            listMonth = oxygen.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "xl":
            switch (xlType) {
            case "step":
                listMonth = band.getMeasureDataWithDate(userId, deviceType, dateType);
                break;
            case "heartRate":
                listMonth = heartRate.getMeasureDataWithDate(userId, deviceType, dateType);
                break;
            case "sleep":
                listMonth = band.getMeasureDataWithDate(userId, deviceType, dateType);
                break;
            default:
                break;
            }
            break;
        case "tzc":
            listMonth = bodyfatscale.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        case "twj":
            listMonth = temperature.getMeasureDataWithDate(userId, deviceType, dateType);
            break;
        default:
            break;
        }

        if (listMonth.size() > 0) {
            resObject.setSuccess(true);
        }

        Collections.reverse(listMonth);
        resObject.setObj(listMonth);
        response.setContentType("text/json;charset=utf-8");
        return resObject;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年7月25日下午2:23:32
     * @serverComment 通过分页获取数据（注意：list结果的最后一个数据是总记录数）
     * @param
     */
    @RequestMapping(params = "getHealthDataByPage", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getHealthDataByPage(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        Map<String, Object> param = ParserParaUtil.getParams(request);
        int userId = getLoginUser().getId();

        String type = (String) param.get("type");
        String xlType = (String) param.get("xlType");
        String deviceType = (String) param.get("deviceType");
        int pageSize = Integer.parseInt((String) param.get("pageSize"));
        int page = Integer.parseInt((String) param.get("page"));

        PaginationDTO paDto = new PaginationDTO(); // 封装分页数据
        if (type.equals("xyj")) {
            paDto = bloodPressure.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
            if (paDto.getData() != null && paDto.getData().size() > 0) {
                resObject.setObj(paDto);
                resObject.setSuccess(true);
                resObject.setMsg("获取血压计数据成功");
            }
        }
        if (type.equals("fhy")) {
            paDto = lunginstrument.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
            if (paDto.getData() != null && paDto.getData().size() > 0) {
                resObject.setObj(paDto);
                resObject.setSuccess(true);
                resObject.setMsg("获取肺活仪数据成功");
            }
        }
        if (type.equals("xty")) {
            paDto = glucometer.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
            if (paDto.getData() != null && paDto.getData().size() > 0) {
                resObject.setObj(paDto);
                resObject.setSuccess(true);
                resObject.setMsg("获取血糖仪数据成功");
            }
        }
        if (type.equals("xyy")) {
            paDto = oxygen.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
            if (paDto.getData() != null && paDto.getData().size() > 0) {
                resObject.setObj(paDto);
                resObject.setSuccess(true);
                resObject.setMsg("获取血氧仪数据成功");
            }
        }
        if (type.equals("xl")) {

            switch (xlType) {
            case "step":
                paDto = band.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
                if (paDto.getData() != null && paDto.getData().size() > 0) {
                    resObject.setObj(paDto);
                    resObject.setSuccess(true);
                    resObject.setMsg("获取计步数据成功");
                }
                break;
            case "heartRate":
                paDto = heartRate.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
                if (paDto.getData() != null && paDto.getData().size() > 0) {
                    resObject.setObj(paDto);
                    resObject.setSuccess(true);
                    resObject.setMsg("获取心率数据成功");
                }
                break;
            case "sleep":
                paDto = band.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
                if (paDto.getData() != null && paDto.getData().size() > 0) {
                    resObject.setObj(paDto);
                    resObject.setSuccess(true);
                    resObject.setMsg("获取计步数据成功");
                }
                break;

            default:
                break;
            }
        }
        if (type.equals("tzc")) {
            paDto = bodyfatscale.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
            if (paDto.getData() != null && paDto.getData().size() > 0) {
                resObject.setObj(paDto);
                resObject.setSuccess(true);
                resObject.setMsg("获取体脂秤数据成功");
            }
        }
        if (type.equals("twj")) {
            paDto = temperature.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
            if (paDto.getData() != null && paDto.getData().size() > 0) {
                resObject.setObj(paDto);
                resObject.setSuccess(true);
                resObject.setMsg("获取体温计数据成功");
            }
        }
        if (type.equals("stepCounter")) {
            paDto = band.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
            if (paDto.getData() != null && paDto.getData().size() > 0) {
                // logger.info("获取计步分页数据操作成功");
                resObject.setObj(paDto);
                resObject.setSuccess(true);
                resObject.setMsg("获取计步数据成功");
            }
        }
        if (type.equals("sleep")) {
            paDto = band.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
            if (paDto.getData() != null && paDto.getData().size() > 0) {
                // logger.info("获取睡眠分页数据操作成功");
                resObject.setObj(paDto);
                resObject.setSuccess(true);
                resObject.setMsg("获取睡眠数据成功");
            }
        }
        if (type.equals("heartRate")) {
            paDto = heartRate.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
            if (paDto.getData() != null && paDto.getData().size() > 0) {
                // logger.info("获取心率分页数据操作成功");
                resObject.setObj(paDto);
                resObject.setSuccess(true);
                resObject.setMsg("获取心率数据成功");
            }
        }
        if (type.equals("bloodPressure")) {
            paDto = bloodPressure.getMeasureDataWithSplit(userId, deviceType, page, pageSize);
            if (paDto.getData() != null && paDto.getData().size() > 0) {
                // logger.info("获取血压计分页数据操作成功");
                resObject.setObj(paDto);
                resObject.setSuccess(true);
                resObject.setMsg("获取血压计数据成功");
            }
        }
        response.setContentType("text/json;charset=utf-8");
        return resObject;
    }

    /**
     * 判断用户是否勾选健康设备
     * 
     * @author wenxian.cai
     * @DateTime 2016年10月8日下午2:38:55
     * @serverComment
     * @param
     */
    @RequestMapping(params = "isHealthDataBinded", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson isHealthDataBinded(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = ParserParaUtil.getParams(request);
        String str = (String) param.get("healthType");
        String[] Arr = str.split(",");
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        int userId = getLoginUser().getId();

        Integer userHealthProduct = getCacheMemberSharingData(userId).getHealthProduct();

        Map<String, Object> healthMap = new HashMap<>();
        for (int i = 0; i < Arr.length; i++) {
            if (!tService.isHealthDataBinded(Arr[i], userHealthProduct)) {
                healthMap.put(Arr[i], "false");
                // resObject.setMsg("用户尚未绑定该健康包");
            } else {
                healthMap.put(Arr[i], "true");
            }
            resObject.setAttributes(healthMap);
        }
        resObject.setSuccess(true);
        // resObject.setMsg("已勾选健康包");
        return resObject;
    }

    private List<Map<String, Object>> getGlucoseSpecialData(List<Map<String, Object>> list) {
        List<Map<String, Object>> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            List<HashMap<String, Object>> list2 = new ArrayList<>();
            hashMap.put("measureDate", list.get(i).get("measureDate"));
            String param = DateTimeUtilT.date((Timestamp) list.get(i).get("measureDate"));
            int listSize = list.size();
            for (int j = i; j < listSize; j++) {
                if (param.equals(DateTimeUtilT.date((Timestamp) list.get(j).get("measureDate")))) {
                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    HashMap<String, Object> hashMap3 = new HashMap<>();
                    hashMap3.put("status", list.get(j).get("status") + "");
                    hashMap3.put("bloodSugar", list.get(j).get("bloodSugar") + "");
                    hashMap3.put("bloodSugarArea", list.get(j).get("bloodSugarArea") + "");
                    hashMap2.put(list.get(j).get("measureType") + "", hashMap3);
                    list2.add(hashMap2);
                    list.remove(j);
                    listSize = listSize - 1;
                    j--;
                }
            }
            hashMap.put("paramData", list2);
            newList.add(hashMap);
            i--;
        }
        return newList;
    }

    @RequestMapping(params = "getUserHealthArea", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getUserHealthArea() {
        AjaxJson resObject = new AjaxJson();

        LoginUser user = getLoginUser();
        int userId = user.getId();

        List<HealthType> healthTypes = new ArrayList<>();
        healthTypes.add(HealthType.heartRate);

        healthTypes.add(HealthType.weight);
        healthTypes.add(HealthType.axungeRatio);
        healthTypes.add(HealthType.WHR);
        healthTypes.add(HealthType.BMI);
        healthTypes.add(HealthType.fatFreeWeight);
        healthTypes.add(HealthType.muscle);
        healthTypes.add(HealthType.moisture);
        healthTypes.add(HealthType.boneWeight);
        healthTypes.add(HealthType.bodyage);
        healthTypes.add(HealthType.baseMetabolism);
        healthTypes.add(HealthType.proteide);
        healthTypes.add(HealthType.visceralFat);
        healthTypes.add(HealthType.diastolic);
        healthTypes.add(HealthType.systolic);

        healthTypes.add(HealthType.vitalCapacity);

        healthTypes.add(HealthType.bloodSugar);

        healthTypes.add(HealthType.saturation);

        healthTypes.add(HealthType.temperature);

        Map<String, Object> healthStandardValues = deviceService.getHealthStandardValueByHealthType(userId,
                healthTypes);
        Map<String, Object> healthStandardValues_new = new HashMap<>();
        for (String key : healthStandardValues.keySet()) {
            Object value = healthStandardValues.get(key);

            if (key.equals("vitalCapacityArea")) {
                continue;
            }

            if (key.equals("weight") && value.equals("0.0-0.0")) {
                continue;
            }

            healthStandardValues_new.put(key + "Area", value);
        }

        resObject.setAttributes(healthStandardValues_new);
        resObject.setSuccess(true);

        return resObject;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月14日下午1:46:24
     * @serverComment 获取联系人
     * @param
     */
    @RequestMapping(params = "getWarningReceive", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson getWarningReceive(@RequestBody Map<String, Object> data) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        int userId = getLoginUser().getId();
        List<TUserContacts> contacts = contactsService.findAllContacts(userId);
        if (contacts.size() == 0) {
            resObject.setMsg("暂无联系人");
            return resObject;
        }
        resObject.setObj(contacts);
        resObject.setMsg("获取联系人列表成功");
        return resObject;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月14日下午1:46:51
     * @serverComment 获取短信预警设备
     * @param
     */
    @RequestMapping(params = "getUserHealthWarningList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson getUserHealthWarningList(@RequestBody Map<String, Object> data) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        int userId = getLoginUser().getId();
        List<HealthPackageDTO> models = deviceService.getUserHealthWarningList(userId);
        resObject.setObj(models);
        resObject.setSuccess(true);
        resObject.setMsg("获取短信预警设备成功");
        return resObject;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月13日下午5:32:21
     * @serverComment 设置接受短信的联系人
     * @param
     */
    @RequestMapping(params = "modifyContactreceiveSms", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson modifyContactreceiveSms(@RequestBody Map<String, Object> data) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "设置预警联系人失败";
        resObject.setMsg(msg);
        int id = Integer.parseInt((String) data.get("id")); // 主键ID
        int userId = getLoginUser().getId();

        contactsService.modifyContactReceiveSMS(id, userId);
        resObject.setSuccess(true);
        return resObject;
    }

    @RequestMapping(params = "deleteContactreceiveSms", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson deleteContactreceiveSms(@RequestBody Map<String, Object> data) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "删除预警联系人失败";
        resObject.setMsg(msg);
        int id = Integer.parseInt((String) data.get("id")); // 主键ID
        int userId = getLoginUser().getId();
        ServiceMessage serviceMessage = contactsService.deleteContactReceiveSMS(id, userId);
        resObject.setSuccess(serviceMessage.isSuccess());
        return resObject;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月14日上午9:53:23
     * @serverComment 修改短信预警项目
     * @param
     */
    @RequestMapping(params = "modifyWarningDevice", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson modifyWarningDevice(@RequestBody Map<String, Object> data) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "设置预警项目失败";
        resObject.setMsg(msg);
        String healthTypeName = (String) data.get("healthTypeName"); // 预警项目名称
        boolean cancel = (boolean) data.get("cancel"); // true: 添加项目,false: 删除项目
        int userId = getLoginUser().getId();
        Map<String, Object> map = new HashMap<>();
        ServiceMessage serviceMessage = new ServiceMessage();
        if (cancel) {
            map.put("userId", userId);

            if (healthTypeName.equals("tolic")) {
                healthTypeName = "systolic";
                map.put("healthTypeName", healthTypeName);
                serviceMessage = deviceService.addWarningDevice(map);
                healthTypeName = "diastolic";
                map.put("healthTypeName", healthTypeName);
                serviceMessage = deviceService.addWarningDevice(map);
            } else {
                map.put("healthTypeName", healthTypeName);
                serviceMessage = deviceService.addWarningDevice(map);
            }

            if (serviceMessage.isSuccess()) {
                resObject.setSuccess(true);
                resObject.setMsg("添加项目成功");
            }
        } else {
            map.put("userId", userId);
            if (healthTypeName.equals("tolic")) {
                healthTypeName = "systolic";
                map.put("healthTypeName", healthTypeName);
                serviceMessage = deviceService.deleteWarningDevice(map);
                healthTypeName = "diastolic";
                map.put("healthTypeName", healthTypeName);
                serviceMessage = deviceService.deleteWarningDevice(map);
            } else {
                map.put("healthTypeName", healthTypeName);
                serviceMessage = deviceService.deleteWarningDevice(map);
            }

            if (serviceMessage.isSuccess()) {
                resObject.setSuccess(true);
                resObject.setMsg("解除项目成功");
            }
        }
        loginService.updateLoginUser(userId, 0); // 更新缓存中的loginUser对象
        return resObject;
    }
}
