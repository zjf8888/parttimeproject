package com.ucai.po;

import java.util.List;

public class FlyOrder {
	private String clientId;

	private String JDName;

	private String FOrder;

	private String OrderDate;

	private List<FlyAir> flyAirs;

	private List<Passenger> passengers;

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
