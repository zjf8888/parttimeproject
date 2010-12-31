package com.alipay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class Api {
	private static final String BASE_URL = "http://192.168.1.101:8081/accuracy/signServlet";

	/**
	 * 提交数据
	 * 
	 * @param xml
	 * @return
	 */
	public InputStream openViewConn(String forderid) {
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
			out.print("forderid=" + forderid);
			// flush输出流的缓冲
			out.flush();
			is = conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 获取订单信息对象
	 * 
	 * @param xml
	 * @return
	 */
	public String getSingInfo(String forderid) {
		try {
			InputStream is = openViewConn(forderid);
			BufferedReader in = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String line = in.readLine();
			System.out.println(line);
			return line;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
