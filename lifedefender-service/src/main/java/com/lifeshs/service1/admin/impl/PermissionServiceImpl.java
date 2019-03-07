package com.lifeshs.service1.admin.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lifeshs.dao1.admin.AdminPermissionDao;
import com.lifeshs.po.admin.AdminPermissionOperation;
import com.lifeshs.po.admin.AdminPermissionPO;
import com.lifeshs.po.admin.OperationCheckedPO;
import com.lifeshs.po.admin.RolePermissionOperationPo;
import com.lifeshs.po.admin.RolePermissionPo;
import com.lifeshs.service1.admin.PermissionService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;


@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    AdminPermissionDao adminPermissionDao;
    
    @Override
    public Paging<RolePermissionOperationPo> getPermissionlist(int curPage, int pageSize , Integer agent) {
    	List<RolePermissionOperationPo> RolePermissionOperationPoList = adminPermissionDao.getPermissionAndOperationList(curPage, pageSize ,agent);
    	
        Paging<RolePermissionOperationPo> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<RolePermissionOperationPo>() {
        	
            @Override
            public int queryTotal() {
                
                return RolePermissionOperationPoList.size();
            }

            @Override
            public List<RolePermissionOperationPo> queryData(int curPage, int pageSize) {
            		if(curPage !=1 ){
            			pageSize = curPage+pageSize;
                    	if (RolePermissionOperationPoList.size() < pageSize) {
        					pageSize = RolePermissionOperationPoList.size();
        				}
            		}
            		
            	
            	
            	
            	
            	List<RolePermissionOperationPo> list = new ArrayList<>(); 
            	for (int i = curPage; i < pageSize; i++) {
            		list.add(RolePermissionOperationPoList.get(i));			
            		
				}
            	
                return list;
            }

			
        });
        return p;
    }

    @Override
    public AdminPermissionPO getPermissionById(int id) {
        // TODO Auto-generated method stub
        return adminPermissionDao.getPermissionById(id);
    }

    @Override
    public Set<String> getPermissionByRoles(Set<Integer> idList) {
        return adminPermissionDao.getPermissionListByRoles(idList);
    }

    @Override
    public Set<String> getPermissionBySuper() {
        return adminPermissionDao.getPermissionBySuper();
    }

    @Override
    public List<OperationCheckedPO> getOperationList(int id, int permissionId) {
        // TODO Auto-generated method stub
        return adminPermissionDao.getOperationList(id, permissionId);
    }
    
  //获取操作列表
    public List<AdminPermissionPO> getOperationByPermissionId(int id, int permissionId){
        return adminPermissionDao.getOperationByPermissionId(id, permissionId);
    }
    
    @Override
    public int updatePermissionById(AdminPermissionPO permission) {
        // TODO Auto-generated method stub
        return adminPermissionDao.updatePermissionById(permission);
    }
    
    @Override
    public int addOperation(AdminPermissionOperation operation){
        return adminPermissionDao.addOperation(operation);
    }
    
    @Override
    public int addPermission(AdminPermissionPO permission){
        return adminPermissionDao.addPermission(permission);
    }

    @Override
    public int delOperationByPermissionId(int permissionId) {
        // TODO Auto-generated method stub
        return adminPermissionDao.delOperationByPermissionId(permissionId);
    }

    @Override
    public int addOperationList(List<AdminPermissionOperation> datas) {
        // TODO Auto-generated method stub
        return adminPermissionDao.addOperationList(datas);
    }

    @Override
    public int delPermissionById(int id) {
        // TODO Auto-generated method stub
        return adminPermissionDao.delPermissionById(id);
    }

	@Override
	public int delAnOperation(AdminPermissionOperation operation) {
		return adminPermissionDao.delAnOperation(operation);
	}

	@Override
	public int delOperationList(Integer permission, String[] operationArr) {
		return adminPermissionDao.delOperationList(permission,operationArr);
		
	}

	@Override
	public int delRolePermission(String[] operationArr, String item, Integer roleId) {
		  int a = 0;
		  List<RolePermissionPo> list = adminPermissionDao.getRolePermissionByRoleId(roleId);
		  String newStr = null;
		  int id = 0;
		  if(operationArr ==null || operationArr.length ==0) {
			  return a;
		  }
		  
		  if(list !=null && list.size()!= 0 ){
			  for (int i = 0; i < list.size(); i++) {
				  String permission = list.get(i).getPermission();
				  if(permission.contains(item+":")){
					  id = list.get(i).getId();
					  newStr = list.get(i).getPermission();
					  String[] sp = permission.split(":");
					  if(sp.length == 1) {
						  continue;
					  }
					  String st = sp[1];
					  for (int j = 0; j < operationArr.length; j++) {
						  if(sp[1].contains(operationArr[j])){
							  
							  st = st.replaceAll(operationArr[j], "");
							 /* if(st.startsWith(",")){
								   st = st.substring(1, st.length());
							  }
							  
							  if(st.endsWith(",")){
								   st = st.substring(0, st.length()-1);
							  }*/
							   
						  }						  						
					}
					 String st1 = "";
					 String[] strings = st.split(","); 
					 for (int j = 0; j < strings.length; j++) {
						if(strings[j]!=""){
							st1 = st1+strings[j];
						}
					}
					  
					  
					  
					  newStr = item+":"+st1;
					  a =  adminPermissionDao.updataRolePermissionById(id,newStr);
					 			  
				  }
			  }
		  }
		  		 
		 return a;
	}

}
