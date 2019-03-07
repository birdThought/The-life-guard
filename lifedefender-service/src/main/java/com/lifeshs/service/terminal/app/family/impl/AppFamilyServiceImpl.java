package com.lifeshs.service.terminal.app.family.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Area;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.Record;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.UserMeasurement;
import com.lifeshs.common.constants.app.UserMeasurementReminderDetail;
import com.lifeshs.common.constants.common.BaseDefine;
import com.lifeshs.common.constants.common.RecordStatus;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserMeasurementReminderDTO;
import com.lifeshs.pojo.member.UserMeasurementReminderDetailDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.family.GroupMemberVO;
import com.lifeshs.service.terminal.app.family.IAppFamilyService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.utils.DateTimeUtil;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.Toolkits;

/**
 * 应用app家庭组接口实现类
 *
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月7日 上午9:44:07
 */
@Service(value = "appFamilyService")
public class AppFamilyServiceImpl extends AppNormalService implements IAppFamilyService {

    private static final Logger logger = Logger.getLogger("app");

    @Override
    public JSONObject getFamilyMemberList(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        List<GroupMemberVO> groupMembers = new ArrayList<>();// 存放总的成员全部信息
        List<Map<String, Object>> member = new ArrayList<>(); // 存放总的成员信息

        // 存放单个成员号码
        groupMembers = terminal.getFamilyService().findGroupMember(userId);
        for (int i = 0; i < groupMembers.size(); i++) {
            GroupMemberVO userMember = groupMembers.get(i);
            if (userMember.getIsCurrentUser()) {
                continue;
            }

            String birthday = "";
            String photo = userMember.getPhoto();
            String realName = userMember.getRealName();
            String mobile = userMember.getMobile();
            Float height = userMember.getHeight();
            Float weight = userMember.getWeight();
            Float hip = userMember.getHip();
            Float waist = userMember.getWaist();
            Float bust = userMember.getBust();
            boolean sex = BaseDefine.SEX;

            if (userMember.getBirthday() != null) {
                birthday = DateTimeUtilT.date(userMember.getBirthday());
            }
            if (userMember.getSex() != null) {
                sex = userMember.getSex().booleanValue();
            }

            Map<String, Object> map = new HashMap<>();

            map.put(User.ID, userMember.getId());
            map.put(User.REALNAME, realName);
            map.put(User.MOBILE, mobile);
            map.put(User.PHOTO, photo);
            map.put(User.AGE, userMember.getAge());
            map.put(User.BIRTHDAY, birthday);
            map.put(Record.HEIGHT, height);
            map.put(Record.WEIGHT, weight);
            map.put(Record.HIP, hip);
            map.put(Record.WAIST, waist);
            map.put(Record.BUST, bust);
            map.put(User.SEX, sex);

            member.add(map);
        }

        return success(member);
    }

    @Override
    public JSONObject addNewFamilyMember(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String userName = mm_0.getString(User.USERNAME);
        String password = mm_0.getString(User.PASSWORD);

        boolean isUserNameVerify = Toolkits.isVerifyUserName(userName);
        if (!isUserNameVerify) {
            return error(Error.USERNAME_UNVERIFY);
        }
        boolean isPassowrdVerify = Toolkits.isVerifyPassword(password);
        if (!isPassowrdVerify) {
            return error(Error.PASSWORD_UNVERIFY);
        }

        int registMemberId = memberService.registMember(userName, password);
        if (registMemberId == -1) {
            return success(NormalMessage.USER_EXIST);
        }

        String realName = (String) mm_0.get(User.REALNAME);
        String sex = (String) mm_0.get(User.SEX);
        String photo = (String) mm_0.get(User.PHOTO);
        String birthday = (String) mm_0.get(User.BIRTHDAY);
        String height = (String) mm_0.get(Record.HEIGHT);
        String weight = (String) mm_0.get(Record.WEIGHT);
        String hip = (String) mm_0.get(Record.HIP);
        String waist = (String) mm_0.get(Record.WAIST);
        String bust = (String) mm_0.get(Record.BUST);

        Double height_d = height != null ? Double.valueOf(height) : null;
        Double weight_d = weight != null ? Double.valueOf(weight) : null;
        Double hip_d = hip != null ? Double.valueOf(hip) : null;
        Double waist_d = waist != null ? Double.valueOf(waist) : null;
        Double bust_d = bust != null ? Double.valueOf(bust) : null;

        Integer newMemberId = updateFamilyBaseInformation(null, userName, realName, birthday, sex, photo, height_d,
                weight_d, hip_d, waist_d, bust_d, userId);

        Map<String, String> returnData = new HashMap<>();
        returnData.put(User.ID, newMemberId + "");

        return success(returnData);
    }

