package com.alipay.wap.security;

public interface Signature {
	/**
	 * 对原始数据进行签名
	 * 
	 * @param content
	 *            原始数据
	 * @param key
	 *            私钥
	 * @return 签名
	 * @throws Exception
	 */
	public String sign(String content, String key) throws Exception;

	/**
	 * 对支付控件签名
	 * 
	 * @param content原始数据
	 * @return
	 * @throws Exception
	 */
	public String sign(String content) throws Exception;

	/**
	 * 验证签名
	 * 
	 * @param content
	 *            原始数据
	 * @param sign
	 *            签名
	 * @param key
	 *            公钥
	 * @return 签名验证通过 False 签名验证失败
	 * @throws Exception
	 */
	public boolean verify(String content, String sign, String key)
			throws Exception;
}
