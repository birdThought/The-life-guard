package com.lifeshs.dao1.measure;

import com.lifeshs.po.BloodPressurePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 血压仪
 *
 */
@Repository
public interface BloodPressureDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BloodPressurePO record);

    int insertSelective(BloodPressurePO record);

    BloodPressurePO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BloodPressurePO record);

    int updateByPrimaryKey(BloodPressurePO record);

    List<BloodPressurePO> selectMeasureDatesByUserId(@Param("userId") Integer userId, @Param("queryDate") String queryDate);

    Date getLastDateByUserId(@Param("userId") Integer userId);

    BloodPressurePO currentPressureDate(@Param("userId")Integer userId,@Param("date") String date);
}