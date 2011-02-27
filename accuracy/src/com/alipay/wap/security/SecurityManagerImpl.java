package com.alipay.wap.security;

/**
 * 安全管理实现类
 * 
 * @author 李卓林
 * 
 */
public class SecurityManagerImpl implements SecurityManager {
	private Signature signature;

	/**
	 * 对原始数据进行签名
	 * 该方法对数据的签名是通过md5，具体的签名方法详看MD5Signature.sign(String,String)
	 * @param content
	 *            原始数据
	 * @param algoType
	 *            算法类型
	 * @param key
	 *            私钥
	 * @return 签名
	 * @throws Exception
	 *             发生一般异常时
	 * @see MD5Signature#sign(String,String)
	 */
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

	/**
	 * 验证签名
	 * 验签同样通过md5验签，具体的验签方法详看MD5Signature.verify(String, String, String)
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
	 *             发生一般异常时
	 *             
	 * @see MD5Signature#verify(String, String, String)
	 */
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

	/**
	 * 支付控件的签名方法
	 * 由于项目原因，该签名方法暂时未使用
	 * @param algoType
	 *            算法类型
	 * @param content
	 *            原始数据
	 * @return 返回签名后的字符串
	 * @throws Exception
	 *             发生一般异常时
	 * @see      MD5Signature#sign(String)       
	 */
	public String sign(String algoType, String content) throws Exception {
		if (algoType.equals("MD5")) {
			//
			signature = new MD5Signature();
		} else {
			throw new Exception("本应用不支持的算法");
		}
		return signature.sign(content);
	}

}
