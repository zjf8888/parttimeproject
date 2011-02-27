package com.alipay.wap.po;

/**
 * 支付客户端配置信息类
 * @author 李卓林
 * 
 */
public class ClientConfig {

	/**
	 * 商户的MD5私钥
	 */
	public static String md5Key = "konbtn7ffh861z6rntoypzyvl60ndqzm";

	/**
	 * 签名的算法
	 */
	public static String md5SignAlgo = "MD5";

	/**
	 * 卖家帐号
	 */
	public static String sellerAccountName = "jdpiaowu@163.com".trim();

	/**
	 * 接收支付宝发送的通知的url
	 */ 
	public static String notifyUrl = "http://www.ecook.cn/accuracy/callBackServlet";
}
