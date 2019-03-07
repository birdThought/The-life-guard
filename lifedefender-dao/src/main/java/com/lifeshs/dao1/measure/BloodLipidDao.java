package com.lifeshs.dao1.measure;

import com.lifeshs.common.constants.app.healthPackage.BloodLipid;
import com.lifeshs.po.BloodLipidPO;
import com.lifeshs.po.UranPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 血脂仪
 *
 */
@Repository
public interface BloodLipidDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BloodLipidPO record);

    int insertSelective(BloodLipidPO record);

    BloodLipidPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BloodLipidPO record);

    int updateByPrimaryKey(BloodLipidPO record);

    List<BloodLipidPO> selectMeasureDatesByUserId(@Param("userId") Integer userId, @Param("queryDate") String queryDate);

    Date getLastDateByUserId(@Param("userId") Integer userId);

    /**
     * 获取最后一条数据
     * @param userId
     * @return
     */
    BloodLipidPO getLastData(@Param("userId") int userId);
}