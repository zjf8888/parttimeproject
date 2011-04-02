package com.szqb.util;

/**
 * 图片缩小算法，方形区域抽样 by alfred
 * **/
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class NewScaleImage {

	int width = 0;

	int height = 0;

	private String destFile;

	private String srcFileName;

	BufferedImage srcBufferImage;

	private int[] rgbArray = null;

	public NewScaleImage(InputStream file) throws IOException {
		String srcFile = "ok.jpg";
		srcFileName = "ok";
		this.destFile = srcFile.substring(0, srcFile.lastIndexOf(".")) + ".jpg";
		srcBufferImage = getBufferedImage(file);
		width = srcBufferImage.getWidth();
		height = srcBufferImage.getHeight();

	}
	public NewScaleImage(BufferedImage bufim,String name) throws IOException {
		srcFileName=name;
		destFile=name;
		srcBufferImage=bufim;
		width = srcBufferImage.getWidth();
		height = srcBufferImage.getHeight();
	}

	public BufferedImage scaleImage(int outWidth, int outHeight) {

		rgbArray = srcBufferImage.getRGB(0, 0, width, height, null, 0, width);
		if (DetermineResultSize(outWidth, outHeight) == 1) {
			return srcBufferImage;
		}
		BufferedImage pbFinalOut = new BufferedImage(outWidth, outHeight,
				BufferedImage.TYPE_INT_RGB);

		double hScale = ((double) width) / ((double) outWidth);// 宽缩小的倍数
		double vScale = ((double) height) / ((double) outHeight);// 高缩小的倍数

		int winX0, winY0, winX1, winY1;
		int valueRGB = 0;
		long R, G, B;
		int x, y, i, j;
		int n;
		for (y = 0; y < outHeight; y++) {
			winY0 = (int) (y * vScale + 0.5);// 得到原图高的Y坐标
			if (winY0 < 0) {
				winY0 = 0;
			}
			winY1 = (int) (winY0 + vScale + 0.5);
			if (winY1 > height) {
				winY1 = height;
			}
			for (x = 0; x < outWidth; x++) {
				winX0 = (int) (x * hScale + 0.5);
				if (winX0 < 0) {
					winX0 = 0;
				}
				winX1 = (int) (winX0 + hScale + 0.5);
				if (winX1 > width) {
					winX1 = width;
				}
				R = 0;
				G = 0;
				B = 0;
				for (i = winX0; i < winX1; i++) {
					for (j = winY0; j < winY1; j++) {
						valueRGB = rgbArray[width * j + i];
						R += GetRedValue(valueRGB);
						G += GetGreenValue(valueRGB);
						B += GetBlueValue(valueRGB);
					}
				}
				n = (winX1 - winX0) * (winY1 - winY0);
				R = (int) (((double) R) / n + 0.5);
				G = (int) (((double) G) / n + 0.5);
				B = (int) (((double) B) / n + 0.5);
				valueRGB = ComRGB(Clip((int) R), Clip((int) G), Clip((int) B));
				pbFinalOut.setRGB(x, y, valueRGB);
			}
		}
		return pbFinalOut;
	}

	/**
	 * 决定图像尺寸
	 */
	public int DetermineResultSize(int w, int h) {
		double scaleH, scaleV;
		scaleH = (double) w / (double) width;
		scaleV = (double) h / (double) height;
		// 判断一下scaleH，scaleV，不做放大操作
		if (scaleH >= 1.0 && scaleV >= 1.0) {
			return 1;
		}
		return 0;

	} // end of DetermineResultSize()

	int Clip(int x) {
		if (x < 0)
			return 0;
		if (x > 255)
			return 255;
		return x;
	}

	private int GetRedValue(int rgbValue) {
		int temp = rgbValue & 0x00ff0000;
		return temp >> 16;
	}

	private int GetGreenValue(int rgbValue) {
		int temp = rgbValue & 0x0000ff00;
		return temp >> 8;
	}

	private int GetBlueValue(int rgbValue) {
		return rgbValue & 0x000000ff;
	}

	private int ComRGB(int redValue, int greenValue, int blueValue) {

		return (redValue << 16) + (greenValue << 8) + blueValue;
	}

	public void resize(int w, int h) throws IOException {

		width = srcBufferImage.getWidth();
		height = srcBufferImage.getHeight();
		BufferedImage _image = scaleImage(w, h);
		FileOutputStream out = new FileOutputStream(destFile);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(_image);
		out.close();
	}

	public void resizeByWidth(int w) throws IOException {
		float h = ((float) height * w / width);
		resize(w, new Float(h).intValue());
	}

	public void resizeByHeight(int h) throws IOException {
		float w = ((float) width * h / height);
		resize(new Float(w).intValue(), h);
	}

	public void resizeFix(int w, int h) throws IOException {
		if (width <= w && height <= h) {
			resize(width, height);
			return;
		}

		if ((float) width / height > (float) w / h) {
			resizeByWidth(w);
		} else {
			resizeByHeight(h);
		}
	}

	public BufferedImage scaleImgFix(int outW, int outH) {
		if (width <= outW && height <= outH) {
			return scaleImage(width, height);
		}

		if ((float) width / height > (float) outW / outH) {
			return scaleImage(outW, getResizeHeight(outW));

		} else {
			return scaleImage(getResizeWidth(outH), outH);

		}
	}

	// 如果图片的高小于宽,计算缩小后的高度.
	public int getResizeHeight(int w) {
		float h = ((float) height * w / width);
		return new Float(h).intValue();
	}

	// 如果图片的高大于宽,计算缩小后的宽度.
	public int getResizeWidth(int h) {
		float w = ((float) width * h / height);
		return new Float(w).intValue();
	}

	public void setDestFile(String fileName) {
		destFile = fileName;
	}

	public String getDestFile() {
		return destFile;
	}

	public static void main(String[] args) {
	}

	public String getSrcFileName() {
		return srcFileName;
	}

	public void setSrcFileName(String srcFileName) {
		this.srcFileName = srcFileName;
	}
	/**
	 * 把FormFile类型文件转化为BufferedImage图像文件
	 * 
	 * @param image
	 * @return
	 * @throws IOException
	 */
	public BufferedImage getBufferedImage(InputStream image) throws IOException {
		BufferedImage bufferedImage = null;
		if ((image != null)) {
			// load file from disk using Sun's JPEGIMageDecoder
			InputStream in = image;
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(in);
			bufferedImage = decoder.decodeAsBufferedImage();
			in.close();
		}
		return bufferedImage;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
