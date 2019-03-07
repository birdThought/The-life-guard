package com.lifeshs.controller.record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.data.TDataDepartment;
import com.lifeshs.entity.record.TRecordMedicalCourse;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.record.MedicalCaseVO;
import com.lifeshs.service.record.IMedicalService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.MapComparator;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 *  病历控制器
 *  @author yuhang.weng  
 *  @DateTime 2016年8月12日 上午9:25:01
 */
@Controller
@RequestMapping("recordHealthFileControl")
public class RecordHealthFileController extends BaseController{

    private static final Logger logger = Logger.getLogger(RecordHealthFileController.class);
    
    @Autowired
    private IMedicalService medicalService;
    
    /**
     *  个人病历入口
     *  @author yuhang.weng 
     *  @DateTime 2016年8月12日 上午9:26:36
     *
     *  @return
     */
    @RequestMapping(params = "enter", method = RequestMethod.GET)
    public String enter(Model model) {
        
        LoginUser loginUser = getLoginUser();
        int userId = loginUser.getId();
        
        PaginationDTO pagination = medicalService.getMedicalByUserIdPageSplit(userId, 1, 5);
        
        model.addAttribute("p", pagination);
        
        return "com/lifeshs/member/healthFile";
    }
    
    /**
     *  添加病历入口
     *  @author yuhang.weng 
     *  @DateTime 2016年8月16日 下午3:45:02
     *
     *  @return
     */
    @RequestMapping(params = "add", method = RequestMethod.GET)
    public String add() {
        return "com/lifeshs/member/addCase";
    }
    
    /**
     *  分页获取个人病历（健康档案）
     *  @author yuhang.weng 
     *  @DateTime 2016年8月12日 上午9:42:33
     *
     *  @param nowPage 当前页码
     *  @param pageSize 页面大小
     *  @return
     */
    @RequestMapping(params = "healthFile", method = RequestMethod.GET)
    public @ResponseBody AjaxJson healthFile(@RequestParam Integer nowPage, @RequestParam Integer pageSize) {
        AjaxJson resObject = new AjaxJson();
        
        LoginUser loginUser = getLoginUser();
        int userId = loginUser.getId();
        
        PaginationDTO pagination = medicalService.getMedicalByUserIdPageSplit(userId, nowPage, pageSize);
        
        resObject.setObj(pagination);
        resObject.setSuccess(true);
        resObject.setMsg("获取病例数据成功");
        return resObject;
    }
    
    /**
     *  浏览病历详情
     *  @author yuhang.weng 
     *  @DateTime 2016年8月16日 上午10:10:39
     *
     *  @param id
     *  @param model
     *  @return
     */
    @RequestMapping(params = "healthFileDetail", method = RequestMethod.GET)
    public String healthFileDetail(@RequestParam Integer id, Model model) {
        
        LoginUser user = getLoginUser();
        
        Map<String, Object> data = getHealthCaseData(id, user.getId());
        
        model.addAttribute("data", data);
        
        return "com/lifeshs/member/lookHealthCase";
    }
    
    /**
     *  编辑病历
     *  @author yuhang.weng 
     *  @DateTime 2016年8月23日 下午1:33:15
     *
     *  @param medicalId
     *  @param model
     *  @return
     */
    @RequestMapping(params = "editMedical", method = RequestMethod.GET)
    public String editMedical(@RequestParam Integer medicalId, Model model) {
        
        LoginUser user = getLoginUser();
        
        Map<String, Object> data = getHealthCaseData(medicalId, user.getId());
        
        model.addAttribute("data", data);
        
        List<TDataDepartment> departments = medicalService.selectDepartmentsByParentId(0);
        model.addAttribute("parents", departments);
        
        return "com/lifeshs/member/editCase";
    }
    
