package com.szqb.web;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.beanaction.ActionContext;
import org.apache.struts.beanaction.BaseBean;

public abstract class AbstractBean extends BaseBean {

	public static final String SUCCESS = "success";

	public static final String FAILURE = "failure";

	private static final String USERNAME = "ecook_username";

	private static final String EMAIL = "ecook_email";

	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

	@SuppressWarnings("unchecked")
	protected void setMessage(String value) {
		ActionContext.getActionContext().getRequestMap().put("message", value);
	}

	@SuppressWarnings("unchecked")
	protected void setCookie(String username, String email) {
		try {
			ActionContext ac = ActionContext.getActionContext();
			HttpServletResponse response = ac.getResponse();
			Cookie usernamecookie;
			usernamecookie = new Cookie(USERNAME, URLEncoder.encode(username,
					"utf-8"));
			usernamecookie.setPath("/");
			response.addCookie(usernamecookie);
			Cookie emailcookie = new Cookie(EMAIL, email);
			emailcookie.setPath("/");
			response.addCookie(emailcookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	protected void setResponse(String json) {
		ActionContext ac = ActionContext.getActionContext();
		HttpServletResponse response = ac.getResponse();
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
