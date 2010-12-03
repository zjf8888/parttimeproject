package com.ucai.po;

import java.util.List;

public class Orders {
	private Order order;

	private FOrders FOrders;

	private List<AirOrder> AirOrders;

	private List<Passenger2> Passengers;

	private LinkMan LinkMan;

	public LinkMan getLinkMan() {
		return LinkMan;
	}

	public void setLinkMan(LinkMan linkMan) {
		LinkMan = linkMan;
	}

	public List<Passenger2> getPassengers() {
		return Passengers;
	}

	public void setPassengers(List<Passenger2> passengers) {
		Passengers = passengers;
	}

	public List<AirOrder> getAirOrders() {
		return AirOrders;
	}

	public void setAirOrders(List<AirOrder> airOrders) {
		AirOrders = airOrders;
	}

	public FOrders getFOrders() {
		return FOrders;
	}

	public void setFOrders(FOrders orders) {
		FOrders = orders;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
