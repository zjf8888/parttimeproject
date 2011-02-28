package com.alipay.wap.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.wap.api.AlipayApi;
import com.alipay.wap.po.ClientConfig;
import com.alipay.wap.po.DirectTradeCreateRes;
import com.alipay.wap.po.ResponseResult;
import com.alipay.wap.tool.DirectTool;
import com.alipay.wap.tool.StringUtil;
import com.alipay.wap.tool.XMapUtil;
import com.ucai.po.ResultOrder;
import com.ucai.webservices.ucai.SetOrderImp;

/**
 * 总支付处理接口,只需提交订单号，程序便可跟支付宝完成所有的操作，直接把连接重定向到支付宝的支付界面<br>
 * 该方法的调用路径为：/tradeServlet<br>
 * 
 * @author 李卓林
 * 
 */
public class TradeServlet extends HttpServlet {
	/**
	 * 返回的文件制式
	 */
	private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

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
	 * 订单处理方法
	 * 程序(1)首先通过订单号在精度天下的业务系统中查询出订单的具体信息（处理方法中SetOrderImp.getFlyOrderList）<br>
	 * (2)然后通过DirectTool.prepareTradeRequestParamsMap对返回的查询结果作规范化处理，<br>
	 * (3)通过DirectTool.sign方法对处理后的数据签名
	 * (4)通过AlipayApi.getResponseResult方法提交签名后的数据
	 * (5)通过XMapUtil对返回结果解释成对象
	 * （6)从解释的对象中获取连接，并对提交的连接进行签名
	 * （7）把浏览器连接重定向到支付宝支付界面
	 * @see SetOrderImp#getFlyOrderList(String, String)
	 * @see DirectTool#prepareTradeRequestParamsMap(ResultOrder)
	 * @see DirectTool#sign(Map, String, String)
	 * @see AlipayApi#getResponseResult(Map, String, String, String)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		try {
			String forderid = request.getParameter("forderid");
			SetOrderImp orderImp = new SetOrderImp();
			ResultOrder resultOrder = orderImp.getFlyOrderList(forderid, "");
			DirectTool dt = new DirectTool();
			Map<String, String> reqParams = dt
					.prepareTradeRequestParamsMap(resultOrder);

			String reqUrl = "http://wappaygw.alipay.com/service/rest.htm";

			String sign = dt.sign(reqParams, ClientConfig.md5SignAlgo,
					ClientConfig.md5Key);
			reqParams.put("sign", sign);

			ResponseResult resResult = new ResponseResult();
			String businessResult = "";
			AlipayApi api = new AlipayApi();
			try {
				resResult = api.getResponseResult(reqParams, reqUrl,
						ClientConfig.md5SignAlgo, ClientConfig.md5Key);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (resResult.isSuccess()) {
				businessResult = resResult.getBusinessResult();
				System.out.println("业务成功结果:" + businessResult);
			} else {
				return;
			}
			DirectTradeCreateRes directTradeCreateRes = null;
			XMapUtil.register(DirectTradeCreateRes.class);
			try {
				directTradeCreateRes = (DirectTradeCreateRes) XMapUtil
						.load(new ByteArrayInputStream(businessResult
								.getBytes("UTF-8")));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 开放平台返回的内容中取出request_token（对返回的内容要先用私钥解密，再用支付宝的公钥验签名）
			String requestToken = directTradeCreateRes.getRequestToken();

			Map<String, String> authParams = dt
					.prepareAuthParamsMap(requestToken);
			String authSign = dt.sign(authParams, ClientConfig.md5SignAlgo,
					ClientConfig.md5Key);
			authParams.put("sign", authSign);
			String redirectURL = "";
			try {
				redirectURL = dt.getRedirectUrl(authParams, reqUrl);
				System.out.println("跳转地址:" + redirectURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (StringUtil.isNotBlank(redirectURL)) {
				response.sendRedirect(redirectURL);
				return;
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * 订单处理方法,具体操作请查看doPost(HttpServletRequest, HttpServletResponse)
	 * @see #doPost(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
