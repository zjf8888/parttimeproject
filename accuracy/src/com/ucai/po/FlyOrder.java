package com.ucai.po;

import java.util.List;

/**
 * 机票扣位参数存储类
 * 
 * @author lin
 * 
 */
public class FlyOrder {
	/**
	 * 航空公司客户ID
	 */
	private String clientId;
	/**
	 * 标识为精度天下
	 */
	private String JDName;
	/**
	 * 订单号
	 */
	private String FOrder;
	/**
	 * 下单时间
	 */
	private String OrderDate;
	/**
	 * 航段集合
	 */
	private List<FlyAir> flyAirs;
	/**
	 * 乘客集合
	 */
	private List<Passenger> passengers;
	/**
	 * 联系人信息
	 */
	private Contact contact;

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public List<FlyAir> getFlyAirs() {
		return flyAirs;
	}

	public void setFlyAirs(List<FlyAir> flyAirs) {
		this.flyAirs = flyAirs;
	}

	public String getFOrder() {
		return FOrder;
	}

	public void setFOrder(String order) {
		FOrder = order;
	}

	public String getJDName() {
		return JDName;
	}

	public void setJDName(String name) {
		JDName = name;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
