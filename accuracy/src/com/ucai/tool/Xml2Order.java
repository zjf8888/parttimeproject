package com.ucai.tool;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.ucai.po.Flight;
import com.ucai.po.FlyOrder;
import com.ucai.po.SeatClass;
import com.ucai.po.Segment;

public class Xml2Order {
	/**
	 * 解释对像
	 * 
	 * @param xml
	 * @return
	 */
	private static FlyOrder xml2Seat(String xml) {
		FlyOrder flyOrder = new FlyOrder();
		flyOrder.setClientId("SZX540");// 预设值--深航编号
		flyOrder.setJDName("JD");// 标识为精度天下
		Calendar calendar = Calendar.getInstance();
		flyOrder.setFOrder("F" + calendar.getTime().getTime());
		flyOrder.setOrderDate(calendar.get(Calendar.YEAR) + "-"
				+ calendar.get(Calendar.MONTH) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH));
		StringReader sr = new StringReader(xml);
		SAXBuilder builder = new SAXBuilder(false);
		try {
			Document doc = builder.build(sr);
			Element flight = doc.getRootElement();
			// 获取错误编码
			Element errinfo = flight.getChild("errinfo");
			String code = errinfo.getChildTextTrim("code");
			return flyOrder;
		} catch (Exception e) {
			e.printStackTrace();
			
			return flyOrder;
		}
	}
}
