package com.ucai.tool;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.ucai.po.JDResInfo;
import com.ucai.po.ResultOrder;
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

	/**
	 * 把xml转化成订单查询结果对象
	 * 
	 * @param xml
	 * @return
	 */
	public static List<ResultOrder> getFlyOrderList(String xml) {
		List<ResultOrder> list = new ArrayList<ResultOrder>();
		try {
			StringReader sr = new StringReader(xml);
			SAXBuilder builder = new SAXBuilder(false);
			Document doc = builder.build(sr);
			Element returnxml = doc.getRootElement();
			Element Orders = returnxml.getChild("Orders");
			List OrderList = Orders.getChildren("Order");
			for (Iterator iter = OrderList.iterator(); iter.hasNext();) {
				Element Order = (Element) iter.next();
				ResultOrder po = new ResultOrder();
				po.setF_Id(Order.getChildTextTrim("f_Id"));
				po.setF_Number(Order.getChildTextTrim("f_Number"));
				po.setF_Type(Order.getChildTextTrim("f_Type"));
				po.setF_PayType(Order.getChildTextTrim("f_PayType"));
				po.setF_PayStatus(Order.getChildTextTrim("f_PayStatus"));
				po.setA_FlyNo(Order.getChildTextTrim("a_FlyNo"));
				po.setA_Pnr(Order.getChildTextTrim("a_Pnr"));
				po.setP_Name(Order.getChildTextTrim("p_Name"));
				po.setP_TicketNo(Order.getChildTextTrim("p_TicketNo"));
				po.setL_Name(Order.getChildTextTrim("l_Name"));
				po.setL_Mobile(Order.getChildTextTrim("l_Mobile"));
				po.setF_SourceId(Order.getChildTextTrim("f_SourceId"));
				po.setL_UserId(Order.getChildTextTrim("l_UserId"));
				po.setA_Scity(Order.getChildTextTrim("a_Scity"));
				po.setA_Ecity(Order.getChildTextTrim("a_Ecity"));
				po.setF_AddDateTime(Order.getChildTextTrim("f_AddDateTime"));
				po.setTotalPrice(Order.getChildTextTrim("TotalPrice"));
				po.setA_FlyDate(Order.getChildTextTrim("a_FlyDate"));
				list.add(po);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
