package com.ucai.po;

import java.io.Serializable;

/**
 * 扣位返回信息类
 * 
 * @author lin
 * 
 */
public class ReturnPo implements Serializable {
	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 状态码
	 */
	private String code; 
	/**
	 * 描述信息
	 */
	private String info;
	/**
	 * 机票订单号
	 */
	private String pnr;
	/**
	 * 航运公司二字码
	 */
	private String FlyConpany;
	/**
	 * 机票价格
	 */
	private String ticketPrice;
	/**
	 * 机场建设费
	 */
	private String tax;
	/**
	 * 燃油附加费
	 */
	private String fuel;
	/**
	 * 总价
	 */
	private String price;
	/**
	 * 订单编号
	 */
	private String forderId;

	public String getForderId() {
		return forderId;
	}

	public void setForderId(String forderId) {
		this.forderId = forderId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFlyConpany() {
		return FlyConpany;
	}

	public void setFlyConpany(String flyConpany) {
		FlyConpany = flyConpany;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
}
