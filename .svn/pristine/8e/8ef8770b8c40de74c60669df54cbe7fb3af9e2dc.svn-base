package com.lifeshs.service1.business.impl;

import com.lifeshs.dao.org.manage.OrgServiceDao;
import com.lifeshs.dao1.business.IBusinessCodeDao;
import com.lifeshs.dao1.business.IUserDao;
import com.lifeshs.po.business.BusinessCodePO;
import com.lifeshs.po.business.BusinessUserPO;
import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.pojo.org.employee.OrgEmploy;
import com.lifeshs.service1.business.BusinessCodeService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @create 2018-03-08
 * 17:24
 * @desc
 */
@Service("businessCodeService")
public class BusinessCodeServiceImpl implements BusinessCodeService {

    private static int TYPE = 3;

    @Resource(name = "businessCodeDao")
    private IBusinessCodeDao businessCodeDao;
    @Resource(name = "businessUserDao")
    private IUserDao userDao;
    @Resource(name = "orgServiceDao")
    private OrgServiceDao orgServiceDao;

    @Override
    public List<VipComboPO> fingByName() {
        return businessCodeDao.findByIdName();
    }

    @Override
    public int addPackage(Integer id, Integer ageId) {
        String name = businessCodeDao.findByName(ageId); //获取套餐名称
        BusinessUserPO user = userDao.getUser(id);
        Integer superior = user.getSuperior();
        String OrgName = user.getName();
        BusinessCodePO bcp = new BusinessCodePO();
        bcp.setBsId(id);
        bcp.setAgeId(ageId);
        bcp.setName(name);
        bcp.setSuperior(superior);
        bcp.setOrgName(OrgName);
        bcp.setProjectType(TYPE);
        int i = businessCodeDao.saveCodePackage(bcp);
        if (i != 0) {
            return i;
        }
        return 0;
    }

    @Override
    public Paging<BusinessCodePO> obtainData(Integer id,Integer page,Integer pageSize) {
        BusinessUserPO user = userDao.getUser(id);
        Paging<BusinessCodePO> p = new Paging<>(page,pageSize);
        p.setQueryProc(new IPagingQueryProc<BusinessCodePO>() {
            @Override
            public int queryTotal() {
                return businessCodeDao.findCountData(id,user.getType(),user.getSuperior());
            }

            @Override
            public List<BusinessCodePO> queryData(int startRow, int pageSize) {
                return businessCodeDao.findListPackage(id,user.getType(),user.getSuperior(),(page - 1) * pageSize,pageSize);
            }
        });

        return p;
    }

    @Override
    public int del(Integer id) {
        return businessCodeDao.deletePackage(id);
    }

    @Override
    public Paging<BusinessUserPO> findBySell(Integer id, Integer page, int pagesize) {
        Paging<BusinessUserPO> p = new Paging<>(page,pagesize);
        p.setQueryProc(new IPagingQueryProc<BusinessUserPO>() {
            @Override
            public int queryTotal() {
                return userDao.findByCountManAgeMent(id);
            }

            @Override
            public List<BusinessUserPO> queryData(int startRow, int pageSize) {
                List<BusinessUserPO> byAllSell = userDao.findByAllSell(id, (page - 1) * pageSize, pageSize);
                return byAllSell;
            }
        });
        return p;
    }

    @Override
    public int ModifySell(Integer id, String userName, String name) {
        return businessCodeDao.updateSellData(id,userName,name);
    }

    @Override
    public List<OrgEmploy> getSercerId(Integer storeId) {
        return orgServiceDao.getServer(storeId);
    }
}
