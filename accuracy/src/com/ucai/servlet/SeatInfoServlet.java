package com.ucai.servlet;

import java.io.IOException;
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
import com.ucai.tool.DoSeat;

/**
 * 扣位信息处理Servlet
 * 
 * @author lin
 * 
 */
public class SeatInfoServlet extends HttpServlet {

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
		DoSeat doseat = new DoSeat();
		ReturnPo rpo = doseat.doSeatInfo(request, response);
		XStream xstream = new XStream();
		xstream.alias("flyOrder", com.ucai.po.FlyOrder.class);
		xstream.alias("flyAir", FlyAir.class);
		xstream.alias("passenger", Passenger.class);
		xstream.alias("Orders", Orders.class);
		xstream.alias("airOrder", AirOrder.class);
		xstream.alias("passenger", Passenger2.class);
		String reXml = xstream.toXML(rpo);
		PrintWriter pw = response.getWriter();
		pw.write(reXml);
		pw.flush();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
