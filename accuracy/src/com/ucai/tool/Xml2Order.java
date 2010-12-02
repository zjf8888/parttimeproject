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

import com.ucai.po.Contact;
import com.ucai.po.Flight;
import com.ucai.po.FlyAir;
import com.ucai.po.FlyOrder;
import com.ucai.po.Passenger;
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

			List<FlyAir> flyAirArrayList = new ArrayList<FlyAir>();
			Element flyAirs = flight.getChild("flyAirs");
			List flyAirList = flyAirs.getChildren("flyAir");

			for (Iterator iter = flyAirList.iterator(); iter.hasNext();) {
				Element flyAirelement = (Element) iter.next();
				FlyAir flyAir = new FlyAir();
				// 航班号
				String flyNo = flyAirelement.getChildTextTrim("flyNo");
				flyAir.setFlyNo(flyNo);
				// 舱位等级
				String flyClass = flyAirelement.getChildTextTrim("flyClass");
				flyAir.setFlyClass(flyClass);
				// 票价
				String flyPrice = flyAirelement.getChildTextTrim("flyPrice");
				flyAir.setFlyPrice(flyPrice);
				// 机建
				String buildfee = flyAirelement.getChildTextTrim("buildfee");
				flyAir.setBuildfee(buildfee);
				// 燃油
				String fuelfee = flyAirelement.getChildTextTrim("fuelfee");
				flyAir.setFuelfee(fuelfee);
				// 机型
				String planesty = flyAirelement.getChildTextTrim("planesty");
				flyAir.setPlanesty(planesty);
				// 出发城市三字码
				String sc = flyAirelement.getChildTextTrim("sc");
				flyAir.setSc(sc);
				// 目标城市三字码
				String ec = flyAirelement.getChildTextTrim("ec");
				flyAir.setEc(ec);
				// 起飞日期
				String sDate = flyAirelement.getChildTextTrim("sDate");
				flyAir.setsDate(sDate);
				// 抵达日期
				String eDate = flyAirelement.getChildTextTrim("eDate");
				flyAir.seteDate(eDate);
				// 起飞时间
				String sTime = flyAirelement.getChildTextTrim("sTime");
				flyAir.setsTime(sTime);
				//抵达时间
				String eTime=flyAirelement.getChildTextTrim("eTime");
				flyAir.seteTime(eTime);
				flyAirArrayList.add(flyAir);
				
			}
			flyOrder.setFlyAirs(flyAirArrayList);
			
			
			
			List<Passenger> passengerArrayList = new ArrayList<Passenger>();
			Element passengers = flight.getChild("passengers");
			List passengersList = flyAirs.getChildren("passenger");

			for (Iterator iter = passengersList.iterator(); iter.hasNext();) {
				Element passengerelement = (Element) iter.next();
				Passenger passenger = new Passenger();
				// 乘客姓名   一定是简体中文，不包含生僻字 英文名 的格式 first name/ last name
				String pasName = passengerelement.getChildTextTrim("pasName");
				passenger.setPasName(pasName);
				// 乘客类型 1/成人、2/儿童、3/婴儿
				String pasType = passengerelement.getChildTextTrim("pasType");
				passenger.setPasType(pasType);
				// 如果乘客类型3/婴儿时填写 出生日期
				String pasYE = passengerelement.getChildTextTrim("pasYE");
				passenger.setPasYE(pasYE);
				// 证件类型 1、身份证，2、港澳通行证，3、护照，4、军官证，5、台胞证，6、国际海员证，7、回乡证，8、其他
				String pasBirthday = passengerelement.getChildTextTrim("pasBirthday");
				passenger.setPasBirthday(pasBirthday);				
				// 证件号码
				String pasBirthNo = passengerelement.getChildTextTrim("pasBirthNo");
				passenger.setPasBirthNo(pasBirthNo);
				// 保险数量
				String insurance_num = passengerelement.getChildTextTrim("insurance_num");
				passenger.setInsurance_num(insurance_num);
				
				passengerArrayList.add(passenger);
				
			}
			flyOrder.setPassengers(passengerArrayList);	
			
			Element contact = flight.getChild("contact");
			Contact contactpo=new Contact();
			// 姓名
			String conName = contact.getChildTextTrim("conName");
			contactpo.setConName(conName);				
			// 固定电话
			String conTel = contact.getChildTextTrim("contact");
			contactpo.setConTel(conTel);
			// 移动电话
			String conMobile = contact.getChildTextTrim("conMobile");
			contactpo.setConMobile(conMobile);
			// 电子邮件
			String conEmail = contact.getChildTextTrim("conEmail");
			contactpo.setConEmail(conEmail);
			// 联系地址
			String conAddress = contact.getChildTextTrim("conAddress");
			contactpo.setConAddress(conAddress); 
			// 配送地址
			String psAddress = contact.getChildTextTrim("psAddress");
			contactpo.setPsAddress(psAddress); 
			flyOrder.setContact(contactpo);
			return flyOrder;
		} catch (Exception e) {
			e.printStackTrace();

			return flyOrder;
		}
	}
}
