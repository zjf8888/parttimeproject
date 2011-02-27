package com.ucai.po;

/**
 * 飞行订单类
 * 
 * @author lin
 * 
 */
public class AirOrder {
	/**
	 * 航班号前面的两条字母（航空公司二字简码）
	 */
	private String a_Company;
	/**
	 * 航班号
	 */
	private String a_FlyNo;
	/**
	 * 出发城市三字编码
	 */
	private String a_Scity;
	/**
	 * 目标城市三字编码
	 */
	private String a_Ecity;
	/**
	 * 舱位码
	 */
	private String a_Class;
	/**
	 * 出发时间
	 */
	private String a_FlySTime;
	/**
	 * 到达时间
	 */
	private String a_FlyETime;
	/**
	 * 起飞日期
	 */
	private String a_FlyDate;
	/**
	 * 机型
	 */
	private String a_PlaneType;
	/**
	 * 订座编码
	 */
	private String a_Pnr;
	/**
	 * PNR状态
	 */
	private String a_PnrState;
	/**
	 * 舱位的票价
	 */
	private String a_ClassPrice;
	/**
	 * 对应航程的价格
	 */
	private String a_BuildFee;
	/**
	 * 对应航程的价格
	 */
	private String a_FuelFee;
	/**
	 * 退改签规定
	 */
	private String a_TGQ;

	public String getA_BuildFee() {
		return a_BuildFee;
	}

	public void setA_BuildFee(String buildFee) {
		a_BuildFee = buildFee;
	}

	public String getA_Class() {
		return a_Class;
	}

	public void setA_Class(String class1) {
		a_Class = class1;
	}

	public String getA_ClassPrice() {
		return a_ClassPrice;
	}

	public void setA_ClassPrice(String classPrice) {
		a_ClassPrice = classPrice;
	}

	public String getA_Company() {
		return a_Company;
	}

	public void setA_Company(String company) {
		a_Company = company;
	}

	public String getA_Ecity() {
		return a_Ecity;
	}

	public void setA_Ecity(String ecity) {
		a_Ecity = ecity;
	}

	public String getA_FlyDate() {
		return a_FlyDate;
	}

	public void setA_FlyDate(String flyDate) {
		a_FlyDate = flyDate;
	}

	public String getA_FlyETime() {
		return a_FlyETime;
	}

	public void setA_FlyETime(String flyETime) {
		a_FlyETime = flyETime;
	}

	public String getA_FlyNo() {
		return a_FlyNo;
	}

	public void setA_FlyNo(String flyNo) {
		a_FlyNo = flyNo;
	}

	public String getA_FlySTime() {
		return a_FlySTime;
	}

	public void setA_FlySTime(String flySTime) {
		a_FlySTime = flySTime;
	}

	public String getA_FuelFee() {
		return a_FuelFee;
	}

	public void setA_FuelFee(String fuelFee) {
		a_FuelFee = fuelFee;
	}

	public String getA_PlaneType() {
		return a_PlaneType;
	}

	public void setA_PlaneType(String planeType) {
		a_PlaneType = planeType;
	}

	public String getA_Pnr() {
		return a_Pnr;
	}

	public void setA_Pnr(String pnr) {
		a_Pnr = pnr;
	}

	public String getA_PnrState() {
		return a_PnrState;
	}

	public void setA_PnrState(String pnrState) {
		a_PnrState = pnrState;
	}

	public String getA_Scity() {
		return a_Scity;
	}

	public void setA_Scity(String scity) {
		a_Scity = scity;
	}

	public String getA_TGQ() {
		return a_TGQ;
	}

	public void setA_TGQ(String a_tgq) {
		a_TGQ = a_tgq;
	}
}
