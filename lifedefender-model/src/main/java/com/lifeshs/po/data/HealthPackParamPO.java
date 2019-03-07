package com.lifeshs.po.data;

import lombok.Data;

/**
 * 健康包Po
 * 
 * @author shiqing.zeng
 * @date 2018.1.29
 */
@Data
public class HealthPackParamPO {

	private Integer id;
	/** 健康参数名称 */
	private String name;
	/** 健康参数中文名称 */
	private String name_cn;
	/** 健康参数对应二进制值 */
	private Long value;
	
}
