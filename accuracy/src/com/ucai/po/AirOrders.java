package com.ucai.po;

import java.util.List;

public class AirOrders {
	private AirOrder airOrder;

	private List<Passenger2> Passengers;

	private LinkMan LinkMan;

	public AirOrder getAirOrder() {
		return airOrder;
	}

	public void setAirOrder(AirOrder airOrder) {
		this.airOrder = airOrder;
	}

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
}
