package com.lifeshs.entity.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_data_sport
 */
@Table(name = "t_data_sport", schema = "")
@SuppressWarnings("serial")
public class TDataSport implements Serializable {

	private Integer id;

	private String name;

	private Integer kind;

	/** 能量（/min） */
	private Double kcal;

	public TDataSport() {
		super();
	}

	public TDataSport(Integer id, String name, Integer kind, Double kcal) {
		super();
		this.id = id;
		this.name = name;
		this.kind = kind;
		this.kcal = kcal;
	}

	@Column(name = "id", nullable = true, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "kind", nullable = false, length = 11)
	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	@Column(name = "kcal", nullable = false, length = 12)
	public Double getKcal() {
		return kcal;
	}

	public void setKcal(Double kcal) {
		this.kcal = kcal;
	}

}