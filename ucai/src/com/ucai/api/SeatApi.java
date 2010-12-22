package com.ucai.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

import com.ucai.po.ReturnPo;

public class SeatApi {
	private static final String BASE_URL = "http://192.168.1.100:8081/seatInfoAndroidServlet";

	/**
	 * 提交数据
	 * 
	 * @param xml
	 * @return
	 */
	public InputStream openViewConn(String xml) {
		InputStream is = null;
		try {
			URL realUrl = new URL(BASE_URL);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			PrintWriter out = new PrintWriter(new OutputStreamWriter(conn
					.getOutputStream(), "utf-8"));
			// 发送请求参数
			out.print("xml=" + xml);
			// flush输出流的缓冲
			out.flush();
			is = conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 查询缓存数年据
	 * 
	 * @param tid
	 *            缓存ID
	 * @param pn
	 *            第几页
	 * @return 航班信息对象
	 */
	public ReturnPo setSeat(String xml) {
		try {
			ReturnPo po = new ReturnPo();
			InputStream is = openViewConn(xml);
			BufferedReader in = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String line = in.readLine();
			JSONObject jsonObject = new JSONObject(line);
			String code = jsonObject.getString("code");
			String info = jsonObject.getString("info");
			String pnr = jsonObject.getString("pnr");
			String FlyConpany = jsonObject.getString("flyConpany");
			String ticketPrice = jsonObject.getString("ticketPrice");
			String tax = jsonObject.getString("tax");
			String fuel = jsonObject.getString("fuel");
			String price = jsonObject.getString("price");
			po.setCode(code);
			po.setInfo(info);
			po.setPnr(pnr);
			po.setFlyConpany(FlyConpany);
			po.setTicketPrice(ticketPrice);
			po.setTax(tax);
			po.setFuel(fuel);
			po.setPrice(price);
			return po;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
