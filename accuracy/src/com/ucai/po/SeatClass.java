package com.ucai.po;

/**
 * 仓位信息
 * 
 * @author lixu
 * 
 */
public class SeatClass {
	/**
	 * 座位等级名称
	 */
	public String classname; 
	/**
	 * 剩余的座位数，大于等于9时为A
	 */
	public String num; 
	/**
	 * 价格
	 */
	public String saleprice; 
	/**
	 * 座位等级码
	 */
	public String classcode; 
	/**
	 * 建设费
	 */
	public String buildfee; 
	/**
	 * 燃油费
	 */
	public String fuelfee; 
	/**
	 * 可否申请电子票
	 */
	public String isApply; 

	/**
	 * 办理说明
	 */
	
	public String discount;
	/**
	 * 退票说明
	 */
	public String refund; 
	/**
	 * 不得签转免费变更
	 */
	public String cmt; 
	/**
	 * 见舱销售，随订随售
	 */
	public String sale; 

	public String getBuildfee() {
		return buildfee;
	}

	public void setBuildfee(String buildfee) {
		this.buildfee = buildfee;
	}

	public String getClasscode() {
		return classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getCmt() {
		return cmt;
	}

	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getFuelfee() {
		return fuelfee;
	}

	public void setFuelfee(String fuelfee) {
		this.fuelfee = fuelfee;
	}

	public String getIsApply() {
		return isApply;
	}

	public void setIsApply(String isApply) {
		this.isApply = isApply;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}
}
