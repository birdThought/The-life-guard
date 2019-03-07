package com.lifeshs.service1.admin.impl;

import com.lifeshs.dao1.admin.permissionDao;
import com.lifeshs.dao1.user.AdminRoleDao;
import com.lifeshs.po.admin.AdminPermissionCheckedPO;
import com.lifeshs.po.admin.RolePermissionPo;
import com.lifeshs.po.admin.permissionPo;
import com.lifeshs.po.customer.CustomerRole;
import com.lifeshs.po.user.AdminRolePO;
import com.lifeshs.service1.admin.AdminRoleService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Administrator
 * @create 2018-02-09
 * 17:24
 * @desc
 */
@Service("adminRoleService")
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Autowired
    private permissionDao permissionDao;

    @Override
    public Paging<CustomerRole> getAdminRoleData(Integer curPage, Integer pageSize ,Integer dlsId) {
        /*return adminRoleDao.findByAdminRoleData(dlsId);*/
        Paging<CustomerRole> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<CustomerRole>() {
        	
            @Override
            public int queryTotal() {
            	return adminRoleDao.getRoleSize(dlsId);        
            }

            @Override
            public List<CustomerRole> queryData(int curPage, int pageSize) {
            		           	
                 List<CustomerRole> list = adminRoleDao.findByAdminRoleData(dlsId,curPage,pageSize);
                 
				return list; 
            }
        });
		return p;
        
    }

    @Override
    public Integer deleteAdmin(Integer id) {
        return adminRoleDao.delete(id);
    }

    @Override
    public Integer update(Integer id, String name , String remark) {
        return adminRoleDao.update(id,name,remark);
    }

    @Override
    public int saveAdmin(String name,String remark,Integer type) {
        return adminRoleDao.save(name,remark,type);
    }

    @Override
    public List<permissionPo> findByAllpermiss() {
        return permissionDao.findByAll();
    }

    @Override
    public List<CustomerRole> findByIdRole(Integer userId) {
        return adminRoleDao.findByIdRole(userId);
    }

	@Override
	public List<RolePermissionPo> queryRolePermission() {
		return adminRoleDao.queryRolePermission();
		 
	}

	@Override
	public Integer addRolePermission(String item, String operation,Integer roleId) {
		List<RolePermissionPo> list = adminRoleDao.queryRolePermission2(roleId);
		Integer number = 0;
		/*int x = 0;
		for (int i = 0; i < list.size(); i++) {
			RolePermissionPo rp = list.get(i);
			String[] sp = rp.getPermission().split(":");
			if(sp[0].equals(item)){
				x++;
			}
			
		}
		
		//判断有么权限item,没有直接添加
		if(x==0){
			String st = item+":"+operation;
			 number = adminRoleDao.insertPemission(roleId,st);
			return number;
		}*/
		
		switch (operation) {
		case "get":
			
			for (int i = 0; i < list.size(); i++) {
				RolePermissionPo rp = list.get(i);
				String[] sp = rp.getPermission().split(":");
				int id = rp.getId();	
				if(sp[0].equals(item)){
					//只有item,没有操作
					if(sp.length == 1){
						String str1 = rp.getPermission()+operation;
						number = adminRoleDao.updateRolePermission(id,str1);
					}else{
						//判断是否包含全部操作
						if(sp[1].contains("add")&&sp[1].contains("del")){
							//改为星。
							String str = item+":*";
							 number = adminRoleDao.updateRolePermission(id,str);
							 return number;
						}
							//直接添加项
							String str1 = rp.getPermission()+","+operation;
							 number = adminRoleDao.updateRolePermission(id,str1);
					}
					
															
				}
			}	
			break;
		case "add":			
			
			for (int i = 0; i < list.size(); i++) {
				RolePermissionPo rp = list.get(i);
				String[] sp = rp.getPermission().split(":");
				int id = rp.getId();	
				if(sp[0].equals(item)){
					
					if(sp.length == 1){
						String str1 = rp.getPermission()+operation;
						number = adminRoleDao.updateRolePermission(id,str1);
						
					}else{
						
						//判断是否包含全部操作，包含改为星
						if(sp[1].contains("get")&&sp[1].contains("del")){
							//改为星。
							String str = item+":*";						
							 number = adminRoleDao.updateRolePermission(id,str);
						}else{
							//直接添加项
							String str1 = rp.getPermission()+","+operation;
							 number = adminRoleDao.updateRolePermission(id,str1);
						}	
					}
					
								
				}
			}		
			break;
		case "del":			
			
			for (int i = 0; i < list.size(); i++) {
				RolePermissionPo rp = list.get(i);
				String[] sp = rp.getPermission().split(":");
				int id = rp.getId();	
				if(sp[0].equals(item)){
					
					if(sp.length == 1){
						String str1 = rp.getPermission()+operation;
						number = adminRoleDao.updateRolePermission(id,str1);
					}else{
						//判断是否包含全部操作
						if(sp[1].contains("add") && sp[1].contains("get")){
							//改为星。
							String str = item+":*";
							 number = adminRoleDao.updateRolePermission(id,str);
						}else {
							//直接添加项
							String str1 = rp.getPermission()+","+operation;
							 number = adminRoleDao.updateRolePermission(id,str1);
						}	
					}
					
									
				}
			}
						
			break;
		default:			
		
			for (int i = 0; i < list.size(); i++) {
				RolePermissionPo rp = list.get(i);
				String[] sp = rp.getPermission().split(":");
				int id = rp.getId();	
				if(sp[0].equals(item)){
					if(sp.length == 1) {
						String str1 = sp[0]+":"+operation;
						return  adminRoleDao.updateRolePermission(id,str1);
						
					}
					
					
					//判断是否包含全部操作
					if(sp[1].equals("*")){
						//不做操作		
						
					}else{//直接添加项						
						String str1 = rp.getPermission()+","+operation;
						number = adminRoleDao.updateRolePermission(id,str1);
					}					
				}
			}
			break;
		}
		return number;	
	};

	@Override
	public Integer delectRolePermission(String item, String operation,Integer roleId) {
		List<RolePermissionPo> list = adminRoleDao.queryRolePermission2(roleId);
		Integer number = 0;
		for (int i = 0; i < list.size(); i++) {
			RolePermissionPo rp = list.get(i);
			String[] sp = rp.getPermission().split(":");
			int id = rp.getId();		
			if(sp[0].equals(item)){
				if(sp[1].equals("*")){
					String str = "";
					//判断是否包含全部操作	
					switch (operation) {
					case "get":
						 str= sp[0]+":add,del";
						  number = adminRoleDao.updateRolePermission(id,str);			
						break;
					case "add":			
						 str = sp[0]+":get,del";
						 number = adminRoleDao.updateRolePermission(id,str);
						break;
					case "del":			
						 str = sp[0]+":add,get";
						 number = adminRoleDao.updateRolePermission(id,str);			
						break;
					default:			
						 str = sp[0]+":get";
						 number = adminRoleDao.updateRolePermission(id,str);
						break;
					}	
				}else{
					
					if(sp[1].split(",").length>1){
						String replace = rp.getPermission().replaceAll(operation, "");
						if(replace.endsWith(",")){
							replace = replace.substring(0,replace.length() - 1);
						}
						
						String[] str = replace.split(":");
						if(str[1].startsWith(",")){							
							replace = str[0]+":"+str[1].substring(1, str[1].length());
						}
						
						 number = adminRoleDao.updateRolePermission(id,replace);
					}else{
						/*String st = item+":"+operation;*/
						number = adminRoleDao.delRolePermission(id,sp[0]+":get");
						
						
						
					}
							
				}
			}
			
			

		}
		return number;
		

		
	}

	@Override
	public Integer delRolePermission(String item) {
		item = item+":";
		return adminRoleDao.delRolePermissionByItem(item);
	}

	@Override
	public Integer delRolePermissionByRoleId(Integer id) {
		// TODO Auto-generated method stub
		return adminRoleDao.delRolePermissionByRoleId(id);
	}

	@Override
	public Integer addRolePermission2(String item, Integer roleId) {
		item = item+":get";
		return adminRoleDao.addRolePermission2(item,roleId);
	}

	@Override
	public Integer delectRolePermission2(String item, Integer roleId) {
		item = item+":";
		return adminRoleDao.delectRolePermission2(item,roleId);
	}

	

	

	
	
	
	
	
}