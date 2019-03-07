package com.lifeshs.pojo.record;

import java.util.List;

import com.lifeshs.entity.record.TRecordSport;
import com.lifeshs.entity.record.TRecordSportDetail;

/**
 *  版权归
 *  TODO 运动数据传输类
 *  @author wenxian.cai 
 *  @datetime 2017年2月24日上午10:34:42
 */
public class SportsDTO {

	/** 运动记录 */
	private TRecordSport recordSport;
	
	/** 运动记录详细集合 */
	private List<TRecordSportDetail> details;
	
	@Override
	public String toString() {
		return "SportsDTO [recordSport=" + recordSport + ", details=" + details + "]";
	}

	public TRecordSport getRecordSport() {
		return recordSport;
	}

	public void setRecordSport(TRecordSport recordSport) {
		this.recordSport = recordSport;
	}

	public List<TRecordSportDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TRecordSportDetail> details) {
		this.details = details;
	}
	
	
}
