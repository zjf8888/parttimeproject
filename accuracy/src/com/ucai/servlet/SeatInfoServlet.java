package com.ucai.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

/**
 * 扣位信息处理Servlet
 * 
 * @author lin
 * 
 */
public class SeatInfoServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * 订单处理方法
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		InputStream inputStream = request.getInputStream();
		// 获取订单xml开始
		java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
		byte[] buffer = new byte[64 * 1024];
		for (;;) {
			int count = inputStream.read(buffer);
			if (count < 0)
				break;
			os.write(buffer, 0, count);
		}
		byte[] a = os.toByteArray();
		// 获取订单xml结束
		com.ucai.po.FlyOrder flyOrder = Xml2Order.xml2Seat(a);// 打包订单对象
		// 转换扣位xml开始
		XStream xstream = new XStream();
		xstream.alias("flyOrder", com.ucai.po.FlyOrder.class);
		xstream.alias("flyAir", FlyAir.class);
		xstream.alias("passenger", Passenger.class);
		String xml = xstream.toXML(flyOrder);
		// 转换扣位xml结束
		IFlightQueryClient client = new IFlightQueryClient();
		IFlightQueryPortType iFlightQueryPortType = client
				.getIFlightQueryHttpPort(); // 设置连接参数
		IFlightQueryTool iFlightQueryTool = new IFlightQueryTool();
		iFlightQueryTool.setTimeOut(iFlightQueryPortType);
		String reXml = null;
		try {
			// 调用扣位远程接口
			reXml = iFlightQueryPortType.getOrderSeat(xml);
			System.out.println(reXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReturnPo rpo = ReturnXml2Po.getReturnPo(reXml);// 打包返回信息对象
		// 判断扣位是否成功，扣位成功就下单
		if (rpo != null && rpo.getCode() != null && rpo.getCode().equals("1")) {
			Orders Orders = FlyOrder2JDOrder.getJDOrderFromFlyOrder(flyOrder,
					rpo);
			rpo.setForderid(Orders.getFOrders().getF_Number());
			xstream.alias("Orders", Orders.class);
			xstream.alias("airOrder", AirOrder.class);
			xstream.alias("passenger", Passenger2.class);
			String jdReXml = xstream.toXML(Orders);// 生成订单xml
			System.out.println(jdReXml);
			SetOrderImp SetOrderImp = new SetOrderImp();
			// 下单
			String rexml = SetOrderImp.FlyOrder(jdReXml);
			System.out.println(rexml);
		}
		reXml = xstream.toXML(rpo);
		PrintWriter pw = response.getWriter();
		pw.write(reXml);
		pw.flush();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
