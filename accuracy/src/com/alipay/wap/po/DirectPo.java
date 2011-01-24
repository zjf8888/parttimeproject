package com.alipay.wap.po;

/**
 * 交易创建信息对象
 * 
 * @author 李卓林
 * 
 */
public class DirectPo {
	private String subject;// 商品名称

	private String out_trade_no;// 外部交易号

	private String total_fee;// 订单价格

	private String seller_account_name;// 卖家帐号

	private String buyer_account_name;// 买家帐号

	private String notify_url;// 接受通知的url

	private String out_user;// 系统用户唯一标识

	private String merchant_url;// 返回商户链接,我们系统为空

	private String pay_expire;// 交易自动关闭时间

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getSeller_account_name() {
		return seller_account_name;
	}

	public void setSeller_account_name(String seller_account_name) {
		this.seller_account_name = seller_account_name;
	}

	public String getBuyer_account_name() {
		return buyer_account_name;
	}

	public void setBuyer_account_name(String buyer_account_name) {
		this.buyer_account_name = buyer_account_name;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getOut_user() {
		return out_user;
	}

	public void setOut_user(String out_user) {
		this.out_user = out_user;
	}

	public String getMerchant_url() {
		return merchant_url;
	}

	public void setMerchant_url(String merchant_url) {
		this.merchant_url = merchant_url;
	}

	public String getPay_expire() {
		return pay_expire;
	}

	public void setPay_expire(String pay_expire) {
		this.pay_expire = pay_expire;
	}

}
