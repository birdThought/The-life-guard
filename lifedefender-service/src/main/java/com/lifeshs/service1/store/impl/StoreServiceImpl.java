package com.lifeshs.service1.store.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.entity.org.TOrgServe;
import com.lifeshs.po.EmployeePO;
import com.lifeshs.po.org.TOrgServePO;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.store.employee.IEmployeeService;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.utils.SMSCommand;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.vo.member.OrgRcmVo;
import com.lifeshs.vo.org.OrgRecommendVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.dao1.order.IOrderDao;
import com.lifeshs.dao1.org.OrgDao;
import com.lifeshs.po.OrgPO;
import com.lifeshs.service1.store.StoreService;

@Service(value = "v2StoreService")
public class StoreServiceImpl implements StoreService {

    @Resource(name = "orgDao")
    private OrgDao orgDao;

    @Resource(name = "orderDao1")
    private IOrderDao orderDao;

    @Autowired
    private SMSService sendSMS;

    @Autowired
    private IEmployeeService employeeService;
    
    @Override
    public OrgPO getStore(int id) {
        return orgDao.getOrg(id);
    }

    @Override
    public int getStoreConsumeMemberNumber(int id) {
        List<Integer> s = new ArrayList<>();
        s.add(OrderStatus.COMPLETED.getStatus());
        s.add(OrderStatus.VALID.getStatus());
        return orderDao.countStoreOrder(id, s, true);
    }

    @Override
    public List<OrgPO> listRecommendedStore(int limit) {
        return orgDao.findRecomandedStoreList(limit);
    }

    @Override
    public List<OrgPO> listStore(int limit) {
        Integer l = null;
        if (limit != 0) {
            l = limit;
        }
        return orgDao.findStoreList(l);
    }

    @Override
    public List<OrgPO> listStore(String name) {
        return orgDao.findStoreListByName(name);
    }

    @Override
    public Paging<OrgPO> getOrgListData(String userNo, String orgName, String province, String city, String district, Integer page, int pageSize) {
        Paging<OrgPO> p = new Paging<>(page,pageSize);
        p.setQueryProc(new IPagingQueryProc<OrgPO>() {
            @Override
            public int queryTotal() {
                return orgDao.getListCount(userNo, province,city,district,orgName);
            }

            @Override
            public List<OrgPO> queryData(int startRow, int pageSize) {
                return orgDao.findByListData(userNo, orgName,province,city,district,(page - 1) * pageSize,pageSize);
            }
        });
        return p;
    }

    @Override
    public OrgPO getDetailsOrg(Integer id) {
        return orgDao.selectByPrimaryKey(id);
    }

    @Override
    public void findByOrgUpdate(boolean isRecommend, Integer id) {
            orgDao.updateOrgBoolean(isRecommend,id);
    }

    @Override
    public void findByOrgSrttus(Integer status, Integer id) {
        orgDao.findByStatus(status,id);
    }

    @Override
    public Paging<OrgPO> findByOrgCheck(String userNo,Integer param, Integer page, int pageSize) {
        Paging<OrgPO> p = new Paging<>(page,pageSize);
        p.setQueryProc(new IPagingQueryProc<OrgPO>() {
            @Override
            public int queryTotal() {
                return orgDao.getOrgCountByOption(userNo,param);
            }

            @Override
            public List<OrgPO> queryData(int startRow, int pageSize) {
                return orgDao.getOrgListByOption(userNo, param,(page-1) * pageSize,pageSize);
            }
        });
        return p;
    }

    /**
     * 获取单个结果
     * @param id
     * @return
     */
    @Override
    public OrgPO findByOrgCheckId(Integer id) {
        return orgDao.getOrg(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void findByOrgVerify(Integer id,Integer orgVerified) {
            orgDao.updateOrgDelId(orgVerified,id);
    }

    /**
     * 审核结果
     * @param id
     */
    @Override
    public EmployeePO findByModifyIrg(Integer id,Integer orgVerified,String reason) {
        if ("".equals(reason)){
            reason = null;
        }
        orgDao.updateOrgId(orgVerified, id,reason);
        return employeeService.getManage(id);
        
    }


    @Override
    public Paging<OrgRcmVo> findByOrgRcm(String userNo, Integer page, int pageSize) {
        Paging<OrgRcmVo> p = new Paging<>(page,pageSize);
        p.setQueryProc(new IPagingQueryProc<OrgRcmVo>() {
            @Override
            public int queryTotal() {
                return orgDao.getOrgRcm(userNo);
            }

            @Override
            public List<OrgRcmVo> queryData(int startRow, int pageSize) {
               return orgDao.getOrgRcmDataList(userNo, (page - 1) * pageSize, pageSize+10);

            }
        });
        return p;
    }
}
