package com.ucai.webservices.ucai;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.ucai.www.FlyOrderResponseFlyOrderResult;
import com.ucai.www.SetOrdersSoapProxy;

import javax.xml.namespace.QName;

public class SetOrderImp {

	public String FlyOrder(String orderxml) {
		
		
		try {
			SetOrdersSoapProxy SetOrdersSoapProxy=new SetOrdersSoapProxy();
			FlyOrderResponseFlyOrderResult FlyOrderResponseFlyOrderResult=SetOrdersSoapProxy.flyOrder(orderxml);
			
			System.out.println(FlyOrderResponseFlyOrderResult.toString());
			return FlyOrderResponseFlyOrderResult.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
