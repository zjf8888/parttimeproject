package com.ucai.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;
import com.ucai.po.AirOrder;
import com.ucai.po.FlyAir;
import com.ucai.po.Orders;
import com.ucai.po.Passenger;
import com.ucai.po.Passenger2;
import com.ucai.po.ReturnPo;
import com.ucai.tool.FlyOrder2JDOrder;
import com.ucai.tool.IFlightQueryTool;
import com.ucai.tool.ReturnXml2Po;
import com.ucai.tool.Xml2Order;
import com.ucai.webservices.flightquery.IFlightQueryClient;
import com.ucai.webservices.flightquery.IFlightQueryPortType;
import com.ucai.webservices.ucai.SetOrderImp;

/**
 * 扣位信息处理Servlet
 * 调用路径为：/spi/orderseat.do<br>
 * 参数<br>
 * 参数InputStream中读取<br>
 * xml的样式如下：<br>
 * &lt;flyOrder&gt;<br>
 &lt;clientId&gt;SZX540&lt;/clientId&gt;<br>
 &lt;!--标识为精度天下--&gt;<br>
 &lt;JDName&gt;JD&lt;/JDName&gt;<br>
 &lt;!--订单号--&gt;<br>
 &lt;FOrder&gt;F201009023123&lt;/FOrder&gt;<br>
 &lt;!--下单时间--&gt;<br>
 &lt;OrderDate&gt;2010-09-27&lt;/OrderDate&gt;<br>
 &lt;!--航段集合--&gt;<br>
 &lt;flyAirs&gt;<br>
   &lt;!--单航程--&gt;<br>
   &lt;!--如果多航程，重复&lt;flyAir&gt;--&gt;<br>
   &lt;flyAir&gt;<br>
     &lt;!--航班号--&gt;<br>
     &lt;flyNo&gt;ZH9959&lt;/flyNo&gt;<br>
     &lt;!--舱位等级--&gt;<br>
     &lt;flyClass&gt;Y&lt;/flyClass&gt;<br>
     &lt;!--票价--&gt;<br>
     &lt;flyPrice&gt;1000&lt;/flyPrice&gt;<br>
     &lt;&gt;<br>&gt;--机建--&gt;<br>
     &lt;buildfee&gt;50&lt;/buildfee&gt;<br>
     &lt;!--燃油--&gt;<br>
     &lt;fuelfee&gt;40&lt;/fuelfee&gt;<br>
     &lt;!--机型--&gt;<br>
     &lt;planesty&gt;739&lt;/planesty&gt;<br>
     &lt;!--出发城市三字码--&gt;<br>
     &lt;sc&gt;SZX&lt;/sc&gt;<br>
     &lt;!--目标城市三字码--&gt;<br>
     &lt;ec&gt;PEK&lt;/ec&gt;<br>
     &lt;!--起飞日期--&gt;<br>
     &lt;sDate&gt;2010-09-30&lt;/sDate&gt;<br>
     &lt;!--抵达日期--&gt;<br>
     &lt;eDate&gt;2010-09-30&lt;/eDate&gt;<br>
     &lt;!--起飞时间--&gt;<br>
     &lt;sTime&gt;08:05&lt;/sTime&gt;<br>
     &lt;!--抵达时间--&gt;<br>
     &lt;eTime&gt;10:55&lt;/eTime&gt;<br>
   &lt;/flyAir&gt;<br>
 &lt;/flyAirs&gt;
 &lt;!--乘客  小于9个--&gt;<br>
 &lt;passengers&gt;<br>
   &lt;!--第一位乘客--&gt;<br>
   &lt;!--如果有多位乘客，重复&lt;passenger&gt;<br>--&gt;<br>
   &lt;passenger&gt;
     &lt;!--乘客姓名   一定是简体中文，不包含生僻字 英文名 的格式 first name/ last name--&gt;<br>
     &lt;pasName&gt;黄河&lt;/pasName&gt;<br>
     &lt;!--乘客类型 1/成人、2/儿童、3/婴儿--&gt;<br>
     &lt;pasType&gt;1&lt;/pasType&gt;<br>
     &lt;!--如果乘客类型3/婴儿时填写 出生日期--&gt;<br>
     &lt;pasYE&gt;2010-09-01&lt;/pasYE&gt;<br>
     &lt;!--证件类型 1、身份证，2、港澳通行证，3、护照，4、军官证，5、台胞证，6、国际海员证，7、回乡证，8、其他--&gt;<br>
     &lt;pasBirthday&gt;1&lt;/pasBirthday&gt;<br>
     &lt;!--证件号码--&gt;<br>
     &lt;pasBirthNo&gt;43102319850263&lt;/pasBirthNo&gt;<br>
     &lt;!--保险数量--&gt;<br>
     &lt;insurance_num&gt;2&lt;/insurance_num&gt;<br>
   &lt;/passenger&gt;<br>
 &lt;/passengers&gt;
 &lt;!--联系人--&gt;<br>
 &lt;contact&gt;<br>
   &lt;!--姓名--&gt;
   &lt;conName&gt;<br>测试&lt;/conName&gt;<br>
   &lt;!--固定电话--&gt;
   &lt;conTel&gt;<br>0755-83000000&lt;/conTel&gt;<br>
   &lt;!--移动电话--&gt;
   &lt;conMobile&gt;13500000000&lt;/conMobile&gt;<br>
   &lt;!--电子邮件--&gt;<br>
   &lt;conEmail&gt;cs@shenzhenair.com&lt;/conEmail&gt;<br>
   &lt;!--联系地址--&gt;<br>
   &lt;conAddress&gt;深圳市福田区农林路&lt;/conAddress&gt;<br>
   &lt;!--配送地址--&gt;<br>
   &lt;psAddress&gt;深圳市福田区农林路&lt;/psAddress&gt;<br>
 &lt;/contact&gt;<br>
&lt;/flyOrder&gt;<br>
 * @author lin
 * 
 */
public class SeatInfoServlet extends HttpServlet {
	/**
	 * 返回的文件类型
	 */
	private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

	/**
	 * 序列化字段
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 初始化方法，系统自动调用，不需处理
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * 订单处理方法,方法处理调用了Xml2Order.xml2Seat
	 * @see Xml2Order#xml2Seat(byte[])
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		InputStream inputStream = request.getInputStream();
		// 获取订单xml开始
		java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
		byte[] buffer = new byte[64 * 1024];
		for (;;) {
			int count = inputStream.read(buffer);
			if (count < 0)
				break;
			os.write(buffer, 0, count);
		}
		byte[] a = os.toByteArray();
		// 获取订单xml结束
		com.ucai.po.FlyOrder flyOrder = Xml2Order.xml2Seat(a);// 打包订单对象
		// 转换扣位xml开始
		XStream xstream = new XStream();
		xstream.alias("flyOrder", com.ucai.po.FlyOrder.class);
		xstream.alias("flyAir", FlyAir.class);
		xstream.alias("passenger", Passenger.class);
		String xml = xstream.toXML(flyOrder);
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
		reXml = xstream.toXML(rpo);
		PrintWriter pw = response.getWriter();
		pw.write(reXml);
		pw.flush();
	}

	/**
	 * get处理方法
	 * @see #doPost(HttpServletRequest, HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
