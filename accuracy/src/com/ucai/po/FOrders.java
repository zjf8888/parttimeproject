package com.ucai.po;
/**
 * 机票信息存储类
 * @author lin
 *
 */
public class FOrders {
	private String f_Number;//机票订单号

	private String f_Ordercode;//订单流水号

	private String f_UserId;//会员ID

	private String f_Totalprice;//票面总价

	private String f_Payprice;//实际支付价格

	private String f_ProfitPrice;//利润

	private String f_FlightType;//航程类型.1-单程;2-往返,3-联程

	private String f_Type;//类型 1-国内;2-国际

	private String f_AddDateTime;//订单生成日期

	private String f_PayType;//支付方式(ID)

	private String f_PayTime;//支付时间

	private String f_PayStatus;//支付状态.0-未支付;1-已支付

	private String f_PayRemark;//支付备注

	private String f_TicketNum;//机票张数

	private String f_Operator;//操作人

	private String f_SourceId;//订单来源ID

	private String f_Status;//订单状态  1、新订单，2、待出票... 

	private String f_IsTrue;//订单真假. 0-假订单;1-真订单

	private String f_DeptID;//部门ID

	private String f_OrderRemark;//机票订单备注

	private String f_FuelFees;//燃油总价

	private String f_BuildFees;//机建总价

	private String f_Agentfee;//代理点数

	private String f_CPDate;//出票日期

	private String f_PeisongAddr;//取票方式

	public String getF_PeisongAddr() {
		return f_PeisongAddr;
	}

	public void setF_PeisongAddr(String peisongAddr) {
		f_PeisongAddr = peisongAddr;
	}

	public String getF_AddDateTime() {
		return f_AddDateTime;
	}

	public void setF_AddDateTime(String addDateTime) {
		f_AddDateTime = addDateTime;
	}

	public String getF_Agentfee() {
		return f_Agentfee;
	}

	public void setF_Agentfee(String agentfee) {
		f_Agentfee = agentfee;
	}

	public String getF_BuildFees() {
		return f_BuildFees;
	}

	public void setF_BuildFees(String buildFees) {
		f_BuildFees = buildFees;
	}

	public String getF_CPDate() {
		return f_CPDate;
	}

	public void setF_CPDate(String date) {
		f_CPDate = date;
	}

	public String getF_DeptID() {
		return f_DeptID;
	}

	public void setF_DeptID(String deptID) {
		f_DeptID = deptID;
	}

	public String getF_FlightType() {
		return f_FlightType;
	}

	public void setF_FlightType(String flightType) {
		f_FlightType = flightType;
	}

	public String getF_FuelFees() {
		return f_FuelFees;
	}

	public void setF_FuelFees(String fuelFees) {
		f_FuelFees = fuelFees;
	}

	public String getF_IsTrue() {
		return f_IsTrue;
	}

	public void setF_IsTrue(String isTrue) {
		f_IsTrue = isTrue;
	}

	public String getF_Number() {
		return f_Number;
	}

	public void setF_Number(String number) {
		f_Number = number;
	}

	public String getF_Operator() {
		return f_Operator;
	}

	public void setF_Operator(String operator) {
		f_Operator = operator;
	}

	public String getF_Ordercode() {
		return f_Ordercode;
	}

	public void setF_Ordercode(String ordercode) {
		f_Ordercode = ordercode;
	}

	public String getF_OrderRemark() {
		return f_OrderRemark;
	}

	public void setF_OrderRemark(String orderRemark) {
		f_OrderRemark = orderRemark;
	}

	public String getF_Payprice() {
		return f_Payprice;
	}

	public void setF_Payprice(String payprice) {
		f_Payprice = payprice;
	}

	public String getF_PayRemark() {
		return f_PayRemark;
	}

	public void setF_PayRemark(String payRemark) {
		f_PayRemark = payRemark;
	}

	public String getF_PayStatus() {
		return f_PayStatus;
	}

	public void setF_PayStatus(String payStatus) {
		f_PayStatus = payStatus;
	}

	public String getF_PayTime() {
		return f_PayTime;
	}

	public void setF_PayTime(String payTime) {
		f_PayTime = payTime;
	}

	public String getF_PayType() {
		return f_PayType;
	}

	public void setF_PayType(String payType) {
		f_PayType = payType;
	}

	public String getF_ProfitPrice() {
		return f_ProfitPrice;
	}

	public void setF_ProfitPrice(String profitPrice) {
		f_ProfitPrice = profitPrice;
	}

	public String getF_SourceId() {
		return f_SourceId;
	}

	public void setF_SourceId(String sourceId) {
		f_SourceId = sourceId;
	}

	public String getF_Status() {
		return f_Status;
	}

	public void setF_Status(String status) {
		f_Status = status;
	}

	public String getF_TicketNum() {
		return f_TicketNum;
	}

	public void setF_TicketNum(String ticketNum) {
		f_TicketNum = ticketNum;
	}

	public String getF_Totalprice() {
		return f_Totalprice;
	}

	public void setF_Totalprice(String totalprice) {
		f_Totalprice = totalprice;
	}

	public String getF_Type() {
		return f_Type;
	}

	public void setF_Type(String type) {
		f_Type = type;
	}

	public String getF_UserId() {
		return f_UserId;
	}

	public void setF_UserId(String userId) {
		f_UserId = userId;
	}

}
