package com.alipay.wap.tool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 参数构造工具类
 * @author lizhlin
 *
 */
public class ParameterUtil {

    /**
     * 将Map组装成待签名数据。
     * 待签名的数据必须按照一定的顺序排列 这个是支付宝提供的服务的规范，否则调用支付宝的服务会通不过签名验证
     * @param params 参数列表
     * @return 签名数据
     */
    public static String getSignData(Map<String, String> params) {
        StringBuffer content = new StringBuffer();

        // 按照key做排序
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);

            if ("sign".equals(key)) {
                continue;
            }
            String value = params.get(key);
            if (value != null) {
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            } else {
                content.append((i == 0 ? "" : "&") + key + "=");
            }

        }

        return content.toString();
    }

    /**
     * 将Map中的数据组装成url
     * @param params 参数列表
     * @return 组装后的参数字符串
     * @throws UnsupportedEncodingException  不支持字符编码
     */
    public static String mapToUrl(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String key : params.keySet()) {
            String value = params.get(key);
            //System.out.println(key+":"+value);
            if (isFirst) {
                sb.append(key + "=" + URLEncoder.encode(value, "utf-8"));
                isFirst = false;
            } else {
                if (value != null) {
                    sb.append("&" + key + "=" + URLEncoder.encode(value, "utf-8"));
                } else {
                    sb.append("&" + key + "=");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 取得URL中的参数值。
     * <p>如不存在，返回空值。</p>
     * 
     * @param url 参数
     * @param name 参数名
     * @return 返回参数对应的值
     */
    public static String getParameter(String url, String name) {
        if (name == null || name.equals("")) {
            return null;
        }
        name = name + "=";
        int start = url.indexOf(name);
        if (start < 0) {
            return null;
        }
        start += name.length();
        int end = url.indexOf("&", start);
        if (end == -1) {
            end = url.length();
        }
        return url.substring(start, end);
    }
}
