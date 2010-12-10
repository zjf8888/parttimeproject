package com.ucai.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * 机票查询Servlet
 * 这个类是针对以xml为传输介质处理的
 * @author lin
 * 
 */
public class FlightInfoServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * get访问处理
	 */
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
			IFlightQueryTool iFlightQueryTool = new IFlightQueryTool();
			iFlightQueryTool.setTimeOut(iFlightQueryPortType);
			// 调用查询机票信息接口
			String flightInfo = iFlightQueryPortType.getFlightInfo(org, dst,
					date, airway, "jdtx", flightNo);
			// 打包返回信息
			Flight flightpo = Xml2Flight.jDomParse(flightInfo);
			// 获取第一页查询信息
			ToSerializationFlight tsFlight = FlightFromPage.setFlightFromPage(
					flightpo, 1);
			// 转换成xml开始
			XStream xstream = new XStream();
			xstream.alias("flightdate", ToSerializationFlight.class);
			xstream.aliasField("date", ToSerializationFlight.class,
					"segmentList");
			xstream.alias("segment", Segment.class);
			xstream.aliasField("classs", Segment.class, "classesList");
			xstream.alias("class", SeatClass.class);
			String xml = xstream.toXML(tsFlight);
			// 转换成xml结束
			PrintWriter pw = response.getWriter();
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
