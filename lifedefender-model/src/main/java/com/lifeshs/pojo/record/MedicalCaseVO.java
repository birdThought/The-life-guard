package com.lifeshs.pojo.record;

import java.util.List;

import com.lifeshs.entity.record.TRecordMedical;
import com.lifeshs.entity.record.TRecordMedicalCourse;

/**
 *  病历+病程VO对象
 *  @author yuhang.weng  
 *  @DateTime 2016年8月20日 下午4:04:30
 */
public class MedicalCaseVO {
	
	private TRecordMedical medical;
	
	private List<TRecordMedicalCourse> courses;

	public TRecordMedical getMedical() {
		return medical;
	}

	public void setMedical(TRecordMedical medical) {
		this.medical = medical;
	}

	public List<TRecordMedicalCourse> getCourses() {
		return courses;
	}

	public void setCourses(List<TRecordMedicalCourse> courses) {
		this.courses = courses;
	}
	
}
