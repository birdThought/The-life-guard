package com.lifeshs.service.data;

import java.util.List;
import java.util.Map;

import com.lifeshs.pojo.data.AreaVO;

/**
 *  地区服务抽象类
 *  @author yuhang.weng  
 *  @DateTime 2016年10月11日 下午3:27:44
 */
public interface IDataAreaService {

	/**
	 *  获取省份
	 *  @author yuhang.weng 
	 *	@DateTime 2016年10月11日 下午3:28:08
	 *
	 *  @return
	 */
	public List<Map<String,String>> findAllProvince();
	
	/**
	 *  获取城市
	 *  @author yuhang.weng 
	 *	@DateTime 2016年10月11日 下午3:28:19
	 *
	 *  @param regex
	 *  @return
	 */
	public List<Map<String,String>> findCity(String regex);
	
	/**
	 *  获取地区
	 *  @author yuhang.weng 
	 *	@DateTime 2016年10月11日 下午3:28:34
	 *
	 *  @param regex
	 *  @return
	 */
	public List<Map<String,String>> findDistrict(String regex);
	
	/**
	 *  获取省份名称
	 *  @author yuhang.weng 
	 *	@DateTime 2016年10月11日 下午4:01:05
	 *
	 *  @param provinceCode
	 *  @return
	 */
	public String getProvinceName(String provinceCode);
	
	/**
	 *  获取城市名称
	 *  @author yuhang.weng 
	 *	@DateTime 2016年10月11日 下午4:01:41
	 *
	 *  @param cityCode
	 *  @return
	 */
	public String getCityName(String cityCode);
	
	/**
	 *  获取地区名称
	 *  @author yuhang.weng 
	 *	@DateTime 2016年10月11日 下午4:02:06
	 *
	 *  @param districtCode
	 *  @return
	 */
	public String getDistrictName(String districtCode);
	
	/**
	 *  获取地点名称
	 *  @author yuhang.weng 
	 *	@DateTime 2016年10月11日 下午5:42:42
	 *
	 *  @param provinceCode
	 *  @param cityCode
	 *  @param districtCode
	 *  @return
	 */
	public AreaVO getAreaNameByCode(String provinceCode, String cityCode, String districtCode);
	
	/**
	 *  获取地区代码
	 *  @author yuhang.weng 
	 *	@DateTime 2016年11月24日 下午3:10:25
	 *
	 *  @param areaName
	 *  @return
	 */
	public List<String> getAreaCode(String areaName);

	/**
	 * @Description: 获取code
	 * @author: wenxian.cai
	 * @create: 2017/4/21 15:56
	 */
	public String getCode(String name);


	/**
	 *	根据代码找到对应地址
	 *	@DateTime 2016年10月11日 下午5:42:42
	 *
	 *  @param provinceCode
	 *  @param cityCode
	 *  @param districtCode
	 *  @return
	 */
	public String  getByCode(String provinceCode, String cityCode, String districtCode);
}
