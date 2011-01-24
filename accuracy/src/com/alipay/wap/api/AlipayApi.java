package com.alipay.wap.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.alipay.wap.po.ErrorCode;
import com.alipay.wap.po.ResponseResult;
import com.alipay.wap.security.SecurityManager;
import com.alipay.wap.security.SecurityManagerImpl;
import com.alipay.wap.tool.ParameterUtil;
import com.alipay.wap.tool.XMapUtil;

/**
 * 支付宝接口类
 * @author lin
 *
 */
public class AlipayApi {
	private SecurityManager securityManager = new SecurityManagerImpl(); //安全检验控制器

	/**
	 * 根据参数返回的数据流
	 * @param reqParams 参数对象map
	 * @param reqUrl 提交数据地址
	 * @return 返回对应的数据流
	 */
	public InputStream openViewConn(Map<String, String> reqParams,
			String reqUrl) {
		InputStream is = null;
		try {
			URL realUrl = new URL(reqUrl);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			PrintWriter out = new PrintWriter(new OutputStreamWriter(
					conn.getOutputStream(), "utf-8"));
			// 发送请求参数
			String params = ParameterUtil.mapToUrl(reqParams);
			out.print(params);
			// flush输出流的缓冲
			out.flush();
			is = conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 提交支付信息
	 * @param reqParams 支付参数maP
	 * @param reqUrl 提交地址
	 * @param secId 算法类型
	 * @param key 公钥
	 * @return 返回支付宝返回对象
	 */
	public ResponseResult getResponseResult(Map<String, String> reqParams,
			String reqUrl, String secId, String key) {
		try {
			InputStream is = openViewConn(reqParams, reqUrl);
			String response = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			response = URLDecoder.decode(buffer.toString(), "utf-8");
			return praseResult(response, secId, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析支付宝返回的结果
	 * 
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ResponseResult praseResult(String response, String secId, String key)
			throws Exception {
		// 调用成功
		HashMap<String, String> resMap = new HashMap<String, String>();
		String v = ParameterUtil.getParameter(response, "v");
		String service = ParameterUtil.getParameter(response, "service");
		String partner = ParameterUtil.getParameter(response, "partner");
		// String secId = ParameterUtil.getParameter(response, "sec_id");
		String sign = ParameterUtil.getParameter(response, "sign");
		String reqId = ParameterUtil.getParameter(response, "req_id");
		resMap.put("v", v);
		resMap.put("service", service);
		resMap.put("partner", partner);
		resMap.put("sec_id", secId);
		resMap.put("req_id", reqId);
		String businessResult = "";
		ResponseResult result = new ResponseResult();
		if (response.contains("<err>")) {
			result.setSuccess(false);
			businessResult = ParameterUtil.getParameter(response, "res_error");

			// 转换错误信息
			XMapUtil.register(ErrorCode.class);
			ErrorCode errorCode = (ErrorCode) XMapUtil
					.load(new ByteArrayInputStream(businessResult
							.getBytes("UTF-8")));
			result.setErrorMessage(errorCode);

			resMap.put("res_error",
					ParameterUtil.getParameter(response, "res_error"));
		} else {
			businessResult = ParameterUtil.getParameter(response, "res_data");
			result.setSuccess(true);
			result.setBusinessResult(businessResult);
			resMap.put("res_data", businessResult);
		}
		String verifyData = ParameterUtil.getSignData(resMap);

		System.out.println(verifyData);
		boolean verified = securityManager.verify(secId, verifyData, sign, key);

		if (!verified) {
			throw new Exception("验证签名失败");
		}
		return result;
	}

}
