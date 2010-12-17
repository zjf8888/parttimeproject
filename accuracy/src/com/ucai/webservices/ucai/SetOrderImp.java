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
			// 这是由于jdom对命名对象中有下行线的bug的修复.
			orderxml = orderxml.replace("__", "_");
			return callService(orderxml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 调用远程扣位方法
	 * 
	 * @param orderxml
	 * @return
	 */
	private String callService(String orderxml) {
		try {
			String service_url = "http://www.ucai.com/FlyWebService/SetOrders.asmx?WSDL";
			Service service = new Service();
			Call call = (Call) service.createCall();
			// 设置地址
			call.setTargetEndpointAddress(new java.net.URL(service_url));
			// 设置SOAPAction
			call.setSOAPActionURI("http://www.ucai.com/FlyOrder");
			// 设置设用的方法名
			call.setOperationName(new QName("http://www.ucai.com", "FlyOrder"));
			// 该方法需要的参数
			call.addParameter(new QName("http://www.ucai.com", "orderxml"),
					XMLType.XSD_STRING, ParameterMode.IN);
			// 设置返回类型
			call
					.setReturnType(new javax.xml.namespace.QName(
							"http://www.ucai.com",
							">>FlyOrderResponse>FlyOrderResult"));
			// 设置返回对象
			call
					.setReturnClass(com.ucai.www.FlyOrderResponseFlyOrderResult.class);
			// 设置返回命名空间
			call.setReturnQName(new javax.xml.namespace.QName(
					"http://www.ucai.com", "FlyOrderResult"));
			// 调用远程方法
			FlyOrderResponseFlyOrderResult FlyOrderResponseFlyOrderResult = (FlyOrderResponseFlyOrderResult) call
					.invoke(new Object[] { orderxml });
			// 获取相应的数据
			MessageElement[] me = FlyOrderResponseFlyOrderResult.get_any();
			// 把获取的数据组装成对象
			JDResInfo po = ReturnXml2Po.getJDResInfoFromXml(me[0].toString());
			System.out.println(po.getCode());
			return po.getCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
