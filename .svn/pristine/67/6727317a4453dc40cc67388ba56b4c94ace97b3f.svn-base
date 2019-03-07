package com.lifeshs.app.api.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.lifeshs.app.api.common.AppBaseController;
import com.lifeshs.app.api.store.ReturnDataAgent;
import com.lifeshs.common.business.Conversion;
import com.lifeshs.common.constants.app.Diet;
import com.lifeshs.common.constants.app.DietFood;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.app.HuanXin;
import com.lifeshs.common.constants.app.Medical;
import com.lifeshs.common.constants.app.MedicalCourse;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.app.Org;
import com.lifeshs.common.constants.app.OrgServe;
import com.lifeshs.common.constants.app.OrgServeGroup;
import com.lifeshs.common.constants.app.OrgServeGroupUser;
import com.lifeshs.common.constants.app.OrgServeUser;
import com.lifeshs.common.constants.app.OrgUser;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.PhysicalRecord;
import com.lifeshs.common.constants.app.Serve;
import com.lifeshs.common.constants.app.Terminal;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.healthPackage.HealthPackage;
import com.lifeshs.common.constants.app.record.MedicalCourseImg;
import com.lifeshs.common.constants.app.record.PhysicalImg;
import com.lifeshs.common.constants.common.BaseDefine;
import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.dao1.measure.UserInfoReadDao;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.entity.org.TOrgGroup;
import com.lifeshs.entity.org.TOrgGroupOrguser;
import com.lifeshs.entity.org.TOrgServe;
import com.lifeshs.entity.org.TServe;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.po.ConsultPO;
import com.lifeshs.po.LessonPO;
import com.lifeshs.po.UserInfoRead;
import com.lifeshs.po.VisitPO;
import com.lifeshs.po.record.MedicalCourseImgPO;
import com.lifeshs.po.record.PhysicalImgPO;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.app.manager.MAppJSON;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.order.UserOrderDTO;
import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.employee.OrgEmploy;
import com.lifeshs.pojo.org.group.CourseTimeDTO;
import com.lifeshs.pojo.org.group.LessonDTO;
import com.lifeshs.pojo.org.group.LessonGroupOrgUserDTO;
import com.lifeshs.pojo.org.server.OrgMemberBase;
import com.lifeshs.pojo.org.server.OrgMemberMessageDetailVO;
import com.lifeshs.pojo.org.server.OrgServeRecord;
import com.lifeshs.pojo.org.server.OrgServer;
import com.lifeshs.pojo.org.server.OrgServerGroupBase;
import com.lifeshs.pojo.org.service.ConsultServiceDTO;
import com.lifeshs.pojo.org.service.LessonServiceDTO;
import com.lifeshs.pojo.org.service.OrgServiceDTO;
import com.lifeshs.pojo.org.service.ServeTypeDTO;
import com.lifeshs.pojo.org.service.ServiceComboDTO;
import com.lifeshs.pojo.org.service.ServiceOrgUserRelationDTO;
import com.lifeshs.pojo.org.service.VisitServiceDTO;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service.org.IServiceOrgService;
import com.lifeshs.service.org.employee.IEmployeeManageService;
import com.lifeshs.service.org.lesson.ILessonGroup;
import com.lifeshs.service.org.service.IOrgServiceManage;
import com.lifeshs.service.record.IRecordService;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service1.record.MedicalService;
import com.lifeshs.service1.record.PhysicalService;
import com.lifeshs.service1.serve.ProjectService;
import com.lifeshs.service1.serve.consult.ConsultService;
import com.lifeshs.service1.serve.lesson.LessonService;
import com.lifeshs.service1.serve.visit.VisitService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.ListUtil;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.ReflectUtils;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.utils.image.ImageUtilV2;
import com.lifeshs.vo.record.MedicalBasicVO;
import com.lifeshs.vo.record.MedicalCourseVO;
import com.lifeshs.vo.record.MedicalVO;
import com.lifeshs.vo.record.PhysicalVO;

/**
 * 管理app-服务
 *
 * @author yuhang.weng
 * @DateTime 2016年11月18日 下午2:38:31
 */
@RestController
@RequestMapping("mapp/serve")
public class MAppServeController extends AppBaseController {
    final static Integer PAGE_SIZE = 5;
    @Autowired
    IOrgServiceManage orgServiceManage;
    
    @Autowired
    private IEmployeeManageService employeeManageService;
    
    @Autowired
    private IServiceOrgService serviceOrgService;

    @Autowired
    private IRecordService recordService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ILessonGroup lessonGroupService;

    @Autowired
    UserInfoReadDao userInfoReadDao;
    
    @Autowired
    ConsultService consultService;
    
    @Autowired
    LessonService lessonService;
    
    @Autowired
    VisitService visitService;
    
    @Autowired
    ProjectService projectService;

    @Resource(name = "physicalService")
    private PhysicalService physicalService;
    
    @Resource(name = "medicalService")
    private MedicalService medicalService;
    
    
    /**
     * 
     *  获取服务
     *  @author liaoguo
     *  @DateTime 2018年8月13日 下午5:33:59
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getServiceManageList", method = RequestMethod.POST)
    public JSONObject getServiceManageList(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();
        // 获取本地订单号
        int pageIndex = data.getIntValue(Page.INDEX);
        int orgId = data.getIntValue(OrgServe.ORG_ID);
        int status = data.getIntValue(OrgServe.STATUS);
        int type = data.getIntValue(OrgServe.TYPE);
        String search = data.getString(OrgServe.SEARCH);
        
        List<OrgServiceDTO> orgServiceList = orgServiceManage.getServiceManageList(orgId, status, search, type, (pageIndex - 1) * PAGE_SIZE, PAGE_SIZE);
        return MAppNormalService.success(orgServiceList);
    }
    
    /**
     * 
     *  获取服务分类
     *  @author liaoguo
     *  @DateTime 2018年8月13日 下午5:33:59
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getServeTypeList", method = RequestMethod.POST)
    public JSONObject getServeTypeList(@RequestParam String json) {
        
        List<ServeTypeDTO> list = orgServiceManage.listServe();
        return MAppNormalService.success(list);
    }
    
    /**
     * 
     *  获取服务师
     *  @author liaoguo
     *  @DateTime 2018年8月17日 下午5:50:41
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getEmployeeList", method = RequestMethod.POST)
    public JSONObject getEmployeeList(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();
        
        Integer orgId = data.getIntValue(OrgServe.ORG_ID);
        Integer pageIndex = data.getIntValue(Page.INDEX);
        Integer pageSize = data.getIntValue(Page.SIZE);
        String realName = data.getString(OrgServe.REALNAME);
        
        List<EmployeeDTO> list = employeeManageService.getEmployeeList(orgId, realName, (pageIndex - 1) * PAGE_SIZE, pageSize);
        System.out.println("getEmployeeList:"+MAppNormalService.success(list));
        return MAppNormalService.success(list);
    }
    
    
    /**
     * 
     *  添加健康咨询服务
     *  @author liaoguo
     *  @DateTime 2018年8月13日 下午5:33:59
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = {"/addConsultService","/saveConsultService"}, method = RequestMethod.POST)
    public JSONObject addConsultService(SubmitDTO sumbitDTO, ConsultServiceDTO service) {
        
        ImageDTO image = null;
        String productImgPath = null;
        image = MAppNormalService.uploadPhoto(service.getImage(), null, "service", false);
        if (image.getUploadSuccess()) {
            productImgPath = image.getNetPath();
            service.setImage(productImgPath);
        }
        
        service.setOrgId(sumbitDTO.getUser().getOrgId());
        service.setProjectType(ProjectType.PROJECT_CONSULT.getValue());
        orgServiceManage.addConsultService(service);
        
        return MAppNormalService.success();
    }
    
    /**
     * 
     *  添加健康课堂服务
     *  @author liaoguo
     *  @DateTime 2018年8月13日 下午6:06:48
     *
     *  @param sumbitDTO
     *  @param service
     *  @return
     */
    @RequestMapping(value = {"/addLessonService","/saveLessonService"}, method = RequestMethod.POST)
    public JSONObject addLessonService(SubmitDTO sumbitDTO, LessonServiceDTO service) {
        
        ImageDTO image = null;
        String productImgPath = null;
        if (StringUtils.isNotBlank(service.getImage())) {
            image = MAppNormalService.uploadPhoto(service.getImage(), null, "service", false);
            if (image.getUploadSuccess()) {
                productImgPath = image.getNetPath();
                service.setImage(productImgPath);
            }
        }
        
        service.setOrgId(sumbitDTO.getUser().getOrgId());
        service.setCreatorId(sumbitDTO.getUser().getId());
        service.setProjectType(ProjectType.PROJECT_LESSON.getValue());
        orgServiceManage.addLessonService(service, service.getOrgUser().get(0).getUserCode()); //服务师的userCode
        
        return MAppNormalService.success();
    }
    
