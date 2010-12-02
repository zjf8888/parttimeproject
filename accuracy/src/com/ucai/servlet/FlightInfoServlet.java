package com.ucai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.thoughtworks.xstream.XStream;
import com.ucai.po.Flight;
import com.ucai.po.SeatClass;
import com.ucai.po.Segment;
import com.ucai.tool.FlightFromPage;
import com.ucai.tool.IFlightQueryTool;
import com.ucai.tool.Xml2Flight;
import com.ucai.tool.po.ToSerializationFlight;
import com.ucai.webservices.flightquery.IFlightQueryClient;
import com.ucai.webservices.flightquery.IFlightQueryPortType;

public class FlightInfoServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		try {

			String org = request.getParameter("org");
			String dst = request.getParameter("dst");
			String date = request.getParameter("date");
			String airway = request.getParameter("airway");
			String flightNo = request.getParameter("flightNo");
			IFlightQueryClient client = new IFlightQueryClient();

			if (airway == null || airway.length() < 1) {
				airway = "";
			}
			if (flightNo == null || flightNo.length() < 1) {
				flightNo = "";
			}

			IFlightQueryPortType iFlightQueryPortType = client
					.getIFlightQueryHttpPort(); // 设置连接参数
			IFlightQueryTool iFlightQueryTool=new IFlightQueryTool();
			iFlightQueryTool.setTimeOut(iFlightQueryPortType);
			String flightInfo = iFlightQueryPortType.getFlightInfo(org, dst,
					date, airway, "jdtx", flightNo);
			System.out.println(flightInfo);
			long haltHour = Calendar.getInstance().getTime().getTime() - 1800000;
			Flight flightpo = Xml2Flight.jDomParse(flightInfo);
			ToSerializationFlight tsFlight = FlightFromPage.setFlightFromPage(
					flightpo, 1);
			XStream xstream = new XStream();
			xstream.alias("flightdate", ToSerializationFlight.class);
			xstream.aliasField("date", ToSerializationFlight.class,
					"segmentList");
			xstream.alias("segment", Segment.class);
			xstream.aliasField("classs", Segment.class, "classesList");
			xstream.alias("class", SeatClass.class);
			String xml = xstream.toXML(tsFlight);
			PrintWriter pw = response.getWriter();
			long haltHour2 = Calendar.getInstance().getTime().getTime() - 1800000;
			System.out.println(haltHour2 - haltHour);
			pw.write(xml);
			pw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