    @Override
    public JSONObject modifyFamilyMember(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        int userId = appJSON.getData().getUserId();// 用户id
        UserDTO user = appJSON.getAopData().getUser();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int userId_f = mm_0.getIntValue(User.ID); // 要修改的家庭成员的id
        String realName = mm_0.getString(User.REALNAME);
        String photo = mm_0.getString(User.PHOTO);
        String sex = mm_0.getString(User.SEX);
        String birthday = mm_0.getString(User.BIRTHDAY);

        Double height = mm_0.getDouble(Record.HEIGHT);
        Double weight = mm_0.getDouble(Record.WEIGHT);
        Double hip = mm_0.getDouble(Record.HIP); // 臀围
        Double waist = mm_0.getDouble(Record.WAIST); // 腰围
        Double bust = mm_0.getDouble(Record.BUST); // 胸围

        UserDTO user_f = memberService.getUser(userId_f);
        String groupKey = user.getGroupKey();
        String groupKey_f = user_f.getGroupKey();

        if (user.getGroupKey() == null || user_f == null || !groupKey.equals(groupKey_f)) {
            return success(NormalMessage.NO_SUCH_ACCOUNT);
        }

        updateFamilyBaseInformation(userId, null, realName, birthday, sex, photo, height, weight, hip, waist, bust,
                null);

        return success();
    }

    @Override
    public JSONObject queryUserList(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();// 用户id
        String userName = appJSON.getData().getFirstJSONObject().getString(User.USERNAME); // 用户登录名

        Map<String, Object> userVO = terminal.getFamilyService().findUserListByUserName(userName, userId);

        List<Map<String, Object>> list_return = new ArrayList<>();

        if (userVO.isEmpty()) {
            return success(NormalMessage.NO_DATA);
        }

        Map<String, Object> map = new HashMap<>();
        map.put(User.ID, userVO.get("id"));
        map.put(User.REALNAME, userVO.get("realName"));
        map.put(User.USERNAME, userVO.get("userName"));
        map.put(User.MOBILE, userVO.get("mobile"));
        map.put(User.PHOTO, userVO.get("photo"));
        list_return.add(map);

        return success(list_return, true);
    }

    @Override
    public JSONObject addFamilyMember(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String userName = mm_0.getString(User.USERNAME);
        String password = mm_0.getString(User.PASSWORD);

        ServiceMessage sm = terminal.getFamilyService().updateUserGroupKey(userName, password, userId);
        if (!sm.isSuccess()) {
            // 失败的时候返回错误信息
            return success(sm.getMessage());
        } else {
            return success();
        }
    }

    @Override
    public JSONObject delFamilyMember(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        int userId_f = appJSON.getData().getFirstJSONObject().getIntValue(User.ID);
        memberService.leaveGroup(userId_f);
        return success();
    }