    /**
     *  获取父类(逐层向上查找)部门信息
     *  @author yuhang.weng 
     *  @DateTime 2016年8月23日 下午1:41:14
     *
     *  @param childDepartmentId
     *  @return
     */
    @RequestMapping(params = "getParentsDepartment", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getParentsDepartment(@RequestParam Integer childDepartmentId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(true);
        String msg = "成功获取部门信息";
        
        List<TDataDepartment> departments = medicalService.selectParentsDepartmentByChildId(childDepartmentId);
        resObject.setObj(departments);
        resObject.setMsg(msg);
        return resObject;
    }
    
    @RequestMapping(params = "getCoursesData", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getCoursesData(@RequestParam Integer medicalId) {
        AjaxJson resObject = new AjaxJson();
        
        List<Map<String, Object>> courses = medicalService.selectMedicalCoursesListByMedicalId(medicalId);
        
        List<Map<String, Object>> obj = new ArrayList<>();
        
        for(int i = 0; i < courses.size(); i ++) {
            Map<String, Object> map = new HashMap<>();
            
            String visitingDate = DateTimeUtilT.date((Date)(courses.get(i).get("visitingDate")));
            map.put("id", courses.get(i).get("id"));
            map.put("visitingDate", visitingDate);
            map.put("courseType", courseDescToTypeNumber((String)courses.get(i).get("courseType")));
            map.put("remark", courses.get(i).get("remark"));
            
            List<String> imgs = new ArrayList<>();
            String img1 = (String)courses.get(i).get("img1");
            String img2 = (String)courses.get(i).get("img2");
            String img3 = (String)courses.get(i).get("img3");
            if(StringUtils.isNotBlank(img1)) {
                imgs.add(img1);
            }
            if(StringUtils.isNotBlank(img2)) {
                imgs.add(img2);
            }
            if(StringUtils.isNotBlank(img3)) {
                imgs.add(img3);
            }
            
            map.put("imgs", imgs.toArray());
            
            obj.add(map);
        }
        
        resObject.setSuccess(true);
        resObject.setObj(obj);
        
        return resObject;
    }
    
    @RequestMapping(params = "updateMedical", method = RequestMethod.POST)
    public @ResponseBody AjaxJson updateMedical(@RequestBody MedicalCaseVO medicalCaseVO) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "修改病历失败";
        
        List<TRecordMedicalCourse> courses = medicalCaseVO.getCourses();
        
        if(courses != null && courses.size() > 0) {
            for(TRecordMedicalCourse course : courses) {
                String img1 = course.getImg1();
                String img2 = course.getImg2();
                String img3 = course.getImg3();
                
                String newRelativePath = "";
                
                if(StringUtils.isNotBlank(img1)) {
                    // 移动图片位置
                    try {
                        newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(img1, "record");
                    } catch (IOException e) {
                        resObject.setMsg("病程图片保存失败");
                    }
                    course.setImg1(newRelativePath);
                }
                if(StringUtils.isNotBlank(img2)) {
                    // 移动图片位置
                    try {
                        newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(img2, "record");
                    } catch (IOException e) {
                        resObject.setMsg("病程图片保存失败");
                    }
                    course.setImg2(newRelativePath);
                }
                if(StringUtils.isNotBlank(img3)) {
                    // 移动图片位置
                    try {
                        newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(img3, "record");
                    } catch (IOException e) {
                        resObject.setMsg("病程图片保存失败");
                    }
                    course.setImg3(newRelativePath);
                }
                
                if(course.getId() != 0) {
                    /**
                     * 删除规则
                     * 数据库里保存的图片路径（原路径）不能为空，避免空删除
                     * 新保存的图片路径（新路径）如果跟原路径不同，就把原路径的图片删除
                     */
                    TRecordMedicalCourse courseEntity = medicalService.selectMedicalCourseById(course.getId());
                    // 删除原图1
                    if(StringUtils.isNotBlank(courseEntity.getImg1()) && !course.getImg1().equals(courseEntity.getImg1())) {
                        ImageUtilV2.delImg(courseEntity.getImg1());
                    }
                    // 删除原图2
                    if(StringUtils.isNotBlank(courseEntity.getImg2()) && !course.getImg2().equals(courseEntity.getImg2())) {
                        ImageUtilV2.delImg(courseEntity.getImg2());
                    }
                    // 删除原图3
                    if(StringUtils.isNotBlank(courseEntity.getImg3()) && !course.getImg3().equals(courseEntity.getImg3())) {
                        ImageUtilV2.delImg(courseEntity.getImg3());
                    }
                }
            }
        }
        
        try {
            if(!medicalService.updateMedicalAndCourses(medicalCaseVO)) {
                resObject.setMsg(msg);
                return resObject;
            }
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());
            return resObject;
        }
        resObject.setSuccess(true);
        resObject.setMsg("修改病历成功");
        return resObject;
    }
    
