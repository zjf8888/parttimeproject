package com.szqb.web;

import java.util.List;
import java.util.Map;

import com.szqb.bean.Content;
import com.szqb.bean.Page;
import com.szqb.bean.Title;
import com.szqb.util.QbExp;

public class IndexBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	private static List<Title> titleList;
	private static Map<String, Page> pageList;
	private static Map<String, Content> contentList;
	private static IndexBean index = new IndexBean();
	private IndexBean() {
	}

	public static IndexBean getIndex() {
		return index;
	}

	public static List<Title> getTitleList() {
		if (titleList == null) {
			QbExp qb = new QbExp();
			qb.run();
		}
		return titleList;
	}

	public static void setTitleList(List<Title> titleList) {
		IndexBean.titleList = titleList;
	}

	public static Map<String, Content> getContentList() {
		return contentList;
	}

	public static void setContentList(Map<String, Content> contentList) {
		IndexBean.contentList = contentList;
	}

	public static Map<String, Page> getPageList() {
		return pageList;
	}

	public static void setPageList(Map<String, Page> pageList) {
		IndexBean.pageList = pageList;
	}

}
