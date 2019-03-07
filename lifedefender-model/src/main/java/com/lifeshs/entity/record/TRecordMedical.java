package com.lifeshs.entity.record;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * t_record_medical
 */
@Table(name = "t_record_medical", schema = "")
@SuppressWarnings("serial")
public class TRecordMedical implements Serializable {
	/** 健康档案(病历) */
	private Integer id;

	/** 用户ID */
	private Integer userId;

	/** 标题 */
	private String title;

	/** 就诊日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date visitingDate;

	/** 就诊医院 */
	private String hospital;

	/** 科室ID */
	private Integer departmentId;

	/** 医生诊断 */
	private String doctorDiagnosis;

	/** 基本病情 */
	private String basicCondition;

	/** 创建时间 */
	private Date createDate;

	public TRecordMedical() {
		super();
	}

	public TRecordMedical(Integer id, Integer userId, String title, Date visitingDate, String hospital,
			Integer departmentId, String doctorDiagnosis, String basicCondition, Date createDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.visitingDate = visitingDate;
		this.hospital = hospital;
		this.departmentId = departmentId;
		this.doctorDiagnosis = doctorDiagnosis;
		this.basicCondition = basicCondition;
		this.createDate = createDate;
	}

	@Column(name = "id", nullable = true, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userId", nullable = true, length = 11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "title", nullable = true, length = 30)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "visitingDate", nullable = false, length = 10)
	public Date getVisitingDate() {
		return visitingDate;
	}

	public void setVisitingDate(Date visitingDate) {
		this.visitingDate = visitingDate;
	}

	@Column(name = "hospital", nullable = false, length = 30)
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	@Column(name = "departmentId", nullable = false, length = 11)
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "doctorDiagnosis", nullable = false, length = 500)
	public String getDoctorDiagnosis() {
		return doctorDiagnosis;
	}

	public void setDoctorDiagnosis(String doctorDiagnosis) {
		this.doctorDiagnosis = doctorDiagnosis;
	}

	@Column(name = "basicCondition", nullable = false, length = 500)
	public String getBasicCondition() {
		return basicCondition;
	}

	public void setBasicCondition(String basicCondition) {
		this.basicCondition = basicCondition;
	}

	@Column(name = "createDate", nullable = false, length = 19)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}