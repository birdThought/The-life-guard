package com.lifeshs.service.terminal.app.record.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Department;
import com.lifeshs.common.constants.app.Diet;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.app.Medical;
import com.lifeshs.common.constants.app.MedicalCourse;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.PhysicalRecord;
import com.lifeshs.common.constants.app.RecordSport;
import com.lifeshs.common.constants.app.RecordSportDetail;
import com.lifeshs.common.constants.app.Sport;
import com.lifeshs.common.constants.app.SportKind;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.entity.data.TDataDepartment;
import com.lifeshs.entity.data.TDataSport;
import com.lifeshs.entity.data.TDataSportKind;
import com.lifeshs.entity.record.TDataFood;
import com.lifeshs.entity.record.TDataFoodKind;
import com.lifeshs.entity.record.TRecordMedical;
import com.lifeshs.entity.record.TRecordMedicalCourse;
import com.lifeshs.entity.record.TRecordPhysicals;
import com.lifeshs.entity.record.TRecordSport;
import com.lifeshs.entity.record.TRecordSportDetail;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.record.DietDetail;
import com.lifeshs.pojo.record.MedicalCaseVO;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.record.ISportService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.record.IAppRecordService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.MapComparator;
import com.lifeshs.utils.comparator.ComparatorDate;

import jodd.util.StringUtil;

@Service(value = "appRecordService")
public class AppRecordServiceImpl extends AppNormalService implements IAppRecordService {

    @Autowired
    private ISportService sportService;

    @Autowired
    private ICommonTrans commonDao;
    
    @Override
    public JSONObject getMedicalRecordsList(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        if(mm_0.containsKey(User.ID))
            userId = mm_0.getIntValue(User.ID);

        PaginationDTO pagination = terminal.getMedicalService().getMedicalByUserIdPageSplit(userId, pageIndex,
                pageSize);

        List<Map<String, Object>> medicals = pagination.getData();

        for (int i = 0; i < medicals.size(); i++) {
            Map<String, Object> data_i = medicals.get(i);

            data_i.remove(User.ID);
            data_i.remove(Normal.CREATE_DATE);
            data_i.remove(Normal.PHOTO_PATH);
            data_i.put("departments", data_i.get("departmentName"));
        }

        return success(medicals, true);
    }

    @Override
    public JSONObject getMedicalRecords(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);
        if(mm_0.containsKey(User.ID))
            userId = mm_0.getIntValue(User.ID);

        int id = mm_0.getIntValue(Medical.ID);// 病历的ID
        Integer targetUserId = mm_0.getInteger(User.ID);
        if (targetUserId != null) {
            userId = targetUserId;
        }

        Map<String, Object> recordMedical = new HashMap<>(); // 病历map
        List<Map<String, Object>> medicaCourselList = new ArrayList<>(); // 病程集合
        List<Map<String, Object>> medicalList = new ArrayList<>(); // 返回集合
        recordMedical = terminal.getMedicalService().selectMedicalByMedicalIdAndUserId(id, userId);
        Map<String, Object> map = new LinkedHashMap<>();

        if (recordMedical == null || recordMedical.isEmpty()) { // 若实体为空,返回找不到数据信息
            return success(NormalMessage.NO_DATA);
        }

        map.put(Medical.ID, recordMedical.get("id"));
        map.put(Medical.TITLE, recordMedical.get("title"));
        map.put(Medical.DEPARTMENTS, ((TDataDepartment) recordMedical.get("department")).getName());
        map.put(Medical.VISITING_DATE, recordMedical.get("visitingDate"));
        if (((String) recordMedical.get("doctorDiagnosis")).length() > 30) { // 若长度大于30,取前三十个字符
            map.put(Medical.DOCTOR_DIAGNOSIS, ((String) recordMedical.get("doctorDiagnosis")).substring(0, 30));
        } else {
            map.put(Medical.DOCTOR_DIAGNOSIS, (recordMedical.get("doctorDiagnosis")));
        }
        map.put(Medical.BASIC_CONDITION, recordMedical.get("basicCondition"));
        map.put(Medical.HOSPITAL, recordMedical.get("hospital"));

        medicaCourselList = terminal.getMedicalService().selectMedicalCoursesListByMedicalId(id);

        List<Map<String, Object>> orderList = new ArrayList<>();
        if (medicaCourselList != null && medicaCourselList.size() > 0) {
            for (Map<String, Object> course : medicaCourselList) {

                /** 类型号码 */
                Integer typeNumber = 0;

                typeNumber = courseDescToTypeNumber((String) course.get("courseType"));

                if (typeNumber.intValue() != 0) {
                    course.put("typeNumber", typeNumber);
                    course.remove("createDate");
                    orderList.add(course);
                }
            }

            // jdk1.8
            // data.sort(new MapComparator("typeNumber"));
            Collections.sort(orderList, new MapComparator("typeNumber"));
        }

