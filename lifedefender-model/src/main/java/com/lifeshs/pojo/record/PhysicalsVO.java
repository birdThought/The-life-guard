package com.lifeshs.pojo.record;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *  体检报告VO
 *  @author yuhang.weng  
 *  @DateTime 2016年9月20日 上午11:23:49
 */
public class PhysicalsVO {

	/**健康档案(体检报告)*/
	private Integer id;
	
	/**用户ID*/
	private Integer userId;
	
	/**标题*/
	private String title;
	
	/**体检机构*/
	private String physicalsOrg;
	
	/**体检日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date physicalsDate;
	
	/**描述信息*/
	private String description;
	
	/** 图片保存路径 */
	private List<String> photos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhysicalsOrg() {
		return physicalsOrg;
	}

	public void setPhysicalsOrg(String physicalsOrg) {
		this.physicalsOrg = physicalsOrg;
	}

	public java.util.Date getPhysicalsDate() {
		return physicalsDate;
	}

	public void setPhysicalsDate(java.util.Date physicalsDate) {
		this.physicalsDate = physicalsDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
}
