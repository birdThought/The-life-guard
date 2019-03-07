package com.lifeshs.controller.org.employee;

import com.alibaba.fastjson.JSON;

import com.lifeshs.common.model.AjaxJson;

import com.lifeshs.controller.common.BaseController;

import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.employee.EmployeeRegisterDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service.org.employee.IEmployeeManageService;
import com.lifeshs.service.org.impl.user.OrgUser;

import com.lifeshs.utils.image.ImageUtilV2;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * 员工管理
 *
 * @author wenxian.cai
 * @create 2017-06-02 15:15
 **/
@RestController
@RequestMapping(value = "org/employee")
public class EmployeeManageController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmployeeManageController.class);
    final static int EMPLOYEE_PAGE_SIZE = 100;
    @Autowired
    private IEmployeeManageService employeeManageService;
    @Autowired
    private OrgUser orgUser;

    /**
     * @Description: 员工管理界面
     * @Author: wenxian.cai
     * @Date: 2017/6/2 17:20
     */
    @RequestMapping()
    public ModelAndView employeeManage() {
        ModelAndView modelAndView = new ModelAndView(
                "platform/org/employeemanage/employee-manage");
        int orgId = getLoginUser().getOrgId();
        PaginationDTO<EmployeeDTO> paginationDTO = employeeManageService.listEmployee(orgId,
                null, 1, EMPLOYEE_PAGE_SIZE);
        modelAndView.addObject("employee", JSON.toJSON(paginationDTO));
        
        return modelAndView;
    }

    /**
     * @Description: 获取员工列表
     * @Author: wenxian.cai
     * @Date: 2017/7/17 18:07
     */
    @RequestMapping(value = "list-employee")
    @ResponseBody
    public AjaxJson listEmployee() {
        AjaxJson ajaxJson = new AjaxJson();
        int orgId = getLoginUser().getOrgId();
        PaginationDTO<EmployeeDTO> paginationDTO = employeeManageService.listEmployee(orgId,
                null, 1, EMPLOYEE_PAGE_SIZE);
        ajaxJson.setObj(paginationDTO);

        return ajaxJson;
    }

    /**
     * @Description: 更新员工
     * @Author: wenxian.cai
     * @Date: 2017/6/6 17:27
     */
    @RequestMapping(params = "updateemployee")
    @ResponseBody
    public AjaxJson updateEmployee(
        @RequestParam(value = "orgUserId")
    Integer orgUserId,
        @RequestParam(value = "status", required = false)
    Integer status) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean bool = employeeManageService.updateEmployee(orgUserId, status);

        if (!bool) {
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);

            return ajaxJson;
        }

        return ajaxJson;
    }

    /**
     * @Description: 添加员工
     * @Author: wenxian.cai
     * @Date: 2017/6/7 10:02
     */
    @RequestMapping(value = "/addemployee")
    public ModelAndView addEmployee() {
        ModelAndView modelAndView = new ModelAndView(
                "platform/org/employeemanage/employee-add");

        //todo 判断该用户是否有权限添加员工
        return modelAndView;
    }

    @RequestMapping(params = "addemployee")
    @ResponseBody
    public AjaxJson addEmployee(@RequestBody
    EmployeeRegisterDTO employee) {
        AjaxJson ajaxJson = new AjaxJson();

        //todo 判断员工信息的完整性、正确性
        boolean isExist = orgUser.userIsExist(employee.getUserName());

        if (isExist) {
            ajaxJson.setMsg("账户已存在");
            ajaxJson.setSuccess(false);

            return ajaxJson;
        }

        try {
            String newRelativePath = null;

            if (StringUtils.isNotBlank(employee.getPhoto())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(employee.getPhoto(),
                        "photo");
                ImageUtilV2.delImg(employee.getPhoto());
                employee.setPhoto(newRelativePath);
            }

            if (StringUtils.isNotBlank(employee.getIdCardPicOne())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(employee.getIdCardPicOne(),
                        "photo");
                ImageUtilV2.delImg(employee.getIdCardPicOne());
                employee.setIdCardPicOne(newRelativePath);
            }

            if (StringUtils.isNotBlank(employee.getIdCardPicTwo())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(employee.getIdCardPicTwo(),
                        "photo");
                ImageUtilV2.delImg(employee.getIdCardPicTwo());
                employee.setIdCardPicTwo(newRelativePath);
            }

            if (StringUtils.isNotBlank(employee.getProfessionalPic())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(employee.getProfessionalPic(),
                        "photo");
                ImageUtilV2.delImg(employee.getProfessionalPic());
                employee.setProfessionalPic(newRelativePath);
            }
        } catch (Exception e) {
            logger.info("移动图片失败");
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);

            return ajaxJson;
        }

        int orgId = getLoginUser().getOrgId();
        employee.setOrgId(orgId);
        employee.setStatus(0);
        employee.setParentId(AgentConstant.AGENT_USER_TYPE_O + getLoginUser().getId());

        boolean bool = employeeManageService.addEmployee(employee);

        if (!bool) {
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);

            return ajaxJson;
        }

        return ajaxJson;
    }
    
    /**
     * @Description: 修改按钮返回的视图
     * @Author: zizhen.huang
     * @Date: 2017/12/5 15:32
     */
    @RequestMapping(value="toupdateemployee", method= {RequestMethod.GET})
    public ModelAndView toUpdateEmplyoee(@RequestParam(value="id") Integer id) {
    	EmployeeDTO employeeDTO = employeeManageService.getEmployee(id);
    	System.out.println("查询到的:"+employeeDTO);
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("employee", JSON.toJSON(employeeDTO));
    	modelAndView.setViewName("platform/org/employeemanage/employee-update");
    	return modelAndView;
    }
}
