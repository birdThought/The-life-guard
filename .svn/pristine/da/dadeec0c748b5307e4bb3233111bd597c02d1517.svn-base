package com.lifeshs.customer.controller.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.beust.jcommander.Parameter;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.admin.AdminPermissionCheckedPO;
import com.lifeshs.po.admin.AdminPermissionOperation;
import com.lifeshs.po.admin.AdminPermissionPO;
import com.lifeshs.po.admin.OperationCheckedPO;
import com.lifeshs.po.admin.RolePermissionOperationPo;
import com.lifeshs.po.admin.RolePermissionPo;
import com.lifeshs.service1.admin.AdminRoleService;
import com.lifeshs.service1.admin.PermissionService;
import com.lifeshs.service1.admin.RoleperService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.customer.CustomerSharingDataVO;

@RestController
@RequestMapping(value = "/permission")
public class AdminPermissionController extends BaseController{
    private static final int ADMIN_PERMISSION_PAGE_SIZE = 10;
    
    static final Logger logger = Logger.getLogger(AdminPermissionController.class);

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private RoleperService roleperService;
    
    /**
     * 权限管理页面
     * 
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView ComboControllerPage() {
        return new ModelAndView("platform/permission/permission-manage");
    }
    
    /**
     * 
     *  获取权限列表
     *  @author liaoguo
     *  @DateTime 2018年6月28日 下午2:57:22
     *
     */
    @RequestMapping("/get/{page}")
    @ResponseBody
    public AjaxJson getPermissionList(@PathVariable("page") int page){
        AjaxJson ajaxJson = new AjaxJson();
        CustomerSharingDataVO userSharing = getUserSharingData();
        if (userSharing.getAgentId() != 0) {   //代理商、业务员
        	
        	Paging<RolePermissionOperationPo> paging = permissionService.getPermissionlist(page, ADMIN_PERMISSION_PAGE_SIZE,userSharing.getAgentId());
            ajaxJson.setObj(paging.getPagination());
        	
        }else{
        	
        	Paging<RolePermissionOperationPo> paging = permissionService.getPermissionlist(page, ADMIN_PERMISSION_PAGE_SIZE,null);
            ajaxJson.setObj(paging.getPagination());
        	
        }
        
        
        
               
        return ajaxJson;
        
    }
    
    /**
     * 
     *  根据id获取权限列表
     *  @author liaoguo
     *  @DateTime 2018年7月3日 上午11:11:34
     *
     *  @param id
     *  @return
     */
    @RequestMapping("/get/Id")
    @ResponseBody
    public AjaxJson getPermissionById(int id){
        AjaxJson ajaxJson = new AjaxJson(); 

        AdminPermissionPO adminPermissionPO = permissionService.getPermissionById(id);
        ajaxJson.setObj(adminPermissionPO);
        
        return ajaxJson;
    }
    
