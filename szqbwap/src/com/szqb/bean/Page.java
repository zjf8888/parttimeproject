package com.szqb.bean;

import java.util.List;

import com.szqb.web.AbstractBean;
import com.szqb.web.IndexBean;

public class Page extends AbstractBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private List<Content> list;

	public String page() {
		Page page = IndexBean.getPageList().get(id);
		title = page.getTitle();
		list = page.getList();
		return SUCCESS;
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

	public List<Content> getList() {
		return list;
	}

	public void setList(List<Content> list) {
		this.list = list;
	}
}
