package com.lifeshs.service.member.impl;

import com.lifeshs.dao.device.IDeviceDao;
import com.lifeshs.service.member.IMemberC3Service;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.member.MemberC3Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-01-31
 * 11:44
 * @desc
 */
@Service("iMemberC3Service")
public class IMemberC3ServiceImpl implements IMemberC3Service {
    @Autowired
    private IDeviceDao iDeviceDao;

    @Override
    public Paging<MemberC3Vo> findByC3Data(Integer condition, String userName, String imei, String createDate, Integer status, int page, int pageSize) {
        Paging<MemberC3Vo> p = new Paging<>(page,pageSize);
        p.setQueryProc(new IPagingQueryProc<MemberC3Vo>() {
            @Override
            public int queryTotal() {
                return iDeviceDao.findByC3Sum(condition,userName,imei,createDate,status);
            }

            @Override
            public List<MemberC3Vo> queryData(int startRow, int pageSize) {
                return iDeviceDao.findByDataList(condition,userName,imei,createDate,status,(page - 1) * pageSize, pageSize);
            }
        });
        return p;
    }
}