    /**
     * 
     *  获取所有操作
     *  @author liaoguo
     *  @DateTime 2018年7月2日 下午3:11:04
     *
     *  @param pId 权限id
     *  @return
     */
    /* @RequestMapping("/getOperation")
    @ResponseBody
    public AjaxJson getOperationByPermissionId(int pId){
        Map<String,Object> map = new HashMap<String,Object>();
        AjaxJson ajaxJson = new AjaxJson(); 
        
        //根据权限id获取权限
        List<AdminPermissionPO> operationList = permissionService.getOperationByPermissionId(pId, 0);
        //获取操作列表
        List<OperationCheckedPO> operationAll = permissionService.getOperationList(0, pId);
        
       //获取操作各项功能权限
        if(operationAll.size() > 0 && StringUtils.isNotBlank(operationList.get(0).getItem())){
            String[] projectStr =  operationList.get(0).getItem().split(":");
            if(projectStr.length > 1){
                //是否是所有权限
                if("*".contains(projectStr[1])){
                    for(int i=0;i<operationAll.size();i++){
                        OperationCheckedPO checkPO = operationAll.get(i);
                        checkPO.setChecked(true);
                    }
                }else{
                    //有选择的权限
                    String[] operationStr = projectStr[1].split(",");
                    for(int i=0;i<operationAll.size();i++){
                        OperationCheckedPO checkPO = operationAll.get(i);
                        for(int j=0;j<operationStr.length;j++){
                            if(checkPO.getOperation().equals(operationStr[j])){
                                checkPO.setChecked(true);
                            }
                        }
                    }
                }
            }
        }
        
        map.put("operationAll", operationAll);
        ajaxJson.setObj(map);
        return ajaxJson;
    }*/
    
//    /**
//     * 
//     *  修改或保存操作
//     *  @author liaoguo
//     *  @DateTime 2018年7月10日 下午18:04:16
//     *
//     *  @param id 权限id
//     *  @param name 权限名称
//     *  @param operationIds 操作按钮id
//     *  @param operationNames 操作按钮名称
//     *  @return
//     */
//    @RequestMapping("/saveOperation")
//    @ResponseBody
//    public AjaxJson saveOperation(AdminPermissionOperation operation){
//        AjaxJson ajaxJson = new AjaxJson(); 
//        
//        permissionService.addOperation(operation);
//        
//        return ajaxJson;
//    }
    
    /**
     * 
     *  修改或保存权限
     *  @author liaoguo
     *  @DateTime 2018年7月5日 下午3:57:16
     *
     *  @param id 权限id
     *  @param name 权限名称
     *  @param operationIds 操作按钮id
     *  @param operationNames 操作按钮名称
     *  @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxJson savePermission(AdminPermissionPO permission,String nameAll,String operationAll){
        AjaxJson ajaxJson = new AjaxJson();

        //判断是新增还是修改
        if(permission.getId() == 0){
            //新增
            permissionService.addPermission(permission);
        }else{
            //修改
            permissionService.delOperationByPermissionId(permission.getId());
            permissionService.updatePermissionById(permission);
        }
        
        String [] nameList = nameAll.split(",");
        String [] operationList = operationAll.split(",");
        List<AdminPermissionOperation> datas = new ArrayList<AdminPermissionOperation>();
        AdminPermissionOperation op = null;
        for(int i=0;i<nameList.length;i++){
            op = new AdminPermissionOperation();
            op.setName(nameList[i]);
            op.setOperation(operationList[i]);
            op.setPemissionId(permission.getId());
            datas.add(op);
        }
        permissionService.addOperationList(datas);
        
        return ajaxJson;
    }
    
    //增加权限
    @RequestMapping("/savePermission")
    @ResponseBody
    public AjaxJson savePermission(String name,String item){
    	 CustomerSharingDataVO userSharing = getUserSharingData();            	
    	 AjaxJson ajaxJson = new AjaxJson();
    	 AdminPermissionPO adminPermission = new AdminPermissionPO();
    	 adminPermission.setItem(item);
    	 adminPermission.setName(name);   
    	 
    	 if (userSharing.getAgentId() != 0) {   //代理商、业务员
    		 adminPermission.setHasAgent(1);
         }else{
        	 adminPermission.setHasAgent(0);
         }
    	 
    	 
    	 
    	 permissionService.addPermission(adminPermission);    	 
    	 return ajaxJson;
    }
    
    
    
    //修改权限
    @RequestMapping("/updatePermission")
    @ResponseBody
    public AjaxJson updatePermission(String id,String name,String item,String oldItem,String oldName){
    	 AjaxJson ajaxJson = new AjaxJson();
    	 AdminPermissionPO adminPermission = new AdminPermissionPO();
    	 adminPermission.setId(Integer.parseInt(id));
    	 adminPermission.setItem(item);
    	 adminPermission.setName(name);    	 
    	 permissionService.updatePermissionById(adminPermission);  
    	 List<RolePermissionPo>list = roleperService.getRolePermissionByItem(oldItem,oldName);
    	    	   	 
    	 if(list!=null && list.size()!=0){
    		 
    		 String st = null;
    		 for (int i = 0; i < list.size(); i++) {
    			     			 
    			 String[] split = list.get(i).getPermission().split(":");
    			 if(split.length == 1) {
    				 st = item+":";
    				  roleperService.updateRolePermissionById(list.get(i).getId(),st);
    				  
    			 }else {
    				 st = item+":"+split[1];
        			 roleperService.updateRolePermissionById(list.get(i).getId(),st);
    			 }
    			 
    		}
    		 
    	 }
    	 
    	 return ajaxJson;
    }
    

    
    /**
     * 
     *  删除权限
     *  @author liaoguo
     *  @DateTime 2018年7月5日 下午3:48:41
     *
     *  @param id
     *  @return
     */
    
