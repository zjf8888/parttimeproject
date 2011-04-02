package com.szqb.bean;

import java.util.ArrayList;
import java.util.Map;

import com.szqb.web.AbstractBean;
import com.szqb.web.IndexBean;

public class Content extends AbstractBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String secondtitle;
	private String content;
	private ArrayList<byte[]> imageList;

	public ArrayList<byte[]> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<byte[]> imageList) {
		this.imageList = imageList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSecondtitle() {
		return secondtitle;
	}

	public void setSecondtitle(String secondtitle) {
		this.secondtitle = secondtitle;
	}

	public String content() {
		Map<String, Content> contentList = IndexBean.getContentList();
		Content content = contentList.get(id);
		title = content.getTitle();
		secondtitle = content.getSecondtitle();
		this.content = content.getContent();
		imageList=content.getImageList();
		return SUCCESS;
	}
}
