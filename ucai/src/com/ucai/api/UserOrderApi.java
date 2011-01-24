package com.ucai.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ucai.po.ResultOrder;

/**
 * 用户订单查询接口类
 * @author lin
 *
 */
public class UserOrderApi {
	private static final String BASE_URL = "http://www.ecook.cn/accuracy/getOrderListByUserIdServlet";

	/**
	 * 查询提交
	 * 
	 * @param userid
	 * @return 输入流
	 */
	public InputStream openViewConn(String userid) {
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
			out.print("userid=" + userid);
			// flush输出流的缓冲
			out.flush();
			is = conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 获取订单列表
	 * @param userid 用户ID
	 * @return 订单列表
	 */
	public List<ResultOrder> setSeat(String userid) {
		try {
			List<ResultOrder> list = new ArrayList<ResultOrder>();
			InputStream is = openViewConn(userid);
			BufferedReader in = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String line = in.readLine();
			JSONObject jsonObject = new JSONObject(line);
			JSONArray al = jsonObject.getJSONArray("resultList");
			for (int i = 0; i < al.length(); i++) {
				JSONObject contentbo = al.getJSONObject(i);
				ResultOrder ro = jsonToResultOrder(contentbo);
				list.add(ro);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过把json对象转换成订单查询结果对象
	 * @param jsonObject json数据
	 * @return 订单查询对象
	 * @throws JSONException
	 */
	private ResultOrder jsonToResultOrder(JSONObject jsonObject)
			throws JSONException {
		ResultOrder po = new ResultOrder();
		po.setF_Id(jsonObject.getString("f_Id"));
		po.setF_Number(jsonObject.getString("f_Number"));
		po.setF_Type(jsonObject.getString("f_Type"));
		po.setF_PayType(jsonObject.getString("f_PayType"));
		po.setF_PayStatus(jsonObject.getString("f_PayStatus"));
		po.setA_FlyNo(jsonObject.getString("a_FlyNo"));
		po.setA_Pnr(jsonObject.getString("a_Pnr"));
		po.setP_Name(jsonObject.getString("p_Name"));
		po.setP_TicketNo(jsonObject.getString("p_TicketNo"));
		po.setL_Name(jsonObject.getString("l_Name"));
		po.setL_Mobile(jsonObject.getString("l_Mobile"));
		po.setF_SourceId(jsonObject.getString("f_SourceId"));
		po.setL_UserId(jsonObject.getString("l_UserId"));
		po.setA_Scity(jsonObject.getString("a_Scity"));
		po.setA_Ecity(jsonObject.getString("a_Ecity"));
		po.setF_AddDateTime(jsonObject.getString("f_AddDateTime"));
		po.setTotalPrice(jsonObject.getString("totalPrice"));
		po.setA_FlyDate(jsonObject.getString("a_FlyDate"));
		return po;
	}
}
