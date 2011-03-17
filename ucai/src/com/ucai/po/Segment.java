package com.ucai.po;

import java.io.Serializable;
import java.util.List;

/**
 * 航程信息PO 一个航程信息包含多种仓位
 * 
 * @author lixu
 * 
 */
public class Segment implements Serializable {
	/**
	 * 序列化版编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 航班号
	 */
	public String fltno;
	/**
	 * 出发城市编码
	 */
	public String sc;
	/**
	 * 出发城市名称
	 */
	public String scAirdrome;
	/**
	 * 到达城市编码
	 */
	public String ec;
	/**
	 * 到达城市
	 */
	public String ecAirdrome;
	/**
	 * 起飞时间
	 */
	public String deptime;
	/**
	 * 到达时间
	 */
	public String arrtime;
	/**
	 * 飞机型号
	 */
	public String planesty;
	/**
	 * 中途停降次数
	 */
	public String stopnum;
	/**
	 * 电子票
	 */
	public String etkt;
	/**
	 * 有餐与否
	 */
	public String meal;
	/**
	 * 出发日期
	 */
	private String date;
	/**
	 * 航班座位信息
	 */
	public List<SeatClass> classesList;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

	public String getArrtime() {
		return arrtime;
	}

	public void setArrtime(String arrtime) {
		this.arrtime = arrtime;
	}

	public List<SeatClass> getClassesList() {
		return classesList;
	}

	public void setClassesList(List<SeatClass> classesList) {
		this.classesList = classesList;
	}

	public String getDeptime() {
		return deptime;
	}

	public void setDeptime(String deptime) {
		this.deptime = deptime;
	}

	public String getEc() {
		return ec;
	}

	public void setEc(String ec) {
		this.ec = ec;
	}

	public String getEcAirdrome() {
		return ecAirdrome;
	}

	public void setEcAirdrome(String ecAirdrome) {
		this.ecAirdrome = ecAirdrome;
	}

	public String getEtkt() {
		return etkt;
	}

	public void setEtkt(String etkt) {
		this.etkt = etkt;
	}

	public String getFltno() {
		return fltno;
	}

	public void setFltno(String fltno) {
		this.fltno = fltno;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getPlanesty() {
		return planesty;
	}

	public void setPlanesty(String planesty) {
		this.planesty = planesty;
	}

	public String getSc() {
		return sc;
	}

	public void setSc(String sc) {
		this.sc = sc;
	}

	public String getScAirdrome() {
		return scAirdrome;
	}

	public void setScAirdrome(String scAirdrome) {
		this.scAirdrome = scAirdrome;
	}

	public String getStopnum() {
		return stopnum;
	}

	public void setStopnum(String stopnum) {
		this.stopnum = stopnum;
	}
}
