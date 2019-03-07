package com.lifeshs.entity.record;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import com.lifeshs.entity.data.TDataSport;

/**
 * t_record_sport_detail
 */
@Table(name = "t_record_sport_detail", schema = "")
@SuppressWarnings("serial")
public class TRecordSportDetail implements Serializable {

	private Integer id;

	/** t_record_sport表的id */
	private Integer recordId;

	/** t_data_sport表的id */
	private Integer sportId;

	/** 时长，单位分钟 */
	private Integer duration;
	
	private TDataSport sport;

	public TRecordSportDetail() {
		super();
	}

	public TRecordSportDetail(Integer id, Integer recordId, Integer sportId, Integer duration) {
		super();
		this.id = id;
		this.recordId = recordId;
		this.sportId = sportId;
		this.duration = duration;
	}

	@Column(name = "id", nullable = true, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "recordId", nullable = false, length = 11)
	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	@Column(name = "sportId", nullable = false, length = 11)
	public Integer getSportId() {
		return sportId;
	}

	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}

	@Column(name = "duration", nullable = false, length = 5)
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public TDataSport getSport() {
		return sport;
	}

	public void setSport(TDataSport sport) {
		this.sport = sport;
	}
}