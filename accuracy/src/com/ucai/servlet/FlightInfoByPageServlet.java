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
import com.ucai.tool.DbCache;
import com.ucai.tool.FlightFromPage;
import com.ucai.tool.po.ToSerializationFlight;
/**
 * 获取xml分页信息
 * @author lin
 *
 */
public class FlightInfoByPageServlet extends HttpServlet {
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
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		try {
			String tid = request.getParameter("tid");
			String pn = request.getParameter("pn");
			int pageno=new Integer(pn);
			DbCache dbCache = new DbCache();
			Flight flightpo =dbCache.query(tid);
			ToSerializationFlight tsFlight=FlightFromPage.setFlightFromPage(flightpo, pageno);
			XStream xstream = new XStream();
			xstream.alias("flightdate", ToSerializationFlight.class);
			xstream.aliasField("date", ToSerializationFlight.class, "segmentList");
			xstream.alias("segment", Segment.class);
			xstream.aliasField("classs", Segment.class, "classesList");
			xstream.alias("class", SeatClass.class);
	        String xml=xstream.toXML(tsFlight);			
			PrintWriter pw = response.getWriter();
			pw.write(xml);
			pw.flush();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}
}