    @RequestMapping("/del")
    @ResponseBody
    public AjaxJson delPermissionById(String[]itemIds){
        AjaxJson ajaxJson = new AjaxJson(); 
        for (int i = 0; i < itemIds.length; i++) {
			String[] st = itemIds[i].split("/");
			int id = Integer.parseInt(st[0]);
			String item = st[2];
			permissionService.delOperationByPermissionId(id);
		    permissionService.delPermissionById(id);
		   	adminRoleService.delRolePermission(item);
		}
       
        
        return ajaxJson;
    }
    
    /**
     * 添加操作
     * @param id
     * @return
     */
    @RequestMapping("/addOperation")
    @ResponseBody
    public AjaxJson addOperation(AdminPermissionOperation  operation){	
        AjaxJson ajaxJson = new AjaxJson(); 
        permissionService.addOperation(operation);
        return ajaxJson;
    }
    
    /**
     * 删除单个操作
     * @param id
     * @return
     */
    @RequestMapping("/delOperation")
    @ResponseBody
    public AjaxJson delOperation(int id,String name){
        AjaxJson ajaxJson = new AjaxJson(); 
        AdminPermissionOperation operation = new AdminPermissionOperation();
        operation.setName(name);
        operation.setPemissionId(id);
        permissionService.delAnOperation(operation);
        return ajaxJson;
    }
    /**
     * 获取操作列表
     * @param pId
     * @return
     */
    @RequestMapping("/getOperation")
    @ResponseBody
    public AjaxJson getOperationByPermissionId(int id){
        Map<String,Object> map = new HashMap<String,Object>();
        AjaxJson ajaxJson = new AjaxJson(); 
        
        //根据权限id获取权限
        List<AdminPermissionPO> operationList = permissionService.getOperationByPermissionId(id, 0);
        //获取操作列表
        List<OperationCheckedPO> operationAll = permissionService.getOperationList(0, id);
        map.put("operationAll", operationAll);
        ajaxJson.setObj(map);
		return ajaxJson;
        
    }
    
    
    
    
    @RequestMapping("/delOperationList")
    @ResponseBody
    public AjaxJson delOperationList(Integer delOperationId, String []operationArr,String roleId,String item){
        AjaxJson ajaxJson = new AjaxJson(); 
        Integer id = Integer.parseInt(roleId);
        int a = permissionService.delOperationList(delOperationId,operationArr);
        permissionService.delRolePermission(operationArr,item,id);
		return ajaxJson;
        
    }
    
    
    
    
    @RequestMapping("/delPermiss")
    public AjaxJson delPermiss(Integer page){
    	AjaxJson ajaxJson = new AjaxJson();     
    	
    	CustomerSharingDataVO userSharing = getUserSharingData();
        if (userSharing.getAgentId() != 0) {   //代理商、业务员
        	Paging<AdminPermissionCheckedPO> paging = roleperService.findByAllOperation(page, ADMIN_PERMISSION_PAGE_SIZE,userSharing.getAgentId());
	        
     
           ajaxJson.setObj(paging.getPagination());
            
        }else {
        	Paging<AdminPermissionCheckedPO> paging = roleperService.findByAllOperation(page, ADMIN_PERMISSION_PAGE_SIZE,null);
	        
        	ajaxJson.setObj(paging.getPagination());
             
        }
    	
        return ajaxJson;
        
        
        
        
    }
        
}
