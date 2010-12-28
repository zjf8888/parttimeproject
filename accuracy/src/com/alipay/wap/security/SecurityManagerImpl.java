package com.alipay.wap.security;


public class SecurityManagerImpl implements SecurityManager {
	private Signature signature;
	@Override
	public String sign(String algoType, String content, String key)
			throws Exception {
		if (algoType.equals("MD5")) {
			//
			signature = new MD5Signature();
		} else {
			throw new Exception("本应用不支持的算法");
		}
		return signature.sign(content, key);
	}

	@Override
	public boolean verify(String algoType, String content, String sign,
			String key) throws Exception {
		if (algoType.equals("MD5")) {
			//
			signature = new MD5Signature();
		} else {
			throw new Exception("本应用不支持的算法");
		}
		return signature.verify(content, sign, key);
	}

}