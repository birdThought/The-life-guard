package com.lifeshs.service1.customer.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.customer.ICustomerUserDao;
import com.lifeshs.po.customer.CustomerUserOfflinePO;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.pojo.client.Client;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.tool.ICacheService;
import com.lifeshs.service1.admin.RoleperService;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.JSONHelper;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.vo.Menu.MenuVo;
import com.lifeshs.vo.customer.CustomerSharingDataVO;

/**
 * 客服用户业务实现
 * author: wenxian.cai
 * date: 2017/10/10 10:48
 */
@Service("customerUserService")
public class CustomerUserServiceImpl extends CommonServiceImpl implements CustomerUserService{

	@Autowired
	ICustomerUserDao customerUserDao;

	@Autowired
	ICacheService cacheService;
	@Autowired
    RoleperService roleperService;

	@Override
	public CustomerUserPO getUser(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerUserPO getUser(int userId) {
		return customerUserDao.getUser(userId);
	}

	@Override
	public CustomerUserPO getUserByUserName(String userName) {

		return customerUserDao.getUserByUserName(userName);
	}
	
	@Override
	public List<CustomerUserPO> getUserByParam(String id, String userNo, String userName){
	    return customerUserDao.getUserByParam(id, userNo, userName);
	}

	@Override
	public void saveUserSharingData(CustomerSharingDataVO po) throws OperationException {
		try {
			cacheService.saveKeyValue(CacheType.USER_SHARE_DATA, po.getId()+ "", po);
		} catch (Exception e) {
			throw new OperationException("缓存-新增用户出错", ErrorCode.OTHER);
		}
	}

	@Override
	public CustomerSharingDataVO getUserSharingData(int userId) {
		Object o = cacheService.getCacheValue(CacheType.USER_SHARE_DATA, String.valueOf(userId));
		if (o == null) {
		    // 如果找不到缓存信息，就重新更新缓存数据
		    try {
                updateUserSharingData(userId);
            } catch (OperationException e) {
                e.printStackTrace();
            }
		    o = cacheService.getCacheValue(CacheType.USER_SHARE_DATA, String.valueOf(userId));
		}
		return JSONHelper.toBean(o, CustomerSharingDataVO.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
	public void updateUserSharingData(int userId) throws OperationException {
		CustomerUserPO po = getUser(userId);
		CustomerSharingDataVO vo = new CustomerSharingDataVO();
		vo.setStatus(po.getStatus());
		vo.setCreateDate(po.getCreateDate());
		vo.setId(po.getId());
		vo.setUserNo(po.getUserNo());
		vo.setModifyDate(po.getModifyDate());
		vo.setName(po.getName());
		vo.setPhoto(po.getPhoto());
		vo.setUserCode(po.getUserCode());
		vo.setUserName(po.getUserName());
		vo.setAgentId(po.getAgentId());
		vo.setAgentNum(po.getAgentNum());
		try {
			cacheService.saveKeyValue(CacheType.USER_SHARE_DATA, po.getId()+ "", vo);
		} catch (Exception e) {
			throw new OperationException("缓存-更新用户出错", ErrorCode.OTHER);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
	public void updatePassword(int userId, String oldPassword, String newPassword) throws OperationException {
		CustomerUserPO user = customerUserDao.getUser(userId);
		if (user == null) {
			throw new OperationException("用户不存在", ErrorCode.UPDATE_NOT_ALLOW);
		}
		if (!user.getPassword().equals(MD5Utils.encryptPassword(oldPassword))) {
			throw new OperationException("旧密码不正确", ErrorCode.UPDATE_NOT_ALLOW);
		}
		try {
			customerUserDao.updatePassword(userId, MD5Utils.encryptPassword(newPassword));
		} catch (Exception e) {
			throw new OperationException("修改密码出错", ErrorCode.UPDATE_NOT_ALLOW);
		}

	}

    @Override
    public List<CustomerUserPO> listUser(List<String> userCodeList) {
        return customerUserDao.findUserListByUserCodeList(userCodeList);
    }
    
    @Override
	public boolean checkUserName(String userName) {
		CustomerUserPO customerUserPO = customerUserDao.getUserByUserName(userName);
		if(customerUserPO!=null) {
			return true;
		} else {
			return false;
		}
	}

    @Override
	public boolean addUser(CustomerUserPO customerUserPO) {
		// 代理商不需要注册环信
		if(StringUtils.isNotBlank(customerUserPO.getParentId())){
			customerUserPO.setPassword(MD5Utils.encryptPassword(customerUserPO.getPassword()));
			int result = customerUserDao.addUser(customerUserPO);
			if(result < 0) {
                return false;
            }
            return true;
		}
    	String userCode = getOrgUserCode().getOrgUserCode();
        if (!registHxUser(userCode)) {// 注册环信失败
            return false;
        }
        customerUserPO.setUserCode("kf"+userCode);
        customerUserPO.setPassword(MD5Utils.encryptPassword(customerUserPO.getPassword()));
        int result = customerUserDao.addUser(customerUserPO);
        if(result < 0) {
            return false;
        }
        return true;
	}
    
	@Override
	public Paging<CustomerUserPO> findUserList(int curPage, int pageSize) {
		Paging<CustomerUserPO> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<CustomerUserPO>() {

			@Override
			public int queryTotal() {
				return customerUserDao.getUserTotalRecord();
			}

			@Override
			public List<CustomerUserPO> queryData(int startRow, int pageSize) {
				return customerUserDao.findUserList(startRow, pageSize);
			}
		});
		return p;
	}
	
//	@Override
//    public Paging<CustomerUserPO> findMemberOfflineList(int agentId, String userName, String name, String mobile, int curPage, int pageSize) {
//        Paging<CustomerUserPO> p = new Paging<>(curPage, pageSize);
//        p.setQueryProc(new IPagingQueryProc<CustomerUserPO>() {
//
//            @Override
//            public int queryTotal() {
//                return customerUserDao.findMemberOfflineCount(agentId,userName,name,mobile);
//            }
//
//            @Override
//            public List<CustomerUserPO> queryData(int startRow, int pageSize) {
//                return customerUserDao.findMemberOfflineList(agentId,userName,name,mobile,startRow, pageSize);
//            }
//        });
//        return p;
//    }
	
	

	@Override
	public Client saveCustomerUserRecord(String userName) {
        CustomerUserPO user = getUserByUserName(userName);
        LoginUser loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setUserNo(user.getUserNo());
        loginUser.setAgentId(user.getAgentId());
        loginUser.setAgentNum(user.getAgentNum());
        loginUser.setRealName(user.getName());
        Client client = new Client();
        client.setUser(loginUser);
        client.setLogindatetime(new Date());
        List<Map<String, Object>> roleMenu = roleperService.findByRoleMenu(user.getAgentId());
        Map<String, List<MenuVo>> menuMap = new LinkedHashMap<>();
        for (Map<String,Object> map: roleMenu) {
            String menuRealm = (String) map.get("menuRealm");
            List<MenuVo> menus;
            if (!menuMap.containsKey(menuRealm)){
                menus = new ArrayList<>();
                menuMap.put(menuRealm, menus);
            }
            menus = menuMap.get(menuRealm);
            menus.add(new MenuVo((String) map.get("path"),(String)map.get("name"),(Integer) map.get("weight")));
        }
        /**
         *  组合排序
         */

        for (Map.Entry<String,List<MenuVo>> entry:menuMap.entrySet()) {
            Collections.sort(entry.getValue(), new Comparator<MenuVo>() {
                @Override
                public int compare(MenuVo o1, MenuVo o2) {
                    int x =o1.getWeight() == null ? 0 : o1.getWeight();
                    int y =o2.getWeight() == null ? 0 : o2.getWeight();
                    return x - y;
                }
            });
        }
        client.setMenu(menuMap);
       return client;
    }
	
	@Override
    public Paging<CustomerUserOfflinePO> findMemberOfflineList(String userNo,String realName,String mobile,int curPage, int pageSize) {
        
        Paging<CustomerUserOfflinePO> p =new Paging<>(curPage,pageSize);
        p.setQueryProc(new IPagingQueryProc<CustomerUserOfflinePO>() {

            @Override
            public int queryTotal() {
                // TODO Auto-generated method stub
                return customerUserDao.countMemberOffline(userNo, realName, mobile);
            }

            @Override
            public List<CustomerUserOfflinePO> queryData(int startRow, int pageSize) {
                return customerUserDao.findMemberOfflineList(userNo,realName,mobile,startRow, pageSize);
            }
        });
        return p;
    }
}
