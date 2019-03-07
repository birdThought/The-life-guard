package com.lifeshs.service.member.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.UserStatus;
import com.lifeshs.entity.member.TUser;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service.common.impl.BaseUserImpl;
import com.lifeshs.utils.MD5Utils;

/**
 * 普通用户（会员）操作类
 * @author dengfeng
 * 增加会员特有方法
 */
@Component("member")
public class Member extends BaseUserImpl {
    
    public Integer register(String userName, String password, String userCode) {
        UserDTO user = new UserDTO();
        user.setUserName(userName);
        user.setPassword(MD5Utils.encryptPassword(password));
        user.setUserCode(userCode);
        user.setStatus(UserStatus.normal.value());
        user.setHealthProduct(0);
        user.setHealthWarning(0);
        user.setHasWarning(0);
        user.setParentId(AgentConstant.AGENT_DEFUALT_PARENT_ID_A2);
        //user.setSex(false);   默认性别取消
        super.addMember(user);
        return user.getId();
    }
    
    public Integer register(UserDTO userDTO) {
        userDTO.setStatus(UserStatus.normal.value());
        
        UserDTO user = new UserDTO();
        user.setUserName(userDTO.getUserName());
        user.setPassword(MD5Utils.encryptPassword(userDTO.getPassword()));
        user.setMobile(userDTO.getMobile());
        user.setMobileVerified(userDTO.getMobileVerified());
        user.setRealName(userDTO.getRealName());
        user.setStatus(userDTO.getStatus());
        user.setHealthProduct(0);
        user.setHealthWarning(0);
        user.setHasWarning(0);
        user.setUserCode(userDTO.getUserCode());
        user.setPhoto(userDTO.getPhoto());
        user.setParentId(userDTO.getParentId());
        super.addMember(user);
        return user.getId();
    }
    
    /**
     * 返回map形式的user信息
     *  @author dachang.luo 
     *  @DateTime 2016年6月30日 上午10:14:57
     *  @serverComment 服务注解
     *
     *  @param userId
     *  @return
     *  @throws Exception
     */
    public Map<String , Object> getUserMap(int userId)throws Exception{
        return commonTrans.findUniqueByPropertyByMap(TUser.class, "id", userId);
    }
}
