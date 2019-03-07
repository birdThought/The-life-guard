package com.lifeshs.entity.record;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_record_sport
 */
@Table(name = "t_record_sport", schema = "")
@SuppressWarnings("serial")
public class TRecordSport implements Serializable {

	private Integer id;

	/** 能量总和 */
	private Integer energy;

	private Integer userId;
	
	/** 运动开始时间 */
	private java.sql.Time startTime;

	/** 记录的日期 */
	private java.util.Date recordDate;

	private java.util.Date createDate;
	
	private List<TRecordSportDetail> details;

	public TRecordSport() {
		super();
	}

	public TRecordSport(Integer id, Integer userId, Integer energy, java.sql.Time startTime, java.util.Date recordDate,
			java.util.Date createDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.energy = energy;
		this.startTime = startTime;
		this.recordDate = recordDate;
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

	@Column(name = "energy", nullable = false, length = 11)
	public Integer getEnergy() {
		return energy;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	}

	@Column(name = "startTime", nullable = false, length = 10)
	public java.sql.Time getStartTime() {
		return startTime;
	}

	public void setStartTime(java.sql.Time startTime) {
		this.startTime = startTime;
	}

	@Column(name = "recordDate", nullable = false, length = 10)
	public java.util.Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(java.util.Date recordDate) {
		this.recordDate = recordDate;
	}

	@Column(name = "createDate", nullable = false, length = 19)
	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public List<TRecordSportDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TRecordSportDetail> details) {
		this.details = details;
	}
}
