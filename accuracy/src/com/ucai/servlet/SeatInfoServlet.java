package com.ucai.servlet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SeatInfoServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		InputStream inputStream = request.getInputStream();

		java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
		byte[] buffer = new byte[64 * 1024];
		for (;;) {
			int count = inputStream.read(buffer);
			if (count < 0)
				break;
			os.write(buffer, 0, count);
		}
		byte[] a = os.toByteArray();
		
		String xml = getUTFStr(a);;
		System.out.println(xml);
		PrintWriter pw = response.getWriter();
		pw.write(new String(a));
		pw.flush();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private String getUTFStr(byte[] utfbytes) {

		int rdlen = utfbytes.length;

		byte abyte2[] = new byte[rdlen + 2];
		abyte2[0] = (byte) (rdlen >> 8);
		abyte2[1] = (byte) rdlen;
		System.arraycopy(utfbytes, 0, abyte2, 2, rdlen);
		try {
			ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(
					abyte2);
			DataInputStream datainputstream = new DataInputStream(
					bytearrayinputstream);

			String utfstr = datainputstream.readUTF();
			bytearrayinputstream = null;
			datainputstream = null;
			return utfstr;
		} catch (IOException ioe) {
			return null;
		}
	}
}
