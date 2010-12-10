package com.ucai.tool;

import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.ucai.po.JDResInfo;
import com.ucai.po.ReturnPo;

/**
 * 返回信息打包类
 * 
 * @author lin
 * 
 */
public class ReturnXml2Po {
	/**
	 * 扣位返回信息打包对象处理方示
	 * 
	 * @param xml
	 * @return
	 */
	public static ReturnPo getReturnPo(String xml) {
		ReturnPo po = new ReturnPo();
		try {
			StringReader sr = new StringReader(xml);
			SAXBuilder builder = new SAXBuilder(false);
			Document doc = builder.build(sr);
			Element returnxml = doc.getRootElement();
			// 获取状态码
			String code = returnxml.getChildTextTrim("code");
			po.setCode(code);
			String info = returnxml.getChildTextTrim("info");
			po.setInfo(info);
			String pnr = returnxml.getChildTextTrim("pnr");
			po.setPnr(pnr);
			String FlyConpany = returnxml.getChildTextTrim("FlyConpany");
			po.setFlyConpany(FlyConpany);
			String ticketPrice = returnxml.getChildTextTrim("ticketPrice");
			po.setTicketPrice(ticketPrice);
			String tax = returnxml.getChildTextTrim("tax");
			po.setTax(tax);
			String fuel = returnxml.getChildTextTrim("fuel");
			po.setFuel(fuel);
			String price = returnxml.getChildTextTrim("price");
			po.setPrice(price);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return po;
	}

	/**
	 * 下订单返回信息打包类
	 * 
	 * @param xml
	 * @return
	 */
	public static JDResInfo getJDResInfoFromXml(String xml) {
		JDResInfo po = new JDResInfo();
		try {
			StringReader sr = new StringReader(xml);
			SAXBuilder builder = new SAXBuilder(false);
			Document doc = builder.build(sr);
			Element returnxml = doc.getRootElement();
			Element ResInfo = returnxml.getChild("ResInfo");
			String code = ResInfo.getChildTextTrim("code");
			String description = ResInfo.getChildTextTrim("description");
			po.setCode(code);
			po.setDescription(description);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return po;
	}
}
