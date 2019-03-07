package com.lifeshs.service1.agency.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.org.AgencyDao;
import com.lifeshs.dao1.org.OrgDao;
import com.lifeshs.po.OrgPO;
import com.lifeshs.po.org.AgencyPO;
import com.lifeshs.service1.agency.AgencyService;
import com.lifeshs.vo.org.OrgBranchVO;

@Service(value = "orgAgencyService")
public class AgencyServiceImpl implements AgencyService {

    @Resource(name = "orgAgencyDao")
    private AgencyDao agencyDao;
    
    @Resource(name = "orgDao")
    private OrgDao orgDao;
    
    @Override
    public OrgBranchVO getBranch(int id) {
        OrgBranchVO data = new OrgBranchVO();
        List<AgencyPO> agencyList = agencyDao.findAgencyByParentIdList(id);
        List<OrgPO> storeList = orgDao.findStoreByParentIdList(id);
        data.setAgencyList(agencyList);
        data.setStoreList(storeList);
        return data;
    }

    @Override
    public void addAgency(AgencyPO agency) throws OperationException {
        int result = agencyDao.addAgency(agency);
        if (result == 0) {
            throw new OperationException("机构添加失败", ErrorCodeEnum.FAILED);
        }
    }

}