    @Override
    public JSONObject switchFamilyAccount(String json, String ip) {
        AppJSON appJSON = parseAppJSON(json);
        String ver = appJSON.getVer();
        String type = appJSON.getType();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int switchUserId = mm_0.getIntValue(User.ID);
        String systemVersion = mm_0.getString(Normal.CURRENT_VERSION);
        String deviceToken = mm_0.getString(Normal.DEVICE_TOKEN);
        
        UserDTO switchUser = memberService.getUser(switchUserId);
        UserDTO user = appJSON.getAopData().getUser();

        if (switchUser == null || StringUtils.isBlank(switchUser.getGroupKey())
                || StringUtils.isBlank(user.getGroupKey())
                || !StringUtils.equals(switchUser.getGroupKey(), user.getGroupKey())) {
            return success(NormalMessage.NO_SUCH_ACCOUNT);
        }

        boolean sex = BaseDefine.SEX;
        Boolean gender = switchUser.getRecordDTO().getGender();
        if (gender != null) {
            sex = gender;
        }
        Map<String, String> address = new HashMap<>();
        address.put(Area.PROVINCE, switchUser.getProvince());
        address.put(Area.CITY, switchUser.getCity());
        address.put(Area.COUNTY, switchUser.getCounty());
        address.put(Area.STREET, switchUser.getStreet());

        Map<String, Object> returnData = loginAction(switchUser, type, ip, ver, systemVersion, deviceToken);
        
        returnData.put(User.USERNAME, switchUser.getUserName());
        returnData.put(User.MOBILE, switchUser.getMobile());
        returnData.put(User.PHOTO, switchUser.getPhoto());
        returnData.put(User.REALNAME, switchUser.getRealName());
        returnData.put(User.SEX, sex);
        returnData.put(Area.ADDRESS, address);
        returnData.put(Record.HEIGHT, switchUser.getRecordDTO().getHeight());
        returnData.put(Record.WEIGHT, switchUser.getRecordDTO().getWeight());
        returnData.put(Record.WAIST, switchUser.getRecordDTO().getWaist());
        returnData.put(Record.BUST, switchUser.getRecordDTO().getBust());
        returnData.put(Record.HIP, switchUser.getRecordDTO().getHip());
        returnData.put(User.BIRTHDAY, switchUser.getRecordDTO().getBirthday());

        return success(returnData);
    }

    @Override
    public JSONObject getUserMeasurementReminder(String json) {
        AppJSON appJSON = parseAppJSON(json);

        Map<String, Object> returnData = new HashMap<>();

        JSONObject requestData = appJSON.getData().getFirstJSONObject();
        int targetUserId = requestData.getIntValue(UserMeasurement.USERID);

        try {
            UserMeasurementReminderDTO userMeasurementReminderDTO = memberService.getUserMeasurementReminder(targetUserId);
            returnData.put(UserMeasurement.USERID, userMeasurementReminderDTO.getUserId());
            returnData.put(UserMeasurement.SMSSWITCH, userMeasurementReminderDTO.getSmsSwitch());
            returnData.put(UserMeasurement.PUSHSWITCH, userMeasurementReminderDTO.getPushSwitch());
        } catch (Exception ex) {
            logger.error(String.format("getUserMeasurementReminder failed,error:%s", ex.getMessage()));
            return error(Error.FETCHFAILED);
        }

        return success(returnData);
    }

    @Override
    public JSONObject listUserMeasurementReminderDetail(String json) {
        AppJSON appJSON = parseAppJSON(json);
        List<Map<String, Object>> returnData = new ArrayList<>();

        JSONObject requestData = appJSON.getData().getFirstJSONObject();
        int targetUserId = requestData.getIntValue(UserMeasurement.USERID);
        try {
            List<UserMeasurementReminderDetailDTO> userMeasurementReminderDetailDTOList = memberService.listUserMeasurementReminderDetail(targetUserId,RecordStatus.NORMAL.value());

            Iterator<UserMeasurementReminderDetailDTO> iterator = userMeasurementReminderDetailDTOList.iterator();
            while (iterator.hasNext()) {
                UserMeasurementReminderDetailDTO userMeasurementReminderDetailDTO = iterator.next();
                Map<String, Object> map = new HashMap<>();
                map.put(UserMeasurementReminderDetail.USERID, userMeasurementReminderDetailDTO.getUserId());
                map.put(UserMeasurementReminderDetail.REMINDERDETAILID, userMeasurementReminderDetailDTO.getReminderDetailId());
                map.put(UserMeasurementReminderDetail.REMINDERTIME, userMeasurementReminderDetailDTO.getReminderTime());
                map.put(UserMeasurementReminderDetail.REPEATCYCLE, userMeasurementReminderDetailDTO.getRepeatCycle());
                map.put(UserMeasurementReminderDetail.DEVICES, userMeasurementReminderDetailDTO.getDevices());
                map.put(UserMeasurementReminderDetail.REMINDERSTATUS, userMeasurementReminderDetailDTO.getReminderStatus());
                returnData.add(map);
            }
        } catch (Exception ex) {
            logger.error(String.format("listUserMeasurementReminderDetail failed,error:%s", ex.getMessage()));
            return error(Error.FETCHFAILED);
        }
//        userMeasurementReminderDetailDTOList.forEach(userMeasurementReminderDetailDTO -> {
//            Map<String, Object> map = new HashMap<>();
//            map.put(UserMeasurementReminderDetail.USERID,userMeasurementReminderDetailDTO.getUserId());
//            map.put(UserMeasurementReminderDetail.REMINDERDETAILID,userMeasurementReminderDetailDTO.getReminderDetailId());
//            map.put(UserMeasurementReminderDetail.REMINDERTIME,userMeasurementReminderDetailDTO.getReminderTime());
//            map.put(UserMeasurementReminderDetail.REPEATCYCLE,userMeasurementReminderDetailDTO.getRepeatCycle());
//            map.put(UserMeasurementReminderDetail.DEVICES,userMeasurementReminderDetailDTO.getDevices());
//            map.put(UserMeasurementReminderDetail.REMINDERSTATUS,userMeasurementReminderDetailDTO.getReminderStatus());
//            returnData.add(map);
//        });

        return success(returnData);
    }

