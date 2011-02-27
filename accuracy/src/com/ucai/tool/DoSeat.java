package com.ucai.tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;
import com.ucai.po.AirOrder;
import com.ucai.po.FlyAir;
import com.ucai.po.Orders;
import com.ucai.po.Passenger;
import com.ucai.po.Passenger2;
import com.ucai.po.ReturnPo;
import com.ucai.webservices.flightquery.IFlightQueryClient;
import com.ucai.webservices.flightquery.IFlightQueryPortType;
import com.ucai.webservices.ucai.SetOrderImp;

/**
 * 扣位处理工具类
 * 
 * @author 李卓林
 * 
 */
public class DoSeat {
	/**
	 * 返回的信息文件类型
	 */
	private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

	/**
	 * 扣位处理方法
	 * 
	 * @param request
	 *            請求
	 * @param response
	 *            回複
	 * @return 返回扣位处理后的xml
	 */
	public ReturnPo doSeatInfo(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		String xml = request.getParameter("xml");
		com.ucai.po.FlyOrder flyOrder = Xml2Order.xml2Seat(xml);// 打包订单对象
		// 转换扣位xml开始
		XStream xstream = new XStream();
		xstream.alias("flyOrder", com.ucai.po.FlyOrder.class);
		xstream.alias("flyAir", FlyAir.class);
		xstream.alias("passenger", Passenger.class);
		xml = xstream.toXML(flyOrder);
		// 转换扣位xml结束
		IFlightQueryClient client = new IFlightQueryClient();
		IFlightQueryPortType iFlightQueryPortType = client
				.getIFlightQueryHttpPort(); // 设置连接参数
		IFlightQueryTool iFlightQueryTool = new IFlightQueryTool();
		iFlightQueryTool.setTimeOut(iFlightQueryPortType);
		String reXml = null;
		try {
			// 调用扣位远程接口
			reXml = iFlightQueryPortType.getOrderSeat(xml);
			System.out.println(reXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReturnPo rpo = ReturnXml2Po.getReturnPo(reXml);// 打包返回信息对象
		// 判断扣位是否成功，扣位成功就下单
		if (rpo != null && rpo.getCode() != null && rpo.getCode().equals("1")) {
			Orders Orders = FlyOrder2JDOrder.getJDOrderFromFlyOrder(flyOrder,
					rpo);
			rpo.setForderid(Orders.getFOrders().getF_Number());
			xstream.alias("Orders", Orders.class);
			xstream.alias("airOrder", AirOrder.class);
			xstream.alias("passenger", Passenger2.class);
			String jdReXml = xstream.toXML(Orders);// 生成订单xml
			System.out.println(jdReXml);
			SetOrderImp SetOrderImp = new SetOrderImp();
			// 下单
			String rexml = SetOrderImp.FlyOrder(jdReXml);
			System.out.println(rexml);
		}
		return rpo;
	}
}
