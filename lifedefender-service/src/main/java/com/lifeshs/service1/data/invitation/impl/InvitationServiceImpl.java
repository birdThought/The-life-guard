package com.lifeshs.service1.data.invitation.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.data.InvitationDao;
import com.lifeshs.dao1.data.InvitationRecordDao;
import com.lifeshs.po.invitation.InvitationPO;
import com.lifeshs.po.invitation.InvitationRecordPO;
import com.lifeshs.pojo.member.InvicationRecordDTO;
import com.lifeshs.service1.data.invitation.InvitationService;

@Service(value = "invitationService")
public class InvitationServiceImpl implements InvitationService {

    @Resource(name = "invitationDao")
    private InvitationDao invitationDao;

    @Resource(name = "invicationRecordDao")
    private InvitationRecordDao recordDao;
    
    @Override
    public boolean addInvitation(InvitationPO invitation) {
        int result = invitationDao.addInvitation(invitation);
        return (result == 1);
    }

    @Override
    public boolean addInvitation(List<InvitationPO> invitationList) {
        invitationDao.addInvitationList(invitationList);
        return true;
    }

    @Override
    @Transactional(rollbackFor = OperationException.class, propagation = Propagation.REQUIRED)
    public void updateInvitaionToUse(int userId, String code) throws OperationException {
        InvitationPO invitationPO = invitationDao.getInvitationCodeByCode(code);
        if (invitationPO == null) {
            throw new OperationException("邀请码不存在", ErrorCodeEnum.NOT_FOUND);
        }
        Date entryDate = invitationPO.getEntryDate();
        if (entryDate != null) {
            throw new OperationException("邀请码已被使用", ErrorCodeEnum.FAILED);
        }
        List<InvicationRecordDTO> records = recordDao.findInvitationRecordByUserId(userId);
        if (records.size() > 0) {
            throw new OperationException("用户已使用过其它邀请码", ErrorCodeEnum.FAILED);
        }
        int result = 0;
        // 修改为已使用
        result = invitationDao.updateInvitationCodeToUsedByCode(code);
        if (result == 0) {
            throw new OperationException("操作失败", ErrorCodeEnum.FAILED);
        }
        result = 0; // 归零
        // 添加邀请码记录
        InvitationRecordPO recordDTO = new InvitationRecordPO();
        recordDTO.setUserId(userId);
        recordDTO.setInvitationCodeId(invitationPO.getId());
        result = recordDao.addUserInvitationRecord(recordDTO);
        
        if (result == 0) {
            throw new OperationException("操作失败", ErrorCodeEnum.FAILED);
        }
    }
}
