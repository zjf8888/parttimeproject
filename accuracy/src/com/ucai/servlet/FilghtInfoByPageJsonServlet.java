package com.ucai.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ucai.po.Flight;
import com.ucai.tool.DbCache;
import com.ucai.tool.FlightFromPage;
import com.ucai.tool.po.ToSerializationFlight;
/**
 * 获取json分页信息
 * @author lin
 *
 */
public class FilghtInfoByPageJsonServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

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
			int pageno = new Integer(pn);
			DbCache dbCache = DbCache.getDbcache();
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
}
