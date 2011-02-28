package com.alipay.wap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.alipay.wap.po.ClientConfig;
import com.alipay.wap.security.SecurityManagerImpl;
import com.alipay.wap.security.SecurityManager;
import com.ucai.webservices.ucai.SetOrderImp;

/**
 * 接收支付宝回传处理Servlet<br>
 * 该方法的调用路径为：/callBackServlet<br>
 * 其中回传数据的验签是通过SecurityManagerImpl.verify(String, String, String, String)<br>
 * 验签成功后便调用SetOrderImp.updateOrder(String, String)更新订单状态
 * 
 * @author 李卓林
 * @see SecurityManagerImpl#verify(String, String, String, String)
 * @see SetOrderImp#updateOrder(String, String)
 */
public class CallBackServlet extends HttpServlet {
	private SecurityManager securityManager = new SecurityManagerImpl();

	/**
	 * 序列化时为了保持版本的兼容性
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 初始化方法
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * 通知处理方法,此方法便是接收支付宝回传处理的，首先把接收到的数据规范化处理,主要是通过方法getVerifyData(Map)进行规范化<br>
	 * 然后通过SecurityManagerImpl.verify(String, String, String, String)<br>
	 * 验签成功后便调用SetOrderImp.updateOrder(String, String)更新订单状态
	 * 
	 * @see SecurityManagerImpl#verify(String, String, String, String)
	 * @see SetOrderImp#updateOrder(String, String)
	 * @see #getVerifyData(Map)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		System.out.println("接收到通知!");
		// 获得通知参数
		Map map = request.getParameterMap();
		// 获得通知签名
		String sign = (String) ((Object[]) map.get("sign"))[0];
		// 获得待验签名的数据
		String verifyData = getVerifyData(map);

		boolean verified = false;
		// 验签名
		try {
			verified = securityManager.verify(ClientConfig.md5SignAlgo,
					verifyData, sign, ClientConfig.md5Key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		// 验证签名通过
		if (verified) {
			// 根据交易状态处理业务逻辑
			// 当交易状态成功，处理业务逻辑成功。回写success
			String orderid = getOrderId(map);
			String price = getPrice(map);
			SetOrderImp setOrderImp = new SetOrderImp();
			System.out.println(setOrderImp.updateOrder(orderid, price));
			System.out.println("接收支付宝系统通知验证签名成功！");
			out.print("success");
		} else {
			System.out.println("接收支付宝系统通知验证签名失败，请检检查！");
			out.print("fail");
		}
	}

	/**
	 * 通知处理方法 get处理<br>
	 * 主要是调用doPost(HttpServletRequest, HttpServletResponse)作为处理方法
	 * 
	 * @see #doPost(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 获得验签名的数据
	 * 
	 * @param map
	 *            回传参数map
	 * @return 验签名字符串
	 */
	private String getVerifyData(Map map) {
		String service = (String) ((Object[]) map.get("service"))[0];
		String v = (String) ((Object[]) map.get("v"))[0];
		String sec_id = (String) ((Object[]) map.get("sec_id"))[0];
		String notify_data = (String) ((Object[]) map.get("notify_data"))[0];
		System.out.println("通知参数为：" + "service=" + service + "&v=" + v
				+ "&sec_id=" + sec_id + "&notify_data=" + notify_data);
		return "service=" + service + "&v=" + v + "&sec_id=" + sec_id
				+ "&notify_data=" + notify_data;
	}

	/**
	 * 获取订单编号
	 * 
	 * @param map
	 *            回传参数map
	 * @return 订单编号
	 */
	private String getOrderId(Map map) {
		String notify_data = (String) ((Object[]) map.get("notify_data"))[0];
		StringReader sr = new StringReader(notify_data);
		SAXBuilder builder = new SAXBuilder(false);
		try {
			Document doc = builder.build(sr);
			Element notify = doc.getRootElement();
			String orderid = notify.getChildTextTrim("out_trade_no");
			return orderid;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}

	/**
	 * 获取价格字段
	 * 
	 * @param map
	 *            回传参数map
	 * @return 价格
	 */
	private String getPrice(Map map) {
		String notify_data = (String) ((Object[]) map.get("notify_data"))[0];
		StringReader sr = new StringReader(notify_data);
		SAXBuilder builder = new SAXBuilder(false);
		try {
			Document doc = builder.build(sr);
			Element notify = doc.getRootElement();
			String price = notify.getChildTextTrim("total_fee");
			return price;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}
}
