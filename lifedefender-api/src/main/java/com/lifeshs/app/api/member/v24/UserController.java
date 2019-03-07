package com.lifeshs.app.api.member.v24;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lifeshs.common.constants.common.ProjectType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.common.v24.AppBaseController;
import com.lifeshs.common.constants.app.AddressManage;
import com.lifeshs.common.constants.app.Area;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.app.HuanXin;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.app.OrgServeGroup;
import com.lifeshs.common.constants.app.Record;
import com.lifeshs.common.constants.app.Serve;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.hobby.UserHobby;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.po.OrderPO;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.address.AddressDTO;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.member.HobbyService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.Toolkits;

import jodd.util.StringUtil;

/**
 * 用户设置
 * 
 * @author yuhang.weng
 * @version 2.4
 * @DateTime 2017年6月14日 下午3:15:27
 */
@RestController(value = "v24UserController")
@RequestMapping(value = "app/user/v24")
public class UserController extends AppBaseController {

    @Resource(name = "memberService")
    private IMemberService memberService;

    @Resource(name = "userHobbyService")
    private HobbyService hobbyService;
    
    @Autowired
    private com.lifeshs.service1.member.IMemberService memberService1;

    @RequestMapping(value = "listAddress", method = RequestMethod.POST)
    public JSONObject listAddress(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        List<AddressDTO> datas = memberService.listAddress(userId);

        List<Map<String, Object>> addresses = new ArrayList<>();
        for (AddressDTO data : datas) {
            Map<String, Object> address = new HashMap<>();
            address.put(AddressManage.ID, data.getId());
            address.put(AddressManage.RECIVER_NAME, data.getReceiverName());
            address.put(AddressManage.CONTACT_NUMBER, data.getContactNumber());
            address.put(AddressManage.ADDRESS, data.getAddress());
            address.put(AddressManage.STREET, data.getStreet());
            address.put(AddressManage.SELECTED, data.getSelected());

            addresses.add(address);
        }

        return AppNormalService.success(addresses, true);
    }

    @RequestMapping(value = "addAddress", method = RequestMethod.POST)
    public JSONObject addAddress(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String receiverName = mm_0.getString(AddressManage.RECIVER_NAME);
        String contactNumber = mm_0.getString(AddressManage.CONTACT_NUMBER);
        String selected = mm_0.getString(AddressManage.SELECTED);
        String address = mm_0.getString(AddressManage.ADDRESS);
        String street = mm_0.getString(AddressManage.STREET);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setUserId(userId);
        addressDTO.setReceiverName(receiverName);
        addressDTO.setContactNumber(contactNumber);
        addressDTO.setAddress(address);
        addressDTO.setStreet(street);
        if (selected != null) {
            if (Normal.TRUE.equals(selected)) {
                addressDTO.setSelected(true);
            } else {
                addressDTO.setSelected(false);
            }
        }

        ServiceMessage sm = memberService.saveAddress(addressDTO);
        if (!sm.isSuccess()) {
            return AppNormalService.error(sm.getMessage(), 400);
        }

        int id = addressDTO.getId();
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(AddressManage.ID, id);
        return AppNormalService.success(returnData);
    }

    @RequestMapping(value = "modifyAddress", method = RequestMethod.POST)
    public JSONObject modifyAddress(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int id = mm_0.getIntValue(AddressManage.ID);
        String receiverName = mm_0.getString(AddressManage.RECIVER_NAME);
        String contactNumber = mm_0.getString(AddressManage.CONTACT_NUMBER);
        String selected = mm_0.getString(AddressManage.SELECTED);
        String address = mm_0.getString(AddressManage.ADDRESS);
        String street = mm_0.getString(AddressManage.STREET);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(id);
        addressDTO.setUserId(userId);
        addressDTO.setReceiverName(receiverName);
        addressDTO.setContactNumber(contactNumber);
        addressDTO.setAddress(address);
        addressDTO.setStreet(street);
        if (selected != null) {
            if (Normal.TRUE.equals(selected)) {
                addressDTO.setSelected(true);
            } else {
                addressDTO.setSelected(false);
            }
        }
        ServiceMessage sm = memberService.updateAddress(addressDTO);
        if (!sm.isSuccess()) {
            return AppNormalService.error(sm.getMessage(), 400);
        }

        return AppNormalService.success();
    }

    @RequestMapping(value = "deleteAddress", method = RequestMethod.POST)
    public JSONObject deleteAddress(@RequestParam String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int id = mm_0.getIntValue(AddressManage.ID);

        boolean success = memberService.removeAddress(id, userId);
        if (!success) {
            return AppNormalService.error(Error.DELETEFAILED, 500);
        }
        return AppNormalService.success();
    }

