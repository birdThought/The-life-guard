package com.lifeshs.pojo.client;


import com.lifeshs.vo.Menu.MenuVo;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 *  版权归
 *  TODO 类说明  在线用户对象
 *  @author duosheng.mo  
 *  @DateTime 2016年4月26日 上午9:49:49
 */
public @Data class
Client implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean admin;
	
	private LoginUser user;

	/** 用户IP */
	private String ip;
	
	/** 登录时间 */
	private java.util.Date logindatetime;
	
	/** 国家 */
	private String country;
	
	/** 国家代码 */
	private String countryId;

	/**权限集合*/
	private List<String> permissions;

	/** 用户菜单 */

	private Map<String,List<MenuVo>> menu;
	
	
}