    @Override
    public JSONObject setUserMeasurementReminderSmsSwitch(String json) {
        AppJSON appJSON = parseAppJSON(json);
        Map<String, Object> returnData = new HashMap<>();

        JSONObject requestData = appJSON.getData().getFirstJSONObject();
        int targetUserId = requestData.getIntValue(UserMeasurement.USERID);
        int smsSwitch = requestData.getIntValue(UserMeasurement.SMSSWITCH);
        try {
            boolean result = memberService.setUserMeasurementReminderSmsSwitch(targetUserId, smsSwitch);
            if (!result) {
                logger.error(String.format("setUserMeasurementReminderSmsSwitch failed,targetUserId:%s,smsSwitch:%s", targetUserId, smsSwitch));
                return error(Error.UPDATEFAILED);
            }
        } catch (Exception ex) {
            logger.error(String.format("listUserMeasurementReminderDetail failed,error:%s", ex.getMessage()));
            return error(Error.UPDATEFAILED);
        }
        return success(returnData);
    }

    @Override
    public JSONObject setUserMeasurementReminderPushSwitch(String json) {
        AppJSON appJSON = parseAppJSON(json);
        Map<String, Object> returnData = new HashMap<>();

        JSONObject requestData = appJSON.getData().getFirstJSONObject();
        int targetUserId = requestData.getIntValue(UserMeasurement.USERID);
        int pushSwitch = requestData.getIntValue(UserMeasurement.PUSHSWITCH);
        try {
            boolean result = memberService.setUserMeasurementReminderPushSwitch(targetUserId, pushSwitch);

            if (!result) {
                logger.error(String.format("setUserMeasurementReminderPushSwitch failed,targetUserId:%s,pushSwitch:%s", targetUserId, pushSwitch));
                return error(Error.UPDATEFAILED);
            }
        } catch (Exception ex) {
            logger.error(String.format("setUserMeasurementReminderPushSwitch failed,error:%s", ex.getMessage()));
            return error(Error.UPDATEFAILED);
        }
        return success(returnData);
    }

