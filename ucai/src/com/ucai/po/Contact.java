package com.ucai.po;

/**
 * 联系人
 * @author lixu
 *
 */
public class Contact {
    /** 
     * 姓名
     */
    public String conName;
    /**
     * 固定电话
     */
    public String conTel;
    /**
     * 移动电话
     */
    public String conMobile;
    /**
     * 电子邮件
     */
    public String conEmail;
    /**
     * 联系地址
     */
    public String conAddress;
    /**
     * 配送地址
     */
    public String psAddress;
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getConTel() {
		return conTel;
	}
	public void setConTel(String conTel) {
		this.conTel = conTel;
	}
	public String getConMobile() {
		return conMobile;
	}
	public void setConMobile(String conMobile) {
		this.conMobile = conMobile;
	}
	public String getConEmail() {
		return conEmail;
	}
	public void setConEmail(String conEmail) {
		this.conEmail = conEmail;
	}
	public String getConAddress() {
		return conAddress;
	}
	public void setConAddress(String conAddress) {
		this.conAddress = conAddress;
	}
	public String getPsAddress() {
		return psAddress;
	}
	public void setPsAddress(String psAddress) {
		this.psAddress = psAddress;
	}
    
}
