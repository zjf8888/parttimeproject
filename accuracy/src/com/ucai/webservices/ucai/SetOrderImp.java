package com.ucai.webservices.ucai;

import com.ucai.www.FlyOrderResponseFlyOrderResult;
import com.ucai.www.SetOrdersSoapProxy;
public class SetOrderImp {

	public String FlyOrder(String orderxml) {
		
		try {
			SetOrdersSoapProxy SetOrdersSoapProxy=new SetOrdersSoapProxy();
			orderxml=new String(orderxml.getBytes( "UTF-8 "), "UTF-8 ");
			FlyOrderResponseFlyOrderResult FlyOrderResponseFlyOrderResult=SetOrdersSoapProxy.flyOrder(orderxml);
			
			System.out.println(FlyOrderResponseFlyOrderResult.toString());
			return FlyOrderResponseFlyOrderResult.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