    /**
     * 
     *  添加到店线下服务
     *  @author liaoguo
     *  @DateTime 2018年8月13日 下午5:34:41
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = {"/addOfflineService","/saveOfflineService"}, method = RequestMethod.POST)
    public JSONObject addOfflineService(SubmitDTO sumbitDTO, VisitServiceDTO service) {
        
        try {
            service.setIntroduce(ImageUtilV2.regexPathFromHtml(StringUtil.filterHtml(service.getIntroduce())));

            ImageDTO image = null;
            String productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureOne())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureOne(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureOne(productImgPath);
                    //将第一张图片存为产品图片
                    service.setImage(productImgPath);
                }
            }

            image = null;
            productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureTwo())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureTwo(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureTwo(productImgPath);
                    if (service.getImage() == null) {
                        service.setImage(productImgPath);
                    }
                }
            }

            image = null;
            productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureThree())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureThree(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureThree(productImgPath);
                    if (service.getImage() == null) {
                        service.setImage(productImgPath);
                    }
                }
            }

            image = null;
            productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureFour())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureFour(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureFour(productImgPath);
                    if (service.getImage() == null) {
                        service.setImage(productImgPath);
                    }
                }
            }
        } catch (Exception e) {
            return MAppNormalService.error("发布线下服务：移动图片失败");
        }

        service.setOrgId(sumbitDTO.getUser().getOrgId());
        service.setProjectType(ProjectType.PROJECT_TOSTORE.getValue());
        orgServiceManage.addVisitService(service);
        
        return MAppNormalService.success();
    }
    
    /**
     * 
     *  添加上门服务-居家养老服务
     *  @author liaoguo
     *  @DateTime 2018年8月13日 下午5:34:21
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = {"/addVisitService","/saveVisitService"}, method = RequestMethod.POST)
    public JSONObject addVisitService(SubmitDTO sumbitDTO, VisitServiceDTO service) {
        
        try {
            service.setIntroduce(ImageUtilV2.regexPathFromHtml(StringUtil.filterHtml(service.getIntroduce())));
            
            ImageDTO image = null;
            String productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureOne())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureOne(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureOne(productImgPath);
                    //将第一张图片存为产品图片
                    service.setImage(productImgPath);
                }
            }
            
            image = null;
            productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureTwo())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureTwo(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureTwo(productImgPath);
                    if (service.getImage() == null) {
                        service.setImage(productImgPath);
                    }
                }
            }
            
            image = null;
            productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureThree())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureThree(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureThree(productImgPath);
                    if (service.getImage() == null) {
                        service.setImage(productImgPath);
                    }
                }
            }

            image = null;
            productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureFour())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureFour(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureFour(productImgPath);
                    if (service.getImage() == null) {
                        service.setImage(productImgPath);
                    }
                }
            }
        } catch (Exception e) {
            return MAppNormalService.error("发布线下服务：移动图片失败");
        }

        service.setOrgId(sumbitDTO.getUser().getOrgId());
        service.setProjectType(ProjectType.PROJECT_VISIT.getValue());
        orgServiceManage.addVisitService(service);
        
        return MAppNormalService.success();
    }
    
    
    /**
     * 
     *  跳转至修改页面需要的数据
     *  @author liaoguo
     *  @DateTime 2018年8月20日 上午10:28:32
     *
     *  @param sumbitDTO
     *  @param type 服务类型
     *  @param code 服务code
     *  @return
     */
    @RequestMapping(value =  {"/getConsultService", "/getVisitService", "/getOfflineService", "/getLessonService"}, method = RequestMethod.POST)
    public JSONObject modifyService(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();
        
        String code = data.getString(OrgServe.CODE);
        String type = data.getString(OrgServe.TYPE);
        if(StringUtils.isBlank(code)){
            return MAppNormalService.error("code不能为空!");
        }
        if(StringUtils.isBlank(type)){
            return MAppNormalService.error("type不能为空!");
        }
        TOrgUser user = appJSON.getAopData().getOrgUser();
        System.out.println(String.format("code:%s,type:%s,orgId:%s", code,type,user.getOrgId()));
        
        net.sf.json.JSONObject mediaJson = null;
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String, Object> maps = null;
        List<Object> listMap = new ArrayList<Object>();

        switch (type) {
        case "1": //健康咨询
            List<ConsultPO> consultList = new ArrayList<ConsultPO>();
            ConsultPO consultPO = consultService.getConsult(code);
            consultList.add(consultPO);
            map.put("project", consultList);
            
            //net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(consultPO);
            //map.put("project", jsonObject);
            map.put("projectType", "consult");

            break;
            
        case "3": //上门服务
            VisitServiceDTO service = orgServiceManage.getVisitService(user.getOrgId(), code);
            if (StringUtils.isNotBlank(service.getIntroduce())) {
                service.setIntroduce(service.getIntroduce().replace("\n", ""));
            }

            for(ServiceComboDTO c : service.getCombo()){
                maps = new HashMap<String, Object>();
                maps.put("id", c.getId());
                maps.put("projectCode", c.getProjectCode());
                maps.put("introduce", c.getIntroduce().replaceAll("\"", "\\\""));
                maps.put("price", c.getPrice());
                maps.put("name", c.getName());
                maps.put("marketPrice", c.getMarketPrice());
                net.sf.json.JSONObject comboJson =  net.sf.json.JSONObject.fromObject(maps);
                listMap.add(comboJson);
            }
            
            maps = new HashMap<String, Object>();
            maps.put("id", service.getId());
            maps.put("name", service.getName());
            maps.put("status", service.getStatus());
            maps.put("code", service.getCode());
            maps.put("image", service.getImage());
            maps.put("userType", service.getUserType());
            maps.put("appointment", service.getAppointment());
            maps.put("introduce", service.getIntroduce());
            maps.put("orgId", service.getOrgId());
            maps.put("serveId", service.getSerialId());
            mediaJson =  net.sf.json.JSONObject.fromObject(service.getMedia());
            maps.put("media", mediaJson);
            
            map.put("combo", listMap);
            map.put("project", maps);
            map.put("projectType", "visit");
            

            break;

        case "4": //健康课堂
            List<LessonPO> lessonList = new ArrayList<LessonPO>();
            LessonPO lessonPO = lessonService.getLesson(code);
            lessonPO.setLessonTime(lessonPO.getLessonTime().replaceAll("\"", "\\\""));
            lessonList.add(lessonPO);
            map.put("project", lessonList);
            map.put("projectType", "lesson");
            
            break;

        case "2": //到店服务
            
            VisitServiceDTO service1 = orgServiceManage.getVisitService(user.getOrgId(), code);
            if (StringUtils.isNotBlank(service1.getIntroduce())) {
                service1.setIntroduce(service1.getIntroduce().replace("\n", ""));
            }

            for(ServiceComboDTO c : service1.getCombo()){
                maps = new HashMap<String, Object>();
                maps.put("id", c.getId());
                maps.put("projectCode", c.getProjectCode());
                maps.put("introduce", c.getIntroduce().replaceAll("\"", "\\\""));
                maps.put("price", c.getPrice());
                maps.put("name", c.getName());
                maps.put("marketPrice", c.getMarketPrice());
                net.sf.json.JSONObject comboJson =  net.sf.json.JSONObject.fromObject(maps);
                listMap.add(comboJson);
            }
            
            maps = new HashMap<String, Object>();
            maps.put("id", service1.getId());
            maps.put("name", service1.getName());
            maps.put("status", service1.getStatus());
            maps.put("code", service1.getCode());
            maps.put("image", service1.getImage());
            maps.put("userType", service1.getUserType());
            maps.put("appointment", service1.getAppointment());
            maps.put("introduce", service1.getIntroduce());
            maps.put("orgId", service1.getOrgId());
            maps.put("serveId", service1.getSerialId());
            
            mediaJson =  net.sf.json.JSONObject.fromObject(service1.getMedia());
            maps.put("media", mediaJson);
            
            map.put("combo", listMap);
            map.put("project", maps);
            map.put("projectType", "offline");
            
            break;
        default:
            map.put("project", "type有误!");

            break;
        }

//        List<ServeTypeDTO> list = orgServiceManage.listServe();
        List<ServiceOrgUserRelationDTO> orgUsers = projectService.findServiceOrgUserRelationList(code);
        
        map.put("type", type);
        map.put("isModify", true);
//        map.put("serveType", list);
        map.put("orgUsers", orgUsers);
        
        System.out.println(map.get("projectType")+"服务:"+MAppNormalService.success(map));
        return MAppNormalService.success(map);
        
    }
    
    
    /**
     * @Description: 改变服务项目状态
     * @Author: liaoguo
     * @Date: 2018/8/20 11:11
     */
    @RequestMapping(value = "/updateServiceStatus")
    public JSONObject updateServiceStatus(@RequestParam String json) {
        //@RequestParam(value = "type") Integer type, @RequestParam(value = "code") String code, @RequestParam(value = "status") Integer status
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();
        String code = data.getString(OrgServe.CODE);
        Integer status = data.getIntValue(OrgServe.STATUS);
        Integer type = data.getIntValue(OrgServe.TYPE);
        ProjectType projectType = ProjectType.values()[type];

        if (ProjectType.PROJECT_CONSULT == projectType) {
            orgServiceManage.updateConsultServiceStatus(code, status);
        }

        if (ProjectType.PROJECT_LESSON == projectType) {
            orgServiceManage.updateLessonServiceStatus(code, status);
        }

        if ((ProjectType.PROJECT_VISIT == projectType) || ProjectType.PROJECT_TOSTORE == projectType) {
            orgServiceManage.updateVisitServiceStatus(code, status);
        }

        return MAppNormalService.success();
    }
    
    //*********start*********
    
    /**
     * 更新课堂服务项目
     * @param service
     * @return
     */
    @RequestMapping(value = "updateLessonService", method = RequestMethod.POST)
    public JSONObject updateLessonService(SubmitDTO sumbitDTO, LessonServiceDTO service){
        
        TOrgUser user = sumbitDTO.getUser();
        
        ImageDTO image = null;
        String productImgPath = null;
        if (StringUtils.isNotBlank(service.getImage())) {
            image = MAppNormalService.uploadPhoto(service.getImage(), null, "service", false);
            if (image.getUploadSuccess()) {
                productImgPath = image.getNetPath();
                service.setImage(productImgPath);
            }
        }

        service.setOrgId(user.getOrgId());
        service.setProjectType(ProjectType.PROJECT_LESSON.getValue());

        LessonPO lesson = new LessonPO();
        lesson.setImage(service.getImage());
        lesson.setName(service.getName());
        lesson.setLessonTime(service.getLessonTime());
        lesson.setStartDate(service.getStartDate());
        lesson.setEndDate(service.getEndDate());
        lesson.setIntroduce(service.getIntroduce());
        lesson.setPrice(BigDecimal.valueOf(service.getPrice()));
        lesson.setCode(service.getCode());
        lesson.setServeId(service.getServeId());
        try {
            lessonService.updateLesson(lesson, service.getOrgUser());
        } catch (OperationException op) {
            return MAppNormalService.error("修改服务失败，请联系管理人员!");
        }

        return MAppNormalService.success();
    }

    /**
     * 更新咨询服务项目
     * @param service
     * @return
     */
    @RequestMapping(value = "updateConsultService")
    public JSONObject updateConsultService(SubmitDTO sumbitDTO, ConsultServiceDTO service) {
        TOrgUser user = sumbitDTO.getUser();

        // 将图片从tmp目录转移到upload下
        ImageDTO image = null;
        String productImgPath = null;
        if (StringUtils.isNotBlank(service.getImage())) {
            image = MAppNormalService.uploadPhoto(service.getImage(), null, "service", false);
            if (image.getUploadSuccess()) {
                productImgPath = image.getNetPath();
                service.setImage(productImgPath);
            }
        }

        service.setOrgId(user.getOrgId());
        service.setProjectType(ProjectType.PROJECT_CONSULT.getValue());

        ConsultPO consult = new ConsultPO();
        consult.setImage(service.getImage());
        consult.setName(service.getName());
        consult.setCode(service.getCode());
        consult.setServeId(service.getServeId());

        try {
            consultService.updateConsult(consult, service.getOrgUser());
        } catch (OperationException op) {
            return MAppNormalService.error("修改服务失败，请联系管理人员!");
        }

        return MAppNormalService.success();
    }

