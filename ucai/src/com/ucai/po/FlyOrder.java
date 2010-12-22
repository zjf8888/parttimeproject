package com.ucai.po;

import java.util.List;
/**
 * 机票扣位参数存储类
 * @author lin
 *
 */
public class FlyOrder {
	private String clientId;//航空公司客户ID

	private String JDName;//标识为精度天下

	private String FOrder;//订单号

	private String OrderDate;//下单时间

	private List<FlyAir> flyAirs;//航段集合

	private List<Passenger> passengers;//乘客集合

	private Contact contact;//联系人信息

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
