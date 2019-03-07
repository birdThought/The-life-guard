package com.lifeshs.service1.data;

/*
 * 病种
 * author: wenxian.cai
 * date: 2017/8/16 14:37
 */

import com.lifeshs.po.data.DiseasesPO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DiseasesService {

    /**
     * 获取单个病种信息
     * @param id
     * @return
     */
    DiseasesPO getDiseases(Integer id);

    /**
     * 获取病种列表
     * @return
     */
    List<DiseasesPO> findDiseasesList();
}
