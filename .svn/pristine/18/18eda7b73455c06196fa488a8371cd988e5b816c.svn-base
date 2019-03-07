package com.lifeshs.entity.consult;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by XuZhanSi on 2016/12/15 0015.
 */
@Table(name = "t_information")
public class TInformation implements Serializable {
	/** 描述 */
	private static final long serialVersionUID = 4698118628270282819L;
	private Integer id;
	private String title;// 标题
	private String content;// 内容
	private String source;// 来源
	private String auther;// 作者
	private String image;// 主要展示的图片
	private Integer columnId;// 对应的栏目
	private Date createDate;// 创建时间

	private TInfomationColumn c;	// 对应栏目

	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "source")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "auther")
	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	@Column(name = "image")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "columnId")
	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	@Column(name = "createDate")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public TInfomationColumn getC() {
		return c;
	}

	public void setC(TInfomationColumn c) {
		this.c = c;
	}

}
