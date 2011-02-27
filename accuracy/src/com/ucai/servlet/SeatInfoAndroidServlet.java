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

/**
 * 扣位信息处理Servlet,android版本,就是返回文件类型为json格式<br>
 * 调用路径为：/seatInfoAndroidServlet<br>
 * 参数<br>
 * xml 请求的xml参数<br>
 * xml的样式如下：<br>
 * 
&lt;flyOrder&gt;<br>
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
 * 
 * @author 李卓林
 * 
 */
public class SeatInfoAndroidServlet extends HttpServlet {

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
	 * 订单处理方法
	 * 方法中主要的xml处理主要依靠DoSeat.doSeatInfo(request, response)方法
	 * @see DoSeat#doSeatInfo(HttpServletRequest, HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DoSeat doseat = new DoSeat();
		ReturnPo rpo = doseat.doSeatInfo(request, response);
		JSONObject jsonObject = JSONObject.fromObject(rpo);
		System.out.println(jsonObject.toString());
		PrintWriter pw = response.getWriter();
		pw.write(jsonObject.toString());
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
