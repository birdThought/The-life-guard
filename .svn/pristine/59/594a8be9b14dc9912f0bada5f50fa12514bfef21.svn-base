package com.lifeshs.service1.member.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.user.UserHobbyDao;
import com.lifeshs.po.user.UserHobbyPO;
import com.lifeshs.service1.member.HobbyService;
import com.lifeshs.utils.ListUtil;

@Service(value = "userHobbyService")
public class HobbyServiceImpl implements HobbyService {

    @Resource(name = "userHobbyDao")
    private UserHobbyDao hobbyDao;
    
    @Override
    public List<UserHobbyPO> listUserHobby(int userId) {
        return hobbyDao.findUserHobbyByUserId(userId);
    }

    @Override
    public void addUserHobby(int userId, List<Integer> hobbyIdList) throws OperationException {
        int size = hobbyIdList.size();
        if (size == 0) {
            throw new OperationException("至少选择一项添加内容", ErrorCodeEnum.REQUEST);
        }
        // 移除重复内容
        hobbyIdList = ListUtil.removeRepeatElement(hobbyIdList, Integer.class);
        int effectRow = hobbyDao.addUserHobby(userId, hobbyIdList);
        if (effectRow != hobbyIdList.size()) {
            throw new OperationException("部分内容添加失败", ErrorCodeEnum.NOT_COMPLETE);
        }
    }

    @Override
    public void removeUserHobby(int userId) {
        hobbyDao.delUserHobby(userId);
    }

    @Override
    public void removeUserHobby(int userId, int userHobbyId) throws OperationException {
        int effectRow = hobbyDao.delUserHobbyList(userId, Arrays.asList(userHobbyId));
        if (effectRow != 1) {
            throw new OperationException("删除失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void removeUserHobby(int userId, List<Integer> userHobbyIdList) throws OperationException {
        int size = userHobbyIdList.size();
        if (size == 0) {
            throw new OperationException("至少选择一项删除内容", ErrorCodeEnum.REQUEST);
        }
        userHobbyIdList = ListUtil.removeRepeatElement(userHobbyIdList, Integer.class);
        int effectRow = hobbyDao.delUserHobbyList(userId, userHobbyIdList);
        if (effectRow != userHobbyIdList.size()) {
            throw new OperationException("部分内容删除失败", ErrorCodeEnum.NOT_COMPLETE);
        }
    }

    @Override
    public void updateUserHobby(UserHobbyPO userHobby) throws OperationException {
        if (userHobby.getId() == null) {
            throw new OperationException("id不能为空", ErrorCodeEnum.NOT_FOUND);
        }
        if (userHobby.getHobbyId() == null) {
            throw new OperationException("兴趣爱好id不能为空", ErrorCodeEnum.NOT_FOUND);
        }
        if (userHobby.getUserId() == null) {
            throw new OperationException("用户id不能为空", ErrorCodeEnum.NOT_FOUND);
        }
        int effectRow = hobbyDao.updateUserHobby(userHobby);
        if (effectRow != 1) {
            throw new OperationException("更新失败", ErrorCodeEnum.FAILED);
        }
    }

}
