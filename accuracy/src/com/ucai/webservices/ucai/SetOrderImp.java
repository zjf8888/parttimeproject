package com.ucai.webservices.ucai;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import javax.xml.namespace.QName;

public class SetOrderImp {

	public byte[] FlyOrder(String orderxml) {
		
		String endpoint = "http://www.ucai.com/FlyWebService/SetOrders.asmx?WSDL";
		try {
			Service service = new Service();
			Call call = (Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName(new QName("http://www.ucai.com","FlyOrder"));
			call.addParameter("name",org.apache.axis.encoding.XMLType.XSD_DATE,javax.xml.rpc.ParameterMode.IN);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI("http://www.ucai.com/FlyOrder");
			byte[] bytexml =(byte[])call.invoke(new Object[]{orderxml});
			System.out.println(bytexml.length);
			return bytexml;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
