package com.lifeshs.po.user;

import lombok.Data;

/**
 * 微信授权后获取的用户信息
 * author: wenxian.cai
 * date: 2017/11/7 11:56
 */
@Data
public class WeiXinUserInfoPO {

	/** openId */
	private String openid;
	/** 微信昵称 */
	private String nickname;
	/** 性别 */
	private String sex;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	/** 国家 */
	private String country;
	/** 头像路径*/
	private String headimgurl;
	/** 用户特权信息，json 数组 */
	private String[] privilege;
	/** unionId */
	private String unionid;
}
