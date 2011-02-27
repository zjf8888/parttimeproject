package com.ucai.po;

/**
 * 错误信息存储类
 * @author lin
 *
 */
public class Errinfo {
	
	/**
	 * 错误码
	 */
	private String code;
	/**
	 * 描述信息
	 */
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
