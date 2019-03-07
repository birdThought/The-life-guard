package com.lifeshs.entity.record;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * t_record_medical_course
 */
 @Table(name = "t_record_medical_course", schema = "")
 @SuppressWarnings("serial")
public class TRecordMedicalCourse implements Serializable{

		/**健康档案(病历-病程)*/
		private Integer id;
		
		/**病历ID*/
		private Integer medicalId;
		
		/**病程类型(首诊、复诊、出院)*/
		private String courseType;
		
		/**备注*/
		private String remark;
		
		/**就诊日期*/
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date visitingDate;
		
		/**图片1路径*/
		private String img1;
		
		/**图片2路径*/
		private String img2;
		
		/**图片3路径*/
		private String img3;
		
		/**创建时间*/
		private Date createDate;
		
	
	public TRecordMedicalCourse() {
		super();
	}
	
	public TRecordMedicalCourse(Integer id, Integer medicalId, String courseType, String remark, Date visitingDate, String img1, String img2, String img3, Date createDate) {
		super();
		this.id = id; 
		this.medicalId = medicalId; 
		this.courseType = courseType; 
		this.remark = remark; 
		this.visitingDate = visitingDate; 
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
	
	@Column(name ="medicalId",nullable=true,length=11)
	public Integer getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(Integer medicalId) {
		this.medicalId = medicalId;
	} 
	
	@Column(name ="courseType",nullable=false,length=10)
	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	} 
	
	@Column(name ="remark",nullable=false,length=500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	} 
	
	@Column(name ="visitingDate",nullable=false,length=10)
	public Date getVisitingDate() {
		return visitingDate;
	}

	public void setVisitingDate(Date visitingDate) {
		this.visitingDate = visitingDate;
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
	
	
}
