package com.lifeshs.customer.controller.account;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.admin.AdminPermissionCheckedPO;
import com.lifeshs.po.admin.RolePermissionPo;
import com.lifeshs.po.customer.CustomerRole;
import com.lifeshs.po.user.AdminRolePO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.service1.admin.AdminRoleService;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.customer.CustomerSharingDataVO;

import aj.org.objectweb.asm.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-02-09
 * 17:02      角色管理
 * @desc
 */
@Controller("roleController")
@RequestMapping("/role")
public class RoleController extends BaseController{

    private static final String DATA = "data";
    
    private static final int ADMIN_PERMISSION_PAGE_SIZE = 10;


    @Autowired
    private AdminRoleService adminRoleService;

    @RequestMapping("/page")
    public ModelAndView getCharacter(){
        ModelAndView mvc = new ModelAndView("platform/Character/rolesManage");
        return mvc;
    }

    @RequestMapping(value = "/data")
    public @ResponseBody AjaxJson getRoleData(Integer page){
        AjaxJson ajaxJson = new AjaxJson();
        CustomerSharingDataVO userSharing = getUserSharingData();
        if (userSharing.getAgentId() != 0) {   //代理商、业务员
            /*List<CustomerRole> adminRoleData = adminRoleService.getAdminRoleData(userSharing.getAgentId());*/
            Paging<CustomerRole> paging = adminRoleService.getAdminRoleData(page, ADMIN_PERMISSION_PAGE_SIZE,userSharing.getAgentId());
	        
     
            ajaxJson.setObj(paging.getPagination());
            return ajaxJson;
        }else {
                /*List<CustomerRole> adminRole = adminRoleService.getAdminRoleData(null);*/
        	Paging<CustomerRole> paging = adminRoleService.getAdminRoleData(page, ADMIN_PERMISSION_PAGE_SIZE,null);
	        
                ajaxJson.setObj(paging.getPagination());
                return ajaxJson;
        }
    }

    @RequestMapping(value = "/del/{id}")
    public @ResponseBody AjaxJson delId(@PathVariable("id")Integer id){
        AjaxJson ajaxJson = new AjaxJson();
        
        Integer integer = adminRoleService.deleteAdmin(id);
        integer = adminRoleService.delRolePermissionByRoleId(id);
        
        return ajaxJson;
    }

    @RequestMapping(value = "/select/{data}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson updateData(@PathVariable("data")String data,@RequestParam(value = "id",required = false)Integer id,@RequestParam("name") String name,@RequestParam("remark") String remark){
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(false);
        switch (data){
            case "update":
                Integer update = adminRoleService.update(id, name,remark);
                if (update != 0) {
                    ajaxJson.setSuccess(true);
                    return ajaxJson;
                }
            break;
            case "save":
                
            	int i ;
                CustomerSharingDataVO userSharing = getUserSharingData();
                if (userSharing.getAgentId() != 0) {   //代理商、业务员
                	Integer type = 1; 
                	 i = adminRoleService.saveAdmin(name,remark,type);
                }else{
                	Integer type = 0; 
                	 i = adminRoleService.saveAdmin(name,remark,type);
                }
                
                
                
                
                if (i != 0){
                    ajaxJson.setSuccess(true);
                    return ajaxJson;
                }
            break;
        }

        return ajaxJson;
    }
    
    
    @RequestMapping("queryRolePermission")
    public @ResponseBody AjaxJson queryRolePermission(){
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(true);
        List<RolePermissionPo> RolePermissionList = adminRoleService.queryRolePermission();
        ajaxJson.setObj(RolePermissionList);
        return ajaxJson;
    }
    
    
    @RequestMapping("updateRolePermission")
    public @ResponseBody AjaxJson updateRolePermission(String item,String operation,boolean judge,Integer roleId){
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(true);
        if(judge){
        	adminRoleService.addRolePermission(item,operation,roleId);
        	return ajaxJson;
        }
        
        adminRoleService.delectRolePermission(item,operation,roleId);
        return ajaxJson;
    }
    
    @RequestMapping("updateRolePermission2")
    public @ResponseBody AjaxJson updateRolePermission2(String item,boolean judge,Integer roleId){
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(true);
        if(judge){
        	adminRoleService.addRolePermission2(item,roleId);
        	return ajaxJson;
        }
        
        adminRoleService.delectRolePermission2(item,roleId);
        return ajaxJson;
    }
    
    
}
