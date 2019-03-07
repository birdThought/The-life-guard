package com.lifeshs.pojo.paper;

import java.util.List;

/**
 *  版权归
 *  TODO 问卷题目DTO
 *  @author wenxian.cai 
 *  @datetime 2017年3月7日下午4:36:12
 */
public class PaperDTO {
	
	/** 题目id */
	private Integer id;
	
	/** 是否为特殊题目 */
	private Boolean special;
	
	/** 题目名称 */
	private String name;
	
	/** 题目类型 */
	private String topicType;

	/** 性别 */
	private Boolean gender;

	private List<PaperOptionDTO> options;

	@Override
	public String toString() {
		return "PaperDTO{" +
				"id=" + id +
				", special=" + special +
				", name='" + name + '\'' +
				", topicType='" + topicType + '\'' +
				", gender=" + gender +
				", options=" + options +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getSpecial() {
		return special;
	}

	public void setSpecial(Boolean special) {
		this.special = special;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public List<PaperOptionDTO> getOptions() {
		return options;
	}

	public void setOptions(List<PaperOptionDTO> options) {
		this.options = options;
	}
}
