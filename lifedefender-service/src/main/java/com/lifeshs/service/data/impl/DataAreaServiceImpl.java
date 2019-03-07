package com.lifeshs.service.data.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao.data.IAreaDao;
import com.lifeshs.pojo.data.AreaVO;
import com.lifeshs.service.data.IDataAreaService;

@Service
public class DataAreaServiceImpl implements IDataAreaService {

	@Autowired
	private IAreaDao areaDao;
	
	@Override
	public List<Map<String, String>> findAllProvince() {
		return areaDao.findAllProvince();
	}

	@Override
	public List<Map<String, String>> findCity(String regex) {
		return areaDao.findCity(regex);
	}

	@Override
	public List<Map<String, String>> findDistrict(String regex) {
		return areaDao.findDistrict(regex);
	}

	@Override
	public String getProvinceName(String provinceCode) {
		String provinceName = "";
		if(StringUtils.isNotBlank(provinceCode)) {
			provinceName = areaDao.getProvinceName(provinceCode+"0000");
		}
		return provinceName;
	}

	@Override
	public String getCityName(String cityCode) {
		String cityName = "";
		if(StringUtils.isNotBlank(cityCode)) {
			cityName = areaDao.getCityName(cityCode+"00");
		}
		return cityName;
	}

	@Override
	public String getDistrictName(String districtCode) {
		String districtName = "";
		if(StringUtils.isNotBlank(districtCode)) {
			districtName = areaDao.getDistrictName(districtCode);
		}
		return districtName;
	}

	@Override
	public AreaVO getAreaNameByCode(String provinceCode, String cityCode, String districtCode) {
		AreaVO areaVO = new AreaVO();
		if(StringUtils.isNotBlank(provinceCode)
			&& StringUtils.isNotBlank(cityCode)
			&& StringUtils.isNotBlank(districtCode)) {
			areaVO = areaDao.getAreaNameByCode(provinceCode+"0000", provinceCode+cityCode+"00", provinceCode+cityCode+districtCode);
		}
		
		String p = areaVO.getProvinceName();
		String c = areaVO.getCityName();
		String d = areaVO.getDistrictName();
		if (p == null) {
			areaVO.setProvinceName("");
		}
		if (c == null) {
			areaVO.setCityName("");
		}
		if (d == null) {
			areaVO.setDistrictName("");
		}
		
		return areaVO;
	}

	@Override
	public List<String> getAreaCode(String areaName) {
		List<String> codes = areaDao.getAreaCode(areaName);
		return codes;
	}

	@Override
	public String getCode(String name) {
		return areaDao.getCode(name);
	}

	@Override
	public String getByCode(String province, String city, String district) {
	    String provinceCode = province + "0000";
       String cityCode = province + city + "00";
		String districtCode = province + city + district ;
		return areaDao.findByCode(provinceCode,cityCode,districtCode);
	}
}
