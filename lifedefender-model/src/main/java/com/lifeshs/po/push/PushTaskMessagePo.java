package com.lifeshs.po.push;

import java.util.Date;

import lombok.Data;

public @Data class PushTaskMessagePo {
	
	private Integer id;
	private Integer sendId;
	private String receiceId;
	private String content;
	private String sendTime;
	private String cycle;
	private Integer status;
	private Date creactTime;
	
}
