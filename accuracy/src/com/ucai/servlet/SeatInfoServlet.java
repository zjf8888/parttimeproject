package com.ucai.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ucai.FlyOrder;

import com.thoughtworks.xstream.XStream;
import com.ucai.po.AirOrder;
import com.ucai.po.FlyAir;
import com.ucai.po.Orders;
import com.ucai.po.Passenger;
import com.ucai.po.Passenger2;
import com.ucai.po.ReturnPo;
import com.ucai.tool.FlyOrder2JDOrder;
import com.ucai.tool.IFlightQueryTool;
import com.ucai.tool.ReturnXml2Po;
import com.ucai.tool.Xml2Order;
import com.ucai.webservices.flightquery.IFlightQueryClient;
import com.ucai.webservices.flightquery.IFlightQueryPortType;
import com.ucai.webservices.ucai.SetOrderImp;

public class SeatInfoServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		InputStream inputStream = request.getInputStream();

		java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
		byte[] buffer = new byte[64 * 1024];
		for (;;) {
			int count = inputStream.read(buffer);
			if (count < 0)
				break;
			os.write(buffer, 0, count);
		}
		byte[] a = os.toByteArray();
		com.ucai.po.FlyOrder flyOrder = Xml2Order.xml2Seat(a);
		XStream xstream = new XStream();
		xstream.alias("flyOrder", FlyOrder.class);
		xstream.alias("flyAir", FlyAir.class);
		xstream.alias("passenger", Passenger.class);
		String xml = xstream.toXML(flyOrder);
		System.out.println(xml);
		IFlightQueryClient client = new IFlightQueryClient();
		IFlightQueryPortType iFlightQueryPortType = client
				.getIFlightQueryHttpPort(); // 设置连接参数
		IFlightQueryTool iFlightQueryTool = new IFlightQueryTool();
		iFlightQueryTool.setTimeOut(iFlightQueryPortType);
		String reXml=null;
		try {
			reXml = iFlightQueryPortType.getOrderSeat(xml);
			System.out.println(reXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReturnPo rpo = ReturnXml2Po.getReturnPo(reXml);
		if(rpo.getPrice()==null){
			rpo.setCode("1");
			rpo.setFlyConpany("MU");
			rpo.setFuel("50");
			rpo.setTax("70");
			rpo.setPnr("HE9CJL");
			rpo.setTicketPrice("1870");
			rpo.setInfo("ok");
			rpo.setPrice("1750");
		}
		System.out.println("--------------");	 	
		rpo.setPnr("HE9CJL");
		Orders Orders = FlyOrder2JDOrder.getJDOrderFromFlyOrder(flyOrder, rpo);
		System.out.println("================"); 
		
		Orders.getOrder().setTotalPrice("0");
		Orders.getFOrders().setF_Payprice("0"); 
		Orders.getFOrders().setF_FuelFees("0");
		Orders.getFOrders().setF_BuildFees("0");
		
		xstream.alias("Orders", Orders.class);
		xstream.alias("airOrder", AirOrder.class);
		xstream.alias("passenger", Passenger2.class);
		String jdReXml = xstream.toXML(Orders);
		System.out.println(jdReXml); 		
		SetOrderImp SetOrderImp=new SetOrderImp();
		String rexml=SetOrderImp.FlyOrder(jdReXml);;		
		System.out.println(rexml);
		PrintWriter pw = response.getWriter();
		pw.write(reXml);
		pw.flush();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
