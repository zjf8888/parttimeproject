package com.ucai.po;

/**
 * 订座响应结构体
 * @author lixu
 *
 */
public class OrderSeatResponse {
	public String code; //错误码值，0时成功,其它失败
	public String info; //错误码提示
	public String pnr;//为null时不成功
	public String flyCompany;
	public String ticketPrice;
	public String tax;
	public String fuel;
	public String price;
}
