package com.szqb.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageDeal {
	public static byte[] imageToBytes(BufferedImage bImage, String format) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, format, baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bytesOut = baos.toByteArray();
		return bytesOut;
	}

	public InputStream downloadFile(String url) {
		URL myFileUrl = null;
		try {
			myFileUrl = new URL(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			return is;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
