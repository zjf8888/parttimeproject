package com.alipay.wap.security;

/**
 * 安全管理接口
 * 
 * @author 李卓林
 * 
 */
public interface SecurityManager {

	/**
	 * 对原始数据进行签名
	 * 
	 * @param content
	 *            原始数据
	 * @param algoType
	 *            算法类型
	 * @param key
	 *            私钥
	 * @return 签名
	 * @throws Exception 发生一般异常时
	 */
	public String sign(String algoType, String content, String key)
			throws Exception;

	/**
	 * 验证签名
	 * 
	 * @param content
	 *            原始数据
	 * @param algoType
	 *            算法类型
	 * @param sign
	 *            签名
	 * @param key
	 *            公钥
	 * @return 签名验证通过 False 签名验证失败
	 * @throws Exception 发生一般异常时
	 */
	public boolean verify(String algoType, String content, String sign,
			String key) throws Exception;

	/**
	 * 支付控件的签名方法
	 * 
	 * @param algoType
	 *            算法类型
	 * @param content
	 *            原始数据
	 * @return 返回签名后的字符串
	 * @throws Exception 发生一般异常时
	 */
	public String sign(String algoType, String content) throws Exception;
}
