package com.lifeshs.po.data;

import java.util.Date;

import lombok.Data;

/**
 * 一般健康建议po
 * 
 * @author shiqinag.zeng
 * @date 2018.1.29 11:47
 *
 */
@Data
public class HealthDesNorPO {

	private Integer id;
	/** 开始年龄 */
	private Integer startAge;
	/** 结束年龄 */
	private Integer endAge;
	/** 性别,0_女,1_男 */
	private Boolean gender;
	/** 状态(低_1,偏低_2,正常_3,偏高_4,高_5) */
	private Integer status;
	/** 描述文字 */
	private String description;
	/** 健康参数ID */
	private Integer healthPackageParamId;
	/** 创建者的ID */
	private Integer adminUserId;
	/** 创建日期 */
	private Date createDateTime;
	/** 修改日期 */
	private Date modifyDateTime;
	/** 显示方式,0_隐藏,1_显示 */
	private Integer display;
	/** 健康包实体 */
	private HealthPackParamPO healthPackageParam;
}
