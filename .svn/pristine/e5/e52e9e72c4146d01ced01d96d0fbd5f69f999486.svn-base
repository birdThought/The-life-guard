package com.lifeshs.controller.org.service;

import com.alibaba.fastjson.JSON;

import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;

import com.lifeshs.controller.common.BaseController;

import com.lifeshs.entity.org.user.TOrgUser;

import com.lifeshs.po.ConsultPO;
import com.lifeshs.po.LessonPO;
import com.lifeshs.po.VisitPO;

import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.org.profile.OrgProfileDTO;
import com.lifeshs.pojo.org.service.ConsultServiceDTO;
import com.lifeshs.pojo.org.service.LessonServiceDTO;
import com.lifeshs.pojo.org.service.OrgServiceDTO;
import com.lifeshs.pojo.org.service.ServeTypeDTO;
import com.lifeshs.pojo.org.service.ServiceOrgUserRelationDTO;
import com.lifeshs.pojo.org.service.VisitServiceDTO;
import com.lifeshs.pojo.page.PaginationDTO;

import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service.org.service.IOrgServiceManage;
import com.lifeshs.service.org.user.IOrgUserService;

import com.lifeshs.service1.serve.ProjectService;
import com.lifeshs.service1.serve.ServeService;
import com.lifeshs.service1.serve.consult.ConsultService;
import com.lifeshs.service1.serve.lesson.LessonService;
import com.lifeshs.service1.serve.visit.VisitService;

import com.lifeshs.utils.StringUtil;
import com.lifeshs.utils.image.ImageUtilV2;

import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;


/**
 * 门店-服务项目管理
 *
 * @author wenxian.cai
 * @create 2017-05-17 10:10
 **/
@RestController
@RequestMapping(value = "org/service")
public class OrgServiceManageController extends BaseController {
    private static final Logger logger = Logger.getLogger(OrgServiceManageController.class);
    final static Integer PAGE_SIZE = 6;
    @Autowired
    IOrgServiceManage orgServiceManage;
    @Autowired
    IOrgUserService orgUserService;
    @Autowired
    IManagerOrgService managerOrgService;
    @Autowired
    LessonService lessonService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ConsultService consultService;
    @Autowired
    VisitService visitService;
    @Autowired
    ServeService serveService;

    /**
     * @Description: 服务项目管理界面
     * @Author: wenxian.cai
     * @Date: 2017/5/18 10:32
     */
    @RequestMapping()
    public ModelAndView serviceManage() {
        ModelAndView modelAndView = new ModelAndView(
                "platform/org/servicemanage/service-manage");
        LoginUser user = getLoginUser();
        Integer orgId = user.getOrgId();

        //        PaginationDTO<OrgServiceDTO> paginationDTO = orgServiceManage.listConsultServiceByStatus(orgId, null, 1, PAGE_SIZE, null);
        PaginationDTO<OrgServiceDTO> paginationDTO = orgServiceManage.listService(orgId,
                null, null, 0, 1, PAGE_SIZE);
        modelAndView.addObject("service", JSON.toJSON(paginationDTO));
        modelAndView.addObject("orgType", user.getType());

        return modelAndView;
    }

