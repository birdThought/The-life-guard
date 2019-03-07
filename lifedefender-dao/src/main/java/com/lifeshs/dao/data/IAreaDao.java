package com.lifeshs.dao.data;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.pojo.data.AreaVO;

/**
 * 地址Dao
 * @author zhansi.Xu
 * @DateTime 2016年9月5日
 */
@Repository("AreaDao")
public interface IAreaDao {
	
	public List<Map<String,String>> findAllProvince();
	
	public List<Map<String,String>> findCity(String regex);
	
	public List<Map<String,String>> findDistrict(String regex);
	
	public String getProvinceName(@Param("provinceCode") String provinceCode);
	
	public String getCityName(@Param("cityCode") String cityCode);
	
	public String getDistrictName(@Param("districtCode") String districtCode);
	
	public AreaVO getAreaNameByCode(@Param("provinceCode") String provinceCode,
                                    @Param("cityCode") String cityCode,
                                    @Param("districtCode") String districtCode);
	
	public List<String> getAreaCode(@Param("areaName") String areaName);

	public String getCode(@Param("name") String name);

    String findByCode(@Param("provinceCode")String provinceCode,@Param("cityCode") String cityCode,@Param("districtCode")String districtCode);
}
