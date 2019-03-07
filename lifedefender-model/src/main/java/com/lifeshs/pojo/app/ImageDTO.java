package com.lifeshs.pojo.app;

import lombok.Data;

public @Data class ImageDTO {

	/** 操作是否成功 */
	private Boolean uploadSuccess;
	/** 删除是否成功 */
	private Boolean delSuccess;
	/** 图片相对路径地址，修改为使用netPath */
	@Deprecated
	private String relativePath;
	/** 图片网络地址 */
	private String netPath;
}
