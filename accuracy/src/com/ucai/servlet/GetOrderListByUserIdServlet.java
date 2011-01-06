package com.ucai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.alipay.wap.po.ResultPo;
import com.ucai.po.ResultOrder;
import com.ucai.webservices.ucai.SetOrderImp;

public class GetOrderListByUserIdServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		try {
			String userid = request.getParameter("userid");
			SetOrderImp orderImp = new SetOrderImp();
			List<ResultOrder> resultList=orderImp.getResultList("", userid);
			ResultPo po=new ResultPo();
			po.setResultList(resultList);
			JSONObject jsonObject = JSONObject.fromObject(po);
			String json = jsonObject.toString();
			System.out.println(json);
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
