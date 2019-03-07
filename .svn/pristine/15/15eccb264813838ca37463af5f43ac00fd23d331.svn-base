package com.lifeshs.dao.healthDescription;

import com.lifeshs.pojo.healthDescription.BloodSugarDTO;
import com.lifeshs.pojo.healthDescription.NormalHealthPackageDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 健康描述Dao
 * 
 * @author yuhang.weng
 * @DateTime 2017年2月20日 上午11:19:01
 */
@Repository("healthDescriptionDao")
public interface IHealthDescriptionDao {

    /**
     * 按照健康参数所属健康包分类，获取健康描述文字
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月20日 上午11:36:59
     *
     * @param gender
     *            性别
     * @param age
     *            年龄
     * @param healthPackageBinaryValue
     *            健康包二进制值
     * @param status
     *            状态(低_1,偏低_2,正常_3,偏高_4,高_5)
     * @return
     */
    List<NormalHealthPackageDTO> listNormalHealthPackageDescription(@Param("gender") @Nullable Boolean gender,
            @Param("age") @Nullable Integer age, @Param("healthPackageBinaryValue") Integer healthPackageBinaryValue,
            @Param("status") @Nullable Integer status);

    /**
     * 获取血糖描述文字
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月20日 下午3:37:57
     *
     * @param diabeticState 糖尿病状态,0_正常,1_轻度,2_中度,3_重度
     * @param status 状态(低_1,偏低_2,正常_3,偏高_4,高_5)
     * @return
     */
    List<BloodSugarDTO> listBloodSuagarDescription(@Param("diabeticState") @Nullable Integer diabeticState,
            @Param("status") @Nullable Integer status, @Param("gender") @Nullable Boolean gender);
}
