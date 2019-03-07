package com.lifeshs.app.api.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Medical;
import com.lifeshs.common.constants.app.MedicalCourse;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.PhysicalRecord;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.record.MedicalCourseImg;
import com.lifeshs.common.constants.app.record.PhysicalImg;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.po.record.MedicalCourseImgPO;
import com.lifeshs.po.record.PhysicalAnalysisPO;
import com.lifeshs.po.record.PhysicalImgPO;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.service.record.IRecordService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.record.IAppRecordService;
import com.lifeshs.service1.measure.MeasureAnalysisService;
import com.lifeshs.service1.record.MedicalService;
import com.lifeshs.service1.record.PhysicalAnalysisService;
import com.lifeshs.service1.record.PhysicalService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.image.ImageUtilV2;
import com.lifeshs.vo.record.MedicalBasicVO;
import com.lifeshs.vo.record.MedicalCourseImgVO;
import com.lifeshs.vo.record.MedicalCourseVO;
import com.lifeshs.vo.record.MedicalVO;
import com.lifeshs.vo.record.PhysicalImgVO;
import com.lifeshs.vo.record.PhysicalVO;

/**
 * 应用app健康档案
 * 
 * @author yuhang.weng
 * @DateTime 2017年2月21日 下午4:43:59
 */
@RestController(value = "appRecordController")
@RequestMapping(value = { "/app", "/app/record" })
public class RecordController {

    @Resource(name = "appRecordService")
    private IAppRecordService recordService;

    @Resource(name = "physicalService")
    private PhysicalService physicalService;
    
    @Resource(name = "physicalAnalysisServiceImpl")
    private PhysicalAnalysisService physicalAnalysisService;
    
    @Resource(name = "medicalService")
    private MedicalService medicalService;
    
    @Resource(name = "measureAnalysisServiceImpl")
    private MeasureAnalysisService measureAnalysisService;
    
    @Autowired
    private IRecordService rService;
    
    /** 健康档案上传文件统一目录 */
    private static final String RECORD_UPLOAD_CATEGORY_PATH = "record";
    
    /**
     * 获取病历列表
     *
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:33:47
     * @serverComment
     * @param
     */
    @RequestMapping(value = "getMedicalRecordsList", method = RequestMethod.POST)
    public JSONObject getMedicalRecordsList(String json) throws Exception {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        int pageIndex = mm_0.getIntValue(Page.INDEX);// 页码
        int pageSize = mm_0.getIntValue(Page.SIZE);// 每页行数
        if(mm_0.containsKey(User.ID)) {
            userId = mm_0.getIntValue(User.ID);
        }
        
        List<MedicalBasicVO> medicalList = medicalService.listMedicalBasic(userId, pageIndex, pageSize).getData();
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (MedicalBasicVO m : medicalList) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put(Medical.ID, m.getId());
            returnData.put(Medical.DEPARTMENTS, m.getDepartments());
            returnData.put(Medical.DOCTOR_DIAGNOSIS, m.getDoctorDiagnosis());
            returnData.put(Medical.VISITING_DATE, m.getVisitingDate());
            returnData.put(Medical.BASIC_CONDITION, m.getBasicCondition());
            returnData.put(Medical.TITLE, m.getTitle());
            returnData.put(Medical.HOSPITAL, m.getHospital());
            returnDataList.add(returnData);
        }
        
