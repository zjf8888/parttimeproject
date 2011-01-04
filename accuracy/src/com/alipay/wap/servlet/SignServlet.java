package com.alipay.wap.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.wap.po.ClientConfig;
import com.alipay.wap.security.SecurityManager;
import com.alipay.wap.security.SecurityManagerImpl;
import com.ucai.po.ResultOrder;
import com.ucai.webservices.ucai.SetOrderImp;

public class SignServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		try {
			String forderid = request.getParameter("forderid");
			SetOrderImp orderImp = new SetOrderImp();
			ResultOrder resultOrder = orderImp.getFlyOrderList(forderid);
			String subject = resultOrder.getA_Scity() + "-"
					+ resultOrder.getA_Ecity();
			String body = "航班号为" + resultOrder.getA_FlyNo() + ",日期为"
					+ resultOrder.getA_FlyDate().split(" ")[0] + "在"
					+ resultOrder.getA_Scity() + "出发,目标城市为"
					+ resultOrder.getA_Ecity();
			String signInfo = "partner=2088301177981331&seller=2088301177981331&out_trade_no="
					+ forderid
					+ "&subject="
					+ subject
					+ "&body="
					+ body
					+ "&total_fee="
					+ resultOrder.getTotalPrice()
					+ "&notify_url=" + ClientConfig.notifyUrl;
			String sign = sign(signInfo, ClientConfig.md5SignAlgo);
			signInfo = signInfo + "&sign=" + sign + "&sign_type="
					+ ClientConfig.md5SignAlgo;
			System.out.println(signInfo);
			PrintWriter pw = response.getWriter();
			pw.write(signInfo);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对参数进行签名
	 * 
	 * @param reqParams
	 * @return
	 */
	public String sign(String signInfo, String signAlgo) {

		String sign = "";
		try {
			SecurityManager securityManager = new SecurityManagerImpl();
			sign = securityManager.sign(signAlgo, signInfo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sign;
	}
}
