package com.ucai.po;
/**
 * 联系人信息类
 * @author lin
 *
 */
public class LinkMan {
	/**
	 * 联系人姓名 
	 */
	private String l_Name;
	/**
	 * 移动电话
	 */
	private String l_Mobile;
	/**
	 * 固定电话
	 */
	private String l_Phone;
	/**
	 * e_mail
	 */
	private String l_Email;
	/**
	 * 联系地址
	 */
	private String l_Address;
	/**
	 * 联系人会员号
	 */
	private String l_UserId;
	/**
	 * 联系人备注
	 */
	private String l_Remark;

	public String getL_Address() {
		return l_Address;
	}

	public void setL_Address(String address) {
		l_Address = address;
	}

	public String getL_Email() {
		return l_Email;
	}

	public void setL_Email(String email) {
		l_Email = email;
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

	public String getL_Phone() {
		return l_Phone;
	}

	public void setL_Phone(String phone) {
		l_Phone = phone;
	}

	public String getL_Remark() {
		return l_Remark;
	}

	public void setL_Remark(String remark) {
		l_Remark = remark;
	}

	public String getL_UserId() {
		return l_UserId;
	}

	public void setL_UserId(String userId) {
		l_UserId = userId;
	}
}
