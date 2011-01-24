package com.ucai.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

import com.ucai.po.ResultOrder;

/**
 * 提交订单api
 * @author lin
 *
 */
public class OrderApi {
	private static final String BASE_URL = "http://www.ecook.cn/accuracy/getFlyOrderListJsonServlet";

	/**
	 * 提交数据
	 * 
	 * @param forderid
	 * @return 输入流对象
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
	 * @return 订单查询结果
	 */
	public ResultOrder setSeat(String xml) {
		try {
			ResultOrder po = new ResultOrder();
			InputStream is = openViewConn(xml);
			BufferedReader in = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String line = in.readLine();
			System.out.println(line);
			JSONObject jsonObject = new JSONObject(line);
			String f_Id = jsonObject.getString("f_Id");
			String f_Number = jsonObject.getString("f_Number");
			String f_PayStatus = jsonObject.getString("f_PayStatus");
			String a_FlyNo = jsonObject.getString("a_FlyNo");
			String a_Pnr = jsonObject.getString("a_Pnr");
			String p_Name = jsonObject.getString("p_Name");
			String l_Name = jsonObject.getString("l_Name");
			String a_Scity = jsonObject.getString("a_Scity");
			String a_Ecity = jsonObject.getString("a_Ecity");
			String a_FlyDate = jsonObject.getString("a_FlyDate");
			String TotalPrice = jsonObject.getString("totalPrice");
			po.setF_Id(f_Id);
			po.setF_Number(f_Number);
			po.setF_PayStatus(f_PayStatus);
			po.setA_FlyNo(a_FlyNo);
			po.setA_Pnr(a_Pnr);
			po.setP_Name(p_Name);
			po.setL_Name(l_Name);
			po.setA_Scity(a_Scity);
			po.setA_Ecity(a_Ecity);
			po.setA_FlyDate(a_FlyDate);
			po.setTotalPrice(TotalPrice);
			return po;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
