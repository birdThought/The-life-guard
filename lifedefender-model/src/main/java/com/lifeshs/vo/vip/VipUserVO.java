package com.lifeshs.vo.vip;

import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.po.vip.VipUserPO;
import lombok.Data;

import java.util.Date;

/**
 * vip会员
 * author: wenxian.cai
 * date: 2017/9/29 10:01
 */
public @Data class VipUserVO {

	private VipUserPO vipUserPO;

	private VipComboPO vipComboPO;

	private int userId;

	private String userName;

	private String realName;

	private String userCode;

	private boolean gender;

	private Date birthday;

	private float height;

	private float weight;

	private Float waist;

	private Float bust;

	private Float hip;

	private String mobile;

	private String photo;

	private Date createDate;
}
