package com.ucai.po;


/**
 * 航程信息PO
 * 一个航程信息包含多种仓位
 * 
 * @author lixu
 *
 */
public class Segment {
	public String fltno;//航班号

	public String sc;//出发城市编码

	public String scAirdrome;

	public String ec;//到达城市编码

	public String ecAirdrome;

	public String deptime;//起飞时间

	public String arrtime;//到达时间

	public String planesty;//飞机型号

	public String stopnum;//中途停降次数

	public String etkt;//电子票

	public String meal;//有餐

	public SeatClass[] classesList;//航班座位信息

	public String getArrtime() {
		return arrtime;
	}

	public void setArrtime(String arrtime) {
		this.arrtime = arrtime;
	}

	public SeatClass[] getClassesList() {
		return classesList;
	}

	public void setClassesList(SeatClass[] classesList) {
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
