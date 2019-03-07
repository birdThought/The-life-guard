package com.lifeshs.vo.business;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 渠道商关联的用户
 * 
 * @author zizhen.huang
 * @DateTime 2018年1月4日14:53:46
 */
@Data
public class BusinessUserVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** 用户id */
	private Integer id;
	/** 用户姓名 */
	private String realName;
	/** 性别 */
	private Integer gender;
	/** 年龄 */
	private Integer age;
	/** 注册日期 */
	private Date createDate;
	/** 引入日期 */
	private Date joinDate;
}
