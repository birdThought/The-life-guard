package com.lifeshs.dao1.data;

import com.lifeshs.po.data.DiseasesPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 病种dao
 * author: wenxian.cai
 * date: 2017/8/16 14:22
 */

@Repository(value = "diseasesDao")
public interface DiseasesDao {

    /**
     * 获取病种列表
     * @return
     */
    List<DiseasesPO> findDiseasesList();

    /**
     * 获取单个病种信息
     * @param id
     * @return
     */
    DiseasesPO getDiseases(@Param("id") Integer id);
}
