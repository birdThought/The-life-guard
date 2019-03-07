package com.lifeshs.pojo.paper;

/**
 *  版权归
 *  TODO 亚健康结果分析传输类
 *  @author wenxian.cai 
 *  @datetime 2017年3月8日下午5:32:36
 */
public class PaperSubHealthStandardDTO {

	/**所属区间*/
	private Integer intervalNumber;
	
	/** 基础描述 */
	private String basicDescription;
	
	/** 建议 */
	private String suggestion;

	@Override
	public String toString() {
		return "PaperSubHealthStandardDTO [basicDescription=" + basicDescription + ", suggestion=" + suggestion + ", intervalNumber=" + intervalNumber + " ]";
	}

	public String getBasicDescription() {
		return basicDescription;
	}

	public void setBasicDescription(String basicDescription) {
		this.basicDescription = basicDescription;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public Integer getIntervalNumber() {
		return intervalNumber;
	}

	public void setIntervalNumber(Integer intervalNumber) {
		this.intervalNumber = intervalNumber;
	}

}
