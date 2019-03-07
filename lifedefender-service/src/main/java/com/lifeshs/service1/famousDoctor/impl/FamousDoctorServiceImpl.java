package com.lifeshs.service1.famousDoctor.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.dao1.famousDoctor.FamousDoctorDao;
import com.lifeshs.po.famousDoctor.FamousDoctorPO;
import com.lifeshs.service1.famousDoctor.FamousDoctorService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.famousDoctor.ProfessionKindVO;

@Service(value = "famousDoctorServiceImpl")
public class FamousDoctorServiceImpl implements FamousDoctorService {

    @Resource(name = "famousDoctorDao")
    private FamousDoctorDao famousDoctorDao;

    @Override
    public FamousDoctorPO getFamousDoctor(int id) {
        return famousDoctorDao.getFamousDoctor(id);
    }

    @Override
    public Paging<FamousDoctorPO> listFamousDoctor(String likeName, String professionKindLikeName, int curPage,
            int pageSize) {
        Paging<FamousDoctorPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<FamousDoctorPO>() {

            @Override
            public int queryTotal() {
                return famousDoctorDao.countFamousDoctorWithCondition(likeName, professionKindLikeName);
            }

            @Override
            public List<FamousDoctorPO> queryData(int startRow, int pageSize) {
                return famousDoctorDao.findFamousDoctorListWithCondition(startRow, pageSize, likeName, professionKindLikeName);
            }
        });
        return p;
    }

    @Override
    public Paging<ProfessionKindVO> listProfessionKind(int curPage, int pageSize) {
        Paging<ProfessionKindVO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<ProfessionKindVO>() {

            @Override
            public int queryTotal() {
                return famousDoctorDao.countProKindName();
            }

            @Override
            public List<ProfessionKindVO> queryData(int startRow, int pageSize) {
                List<String> kindNameList = famousDoctorDao.findProKindNameList(startRow, pageSize);
                // 每个类型取3条数据
                return famousDoctorDao.findFamousDoctorKind(3, kindNameList);
            }
        });
        return p;
    }

}
