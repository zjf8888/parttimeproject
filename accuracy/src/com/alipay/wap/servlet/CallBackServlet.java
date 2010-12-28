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

public class CallBackServlet extends HttpServlet {
	private SecurityManager securityManager = new SecurityManagerImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * 通知处理方法
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
			//  根据交易状态处理业务逻辑
			// 当交易状态成功，处理业务逻辑成功。回写success
			String orderid=getOrderId(map);
			SetOrderImp setOrderImp=new SetOrderImp();
			System.out.println(setOrderImp.updateOrder(orderid));
			System.out.println("接收支付宝系统通知验证签名成功！");
			out.print("success");
		} else {
			System.out.println("接收支付宝系统通知验证签名失败，请检检查！");
			out.print("fail");
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 获得验签名的数据
	 * 
	 * @param map
	 * @return
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
	private String getOrderId(Map map){
		String notify_data = (String) ((Object[]) map.get("notify_data"))[0];
		StringReader sr = new StringReader(notify_data);
		SAXBuilder builder = new SAXBuilder(false);
		try {
			Document doc = builder.build(sr);
			Element notify = doc.getRootElement();
			String orderid=notify.getChildTextTrim("out_trade_no");
			return orderid;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
		return null;
	}
}
