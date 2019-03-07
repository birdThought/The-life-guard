package com.lifeshs.entity.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_data_sport_kind
 */
@Table(name = "t_data_sport_kind", schema = "")
@SuppressWarnings("serial")
public class TDataSportKind implements Serializable {

	private Integer id;

	/** 运动种类名称 */
	private String name;
	
	private List<TDataSport> sports;

	public TDataSportKind() {
		super();
	}

	public TDataSportKind(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Column(name = "id", nullable = true, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TDataSport> getSports() {
		return sports;
	}

	public void setSports(List<TDataSport> sports) {
		this.sports = sports;
	}

}