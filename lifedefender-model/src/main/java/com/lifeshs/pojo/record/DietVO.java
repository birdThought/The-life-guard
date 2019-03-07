package com.lifeshs.pojo.record;

import java.util.List;

/**
 *  饮食VO
 *  @author yuhang.weng  
 *  @DateTime 2016年9月20日 下午2:52:11
 */
public class DietVO {

	/** ID */
	private Integer id;
	/** 饮食类型（早餐，早餐加餐，午餐，午餐加餐，晚餐，晚餐加餐） */
	private String dietType;
	/** 能量 */
	private String energy;
	/** 饮食详细清单 */
	private List<String> dietDetails;
	/**时间*/
	private String dietTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDietType() {
		return dietType;
	}

	public void setDietType(String dietType) {
		this.dietType = dietType;
	}

	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String energy) {
		this.energy = energy;
	}

	public List<String> getDietDetails() {
		return dietDetails;
	}

	public void setDietDetails(List<String> dietDetails) {
		this.dietDetails = dietDetails;
	}

	public String getDietTime() {
		return dietTime;
	}

	public void setDietTime(String dietTime) {
		this.dietTime = dietTime;
	}
}
