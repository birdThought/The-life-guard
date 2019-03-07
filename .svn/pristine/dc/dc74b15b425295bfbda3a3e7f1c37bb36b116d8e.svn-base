package com.lifeshs.entity.record;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * t_record_physicals
 */
 @Table(name = "t_record_physicals", schema = "")
 @SuppressWarnings("serial")
public class TRecordPhysicals implements Serializable{

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
	private Date physicalsDate;
	
	/**图片1路径*/
	private String img1;
	
	/**图片2路径*/
	private String img2;
	
	/**图片3路径*/
	private String img3;
	
	/**创建时间*/
	private Date createDate;
	
	/**描述信息*/
	private String description;
		
	
	public TRecordPhysicals() {
		super();
	}
	
	public TRecordPhysicals(Integer id, Integer userId, String title, String physicalsOrg, Date physicalsDate, String img1, String img2, String img3, Date createDate) {
		super();
		this.id = id; 
		this.userId = userId; 
		this.title = title; 
		this.physicalsOrg = physicalsOrg; 
		this.physicalsDate = physicalsDate; 
		this.img1 = img1; 
		this.img2 = img2; 
		this.img3 = img3; 
		this.createDate = createDate; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	
	@Column(name ="userId",nullable=false,length=11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	} 
	
	@Column(name ="title",nullable=false,length=30)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	} 
	
	@Column(name ="physicalsOrg",nullable=false,length=30)
	public String getPhysicalsOrg() {
		return physicalsOrg;
	}

	public void setPhysicalsOrg(String physicalsOrg) {
		this.physicalsOrg = physicalsOrg;
	} 
	
	@Column(name ="physicalsDate",nullable=false,length=10)
	public Date getPhysicalsDate() {
		return physicalsDate;
	}

	public void setPhysicalsDate(Date physicalsDate) {
		this.physicalsDate = physicalsDate;
	} 
	
	@Column(name ="img1",nullable=false,length=150)
	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	} 
	
	@Column(name ="img2",nullable=false,length=150)
	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	} 
	
	@Column(name ="img3",nullable=false,length=150)
	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	} 
	
	@Column(name ="createDate",nullable=false,length=19)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name ="description",nullable=false,length=19)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
	
	
}
