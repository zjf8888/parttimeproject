package com.ucai.po;

/**
 * 订单查询结果
 * 
 * @author lin
 * 
 */
public class ResultOrder {
	/**
	 * 订单ID
	 */
	private String f_Id;
	/**
	 * 订单号
	 */
	private String f_Number;
	/**
	 * 机票类型（国内或国际）
	 */
	private String f_Type;
	/**
	 * 支付方式
	 */
	private String f_PayType;
	/**
	 * 支付状态
	 */
	private String f_PayStatus;
	/**
	 * 航班号
	 */
	private String a_FlyNo;
	/**
	 * PNR
	 */
	private String a_Pnr;
	/**
	 * 乘客姓名
	 */
	private String p_Name;
	/**
	 * 票号
	 */
	private String p_TicketNo;
	/**
	 * 联系人名称
	 */
	private String l_Name;
	/**
	 * 联系人电话号码
	 */
	private String l_Mobile;
	/**
	 * 订单来源
	 */
	private String f_SourceId;
	/**
	 * 会员ID
	 */
	private String l_UserId;
	/**
	 * 出发城市三字码 接口中出来的数据已经转为城市名称
	 */
	private String a_Scity;
	/**
	 * 目标城市三字码
	 */
	private String a_Ecity;
	/**
	 * 下订单时间
	 */
	private String f_AddDateTime;
	/**
	 * 订单总价
	 */
	private String TotalPrice;
	/**
	 * 起飞日期
	 */
	private String a_FlyDate;

	public String getA_Ecity() {
		return a_Ecity;
	}

	public void setA_Ecity(String ecity) {
		a_Ecity = ecity;
	}

	public String getA_FlyDate() {
		return a_FlyDate;
	}

	public void setA_FlyDate(String flyDate) {
		a_FlyDate = flyDate;
	}

	public String getA_FlyNo() {
		return a_FlyNo;
	}

	public void setA_FlyNo(String flyNo) {
		a_FlyNo = flyNo;
	}

	public String getA_Pnr() {
		return a_Pnr;
	}

	public void setA_Pnr(String pnr) {
		a_Pnr = pnr;
	}

	public String getA_Scity() {
		return a_Scity;
	}

	public void setA_Scity(String scity) {
		a_Scity = scity;
	}

	public String getF_AddDateTime() {
		return f_AddDateTime;
	}

	public void setF_AddDateTime(String addDateTime) {
		f_AddDateTime = addDateTime;
	}

	public String getF_Id() {
		return f_Id;
	}

	public void setF_Id(String id) {
		f_Id = id;
	}

	public String getF_Number() {
		return f_Number;
	}

	public void setF_Number(String number) {
		f_Number = number;
	}

	public String getF_PayStatus() {
		return f_PayStatus;
	}

	public void setF_PayStatus(String payStatus) {
		f_PayStatus = payStatus;
	}

	public String getF_PayType() {
		return f_PayType;
	}

	public void setF_PayType(String payType) {
		f_PayType = payType;
	}

	public String getF_SourceId() {
		return f_SourceId;
	}

	public void setF_SourceId(String sourceId) {
		f_SourceId = sourceId;
	}

	public String getF_Type() {
		return f_Type;
	}

	public void setF_Type(String type) {
		f_Type = type;
	}

	public String getL_Mobile() {
		return l_Mobile;
	}

	public void setL_Mobile(String mobile) {
		l_Mobile = mobile;
	}

	public String getL_Name() {
		return l_Name;
	}

	public void setL_Name(String name) {
		l_Name = name;
	}

	public String getL_UserId() {
		return l_UserId;
	}

	public void setL_UserId(String userId) {
		l_UserId = userId;
	}

	public String getP_Name() {
		return p_Name;
	}

	public void setP_Name(String name) {
		p_Name = name;
	}

	public String getP_TicketNo() {
		return p_TicketNo;
	}

	public void setP_TicketNo(String ticketNo) {
		p_TicketNo = ticketNo;
	}

	public String getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}
}
