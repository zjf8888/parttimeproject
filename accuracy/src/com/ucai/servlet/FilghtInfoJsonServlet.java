package com.ucai.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.params.HttpClientParams;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

import com.ucai.po.Flight;
import com.ucai.tool.FlightFromPage;
import com.ucai.tool.Xml2Flight;
import com.ucai.tool.po.ToSerializationFlight;
import com.ucai.webservices.flightquery.IFlightQueryClient;
import com.ucai.webservices.flightquery.IFlightQueryPortType;

/**
 * 机票查询Servlet 这个类是针对以json为传输介质处理的
 * 
 * @author lin
 * 
 */
public class FilghtInfoJsonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

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

			if (airway == null || airway.length() < 1 || airway.equals("null")) {
				airway = "";
			}
			if (flightNo == null || flightNo.length() < 1
					|| airway.equals("null")) {
				flightNo = "";
			}

			IFlightQueryPortType iFlightQueryPortType = client
					.getIFlightQueryHttpPort(); // 设置连接参数
			setTimeOut(iFlightQueryPortType);
			// 调用查询机票信息接口
			String flightInfo = iFlightQueryPortType.getFlightInfo(org, dst,
					date, airway, "jdtx", flightNo);
			// 打包返回信息
			Flight flightpo = Xml2Flight.jDomParse(flightInfo);
			// 获取第一页查询信息
			ToSerializationFlight tsFlight = FlightFromPage.setFlightFromPage(
					flightpo, 1);
			// 转换成json开始
			JSONObject jsonObject = JSONObject.fromObject(tsFlight);
			PrintWriter pw = response.getWriter();
			pw.write(jsonObject.toString());
			pw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void setTimeOut(IFlightQueryPortType iFlightQueryPortType) {
		HttpClientParams params = new HttpClientParams();
		params
				.setParameter(HttpClientParams.USE_EXPECT_CONTINUE,
						Boolean.FALSE);
		params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT,
				new Long(10000));// 单位是毫秒
		Client timeclient = Client.getInstance(iFlightQueryPortType);
		timeclient.setProperty(CommonsHttpMessageSender.HTTP_CLIENT_PARAMS,
				params);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