    /**
     *  删除病历
     *  @author yuhang.weng 
     *  @DateTime 2016年8月16日 下午2:18:05
     *
     *  @param medicalId
     *  @return
     */
    @RequestMapping(params = "deleteMedical", method = RequestMethod.POST)
    public @ResponseBody AjaxJson deleteMedical(@RequestParam Integer medicalId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "删除病历失败";
        
        LoginUser user = getLoginUser();
        
        try {
            if(!medicalService.deleteMedical(user.getId(), medicalId)) {
                resObject.setMsg(msg);
                return resObject;
            }
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());
            return resObject;
        }
        resObject.setSuccess(true);
        resObject.setMsg("删除成功");
        return resObject;
    }
    
    
    
    /**
     *  获取子类部门
     *  @author yuhang.weng 
     *  @DateTime 2016年8月16日 下午4:05:39
     *
     *  @param parentId
     *  @return
     */
    @RequestMapping(params = "getChildDepartments", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getChildDepartments(@RequestParam Integer parentId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(true);
        
        List<TDataDepartment> departments = medicalService.selectDepartmentsByParentId(parentId);
        resObject.setObj(departments);
        return resObject;
    }
    
    @RequestMapping(params = "getUserMessage", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getUserMessage() {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(true);
        
        LoginUser user = getLoginUser();
        
        Map<String, Object> userMessage = getUserMessage(user.getId());
        
        resObject.setMsg("获取信息成功");
        resObject.setObj(userMessage);
        
        return resObject;
    }
    
    @RequestMapping(params = "uploadImgs", method = RequestMethod.POST)
    public @ResponseBody AjaxJson uploadImgs(@RequestParam(value="files") MultipartFile[] files) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "";
        
        int imgSuccessSaveNumber = 0;
        List<String> relativePaths = new ArrayList<>();
        
        for (MultipartFile file : files) {
            int imgNumber = 1;
            if(!file.isEmpty()) {
                String netPath = "";
                String message = "上传图片失败";
                
                if(file.getSize() > 0) {
                    try {
                        if(file.getSize() > 200 * 1024) {
                            netPath = ImageUtilV2.saveByte(file.getBytes(), "", true, 800, 800);
                        } else {
                            netPath = ImageUtilV2.saveByte(file.getBytes(), "", true);
                        }
                    } catch (IOException e) {
                        logger.error(message + e.getMessage());
                        resObject.setMsg(message + ":" + e.getMessage());
                    }
                    relativePaths.add(netPath);
                    
                    imgNumber ++;
                    imgSuccessSaveNumber ++;
                } else {
                    msg += "文件"+imgNumber+"大小不正确<br>";
                }
            } else {
                msg += "文件"+imgNumber+"数据传输失败<br>";
            }
        }
        
        if(files.length == imgSuccessSaveNumber) {
            resObject.setSuccess(true);
            resObject.setMsg("图片上传成功");
            resObject.setObj(relativePaths);
            return resObject;
        }
        
        resObject.setMsg(msg);
        return resObject;
    }
    
    @RequestMapping(params = "saveCase", method = RequestMethod.POST)
    public @ResponseBody AjaxJson saveCase(@RequestBody MedicalCaseVO medicalCaseVO) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "保存病历失败";
        
        LoginUser user = getLoginUser();
        Integer userId = user.getId();
        
        List<TRecordMedicalCourse> courses = medicalCaseVO.getCourses();
        
//      List<TRecordMedicalCourse> coursesTmp = 
        
        
        if(courses != null && courses.size() > 0) {
            for(TRecordMedicalCourse course : courses) {
                String img1 = course.getImg1();
                String img2 = course.getImg2();
                String img3 = course.getImg3();
                
                String newRelativePath = "";
                
                if(StringUtils.isNotBlank(img1)) {
                    // 移动图片位置
                    try {
                        newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(img1, "record");
                    } catch (IOException e) {
                        resObject.setMsg("病程图片保存失败");
                    }
                    course.setImg1(newRelativePath);
                }
                if(StringUtils.isNotBlank(img2)) {
                    // 移动图片位置
                    try {
                        newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(img2, "record");
                    } catch (IOException e) {
                        resObject.setMsg("病程图片保存失败");
                    }
                    course.setImg2(newRelativePath);
                }
                if(StringUtils.isNotBlank(img3)) {
                    // 移动图片位置
                    try {
                        newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(img3, "record");
                    } catch (IOException e) {
                        resObject.setMsg("病程图片保存失败");
                    }
                    course.setImg3(newRelativePath);
                }
            }
        }
        
        try {
            if(!medicalService.addMedical(userId, medicalCaseVO)) {
                resObject.setMsg(msg);
                return resObject;
            }
        } catch (Exception e) {
            logger.error(msg+":"+e.getMessage());
            resObject.setMsg(msg+":"+e.getMessage());
            return resObject;
        }
        resObject.setSuccess(true);
        resObject.setMsg("保存成功");
        return resObject;
    }
    
    /**
     *  获取病例数据
     *  @author yuhang.weng 
     *  @DateTime 2016年8月16日 上午10:11:05
     *
     *  @param medicalId
     *  @param user
     *  @return
     */
    private Map<String, Object> getHealthCaseData(Integer medicalId, Integer userId) {
        
        Map<String, Object> userMessage = getUserMessage(userId);
        
        Map<String, Object> medical = getMedicalData(medicalId, userId);
        
        List<Map<String, Object>> courses = getMedicalCoursesData(medicalId);
        
        Map<String, Object> data = new HashMap<>();
        
        data.putAll(userMessage);
        data.putAll(medical);
        data.put("courses", courses);
        
        return data;
    }
    
    /**
     *  获取健康档案-病历封装数据
     *  @author yuhang.weng 
     *  @DateTime 2016年8月16日 上午10:33:48
     *
     *  @param medicalId
     *  @param userId
     *  @return
     */
    private Map<String, Object> getMedicalData(Integer medicalId, Integer userId) {
        Map<String, Object> medical = medicalService.selectMedicalByMedicalIdAndUserId(medicalId, userId);
        
        Map<String, Object> data = new HashMap<>();
        if(medical != null){
            data.put("id", medical.get("id"));
            data.put("title", medical.get("title"));
            data.put("departmentName", ((TDataDepartment)medical.get("department")).getName());
            data.put("departmentId", medical.get("departmentId"));
            data.put("date", medical.get("visitingDate"));
            data.put("diagnosis", medical.get("doctorDiagnosis"));
            data.put("basicCondition", medical.get("basicCondition"));
            data.put("hospital", medical.get("hospital"));
        }
        
        return data;
    }
    
    /**
     *  获取健康档案-病程封装数据
     *  @author yuhang.weng 
     *  @DateTime 2016年8月16日 上午10:34:21
     *
     *  @param medicalId
     *  @return
     */
    private List<Map<String, Object>> getMedicalCoursesData(Integer medicalId) {
        List<Map<String, Object>> courses = medicalService.selectMedicalCoursesListByMedicalId(medicalId);
        
        List<Map<String, Object>> data = new ArrayList<>();
        
        if(courses != null && courses.size() > 0) {
            for (Map<String, Object> course : courses) {
                
                /** 类型号码 */
                Integer typeNumber = 0;
                
                typeNumber = courseDescToTypeNumber((String)course.get("courseType"));
                
                if(typeNumber.intValue() != 0) {
                    course.put("typeNumber", typeNumber);
                    course.remove("courseType");
                    course.remove("createDate");
                    data.add(course);
                }
            }
            
            // jdk1.8
            //data.sort(new MapComparator("typeNumber"));
            Collections.sort(data, new MapComparator("typeNumber"));
        }
        
        return data;
    }
    
    private Map<String, Object> getUserMessage(Integer userId) {
        Map<String, Object> data = new HashMap<>();
        
        
        MemberSharingData sharingData = getCacheMemberSharingData(userId);
        
        
        String name = sharingData.getRealName();
        String sex = "尚未设置性别";
        if(sharingData.getSex() != null) {
            sex = sharingData.getSex();
        }
        
        Date birthday = sharingData.getBirthday();
        String birthday_s = "尚未设置出生日期" ;
        Integer age = 0;
        
        if(birthday != null) {
            birthday_s = DateTimeUtilT.date(birthday);
            age = DateTimeUtilT.calculateAge(birthday);
        }
        
        data.put("name", name);
        data.put("sex", sex);
        data.put("birthday", birthday_s);
        data.put("age", age);
        
        return data;
    }
    
    private Integer courseDescToTypeNumber(String courseDesc) {
        /** 类型号码 */
        Integer typeNumber = 0;
        
        if(courseDesc.equals("首诊")){
            typeNumber = 1;
        }
        if(courseDesc.equals("复诊")){
            typeNumber = 2;
        }
        if(courseDesc.equals("出院")){
            typeNumber = 3;
        }
        if(courseDesc.equals("晚期")){
            typeNumber = 4;
        }
        return typeNumber;
    }
}
