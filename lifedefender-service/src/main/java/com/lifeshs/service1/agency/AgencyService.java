package com.lifeshs.service1.agency;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.org.AgencyPO;
import com.lifeshs.vo.org.OrgBranchVO;

public interface AgencyService {

    /**
     *  获取机构分支
     *  @author yuhang.weng 
     *  @DateTime 2017年9月21日 下午2:53:14
     *
     *  @param id 管理机构id
     *  @return
     */
    OrgBranchVO getBranch(int id);
    
    /**
     *  添加管理机构
     *  @author yuhang.weng 
     *  @DateTime 2017年9月21日 下午4:13:30
     *
     *  @param agency 管理机构
     *  @exception OperationException
     */
    void addAgency(AgencyPO agency) throws OperationException;
}
