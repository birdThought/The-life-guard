package com.lifeshs.service1.push.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.dao1.push.OrgUserDeviceTokenDao;
import com.lifeshs.dao1.push.UserDeviceTokenDao;
import com.lifeshs.po.push.OrgUserDeviceTokenPO;
import com.lifeshs.po.push.UserDeviceTokenPO;
import com.lifeshs.service1.push.PushDataService;

@Service(value = "pushDataService")
public class PushDataServiceImpl implements PushDataService {

    @Resource(name = "orgUserPushDeviceTokenDao")
    private OrgUserDeviceTokenDao orgUserDeviceTokenDao;

    @Resource(name = "userPushDeviceTokenDao")
    private UserDeviceTokenDao userDeviceTokenDao;
    
    @Override
    public OrgUserDeviceTokenPO getOrgUserPushToken(int userId) {
        return orgUserDeviceTokenDao.findDeviceTokenByUserId(userId);
    }

    @Override
    public void addOrgUserPushToken(OrgUserDeviceTokenPO token) throws BaseException {
        Integer userId = token.getUserId();
        String deviceToken = token.getDeviceToken();
        if (userId == null) {
            throw new ParamException("用户id不允许为空");
        }
        if (StringUtils.isBlank(deviceToken)) {
            throw new ParamException("设备token不允许为空");
        }
        OrgUserDeviceTokenPO po = orgUserDeviceTokenDao.findDeviceToken(userId,token.getOS(),deviceToken,token.getSystemVersion());
        //存在记录
        if(po != null){
            //不是同一台设备
            if(po.getDeleted() !=0){
                orgUserDeviceTokenDao.delDeviceTokenByUserId(userId);
                orgUserDeviceTokenDao.updateTokenByUserId(userId,token.getOS(),deviceToken,token.getSystemVersion(), 0);
            }
        }else{
            List<OrgUserDeviceTokenPO> poList = orgUserDeviceTokenDao.findDeviceTokenList(userId);
            if(poList.size()>0){
                orgUserDeviceTokenDao.delDeviceTokenByUserId(userId);
            }
            //不存在记录
            orgUserDeviceTokenDao.addDeviceToken(token);
        }
    }

    @Override
    public void deleteOrgUserPushToken(int userId) throws OperationException {
        int result = orgUserDeviceTokenDao.delDeviceTokenByUserId(userId);
        if (result == 0) {
            throw new OperationException("删除token失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public UserDeviceTokenPO getUserPushToken(int userId) {
        return userDeviceTokenDao.findDeviceTokenByUserId(userId);
    }
    
    @Override
    public List<UserDeviceTokenPO> findUserDeviceTokenPOList(String projectCode, Integer diseasesId, Integer gender, String startAge, String endAge, String mobile, Integer orgId) {
        return userDeviceTokenDao.findUserDeviceTokenPOList(projectCode, diseasesId, gender, startAge, endAge, mobile, orgId);
    }

    @Override
    public void addUserPushToken(UserDeviceTokenPO token) throws BaseException {
        Integer userId = token.getUserId();
        String deviceToken = token.getDeviceToken();
        if (userId == null) {
            throw new ParamException("用户id不允许为空");
        }
        if (StringUtils.isBlank(deviceToken)) {
            throw new ParamException("设备token不允许为空");
        }
        
        UserDeviceTokenPO po = userDeviceTokenDao.findDeviceToken(userId,token.getOS(),deviceToken,token.getSystemVersion());
        //存在记录
        if(po != null){
            //不是同一台设备
            if(po.getDisplay() ==0){
                userDeviceTokenDao.delDeviceTokenByUserId(userId);
                userDeviceTokenDao.updateTokenByUserId(userId,token.getOS(),deviceToken,token.getSystemVersion(), 1);
            }
        }else{
            List<UserDeviceTokenPO> poList = userDeviceTokenDao.findDeviceTokenList(userId);
            //不存在记录
            if(poList.size()>0){
                userDeviceTokenDao.delDeviceTokenByUserId(userId);
            }
            userDeviceTokenDao.addDeviceToken(token);
        }
    }

    @Override
    public void deleteUserPushToken(int userId) throws OperationException {
        int result = userDeviceTokenDao.delDeviceTokenByUserId(userId);
        if (result == 0) {
            throw new OperationException("删除token失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public List<OrgUserDeviceTokenPO> getOrgUserPushToken(List<Integer> userIdList) {
        List<OrgUserDeviceTokenPO> dataList = new ArrayList<>();
        if (userIdList.size() == 0) {
            dataList = orgUserDeviceTokenDao.findDeviceTokenByUserIdList(userIdList);
        }
        return dataList;
    }

    @Override
    public List<UserDeviceTokenPO> getUserPushToken(List<Integer> userIdList) {
        List<UserDeviceTokenPO> dataList = new ArrayList<>();
        if (userIdList.size() > 0) {
            dataList = userDeviceTokenDao.findDeviceTokenByUserIdList(userIdList);
        }
        return dataList;
    }
}
