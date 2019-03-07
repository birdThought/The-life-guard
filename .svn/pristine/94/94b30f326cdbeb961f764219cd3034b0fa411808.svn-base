package com.lifeshs.service1.data.impl;

import com.lifeshs.dao1.data.DiseasesDao;
import com.lifeshs.po.data.DiseasesPO;
import com.lifeshs.service1.data.DiseasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * 病种服务实现
 * author: wenxian.cai
 * date: 2017/8/16 14:41
 */
@Service("diseasesService")
public class DiseasesServiceImpl implements DiseasesService{

    @Autowired
    DiseasesDao diseasesDao;

    @Override
    public DiseasesPO getDiseases(Integer id) {
        return diseasesDao.getDiseases(id);
    }

    @Override
    public List<DiseasesPO> findDiseasesList() {
        return diseasesDao.findDiseasesList();
    }
}
