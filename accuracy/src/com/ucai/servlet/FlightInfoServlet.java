package com.ucai.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ucai.webservices.flightquery.IFlightQueryClient;
import com.ucai.webservices.flightquery.IFlightQueryPortType;

import net.sf.json.JSONObject;

public class FlightInfoServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

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
			IFlightQueryClient client = new IFlightQueryClient();

			// create a default service endpoint
			IFlightQueryPortType iFlightQueryPortType = client
					.getIFlightQueryHttpPort();
			String flightInfo = iFlightQueryPortType.getFlightInfo("szx",
					"kwe", "2010-12-1", "", "jdtx", "");
			System.out.print(flightInfo);
			JSONObject jsonObject = JSONObject.fromObject("{abc:\""+flightInfo+"\"}");
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
