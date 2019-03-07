package com.lifeshs.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.UserStatus;
import com.lifeshs.dao.common.IBaseUserDao;
import com.lifeshs.dao.member.IMemberDao;
import com.lifeshs.dao.org.user.OrgUserDao;
import com.lifeshs.entity.member.TUser;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.pojo.log.SensitiveOperationLogDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.service.common.IBaseUser;
import com.lifeshs.service.common.transform.ICommonTrans;

/**
 * 用户的基类
 * 
 * @author dengfeng 封装通用操作方法
 */
@Component("baseUser")
public abstract class BaseUserImpl implements IBaseUser {

    @Autowired
    protected ICommonTrans commonTrans;
    
    @Autowired
    private IBaseUserDao baseUserDao;

    @Autowired
    private IMemberDao memberDao;
    
    @Resource(name = "orgUserDao")
    private OrgUserDao orgUserDao;
    
    @Override
    public boolean addMember(UserDTO user) {
        int effectRowCount = memberDao.addUser(user);
        if (effectRowCount > 0) {
            return true;
        }
        return false;
    }

    @Override
    public <T> boolean basicInformation(T entity) {
        int result = commonTrans.updateEntitie(entity);
        if (result == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkEmail(String email) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("emailVerified", true);
        
        List<Map<String, Object>> users = commonTrans.findByMap(TUser.class, params);
        if(!users.isEmpty() && users.size() > 0) {
            return true;
        }
        
        List<Map<String, Object>> oUsers = commonTrans.findByMap(TOrgUser.class, params);
        if(!oUsers.isEmpty() && oUsers.size() > 0) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public boolean checkMobile(String mobile) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("mobileVerified", true);
        
        List<Map<String, Object>> users = commonTrans.findByMap(TUser.class, params);
        if(!users.isEmpty() && users.size() > 0) {
            return true;
        }
        
        List<Map<String, Object>> oUsers = commonTrans.findByMap(TOrgUser.class, params);
        if(!oUsers.isEmpty() && oUsers.size() > 0) {
            return true;
        }
        
        return false;
    }

    /**
     * 用户是否已存在（和机构用户一起判断）
     *  @author dengfeng
     *  @DateTime 2016-6-2 上午10:44:39
     *
     *  @param userName
     *  @return
     * @throws Exception 
     */
    public boolean userIsExist(String userName) {
        // 用户
        UserPO user = memberDao.findUserByUserName(userName);
        if (user == null) {
            // 机构用户
            OrgUserPO orgUser = orgUserDao.findUserByUserName(userName);
            if (orgUser == null) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 邮箱是否已存在（和机构用户邮箱一起判断）
     *  @author dengfeng
     *  @DateTime 2016-6-2 上午10:44:39
     *
     *  @param userName
     *  @return
     * @throws Exception 
     */
    public boolean emailIsExist(String email) throws Exception {
        return isExist("email", email);
    }

    /**
     * 判断指定的属性的值的用户是否在系统中已存在
     *  @author duosheng.mo , dengfeng update @20160602
     *  @DateTime 2016年5月20日 上午11:19:23
     *  
     *  @param property 属性项
     *  @param propertyValue 属性值
     *  @return
     *  @throws Exception
     */
    private boolean isExist(String property, String propertyValue) {
        boolean bool = false;
        List<Map<String, Object>> userList = commonTrans.findByPropertyByMap(TUser.class, property, propertyValue);
        List<Map<String, Object>> orgUserList = commonTrans.findByPropertyByMap(TOrgUser.class, property, propertyValue);
        //若用户状态不是注销，则用户存在
        if(userList != null && userList.size() > 0){
            for(Map<String, Object> user : userList){
                int status = (int) user.get("status");
                if(status != UserStatus.logoff.value()){
                    bool = true;
                    break;
                }
            }
        }
        if(!bool && orgUserList != null && orgUserList.size() > 0){
            for(Map<String, Object> orgUser : orgUserList){
                int status = (Integer)orgUser.get("status");
                if(status != UserStatus.logoff.value()){
                    bool = true;
                    break;
                }
            }
        }
        return bool;
    }
    
    public String checkMobileReturnUserId(String mobile) {
        String userId = null;
        
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("mobileVerified", true);
        
        boolean get = false;
        List<Map<String, Object>> users = commonTrans.findByMap(TUser.class, params);
        for (Map<String, Object> map : users) {
            if ((Integer) map.get("status") != UserStatus.logoff.value()) {
                userId = String.valueOf((Integer) map.get("id"));
                get = true;
                break;
            }
        }
        
        if (get) {
            return userId;
        }
        
        List<Map<String, Object>> oUsers = commonTrans.findByMap(TOrgUser.class, params);
        for (Map<String, Object> map : oUsers) {
            if ((Integer) map.get("status") != UserStatus.logoff.value()) {
                userId = String.valueOf((Integer) map.get("id"));
                break;
            }
        }
        return userId;
    }
    
    public String checkEmailReturnUserId(String email) {
        String userId = "";
        
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("emailVerified", true);
        
        boolean get = false;
        
        List<Map<String, Object>> users = commonTrans.findByMap(TUser.class, params);
        for (Map<String, Object> user : users) {
            int status = (int) user.get("status");
            if (status != UserStatus.logoff.value()) {
                userId = String.valueOf(user.get("id"));
                get = true;
                break;
            }
        }
        
        if (get) {
            return userId;
        }
        
        List<Map<String, Object>> orgUsers = commonTrans.findByMap(TOrgUser.class, params);
        for (Map<String, Object> orgUser : orgUsers) {
            int status = (int) orgUser.get("status");
            if (status != UserStatus.logoff.value()) {
                userId = String.valueOf(orgUser.get("id"));
                break;
            }
        }
        
        return userId;
    }

    @Override
    public void saveSensitiveLog(SensitiveOperationLogDTO log) {
        baseUserDao.insertSensitiveLog(log);
    }
}
