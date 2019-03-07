package com.lifeshs.utils;

public class CommonParam {
	
	public static final String apk_download = "/var/tomcat/default/webapps/tz_res/download/apk/";// apk确认上传的正式路径
	public static final String apk_download_tmp = "/var/tomcat/default/webapps/tz_res/download/tmp";// apk上传的临时路径

	public static final String IMAGE_ROOT_PATH = "/life/lifeshs/lifekeepers_files";// 图片保存的路径

	/******************** 以下为在IMAGE_ROOT_PATH下不同图片保存的根目录 ****************/
	public static final String LOGO = "logo"; // 机构logo
	public static final String LICENCE = "license";// 机构营业执照
	public static final String SERVE = "serve";// 服务图片
	public static final String FOOD = "food";// 食物图片
	public static final String HTML = "html";// 资讯上传图片产生的路径
	public static final String HEAD = "head";// 用户头像
	/** 广告 */
	public static final String AD = "ad";
}
