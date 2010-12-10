package com.ucai.po;

/**
 * 下单订单基本信息
 * 
 * @author lin
 * 
 */
public class Order {
	private String order_NO;// 总订单号

	private String TotalPrice;// 订单总价

	private String orderdate;// 下订单时间

	private String userId;// 会员ID

	private String aserialnumber;// 总订单流水号

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