        return AppNormalService.success(returnDataList, true);
    }

    /**
     * 获取病历详细
     *
     * @author wenxian.cai
     * @DateTime 2016年8月9日下午2:44:02
     * @serverComment
     * @param
     */
    @RequestMapping(value = "getMedicalRecords", method = RequestMethod.POST)
    public JSONObject getMedicalRecords(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        int id = mm_0.getIntValue(Medical.ID);// 病历的ID
        if(mm_0.containsKey(User.ID)) {
            userId = mm_0.getIntValue(User.ID);
        }

        MedicalVO medical = medicalService.getMedical(id, userId);
        Map<String, Object> returnData = enclosureMedical(medical);
        return AppNormalService.success(returnData, true);
    }
    
    /**
     *  封装病历数据
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午2:55:10
     *
     *  @param data
     *  @return
     */
    private Map<String, Object> enclosureMedical(MedicalVO data) {
        Map<String, Object> returnData = new HashMap<>();
        if (data == null) {
            return returnData;
        }
        String doctorDiagnosis = data.getDoctorDiagnosis();
        // 若长度大于30,取前三十个字符
        if (doctorDiagnosis.length() > 30) {
            doctorDiagnosis = doctorDiagnosis.substring(0, 30);
        }
        returnData.put(Medical.ID, data.getId());
        returnData.put(Medical.TITLE, data.getTitle());
        returnData.put(Medical.VISITING_DATE, data.getVisitingDate());
        returnData.put(Medical.HOSPITAL, data.getHospital());
        returnData.put(Medical.DEPARTMENTS, data.getDepartments());
        returnData.put(Medical.DOCTOR_DIAGNOSIS, doctorDiagnosis);
        returnData.put(Medical.BASIC_CONDITION, data.getBasicCondition());
        
        List<Map<String, Object>> returnDataCourseList = enclosureMedicalCourseList(data.getCourseList());
        returnData.put(MedicalCourse.COURSE, returnDataCourseList);
        return returnData;
    }
    
    /**
     *  封装病程数据
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午2:55:21
     *
     *  @param dataList 病程集合
     *  @return
     */
    private List<Map<String, Object>> enclosureMedicalCourseList(List<MedicalCourseVO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        // 数据封装病程
        for (MedicalCourseVO m : dataList) {
            Map<String, Object> returnDataCourseMap = enclosureMedicalCourse(m);
            returnDataList.add(returnDataCourseMap);
        }
        return returnDataList;
    }
    
    /**
     *  封装病程数据
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 上午11:57:04
     *
     *  @param data 一个病程
     *  @return
     */
    private Map<String, Object> enclosureMedicalCourse(MedicalCourseVO data) {
        Map<String, Object> returnDataCourseMap = new HashMap<>();
        returnDataCourseMap.put(MedicalCourse.ID, data.getId());
        returnDataCourseMap.put(MedicalCourse.TYPE, data.getCourseType());
        returnDataCourseMap.put(MedicalCourse.REMARK, data.getRemark());
        returnDataCourseMap.put(MedicalCourse.VISITING_DATE, data.getVisitingDate());
        
        // 数据封装图片
        List<Map<String, Object>> returnDataImgList = enclosureMedicalCourseImgList(data.getImgList());
        returnDataCourseMap.put(MedicalCourseImg.IMG, returnDataImgList);
        String img1 = null;
        String img2 = null;
        String img3 = null;
        for (int i = 0; i < data.getImgList().size(); i++) {
            if (i == 0) {
                img1 = data.getImgList().get(i).getImg();
            }
            if (i == 1) {
                img2 = data.getImgList().get(i).getImg();
            }
            if (i == 2) {
                img3 = data.getImgList().get(i).getImg();
            }
        }
        returnDataCourseMap.put(MedicalCourse.IMG1, img1);
        returnDataCourseMap.put(MedicalCourse.IMG2, img2);
        returnDataCourseMap.put(MedicalCourse.IMG3, img3);
        return returnDataCourseMap;
    }

    /**
     *  封装病程图片数据
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午2:55:48
     *
     *  @param dataList
     *  @return
     */
    private List<Map<String, Object>> enclosureMedicalCourseImgList(List<MedicalCourseImgPO> dataList) {
        List<Map<String, Object>> returnDataImgList = new ArrayList<>();
        for (MedicalCourseImgPO i : dataList) {
            Map<String, Object> returnDataImg = new HashMap<>();
            returnDataImg.put(MedicalCourseImg.ID, i.getId());
            returnDataImg.put(MedicalCourseImg.IMG_PATH, i.getImg());
            returnDataImg.put(MedicalCourseImg.CREATE_DATE, i.getCreateDate());
            returnDataImg.put(MedicalCourseImg.MODIFY_DATE, i.getModifyDate());
            returnDataImgList.add(returnDataImg);
        }
        return returnDataImgList;
    }

    /**
     * 添加个人病历
     *
     * @author wenxian.cai
     * @DateTime 2016年8月9日下午4:18:11
     * @serverComment
     * @param json
     */
    @RequestMapping(value = "addMedicalRecords", method = RequestMethod.POST)
    public JSONObject addMedicalRecords(String json) throws Exception {
        return recordService.addMedicalRecords(json);
    }

    /**
     * 修改个人病历
     *
     * @author wenxian.cai
     * @DateTime 2016年8月9日下午5:26:54
     * @serverComment
     * @param json
     */
    @RequestMapping(value = "modifyMedicalRecords", method = RequestMethod.POST)
    public JSONObject modifyMedicalRecords(String json) throws Exception {
        return recordService.modifyMedicalRecords(json);
    }

    /**
     * 删除个人病历
     *
     * @author wenxian.cai
     * @DateTime 2016年8月9日下午5:57:25
     * @serverComment
     * @param json
     */
    @RequestMapping(value = "delMedicalRecords", method = RequestMethod.POST)
    public JSONObject delMedicalRecords(String json) throws Exception {
        return recordService.delMedicalRecords(json);
    }

    /**
     * 添加个人病程
     *
     * @author wenxian.cai
     * @DateTime 2016年8月9日下午7:53:18
     * @serverComment
     * @param json
     */
    @RequestMapping(value = "addMedicalCourse", method = RequestMethod.POST)
    public JSONObject addMedicalCourse(String json) throws BaseException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        Integer userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        int medicalId = mm_0.getIntValue("medicalId"); // 病历ID // TODO 特例
        String visitingDate = mm_0.getString(Medical.VISITING_DATE); // 就诊日期
        String courseType = mm_0.getString(Medical.COURSE_TYPE); // 病程类型
        String remark = mm_0.getString(Medical.REMARK); // 备注

        /**
         * 如果是版本 2.4.5，包括2.4.5之前的接口，使用的是3张BASE64的图片
         * 版本2.4.5之后的接口，使用9张图模式
         */
        List<MedicalCourseImgPO> imgList = new ArrayList<>();
        /** 这一段是兼容性代码 */
        String img1 = mm_0.getString(Medical.IMG1);
        String img2 = mm_0.getString(Medical.IMG2);
        String img3 = mm_0.getString(Medical.IMG3);
        boolean img1NotNull = (img1 != null);
        boolean img2NotNull = (img2 != null);
        boolean img3NotNull = (img3 != null);
        if (img1NotNull || img2NotNull || img3NotNull) {
            ImageDTO imageVO = null;
            if (img1NotNull) {
                imageVO = AppNormalService.uploadPhoto(img1, null, RECORD_UPLOAD_CATEGORY_PATH, false);
                if (imageVO.getUploadSuccess()) {
                    MedicalCourseImgPO imgPO = new MedicalCourseImgPO();
                    imgPO.setImg(imageVO.getNetPath());
                    imgList.add(imgPO); // 添加一张图片
                }
            }
            if (img2NotNull) {
                imageVO = AppNormalService.uploadPhoto(img2, null, RECORD_UPLOAD_CATEGORY_PATH, false);
                if (imageVO.getUploadSuccess()) {
                    MedicalCourseImgPO imgPO = new MedicalCourseImgPO();
                    imgPO.setImg(imageVO.getNetPath());
                    imgList.add(imgPO); // 添加一张图片
                }
            }
            if (img3NotNull) {
                imageVO = AppNormalService.uploadPhoto(img3, null, RECORD_UPLOAD_CATEGORY_PATH, false);
                if (imageVO.getUploadSuccess()) {
                    MedicalCourseImgPO imgPO = new MedicalCourseImgPO();
                    imgPO.setImg(imageVO.getNetPath());
                    imgList.add(imgPO); // 添加一张图片
                }
            }
            /** 兼容代码结尾 */
        } else {
            /** 2.4.5之后的9张图代码 */
            String imgStr = mm_0.getString(MedicalCourseImg.IMG);
            if (StringUtils.isNotBlank(imgStr)) {
                List<MedicalCourseImgVO> imgVOList = JSONArray.parseArray(imgStr, MedicalCourseImgVO.class);
                for (MedicalCourseImgVO i : imgVOList) {
                    MedicalCourseImgPO imgPO = new MedicalCourseImgPO();
                    // 图片需要移动到正式目录下
                    String nwNetPath = null;
                    try {
                        nwNetPath = ImageUtilV2.copyImgFileToUploadFolder(i.getImg(), RECORD_UPLOAD_CATEGORY_PATH, false);
                    } catch (IOException e) {
                        throw new ParamException("您提交的图片格式不正确", ErrorCodeEnum.FAILED);
                    }
                    
                    imgPO.setImg(nwNetPath);
                    imgList.add(imgPO);
                }
            }
            /** 9张图代码结尾 */
        }
        
        Date mDate = null;
        if (StringUtils.isNotBlank(visitingDate)) {
            mDate = DateTimeUtilT.date(visitingDate);
        }
        
        MedicalCourseVO course = new MedicalCourseVO();
        course.setMedicalId(medicalId);
        course.setCourseType(courseType);
        course.setRemark(remark);
        course.setVisitingDate(mDate);
        course.setImgList(imgList);
        
        medicalService.addMedicalCourse(course, userId);
        
        // 返回对象
        MedicalCourseVO courseVO = medicalService.getMedicalCourse(course.getId(), userId);
        Map<String, Object> returnData = enclosureMedicalCourse(courseVO);
        return AppNormalService.success(returnData);
    }

    /**
     * 修改个人病程
     *
     * @author wenxian.cai
     * @DateTime 2016年8月9日下午8:27:27
     * @serverComment
     * @param
     */
    @RequestMapping(value = "modifyMedicalCourse", method = RequestMethod.POST)
    public JSONObject modifyMedicalCourse(String json) throws BaseException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        int id = mm_0.getIntValue(MedicalCourse.ID); // 病程ID
        String visitingDate = mm_0.getString(MedicalCourse.VISITING_DATE); // 就诊日期
        String courseType = mm_0.getString(MedicalCourse.TYPE); // 病程类型
        String remark = mm_0.getString(MedicalCourse.REMARK); // 备注

        /**
         * 如果是版本 2.4.5，包括2.4.5之前的接口，使用的是3张BASE64的图片
         * 版本2.4.5之后的接口，使用9张图模式
         */
        List<MedicalCourseImgPO> imgList = new ArrayList<>();
        /** 这一段是兼容性代码 */
        String img1 = mm_0.getString(Medical.IMG1);
        String img2 = mm_0.getString(Medical.IMG2);
        String img3 = mm_0.getString(Medical.IMG3);
        boolean img1NotNull = (img1 != null);
        boolean img2NotNull = (img2 != null);
        boolean img3NotNull = (img3 != null);
        if (img1NotNull || img2NotNull || img3NotNull) {
            ImageDTO imageVO = null;
            if (img1NotNull) {
                imageVO = AppNormalService.uploadPhoto(img1, null, RECORD_UPLOAD_CATEGORY_PATH, false);
                if (imageVO.getUploadSuccess()) {
                    MedicalCourseImgPO imgPO = new MedicalCourseImgPO();
                    imgPO.setImg(imageVO.getNetPath());
                    imgList.add(imgPO); // 添加一张图片
                }
            }
            if (img2NotNull) {
                imageVO = AppNormalService.uploadPhoto(img2, null, RECORD_UPLOAD_CATEGORY_PATH, false);
                if (imageVO.getUploadSuccess()) {
                    MedicalCourseImgPO imgPO = new MedicalCourseImgPO();
                    imgPO.setImg(imageVO.getNetPath());
                    imgList.add(imgPO); // 添加一张图片
                }
            }
            if (img3NotNull) {
                imageVO = AppNormalService.uploadPhoto(img3, null, RECORD_UPLOAD_CATEGORY_PATH, false);
                if (imageVO.getUploadSuccess()) {
                    MedicalCourseImgPO imgPO = new MedicalCourseImgPO();
                    imgPO.setImg(imageVO.getNetPath());
                    imgList.add(imgPO); // 添加一张图片
                }
            }
            /** 兼容代码结尾 */
        } else {
            /** 2.4.5之后的9张图代码 */
            String imgStr = mm_0.getString(MedicalCourseImg.IMG);
            if (StringUtils.isNotBlank(imgStr)) {
                List<MedicalCourseImgVO> imgVOList = JSONArray.parseArray(imgStr, MedicalCourseImgVO.class);
                for (MedicalCourseImgVO i : imgVOList) {
                    MedicalCourseImgPO imgPO = new MedicalCourseImgPO();
                    // 图片需要移动到正式目录下
                    String netPath = i.getImg();
                    if (StringUtils.isNotBlank(netPath)) {
                        try {
                            netPath = ImageUtilV2.copyImgFileToUploadFolder(netPath, RECORD_UPLOAD_CATEGORY_PATH, false);
                        } catch (IOException e) {
                            throw new ParamException("您提交的图片格式不正确", ErrorCodeEnum.FAILED);
                        }
                    }
                    
                    imgPO.setId(i.getId());
                    imgPO.setImg(netPath);
                    imgList.add(imgPO);
                }
            }
            /** 9张图代码结尾 */
        }
        
        Date mDate = null;
        if (StringUtils.isNotBlank(visitingDate)) {
            mDate = DateTimeUtilT.date(visitingDate);
        }
        
        MedicalCourseVO course = new MedicalCourseVO();
        course.setId(id);
        course.setCourseType(courseType);
        course.setRemark(remark);
        course.setVisitingDate(mDate);
        course.setImgList(imgList);
        
        medicalService.updateMedicalCourse(course, userId);
        
        // 返回对象
        MedicalCourseVO courseVO = medicalService.getMedicalCourse(id, userId);
        Map<String, Object> returnData = enclosureMedicalCourse(courseVO);
        return AppNormalService.success(returnData);
    }

    /**
     * 删除个人病程
     *
     * @author wenxian.cai
     * @DateTime 2016年8月10日上午9:27:28
     * @serverComment
     * @param json
     */
    @RequestMapping(value = "delMedicalCourse", method = RequestMethod.POST)
    public JSONObject delMedicalCourse(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        int id = appJSON.getData().getFirstJSONObject().getIntValue("id"); // 病程主键ID

        medicalService.deleteMedicalCourse(id, userId);
        return AppNormalService.success();
    }

    /**
     * 获取所有科室
     *
     * @author yuhang.weng
     * @DateTime 2016年12月12日 上午10:26:07
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getMedicalDepartments", method = RequestMethod.POST)
    public JSONObject getMedicalDepartments(String json) {
        return recordService.getMedicalDepartments(json);
    }

    /**
     * 获取个人体检报告列表
     *
     * @author wenxian.cai
     * @DateTime 2016年8月10日上午9:47:43
     * @serverComment
     * @param json
     */
    @RequestMapping(value = "getPhysicalsList", method = RequestMethod.POST)
    public JSONObject getPhysicalsList(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        
        Integer pageIndex = mm_0.getInteger(Page.INDEX);// 页码
        Integer pageSize = mm_0.getInteger(Page.SIZE);// 每页行数
        if(mm_0.containsKey(User.ID)) {
            userId = mm_0.getIntValue(User.ID);
        }

        List<PhysicalVO> physicalList = physicalService.listPhysical(userId, pageIndex, pageSize).getData();
        // 封装数据
        List<Map<String, Object>> returnDataList = enclosurePhysicalList(physicalList);
        return AppNormalService.success(returnDataList, true);
    }

    /**
     *  封装体检报告对象数据
     *  @author yuhang.weng 
     *  @DateTime 2017年9月12日 下午2:40:27
     *
     *  @param dataList
     *  @return
     */
    private List<Map<String, Object>> enclosurePhysicalList(List<PhysicalVO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (PhysicalVO physical : dataList) {
            Map<String, Object> returnData = enclosurePhysical(physical);
            returnDataList.add(returnData);
        }
        return returnDataList;
    }
    
    /**
     *  封装体检报告对象数据
     *  @author yuhang.weng 
     *  @DateTime 2017年9月12日 下午2:40:44
     *
     *  @param data
     *  @return
     */
    private Map<String, Object> enclosurePhysical(PhysicalVO data) {
        if (data == null) {
            return null;
        }
        
        String img1 = null;
        String img2 = null;
        String img3 = null;
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(PhysicalRecord.ID, data.getId());
        returnData.put(PhysicalRecord.TITLE, data.getTitle());
        returnData.put(PhysicalRecord.ORG, data.getPhysicalsOrg());
        returnData.put(PhysicalRecord.DATE, data.getPhysicalsDate());
        returnData.put(PhysicalRecord.DESCRIPTION, data.getDescription());
        
        List<Map<String, Object>> returnImgList = new ArrayList<>();
        for (int i = 0; i < data.getImgList().size(); i++) {
            PhysicalImgPO img = data.getImgList().get(i);
            Map<String, Object> returnImg = new HashMap<>();
            returnImg.put(PhysicalImg.ID, img.getId());
            returnImg.put(PhysicalImg.IMG_PATH, img.getImg());
            returnImg.put(PhysicalImg.CREATE_DATE, img.getCreateDate());
            returnImg.put(PhysicalImg.MODIFY_DATE, img.getModifyDate());
            returnImgList.add(returnImg);
            
            /** 兼容代码开始(版本<=2.4.5) */
            if (i == 0) {
                img1 = img.getImg();
            }
            if (i == 1) {
                img2 = img.getImg();
            }
            if (i == 2) {
                img3 = img.getImg();
            }
            /** 兼容代码结束 */
        }
        
        returnData.put(PhysicalRecord.IMG, returnImgList);
        
        /** 兼容代码开始(版本<=2.4.5) */
        returnData.put(PhysicalRecord.IMG1, img1);
        returnData.put(PhysicalRecord.IMG2, img2);
        returnData.put(PhysicalRecord.IMG3, img3);
        /** 兼容代码结束 */
        
        List<Map<String, Object>> returnAnalysisList = new ArrayList<>();
        for (PhysicalAnalysisPO a : data.getAnalysisList()) {
            Map<String, Object> returnAnalysis = new HashMap<>();
            returnAnalysis.put("doctorSign", a.getDoctorSign());
            returnAnalysis.put("content", a.getReply());
            returnAnalysisList.add(returnAnalysis);
        }
        returnData.put(PhysicalRecord.ANALYSIS, returnAnalysisList);
        
        return returnData;
    }
    
    /**
     * 获取体检报告详细
     *
     * @author wenxian.cai
     * @DateTime 2016年8月10日上午10:23:44
     * @serverComment
     * @param json
     */
    @RequestMapping(value = "getPhysicals", method = RequestMethod.POST)
    public JSONObject getPhysicals(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int id = mm_0.getIntValue(PhysicalRecord.ID);// 体检报告主键ID

        PhysicalVO physical = physicalService.getPhysical(id, userId);
        // 数据封装
        Map<String, Object> returnData = enclosurePhysical(physical);
        return AppNormalService.success(returnData, true);
    }

    /**
     * 添加个人体检报告
     *
     * @author wenxian.cai
     * @DateTime 2016年8月10日上午10:40:10
     * @serverComment
     * @param json
     */
    @RequestMapping(value = "addPhysicals", method = RequestMethod.POST)
    public JSONObject addPhysicals(String json) throws BaseException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String physicalsOrg = mm_0.getString(PhysicalRecord.ORG); // 就诊日期
        String physicalsDate = mm_0.getString(PhysicalRecord.DATE); // 病程类型
        String descript = mm_0.getString("remark");
        if (descript == null) {
            // remark 与 PhysicalRecord.DESCRIPTION 都属于描述字段
            descript = mm_0.getString(PhysicalRecord.DESCRIPTION);
        }

        /**
         * 如果是版本 2.4.5，包括2.4.5之前的接口，使用的是3张BASE64的图片
         * 版本2.4.5之后的接口，使用9张图模式
         */
        List<PhysicalImgPO> imgList = new ArrayList<>();
        /** 这一段是兼容性代码 */
        String img1 = mm_0.getString(PhysicalRecord.IMG1);
        String img2 = mm_0.getString(PhysicalRecord.IMG2);
        String img3 = mm_0.getString(PhysicalRecord.IMG3);
        boolean img1NotNull = (img1 != null);
        boolean img2NotNull = (img2 != null);
        boolean img3NotNull = (img3 != null);
        if (img1NotNull || img2NotNull || img3NotNull) {
            ImageDTO imageVO = null;
            if (img1NotNull) {
                imageVO = AppNormalService.uploadPhoto(img1, null, RECORD_UPLOAD_CATEGORY_PATH, false);
                if (imageVO.getUploadSuccess()) {
                    PhysicalImgPO imgPO = new PhysicalImgPO();
                    imgPO.setImg(imageVO.getNetPath());
                    imgList.add(imgPO); // 添加一张图片
                }
            }
            if (img2NotNull) {
                imageVO = AppNormalService.uploadPhoto(img2, null, RECORD_UPLOAD_CATEGORY_PATH, false);
                if (imageVO.getUploadSuccess()) {
                    PhysicalImgPO imgPO = new PhysicalImgPO();
                    imgPO.setImg(imageVO.getNetPath());
                    imgList.add(imgPO); // 添加一张图片
                }
            }
            if (img3NotNull) {
                imageVO = AppNormalService.uploadPhoto(img3, null, RECORD_UPLOAD_CATEGORY_PATH, false);
                if (imageVO.getUploadSuccess()) {
                    PhysicalImgPO imgPO = new PhysicalImgPO();
                    imgPO.setImg(imageVO.getNetPath());
                    imgList.add(imgPO); // 添加一张图片
                }
            }
            /** 兼容代码结尾 */
        } else {
            /** 2.4.5之后的9张图代码 */
            String imgStr = mm_0.getString(PhysicalRecord.IMG);
            if (StringUtils.isNotBlank(imgStr)) {
                List<PhysicalImgVO> imgVOList = JSONArray.parseArray(imgStr, PhysicalImgVO.class);
                for (PhysicalImgVO i : imgVOList) {
                    PhysicalImgPO imgPO = new PhysicalImgPO();
                    // 图片需要移动到正式目录下
                    String nwNetPath = null;
                    try {
                        nwNetPath = ImageUtilV2.copyImgFileToUploadFolder(i.getImg(), RECORD_UPLOAD_CATEGORY_PATH, false);
                    } catch (IOException e) {
                        throw new ParamException("您提交的图片格式不正确", ErrorCodeEnum.FAILED);
                    }
                    imgPO.setImg(nwNetPath);
                    imgList.add(imgPO);
                }
            }
            /** 9张图代码结尾 */
        }
        
        Date pDate = null;
        if (physicalsDate != null) {
            pDate = DateTimeUtilT.date(physicalsDate);
        }

        PhysicalVO physical = new PhysicalVO();
        physical.setUserId(userId);
        physical.setPhysicalsOrg(physicalsOrg);
        physical.setPhysicalsDate(pDate);
        physical.setDescription(descript);
        physical.setImgList(imgList);
        
        physicalService.addPhysical(physical);

        PhysicalVO nwPhysical = physicalService.getPhysical(physical.getId(), userId);
        // 封装数据
        Map<String, Object> returnData = enclosurePhysical(nwPhysical);
        return AppNormalService.success(returnData);
    }

    /**
     * 修改个人体检报告
     *
     * @author wenxian.cai
     * @DateTime 2016年8月10日上午11:00:09
     * @serverComment
     * @param
     */
    @RequestMapping(value = "modifyPhysicals", method = RequestMethod.POST)
    public JSONObject modifyPhysicals(String json) throws BaseException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();

        int id = mm_0.getIntValue(PhysicalRecord.ID); // 体检报告主键ID
        String physicalsOrg = mm_0.getString(PhysicalRecord.ORG); // 就诊日期
        String physicalsDate = mm_0.getString(PhysicalRecord.DATE); // 病程类型
        String descript = mm_0.getString("remark");
        if (descript == null) {
            // remark 与 PhysicalRecord.DESCRIPTION 都属于描述字段
            descript = mm_0.getString(PhysicalRecord.DESCRIPTION);
        }
        String imgStr = mm_0.getString(PhysicalRecord.IMG);
        
        List<PhysicalImgPO> imgList = new ArrayList<>();
        if (StringUtils.isNotBlank(imgStr)) {
            List<PhysicalImgVO> imgVOList = JSONArray.parseArray(imgStr, PhysicalImgVO.class);
            for (PhysicalImgVO i : imgVOList) {
                PhysicalImgPO imgPO = new PhysicalImgPO();
                String netPath = i.getImg();
                if (StringUtils.isNotBlank(netPath)) {
                    // 图片需要移动到正式目录下
                    try {
                        netPath = ImageUtilV2.copyImgFileToUploadFolder(netPath, RECORD_UPLOAD_CATEGORY_PATH, false);
                    } catch (IOException e) {
                        throw new ParamException("您提交的图片格式不正确", ErrorCodeEnum.FAILED);
                    }
                }
                imgPO.setImg(netPath);
                imgPO.setId(i.getId());
                imgList.add(imgPO);
            }
        }
        
        Date pDate = null;
        if (StringUtils.isNotBlank(physicalsDate)) {
            pDate = DateTimeUtilT.date(physicalsDate);
        }
        PhysicalVO physical = new PhysicalVO();
        physical.setId(id);
        physical.setUserId(userId);
        physical.setPhysicalsOrg(physicalsOrg);
        physical.setPhysicalsDate(pDate);
        physical.setDescription(descript);
        physical.setImgList(imgList);

        physicalService.updatePhysical(physical);
        
        PhysicalVO nwPhysical = physicalService.getPhysical(id, userId);
        // 数据封装
        Map<String, Object> returnData = enclosurePhysical(nwPhysical);
        return AppNormalService.success(returnData);
    }

    /**
     * 删除个人体检报告
     *
     * @author wenxian.cai
     * @DateTime 2016年8月10日上午11:06:19
     * @serverComment
     * @param json
     */
    @RequestMapping(value = "delPhysicals", method = RequestMethod.POST)
    public JSONObject delPhysicals(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int id = mm_0.getIntValue(PhysicalRecord.ID); // 体检报告主键ID
        physicalService.deletePhysical(id, userId);
        return AppNormalService.success();
    }
    

    /**
     *  修改体检报告阅读状态为已读
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 上午11:26:56
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "readPhysicalAnalysis", method = RequestMethod.POST)
    public JSONObject readPhysicalAnalysis(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int id = mm_0.getIntValue(PhysicalRecord.ID);
        
        physicalAnalysisService.readAnalysisList(id);
        return AppNormalService.success();
    }

    /**
     * 获取健康日记能量列表
     *
     * @author yuhang.weng
     * @DateTime 2016年10月22日 上午9:12:58
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getDietKcal", method = RequestMethod.POST)
    public JSONObject getDietKcal(String json) {
        return recordService.getDietKcal(json);
    }

    /**
     * 获取健康日记能量列表
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月21日 下午5:05:17
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getDiaryEnergy", method = RequestMethod.POST)
    public JSONObject getDiaryEnergy(String json) {
        return recordService.getDiaryEnergy(json);
    }

    /**
     * 获取健康日记(分页)
     *
     * @author yuhang.weng
     * @DateTime 2016年10月28日 上午11:41:01
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getDiets", method = RequestMethod.POST)
    public JSONObject getDiets(String json) {
        return recordService.getDiets(json);
    }

    /**
     * 获取健康日记详细
     *
     * @author wenxian.cai
     * @DateTime 2016年8月19日上午9:55:27
     * @serverComment
     * @param
     */
    @RequestMapping(value = "getDiet", method = RequestMethod.POST)
    public JSONObject getDiet(String json) throws Exception {
        return recordService.getDiet(json);
    }

    /**
     * 获取健康日记详细
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月21日 下午5:06:03
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getHealthDiary", method = RequestMethod.POST)
    public JSONObject getHealthDiary(String json) {
        return recordService.getHealthDiary(json);
    }

    /**
     * 添加健康日记
     *
     * @author wenxian.cai
     * @DateTime 2016年8月19日上午9:56:51
     * @serverComment
     * @param
     */
    @RequestMapping(value = "addDiet", method = RequestMethod.POST)
    public JSONObject addDiet(String json) throws Exception {
        return recordService.addDiet(json);
    }

    /**
     * 添加运动记录
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月21日 下午5:06:42
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "addSport", method = RequestMethod.POST)
    public JSONObject addSport(String json) {
        return recordService.addSport(json);
    }

    /**
     * 获取运动记录的所有运动
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月21日 下午5:07:01
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getAllSport", method = RequestMethod.POST)
    public JSONObject getAllSport(String json) {
        return recordService.getAllSport(json);
    }

    /**
     * 获取全部食物类型
     *
     * @author wenxian.cai
     * @DateTime 2016年8月20日上午10:23:37
     * @serverComment
     * @param
     */
    @RequestMapping(value = "getFoodType", method = RequestMethod.POST)
    public JSONObject getFoodType(String json) throws Exception {
        return recordService.getFoodType(json);
    }

    /**
     * 获取食物详细
     *
     * @author wenxian.cai
     * @DateTime 2016年8月20日上午11:00:34
     * @serverComment
     * @param
     */
    @RequestMapping(value = "getFood", method = RequestMethod.POST)
    public JSONObject getFood(String json) throws Exception {
        return recordService.getFood(json);
    }

    /**
     * 获取食物类型以及食物列表
     *
     * @author yuhang.weng
     * @DateTime 2016年10月25日 下午7:08:21
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getFoodTypeWithFoods", method = RequestMethod.POST)
    public JSONObject getFoodTypeWithFoods(String json) {
        return recordService.getFoodTypeWithFoods(json);
    }

    /**
     * 修改健康日记
     *
     * @author wenxian.cai
     * @DateTime 2016年8月19日上午9:57:32
     * @serverComment
     * @param
     */
    @RequestMapping(value = "modifyDiet", method = RequestMethod.POST)
    public JSONObject modifyDiet(String json) throws Exception {
        return recordService.modifyDiet(json);
    }

    /**
     * 删除健康日记
     *
     * @author wenxian.cai
     * @DateTime 2016年8月19日上午9:58:04
     * @serverComment
     * @param
     */
    @RequestMapping(value = "delDiet", method = RequestMethod.POST)
    public JSONObject delDiet(String json) throws Exception {
        return recordService.delDiet(json);
    }
    
    /**
     *  健康档案红点
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 上午11:50:44
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getRecordRedPoint", method = RequestMethod.POST)
    public JSONObject getRecordRedPoint(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        
        Map<String, Object> returnData = new HashMap<>();
        int unReadPAnalysis = physicalAnalysisService.countUnReadAnalysis(userId);
        int physicalRedPoint = 0;
        if (unReadPAnalysis > 0) {
            physicalRedPoint = 1;
        }
        returnData.put("physical", physicalRedPoint);
        
        int unReadMAnalysis = measureAnalysisService.countUnReadAnalysis(userId);
        int measureRedPoint = 0;
        if (unReadMAnalysis > 0) {
            measureRedPoint = 1;
        }
        returnData.put("measure", measureRedPoint);
        
        return AppNormalService.success(returnData);
    }
    
    /**
     * 获取会员一个月内有数据记录的日期
     *
     * @param json: {
     *              "type":"APP_A",
     *              "ver":"1",
     *              "user":"12345678966666",
     *              "data":{
     *              "userId":"", //会员ID
     *              "queryDate":"2017-05-01", //查询月的第一天
     *              "queryType":"1" //查询类型: 1->健康数据,2->体检报告(不可用),3->病例记录(不可用),4->饮食记录
     *              },
     *              "token":"token",
     *              "timestamp":""
     *              }
     * @return JSONObject
     * @author wuj
     * @since 2017-08-01 14:13:16
     */
    @RequestMapping(value = "queryDatesWithData", method = RequestMethod.POST)
    public JSONObject queryDatesWithData(@RequestParam String json) throws Exception {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData().getFirstJSONObject();

        Integer userId = data.getInteger("userId");
        String queryDate = data.getString("queryDate");
        Integer queryType = data.getInteger("queryType");

        if (userId == null) {
            userId = appJSON.getData().getUserId();
        }
        if (userId == null || queryDate == null || queryType == null) {
            return AppNormalService.error("参数丢失", ErrorCodeEnum.MISSING);
        }

        List<Map<String, Object>> returnList = new ArrayList<>();
        Map<String, Integer> resultMap = rService.queryDatesWithData(userId, queryDate, queryType);

        for (Map.Entry<String, Integer> en : resultMap.entrySet()) {
            Map<String, Object> temp = new HashMap<>();

            Integer value = en.getValue();
            String key = en.getKey();

            temp.put("day", key);
            temp.put("status", value);

            returnList.add(temp);
        }

        return AppNormalService.success(returnList);
    }
}
