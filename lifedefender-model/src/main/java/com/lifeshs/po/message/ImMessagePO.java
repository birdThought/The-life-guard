package com.lifeshs.po.message;

import com.lifeshs.utils.UUID;
import lombok.Data;

import java.io.Serializable;

/**
 * IM聊天信息
 * author: wenxian.cai
 * date: 2017/9/21 14:29
 */
public @Data class ImMessagePO implements Serializable{
	/** 内容类型 */
	private String contentType;

	/** 内容 */
	private String content;

	/** 环信消息id */
	private String id;

	/** 发送者*/
	private String from;

	/** 接受者*/
	private String to;

	/** 聊天类型（chat/groupchat）*/
	private String type;

	/** 是否已读*/
	private boolean read;

	/** 时间*/
	private String time;

	/** 是否自己为发送者*/
	private boolean mine;

	/** 头像*/
	private String head;

	/** 用户名*/
	private String userName;

	/** 发送状态（true, false, loading）*/  //todo 分类修改
	private String status;

	/** 是否为第一次对话*/
	private boolean speak0;

	private String owner;

}
