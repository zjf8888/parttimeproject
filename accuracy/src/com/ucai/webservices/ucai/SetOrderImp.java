package com.ucai.webservices.ucai;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.MessageElement;

import com.ucai.po.JDResInfo;
import com.ucai.tool.ReturnXml2Po;
import com.ucai.www.FlyOrderResponseFlyOrderResult;
import com.ucai.www.SetOrdersLocator;
import com.ucai.www.SetOrdersSoapStub;

public class SetOrderImp {

	public String FlyOrder(String orderxml) {

		try {
			SetOrdersLocator service = new SetOrdersLocator();
			SetOrdersSoapStub SetOrdersSoapProxy = (SetOrdersSoapStub) service
					.getSetOrdersSoap();

			orderxml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" + orderxml;
			System.out.println(orderxml);
			FlyOrderResponseFlyOrderResult FlyOrderResponseFlyOrderResult = SetOrdersSoapProxy
					.flyOrder(abc);
			MessageElement[] me = FlyOrderResponseFlyOrderResult.get_any();
			JDResInfo po = ReturnXml2Po.getJDResInfoFromXml(me[0].toString());
			System.out.println(callService(abc));
			return FlyOrderResponseFlyOrderResult.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	String abc = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><Orders>  <order>    <order_NO>Z20101118025214214</order_NO>    <TotalPrice>4780</TotalPrice>    <orderdate>2010-11-18 14:52:16</orderdate>    <userId>17</userId>    <aserialnumber>Z201010018</aserialnumber>  </order>  <FOrders>    <f_Number>F20101118025214214</f_Number>    <f_Ordercode>F201010011</f_Ordercode>    <f_UserId>17</f_UserId>    <f_Totalprice>4220</f_Totalprice>    <f_ProfitPrice>0</f_ProfitPrice>    <f_FlightType>2</f_FlightType>    <f_Type>1</f_Type>    <f_AddDateTime>2010-11-18 14:52:16</f_AddDateTime>    <f_PayType>1</f_PayType>    <f_PayTime>1900-01-01</f_PayTime>    <f_PayStatus>0</f_PayStatus>    <f_PayRemark></f_PayRemark>    <f_TicketNum>2</f_TicketNum>    <f_Operator></f_Operator>    <f_SourceId>1</f_SourceId>    <f_Status>1</f_Status>    <f_IsTrue>1</f_IsTrue>    <f_DeptID>0</f_DeptID>    <f_OrderRemark></f_OrderRemark>    <f_FuelFees>280</f_FuelFees>    <f_BuildFees>200</f_BuildFees>    <f_Agentfee>3</f_Agentfee>    <f_CPDate>1900-01-01</f_CPDate>    <f_Payprice>4780</f_Payprice>  </FOrders>  <AirOrders>    <airOrder>      <a_Company>CA</a_Company>      <a_FlyNo>CA3311</a_FlyNo>      <a_Scity>PEK</a_Scity>      <a_Ecity>SZX</a_Ecity>      <a_Class>L</a_Class>      <a_FlySTime>08:10</a_FlySTime>      <a_FlyETime>11:20</a_FlyETime>      <a_FlyDate>2010-11-29</a_FlyDate>      <a_PlaneType>739</a_PlaneType>      <a_Pnr>MECC8</a_Pnr>      <a_PnrState>HK</a_PnrState>      <a_ClassPrice>1230</a_ClassPrice>      <a_BuildFee>50</a_BuildFee>      <a_FuelFee>70</a_FuelFee>    </airOrder>    <airOrder>      <a_Company>CA</a_Company>      <a_FlyNo>CA3310</a_FlyNo>      <a_Scity>SZX</a_Scity>      <a_Ecity>PEK</a_Ecity>      <a_Class>G</a_Class>      <a_FlySTime>07:55</a_FlySTime>      <a_FlyETime>10:55</a_FlyETime>      <a_FlyDate>2010-11-30</a_FlyDate>      <a_PlaneType>739</a_PlaneType>      <a_Pnr>MECC8</a_Pnr>      <a_PnrState>HK</a_PnrState>      <a_ClassPrice>880</a_ClassPrice>      <a_BuildFee>50</a_BuildFee>      <a_FuelFee>70</a_FuelFee>    </airOrder>  </AirOrders>  <Passengers>    <passenger>      <p_Name>测试</p_Name>      <p_TypeID>1</p_TypeID>      <p_CardType>1</p_CardType>      <p_CardNo>0123456789</p_CardNo>      <p_InsurType>1</p_InsurType>      <p_InsurBuyNumber>2</p_InsurBuyNumber>      <p_InsurZSNumber>0</p_InsurZSNumber>      <p_TicketNo></p_TicketNo>      <p_CPDate></p_CPDate>      <p_Status>1</p_Status>      <p_TicketPrice>2110</p_TicketPrice>      <p_RTicketFee>2110</p_RTicketFee>      <p_BuildFee>100</p_BuildFee>      <p_FuelFee>140</p_FuelFee>      <p_TicketState>1</p_TicketState>    </passenger>    <passenger>      <p_Name>测试1</p_Name>      <p_TypeID>1</p_TypeID>      <p_CardType>1</p_CardType>      <p_CardNo>0123456789</p_CardNo>      <p_InsurType>1</p_InsurType>      <p_InsurBuyNumber>2</p_InsurBuyNumber>      <p_InsurZSNumber>0</p_InsurZSNumber>      <p_TicketNo></p_TicketNo>      <p_CPDate></p_CPDate>      <p_Status>1</p_Status>      <p_TicketPrice>2110</p_TicketPrice>      <p_RTicketFee>2110</p_RTicketFee>      <p_BuildFee>100</p_BuildFee>      <p_FuelFee>140</p_FuelFee>      <p_TicketState>1</p_TicketState>    </passenger>  </Passengers>  <LinkMan>    <l_Name>侯左锋</l_Name>    <l_Mobile>15989547917</l_Mobile>    <l_Phone></l_Phone>    <l_Email>faith0308@163.com</l_Email>    <l_Address></l_Address>    <l_UserId>17</l_UserId>    <l_Remark></l_Remark>  </LinkMan></Orders>";

	private String callService(String orderxml) {
		try {
			String service_url = "http://www.ucai.com/FlyWebService/SetOrders.asmx?WSDL";
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(service_url));
			call.setSOAPActionURI("http://www.ucai.com/FlyOrder");
			call.setOperationName(new QName("http://www.ucai.com/FlyOrder",
					"FlyOrder"));
			// 该方法需要的参数
			call.addParameter("orderxml",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);

			call.setReturnType(new javax.xml.namespace.QName("http://www.ucai.com/FlyOrder", ">>FlyOrderResponse>FlyOrderResult"));
	        call.setReturnClass(com.ucai.www.FlyOrderResponseFlyOrderResult.class);
	        call.setReturnQName(new javax.xml.namespace.QName("http://www.ucai.com/FlyOrder", "FlyOrderResult"));
			// call.setUseSOAPAction(true);
			// //call.setSOAPActionURI("http://intelink.net/GetStrByJobno");
			// 调用该方法, new Object[] { CustNo, passwd, Jobno}为参数列表
	        FlyOrderResponseFlyOrderResult FlyOrderResponseFlyOrderResult = (FlyOrderResponseFlyOrderResult) call.invoke(new Object[] { orderxml });
	        MessageElement[] me = FlyOrderResponseFlyOrderResult.get_any();
			JDResInfo po = ReturnXml2Po.getJDResInfoFromXml(me[0].toString());
			System.out.println(po.getCode());
			return "abc";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getUTFStr(byte[] utfbytes) {

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
