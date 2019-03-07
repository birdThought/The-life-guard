package com.lifeshs.pojo.paper;

/**
 *  版权归
 *  TODO 问卷选项DTO
 *  @author wenxian.cai 
 *  @datetime 2017年3月7日下午5:39:26
 */
public class PaperOptionDTO {

	/** 选项id */
	private Integer id;
	
	/** 选项名称 */
	private String name;
	
	/** 选项分数 */
	private Integer score;

	@Override
	public String toString() {
		return "PaperOptionDTO [id=" + id + ", name=" + name + ", score=" + score + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	
}
