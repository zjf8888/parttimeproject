package com.ucai.po;

/**
 * 订座响应结构体
 * @author lixu
 *
 */
public class OrderSeatResponse {
	/**
	 * 错误码值，0时成功,其它失败
	 */
	public String code; 
	/**
	 * 错误码提示
	 */
	public String info; 
	/**
	 * 为null时不成功
	 */
	public String pnr;
	/**
	 * 航空公司
	 */
	public String flyCompany;
	/**
	 * 机票价格
	 */
	public String ticketPrice;
	/**
	 * 燃油税
	 */
	public String tax;
	/**
	 * 机场建设费
	 */
	public String fuel;
	/**
	 * 总价
	 */
	public String price;
}
