package com.ucai.webservices.ucai;

public interface ISetOrderService {
	public byte[] FlyOrder(String orderxml);

	public byte[] UPSOrder(String fOrders, String state, String payDoc);

	public byte[] USOrder(String OrderStatusXml);
}
