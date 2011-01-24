package com.alipay.wap.security;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;

import com.alipay.wap.tool.StringUtil;

/**
 * MD5签名、验签类
 * 
 * @author 李卓林
 * 
 */
public class MD5Signature implements Signature {

	/**
	 * 带key的签名方法
	 * 
	 * @param content签名内容
	 * @param key
	 *            商户key
	 * @return 返回的签名内容
	 */
	public String sign(String content, String key) throws Exception {
		String tosign = (content == null ? "" : content) + key;

		try {
			return DigestUtils.md5Hex(getContentBytes(tosign, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new SignatureException("MD5签名[content = " + content
					+ "; charset = utf-8" + "]发生异常!", e);
		}
	}

	/**
	 * 对支付控件签名
	 * 
	 * @param content签名内容
	 * @return 返回的签名内容
	 */
	public String sign(String content) throws Exception {
		String tosign = (content == null ? "" : content);
		try {
			return DigestUtils.md5Hex(getContentBytes(tosign, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new SignatureException("MD5签名[content = " + content
					+ "; charset = utf-8" + "]发生异常!", e);
		}
	}

	/**
	 * 验签方法
	 * 
	 * @param content 验签内容
	 * @param sign 签名
	 * @param key 商户key
	 * @return 返回的验签结果，合法返回true,否则false
	 */
	public boolean verify(String content, String sign, String key)
			throws Exception {
		String tosign = (content == null ? "" : content) + key;

		try {
			String mySign = DigestUtils
					.md5Hex(getContentBytes(tosign, "utf-8"));

			return StringUtil.equals(mySign, sign) ? true : false;
		} catch (UnsupportedEncodingException e) {
			throw new SignatureException("MD5验证签名[content = " + content
					+ "; charset =utf-8 " + "; signature = " + sign + "]发生异常!",
					e);
		}
	}

	/**
	 * 通过string返回byte[]
	 * @param content 内容
	 * @param charset 编码
	 * @return 返回对应的字节码数组
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	protected byte[] getContentBytes(String content, String charset)
			throws UnsupportedEncodingException {
		if (StringUtil.isEmpty(charset)) {
			return content.getBytes();
		}

		return content.getBytes(charset);
	}

}
