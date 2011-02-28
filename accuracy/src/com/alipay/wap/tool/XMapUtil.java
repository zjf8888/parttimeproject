package com.alipay.wap.tool;

import java.io.InputStream;
import java.util.List;

import org.nuxeo.common.xmap.XMap;

/**
 * 支付xml解释工具类<br>
 * 作为对xmap开源工具的辅助类
 * 
 * @author 李卓林
 * 
 */
public class XMapUtil {
	/**
	 * xml转换beam元素
	 */
	private static final XMap xmap;

	/**
	 * 静态初始化元素
	 */	
	static {
		xmap = new XMap();
	}

	/**
	 * 注册Object。
	 * 
	 * @param clazz 注冊對應的類
	 */
	public static void register(Class<?> clazz) {
		if (clazz != null) {
			xmap.register(clazz);
		}
	}

	/**
	 * 解析xml到Object
	 * 
	 * @param is 需轉換的輸入流
	 * @return 轉換后的對應
	 * @throws Exception 一般異常
	 */
	public static Object load(InputStream is) throws Exception {
		Object obj = null;
		try {
			obj = xmap.load(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return obj;
	}

	/**
	 * Object到XML。
	 * 
	 * @param obj 需转换的对象
	 * @param encoding 相应的编码
	 * @param outputsFields 输出的元素
	 * @return 返回的xml字符串
	 * @throws Exception 一般异常
	 */
	public static String asXml(Object obj, String encoding,
			List<String> outputsFields) throws Exception {

		return xmap.asXmlString(obj, encoding, outputsFields);
	}
}
