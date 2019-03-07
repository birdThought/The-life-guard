package com.lifeshs.service.member.impl;

import com.lifeshs.dao1.member.TunregistHxDao;
import com.lifeshs.entity.huanxin.TUnregistHx;
import com.lifeshs.pojo.huanxin.HuanxinUserVO;
import com.lifeshs.service.member.TUnregistHxService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @create 2018-01-31
 * 14:36
 * @desc
 */
@Service("tUnregistHxService")
public class TUnregistHxServiceImpl implements TUnregistHxService{

    @Autowired
    private TunregistHxDao tunregistHxDao;

    @Override
    public Paging<TUnregistHx> findByListDate(int page, int pageSize) {
        Paging<TUnregistHx> p = new Paging<>(page,pageSize);
        p.setQueryProc(new IPagingQueryProc<TUnregistHx>() {
            @Override
            public int queryTotal() {
                return tunregistHxDao.findBySum();
            }

            @Override
            public List<TUnregistHx> queryData(int startRow, int pageSize) {
                return tunregistHxDao.findByList((page -1) * pageSize,pageSize);
            }
        });
        return p;
    }

    @Override
    public List<HuanxinUserVO> findByAll() {
        List<HuanxinUserVO> list = new ArrayList<>();
        List<TUnregistHx> byAll = tunregistHxDao.findByAll();
        for (TUnregistHx th : byAll){
            HuanxinUserVO hu = new HuanxinUserVO();
            hu.setUsername(th.getUsername());
            hu.setPassword(th.getPassword());
        list.add(hu);
        }
        return list;
    }
}
