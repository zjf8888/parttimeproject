package com.ucai.po;

/**
 * 扣位返回信息类
 * 
 * @author lin
 * 
 */
public class ReturnPo {
	private String code;// 状态码

	private String info;// 描述信息

	private String pnr;// 机票订单号

	private String FlyConpany;// 航运公司二字码

	private String ticketPrice;// 机票价格

	private String tax;// 机场建设费

	private String fuel;// 燃油附加费

	private String price;// 总价

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
