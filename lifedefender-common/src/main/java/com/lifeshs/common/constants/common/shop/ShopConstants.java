package com.lifeshs.common.constants.common.shop;

/**
 * 
 * @author liu
 * @时间 2018年12月14日 上午10:26:21
 * @说明 商城有关枚举集合
 */
public class ShopConstants {
	/**
	 * 商铺状态
	 * @author liu
	 * @时间 2018年12月14日 上午10:26:15
	 * @说明
	 */
	public enum ShopState {
		待审核(0)
		,审核不通过(1)
		,审核通过(2)
		,冻结(3)
		,解冻(4);
		
		private int code;
		
		private ShopState(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
	}
	
	/**
	 * 店铺审核表动作
	 * @author liu
	 * @时间 2018年12月14日 上午10:25:04
	 * @说明 不通过-1,通过-2
	 */
	public enum ShopAuthit {
		不通过(1),
		通过(2);
		
		private int code;
		
		private ShopAuthit(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
	}
	
	/**
	 * 商品状态
	 * @author liu
	 * @时间 2018年12月14日 上午10:24:44
	 * @说明 1.待上架 2.已上架 3.已下架
	 */
	public enum GoodsStatus {
		待上架(1)
		,已上架(2)
		,已下架(3);
		
		private int code;
		
		private GoodsStatus(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
	}
	
	/**
	 * 商城首页广告状态
	 * @author liu
	 * @时间 2018年12月21日 上午10:52:33
	 * @说明
	 */
	public enum AdvertStatus {
		删除(0),
		使用(1);
		
		private int code;
		
		private AdvertStatus(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
	}
	
	public enum AdvertType {
		商铺广告(1),
		商品广告(2);
		
		private int code;
		
		private AdvertType(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
		
		public boolean codeEq(Integer code) {
			return this.code == code;
		}
	}
	
	public enum GoodsSpecType {
		统一规格(1),
		多规格(2);
		
		private int code;
		
		private GoodsSpecType(int code) {
			this.code = code;
		}
		
		public int code() {
			return code;
		}
		
		public boolean codeEq(Integer code) {
			return this.code == code;
		}
	}
}
