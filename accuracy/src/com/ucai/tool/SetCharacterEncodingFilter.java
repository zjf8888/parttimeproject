package com.ucai.tool;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 字符过滤类,是作为系统统一管理字符方法，在/WEB-INF/web.xml配置使用
 * 
 * @author lin
 * 
 */
public class SetCharacterEncodingFilter implements Filter {
	/**
	 * 字符编码
	 */
	protected String encoding = null;
	/**
	 * 过滤配置
	 */
	protected FilterConfig filterConfig = null;
	/**
	 * 字符是否忽略
	 */
	protected boolean ignore = true;

	/**
	 * 销毁对象方法
	 */
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	/**
	 * 过滤方法
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            回答
	 * @param chain
	 *            资源请求链表
	 * @exception IOException
	 *                IO异常
	 * @exception ServletException
	 *                Servlet异常
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// Conditionally select and set the character encoding to be used
		if (ignore || (request.getCharacterEncoding() == null)) {
			String encoding = selectEncoding(request);
			if (encoding != null) {
				request.setCharacterEncoding(encoding); // 就是这句话在工作的
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * 初始化方法
	 * 
	 * @param filterConfig
	 *            过滤器配置对象
	 * @exception ServletException
	 *                Servlet异常
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("true")) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("yes")) {
			this.ignore = true;
		} else {
			this.ignore = false;
		}

	}

	/**
	 * 编码选择方法
	 * 
	 * @param request
	 *            请求对象
	 * @return 编码
	 */
	protected String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}

}