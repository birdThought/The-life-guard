package com.lifeshs.service.family.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.dao.member.IMemberDao;
import com.lifeshs.entity.member.TUser;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.family.GroupMemberVO;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.family.IFamilyService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.PasswordUtil;
import com.lifeshs.utils.UUID;

@Component
public class FamilyServiceImpl implements IFamilyService {

    @Autowired
    private ICommonTrans commonTrans;
    
    @Autowired
    private IMemberDao memberDao;

    @Override
    public TUser findUserByUserName(String userName) throws Exception {
        return commonTrans.findUniqueByProperty(TUser.class, "userName", userName);
    }

    @Override
    public Map<String, Object> findUserListByUserName(String userName, Integer userId) {
        Map<String, Object> data = new HashMap<>();

//        TUser userInvitee = commonTrans.findUniqueByProperty(TUser.class, "userName", userName);
        UserDTO userInvitee = memberDao.getUserByUserName(userName);
        if (userInvitee == null) {
            return data;
        }
        UserRecordDTO recordDTO = userInvitee.getRecordDTO();

        TUser userNowLogin = commonTrans.getEntity(TUser.class, userId);
        String groupKey = userNowLogin.getGroupKey();

        boolean ownerMember = false;

        // group key相同，并且不为空， 即是用户已经在家庭组中
        if (StringUtils.equals(groupKey, userInvitee.getGroupKey()) && StringUtils.isNotBlank(groupKey)) {
            ownerMember = true;
        }

        String realName = userInvitee.getRealName() == null ? userInvitee.getUserName() : userInvitee.getRealName();
        
        if (realName.length() == 2) {
            realName = realName.substring(0, 1) + "****";
        } else if (realName.length() > 3) {
            realName = realName.substring(0, 1) + "****" + realName.substring(realName.length() - 1, realName.length());
        }
        
        Integer age = 0;
        if (recordDTO.getBirthday() != null) {
            age = DateTimeUtilT.calculateAge(recordDTO.getBirthday());
        }
        String mobile = userInvitee.getMobile();
        if (StringUtils.isNotBlank(mobile)) {
            mobile = mobile.substring(0, 3) + "****" + mobile.substring(7);
        }

        data.put("id", userInvitee.getId());
        data.put("realName", realName);
        data.put("userName", userInvitee.getUserName());
        data.put("age", age);
        data.put("mobile", mobile);
        data.put("ownerMember", ownerMember);
        data.put("photo", userInvitee.getPhoto());

        return data;
    }

    @Override
    public ServiceMessage updateUserGroupKey(String userName, String password, int currentUserId) {
        String groupKey = getCurrentUserGroupKey(currentUserId);

        UserDTO user = findUserByNameAndPassword(userName, password);
        if (user == null) {
            // 用户名或密码不正确
            return new ServiceMessage(false, "用户名或密码不正确");
        }

        if (groupKey.equals(user.getGroupKey())) {
            return new ServiceMessage(false, "该用户已经加入家庭组");
        }

        if (user.getGroupKey() != null && !"".equals(user.getGroupKey())) {
            // 该用户已经加入别的家庭组
            return new ServiceMessage(false, "该用户已经加入别的家庭组");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setGroupKey(groupKey);
        memberDao.updateUser(userDTO);

        return new ServiceMessage(true, "成功加入家庭组");
    }

    private UserDTO findUserByNameAndPassword(String userName, String password) {
        UserDTO familyMember = memberDao.getUserByUserName(userName);
        if (familyMember == null) {
            return null;
        }
        String original_password = familyMember.getPassword();
        String md5Password = MD5Utils.encryptPassword(password);
        if (StringUtils.equals(md5Password, original_password)) {
            return familyMember;
        }
        // Start 临时使用，替换旧平台密码完成后清除 dengfeng
        String enpwdOld = PasswordUtil.encrypt("", password, PasswordUtil.getStaticSalt());
        if (StringUtils.equals(enpwdOld, original_password)) {
            // 密码正确，就将原密码修改为新的加密方式
            UserDTO userDTO = new UserDTO();
            userDTO.setId(familyMember.getId());
            userDTO.setPassword(password);
            memberDao.updateUser(userDTO);
        }
        return null;
    }

    @Override
    public List<GroupMemberVO> findGroupMember(Integer userId) {
        List<GroupMemberVO> users = new ArrayList<>();

        UserDTO user = memberDao.getUser(userId);
        String groupKey = user.getGroupKey();

        List<UserDTO> userDTOs = memberDao.listUserByGroupKey(groupKey);
        if (userDTOs != null) {
            for (UserDTO member : userDTOs) {
                GroupMemberVO tmp = new GroupMemberVO();

                UserRecordDTO recordDTO = member.getRecordDTO();
                String realName = member.getUserName();
                if (StringUtils.isNotBlank(member.getRealName())) {
                    realName = member.getRealName();
                }
                int age = 0;
                if (recordDTO.getBirthday() != null) {
                    age = DateTimeUtilT.calculateAge(recordDTO.getBirthday());
                }
                boolean isCurrentUser = false;
                if (userId.equals(member.getId())) {
                    isCurrentUser = true;
                }

                tmp.setId(member.getId());
                tmp.setRealName(realName);
                tmp.setAge(age);
                tmp.setMobile(member.getMobile());
                tmp.setIsCurrentUser(isCurrentUser);
                tmp.setBirthday(recordDTO.getBirthday());
                tmp.setHeight(recordDTO.getHeight());
                tmp.setWeight(recordDTO.getWeight());
                tmp.setHip(recordDTO.getHip());
                tmp.setWaist(recordDTO.getWaist());
                tmp.setBust(recordDTO.getBust());
                tmp.setSex(recordDTO.getGender());
                tmp.setPhoto(member.getPhoto());

                users.add(tmp);
            }
        }

        return users;
    }

    private String getCurrentUserGroupKey(int userId) {
        UserDTO user = memberDao.getUser(userId);

        String groupKey = user.getGroupKey();
        if (groupKey == null || "".equals(groupKey)) {
            // 创建用户组
            groupKey = UUID.generate();
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userId);
            userDTO.setGroupKey(groupKey);
            memberDao.updateUser(userDTO);
        }

        return groupKey;
    }

    @Override
    public void updateMemberInfo(UserDTO user, Integer currentUserId) {
        if (currentUserId != null) {
            String groupKey = getCurrentUserGroupKey(currentUserId);
            user.setGroupKey(groupKey);
        }
        memberDao.updateUser(user);
        memberDao.updateUserRecord(user.getRecordDTO());
    }
}
