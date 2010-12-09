package com.ucai.webservices.ucai;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.message.MessageElement;

import com.ucai.po.JDResInfo;
import com.ucai.tool.ReturnXml2Po;
import com.ucai.www.FlyOrderResponseFlyOrderResult;


public class SetOrderImp {

	public String FlyOrder(String orderxml) {

		try {
			orderxml = orderxml.replace("__", "_");
			return callService(orderxml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String callService(String orderxml) {
		try {
			String service_url = "http://www.ucai.com/FlyWebService/SetOrders.asmx?WSDL";
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(service_url));
			call.setSOAPActionURI("http://www.ucai.com/FlyOrder");
			call.setOperationName(new QName("http://www.ucai.com", "FlyOrder"));
			// 该方法需要的参数
			call.addParameter(new QName("http://www.ucai.com", "orderxml"),
					XMLType.XSD_STRING, ParameterMode.IN);
			call
					.setReturnType(new javax.xml.namespace.QName(
							"http://www.ucai.com",
							">>FlyOrderResponse>FlyOrderResult"));
			call
					.setReturnClass(com.ucai.www.FlyOrderResponseFlyOrderResult.class);
			call.setReturnQName(new javax.xml.namespace.QName(
					"http://www.ucai.com", "FlyOrderResult"));
			FlyOrderResponseFlyOrderResult FlyOrderResponseFlyOrderResult = (FlyOrderResponseFlyOrderResult) call
					.invoke(new Object[] { orderxml });
			MessageElement[] me = FlyOrderResponseFlyOrderResult.get_any();
			JDResInfo po = ReturnXml2Po.getJDResInfoFromXml(me[0].toString());
			System.out.println(po.getCode());
			return po.getCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
