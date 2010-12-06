package com.ucai.tool;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ucai.po.AirOrder;
import com.ucai.po.Contact;
import com.ucai.po.FOrders;
import com.ucai.po.FlyAir;
import com.ucai.po.FlyOrder;
import com.ucai.po.LinkMan;
import com.ucai.po.Order;
import com.ucai.po.Orders;
import com.ucai.po.Passenger;
import com.ucai.po.Passenger2;
import com.ucai.po.ReturnPo;

public class FlyOrder2JDOrder {
	public static Orders getJDOrderFromFlyOrder(FlyOrder flyOrder, ReturnPo rpo) {
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

		FOrders fOrders = new FOrders();
		fOrders.setF_Number("f" + id);
		fOrders.setF_Ordercode("shouji0f");
		fOrders.setF_UserId("0");
		List<FlyAir> flyAirs = flyOrder.getFlyAirs();
		int totalprice = 0;
		for (int i = 0; i < flyAirs.size(); i++) {
			FlyAir flyAir = flyAirs.get(i);
			int price = new Integer(flyAir.flyPrice);
			totalprice += price;
		}
		fOrders.setF_Totalprice("" + totalprice);
		fOrders.setF_Payprice(rpo.getPrice());
		fOrders.setF_ProfitPrice("0");
		// 航程类型.1-单程;2-往返,3-联程 准备修改后再作处理
		fOrders.setF_FlightType("1");
		// 类型 1-国内;2-国际
		fOrders.setF_Type("1");
		fOrders.setF_AddDateTime(calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND));
		fOrders.setF_PayType("-1");
		fOrders.setF_PayTime("1900-01-01");
		fOrders.setF_PayStatus("0");
		fOrders.setF_TicketNum("1");
		fOrders.setF_Operator("");
		fOrders.setF_SourceId("2");
		fOrders.setF_Status("1");
		fOrders.setF_IsTrue("1");
		fOrders.setF_DeptID("0");
		fOrders.setF_OrderRemark("");
		fOrders.setF_FuelFees(rpo.getFuel());
		fOrders.setF_BuildFees(rpo.getTax());
		fOrders.setF_Agentfee("3");
		fOrders.setF_CPDate("1900-01-01");
		fOrders.setF_Payprice(rpo.getPrice());
		// 取票方式:1、不需要行程单，通过有效证件办理登机2、机场自取：深圳宝安机场B楼深航柜台3、邮寄地址
		// 这个没有传过来，需要进一步协商。
		fOrders.setF_PeisongAddr("1");
		orders.setFOrders(fOrders);

		List<AirOrder> AirOrders = new ArrayList<AirOrder>();

		List<Passenger2> Passengers2 = new ArrayList<Passenger2>();
		List<Passenger> passengers = flyOrder.getPassengers();

		for (int i = 0; i < flyAirs.size(); i++) {
			FlyAir flyAir = flyAirs.get(i);
			AirOrder airOrder = new AirOrder();
			airOrder.setA_Company("");
			airOrder.setA_FlyNo(flyAir.getFlyNo());
			airOrder.setA_Scity(flyAir.getSc());
			airOrder.setA_Ecity(flyAir.getEc());
			airOrder.setA_Class(flyAir.getFlyClass());
			airOrder.setA_FlySTime(flyAir.getsTime());
			airOrder.setA_FlyETime(flyAir.geteTime());
			airOrder.setA_FlyDate(flyAir.getsDate());
			airOrder.setA_PlaneType(flyAir.getPlanesty());
			airOrder.setA_Pnr(rpo.getPnr());
			airOrder.setA_PnrState(rpo.getCode());
			airOrder.setA_ClassPrice(flyAir.getFlyPrice());
			airOrder.setA_BuildFee(flyAir.getBuildfee());
			airOrder.setA_FuelFee(flyAir.getFuelfee());
			airOrder.setA_TGQ("");
			AirOrders.add(airOrder);

			// 由于需要记录票价，而且订单跟个人信息相同，故在同一循环里处理。
			Passenger pa1 = passengers.get(i);
			Passenger2 ppo = new Passenger2();
			ppo.setP_Name(pa1.getPasName());
			ppo.setP_TypeID(pa1.getPasType());
			ppo.setP_CardType(pa1.getPasBirthday());
			ppo.setP_CardNo(pa1.getPasBirthNo());
			ppo.setP_InsurType("");// 该字段在之前没有提及
			ppo.setP_InsurBuyNumber(pa1.getInsurance_num());
			ppo.setP_InsurZSNumber("0");// 该字段在之前没有提及
			ppo.setP_TicketNo("");
			ppo.setP_CPDate("1900-01-01");
			ppo.setP_Status("1");
			ppo.setP_TicketPrice(airOrder.getA_ClassPrice());
			ppo.setP_RTicketFee(airOrder.getA_ClassPrice());
			ppo.setP_BuildFee(airOrder.getA_BuildFee());
			ppo.setP_FuelFee(airOrder.getA_FuelFee());
			ppo.setP_TicketState("1");
			Passengers2.add(ppo);
		}

		orders.setAirOrders(AirOrders);
		orders.setPassengers(Passengers2);

		LinkMan LinkMan = new LinkMan();
		Contact contact = flyOrder.getContact();
		LinkMan.setL_Name(contact.getConName());
		LinkMan.setL_Mobile(contact.getConMobile());
		LinkMan.setL_Phone(contact.getConTel());
		LinkMan.setL_Email(contact.getConEmail());
		LinkMan.setL_Address(contact.getConAddress());
		LinkMan.setL_UserId("0");
		LinkMan.setL_Remark("");
		orders.setLinkMan(LinkMan);
		return orders;

	}
}
