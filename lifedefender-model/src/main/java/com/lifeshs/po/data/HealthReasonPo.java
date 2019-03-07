package com.lifeshs.po.data;

import java.sql.Date;

import lombok.Data;

@Data
public class HealthReasonPo {

	private Integer id;
	/** 健康参数Id */
	private Integer healthPackageParamId;
	/** 行成原因 */
	private String reason;
	/** 状态(低_1,偏低_2,正常_3,偏高_4,高_5) */
	private String status;
	/** 是否为专业原因 否_0,是_1 */
	private Integer professional;
	/** 创建日期 */
	private Date createDate;
	/**修改日期 */
	private Date modifyDate;
	/** 健康包实体 */
	private HealthPackParamPO healthPackageParam;

}
