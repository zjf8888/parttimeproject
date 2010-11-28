package com.ucai.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.ucai.po.Flight;
import com.ucai.po.SeatClass;
import com.ucai.po.Segment;
import com.ucai.webservices.flightquery.IFlightQueryClient;
import com.ucai.webservices.flightquery.IFlightQueryPortType;

import net.sf.json.JSONObject;

public class FlightInfoServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

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

			if (airway == null || airway.length() < 1) {
				airway = "";
			}
			if (flightNo == null || flightNo.length() < 1) {
				flightNo = "";
			}

			IFlightQueryPortType iFlightQueryPortType = client
					.getIFlightQueryHttpPort();
			String flightInfo = iFlightQueryPortType.getFlightInfo(org, dst,
					date, airway, "jdtx", flightNo);
			jDomParse(flightInfo);
			System.out.print(flightInfo);
			JSONObject jsonObject = JSONObject.fromObject("{abc:\""
					+ flightInfo + "\"}");
			PrintWriter pw = response.getWriter();
			pw.write(jsonObject.toString());
			pw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public Flight jDomParse(String xml) {
		Flight flightpo = new Flight();
		StringReader sr = new StringReader(xml);
		SAXBuilder builder = new SAXBuilder(false);
		try {
			Document doc = builder.build(sr);
			Element flight = doc.getRootElement();
			// 获取错误编码
			Element errinfo = flight.getChild("errinfo");
			String code = errinfo.getChildTextTrim("code");
			flightpo.setErrorCode(code);
			// 获取出发城市编码
			String startcity = flight.getChildTextTrim("startcity");
			flightpo.setStartcity(startcity);
			// 获取终结城市编码
			String endcity = flight.getChildTextTrim("endcity");
			flightpo.setEndcity(endcity);
			// 获取出发日期
			String startdate = flight.getChildTextTrim("startdate");
			flightpo.setStartdate(startdate);
			
			//获取航班信息
			List<Segment> segmentArrayList=new ArrayList<Segment>();
			Element date =flight.getChild("date");
			List segmentList=date.getChildren("segment");
			for (Iterator iter = segmentList.iterator(); iter.hasNext();) {
				Element segment = (Element) iter.next();
				Segment segmentpo=new Segment();
				//获取飞机航班号
				String fltno=segment.getChildTextTrim("fltno");
				segmentpo.setFltno(fltno);
				//获取出发城市编码
				String sc=segment.getChildTextTrim("sc");
				segmentpo.setSc(sc);
				//获取出发机场名称
				String scAirdrome=segment.getChildTextTrim("scAirdrome");
				segmentpo.setScAirdrome(scAirdrome);
				//获取到达城市编码
				String ec=segment.getChildTextTrim("ec");
				segmentpo.setEc(ec);
				//获取到达机场名称
				String ecAirdrome=segment.getChildTextTrim("ecAirdrome");
				segmentpo.setEcAirdrome(ecAirdrome);
				//起飞时间
				String deptime=segment.getChildTextTrim("deptime");
				segmentpo.setDeptime(deptime);
				//到达时间
				String arrtime =segment.getChildTextTrim("arrtime");
				segmentpo.setArrtime(arrtime);
				//飞机型号
				String planesty=segment.getChildTextTrim("planesty");
				segmentpo.setPlanesty(planesty);
				//中途停降次数
				String stopnum=segment.getChildTextTrim("stopnum");
				segmentpo.setStopnum(stopnum);
				//电子票
				String etkt=segment.getChildTextTrim("etkt");
				segmentpo.setEtkt(etkt);
				//有餐否
				String meal=segment.getChildTextTrim("meal");
				segmentpo.setMeal(meal);
				
				//以下为处理航班座位信息
				
				Element classs =flight.getChild("classs");
				List<SeatClass> classArraylist=new ArrayList<SeatClass>();
				List classList=classs.getChildren("class");
				for (Iterator classiter = classList.iterator(); classiter.hasNext();) {
					SeatClass seatClass=new SeatClass();
					Element cla = (Element) classiter.next();
					//座位等级名称
					String classname=cla.getChildTextTrim("classname");
					seatClass.setClassname(classname);
					//剩余的座位数，大于等于9时为A
					String num=cla.getChildTextTrim("num");
					seatClass.setNum(num);
					//价格
					String saleprice=cla.getChildTextTrim("saleprice");
					seatClass.setSaleprice(saleprice);
					//座位等级码
					String classcode=cla.getChildTextTrim("classcode");
					seatClass.setClasscode(classcode);
					//机场建设费
					String buildfee=cla.getChildTextTrim("buildfee");
					seatClass.setBuildfee(buildfee);
					//燃油费
					String fuelfee=cla.getChildTextTrim("fuelfee");
					seatClass.setFuelfee(fuelfee);
					//可否申请电子票
					String isApply=cla.getChildTextTrim("isApply");
					seatClass.setIsApply(isApply);
					//办理说明
					String discount=cla.getChildTextTrim("discount");
					seatClass.setDiscount(discount);
					//退票说明
					String refund=cla.getChildTextTrim("refund");
					seatClass.setRefund(refund);
					//免费变更
					String cmt=cla.getChildTextTrim("cmt");
					seatClass.setCmt(cmt);
					//见舱销售，随订随售
					String sale=cla.getChildTextTrim("sale");
					seatClass.setSale(sale);
					classArraylist.add(seatClass);
				}
				if(classList.size()>0){
					SeatClass[] classesList=(SeatClass[])classArraylist.toArray();
					segmentpo.setClassesList(classesList);
					segmentArrayList.add(segmentpo);
				}
			}
			Segment[] segment=(Segment[])segmentArrayList.toArray();
			flightpo.setSegmentList(segment);
			return flightpo;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