    @Override
    public JSONObject addUserMeasurementReminderDetail(String json) {
        AppJSON appJSON = parseAppJSON(json);
        Map<String, Object> returnData = new HashMap<>();

        JSONObject requestData = appJSON.getData().getFirstJSONObject();

        Integer userId = requestData.getInteger(UserMeasurementReminderDetail.USERID);
        String reminderTime = requestData.getString(UserMeasurementReminderDetail.REMINDERTIME);
        String repeatCycle = requestData.getString(UserMeasurementReminderDetail.REPEATCYCLE);
        String devices = requestData.getString(UserMeasurementReminderDetail.DEVICES);
        Integer reminderStatus = requestData.getInteger(UserMeasurementReminderDetail.REMINDERSTATUS);
        if (userId == null) {
            return error(String.format("%s:%s",Error.PARAMETER_MISSING,UserMeasurementReminderDetail.USERID));
        }
        if (reminderTime == null) {
            return error(String.format("%s:%s",Error.PARAMETER_MISSING,UserMeasurementReminderDetail.REMINDERTIME));
        }
        if (repeatCycle == null) {
            return error(String.format("%s:%s",Error.PARAMETER_MISSING,UserMeasurementReminderDetail.REPEATCYCLE));
        }
        if (devices == null) {
            return error(String.format("%s:%s",Error.PARAMETER_MISSING,UserMeasurementReminderDetail.DEVICES));
        }
        if (reminderStatus == null) {
            return error(String.format("%s:%s",Error.PARAMETER_MISSING,UserMeasurementReminderDetail.REMINDERSTATUS));
        }
        UserMeasurementReminderDetailDTO userMeasurementReminderDetailDTO = new UserMeasurementReminderDetailDTO();
        userMeasurementReminderDetailDTO.setUserId(userId);
        userMeasurementReminderDetailDTO.setReminderTime(reminderTime);
        userMeasurementReminderDetailDTO.setRepeatCycle(repeatCycle);
        userMeasurementReminderDetailDTO.setDevices(devices);
        userMeasurementReminderDetailDTO.setReminderStatus(reminderStatus);
        userMeasurementReminderDetailDTO.setCreateTime(DateTimeUtil.getDBDate());
        try {
            boolean result = memberService.addUserMeasurementReminderDetail(userMeasurementReminderDetailDTO);

            if (!result) {
                logger.error(String.format("addUserMeasurementReminderDetail failed,userMeasurementReminderDetailDTO:%s",
                        userMeasurementReminderDetailDTO.toString()));
                return error(Error.ADDFAILED);
            }
        } catch (Exception ex) {
            logger.error(String.format("addUserMeasurementReminderDetail failed,error:%s", ex.getMessage()));
            return error(Error.ADDFAILED);
        }
        Integer reminderDetailId = userMeasurementReminderDetailDTO.getReminderDetailId();
        returnData.put(UserMeasurementReminderDetail.REMINDERDETAILID, reminderDetailId);
        return success(returnData);
    }

    @Override
    public JSONObject setUserMeasurementReminderDetailStatus(String json) {
        AppJSON appJSON = parseAppJSON(json);
        Map<String, Object> returnData = new HashMap<>();

        JSONObject requestData = appJSON.getData().getFirstJSONObject();
        int reminderDetailId = requestData.getIntValue(UserMeasurementReminderDetail.REMINDERDETAILID);
        int reminderStatus = requestData.getIntValue(UserMeasurementReminderDetail.REMINDERSTATUS);
        try {
            boolean result = memberService.setUserMeasurementReminderDetailStatus(reminderDetailId, reminderStatus);

            if (!result) {
                logger.error(String.format("setUserMeasurementReminderDetailStatus failed,reminderDetailId:%s,reminderStatus:%s", reminderDetailId, reminderStatus));
                return error(Error.UPDATEFAILED);
            }
        } catch (Exception ex) {
            logger.error(String.format("setUserMeasurementReminderDetailStatus failed,error:%s", ex.getMessage()));
            return error(Error.UPDATEFAILED);
        }
        return success(returnData);
    }

    /**
     * 逻辑删除提醒项目
     *
     * @param json
     * @return
     */
    @Override
    public JSONObject delUserMeasurementReminderDetail(String json) {
        AppJSON appJSON = parseAppJSON(json);
        Map<String, Object> returnData = new HashMap<>();

        JSONObject requestData = appJSON.getData().getFirstJSONObject();
        Integer reminderDetailId = requestData.getInteger(UserMeasurementReminderDetail.REMINDERDETAILID);
        if(reminderDetailId ==null){
            return error(String.format("%s:%s",Error.PARAMETER_MISSING,UserMeasurementReminderDetail.REMINDERDETAILID));
        }
        try {
            boolean result = memberService.delUserMeasurementReminderDetail(reminderDetailId, RecordStatus.DELETED.value());

            if (!result) {
                logger.error(String.format("delUserMeasurementReminderDetail failed,reminderDetailId:%s", reminderDetailId));
                return error(Error.DELETEFAILED);
            }
        } catch (Exception ex) {
            logger.error(String.format("delUserMeasurementReminderDetail failed,error:%s", ex.getMessage()));
            return error(Error.DELETEFAILED);
        }
        return success(returnData);
    }

