package com.lifeshs.pojo.paper;

/**
 *  版权归
 *  TODO 9种体质分析传输类
 *  @author wenxian.cai 
 *  @datetime 2017年3月9日下午4:05:17
 */

public class PaperPhysicalStandardDTO {
	
	/** 体质名称 */
	private String physicalName;
	
	/** 病因 */
	private String cause;
	
	/** 症状*/
	private String symptoms;
	
	/** 临床表现 */
	private String manifestations;
	
	/** 易感疾病 */
	private String susceptibleDisease;
	
	/** 建议 */
	private String suggestion;

	@Override
	public String toString() {
		return "PaperPhysicalStandardDTO [physicalName=" + physicalName + ", cause=" + cause + ", symptoms=" + symptoms
		        + ", manifestations=" + manifestations + ", susceptibleDisease=" + susceptibleDisease + ", suggestion="
		        + suggestion + "]";
	}

	public String getPhysicalName() {
		return physicalName;
	}

	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getManifestations() {
		return manifestations;
	}

	public void setManifestations(String manifestations) {
		this.manifestations = manifestations;
	}

	public String getSusceptibleDisease() {
		return susceptibleDisease;
	}

	public void setSusceptibleDisease(String susceptibleDisease) {
		this.susceptibleDisease = susceptibleDisease;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
}
