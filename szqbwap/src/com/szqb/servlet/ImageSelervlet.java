package com.szqb.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import com.szqb.bean.Content;
import com.szqb.web.IndexBean;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class ImageSelervlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "image/jpeg";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * 如果type为1时,获取的是大图,否则为小图
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		String id;
		String type;
		ServletOutputStream out = response.getOutputStream();
		try {
			id = request.getParameter("id");
			type=request.getParameter("list");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		try {
			byte buf[] = this.getImage(id,type);
			out.write(buf);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private byte[] getImage(String id,String type) {		
		try {
			Map<String, Content> contentList=IndexBean.getContentList();
			Content co=contentList.get(id);
			ArrayList<byte[]> imageList=co.getImageList();
			byte[] image=imageList.get(new Integer(type));
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void destroy() {
	}
}