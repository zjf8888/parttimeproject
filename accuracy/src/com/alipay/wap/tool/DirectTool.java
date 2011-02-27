package com.alipay.wap.tool;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.alipay.wap.po.ClientConfig;
import com.alipay.wap.security.SecurityManagerImpl;
import com.alipay.wap.security.SecurityManager;
import com.ucai.po.ResultOrder;

/**
 * 交易创建工具类
 * 
 * @author 李卓林
 * 
 */
public class DirectTool {
	/**
	 * 返回的文件制式
	 */
	private SecurityManager securityManager = new SecurityManagerImpl();

	/**
	 * 准备alipay.wap.trade.create.direct服务的参数
	 * 
	 * @param resultOrder 订单查询结果
	 * @return 参数对照表
	 * @throws UnsupportedEncodingException 不支持字符编码
	 */
	public Map<String, String> prepareTradeRequestParamsMap(
			ResultOrder resultOrder) throws UnsupportedEncodingException {
		Map<String, String> requestParams = new HashMap<String, String>();
		// 商品名称
		String subject = resultOrder.getA_Scity() + "-"
				+ resultOrder.getA_Ecity();
		// 外部交易号
		String outTradeNo = resultOrder.getF_Number().trim();
		// 商品总价
		String totalFee = "0.01";
		// String totalFee = resultOrder.getTotalPrice().trim();
		// 卖家帐号
		String sellerAccountName = ClientConfig.sellerAccountName;
		// 接收支付宝发送的通知的url
		String notifyUrl = ClientConfig.notifyUrl;
		// req_data的内容
		String reqData = "<direct_trade_create_req>"
				+ "<subject>"
				+ subject
				+ "</subject><out_trade_no>"
				+ outTradeNo
				+ "</out_trade_no><total_fee>"
				+ totalFee
				+ "</total_fee><seller_account_name>"
				+ sellerAccountName
				+ "</seller_account_name><notify_url>"
				+ notifyUrl
				+ "</notify_url><pay_expire>10</pay_expire></direct_trade_create_req>";
		requestParams.put("req_data", reqData);
		requestParams.put("req_id", System.currentTimeMillis() + "");
		requestParams.putAll(prepareCommonParams());
		System.out.println("prepareTradeRequestParamsMap" + requestParams);
		return requestParams;
	}

	/**
	 * 准备通用参数
	 * 
	 * @return 通用参数
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
	 * @param reqParams 通用参数表
	 * @param signAlgo 算法类型
	 * @param key 私钥
	 * @return 签名后的字符串
	 */
	public String sign(Map<String, String> reqParams, String signAlgo,
			String key) {

		String signData = ParameterUtil.getSignData(reqParams);
		String sign = "";
		try {
			sign = securityManager.sign(signAlgo, signData, key);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sign;
	}

	/**
	 * 准备alipay.wap.auth.authAndExecute服务的参数
	 * 
	 * @param requestToken 请求标志
	 * @return 请求参数列表
	 */
	public Map<String, String> prepareAuthParamsMap(String requestToken) {
		Map<String, String> requestParams = new HashMap<String, String>();
		String reqData = "<auth_and_execute_req><request_token>" + requestToken
				+ "</request_token></auth_and_execute_req>";
		requestParams.put("req_data", reqData);
		requestParams.putAll(prepareCommonParams());
		String callbackUrl = "http://www.ucai.com/";
		requestParams.put("call_back_url", callbackUrl);
		requestParams.put("service", "alipay.wap.auth.authAndExecute");
		return requestParams;
	}

	/**
	 * 调用alipay.wap.auth.authAndExecute服务的时候需要跳转到支付宝的页面，组装跳转url
	 * 
	 * @param reqParams 请求参数列表
	 * @return 跳转url字符串
	 * @throws Exception 一般异常
	 */
	public String getRedirectUrl(Map<String, String> reqParams, String reqUrl)
			throws Exception {
		String redirectUrl = reqUrl + "?";
		redirectUrl = redirectUrl + ParameterUtil.mapToUrl(reqParams);
		return redirectUrl;
	}
}
