package com.ucai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.ucai.po.Flight;
import com.ucai.po.SeatClass;
import com.ucai.po.Segment;
import com.ucai.tool.DbCache;
import com.ucai.tool.DbCount;
import com.ucai.tool.Xml2Flight;
import com.ucai.webservices.flightquery.IFlightQueryClient;
import com.ucai.webservices.flightquery.IFlightQueryPortType;

import net.sf.json.JSONObject;

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
					.getIFlightQueryHttpPort();
			String flightInfo = iFlightQueryPortType.getFlightInfo(org, dst,
					date, airway, "jdtx", flightNo);
			Flight flightpo = Xml2Flight.jDomParse(flightInfo);
			
			JSONObject jsonObject = JSONObject.fromObject("{abc:\""
					+ flightInfo + "\"}");
			PrintWriter pw = response.getWriter();
			pw.write(jsonObject.toString());
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