    /**
     * @Description: 获取服务项目列表
     * @Author: wenxian.cai
     * @Date: 2017/5/18 10:32
     */
    @RequestMapping(value = "/listservice/{page}")
    @ResponseBody
    public AjaxJson listService(@PathVariable(value = "page")
    Integer page, @RequestParam(value = "type")
    Integer type, @RequestParam(value = "status")
    Integer status, @RequestParam(value = "search")
    String search) {
        AjaxJson ajaxJson = new AjaxJson();
        Integer orgId = getLoginUser().getOrgId();
        PaginationDTO<OrgServiceDTO> paginationDTO = new PaginationDTO();
        /*switch (type) {
            case "all":
                paginationDTO = orgServiceManage.listService(orgId, status, search, "all", page, PAGE_SIZE);
                break;
            case "online":
        //                paginationDTO = orgServiceManage.listConsultServiceByStatus(orgId, status, page, PAGE_SIZE, search);
                paginationDTO = orgServiceManage.listService(orgId, status, search, "online", page, PAGE_SIZE);
                break;
            case "offline":
        //                paginationDTO = orgServiceManage.listLessonServiceByStatus(orgId, status, page, PAGE_SIZE, search);
                //暂无线下
                paginationDTO.setData(new ArrayList());
        //                paginationDTO = orgServiceManage.listService(orgId, status, search, "offline", page, PAGE_SIZE);
                break;
            case "visit":
        //                paginationDTO = orgServiceManage.listVisitServiceByStatus(orgId, status, page, PAGE_SIZE, search);
        
                break;
        }*/

        /*if ("offline".equals(type)) {
            paginationDTO.setData(new ArrayList());
            ajaxJson.setObj(paginationDTO);
            return ajaxJson;
        }*/
        paginationDTO = orgServiceManage.listService(orgId, status, search,
                type, page, PAGE_SIZE);
        ajaxJson.setObj(paginationDTO);

        return ajaxJson;
    }

    /**
     * @Description: 添加健康课堂服务
     * @Author: wenxian.cai
     * @Date: 2017/5/18 14:32
     */
    @RequestMapping(value = "/addservice/lesson")
    @ResponseBody
    public AjaxJson addLessonService( @RequestBody(required = false) LessonServiceDTO service) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();

