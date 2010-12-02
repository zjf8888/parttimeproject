package com.ucai.po;

public class Order {
	private String order_NO;

	private String TotalPrice;

	private String orderdate;

	private String userId;

	private String aserialnumber;

	public String getAserialnumber() {
		return aserialnumber;
	}

	public void setAserialnumber(String aserialnumber) {
		this.aserialnumber = aserialnumber;
	}

	public String getOrder_NO() {
		return order_NO;
	}

	public void setOrder_NO(String order_NO) {
		this.order_NO = order_NO;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
