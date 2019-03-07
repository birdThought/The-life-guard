package com.lifeshs.service1.data.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.dao1.data.PhysicalItemDao;
import com.lifeshs.po.data.PhysicalPO;
import com.lifeshs.service1.data.PhysicalItemService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;

@Service(value = "dataPhysicalServiceImpl")
public class PhysicalItemServiceImpl implements PhysicalItemService {

    @Resource(name = "dataPhysicalDao")
    private PhysicalItemDao physicalDao;
    
    @Override
    public Paging<PhysicalPO> listPhysicalItem(int curPage, int pageSize) {
        Paging<PhysicalPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<PhysicalPO>() {

            @Override
            public int queryTotal() {
                return physicalDao.countPhysicalItem();
            }

            @Override
            public List<PhysicalPO> queryData(int startRow, int pageSize) {
                return physicalDao.findPhysicalItemWithConditionList(startRow, pageSize);
            }
            
        });
        
        return p;
    }

    @Override
    public PhysicalPO getPhysicalItem(int id) {
        return physicalDao.getPhysicalItem(id);
    }
}
