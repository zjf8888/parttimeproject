package com.alipay.wap.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.thoughtworks.xstream.XStream;
import com.ucai.po.AirOrder;
import com.ucai.po.Flight;
import com.ucai.po.FlyAir;
import com.ucai.po.Orders;
import com.ucai.po.Passenger;
import com.ucai.po.Passenger2;
import com.ucai.po.ReturnPo;
import com.ucai.tool.DbCache;
import com.ucai.tool.FlightFromPage;
import com.ucai.tool.FlyOrder2JDOrder;
import com.ucai.tool.IFlightQueryTool;
import com.ucai.tool.ReturnXml2Po;
import com.ucai.tool.Xml2Order;
import com.ucai.tool.po.ToSerializationFlight;
import com.ucai.webservices.flightquery.IFlightQueryClient;
import com.ucai.webservices.flightquery.IFlightQueryPortType;
import com.ucai.webservices.ucai.SetOrderImp;

public class TradeServlet extends HttpServlet {
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
		try {
			String tid = request.getParameter("subject");
			String pn = request.getParameter("pn");
			int pageno = new Integer(pn);
			DbCache dbCache = new DbCache();
			Flight flightpo = dbCache.query(tid);
			ToSerializationFlight tsFlight = FlightFromPage.setFlightFromPage(
					flightpo, pageno);
			JSONObject jsonObject = JSONObject.fromObject(tsFlight);
			PrintWriter pw = response.getWriter();
			pw.write(jsonObject.toString());
			pw.flush();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
