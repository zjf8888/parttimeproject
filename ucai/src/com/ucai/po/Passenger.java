package com.ucai.po;

/**
 * 乘客
 * 
 * @author lixu
 * 
 */
public class Passenger {
	/**
	 *  乘客姓名 一定是简体中文，不包含生僻字 英文名 的格式 first name/ last name
	 */
	public String pasName;

	/**
	 *  乘客类型 1/成人、2/儿童、3/婴儿
	 */
	public String pasType;

	/**
	 *  如果乘客类型3/婴儿时填写 出生日期
	 */
	public String pasYE;

	/**
	 *  证件类型 1、身份证，2、港澳通行证，3、护照，4、军官证，5、台胞证，6、国际海员证，7、回乡证，8、其他
	 */
	public String pasBirthday;

	/**
	 *  证件号码
	 */
	public String pasBirthNo;

	/**
	 *  保险数量
	 */
	public String insurance_num;
	/**
	 * 保险价格
	 */
	public String insurance_price;

	public String getInsurance_num() {
		return insurance_num;
	}

	public void setInsurance_num(String insurance_num) {
		this.insurance_num = insurance_num;
	}

	public String getInsurance_price() {
		return insurance_price;
	}

	public void setInsurance_price(String insurance_price) {
		this.insurance_price = insurance_price;
	}

	public String getPasBirthday() {
		return pasBirthday;
	}

	public void setPasBirthday(String pasBirthday) {
		this.pasBirthday = pasBirthday;
	}

	public String getPasBirthNo() {
		return pasBirthNo;
	}

	public void setPasBirthNo(String pasBirthNo) {
		this.pasBirthNo = pasBirthNo;
	}

	public String getPasName() {
		return pasName;
	}

	public void setPasName(String pasName) {
		this.pasName = pasName;
	}

	public String getPasType() {
		return pasType;
	}

	public void setPasType(String pasType) {
		this.pasType = pasType;
	}

	public String getPasYE() {
		return pasYE;
	}

	public void setPasYE(String pasYE) {
		this.pasYE = pasYE;
	}
}
