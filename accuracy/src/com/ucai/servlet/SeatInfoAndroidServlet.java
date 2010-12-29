package com.ucai.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ucai.po.ReturnPo;
import com.ucai.tool.DoSeat;

public class SeatInfoAndroidServlet extends HttpServlet {

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
		DoSeat doseat = new DoSeat();
		ReturnPo rpo = doseat.doSeatInfo(request, response);
		JSONObject jsonObject = JSONObject.fromObject(rpo);
		PrintWriter pw = response.getWriter();
		pw.write(jsonObject.toString());
		pw.flush();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
