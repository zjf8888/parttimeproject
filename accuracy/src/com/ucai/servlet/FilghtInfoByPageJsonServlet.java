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
 * 获取json分页信息,该方法的调用路径为：/filghtInfoByPageJsonServlet<br>
 * 需要的参数为<br>
 * tid 查询记录ID<br>
 * pn 页码，从0开始的页码<br>
 * 
 * @author lin
 * 
 */
public class FilghtInfoByPageJsonServlet extends HttpServlet {

	/**
	 * 序列化字段
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 返回的文件类型
	 */
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	/**
	 * 初始化方法
	 */	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	/**
	 * 获取分页信息方法
	 * @see #doGet(HttpServletRequest, HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 获取分页信息方法
	 */
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
