package com.lifeshs.common.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Map;

/**
 *  版权归
 *  TODO $.ajax后需要接受的JSON
 *  @author duosheng.mo  
 *  @DateTime 2016年4月20日 下午5:26:18
 */
@SuppressWarnings("serial")
public class AjaxJson implements Serializable{

	private boolean success = true;// 是否成功
	private String msg = "操作成功";// 提示信息
	private Object obj = null;// 其他信息
	private Map<String, Object> attributes;// 其他参数
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getJsonStr(){
		JSONObject obj = new JSONObject();
		obj.put("success", this.isSuccess());
		obj.put("msg", this.getMsg());
		obj.put("obj", this.obj);
		obj.put("attributes", this.attributes);
		return obj.toJSONString();
	}

	@Override
	public String toString() {
		return "AjaxJson{" +
				"success=" + success +
				", msg='" + msg + '\'' +
				", obj=" + obj +
				", attributes=" + attributes +
				'}';
	}
}
