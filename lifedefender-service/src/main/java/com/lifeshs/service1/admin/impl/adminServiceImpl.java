package com.lifeshs.service1.admin.impl;

import com.lifeshs.dao1.admin.adminDao;
import com.lifeshs.po.admin.adminPO;
import com.lifeshs.service1.admin.adminService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.visit.OperatingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @create 2018-02-06
 * 15:14
 * @desc
 */
@Service("adminService")
public class adminServiceImpl implements adminService {

    @Resource(name = "adminDao")
    private adminDao adminDao;

    @Override
    public Paging<adminPO> getBackLogData(Integer page, Integer pageSize) {
        Paging<adminPO> p = new Paging<>(page,pageSize);
        p.setQueryProc(new IPagingQueryProc<adminPO>() {
            @Override
            public int queryTotal() {
                return adminDao.findByAdminCount();
            }

            @Override
            public List<adminPO> queryData(int startRow, int pageSize) {
                return adminDao.findByAdminData((page - 1)* pageSize,pageSize);
            }
        });
        return p;
    }

    @Override
    public Paging<OperatingVo> getByOperatingData(Integer type, String date, String realName, Integer page, int pageSize) {
        Paging<OperatingVo> p = new Paging<>(page,pageSize);
        p.setQueryProc(new IPagingQueryProc<OperatingVo>() {
            @Override
            public int queryTotal() {
                return adminDao.findByOperatingCount(type,realName,date);
            }

            @Override
            public List<OperatingVo> queryData(int startRow, int pageSize) {
                return adminDao.findByOperatingData(type,realName,date,(page - 1) * pageSize,pageSize);
            }
        });
        return p;
    }
}
