package com.lifeshs.entity.device;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_app_version
 */
@Table(name = "t_app_version", schema = "")
@SuppressWarnings("serial")
public class TAppVersion implements Serializable{
	
	private String id;
	
	
	private String version;
	
	
	private String appname;
	
	public TAppVersion() {
		super();
	}
	
	public TAppVersion(String id, String version, String appname) {
		super();
		this.id = id; 
		this.version = version; 
		this.appname = appname; 
	}
	
	@Column(name ="id",nullable=true,length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="version",nullable=false,length=10)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	@Column(name ="appname",nullable=false,length=200)
	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}
	
}
