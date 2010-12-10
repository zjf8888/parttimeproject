package com.ucai.po;

/**
 * 扣位乘客信息类
 * 
 * @author lin
 * 
 */
public class Passenger2 {
	private String p_Name;// 乘客姓名

	private String p_TypeID;// 乘客类型ID

	private String p_CardType;// 证件类型ID

	private String p_CardNo;// 证件号码

	private String p_InsurType;// 保险类型。0-送;1-买

	private String p_InsurBuyNumber;// 购买保险数量

	private String p_InsurZSNumber;// 赠送保险数量

	private String p_TicketNo;// 票号

	private String p_CPDate;// 出票时间

	private String p_Status;// 票状态 票状态 1正常状态 2申请退3申请废4申请改签5申请升仓
							// 12驳回退13驳回废14驳回改签15驳回升仓 22同意退23同意废24同意改签25同意升仓

	private String p_TicketPrice;// 票价

	private String p_RTicketFee;// 实收票价

	private String p_BuildFee;// 机建费

	private String p_FuelFee;// 燃油费

	private String p_TicketState;// 机票状态

	public String getP_BuildFee() {
		return p_BuildFee;
	}

	public void setP_BuildFee(String buildFee) {
		p_BuildFee = buildFee;
	}

	public String getP_CardNo() {
		return p_CardNo;
	}

	public void setP_CardNo(String cardNo) {
		p_CardNo = cardNo;
	}

	public String getP_CardType() {
		return p_CardType;
	}

	public void setP_CardType(String cardType) {
		p_CardType = cardType;
	}

	public String getP_CPDate() {
		return p_CPDate;
	}

	public void setP_CPDate(String date) {
		p_CPDate = date;
	}

	public String getP_FuelFee() {
		return p_FuelFee;
	}

	public void setP_FuelFee(String fuelFee) {
		p_FuelFee = fuelFee;
	}

	public String getP_InsurBuyNumber() {
		return p_InsurBuyNumber;
	}

	public void setP_InsurBuyNumber(String insurBuyNumber) {
		p_InsurBuyNumber = insurBuyNumber;
	}

	public String getP_InsurType() {
		return p_InsurType;
	}

	public void setP_InsurType(String insurType) {
		p_InsurType = insurType;
	}

	public String getP_InsurZSNumber() {
		return p_InsurZSNumber;
	}

	public void setP_InsurZSNumber(String insurZSNumber) {
		p_InsurZSNumber = insurZSNumber;
	}

	public String getP_Name() {
		return p_Name;
	}

	public void setP_Name(String name) {
		p_Name = name;
	}

	public String getP_RTicketFee() {
		return p_RTicketFee;
	}

	public void setP_RTicketFee(String ticketFee) {
		p_RTicketFee = ticketFee;
	}

	public String getP_Status() {
		return p_Status;
	}

	public void setP_Status(String status) {
		p_Status = status;
	}

	public String getP_TicketNo() {
		return p_TicketNo;
	}

	public void setP_TicketNo(String ticketNo) {
		p_TicketNo = ticketNo;
	}

	public String getP_TicketPrice() {
		return p_TicketPrice;
	}

	public void setP_TicketPrice(String ticketPrice) {
		p_TicketPrice = ticketPrice;
	}

	public String getP_TicketState() {
		return p_TicketState;
	}

	public void setP_TicketState(String ticketState) {
		p_TicketState = ticketState;
	}

	public String getP_TypeID() {
		return p_TypeID;
	}

	public void setP_TypeID(String typeID) {
		p_TypeID = typeID;
	}
}
