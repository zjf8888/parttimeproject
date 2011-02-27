package com.ucai.po;

import java.util.List;
/**
 * 下单总订单信息类
 * @author lin
 *
 */
public class Orders {
	/**
	 * 订单基本信息
	 */
	private Order order;
	/**
	 * 飞机订单基本信息
	 */
	private FOrders FOrders;
	/**
	 * 扣位订单集合
	 */
	private List<AirOrder> AirOrders;
	/**
	 * 客人基本信息
	 */
	private List<Passenger2> Passengers;
	/**
	 * 联系人信息
	 */
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
