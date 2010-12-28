package com.alipay.wap.security;

/**
 * 安全管理类
 * 
 * @author lizhlin
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
	 * @throws Exception
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
	 * @throws Exception
	 */
	public boolean verify(String algoType, String content, String sign,
			String key) throws Exception;
}