    /**
     * 更新提醒项目
     *
     * @param json
     * @return
     */
    @Override
    public JSONObject updateUserMeasurementReminderDetail(String json) {
        AppJSON appJSON = parseAppJSON(json);
        Map<String, Object> returnData = new HashMap<>();

        JSONObject requestData = appJSON.getData().getFirstJSONObject();
        Integer reminderDetailId = requestData.getInteger(UserMeasurementReminderDetail.REMINDERDETAILID);
        String reminderTime = requestData.getString(UserMeasurementReminderDetail.REMINDERTIME);
        String repeatCycle = requestData.getString(UserMeasurementReminderDetail.REPEATCYCLE);
        String devices = requestData.getString(UserMeasurementReminderDetail.DEVICES);
        Integer reminderStatus = requestData.getInteger(UserMeasurementReminderDetail.REMINDERSTATUS);
        if (reminderTime == null) {
            return error(String.format("%s:%s",Error.PARAMETER_MISSING,UserMeasurementReminderDetail.REMINDERTIME));
        }
        if (repeatCycle == null) {
            return error(String.format("%s:%s",Error.PARAMETER_MISSING,UserMeasurementReminderDetail.REPEATCYCLE));
        }
        if (devices == null) {
            return error(String.format("%s:%s",Error.PARAMETER_MISSING,UserMeasurementReminderDetail.DEVICES));
        }
        if (reminderStatus == null) {
            return error(String.format("%s:%s",Error.PARAMETER_MISSING,UserMeasurementReminderDetail.REMINDERSTATUS));
        }
        UserMeasurementReminderDetailDTO userMeasurementReminderDetailDTO = new UserMeasurementReminderDetailDTO();
        userMeasurementReminderDetailDTO.setReminderDetailId(reminderDetailId);
        userMeasurementReminderDetailDTO.setReminderTime(reminderTime);
        userMeasurementReminderDetailDTO.setRepeatCycle(repeatCycle);
        userMeasurementReminderDetailDTO.setDevices(devices);
        userMeasurementReminderDetailDTO.setReminderStatus(reminderStatus);
        userMeasurementReminderDetailDTO.setUpdateTime(DateTimeUtil.getDBDate());
        try {
            boolean result = memberService.updateUserMeasurementReminderDetail(userMeasurementReminderDetailDTO);

            if (!result) {
                logger.error(String.format("updateUserMeasurementReminderDetail failed,userMeasurementReminderDetailDTO:%s",
                        userMeasurementReminderDetailDTO.toString()));
                return error(Error.UPDATEFAILED);
            }
        } catch (Exception ex) {
            logger.error(String.format("updateUserMeasurementReminderDetail failed,error:%s", ex.getMessage()));
            return error(Error.UPDATEFAILED);
        }
        return success(returnData);
    }

    /**
     * 更新家庭成员基本信息
     *
     * @param userId
     * @param userName
     * @param realName
     * @param birthday
     * @param sex
     * @param photo
     * @param height
     * @param weight
     * @param hip
     * @param waist
     * @param bust
     * @param currentUserId
     * @return
     */
    private Integer updateFamilyBaseInformation(Integer userId, String userName, String realName, String birthday,
                                                String sex, String photo, Double height, Double weight, Double hip, Double waist, Double bust,
                                                Integer currentUserId) {
        UserDTO userDTO = new UserDTO();
        UserRecordDTO recordDTO = new UserRecordDTO();

        if (userId == null) {
            userId = memberService.getUser(userName).getId();
        }
        userDTO.setId(userId);
        recordDTO.setUserId(userId);

        if (StringUtils.isNotBlank(realName)) {
            userDTO.setRealName(realName);
        }
        if (StringUtils.isNotBlank(birthday)) {
            recordDTO.setBirthday(DateTimeUtilT.date(birthday));
        }
        if (StringUtils.isNotBlank(photo)) {
            ImageDTO imageVO = uploadPhoto(photo, null, "head", false);

            if (imageVO.getUploadSuccess()) {
                String netPath = imageVO.getNetPath();
                userDTO.setPhoto(netPath);
            }
        }
        if (sex != null) {
            boolean sex_b = Integer.valueOf(sex).intValue() == 0 ? false : true;
            recordDTO.setGender(sex_b);
        }
        if (height != null) {
            recordDTO.setHeight(height.floatValue());
        }
        if (weight != null) {
            recordDTO.setWeight(weight.floatValue());
        }
        if (hip != null) {
            recordDTO.setHip(hip.floatValue());
        }
        if (waist != null) {
            recordDTO.setWaist(waist.floatValue());
        }
        if (bust != null) {
            recordDTO.setBust(bust.floatValue());
        }
        userDTO.setRecordDTO(recordDTO);

        terminal.getFamilyService().updateMemberInfo(userDTO, currentUserId);
        return userId;
    }
}