        for (int i = 0; i < orderList.size(); i++) {
            Object visitingDate1 = orderList.get(i).get("visitingDate");
            orderList.get(i).remove("medicalId");
            orderList.get(i).remove("createDate");
            orderList.get(i).remove("visitingDate");
            orderList.get(i).put("visitingDate", visitingDate1);

            String newImg1 = (String) orderList.get(i).get("img1");
            String newImg2 = (String) orderList.get(i).get("img2");
            String newImg3 = (String) orderList.get(i).get("img3");

            orderList.get(i).put(Medical.IMG1, newImg1);
            orderList.get(i).put(Medical.IMG2, newImg2);
            orderList.get(i).put(Medical.IMG3, newImg3);

            orderList.get(i).remove("typeNumber");
        }

        map.put(MedicalCourse.COURSE, orderList);
        medicalList.add(map);

        return success(medicalList, true);
    }

    @Override
    public JSONObject addMedicalRecords(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        String title = mm_0.getString(Medical.TITLE); // 标题
        String visitingDate = mm_0.getString(Medical.VISITING_DATE); // 每页行数
        Integer departmentId = mm_0.getInteger(Medical.DEPARTMENT_ID); // 科室id
        String doctorDiagnosis = mm_0.getString(Medical.DOCTOR_DIAGNOSIS); // 医生诊断
        String basicCondition = mm_0.getString(Medical.BASIC_CONDITION); // 基本病情
        String hospital = (String) mm_0.get(Medical.HOSPITAL);

        if (StringUtils.isBlank(hospital)) {
            hospital = "";
        }

        TRecordMedical tRecordMedical = new TRecordMedical();
        tRecordMedical.setUserId(userId);
        tRecordMedical.setTitle(title);
        tRecordMedical.setVisitingDate(DateTimeUtilT.date(visitingDate));
        tRecordMedical.setDepartmentId(departmentId);
        tRecordMedical.setDoctorDiagnosis(doctorDiagnosis);
        tRecordMedical.setBasicCondition(basicCondition);
        tRecordMedical.setHospital(hospital);
        MedicalCaseVO medicalCaseVO = new MedicalCaseVO();
        medicalCaseVO.setMedical(tRecordMedical);
        boolean result = terminal.getMedicalService().addMedical(userId, medicalCaseVO);
        if (!result) {
            return error(Error.FAIL_ACTION);
        }

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Medical.ID, medicalCaseVO.getMedical().getId());
        return success(returnData);
    }

    @Override
    public JSONObject modifyMedicalRecords(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int id = mm_0.getIntValue(Medical.ID); // 病历ID
        String title = mm_0.getString(Medical.TITLE); // 标题
        String visitingDate = mm_0.getString(Medical.VISITING_DATE); // 每页行数
        int departmentId = mm_0.getIntValue(Medical.DEPARTMENT_ID); // 科室id
        String doctorDiagnosis = mm_0.getString(Medical.DOCTOR_DIAGNOSIS); // 医生诊断
        String basicCondition = mm_0.getString(Medical.BASIC_CONDITION); // 基本病情
        String hospital = mm_0.getString(Medical.HOSPITAL); // 就诊医院

        // TRecordMedical tRecordMedical = new TRecordMedical();
        // tRecordMedical.setId(id);
        // tRecordMedical.setUserId(userId);
        // tRecordMedical.setTitle(title);
        // tRecordMedical.setVisitingDate(DateTimeUtil.strFormatDate3(visitingDate));
        // tRecordMedical.setDepartmentId(departmentId);
        // tRecordMedical.setDoctorDiagnosis(doctorDiagnosis);
        // tRecordMedical.setBasicCondition(basicCondition);
        // MedicalCaseVO medicalCaseVO = new MedicalCaseVO();
        // medicalCaseVO.setMedical(tRecordMedical);
        Integer result = terminal.getMedicalService().updataMedical(userId, id, title, DateTimeUtilT.date(visitingDate),
                departmentId, doctorDiagnosis, basicCondition, hospital);

        if (result == 0) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject delMedicalRecords(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int id = mm_0.getIntValue(Medical.ID); // 病历ID

        boolean result = terminal.getMedicalService().deleteMedical(userId, id);
        if (!result) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject addMedicalCourse(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int medicalId = mm_0.getIntValue("medicalId"); // 病历ID // TODO 特例
        String visitingDate = mm_0.getString(Medical.VISITING_DATE); // 就诊日期
        String courseType = mm_0.getString(Medical.COURSE_TYPE); // 病程类型
        String remark = mm_0.getString(Medical.REMARK); // 备注

        String img1 = mm_0.getString(MedicalCourse.IMG1);
        String img2 = mm_0.getString(MedicalCourse.IMG2);
        String img3 = mm_0.getString(MedicalCourse.IMG3);

        ImageDTO imageVO = null;
        if (StringUtils.isNotBlank(img1)) {
            imageVO = uploadPhoto(img1, null, "record", false);

            if (imageVO.getUploadSuccess()) {
                img1 = imageVO.getNetPath();
            }
        }
        if (StringUtils.isNotBlank(img2)) {
            imageVO = uploadPhoto(img2, null, "record", false);

            if (imageVO.getUploadSuccess()) {
                img2 = imageVO.getNetPath();
            }
        }
        if (StringUtils.isNotBlank(img3)) {
            imageVO = uploadPhoto(img3, null, "record", false);

            if (imageVO.getUploadSuccess()) {
                img3 = imageVO.getNetPath();
            }
        }

        int result = terminal.getMedicalService().addMedicalCourse(userId, medicalId, courseType, remark,
                DateTimeUtilT.date(visitingDate), img1, img2, img3);
        if (result < 1) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject modifyMedicalCourse(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int id = Integer.parseInt((String) mm_0.get(MedicalCourse.ID)); // 病程ID
        String visitingDate = (String) mm_0.get(MedicalCourse.VISITING_DATE); // 就诊日期
        String courseType = (String) mm_0.get(MedicalCourse.TYPE); // 病程类型
        String remark = (String) mm_0.get(MedicalCourse.REMARK); // 备注
        String img1 = mm_0.getString(MedicalCourse.IMG1); // 图片
        String img2 = mm_0.getString(MedicalCourse.IMG2);
        String img3 = mm_0.getString(MedicalCourse.IMG3);

        TRecordMedicalCourse course = terminal.getMedicalService().selectMedicalCourseById(id);

        ImageDTO imageVO = null;
        if (img1 != null) {
            imageVO = uploadPhoto(img1, course.getImg1(), "record", false);

            if (imageVO.getUploadSuccess()) {
                img1 = imageVO.getNetPath();
            }
        }
        if (img2 != null) {
            imageVO = uploadPhoto(img2, course.getImg2(), "record", false);

            if (imageVO.getUploadSuccess()) {
                img2 = imageVO.getNetPath();
            }
        }
        if (img3 != null) {
            imageVO = uploadPhoto(img3, course.getImg3(), "record", false);

            if (imageVO.getUploadSuccess()) {
                img3 = imageVO.getNetPath();
            }
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("courseType", courseType);
        map.put("remark", remark);
        map.put("visitingDate", visitingDate);
        map.put("img1", img1);
        map.put("img2", img2);
        map.put("img3", img3);
        map.put("createDate", DateTimeUtilT.date(new Date()));
        int result = terminal.getMedicalService().updataMedicalCourse(map, id);
        if (result < 1) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject delMedicalCourse(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        int id = mm.getJSONObject(0).getIntValue("id"); // 病程主键ID

        boolean flag = terminal.getMedicalService().deleteMedicalCourse(id);

        if (!flag) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject getMedicalDepartments(String json) {
        List<TDataDepartment> departments = terminal.getMedicalService().selectAllDepartments();

        List<Map<String, String>> datas = new ArrayList<>();
        for (TDataDepartment department : departments) {
            Map<String, String> data = new HashMap<>();
            data.put(Department.ID, department.getId() + "");
            data.put(Department.NAME, department.getName());
            data.put(Department.PARENT_ID, department.getParentId() + "");

            datas.add(data);
        }

        return success(datas);
    }

    @Override
    public JSONObject getPhysicalsList(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        int userId = data.getIntValue(User.ID);
        Integer pageIndex = mm.getJSONObject(0).getInteger(Page.INDEX);// 页码
        Integer pageSize = mm.getJSONObject(0).getInteger(Page.SIZE);// 每页行数
        if(mm.getJSONObject(0).containsKey(User.ID))
            userId = mm.getJSONObject(0).getIntValue(User.ID);

        List<Map<String, Object>> physicalsList = new ArrayList<>();
        PaginationDTO paginationDTO = terminal.getRecordService().selectPhysicalsByUserIdPageSplit(userId, pageIndex,
                pageSize);
        physicalsList = paginationDTO.getData();
        for (int i = 0; i < physicalsList.size(); i++) {
            physicalsList.get(i).remove("userId");
            physicalsList.get(i).remove("createDate");

            String descript = (String) physicalsList.get(i).get("description");
            if (StringUtils.isBlank(descript)) {
                descript = "";
            }
            physicalsList.get(i).remove("description");
            physicalsList.get(i).put(PhysicalRecord.DESCRIPTION, descript);

        }

        return success(physicalsList, true);
    }

    @Override
    public JSONObject getPhysicals(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int id = mm_0.getIntValue(PhysicalRecord.ID);// 体检报告主键ID

        List<Map<String, Object>> physicalsList = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        TRecordPhysicals recordPhysicals = (TRecordPhysicals) terminal.getRecordService().selectPhysicalsById(id);
        if (recordPhysicals == null) {
            return success(NormalMessage.NO_DATA);
        }

        String descript = recordPhysicals.getDescription();
        if (StringUtils.isBlank(descript)) {
            descript = "";
        }

        map.put(PhysicalRecord.ID, id);
        map.put(PhysicalRecord.TITLE, recordPhysicals.getTitle());
        map.put(PhysicalRecord.ORG, recordPhysicals.getPhysicalsOrg());
        map.put(PhysicalRecord.DATE, recordPhysicals.getPhysicalsDate());
        map.put(PhysicalRecord.DESCRIPTION, descript);

        map.put(PhysicalRecord.IMG1, recordPhysicals.getImg1());
        map.put(PhysicalRecord.IMG2, recordPhysicals.getImg2());
        map.put(PhysicalRecord.IMG3, recordPhysicals.getImg3());
        physicalsList.add(map);

        return success(physicalsList);
    }

    @Override
    public JSONObject addPhysicals(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String physicalsOrg = mm_0.getString(PhysicalRecord.ORG); // 就诊日期
        String physicalsDate = mm_0.getString(PhysicalRecord.DATE); // 病程类型
        String img1 = mm_0.getString(PhysicalRecord.IMG1); // 图片
        String img2 = mm_0.getString(PhysicalRecord.IMG2);
        String img3 = mm_0.getString(PhysicalRecord.IMG3);
        String descript = mm_0.getString("remark");

        ImageDTO imageVO = null;
        String img1_src = null;
        if (StringUtil.isNotBlank(img1)) {
            imageVO = uploadPhoto(img1, null, "record", false);
            if (imageVO.getUploadSuccess()) {
                img1_src = imageVO.getNetPath();
            }
        }
        String img2_src = null;
        if (StringUtil.isNotBlank(img2)) {
            imageVO = uploadPhoto(img2, null, "record", false);
            if (imageVO.getUploadSuccess()) {
                img2_src = imageVO.getNetPath();
            }
        }
        String img3_src = null;
        if (StringUtil.isNotBlank(img3)) {
            imageVO = uploadPhoto(img3, null, "record", false);
            if (imageVO.getUploadSuccess()) {
                img3_src = imageVO.getNetPath();
            }
        }

        TRecordPhysicals recordPhysical = new TRecordPhysicals();
        recordPhysical.setPhysicalsOrg(physicalsOrg);
        recordPhysical.setPhysicalsDate(DateTimeUtilT.date(physicalsDate));
        recordPhysical.setUserId(userId);
        recordPhysical.setImg1(img1_src);
        recordPhysical.setImg2(img2_src);
        recordPhysical.setImg3(img3_src);
        recordPhysical.setDescription(descript);

        terminal.getRecordService().addPhysicals(recordPhysical);

        return success();
    }

    private boolean isNetPath(String str) {
        return str.startsWith(Normal.PHOTO_PREFIX);
    }
    
    @Override
    public JSONObject modifyPhysicals(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int id = mm_0.getIntValue(PhysicalRecord.ID); // 体检报告主键ID
        // String title = (String) mm.getJSONObject(0).get("title"); //标题
        String physicalsOrg = mm_0.getString(PhysicalRecord.ORG); // 就诊日期
        String physicalsDate = mm_0.getString(PhysicalRecord.DATE); // 病程类型
        String img1 = mm_0.getString(PhysicalRecord.IMG1); // 图片
        String img2 = mm_0.getString(PhysicalRecord.IMG2);
        String img3 = mm_0.getString(PhysicalRecord.IMG3);
        String descript = mm_0.getString("remark");

        TRecordPhysicals physical = commonDao.getEntity(TRecordPhysicals.class, id);

        ImageDTO imageVO = null;
        if (img1 != null && !isNetPath(img1)) {
            imageVO = uploadPhoto(img1, physical.getImg1(), "record", false);
            
            if (imageVO.getUploadSuccess()) {
                img1 = imageVO.getNetPath();
            }
        }
        if (img2 != null && !isNetPath(img2)) {
            imageVO = uploadPhoto(img2, physical.getImg2(), "record", false);
            
            if (imageVO.getUploadSuccess()) {
                img2 = imageVO.getNetPath();
            }
        }
        if (img3 != null && !isNetPath(img3)) {
            imageVO = uploadPhoto(img3, physical.getImg3(), "record", false);

            if (imageVO.getUploadSuccess()) {
                img3 = imageVO.getNetPath();
            }
        }

        TRecordPhysicals recordPhysical = new TRecordPhysicals();
        recordPhysical.setPhysicalsOrg(physicalsOrg);
        recordPhysical.setPhysicalsDate(DateTimeUtilT.date(physicalsDate));
        recordPhysical.setId(id);
        recordPhysical.setUserId(userId);

        recordPhysical.setImg1(img1);
        recordPhysical.setImg2(img2);
        recordPhysical.setImg3(img3);
        recordPhysical.setDescription(descript);

        try {
            terminal.getRecordService().updatePhysicals(recordPhysical);
        } catch (ParamException e) {
            return success(e.getMessage());
        }

        return success();
    }

    @Override
    public JSONObject delPhysicals(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int id = mm_0.getIntValue(PhysicalRecord.ID); // 体检报告主键ID

        int effectRowCount = terminal.getRecordService().deletePhysicals(userId, id);

        if (effectRowCount < 1) {
            return error(Error.FAIL_ACTION);
        }

        return success();
    }

    @Override
    public JSONObject getDietKcal(String json) {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);
        String startDate = mm_0.getString(Diet.START_DATE);
        String endDate = mm_0.getString(Diet.END_DATE);

        List<Map<String, Object>> dietEnergies = new ArrayList<>();

        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            dietEnergies = terminal.getRecordService().selectDietEnergyByUserIdWithDate(userId, true, startDate,
                    endDate);
        } else {
            dietEnergies = terminal.getRecordService().selectDietEnergyByUserIdWithDate(userId, false, null, null);
        }

        for (int i = 0; i < dietEnergies.size(); i++) {
            Object energy = dietEnergies.get(i).get(Diet.ENERGY);
            dietEnergies.get(i).put(Diet.KCAL, energy);

            Object recordDate = dietEnergies.get(i).get(Diet.RECORD_DATE);
            dietEnergies.get(i).put(Diet.DATE, recordDate);

            dietEnergies.get(i).remove(Diet.ENERGY);
            dietEnergies.get(i).remove(Diet.RECORD_DATE);
        }

        return success(dietEnergies, true);
    }

    @Override
    public JSONObject getDiaryEnergy(String json) {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);
        String startDate = mm_0.getString(Diet.START_DATE);
        String endDate = mm_0.getString(Diet.END_DATE);
        if(mm_0.containsKey(User.ID))
            userId = mm_0.getIntValue(User.ID);

        List<Map<String, Object>> dietEnergies = new ArrayList<>();
        List<TRecordSport> sportEnergies = new ArrayList<>();

        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            dietEnergies = terminal.getRecordService().selectDietEnergyByUserIdWithDate(userId, true, startDate,
                    endDate);
            sportEnergies = sportService.selectSportEnergyByUserIdWithDate(userId, true, startDate, endDate);
        } else {
            dietEnergies = terminal.getRecordService().selectDietEnergyByUserIdWithDate(userId, false, null, null);
            sportEnergies = sportService.selectSportEnergyByUserIdWithDate(userId, false, null, null);
        }

        List<Map<String, Object>> sports = new ArrayList<>();
        for (int i = 0; i < sportEnergies.size(); i++) {
            Map<String, Object> sport = new HashMap<>();

            TRecordSport sportEnergy = sportEnergies.get(i);
            sport.put(RecordSport.ENERGY, sportEnergy.getEnergy());
            sport.put(RecordSport.DATE, sportEnergy.getRecordDate());

            sports.add(sport);
        }

        List<Map<String, Object>> returnDatas = combineSportAndDietEnergy(dietEnergies, sports);

        return success(returnDatas, true);
    }

    @Override
    public JSONObject getDiets(String json) {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int pageIndex = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        if(mm_0.containsKey(User.ID))
            userId = mm_0.getIntValue(User.ID);

        List<DietDetail> diets = terminal.getRecordService().selectDietSplitWithRecordDate(userId, pageIndex, pageSize);

        List<Map<String, Object>> dds = groupDietWithDate(diets);

        return success(dds, true);
    }

    @Override
    public JSONObject getDiet(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String recordDate = mm_0.getString(Diet.RECORD_DATE); // 档案日期

        List<Map<String, Object>> list = getDietWithDate(userId, recordDate);

        return success(list, true);
    }

    @Override
    public JSONObject getHealthDiary(String json) {
        AppJSON appJSON = parseAppJSON(json);

        UserDTO user = appJSON.getAopData().getUser();
        int userId = user.getId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String recordDate = mm_0.getString(Diet.RECORD_DATE);
        if(mm_0.containsKey(User.ID))
            userId = mm_0.getIntValue(User.ID);

        Map<String, Object> returnData = new HashMap<>();

        List<Map<String, Object>> diets = getDietWithDate(userId, recordDate);

        List<Map<String, Object>> sports = getSportWithDate(userId, recordDate);

        returnData.put(Diet.DIETS, diets);
        returnData.put(RecordSport.SPORTS, sports);

        return success(returnData, true);
    }

    @Override
    public JSONObject addDiet(String json) throws Exception {
        String msg = "添加健康日记失败";

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        String dietType = mm_0.getString(Diet.TYPE); // 饮食类型
        Time dietTime = new Time(DateTimeUtilT.time(mm_0.getString(Diet.TIME)).getTime()); //
        int energy = mm_0.getIntValue(Diet.ENERGY);
        JSONArray foods = mm_0.getJSONArray(Diet.FOODS);

        Date date_new = new Date();
        // dateFormat.format(date);
        String date = DateTimeUtilT.date(date_new);

        List<Map<String, Object>> dietlist = terminal.getRecordService().selectDietByUserIdWithDate(userId, date); // 存放饮食Diet集合
        boolean bool = true;
        for (int i = 0; i < dietlist.size(); i++) {
            String type = (String) dietlist.get(i).get("dietType");
            if (type.equals(dietType)) {
                bool = false;
            }
        }
        if (!bool) { // 如果添加的饮食类型已存在，则不执行添加
            msg = "" + dietType + "已存在";
            return success(msg);
        } else {
            // 添加饮食
            HashMap<String, Object> dietMap = new HashMap<>();
            dietMap.put("recordDate", String.valueOf(date));
            dietMap.put("dietType", dietType);
            dietMap.put("dietTime", dietTime);
            dietMap.put("energy", energy);
            int dietId = terminal.getRecordService().addDiet(dietMap, userId);
            // 添加食物
            Map<Integer, HashMap<String, Object>> foods_tmp = new HashMap<>();
            for (int i = 0; i < foods.size(); i++) {
                JSONObject foods_i = foods.getJSONObject(i);
                Integer foodId = foods_i.getInteger("foodId"); // 食物id
                Float kcal_f = foods_i.getFloat("kcal");
                int kcal = kcal_f.intValue(); // 单个食物总能量
                String foodWeight = foods_i.getString("foodWeight");

                HashMap<String, Object> foodMap = new HashMap<>();

                if (foods_tmp.containsKey(foodId)) {
                    foodMap = foods_tmp.get(foodId);
                    float kcal_tmp = (float) (Integer) foodMap.get("kcal");
                    Integer foodWeight_tmp = Integer.parseInt((String) foodMap.get("foodWeight"));
                    foodWeight = (Integer.parseInt(foodWeight) + foodWeight_tmp) + "";
                    kcal += kcal_tmp;
                }

                foodMap.put("foodID", foodId);
                foodMap.put("foodWeight", Integer.valueOf(foodWeight));
                foodMap.put("kcal", kcal);
                foods_tmp.put(foodId, foodMap);
            }

            for (Integer key : foods_tmp.keySet()) {
                HashMap<String, Object> foodMap = foods_tmp.get(key);
                Integer foodId = (Integer) foodMap.get("foodID");
                foodMap.put("foodID", foodId);
                terminal.getRecordService().addDietFood(foodMap, dietId);
            }
        }

        return success();
    }

    @Override
    public JSONObject addSport(String json) {
        AppJSON appJSON = parseAppJSON(json);

        UserDTO user = appJSON.getAopData().getUser();

        JSONArray mm = appJSON.getData().getMsg();
        for (int i = 0; i < mm.size(); i++) {
            JSONObject mm_i = mm.getJSONObject(i);

            String st = mm_i.getString(RecordSport.START_TIME);
            JSONArray details = mm_i.getJSONArray(RecordSportDetail.DETAILS);

            Time startTime = new Time(DateTimeUtilT.time(st).getTime());

            List<TRecordSportDetail> sportDetails = new ArrayList<>();
            for (int j = 0; j < details.size(); j++) {
                TRecordSportDetail sportDetail = new TRecordSportDetail();
                JSONObject detail = details.getJSONObject(j);
                sportDetail.setDuration(detail.getInteger(RecordSportDetail.DURATION));
                sportDetail.setSportId(detail.getInteger(Sport.ID));

                sportDetails.add(sportDetail);
            }

            TRecordSport recordSport = new TRecordSport();
            recordSport.setUserId(user.getId());
            recordSport.setStartTime(startTime);
            recordSport.setDetails(sportDetails);
            recordSport.setRecordDate(new Date());

            sportService.addSport(recordSport);
        }

        return success();
    }

    @Override
    public JSONObject getAllSport(String json) {
        List<Map<String, Object>> sportKinds = new ArrayList<>();

        List<TDataSportKind> datas = sportService.selectAllSports();
        for (TDataSportKind data : datas) {
            Map<String, Object> sportKind = new HashMap<>();
            sportKind.put(SportKind.ID, data.getId());
            sportKind.put(SportKind.NAME, data.getName());

            List<Map<String, Object>> sports = new ArrayList<>();
            for (TDataSport s : data.getSports()) {
                Map<String, Object> sport = new HashMap<>();

                sport.put(Sport.ID, s.getId());
                sport.put(Sport.NAME, s.getName());
                sport.put(Sport.KCAL, s.getKcal());

                sports.add(sport);
            }
            sportKind.put(Sport.SPORTS, sports);

            sportKinds.add(sportKind);
        }

        return success(sportKinds);
    }

    @Override
    public JSONObject getFoodType(String json) throws Exception {
        List<TDataFoodKind> dietlist = terminal.getRecordService().selectAllFoodKind();
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < dietlist.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("kindId", dietlist.get(i).getId());
            map.put("typeName", dietlist.get(i).getName());
            list.add(i, map);
        }

        return success(list);
    }

    @Override
    public JSONObject getFood(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        int foodType = mm.getJSONObject(0).getIntValue("foodType"); // 类型
                                                                    // 0：获取全部食物
                                                                    // 1：蔬菜类；2：肉类；3：海鲜类；4：水果类
        List<Map<String, Object>> list = new ArrayList<>();
        if (foodType == 0) { // 获取全部食物
            List<TDataFood> foodlist = terminal.getRecordService().selectAllFood();

            for (int i = 0; i < foodlist.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", foodlist.get(i).getId());
                map.put("foodName", foodlist.get(i).getName());
                map.put("kcal", foodlist.get(i).getKcal());
                map.put("image", foodlist.get(i).getImage());
                list.add(i, map);
            }
        } else {
            TDataFoodKind tDataFoodKind = commonDao.getEntity(TDataFoodKind.class, foodType);
            list = terminal.getRecordService().selectFoodByKind(tDataFoodKind.getName());
            for (int i = 0; i < list.size(); i++) {
                list.get(i).put("id", list.get(i).get("ID"));
                list.get(i).remove("ID");
                list.get(i).put("kcal", list.get(i).get("KCAL"));
                list.get(i).remove("KCAL");
                list.get(i).put("foodName", list.get(i).get("NAME"));
                list.get(i).remove("NAME");
            }
        }

        return success(list);
    }

    @Override
    public JSONObject getFoodTypeWithFoods(String json) {
        List<Map<String, Object>> datas = new ArrayList<>();

        List<TDataFoodKind> foodKinds = terminal.getRecordService().selectAllFoodKind();
        for (TDataFoodKind foodKind : foodKinds) {
            Map<String, Object> data = new HashMap<>();
            data.put("kindId", foodKind.getId());
            data.put("typeName", foodKind.getName());   

            List<Map<String, Object>> foods = terminal.getRecordService().selectFoodByKind(foodKind.getName());
            for (int i = 0; i < foods.size(); i++) {
                foods.get(i).put("foodName", foods.get(i).get("NAME"));
                foods.get(i).remove("NAME");

                foods.get(i).put(Diet.FOOD_IMAGE, foods.get(i).get("image"));
                foods.get(i).put("id", foods.get(i).get("ID"));
                foods.get(i).put("kcal", foods.get(i).get("KCAL"));
                foods.get(i).remove("ID");
                foods.get(i).remove("KCAL");
            }
            data.put("foods", foods);

            datas.add(data);
        }

        return success(datas);
    }

    @Override
    public JSONObject modifyDiet(String json) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONObject delDiet(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        int id = (int) mm.getJSONObject(0).getIntValue("id"); // 饮食id

        terminal.getRecordService().deleteDiet(id);
        List<Map<String, Object>> list = terminal.getRecordService().selectDietFoodByDietId(id);
        for (int i = 0; i < list.size(); i++) {
            terminal.getRecordService().deleteDietFood((Integer) list.get(i).get("id")); // 将属于该饮食的食物dietFood删除
        }
        return success();
    }

    private Integer courseDescToTypeNumber(String courseDesc) {
        /** 类型号码 */
        Integer typeNumber = 0;

        if (courseDesc.equals("首诊")) {
            typeNumber = 1;
        }
        if (courseDesc.equals("复诊")) {
            typeNumber = 2;
        }
        if (courseDesc.equals("出院")) {
            typeNumber = 3;
        }
        if (courseDesc.equals("晚期")) {
            typeNumber = 4;
        }
        return typeNumber;
    }

    private List<Map<String, Object>> combineSportAndDietEnergy(List<Map<String, Object>> diets,
            List<Map<String, Object>> sports) {
        List<Map<String, Object>> energies = new ArrayList<>();

        List<Date> energyDates = new ArrayList<>();

        Map<Date, Map<String, Map<String, Object>>> tMap = new HashMap<>();
        // 构造的tMap的格式
        /**
         * tMap: {date->{ "diet"->diet, "sport"->sport }}
         **/

        // 第一次将所有的饮食数据存储到tMap中
        for (Map<String, Object> diet : diets) {
            Date date1 = (Date) diet.get(Diet.RECORD_DATE);
            Map<String, Map<String, Object>> childMap = new HashMap<>();
            childMap.put("diet", diet);
            tMap.put(date1, childMap);

            energyDates.add(date1);

        }
        for (Map<String, Object> sport : sports) {
            Date date2 = (Date) sport.get(RecordSport.DATE);

            if (tMap.containsKey(date2)) {
                tMap.get(date2).put("sport", sport);
            } else {
                Map<String, Map<String, Object>> childMap = new HashMap<>();
                childMap.put("sport", sport);
                tMap.put(date2, childMap);
            }

            if (!energyDates.contains(date2)) {
                energyDates.add(date2);
            }
        }

        // 对energyDates的值进行排序
        ComparatorDate c = new ComparatorDate();
        Collections.sort(energyDates, c);

        for (Date date : energyDates) {
            Map<String, Object> energy = new HashMap<>();
            energy.put(Normal.ENERGY_DATE, date);

            Map<String, Object> d = tMap.get(date).get("diet");
            Object de = 0;
            if (d != null) {
                de = d.get(Diet.ENERGY);
            }
            energy.put(Diet.DIET_ENERGY, de);

            Map<String, Object> s = tMap.get(date).get("sport");
            Object se = 0;
            if (s != null) {
                se = s.get(RecordSport.ENERGY);
            }
            energy.put(RecordSport.SPORT_ENERGY, se);

            energies.add(energy);
        }

        return energies;
    }

    /**
     * 为饮食类对象重新组装数据
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月28日 下午5:25:57
     *
     * @param diets
     * @return
     */
    private List<Map<String, Object>> groupDietWithDate(List<DietDetail> diets) {

        List<Map<String, Object>> datas = new ArrayList<>();

        /**
         * 把数据保存为以date为键，diets为值的形式
         * 
         * {"recordDate", [Map, Map, Map, ...], "recordDate", [Map, ...]}
         * 
         */
        Map<String, List<DietDetail>> root = new LinkedHashMap<>();
        for (DietDetail diet : diets) {
            String recordDate = DateTimeUtilT.date(diet.getRecordDate());
            if (root.containsKey(recordDate)) { // 包含该数据就再次加入
                root.get(recordDate).add(diet);
            } else {
                List<DietDetail> root_tmp = new ArrayList<>();
                root_tmp.add(diet);
                root.put(recordDate, root_tmp);
            }
        }
        /**
         * 把内层保存为dietTime为键，diets为值的形式
         */
        // List<String> dietTypes = new ArrayList<>();
        for (String recordDateKey : root.keySet()) {

            /**
             * secondData: {"dietType", [Map, Map, Map, ...], "dietType", [Map,
             * ...]}
             */

            List<Map<String, Object>> datas_tmp = new ArrayList<>();

            Map<String, List<DietDetail>> secondData = new LinkedHashMap<>();
            for (DietDetail diet : root.get(recordDateKey)) {
                String dietType = diet.getDietType();
                if (secondData.containsKey(dietType)) {
                    ((List<DietDetail>) secondData.get(dietType)).add(diet);
                } else {
                    List<DietDetail> details = new ArrayList<>();
                    details.add(diet);
                    secondData.put(dietType, details);
                }
            }
            for (String dietTypeKey : secondData.keySet()) {
                List<Map<String, Object>> foods = new ArrayList<>();
                List<DietDetail> details = secondData.get(dietTypeKey);
                for (DietDetail detail : details) {
                    Map<String, Object> food = new HashMap<>();
                    food.put(Diet.FOOD_ID, detail.getFoodId());
                    food.put(Diet.FOOD_NAME, detail.getFoodName());
                    food.put(Diet.FOOD_WEIGHT, detail.getFoodWeight());
                    food.put(Diet.FOOD_KCAL, detail.getKcal());
                    food.put(Diet.FOOD_IMAGE, detail.getImage());

                    foods.add(food);
                }

                /** 插入数据 */
                Map<String, Object> thirdData = new LinkedHashMap<>();

                DietDetail diet_0 = details.get(0);

                thirdData.put(Diet.ID, diet_0.getId() + "");
                thirdData.put(Diet.TYPE, diet_0.getDietType());
                // thirdData.put(AppDiet.TIME,
                // DateTimeUtilT.time(diet_0.getDietTime()));
                thirdData.put(Diet.TIME, diet_0.getDietTime());
                thirdData.put(Diet.ENERGY, diet_0.getEnergy());
                thirdData.put(Diet.RECORD_DATE, recordDateKey);
                thirdData.put(Diet.FOODS, foods);

                datas_tmp.add(thirdData);
            }

            datas_tmp = dietTimeSort(datas_tmp);

            if (datas_tmp.size() > 0) {
                java.util.Collections.reverse(datas_tmp);
            }

            datas.addAll(datas_tmp);
        }

        if (datas.size() > 0) {
            java.util.Collections.reverse(datas);
        }

        return datas;
    }

    private List<Map<String, Object>> getDietWithDate(int userId, String recordDate) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> dietlist = terminal.getRecordService().selectDietByUserIdWithDate(userId, recordDate); // 存放饮食Diet集合

        for (int i = 0; i < dietlist.size(); i++) {
            Map<String, Object> map = dietlist.get(i);
            /*
             * SimpleDateFormat dateFormater = new SimpleDateFormat("hh:mm:ss");
             * Time time=(Time)map.get("dietTime"); dateFormater.format(time);
             * System.out.println("time:"+time); map.put("dietTime", time);
             */
            map.remove("createDate");
            map.remove("recordDate");
            map.remove("userId");
            int id = (int) map.get("id");
            List<Map<String, Object>> foodList = new ArrayList<>(); // 存放一个Diet的食物Food集合
            foodList = terminal.getRecordService().selectDietFoodByDietId(id);
            for (int j = 0; j < foodList.size(); j++) {
                // foodList.get(j).remove("image");
                // foodList.get(j).remove("kcal");
                foodList.get(j).put(Diet.FOOD_NAME, foodList.get(j).get("name"));
                foodList.get(j).put(Diet.FOOD_TYPE, foodList.get(j).get("kind"));
                foodList.get(j).remove("name");
                foodList.get(j).remove("kind");

                String newImage = (String) foodList.get(j).get("image");
                foodList.get(j).put(Diet.FOOD_IMAGE, newImage);

                map.put(Diet.FOOD, foodList);
            }
            list.add(map);
        }

        return list;
    }

    private List<Map<String, Object>> getSportWithDate(int userId, String recordDate) {
        List<Map<String, Object>> records = new ArrayList<>();

        List<TRecordSport> recordSports = sportService.selectTRecordSportWithDate(userId, recordDate);
        for (TRecordSport recordSport : recordSports) {

            Map<String, Object> tMap = new HashMap<>();
            tMap.put(RecordSport.ID, recordSport.getId());
            tMap.put(RecordSport.START_TIME, recordSport.getStartTime());
            tMap.put(RecordSport.ENERGY, recordSport.getEnergy());

            List<Map<String, Object>> tList = new ArrayList<>();

            List<TRecordSportDetail> details = recordSport.getDetails();
            for (TRecordSportDetail detail : details) {

                Map<String, Object> tMap2 = new HashMap<>();
                tMap2.put(RecordSportDetail.DURATION, detail.getDuration());
                tMap2.put(Sport.NAME, detail.getSport().getName());

                tList.add(tMap2);
            }

            tMap.put(RecordSportDetail.DETAILS, tList);

            records.add(tMap);
        }

        return records;
    }
}
