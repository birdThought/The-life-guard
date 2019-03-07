package com.lifeshs.service1.admin.impl;

import com.lifeshs.dao1.admin.RoleperDao;
import com.lifeshs.po.admin.AdminPermissionCheckedPO;
import com.lifeshs.po.admin.AdminPermissionPO;
import com.lifeshs.po.admin.RolePermissionOperationPo;
import com.lifeshs.po.admin.RolePermissionPo;
import com.lifeshs.po.admin.RoleperPO;
import com.lifeshs.po.admin.permissionPo;
import com.lifeshs.po.customer.CustomerRole;
import com.lifeshs.service1.admin.RoleperService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Administrator
 * @create 2018-02-26
 * 9:32
 * @desc
 */

@Service("roleperService")
public class RoleperServiceImpl implements RoleperService {

    @Resource(name = "roleperDao")
    private RoleperDao roleperDao;
    
   

    /**
     * 获取权限菜单
     * @return
     */
    @Override
    public List<AdminPermissionPO> findByAll() {
        List<AdminPermissionPO> permissions = roleperDao.findByAll();
      
        return permissions;
    }

    /**
     * 获取角色权限集
     * @param roleId
     * @return
     */
    @Override
    public List<RoleperPO> findByid(Integer roleId) {
        List<RoleperPO> maps = roleperDao.findById(roleId);
       
        return maps;
    }

    /**
     *  添加角色权限菜单或更新角色权限菜单
     * @param roleId
     * @param params
     * @return
     */
    @Override
    public boolean uptateRoleMenuData(Integer roleId, List<Map<String, Object>> params) {
        roleperDao.deleteRoleMenu(roleId);
        boolean b = false;
        for (Map<String,Object> map:params) {
         Map<String, Object> mmp  = null;
                mmp = map;
            b= roleperDao.addRoleMenu(roleId, mmp);
        }
        if (b){
            return b;
        }
        return false;
    }

    @Override
    public Set<String> getCustomerPermissionSet(Integer id) {
        List<RoleperPO> roleIdList = findByid(id);
        List<Integer> idlist = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (RoleperPO rp: roleIdList) {
            idlist.add(rp.getPerid());
            map.put(rp.getPerid(), rp.getParam());
        }
        List<permissionPo> permissionPoList = roleperDao.loadPermissionList(idlist);
        Set<String> permissionSet = new HashSet<>();
        for (permissionPo pso : permissionPoList) {
            permissionSet.add(pso.getMenu());
        }
        map.clear();
        idlist.clear();
        roleIdList.clear();
        permissionPoList.clear();
        return permissionSet;
    }

    @Override
    public List<Map<String, Object>> findByRoleMenu(Integer roleId) {
        return roleperDao.loadRoleMenu(roleId);
    }

	@Override
	public List<RolePermissionPo> findPermissionByid(Integer roleId) {
		
		return roleperDao.findPermissionByid(roleId);
	}

	@Override
	public Paging<AdminPermissionCheckedPO> findByAllOperation(Integer curPage,Integer pageSize, Integer agentId) {
		
		 Paging<AdminPermissionCheckedPO> p = new Paging<>(curPage, pageSize);
	        p.setQueryProc(new IPagingQueryProc<AdminPermissionCheckedPO>() {
	        	
	            @Override
	            public int queryTotal() {
	            	return roleperDao.getRoleSize(agentId);        
	            }

	            @Override
	            public List<AdminPermissionCheckedPO> queryData(int curPage, int pageSize) {
	            		           	
	                 List<AdminPermissionCheckedPO> list = roleperDao.findByAllOperation(agentId,curPage,pageSize);
	                 
					return list; 
	            }
	        });
			return p;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public void addRolePermission(Integer roleId, List<String> params) {
		for (int i = 0; i < params.size(); i++) {
			roleperDao.addRolePermission(roleId,params.get(i)+":");
		}
		
		
	}

	@Override
	public void deleteRoleMenu(Integer roleId) {
		roleperDao.deleteRoleMenu(roleId);
		
	}

	@Override
	public List<RolePermissionPo> getRolePermissionByItem(String item,String name) {
		item = item+":";
		return roleperDao.getRolePermissionByItem(item,name);
	}

	@Override
	public Integer updateRolePermissionById(int id, String st) {
		
		return roleperDao.updateRolePermissionById(id,st);
	}

	@Override
	public Paging<AdminPermissionCheckedPO> getPermissionlist(Integer curPage, int pageSize,Integer roleId,Integer agentId) {
		
        Paging<AdminPermissionCheckedPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<AdminPermissionCheckedPO>() {
        	
            @Override
            public int queryTotal() {
            	return roleperDao.getPermissionSize(agentId);               
            }

            @Override
            public List<AdminPermissionCheckedPO> queryData(int curPage, int pageSize) {
            		           	
                 List<AdminPermissionCheckedPO> list = roleperDao.getPermissionList(curPage,pageSize,agentId);
                 List<RolePermissionPo> permissionList = roleperDao.findPermissionByid(roleId);
               //添加勾选项
                 for(int x=0;x<permissionList.size();x++){
                 	for(int i=0;i<list.size();i++){		        		
         				if(permissionList.get(x).getPermission().split(":")[0].equals(list.get(i).getItem())){
         					list.get(i).setChecked(true);
         				}
         			}
         			
         		}
				return list; 
            }

			
        });
        return p;
	}

	

	

	
}