    /**
     * 获取我的服务列表
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "listServe", method = RequestMethod.POST)
    public JSONObject listServe(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int userId = appJSON.getData().getUserId();
        int code = Integer.parseInt(mm_0.getString(Serve.CODE));
        ProjectType projectType = ProjectType.values()[code];
        // 分页参数
        int pageIndex = mm_0.getInteger("pageIndex");
        int pageSize = mm_0.getInteger("pageSize");

        // 获得用户购买的服务list
        List<OrderPO> orderPOList = memberService1.queryServeList(userId, projectType, pageIndex, pageSize);
        List<Map<String, String>> returnData = new ArrayList<>();
        for (OrderPO po : orderPOList) {
            // 开始装配
            Map map = new HashMap();
            map.put(Order.IMAGE, po.getProjectImage() == null ? "" : po.getProjectImage());
            map.put(Order.ORG_NAME, po.getOrg() == null ? "" : po.getOrg().getOrgName());
            map.put(Order.CREATEDATE, po.getCreateDate());
            map.put(Serve.CODE, code);
            map.put(Order.STATUS, po.getStatusEnum().getValue());

            if (projectType == ProjectType.PROJECT_CONSULT) {
                map.put(Order.FOOTER, "服务类型:健康咨询");
//                map.put(Order.FOOTER, "服务类型:" + po.getServe().getName());

                map.put(Order.TITLE, po.getEmployee() == null ? "" : po.getEmployee().getRealName());
                map.put(Order.CONTENT, po.getEmployee() == null ? "" : po.getEmployee().getExpertise());
                map.put(HuanXin.USERNAME, po.getEmployee() == null ? "" : po.getEmployee().getUserCode());
                returnData.add(map);
            } else if (projectType == ProjectType.PROJECT_LESSON) {
                map.put(Order.FOOTER, "服务类型:健康课堂");

                map.put(Order.TITLE, Toolkits.projectTilte(po.getSubject()));
                map.put(Order.CONTENT, "主讲人:" + (po.getEmployee() == null ? "" : po.getEmployee().getRealName()));
                map.put(HuanXin.USERNAME, po.getLesson() == null ? "" : po.getLesson().getHuanxinId());
                map.put(OrgServeGroup.ID, po.getLesson() == null ? "" : po.getLesson().getId());
                returnData.add(map);
            }

        }

        return AppNormalService.success(returnData);
    }

    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public JSONObject updateUserInfo(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        UserDTO userAOP = appJSON.getAopData().getUser();
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        String name = mm_0.getString(User.REALNAME);
        String photo = mm_0.getString(User.PHOTO);
        JSONObject address = mm_0.getJSONObject(Area.ADDRESS);
        String sex = mm_0.getString(User.SEX);
        String birthday = mm_0.getString(User.BIRTHDAY);
        Float height = mm_0.getFloat(Record.HEIGHT);
        Float weight = mm_0.getFloat(Record.WEIGHT);
        Float waist = mm_0.getFloat(Record.WAIST);
        Float bust = mm_0.getFloat(Record.BUST);
        Float hip = mm_0.getFloat(Record.HIP);

        UserDTO user = new UserDTO();
        user.setId(userId);

        user.setRealName(name);
        if (address != null) {
            String province = address.getString(Area.PROVINCE);
            String city = address.getString(Area.CITY);
            String county = address.getString(Area.COUNTY);
            String street = address.getString(Area.STREET);
            user.setProvince(province);
            user.setCity(city);
            user.setCounty(county);
            user.setStreet(street);
        }

        if (photo != null && StringUtil.isNotBlank(photo)) {
            String old_photo = user.getPhoto();
            ImageDTO imageVO = AppNormalService.uploadPhoto(photo, old_photo, "head", false);
            if (imageVO.getUploadSuccess()) {
                user.setPhoto(imageVO.getNetPath());
            }
        }

        UserRecordDTO record = new UserRecordDTO();
        record.setUserId(userId);
        // 用户性别
        if (Normal.TRUE.equals(sex)) {
            record.setGender(true);
            userAOP.getRecordDTO().setGender(true);
        }
        if (Normal.FALSE.equals(sex)) {
            record.setGender(false);
            userAOP.getRecordDTO().setGender(false);
        }
        // 出生日期
        if (StringUtils.isNotBlank(birthday)) {
            Date b = DateTimeUtilT.date(birthday);
            record.setBirthday(b);
            userAOP.getRecordDTO().setBirthday(b);
        }
        // 身高 体重 三围
        record.setHeight(height);
        record.setWeight(weight);
        record.setWaist(waist);
        record.setBust(bust);
        record.setHip(hip);
        user.setRecordDTO(record);

        try {
            memberService.updateUserInfo(user);
        } catch (OperationException e) {
            return AppNormalService.error(e.getMessage(), e.getCode());
        }

        Map<String, Object> healthArea = normalService.getSystemCalculateHealthArea(userAOP, 2);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("healthArea", healthArea);
        return AppNormalService.success(returnData, true);
    }
    
    /**
     *  修改用户的兴趣爱好
     *  @author yuhang.weng 
     *  @DateTime 2017年8月15日 下午2:53:25
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "modifyUserHobby", method = RequestMethod.POST)
    public JSONObject modifyUserHobby(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String hobbyListStr = mm_0.getString(UserHobby.HOBBY);
        
        if (StringUtils.isBlank(hobbyListStr)) {
            return AppNormalService.error(Error.PARAMETER_MISSING, 400);
        }
        
        List<Integer> hobbyIdList = JSONArray.parseArray(hobbyListStr, Integer.class);
        
        hobbyService.removeUserHobby(userId);
        try {
            hobbyService.addUserHobby(userId, hobbyIdList);
        } catch (OperationException e) {
            return AppNormalService.error(e.getMessage(), e.getCode());
        }
        return AppNormalService.success();
    }
}
