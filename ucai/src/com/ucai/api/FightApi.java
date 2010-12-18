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

import com.ucai.po.Flight;
import com.ucai.po.SeatClass;
import com.ucai.po.Segment;

public class FightApi {
	private static final String BASE_URL = "http://192.168.1.102:8081/filghtInfoJsonServlet";
	private static final String CACHE_URL = "http://192.168.1.102:8081/filghtInfoByPageJsonServlet";

	/**
	 * 获取查询航班数据流
	 * 
	 * @param startcity
	 *            , 出发城市
	 * @param endcity
	 *            ,到达城市
	 * @param date
	 *            ,出发日期
	 * @param airway
	 *            ,航空公司
	 * @param flightNo航班号
	 *            return 航班数据流
	 */
	public InputStream openViewConn(String startcity, String endcity,
			String date, String airway, String flightNo) {
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
			out.print("org=" + startcity + "&dst=" + endcity + "&date=" + date
					+ "&airway=" + airway + "&flightNo=" + flightNo);
			// flush输出流的缓冲
			out.flush();
			is = conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 分页查询 String tid 查询ID String pn 查询页面
	 */
	public InputStream openViewConn(String tid, String pn) {
		InputStream is = null;
		try {
			URL realUrl = new URL(CACHE_URL);
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
			out.print("tid=" + tid + "&pn=" + pn);
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
	public Flight getCacheFlightPo(String tid, String pn) {
		try {
			Flight po = new Flight();
			InputStream is = openViewConn(tid, pn);
			BufferedReader in = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String line = in.readLine();
			JSONObject jsonObject = new JSONObject(line);
			JSONObject errinfo = jsonObject.getJSONObject("errinfo");
			String errorCode = errinfo.getString("code");
			String errorTips = errinfo.getString("description");
			String transId = jsonObject.getString("transId");
			String scity = jsonObject.getString("startcity");
			String ecity = jsonObject.getString("endcity");
			String startdate = jsonObject.getString("startdate");
			String totalNums = jsonObject.getString("totalNums");
			String totalPages = jsonObject.getString("totalPages");
			String pageNo = jsonObject.getString("pageNo");
			List<Segment> segmentList = new ArrayList<Segment>();

			JSONArray al = jsonObject.getJSONArray("segmentList");
			for (int i = 0; i < al.length(); i++) {
				JSONObject contentbo = al.getJSONObject(i);
				Segment cb = jsonToSegment(contentbo);
				segmentList.add(cb);
			}

			po.setTransId(transId);
			po.setErrorCode(errorCode);
			po.setErrorTips(errorTips);
			po.setStartcity(scity);
			po.setEndcity(ecity);
			po.setStartdate(startdate);
			po.setTotalNums(new Integer(totalNums));
			po.setTotalPages(new Integer(totalPages));
			po.setPageNo(new Integer(pageNo));
			po.setSegmentList(segmentList);
			return po;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Flight getFlightPo(String startcity, String endcity, String date,
			String airway, String flightNo) {
		try {
			Flight po = new Flight();
			InputStream is = openViewConn(startcity, endcity, date, airway,
					flightNo);
			BufferedReader in = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String line = in.readLine();
			JSONObject jsonObject = new JSONObject(line);
			JSONObject errinfo = jsonObject.getJSONObject("errinfo");
			String errorCode = errinfo.getString("code");
			String errorTips = errinfo.getString("description");
			String transId = jsonObject.getString("transId");
			String scity = jsonObject.getString("startcity");
			String ecity = jsonObject.getString("endcity");
			String startdate = jsonObject.getString("startdate");
			String totalNums = jsonObject.getString("totalNums");
			String totalPages = jsonObject.getString("totalPages");
			String pageNo = jsonObject.getString("pageNo");
			List<Segment> segmentList = new ArrayList<Segment>();

			JSONArray al = jsonObject.getJSONArray("segmentList");
			for (int i = 0; i < al.length(); i++) {
				JSONObject contentbo = al.getJSONObject(i);
				Segment cb = jsonToSegment(contentbo);
				segmentList.add(cb);
			}

			po.setTransId(transId);
			po.setErrorCode(errorCode);
			po.setErrorTips(errorTips);
			po.setStartcity(scity);
			po.setEndcity(ecity);
			po.setStartdate(startdate);
			po.setTotalNums(new Integer(totalNums));
			po.setTotalPages(new Integer(totalPages));
			po.setPageNo(new Integer(pageNo));
			po.setSegmentList(segmentList);
			return po;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * json转换成对应对象方法
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	private static Segment jsonToSegment(JSONObject json) throws JSONException {
		Segment contentBean = new Segment();
		contentBean.setFltno(json.getString("fltno"));
		contentBean.setSc(json.getString("sc"));
		contentBean.setScAirdrome(json.getString("scAirdrome"));
		contentBean.setEc(json.getString("ec"));
		contentBean.setEcAirdrome(json.getString("ecAirdrome"));
		contentBean.setDeptime(json.getString("deptime"));
		contentBean.setArrtime(json.getString("arrtime"));
		contentBean.setPlanesty(json.getString("planesty"));
		contentBean.setStopnum(json.getString("stopnum"));
		contentBean.setEtkt(json.getString("etkt"));
		contentBean.setMeal(json.getString("meal"));
		List<SeatClass> seatClassList = new ArrayList<SeatClass>();
		JSONArray al = json.getJSONArray("classesList");
		for (int i = 0; i < al.length(); i++) {
			JSONObject contentbo = al.getJSONObject(i);
			SeatClass cb = jsonToSeatClass(contentbo);
			seatClassList.add(cb);
		}
		contentBean.setClassesList(seatClassList);
		return contentBean;
	}

	/**
	 * json转换成对应对象方法
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	private static SeatClass jsonToSeatClass(JSONObject json)
			throws JSONException {
		SeatClass contentBean = new SeatClass();
		contentBean.setClassname(json.getString("classname"));
		contentBean.setNum(json.getString("num"));
		contentBean.setSaleprice(json.getString("saleprice"));
		contentBean.setClasscode(json.getString("classcode"));
		contentBean.setBuildfee(json.getString("buildfee"));
		contentBean.setFuelfee(json.getString("fuelfee"));
		contentBean.setIsApply(json.getString("isApply"));
		contentBean.setDiscount(json.getString("discount"));
		contentBean.setRefund(json.getString("refund"));
		contentBean.setCmt(json.getString("cmt"));
		contentBean.setSale(json.getString("sale"));
		return contentBean;
	}
}
