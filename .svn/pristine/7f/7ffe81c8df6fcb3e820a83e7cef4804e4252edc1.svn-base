package com.lifeshs.service1.smsRemind.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.smsRemind.UserTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.smsRemind.SmsRemindDao;
import com.lifeshs.pojo.member.UserSMSRemainDTO;
import com.lifeshs.service1.smsRemind.SmsRemindService;

@Service(value = "smsRemindService")
public class SmsRemindServiceImpl implements SmsRemindService {

    @Resource(name = "smsRemindDao")
    private SmsRemindDao remindDao;

    @Override
    @Transactional(rollbackFor = OperationException.class, propagation = Propagation.REQUIRED)
    public void addMemberSmsRemind(int userId, long number) throws OperationException {
        addSmsRemind(userId, number, UserTypeEnum.MEMBER);
    }

    @Override
    @Transactional(rollbackFor = OperationException.class, propagation = Propagation.REQUIRED)
    public void addOrgSmsRemind(int orgId, long number) throws OperationException {
        addSmsRemind(orgId, number, UserTypeEnum.ORG);
    }
    
    /**
     *  添加短信剩余数量
     *  @author yuhang.weng 
     *  @DateTime 2017年8月31日 上午9:17:55
     *
     *  @param objectId 对象ID
     *  @param number 添加数量
     *  @param userType 对象类型
     *  @throws OperationException
     */
    private void addSmsRemind(int objectId, long number, UserTypeEnum userType) throws OperationException {
        UserSMSRemainDTO remindDTO = remindDao.findSMSRemainByUserIdAndUserType(objectId, userType.value());
        // 如果用户没有短信剩余记录的话，就新建一条记录
        if (remindDTO == null) {
            UserSMSRemainDTO nwRemainDTO = new UserSMSRemainDTO();
            nwRemainDTO.setUserId(objectId);
            nwRemainDTO.setRemainNumber(number);
            nwRemainDTO.setUserType(userType.value());
            int result = remindDao.addSMSRemain(nwRemainDTO);
            if (result == 0) {
                throw new OperationException("操作失败", ErrorCodeEnum.FAILED);
            }
            return;
        }
        // 这里获取用户剩余的短信记录，相加之后保存到数据库
        Long originNumber = remindDTO.getRemainNumber();
        if (originNumber == null) {
            originNumber = 0L;
        }
        number = originNumber + number;
        remindDTO.setRemainNumber(number);
        int result = remindDao.updateSMSRemain(remindDTO);
        if (result == 0) {
            throw new OperationException("操作失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public UserSMSRemainDTO getMemberSmsRemind(int userId) {
        return remindDao.findSMSRemainByUserIdAndUserType(userId, UserTypeEnum.MEMBER.value());
    }

    @Override
    public UserSMSRemainDTO getOrgSmsRemind(int orgId) {
        return remindDao.findSMSRemainByUserIdAndUserType(orgId, UserTypeEnum.ORG.value());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void reduceMemberSmsRemind(int userId, long number) throws OperationException {
        reduceSmsRemind(userId, number, UserTypeEnum.MEMBER);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void reduceOrgSmsRemind(int orgId, long number) throws OperationException {
        reduceSmsRemind(orgId, number, UserTypeEnum.ORG);
    }
    
    private void reduceSmsRemind(int objectId, long number, UserTypeEnum userType) throws OperationException {
        UserSMSRemainDTO smsRemain = remindDao.findSMSRemainByUserIdAndUserType(objectId, userType.value());
        if (smsRemain == null || smsRemain.getRemainNumber() == null || smsRemain.getRemainNumber().longValue() == 0) {
            throw new OperationException("用户剩余短信数量不足，操作失败", ErrorCodeEnum.FAILED);
        }
        
        long reminderNumber = smsRemain.getRemainNumber();
        reminderNumber = reminderNumber - number;
        if (reminderNumber < 0) {
            throw new OperationException("用户剩余短信数量不足，操作失败", ErrorCodeEnum.FAILED);
        }
        smsRemain.setRemainNumber(reminderNumber);
        int result = remindDao.updateSMSRemain(smsRemain);
        if (result == 0) {
            throw new OperationException("操作失败", ErrorCodeEnum.FAILED);
        }
    }
}