        // 将图片从tmp目录转移到upload下
        if (StringUtils.isNotBlank(service.getImage())) {
            try {
                String newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getImage(),
                        "service");
                ImageUtilV2.delImg(service.getImage());
                service.setImage(newRelativePath);
            } catch (IOException e) {
                ajaxJson.setMsg("操作失败");
                ajaxJson.setSuccess(false);

                return ajaxJson;
            }
        }

        service.setOrgId(user.getOrgId());
        service.setProjectType(ProjectType.PROJECT_LESSON.getValue());
        service.setCreatorId(service.getOrgUser().get(0).getOrgUserId());
        orgServiceManage.addLessonService(service, service.getOrgUser().get(0).getUserCode()); //服务师的userCode

        return ajaxJson;
    }

    /**
     * @Description: 添加健康咨询服务
     * @Author: wenxian.cai
     * @Date: 2017/5/18 16:35
     */
    @RequestMapping(value = "/addservice/consult")
    @ResponseBody
    public AjaxJson addConsultService(@RequestBody
    ConsultServiceDTO service) {
        AjaxJson ajaxJson = new AjaxJson();
        Integer orgId = getLoginUser().getOrgId();

        // 将图片从tmp目录转移到upload下
        if (StringUtils.isNotBlank(service.getImage())) {
            try {
                String newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getImage(),
                        "service");
                ImageUtilV2.delImg(service.getImage());
                service.setImage(newRelativePath);
            } catch (IOException e) {
                ajaxJson.setMsg("操作失败");
                ajaxJson.setSuccess(false);

                return ajaxJson;
            }
        }

        service.setOrgId(orgId);
        service.setProjectType(ProjectType.PROJECT_CONSULT.getValue());
        orgServiceManage.addConsultService(service);

        return ajaxJson;
    }

    /**
     * @Description: 添加上门服务-居家养老服务
     * @Author: wenxian.cai
     * @Date: 2017/5/18 16:35
     */
    @RequestMapping(value = "/addservice/visit")
    @ResponseBody
    public AjaxJson addVisitService(@RequestBody
    VisitServiceDTO service) {
        AjaxJson ajaxJson = new AjaxJson();
        Integer orgId = getLoginUser().getOrgId();

        try {
            service.setIntroduce(ImageUtilV2.regexPathFromHtml(
                    StringUtil.filterHtml(service.getIntroduce())));

            String newRelativePath = null;

            if (StringUtils.isNotBlank(service.getMedia().getPictureOne())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia()
                                                                               .getPictureOne(),
                        "service");
                ImageUtilV2.delImg(service.getMedia().getPictureOne());
                service.getMedia().setPictureOne(newRelativePath);
                //将第一张图片存为产品图片
                service.setImage(newRelativePath);
            }

            if (StringUtils.isNotBlank(service.getMedia().getPictureTwo())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia()
                                                                               .getPictureTwo(),
                        "service");
                ImageUtilV2.delImg(service.getMedia().getPictureTwo());
                service.getMedia().setPictureTwo(newRelativePath);

                if (service.getImage() == null) {
                    service.setImage(newRelativePath);
                }
            }

            if (StringUtils.isNotBlank(service.getMedia().getPictureThree())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia()
                                                                               .getPictureThree(),
                        "service");
                ImageUtilV2.delImg(service.getMedia().getPictureThree());
                service.getMedia().setPictureThree(newRelativePath);

                if (service.getImage() == null) {
                    service.setImage(newRelativePath);
                }
            }

            if (StringUtils.isNotBlank(service.getMedia().getPictureFour())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia()
                                                                               .getPictureFour(),
                        "service");
                ImageUtilV2.delImg(service.getMedia().getPictureFour());
                service.getMedia().setPictureFour(newRelativePath);

                if (service.getImage() == null) {
                    service.setImage(newRelativePath);
                }
            }

            //todo 视频
        } catch (Exception e) {
            logger.info("发布上门服务：移动图片失败");
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);

            return ajaxJson;
        }

        service.setOrgId(orgId);
        service.setProjectType(ProjectType.PROJECT_VISIT.getValue());
        orgServiceManage.addVisitService(service);

        return ajaxJson;
    }

    /**
     * @Description: 添加线下服务
     * @Author: wenxian.cai
     * @Date: 2017/7/3 15:48
     */
    @RequestMapping(value = "/addservice/offline")
    @ResponseBody
    public AjaxJson addOfflineService(@RequestBody
    VisitServiceDTO service) {
        AjaxJson ajaxJson = new AjaxJson();
        Integer orgId = getLoginUser().getOrgId();

        try {
            service.setIntroduce(ImageUtilV2.regexPathFromHtml(
                    StringUtil.filterHtml(service.getIntroduce())));

            String newRelativePath = null;

            if (StringUtils.isNotBlank(service.getMedia().getPictureOne())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia()
                                                                               .getPictureOne(),
                        "service");
                ImageUtilV2.delImg(service.getMedia().getPictureOne());
                service.getMedia().setPictureOne(newRelativePath);
                //将第一张图片存为产品图片
                service.setImage(newRelativePath);
            }

            if (StringUtils.isNotBlank(service.getMedia().getPictureTwo())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia()
                                                                               .getPictureTwo(),
                        "service");
                ImageUtilV2.delImg(service.getMedia().getPictureTwo());
                service.getMedia().setPictureTwo(newRelativePath);

                if (service.getImage() == null) {
                    service.setImage(newRelativePath);
                }
            }

            if (StringUtils.isNotBlank(service.getMedia().getPictureThree())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia()
                                                                               .getPictureThree(),
                        "service");
                ImageUtilV2.delImg(service.getMedia().getPictureThree());
                service.getMedia().setPictureThree(newRelativePath);

                if (service.getImage() == null) {
                    service.setImage(newRelativePath);
                }
            }

            if (StringUtils.isNotBlank(service.getMedia().getPictureFour())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia()
                                                                               .getPictureFour(),
                        "service");
                ImageUtilV2.delImg(service.getMedia().getPictureFour());
                service.getMedia().setPictureFour(newRelativePath);

                if (service.getImage() == null) {
                    service.setImage(newRelativePath);
                }
            }

            //todo 视频
        } catch (Exception e) {
            logger.info("发布线下服务：移动图片失败");
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);

            return ajaxJson;
        }

        service.setOrgId(orgId);
        service.setProjectType(ProjectType.PROJECT_TOSTORE.getValue());
        orgServiceManage.addVisitService(service);

        return ajaxJson;
    }

    /**
     * @Description: 改变服务项目状态
     * @Author: wenxian.cai
     * @Date: 2017/5/26 15:51
     */
    @RequestMapping(value = "/updatestatus")
    @ResponseBody
    public AjaxJson updateServiceStatus(@RequestParam(value = "type") Integer type, @RequestParam(value = "code") String code, @RequestParam(value = "status") Integer status) {
        AjaxJson ajaxJson = new AjaxJson();
        ProjectType projectType = ProjectType.values()[type];

        if (ProjectType.PROJECT_CONSULT == projectType) {
            orgServiceManage.updateConsultServiceStatus(code, status);
            return ajaxJson;
        }

        if (ProjectType.PROJECT_LESSON == projectType) {
            orgServiceManage.updateLessonServiceStatus(code, status);
            return ajaxJson;
        }

        if ((ProjectType.PROJECT_VISIT == projectType) || ProjectType.PROJECT_TOSTORE == projectType) {
            orgServiceManage.updateVisitServiceStatus(code, status);
            return ajaxJson;
        }

        return ajaxJson;
    }

    /**
     * @Description: 获取机构服务师列表
     * @Author: wenxian.cai
     * @Date: 2017/5/18 19:44
     */
    @RequestMapping(value = "/listOrgUser")
    @ResponseBody
    public AjaxJson listOrgUser(
        @RequestParam(value = "map", required = false)
    Map map) {
        AjaxJson ajaxJson = new AjaxJson();
        int orgId = getLoginUser().getOrgId();
        List<TOrgUser> list = orgUserService.getEmployList2(1, 100, 0, 1, orgId);
        ajaxJson.setObj(list);
        System.out.println("list:" + list);

        return ajaxJson;
    }

    /**
     * @Description: 发布服务页面
     * @Author: wenxian.cai
     * @Date: 2017/5/22 15:43
     */
    @RequestMapping(value = "/publishservice/{type}")
    public ModelAndView publishOnlineService(@PathVariable String type) {
        LoginUser user = getLoginUser();
        String url = null;
        Integer projectType = null;

        switch (type) {
        case "consult":
        case "lesson":
            url = "platform/org/servicemanage/publish-online-service";
            break;
        case "offline":
        case "visit":
            url = "platform/org/servicemanage/publish-visit-service";
            break;
        }

        List<ServeTypeDTO> list = orgServiceManage.listServe();
        ModelAndView modelAndView = new ModelAndView(url);
        modelAndView.addObject("serveType", JSON.toJSON(list));
        modelAndView.addObject("orgType", user.getType());
        modelAndView.addObject("projectType", type);
        modelAndView.addObject("project", "{}");

        return modelAndView;
    }

    /**
     * @Description: 项目预览
     * @Author: wenxian.cai
     * @Date: 2017/5/22 16:49
     */
    @RequestMapping(value = "/preview")
    public ModelAndView previewService(@RequestParam(value = "code")
    String code) {
        ModelAndView modelAndView = new ModelAndView(
                "platform/org/servicemanage/preview-visit-service");
        Integer orgId = getLoginUser().getOrgId();
        VisitServiceDTO service = orgServiceManage.getVisitService(orgId, code);
        OrgProfileDTO org = managerOrgService.getOrgInfo(orgId);
        modelAndView.addObject("org", JSON.toJSON(org));
        modelAndView.addObject("combo", JSON.toJSON(service.getCombo()));

        if (StringUtils.isNotBlank(service.getIntroduce())) {
            service.setIntroduce(service.getIntroduce().replace("\n", ""));
        }

        modelAndView.addObject("serviceDescription", service.getIntroduce());
        service.setIntroduce(null);
        service.setCombo(null);
        modelAndView.addObject("service", JSON.toJSON(service));

        return modelAndView;
    }

    /**
     * @Description: 得到单条健康课堂信息（按环信ID）
     * @Author: wenxian.cai
     * @Date: 2017/7/20 14:50
     */
    @RequestMapping(value = "/get-lesson-by-huanxinId")
    @ResponseBody
    public AjaxJson getLessonByHuanxinId(
        @RequestParam(value = "huanxinId")
    String huanxinId) {
        AjaxJson ajaxJson = new AjaxJson();
        LessonPO lessonPO = lessonService.findLessonByHuanxinId(huanxinId);
        ajaxJson.setObj(lessonPO);

        return ajaxJson;
    }

    /**
     * 修改项目
     * @param type
     * @param code
     * @return
     */
    @RequestMapping(value = "/modify-service/{type}")
    public ModelAndView modifyService(@PathVariable
    String type, @RequestParam(value = "code")
    String code) {
        LoginUser user = getLoginUser();

        //todo 1.判断该项目是否属于该机构 2.判断该用户是否有权限修改项目
        ModelAndView modelAndView = null;
        Integer projectType = null;

        switch (type) { //对应projectType
        case "1": //健康咨询
            modelAndView = new ModelAndView(
                    "platform/org/servicemanage/publish-online-service");

            ConsultPO consultPO = consultService.getConsult(code);
            modelAndView.addObject("project", JSON.toJSON(consultPO));
            modelAndView.addObject("projectType", "consult");
            projectType = 1;

            break;

        case "3": //居家养老
            modelAndView = new ModelAndView(
                    "platform/org/servicemanage/publish-visit-service");

            VisitServiceDTO service = orgServiceManage.getVisitService(user.getOrgId(),
                    code);
            modelAndView.addObject("combo", JSON.toJSON(service.getCombo()));

            if (StringUtils.isNotBlank(service.getIntroduce())) {
                service.setIntroduce(service.getIntroduce().replace("\n", ""));
            }

            modelAndView.addObject("serviceDescription",
                service.getIntroduce());
            service.setIntroduce(null);
            service.setCombo(null);
            modelAndView.addObject("project", JSON.toJSON(service));
            modelAndView.addObject("projectType", "visit");
            projectType = 3;

            break;

        case "4": //健康课堂
            modelAndView = new ModelAndView(
                    "platform/org/servicemanage/publish-online-service");

            LessonPO lessonPO = lessonService.getLesson(code);

            modelAndView.addObject("lessonTime",
                JSON.toJSON(lessonPO.getLessonTime()));
            lessonPO.setLessonTime(null);
            modelAndView.addObject("project", JSON.toJSON(lessonPO));
            modelAndView.addObject("projectType", "lesson");
            projectType = 1;

            break;

        case "2": //健康养生
            modelAndView = new ModelAndView(
                    "platform/org/servicemanage/publish-visit-service");

            VisitServiceDTO service1 = orgServiceManage.getVisitService(user.getOrgId(),
                    code);
            modelAndView.addObject("combo", JSON.toJSON(service1.getCombo()));

            if (StringUtils.isNotBlank(service1.getIntroduce())) {
                service1.setIntroduce(service1.getIntroduce()
                                                .replace("\n", ""));
            }

            modelAndView.addObject("serviceDescription",
                service1.getIntroduce());
            service1.setIntroduce(null);
            service1.setCombo(null);
            modelAndView.addObject("project", JSON.toJSON(service1));
            modelAndView.addObject("projectType", "offline");
            projectType = 2;

            break;

        default:
            modelAndView = new ModelAndView("common/404");

            break;
        }

        List<ServeTypeDTO> list = orgServiceManage.listServe();
        List<ServiceOrgUserRelationDTO> orgUsers = projectService.findServiceOrgUserRelationList(code);
        modelAndView.addObject("isModify", true);
        modelAndView.addObject("orgType", user.getType());
        modelAndView.addObject("serveType", JSON.toJSON(list));
        modelAndView.addObject("type", type);
        modelAndView.addObject("orgUsers", JSON.toJSON(orgUsers));

        return modelAndView;
    }

    /**
     * 更新课堂服务项目
     * @param service
     * @return
     */
    @RequestMapping(value = "/update-service/lesson")
    @ResponseBody
    public AjaxJson updateLessonService(
        @RequestBody(required = false)
    LessonServiceDTO service) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();

        // 将图片从tmp目录转移到upload下
        if (StringUtils.isNotBlank(service.getImage())) {
            try {
                String newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getImage(),
                        "service");
                service.setImage(newRelativePath);
            } catch (IOException e) {
                ajaxJson.setMsg("操作失败");
                ajaxJson.setSuccess(false);

                return ajaxJson;
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
            logger.error(op.getMessage());
            ajaxJson.setMsg(op.getMessage());
            ajaxJson.setSuccess(false);

            return ajaxJson;
        }

        return ajaxJson;
    }

    /**
     * 更新咨询服务项目
     * @param service
     * @return
     */
    @RequestMapping(value = "/update-service/consult")
    @ResponseBody
    public AjaxJson updateConsultService(
        @RequestBody(required = false)
    ConsultServiceDTO service) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();

        // 将图片从tmp目录转移到upload下
        if (StringUtils.isNotBlank(service.getImage())) {
            try {
                String newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getImage(),
                        "service");
                service.setImage(newRelativePath);
            } catch (IOException e) {
                ajaxJson.setMsg("操作失败");
                ajaxJson.setSuccess(false);

                return ajaxJson;
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
            logger.error(op.getMessage());
            ajaxJson.setMsg(op.getMessage());
            ajaxJson.setSuccess(false);

            return ajaxJson;
        }

        return ajaxJson;
    }

    /**
     * 更新线下、上门服务项目
     * @param service
     * @return
     */
    @RequestMapping(value =  {
        "/update-service/visit", "/update-service/offline"}
    )
    @ResponseBody
    public AjaxJson updateVisitService(@RequestBody(required = false)VisitServiceDTO service) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();

        // 将图片从tmp目录转移到upload下
        try {
            service.setIntroduce(ImageUtilV2.regexPathFromHtml(StringUtil.filterHtml(service.getIntroduce())));

            String newRelativePath = null;

            if (StringUtils.isNotBlank(service.getMedia().getPictureOne())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia().getPictureOne(),"service");
                //ImageUtilV2.delImg(service.getMedia().getPictureOne());
                service.getMedia().setPictureOne(newRelativePath);
                //将第一张图片存为产品图片
                service.setImage(newRelativePath);
            }

            if (StringUtils.isNotBlank(service.getMedia().getPictureTwo())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia().getPictureTwo(),"service");
                //ImageUtilV2.delImg(service.getMedia().getPictureTwo());
                service.getMedia().setPictureTwo(newRelativePath);

                if (service.getImage() == null) {
                    service.setImage(newRelativePath);
                }
            }

            if (StringUtils.isNotBlank(service.getMedia().getPictureThree())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia().getPictureThree(),"service");
                //ImageUtilV2.delImg(service.getMedia().getPictureThree());
                service.getMedia().setPictureThree(newRelativePath);

                if (service.getImage() == null) {
                    service.setImage(newRelativePath);
                }
            }

            if (StringUtils.isNotBlank(service.getMedia().getPictureFour())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(service.getMedia().getPictureFour(),"service");
                //ImageUtilV2.delImg(service.getMedia().getPictureFour());
                service.getMedia().setPictureFour(newRelativePath);

                if (service.getImage() == null) {
                    service.setImage(newRelativePath);
                }
            }
        } catch (Exception e) {
            logger.info("更新线下、上门服务：移动图片失败");
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);

            return ajaxJson;
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
            visitService.updateVisit(visitPO, service.getOrgUser(),
                service.getCombo(), service.getMedia());
        } catch (OperationException op) {
            logger.error(op.getMessage());
            ajaxJson.setMsg(op.getMessage());
            ajaxJson.setSuccess(false);

            return ajaxJson;
        }

        return ajaxJson;
    }

    /**
     * 获取所有服务
     * @return
     */
    @RequestMapping(value = "/list-serve", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listServe() {
        AjaxJson ajaxJson = new AjaxJson();
        List<ServeTypeDTO> list = serveService.listFirstServeType();
        ajaxJson.setObj(list);

        return ajaxJson;
    }
}
