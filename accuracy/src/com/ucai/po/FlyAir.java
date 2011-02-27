package com.ucai.po;

/**
 * 订票航程信息
 * @author lixu
 *
 */
public class FlyAir {
    /**
     * 航班号
     */
    public String flyNo;
    /**
     * 舱位等级
     */
    public String flyClass;
    /**
     * 票价
     */
    public String flyPrice;
    /**
     * 机建
     */
    public String buildfee;
    /**
     * 燃油
     */
    public String fuelfee;
    /**
     * 机型
     */
    public String planesty;
    /**
     * 出发城市三字码
     */
    public String sc;
    /**
     * 目标城市三字码
     */
    public String ec;
    /**
     * 起飞日期
     */
    public String sDate;
    /**
     * 抵达日期
     */
    public String eDate;
    /**
     * 起飞时间
     */
    public String sTime;
    /**
     * 抵达时间
     */
    public String eTime;
	public String getFlyNo() {
		return flyNo;
	}
	public void setFlyNo(String flyNo) {
		this.flyNo = flyNo;
	}
	public String getFlyClass() {
		return flyClass;
	}
	public void setFlyClass(String flyClass) {
		this.flyClass = flyClass;
	}
	public String getFlyPrice() {
		return flyPrice;
	}
	public void setFlyPrice(String flyPrice) {
		this.flyPrice = flyPrice;
	}
	public String getBuildfee() {
		return buildfee;
	}
	public void setBuildfee(String buildfee) {
		this.buildfee = buildfee;
	}
	public String getFuelfee() {
		return fuelfee;
	}
	public void setFuelfee(String fuelfee) {
		this.fuelfee = fuelfee;
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
	public String getEc() {
		return ec;
	}
	public void setEc(String ec) {
		this.ec = ec;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	public String geteTime() {
		return eTime;
	}
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}
    
}
