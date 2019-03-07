package com.lifeshs.dao1.measure;

import com.lifeshs.po.BodyFatScalePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 体脂称
 */
@Repository
public interface BodyFatScaleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BodyFatScalePO record);

    int insertSelective(BodyFatScalePO record);

    BodyFatScalePO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BodyFatScalePO record);

    int updateByPrimaryKey(BodyFatScalePO record);

    List<BodyFatScalePO> selectMeasureDatesByUserId(@Param("userId") Integer userId, @Param("queryDate") String queryDate);

    Date getLastDateByUserId(@Param("userId") Integer userId);

    BodyFatScalePO currrntDodyfatsDate(@Param("userId")Integer userId,@Param("date") String date);
}