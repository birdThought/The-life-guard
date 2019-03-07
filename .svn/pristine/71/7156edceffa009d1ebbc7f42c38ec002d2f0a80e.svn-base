package com.lifeshs.customer.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.admin.AdminPermissionCheckedPO;
import com.lifeshs.po.admin.AdminPermissionPO;
import com.lifeshs.po.admin.RolePermissionOperationPo;
import com.lifeshs.po.admin.RolePermissionPo;
import com.lifeshs.po.customer.CustomerRole;
import com.lifeshs.service1.admin.AdminRoleService;
import com.lifeshs.service1.admin.PermissionService;
import com.lifeshs.service1.admin.RoleperService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.customer.CustomerSharingDataVO;

import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @create 2018-02-10
 * 11:53  权限菜单页
 * @desc
 */
@RestController("permissionController")
@RequestMapping("/permiss")
public class permissionController extends BaseController{
	
	private static final int ADMIN_PERMISSION_PAGE_SIZE = 10;

    private final static String DATA ="data";

    @Autowired
    private RoleperService roleperService;

    @Autowired
    private AdminRoleService adminRoleService;
    
    

    @RequestMapping(value = "pagePermission")
    public AjaxJson getpermission(Integer page ,Integer roleId){
    	AjaxJson ajaxJson = new AjaxJson();
    	CustomerSharingDataVO userSharing = getUserSharingData();
        if (userSharing.getAgentId() != 0) {   //代理商、业务员
        	Paging<AdminPermissionCheckedPO> paging = roleperService.getPermissionlist(page, ADMIN_PERMISSION_PAGE_SIZE,roleId,userSharing.getAgentId());
	        
     
            ajaxJson.setObj(paging.getPagination());
            return ajaxJson;
        }else {
        	Paging<AdminPermissionCheckedPO> paging = roleperService.getPermissionlist(page, ADMIN_PERMISSION_PAGE_SIZE,roleId,null);
	        
                ajaxJson.setObj(paging.getPagination());
                return ajaxJson;
        }
    	
    	   	
    }
        
    

    @RequestMapping(value = "setRoleMenu/{roleId}")
    public @ResponseBody AjaxJson updateSaveRole(@PathVariable("roleId")Integer roleId, @RequestBody List<String> params){
        AjaxJson ajaxJson = new AjaxJson();
        roleperService.deleteRoleMenu(roleId);        
        roleperService.addRolePermission(roleId,params);                        
       /* boolean b = roleperService.uptateRoleMenuData(roleId, params);
        if (b){
            ajaxJson.setSuccess(true);
            return ajaxJson;
        }
        ajaxJson.setSuccess(false);*/
        return ajaxJson;
    }
}
