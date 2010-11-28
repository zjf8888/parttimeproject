package com.ucai.po;

import java.util.List;

/**
 * 航班信息 一个航班包括多个航程
 * 
 * @author lixu
 * 
 */
public class Flight {
	public String transId;// 事务ID 翻页的时候服务器可判断是那个缓存内容的翻页

	public String errorCode;

	public String errorTips;

	public String startcity; // 出发城市编码

	public String endcity;// 到达城市编码

	public String startdate;// 出发日期

	public List<Segment> segmentList;// 航程列表

	public int totalNums;// 总记录数 总共应该有多少航班

	public int totalPages;// 总页数

	public int pageNo;// 页号，从1开始 当前页

	public String getEndcity() {
		return endcity;
	}

	public void setEndcity(String endcity) {
		this.endcity = endcity;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorTips() {
		return errorTips;
	}

	public void setErrorTips(String errorTips) {
		this.errorTips = errorTips;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<Segment> getSegmentList() {
		return segmentList;
	}

	public void setSegmentList(List<Segment> segmentList) {
		this.segmentList = segmentList;
	}

	public String getStartcity() {
		return startcity;
	}

	public void setStartcity(String startcity) {
		this.startcity = startcity;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public int getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(int totalNums) {
		this.totalNums = totalNums;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

}
