package com.ucai.tool;

import java.util.Calendar;

import com.ucai.po.FlyOrder;
import com.ucai.po.Order;
import com.ucai.po.Orders;

public class FlyOrder2JDOrder {
	public Orders FlyOrder2JDOrder(FlyOrder flyOrder) {
		Orders orders=new Orders();
		long id=Calendar.getInstance().getTime().getTime();
		Order order=new Order();
		order.setOrder_NO("Z"+id);
		return null;

	}
}
