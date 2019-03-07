package com.lifeshs.service.org.member.impl;

import com.lifeshs.dao.org.member.IMemberManageDao;
import com.lifeshs.pojo.org.OrgMemberDTO;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.org.member.IMemberManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机构-会员管理
 *
 * @author wenxian.cai
 * @create 2017-05-05 11:09
 **/
@Service("memberManageService")
public class MemberManageServiceImpl extends CommonServiceImpl implements IMemberManageService{

    @Autowired
    IMemberManageDao memberManageDao;

    @Override
    public List<OrgMemberDTO> listMemberByOrgUser(int orgUserId, int status) {
        return memberManageDao.listMemberByOrgUser(orgUserId, status);
    }

    @Override
    public int getCountOfMemberByOrgUser(int orgUserId, int status) {
        return memberManageDao.getCountOfMemberByOrgUser(orgUserId, status);
    }

    @Override
    public List<OrgMemberDTO> listMemberByOrg(int orgId, int status) {
        return memberManageDao.listMemberByOrg(orgId, status);
    }

    @Override
    public int countMemberByOrgId(int orgId) {
        int count = memberManageDao.countMemberByOrgId(orgId);
        return count;
    }
}
