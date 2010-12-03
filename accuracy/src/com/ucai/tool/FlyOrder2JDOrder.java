package com.ucai.tool;

import java.util.Calendar;
import java.util.List;

import com.ucai.po.FOrders;
import com.ucai.po.FlyAir;
import com.ucai.po.FlyOrder;
import com.ucai.po.Order;
import com.ucai.po.Orders;
import com.ucai.po.ReturnPo;

public class FlyOrder2JDOrder {
	public Orders FlyOrder2JDOrder(FlyOrder flyOrder, ReturnPo rpo) {
		Orders orders = new Orders();
		long id = Calendar.getInstance().getTime().getTime();
		Order order = new Order();
		order.setOrder_NO("Z" + id);
		order.setTotalPrice(rpo.getPrice());
		Calendar calendar = Calendar.getInstance();
		order.setOrderdate(calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND));
		order.setUserId("0");
		order.setAserialnumber("shouji0z");
		orders.setOrder(order);
		
		FOrders fOrders=new FOrders();
		fOrders.setF_Number("f"+id);
		fOrders.setF_Ordercode("shouji0f");
		fOrders.setF_UserId("0");
		List<FlyAir> flyAirs=flyOrder.getFlyAirs();
		int totalprice=0;
		for(int i=0;i<flyAirs.size();i++){
			FlyAir flyAir=flyAirs.get(i);
			int price=new Integer(flyAir.flyPrice);
			totalprice+=price;
		}
		fOrders.setF_Totalprice(""+totalprice);
		fOrders.setF_Payprice(rpo.getPrice());
		fOrders.setF_ProfitPrice("0");
		//航程类型.1-单程;2-往返,3-联程 准备修改后再作处理
		fOrders.setF_FlightType("1");
		//类型 1-国内;2-国际
		fOrders.setF_Type("1");
		return null;

	}
}