    /**
     * 更新线下、上门服务项目
     * @param service
     * @return
     */
    @RequestMapping(value =  {"/updateVisitService", "/updateOfflineService"})
    public JSONObject updateVisitService(SubmitDTO sumbitDTO, VisitServiceDTO service) {
        TOrgUser user = sumbitDTO.getUser();

        // 将图片从tmp目录转移到upload下
        try {
            service.setIntroduce(ImageUtilV2.regexPathFromHtml(StringUtil.filterHtml(service.getIntroduce())));
            
            ImageDTO image = null;
            String productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureOne())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureOne(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureOne(productImgPath);
                    //将第一张图片存为产品图片
                    service.setImage(productImgPath);
                }
            }
            
            image = null;
            productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureTwo())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureTwo(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureTwo(productImgPath);
                    if (service.getImage() == null) {
                        service.setImage(productImgPath);
                    }
                }
            }
            
            image = null;
            productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureThree())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureThree(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureThree(productImgPath);
                    if (service.getImage() == null) {
                        service.setImage(productImgPath);
                    }
                }
            }

            image = null;
            productImgPath = null;
            if (StringUtils.isNotBlank(service.getMedia().getPictureFour())) {
                image = MAppNormalService.uploadPhoto(service.getMedia().getPictureFour(), null, "service", false);
                if (image.getUploadSuccess()) {
                    productImgPath = image.getNetPath();
                    service.getMedia().setPictureFour(productImgPath);
                    if (service.getImage() == null) {
                        service.setImage(productImgPath);
                    }
                }
            }
            
        } catch (Exception e) {
            return MAppNormalService.error("更新线下、上门服务：移动图片失败!");
        }

        VisitPO visitPO = new VisitPO();
        visitPO.setImage(service.getImage());
        visitPO.setName(service.getName());
        visitPO.setCode(service.getCode());
        visitPO.setAppointment(service.getAppointment());
        visitPO.setUserType(service.getUserType());
        visitPO.setIntroduce(service.getIntroduce());
        visitPO.setServeId(service.getServeId());
        try {
            visitService.updateVisit(visitPO, service.getOrgUser(), service.getCombo(), service.getMedia());
        } catch (OperationException op) {
            return MAppNormalService.error("修改服务失败，请联系管理人员!");
        }

        return MAppNormalService.success();
    }
    
    //***********end***********
    
    
    /**
     * 得到服务师所属的服务
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月26日 上午10:34:44
     */
    @RequestMapping(value = "getServesByUser", method = RequestMethod.POST)
    public JSONObject getServesByUser(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser user = appJSON.getAopData().getOrgUser();

        int orgId = user.getOrgId();
        int userType = user.getUserType();
        Integer userId = userType == 1 ? user.getId() : null;
        List<OrgServer> orgServers = serviceOrgService.getOrgServiceListAndMemberCount(orgId, userId);

        // no-data
        // if (no-data)

        // Map<String, Object>
        List<Map<String, Object>> serves = new ArrayList<>();
        for (OrgServer orgServer : orgServers) {
            Map<String, Object> serve = new HashMap<>();
            serve.put(OrgServe.CLASSIFY, orgServer.getClassify());
            serve.put(OrgServe.ID, orgServer.getOrgServeId() + "");
            serve.put(OrgServe.NAME, orgServer.getServiceName());
            serve.put(OrgServe.MEMBER_COUNT, orgServer.getMemberCount() + "");

            String startDate = DateTimeUtilT.date(orgServer.getCreateDate());
            serve.put(OrgServe.CREATE_DATE, startDate);
            serve.put(OrgServe.IMAGE, orgServer.getImage());
            serve.put(Serve.CODE, orgServer.getCode());
            serve.put(OrgServeGroup.COUNT, orgServer.getGroupCount());

            serves.add(serve);
        }

        return MAppNormalService.success(serves);
    }

    /**
     * 得到服务所属群组
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月26日 下午3:23:28
     */
    @RequestMapping(value = "getGroupsByServe", method = RequestMethod.POST)
    public JSONObject getGroupsByServe(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser user = appJSON.getAopData().getOrgUser();

        JSONObject data = appJSON.getData();
        int userId = user.getId();
        int orgId = user.getOrgId();

        int orgServeId = data.getIntValue(OrgServe.ID);
        int isGetOther = data.getIntValue(Normal.IS_GET_OTHER);

        boolean addServers = isGetOther - 1 > 0;
        boolean addUsers = isGetOther - 2 > 0;

        List<Map<String, Object>> groups = new ArrayList<>();

        List<OrgServerGroupBase> serveGroupsBase = serviceOrgService.findGroupByKeyWithOrgServeId(orgId, orgServeId,
                user.getUserType() == 1 ? userId : null);

        for (OrgServerGroupBase serveGroupBase : serveGroupsBase) {
            int groupId = serveGroupBase.getId();

            Map<String, Object> group = new HashMap<>();
            group.put(OrgServeGroup.USER_COUNT, serveGroupBase.getMemberCount() + "");
            group.put(OrgServeGroup.ID, groupId + "");
            group.put(OrgServeGroup.NAME, serveGroupBase.getName());
            group.put(OrgServeGroup.CREATE_DATE, DateTimeUtilT.date(serveGroupBase.getCreateDate()));

            if (addUsers) {
                // add users
                List<Map<String, Object>> members = getAllUserInGroup(groupId);
                group.put(User.USERS, members);
            }

            if (addServers) {
                // add org project users
                List<Map<String, Object>> serveUsers = getAllServeUserInGroup(groupId);
                group.put(OrgServeUser.USERS, serveUsers);
            }

            groups.add(group);
        }

        return MAppNormalService.success(groups);
    }

    /**
     * 得到单个群组
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月26日 下午3:25:06
     */
    @RequestMapping(value = "getGroup", method = RequestMethod.POST)
    public JSONObject getGroup(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        int groupId = data.getIntValue(OrgServeGroup.ID);
        int isGetOther = data.getIntValue(Normal.IS_GET_OTHER);

        boolean addGroup = isGetOther - 1 > 0;
        boolean addServe = isGetOther - 2 > 0;
        boolean addOrg = isGetOther - 3 > 0;

        Map<String, Object> returnData = new HashMap<>();

        List<Map<String, Object>> users = getAllUserInGroup(groupId);
        returnData.put(User.USERS, users);

        if (addGroup) {
            List<Map<String, Object>> serveUsers = getAllServeUserInGroup(groupId);
            Map<String, Object> serveGroup = getGroupData(groupId);
            serveGroup.put(OrgServeUser.USERS, serveUsers);

            returnData.put(OrgServeGroup.GROUP, serveGroup);
        }

        Map<String, String> serveAndOrg = null;

        if (addServe) {
            serveAndOrg = getServeAndOrgInfo(groupId);

            Map<String, String> serve = new LinkedHashMap<>();
            serve.put(OrgServe.ABOUT, serveAndOrg.get("about_os"));
            serve.put(Serve.IMAGE, serveAndOrg.get("image"));
            serve.put(OrgServe.ID, serveAndOrg.get("orgServeId"));
            serve.put(Serve.NAME, serveAndOrg.get("name"));

            returnData.put(OrgServe.SERVE, serve);
        }

        if (addOrg) {
            Map<String, String> org = new LinkedHashMap<>();
            org.put(Org.ABOUT, serveAndOrg.get("about_o"));
            org.put(Org.LOGO, serveAndOrg.get("logo"));
            org.put(Org.ID, serveAndOrg.get("orgId"));
            org.put(Org.NAME, serveAndOrg.get("orgName"));

            returnData.put(Org.ORG, org);
        }

        return MAppNormalService.success(returnData);
    }

    /**
     * 获取群组下的会员信息
     *
     * @param groupId
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月26日 下午4:05:16
     */
    private List<Map<String, Object>> getAllUserInGroup(int groupId) {
        List<Map<String, Object>> members = new ArrayList<>();

        List<OrgMemberBase> orgMembersBase = serviceOrgService.findAllUserInGroupWithoutPageSplit(groupId);
        for (OrgMemberBase orgMemberBase : orgMembersBase) {
            Map<String, Object> member = new LinkedHashMap<>();

            String endDate = "";
            String daysRemaining = "";
            String timesRemaining = "";
            boolean sex = BaseDefine.SEX;

            Date e = orgMemberBase.getEndDate();
            if (e != null) {
                endDate = DateTimeUtilT.date(e);
                daysRemaining = DateTimeUtilT.calculateDayInterval(new Date(), e) + "";
            }
            Integer t = orgMemberBase.getTimesRemaining();
            if (t != null) {
                timesRemaining = t + "";
            }
            Boolean s = orgMemberBase.getSex();
            if (s != null) {
                sex = s.booleanValue();
            }
            int hasWarning = 0;
            if (orgMemberBase.getHasWarning() != null) {
                hasWarning = orgMemberBase.getHasWarning().intValue();
            }

            member.put(User.ID, orgMemberBase.getId());
            member.put(User.CODE, orgMemberBase.getHxId());
            member.put(User.PHOTO, orgMemberBase.getPhoto());
            member.put(Order.HAS_WARNING, hasWarning);
            member.put(User.REALNAME, orgMemberBase.getRealName());
            member.put(Order.END_DATE, endDate);
            member.put(Order.TIMES_REMAINING, timesRemaining);
            member.put(User.SEX, sex);
            member.put(Order.CHARGE_MODE, orgMemberBase.getChargeMode());
            member.put(Order.DAYS_REMAINING, daysRemaining);

            members.add(member);
        }

        return members;
    }

    private Map<String, Object> getGroupData(int groupId) {
        Map<String, Object> serveGroup = new HashMap<>();

        OrgServerGroupBase group = serviceOrgService.getOrgGroupBaseInfo(groupId);
        String createDate_s = "";
        Date createDate = group.getCreateDate();
        if (createDate != null) {
            createDate_s = DateTimeUtilT.date(createDate);
        }

        serveGroup.put(OrgServeGroup.USER_COUNT, group.getMemberCount() + "");
        serveGroup.put(OrgServeGroup.ID, groupId + "");
        serveGroup.put(OrgServeGroup.NAME, group.getName());
        serveGroup.put(OrgServeGroup.CREATE_DATE, createDate_s);

        return serveGroup;
    }

    /**
     * 获取群组下的服务师信息
     *
     * @param groupId
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月26日 下午4:05:33
     */
    private List<Map<String, Object>> getAllServeUserInGroup(int groupId) {

        List<Map<String, Object>> serveUsers_tmp = new ArrayList<>();

        List<Map<String, Object>> serveUsers = serviceOrgService.getControlGroupServer(groupId);
        for (Map<String, Object> serveUser : serveUsers) {
            Map<String, Object> serveUser_tmp = new HashMap<>();

            int serveUser_userId = (int) serveUser.get("id");
            String serveUser_realName = (String) serveUser.get("realName");
            String serveUser_photo = (String) serveUser.get("photo");
            Boolean serveUser_sex = (Boolean) serveUser.get("sex");
            String serveUser_groupNames = (String) serveUser.get("groupNames");

            serveUser_tmp.put(OrgUser.PHOTO, serveUser_photo);
            serveUser_tmp.put(OrgUser.NAME, serveUser_realName);
            serveUser_tmp.put(OrgUser.ID, serveUser_userId);
            serveUser_tmp.put(OrgUser.SEX, serveUser_sex);
            serveUser_tmp.put(OrgServeGroup.NAMES, serveUser_groupNames);

            serveUsers_tmp.add(serveUser_tmp);
        }

        return serveUsers_tmp;
    }

    private Map<String, String> getServeAndOrgInfo(int groupId) {
        Map<String, String> serveAndOrg = new HashMap<>();

        Map<String, Object> serveAndOrgInfo = serviceOrgService.getServeAndOrgInfoByGroupId(groupId);

        String orgServeId = "";
        String name = "";
        String image = "";
        String about_os = "";
        String orgId = "";
        String orgName = "";
        String logo = "";
        String about_o = "";

        if (serveAndOrgInfo != null && !serveAndOrgInfo.isEmpty()) {
            image = (String) serveAndOrgInfo.get("image");
            logo = (String) serveAndOrgInfo.get("logo");

            orgServeId = serveAndOrgInfo.get("orgServeId") + "";
            name = serveAndOrgInfo.get("name") + "";
            about_os = serveAndOrgInfo.get("orgServeAbout") + "";

            orgId = serveAndOrgInfo.get("orgId") + "";
            orgName = serveAndOrgInfo.get("orgName") + "";
            about_o = serveAndOrgInfo.get("orgAbout") + "";
        }

        serveAndOrg.put("orgServeId", orgServeId);
        serveAndOrg.put("name", name);
        serveAndOrg.put("image", image);
        serveAndOrg.put("about_os", about_os);

        serveAndOrg.put("orgId", orgId);
        serveAndOrg.put("orgName", orgName);
        serveAndOrg.put("logo", logo);
        serveAndOrg.put("about_o", about_o);

        return serveAndOrg;
    }

    /**
     * 获取指定用户的病历列表
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月30日 上午11:39:33
     */
    @RequestMapping(value = "getMedicalList", method = RequestMethod.POST)
    public JSONObject getMedicalList(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        int userId = data.getIntValue(User.ID);
        int pageIndex = data.getIntValue(Page.INDEX);
        int pageSize = data.getIntValue(Page.SIZE);

        UserDTO user = memberService.getUser(userId);
        String name = user.getRealName();
        UserRecordDTO record = user.getRecordDTO();
        int age = 0;
        if (record.getBirthday() != null) {
            age = DateTimeUtilT.calculateAge(record.getBirthday());
        }
        
        List<MedicalBasicVO> medicalList = medicalService.listMedicalBasic(userId, pageIndex, pageSize).getData();
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (MedicalBasicVO medical : medicalList) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put(Medical.ID, medical.getId());
            returnData.put(Medical.TITLE, medical.getTitle());
            returnData.put(Medical.VISITING_DATE, medical.getVisitingDate());
            returnData.put(Medical.DEPARTMENTS, medical.getDepartments());
            returnData.put(Medical.HOSPITAL, medical.getHospital());
            returnData.put(Medical.BASIC_CONDITION, medical.getBasicCondition());
            returnData.put(Medical.DOCTOR_DIAGNOSIS, medical.getDoctorDiagnosis());
            returnData.put(User.REALNAME, name);
            returnData.put(User.SEX, record.getGender());
            returnData.put(User.AGE, age);
            returnDataList.add(returnData);
        }
        
        if (returnDataList.size() == 0) {
            return MAppNormalService.success(NormalMessage.NO_DATA);
        }

        return MAppNormalService.success(returnDataList);
    }

    /**
     * 获取指定用户的病历
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月30日 上午11:39:38
     */
    @RequestMapping(value = "getMedical", method = RequestMethod.POST)
    public JSONObject getMedical(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();
        int medicalId = data.getIntValue(Medical.ID);
        
        MedicalVO medical = medicalService.getMedical(medicalId);

        // 头30个字
        String doctorDiagnosis = medical.getDoctorDiagnosis();
        if (doctorDiagnosis.length() > 30) {
            doctorDiagnosis = doctorDiagnosis.substring(0, 30);
        }

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Medical.ID, medical.getId());
        returnData.put(Medical.TITLE, medical.getTitle());
        returnData.put(Medical.DEPARTMENTS, medical.getDepartments());
        returnData.put(Medical.DOCTOR_DIAGNOSIS, doctorDiagnosis);
        returnData.put(Medical.BASIC_CONDITION, medical.getBasicCondition());
        returnData.put(Medical.HOSPITAL, medical.getHospital());
        returnData.put(Medical.VISITING_DATE, medical.getVisitingDate());

        List<MedicalCourseVO> courseList = medical.getCourseList();
        List<Map<String, Object>> returnCourseList = new ArrayList<>();
        for (MedicalCourseVO course : courseList) {
            Map<String, Object> returnDataCourse = new HashMap<>();
            returnDataCourse.put(MedicalCourse.ID, course.getId());
            returnDataCourse.put(MedicalCourse.TYPE, course.getCourseType());
            returnDataCourse.put(MedicalCourse.REMARK, course.getRemark());
            returnDataCourse.put(MedicalCourse.VISITING_DATE, course.getVisitingDate());
            
            String img1 = null;
            String img2 = null;
            String img3 = null;
            List<Map<String, Object>> returnDataImgList = new ArrayList<>();
            for (int i = 0; i < course.getImgList().size(); i++) {
                MedicalCourseImgPO img = course.getImgList().get(i);
                Map<String, Object> returnDataImg = new HashMap<>();
                returnDataImg.put(MedicalCourseImg.ID, img.getId());
                returnDataImg.put(MedicalCourseImg.IMG_PATH, img.getImg());
                returnDataImg.put(MedicalCourseImg.CREATE_DATE, img.getCreateDate());
                returnDataImg.put(MedicalCourseImg.MODIFY_DATE, img.getModifyDate());
                returnDataImgList.add(returnDataImg);
                
                /** 兼容代码开始 */
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
            returnDataCourse.put(MedicalCourseImg.IMG, returnDataImgList);
            returnDataCourse.put(MedicalCourse.IMG1, img1);
            returnDataCourse.put(MedicalCourse.IMG2, img2);
            returnDataCourse.put(MedicalCourse.IMG3, img3);
            returnCourseList.add(returnDataCourse);
        }
        returnData.put(MedicalCourse.COURSES, returnCourseList);

        return MAppNormalService.success(returnData);
    }

    /**
     * 获取指定用户的体检报告列表
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月2日 下午4:30:06
     */
    @RequestMapping(value = "getPhysicalsList", method = RequestMethod.POST)
    public JSONObject getPhysicalsList(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        int userId = data.getIntValue(User.ID);
        int pageIndex = data.getIntValue(Page.INDEX);
        int pageSize = data.getIntValue(Page.SIZE);

        List<Map<String, Object>> returnDataList = new ArrayList<>();
        List<PhysicalVO> physicalVOList = physicalService.listPhysical(userId, pageIndex, pageSize).getData();
        for (PhysicalVO p : physicalVOList) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put(PhysicalRecord.ID, p.getId());
            returnData.put(PhysicalRecord.TITLE, p.getTitle());
            returnData.put(PhysicalRecord.DATE, p.getPhysicalsDate());
            returnData.put(PhysicalRecord.DESCRIPTION, p.getDescription());
            returnData.put(PhysicalRecord.ORG, p.getPhysicalsOrg());
            
            String img1 = null;
            String img2 = null;
            String img3 = null;
            List<Map<String, Object>> returnImgList = new ArrayList<>();
            for (int i = 0; i < p.getImgList().size(); i++) {
                PhysicalImgPO img = p.getImgList().get(i);
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
            returnDataList.add(returnData);
        }
        
//        List<Map<String, Object>> physicalsList = new ArrayList<>();
//        PaginationDTO paginationDTO = terminal.getRecordService().selectPhysicalsByUserIdPageSplit(userId, pageIndex,
//                pageSize);
//        physicalsList = paginationDTO.getData();
//        if (physicalsList.size() < 1) {
//            return MAppNormalService.success(NormalMessage.NO_DATA);
//        }
//        for (int i = 0; i < physicalsList.size(); i++) {
//
//            // modify
//
//            int phy_id = (int) physicalsList.get(i).get("id");
//            physicalsList.get(i).put(PhysicalRecord.ID, phy_id + "");
//
//            String img1_i = (String) physicalsList.get(i).get("img1");
//            physicalsList.get(i).put(PhysicalRecord.IMG1, img1_i);
//            String img2_i = (String) physicalsList.get(i).get("img2");
//            physicalsList.get(i).put(PhysicalRecord.IMG2, img2_i);
//            String img3_i = (String) physicalsList.get(i).get("img3");
//            physicalsList.get(i).put(PhysicalRecord.IMG3, img3_i);
//
//            String physicalsDate = "";
//            Object pD = physicalsList.get(i).get("physicalsDate");
//            if (pD != null) {
//                physicalsDate = DateTimeUtilT.date((Date) pD);
//            }
//            physicalsList.get(i).put(PhysicalRecord.DATE, physicalsDate);
//
//            String descript = (String) physicalsList.get(i).get("description");
//            if (StringUtils.isBlank(descript)) {
//                descript = "";
//            }
//
//            physicalsList.get(i).put(PhysicalRecord.DESCRIPTION, descript);
//
//            // remove
//            physicalsList.get(i).remove("userId");
//            physicalsList.get(i).remove("createDate");
//            physicalsList.get(i).remove("description");
//        }

        if (returnDataList.size() == 0) {
            return MAppNormalService.success(NormalMessage.NO_DATA);
        }
        
        return MAppNormalService.success(returnDataList);
    }

    /**
     * 获取指定用户的饮食列表
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @updateBy wuj 2017-08-25 09:56:46
     * @updateReason 新增需求, 如果请求日期为空, 则后台查询最近一天的数据.
     * @DateTime 2016年12月2日 下午4:30:23
     */
    @RequestMapping(value = "getDietList", method = RequestMethod.POST)
    public JSONObject getDietList(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        int userId = data.getIntValue(User.ID);

        // 判断请求日期是否为空
        String recordDate = !org.springframework.util.StringUtils.hasText(data.getString(Diet.RECORD_DATE)) ?
                recordService.getLastDateForUserDietData(userId) : data.getString(Diet.RECORD_DATE);

        List<Map<String, Object>> diets = recordService.selectDietByUserIdWithDate(userId, recordDate);

        if (diets.size() == 0) {
            return MAppNormalService.success(NormalMessage.NO_DATA);
        }

        for (Map<String, Object> diet : diets) {
            int diet_id = (Integer) diet.get("id");

            List<Map<String, Object>> foods = new ArrayList<>();

            List<Map<String, Object>> dietFoods = recordService.selectDietFoodByDietId(diet_id);
            for (Map<String, Object> dietFood : dietFoods) {

                Object image = dietFood.get("image");

                Map<String, Object> food = new HashMap<>();
                food.put(DietFood.ID, dietFood.get("id") + "");
                food.put(DietFood.NAME, dietFood.get("name") + "");
                food.put(DietFood.WEIGHT, dietFood.get("foodWeight") + "");
                food.put(DietFood.TYPE, dietFood.get("kind") + "");
                food.put(DietFood.KCAL, dietFood.get("kcal") + "");
                food.put(DietFood.IMAGE, image);

                foods.add(food);
            }

            // modify
            String dietTime = "";
            Object t = diet.get("dietTime");
            if (t != null) {
                dietTime = DateTimeUtilT.time((Date) t);
            }
            diet.put(Diet.TIME, dietTime);

            String rD = "";
            Object r = diet.get("recordDate");
            if (r != null) {
                rD = DateTimeUtilT.date((Date) r);
            }
            diet.put(Diet.RECORD_DATE, rD);

            Object id = diet.get("id");
            diet.put(Diet.ID, id + "");

            int eg = (int) diet.get("energy");
            diet.put(Diet.ENERGY, eg + "");

            // add food
            diet.put(DietFood.FOODS, foods);

            // remove userId createDate
            diet.remove("userId");
            diet.remove("createDate");
        }

        return MAppNormalService.success(diets);
    }

    /**
     * 获取全部设备数据
     * <p>
     * 修改后接口功能描述: 如果参数 {@param withDate}为空,返回用户测量数据最后一天的所有数据,
     * 否则返回全部设备数据.</p>
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月30日 下午7:49:40
     * @editor wuj
     * @editTime 2017-08-04 13:45:37
     */
    @RequestMapping(value = "getHealthData", method = RequestMethod.POST)
    public JSONObject getHealthData(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();
        Integer serveUserId = appJSON.getUser();

        int userId = data.getIntValue(User.ID);
        String withDate = data.getString(HealthPackage.WITH_DATE);
        String terminalType = data.getString(Terminal.TYPE);

        String deviceType = Conversion.getDeviceType(terminalType);
        if ("-1".equals(deviceType)) {
            return MAppNormalService.success(NormalMessage.TERMINAL_NO_FOUND);
        }

        UserDTO user = memberService.getUser(userId);

        // 消除红点
        orderService.removeRedPoint(user.getId(), serveUserId);

        if (!StringUtils.isBlank(withDate)) {
            return MAppNormalService.success(returnDataAndCheckIsRead(deviceType, user, withDate, serveUserId));
        } else {
            try {
                withDate = recordService.getLastDateWithinData(userId);
            } catch (DataBaseException e) {
                return MAppNormalService.error(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                return MAppNormalService.error("未知异常");
            }

            if (withDate == null) {
                return MAppNormalService.success("未找到用户测量数据");
            }

            return MAppNormalService.success(returnDataAndCheckIsRead(deviceType, user, withDate, serveUserId));
        }
    }

    /**
     * 返回健康数据,并检查用户是否查看过数据
     *
     * @param deviceType
     * @param user
     * @param withDate
     * @param serveUserId
     * @return
     */
    private Map returnDataAndCheckIsRead(String deviceType, UserDTO user, String withDate, int serveUserId) {
        boolean isRead = false;
        int userId = user.getId();

        List<Map<String, Object>> list = mappNormalService.getUserAllData(deviceType, user, withDate, true);

        // 获取用户设备
        int device = 0;
        Map temp = new HashMap();
        for (Map<String, Object> map : list) {
            if (map.containsKey("device")) {
                device = (int) map.get("device");
                temp = map;
            }
        }

        list.remove(temp); // 移除key="device"的Map

        // 时间字符转换 "yyyy-MM-dd HH:mm:ss" => "yyyy-MM-dd"
        String measureDate = withDate.contains(" ") ? withDate.substring(0, withDate.indexOf(" ")) : withDate;
        // 根据测量时间,会员ID,服务师ID获取当天的读取记录
        UserInfoRead userInfoRead = userInfoReadDao.selectByUserIdAndOrgUserId(userId, serveUserId, measureDate);
        if (userInfoRead == null && device != 0) {
            UserInfoRead infoRead = new UserInfoRead();
            infoRead.setIsRead(false);
            infoRead.setDevice(device == 0 ? null : device);
            infoRead.setMeasureDate(DateTimeUtilT.date(measureDate));
            infoRead.setUserId(userId);
            infoRead.setOrgUserId(serveUserId);

            userInfoReadDao.insert(infoRead);
        } else if (userInfoRead != null && device != 0) {
            isRead = userInfoRead.getIsRead();
        }

        HashMap<Object, Object> returnMap = new HashMap<>(2);
        returnMap.put("healthData", list);
        returnMap.put("isRead", String.valueOf(isRead));

        return returnMap;
    }


    /**
     * 获取用户信息
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月2日 下午4:30:51
     */
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    public JSONObject getUserInfo(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        int userId = data.getIntValue(User.ID);
        int serveGroupId = data.getIntValue(OrgServeGroup.ID);

        OrgMemberMessageDetailVO detail = serviceOrgService.getMemberMessageDetail(userId, serveGroupId, null);

        Map<String, String> returnData = new HashMap<>();
        String photo = detail.getPhoto();
        String birthday = "";
        if (detail.getBirthday() != null) {
            birthday = DateTimeUtilT.date(detail.getBirthday());
        }
        int sex_i = 1;
        if (detail.getSex() != null && !detail.getSex().booleanValue()) {
            sex_i = 0;
        }
        String endDate = "";
        String daysRemaining = "";
        Date e = detail.getEndDate();
        if (e != null) {
            endDate = DateTimeUtilT.date(e);
            daysRemaining = DateTimeUtilT.calculateDayInterval(new Date(), e) + "";
        }
        String timesRemaining = "";
        if (detail.getTimesRemaining() != null) {
            timesRemaining = detail.getTimesRemaining() + "";
        }

        returnData.put(User.PHOTO, photo);
        returnData.put(User.USERNAME, detail.getUserName());
        returnData.put(User.REALNAME, detail.getRealName());
        returnData.put(User.BIRTHDAY, birthday);
        returnData.put(User.SEX, sex_i + "");
        returnData.put(OrgServeGroup.NAME, detail.getGroupName());
        returnData.put(Serve.SERVE_NAME, detail.getCurrentServeName());
        returnData.put(Order.CONSUME_MODE, detail.getConsumeMode());
        returnData.put(Order.DAYS_REMAINING, daysRemaining);
        returnData.put(Order.TIMES_REMAINING, timesRemaining);
        returnData.put(Order.CHARGE_MODE, detail.getChargeMode() + "");
        returnData.put(Order.END_DATE, endDate);

        return MAppNormalService.success(returnData);
    }

    @RequestMapping(value = "getUserByHuanxinAcount", method = RequestMethod.POST)
    public JSONObject getUserByHuanxinAcount(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();
        int userId = appJSON.getUser();

        String userNames = data.getString(HuanXin.USERNAMES);
        String groupUserNames = data.getString(HuanXin.GROUP_USERNAME);
        List<String> names = new ArrayList<>();
        List<String> huanxinGroupNames = new ArrayList<>();

        if (StringUtils.isNotBlank(userNames)) {
            names = JSONArray.parseArray(userNames, String.class);
        }
        if (StringUtils.isNotBlank(groupUserNames)) {
            huanxinGroupNames = JSONArray.parseArray(groupUserNames, String.class);
        }

        List<Map<String, Object>> returnDatas = new ArrayList<>();
        if (names.size() > 0) {
            names = ListUtil.removeRepeatElement(names, String.class);
            List<Map<String, Object>> users = serviceOrgService.getUsersByHuanxinAccount(names);
            for (int i = 0; i < users.size(); i++) {
                // name
                users.get(i).put(OrgUser.NAME, users.get(i).get("name"));
                // remove id
                users.get(i).remove("userId");
            }

            returnDatas.addAll(users);
        }
        if (huanxinGroupNames.size() > 0) {
            huanxinGroupNames = ListUtil.removeRepeatElement(huanxinGroupNames, String.class);

            List<Map<String, Object>> groups = new ArrayList<>();
            List<LessonDTO> lessonDTOs = lessonGroupService.listServeUserLesson(huanxinGroupNames, userId);
            for (LessonDTO lessonDTO : lessonDTOs) {
                Map<String, Object> group = new HashMap<>();
                /** 课堂默认code为02 */
                group.put(Serve.CODE, "02");
                group.put(HuanXin.USERNAME, lessonDTO.getHuanxinId());
                group.put(OrgServeGroup.PHTOTO, lessonDTO.getPhoto());
                group.put(OrgServeGroup.ID, lessonDTO.getId());
                group.put(OrgServeGroup.NAME, lessonDTO.getName());
                group.put(OrgServeGroup.USER_COUNT, lessonDTO.getLessonUserCount());
                groups.add(group);
            }
            returnDatas.addAll(groups);
        }
        if (returnDatas.size() > 0) {
            /** 把查询的环信ID合并到一起，标识未知账号 */
            names.addAll(huanxinGroupNames);
            returnDatas = setUnknowAccountForNotExist(names, returnDatas);
        }

        return MAppNormalService.success(returnDatas);
    }

    /**
     * 将未知的账号设置提示“未知账号”
     *
     * @param huanxinUserNames
     * @param users
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月27日 下午1:31:33
     */
    private List<Map<String, Object>> setUnknowAccountForNotExist(List<String> huanxinUserNames,
                                                                  List<Map<String, Object>> users) {
        List<String> name_tmp = new ArrayList<>();

        for (Map<String, Object> user : users) {
            name_tmp.add((String) user.get(HuanXin.USERNAME));
        }

        for (String name : huanxinUserNames) {
            if (!name_tmp.contains(name)) {
                Map<String, Object> user_blank = new HashMap<>();
                user_blank.put(HuanXin.USERNAME, name);
                user_blank.put(OrgUser.NAME, "未知账号");
                user_blank.put(OrgUser.PHOTO, "未知账号");
                user_blank.put(OrgUser.ID, "-1");

                users.add(user_blank);
            }
        }

        return users;
    }

    @RequestMapping(value = "getNotOpenServeList", method = RequestMethod.POST)
    public JSONObject getNotOpenServeList(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser user = appJSON.getAopData().getOrgUser();

        int orgId = user.getOrgId();

        List<Map<String, String>> returnDatas = new ArrayList<>();

        List<TServe> serves = serviceOrgService.getServeDatas(orgId);
        for (TServe serve : serves) {
            Map<String, String> returnData = new HashMap<>();

            String hasOrder = serve.getHasOrder();
            List<String> orders = new ArrayList<>();
            if (StringUtils.isNotBlank(hasOrder)) {
                Collections.addAll(orders, hasOrder.split(","));
            }

            if (orders.contains(serve.getId() + "")) {
                continue;
            }

            String image = serve.getImage();

            returnData.put(Serve.ID, serve.getId() + "");
            returnData.put(Serve.NAME, serve.getName());
            returnData.put(Serve.PROFITSHARE, serve.getProfitShare() + "");
            returnData.put(Serve.IMAGE, image);
            returnData.put(Serve.TYPE, serve.getServeType() + "");
            returnData.put(Serve.CLASSIFY, serve.getClassify());
            returnData.put(Serve.ABOUT, serve.getAbout());
            returnData.put(Serve.CHARGE_MODE, serve.getChargeMode());

            returnDatas.add(returnData);
        }

        return MAppNormalService.success(returnDatas);
    }

    @RequestMapping(value = "openServe", method = RequestMethod.POST)
    public JSONObject openServe(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        TOrgServe serve = getAOrgServe(appJSON.getData(), false);

        Integer orgServeId = serviceOrgService.openOrderService(serve, orgUser.getRealName(), orgUser.getOrgId());

        Map<String, Integer> returnData = new HashMap<>();
        returnData.put(OrgServe.ID, orgServeId);

        return MAppNormalService.success(returnData);
    }

    private TOrgServe getAOrgServe(JSONObject data, boolean isModify) {
        int serveId = data.getIntValue(Serve.ID);
        int hasFree = data.getIntValue(OrgServe.HAS_FREE);
        Integer freeDate = data.getInteger(OrgServe.FREE_DATE);
        Integer hasTime = data.getInteger(OrgServe.HAS_TIME);
        Float timePrice = data.getFloat(OrgServe.TIME_PRICE);
        Integer hasMonth = data.getInteger(OrgServe.HAS_MONTH);
        Float monthPrice = data.getFloat(OrgServe.MONTH_PRICE);
        Integer hasYear = data.getInteger(OrgServe.HAS_YEAR);
        Float yearPrice = data.getFloat(OrgServe.YEAR_PRICE);
        String classify = data.getString(OrgServe.CLASSIFY);
        String about = data.getString(OrgServe.ABOUT);

        TOrgServe serve = new TOrgServe();
        serve.setServeId(serveId);
        serve.setClassify(classify);
        serve.setAbout(about);
        if (hasFree == 1) {
            serve.setHasFree(true);
            serve.setFreeDate(freeDate);
        }
        if (hasFree == 0) {
            serve.setHasFree(false);
            if (hasTime.intValue() == 1) {
                serve.setHasTime(true);
                serve.setTimePrice(((Float) (timePrice * 100)).intValue());
            } else {
                serve.setHasTime(false);
            }
            if (hasMonth.intValue() == 1) {
                serve.setHasMonth(true);
                serve.setMonthPrice(((Float) (monthPrice * 100)).intValue());
            } else {
                serve.setHasMonth(false);
            }
            if (hasYear.intValue() == 1) {
                serve.setHasYear(true);
                serve.setYearPrice(((Float) (yearPrice * 100)).intValue());
            } else {
                serve.setHasYear(false);
            }
        }

        Integer orgServeId = data.getIntValue(OrgServe.ID);
        if (isModify) {
            serve.setId(orgServeId);
        }

        return serve;
    }

    @RequestMapping(value = "getOrgServe", method = RequestMethod.POST)
    public JSONObject getOrgServe(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        JSONObject data = appJSON.getData();
        int orgServeId = data.getIntValue(OrgServe.ID);

        int orgId = orgUser.getOrgId();

        Map<String, Object> condition = new HashMap<>();
        condition.put("id", orgServeId);
        condition.put("orgId", orgId);

        List<Map<String, Object>> orgServeList = commonTrans.findByMap(TOrgServe.class, condition);
        Map<String, Object> orgServeMap = orgServeList.get(0);
        TOrgServe orgServe = ReflectUtils.getBean(orgServeMap, TOrgServe.class);

        TServe serve = commonTrans.getEntity(TServe.class, orgServe.getServeId());

        Integer timePrice = orgServe.getTimePrice();
        if (timePrice == null) {
            timePrice = 0;
        }
        Integer monthPrice = orgServe.getMonthPrice();
        if (monthPrice == null) {
            monthPrice = 0;
        }
        Integer yearPrice = orgServe.getYearPrice();
        if (yearPrice == null) {
            yearPrice = 0;
        }

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Serve.ID, orgServe.getServeId());
        returnData.put(OrgServe.HAS_FREE, orgServe.getHasFree());
        returnData.put(OrgServe.FREE_DATE, orgServe.getFreeDate());
        returnData.put(OrgServe.HAS_TIME, orgServe.getHasTime());
        returnData.put(OrgServe.TIME_PRICE, NumberUtils.changeF2Y(timePrice + ""));
        returnData.put(OrgServe.HAS_MONTH, orgServe.getHasMonth());
        returnData.put(OrgServe.MONTH_PRICE, NumberUtils.changeF2Y(monthPrice + ""));
        returnData.put(OrgServe.HAS_YEAR, orgServe.getHasYear());
        returnData.put(OrgServe.YEAR_PRICE, NumberUtils.changeF2Y(yearPrice + ""));
        returnData.put(OrgServe.CLASSIFY, orgServe.getClassify());
        returnData.put("allClassifies", serve.getClassify());
        returnData.put(OrgServe.ABOUT, orgServe.getAbout());
        returnData.put(Serve.CHARGE_MODE, serve.getChargeMode());

        return MAppNormalService.success(returnData);
    }

    @RequestMapping(value = "modifyOrgServe", method = RequestMethod.POST)
    public JSONObject modifyOrgServe(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        JSONObject data = appJSON.getData();
        int orgId = orgUser.getOrgId();

        TOrgServe orgServe = getAOrgServe(data, true);

        serviceOrgService.updateOrderServiceDetail(orgId, orgServe);

        return MAppNormalService.success();
    }

    @RequestMapping(value = "addGroup", method = RequestMethod.POST)
    public JSONObject addGroup(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        JSONObject data = appJSON.getData();

        int orgServeId = data.getIntValue(OrgServe.ID);
        String serveGroupName = data.getString(OrgServeGroup.NAME);
        JSONArray serveUsers = data.getJSONArray(OrgServeUser.USERS);

        JSONArray servers = new JSONArray();

        for (int i = 0; i < serveUsers.size(); i++) {
            JSONObject serveUser = serveUsers.getJSONObject(i);

            int userId = serveUser.getIntValue(OrgUser.ID);
            servers.add(userId + "");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("name", serveGroupName);
        params.put("defaultGroup", false);
        params.put("servers", servers);

        int groupId = serviceOrgService.addGroup(orgUser.getOrgId(), null, params, orgServeId);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(OrgServeGroup.ID, groupId);

        return MAppNormalService.success(returnData);
    }

    @RequestMapping(value = "modifyGroup", method = RequestMethod.POST)
    public JSONObject modifyGroup(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        int groupId = data.getIntValue(OrgServeGroup.ID);
        String name = data.getString(OrgServeGroup.NAME);
        JSONArray serveUsers = data.getJSONArray(OrgServeUser.USERS);

        TOrgGroup group = commonTrans.getEntity(TOrgGroup.class, groupId);
        group.setName(name);
        commonTrans.updateEntitie(group);

        Map<String, Object> params = new HashMap<>();
        params.put("groupId", groupId);

        commonTrans.deleteByProperty(TOrgGroupOrguser.class, params);

        List<TOrgGroupOrguser> userList = new ArrayList<TOrgGroupOrguser>();
        for (int i = 0; i < serveUsers.size(); i++) {
            TOrgGroupOrguser user = new TOrgGroupOrguser();
            user.setGroupId(group.getId());
            user.setCreateDate(new Date());
            user.setOrgUserId(serveUsers.getJSONObject(i).getInteger(OrgUser.ID));
            userList.add(user);
        }
        if (userList.size() > 0) {
            commonTrans.batchSave(userList);
        }

        return MAppNormalService.success();
    }

    @RequestMapping(value = "delGroup", method = RequestMethod.POST)
    public JSONObject delGroup(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser orgUser = appJSON.getAopData().getOrgUser();
        JSONObject data = appJSON.getData();

        int serveGroupId = data.getIntValue(OrgServeGroup.ID);
        Integer newServeGroupId = data.getInteger(OrgServeGroup.NEW_ID);

        Map<String, Object> params = new HashMap<>();
        params.put("oldGroup", serveGroupId + "");
        params.put("newGroup", newServeGroupId);

        /** 如果没有提供新群组ID，默认将会员移动到默认群组 */
        boolean moveToDefaultGroup = (newServeGroupId == null);

        ServiceMessage sm = serviceOrgService.delGroup(params, orgUser.getOrgId(), moveToDefaultGroup);

        if (!sm.isSuccess()) {
            return MAppNormalService.success(sm.getMessage());
        }

        JSONObject newGroupRoot = JSONObject.parseObject(sm.getMessage());
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(OrgServeGroup.NEW_ID, newGroupRoot.getIntValue("id"));
        returnData.put(OrgServeGroup.NEW_NAME, newGroupRoot.getString("name"));

        return MAppNormalService.success(returnData);
    }

    @RequestMapping(value = "moveUser", method = RequestMethod.POST)
    public JSONObject moveUser(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        JSONObject data = appJSON.getData();
        int serveGroupId = data.getIntValue(OrgServeGroup.ID);
        int newServeGroupId = data.getIntValue(OrgServeGroup.NEW_ID);
        int userId = data.getIntValue(User.ID);

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("newGroup", newServeGroupId);
        params.put("oldGroup", serveGroupId);

        boolean success = serviceOrgService.moveGroup(params, orgUser.getOrgId());

        if (!success) {
            return MAppNormalService.error(Error.FAIL_ACTION);
        }

        return MAppNormalService.success();
    }

    @RequestMapping(value = "getGroupServeUsers", method = RequestMethod.POST)
    public JSONObject getGroupServeUsers(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        JSONObject data = appJSON.getData();
        int orgId = orgUser.getOrgId();

        Integer serveGroupId = data.getInteger(OrgServeGroup.ID);

        List<Map<String, Object>> servers = new ArrayList<>();
        if (serveGroupId != null) {
            servers = serviceOrgService.getControlGroupServer(serveGroupId);
        }

        List<Integer> selectedId = new ArrayList<>();
        for (Map<String, Object> server : servers) {
            selectedId.add((Integer) server.get("id"));
        }

        List<Map<String, Object>> returnDatas = new ArrayList<>();

        List<OrgEmploy> emList = serviceOrgService.getOrgServersWithGroupCount(orgId);
        for (OrgEmploy em : emList) {
            Map<String, Object> returnData = new HashMap<>();

            int selected = 0;
            if (selectedId.contains(em.getId())) {
                selected = 1;
            }

            String photo = em.getPhoto();

            boolean sex = em.isSex();
            int sex_i = 0;
            if (sex) {
                sex_i = 1;
            }

            String groupNames = em.getGroupNames();
            if (StringUtils.isBlank(groupNames)) {
                groupNames = "无";
            }

            returnData.put(OrgUser.ID, em.getId());
            returnData.put(OrgUser.NAME, em.getRealName());
            returnData.put(OrgUser.PHOTO, photo);
            returnData.put(OrgUser.SEX, sex_i);
            returnData.put(OrgServeGroup.NAMES, groupNames);
            returnData.put(Normal.SELECTED, selected);

            returnDatas.add(returnData);
        }

        return MAppNormalService.success(returnDatas);
    }

    @RequestMapping(value = "orderHistory", method = RequestMethod.POST)
    public JSONObject orderHistory(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        JSONObject data = appJSON.getData();
        int pageIndex = data.getIntValue(Page.INDEX);
        int pageSize = data.getIntValue(Page.SIZE);
        int orgServeId = data.getIntValue(OrgServe.ID);
        Integer status = data.getInteger(Order.STATUS);
        Integer serveGroupId = data.getInteger(OrgServeGroup.ID);

        int orgId = orgUser.getOrgId();
        int userType = orgUser.getUserType();

        // group
        List<OrgServerGroupBase> groupList = serviceOrgService.findGroupByKeyWithOrgServeId(orgId, orgServeId,
                userType == 1 ? orgUser.getId() : null);
        List<Map<String, Object>> groups = new ArrayList<>();
        Map<String, Object> group_tmp = new HashMap<>();
        group_tmp.put(OrgServeGroup.ID, 0);
        group_tmp.put(OrgServeGroup.NAME, "全部");
        groups.add(group_tmp);
        for (OrgServerGroupBase base : groupList) {
            Map<String, Object> group = new HashMap<>();

            group.put(OrgServeGroup.ID, base.getId());
            group.put(OrgServeGroup.NAME, base.getName());

            groups.add(group);
        }

        // state
        List<Map<String, Object>> statuses = new ArrayList<>();

        Map<String, Object> all = new HashMap<>();
        all.put("status", 0);
        all.put("name", "所有");
        statuses.add(all);

        Map<String, Object> valid = new HashMap<>();
        valid.put("status", 3);
        valid.put("name", "有效");
        statuses.add(valid);

        Map<String, Object> complete = new HashMap<>();
        complete.put("status", 4);
        complete.put("name", "已完成");
        statuses.add(complete);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put("groups", groups);
        returnData.put("statuses", statuses);

        // {dPage=15, page=0, serverId=1, orgId=151}
        Map<String, Object> params = new HashMap<>();
        params.put("page", pageIndex);
        params.put("dPage", pageSize);
        params.put("orgServeId", orgServeId);
        params.put("orgId", orgId);

        Map<String, Object> condition = new HashMap<>();
        if (status != null) {
            if (status.intValue() == 0) { // 0表示显示全部(包括3,4)
                List<Object> list = new ArrayList<>();
                list.add(3);
                list.add(4);
                condition.put("status", list);
            } else {
                condition.put("status", status);
            }
        }
        if (serveGroupId != null) {
            if (serveGroupId.intValue() != 0) { // 0表示显示全部
                condition.put("serveGroupId", serveGroupId);
            }
        }
        if (!condition.isEmpty()) {
            params.put("condition", condition);
        }

        List<OrgServeRecord> records = serviceOrgService.findHistoryServeRecord(params);
        List<Map<String, Object>> orders = new ArrayList<>();
        for (OrgServeRecord record : records) {

            int daysRemaining = 0;
            Date endDate = record.getEndDate();
            if (endDate != null) {
                daysRemaining = DateTimeUtilT.calculateDayInterval(new Date(), endDate);
            }
            Integer timesRemaining = record.getTimesRemaining();
            if (timesRemaining == null) {
                timesRemaining = 0;
            }

            Map<String, Object> order = new HashMap<>();
            order.put(User.REALNAME, record.getRealName());
            order.put(User.SEX, record.getSex());
            order.put(OrgServeGroup.NAME, record.getGroupName());
            order.put(Order.DAYS_REMAINING, daysRemaining);
            order.put(Order.TIMES_REMAINING, timesRemaining);
            order.put(Order.CHARGE_MODE, record.getChargeMode());
            order.put(Order.PRICE, record.getFair());
            order.put(Order.STATUS, record.getStatus());
            order.put(User.PHOTO, record.getPhoto());

            orders.add(order);
        }
        returnData.put(OrgServe.RECORDS, orders);

        return MAppNormalService.success(returnData);
    }

    /**
     * 获取健康课堂列表
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年2月28日 上午10:56:41
     */
    @RequestMapping(value = "listLessonGroup", method = RequestMethod.POST)
    public JSONObject listLessonGroup(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        Integer orgServeId = data.getInteger(OrgServe.ID);
        List<LessonDTO> lessonDTOs = lessonGroupService.listServeLesson(orgServeId);

        List<Map<String, Object>> returnDatas = new ArrayList<>();
        for (LessonDTO lessonDTO : lessonDTOs) {
            int userCount = lessonDTO.getLessonUserCount();
            List<LessonGroupOrgUserDTO> groupOrgUsers = lessonDTO.getOrgUsers();
            String creatorUserCode = "";
            Integer creatorId = lessonDTO.getCreatorId();
            for (LessonGroupOrgUserDTO groupOrgUser : groupOrgUsers) {
                if (groupOrgUser.getId() == creatorId) {
                    creatorUserCode = groupOrgUser.getUserCode();
                    break;
                }
            }

            Map<String, Object> lesson = new HashMap<>();
            lesson.put(OrgServeGroup.PHTOTO, lessonDTO.getPhoto());
            lesson.put(OrgServeGroup.NAME, lessonDTO.getName());
            lesson.put(OrgServeGroup.HUANXIN_ID, lessonDTO.getHuanxinId());
            lesson.put(OrgServeGroup.ID, lessonDTO.getId());
            lesson.put(OrgServeGroup.USER_COUNT, userCount);
            lesson.put(OrgUser.CODE, creatorUserCode);
            returnDatas.add(lesson);
        }

        return MAppNormalService.success(returnDatas);
    }

    /**
     * 创建健康课堂
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年2月28日 下午1:36:37
     */
    @RequestMapping(value = "createLessonGroup", method = RequestMethod.POST)
    public JSONObject createLessonGroup(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser user = appJSON.getAopData().getOrgUser();

        JSONObject data = appJSON.getData();
        Integer orgServeId = data.getInteger(OrgServe.ID);
        String photo = data.getString(OrgServeGroup.PHTOTO);
        String name = data.getString(OrgServeGroup.NAME);
        // TODO 暂时使用abc替换
        String description = data.getString("abc");
        if (description == null) {
            description = data.getString("remark");
        }

        if (name.length() > 20) {
            return MAppNormalService.error("群组名称长度不可超过20个字符");
        }

        /** 处理头像字段 */
        String relativePath = null;
        if (StringUtils.isNotBlank(photo)) {
            ImageDTO imageDTO = MAppNormalService.uploadPhoto(photo, null, "head", false);
            if (imageDTO.getUploadSuccess()) {
                relativePath = imageDTO.getNetPath();
            }
        }

        /** 处理开课时间字段 */
        List<CourseTimeDTO> courseTimeDTOs = jsonToCourseTimeList(appJSON.getData());
        if (courseTimeDTOs == null) {
            return MAppNormalService.error("开课时间格式不正确");
        }

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setDefaultGroup(false);
        lessonDTO.setCourseTime(courseTimeDTOs);
        lessonDTO.setName(name);
        lessonDTO.setOrgServeId(orgServeId);
        lessonDTO.setDescription(description);
        lessonDTO.setPhoto(relativePath);

        lessonGroupService.addLesson(lessonDTO, user.getId(), user.getUserCode());

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(OrgServeGroup.ID, lessonDTO.getId());
        returnData.put(OrgServeGroup.HUANXIN_ID, lessonDTO.getHuanxinId());
        return MAppNormalService.success(returnData);
    }

    /**
     * json接收的数据转换为CourseTimeDTO集合
     *
     * @param data
     * @return 如果返回的数据为null，表示转换失败
     * @author yuhang.weng
     * @DateTime 2017年3月2日 下午4:40:27
     */
    private List<CourseTimeDTO> jsonToCourseTimeList(JSONObject data) {
        JSONArray courseTimes = data.getJSONArray(OrgServeGroup.COURSE_TIME);

        List<CourseTimeDTO> courseTimeDTOs = new ArrayList<>();
        for (int i = 0; i < courseTimes.size(); i++) {
            String startTime = courseTimes.getJSONObject(i).getString(OrgServeGroup.START_TIME);
            Integer week = courseTimes.getJSONObject(i).getInteger(OrgServeGroup.WEEK);

            CourseTimeDTO courseTimeDTO = new CourseTimeDTO();
            /** 判断时间格式 */
            if (!Toolkits.isVerifyHH_mm(startTime)) {
                return null;
            }
            courseTimeDTO.setStartTime(startTime);
            /** 判断日期格式 */
            if (week > 7 || week < 1) {
                return null;
            }

            String week_s = MAppNormalService.weekdayToString(week);
            courseTimeDTO.setWeeks(week_s);

            courseTimeDTOs.add(courseTimeDTO);
        }
        return courseTimeDTOs;
    }

    /**
     * 获取健康课堂信息
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年2月28日 下午1:36:56
     */
    @RequestMapping(value = "getLessonGroup", method = RequestMethod.POST)
    public JSONObject getLessonGroup(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();
        Integer orgServeGroupId = data.getInteger(OrgServeGroup.ID);

        LessonDTO lesson = lessonGroupService.getLesson(orgServeGroupId);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(OrgServeGroup.PHTOTO, lesson.getPhoto());
        returnData.put(OrgServeGroup.NAME, lesson.getName());
        returnData.put(OrgServeGroup.DESCRIPTION, lesson.getDescription());
        returnData.put(OrgServeGroup.SILENCE, lesson.getSilence());

        List<Map<String, Object>> courseTimes = new ArrayList<>();
        for (CourseTimeDTO courseTimeDTO : lesson.getCourseTime()) {
            Map<String, Object> courseTime = new HashMap<>();
            courseTime.put(OrgServeGroup.START_TIME, courseTimeDTO.getStartTime());

            int weekday = MAppNormalService.weeksToInt(courseTimeDTO.getWeeks());
            courseTime.put(OrgServeGroup.WEEK, weekday);
            courseTimes.add(courseTime);
        }
        returnData.put(OrgServeGroup.COURSE_TIME, courseTimes);

        /** 遍历群组用户 */
        List<Map<String, Object>> users = new ArrayList<>();
        for (LessonGroupOrgUserDTO orgUser : lesson.getOrgUsers()) {
            Map<String, Object> user = new HashMap<>();
            user.put(OrgServeGroupUser.TYPE, orgUser.getGroupUserType());
            user.put(OrgServeGroupUser.PHOTO, orgUser.getPhoto());
            user.put(OrgServeGroupUser.NAME, orgUser.getRealName());
            user.put(OrgServeGroupUser.ID, orgUser.getId());
            user.put(OrgServeGroupUser.CODE, orgUser.getUserCode());
            user.put(OrgServeGroupUser.MOBILE, orgUser.getMobile());
            user.put(OrgServeGroupUser.SEX, orgUser.getSex());
            user.put(OrgServeGroupUser.EMAIL, orgUser.getEmail());
            user.put(OrgServeGroupUser.WORK_PHONE, orgUser.getTel());
            users.add(user);
        }
        for (UserOrderDTO member : lesson.getUserOrders()) {
            Map<String, Object> user = new HashMap<>();
            user.put(OrgServeGroupUser.TYPE, 2);
            user.put(OrgServeGroupUser.PHOTO, member.getUserPhoto());
            user.put(OrgServeGroupUser.NAME, member.getUserRealName());
            user.put(OrgServeGroupUser.ID, member.getUserId());
            user.put(OrgServeGroupUser.CODE, member.getUserCode());
            users.add(user);
        }
        returnData.put(OrgServeGroupUser.USERS, users);

        return MAppNormalService.success(returnData);
    }

    /**
     * 更新健康课堂简介
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月2日 下午3:46:10
     */
    @RequestMapping(value = "updateLessonGroupDescription", method = RequestMethod.POST)
    public JSONObject updateLessonGroupDescription(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        Integer orgServeGroupId = data.getInteger(OrgServeGroup.ID);
        // TODO 暂时使用abc
        String description = data.getString("abc");
        if (description == null) {
            description = data.getString("remark");
        }

        LessonDTO lesson = new LessonDTO();
        lesson.setId(orgServeGroupId);
        lesson.setDescription(description);
        boolean success = lessonGroupService.updateLesson(lesson);

        if (!success) {
            return MAppNormalService.error(Error.FAIL_ACTION);
        }

        return MAppNormalService.success();
    }

    /**
     * 更新健康课堂开课时间
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月2日 下午4:48:02
     */
    @RequestMapping(value = "updateLessonGroupStartTime", method = RequestMethod.POST)
    public JSONObject updateLessonGroupStartTime(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        Integer orgServeGroupId = data.getInteger(OrgServeGroup.ID);

        List<CourseTimeDTO> courseTimeDTOs = jsonToCourseTimeList(data);
        if (courseTimeDTOs == null) {
            return MAppNormalService.error("开课时间格式不正确");
        }

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(orgServeGroupId);
        lessonDTO.setCourseTime(courseTimeDTOs);
        boolean success = lessonGroupService.updateLesson(lessonDTO);

        if (!success) {
            return MAppNormalService.error(Error.FAIL_ACTION);
        }

        return MAppNormalService.success();
    }

    /**
     * 更新健康课堂头像
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月2日 下午4:11:04
     */
    @RequestMapping(value = "updateLessonGroupPhoto", method = RequestMethod.POST)
    public JSONObject updateLessonGroupPhoto(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        Integer orgServeGroupId = data.getInteger(OrgServeGroup.ID);
        String photo = data.getString(OrgServeGroup.PHTOTO);

        String photo_new = null;
        /** 非NULL判断 */
        if (photo != null) {
            /** 此处不处理原图片删除，交由service层处理 */
            ImageDTO imageDTO = MAppNormalService.uploadPhoto(photo, null, "head", false);
            if (imageDTO.getUploadSuccess()) {
                photo_new = imageDTO.getNetPath();
            }
        }

        LessonDTO lesson = new LessonDTO();
        lesson.setId(orgServeGroupId);
        lesson.setPhoto(photo_new);
        boolean success = lessonGroupService.updateLesson(lesson);

        if (!success) {
            return MAppNormalService.error(Error.FAIL_ACTION);
        }

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(OrgServeGroup.PHTOTO, photo_new);
        return MAppNormalService.success();
    }

    /**
     * 更新健康课堂名称
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月2日 下午4:11:23
     */
    @RequestMapping(value = "updateLessonGroupName", method = RequestMethod.POST)
    public JSONObject updateLessonGroupName(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        Integer orgServeGroupId = data.getInteger(OrgServeGroup.ID);
        String name = data.getString(OrgServeGroup.NAME);

        if (name.length() > 20) {
            return MAppNormalService.error("群组名称长度不可超过20个字符");
        }
        LessonDTO lesson = new LessonDTO();
        lesson.setId(orgServeGroupId);
        lesson.setName(name);
        boolean success = lessonGroupService.updateLesson(lesson);

        if (!success) {
            return MAppNormalService.error(Error.FAIL_ACTION);
        }
        return MAppNormalService.success();
    }

    /**
     * 删除课堂
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月2日 下午6:58:21
     */
    @RequestMapping(value = "deleteLessonGroup", method = RequestMethod.POST)
    public JSONObject deleteLessonGroup(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        Integer orgServeGroupId = data.getInteger(OrgServeGroup.ID);

        boolean success = lessonGroupService.deleteLesson(orgServeGroupId);
        if (!success) {
            return MAppNormalService.error(Error.FAIL_ACTION);
        }
        return MAppNormalService.success();
    }

    /**
     * 禁言
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月3日 上午10:54:21
     */
    @RequestMapping(value = "gag", method = RequestMethod.POST)
    public JSONObject gag(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        Integer orgServeGroupId = data.getInteger(OrgServeGroup.ID);

        boolean success = lessonGroupService.gag(orgServeGroupId);
        if (!success) {
            return MAppNormalService.error(Error.FAIL_ACTION);
        }
        return MAppNormalService.success();
    }

    /**
     * 解除禁言
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月3日 上午10:54:28
     */
    @RequestMapping(value = "removeGag", method = RequestMethod.POST)
    public JSONObject removeGag(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        Integer orgServeGroupId = data.getInteger(OrgServeGroup.ID);

        boolean success = lessonGroupService.removeGag(orgServeGroupId);
        if (!success) {
            return MAppNormalService.error(Error.FAIL_ACTION);
        }
        return MAppNormalService.success();
    }

    /**
     * 添加服务师
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月4日 上午10:47:54
     */
    @RequestMapping(value = "addLessonGroupOrgUser", method = RequestMethod.POST)
    public JSONObject addLessonGroupOrgUser(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        JSONObject data = appJSON.getData();
        Integer orgServeGroupId = data.getInteger(OrgServeGroup.ID);
        List<Integer> orgUserIds = JSONArray.parseArray(data.getString(OrgUser.IDS), Integer.class);

        if (orgUserIds.size() >= 0) {
            boolean success = lessonGroupService.addOrgUser(orgServeGroupId, orgUserIds);
            if (!success) {
                return MAppNormalService.error(Error.FAIL_ACTION);
            }
        }
        return MAppNormalService.success();
    }

    /**
     * 移除服务师
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月4日 上午10:48:03
     */
    @RequestMapping(value = "removeLessonGroupOrgUser", method = RequestMethod.POST)
    public JSONObject removeLessonGroupOrgUser(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        JSONObject data = appJSON.getData();
        Integer orgServeGroupId = data.getInteger(OrgServeGroup.ID);
        List<Integer> orgUserIds = JSONArray.parseArray(data.getString(OrgUser.IDS), Integer.class);

        if (orgUserIds.size() >= 0) {
            boolean success = lessonGroupService.removeOrgUser(orgServeGroupId, orgUserIds);
            if (!success) {
                return MAppNormalService.error(Error.FAIL_ACTION);
            }
        }

        return MAppNormalService.success();
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
    public JSONObject queryDatesWithData(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        Integer userId = data.getInteger("userId");
        String queryDate = data.getString("queryDate");
        Integer queryType = data.getInteger("queryType");

        if (userId == null || queryDate == null || queryType == null) {
            return ReturnDataAgent.error("参数异常");
        }

        /**
         *  这里没有判断用户是否是机构或个体户的会员
         */
        List<Map<String, Integer>> returnList = new ArrayList<>();
        try {
            Map<String, Integer> resultMap = recordService.queryDatesWithData(userId, queryDate, queryType);

            for (Map.Entry<String, Integer> en : resultMap.entrySet()) {
                Map temp = new HashMap();

                Integer value = en.getValue();
                String key = en.getKey();

                temp.put("day", key);
                temp.put("status", value);

                returnList.add(temp);
            }

        } catch (DataBaseException e) {
            e.printStackTrace();
            return ReturnDataAgent.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnDataAgent.error("查询异常");
        }

        return ReturnDataAgent.success(returnList);
    }
}
