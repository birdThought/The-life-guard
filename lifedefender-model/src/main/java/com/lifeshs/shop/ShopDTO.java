package com.lifeshs.shop;

import java.util.Date;

import lombok.Data;

/*
 * 商铺表(t_shop)
 * Founder: jiang chang bin   2018/11/8 17:23
 */
public @Data class ShopDTO {

	        //编号
			private Integer id;

			//商铺名称
			private String shopName;
			
			//所属门店编号
			private Integer orgId;
			
			private Integer userId;
					
			//开通时间
			private Date createTime;
			
			//状态修改时间
			private Date stateTime;
			
			//联系人
			private String userName;
			
			//手机号
			private String mobile;
			
			//电话(座机)
			private String telephone;
			
			//实际地址
			private String address;
			
			// 状态
			private Integer state;
			
			// 身份证号
			private String identification;
			
			// 身份证正面
			private String identificationFore;
			
			// 身份证反面
			private String identificationBack;
			
			// 行政编码
			private String areaCode;
}
