package com.alipay.wap.po;

public class ClientConfig {

	/**
	 * 商户的MD5私钥
	 */
	public static String md5Key = "";

	/**
	 * 签名的算法
	 */
	public static String md5SignAlgo = "MD5";

	public static void setMd5Key(String md5Key) {
		ClientConfig.md5Key = md5Key;
	}

	public static void setMd5SignAlgo(String md5SignAlgo) {
		ClientConfig.md5SignAlgo = md5SignAlgo;
	}
}
