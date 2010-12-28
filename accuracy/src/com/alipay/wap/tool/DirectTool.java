package com.alipay.wap.tool;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alipay.wap.security.SecurityManagerImpl;
import com.alipay.wap.security.SecurityManager;
import com.ucai.po.ResultOrder;

public class DirectTool {
	private SecurityManager securityManager = new SecurityManagerImpl();
	/**
	 * 准备alipay.wap.trade.create.direct服务的参数
	 * 
	 * @param resultOrder
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, String> prepareTradeRequestParamsMap(
			ResultOrder resultOrder) throws UnsupportedEncodingException {
		Map<String, String> requestParams = new HashMap<String, String>();
		// 商品名称
		String subject = resultOrder.getA_Scity()+"-"+resultOrder.getA_Ecity();
		// 外部交易号
		String outTradeNo = resultOrder.getF_Number().trim();
		// 商品总价
		String totalFee = resultOrder.getTotalPrice().trim();
		// req_data的内容
		String reqData = "<direct_trade_create_req>" + "<subject>" + subject
				+ "</subject><out_trade_no>" + outTradeNo
				+ "</out_trade_no><total_fee>" + totalFee
				+ "</total_fee></direct_trade_create_req>";
		requestParams.put("req_data", reqData);
		requestParams.put("req_id", System.currentTimeMillis() + "");
		requestParams.putAll(prepareCommonParams());
		System.out.println("prepareTradeRequestParamsMap" + requestParams);
		return requestParams;
	}
	/**
	 * 准备通用参数
	 * 
	 * @return
	 */
	private Map<String, String> prepareCommonParams() {
		Map<String, String> commonParams = new HashMap<String, String>();
		String service = "alipay.wap.trade.create.direct";
		commonParams.put("service", service);
		String secId = "MD5";
		commonParams.put("sec_id", secId);
		String partner = "2088301177981331";
		commonParams.put("partner", partner);		
		String format = "xml";
		commonParams.put("format", format);
		String v = "2.0";
		commonParams.put("v", v);
		System.out.println("prepareCommonParams" + commonParams);
		return commonParams;
	}
	/**
	 * 对参数进行签名
	 * 
	 * @param reqParams
	 * @return
	 */
	public String sign(Map<String, String> reqParams,String signAlgo,String key) {

		String signData = ParameterUtil.getSignData(reqParams);
		String sign = "";
		try {
			sign = securityManager.sign(signAlgo,
					signData, key);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sign;
	}
	/**
	 * 准备alipay.wap.auth.authAndExecute服务的参数
	 * 
	 * @param requestToken
	 * @return
	 */
	public Map<String, String> prepareAuthParamsMap(
			HttpServletRequest request, String requestToken) {
		Map<String, String> requestParams = new HashMap<String, String>();
		String reqData = "<auth_and_execute_req><request_token>" + requestToken
				+ "</request_token></auth_and_execute_req>";
		requestParams.put("req_data", reqData);
		requestParams.putAll(prepareCommonParams());
		String callbackUrl = request.getParameter("call_back_url").trim();
		requestParams.put("call_back_url", callbackUrl);
		requestParams.put("service", "alipay.wap.auth.authAndExecute");
		return requestParams;
	}
	/**
	 * 调用alipay.wap.auth.authAndExecute服务的时候需要跳转到支付宝的页面，组装跳转url
	 * 
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	public String getRedirectUrl(Map<String, String> reqParams,String reqUrl)
			throws Exception {
		String redirectUrl = reqUrl + "?";
		redirectUrl = redirectUrl + ParameterUtil.mapToUrl(reqParams);
		return redirectUrl;
	}
}