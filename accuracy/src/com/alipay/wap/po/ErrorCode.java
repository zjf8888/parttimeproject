package com.alipay.wap.po;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
/**
 * 错误码存储类
 * @author 李卓林
 *
 */
@XObject("err")
public class ErrorCode {
	/**
	 * 主错误码
	 */
	@XNode("code")
	private String code;

	/**
	 * 子错误码
	 */
	@XNode("sub_code")
	private String subCode;

	/**
	 * 错误信息
	 */
	@XNode("msg")
	private String msg;

	/**
	 * 错误详情
	 */
	@XNode("detail")
	private String detail;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
